package net.tslat.aoa3.content.world.spawner;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.tslat.aoa3.common.registration.entity.AoAMiscEntities;
import net.tslat.aoa3.content.entity.misc.PixonEntity;
import net.tslat.smartbrainlib.util.RandomUtil;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class PixonSpawner implements AoACustomSpawner {
	public static final Codec<PixonSpawner> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			IntProvider.CODEC.fieldOf("spawn_interval").forGetter(spawner -> spawner.spawnInterval),
			IntProvider.CODEC.fieldOf("extra_delay_per_spawn").forGetter(spawner -> spawner.extraDelayPerSpawn),
			Codec.FLOAT.fieldOf("chance_per_player").forGetter(spawner -> spawner.chancePerPlayer),
			IntProvider.CODEC.fieldOf("spawns_per_player").forGetter(spawner -> spawner.spawnsPerPlayer),
			Biome.LIST_CODEC.optionalFieldOf("biome_blacklist").forGetter(spawner -> spawner.biomeBlacklist),
			ResourceLocation.CODEC.listOf().xmap(Set::copyOf, List::copyOf).fieldOf("dimension_blacklist").forGetter(spawner -> spawner.dimensionBlacklist),
			SpawnData.CustomSpawnRules.CODEC.optionalFieldOf("spawn_rules").forGetter(spawner -> spawner.spawnRules),
			Codec.BOOL.fieldOf("spawn_in_flat_world").forGetter(spawner -> spawner.spawnInFlatWorld)
	).apply(builder, PixonSpawner::new));

	private final IntProvider spawnInterval;
	private final IntProvider extraDelayPerSpawn;
	private final float chancePerPlayer;
	private final IntProvider spawnsPerPlayer;
	private final Optional<HolderSet<Biome>> biomeBlacklist;
	private final Set<ResourceLocation> dimensionBlacklist;
	private final Optional<SpawnData.CustomSpawnRules> spawnRules;
	private final boolean spawnInFlatWorld;

	private long nextSpawnTick = -1;

	public PixonSpawner(IntProvider spawnInterval, IntProvider extraDelayPerSpawn, float chancePerPlayer, IntProvider spawnsPerPlayer, Optional<HolderSet<Biome>> biomeBlacklist, Set<ResourceLocation> dimensionBlacklist, Optional<SpawnData.CustomSpawnRules> spawnRules, boolean spawnInFlatWorld) {
		this.spawnInterval = spawnInterval;
		this.extraDelayPerSpawn = extraDelayPerSpawn;
		this.chancePerPlayer = chancePerPlayer;
		this.spawnsPerPlayer = spawnsPerPlayer;
		this.biomeBlacklist = biomeBlacklist;
		this.dimensionBlacklist = dimensionBlacklist;
		this.spawnRules = spawnRules;
		this.spawnInFlatWorld = spawnInFlatWorld;
	}

	@Override
	public boolean shouldAddToDimension(ServerLevel level) {
		return (!level.isFlat() || this.spawnInFlatWorld) && !this.dimensionBlacklist.contains(level.dimension().location());
	}

	@Override
	public AoACustomSpawner copy() {
		return new PixonSpawner(this.spawnInterval, this.extraDelayPerSpawn, this.chancePerPlayer, this.spawnsPerPlayer, this.biomeBlacklist, this.dimensionBlacklist, this.spawnRules, this.spawnInFlatWorld);
	}

	@Override
	public int tick(ServerLevel level, boolean spawnHostiles, boolean spawnPassives) {
		if (this.nextSpawnTick > level.getGameTime() || !spawnPassives || !level.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING))
			return 0;

		RandomSource random = level.getRandom();
		this.nextSpawnTick = level.getGameTime() + this.spawnInterval.sample(random);

		return doSpawning(level, random);
	}

	private int doSpawning(ServerLevel level, RandomSource random) {
		int count = 0;

		for (ServerPlayer pl : level.getPlayers(pl -> !pl.isSpectator() && pl.isAlive())) {
			if (level.getRandom().nextFloat() >= this.chancePerPlayer)
				continue;

			for (BlockPos spawnPos : findNearbySpawnPositions(level, random, pl.blockPosition(), 40, 128, this.spawnsPerPlayer.sample(random))) {
				if (this.spawnRules.isPresent()) {
					SpawnData.CustomSpawnRules spawnRules = this.spawnRules.get();

					if (!spawnRules.blockLightLimit().isValueInRange(level.getBrightness(LightLayer.BLOCK, spawnPos)) || !spawnRules.skyLightLimit().isValueInRange(level.getBrightness(LightLayer.SKY, spawnPos)))
						continue;
				}

				PixonEntity pixon = AoAMiscEntities.PIXON.get().create(level, null, null, spawnPos, MobSpawnType.NATURAL, false, false);

				if (pixon == null)
					continue;

 				pixon.finalizeSpawn(level, level.getCurrentDifficultyAt(pixon.blockPosition()));
				level.addFreshEntityWithPassengers(pixon);

				this.nextSpawnTick += this.extraDelayPerSpawn.sample(random);
				count++;
			}
		}

		return count;
	}

	private List<BlockPos> findNearbySpawnPositions(ServerLevel level, RandomSource random, BlockPos centerPos, int minRadius, int maxRadius, int maxTries) {
		EntityType<PixonEntity> entityType = AoAMiscEntities.PIXON.get();
		List<BlockPos> positions = new ObjectArrayList<>();
		RandomUtil.EasyRandom rand = new RandomUtil.EasyRandom(random);
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		float radius = Math.max(maxRadius - minRadius, 0);

		for (int i = 0; i < maxTries; i++) {
			double xAdjust = rand.randomValueBetween(-radius, radius);
			double zAdjust = rand.randomValueBetween(-radius, radius);
			int newX = (int)Math.floor(centerPos.getX() + xAdjust + radius * Math.signum(xAdjust));
			int newZ = (int)Math.floor(centerPos.getZ() + zAdjust + radius * Math.signum(zAdjust));

			mutablePos.set(level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, mutablePos.set(newX, 0, newZ)));

			if (level.dimensionType().hasCeiling()) {
				while (!level.getBlockState(mutablePos.move(Direction.DOWN)).isAir()) {}
				while (level.getBlockState(mutablePos.move(Direction.DOWN)).isAir() && mutablePos.getY() > level.getMinBuildHeight()) {}
			}

			if (level.noCollision(entityType.getAABB(mutablePos.getX() + 0.5d, mutablePos.getY(), mutablePos.getZ() + 0.5d)))
				positions.add(mutablePos.immutable());
		}

		return positions;
	}
}

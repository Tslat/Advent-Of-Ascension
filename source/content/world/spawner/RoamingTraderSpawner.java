package net.tslat.aoa3.content.world.spawner;

import com.mojang.datafixers.util.Pair;
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
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.smartbrainlib.util.RandomUtil;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class RoamingTraderSpawner implements AoACustomSpawner {
	public static final Codec<RoamingTraderSpawner> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			IntProvider.CODEC.fieldOf("spawn_interval").forGetter(spawner -> spawner.spawnInterval),
			IntProvider.CODEC.fieldOf("extra_delay_per_spawn").forGetter(spawner -> spawner.extraDelayPerSpawn),
			Codec.FLOAT.fieldOf("chance_per_player").forGetter(spawner -> spawner.chancePerPlayer),
			IntProvider.CODEC.fieldOf("spawns_per_player").forGetter(spawner -> spawner.spawnsPerPlayer),
			Biome.LIST_CODEC.optionalFieldOf("biome_blacklist").forGetter(spawner -> spawner.biomeBlacklist),
			ResourceLocation.CODEC.listOf().xmap(Set::copyOf, List::copyOf).fieldOf("dimension_blacklist").forGetter(spawner -> spawner.dimensionBlacklist),
			WeightedRandomList.codec(MobSpawnSettings.SpawnerData.CODEC).fieldOf("spawns").forGetter(spawner -> spawner.spawns),
			SpawnData.CustomSpawnRules.CODEC.optionalFieldOf("spawn_rules").forGetter(spawner -> spawner.spawnRules),
			Codec.BOOL.fieldOf("spawn_in_flat_world").forGetter(spawner -> spawner.spawnInFlatWorld)
	).apply(builder, RoamingTraderSpawner::new));

	private final IntProvider spawnInterval;
	private final IntProvider extraDelayPerSpawn;
	private final float chancePerPlayer;
	private final IntProvider spawnsPerPlayer;
	private final Optional<HolderSet<Biome>> biomeBlacklist;
	private final Set<ResourceLocation> dimensionBlacklist;
	private final WeightedRandomList<MobSpawnSettings.SpawnerData> spawns;
	private final Optional<SpawnData.CustomSpawnRules> spawnRules;
	private final boolean spawnInFlatWorld;

	private long nextSpawnTick = -1;

	public RoamingTraderSpawner(IntProvider spawnInterval, IntProvider extraDelayPerSpawn, float chancePerPlayer, IntProvider spawnsPerPlayer, Optional<HolderSet<Biome>> biomeBlacklist, Set<ResourceLocation> dimensionBlacklist, WeightedRandomList<MobSpawnSettings.SpawnerData> spawns, Optional<SpawnData.CustomSpawnRules> spawnRules, boolean spawnInFlatWorld) {
		this.spawnInterval = spawnInterval;
		this.extraDelayPerSpawn = extraDelayPerSpawn;
		this.chancePerPlayer = chancePerPlayer;
		this.spawnsPerPlayer = spawnsPerPlayer;
		this.biomeBlacklist = biomeBlacklist;
		this.dimensionBlacklist = dimensionBlacklist;
		this.spawns = spawns;
		this.spawnRules = spawnRules;
		this.spawnInFlatWorld = spawnInFlatWorld;
	}

	@Override
	public boolean shouldAddToDimension(ServerLevel level) {
		return (!level.isFlat() || this.spawnInFlatWorld) && !this.dimensionBlacklist.contains(level.dimension().location());
	}

	@Override
	public AoACustomSpawner copy() {
		return new RoamingTraderSpawner(this.spawnInterval, this.extraDelayPerSpawn, this.chancePerPlayer, this.spawnsPerPlayer, this.biomeBlacklist, this.dimensionBlacklist, this.spawns, this.spawnRules, this.spawnInFlatWorld);
	}

	@Override
	public int tick(ServerLevel level, boolean spawnHostiles, boolean spawnPassives) {
		if (this.nextSpawnTick > level.getGameTime() || !spawnPassives || !level.getGameRules().getBoolean(GameRules.RULE_DO_TRADER_SPAWNING))
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

			for (Pair<EntityType<?>, BlockPos> spawn : findNearbySpawnPositions(level, random, pl.blockPosition(), 20, 64, this.spawnsPerPlayer.sample(random))) {
				BlockPos pos = spawn.getSecond();

				if (this.spawnRules.isPresent()) {
					if (!spawn.getFirst().getCategory().isFriendly() && level.getDifficulty() == Difficulty.PEACEFUL)
						continue;

					SpawnData.CustomSpawnRules spawnRules = this.spawnRules.get();

					if (!spawnRules.blockLightLimit().isValueInRange(level.getBrightness(LightLayer.BLOCK, pos)) || !spawnRules.skyLightLimit().isValueInRange(level.getBrightness(LightLayer.SKY, pos)))
						continue;
				}

				Entity entity = spawn.getFirst().create(level, null, null, pos, MobSpawnType.NATURAL, false, false);

				if (entity == null)
					continue;

				if (entity instanceof Mob mob) {
					if (this.spawnRules.isEmpty() && !mob.checkSpawnRules(level, MobSpawnType.NATURAL) || !mob.checkSpawnObstruction(level))
						continue;

					ForgeEventFactory.onFinalizeSpawn(mob, level, level.getCurrentDifficultyAt(pos), MobSpawnType.NATURAL, null, null);
				}

				level.addFreshEntityWithPassengers(entity);

				this.nextSpawnTick += this.extraDelayPerSpawn.sample(random);
				count++;
			}
		}

		return count;
	}

	private List<Pair<EntityType<?>, BlockPos>> findNearbySpawnPositions(ServerLevel level, RandomSource random, BlockPos centerPos, int minRadius, int maxRadius, int maxTries) {
		List<Pair<EntityType<?>, BlockPos>> positions = new ObjectArrayList<>();
		RandomUtil.EasyRandom rand = new RandomUtil.EasyRandom(random);
		BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos();
		float radius = Math.max(maxRadius - minRadius, 0);

		for (int i = 0; i < maxTries; i++) {
			this.spawns.getRandom(random).ifPresent(spawn -> {
				double xAdjust = rand.randomValueBetween(-radius, radius);
				double zAdjust = rand.randomValueBetween(-radius, radius);
				int newX = (int)Math.floor(centerPos.getX() + xAdjust + radius * Math.signum(xAdjust));
				int newZ = (int)Math.floor(centerPos.getZ() + zAdjust + radius * Math.signum(zAdjust));
				SpawnPlacements.Type placementType = SpawnPlacements.getPlacementType(spawn.type);
				Heightmap.Types heightmap = SpawnPlacements.getHeightmapType(spawn.type);

				mutablePos.set(level.getHeightmapPos(heightmap, mutablePos.set(newX, 0, newZ)));

				if (level.dimensionType().hasCeiling()) {
					while (!level.getBlockState(mutablePos.move(Direction.DOWN)).isAir()) {}
					while (level.getBlockState(mutablePos.move(Direction.DOWN)).isAir() && mutablePos.getY() > level.getMinBuildHeight()) {}
				}

				if (NaturalSpawner.isSpawnPositionOk(placementType, level, mutablePos, spawn.type)) {
					if (level.noCollision(spawn.type.getAABB(mutablePos.getX() + 0.5d, mutablePos.getY(), mutablePos.getZ() + 0.5d)))
						positions.add(Pair.of(spawn.type, mutablePos.immutable()));
				}
			});
		}

		return positions;
	}
}

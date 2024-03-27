package net.tslat.aoa3.content.world.spawner;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.SpawnData;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.neoforged.neoforge.event.EventHooks;
import net.tslat.aoa3.common.registration.entity.AoACustomSpawners;

public class RoamingTraderSpawner implements AoACustomSpawner<Entity> {
	public static final Codec<RoamingTraderSpawner> CODEC = RecordCodecBuilder.create(builder -> builder.group(
			AoACustomSpawner.GENERIC_SETTINGS_CODEC.fieldOf("base_settings").forGetter(spawner -> spawner.baseSettings),
			ExtraCodecs.lazyInitializedCodec(() -> WeightedRandomList.codec(MobSpawnSettings.SpawnerData.CODEC)).fieldOf("spawns").forGetter(spawner -> spawner.spawns)
	).apply(builder, RoamingTraderSpawner::new));

	private final GenericSettings baseSettings;
	private final WeightedRandomList<MobSpawnSettings.SpawnerData> spawns;

	private long nextSpawnTick = -1;

	public RoamingTraderSpawner(GenericSettings baseSettings, WeightedRandomList<MobSpawnSettings.SpawnerData> spawns) {
		this.baseSettings = baseSettings;
		this.spawns = spawns;
	}

	@Override
	public boolean shouldAddToDimension(ServerLevel level) {
		if (level.isFlat() && !this.baseSettings.spawnInSuperflat())
			return false;

		return this.baseSettings.whitelistMode() == this.baseSettings.dimensions().contains(level.dimension());
	}

	@Override
	public AoACustomSpawner<Entity> copy() {
		return new RoamingTraderSpawner(this.baseSettings, this.spawns);
	}

	@Override
	public Type getType() {
		return AoACustomSpawners.ROAMING_TRADER.get();
	}

	@Override
	public int tick(ServerLevel level, boolean spawnHostiles, boolean spawnPassives) {
		if (this.nextSpawnTick > level.getGameTime() || !spawnPassives || !level.getGameRules().getBoolean(GameRules.RULE_DO_TRADER_SPAWNING))
			return 0;

		RandomSource random = level.getRandom();
		this.nextSpawnTick = level.getGameTime() + this.baseSettings.spawnInterval().sample(random);

		return doSpawning(level, random);
	}

	private int doSpawning(ServerLevel level, RandomSource random) {
		int count = 0;

		for (ServerPlayer pl : level.getPlayers(pl -> !pl.isSpectator() && pl.isAlive())) {
			if (level.getRandom().nextFloat() >= this.baseSettings.chancePerPlayer())
				continue;

			for (Pair<EntityType<Entity>, BlockPos> spawn : findNearbySpawnPositions(level, random, pl.blockPosition(), 20, 64, this.baseSettings.spawnAttemptsPerPlayer().sample(random), () -> this.spawns.getRandom(random).map(data -> (EntityType<Entity>)data.type))) {
				BlockPos pos = spawn.right();

				if (this.baseSettings.spawnRules().isPresent()) {
					if (!spawn.left().getCategory().isFriendly() && level.getDifficulty() == Difficulty.PEACEFUL)
						continue;

					SpawnData.CustomSpawnRules spawnRules = this.baseSettings.spawnRules().get();

					if (!spawnRules.blockLightLimit().isValueInRange(level.getBrightness(LightLayer.BLOCK, pos)) || !spawnRules.skyLightLimit().isValueInRange(level.getBrightness(LightLayer.SKY, pos)))
						continue;
				}

				Entity entity = spawn.left().create(level, null, null, pos, MobSpawnType.NATURAL, false, false);

				if (entity == null)
					continue;

				if (entity instanceof Mob mob) {
					if (this.baseSettings.spawnRules().isEmpty() && !mob.checkSpawnRules(level, MobSpawnType.NATURAL) || !mob.checkSpawnObstruction(level))
						continue;

					EventHooks.onFinalizeSpawn(mob, level, level.getCurrentDifficultyAt(pos), MobSpawnType.NATURAL, null, null);
				}

				level.addFreshEntityWithPassengers(entity);

				this.nextSpawnTick += this.baseSettings.extraDelayPerSpawn().sample(random);
				count++;
			}
		}

		return count;
	}
}

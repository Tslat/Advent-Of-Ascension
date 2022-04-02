package net.tslat.aoa3.content.world.spawner;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeHooks;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.util.RandomUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TraderSpawner implements CustomSpawner {
	private static final HashMap<ResourceKey<Biome>, List<MobSpawnSettings.SpawnerData>> SPAWNS = new HashMap<ResourceKey<Biome>, List<MobSpawnSettings.SpawnerData>>();

	private int spawnCooldown = 24000;

	@Override
	public int tick(ServerLevel world, boolean spawnHostiles, boolean spawnPassives) {
		if (this.spawnCooldown-- > 12000 || !spawnPassives || !world.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING))
			return 0;

		if (RandomUtil.oneInNChance(Math.max(1, spawnCooldown))) {
			spawnCooldown = RandomUtil.randomNumberBetween(12000, 36000);

			return doSpawning(world);
		}

		return 0;
	}

	private int doSpawning(ServerLevel world) {
		int count = 0;

		for (ServerPlayer pl : world.getPlayers(pl -> !pl.isSpectator() && pl.isAlive())) {
			for (Pair<EntityType<? extends Mob>, BlockPos> spawn : findNearbySpawnPositions(world, pl.blockPosition(), 64, 2)) {
				BlockPos pos = spawn.getSecond();
				Mob entity = spawn.getFirst().create(world, null, null, null, pos, MobSpawnType.NATURAL, false, false);

				if (entity == null)
					continue;

				int eventResult = ForgeHooks.canEntitySpawn(entity, world, pos.getX(), pos.getY(), pos.getZ(), null, MobSpawnType.NATURAL);

				if (eventResult != -1 && (eventResult == 1 || (entity.checkSpawnRules(world, MobSpawnType.NATURAL) && entity.checkSpawnObstruction(world)))) {
					world.addFreshEntity(entity);

					spawnCooldown += 3600;
					count++;
				}
			}
		}

		return count;
	}

	private List<Pair<EntityType<? extends Mob>, BlockPos>> findNearbySpawnPositions(ServerLevel world, BlockPos centerPos, int radius, int maxTries) {
		ArrayList<Pair<EntityType<? extends Mob>, BlockPos>> positions = new ArrayList<Pair<EntityType<? extends Mob>, BlockPos>>();

		for (int i = 0; i < maxTries; i++) {
			int x = centerPos.getX() + RandomUtil.randomNumberBetween(-radius, radius);
			int z = centerPos.getZ() + RandomUtil.randomNumberBetween(-radius, radius);
			BlockPos pos = new BlockPos(x, centerPos.getY(), z);
			Biome biome = world.getBiome(pos).value();
			Optional<ResourceKey<Biome>> key = world.getServer().registryAccess().registry(Registry.BIOME_REGISTRY).get().getResourceKey(biome);

			if (key.isEmpty() || !SPAWNS.containsKey(key.get()))
				continue;

			EntityType<? extends Mob> trader = (EntityType<? extends Mob>)WeightedRandom.getRandomItem(RandomUtil.RANDOM.source(), SPAWNS.get(key.get())).get().type;
			SpawnPlacements.Type placementType = SpawnPlacements.getPlacementType(trader);
			Heightmap.Types heightmap = SpawnPlacements.getHeightmapType(trader);
			pos = new BlockPos(x, world.getHeight(heightmap, x, z), z);

			if (!NaturalSpawner.isSpawnPositionOk(placementType, world, pos, trader))
				continue;

			if (world.noCollision(trader.getAABB(pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d)))
				positions.add(Pair.of(trader, pos));
		}

		return positions;
	}

	public static void addSpawn(ResourceKey<Biome> biome, MobSpawnSettings.SpawnerData spawnData) {
		if (SPAWNS.containsKey(biome)) {
			SPAWNS.get(biome).add(spawnData);
		}
		else {
			ArrayList<MobSpawnSettings.SpawnerData> spawnList = new ArrayList<MobSpawnSettings.SpawnerData>();

			spawnList.add(spawnData);

			SPAWNS.put(biome, spawnList);
		}
	}

	public static boolean isValidSpawnWorld(ServerLevel world) {
		ResourceKey<Level> key = world.dimension();

		return key == Level.OVERWORLD || key == Level.NETHER || (key.location().getNamespace().equals(AdventOfAscension.MOD_ID) && key != AoADimensions.NOWHERE.key);
	}
}

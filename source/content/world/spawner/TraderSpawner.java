package net.tslat.aoa3.content.world.spawner;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.spawner.ISpecialSpawner;
import net.minecraft.world.spawner.WorldEntitySpawner;
import net.minecraftforge.common.ForgeHooks;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.util.RandomUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class TraderSpawner implements ISpecialSpawner {
	private static final HashMap<RegistryKey<Biome>, List<MobSpawnInfo.Spawners>> SPAWNS = new HashMap<RegistryKey<Biome>, List<MobSpawnInfo.Spawners>>();

	private int spawnCooldown = 24000;

	@Override
	public int tick(ServerWorld world, boolean spawnHostiles, boolean spawnPassives) {
		if (this.spawnCooldown-- > 12000 || !spawnPassives || !world.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING))
			return 0;

		if (RandomUtil.oneInNChance(Math.max(1, spawnCooldown))) {
			spawnCooldown = RandomUtil.randomNumberBetween(12000, 36000);

			return doSpawning(world);
		}

		return 0;
	}

	private int doSpawning(ServerWorld world) {
		int count = 0;

		for (ServerPlayerEntity pl : world.getPlayers(pl -> !pl.isSpectator() && pl.isAlive())) {
			for (Pair<EntityType<? extends MobEntity>, BlockPos> spawn : findNearbySpawnPositions(world, pl.blockPosition(), 64, 2)) {
				BlockPos pos = spawn.getSecond();
				MobEntity entity = spawn.getFirst().create(world, null, null, null, pos, SpawnReason.NATURAL, false, false);

				if (entity == null)
					continue;

				int eventResult = ForgeHooks.canEntitySpawn(entity, world, pos.getX(), pos.getY(), pos.getZ(), null, SpawnReason.NATURAL);

				if (eventResult != -1 && (eventResult == 1 || (entity.checkSpawnRules(world, SpawnReason.NATURAL) && entity.checkSpawnObstruction(world)))) {
					world.addFreshEntity(entity);

					spawnCooldown += 3600;
					count++;
				}
			}
		}

		return count;
	}

	private List<Pair<EntityType<? extends MobEntity>, BlockPos>> findNearbySpawnPositions(ServerWorld world, BlockPos centerPos, int radius, int maxTries) {
		ArrayList<Pair<EntityType<? extends MobEntity>, BlockPos>> positions = new ArrayList<Pair<EntityType<? extends MobEntity>, BlockPos>>();

		for (int i = 0; i < maxTries; i++) {
			int x = centerPos.getX() + RandomUtil.randomNumberBetween(-radius, radius);
			int z = centerPos.getZ() + RandomUtil.randomNumberBetween(-radius, radius);
			BlockPos pos = new BlockPos(x, centerPos.getY(), z);
			Biome biome = world.getBiome(pos);
			Optional<RegistryKey<Biome>> key = world.getServer().registryAccess().registry(Registry.BIOME_REGISTRY).get().getResourceKey(biome);

			if (!key.isPresent() || !SPAWNS.containsKey(key.get()))
				continue;

			EntityType<? extends MobEntity> trader = (EntityType<? extends MobEntity>)WeightedRandom.getRandomItem(RandomUtil.RANDOM.source(), SPAWNS.get(key.get())).type;
			EntitySpawnPlacementRegistry.PlacementType placementType = EntitySpawnPlacementRegistry.getPlacementType(trader);
			Heightmap.Type heightmap = EntitySpawnPlacementRegistry.getHeightmapType(trader);
			pos = new BlockPos(x, world.getHeight(heightmap, x, z), z);

			if (!WorldEntitySpawner.isSpawnPositionOk(placementType, world, pos, trader))
				continue;

			if (world.noCollision(trader.getAABB(pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d)))
				positions.add(Pair.of(trader, pos));
		}

		return positions;
	}

	public static void addSpawn(RegistryKey<Biome> biome, MobSpawnInfo.Spawners spawnData) {
		if (SPAWNS.containsKey(biome)) {
			SPAWNS.get(biome).add(spawnData);
		}
		else {
			ArrayList<MobSpawnInfo.Spawners> spawnList = new ArrayList<MobSpawnInfo.Spawners>();

			spawnList.add(spawnData);

			SPAWNS.put(biome, spawnList);
		}
	}

	public static boolean isValidSpawnWorld(ServerWorld world) {
		RegistryKey<World> key = world.dimension();

		return key == World.OVERWORLD || key == World.NETHER || (key.location().getNamespace().equals(AdventOfAscension.MOD_ID) && key != AoADimensions.NOWHERE.key);
	}
}

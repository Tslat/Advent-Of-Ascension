package net.tslat.aoa3.world.spawner;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.*;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.AxisAlignedBB;
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
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.util.RandomUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PixonSpawner implements ISpecialSpawner {
	private static final HashMap<RegistryKey<Biome>, List<MobSpawnInfo.Spawners>> SPAWNS = new HashMap<RegistryKey<Biome>, List<MobSpawnInfo.Spawners>>();

	private int spawnCooldown = 1200;

	@Override
	public int tick(ServerWorld world, boolean spawnHostiles, boolean spawnPassives) {
		if (this.spawnCooldown-- > 1200 || !spawnPassives || !world.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING))
			return 0;

		if (RandomUtil.oneInNChance(Math.max(1, spawnCooldown))) {
			spawnCooldown = RandomUtil.randomNumberBetween(6000, 12000);

			return doSpawning(world);
		}

		return 0;
	}

	private int doSpawning(ServerWorld world) {
		int count = 0;

		for (ServerPlayerEntity pl : world.getPlayers(pl -> !pl.isSpectator() && pl.isAlive())) {
			for (Pair<EntityType<? extends MobEntity>, BlockPos> spawn : findNearbySpawnPositions(world, pl.blockPosition(), 64, 10)) {
				BlockPos pos = spawn.getSecond();
				MobEntity entity = spawn.getFirst().create(world, null, null, null, pos, SpawnReason.NATURAL, false, false);

				if (entity == null)
					continue;

				int eventResult = ForgeHooks.canEntitySpawn(entity, world, pos.getX(), pos.getY(), pos.getZ(), null, SpawnReason.NATURAL);

				if (eventResult != -1 && (eventResult == 1 || (entity.checkSpawnRules(world, SpawnReason.NATURAL) && entity.checkSpawnObstruction(world)))) {
					world.addFreshEntity(entity);

					spawnCooldown += 1000;
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

			EntityType<? extends MobEntity> pixon = (EntityType<? extends MobEntity>)WeightedRandom.getRandomItem(RandomUtil.RANDOM.source(), SPAWNS.get(key.get())).type;
			EntitySpawnPlacementRegistry.PlacementType placementType = EntitySpawnPlacementRegistry.getPlacementType(pixon);
			Heightmap.Type heightmap = EntitySpawnPlacementRegistry.getHeightmapType(pixon);
			pos = new BlockPos(x, world.getHeight(heightmap, x, z), z);

			if (world.getEntities(pixon, new AxisAlignedBB(x - 5, pos.getY() - 5, z - 5, x + 5, pos.getY() + 5, z + 5), LivingEntity::isAlive).size() > 3)
				continue;

			if (WorldEntitySpawner.isSpawnPositionOk(placementType, world, pos, pixon))
				positions.add(Pair.of(pixon, pos));
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

		return key == World.OVERWORLD
				|| key == AoADimensions.HAVEN.key || key == AoADimensions.RUNANDOR.key || key == AoADimensions.CANDYLAND.key
				|| key == AoADimensions.SHYRELANDS.key || key == AoADimensions.ABYSS.key || key == AoADimensions.DUSTOPIA.key
				|| key == AoADimensions.LBOREAN.key || key == AoADimensions.LELYETIA.key || key == AoADimensions.MYSTERIUM.key;
	}
}

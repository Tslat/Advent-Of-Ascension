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
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.ForgeHooks;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.library.builder.EntityPredicate;
import net.tslat.smartbrainlib.api.util.EntityRetrievalUtil;
import net.tslat.aoa3.util.RandomUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class PixonSpawner implements CustomSpawner {
	private static final HashMap<ResourceKey<Biome>, List<MobSpawnSettings.SpawnerData>> SPAWNS = new HashMap<ResourceKey<Biome>, List<MobSpawnSettings.SpawnerData>>();
	private int spawnCooldown = 1200;

	@Override
	public int tick(ServerLevel world, boolean spawnHostiles, boolean spawnPassives) {
		if (this.spawnCooldown-- > 1200 || !spawnPassives || !world.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING))
			return 0;

		if (RandomUtil.oneInNChance(Math.max(1, spawnCooldown))) {
			spawnCooldown = RandomUtil.randomNumberBetween(6000, 12000);

			return doSpawning(world);
		}

		return 0;
	}

	private int doSpawning(ServerLevel world) {
		int count = 0;

		for (ServerPlayer pl : world.getPlayers(pl -> !pl.isSpectator() && pl.isAlive())) {
			for (Pair<EntityType<? extends Mob>, BlockPos> spawn : findNearbySpawnPositions(world, pl.blockPosition(), 64, 10)) {
				BlockPos pos = spawn.getSecond();
				Mob entity = spawn.getFirst().create(world, null, null, null, pos, MobSpawnType.NATURAL, false, false);

				if (entity == null)
					continue;

				int eventResult = ForgeHooks.canEntitySpawn(entity, world, pos.getX(), pos.getY(), pos.getZ(), null, MobSpawnType.NATURAL);

				if (eventResult != -1 && (eventResult == 1 || (entity.checkSpawnRules(world, MobSpawnType.NATURAL) && entity.checkSpawnObstruction(world)))) {
					world.addFreshEntity(entity);

					spawnCooldown += 1000;
					count++;
				}
			}
		}

		return count;
	}

	private List<Pair<EntityType<? extends Mob>, BlockPos>> findNearbySpawnPositions(ServerLevel world, BlockPos centerPos, int radius, int maxTries) {
		ArrayList<Pair<EntityType<? extends Mob>, BlockPos>> positions = new ArrayList<>();

		for (int i = 0; i < maxTries; i++) {
			int x = centerPos.getX() + RandomUtil.randomNumberBetween(-radius, radius);
			int z = centerPos.getZ() + RandomUtil.randomNumberBetween(-radius, radius);
			BlockPos pos = new BlockPos(x, centerPos.getY(), z);
			Biome biome = world.getBiome(pos).value();
			Optional<ResourceKey<Biome>> key = world.getServer().registryAccess().registry(Registry.BIOME_REGISTRY).get().getResourceKey(biome);

			if (!key.isPresent() || !SPAWNS.containsKey(key.get()))
				continue;

			EntityType<? extends Mob> pixon = (EntityType<? extends Mob>)WeightedRandom.getRandomItem(RandomUtil.RANDOM.getSource(), SPAWNS.get(key.get())).get().type;
			SpawnPlacements.Type placementType = SpawnPlacements.getPlacementType(pixon);
			Heightmap.Types heightmap = SpawnPlacements.getHeightmapType(pixon);
			pos = new BlockPos(x, world.getRandom().nextInt(world.getHeight(heightmap, x, z) + 1), z);

			if (EntityRetrievalUtil.getEntities(world, new AABB(x - 5, pos.getY() - 5, z - 5, x + 5, pos.getY() + 5, z + 5), new EntityPredicate<>().is(pixon).isAlive()).size() > 3)
				continue;

			if (!NaturalSpawner.isSpawnPositionOk(placementType, world, pos, pixon))
				continue;

			if (world.noCollision(pixon.getAABB(pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d)))
				positions.add(Pair.of(pixon, pos));
		}

		return positions;
	}

	public static void addSpawn(ResourceKey<Biome> biome, MobSpawnSettings.SpawnerData spawnData) {
		if (SPAWNS.containsKey(biome)) {
			SPAWNS.get(biome).add(spawnData);
		}
		else {
			ArrayList<MobSpawnSettings.SpawnerData> spawnList = new ArrayList<>();

			spawnList.add(spawnData);

			SPAWNS.put(biome, spawnList);
		}
	}

	public static boolean isValidSpawnWorld(ServerLevel world) {
		ResourceKey<Level> key = world.dimension();

		return key == Level.OVERWORLD
				|| key == AoADimensions.HAVEN.key || key == AoADimensions.RUNANDOR.key || key == AoADimensions.CANDYLAND.key
				|| key == AoADimensions.SHYRELANDS.key || key == AoADimensions.ABYSS.key || key == AoADimensions.DUSTOPIA.key
				|| key == AoADimensions.LBOREAN.key || key == AoADimensions.LELYETIA.key || key == AoADimensions.MYSTERIUM.key;
	}
}

package net.tslat.aoa3.content.world.spawner;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.NaturalSpawner;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.registries.RegistryObject;
import net.tslat.aoa3.common.registration.entity.AoANpcs;
import net.tslat.smartbrainlib.util.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public class RoamingTradersSpawner implements CustomSpawner {
	private static final RegistryObject<EntityType<? extends Mob>>[] ENTITIES = new RegistryObject[] {AoANpcs.LOTTOMAN, AoANpcs.UNDEAD_HERALD};

	private long nextSpawn = -1;

	@Override
	public int tick(ServerLevel world, boolean spawnHostiles, boolean spawnPassives) {
		if (this.nextSpawn > world.getGameTime() || !spawnPassives || !world.getGameRules().getBoolean(GameRules.RULE_DO_TRADER_SPAWNING))
			return 0;

		this.nextSpawn = world.getGameTime() + RandomUtil.randomNumberBetween(12000, 36000);

		return doSpawning(world);
	}

	private int doSpawning(ServerLevel world) {
		int count = 0;

		for (ServerPlayer pl : world.getPlayers(pl -> !pl.isSpectator() && pl.isAlive())) {
			for (Pair<EntityType<? extends Mob>, BlockPos> spawn : findNearbySpawnPositions(world, pl.blockPosition(), 64, 1)) {
				BlockPos pos = spawn.getSecond();
				Mob entity = spawn.getFirst().create(world, null, null, pos, MobSpawnType.NATURAL, false, false);

				if (entity == null)
					continue;

				int eventResult = 0;//ForgeHooks.canEntitySpawn(entity, world, pos.getX(), pos.getY(), pos.getZ(), null, MobSpawnType.NATURAL); TODO when forge catches up?

				if (eventResult != -1 && (eventResult == 1 || (entity.checkSpawnRules(world, MobSpawnType.NATURAL) && entity.checkSpawnObstruction(world)))) {
					world.addFreshEntity(entity);

					this.nextSpawn += RandomUtil.randomNumberBetween(8000, 12000);
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
			EntityType<? extends Mob> entity = RandomUtil.getRandomSelection(ENTITIES).get();
			SpawnPlacements.Type placementType = SpawnPlacements.getPlacementType(entity);
			Heightmap.Types heightmap = SpawnPlacements.getHeightmapType(entity);
			BlockPos pos = new BlockPos(x, world.getHeight(heightmap, x, z), z);

			if (!NaturalSpawner.isSpawnPositionOk(placementType, world, pos, entity))
				continue;

			if (world.noCollision(entity.getAABB(pos.getX() + 0.5d, pos.getY(), pos.getZ() + 0.5d)))
				positions.add(Pair.of(entity, pos));
		}

		return positions;
	}
}

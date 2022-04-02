package net.tslat.aoa3.content.world.spawner;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.CustomSpawner;
import net.minecraft.world.level.GameRules;
import net.tslat.aoa3.util.RandomUtil;

import javax.annotation.Nullable;

public class VisualentSpawner implements CustomSpawner {
	private int spawnCooldown = 18000;

	@Override
	public int tick(ServerLevel world, boolean spawnHostiles, boolean spawnPassives) {
		if (this.spawnCooldown-- > 6000 || !spawnHostiles || !world.getGameRules().getBoolean(GameRules.RULE_DOMOBSPAWNING) || world.getDifficulty() == Difficulty.PEACEFUL)
			return 0;

		if (RandomUtil.oneInNChance(Math.max(1, spawnCooldown))) {
			spawnCooldown = 6000;

			/*if (world.getEntities(AoAMobs.VISUALENT.get(), Entity::isAlive).isEmpty()) {
				Player pl = world.getRandomPlayer();

				if (pl == null)
					return 0;

				BlockPos spawnPos = findNearbySpawnPosition(world, pl.blockPosition(), 64, 10);

				if (spawnPos == null)
					return 0;

				VisualentEntity visualent = (VisualentEntity)AoAMobs.VISUALENT.get().create(world, null, null, null, spawnPos, MobSpawnType.NATURAL, false, false);

				if (visualent == null)
					return 0;

				int eventResult = ForgeHooks.canEntitySpawn(visualent, world, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), null, MobSpawnType.NATURAL);

				if (eventResult != -1 && (eventResult == 1 || (visualent.checkSpawnRules(world, MobSpawnType.NATURAL) && visualent.checkSpawnObstruction(world)))) {
					world.addFreshEntity(visualent);

					return 1;
				}
			}*/
		}

		return 0;
	}

	@Nullable
	private BlockPos findNearbySpawnPosition(ServerLevel world, BlockPos centerPos, int radius, int maxTries) {
		for (int i = 0; i < maxTries; i++) {
			BlockPos pos = RandomUtil.getRandomPositionWithinRange(centerPos, radius, radius, radius);

			if (!world.getWorldBorder().isWithinBounds(pos))
				continue;

			if (centerPos.distSqr(pos) < 20 * 20)
				continue;

			//if (world.noCollision(AoAMobs.VISUALENT.get().getAABB(pos.getX(), pos.getY(), pos.getZ())))
			//	return pos;
		}

		return null;
	}
}

package net.tslat.aoa3.util;

import net.minecraft.core.Vec3i;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.smartbrainlib.util.RandomUtil;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public final class EntitySpawningUtil {
	@Nullable
	public static <T extends Entity> T spawnEntity(ServerLevel level, EntityType<T> entityType) {
		return spawnEntity(level, entityType, new Vec3(0, 0, 0), MobSpawnType.COMMAND, entity -> {});
	}

	@Nullable
	public static <T extends Entity> T spawnEntity(ServerLevel level, EntityType<T> entityType, Vec3i position, MobSpawnType spawnReason) {
		return spawnEntity(level, entityType, Vec3.atBottomCenterOf(position), spawnReason, entity -> {});
	}

	@Nullable
	public static <T extends Entity> T spawnEntity(ServerLevel level, EntityType<T> entityType, Vec3 position, MobSpawnType spawnReason) {
		return spawnEntity(level, entityType, position, spawnReason, entity -> {});
	}

	@Nullable
	public static <T extends Entity> T spawnEntity(ServerLevel level, EntityType<T> entityType, Vec3i position, Consumer<T> entityModifications) {
		return spawnEntity(level, entityType, Vec3.atBottomCenterOf(position), MobSpawnType.COMMAND, entityModifications);
	}

	@Nullable
	public static <T extends Entity> T spawnEntity(ServerLevel level, EntityType<T> entityType, Vec3 position, Consumer<T> entityModifications) {
		return spawnEntity(level, entityType, position, MobSpawnType.COMMAND, entityModifications);
	}

	@Nullable
	public static <T extends Entity> T spawnEntity(ServerLevel level, EntityType<T> entityType, Vec3i position, MobSpawnType spawnReason, Consumer<T> entityModifications) {
		return spawnEntity(level, entityType, Vec3.atBottomCenterOf(position), spawnReason, entityModifications);
	}

	@Nullable
	public static <T extends Entity> T spawnEntity(ServerLevel level, EntityType<T> entityType, Vec3 position, MobSpawnType spawnReason, Consumer<T> entityModifications) {
		T entity = entityType.create(level);

		if (entity == null)
			return null;

		entity.moveTo(position.x(), position.y(), position.z(), Mth.wrapDegrees((float)RandomUtil.randomValueUpTo(360d)), 0);
		entityModifications.accept(entity);

		if (entity instanceof Mob mob) {
			mob.yHeadRot = mob.getYRot();
			mob.yBodyRot = mob.getYRot();
			ForgeEventFactory.onFinalizeSpawn(mob, level, level.getCurrentDifficultyAt(entity.blockPosition()), spawnReason, null, null);
			mob.playAmbientSound();
		}

		level.addFreshEntityWithPassengers(entity);

		return entity;
	}
}

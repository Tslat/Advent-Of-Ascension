package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

import java.util.UUID;

public class FireflyShotEntity extends BaseEnergyShot {
	public final UUID lastTargetUUID;

	public FireflyShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);

		this.lastTargetUUID = null;
	}

	public FireflyShotEntity(Level world) {
		super(AoAProjectiles.FIREFLY_SHOT.get(), world);

		this.lastTargetUUID = null;
	}

	public FireflyShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, FireflyShotEntity shot, UUID lastTargetUUID, double motionX, double motionY, double motionZ) {
		super(AoAProjectiles.FIREFLY_SHOT.get(), shooter, weapon, shot.getX(), shot.getY(), shot.getZ(), motionX, motionY, motionZ);

		this.lastTargetUUID = lastTargetUUID;
	}

	public FireflyShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, FireflyShotEntity shot, double motionX, double motionY, double motionZ) {
		super(AoAProjectiles.FIREFLY_SHOT.get(), shooter, weapon, shot.getX(), shot.getY(), shot.getZ(), motionX, motionY, motionZ);

		this.lastTargetUUID = null;
	}

	public FireflyShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.FIREFLY_SHOT.get(), shooter, weapon, maxAge);

		this.lastTargetUUID = null;
	}

	public FireflyShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.FIREFLY_SHOT.get(), world, x, y, z);

		this.lastTargetUUID = null;
	}

	@Override
	public float getGravity() {
		return 0.05f;
	}
}

package net.tslat.aoa3.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

import java.util.UUID;

public class FireflyShotEntity extends BaseEnergyShot {
	public final UUID lastTargetUUID;

	public FireflyShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);

		this.lastTargetUUID = null;
	}

	public FireflyShotEntity(World world) {
		super(AoAEntities.Projectiles.FIREFLY_SHOT.get(), world);

		this.lastTargetUUID = null;
	}

	public FireflyShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, FireflyShotEntity shot, UUID lastTargetUUID, double motionX, double motionY, double motionZ) {
		super(AoAEntities.Projectiles.FIREFLY_SHOT.get(), shooter, weapon, shot.getPosX(), shot.getPosY(), shot.getPosZ(), motionX, motionY, motionZ);

		this.lastTargetUUID = lastTargetUUID;
	}

	public FireflyShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, FireflyShotEntity shot, double motionX, double motionY, double motionZ) {
		super(AoAEntities.Projectiles.FIREFLY_SHOT.get(), shooter, weapon, shot.getPosX(), shot.getPosY(), shot.getPosZ(), motionX, motionY, motionZ);

		this.lastTargetUUID = null;
	}

	public FireflyShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.FIREFLY_SHOT.get(), shooter, weapon, maxAge);

		this.lastTargetUUID = null;
	}

	public FireflyShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.FIREFLY_SHOT.get(), world, x, y, z);

		this.lastTargetUUID = null;
	}

	@Override
	public float getGravityVelocity() {
		return 0.05f;
	}
}

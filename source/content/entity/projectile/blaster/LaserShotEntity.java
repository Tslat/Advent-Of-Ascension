package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class LaserShotEntity extends BaseEnergyShot {
	public LaserShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public LaserShotEntity(Level world) {
		super(AoAProjectiles.LASER_SHOT.get(), world);
	}

	public LaserShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.LASER_SHOT.get(), shooter, weapon, maxAge);
	}

	public LaserShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.LASER_SHOT.get(), world, x, y, z);
	}
}

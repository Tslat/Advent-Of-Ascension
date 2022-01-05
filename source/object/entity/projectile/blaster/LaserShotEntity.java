package net.tslat.aoa3.object.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.object.item.EnergyProjectileWeapon;

public class LaserShotEntity extends BaseEnergyShot {
	public LaserShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public LaserShotEntity(World world) {
		super(AoAEntities.Projectiles.LASER_SHOT.get(), world);
	}

	public LaserShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.LASER_SHOT.get(), shooter, weapon, maxAge);
	}

	public LaserShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.LASER_SHOT.get(), world, x, y, z);
	}
}

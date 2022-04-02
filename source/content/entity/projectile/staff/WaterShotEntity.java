package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class WaterShotEntity extends BaseEnergyShot {
	public WaterShotEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public WaterShotEntity(Level world) {
		super(AoAProjectiles.WATER_SHOT.get(), world);
	}

	public WaterShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.WATER_SHOT.get(), shooter, weapon, maxAge);
	}

	public WaterShotEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.WATER_SHOT.get(), world, x, y, z);
	}
}

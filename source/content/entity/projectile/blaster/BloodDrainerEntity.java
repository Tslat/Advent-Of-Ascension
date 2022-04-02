package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class BloodDrainerEntity extends BaseEnergyShot {
	public BloodDrainerEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public BloodDrainerEntity(Level world) {
		super(AoAProjectiles.BLOOD_DRAINER.get(), world);
	}

	public BloodDrainerEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.BLOOD_DRAINER.get(), shooter, weapon, maxAge);
	}

	public BloodDrainerEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.BLOOD_DRAINER.get(), world, x, y, z);
	}
}

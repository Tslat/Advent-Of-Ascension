package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class BloodDrainerEntity extends BaseEnergyShot {
	public BloodDrainerEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public BloodDrainerEntity(World world) {
		super(AoAEntities.Projectiles.BLOOD_DRAINER.get(), world);
	}

	public BloodDrainerEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.BLOOD_DRAINER.get(), shooter, weapon, maxAge);
	}

	public BloodDrainerEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.BLOOD_DRAINER.get(), world, x, y, z);
	}
}

package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class PowerRayEntity extends BaseEnergyShot {
	public PowerRayEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public PowerRayEntity(World world) {
		super(AoAEntities.Projectiles.POWER_RAY.get(), world);
	}

	public PowerRayEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.POWER_RAY.get(), shooter, weapon, maxAge);
	}

	public PowerRayEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.POWER_RAY.get(), world, x, y, z);
	}
}

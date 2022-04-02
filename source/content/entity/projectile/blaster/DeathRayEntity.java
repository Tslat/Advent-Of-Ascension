package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class DeathRayEntity extends BaseEnergyShot {
	public DeathRayEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public DeathRayEntity(Level world) {
		super(AoAProjectiles.DEATH_RAY.get(), world);
	}

	public DeathRayEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.DEATH_RAY.get(), shooter, weapon, maxAge);
	}

	public DeathRayEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.DEATH_RAY.get(), world, x, y, z);
	}
}

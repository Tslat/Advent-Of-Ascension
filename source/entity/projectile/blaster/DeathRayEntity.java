package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class DeathRayEntity extends BaseEnergyShot {
	public DeathRayEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public DeathRayEntity(World world) {
		super(AoAEntities.Projectiles.DEATH_RAY.get(), world);
	}

	public DeathRayEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.DEATH_RAY.get(), shooter, weapon, maxAge);
	}

	public DeathRayEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.DEATH_RAY.get(), world, x, y, z);
	}
}

package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class CelestialFallEntity extends BaseEnergyShot {
	public CelestialFallEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public CelestialFallEntity(Level world) {
		super(AoAProjectiles.CELESTIAL_FALL.get(), world);
	}

	public CelestialFallEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(AoAProjectiles.CELESTIAL_FALL.get(), shooter, weapon, posX, posY, posZ, velocity);
	}

	public CelestialFallEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.CELESTIAL_FALL.get(), world, x, y, z);
	}
}

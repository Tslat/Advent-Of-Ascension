package net.tslat.aoa3.object.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.item.EnergyProjectileWeapon;

public class CelestialFallEntity extends BaseEnergyShot {
	public CelestialFallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public CelestialFallEntity(World world) {
		super(AoAEntities.Projectiles.CELESTIAL_FALL.get(), world);
	}

	public CelestialFallEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(AoAEntities.Projectiles.CELESTIAL_FALL.get(), shooter, weapon, posX, posY, posZ, velocity);
	}

	public CelestialFallEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.CELESTIAL_FALL.get(), world, x, y, z);
	}
}

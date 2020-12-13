package net.tslat.aoa3.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class NightmareFallEntity extends BaseEnergyShot {
	public NightmareFallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public NightmareFallEntity(World world) {
		super(AoAEntities.Projectiles.NIGHTMARE_FALL.get(), world);
	}

	public NightmareFallEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(AoAEntities.Projectiles.NIGHTMARE_FALL.get(), shooter, weapon, posX, posY, posZ, velocity);
	}

	public NightmareFallEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.NIGHTMARE_FALL.get(), world, x, y, z);
	}
}

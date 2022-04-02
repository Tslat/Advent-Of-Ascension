package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class MoonlightFallEntity extends BaseEnergyShot {
	public MoonlightFallEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public MoonlightFallEntity(Level world) {
		super(AoAProjectiles.MOONLIGHT_FALL.get(), world);
	}

	public MoonlightFallEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(AoAProjectiles.METEOR_FALL.get(), shooter, weapon, posX, posY, posZ, velocity);
	}

	public MoonlightFallEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.METEOR_FALL.get(), world, x, y, z);
	}
}

package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class LunarFallEntity extends BaseEnergyShot {
	public LunarFallEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public LunarFallEntity(Level world) {
		super(AoAProjectiles.LUNAR_FALL.get(), world);
	}

	public LunarFallEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(AoAProjectiles.LUNAR_FALL.get(), shooter, weapon, posX, posY, posZ, velocity);
	}

	public LunarFallEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.LUNAR_FALL.get(), world, x, y, z);
	}
}

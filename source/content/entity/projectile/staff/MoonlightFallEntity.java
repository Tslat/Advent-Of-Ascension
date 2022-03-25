package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class MoonlightFallEntity extends BaseEnergyShot {
	public MoonlightFallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public MoonlightFallEntity(World world) {
		super(AoAEntities.Projectiles.MOONLIGHT_FALL.get(), world);
	}

	public MoonlightFallEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(AoAEntities.Projectiles.METEOR_FALL.get(), shooter, weapon, posX, posY, posZ, velocity);
	}

	public MoonlightFallEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.METEOR_FALL.get(), world, x, y, z);
	}
}

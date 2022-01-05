package net.tslat.aoa3.object.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.item.EnergyProjectileWeapon;

public class MeteorFallEntity extends BaseEnergyShot {
	public MeteorFallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public MeteorFallEntity(World world) {
		super(AoAEntities.Projectiles.METEOR_FALL.get(), world);
	}

	public MeteorFallEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(AoAEntities.Projectiles.METEOR_FALL.get(), shooter, weapon, posX, posY, posZ, velocity);
	}

	public MeteorFallEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.METEOR_FALL.get(), world, x, y, z);
	}
}

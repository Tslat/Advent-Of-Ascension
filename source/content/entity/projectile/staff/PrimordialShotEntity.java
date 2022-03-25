package net.tslat.aoa3.content.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class PrimordialShotEntity extends BaseEnergyShot {
	public PrimordialShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public PrimordialShotEntity(World world) {
		super(AoAEntities.Projectiles.PRIMORDIAL_SHOT.get(), world);
	}

	public PrimordialShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.PRIMORDIAL_SHOT.get(), shooter, weapon, maxAge);
	}

	public PrimordialShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.PRIMORDIAL_SHOT.get(), world, x, y, z);
	}
}

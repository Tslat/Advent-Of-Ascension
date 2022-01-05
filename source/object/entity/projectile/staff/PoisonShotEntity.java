package net.tslat.aoa3.object.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.item.EnergyProjectileWeapon;

public class PoisonShotEntity extends BaseEnergyShot {
	public PoisonShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public PoisonShotEntity(World world) {
		super(AoAEntities.Projectiles.POISON_SHOT.get(), world);
	}

	public PoisonShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.POISON_SHOT.get(), shooter, weapon, maxAge);
	}

	public PoisonShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.POISON_SHOT.get(), world, x, y, z);
	}
}

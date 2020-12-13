package net.tslat.aoa3.entity.projectile.staff;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class TangleFallEntity extends BaseEnergyShot {
	public TangleFallEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public TangleFallEntity(World world) {
		super(AoAEntities.Projectiles.TANGLE_FALL.get(), world);
	}

	public TangleFallEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, double posX, double posY, double posZ, float velocity) {
		super(AoAEntities.Projectiles.TANGLE_FALL.get(), shooter, weapon, posX, posY, posZ, velocity);
	}

	public TangleFallEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.TANGLE_FALL.get(), world, x, y, z);
	}
}

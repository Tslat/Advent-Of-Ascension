package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class AtomizerBounceEntity extends BaseEnergyShot {
	public AtomizerBounceEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}
	
	public AtomizerBounceEntity(Level world) {
		super(AoAProjectiles.ATOMIZER_BOUNCE.get(), world);
	}

	public AtomizerBounceEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, AtomizerShotEntity shot, double motionX, double motionY, double motionZ) {
		super(AoAProjectiles.ATOMIZER_BOUNCE.get(), shooter, weapon, shot.getX(), shot.getY(), shot.getZ(), motionX, motionY, motionZ);
	}

	public AtomizerBounceEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.ATOMIZER_BOUNCE.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}
}

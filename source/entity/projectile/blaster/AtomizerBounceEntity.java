package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class AtomizerBounceEntity extends BaseEnergyShot {
	public AtomizerBounceEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public AtomizerBounceEntity(World world) {
		super(AoAEntities.Projectiles.ATOMIZER_BOUNCE.get(), world);
	}

	public AtomizerBounceEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, AtomizerShotEntity shot, double motionX, double motionY, double motionZ) {
		super(AoAEntities.Projectiles.ATOMIZER_BOUNCE.get(), shooter, weapon, shot.getX(), shot.getY(), shot.getZ(), motionX, motionY, motionZ);
	}

	public AtomizerBounceEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.ATOMIZER_BOUNCE.get(), world, x, y, z);
	}

	@Override
	public float getGravity() {
		return 0.1f;
	}
}

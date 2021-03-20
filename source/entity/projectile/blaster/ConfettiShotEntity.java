package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class ConfettiShotEntity extends BaseEnergyShot {
	public ConfettiShotEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}
	
	public ConfettiShotEntity(World world) {
		super(AoAEntities.Projectiles.CONFETTI_SHOT.get(), world);
	}

	public ConfettiShotEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.CONFETTI_SHOT.get(), shooter, weapon, maxAge);
	}

	public ConfettiShotEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.CONFETTI_SHOT.get(), world, x, y, z);
	}

	@Override
	protected void onHit(RayTraceResult result) {
		if (!level.isClientSide)
			remove();
	}
}

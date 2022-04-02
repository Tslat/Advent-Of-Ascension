package net.tslat.aoa3.content.entity.projectile.blaster;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.item.EnergyProjectileWeapon;

public class ConfettiClusterEntity extends BaseEnergyShot {
	public ConfettiClusterEntity(EntityType<? extends ThrowableProjectile> entityType, Level world) {
		super(entityType, world);
	}

	public ConfettiClusterEntity(Level world) {
		super(AoAProjectiles.CONFETTI_CLUSTER.get(), world);
	}

	public ConfettiClusterEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAProjectiles.CONFETTI_CLUSTER.get(), shooter, weapon, maxAge);
	}

	public ConfettiClusterEntity(Level world, double x, double y, double z) {
		super(AoAProjectiles.CONFETTI_CLUSTER.get(), world, x, y, z);
	}

	@Override
	protected void onHit(HitResult result) {
		if (!level.isClientSide)
			discard();
	}
}

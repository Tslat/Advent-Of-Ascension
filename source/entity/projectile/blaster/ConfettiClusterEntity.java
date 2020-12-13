package net.tslat.aoa3.entity.projectile.blaster;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.item.EnergyProjectileWeapon;

public class ConfettiClusterEntity extends BaseEnergyShot {
	public ConfettiClusterEntity(EntityType<? extends ThrowableEntity> entityType, World world) {
		super(entityType, world);
	}

	public ConfettiClusterEntity(World world) {
		super(AoAEntities.Projectiles.CONFETTI_CLUSTER.get(), world);
	}

	public ConfettiClusterEntity(LivingEntity shooter, EnergyProjectileWeapon weapon, int maxAge) {
		super(AoAEntities.Projectiles.CONFETTI_CLUSTER.get(), shooter, weapon, maxAge);
	}

	public ConfettiClusterEntity(World world, double x, double y, double z) {
		super(AoAEntities.Projectiles.CONFETTI_CLUSTER.get(), world, x, y, z);
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote)
			remove();
	}
}

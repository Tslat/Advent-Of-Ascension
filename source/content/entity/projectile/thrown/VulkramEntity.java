package net.tslat.aoa3.content.entity.projectile.thrown;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.ThrownItemProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;

public class VulkramEntity extends ThrownItemProjectile {
	public VulkramEntity(EntityType<? extends VulkramEntity> entityType, Level level) {
		super(entityType, level);
	}

	public VulkramEntity(EntityType<? extends VulkramEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public VulkramEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.VULKRAM.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.05f;
	}

	@Override
	protected void doEntityImpact(EntityHitResult rayTrace, Entity hitEntity) {
		if (DamageUtil.doProjectileAttack(getShooter(), this, hitEntity, getShotContext().damage()) && getShooter() instanceof LivingEntity shooter)
			EntityUtil.healEntity(shooter, 1);
	}
}

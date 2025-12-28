package net.tslat.aoa3.content.entity.projectile.thrown;

import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.entity.AoAProjectiles;
import net.tslat.aoa3.content.entity.projectile.base.ThrownItemProjectile;
import net.tslat.aoa3.content.entity.projectile.base.WeaponFiringContext;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.tme.api.object.builder.EffectBuilder;

public class GooBallEntity extends ThrownItemProjectile {
	public GooBallEntity(EntityType<? extends GooBallEntity> entityType, Level level) {
		super(entityType, level);
	}

	public GooBallEntity(EntityType<? extends GooBallEntity> entityType, Level level, WeaponFiringContext context) {
		super(entityType, level, context);
	}

	public GooBallEntity(Level level, WeaponFiringContext context) {
		this(AoAProjectiles.GOO_BALL.get(), level, context);
	}

	@Override
	public double getDefaultGravity() {
		return 0.05f;
	}

	@Override
	protected void doEntityImpact(EntityHitResult rayTrace, Entity hitEntity) {
		if (DamageUtil.doProjectileAttack(getShooter(), this, hitEntity, getShotContext().damage()))
			EntityUtil.applyPotions(hitEntity, getOwner(), new EffectBuilder(MobEffects.MOVEMENT_SLOWDOWN, 60).level(2));

		level().playSound(null, getX(), getY(), getZ(), AoASounds.GOO_BALL_IMPACT.get(), SoundSource.PLAYERS, 1.0f, 1.0f);
	}
}

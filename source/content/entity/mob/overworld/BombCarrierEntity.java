package net.tslat.aoa3.content.entity.mob.overworld;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.base.AoARangedMob;
import net.tslat.aoa3.content.entity.projectile.mob.BaseMobProjectile;
import net.tslat.aoa3.content.entity.projectile.mob.BombCarrierDynamiteEntity;
import net.tslat.aoa3.library.builder.SoundBuilder;
import net.tslat.aoa3.util.PositionAndMotionUtil;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animation.AnimatableManager;

import javax.annotation.Nullable;

public class BombCarrierEntity extends AoARangedMob<BombCarrierEntity> {
	public BombCarrierEntity(EntityType<? extends BombCarrierEntity> entityType, Level world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
		return 1.53125f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_GOBLIN_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_GOBLIN_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_GOBLIN_HURT.get();
	}

	@Override
	protected int getPreAttackTime() {
		return 20;
	}

	@Override
	protected int getAttackSwingDuration() {
		return 52;
	}

	@Nullable
	@Override
	protected SoundEvent getShootSound() {
		return null;
	}

	@Override
	protected BaseMobProjectile getNewProjectileInstance() {
		return null;
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		BaseMobProjectile projectile = new BombCarrierDynamiteEntity(level, position(), this);

		projectile.setYRot(getYHeadRot());
		PositionAndMotionUtil.moveRelativeToFacing(projectile, -0.2f, 0, 0.6f);
		PositionAndMotionUtil.moveTowards(projectile, target.getEyePosition(), 0.65d, 4 - level.getDifficulty().getId());
		projectile.setDeltaMovement(PositionAndMotionUtil.accountForGravity(projectile.position(), projectile.getDeltaMovement(), target.position(), projectile.getGravity()));
		PositionAndMotionUtil.faceTowardsMotion(projectile);

		level.addFreshEntity(projectile);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
		controllers.add(
				DefaultAnimations.genericWalkIdleController(this),
				DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_THROW)
						.setSoundKeyframeHandler(event -> new SoundBuilder(AoASounds.LIGHT_FUSE).followEntity(this).category(SoundSource.HOSTILE).execute()));
	}
}

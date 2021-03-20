package net.tslat.aoa3.entity.mob.lborean;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAWaterMeleeMob;
import net.tslat.aoa3.util.DamageUtil;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class NeptunoEntity extends AoAWaterMeleeMob {
	private static final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("neptuno.idle", true);
	private static final AnimationBuilder SWIM_ANIMATION = new AnimationBuilder().addAnimation("neptuno.swim", true);
	private static final AnimationBuilder ATTACK_ANIMATION = new AnimationBuilder().addAnimation("neptuno.attack", false);

	public NeptunoEntity(EntityType<? extends WaterMobEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 2.71875f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_NEPTUNO_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_NEPTUNO_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_NEPTUNO_HURT.get();
	}

	@Override
	protected void onAttack(Entity target) {
		if (target instanceof LivingEntity)
			DamageUtil.doBodySlamKnockback((LivingEntity)target, this, 4, 1.5f, 4);
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(new AnimationController<NeptunoEntity>(this, "base_animations", 0, new AnimationController.IAnimationPredicate<NeptunoEntity>() {
			@Override
			public <P extends IAnimatable> PlayState test(AnimationEvent<P> animationEvent) {
				if (swinging) {
					animationEvent.getController().setAnimation(ATTACK_ANIMATION);

					return PlayState.CONTINUE;
				}
				else if (animationEvent.isMoving()) {
					animationEvent.getController().setAnimation(SWIM_ANIMATION);

					return PlayState.CONTINUE;
				}
				else {
					animationEvent.getController().setAnimation(IDLE_ANIMATION);

					return PlayState.CONTINUE;
				}
			}
		}));
	}
}

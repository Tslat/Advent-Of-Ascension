package net.tslat.aoa3.entity.mob.lunalus;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAFlyingMeleeMob;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class VisularEntity extends AoAFlyingMeleeMob {
	private static final AnimationBuilder IDLE_ANIMATION = new AnimationBuilder().addAnimation("visular.idle", true);
	private static final AnimationBuilder MOVE_ANIMATION = new AnimationBuilder().addAnimation("visular.move", true);
	private static final AnimationBuilder ATTACK_ANIMATION = new AnimationBuilder().addAnimation("visular.attack", false);

	public VisularEntity(EntityType<? extends FlyingEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.625f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_VISULAR_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_VISULAR_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_VISULAR_HURT.get();
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(new AnimationController<VisularEntity>(this, "base_animations", 0, new AnimationController.IAnimationPredicate<VisularEntity>() {
			@Override
			public <P extends IAnimatable> PlayState test(AnimationEvent<P> animationEvent) {
				if (swinging) {
					animationEvent.getController().setAnimation(ATTACK_ANIMATION);

					return PlayState.CONTINUE;
				}
				else if (animationEvent.isMoving()) {
					animationEvent.getController().setAnimation(MOVE_ANIMATION);

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

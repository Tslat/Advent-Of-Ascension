package net.tslat.aoa3.entity.mob.lborean;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.base.AoAWaterMeleeMob;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

import javax.annotation.Nullable;

public class AnglerEntity extends AoAWaterMeleeMob implements IAnimatable {
	private static final AnimationBuilder BITE_ANIMATION = new AnimationBuilder().addAnimation("angler.bite", false);
	private static final AnimationBuilder SWIM_ANIMATION = new AnimationBuilder().addAnimation("angler.swim", true);

	public AnglerEntity(EntityType<? extends WaterMobEntity> entityType, World world) {
		super(entityType, world);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.8f;
	}

	@Nullable
	@Override
	protected SoundEvent getAmbientSound() {
		return AoASounds.ENTITY_ANGLER_AMBIENT.get();
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return AoASounds.ENTITY_ANGLER_DEATH.get();
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return AoASounds.ENTITY_ANGLER_HURT.get();
	}

	@Override
	public int getCurrentSwingDuration() {
		return 20;
	}

	@Override
	public void registerControllers(AnimationData animationData) {
		animationData.addAnimationController(new AnimationController<AnglerEntity>(this, "base_animations", 0, new AnimationController.IAnimationPredicate<AnglerEntity>() {
			@Override
			public PlayState test(AnimationEvent<AnglerEntity> event) {
				if (swinging) {
					event.getController().setAnimation(BITE_ANIMATION);

					return PlayState.CONTINUE;
				}
				else if (event.isMoving()) {
					event.getController().setAnimation(SWIM_ANIMATION);

					return PlayState.CONTINUE;
				}

				return PlayState.STOP;
			}
		}));
	}
}

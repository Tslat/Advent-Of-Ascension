package net.tslat.aoa3.client.render;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.ForgeMod;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DefaultAnimations;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Predicate;

public final class AoAAnimations {
	public static final RawAnimation RECOVER = RawAnimation.begin().thenPlay("misc.rest");
	public static final RawAnimation EAT = RawAnimation.begin().thenPlay("misc.eat");
	public static final RawAnimation SUCCEED = RawAnimation.begin().thenPlay("misc.succeed");
	public static final RawAnimation INTERACT = RawAnimation.begin().thenPlay("misc.interact").thenPlay("misc.interact.hold");
	public static final RawAnimation INTERACT_END = RawAnimation.begin().thenPlay("misc.interact.end");

	public static final RawAnimation ATTACK_SPIN = RawAnimation.begin().thenPlay("attack.spin");
	public static final RawAnimation ATTACK_BLOCK = RawAnimation.begin().thenPlay("attack.block").thenPlay("attack.block.hold");
	public static final RawAnimation ATTACK_CHARGE = RawAnimation.begin().thenPlay("attack.charge").thenPlay("attack.charge.hold");
	public static final RawAnimation ATTACK_CHARGE_END = RawAnimation.begin().thenPlay("attack.charge.end");
	public static final RawAnimation ATTACK_POUNCE = RawAnimation.begin().thenPlay("attack.pounce");

	public static final RawAnimation ATTACK_SWIPE_LEFT = RawAnimation.begin().thenPlay("attack.swipe_left");
	public static final RawAnimation ATTACK_SWIPE_RIGHT = RawAnimation.begin().thenPlay("attack.swipe_right");
	public static final RawAnimation ATTACK_SHOOT_ALTERNATE = RawAnimation.begin().thenPlay("attack.shoot_alternate");

	public static final RawAnimation SWIM_SPRINT = RawAnimation.begin().thenLoop("move.swim_sprint");

	public static <T extends LivingEntity & GeoEntity> AnimationController<T> genericWalkRunSwimIdleController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				if (false && entity.isInWater() && entity.getFluidTypeHeight(ForgeMod.WATER_TYPE.get()) > entity.getFluidJumpThreshold()) { // Disable until Geckolib fixes fluid movement
					// TODO Fix Geckolib fluid movement?
					// Unsure, entity might be floating too high.
					// Think I already fixed it in Geckolib though
					event.setAnimation(DefaultAnimations.SWIM);
				}
				else if (entity.isSprinting()) {
					event.setAnimation(DefaultAnimations.RUN);
				}
				else {
					event.setAnimation(DefaultAnimations.WALK);
				}
			}
			else {
				event.setAnimation(DefaultAnimations.IDLE);
			}

			return PlayState.CONTINUE;
		});
	}

	public static <T extends LivingEntity & GeoAnimatable> AnimationController<T> genericAttackAnimation(T animatable, RawAnimation attackAnimation) {
		return new AnimationController<>(animatable, "Attack", 0, state -> {
			if (animatable.swinging)
				return state.setAndContinue(attackAnimation);

			state.getController().forceAnimationReset();

			return PlayState.STOP;
		});
	}

	public static <T extends LivingEntity & GeoEntity> AnimationController<T> dynamicAttackController(T entity, Function<AnimationState<T>, RawAnimation> animationSupplier) {
		return new AnimationController<T>(entity, "attacking", 0, state -> {
			if (entity.swinging)
				return state.setAndContinue(animationSupplier.apply(state));

			state.getController().forceAnimationReset();

			return PlayState.STOP;
		});
	}

	public static <T extends Entity & GeoEntity> AnimationController<T> genericSpawnController(T entity, int spawnTicks) {
		return new AnimationController<T>(entity, "spawning", 0, state -> {
			if (entity.tickCount < spawnTicks)
				return state.setAndContinue(DefaultAnimations.SPAWN);

			return PlayState.STOP;
		});
	}

	public static <T extends LivingEntity & GeoEntity> AnimationController<T> genericHeldPoseController(T entity, RawAnimation poseToHold, @Nullable RawAnimation animOnRelease, Predicate<T> posePredicate) {
		return new AnimationController<>(entity, "posing", 0, state -> {
			if (posePredicate.test(entity))
				return state.setAndContinue(poseToHold);

			if (animOnRelease != null && state.getController().getCurrentAnimation() != null)
				return state.setAndContinue(animOnRelease);

			return PlayState.STOP;
		});
	}
}

package net.tslat.aoa3.client.render;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.ForgeMod;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Predicate;

public final class AoAAnimations {
	public static final AnimationBuilder IDLE = new AnimationBuilder().addAnimation("misc.idle", ILoopType.EDefaultLoopTypes.LOOP);
	public static final AnimationBuilder LIVING = new AnimationBuilder().addAnimation("misc.living", ILoopType.EDefaultLoopTypes.LOOP);
	public static final AnimationBuilder RECOVER = new AnimationBuilder().addAnimation("misc.rest", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder EAT = new AnimationBuilder().addAnimation("misc.eat", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder SUCCEED = new AnimationBuilder().addAnimation("misc.succeed", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder SPAWN = new AnimationBuilder().addAnimation("misc.spawn", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder INTERACT = new AnimationBuilder().addAnimation("misc.interact", ILoopType.EDefaultLoopTypes.PLAY_ONCE).addAnimation("misc.interact.hold", ILoopType.EDefaultLoopTypes.LOOP);
	public static final AnimationBuilder INTERACT_END = new AnimationBuilder().addAnimation("misc.interact.end", ILoopType.EDefaultLoopTypes.PLAY_ONCE);

	public static final AnimationBuilder WALK = new AnimationBuilder().addAnimation("move.walk", ILoopType.EDefaultLoopTypes.LOOP);
	public static final AnimationBuilder RUN = new AnimationBuilder().addAnimation("move.run", ILoopType.EDefaultLoopTypes.LOOP);
	public static final AnimationBuilder FLY = new AnimationBuilder().addAnimation("move.fly", ILoopType.EDefaultLoopTypes.LOOP);
	public static final AnimationBuilder SWIM = new AnimationBuilder().addAnimation("move.swim", ILoopType.EDefaultLoopTypes.LOOP);

	public static final AnimationBuilder ATTACK_SWING = new AnimationBuilder().addAnimation("attack.swing", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder ATTACK_THROW = new AnimationBuilder().addAnimation("attack.throw", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder ATTACK_BITE = new AnimationBuilder().addAnimation("attack.bite", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder ATTACK_SLAM = new AnimationBuilder().addAnimation("attack.slam", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder ATTACK_STOMP = new AnimationBuilder().addAnimation("attack.stomp", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder ATTACK_STRIKE = new AnimationBuilder().addAnimation("attack.strike", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder ATTACK_SPIN = new AnimationBuilder().addAnimation("attack.spin", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder ATTACK_FLYING_BITE = new AnimationBuilder().addAnimation("attack.midair_bite", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder ATTACK_SHOOT = new AnimationBuilder().addAnimation("attack.shoot", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder ATTACK_BLOCK = new AnimationBuilder().addAnimation("attack.block", ILoopType.EDefaultLoopTypes.PLAY_ONCE).addAnimation("attack.block.hold", ILoopType.EDefaultLoopTypes.LOOP);
	public static final AnimationBuilder ATTACK_CHARGE = new AnimationBuilder().addAnimation("attack.charge", ILoopType.EDefaultLoopTypes.PLAY_ONCE).addAnimation("attack.charge.hold", ILoopType.EDefaultLoopTypes.LOOP);
	public static final AnimationBuilder ATTACK_CHARGE_END = new AnimationBuilder().addAnimation("attack.charge.end", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder ATTACK_POWERUP = new AnimationBuilder().addAnimation("attack.powerup", ILoopType.EDefaultLoopTypes.PLAY_ONCE);

	public static final AnimationBuilder ATTACK_SWIPE_LEFT = new AnimationBuilder().addAnimation("attack.swipe_left", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder ATTACK_SWIPE_RIGHT = new AnimationBuilder().addAnimation("attack.swipe_right", ILoopType.EDefaultLoopTypes.PLAY_ONCE);
	public static final AnimationBuilder ATTACK_SHOOT_ALTERNATE = new AnimationBuilder().addAnimation("attack.shoot_alternate", ILoopType.EDefaultLoopTypes.PLAY_ONCE);

	public static <T extends Entity & IAnimatable> AnimationController<T> genericLivingAnimation(T entity) {
		return new AnimationController<T>(entity, "living", 0, event -> {
			event.getController().setAnimation(LIVING);

			return PlayState.CONTINUE;
		});
	}

	public static <T extends Entity & IAnimatable> AnimationController<T> genericIdleController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			event.getController().setAnimation(IDLE);

			return PlayState.CONTINUE;
		});
	}

	public static <T extends Entity & IAnimatable> AnimationController<T> genericWalkController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				event.getController().setAnimation(WALK);

				return PlayState.CONTINUE;
			}

			return PlayState.STOP;
		});
	}

	public static <T extends Entity & IAnimatable> AnimationController<T> genericSwimController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				event.getController().setAnimation(SWIM);

				return PlayState.CONTINUE;
			}

			return PlayState.STOP;
		});
	}

	public static <T extends Entity & IAnimatable> AnimationController<T> genericSwimIdleController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				event.getController().setAnimation(SWIM);
			}
			else {
				event.getController().setAnimation(IDLE);
			}

			return PlayState.CONTINUE;
		});
	}

	public static <T extends Entity & IAnimatable> AnimationController<T> genericWalkIdleController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				event.getController().setAnimation(WALK);
			}
			else {
				event.getController().setAnimation(IDLE);
			}

			return PlayState.CONTINUE;
		});
	}

	public static <T extends Entity & IAnimatable> AnimationController<T> genericFlyController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			event.getController().setAnimation(FLY);

			return PlayState.CONTINUE;
		});
	}

	public static <T extends Entity & IAnimatable> AnimationController<T> genericFlyIdleController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				event.getController().setAnimation(FLY);
			}
			else {
				event.getController().setAnimation(IDLE);
			}

			return PlayState.CONTINUE;
		});
	}

	public static <T extends LivingEntity & IAnimatable> AnimationController<T> genericWalkRunIdleController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				if (entity.isSprinting()) {
					event.getController().setAnimation(RUN);
				}
				else {
					event.getController().setAnimation(WALK);
				}
			}
			else {
				event.getController().setAnimation(IDLE);
			}

			return PlayState.CONTINUE;
		});
	}

	public static <T extends LivingEntity & IAnimatable> AnimationController<T> genericWalkRunSwimIdleController(T entity) {
		return new AnimationController<T>(entity, "movement", 0, event -> {
			if (event.isMoving()) {
				if (false && entity.isInWater() && entity.getFluidTypeHeight(ForgeMod.WATER_TYPE.get()) > entity.getFluidJumpThreshold()) { // Disable until Geckolib fixes fluid movement
					event.getController().setAnimation(SWIM);
				}
				else if (entity.isSprinting()) {
					event.getController().setAnimation(RUN);
				}
				else {
					event.getController().setAnimation(WALK);
				}
			}
			else {
				event.getController().setAnimation(IDLE);
			}

			return PlayState.CONTINUE;
		});
	}

	public static <T extends LivingEntity & IAnimatable> AnimationController<T> genericAttackController(T entity, AnimationBuilder attackAnimation) {
		return new AnimationController<T>(entity, "attacking", 0, event -> {
			if (entity.swinging) {
				event.getController().setAnimation(attackAnimation);

				return PlayState.CONTINUE;
			}

			event.getController().markNeedsReload();
			return PlayState.STOP;
		});
	}

	public static <T extends LivingEntity & IAnimatable> AnimationController<T> dynamicAttackController(T entity, Function<AnimationEvent<T>, AnimationBuilder> animationSupplier) {
		return new AnimationController<T>(entity, "attacking", 0, event -> {
			if (entity.swinging) {
				event.getController().setAnimation(animationSupplier.apply(event));

				return PlayState.CONTINUE;
			}

			event.getController().markNeedsReload();
			return PlayState.STOP;
		});
	}

	public static <T extends LivingEntity & IAnimatable> AnimationController<T> customAttackController(T entity, AnimationController.IAnimationPredicate<T> controllerPredicate) {
		return new AnimationController<T>(entity, "attacking", 0, controllerPredicate);
	}

	public static <T extends Entity & IAnimatable> AnimationController<T> genericSpawnController(T entity, int spawnTicks) {
		return new AnimationController<T>(entity, "spawning", 0, event -> {
			if (entity.tickCount < spawnTicks) {
				event.getController().setAnimation(SPAWN);

				return PlayState.CONTINUE;
			}

			return PlayState.STOP;
		});
	}

	public static <T extends LivingEntity & IAnimatable> AnimationController<T> genericHeldPoseController(T entity, AnimationBuilder poseToHold, @Nullable AnimationBuilder animOnRelease, Predicate<T> posePredicate) {
		return new AnimationController<>(entity, "posing", 0, event -> {
			AnimationController<?> controller = event.getController();

			if (posePredicate.test(entity)) {
				controller.setAnimation(poseToHold);

				return PlayState.CONTINUE;
			}
			else if (animOnRelease != null && controller.getCurrentAnimation() != null) {
				controller.setAnimation(animOnRelease);

				return PlayState.CONTINUE;
			}

			return PlayState.STOP;
		});
	}
}

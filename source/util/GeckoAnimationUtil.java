package net.tslat.aoa3.util;

import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;

public class GeckoAnimationUtil {
	public static <T extends IAnimatable> AnimationController.IAnimationPredicate<T> loopedAnimationPredicate(String animationName) {
		return new AnimationController.IAnimationPredicate<T>() {
			@Override
			public <P extends IAnimatable> PlayState test(AnimationEvent<P> event) {
				event.getController().setAnimation(new AnimationBuilder().addAnimation(animationName, true));

				return PlayState.CONTINUE;
			}
		};
	}

	public static <T extends IAnimatable> void addMovementAnimation(AnimationData data, T target, String name, String animName) {
		data.addAnimationController(new AnimationController<T>(target, name, 0, (AnimationController.IAnimationPredicate<T>)loopedAnimationPredicate(animName)));
	}
}

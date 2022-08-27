package net.tslat.aoa3.common.misc;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.easing.EasingType;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

import java.util.Map;
import java.util.function.Function;

public class AoAAnimationController<T extends IAnimatable> extends AnimationController<T> {
	private final Map<String, AnimationBuilder> triggerableAnimations = new Object2ObjectOpenHashMap<>(1);

	private AnimationBuilder triggeredAnim = null;

	public AoAAnimationController(T animatable, String name, float transitionLengthTicks, IAnimationPredicate<T> animationPredicate) {
		super(animatable, name, transitionLengthTicks, animationPredicate);
	}

	public AoAAnimationController(T animatable, String name, float transitionLengthTicks, EasingType easingtype, IAnimationPredicate<T> animationPredicate) {
		super(animatable, name, transitionLengthTicks, easingtype, animationPredicate);
	}

	public AoAAnimationController(T animatable, String name, float transitionLengthTicks, Function<Double, Double> customEasingMethod, IAnimationPredicate<T> animationPredicate) {
		super(animatable, name, transitionLengthTicks, customEasingMethod, animationPredicate);
	}

	public AoAAnimationController<T> triggerable(String triggerName, AnimationBuilder animationBuilder) {
		this.triggerableAnimations.put(triggerName, animationBuilder);

		return this;
	}

	public void tryTrigger(String triggerName) {
		AnimationBuilder anim = this.triggerableAnimations.get(triggerName);

		if (anim != null)
			this.triggeredAnim = anim;
	}

	@Override
	protected PlayState testAnimationPredicate(AnimationEvent<T> event) {
		if (this.triggeredAnim != null) {
			if (this.currentAnimationBuilder != this.triggeredAnim)
				this.currentAnimation = null;

			setAnimation(this.triggeredAnim);

			if (event.getController().getAnimationState() != AnimationState.Stopped)
				return PlayState.CONTINUE;

			this.triggeredAnim = null;
			this.currentAnimationBuilder = new AnimationBuilder();
		}

		return super.testAnimationPredicate(event);
	}
}

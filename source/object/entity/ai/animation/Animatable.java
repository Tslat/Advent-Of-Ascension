package net.tslat.aoa3.object.entity.ai.animation;

import software.bernie.geckolib3.core.IAnimatable;

import java.util.HashMap;

public interface Animatable extends IAnimatable {
	HashMap<String, Integer> animationStates = new HashMap<String, Integer>();

	default void addAnimationState(String anim) {
		addAnimationState(anim, 0);
	}

	default void addAnimationStates(String... anims) {
		for (String anim : anims) {
			addAnimationState(anim, 0);
		}
	}

	default void addAnimationState(String anim, int defaultState) {
		animationStates.put(anim, defaultState);
	}

	default void setAnimationState(String anim, int state) {
		animationStates.computeIfPresent(anim, (key, value) -> state);
	}

	default void resetAnimationState(String anim) {
		setAnimationState(anim, 0);
	}

	default int getAnimationState(String anim) {
		return animationStates.getOrDefault(anim, -1);
	}
}

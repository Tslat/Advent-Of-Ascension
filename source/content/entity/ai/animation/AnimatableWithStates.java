package net.tslat.aoa3.content.entity.ai.animation;

import software.bernie.geckolib3.core.IAnimatable;

import java.util.Map;

public interface AnimatableWithStates extends IAnimatable {
	Map<String, Integer> getAnimationStates();

	default void addAnimationState(String anim) {
		addAnimationState(anim, 0);
	}

	default void addAnimationStates(String... anims) {
		for (String anim : anims) {
			addAnimationState(anim, 0);
		}
	}

	default void addAnimationState(String anim, int defaultState) {
		getAnimationStates().put(anim, defaultState);
	}

	default void setAnimationState(String anim, int state) {
		getAnimationStates().computeIfPresent(anim, (key, value) -> state);
	}

	default void resetAnimationState(String anim) {
		setAnimationState(anim, 0);
	}

	default int getAnimationState(String anim) {
		return getAnimationStates().getOrDefault(anim, -1);
	}
}

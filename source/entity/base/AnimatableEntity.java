package net.tslat.aoa3.entity.base;

import javax.annotation.Nullable;

public interface AnimatableEntity {
	@Nullable
	String getCurrentAnimation();

	int getCurrentAnimationTicks();

	void finishAnimation();

	void startAnimation(String animation);

	void resetAnimation();
}

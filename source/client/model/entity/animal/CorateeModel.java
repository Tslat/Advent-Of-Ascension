package net.tslat.aoa3.client.model.entity.animal;

import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.animal.CorateeEntity;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class CorateeModel extends DefaultedEntityGeoModel<CorateeEntity> {
	public CorateeModel() {
		super(AdventOfAscension.id("animal/lborean/coratee"));
	}

	@Override
	public void setCustomAnimations(CorateeEntity coratee, long instanceId, AnimationState<CorateeEntity> animationState) {
		super.setCustomAnimations(coratee, instanceId, animationState);

		if (coratee.isBaby()) {
			CoreGeoBone root = getAnimationProcessor().getBone("bone");
			CoreGeoBone head = getAnimationProcessor().getBone("head");

			root.setScaleX(0.5f);
			root.setScaleY(0.5f);
			root.setScaleZ(0.5f);
			head.setScaleX(1.5f);
			head.setScaleY(1.5f);
			head.setScaleZ(1.5f);
		}
	}
}

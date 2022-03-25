package net.tslat.aoa3.client.model.entity.animal;

import net.tslat.aoa3.client.model.entity.EntityGeoModel;
import net.tslat.aoa3.content.entity.animal.CorateeEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;

import javax.annotation.Nullable;

public class CorateeModel extends EntityGeoModel<CorateeEntity> {
	public CorateeModel() {
		super("animal/lborean/coratee");
	}

	@Override
	public void setLivingAnimations(CorateeEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);

		if (entity.isBaby()) {
			IBone root = getAnimationProcessor().getBone("bone");
			IBone head = getAnimationProcessor().getBone("head");

			root.setScaleX(0.5f);
			root.setScaleY(0.5f);
			root.setScaleZ(0.5f);
			head.setScaleX(1.5f);
			head.setScaleY(1.5f);
			head.setScaleZ(1.5f);
		}
	}
}

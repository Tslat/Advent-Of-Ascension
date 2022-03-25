package net.tslat.aoa3.client.model.entity.boss;

import net.tslat.aoa3.client.model.entity.EntityGeoModel;
import net.tslat.aoa3.content.entity.boss.KrorEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;

import javax.annotation.Nullable;

public class KrorModel extends EntityGeoModel<KrorEntity> {
	public KrorModel() {
		super("boss/kror/kror");
	}

	@Override
	public void setLivingAnimations(KrorEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);

		IBone root = getAnimationProcessor().getBone("body");

		root.setScaleX(1.25f);
		root.setScaleY(1.25f);
		root.setScaleZ(1.25f);
	}
}

package net.tslat.aoa3.client.render.entity.animal;

import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.animal.precasia.DeinotheriumEntity;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class DeinotheriumModel extends DefaultedEntityGeoModel<DeinotheriumEntity> {
	public DeinotheriumModel() {
		super(AdventOfAscension.id("animal/precasia/deinotherium"), true);
	}

	@Override
	public void setCustomAnimations(DeinotheriumEntity animatable, long instanceId, AnimationState<DeinotheriumEntity> animationState) {
		super.setCustomAnimations(animatable, instanceId, animationState);

		final boolean isBaby = animatable.isBaby();
		final float headMod = isBaby ? 1.5f : 1f;
		final float rootMod = isBaby ? 0.5f : 1f;

		getBone("bone").ifPresent(bone -> bone.updateScale(rootMod, rootMod, rootMod));
		getBone("head").ifPresent(bone -> bone.updateScale(headMod, headMod, headMod));
		getBone("bone2").ifPresent(bone -> bone.setHidden(isBaby));
	}
}

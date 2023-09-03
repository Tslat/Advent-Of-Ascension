package net.tslat.aoa3.client.render.entity.animal;

import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.mob.overworld.HorndronEntity;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class HorndronModel extends DefaultedEntityGeoModel<HorndronEntity> {
	public HorndronModel() {
		super(AdventOfAscension.id("animal/precasia/horndron"), true);
	}

	@Override
	public void setCustomAnimations(HorndronEntity animatable, long instanceId, AnimationState<HorndronEntity> animationState) {
		super.setCustomAnimations(animatable, instanceId, animationState);

		final boolean isBaby = animatable.isBaby();
		final float headMod = isBaby ? 0.75f : 1f;
		final float rootMod = isBaby ? 0.5f : 1f;

		getBone("bone").ifPresent(bone -> bone.updateScale(rootMod, rootMod, rootMod));
		getBone("head").ifPresent(bone -> bone.updateScale(headMod, headMod, headMod));
		getBone("horn1").ifPresent(bone -> bone.setHidden(isBaby));
		getBone("horn2").ifPresent(bone -> bone.setHidden(isBaby));
	}
}

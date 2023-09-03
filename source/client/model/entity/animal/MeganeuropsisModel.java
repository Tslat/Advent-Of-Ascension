package net.tslat.aoa3.client.model.entity.animal;

import net.minecraft.util.Mth;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.animal.MeganeuropsisEntity;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class MeganeuropsisModel extends DefaultedEntityGeoModel<MeganeuropsisEntity> {
	public MeganeuropsisModel() {
		super(AdventOfAscension.id("mob/precasia/meganeuropsis"));
	}

	@Override
	public void setCustomAnimations(MeganeuropsisEntity animatable, long instanceId, AnimationState<MeganeuropsisEntity> animationState) {
		final float age = animatable.tickCount + animationState.getAnimatable().tickCount;
		final float wingSpeedMod = animatable.onGround() ? 0.05f : 7.5f;
		final float rot1 = -(Mth.sin(age * wingSpeedMod) * 0.4f) * 0.436332313f;
		final float rot2 = -(Mth.sin(age * wingSpeedMod + Mth.HALF_PI) * 0.4f) * 0.436332313f;

		getBone("wing1").ifPresent(bone -> bone.setRotZ(-5 * Mth.DEG_TO_RAD + rot1));
		getBone("wing2").ifPresent(bone -> bone.setRotZ(5 * Mth.DEG_TO_RAD - rot1));
		getBone("wing3").ifPresent(bone -> bone.setRotZ(-5 * Mth.DEG_TO_RAD + rot2));
		getBone("wing4").ifPresent(bone -> bone.setRotZ(5 * Mth.DEG_TO_RAD - rot2));
	}
}

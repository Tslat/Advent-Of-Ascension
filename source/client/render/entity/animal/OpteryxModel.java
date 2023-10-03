package net.tslat.aoa3.client.render.entity.animal;

import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.entity.mob.MultiStageHeadModel;
import net.tslat.aoa3.content.entity.animal.precasia.OpteryxEntity;
import software.bernie.geckolib.core.animation.AnimationState;

public class OpteryxModel extends MultiStageHeadModel<OpteryxEntity> {
	public OpteryxModel() {
		super(AdventOfAscension.id("animal/precasia/opteryx"));

		withStages(
				new Stage("head", FloatFloatPair.of(-45, -14), FloatFloatPair.of(-21, 21)),
				new Stage("neck", FloatFloatPair.of(5, 30), FloatFloatPair.of(-32, 32))
		);
	}

	@Override
	public ResourceLocation getModelResource(OpteryxEntity animatable) {
		if (animatable.isEgg())
			return buildFormattedModelPath(AdventOfAscension.id("animal/precasia/opteryx_egg"));

		return super.getModelResource(animatable);
	}

	@Override
	public ResourceLocation getTextureResource(OpteryxEntity animatable) {
		if (animatable.isEgg())
			return buildFormattedTexturePath(AdventOfAscension.id("animal/precasia/opteryx_egg"));

		return super.getTextureResource(animatable);
	}

	@Override
	public void setCustomAnimations(OpteryxEntity animatable, long instanceId, AnimationState<OpteryxEntity> animationState) {
		if (animatable.isEgg())
			return;

		super.setCustomAnimations(animatable, instanceId, animationState);

		final boolean isBaby = animatable.isBaby();
		final float headMod = isBaby ? 1.5f : 1f;
		final float rootMod = isBaby ? 0.5f : 1f;

		getBone("body").ifPresent(bone -> bone.updateScale(rootMod, rootMod, rootMod));
		getBone("head").ifPresent(bone -> bone.updateScale(headMod, headMod, headMod));
		getBone("bone5").ifPresent(bone -> bone.setHidden(isBaby));
	}
}

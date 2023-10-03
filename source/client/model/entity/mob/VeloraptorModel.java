package net.tslat.aoa3.client.model.entity.mob;

import it.unimi.dsi.fastutil.floats.FloatFloatPair;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.content.entity.mob.precasia.VeloraptorEntity;

public class VeloraptorModel extends MultiStageHeadModel<VeloraptorEntity> {
	public VeloraptorModel() {
		super(AdventOfAscension.id("mob/precasia/veloraptor"));

		withStages(
				new Stage("head", FloatFloatPair.of(0, 42.5f), FloatFloatPair.of(-7, 7)),
				new Stage("neck", FloatFloatPair.of(0, 75), FloatFloatPair.of(-20, 20))
		);
	}

	@Override
	public ResourceLocation getTextureResource(VeloraptorEntity animatable) {
		final ResourceLocation texture = super.getTextureResource(animatable);

		return new ResourceLocation(texture.getNamespace(), texture.getPath().substring(0, texture.getPath().indexOf(".")) + "_" + animatable.getVariant().name + ".png");
	}
}

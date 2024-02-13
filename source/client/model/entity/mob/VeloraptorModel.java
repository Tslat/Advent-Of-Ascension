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
	public ResourceLocation getModelResource(VeloraptorEntity veloraptor) {
		return AdventOfAscension.id("geo/entity/mob/precasia/veloraptor/" + veloraptor.getVariant().name() + "_veloraptor.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(VeloraptorEntity veloraptor) {
		return AdventOfAscension.id("textures/entity/mob/precasia/veloraptor/" + veloraptor.getVariant().name() + "_veloraptor.png");
	}
}

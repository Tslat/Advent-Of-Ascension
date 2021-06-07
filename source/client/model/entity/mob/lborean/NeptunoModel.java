package net.tslat.aoa3.client.model.entity.mob.lborean;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.lborean.NeptunoEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NeptunoModel extends AnimatedGeoModel<NeptunoEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/lborean/neptuno.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/lborean/neptuno.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/lborean/neptuno.animation.json");

	@Override
	public ResourceLocation getModelLocation(NeptunoEntity anglerEntity) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(NeptunoEntity anglerEntity) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(NeptunoEntity anglerEntity) {
		return ANIMATIONS;
	}
}

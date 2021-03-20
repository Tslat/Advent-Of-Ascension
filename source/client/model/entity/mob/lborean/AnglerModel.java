package net.tslat.aoa3.client.model.entity.mob.lborean;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.lborean.AnglerEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AnglerModel extends AnimatedGeoModel<AnglerEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/lborean/angler.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entities/mobs/lborean/angler.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/lborean/angler.animation.json");

	@Override
	public ResourceLocation getModelLocation(AnglerEntity anglerEntity) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(AnglerEntity anglerEntity) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(AnglerEntity anglerEntity) {
		return ANIMATIONS;
	}
}

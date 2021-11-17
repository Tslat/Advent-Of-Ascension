package net.tslat.aoa3.client.model.entity.mob.lborean;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.lborean.MuncherEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MuncherModel extends AnimatedGeoModel<MuncherEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/lborean/muncher.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/lborean/muncher.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/lborean/muncher.animation.json");

	@Override
	public ResourceLocation getModelLocation(MuncherEntity muncher) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(MuncherEntity muncher) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(MuncherEntity muncher) {
		return ANIMATIONS;
	}
}

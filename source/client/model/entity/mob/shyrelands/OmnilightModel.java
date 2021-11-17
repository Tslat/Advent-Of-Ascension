package net.tslat.aoa3.client.model.entity.mob.shyrelands;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.shyrelands.OmnilightEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class OmnilightModel extends AnimatedGeoModel<OmnilightEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/shyrelands/omnilight.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/shyrelands/omnilight.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/shyrelands/omnilight.animation.json");

	@Override
	public ResourceLocation getModelLocation(OmnilightEntity omnilight) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(OmnilightEntity omnilight) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(OmnilightEntity omnilight) {
		return ANIMATIONS;
	}
}

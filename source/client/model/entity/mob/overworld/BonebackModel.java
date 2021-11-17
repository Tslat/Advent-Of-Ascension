package net.tslat.aoa3.client.model.entity.mob.overworld;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.overworld.BonebackEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BonebackModel extends AnimatedGeoModel<BonebackEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/overworld/boneback.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/overworld/boneback.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/overworld/boneback.animation.json");

	@Override
	public ResourceLocation getModelLocation(BonebackEntity boneback) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(BonebackEntity boneback) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(BonebackEntity boneback) {
		return ANIMATIONS;
	}
}

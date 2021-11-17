package net.tslat.aoa3.client.model.entity.mob.overworld;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.overworld.BushBabyEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BushBabyModel extends AnimatedGeoModel<BushBabyEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/overworld/bush_baby.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/overworld/bush_baby.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/overworld/bush_baby.animation.json");

	@Override
	public ResourceLocation getModelLocation(BushBabyEntity bushBaby) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(BushBabyEntity bushBaby) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(BushBabyEntity bushBaby) {
		return ANIMATIONS;
	}
}

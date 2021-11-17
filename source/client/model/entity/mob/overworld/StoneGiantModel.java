package net.tslat.aoa3.client.model.entity.mob.overworld;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.overworld.bigday.StoneGiantEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StoneGiantModel extends AnimatedGeoModel<StoneGiantEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/overworld/stone_giant.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/overworld/stone_giant.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/overworld/stone_giant.animation.json");

	@Override
	public ResourceLocation getModelLocation(StoneGiantEntity sandGiant) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(StoneGiantEntity sandGiant) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(StoneGiantEntity sandGiant) {
		return ANIMATIONS;
	}
}

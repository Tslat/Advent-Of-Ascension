package net.tslat.aoa3.client.model.entity.mob.overworld;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.overworld.bigday.LeafyGiantEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LeafyGiantModel extends AnimatedGeoModel<LeafyGiantEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/overworld/leafy_giant.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/overworld/leafy_giant.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/overworld/leafy_giant.animation.json");

	@Override
	public ResourceLocation getModelLocation(LeafyGiantEntity leafyGiant) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(LeafyGiantEntity leafyGiant) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(LeafyGiantEntity leafyGiant) {
		return ANIMATIONS;
	}
}

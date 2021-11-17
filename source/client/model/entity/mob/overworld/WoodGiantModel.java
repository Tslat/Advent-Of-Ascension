package net.tslat.aoa3.client.model.entity.mob.overworld;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.overworld.bigday.WoodGiantEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WoodGiantModel extends AnimatedGeoModel<WoodGiantEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/overworld/wood_giant.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/overworld/wood_giant.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/overworld/wood_giant.animation.json");

	@Override
	public ResourceLocation getModelLocation(WoodGiantEntity woodGiant) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(WoodGiantEntity woodGiant) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(WoodGiantEntity woodGiant) {
		return ANIMATIONS;
	}
}

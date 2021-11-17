package net.tslat.aoa3.client.model.entity.mob.overworld;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.overworld.bigday.SandGiantEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SandGiantModel extends AnimatedGeoModel<SandGiantEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/overworld/sand_giant.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/overworld/sand_giant.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/overworld/sand_giant.animation.json");

	@Override
	public ResourceLocation getModelLocation(SandGiantEntity sandGiant) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(SandGiantEntity sandGiant) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(SandGiantEntity sandGiant) {
		return ANIMATIONS;
	}
}

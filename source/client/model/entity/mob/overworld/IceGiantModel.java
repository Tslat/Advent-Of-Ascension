package net.tslat.aoa3.client.model.entity.mob.overworld;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.overworld.bigday.IceGiantEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class IceGiantModel extends AnimatedGeoModel<IceGiantEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/overworld/ice_giant.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/overworld/ice_giant.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/overworld/ice_giant.animation.json");

	@Override
	public ResourceLocation getModelLocation(IceGiantEntity iceGiant) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(IceGiantEntity iceGiant) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(IceGiantEntity iceGiant) {
		return ANIMATIONS;
	}
}

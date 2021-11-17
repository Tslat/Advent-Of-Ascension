package net.tslat.aoa3.client.model.entity.mob.overworld;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.overworld.MuckopedeEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class MuckopedeModel extends AnimatedGeoModel<MuckopedeEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/overworld/muckopede.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/overworld/muckopede.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/overworld/muckopede.animation.json");

	@Override
	public ResourceLocation getModelLocation(MuckopedeEntity muckopede) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(MuckopedeEntity muckopede) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(MuckopedeEntity muckopede) {
		return ANIMATIONS;
	}
}

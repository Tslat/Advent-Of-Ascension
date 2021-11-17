package net.tslat.aoa3.client.model.entity.mob.overworld;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.overworld.ChomperEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ChomperModel extends AnimatedGeoModel<ChomperEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/overworld/chomper.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/overworld/chomper.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/overworld/chomper.animation.json");

	@Override
	public ResourceLocation getModelLocation(ChomperEntity chomper) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(ChomperEntity chomper) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(ChomperEntity chomper) {
		return ANIMATIONS;
	}
}

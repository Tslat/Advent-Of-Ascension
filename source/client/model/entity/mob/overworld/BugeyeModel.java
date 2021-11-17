package net.tslat.aoa3.client.model.entity.mob.overworld;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.overworld.BugeyeEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BugeyeModel extends AnimatedGeoModel<BugeyeEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/overworld/bugeye.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/overworld/bugeye.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/overworld/bugeye.animation.json");

	@Override
	public ResourceLocation getModelLocation(BugeyeEntity bugeye) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(BugeyeEntity bugeye) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(BugeyeEntity bugeye) {
		return ANIMATIONS;
	}
}

package net.tslat.aoa3.client.model.entity.mob.overworld;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.overworld.NightflyEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NightflyModel extends AnimatedGeoModel<NightflyEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/overworld/nightfly.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/overworld/nightfly.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/overworld/nightfly.animation.json");

	@Override
	public ResourceLocation getModelLocation(NightflyEntity nightfly) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(NightflyEntity nightfly) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(NightflyEntity nightfly) {
		return ANIMATIONS;
	}
}
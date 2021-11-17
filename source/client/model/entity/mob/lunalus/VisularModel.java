package net.tslat.aoa3.client.model.entity.mob.lunalus;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.lunalus.VisularEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VisularModel extends AnimatedGeoModel<VisularEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/lunalus/visular.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/lunalus/visular.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/lunalus/visular.animation.json");

	@Override
	public ResourceLocation getModelLocation(VisularEntity visular) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(VisularEntity visular) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(VisularEntity visular) {
		return ANIMATIONS;
	}
}

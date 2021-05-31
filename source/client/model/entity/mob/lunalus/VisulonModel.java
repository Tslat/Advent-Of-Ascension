package net.tslat.aoa3.client.model.entity.mob.lunalus;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.lunalus.VisulonEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class VisulonModel extends AnimatedGeoModel<VisulonEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/lunalus/visulon.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entities/mobs/lunalus/visulon.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/lunalus/visulon.animation.json");

	@Override
	public ResourceLocation getModelLocation(VisulonEntity anglerEntity) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(VisulonEntity anglerEntity) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(VisulonEntity anglerEntity) {
		return ANIMATIONS;
	}
}

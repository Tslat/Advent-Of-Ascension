package net.tslat.aoa3.client.model.entity.mob.shyrelands;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.shyrelands.ArcbeastEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ArcbeastModel extends AnimatedGeoModel<ArcbeastEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/shyrelands/arcbeast.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/shyrelands/arcbeast.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/shyrelands/arcbeast.animation.json");

	@Override
	public ResourceLocation getModelLocation(ArcbeastEntity arcbeast) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(ArcbeastEntity arcbeast) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(ArcbeastEntity arcbeast) {
		return ANIMATIONS;
	}
}

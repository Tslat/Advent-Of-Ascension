package net.tslat.aoa3.client.model.entity.mob.lborean;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.lborean.SeaViperEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SeaViperModel extends AnimatedGeoModel<SeaViperEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/lborean/sea_viper.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entities/mobs/lborean/sea_viper.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/lborean/sea_viper.animation.json");

	@Override
	public ResourceLocation getModelLocation(SeaViperEntity anglerEntity) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(SeaViperEntity anglerEntity) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(SeaViperEntity anglerEntity) {
		return ANIMATIONS;
	}
}

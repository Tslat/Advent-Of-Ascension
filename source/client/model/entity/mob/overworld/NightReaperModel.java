package net.tslat.aoa3.client.model.entity.mob.overworld;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.overworld.NightReaperEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class NightReaperModel extends AnimatedGeoModel<NightReaperEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/overworld/night_reaper.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/overworld/night_reaper.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/overworld/night_reaper.animation.json");

	@Override
	public ResourceLocation getModelLocation(NightReaperEntity nightReaper) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(NightReaperEntity nightReaper) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(NightReaperEntity nightReaper) {
		return ANIMATIONS;
	}
}

package net.tslat.aoa3.client.model.entity.mob.shyrelands;

import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.entity.mob.shyrelands.SyskerEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SyskerModel extends AnimatedGeoModel<SyskerEntity> {
	private static final ResourceLocation MODEL = new ResourceLocation(AdventOfAscension.MOD_ID, "geo/entities/mobs/shyrelands/sysker.geo.json");
	private static final ResourceLocation TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/entity/mobs/shyrelands/sysker.png");
	private static final ResourceLocation ANIMATIONS = new ResourceLocation(AdventOfAscension.MOD_ID, "animations/entities/mobs/shyrelands/sysker.animation.json");

	@Override
	public ResourceLocation getModelLocation(SyskerEntity sysker) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(SyskerEntity sysker) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(SyskerEntity sysker) {
		return ANIMATIONS;
	}
}

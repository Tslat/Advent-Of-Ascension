/*
package net.tslat.aoa3.client.render.entity.boss;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;


import javax.annotation.Nullable;

public class CottonCandorRenderer extends MobRenderer<CottonCandorEntity, EntityModel<CottonCandorEntity>> {
	private static final ResourceLocation WIND_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_wind.png");
	private static final ResourceLocation WATER_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_water.png");
	private static final ResourceLocation FIRE_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_fire.png");
	private static final ResourceLocation WITHER_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_wither.png");
	private static final ResourceLocation POISON_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_poison.png");

	public CottonCandorRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CottonCandorModel(), AoAMobs.COTTON_CANDOR.get().getWidth() / 3);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(CottonCandorEntity cottonCandor) {
		return switch (cottonCandor.getStage()) {
			case 0 -> WIND_PHASE_TEXTURE;
			case 1 -> WATER_PHASE_TEXTURE;
			case 2 -> FIRE_PHASE_TEXTURE;
			case 3 -> POISON_PHASE_TEXTURE;
			default -> WITHER_PHASE_TEXTURE;
		};
	}
}*/

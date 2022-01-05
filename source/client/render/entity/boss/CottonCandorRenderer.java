package net.tslat.aoa3.client.render.entity.boss;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.boss.CottonCandorModel;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.object.entity.boss.CottonCandorEntity;

import javax.annotation.Nullable;

public class CottonCandorRenderer extends MobRenderer<CottonCandorEntity, EntityModel<CottonCandorEntity>> {
	private static final ResourceLocation WIND_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_wind.png");
	private static final ResourceLocation WATER_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_water.png");
	private static final ResourceLocation FIRE_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_fire.png");
	private static final ResourceLocation WITHER_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_wither.png");
	private static final ResourceLocation POISON_PHASE_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/cottoncandor/cotton_candor_poison.png");

	public CottonCandorRenderer(EntityRendererManager renderManager) {
		super(renderManager, new CottonCandorModel(), AoAEntities.Mobs.COTTON_CANDOR.get().getWidth() / 3);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(CottonCandorEntity cottonCandor) {
		switch (cottonCandor.getStage()) {
			case 0:
				return WIND_PHASE_TEXTURE;
			case 1:
				return WATER_PHASE_TEXTURE;
			case 2:
				return FIRE_PHASE_TEXTURE;
			case 3:
				return POISON_PHASE_TEXTURE;
			case 4:
			default:
				return WITHER_PHASE_TEXTURE;
		}
	}
}
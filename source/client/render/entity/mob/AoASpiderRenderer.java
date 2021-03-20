package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;

public class AoASpiderRenderer extends AoAMobRenderer {
	public AoASpiderRenderer(EntityRendererManager renderManager, EntityModel<MobEntity> model, float shadowSize, float scale, ResourceLocation texture) {
		super(renderManager, model, shadowSize, scale, texture);
	}

	@Override
	protected float getFlipDegrees(MobEntity LivingEntityIn) {
		return 180f;
	}
}
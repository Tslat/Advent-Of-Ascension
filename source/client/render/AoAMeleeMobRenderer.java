package net.tslat.aoa3.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.entity.base.AoAMeleeMob;

import javax.annotation.Nullable;

public class AoAMeleeMobRenderer extends RenderLiving<AoAMeleeMob> {
	private final ResourceLocation texture;
	private final float scale;

	public AoAMeleeMobRenderer(RenderManager renderManager, ModelBase model, float entityWidth, float scale, ResourceLocation textureResource) {
		super(renderManager, model, entityWidth / 3);
		this.texture = textureResource;
		this.scale = scale;
	}

	@Override
	protected void preRenderCallback(AoAMeleeMob entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(scale, scale, scale);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(AoAMeleeMob entity) {
		return texture;
	}
}

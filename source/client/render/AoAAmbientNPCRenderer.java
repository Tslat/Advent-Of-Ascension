package net.tslat.aoa3.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.entity.base.AoAAmbientNPC;

import javax.annotation.Nullable;

public class AoAAmbientNPCRenderer extends RenderLiving<AoAAmbientNPC> {
	private final ResourceLocation texture;
	private final float scale;

	public AoAAmbientNPCRenderer(RenderManager renderManager, ModelBase model, float entityWidth, float scale, ResourceLocation textureResource) {
		super(renderManager, model, entityWidth / 3);
		this.texture = textureResource;
		this.scale = scale;
	}

	@Override
	protected void preRenderCallback(AoAAmbientNPC entitylivingbaseIn, float partialTickTime) {
		GlStateManager.scale(scale, scale, scale);
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(AoAAmbientNPC entity) {
		return texture;
	}
}

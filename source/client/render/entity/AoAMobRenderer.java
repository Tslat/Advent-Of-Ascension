package net.tslat.aoa3.client.render.entity;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;

public class AoAMobRenderer extends MobRenderer<MobEntity, EntityModel<MobEntity>> {
	private final ResourceLocation texture;
	private final float scale;

	public AoAMobRenderer(EntityRendererManager renderManager, EntityModel<MobEntity> model, float shadowSize, float scale, ResourceLocation texture) {
		super(renderManager, model, shadowSize);

		this.model = getModel();
		this.texture = texture;
		this.scale = scale;
	}

	@Override
	protected void scale(MobEntity entity, MatrixStack matrix, float partialTicks) {
		matrix.scale(scale, scale, scale);
	}

	@Override
	public ResourceLocation getTextureLocation(MobEntity entity) {
		return texture;
	}
}

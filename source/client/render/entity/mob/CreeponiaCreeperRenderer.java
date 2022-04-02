/*
package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.tslat.aoa3.client.render.entity.layer.CustomCreeperChargeRenderLayer;
import net.tslat.aoa3.content.entity.mob.creeponia.AoACreeponiaCreeper;

public class CreeponiaCreeperRenderer extends MobRenderer<AoACreeponiaCreeper, EntityModel<AoACreeponiaCreeper>> {
	private final ResourceLocation texture;
	private final float scale;

	public CreeponiaCreeperRenderer(EntityRendererProvider.Context renderManager, EntityModel<AoACreeponiaCreeper> model, EntityModel<AoACreeponiaCreeper> expandedModel, float shadowSize, float scale, ResourceLocation texture) {
		super(renderManager, model, shadowSize);

		addLayer(new CustomCreeperChargeRenderLayer(this, expandedModel));
		this.texture = texture;
		this.scale = scale;
	}

	@Override
	protected void scale(AoACreeponiaCreeper entity, PoseStack matrix, float partialTicks) {
		float flashIntensity = entity.getCreeperFlashIntensity(partialTicks);
		float flashRatio = 1.0F + Mth.sin(flashIntensity * 100.0F) * flashIntensity * 0.01F;
		flashIntensity = Mth.clamp(flashIntensity, 0.0F, 1.0F);
		flashIntensity = flashIntensity * flashIntensity;
		flashIntensity = flashIntensity * flashIntensity;
		float horizontalScale = (1.0F + flashIntensity * 0.4F) * flashRatio;
		float yScale = (1.0F + flashIntensity * 0.1F) / flashRatio;

		matrix.scale(scale, scale, scale);
		matrix.scale(horizontalScale, yScale, horizontalScale);
	}

	@Override
	protected float getWhiteOverlayProgress(AoACreeponiaCreeper livingEntityIn, float partialTicks) {
		float flashIntensity = livingEntityIn.getCreeperFlashIntensity(partialTicks);

		return (int)(flashIntensity * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(flashIntensity, 0.5F, 1.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(AoACreeponiaCreeper entity) {
		return texture;
	}
}
*/

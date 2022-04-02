/*
package net.tslat.aoa3.client.render.entity.minion;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Mob;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.tslat.aoa3.client.render.entity.layer.FriendlyCreeperChargeRenderLayer;

public class FriendlyCreeperRenderer extends LivingRenderer<Mob, EntityModel<Mob>> {
	private static final ResourceLocation textures = new ResourceLocation("minecraft", "textures/entity/creeper/creeper.png");

	public FriendlyCreeperRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new CreeperModel<Mob>(), 1 / 3f);

		addLayer(new FriendlyCreeperChargeRenderLayer(this, new CreeperModel<Mob>(2)));
	}

	@Override
	protected void scale(Mob entity, PoseStack matrix, float partialTicks) {
		float flashIntensity = 0;//((FriendlyCreeperEntity)entity).getCreeperFlashIntensity(partialTicks); TODO
		float scaleRotationMod = 1.0F + Mth.sin(flashIntensity * 100.0F) * flashIntensity * 0.01F;
		flashIntensity = (float)Math.pow(Mth.clamp(flashIntensity, 0.0F, 1.0F), 3);
		float scaleHorizontal = (1.0F + flashIntensity * 0.4F) * scaleRotationMod;
		float scaleVertical = (1.0F + flashIntensity * 0.1F) / scaleRotationMod;

		matrix.scale(scaleHorizontal, scaleVertical, scaleHorizontal);
	}

	@Override
	protected float getWhiteOverlayProgress(Mob entity, float partialTicks) {
		float flashIntensity = 0;//((FriendlyCreeperEntity)entity).getCreeperFlashIntensity(partialTicks); TODO

		return (int)(flashIntensity * 10.0F) % 2 == 0 ? 0.0F : Mth.clamp(flashIntensity, 0.5F, 1.0F);
	}

	@Override
	public ResourceLocation getTextureLocation(Mob entity) {
		return textures;
	}
}
*/

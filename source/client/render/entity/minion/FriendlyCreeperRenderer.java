package net.tslat.aoa3.client.render.entity.minion;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.CreeperModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.client.render.entity.layer.FriendlyCreeperChargeRenderLayer;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.FriendlyCreeperEntity;

public class FriendlyCreeperRenderer extends LivingRenderer<MobEntity, EntityModel<MobEntity>> {
	private static final ResourceLocation textures = new ResourceLocation("minecraft", "textures/entity/creeper/creeper.png");

	public FriendlyCreeperRenderer(EntityRendererManager renderManager) {
		super(renderManager, new CreeperModel<MobEntity>(), AoAEntities.Minions.FRIENDLY_CREEPER.get().getWidth() / 3f);

		addLayer(new FriendlyCreeperChargeRenderLayer(this, new CreeperModel<MobEntity>(2)));
	}

	@Override
	protected void preRenderCallback(MobEntity entity, MatrixStack matrix, float partialTicks) {
		float flashIntensity = ((FriendlyCreeperEntity)entity).getCreeperFlashIntensity(partialTicks);
		float scaleRotationMod = 1.0F + MathHelper.sin(flashIntensity * 100.0F) * flashIntensity * 0.01F;
		flashIntensity = (float)Math.pow(MathHelper.clamp(flashIntensity, 0.0F, 1.0F), 3);
		float scaleHorizontal = (1.0F + flashIntensity * 0.4F) * scaleRotationMod;
		float scaleVertical = (1.0F + flashIntensity * 0.1F) / scaleRotationMod;

		matrix.scale(scaleHorizontal, scaleVertical, scaleHorizontal);
	}

	@Override
	protected float getOverlayProgress(MobEntity entity, float partialTicks) {
		float flashIntensity = ((FriendlyCreeperEntity)entity).getCreeperFlashIntensity(partialTicks);

		return (int)(flashIntensity * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(flashIntensity, 0.5F, 1.0F);
	}

	@Override
	public ResourceLocation getEntityTexture(MobEntity entity) {
		return textures;
	}
}

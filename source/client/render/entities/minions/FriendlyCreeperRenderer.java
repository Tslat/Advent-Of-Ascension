package net.tslat.aoa3.client.render.entities.minions;

import net.minecraft.client.model.ModelCreeper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.client.render.entities.layers.RenderLayerFriendlyCreeperCharge;
import net.tslat.aoa3.entity.minions.EntityFriendlyCreeper;

import javax.annotation.Nullable;

public class FriendlyCreeperRenderer extends RenderLiving<EntityFriendlyCreeper> {
	private static final ResourceLocation textures = new ResourceLocation("minecraft", "textures/entity/creeper/creeper.png");

	public FriendlyCreeperRenderer(RenderManager renderManager) {
		super(renderManager, new ModelCreeper(), EntityFriendlyCreeper.entityWidth / 3f);

		addLayer(new RenderLayerFriendlyCreeperCharge(this, mainModel));
	}

	@Override
	protected void preRenderCallback(EntityFriendlyCreeper creeper, float partialTicks) {
		float flashIntensity = creeper.getCreeperFlashIntensity(partialTicks);
		float scaleRotationMod = 1.0F + MathHelper.sin(flashIntensity * 100.0F) * flashIntensity * 0.01F;
		flashIntensity = (float)Math.pow(MathHelper.clamp(flashIntensity, 0.0F, 1.0F), 3);
		float scaleHorizontal = (1.0F + flashIntensity * 0.4F) * scaleRotationMod;
		float scaleVertical = (1.0F + flashIntensity * 0.1F) / scaleRotationMod;

		GlStateManager.scale(scaleHorizontal, scaleVertical, scaleHorizontal);
	}

	@Override
	protected int getColorMultiplier(EntityFriendlyCreeper creeper, float lightBrightness, float partialTickTime) {
		float flashIntensity = creeper.getCreeperFlashIntensity(partialTickTime);

		if ((int)(flashIntensity * 10.0F) % 2 == 0) {
			return 0;
		}
		else {
			int flashColourMod = MathHelper.clamp((int)(flashIntensity * 0.2F * 255.0F), 0, 255);

			return flashColourMod << 24 | 822083583;
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(EntityFriendlyCreeper entity) {
		return textures;
	}
}

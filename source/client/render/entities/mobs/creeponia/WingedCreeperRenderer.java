package net.tslat.aoa3.client.render.entities.mobs.creeponia;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.client.model.entities.mobs.creeponia.ModelWingedCreeper;
import net.tslat.aoa3.client.render.entities.layers.RenderLayerCustomCreeperCharge;
import net.tslat.aoa3.entity.mobs.creeponia.EntityWingedCreeper;

public class WingedCreeperRenderer extends RenderLiving<EntityWingedCreeper> {
	private final ResourceLocation texture;

	public WingedCreeperRenderer(RenderManager renderManager, ResourceLocation textureResource) {
		super(renderManager, new ModelWingedCreeper(), EntityWingedCreeper.entityWidth / 3);

		this.texture = textureResource;

		this.addLayer(new RenderLayerCustomCreeperCharge(this, new ModelWingedCreeper()));
	}

	@Override
	protected void preRenderCallback(EntityWingedCreeper wingedCreeper, float partialTickTime) {
		float flashIntensity = wingedCreeper.getCreeperFlashIntensity(partialTickTime);
		float scaleRotationMod = 1.0F + MathHelper.sin(flashIntensity * 100.0F) * flashIntensity * 0.01F;
		flashIntensity = (float)Math.pow(MathHelper.clamp(flashIntensity, 0.0F, 1.0F), 3);
		float scaleHorizontal = (1.0F + flashIntensity * 0.4F) * scaleRotationMod;
		float scaleVertical = (1.0F + flashIntensity * 0.1F) / scaleRotationMod;

		GlStateManager.scale(scaleHorizontal, scaleVertical, scaleHorizontal);
	}


	@Override
	protected int getColorMultiplier(EntityWingedCreeper wingedCreeper, float lightBrightness, float partialTickTime) {
		float flashIntensity = wingedCreeper.getCreeperFlashIntensity(partialTickTime);

		if ((int)(flashIntensity * 10.0F) % 2 == 0) {
			return 0;
		}
		else {
			int flashColourMod = MathHelper.clamp((int)(flashIntensity * 0.2F * 255.0F), 0, 255);

			return flashColourMod << 24 | 822083583;
		}
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityWingedCreeper entity) {
		return texture;
	}
}

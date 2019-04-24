package net.tslat.aoa3.client.render.entities.boss.baroness;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.model.entities.boss.baroness.ModelBaronBomb;

import javax.annotation.Nullable;

public class BaronBombRenderer extends RenderEntity {
	private final ResourceLocation texture;
	private final ModelBase model = new ModelBaronBomb();

	public BaronBombRenderer(RenderManager renderManager, ResourceLocation texture) {
		super(renderManager);
		this.texture = texture;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();

		try {
			GlStateManager.translate(x, y, z);

			float rotation = entity.ticksExisted + partialTicks;

			GlStateManager.enableAlpha();
			GlStateManager.rotate(180, 0, 0, 0);
			this.renderModel(entity, 0, 0, rotation, 0, 0, 0.0625F);
			GlStateManager.depthMask(true);
			GlStateManager.disableRescaleNormal();
			GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GlStateManager.enableTexture2D();
			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
			GlStateManager.enableCull();
			GlStateManager.popMatrix();
		}
		catch (Exception ex) {
			AdventOfAscension.getLogger().error("Failed BaronBomb Render", ex);
			ex.printStackTrace();
		}
	}

	private void renderModel(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
		boolean flag = !entity.isInvisible() || renderOutlines;
		boolean flag1 = !flag && !entity.isInvisibleToPlayer(Minecraft.getMinecraft().player);

		if (flag || flag1) {
			if (!this.bindEntityTexture(entity))
				return;

			if (flag1)
				GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);

			this.model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

			if (flag1)
				GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
		}
	}

	@Nullable
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return texture;
	}
}

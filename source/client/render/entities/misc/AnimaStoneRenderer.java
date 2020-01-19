package net.tslat.aoa3.client.render.entities.misc;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.fx.FXFlickeringFluffyTrail;
import net.tslat.aoa3.client.model.entities.misc.ModelAnimaStone;
import net.tslat.aoa3.library.Enums;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class AnimaStoneRenderer extends RenderEntity {
	private final ResourceLocation texture;
	private final ModelBase model = new ModelAnimaStone();

	public AnimaStoneRenderer(RenderManager renderManager, final ResourceLocation resource) {
		super(renderManager);

		texture = resource;
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		try {
			float rotation = entity.ticksExisted + partialTicks;

			GlStateManager.pushMatrix();
			GlStateManager.disableCull();
			GlStateManager.translate(x, y, z);
			GlStateManager.enableAlpha();
			GlStateManager.rotate(180, 0, 0, 0);
			GlStateManager.translate(0, -1.5, 0);
			GlStateManager.scale(-1, 1, 1);
			renderModel(entity, 0, 0, rotation, entity.rotationYaw, entity.rotationPitch, 0.0625f);
			new FXFlickeringFluffyTrail(entity.world, entity.posX, entity.posY + 0.3, entity.posZ, 0, 0, 0, Enums.RGBIntegers.CYAN, 5, 0.5f).create();
			GlStateManager.depthMask(true);
			GlStateManager.disableRescaleNormal();
			GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GlStateManager.enableTexture2D();
			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
		}
		catch (Exception ex) {
			AdventOfAscension.logMessage(Level.ERROR, "Failed Anima Stone Render", ex);
			ex.printStackTrace();
		}

		GlStateManager.enableCull();
		GlStateManager.popMatrix();
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
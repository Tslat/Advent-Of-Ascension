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
import net.tslat.aoa3.client.model.entities.misc.ModelLottoTotem;
import org.apache.logging.log4j.Level;

import javax.annotation.Nullable;
import java.util.Random;

@SideOnly(Side.CLIENT)
public class LottoTotemRenderer extends RenderEntity {
	private static final Random particleRand = new Random();

	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entities/misc/lotto_totem.png");
	private final ModelBase model = new ModelLottoTotem();

	public LottoTotemRenderer(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		try {
			GlStateManager.pushMatrix();
			GlStateManager.disableCull();
			GlStateManager.translate(x, y, z);
			GlStateManager.enableAlpha();
			GlStateManager.rotate(180, 0, 0, 0);
			GlStateManager.translate(0, -1.5, 0);
			GlStateManager.scale(-1, 1, 1);

			float rotation = entity.ticksExisted + partialTicks;

			renderModel(entity, 0, rotation, 0, entity.rotationYaw, entity.rotationPitch, 0.0625f);
			GlStateManager.depthMask(true);
			GlStateManager.disableRescaleNormal();
			GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
			GlStateManager.enableTexture2D();
			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
		}
		catch (Exception ex) {
			AdventOfAscension.logMessage(Level.ERROR, "Failed Lotto Totem Render", ex);
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

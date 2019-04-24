package net.tslat.aoa3.client.gui.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;

public class HelmetScreenRenderer {
	public static boolean active = false;
	public static Enums.HelmetScreens screen;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderHelmetScreen(final RenderGameOverlayEvent.Post event) {
		if (!active || event.isCanceled() || event.getType() != RenderGameOverlayEvent.ElementType.HELMET || Minecraft.getMinecraft().gameSettings.thirdPersonView != 0)
			return;

		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc);

		switch (screen) {
			case NIGHT_VISION_GOGGLES:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/helmets/night_vision_goggles.png"));
				break;
			default:
				return;
		}

		final Tessellator tess = Tessellator.getInstance();
		final BufferBuilder buff = tess.getBuffer();

		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		buff.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
		buff.pos(0.0D, res.getScaledHeight_double(), -90.0D).tex(0.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.pos(res.getScaledWidth_double(), res.getScaledHeight_double(), -90.0D).tex(1.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.pos(res.getScaledWidth_double(), 0.0, -90.0D).tex(1.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		tess.draw();
		GlStateManager.depthMask(true);
	}
}

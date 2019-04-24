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

public class ScreenOverlayRenderer {
	public static int overlayTicks = 0;
	public static Enums.ScreenOverlays screen;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderOverlay(final RenderGameOverlayEvent.Post event) {
		if (overlayTicks == 0 || event.isCanceled() || event.getType() != RenderGameOverlayEvent.ElementType.HELMET || Minecraft.getMinecraft().gameSettings.thirdPersonView != 0)
			return;

		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc);

		switch(screen) {
			case SCRATCHES:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/scratches.png"));
				break;
			case BLOODY:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/bloody.png"));
				break;
			case STATIC:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/static.png"));
				break;
			case GRILLFACE:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/grillface.png"));
				break;
			case DARKNESS:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/darkness.png"));
				break;
			case EILOSAPIEN:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/eilosapien.png"));
				break;
			case PURPLE_FOG:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/purple_fog.png"));
				break;
			case CIRCLES:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/circles.png"));
				break;
			case CONIFERON_VINES:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/coniferon_vines.png"));
				break;
			case SPIKEY_CIRCLES:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/spikey_circles.png"));
				break;
			case SHYRE_DIZZY:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/shyrelands_dizzy.png"));
				break;
			case SHYRE_BLIND:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/shyrelands_blind.png"));
				break;
			case LIGHTWALKER:
				mc.getTextureManager().bindTexture(new ResourceLocation("aoa3:textures/gui/overlays/lightwalker.png"));
				break;
			default:
				return;
		}

		final Tessellator tess = Tessellator.getInstance();
		final BufferBuilder buff = tess.getBuffer();

		GlStateManager.pushAttrib();
		GlStateManager.enableAlpha();
		GlStateManager.enableBlend();
		buff.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);
		buff.pos(0.0D, res.getScaledHeight_double(), -90.0D).tex(0.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.pos(res.getScaledWidth_double(), res.getScaledHeight_double(), -90.0D).tex(1.0D, 1.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.pos(res.getScaledWidth_double(), 0.0, -90.0D).tex(1.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		buff.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).normal(0.0F, 1.0F, 0.0F).endVertex();
		tess.draw();
		GlStateManager.depthMask(true);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		GlStateManager.disableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.popAttrib();
	}
}

package net.nevermine.gui.screen;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class HelmetScreen {
	public static boolean show;
	public static int image;
	private static ScaledResolution res;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderOverlay(final RenderGameOverlayEvent.Post event) {
		if (event.isCanceled() || event.type != RenderGameOverlayEvent.ElementType.HELMET) {
			return;
		}
		if (HelmetScreen.show) {
			drawOverlay();
		}
	}

	public static void drawOverlay() {
		HelmetScreen.res = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		GL11.glDisable(2929);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glDisable(3008);
		if (HelmetScreen.image == 1) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/nightVision.png"));
		}
		if (HelmetScreen.image == 2) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/thermalSuit.png"));
		}
		final Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0, (double)HelmetScreen.res.getScaledHeight(), -90.0, 0.0, 1.0);
		tessellator.addVertexWithUV((double)HelmetScreen.res.getScaledWidth(), (double)HelmetScreen.res.getScaledHeight(), -90.0, 1.0, 1.0);
		tessellator.addVertexWithUV((double)HelmetScreen.res.getScaledWidth(), 0.0, -90.0, 1.0, 0.0);
		tessellator.addVertexWithUV(0.0, 0.0, -90.0, 0.0, 0.0);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929);
		GL11.glEnable(3008);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	}
}

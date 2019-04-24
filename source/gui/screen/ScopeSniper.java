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

public class ScopeSniper {
	public static boolean show;
	public static int image;
	private static ScaledResolution res;

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void renderOverlay(final RenderGameOverlayEvent.Post event) {
		if (event.isCanceled() || event.type != RenderGameOverlayEvent.ElementType.HELMET || Minecraft.getMinecraft().gameSettings.thirdPersonView != 0) {
			return;
		}
		if (ScopeSniper.show) {
			drawOverlay();
		}
	}

	public static void drawOverlay() {
		ScopeSniper.res = new ScaledResolution(Minecraft.getMinecraft(), Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
		GL11.glDisable(2929);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glDisable(3008);
		if (ScopeSniper.image == 1) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperBasic.png"));
		}
		else if (ScopeSniper.image == 2) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperDotted.png"));
		}
		else if (ScopeSniper.image == 3) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperScratches.png"));
		}
		else if (ScopeSniper.image == 4) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperRedlight.png"));
		}
		else if (ScopeSniper.image == 5) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperFloro.png"));
		}
		else if (ScopeSniper.image == 6) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperMarker.png"));
		}
		else if (ScopeSniper.image == 7) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperMonster.png"));
		}
		else if (ScopeSniper.image == 8) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperDischarge.png"));
		}
		else if (ScopeSniper.image == 9) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperMoon.png"));
		}
		else if (ScopeSniper.image == 10) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperCrystal.png"));
		}
		else if (ScopeSniper.image == 11) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperCandy.png"));
		}
		else if (ScopeSniper.image == 12) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperDiamond.png"));
		}
		else if (ScopeSniper.image == 13) {
			Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/sniperBoulder.png"));
		}
		final Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV(0.0, (double)ScopeSniper.res.getScaledHeight(), -90.0, 0.0, 1.0);
		tessellator.addVertexWithUV((double)ScopeSniper.res.getScaledWidth(), (double)ScopeSniper.res.getScaledHeight(), -90.0, 1.0, 1.0);
		tessellator.addVertexWithUV((double)ScopeSniper.res.getScaledWidth(), 0.0, -90.0, 1.0, 0.0);
		tessellator.addVertexWithUV(0.0, 0.0, -90.0, 0.0, 0.0);
		tessellator.draw();
		GL11.glDepthMask(true);
		GL11.glEnable(2929);
		GL11.glEnable(3008);
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
	}
}

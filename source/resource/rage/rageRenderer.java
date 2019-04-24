package net.nevermine.resource.rage;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.nevermine.assist.ConfigurationHelper;

public class rageRenderer {
	Minecraft mc;

	public rageRenderer() {
		mc = Minecraft.getMinecraft();
	}

	@SubscribeEvent
	public void onRender(final TickEvent.RenderTickEvent event) {
		onTickRender();
	}

	private void onTickRender() {
		if (mc.currentScreen == null) {
			final GuiIngame gig = mc.ingameGUI;
			final ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			final int i = scaledresolution.getScaledWidth();
			final int k = scaledresolution.getScaledHeight();
			if (rageHelper.getProperties(mc.thePlayer).getBarValue() > 150.0f) {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/rageSquareActive.png"));
			}
			else {
				mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/rageSquare.png"));
			}
			final int y = ConfigurationHelper.rageBarY;
			int x = i - ConfigurationHelper.rageBarX;
			gig.drawTexturedModalRect(x, y, 0, 0, 25, 25);
			gig.drawTexturedModalRect(x, y, 0, 25, (int)(3.125 * (rageHelper.getProperties(mc.thePlayer).getBarValue() / 25.0f)), 50);
			if (rageHelper.getProperties(mc.thePlayer).getBarInt() >= 100) {
				x -= 6;
			}
			else if (rageHelper.getProperties(mc.thePlayer).getBarInt() >= 10) {
				x -= 3;
			}
			Minecraft.getMinecraft().fontRenderer.drawString("" + rageHelper.getProperties(mc.thePlayer).getBarInt(), x + 11, y + 14, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + rageHelper.getProperties(mc.thePlayer).getBarInt(), x + 9, y + 14, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + rageHelper.getProperties(mc.thePlayer).getBarInt(), x + 10, y + 15, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + rageHelper.getProperties(mc.thePlayer).getBarInt(), x + 10, y + 13, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + rageHelper.getProperties(mc.thePlayer).getBarInt(), x + 10, y + 14, 16777215);
		}
	}
}

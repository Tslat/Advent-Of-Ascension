package net.nevermine.resource.energy;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.nevermine.assist.ConfigurationHelper;

public class energyRenderer {
	Minecraft mc;

	public energyRenderer() {
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
			mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/energySquare.png"));
			final int y = ConfigurationHelper.ancientBarY;
			int x = i - ConfigurationHelper.ancientBarX;
			gig.drawTexturedModalRect(x, y, 0, 0, 25, 25);
			gig.drawTexturedModalRect(x, y, 0, 25, (int)(3.125 * (energyHelper.getProperties(mc.thePlayer).getBarValue() / 25.0f)), 50);
			if (energyHelper.getProperties(mc.thePlayer).getBarInt() >= 100) {
				x -= 6;
			}
			else if (energyHelper.getProperties(mc.thePlayer).getBarInt() >= 10) {
				x -= 3;
			}
			Minecraft.getMinecraft().fontRenderer.drawString("" + energyHelper.getProperties(mc.thePlayer).getBarInt(), x + 11, y + 14, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + energyHelper.getProperties(mc.thePlayer).getBarInt(), x + 9, y + 14, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + energyHelper.getProperties(mc.thePlayer).getBarInt(), x + 10, y + 15, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + energyHelper.getProperties(mc.thePlayer).getBarInt(), x + 10, y + 13, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + energyHelper.getProperties(mc.thePlayer).getBarInt(), x + 10, y + 14, 16777215);
		}
	}
}

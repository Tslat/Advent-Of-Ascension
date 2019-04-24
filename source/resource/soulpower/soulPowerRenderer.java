package net.nevermine.resource.soulpower;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.skill.augury.auguryRenderer;

public class soulPowerRenderer {
	Minecraft mc;

	public soulPowerRenderer() {
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
			mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/soulpowerSquare.png"));
			final int y = ConfigurationHelper.soulBarY;
			int x = i - ConfigurationHelper.soulBarX;
			final int level = auguryRenderer.value;
			if (level < 35) {
				gig.drawTexturedModalRect(x, y, 0, 0, 25, 25);
				gig.drawTexturedModalRect(x, y, 0, 25, (int)(3.125 * (soulPowerHelper.getProperties(mc.thePlayer).getBarValue() / 2500.0f)), 50);
			}
			else if (level < 70) {
				gig.drawTexturedModalRect(x, y, 0, 0, 25, 25);
				gig.drawTexturedModalRect(x, y, 0, 25, (int)(3.125 * (soulPowerHelper.getProperties(mc.thePlayer).getBarValue() / 3250.0f)), 50);
			}
			else if (level < 90) {
				gig.drawTexturedModalRect(x, y, 0, 0, 25, 25);
				gig.drawTexturedModalRect(x, y, 0, 25, (int)(3.125 * (soulPowerHelper.getProperties(mc.thePlayer).getBarValue() / 4000.0f)), 50);
			}
			else {
				gig.drawTexturedModalRect(x, y, 0, 0, 25, 25);
				gig.drawTexturedModalRect(x, y, 0, 25, (int)(3.125 * (soulPowerHelper.getProperties(mc.thePlayer).getBarValue() / 5000.0f)), 50);
			}
			if (soulPowerHelper.getProperties(mc.thePlayer).getBarInt() >= 10) {
				x -= 3;
			}
			Minecraft.getMinecraft().fontRenderer.drawString("" + soulPowerHelper.getProperties(mc.thePlayer).getBarInt(), x + 11, y + 14, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + soulPowerHelper.getProperties(mc.thePlayer).getBarInt(), x + 9, y + 14, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + soulPowerHelper.getProperties(mc.thePlayer).getBarInt(), x + 10, y + 15, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + soulPowerHelper.getProperties(mc.thePlayer).getBarInt(), x + 10, y + 13, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + soulPowerHelper.getProperties(mc.thePlayer).getBarInt(), x + 10, y + 14, 16777215);
		}
	}
}

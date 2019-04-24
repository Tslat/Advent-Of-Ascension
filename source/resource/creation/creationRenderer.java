package net.nevermine.resource.creation;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.nevermine.assist.ConfigurationHelper;
import net.nevermine.skill.augury.auguryRenderer;

public class creationRenderer {
	Minecraft mc;

	public creationRenderer() {
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
			mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/creationSquare.png"));
			final int y = ConfigurationHelper.creationBarY;
			int x = i - ConfigurationHelper.creationBarX;
			final int level = auguryRenderer.value;
			if (level >= 0 && level < 30) {
				gig.drawTexturedModalRect(x, y, 0, 0, 25, 25);
				gig.drawTexturedModalRect(x, y, 0, 25, (int)(3.125 * (creationHelper.getProperties(mc.thePlayer).getBarValue() / 750.0f)), 50);
			}
			else if (level >= 30 && level < 60) {
				gig.drawTexturedModalRect(x, y, 0, 0, 25, 25);
				gig.drawTexturedModalRect(x, y, 0, 25, (int)(3.125 * (creationHelper.getProperties(mc.thePlayer).getBarValue() / 900.0f)), 50);
			}
			else if (level >= 60 && level < 85) {
				gig.drawTexturedModalRect(x, y, 0, 0, 25, 25);
				gig.drawTexturedModalRect(x, y, 0, 25, (int)(3.125 * (creationHelper.getProperties(mc.thePlayer).getBarValue() / 1050.0f)), 50);
			}
			else {
				gig.drawTexturedModalRect(x, y, 0, 0, 25, 25);
				gig.drawTexturedModalRect(x, y, 0, 25, (int)(3.125 * (creationHelper.getProperties(mc.thePlayer).getBarValue() / 1200.0f)), 50);
			}
			if (creationHelper.getProperties(mc.thePlayer).getBarInt() >= 10) {
				x -= 3;
			}
			Minecraft.getMinecraft().fontRenderer.drawString("" + creationHelper.getProperties(mc.thePlayer).getBarInt(), x + 11, y + 14, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + creationHelper.getProperties(mc.thePlayer).getBarInt(), x + 9, y + 14, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + creationHelper.getProperties(mc.thePlayer).getBarInt(), x + 10, y + 15, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + creationHelper.getProperties(mc.thePlayer).getBarInt(), x + 10, y + 13, 0);
			Minecraft.getMinecraft().fontRenderer.drawString("" + creationHelper.getProperties(mc.thePlayer).getBarInt(), x + 10, y + 14, 16777215);
		}
	}
}

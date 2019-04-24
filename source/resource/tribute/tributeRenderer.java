package net.nevermine.resource.tribute;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.nevermine.assist.ConfigurationHelper;

public class tributeRenderer {
	Minecraft mc;
	public static int selyan;
	public static int luxon;
	public static int erebon;
	public static int pluton;

	public tributeRenderer() {
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
			mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/tributeSelyan.png"));
			int y = ConfigurationHelper.tributeBarY;
			final int x = i - ConfigurationHelper.tributeBarX;
			gig.drawTexturedModalRect(x, y, 0, 0, 25, 8);
			gig.drawTexturedModalRect(x, y, 0, 8, (int)(3.125 * (tributeRenderer.selyan / 25)), 16);
			y += 8;
			mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/tributeLuxon.png"));
			gig.drawTexturedModalRect(x, y, 0, 0, 25, 5);
			gig.drawTexturedModalRect(x, y, 0, 5, (int)(3.125 * (tributeRenderer.luxon / 25)), 10);
			y += 5;
			mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/tributeErebon.png"));
			gig.drawTexturedModalRect(x, y, 0, 0, 25, 5);
			gig.drawTexturedModalRect(x, y, 0, 5, (int)(3.125 * (tributeRenderer.erebon / 25)), 10);
			y += 5;
			mc.getTextureManager().bindTexture(new ResourceLocation("nevermine:textures/gui/tributePluton.png"));
			gig.drawTexturedModalRect(x, y, 0, 0, 25, 7);
			gig.drawTexturedModalRect(x, y, 0, 7, (int)(3.125 * (tributeRenderer.pluton / 25)), 14);
		}
	}
}

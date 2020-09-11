package net.tslat.aoa3.client.gui.mainwindow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.RenderUtil;
import net.tslat.aoa3.utils.StringUtil;
import org.apache.logging.log4j.Level;

import java.net.URI;
import java.net.URISyntaxException;

@SideOnly(Side.CLIENT)
public class AdventGuiTabHelp extends GuiScreen {
	private int adjustedMouseX;
	private int adjustedMouseY;

	private int tipNumber = 0;

	AdventGuiTabHelp() {
		tipNumber = AdventOfAscension.rand.nextInt(15);
	}

	@Override
	public void initGui() {
		super.initGui();

		String language = FMLCommonHandler.instance().getCurrentLanguage();
		String wikiLink;
		String wikiDownloadLink;

		if (language.equals("zh_cn") || language.equals("zh_tw")) {
			wikiLink = "https://adventofascension-zh.gamepedia.com/Advent_of_Ascension_Wiki";
			wikiDownloadLink = "https://adventofascension-zh.gamepedia.com/%E4%B8%8B%E8%BD%BD";
		}
		else {
			wikiLink = "https://adventofascension.gamepedia.com/Advent_of_Ascension_Wiki";
			wikiDownloadLink = "https://adventofascension.gamepedia.com/Download";
		}

		this.buttonList.add(new ThemeButton(0, 640, 35, 30, 40));
		this.buttonList.add(new WebLinkButton(mc, 1, 640, 130, 76, 18, StringUtil.getLocaleString("gui.aoamain.link.discord"), stringtoURI("https://discord.gg/DNYqNNq")));
		this.buttonList.add(new WebLinkButton(mc, 2, 640, 170, 108, 18, StringUtil.getLocaleString("gui.aoamain.link.wiki"), stringtoURI(wikiLink)));
		this.buttonList.add(new WebLinkButton(mc, 3, 640, 210, 60, 18, StringUtil.getLocaleString("gui.aoamain.link.github"), stringtoURI("https://github.com/Tslat/Advent-Of-Ascension")));
		this.buttonList.add(new WebLinkButton(mc, 4, 640, 250, 80, 18, StringUtil.getLocaleString("gui.aoamain.link.patreon"), stringtoURI("https://www.patreon.com/Tslat")));
		this.buttonList.add(new WebLinkButton(mc, 5, 640, 290, 68, 18, StringUtil.getLocaleString("gui.aoamain.link.update"), stringtoURI(wikiDownloadLink)));
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.adjustedMouseX = (int)(mouseX * AdventMainGui.scaleInverse);
		this.adjustedMouseY = (int)(mouseY * AdventMainGui.scaleInverse);
		drawGradientRect(AdventMainGui.scaledTabRootX + 35, AdventMainGui.scaledTabRootY + 55, AdventMainGui.scaledTabRootX + 590, AdventMainGui.scaledTabRootY + 280, -1072689136, -804253680);
		RenderUtil.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.theme"), AdventMainGui.scaledTabRootX + 672, AdventMainGui.scaledTabRootY + 10, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);
		RenderUtil.drawCenteredScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.links"), AdventMainGui.scaledTabRootX + 672, AdventMainGui.scaledTabRootY + 100, 2f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.OUTLINED);

		RenderUtil.drawScaledString(mc.fontRenderer, StringUtil.getLocaleString("gui.aoamain.help.1"), AdventMainGui.scaledTabRootX + 80, AdventMainGui.scaledTabRootY + 25, 2.5f, Enums.RGBIntegers.LIGHT_CORAL, RenderUtil.StringRenderType.OUTLINED);

		GlStateManager.scale(1.5625f, 1.5625f, 1.5625f);
		mc.fontRenderer.drawSplitString(StringUtil.getLocaleString("gui.aoamain.help.2").replace("<br>", "\n"), (int)((AdventMainGui.scaledTabRootX + 40) / 1.5625f), (int)((AdventMainGui.scaledTabRootY + 60) / 1.5625f), 360, Enums.RGBIntegers.WHITE);
		GlStateManager.scale(0.64f, 0.64f, 0.64f);
		RenderUtil.drawScaledString(mc.fontRenderer, StringUtil.getLocaleStringWithArguments("gui.aoamain.help.tip", StringUtil.getLocaleString("gui.aoamain.tip." + tipNumber)), AdventMainGui.scaledTabRootX + 30, AdventMainGui.scaledTabRootY + 310, 1.8f, Enums.RGBIntegers.WHITE, RenderUtil.StringRenderType.DROP_SHADOW);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button instanceof ThemeButton) {
			AdventMainGui.currentTextureIndex++;

			if (AdventMainGui.currentTextureIndex == AdventMainGui.textures.size())
				AdventMainGui.currentTextureIndex = 0;

			AdventMainGui.changeTheme();
		}
		else if (button instanceof WebLinkButton) {
			this.openURL(((WebLinkButton)button).url);
		}
	}

	private void openURL(URI url) {
		try {
			Class<?> oclass = Class.forName("java.awt.Desktop");
			Object object = oclass.getMethod("getDesktop").invoke(null);

			oclass.getMethod("browse", URI.class).invoke(object, url);
		} catch (Throwable throwable1) {
			Throwable throwable = throwable1.getCause();

			AdventOfAscension.logMessage(Level.ERROR, "Couldn't open link: " + (throwable == null ? "<UNKNOWN>" : throwable.getMessage()));
		}
	}

	private URI stringtoURI(String url) {
		try {
			return new URI(url);
		}
		catch (URISyntaxException e) {
			return null;
		}
	}

	private static class WebLinkButton extends GuiButton {
		private final URI url;

		public WebLinkButton(Minecraft mc, int buttonId, int x, int y, int width, int height, String name, URI link) {
			super(buttonId, x, y, width, height, name);

			this.url = link;

			if (url == null) {
				enabled = false;
				visible = false;
			}
			else {
				this.width = (int)(mc.fontRenderer.getStringWidth(displayString) * 2f);
			}
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

				hovered = isMouseInRegion(mouseX, mouseY, x, y);
				int stringColour = Enums.RGBIntegers.LIGHT_CORAL;

				if (hovered)
					stringColour = Enums.RGBIntegers.YELLOW_2;

				RenderUtil.drawCenteredScaledString(mc.fontRenderer, displayString, (int)(AdventMainGui.scaledTabRootX + x + width / 2f), AdventMainGui.scaledTabRootY + y, 2f, stringColour, RenderUtil.StringRenderType.OUTLINED);
			}
		}

		@Override
		public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
			return enabled && hovered;
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= (int)((AdventMainGui.scaledTabRootX + buttonX) * AdventMainGui.scale) && mouseX <= (int)((AdventMainGui.scaledTabRootX + buttonX + width) * AdventMainGui.scale) && mouseY >= (int)((AdventMainGui.scaledTabRootY + buttonY) * AdventMainGui.scale) && mouseY <= (int)((AdventMainGui.scaledTabRootY + buttonY + height) * AdventMainGui.scale);
		}
	}

	private static class ThemeButton extends GuiButton {
		public ThemeButton(int buttonId, int x, int y, int width, int height) {
			super(buttonId, x, y, width, height, ConfigurationUtil.MainConfig.mainWindowTheme);
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				mc.getTextureManager().bindTexture(AdventMainGui.textures.get(AdventMainGui.currentTextureIndex).menuButtonTexture);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

				hovered = isMouseInRegion(mouseX, mouseY, x, y);
				displayString = ConfigurationUtil.MainConfig.mainWindowTheme.replace("_", " ");
				width = (int)(Math.max(width - 10, 10 + mc.fontRenderer.getStringWidth(displayString) * 1.5f));

				drawScaledCustomSizeModalRect(AdventMainGui.scaledTabRootX + x, AdventMainGui.scaledTabRootY + y, 0, (getHoverState(hovered) == 2 ? 60 : 120), 180, 60, width, height, 180, 180);

				int stringColour = 14737632;

				if (packedFGColour != 0) {
					stringColour = packedFGColour;
				}
				else if (!this.enabled) {
					stringColour = 10526880;
				}
				else if (this.hovered) {
					stringColour = 16777120;
				}

				RenderUtil.drawCenteredScaledString(mc.fontRenderer, displayString, (int)(AdventMainGui.scaledTabRootX + x + width / 2f), (int)(AdventMainGui.scaledTabRootY + y + height / 2.5), 1.5f, stringColour, RenderUtil.StringRenderType.DROP_SHADOW);
			}
		}

		@Override
		public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
			return enabled && hovered;
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= (int)((AdventMainGui.scaledTabRootX + buttonX) * AdventMainGui.scale) && mouseX <= (int)((AdventMainGui.scaledTabRootX + buttonX + width) * AdventMainGui.scale) && mouseY >= (int)((AdventMainGui.scaledTabRootY + buttonY) * AdventMainGui.scale) && mouseY <= (int)((AdventMainGui.scaledTabRootY + buttonY + height) * AdventMainGui.scale);
		}
	}
}

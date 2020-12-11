package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.RenderUtil;

import javax.annotation.Nullable;
import java.net.URI;
import java.net.URISyntaxException;

@OnlyIn(Dist.CLIENT)
public class AdventGuiTabHelp extends Screen {
	private int tipNumber = 0;

	protected AdventGuiTabHelp() {
		super(new TranslationTextComponent("gui.aoa3.adventGui.help"));

		tipNumber = RandomUtil.randomNumberUpTo(14);
	}

	@Override
	protected void init() {
		String language = Minecraft.getInstance().getLanguageManager().getCurrentLanguage().getCode();
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

		addButton(new ThemeButton(640, 35, 30, 40));
		addButton(new WebLinkButton(getMinecraft(), 640, 130, 76, 18, LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.linkDiscord"), stringToURI("https://discord.gg/DNYqNNq")));
		addButton(new WebLinkButton(getMinecraft(), 640, 170, 108, 18, LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.linkWiki"), stringToURI(wikiLink)));
		addButton(new WebLinkButton(getMinecraft(), 640, 210, 60, 18, LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.linkGithub"), stringToURI("https://github.com/Tslat/Advent-Of-Ascension")));
		addButton(new WebLinkButton(getMinecraft(), 640, 250, 80, 18, LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.linkPatreon"), stringToURI("https://www.patreon.com/Tslat")));
		addButton(new WebLinkButton(getMinecraft(), 640, 290, 68, 18, LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.linkUpdate"), stringToURI(wikiDownloadLink)));
	}

	@Override
	public void render(int mouseX, int mouseY, float partialTicks) {
		RenderUtil.drawVerticalGradient(AdventMainGui.scaledTabRootX + 35, AdventMainGui.scaledTabRootY + 55, 0, 570, 225, -1072689136, -804253680);
		RenderUtil.drawCenteredScaledString(font, LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.theme"), AdventMainGui.scaledTabRootX + 672, AdventMainGui.scaledTabRootY + 10, 2f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);
		RenderUtil.drawCenteredScaledString(font, LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.links"), AdventMainGui.scaledTabRootX + 672, AdventMainGui.scaledTabRootY + 100, 2f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.OUTLINED);

		RenderUtil.drawScaledString(font, LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.title"), AdventMainGui.scaledTabRootX + 80, AdventMainGui.scaledTabRootY + 25, 2.5f, NumberUtil.RGB(239, 137, 119), RenderUtil.StringRenderType.OUTLINED);

		RenderSystem.scalef(1.5625f, 1.5625f, 1.5625f);
		font.drawSplitString(LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.description").replace("<br>", "\n"), (int)((AdventMainGui.scaledTabRootX + 40) / 1.5625f), (int)((AdventMainGui.scaledTabRootY + 60) / 1.5625f), 360, NumberUtil.RGB(255, 255, 255));
		RenderSystem.scalef(0.64f, 0.64f, 0.64f);
		RenderUtil.drawScaledString(font, LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.tip", LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.tip." + tipNumber)), AdventMainGui.scaledTabRootX + 30, AdventMainGui.scaledTabRootY + 310, 1.8f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.DROP_SHADOW);
		super.render(mouseX, mouseY, partialTicks);
	}

	private static void openURL(URI url) {
		Util.getOSType().openURI(url);
	}

	@Nullable
	private URI stringToURI(String url) {
		try {
			return new URI(url);
		}
		catch (URISyntaxException e) {
			return null;
		}
	}

	private static class WebLinkButton extends Widget {
		private final URI url;

		public WebLinkButton(Minecraft mc, int x, int y, int width, int height, String name, URI link) {
			super(x, y, width, height, name);

			this.url = link;

			if (url == null) {
				active = false;
				visible = false;
			}
			else {
				this.width = (int)(mc.fontRenderer.getStringWidth(getMessage()) * 2f);
			}
		}

		@Override
		public void onClick(double mouseX, double mouseY) {
			openURL(url);
		}

		@Override
		public void render(int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				Minecraft mc = Minecraft.getInstance();

				RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);

				isHovered = isMouseInRegion(mouseX, mouseY, x, y);
				int stringColour = NumberUtil.RGB(239, 137, 119);

				if (isHovered)
					stringColour = NumberUtil.RGB(247, 239, 0);

				RenderUtil.drawCenteredScaledString(mc.fontRenderer, getMessage(), (int)(AdventMainGui.scaledTabRootX + x + width / 2f), AdventMainGui.scaledTabRootY + y, 2f, stringColour, RenderUtil.StringRenderType.OUTLINED);
			}
		}

		@Override
		protected boolean clicked(double p_clicked_1_, double p_clicked_3_) {
			return active && isHovered;
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= (int)((AdventMainGui.scaledTabRootX + buttonX) * AdventMainGui.scale) && mouseX <= (int)((AdventMainGui.scaledTabRootX + buttonX + width) * AdventMainGui.scale) && mouseY >= (int)((AdventMainGui.scaledTabRootY + buttonY) * AdventMainGui.scale) && mouseY <= (int)((AdventMainGui.scaledTabRootY + buttonY + height) * AdventMainGui.scale);
		}
	}

	private static class ThemeButton extends Widget {
		public ThemeButton(int x, int y, int width, int height) {
			super(x, y, width, height, AoAConfig.CLIENT.adventGuiTheme.get());
		}

		@Override
		public void onClick(double mouseX, double mouseY) {
			AdventMainGui.currentTextureIndex++;

			if (AdventMainGui.currentTextureIndex == AdventMainGui.textures.size())
				AdventMainGui.currentTextureIndex = 0;

			AdventMainGui.changeTheme();
		}

		@Override
		public void render(int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				Minecraft mc = Minecraft.getInstance();

				mc.getTextureManager().bindTexture(AdventMainGui.textures.get(AdventMainGui.currentTextureIndex).menuButtonTexture);
				RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

				isHovered = isMouseInRegion(mouseX, mouseY, x, y);

				setMessage(AoAConfig.CLIENT.adventGuiTheme.get().replace("_", " "));

				width = (int)(Math.max(width - 10, 10 + mc.fontRenderer.getStringWidth(getMessage()) * 1.5f));

				RenderUtil.renderScaledCustomSizedTexture(AdventMainGui.scaledTabRootX + x, AdventMainGui.scaledTabRootY + y, 0, (getYImage(isHovered) == 2 ? 60 : 120), 180, 60, width, height, 180, 180);

				int stringColour = 14737632;

				if (getFGColor() != 0) {
					stringColour = getFGColor();
				}
				else if (!this.active) {
					stringColour = 10526880;
				}
				else if (this.isHovered) {
					stringColour = 16777120;
				}

				RenderUtil.drawCenteredScaledString(mc.fontRenderer, getMessage(), (int)(AdventMainGui.scaledTabRootX + x + width / 2f), (int)(AdventMainGui.scaledTabRootY + y + height / 2.5), 1.5f, stringColour, RenderUtil.StringRenderType.DROP_SHADOW);
			}
		}

		@Override
		protected boolean clicked(double p_clicked_1_, double p_clicked_3_) {
			return active && isHovered;
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= (int)((AdventMainGui.scaledTabRootX + buttonX) * AdventMainGui.scale) && mouseX <= (int)((AdventMainGui.scaledTabRootX + buttonX + width) * AdventMainGui.scale) && mouseY >= (int)((AdventMainGui.scaledTabRootY + buttonY) * AdventMainGui.scale) && mouseY <= (int)((AdventMainGui.scaledTabRootY + buttonY + height) * AdventMainGui.scale);
		}
	}
}

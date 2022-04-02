package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.RenderUtil;

import javax.annotation.Nullable;
import java.net.URI;
import java.net.URISyntaxException;

public class AdventGuiTabHelp extends Screen {
	private int tipNumber;

	protected AdventGuiTabHelp() {
		super(new TranslatableComponent("gui.aoa3.adventGui.help"));

		tipNumber = RandomUtil.randomNumberUpTo(IntegrationManager.isPatchouliActive() ? 12 : 13);
	}

	@Override
	protected void init() {
		String language = Minecraft.getInstance().getLanguageManager().getSelected().getCode();
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

		addRenderableWidget(new ThemeButton(640, 35, 30, 40));
		addRenderableWidget(new WebLinkButton(getMinecraft(), 640, 130, 76, 18, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.linkDiscord"), stringToURI("https://discord.gg/DNYqNNq")));
		addRenderableWidget(new WebLinkButton(getMinecraft(), 640, 170, 108, 18, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.linkWiki"), stringToURI(wikiLink)));
		addRenderableWidget(new WebLinkButton(getMinecraft(), 640, 210, 60, 18, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.linkGithub"), stringToURI("https://github.com/Tslat/Advent-Of-Ascension")));
		addRenderableWidget(new WebLinkButton(getMinecraft(), 640, 250, 80, 18, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.linkPatreon"), stringToURI("https://www.patreon.com/Tslat")));
		addRenderableWidget(new WebLinkButton(getMinecraft(), 640, 290, 68, 18, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.linkUpdate"), stringToURI(wikiDownloadLink)));
	}

	@Override
	public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
		RenderUtil.drawVerticalGradient(matrix, AdventMainGui.scaledTabRootX + 35, AdventMainGui.scaledTabRootY + 55, 0, 570, 225, -1072689136, -804253680);
		RenderUtil.drawCenteredScaledMessage(matrix, font, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.theme"), AdventMainGui.scaledTabRootX + 672, AdventMainGui.scaledTabRootY + 10, 2f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);
		RenderUtil.drawCenteredScaledMessage(matrix, font, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.links"), AdventMainGui.scaledTabRootX + 672, AdventMainGui.scaledTabRootY + 100, 2f, ColourUtil.WHITE, RenderUtil.StringRenderType.OUTLINED);

		RenderUtil.drawScaledMessage(matrix, font, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.title"), AdventMainGui.scaledTabRootX + 80, AdventMainGui.scaledTabRootY + 25, 2.5f, ColourUtil.RGB(239, 137, 119), RenderUtil.StringRenderType.OUTLINED);

		matrix.pushPose();
		matrix.scale(1.5625f, 1.5625f, 1.5625f);
		RenderUtil.drawWrappedMessage(matrix, font, new TextComponent(LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.description").replace("<br>", "\n")), (int)((AdventMainGui.scaledTabRootX + 40) / 1.5625f), (int)((AdventMainGui.scaledTabRootY + 60) / 1.5625f), 360, ColourUtil.WHITE);
		matrix.popPose();

		RenderUtil.drawScaledMessage(matrix, font, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.tip", LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.tip." + tipNumber)), AdventMainGui.scaledTabRootX + 30, AdventMainGui.scaledTabRootY + 310, 1.8f, ColourUtil.WHITE, RenderUtil.StringRenderType.DROP_SHADOW);

		super.render(matrix, mouseX, mouseY, partialTicks);
	}

	private static void openURL(URI url) {
		Util.getPlatform().openUri(url);
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

	private static class WebLinkButton extends Button {
		private final URI url;

		public WebLinkButton(Minecraft mc, int x, int y, int width, int height, Component name, URI link) {
			super(x, y, width, height, name, button -> {});

			this.url = link;

			if (url == null) {
				active = false;
				visible = false;
			}
			else {
				this.width = (int)(mc.font.width(getMessage()) * 2f);
			}
		}

		@Override
		public void onClick(double mouseX, double mouseY) {
			openURL(url);
		}

		@Override
		public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				Minecraft mc = Minecraft.getInstance();

				RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

				isHovered = isMouseInRegion(mouseX, mouseY, x, y);
				int stringColour = ColourUtil.RGB(239, 137, 119);

				if (isHovered)
					stringColour = ColourUtil.RGB(247, 239, 0);

				RenderUtil.drawCenteredScaledMessage(matrix, mc.font, getMessage(), (int)(AdventMainGui.scaledTabRootX + x + width / 2f), AdventMainGui.scaledTabRootY + y, 2f, stringColour, RenderUtil.StringRenderType.OUTLINED);
			}
		}

		@Override
		protected boolean clicked(double p_clicked_1_, double p_clicked_3_) {
			return active && isHovered;
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= (int)((AdventMainGui.scaledTabRootX + buttonX) * AdventMainGui.SCALE) && mouseX <= (int)((AdventMainGui.scaledTabRootX + buttonX + width) * AdventMainGui.SCALE) && mouseY >= (int)((AdventMainGui.scaledTabRootY + buttonY) * AdventMainGui.SCALE) && mouseY <= (int)((AdventMainGui.scaledTabRootY + buttonY + height) * AdventMainGui.SCALE);
		}
	}

	private static class ThemeButton extends Button {
		public ThemeButton(int x, int y, int width, int height) {
			super(x, y, width, height, new TextComponent(AdventMainGui.theme.name()), button -> {});
		}

		@Override
		public void onClick(double mouseX, double mouseY) {
			AdventMainGui.changeTheme();
			setMessage(new TextComponent(AdventMainGui.theme.name()));
		}

		@Override
		public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				Minecraft mc = Minecraft.getInstance();
				isHovered = isMouseInRegion(mouseX, mouseY, x, y);
				width = (int)(10 + Minecraft.getInstance().font.width(getMessage()) * 1.5f);

				RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
				RenderSystem.setShaderTexture(0, AdventMainGui.theme.menuButtonTexture());
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

				RenderUtil.renderScaledCustomSizedTexture(matrix, AdventMainGui.scaledTabRootX + x, AdventMainGui.scaledTabRootY + y, 0, (getYImage(isHovered) == 2 ? 60 : 120), 180, 60, width, height, 180, 180);

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

				RenderUtil.drawCenteredScaledMessage(matrix, mc.font, getMessage(), (int)(AdventMainGui.scaledTabRootX + x + width / 2f), (int)(AdventMainGui.scaledTabRootY + y + height / 2.5), 1.5f, stringColour, RenderUtil.StringRenderType.DROP_SHADOW);
			}
		}

		@Override
		protected boolean clicked(double p_clicked_1_, double p_clicked_3_) {
			return active && isHovered;
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= (int)((AdventMainGui.scaledTabRootX + buttonX) * AdventMainGui.SCALE) && mouseX <= (int)((AdventMainGui.scaledTabRootX + buttonX + width) * AdventMainGui.SCALE) && mouseY >= (int)((AdventMainGui.scaledTabRootY + buttonY) * AdventMainGui.SCALE) && mouseY <= (int)((AdventMainGui.scaledTabRootY + buttonY + height) * AdventMainGui.SCALE);
		}
	}
}

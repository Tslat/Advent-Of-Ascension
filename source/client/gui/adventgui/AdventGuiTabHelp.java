package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RenderUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.URISyntaxException;

public class AdventGuiTabHelp extends Screen {
	private int tipNumber;

	protected AdventGuiTabHelp() {
		super(Component.translatable("gui.aoa3.adventGui.help"));

		tipNumber = RandomUtil.randomNumberUpTo(IntegrationManager.isPatchouliActive() ? 12 : 13);
	}

	@Override
	protected void init() {
		addRenderableWidget(new ThemeButton(640, 35, 30, 40));
		addRenderableWidget(new WebLinkButton(getMinecraft(), 640, 130, 76, 18, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.linkDiscord"), stringToURI("https://discord.gg/DNYqNNq")));
		addRenderableWidget(new WebLinkButton(getMinecraft(), 640, 170, 108, 18, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.linkWiki"), stringToURI("https://adventofascension.gamepedia.com/Advent_of_Ascension_Wiki")));
		addRenderableWidget(new WebLinkButton(getMinecraft(), 640, 210, 60, 18, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.linkGithub"), stringToURI("https://github.com/Tslat/Advent-Of-Ascension")));
		addRenderableWidget(new WebLinkButton(getMinecraft(), 640, 250, 80, 18, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.linkPatreon"), stringToURI("https://www.patreon.com/Tslat")));
		addRenderableWidget(new WebLinkButton(getMinecraft(), 640, 290, 68, 18, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.linkUpdate"), stringToURI("https://adventofascension.gamepedia.com/Download")));
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		PoseStack poseStack = guiGraphics.pose();

		RenderUtil.renderVerticalGradient(poseStack, AdventMainGui.scaledTabRootX + 35, AdventMainGui.scaledTabRootY + 55, 570, 225, -1072689136, -804253680);
		RenderUtil.renderCenteredScaledText(poseStack, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.theme"), AdventMainGui.scaledTabRootX + 672, AdventMainGui.scaledTabRootY + 10, 2f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);
		RenderUtil.renderCenteredScaledText(poseStack, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.links"), AdventMainGui.scaledTabRootX + 672, AdventMainGui.scaledTabRootY + 100, 2f, ColourUtil.WHITE, RenderUtil.TextRenderType.OUTLINED);

		RenderUtil.renderScaledText(poseStack, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.title"), AdventMainGui.scaledTabRootX + 80, AdventMainGui.scaledTabRootY + 25, 2.5f, ColourUtil.RGB(239, 137, 119), RenderUtil.TextRenderType.OUTLINED);

		poseStack.pushPose();
		poseStack.scale(1.5625f, 1.5625f, 1.5625f);
		RenderUtil.renderWrappedText(poseStack, Component.literal(LocaleUtil.getLocaleString("gui.aoa3.adventGui.help.description").replace("<br>", "\n")), (int)((AdventMainGui.scaledTabRootX + 40) / 1.5625f), (int)((AdventMainGui.scaledTabRootY + 60) / 1.5625f), 360, ColourUtil.WHITE, RenderUtil.TextRenderType.NORMAL);
		poseStack.popPose();

		RenderUtil.renderScaledText(poseStack, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.tip", LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help.tip." + tipNumber)), AdventMainGui.scaledTabRootX + 30, AdventMainGui.scaledTabRootY + 310, 1.8f, ColourUtil.WHITE, RenderUtil.TextRenderType.DROP_SHADOW);

		super.render(guiGraphics, mouseX, mouseY, partialTicks);
	}

	@Override
	public void renderTransparentBackground(GuiGraphics pGuiGraphics) {}

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
			super(x, y, width, height, name, button -> {}, DEFAULT_NARRATION);

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
		public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

				isHovered = isMouseInRegion(mouseX, mouseY, getX(), getY());
				int stringColour = ColourUtil.RGB(239, 137, 119);

				if (isHovered)
					stringColour = ColourUtil.RGB(247, 239, 0);

				RenderUtil.renderCenteredScaledText(guiGraphics.pose(), getMessage(), (int)(AdventMainGui.scaledTabRootX + getX() + width / 2f), AdventMainGui.scaledTabRootY + getY(), 2f, stringColour, RenderUtil.TextRenderType.OUTLINED);
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
			super(x, y, width, height, Component.literal(AdventMainGui.theme.name()), button -> {}, DEFAULT_NARRATION);
		}

		@Override
		public void onClick(double mouseX, double mouseY) {
			AdventMainGui.changeTheme();
			setMessage(Component.literal(AdventMainGui.theme.name()));
			setFocused(false);
		}

		@Override
		public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				isHovered = isMouseInRegion(mouseX, mouseY, getX(), getY());
				width = (int)(10 + Minecraft.getInstance().font.width(getMessage()) * 1.5f);

				RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
				RenderSystem.setShaderTexture(0, AdventMainGui.theme.menuButtonTexture());
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

				RenderUtil.renderScaledCustomSizedTexture(guiGraphics.pose(), AdventMainGui.scaledTabRootX + getX(), AdventMainGui.scaledTabRootY + getY(), 0, isHovered ? 60 : 0, 180, 60, width, height, 180, 180);


				int stringColour = 14737632;

				if (getFGColor() >= 0)
					stringColour = getFGColor();

				if (this.isHovered)
					stringColour = 16777120;

				RenderUtil.renderCenteredScaledText(guiGraphics.pose(), getMessage(), (int)(AdventMainGui.scaledTabRootX + getX() + width / 2f), (int)(AdventMainGui.scaledTabRootY + getY() + height / 2.5), 1.5f, stringColour, RenderUtil.TextRenderType.DROP_SHADOW);
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

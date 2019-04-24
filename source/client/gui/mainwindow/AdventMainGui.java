package net.tslat.aoa3.client.gui.mainwindow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WebUtil;

import javax.annotation.Nullable;
import java.io.IOException;

@SideOnly(Side.CLIENT)
public class AdventMainGui extends GuiScreen {
	@Nullable
	protected static ResourceLocation overlayTexture = null;
	protected static ResourceLocation backgroundTexture;
	protected static ResourceLocation menuButtonTexture;

	private static ADVENT_WINDOW_TAB selectedTab = ADVENT_WINDOW_TAB.PLAYER;
	protected static MainWindowThemes currentTheme;
	@Nullable
	private static GuiScreen tabScreen;

	protected EntityPlayer player;

	protected static final int guiWidth = 976;
	protected static final int guiHeight = 480;
	protected static final float scale = 0.45f;
	protected static final float scaleInverse = 1 / scale;
	protected static int scaledRootX;
	protected static int scaledRootY;
	protected static int scaledTabRootX;
	protected static int scaledTabRootY;

	private static int updateMessageTicker = 0;

	public AdventMainGui(EntityPlayer player) {
		this.player = player;
	}

	@Override
	public void initGui() {
		super.initGui();

		this.buttonList.add(new AdventMainGuiTabButton(0, 11, 129, StringUtil.getLocaleString("gui.aoamain.tab.player"), ADVENT_WINDOW_TAB.PLAYER));
		this.buttonList.add(new AdventMainGuiTabButton(1, 11, 199, StringUtil.getLocaleString("gui.aoamain.tab.bestiary"), ADVENT_WINDOW_TAB.BESTIARY));
		this.buttonList.add(new AdventMainGuiTabButton(2, 11, 269, StringUtil.getLocaleString("gui.aoamain.tab.leaderboards"), ADVENT_WINDOW_TAB.LEADERBOARDS));
		this.buttonList.add(new AdventMainGuiTabButton(3, 11, 339, StringUtil.getLocaleString("gui.aoamain.tab.guides"), ADVENT_WINDOW_TAB.GUIDES));
		this.buttonList.add(new AdventMainGuiTabButton(4, 11, 409, StringUtil.getLocaleString("gui.aoamain.tab.help"), ADVENT_WINDOW_TAB.HELP));

		this.buttonList.get(1).enabled = false;
		this.buttonList.get(2).enabled = false;
		//this.buttonList.get(3).enabled = false;

		correctGuiPositions();

		if (tabScreen == null)
			initTabScreen();

		setThemeTextures();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();

		this.mc.getTextureManager().bindTexture(backgroundTexture);

		GlStateManager.pushMatrix();
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.scale(scale, scale, scale);

		drawModalRectWithCustomSizedTexture(scaledRootX, scaledRootY, 24, 16, guiWidth, guiHeight, 1024, 512);

		GlStateManager.scale(scaleInverse, scaleInverse, scaleInverse);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		super.drawScreen(mouseX, mouseY, partialTicks);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		GlStateManager.scale(scale, scale, scale);

		if (overlayTexture != null) {
			mc.getTextureManager().bindTexture(overlayTexture);
			drawModalRectWithCustomSizedTexture(scaledRootX - ((1024 - guiWidth) / 2), scaledRootY - ((512 - guiHeight) / 2), 0, 0, 1024, 512, 1024, 512);
		}

		GlStateManager.scale(1.25, 1.25, 1.25);
		mc.fontRenderer.drawString("v" + AdventOfAscension.version, (scaledRootX + 175) / 1.25f, (scaledRootY + 85) / 1.25f, Enums.RGBIntegers.GOLD_YELLOW, true);

		if (WebUtil.isUpdateAvailable()) {
			updateMessageTicker--;

			if (updateMessageTicker <= -30)
				updateMessageTicker = 90;

			if (updateMessageTicker > 0) {
				String msg = StringUtil.getLocaleStringWithArguments("gui.aoamain.update", WebUtil.getLatestVersion());
				mc.fontRenderer.drawString(msg, (int)((scaledRootX + 925 - mc.fontRenderer.getStringWidth(msg)) / 1.25f), (int)((scaledRootY + 105) / 1.25f), Enums.RGBIntegers.RED_2, true);
			}
		}

		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.scale(0.8, 0.8, 0.8);

		if (tabScreen != null)
			tabScreen.drawScreen(mouseX, mouseY, partialTicks);

		GlStateManager.scale(scaleInverse, scaleInverse, scaleInverse);
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.popMatrix();
	}

	protected enum ADVENT_WINDOW_TAB {
		PLAYER,
		BESTIARY,
		GUIDES,
		LEADERBOARDS,
		HELP
	}

	private static class AdventMainGuiTabButton extends GuiButton {
		private static final int buttonWidth = 180;
		private static final int buttonHeight = 60;

		private final ADVENT_WINDOW_TAB tabID;

		public AdventMainGuiTabButton(int buttonId, int x, int y, String buttonText, ADVENT_WINDOW_TAB tab) {
			super(buttonId, x, y, buttonWidth, buttonHeight, buttonText);

			this.tabID = tab;
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				mc.getTextureManager().bindTexture(menuButtonTexture);
				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				GlStateManager.scale(scale, scale, scale);

				hovered = isMouseInRegion(mouseX, mouseY, x, y);
				int textureX = 0;
				int textureY = buttonHeight * (tabID == selectedTab ? 0 : (getHoverState(this.hovered) == 2) ? 1 : 2);

				drawScaledCustomSizeModalRect(scaledRootX + x, scaledRootY + y, textureX, textureY, 180, 60, 180, 60, 180, 180);

				int stringColour = Enums.RGBIntegers.LIGHT_CORAL;

				if (packedFGColour != 0) {
					stringColour = packedFGColour;
				}
				else if (!this.enabled) {
					stringColour = 10526880;
				}
				else if (this.hovered || tabID == selectedTab) {
					stringColour = Enums.RGBIntegers.YELLOW_2;
				}

				drawCenteredScaledString(mc.fontRenderer, displayString, scaledRootX + x + 90, scaledRootY + y + 25, 2f, stringColour, StringRenderType.OUTLINED);
				GlStateManager.scale(scaleInverse, scaleInverse, scaleInverse);
			}
		}

		@Override
		public boolean mousePressed(Minecraft mc, int mouseX, int mouseY) {
			return enabled && hovered && selectedTab != tabID;
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= (int)((scaledRootX + buttonX) * scale) && mouseX <= (int)((scaledRootX + buttonX + buttonWidth) * scale) && mouseY >= (int)((scaledRootY + buttonY) * scale) && mouseY <= (int)((scaledRootY + buttonY + buttonHeight) * scale);
		}
	}

	private static void setThemeTextures() {
		try {
			currentTheme = MainWindowThemes.valueOf(ConfigurationUtil.mainWindowTheme);
		}
		catch (IllegalArgumentException e) {
			currentTheme = MainWindowThemes.Default;
		}

		switch (currentTheme) {
			case Jungle:
				backgroundTexture = new ResourceLocation("aoa3", "textures/gui/maingui/themes/jungle/background.png");
				menuButtonTexture = new ResourceLocation("aoa3", "textures/gui/maingui/themes/jungle/tab_buttons.png");
				overlayTexture = new ResourceLocation("aoa3", "textures/gui/maingui/themes/jungle/overlay.png");
				break;
			case Ancient_Ruins:
				backgroundTexture = new ResourceLocation("aoa3", "textures/gui/maingui/themes/default/background.png");
				menuButtonTexture = new ResourceLocation("aoa3", "textures/gui/maingui/themes/default/tab_buttons.png");
				overlayTexture = new ResourceLocation("aoa3", "textures/gui/maingui/themes/ancientruins/overlay.png");
				break;
			case Hell:
				backgroundTexture = new ResourceLocation("aoa3", "textures/gui/maingui/themes/hell/background.png");
				menuButtonTexture = new ResourceLocation("aoa3", "textures/gui/maingui/themes/hell/tab_buttons.png");
				overlayTexture = new ResourceLocation("aoa3", "textures/gui/maingui/themes/hell/overlay.png");
				break;
			default:
				ConfigurationUtil.mainWindowTheme = "Default";
				currentTheme = MainWindowThemes.Default;
			case Default:
				backgroundTexture = new ResourceLocation("aoa3", "textures/gui/maingui/themes/default/background.png");
				menuButtonTexture = new ResourceLocation("aoa3", "textures/gui/maingui/themes/default/tab_buttons.png");
				overlayTexture = null;
				break;
		}
	}

	protected static void drawOutlinedText(FontRenderer fontRenderer, String msg, int x, int y, int colour, float currentScale) {
		if (!Minecraft.getMinecraft().isUnicode())
			currentScale = 1;

		fontRenderer.drawString(msg, x, y + (1 / currentScale), 0, false);
		fontRenderer.drawString(msg, x, y - (1 / currentScale), 0, false);
		fontRenderer.drawString(msg, x + (1 / currentScale), y, 0, false);
		fontRenderer.drawString(msg, x - (1 / currentScale), y, 0, false);
		fontRenderer.drawString(msg, x, y, colour, false);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	}

	protected static void drawScaledString(FontRenderer fontRenderer, String msg, int x, int y, float scale, int colour, StringRenderType renderType) {
		float realX = x / scale;
		float realY = y / scale;

		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, scale);

		if (renderType == StringRenderType.OUTLINED) {
			if (!Minecraft.getMinecraft().isUnicode())
				scale = 1;

			fontRenderer.drawString(msg, realX, realY + (1 / scale), 0, false);
			fontRenderer.drawString(msg, realX, realY - (1 / scale), 0, false);
			fontRenderer.drawString(msg, realX + (1 / scale), realY, 0, false);
			fontRenderer.drawString(msg, realX - (1 / scale), realY, 0, false);
		}

		fontRenderer.drawString(msg, realX, realY, colour, renderType == StringRenderType.DROP_SHADOW);
		GlStateManager.popMatrix();
	}

	protected static void drawCenteredScaledString(FontRenderer fontRenderer, String msg, int x, int y, float scale, int colour, StringRenderType renderType) {
		float realX = (x - fontRenderer.getStringWidth(msg) * scale / 2f) / scale;
		float realY = y / scale;

		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, scale);

		if (renderType == StringRenderType.OUTLINED) {
			if (!Minecraft.getMinecraft().isUnicode())
				scale = 1;

			fontRenderer.drawString(msg, realX, realY + (1 / scale), 0, false);
			fontRenderer.drawString(msg, realX, realY - (1 / scale), 0, false);
			fontRenderer.drawString(msg, realX + (1 / scale), realY, 0, false);
			fontRenderer.drawString(msg, realX - (1 / scale), realY, 0, false);
		}

		fontRenderer.drawString(msg, realX, realY, colour, renderType == StringRenderType.DROP_SHADOW);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		GlStateManager.popMatrix();
	}

	protected static void drawSplitLineStringWithShadow(FontRenderer fontRenderer, String msg, int x, int y, int wrapWidth, float scale, int colour) {
		GlStateManager.pushMatrix();
		GlStateManager.scale(scale, scale, scale);

		fontRenderer.drawSplitString(msg, (int)((x + 1) / scale), (int)((y + 1) / scale), (int)((wrapWidth + 1) / scale), Enums.RGBIntegers.BLACK);
		fontRenderer.drawSplitString(msg, (int)(x / scale), (int)(y / scale), (int)(wrapWidth / scale), colour);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		GlStateManager.popMatrix();
	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();

		if (tabScreen != null)
			tabScreen.handleMouseInput();
	}

	protected enum StringRenderType {
		NORMAL,
		DROP_SHADOW,
		OUTLINED
	}

	protected enum MainWindowThemes {
		Default,
		Jungle,
		Ancient_Ruins,
		Hell
	}

	private void initTabScreen() {
		switch (selectedTab) {
			case PLAYER:
				tabScreen = new AdventGuiTabPlayer();
				tabScreen.setWorldAndResolution(mc, (int)((width + 182) * scale), (int)((height + 118) * scale));
				break;
			case HELP:
				tabScreen = new AdventGuiTabHelp();
				tabScreen.setWorldAndResolution(mc, (int)((width + 182) * scale), (int)((height + 118) * scale));
				break;
			case BESTIARY:
			case GUIDES:
				tabScreen = new AdventGuiTabGuides();
				tabScreen.setWorldAndResolution(mc, (int)((width + 182) * scale), (int)((height + 118) * scale));
				break;
			case LEADERBOARDS:
			default:
				tabScreen = null;
				break;
		}
	}

	@Override
	public boolean doesGuiPauseGame() {
		return ConfigurationUtil.mainWindowPausesGame;
	}

	private void correctGuiPositions() {
		ScaledResolution res = new ScaledResolution(this.mc);

		scaledRootX = (int) (Math.round((res.getScaledWidth() / 2d) / scale) - Math.round(guiWidth / 2d));
		scaledRootY = (int) (Math.round((res.getScaledHeight() / 2d) / scale) - Math.round(guiHeight / 2d));
		scaledTabRootX = scaledRootX + 201;
		scaledTabRootY = scaledRootY + 129;
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		if (button instanceof AdventMainGuiTabButton) {
			AdventMainGui.selectedTab = ((AdventMainGuiTabButton)button).tabID;
			initTabScreen();
		}
	}

	@Override
	public void onResize(Minecraft mcIn, int width, int height) {
		super.onResize(mcIn, width, height);

		correctGuiPositions();

		if (tabScreen != null)
			tabScreen.onResize(mc, (int)((width + 182) * scale), (int)((height + 118) * scale));
	}

	@Override
	public void onGuiClosed() {
		super.onGuiClosed();

		if (tabScreen != null)
			tabScreen.onGuiClosed();
	}

	protected static void changeTheme(MainWindowThemes theme) {
		ConfigurationUtil.changeMainWindowTheme(theme.toString());
		setThemeTextures();
	}
}

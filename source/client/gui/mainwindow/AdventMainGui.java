package net.tslat.aoa3.client.gui.mainwindow;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.event.KeyBinder;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.RenderUtil;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.WebUtil;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;

@SideOnly(Side.CLIENT)
public class AdventMainGui extends GuiScreen implements IProgressMeter {
	private static final ResourceLocation titleResource = AdventOfAscension.instance().getCurrentHoliday() == AdventOfAscension.Holiday.APRIL_FOOLS ? new ResourceLocation("aoa3", "textures/gui/maingui/aoa_title_alt.png") : new ResourceLocation("aoa3", "textures/gui/maingui/aoa_title.png");

	public static String currentLanguage = FMLCommonHandler.instance().getCurrentLanguage();

	protected static final ArrayList<AdventGuiTextures> textures = new ArrayList<AdventGuiTextures>();
	protected static int currentTextureIndex = 0;

	private static ADVENT_WINDOW_TAB selectedTab = ADVENT_WINDOW_TAB.PLAYER;
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

		currentLanguage = FMLCommonHandler.instance().getCurrentLanguage();

		if (textures.isEmpty())
			prepTextures();
	}

	public static boolean isBlockingKeys() {
		if (tabScreen instanceof AdventGuiTabBestiary) {
			GuiTextField bestiarySearchField = ((AdventGuiTabBestiary)tabScreen).searchField;

			return bestiarySearchField != null && bestiarySearchField.isFocused();
		}

		return false;
	}

	@Override
	public void initGui() {
		super.initGui();

		this.buttonList.add(new AdventMainGuiTabButton(0, 11, 129, StringUtil.getLocaleString("gui.aoamain.tab.player"), ADVENT_WINDOW_TAB.PLAYER));
		this.buttonList.add(new AdventMainGuiTabButton(1, 11, 199, StringUtil.getLocaleString("gui.aoamain.tab.bestiary"), ADVENT_WINDOW_TAB.BESTIARY));
		//this.buttonList.add(new AdventMainGuiTabButton(2, 11, 269, "", ADVENT_WINDOW_TAB.LEADERBOARDS));
		this.buttonList.add(new AdventMainGuiTabButton(2, 11, 269, StringUtil.getLocaleString("gui.aoamain.tab.guides"), ADVENT_WINDOW_TAB.GUIDES));
		this.buttonList.add(new AdventMainGuiTabButton(3, 11, 339, StringUtil.getLocaleString("gui.aoamain.tab.help"), ADVENT_WINDOW_TAB.HELP));

		//this.buttonList.get(2).enabled = false;

		correctGuiPositions();

		if (tabScreen == null) {
			initTabScreen();
		}
		else {
			tabScreen.initGui();
		}

		for (int i = 0; i < textures.size(); i++) {
			if (textures.get(i).name.equalsIgnoreCase(ConfigurationUtil.MainConfig.mainWindowTheme)) {
				currentTextureIndex = i;

				break;
			}
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		this.drawDefaultBackground();

		AdventGuiTextures currentTextures = textures.get(currentTextureIndex);

		this.mc.getTextureManager().bindTexture(currentTextures.backgroundTexture);

		GlStateManager.pushMatrix();
		GlStateManager.color(1, 1, 1, 1);
		GlStateManager.scale(scale, scale, scale);

		drawModalRectWithCustomSizedTexture(scaledRootX, scaledRootY, 24, 16, guiWidth, guiHeight, 1024, 512);

		mc.getTextureManager().bindTexture(titleResource);
		drawModalRectWithCustomSizedTexture(scaledRootX - ((1024 - guiWidth) / 2) + 68, scaledRootY - ((512 - guiHeight) / 2) + 21, 0, 0, 892, 112, 892, 112);

		GlStateManager.scale(scaleInverse, scaleInverse, scaleInverse);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		super.drawScreen(mouseX, mouseY, partialTicks);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		GlStateManager.scale(scale, scale, scale);

		if (currentTextures.overlayTexture != null) {
			mc.getTextureManager().bindTexture(currentTextures.overlayTexture);
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

	@Override
	public void onStatsUpdated() {
		if (tabScreen instanceof IProgressMeter)
			((IProgressMeter)tabScreen).onStatsUpdated();
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

		private AdventMainGuiTabButton(int buttonId, int x, int y, String buttonText, ADVENT_WINDOW_TAB tab) {
			super(buttonId, x, y, buttonWidth, buttonHeight, buttonText);

			this.tabID = tab;
		}

		@Override
		public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				mc.getTextureManager().bindTexture(textures.get(currentTextureIndex).menuButtonTexture);
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

				RenderUtil.drawCenteredScaledString(mc.fontRenderer, displayString, scaledRootX + x + 90, scaledRootY + y + 25, 2f, stringColour, RenderUtil.StringRenderType.OUTLINED);
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

	private static void prepTextures() {
		textures.add(new AdventGuiTextures("Default", null, null, null));
		textures.add(new AdventGuiTextures("Jungle", new ResourceLocation("aoa3", "textures/gui/maingui/themes/jungle/background.png"), new ResourceLocation("aoa3", "textures/gui/maingui/themes/jungle/tab_buttons.png"), new ResourceLocation("aoa3", "textures/gui/maingui/themes/jungle/overlay.png")));
		textures.add(new AdventGuiTextures("Ancient Ruins", new ResourceLocation("aoa3", "textures/gui/maingui/themes/dark/background.png"), new ResourceLocation("aoa3", "textures/gui/maingui/themes/dark/tab_buttons.png"), new ResourceLocation("aoa3", "textures/gui/maingui/themes/ancientruins/overlay.png")));
		textures.add(new AdventGuiTextures("Hell", new ResourceLocation("aoa3", "textures/gui/maingui/themes/hell/background.png"), new ResourceLocation("aoa3", "textures/gui/maingui/themes/hell/tab_buttons.png"), new ResourceLocation("aoa3", "textures/gui/maingui/themes/hell/overlay.png")));
		textures.add(new AdventGuiTextures("Crystals", new ResourceLocation("aoa3", "textures/gui/maingui/themes/crystals/background.png"), new ResourceLocation("aoa3", "textures/gui/maingui/themes/crystals/tab_buttons.png"), new ResourceLocation("aoa3", "textures/gui/maingui/themes/crystals/overlay.png")));
		textures.add(new AdventGuiTextures("Transparent", new ResourceLocation("aoa3", "textures/gui/maingui/themes/transparent/background.png"), new ResourceLocation("aoa3", "textures/gui/maingui/themes/transparent/tab_buttons.png"), null));
		textures.add(new AdventGuiTextures("Dark", new ResourceLocation("aoa3", "textures/gui/maingui/themes/dark/background.png"), new ResourceLocation("aoa3", "textures/gui/maingui/themes/dark/tab_buttons.png"), null));
	}

	@Override
	public void handleMouseInput() throws IOException {
		super.handleMouseInput();

		if (tabScreen != null)
			tabScreen.handleMouseInput();
	}

	@Override
	public void handleKeyboardInput() throws IOException {
		if (!isBlockingKeys() && Keyboard.getEventKey() == KeyBinder.keyAdventGui.getKeyCode() && Keyboard.getEventKeyState())
			Minecraft.getMinecraft().displayGuiScreen(null);

		super.handleKeyboardInput();

		if (tabScreen != null)
			tabScreen.handleKeyboardInput();
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
				tabScreen = new AdventGuiTabBestiary();
				tabScreen.setWorldAndResolution(mc, (int)((width + 182) * scale), (int)((height + 118) * scale));
				break;
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

	private ResourceLocation getTitleBarTexture() {
		return titleResource;
	}

	@Override
	public boolean doesGuiPauseGame() {
		return ConfigurationUtil.MainConfig.mainWindowPausesGame;
	}

	private void correctGuiPositions() {
		ScaledResolution res = new ScaledResolution(this.mc);

		scaledRootX = (int) (Math.round((res.getScaledWidth() / 2d) / scale) - Math.round(guiWidth / 2d));
		scaledRootY = (int) (Math.round((res.getScaledHeight() / 2d) / scale) - Math.round(guiHeight / 2d));
		scaledTabRootX = scaledRootX + 201;
		scaledTabRootY = scaledRootY + 129;
	}

	@Override
	protected void actionPerformed(GuiButton button) {
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

	public static void addTheme(String name, @Nullable ResourceLocation backgroundTexture, @Nullable ResourceLocation menuButtonsTexture, @Nullable ResourceLocation overlayTexture) {
		textures.add(new AdventGuiTextures(name, backgroundTexture, menuButtonsTexture, overlayTexture));
	}

	protected static void changeTheme() {
		ConfigurationUtil.changeMainWindowTheme(textures.get(currentTextureIndex).name);
	}

	protected static class AdventGuiTextures {
		private final String name;
		@Nullable
		protected final ResourceLocation overlayTexture;
		protected final ResourceLocation backgroundTexture;
		protected final ResourceLocation menuButtonTexture;

		private AdventGuiTextures(String name, @Nullable ResourceLocation backgroundTexture, @Nullable ResourceLocation menuButtonTexture, @Nullable ResourceLocation overlayTexture) {
			this.name = name;
			this.backgroundTexture = backgroundTexture == null ? new ResourceLocation("aoa3", "textures/gui/maingui/themes/default/background.png") : backgroundTexture;
			this.menuButtonTexture = menuButtonTexture == null ? new ResourceLocation("aoa3", "textures/gui/maingui/themes/default/tab_buttons.png") : menuButtonTexture;
			this.overlayTexture = overlayTexture;
		}
	}
}

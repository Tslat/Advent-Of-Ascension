package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.achievement.StatsUpdateListener;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.AoAKeybinds;
import net.tslat.aoa3.common.registration.AoAConfigs;
import net.tslat.aoa3.data.client.AdventGuiThemeReloadListener;
import net.tslat.aoa3.integration.IntegrationManager;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;

public class AdventMainGui extends Screen implements StatsUpdateListener {	
	private static final ResourceLocation TITLE = HolidayUtil.isAprilFools() ? new ResourceLocation("aoa3", "textures/gui/adventgui/aoa_title_alt.png") : new ResourceLocation("aoa3", "textures/gui/adventgui/aoa_title.png");
	protected static final int GUI_WIDTH = 1024;
	protected static final int GUI_HEIGHT = 512;
	protected static final int BACKGROUND_TEXTURE_WIDTH = 976;
	protected static final int BACKGROUND_TEXTURE_HEIGHT = 480;
	protected static final float SCALE = 0.45f;

	protected static AdventMainGui instance;
	protected static AdventGuiThemeReloadListener.AdventGuiTheme theme = null;

	private static ADVENT_WINDOW_TAB selectedTab = ADVENT_WINDOW_TAB.PLAYER;
	@Nullable
	private static Screen tabScreen;

	protected Player player;

	protected static int scaledRootX;
	protected static int scaledRootY;
	protected static int scaledTabRootX;
	protected static int scaledTabRootY;

	private static int updateMessageTicker = 0;

	public AdventMainGui(Player player) {
		super(Component.translatable("gui.aoa3.adventGui.title"));

		this.player = player;
		instance = this;
		tabScreen = null;
	}

	@Override
	protected void init() {
		theme = AdventGuiThemeReloadListener.setTheme(AoAConfigs.CLIENT.adventGuiTheme.get());

		addRenderableWidget(new AdventMainGuiTabButton(11, 129, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.stats"), ADVENT_WINDOW_TAB.PLAYER));
		addRenderableWidget(new AdventMainGuiTabButton(11, 199, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.bestiary"), ADVENT_WINDOW_TAB.BESTIARY));
		addRenderableWidget(new AdventMainGuiTabButton(11, 269, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.lore"), ADVENT_WINDOW_TAB.LORE));
		addRenderableWidget(new AdventMainGuiTabButton(11, 339, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.leaderboards"), ADVENT_WINDOW_TAB.LEADERBOARDS));
		addRenderableWidget(new AdventMainGuiTabButton(11, 409, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help"), ADVENT_WINDOW_TAB.HELP));

		correctGuiPositions();
		initTabScreen();
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		renderBackground(guiGraphics);

		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		RenderSystem.setShaderTexture(0, theme.backgroundTexture());

		PoseStack poseStack = guiGraphics.pose();

		poseStack.pushPose();
		RenderSystem.setShaderColor(1, 1, 1, 1);
		poseStack.scale(SCALE, SCALE, SCALE);

		RenderUtil.renderCustomSizedTexture(poseStack, scaledRootX, scaledRootY, 24, 16, BACKGROUND_TEXTURE_WIDTH, BACKGROUND_TEXTURE_HEIGHT, GUI_WIDTH, GUI_HEIGHT);
		RenderSystem.setShaderTexture(0, TITLE);
		RenderUtil.renderCustomSizedTexture(poseStack, scaledRootX - ((GUI_WIDTH - BACKGROUND_TEXTURE_WIDTH) / 2) + 68, scaledRootY - ((GUI_HEIGHT - BACKGROUND_TEXTURE_HEIGHT) / 2) + 21, 0, 0, 892, 112, 892, 112);

		super.render(guiGraphics, mouseX, mouseY, partialTicks);
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);

		if (theme.overlayTexture() != null) {
			RenderSystem.setShaderTexture(0, theme.overlayTexture());
			RenderSystem.enableBlend();
			RenderUtil.renderCustomSizedTexture(poseStack, scaledRootX - ((GUI_WIDTH - BACKGROUND_TEXTURE_WIDTH) / 2), scaledRootY - ((GUI_HEIGHT - BACKGROUND_TEXTURE_HEIGHT) / 2), 0, 0, GUI_WIDTH, GUI_HEIGHT, GUI_WIDTH, GUI_HEIGHT);
			RenderSystem.disableBlend();
		}

		RenderUtil.renderScaledText(poseStack, Component.literal("v" + AdventOfAscension.VERSION), scaledRootX + 175, scaledRootY + 85, 1.25f, ColourUtil.RGB(255, 223, 0), RenderUtil.TextRenderType.DROP_SHADOW);

		if (WebUtil.isUpdateAvailable()) {
			updateMessageTicker--;

			if (updateMessageTicker <= -30)
				updateMessageTicker = 90;

			if (updateMessageTicker > 0) {
				Component msg = LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.update", Component.literal(WebUtil.getLatestVersion()));

				RenderUtil.renderScaledText(poseStack, msg, scaledRootX + 915 - font.width(msg), scaledRootY + 105, 1.25f, ColourUtil.RGB(229, 0, 0), RenderUtil.TextRenderType.DROP_SHADOW);
			}
		}

		if (tabScreen != null)
			tabScreen.render(guiGraphics, mouseX, mouseY, partialTicks);

		RenderSystem.setShaderColor(1, 1, 1, 1);
		poseStack.popPose();
	}

	public static boolean isBlockingKeys() {
		if (tabScreen instanceof AdventGuiTabBestiary) {
			EditBox bestiarySearchField = ((AdventGuiTabBestiary)tabScreen).searchField;

			return bestiarySearchField != null && bestiarySearchField.isFocused();
		}

		return false;
	}

	@Override
	public void onStatsUpdated() {
		if (tabScreen instanceof StatsUpdateListener)
			((StatsUpdateListener)tabScreen).onStatsUpdated();
	}

	protected enum ADVENT_WINDOW_TAB {
		PLAYER,
		BESTIARY,
		LORE,
		LEADERBOARDS,
		HELP
	}

	private static class AdventMainGuiTabButton extends Button {
		private static final int buttonWidth = 180;
		private static final int buttonHeight = 60;

		private final ADVENT_WINDOW_TAB tabID;

		private AdventMainGuiTabButton(int x, int y, Component buttonText, ADVENT_WINDOW_TAB tab) {
			super(x, y, buttonWidth, buttonHeight, buttonText, button -> {}, DEFAULT_NARRATION);

			this.tabID = tab;

			if (tab == ADVENT_WINDOW_TAB.LORE && !IntegrationManager.isPatchouliActive())
				this.active = false;
		}

		@Override
		public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				Minecraft mc = Minecraft.getInstance();
				PoseStack poseStack = guiGraphics.pose();

				RenderSystem.setShaderTexture(0, theme.menuButtonTexture());
				RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

				isHovered = isMouseInRegion(mouseX, mouseY, getX(), getY());
				int textureX = 0;
				int textureY = buttonHeight * (tabID == selectedTab ? 0 : RenderUtil.selectVForWidgetState(this, 2, 2, 1));

				RenderUtil.renderScaledCustomSizedTexture(poseStack, scaledRootX + getX(), scaledRootY + getY(), textureX, textureY, 180, 60, 180, 60, 180, 180);

				int stringColour = 14737632;

				if (getFGColor() >= 0)
					stringColour = getFGColor();

				if (isHovered() && this.tabID != selectedTab)
					stringColour = 16777120;

				RenderUtil.renderCenteredScaledText(poseStack, getMessage(), scaledRootX + getX() + 90, scaledRootY + getY() + 25, 2f, stringColour, RenderUtil.TextRenderType.OUTLINED);

				poseStack.pushPose();
				poseStack.scale(2f, 2f, 2f);

				if (isHovered) {
					if (!active && this.tabID == ADVENT_WINDOW_TAB.LORE) {
						setTooltip(Tooltip.create(Component.translatable("gui." + AdventOfAscension.MOD_ID + ".adventGui.lore.patchouli")));
					}
					else {
						setTooltip(null);
					}
				}

				//if (isHovered)
					//renderToolTip(matrix, (int)(mouseX / SCALE / 2), (int)(mouseY / SCALE / 2));

				poseStack.popPose();
			}
		}

		@Override
		protected boolean clicked(double mouseX, double mouseY) {
			return active && isHovered && selectedTab != tabID;
		}

		@Override
		public void onClick(double mouseX, double mouseY) {
			AdventMainGui.selectedTab = tabID;
			AdventMainGui.tabScreen = null;
			AdventMainGui.instance.initTabScreen();
		}

		private boolean isMouseInRegion(int mouseX, int mouseY, int buttonX, int buttonY) {
			return mouseX >= (int)((scaledRootX + buttonX) * SCALE) && mouseX <= (int)((scaledRootX + buttonX + buttonWidth) * SCALE) && mouseY >= (int)((scaledRootY + buttonY) * SCALE) && mouseY <= (int)((scaledRootY + buttonY + buttonHeight) * SCALE);
		}
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollAmount) {
		if (tabScreen != null)
			return tabScreen.mouseScrolled(mouseX, mouseY, scrollAmount);

		return super.mouseScrolled(mouseX, mouseY, scrollAmount);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (tabScreen != null && tabScreen.mouseClicked(mouseX, mouseY, button))
			return true;

		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (tabScreen != null && tabScreen.mouseReleased(mouseX, mouseY, button))
			return true;

		return super.mouseReleased(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseDragged(double mouseX, double mouseY, int button, double dragX, double dragY) {
		if (tabScreen != null && tabScreen.mouseDragged(mouseX, mouseY, button, dragX, dragY))
			return true;

		return super.mouseDragged(mouseX, mouseY, button, dragX, dragY);
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (!isBlockingKeys() && keyCode == AoAKeybinds.ADVENT_GUI.getKey().getValue()) {
			getMinecraft().setScreen(null);

			return true;
		}

		boolean used = false;

		if (tabScreen != null)
			used = tabScreen.keyPressed(keyCode, scanCode, modifiers);

		return super.keyPressed(keyCode, scanCode, modifiers) || used;
	}

	@Override
	public boolean charTyped(char character, int arg) {
		if (tabScreen != null)
			return tabScreen.charTyped(character, arg);

		return super.charTyped(character, arg);
	}

	@Override
	public boolean keyReleased(int keyCode, int scanCode, int modifiers) {
		if (!super.keyReleased(keyCode, scanCode, modifiers)) {
			if (tabScreen != null)
				return tabScreen.keyReleased(keyCode, scanCode, modifiers);

			return false;
		}

		return true;
	}

	private void initTabScreen() {
		Minecraft mc = getMinecraft();
		int tabWidth = (int)((width + 182) * SCALE);
		int tabHeight =  (int)((height + 118) * SCALE);

		if (tabScreen != null) {
			tabScreen.init(mc, tabWidth, tabHeight);
		}
		else {
			switch (selectedTab) {
				case PLAYER -> tabScreen = new AdventGuiTabPlayer();
				case HELP -> tabScreen = new AdventGuiTabHelp();
				case BESTIARY -> tabScreen = new AdventGuiTabBestiary();
				case LORE -> tabScreen = new AdventGuiTabLore();
				case LEADERBOARDS -> tabScreen = new AdventGuiTabLeaderboard();
				default -> {}
			}

			if (tabScreen != null) {
				tabScreen.init(mc, tabWidth, tabHeight);
				tabScreen.resize(mc, tabWidth, tabHeight);
			}
		}
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	private void correctGuiPositions() {
		Window window = getMinecraft().getWindow();

		scaledRootX = (int) (Math.round((window.getGuiScaledWidth() / 2d) / SCALE) - Math.round(BACKGROUND_TEXTURE_WIDTH / 2d));
		scaledRootY = (int) (Math.round((window.getGuiScaledHeight() / 2d) / SCALE) - Math.round(BACKGROUND_TEXTURE_HEIGHT / 2d));
		scaledTabRootX = scaledRootX + 201;
		scaledTabRootY = scaledRootY + 129;
	}

	@Override
	public void resize(Minecraft mc, int width, int height) {
		super.resize(mc, width, height);

		correctGuiPositions();

		if (tabScreen != null)
			tabScreen.resize(mc, (int)((width + 182) * SCALE), (int)((height + 118) * SCALE));
	}

	@Override
	public void removed() {
		super.removed();

		if (tabScreen != null)
			tabScreen.removed();
	}

	public static void changeTheme() {
		theme = AdventGuiThemeReloadListener.getNextTheme();

		AoAConfigs.CLIENT.changeAdventGuiTheme(theme.name());
	}
}

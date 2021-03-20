package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.resources.Language;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.client.Keybinds;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.util.*;

import javax.annotation.Nullable;
import java.util.ArrayList;

@OnlyIn(Dist.CLIENT)
public class AdventMainGui extends Screen implements IProgressMeter {
	private static final ResourceLocation titleResource = HolidayUtil.getCurrentHoliday() == HolidayUtil.Holiday.APRIL_FOOLS ? new ResourceLocation("aoa3", "textures/gui/maingui/aoa_title_alt.png") : new ResourceLocation("aoa3", "textures/gui/maingui/aoa_title.png");

	public static Language currentLanguage = Minecraft.getInstance().getLanguageManager().getSelected();

	protected static final ArrayList<AdventGuiTextures> textures = new ArrayList<AdventGuiTextures>();
	protected static int currentTextureIndex = 0;

	private static AdventMainGui instance;
	private static ADVENT_WINDOW_TAB selectedTab = ADVENT_WINDOW_TAB.PLAYER;
	@Nullable
	private static Screen tabScreen;

	protected PlayerEntity player;

	protected static final int guiWidth = 976;
	protected static final int guiHeight = 480;
	protected static final float scale = 0.45f;
	protected static final float scaleInverse = 1 / scale;
	protected static int scaledRootX;
	protected static int scaledRootY;
	protected static int scaledTabRootX;
	protected static int scaledTabRootY;

	private static int updateMessageTicker = 0;

	public AdventMainGui(PlayerEntity player) {
		super(new TranslationTextComponent("gui.aoa3.adventGui.title"));
		instance = this;

		if (this.player == null || this.player.getCommandSenderWorld() == null || this.player.getCommandSenderWorld() != player.getCommandSenderWorld())
			tabScreen = null;

		this.player = player;

		currentLanguage = Minecraft.getInstance().getLanguageManager().getSelected();

		if (textures.isEmpty())
			prepTextures();
	}

	public static boolean isBlockingKeys() {
		if (tabScreen instanceof AdventGuiTabBestiary) {
			TextFieldWidget bestiarySearchField = ((AdventGuiTabBestiary)tabScreen).searchField;

			return bestiarySearchField != null && bestiarySearchField.isFocused();
		}

		return false;
	}

	@Override
	protected void init() {
		addButton(new AdventMainGuiTabButton(11, 129, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.stats"), ADVENT_WINDOW_TAB.PLAYER));
		addButton(new AdventMainGuiTabButton(11, 199, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.bestiary"), ADVENT_WINDOW_TAB.BESTIARY));
		addButton(new AdventMainGuiTabButton(11, 269, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.guides"), ADVENT_WINDOW_TAB.GUIDES));
		addButton(new AdventMainGuiTabButton(11, 339, LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.help"), ADVENT_WINDOW_TAB.HELP));

		correctGuiPositions();
		initTabScreen();

		for (int i = 0; i < textures.size(); i++) {
			if (textures.get(i).name.equalsIgnoreCase(AoAConfig.CLIENT.adventGuiTheme.get())) {
				currentTextureIndex = i;

				break;
			}
		}
	}

	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrix);

		AdventGuiTextures currentTextures = textures.get(currentTextureIndex);

		getMinecraft().getTextureManager().bind(currentTextures.backgroundTexture);

		matrix.pushPose();
		RenderSystem.color4f(1, 1, 1, 1);
		matrix.scale(scale, scale, scale);

		RenderUtil.renderCustomSizedTexture(matrix, scaledRootX, scaledRootY, 24, 16, guiWidth, guiHeight, 1024, 512);

		getMinecraft().getTextureManager().bind(titleResource);
		RenderUtil.renderCustomSizedTexture(matrix, scaledRootX - ((1024 - guiWidth) / 2) + 68, scaledRootY - ((512 - guiHeight) / 2) + 21, 0, 0, 892, 112, 892, 112);

		matrix.scale(scaleInverse, scaleInverse, scaleInverse);
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		super.render(matrix, mouseX, mouseY, partialTicks);
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		matrix.scale(scale, scale, scale);

		if (currentTextures.overlayTexture != null) {
			getMinecraft().getTextureManager().bind(currentTextures.overlayTexture);
			RenderSystem.enableBlend();
			RenderUtil.renderCustomSizedTexture(matrix, scaledRootX - ((1024 - guiWidth) / 2), scaledRootY - ((512 - guiHeight) / 2), 0, 0, 1024, 512, 1024, 512);
			RenderSystem.disableBlend();
		}

		matrix.scale(1.25f, 1.25f, 1.25f);
		font.drawShadow(matrix, new StringTextComponent("v" + AdventOfAscension.VERSION), (scaledRootX + 175) / 1.25f, (scaledRootY + 85) / 1.25f, NumberUtil.RGB(255, 223, 0));

		if (WebUtil.isUpdateAvailable()) {
			updateMessageTicker--;

			if (updateMessageTicker <= -30)
				updateMessageTicker = 90;

			if (updateMessageTicker > 0) {
				ITextComponent msg = LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.update", new StringTextComponent(WebUtil.getLatestVersion()));

				font.drawShadow(matrix, msg, (int)((scaledRootX + 925 - font.width(msg)) / 1.25f), (int)((scaledRootY + 105) / 1.25f), NumberUtil.RGB(229, 0, 0));
			}
		}

		RenderSystem.color4f(1, 1, 1, 1);
		matrix.scale(0.8f, 0.8f, 0.8f);

		if (tabScreen != null)
			tabScreen.render(matrix, mouseX, mouseY, partialTicks);

		matrix.scale(scaleInverse, scaleInverse, scaleInverse);
		RenderSystem.color4f(1, 1, 1, 1);
		matrix.popPose();
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
		HELP
	}

	private static class AdventMainGuiTabButton extends Widget {
		private static final int buttonWidth = 180;
		private static final int buttonHeight = 60;

		private final ADVENT_WINDOW_TAB tabID;

		private AdventMainGuiTabButton(int x, int y, ITextComponent buttonText, ADVENT_WINDOW_TAB tab) {
			super(x, y, buttonWidth, buttonHeight, buttonText);

			this.tabID = tab;
		}

		@Override
		public void renderButton(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
			if (visible) {
				Minecraft mc = Minecraft.getInstance();
				mc.getTextureManager().bind(textures.get(currentTextureIndex).menuButtonTexture);
				RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
				matrix.scale(scale, scale, scale);

				isHovered = isMouseInRegion(mouseX, mouseY, x, y);
				int textureX = 0;
				int textureY = buttonHeight * (tabID == selectedTab ? 0 : (getYImage(this.isHovered) == 2) ? 1 : 2);

				RenderUtil.renderScaledCustomSizedTexture(matrix, scaledRootX + x, scaledRootY + y, textureX, textureY, 180, 60, 180, 60, 180, 180);

				int stringColour = NumberUtil.RGB(239, 137, 119);

				if (getFGColor() != 0) {
					stringColour = getFGColor();
				}
				else if (!this.active) {
					stringColour = 10526880;
				}
				else if (this.isHovered || tabID == selectedTab) {
					stringColour = NumberUtil.RGB(247, 239, 0);
				}

				RenderUtil.drawCenteredScaledMessage(matrix, mc.font, getMessage(), scaledRootX + x + 90, scaledRootY + y + 25, 2f, stringColour, RenderUtil.StringRenderType.OUTLINED);
				matrix.scale(scaleInverse, scaleInverse, scaleInverse);
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
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollAmount) {
		if (tabScreen != null)
			return tabScreen.mouseScrolled(mouseX, mouseY, scrollAmount);

		return super.mouseScrolled(mouseX, mouseY, scrollAmount);
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		boolean used = false;

		if (tabScreen != null)
			used = tabScreen.mouseClicked(mouseX, mouseY, button);

		return super.mouseClicked(mouseX, mouseY, button) || used;
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		boolean used = false;

		if (tabScreen != null)
			used = tabScreen.mouseReleased(mouseX, mouseY, button);

		return super.mouseReleased(mouseX, mouseY, button) || used;
	}

	@Override
	public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
		if (!isBlockingKeys() && keyCode == Keybinds.ADVENT_GUI.getKey().getValue()) {
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
		int tabWidth = (int)((width + 182) * scale);
		int tabHeight =  (int)((height + 118) * scale);

		if (tabScreen != null) {
			tabScreen.init(mc, tabWidth, tabHeight);
		}
		else {
			switch (selectedTab) {
				case PLAYER:
					tabScreen = new AdventGuiTabPlayer();
					tabScreen.resize(mc, tabWidth, tabHeight);
					break;
				case HELP:
					tabScreen = new AdventGuiTabHelp();
					tabScreen.resize(mc, tabWidth, tabHeight);
					break;
				case BESTIARY:
					tabScreen = new AdventGuiTabBestiary();
					tabScreen.resize(mc, tabWidth, tabHeight);
					break;
				case GUIDES:
					tabScreen = new AdventGuiTabGuides();
					tabScreen.resize(mc, tabWidth, tabHeight);
					break;
				default:
					tabScreen = null;
					break;
			}
		}
	}

	private ResourceLocation getTitleBarTexture() {
		return titleResource;
	}

	@Override
	public boolean isPauseScreen() {
		return AoAConfig.CLIENT.adventGuiPausesGame.get();
	}

	private void correctGuiPositions() {
		MainWindow window = getMinecraft().getWindow();

		scaledRootX = (int) (Math.round((window.getGuiScaledWidth() / 2d) / scale) - Math.round(guiWidth / 2d));
		scaledRootY = (int) (Math.round((window.getGuiScaledHeight() / 2d) / scale) - Math.round(guiHeight / 2d));
		scaledTabRootX = scaledRootX + 201;
		scaledTabRootY = scaledRootY + 129;
	}

	@Override
	public void resize(Minecraft mc, int width, int height) {
		super.resize(mc, width, height);

		correctGuiPositions();

		if (tabScreen != null)
			tabScreen.resize(mc, (int)((width + 182) * scale), (int)((height + 118) * scale));
	}

	@Override
	public void removed() {
		super.removed();

		if (tabScreen != null)
			tabScreen.removed();
	}

	public static void addTheme(String name, @Nullable ResourceLocation backgroundTexture, @Nullable ResourceLocation menuButtonsTexture, @Nullable ResourceLocation overlayTexture) {
		textures.add(new AdventGuiTextures(name, backgroundTexture, menuButtonsTexture, overlayTexture));
	}

	protected static void changeTheme() {
		AoAConfig.CLIENT.changeAdventGuiTheme(textures.get(currentTextureIndex).name);
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

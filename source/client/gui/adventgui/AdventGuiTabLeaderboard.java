package net.tslat.aoa3.client.gui.adventgui;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.tslat.aoa3.client.gui.lib.ScrollablePane;
import net.tslat.aoa3.library.object.RenderContext;
import net.tslat.aoa3.player.ClientPlayerDataManager;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RenderUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class AdventGuiTabLeaderboard extends Screen {
	private CategoryMenu categoryMenu;

	@Nullable
	private AoASkill currentSkill = null;
	private volatile PageData currentData = null;

	private int adjustedMouseX;
	private int adjustedMouseY;

	protected AdventGuiTabLeaderboard() {
		super(Component.translatable("gui.aoa3.adventGui.leaderboards"));
	}

	@Override
	public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
		super.render(guiGraphics, mouseX, mouseY, partialTicks);

		this.adjustedMouseX = (int)(mouseX * (1 / AdventMainGui.SCALE));
		this.adjustedMouseY = (int)(mouseY * (1 / AdventMainGui.SCALE));
		this.categoryMenu.render(guiGraphics, this.adjustedMouseX, this.adjustedMouseY, partialTicks);

		RenderContext renderContext = RenderContext.of(guiGraphics);
		final int boardPaneLeft = AdventMainGui.scaledTabRootX + 136;
		final int boardPaneWidth = 629;

		renderContext.renderCenteredScaledText(this.currentSkill == null ? LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.player.totalLevel") : this.currentSkill.getName(), boardPaneLeft + boardPaneWidth / 2f, AdventMainGui.scaledTabRootY + 10, 1.5f, 14737632, RenderUtil.TextRenderType.DROP_SHADOW);
		renderContext.renderCenteredScaledText(LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.leaderboards.rank"), boardPaneLeft + 50, AdventMainGui.scaledTabRootY + 50, 1.5f, 14737632, RenderUtil.TextRenderType.DROP_SHADOW);
		renderContext.renderCenteredScaledText(LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.leaderboards.player"), boardPaneLeft + boardPaneWidth * (1 / 2.5f), AdventMainGui.scaledTabRootY + 50, 1.5f, 14737632, RenderUtil.TextRenderType.DROP_SHADOW);
		renderContext.renderCenteredScaledText(LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.player.skillLevel"), boardPaneLeft + 525, AdventMainGui.scaledTabRootY + 50, 1.5f, 14737632, RenderUtil.TextRenderType.DROP_SHADOW);

		PageData currentData = this.currentData;

		if (currentData == null)
			currentData = new PageData(1, new Component[] {Minecraft.getInstance().player.getDisplayName()}, new int[] {this.currentSkill == null ? ClientPlayerDataManager.get().getTotalLevel() : ClientPlayerDataManager.get().getSkill(this.currentSkill).getLevel(true)});

		int pageNum = currentData.page();

		for (int i = 1; i <= 10; i++) {
			int y = AdventMainGui.scaledTabRootY + 71 + (27 * (i - 1));

			guiGraphics.fill(boardPaneLeft, y, boardPaneLeft + boardPaneWidth, y + 27, i % 2 == 0 ? -1072689136 : -804253680);

			if (i - 1 < currentData.players.length) {
				renderContext.renderCenteredScaledText(Component.literal(String.valueOf((pageNum - 1) * 10 + i)), boardPaneLeft + 50, y + 8, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.DROP_SHADOW);
				renderContext.renderScaledText(currentData.players[i - 1], boardPaneLeft + 150, y + 8, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.DROP_SHADOW);
				renderContext.renderCenteredScaledText(Component.literal(String.valueOf(currentData.levels[i - 1])), boardPaneLeft + 525, y + 8, 1.5f, ColourUtil.WHITE, RenderUtil.TextRenderType.DROP_SHADOW);
			}
		}
	}

	@Override
	public void renderTransparentBackground(GuiGraphics pGuiGraphics) {}

	@Override
	protected void init() {
		if (this.categoryMenu == null)
			this.categoryMenu = new CategoryMenu(getMinecraft(), AdventMainGui.scaledTabRootY, AdventMainGui.scaledTabRootX, 340, 125, AdventMainGui.SCALE);
	}

	@Override
	public void resize(Minecraft minecraft, int width, int height) {
		super.resize(minecraft, width, height);

		if (this.categoryMenu != null)
			this.categoryMenu.onResize(getMinecraft(), AdventMainGui.scaledTabRootX, AdventMainGui.scaledTabRootY, 135, 340);
	}

	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double lateralScroll, double verticalScroll) {
		if (this.categoryMenu != null)
			return this.categoryMenu.handleMouseScroll(-1, -1, verticalScroll);

		return false;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int mouseButton) {
		return this.categoryMenu != null && this.categoryMenu.handleMouseClick(this.adjustedMouseX, this.adjustedMouseY, mouseButton);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (this.categoryMenu != null)
			return this.categoryMenu.handleMouseReleased(mouseX, mouseY, button);

		return false;
	}

	private class CategoryMenu extends ScrollablePane {
		private final List<SkillSelectButton> buttons = new ObjectArrayList<>();

		public CategoryMenu(Minecraft mc, int top, int left, int viewHeight, int viewWidth, float renderingScale) {
			super(mc, top, left, viewHeight, viewWidth, renderingScale);

			buttons.add(new SkillSelectButton(35, 35, null));

			for (AoASkill.Instance skill : ClientPlayerDataManager.get().getSkills()) {
				buttons.add(new SkillSelectButton(35, 35, skill.type()));
			}
		}

		@Override
		public int getFullPaneHeight() {
			return 10 + buttons.size() * 45;
		}

		@Override
		public void drawPaneContents(GuiGraphics guiGraphics, int top, int left, int right, int bottom, float scrollDistance, float partialTicks) {
			int y = 10;

			for (SkillSelectButton button : this.buttons) {
				button.x = left + 10;
				button.y = top + y;
				button.render(guiGraphics, (int)this.mouseX, (int)this.mouseY, partialTicks);

				y += 45;
			}
		}

		@Override
		public void drawBackground(GuiGraphics guiGraphics) {

		}

		@Override
		public boolean handleMouseClick(double mouseX, double mouseY, int button) {
			super.handleMouseClick(mouseX, mouseY, button);

			boolean clicked = false;

			for (SkillSelectButton skillButton : this.buttons) {
				if (skillButton.isMouseOver) {
					AdventGuiTabLeaderboard.this.currentSkill = skillButton.skill;
					clicked = true;
				}
			}

			return clicked;
		}

		private class SkillSelectButton {
			protected final int height;
			protected int width;
			@Nullable
			protected AoASkill skill;
			protected Component text;
			protected boolean isMouseOver = false;
			protected float x;
			protected float y;

			public SkillSelectButton(int minWidth, int height, @Nullable AoASkill skill) {
				this.width = minWidth;
				this.height = height;
				this.skill = skill;
				this.text = this.skill == null ? LocaleUtil.getLocaleMessage("gui.aoa3.adventGui.player.totalLevel") : skill.getName();

				if (skill == null)
					AdventGuiTabLeaderboard.this.currentSkill = this.skill;
			}

			public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
				this.isMouseOver = isMouseOver(mouseX, mouseY);
				this.width = (int)(10 + Minecraft.getInstance().font.width(this.text) * 1.5f);
				RenderContext renderContext = RenderContext.of(guiGraphics);

				renderContext.setTextureForRendering(AdventMainGui.theme.menuButtonTexture());
				renderContext.renderScaledCustomSizedTexture(this.x, this.y, 0, AdventGuiTabLeaderboard.this.currentSkill == this.skill ? 0 : this.isMouseOver ? 60 : 120, 180, 60, this.width, this.height, 180, 180);

				int stringColour = 14737632;

				if (this.isMouseOver && AdventGuiTabLeaderboard.this.currentSkill != this.skill)
					stringColour = 16777120;

				renderContext.renderCenteredScaledText(this.text, (int)(this.x + this.width / 2f), (int)(this.y + this.height / 2.5), 1.5f, stringColour, RenderUtil.TextRenderType.DROP_SHADOW);
			}

			private boolean isMouseOver(int mouseX, int mouseY) {
				return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
			}
		}
	}

	public static record PageData(int page, Component[] players, int[] levels) {}
}

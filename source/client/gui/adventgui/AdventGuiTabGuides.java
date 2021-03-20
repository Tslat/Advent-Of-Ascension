package net.tslat.aoa3.client.gui.adventgui;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.client.gui.lib.ScrollablePane;
import net.tslat.aoa3.data.client.GuidesManager;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RenderUtil;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class AdventGuiTabGuides extends Screen {
	private BundlesMenu scrollMenu;

	private int openBundleIndex = -1;
	private long lastSelectionTime = 0;
	private int openBundleHeight = 0;
	private List<IReorderingProcessor> openBundleLines = null;

	private int adjustedMouseX;
	private int adjustedMouseY;

	protected AdventGuiTabGuides() {
		super(new TranslationTextComponent("gui.aoa3.adventGui.guides"));
	}

	@Override
	protected void init() {
		if (scrollMenu == null)
			scrollMenu = new BundlesMenu(getMinecraft(), AdventMainGui.scaledTabRootY, AdventMainGui.scaledTabRootX, 340, 764, AdventMainGui.scale);
	}

	@Override
	public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
		super.render(matrix, mouseX, mouseY, partialTicks);

		this.adjustedMouseX = (int)(mouseX * AdventMainGui.scaleInverse);
		this.adjustedMouseY = (int)(mouseY * AdventMainGui.scaleInverse);

		if (scrollMenu != null)
			scrollMenu.render(matrix, adjustedMouseX, adjustedMouseY, partialTicks);
	}


	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double scrollAmount) {
		if (scrollMenu != null)
			return scrollMenu.handleMouseScroll(adjustedMouseX, adjustedMouseY, scrollAmount);

		return false;
	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (scrollMenu != null)
			return scrollMenu.handleMouseClick(adjustedMouseX, adjustedMouseY, button);

		return false;
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		if (scrollMenu != null)
			return scrollMenu.handleMouseReleased(adjustedMouseX, adjustedMouseY, button);

		return false;
	}

	@Override
	public void resize(Minecraft mc, int width, int height) {
		super.resize(mc, width, height);

		if (scrollMenu != null)
			scrollMenu.onResize(mc, AdventMainGui.scaledTabRootX, AdventMainGui.scaledTabRootY, 764, 340);
	}

	private class BundlesMenu extends ScrollablePane {
		public BundlesMenu(Minecraft mc, int top, int left, int viewHeight, int viewWidth, float... currentScale) {
			super(mc, top, left, viewHeight, viewWidth, currentScale);
		}

		@Override
		public int getFullPaneHeight() {
			return openBundleIndex < 0 ? GuidesManager.GUIDES.size() * 30 : 30 + openBundleHeight;
		}

		@Override
		public void drawPaneContents(MatrixStack matrix, int top, int left, int right, int bottom, float scrollDistance, float partialTicks) {
			int timeAdjustedTop = 0;
			float selectedPercentSwitched = 0;

			if (openBundleIndex >= 0) {
				selectedPercentSwitched = Math.min(1, (System.currentTimeMillis() - lastSelectionTime) / 400f);
				timeAdjustedTop = top + (int)((openBundleIndex * 20) * (1 - selectedPercentSwitched));
			}

			if (timeAdjustedTop != top) {
				for (int i = Math.max(0, (int)(scrollDistance / 30f)); i * 30 <= bottom - top && i < GuidesManager.GUIDES.size(); i++) {
					GuidesManager.Guide guide = GuidesManager.GUIDES.get(i);
					int rowTop = top + i * 30;
					int rowBottom = rowTop + 30;

					RenderUtil.drawColouredBox(matrix, left, rowTop, 0, right - left, rowBottom - rowTop, i % 2 == 0 ? 0xFF010101 : 0xFF202020);
					RenderUtil.drawCenteredScaledString(matrix, font, guide.title, left + (int)(viewWidth / 2f), rowTop + 8, 2f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.NORMAL);
					RenderUtil.drawScaledString(matrix, font, "V", left + 5, rowTop + 18, 1.5f, NumberUtil.RGB(181, 181, 181), RenderUtil.StringRenderType.OUTLINED);
					RenderUtil.drawScaledString(matrix, font, "V", right - 20, rowTop + 18, 1.5f, NumberUtil.RGB(181, 181, 181), RenderUtil.StringRenderType.OUTLINED);
				}
			}

			if (openBundleIndex >= 0) {
				GuidesManager.Guide guide = GuidesManager.GUIDES.get(openBundleIndex);

				if (openBundleLines == null) {
					openBundleLines = font.split(new StringTextComponent(guide.content), (int)((viewWidth - 30) / 1.5f));
					openBundleHeight = Math.max(viewHeight - 30, 25 + (int)(openBundleLines.size() * (font.lineHeight * 1.5f)));
				}

				int timeAdjustedBottom = (int)(timeAdjustedTop + 30 + openBundleHeight * selectedPercentSwitched);

				RenderUtil.drawColouredBox(matrix, left, timeAdjustedTop, 0, right - left, timeAdjustedBottom - timeAdjustedTop + 30, openBundleIndex % 2 == 0 ? 0xFF010101 : 0xFF202020);
				RenderUtil.drawCenteredScaledString(matrix, font, guide.title, left + (int)(viewWidth / 2f), timeAdjustedTop + 8, 2f, NumberUtil.RGB(255, 255, 255), RenderUtil.StringRenderType.NORMAL);
				RenderUtil.drawScaledString(matrix, font, "^", left + 5,  timeAdjustedTop + 18, 1.5f, NumberUtil.RGB(181, 181, 181), RenderUtil.StringRenderType.OUTLINED);
				RenderUtil.drawScaledString(matrix, font, "^", right - 20, timeAdjustedTop + 18, 1.5f, NumberUtil.RGB(181, 181, 181), RenderUtil.StringRenderType.OUTLINED);
				RenderUtil.drawColouredBox(matrix, left, timeAdjustedTop + 30, 0, right - left, timeAdjustedBottom - timeAdjustedTop, 0xFF505050);

				if (selectedPercentSwitched == 1) {
					int lineOffset = 0;

					matrix.pushPose();
					matrix.scale(1.5f, 1.5f, 1.5f);

					for (IReorderingProcessor line : openBundleLines) {
						font.draw(matrix, line, (int)((left + 20) / 1.5f), (int)((top + 30 + lineOffset) / 1.5f), NumberUtil.RGB(255, 255, 255));
						lineOffset += 14;
					}

					matrix.popPose();
				}
			}
		}

		@Override
		public void drawBackground(MatrixStack matrix) {}

		@Override
		public boolean handleMouseClick(double mouseX, double mouseY, int button) {
			super.handleMouseClick(mouseX, mouseY, button);

			int relativeMouseX = (int)mouseX - left + 2;

			if (relativeMouseX < 0 || relativeMouseX > viewWidth - 6)
				return false;

			int newTop = top - Math.max(0, (int)distanceScrolled);
			int relativeMouseY = (int)mouseY - newTop + 2;

			if (mouseY - top < 0 || mouseY > top + viewHeight)
				return false;

			if (openBundleIndex < 0) {
				int selectedIndex = relativeMouseY / 30;

				if (GuidesManager.GUIDES.size() > selectedIndex) {
					openBundleIndex = selectedIndex;
					lastSelectionTime = System.currentTimeMillis();
					openBundleHeight = 600;
					distanceScrolled = 0;
				}
			}
			else  {
				if (relativeMouseY <= 30) {
					openBundleIndex = -1;
					openBundleHeight = 0;
					openBundleLines = null;
				}
			}

			return true;
		}
	}
}

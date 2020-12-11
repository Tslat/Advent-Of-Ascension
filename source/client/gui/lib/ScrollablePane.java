package net.tslat.aoa3.client.gui.lib;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.gui.GuiUtils;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public abstract class ScrollablePane {
	private Minecraft mc;
	protected int top;
	protected int bottom;
	protected int left;
	protected int right;
	protected int viewWidth;
	protected int viewHeight;
	protected int scrollBarHeight;
	private float currentRenderScale;

	protected int mouseX;
	protected int mouseY;
	private boolean mouseFocussed;
	protected boolean isDragging = false;

	private float mouseYPosState = -2f;
	protected float distanceScrolled;
	private float scrollFactor;

	public ScrollablePane(Minecraft mc, int top, int left, int viewHeight, int viewWidth, float... renderingScale) {
		this.mc = mc;
		this.top = top;
		this.left = left;
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
		this.bottom = top + viewHeight;
		this.right = left + viewWidth;

		if (renderingScale.length > 0)
			currentRenderScale = renderingScale[0];
	}

	public void render(int mouseX, int mouseY, float partialTicks) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		int scrollBarWidth = 6;
		mouseFocussed = isMouseHovering();
		int paneHeight = getFullPaneHeight();
		int paneViewDiff = paneHeight - viewHeight;
		Tessellator tess = Tessellator.getInstance();
		BufferBuilder buff = tess.getBuffer();

		if (paneViewDiff > 0)
			scrollBarHeight = (int)MathHelper.clamp(((float)(viewHeight * viewHeight) / (float)paneHeight), 32, viewHeight);

		if (isDragging) {
			if (mouseYPosState == -1) {
				if (mouseFocussed) {
					if (mouseX >= right - scrollBarWidth && mouseX <= right) {
						scrollFactor = -1;
						int scrollHeight = Math.max(paneHeight - viewHeight, 1);
						scrollFactor /= (float)(viewHeight - scrollBarHeight) / (float)scrollHeight;
					}
					else {
						scrollFactor = 1;
					}

					mouseYPosState = mouseY;
				}
				else {
					mouseYPosState = -2;
				}
			}
			else if (mouseYPosState >= 0) {
				distanceScrolled = distanceScrolled - ((float)mouseY - mouseYPosState) * scrollFactor;
				mouseYPosState = mouseY;
			}
		}
		else {
			mouseYPosState = -1;
		}

		distanceScrolled = MathHelper.clamp(distanceScrolled, 0, paneHeight - viewHeight);
		GL11.glEnable(GL11.GL_SCISSOR_TEST);

		MainWindow mcWindow = mc.getMainWindow();
		float windowWidthScale = currentRenderScale * (mcWindow.getWidth() / (float)mcWindow.getScaledWidth());
		float windowHeightScale = currentRenderScale * (mcWindow.getHeight() / (float)mcWindow.getScaledHeight());

		GL11.glScissor((int)((left - 1.5) * windowWidthScale), (int)((mcWindow.getHeight() / windowHeightScale - bottom) * windowHeightScale), (int)((viewWidth + 3) * windowWidthScale), (int)((viewHeight + 1.5) * windowHeightScale));
		drawBackground();
		GuiUtils.drawGradientRect(0, left - 1, top - 1, right + 1, bottom + 1, 0xC0101010, 0xD0101010);
		int newTop = top - Math.max(0, (int)distanceScrolled);
		drawPaneContents(newTop, left, right, bottom, distanceScrolled, partialTicks);
		RenderSystem.disableDepthTest();

		if (paneViewDiff > 0) {
			int barTop = Math.max((int)distanceScrolled * (viewHeight - scrollBarHeight) / paneViewDiff + top, top);
			int barLeft = right - 6;

			RenderSystem.disableTexture();
			buff.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
			buff.pos(barLeft, bottom, 0).tex(0, 1).color(0, 0, 0, 255).endVertex();
			buff.pos(right + 1, bottom, 0).tex(1, 1).color(0, 0, 0, 255).endVertex();
			buff.pos(right + 1, top, 0).tex(1, 0).color(0, 0, 0, 255).endVertex();
			buff.pos(barLeft, top, 0).tex(0, 0).color(0, 0, 0, 255).endVertex();
			tess.draw();
			buff.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
			buff.pos(barLeft, barTop + scrollBarHeight, 0).tex(0, 1).color(128, 128, 128, 255).endVertex();
			buff.pos(right + 1, barTop + scrollBarHeight, 0).tex(1, 1).color(128, 128, 128, 255).endVertex();
			buff.pos(right + 1, barTop, 0).tex(1, 0).color(128, 128, 128, 255).endVertex();
			buff.pos(barLeft, barTop, 0).tex(0, 0).color(128, 128, 128, 255).endVertex();
			tess.draw();
			buff.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
			buff.pos(barLeft, barTop + scrollBarHeight - 1, 0).tex(0, 1).color(192, 192, 192, 255).endVertex();
			buff.pos(right, barTop + scrollBarHeight - 1, 0).tex(1, 1).color(192, 192, 192, 255).endVertex();
			buff.pos(right, barTop, 0).tex(1, 0).color(192, 192, 192, 255).endVertex();
			buff.pos(barLeft, barTop, 0).tex(0, 0).color(192, 192, 192, 255).endVertex();
			tess.draw();
		}

		RenderSystem.enableTexture();
		RenderSystem.shadeModel(GL11.GL_FLAT);
		RenderSystem.enableAlphaTest();
		RenderSystem.disableBlend();
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
	}

	public void onResize(Minecraft mc, int left, int top, int viewWidth, int viewHeight) {
		this.mc = mc;
		this.top = top;
		this.left = left;
		this.viewHeight = viewHeight;
		this.viewWidth = viewWidth;
		this.bottom = top + viewHeight;
		this.right = left + viewWidth;
	}

	public boolean handleMouseClick(double mouseX, double mouseY, int button) {
		isDragging = true;

		return true;
	}

	public boolean handleMouseReleased(double mouseX, double mouseY, int button) {
		isDragging = false;

		return true;
	}

	public boolean handleMouseScroll(double mouseX, double mouseY, double scrollAmount) {
		if (!mouseFocussed)
			return false;

		if (scrollAmount != 0)
			distanceScrolled += -20 * scrollAmount;

		return true;
	}

	private boolean isMouseHovering() {
		return mouseX >= left && mouseX <= right && mouseY >= top && mouseY <= bottom;
	}

	public abstract int getFullPaneHeight();

	public abstract void drawPaneContents(int top, int left, int right, int bottom, float scrollDistance, float partialTicks);

	public abstract void drawBackground();
}

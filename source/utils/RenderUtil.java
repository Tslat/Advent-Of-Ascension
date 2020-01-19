package net.tslat.aoa3.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderUtil {
	public static void drawScaledCustomSizeModalRect(double x, double y, float u, float v, float uWidth, float vHeight, double width, double height, float tileWidth, float tileHeight) {
		float f = 1.0F / tileWidth;
		float f1 = 1.0F / tileHeight;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();

		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(x, y + height, 0.0D).tex((double)(u * f), (double)((v + vHeight) * f1)).endVertex();
		bufferbuilder.pos(x + width, y + height, 0.0D).tex((double)((u + uWidth) * f), (double)((v + vHeight) * f1)).endVertex();
		bufferbuilder.pos(x + width, y, 0.0D).tex((double)((u + uWidth) * f), (double)(v * f1)).endVertex();
		bufferbuilder.pos(x, y, 0.0D).tex((double)(u * f), (double)(v * f1)).endVertex();
		tessellator.draw();
	}

	public static void drawCenteredScaledString(FontRenderer fontRenderer, String msg, int x, int y, float scale, int colour, StringRenderType renderType) {
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
		GlStateManager.popMatrix();
	}

	public static void drawScaledString(FontRenderer fontRenderer, String msg, int x, int y, float scale, int colour, RenderUtil.StringRenderType renderType) {
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

	public static void drawOutlinedText(FontRenderer fontRenderer, String msg, int x, int y, int colour, float currentScale) {
		if (!Minecraft.getMinecraft().isUnicode())
			currentScale = 1;

		fontRenderer.drawString(msg, x, y + (1 / currentScale), 0, false);
		fontRenderer.drawString(msg, x, y - (1 / currentScale), 0, false);
		fontRenderer.drawString(msg, x + (1 / currentScale), y, 0, false);
		fontRenderer.drawString(msg, x - (1 / currentScale), y, 0, false);
		fontRenderer.drawString(msg, x, y, colour, false);
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
	}

	public enum StringRenderType {
		NORMAL,
		DROP_SHADOW,
		OUTLINED
	}
}

package net.tslat.aoa3.util;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;

public abstract class RenderUtil {
	public static void renderTexture(int x, int y, float u, float v, float width, float height) {
		renderCustomSizedTexture(x, y, u, v, (int)width, (int)height, width, height);
	}

	public static void renderCustomSizedTexture(int x, int y, float u, float v, int uWidth, int vHeight, float textureWidth, float textureHeight) {
		renderScaledCustomSizedTexture(x, y, u, v, uWidth, vHeight, uWidth, vHeight, textureWidth, textureHeight);
	}

	public static void renderScaledCustomSizedTexture(double x, double y, float u, float v, float uWidth, float vHeight, double renderWidth, double renderHeight, float textureWidth, float textureHeight) {
		BufferBuilder buffer = Tessellator.getInstance().getBuffer();
		float widthRatio = 1.0F / textureWidth;
		float heightRatio = 1.0F / textureHeight;

		buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(x, y + renderHeight, 0.0D).tex(u * widthRatio, (v + vHeight) * heightRatio).endVertex();
		buffer.pos(x + renderWidth, y + renderHeight, 0.0D).tex((u + uWidth) * widthRatio, (v + vHeight) * heightRatio).endVertex();
		buffer.pos(x + renderWidth, y, 0.0D).tex((u + uWidth) * widthRatio, v * heightRatio).endVertex();
		buffer.pos(x, y, 0.0D).tex(u * widthRatio, v * heightRatio).endVertex();
		buffer.finishDrawing();
		WorldVertexBufferUploader.draw(buffer);
	}

	public static void drawVerticalGradient(int x, int y, int z, int width, int height, int topColour, int bottomColour) {
		float alphaTop = (float)(topColour >> 24 & 255) / 255.0F;
		float redTop = (float)(topColour >> 16 & 255) / 255.0F;
		float greenTop = (float)(topColour >> 8 & 255) / 255.0F;
		float blueTop = (float)(topColour & 255) / 255.0F;
		float alphaBottom = (float)(bottomColour >> 24 & 255) / 255.0F;
		float redBottom = (float)(bottomColour >> 16 & 255) / 255.0F;
		float greenBottom = (float)(bottomColour >> 8 & 255) / 255.0F;
		float blueBottom = (float)(bottomColour & 255) / 255.0F;

		RenderSystem.pushTextureAttributes();
		RenderSystem.disableTexture();
		RenderSystem.enableBlend();
		RenderSystem.disableAlphaTest();
		RenderSystem.defaultBlendFunc();

		RenderSystem.shadeModel(7425);
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();

		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		bufferbuilder.pos(x + width, y, z).color(redTop, greenTop, blueTop, alphaTop).endVertex();
		bufferbuilder.pos(x, y, z).color(redTop, greenTop, blueTop, alphaTop).endVertex();
		bufferbuilder.pos(x, y + height, z).color(redBottom, greenBottom, blueBottom, alphaBottom).endVertex();
		bufferbuilder.pos(x + width, y + height, z).color(redBottom, greenBottom, blueBottom, alphaBottom).endVertex();
		tessellator.draw();
		RenderSystem.shadeModel(7424);
		RenderSystem.disableBlend();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableTexture();
		RenderSystem.popAttributes();
	}

	public static void drawColouredBox(int x, int y, int z, int width, int height, int colour) {
		float alpha = (float)(colour >> 24 & 255) / 255.0F;
		float red = (float)(colour >> 16 & 255) / 255.0F;
		float green = (float)(colour >> 8 & 255) / 255.0F;
		float blue = (float)(colour & 255) / 255.0F;

		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
		Matrix4f matrix = TransformationMatrix.identity().getMatrix();

		RenderSystem.pushTextureAttributes();
		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		bufferbuilder.pos(matrix, (float)x, (float)y + height, z).color(red, green, blue, alpha).endVertex();
		bufferbuilder.pos(matrix, (float)x + width, (float)y + height, z).color(red, green, blue, alpha).endVertex();
		bufferbuilder.pos(matrix, (float)x + width, (float)y, z).color(red, green, blue, alpha).endVertex();
		bufferbuilder.pos(matrix, (float)x, (float)y, z).color(red, green, blue, alpha).endVertex();
		bufferbuilder.finishDrawing();
		WorldVertexBufferUploader.draw(bufferbuilder);
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
		RenderSystem.popAttributes();
	}

	public static void drawCenteredScaledString(FontRenderer fontRenderer, String msg, int x, int y, float scale, int colour, StringRenderType renderType) {
		RenderSystem.pushMatrix();
		RenderSystem.scalef(scale, scale, scale);

		float realX = (x - fontRenderer.getStringWidth(msg) * scale / 2f) / scale;
		float realY = y / scale;

		if (renderType == StringRenderType.OUTLINED) {
			fontRenderer.drawString(msg, realX, realY + (1 / scale), 0);
			fontRenderer.drawString(msg, realX, realY - (1 / scale), 0);
			fontRenderer.drawString(msg, realX + (1 / scale), realY, 0);
			fontRenderer.drawString(msg, realX - (1 / scale), realY, 0);
		}

		if (renderType == StringRenderType.DROP_SHADOW) {
			fontRenderer.drawStringWithShadow(msg, realX, realY, colour);
		}
		else {
			fontRenderer.drawString(msg, realX, realY, colour);
		}

		RenderSystem.enableAlphaTest();
		RenderSystem.popMatrix();
	}

	public static void drawScaledString(FontRenderer fontRenderer, String msg, int x, int y, float scale, int colour, RenderUtil.StringRenderType renderType) {
		float realX = x / scale;
		float realY = y / scale;

		RenderSystem.pushMatrix();
		RenderSystem.scalef(scale, scale, scale);

		if (renderType == StringRenderType.OUTLINED) {
			fontRenderer.drawString(msg, realX, realY + (1 / scale), 0);
			fontRenderer.drawString(msg, realX, realY - (1 / scale), 0);
			fontRenderer.drawString(msg, realX + (1 / scale), realY, 0);
			fontRenderer.drawString(msg, realX - (1 / scale), realY, 0);
		}

		if (renderType == StringRenderType.DROP_SHADOW) {
			fontRenderer.drawStringWithShadow(msg, realX, realY, colour);
		}
		else {
			fontRenderer.drawString(msg, realX, realY, colour);
		}

		RenderSystem.enableAlphaTest();
		RenderSystem.popMatrix();
	}

	public static void drawOutlinedText(FontRenderer fontRenderer, String msg, int x, int y, int colour, float currentScale) {
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		fontRenderer.drawString(msg, x, y + (1 / currentScale), 0);
		fontRenderer.drawString(msg, x, y - (1 / currentScale), 0);
		fontRenderer.drawString(msg, x + (1 / currentScale), y, 0);
		fontRenderer.drawString(msg, x - (1 / currentScale), y, 0);
		fontRenderer.drawString(msg, x, y, colour);
		RenderSystem.enableAlphaTest();
	}

	/*public static void blit(int posX, int posY, int uSize, int vSize, float u, float v, int uSize2, int vSize2, int textureWidth, int textureHeight) {
		innerBlit(posX, posX + uSize, posY, posY + vSize, 0, uSize2, vSize2, u, v, textureWidth, textureHeight);
	}

	public static void blit(int posX, int posY, float u, float v, int uSize, int vSize, int textureWidth, int textureHeight) {
		blit(posX, posY, uSize, vSize, u, v, uSize, vSize, textureWidth, textureHeight);
	}

	private static void innerBlit(int posX, int posRight, int posY, int posBottom, int posZ, int renderWidth, int renderHeight, float u, float v, int textureWidth, int textureHeight) {
		innerBlit(posX, posRight, posY, posBottom, posZ, u / (float)textureWidth, (u + (float)renderWidth) / (float)textureWidth, v / (float)textureHeight, (v + (float)renderHeight) / (float)textureHeight);
	}

	protected static void innerBlit(int posX, int posRight, int posY, int posBottom, int posZ, float uRatio, float uRatioRight, float vRatio, float vRatioBottom) {
		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();

		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(posX, posBottom, posZ).tex(uRatio, vRatioBottom).endVertex();
		bufferbuilder.pos(posRight, posBottom, posZ).tex(uRatioRight, vRatioBottom).endVertex();
		bufferbuilder.pos(posRight, posY, posZ).tex(uRatioRight, vRatio).endVertex();
		bufferbuilder.pos(posX, posY, posZ).tex(uRatio, vRatio).endVertex();
		bufferbuilder.finishDrawing();
		RenderSystem.enableAlphaTest();
		WorldVertexBufferUploader.draw(bufferbuilder);
	}*/

	public enum StringRenderType {
		NORMAL,
		DROP_SHADOW,
		OUTLINED
	}
}

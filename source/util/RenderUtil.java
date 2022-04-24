package net.tslat.aoa3.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.IReorderingProcessor;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.ITextProperties;
import net.minecraft.util.text.StringTextComponent;
import net.tslat.aoa3.config.AoAConfig;

public final class RenderUtil {
	public static void renderTexture(MatrixStack matrix, int x, int y, float u, float v, float width, float height) {
		renderCustomSizedTexture(matrix, x, y, u, v, (int)width, (int)height, width, height);
	}

	public static void renderCustomSizedTexture(MatrixStack matrix, int x, int y, float u, float v, float uWidth, float vHeight, float textureWidth, float textureHeight) {
		renderScaledCustomSizedTexture(matrix, x, y, u, v, uWidth, vHeight, uWidth, vHeight, textureWidth, textureHeight);
	}

	public static void renderScaledCustomSizedTexture(MatrixStack matrixStack, float x, float y, float u, float v, float uWidth, float vHeight, float renderWidth, float renderHeight, float textureWidth, float textureHeight) {
		BufferBuilder buffer = Tessellator.getInstance().getBuilder();
		Matrix4f matrix = matrixStack.last().pose();
		float widthRatio = 1.0F / textureWidth;
		float heightRatio = 1.0F / textureHeight;

		buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		buffer.vertex(matrix, x, y + renderHeight, 0f).uv(u * widthRatio, (v + vHeight) * heightRatio).endVertex();
		buffer.vertex(matrix, x + renderWidth, y + renderHeight, 0f).uv((u + uWidth) * widthRatio, (v + vHeight) * heightRatio).endVertex();
		buffer.vertex(matrix, x + renderWidth, y, 0f).uv((u + uWidth) * widthRatio, v * heightRatio).endVertex();
		buffer.vertex(matrix, x, y, 0f).uv(u * widthRatio, v * heightRatio).endVertex();
		buffer.end();
		WorldVertexBufferUploader.end(buffer);
	}

	public static void renderFullscreenTexture() {
		Minecraft mc = Minecraft.getInstance();
		MainWindow window = mc.getWindow();

		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.defaultBlendFunc();
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.disableAlphaTest();

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuilder();

		buffer.begin(7, DefaultVertexFormats.POSITION_TEX);
		buffer.vertex(0, window.getGuiScaledHeight(), -90).uv(0, 1).endVertex();
		buffer.vertex(window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90).uv(1, 1).endVertex();
		buffer.vertex(window.getGuiScaledWidth(), 0, -90).uv(1, 0).endVertex();
		buffer.vertex(0, 0, -90).uv(0, 0).endVertex();
		tessellator.end();
		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		RenderSystem.enableAlphaTest();
		RenderSystem.color4f(1, 1, 1, 1);
	}

	public static void drawVerticalGradient(MatrixStack matrixStack, int x, int y, int z, int width, int height, int topColour, int bottomColour) {
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
		BufferBuilder bufferbuilder = tessellator.getBuilder();
		Matrix4f matrix = matrixStack.last().pose();

		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		bufferbuilder.vertex(matrix, x + width, y, z).color(redTop, greenTop, blueTop, alphaTop).endVertex();
		bufferbuilder.vertex(matrix, x, y, z).color(redTop, greenTop, blueTop, alphaTop).endVertex();
		bufferbuilder.vertex(matrix, x, y + height, z).color(redBottom, greenBottom, blueBottom, alphaBottom).endVertex();
		bufferbuilder.vertex(matrix, x + width, y + height, z).color(redBottom, greenBottom, blueBottom, alphaBottom).endVertex();
		tessellator.end();
		RenderSystem.shadeModel(7424);
		RenderSystem.disableBlend();
		RenderSystem.enableAlphaTest();
		RenderSystem.enableTexture();
		RenderSystem.popAttributes();
	}

	public static void drawColouredBox(MatrixStack matrixStack, int x, int y, int z, int width, int height, int colour) {
		float alpha = (float)(colour >> 24 & 255) / 255.0F;
		float red = (float)(colour >> 16 & 255) / 255.0F;
		float green = (float)(colour >> 8 & 255) / 255.0F;
		float blue = (float)(colour & 255) / 255.0F;

		BufferBuilder bufferbuilder = Tessellator.getInstance().getBuilder();
		Matrix4f matrix = matrixStack.last().pose();

		RenderSystem.pushTextureAttributes();
		RenderSystem.enableBlend();
		RenderSystem.disableTexture();
		RenderSystem.defaultBlendFunc();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_COLOR);
		bufferbuilder.vertex(matrix, (float)x, (float)y + height, z).color(red, green, blue, alpha).endVertex();
		bufferbuilder.vertex(matrix, (float)x + width, (float)y + height, z).color(red, green, blue, alpha).endVertex();
		bufferbuilder.vertex(matrix, (float)x + width, (float)y, z).color(red, green, blue, alpha).endVertex();
		bufferbuilder.vertex(matrix, (float)x, (float)y, z).color(red, green, blue, alpha).endVertex();
		bufferbuilder.end();
		WorldVertexBufferUploader.end(bufferbuilder);
		RenderSystem.enableTexture();
		RenderSystem.disableBlend();
		RenderSystem.popAttributes();
	}

	public static void drawCenteredScaledMessage(MatrixStack matrix, FontRenderer fontRenderer, ITextComponent msg, float x, float y, float scale, int colour, StringRenderType renderType) {
		matrix.pushPose();
		matrix.scale(scale, scale, 1);

		float realX = (x - fontRenderer.width(msg) * scale / 2f) / scale;
		float realY = y / scale;

		if (renderType == StringRenderType.OUTLINED) {
			fontRenderer.draw(matrix, msg, realX, realY + (1 / scale), 0);
			fontRenderer.draw(matrix, msg, realX, realY - (1 / scale), 0);
			fontRenderer.draw(matrix, msg, realX + (1 / scale), realY, 0);
			fontRenderer.draw(matrix, msg, realX - (1 / scale), realY, 0);
		}

		if (renderType == StringRenderType.DROP_SHADOW) {
			fontRenderer.drawShadow(matrix, msg, realX, realY, colour);
		}
		else {
			fontRenderer.draw(matrix, msg, realX, realY, colour);
		}

		RenderSystem.enableAlphaTest();
		matrix.popPose();
	}

	public static void drawCenteredScaledString(MatrixStack matrix, FontRenderer fontRenderer, String msg, float x, float y, float scale, int colour, StringRenderType renderType) {
		drawCenteredScaledMessage(matrix, fontRenderer, new StringTextComponent(msg), x, y, scale, colour, renderType);
	}

	public static void drawScaledMessage(MatrixStack matrix, FontRenderer fontRenderer, ITextComponent msg, float x, float y, float scale, int colour, RenderUtil.StringRenderType renderType) {
		float realX = x / scale;
		float realY = y / scale;

		matrix.pushPose();
		matrix.scale(scale, scale, scale);

		if (renderType == StringRenderType.OUTLINED) {
			fontRenderer.draw(matrix, msg, realX, realY + (1 / scale), 0);
			fontRenderer.draw(matrix, msg, realX, realY - (1 / scale), 0);
			fontRenderer.draw(matrix, msg, realX + (1 / scale), realY, 0);
			fontRenderer.draw(matrix, msg, realX - (1 / scale), realY, 0);
		}

		if (renderType == StringRenderType.DROP_SHADOW) {
			fontRenderer.drawShadow(matrix, msg, realX, realY, colour);
		}
		else {
			fontRenderer.draw(matrix, msg, realX, realY, colour);
		}

		RenderSystem.enableAlphaTest();
		matrix.popPose();
	}

	public static void drawScaledString(MatrixStack matrix, FontRenderer fontRenderer, String msg, int x, int y, float scale, int colour, RenderUtil.StringRenderType renderType) {
		drawScaledMessage(matrix, fontRenderer, new StringTextComponent(msg), x, y, scale, colour, renderType);
	}

	public static void drawOutlinedText(MatrixStack matrix, FontRenderer fontRenderer, String msg, int x, int y, int colour, float currentScale) {
		RenderSystem.color4f(1.0f, 1.0f, 1.0f, 1.0f);
		fontRenderer.draw(matrix, msg, x, y + (1 / currentScale), 0);
		fontRenderer.draw(matrix, msg, x, y - (1 / currentScale), 0);
		fontRenderer.draw(matrix, msg, x + (1 / currentScale), y, 0);
		fontRenderer.draw(matrix, msg, x - (1 / currentScale), y, 0);
		fontRenderer.draw(matrix, msg, x, y, colour);
		RenderSystem.enableAlphaTest();
	}

	public static void drawWrappedMessage(MatrixStack matrix, FontRenderer fontRenderer, ITextProperties message, float x, float y, int maxLength, int color) {
		Matrix4f matrix4f = matrix.last().pose();

		for (IReorderingProcessor processor : fontRenderer.split(message, maxLength)) {
			IRenderTypeBuffer.Impl renderTypeBuffer = IRenderTypeBuffer.immediate(Tessellator.getInstance().getBuilder());

			fontRenderer.drawInBatch(processor, x, y, color, false, matrix4f, renderTypeBuffer, false, 0, 15728880);
			renderTypeBuffer.endBatch();

			y += fontRenderer.lineHeight;
		}
	}

	public static void renderItemInGui(MatrixStack matrix, Minecraft mc, ItemStack stack, int x, int y) {
		IBakedModel model = mc.getItemRenderer().getModel(stack, null, null);

		matrix.pushPose();
		mc.getTextureManager().bind(AtlasTexture.LOCATION_BLOCKS);
		mc.getTextureManager().getTexture(AtlasTexture.LOCATION_BLOCKS).setFilter(false, false);
		RenderSystem.enableRescaleNormal();
		RenderSystem.enableAlphaTest();
		RenderSystem.defaultAlphaFunc();
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		RenderSystem.color4f(1, 1, 1, 1);
		matrix.translate((float)x, (float)y, 100.0F + mc.getItemRenderer().blitOffset);
		matrix.translate(8, 8, 0);
		matrix.scale(1, -1, 1);
		matrix.scale(16, 16, 16);

		IRenderTypeBuffer.Impl renderTypeBuffer = Minecraft.getInstance().renderBuffers().bufferSource();
		boolean useItemLight = !model.usesBlockLight();

		if (useItemLight)
			RenderHelper.setupForFlatItems();

		mc.getItemRenderer().render(stack, ItemCameraTransforms.TransformType.GUI, false, matrix, renderTypeBuffer, 15728880, OverlayTexture.NO_OVERLAY, model);
		renderTypeBuffer.endBatch();
		RenderSystem.enableDepthTest();

		if (useItemLight)
			RenderHelper.setupFor3DItems();

		RenderSystem.disableAlphaTest();
		RenderSystem.disableRescaleNormal();
		matrix.popPose();
	}

	public static int getPotionGuiRenderOffset() {
		Minecraft mc = Minecraft.getInstance();

		if (mc.player == null || mc.player.getActiveEffects().isEmpty() || AoAConfig.CLIENT.disableHudPotionOffset.get())
			return 0;

		int effectRenderYOffset = 0;

		for (EffectInstance effect : mc.player.getActiveEffects()) {
			if (effect.getDuration() > 0 && effect.getEffect().shouldRenderHUD(effect) && effect.isVisible()) {
				if (!effect.getEffect().isBeneficial()) {
					effectRenderYOffset = 50;
					break;
				}
				else {
					effectRenderYOffset = 25;
				}
			}
		}

		return effectRenderYOffset;
	}

	public static void renderBlockOutline(IVertexBuilder buffer, float red, float green, float blue, float alpha) {
		buffer.vertex(0, 1, 0).color(red, green, blue, alpha).endVertex();
		buffer.vertex(1, 1, 0).color(red, green, blue, alpha).endVertex();
		buffer.vertex(1, 1, 0).color(red, green, blue, alpha).endVertex();
		buffer.vertex(1, 1, 1).color(red, green, blue, alpha).endVertex();
		buffer.vertex(1, 1, 1).color(red, green, blue, alpha).endVertex();
		buffer.vertex(0, 1, 1).color(red, green, blue, alpha).endVertex();
		buffer.vertex(0, 1, 1).color(red, green, blue, alpha).endVertex();
		buffer.vertex(0, 1, 0).color(red, green, blue, alpha).endVertex();

		buffer.vertex(1, 0, 0).color(red, green, blue, alpha).endVertex();
		buffer.vertex(1, 0, 1).color(red, green, blue, alpha).endVertex();
		buffer.vertex(1, 0, 1).color(red, green, blue, alpha).endVertex();
		buffer.vertex(0, 0, 1).color(red, green, blue, alpha).endVertex();
		buffer.vertex(0, 0, 1).color(red, green, blue, alpha).endVertex();
		buffer.vertex(0, 0, 0).color(red, green, blue, alpha).endVertex();
		buffer.vertex(0, 0, 0).color(red, green, blue, alpha).endVertex();
		buffer.vertex(1, 0, 0).color(red, green, blue, alpha).endVertex();

		buffer.vertex(1, 0, 1).color(red, green, blue, alpha).endVertex();
		buffer.vertex(1, 1, 1).color(red, green, blue, alpha).endVertex();

		buffer.vertex(1, 0, 0).color(red, green, blue, alpha).endVertex();
		buffer.vertex(1, 1, 0).color(red, green, blue, alpha).endVertex();

		buffer.vertex(0, 0, 1).color(red, green, blue, alpha).endVertex();
		buffer.vertex(0, 1, 1).color(red, green, blue, alpha).endVertex();

		buffer.vertex(0, 0, 0).color(red, green, blue, alpha).endVertex();
		buffer.vertex(0, 1, 0).color(red, green, blue, alpha).endVertex();
	}

	public enum StringRenderType {
		NORMAL,
		DROP_SHADOW,
		OUTLINED
	}
}

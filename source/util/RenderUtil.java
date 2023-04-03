package net.tslat.aoa3.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientMobEffectExtensions;
import net.tslat.aoa3.common.registration.AoAConfigs;
import org.joml.Matrix4f;

public final class RenderUtil {
	public static void renderTexture(PoseStack matrix, int x, int y, float u, float v, float width, float height) {
		renderCustomSizedTexture(matrix, x, y, u, v, (int)width, (int)height, width, height);
	}

	public static void renderCustomSizedTexture(PoseStack matrix, int x, int y, float u, float v, float uWidth, float vHeight, float textureWidth, float textureHeight) {
		renderScaledCustomSizedTexture(matrix, x, y, u, v, uWidth, vHeight, uWidth, vHeight, textureWidth, textureHeight);
	}

	public static void renderScaledCustomSizedTexture(PoseStack matrixStack, float x, float y, float u, float v, float uWidth, float vHeight, float renderWidth, float renderHeight, float textureWidth, float textureHeight) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		BufferBuilder buffer = Tesselator.getInstance().getBuilder();
		Matrix4f matrix = matrixStack.last().pose();
		float widthRatio = 1.0F / textureWidth;
		float heightRatio = 1.0F / textureHeight;

		buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		buffer.vertex(matrix, x, y + renderHeight, 0f).uv(u * widthRatio, (v + vHeight) * heightRatio).endVertex();
		buffer.vertex(matrix, x + renderWidth, y + renderHeight, 0f).uv((u + uWidth) * widthRatio, (v + vHeight) * heightRatio).endVertex();
		buffer.vertex(matrix, x + renderWidth, y, 0f).uv((u + uWidth) * widthRatio, v * heightRatio).endVertex();
		buffer.vertex(matrix, x, y, 0f).uv(u * widthRatio, v * heightRatio).endVertex();
		BufferUploader.drawWithShader(buffer.end());
	}

	public static void renderFullscreenTexture() {
		Minecraft mc = Minecraft.getInstance();
		Window window = mc.getWindow();

		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderColor(1, 1, 1, 1);
		//RenderSystem.disableAlphaTest();

		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder buffer = tesselator.getBuilder();

		buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		buffer.vertex(0, window.getGuiScaledHeight(), -90).uv(0, 1).endVertex();
		buffer.vertex(window.getGuiScaledWidth(), window.getGuiScaledHeight(), -90).uv(1, 1).endVertex();
		buffer.vertex(window.getGuiScaledWidth(), 0, -90).uv(1, 0).endVertex();
		buffer.vertex(0, 0, -90).uv(0, 0).endVertex();
		tesselator.end();
		RenderSystem.depthMask(true);
		RenderSystem.enableDepthTest();
		//RenderSystem.enableAlphaTest();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}

	public static void drawVerticalGradient(PoseStack matrixStack, int x, int y, int z, int width, int height, int topColour, int bottomColour) {
		float alphaTop = (float)(topColour >> 24 & 255) / 255.0F;
		float redTop = (float)(topColour >> 16 & 255) / 255.0F;
		float greenTop = (float)(topColour >> 8 & 255) / 255.0F;
		float blueTop = (float)(topColour & 255) / 255.0F;
		float alphaBottom = (float)(bottomColour >> 24 & 255) / 255.0F;
		float redBottom = (float)(bottomColour >> 16 & 255) / 255.0F;
		float greenBottom = (float)(bottomColour >> 8 & 255) / 255.0F;
		float blueBottom = (float)(bottomColour & 255) / 255.0F;

		//RenderSystem.pushTextureAttributes();
		RenderSystem.enableBlend();
		//RenderSystem.disableAlphaTest();
		RenderSystem.defaultBlendFunc();
		//RenderSystem.shadeModel(7425);

		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		Tesselator tesselator = Tesselator.getInstance();
		BufferBuilder bufferbuilder = tesselator.getBuilder();
		Matrix4f matrix = matrixStack.last().pose();

		bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
		bufferbuilder.vertex(matrix, x + width, y, z).color(redTop, greenTop, blueTop, alphaTop).endVertex();
		bufferbuilder.vertex(matrix, x, y, z).color(redTop, greenTop, blueTop, alphaTop).endVertex();
		bufferbuilder.vertex(matrix, x, y + height, z).color(redBottom, greenBottom, blueBottom, alphaBottom).endVertex();
		bufferbuilder.vertex(matrix, x + width, y + height, z).color(redBottom, greenBottom, blueBottom, alphaBottom).endVertex();
		tesselator.end();
		//RenderSystem.shadeModel(7424);
		RenderSystem.disableBlend();
		//RenderSystem.enableAlphaTest();
		//RenderSystem.popAttributes();
	}

	public static void drawColouredBox(PoseStack matrixStack, int x, int y, int z, int width, int height, int colour) {
		float alpha = (float)(colour >> 24 & 255) / 255.0F;
		float red = (float)(colour >> 16 & 255) / 255.0F;
		float green = (float)(colour >> 8 & 255) / 255.0F;
		float blue = (float)(colour & 255) / 255.0F;

		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		BufferBuilder bufferBuilder = Tesselator.getInstance().getBuilder();
		Matrix4f matrix = matrixStack.last().pose();

		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
		bufferBuilder.vertex(matrix, (float)x, (float)y + height, z).color(red, green, blue, alpha).endVertex();
		bufferBuilder.vertex(matrix, (float)x + width, (float)y + height, z).color(red, green, blue, alpha).endVertex();
		bufferBuilder.vertex(matrix, (float)x + width, (float)y, z).color(red, green, blue, alpha).endVertex();
		bufferBuilder.vertex(matrix, (float)x, (float)y, z).color(red, green, blue, alpha).endVertex();
		BufferUploader.drawWithShader(bufferBuilder.end());
		RenderSystem.disableBlend();
	}

	public static void drawCenteredScaledMessage(PoseStack matrix, Font fontRenderer, Component msg, float x, float y, float scale, int colour, StringRenderType renderType) {
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

		//RenderSystem.enableAlphaTest();
		matrix.popPose();
	}

	public static void drawCenteredScaledString(PoseStack matrix, Font fontRenderer, String msg, float x, float y, float scale, int colour, StringRenderType renderType) {
		drawCenteredScaledMessage(matrix, fontRenderer, Component.literal(msg), x, y, scale, colour, renderType);
	}

	public static void drawScaledMessage(PoseStack matrix, Font fontRenderer, Component msg, float x, float y, float scale, int colour, StringRenderType renderType) {
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

		//RenderSystem.enableAlphaTest();
		matrix.popPose();
	}

	public static void drawScaledString(PoseStack matrix, Font fontRenderer, String msg, int x, int y, float scale, int colour, StringRenderType renderType) {
		drawScaledMessage(matrix, fontRenderer, Component.literal(msg), x, y, scale, colour, renderType);
	}

	public static void drawOutlinedText(PoseStack matrix, Font fontRenderer, String msg, int x, int y, int colour, float currentScale) {
		RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f);
		fontRenderer.draw(matrix, msg, x, y + (1 / currentScale), 0);
		fontRenderer.draw(matrix, msg, x, y - (1 / currentScale), 0);
		fontRenderer.draw(matrix, msg, x + (1 / currentScale), y, 0);
		fontRenderer.draw(matrix, msg, x - (1 / currentScale), y, 0);
		fontRenderer.draw(matrix, msg, x, y, colour);
		//RenderSystem.enableAlphaTest();
	}

	public static void drawWrappedMessage(PoseStack matrix, Font fontRenderer, Component message, float x, float y, int maxLength, int color) {
		Matrix4f matrix4f = matrix.last().pose();

		for (FormattedCharSequence processor : fontRenderer.split(message, maxLength)) {
			MultiBufferSource.BufferSource renderTypeBuffer = MultiBufferSource.immediate(Tesselator.getInstance().getBuilder());

			fontRenderer.drawInBatch(processor, x, y, color, false, matrix4f, renderTypeBuffer, Font.DisplayMode.NORMAL, 0, LightTexture.FULL_BRIGHT);
			renderTypeBuffer.endBatch();

			y += fontRenderer.lineHeight;
		}
	}

	public static void renderItemInGui(PoseStack matrix, Minecraft mc, ItemStack stack, int x, int y) {
		BakedModel model = mc.getItemRenderer().getModel(stack, null, null, 0);

		matrix.pushPose();
		RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
		RenderSystem.setShaderTexture(0, InventoryMenu.BLOCK_ATLAS);
		mc.getTextureManager().getTexture(InventoryMenu.BLOCK_ATLAS).setFilter(false, false);
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		matrix.translate((float)x, (float)y, 100.0);
		matrix.translate(8, 8, 0);
		matrix.scale(1, -1, 1);
		matrix.scale(16, 16, 16);

		MultiBufferSource.BufferSource renderTypeBuffer = Minecraft.getInstance().renderBuffers().bufferSource();
		boolean useItemLight = !model.usesBlockLight();

		if (useItemLight)
			Lighting.setupForFlatItems();

		mc.getItemRenderer().render(stack, ItemDisplayContext.GUI, false, matrix, renderTypeBuffer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, model);
		renderTypeBuffer.endBatch();
		RenderSystem.enableDepthTest();

		if (useItemLight)
			Lighting.setupFor3DItems();

		matrix.popPose();
	}

	public static int getPotionGuiRenderOffset() {
		Minecraft mc = Minecraft.getInstance();

		if (mc.player == null || mc.player.getActiveEffects().isEmpty() || AoAConfigs.CLIENT.disableHudPotionOffset.get())
			return 0;

		int effectRenderYOffset = 0;

		for (MobEffectInstance effect : mc.player.getActiveEffects()) {
			if (effect.getDuration() > 0 && IClientMobEffectExtensions.DEFAULT.isVisibleInGui(effect) && effect.isVisible()) {
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

	public static void prepRenderTexture(ResourceLocation texture) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		setRenderingTexture(texture);
	}

	public static void setRenderingTexture(ResourceLocation texture) {
		RenderSystem.setShaderTexture(0, texture);
	}

	public static void resetShaderColour() {
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}

	public static void setDefaultAlphaBlend() {
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	}

	public static int selectVForWidgetState(AbstractWidget widget, int disabledV, int activeV, int focussedV) {
		return widget.isActive() ? widget.isHoveredOrFocused() ? focussedV : activeV : disabledV;
	}

	public enum StringRenderType {
		NORMAL,
		DROP_SHADOW,
		OUTLINED
	}
}

package net.tslat.aoa3.util;

import com.mojang.blaze3d.font.GlyphInfo;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientMobEffectExtensions;
import net.tslat.aoa3.common.registration.AoAConfigs;
import org.joml.Matrix4f;

public final class RenderUtil {
	// <-- Textures

	public static void renderTexture(PoseStack matrix, int x, int y, float u, float v, float width, float height) {
		renderCustomSizedTexture(matrix, x, y, u, v, width, height, width, height);
	}

	public static void renderCustomSizedTexture(PoseStack matrix, int x, int y, float u, float v, float uWidth, float vHeight, float textureWidth, float textureHeight) {
		renderScaledCustomSizedTexture(matrix, x, y, u, v, uWidth, vHeight, uWidth, vHeight, textureWidth, textureHeight);
	}

	public static void renderScaledCustomSizedTexture(PoseStack matrixStack, float x, float y, float u, float v, float uWidth, float vHeight, float renderWidth, float renderHeight, float textureWidth, float textureHeight) {
		final BufferBuilder buffer = Tesselator.getInstance().getBuilder();
		final Matrix4f pose = matrixStack.last().pose();
		final float widthRatio = 1 / textureWidth;
		final float heightRatio = 1 / textureHeight;

		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		buffer.vertex(pose, x, y + renderHeight, 0f).uv(u * widthRatio, (v + vHeight) * heightRatio).endVertex();
		buffer.vertex(pose, x + renderWidth, y + renderHeight, 0f).uv((u + uWidth) * widthRatio, (v + vHeight) * heightRatio).endVertex();
		buffer.vertex(pose, x + renderWidth, y, 0f).uv((u + uWidth) * widthRatio, v * heightRatio).endVertex();
		buffer.vertex(pose, x, y, 0f).uv(u * widthRatio, v * heightRatio).endVertex();
		BufferUploader.drawWithShader(buffer.end());
	}

	public static void renderFullscreenTexture(GuiGraphics guiGraphics, ResourceLocation texture) {
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		resetShaderColour();
		guiGraphics.blit(texture, 0, 0, -90, 0, 0, guiGraphics.guiWidth(), guiGraphics.guiHeight(), guiGraphics.guiWidth(), guiGraphics.guiHeight());
		RenderSystem.depthMask(true);
		resetShaderColour();
	}

	public static void renderVerticalGradient(PoseStack poseStack, float x, float y, float width, float height, int topColour, int bottomColour) {
		MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

		renderVerticalGradient(poseStack, bufferSource.getBuffer(RenderType.gui()), x, y, width, height, topColour, bottomColour);
		bufferSource.endBatch(RenderType.gui());
	}

	public static void renderVerticalGradient(PoseStack poseStack, VertexConsumer buffer, float x, float y, float width, float height, int topColour, int bottomColour) {
		final float alphaTop = (float)(topColour >> 24 & 255) / 255f;
		final float redTop = (float)(topColour >> 16 & 255) / 255f;
		final float greenTop = (float)(topColour >> 8 & 255) / 255f;
		final float blueTop = (float)(topColour & 255) / 255f;
		final float alphaBottom = (float)(bottomColour >> 24 & 255) / 255f;
		final float redBottom = (float)(bottomColour >> 16 & 255) / 255f;
		final float greenBottom = (float)(bottomColour >> 8 & 255) / 255f;
		final float blueBottom = (float)(bottomColour & 255) / 255f;
		final Matrix4f pose = poseStack.last().pose();

		buffer.vertex(pose, x + width, y, 0).color(redTop, greenTop, blueTop, alphaTop).endVertex();
		buffer.vertex(pose, x, y, 0).color(redTop, greenTop, blueTop, alphaTop).endVertex();
		buffer.vertex(pose, x, y + height, 0).color(redBottom, greenBottom, blueBottom, alphaBottom).endVertex();
		buffer.vertex(pose, x + width, y + height, 0).color(redBottom, greenBottom, blueBottom, alphaBottom).endVertex();
	}

	public static void drawRectangle(PoseStack poseStack, float x, float y, float width, float height, int colour) {
		MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();

		drawRectangle(poseStack, bufferSource.getBuffer(RenderType.gui()), x, y, width, height, colour);
		bufferSource.endBatch(RenderType.gui());
	}

	public static void drawRectangle(PoseStack poseStack, VertexConsumer buffer, float x, float y, float width, float height, int colour) {
		final float alpha = (float)(colour >> 24 & 255) / 255f;
		final float red = (float)(colour >> 16 & 255) / 255f;
		final float green = (float)(colour >> 8 & 255) / 255f;
		final float blue = (float)(colour & 255) / 255f;
		final Matrix4f pose = poseStack.last().pose();

		buffer.vertex(pose, x, y + height, 0).color(red, green, blue, alpha).endVertex();
		buffer.vertex(pose, x + width, y + height, 0).color(red, green, blue, alpha).endVertex();
		buffer.vertex(pose, x + width, y, 0).color(red, green, blue, alpha).endVertex();
		buffer.vertex(pose, x, y, 0).color(red, green, blue, alpha).endVertex();
	}

	// <-- Text

	public static void renderText(PoseStack poseStack, Component text, float x, float y, int colour, TextRenderType renderType) {
		renderText(poseStack, Minecraft.getInstance().font, text.getVisualOrderText(), x, y, colour, 0, renderType, LightTexture.FULL_BRIGHT, Minecraft.getInstance().renderBuffers().bufferSource());
	}

	public static void renderText(PoseStack poseStack, Font fontRenderer, FormattedCharSequence text, float x, float y, int colour, int outlineColour, TextRenderType renderType, int packedLight, MultiBufferSource.BufferSource bufferSource) {
		renderType.render(fontRenderer, poseStack.last().pose(), text, x, y, colour, outlineColour, packedLight, bufferSource);
		bufferSource.endLastBatch();
	}

	public static void renderCenteredScaledText(PoseStack poseStack, Component text, float x, float y, float scale, int colour, TextRenderType renderType) {
		renderCenteredScaledText(poseStack, Minecraft.getInstance().font, text, x, y, scale, colour, 0, renderType, LightTexture.FULL_BRIGHT, Minecraft.getInstance().renderBuffers().bufferSource());
	}

	public static void renderCenteredScaledText(PoseStack poseStack, Font fontRenderer, Component text, float x, float y, float scale, int colour, int outlineColour, TextRenderType renderType, int packedLight, MultiBufferSource.BufferSource bufferSource) {
		poseStack.pushPose();
		poseStack.scale(scale, scale, 1);

		final float renderX = (x - fontRenderer.width(text) * scale / 2f) / scale;
		final float renderY = y / scale;

		renderText(poseStack, fontRenderer, text.getVisualOrderText(), renderX, renderY, colour, outlineColour, renderType, packedLight, bufferSource);

		poseStack.popPose();
	}

	public static void renderScaledText(PoseStack poseStack, Component text, float x, float y, float scale, int colour, TextRenderType renderType) {
		renderScaledText(poseStack, Minecraft.getInstance().font, text, x, y, scale, colour, 0, renderType, LightTexture.FULL_BRIGHT, Minecraft.getInstance().renderBuffers().bufferSource());
	}

	public static void renderScaledText(PoseStack poseStack, Font fontRenderer, Component text, float x, float y, float scale, int colour, int outlineColour, TextRenderType renderType, int packedLight, MultiBufferSource.BufferSource bufferSource) {
		poseStack.pushPose();
		poseStack.scale(scale, scale, 1);

		renderText(poseStack, fontRenderer, text.getVisualOrderText(), x / scale, y / scale, colour, outlineColour, renderType, packedLight, bufferSource);

		poseStack.popPose();
	}

	public static void renderWrappedText(PoseStack poseStack, Component text, float x, float y, int maxLength, int colour, TextRenderType renderType) {
		renderWrappedText(poseStack, Minecraft.getInstance().font, text, x, y, maxLength, colour, 0, renderType, LightTexture.FULL_BRIGHT, Minecraft.getInstance().renderBuffers().bufferSource());
	}

	public static void renderWrappedText(PoseStack poseStack, Font fontRenderer, Component text, float x, float y, int maxLength, int colour, int outlineColour, TextRenderType renderType, int packedLight, MultiBufferSource.BufferSource bufferSource) {
		for (FormattedCharSequence line : fontRenderer.split(text, maxLength)) {
			renderText(poseStack, fontRenderer, line, x, y, colour, outlineColour, renderType, packedLight, bufferSource);
			bufferSource.endLastBatch();

			y += fontRenderer.lineHeight;
		}
	}

	// <-- Other

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

	public static void prepSpriteRender(ResourceLocation sprite) {
		prepRenderTexture(Minecraft.getInstance().getGuiSprites().getSprite(sprite).atlasLocation());
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

	public interface TextRenderType {
		void render(final Font fontRenderer, final Matrix4f pose, final FormattedCharSequence text, float x, float y, int colour, int outlineColour, int packedLight, final MultiBufferSource.BufferSource bufferSource);

		TextRenderType NORMAL = (fontRenderer, pose, text, x, y, colour, outlineColour, packedLight, bufferSource) -> {
			fontRenderer.drawInBatch(text, x, y, colour, false, pose, bufferSource, Font.DisplayMode.NORMAL, outlineColour, packedLight);
		};
		TextRenderType DROP_SHADOW = (fontRenderer, pose, text, x, y, colour, outlineColour, packedLight, bufferSource) -> {
			fontRenderer.drawInBatch(text, x, y, colour, true, pose, bufferSource, Font.DisplayMode.NORMAL, outlineColour, packedLight);
		};
		TextRenderType TRANSLUCENT = (fontRenderer, pose, text, x, y, colour, outlineColour, packedLight, bufferSource) -> {
			fontRenderer.drawInBatch(text, x, y, colour, false, pose, bufferSource, Font.DisplayMode.SEE_THROUGH, outlineColour, packedLight);
		};
		TextRenderType GLOWING = (fontRenderer, pose, text, x, y, colour, outlineColour, packedLight, bufferSource) -> {
			fontRenderer.drawInBatch8xOutline(text, x, y, colour, outlineColour, pose, bufferSource, packedLight);
		};
		TextRenderType OUTLINED = (fontRenderer, pose, text, x, y, colour, outlineColour, packedLight, bufferSource) -> {
			final int borderColour = (outlineColour & -67108864) == 0 ? outlineColour | -16777216 : outlineColour;
			Font.StringRenderOutput outlineOutput = fontRenderer.new StringRenderOutput(bufferSource, 0, 0, borderColour, false, pose, Font.DisplayMode.NORMAL, packedLight);

			for (float deltaX = -1; deltaX <= 1; deltaX++) {
				for (float deltaY = -1; deltaY <= 1; deltaY++) {
					if (deltaX == 0 ^ deltaY == 0) {
						final float[] newX = new float[] {x};
						final float offsetX = deltaX;
						final float offsetY = deltaY;

						text.accept((currentPosition, style, codePoint) -> {
							GlyphInfo glyphInfo = fontRenderer.getFontSet(style.getFont()).getGlyphInfo(codePoint, fontRenderer.filterFishyGlyphs);
							outlineOutput.x = newX[0] + offsetX * glyphInfo.getShadowOffset() * 0.6f;
							outlineOutput.y = y + offsetY * glyphInfo.getShadowOffset() * 0.6f;
							newX[0] += glyphInfo.getAdvance(style.isBold());

							return outlineOutput.accept(currentPosition, style.withColor(borderColour), codePoint);
						});
					}
				}
			}

			Font.StringRenderOutput output = fontRenderer.new StringRenderOutput(bufferSource, x, y, (colour & -67108864) == 0 ? colour | -16777216 : colour, false, pose, Font.DisplayMode.POLYGON_OFFSET, packedLight);

			text.accept(output);
			output.finish(0, x);
		};
	}
}

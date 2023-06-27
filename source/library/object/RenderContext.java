package net.tslat.aoa3.library.object;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.util.RenderUtil;
import org.jetbrains.annotations.Nullable;

public class RenderContext {
	private final Minecraft mc;
	private final GuiGraphics guiGraphics;
	private final PoseStack poseStack;
	private final MultiBufferSource.BufferSource buffers;
	private Font fontRenderer;

	public static RenderContext of(final GuiGraphics guiGraphics) {
		return new RenderContext(guiGraphics, Minecraft.getInstance(), guiGraphics.pose(), guiGraphics.bufferSource());
	}

	public RenderContext(@Nullable GuiGraphics guiGraphics, Minecraft minecraft, PoseStack poseStack, MultiBufferSource.BufferSource buffers) {
		this.mc = minecraft;
		this.guiGraphics = guiGraphics;
		this.poseStack = poseStack;
		this.buffers = buffers;
		this.fontRenderer = this.mc.font;
	}

	public RenderContext(Minecraft minecraft, PoseStack poseStack, MultiBufferSource.BufferSource buffers) {
		this(null, minecraft, poseStack, buffers);
	}

	public RenderContext(Minecraft minecraft, MultiBufferSource.BufferSource buffers) {
		this(minecraft, new PoseStack(), buffers);
	}

	public RenderContext() {
		this(Minecraft.getInstance(), Minecraft.getInstance().renderBuffers().bufferSource());
	}

	public GuiGraphics getGuiGraphics() {
		return this.guiGraphics == null ? new GuiGraphics(this.mc, this.poseStack, this.buffers) : this.guiGraphics;
	}

	public Font getFont() {
		return this.fontRenderer;
	}

	public RenderContext withFont(final Font fontRenderer) {
		this.fontRenderer = fontRenderer;

		return this;
	}

	// <-- Rendering

	public void pushPose() {
		this.poseStack.pushPose();
	}

	public void popPose() {
		this.poseStack.popPose();
	}

	// <-- Textures

	public void renderTexture(int x, int y, float u, float v, float width, float height) {
		renderCustomSizedTexture(x, y, u, v, width, height, width, height);
	}

	public void renderCustomSizedTexture(int x, int y, float u, float v, float uWidth, float vHeight, float textureWidth, float textureHeight) {
		renderScaledCustomSizedTexture(x, y, u, v, uWidth, vHeight, uWidth, vHeight, textureWidth, textureHeight);
	}

	public void renderScaledCustomSizedTexture(float x, float y, float u, float v, float uWidth, float vHeight, float renderWidth, float renderHeight, float textureWidth, float textureHeight) {
		RenderUtil.renderScaledCustomSizedTexture(this.poseStack, x, y, u, v, uWidth, vHeight, renderWidth, renderHeight, textureWidth, textureHeight);
	}

	// <-- Text

	public void renderText(Component text, float x, float y, int colour, RenderUtil.TextRenderType renderType) {
		renderText(text, x, y, colour, 0, renderType, LightTexture.FULL_BRIGHT);
	}

	public void renderText(Component text, float x, float y, int colour, int outlineColour, RenderUtil.TextRenderType renderType, int packedLight) {
		renderText(text.getVisualOrderText(), x, y, colour, outlineColour, renderType, packedLight);
	}

	public void renderText(FormattedCharSequence text, float x, float y, int colour, int outlineColour, RenderUtil.TextRenderType renderType, int packedLight) {
		RenderUtil.renderText(this.poseStack, this.fontRenderer, text, x, y, colour, outlineColour, renderType, packedLight, this.buffers);
		this.guiGraphics.flushIfUnmanaged();
	}

	public void renderCenteredScaledText(Component text, float x, float y, float scale, int colour, RenderUtil.TextRenderType renderType) {
		renderCenteredScaledText(text, x, y, scale, colour, 0, renderType, LightTexture.FULL_BRIGHT);
	}

	public void renderCenteredScaledText(Component text, float x, float y, float scale, int colour, int outlineColour, RenderUtil.TextRenderType renderType, int packedLight) {
		RenderUtil.renderCenteredScaledText(this.poseStack, this.fontRenderer, text, x, y, scale, colour, outlineColour, renderType, packedLight, this.buffers);
		this.guiGraphics.flushIfUnmanaged();
	}

	public void renderScaledText(Component text, float x, float y, float scale, int colour, RenderUtil.TextRenderType renderType) {
		renderScaledText(text, x, y, scale, colour, 0, renderType, LightTexture.FULL_BRIGHT);
	}

	public void renderScaledText(Component text, float x, float y, float scale, int colour, int outlineColour, RenderUtil.TextRenderType renderType, int packedLight) {
		this.poseStack.pushPose();
		this.poseStack.scale(scale, scale, 1);

		renderText(text, x / scale, y / scale, colour, outlineColour, renderType, packedLight);

		this.poseStack.popPose();
	}

	public void renderWrappedText(Component text, float x, float y, int maxLength, int colour, RenderUtil.TextRenderType renderType) {
		renderWrappedText(text, x, y, maxLength, colour, 0, renderType, LightTexture.FULL_BRIGHT);
	}

	public void renderWrappedText(Component text, float x, float y, int maxLength, int colour, int outlineColour, RenderUtil.TextRenderType renderType, int packedLight) {
		RenderUtil.renderWrappedText(this.poseStack, this.fontRenderer, text, x, y, maxLength, colour, outlineColour, renderType, packedLight, this.buffers);
	}

	// <-- Other

	public void renderItem(LivingEntity owner, ItemStack stack, int centerX, int centerY) {
		renderItem(owner, stack, centerX, centerY, 0);
	}

	public void renderItem(LivingEntity owner, ItemStack stack, int centerX, int centerY, int seed) {
		this.guiGraphics.renderItem(owner, stack, centerX, centerY, seed);
	}

	public void renderItemAndDetails(LivingEntity owner, ItemStack stack, int centerX, int centerY) {
		renderItemAndDetails(owner, stack, centerX, centerY, 0);
	}

	public void renderItemAndDetails(LivingEntity owner, ItemStack stack, int centerX, int centerY, int seed) {
		renderItem(owner, stack, centerX, centerY, seed);
		renderItemDetails(stack, centerX, centerY);
	}

	public void renderDummyItemAndDetails(ItemStack stack, int centerX, int centerY) {
		renderDummyItem(stack, centerX, centerY);
		renderItemDetails(stack, centerX, centerY);
	}

	public void renderDummyItem(ItemStack stack, int centerX, int centerY) {
		this.guiGraphics.renderFakeItem(stack, centerX, centerY);
	}

	public void renderItemDetails(ItemStack stack, int centerX, int centerY) {
		renderItemDetails(stack, centerX, centerY, null);
	}

	public void renderItemDetails(ItemStack stack, int centerX, int centerY, @Nullable String sizeOverride) {
		this.guiGraphics.renderItemDecorations(this.fontRenderer, stack, centerX, centerY, sizeOverride);
	}

	// <-- Helpers

	public void resetShaderColour() {
		setShaderColour(1, 1, 1, 1);
	}

	public void setTextureForRendering(ResourceLocation texture) {
		RenderSystem.setShaderTexture(0, texture);
	}

	public void setShaderColour(float red, float green, float blue, float alpha) {
		this.guiGraphics.setColor(red, green, blue, alpha);
	}

	public int textWidth(final FormattedText text) {
		return getFont().width(text);
	}

	public enum TextRenderType {
		NORMAL,
		DROP_SHADOW,
		OUTLINED
	}
}

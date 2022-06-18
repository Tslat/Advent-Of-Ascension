package net.tslat.aoa3.client.fluid.renderproperties;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.IFluidTypeRenderProperties;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.ColourUtil;
import org.jetbrains.annotations.Nullable;

public class ToxicWasteRenderProperties implements IFluidTypeRenderProperties {
	public static final ResourceLocation OVERLAY = new ResourceLocation(AdventOfAscension.MOD_ID, "block/toxic_waste_overlay");
	public static final ResourceLocation UNDERWATER = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/block/toxic_waste_overlay.png");
	public static final ResourceLocation FLOWING = new ResourceLocation(AdventOfAscension.MOD_ID, "block/toxic_waste_flow");
	public static final ResourceLocation STILL = new ResourceLocation(AdventOfAscension.MOD_ID, "block/toxic_waste_still");

	@Override
	public int getColorTint() {
		return ColourUtil.RGBA(38, 42, 23, 255);
	}

	@Override
	public ResourceLocation getStillTexture() {
		return STILL;
	}

	@Override
	public @Nullable ResourceLocation getOverlayTexture() {
		return OVERLAY;
	}

	@Override
	public ResourceLocation getFlowingTexture() {
		return FLOWING;
	}

	@Nullable
	@Override
	public ResourceLocation getRenderOverlayTexture(Minecraft mc) {
		return UNDERWATER;
	}

	@Override
	public void renderOverlay(Minecraft mc, PoseStack stack) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.enableTexture();
		RenderSystem.setShaderTexture(0, getRenderOverlayTexture(mc));

		BufferBuilder buffer = Tesselator.getInstance().getBuilder();
		float uOffset = mc.player.getYRot() / 512f + (mc.player.tickCount / 1800f);
		float vOffset = mc.player.getXRot() / 1024f + (mc.player.tickCount / 3600f);
		Matrix4f matrixStack = stack.last().pose();

		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderColor(0.5f, 0.5f, 0.5f, 0.5f);
		buffer.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
		buffer.vertex(matrixStack, -1.0F, -1.0F, -0.5F).uv(uOffset, 1 + vOffset).normal(0.0F, 1.0F, 0.0F).endVertex();
		buffer.vertex(matrixStack, 1.0F, -1.0F, -0.5F).uv(1 + uOffset, 1 + vOffset).normal(0.0F, 1.0F, 0.0F).endVertex();
		buffer.vertex(matrixStack, 1.0F, 1.0F, -0.5F).uv(1 + uOffset, vOffset).normal(0.0F, 1.0F, 0.0F).endVertex();
		buffer.vertex(matrixStack, -1.0F, 1.0F, -0.5F).uv(uOffset, vOffset).normal(0.0F, 1.0F, 0.0F).endVertex();
		BufferUploader.drawWithShader(buffer.end());
		RenderSystem.disableBlend();
	}
}

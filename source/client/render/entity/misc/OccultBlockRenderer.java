package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.OutlineLayerBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.entity.misc.OccultBlockEntity;

import javax.annotation.Nullable;

public class OccultBlockRenderer extends EntityRenderer<OccultBlockEntity> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entities/misc/occult_block.png");

	public OccultBlockRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public void render(OccultBlockEntity entity, float entityYaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer bufferIn, int packedLightIn) {
		if (entity.isAlive()) {
			Minecraft mc = Minecraft.getInstance();
			BlockState block = entity.getMarkedBlock();
			OutlineLayerBuffer renderTypeBuffer = mc.renderBuffers().outlineBufferSource();

			applyColourToBuffer(renderTypeBuffer, block);

			matrix.pushPose();
			matrix.scale(1.001f, 1.001f, 1.001f);
			matrix.translate(-0.0005f, 0.0005f, -0.0005f);
			mc.getBlockRenderer().renderSingleBlock(block, matrix, renderTypeBuffer, LightTexture.pack(15, 15), OverlayTexture.NO_OVERLAY);
			matrix.popPose();
		}

		super.render(entity, entityYaw, partialTicks, matrix, bufferIn, packedLightIn);
	}

	protected void applyColourToBuffer(OutlineLayerBuffer buffer, BlockState block) {
		switch (block.getBlock().getHarvestLevel(block)) {
			case 0:
				buffer.setColor(255, 0, 0, 255);
			case 1:
				buffer.setColor(223, 153, 0, 255);
			case 2:
				buffer.setColor(255, 255, 0, 255);
			case 3:
				buffer.setColor(0, 147, 66, 255);
			case 4:
			default:
				buffer.setColor(0, 153, 0, 255);
		}
	}

	@Nullable
	public ResourceLocation getTextureLocation(OccultBlockEntity entity) {
		return texture;
	}
}

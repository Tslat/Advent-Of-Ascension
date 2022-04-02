/*
package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.tslat.aoa3.client.model.entity.misc.FishingCageModel;
import net.tslat.aoa3.content.entity.misc.FishingCageEntity;

import javax.annotation.Nullable;

public class FishingCageRenderer extends EntityRenderer<FishingCageEntity> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entity/misc/fishing_cage.png");
	private final FishingCageModel model = new FishingCageModel();

	public FishingCageRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager);
	}

	@Override
	public void render(FishingCageEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		matrix.pushPose();
		matrix.translate(0, 1.5d, 0);
		matrix.mulPose(Vector3f.XP.rotationDegrees(180));
		matrix.mulPose(Vector3f.YP.rotationDegrees(entityYaw));
		matrix.scale(-1, 1, -1);

		IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));

		model.setupAnim(entity, 0, 0, entity.tickCount, entityYaw, 0);
		model.renderToBuffer(matrix, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

		matrix.translate(0, 1.4d, 0.1d);
		matrix.mulPose(Vector3f.XP.rotationDegrees(90));

		ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

		for (ItemStack stack : entity.getLoot()) {
			itemRenderer.renderStatic(stack, ItemCameraTransforms.TransformType.GROUND, packedLight, OverlayTexture.NO_OVERLAY, matrix, buffer);
			matrix.translate(0, -0.15d, 0);
		}

		matrix.popPose();

		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(FishingCageEntity entity) {
		return texture;
	}
}
*/

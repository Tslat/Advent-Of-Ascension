package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.AoAEntityRendering;
import net.tslat.aoa3.client.model.misc.LottoTotemModel;
import net.tslat.aoa3.content.entity.misc.LottoTotemEntity;

import javax.annotation.Nullable;

public class LottoTotemRenderer extends EntityRenderer<LottoTotemEntity> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entity/misc/lotto_totem.png");
	private final LottoTotemModel model;

	public LottoTotemRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager);

		this.model = new LottoTotemModel(renderManager.bakeLayer(AoAEntityRendering.LOTTO_TOTEM.getMainLayerLocation()));
	}

	@Override
	public void render(LottoTotemEntity entity, float entityYaw, float partialTicks, PoseStack matrix, MultiBufferSource buffer, int packedLight) {
		matrix.pushPose();
		matrix.translate(0, 1.5d, 0);
		matrix.mulPose(Axis.XP.rotationDegrees(180));
		matrix.scale(-1, 1, -1);

		VertexConsumer vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));

		model.setupAnim(entity, 0, 0, entity.tickCount, entityYaw, 0);
		model.renderToBuffer(matrix, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

		matrix.popPose();

		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(LottoTotemEntity entity) {
		return texture;
	}
}
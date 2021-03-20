package net.tslat.aoa3.client.render.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.tslat.aoa3.client.model.entity.misc.BaronBombModel;
import net.tslat.aoa3.entity.misc.BaronBombEntity;

import javax.annotation.Nullable;

public class BaronBombRenderer extends EntityRenderer<BaronBombEntity> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entities/misc/baron_bomb.png");
	private final EntityModel<BaronBombEntity> model = new BaronBombModel();

	public BaronBombRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public void render(BaronBombEntity entity, float entityYaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		matrix.pushPose();
		matrix.mulPose(Vector3f.XP.rotationDegrees(180));
		matrix.scale(-1, 1, -1);

		IVertexBuilder vertexBuilder = buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));

		model.setupAnim(entity, 0, 0, entity.tickCount, entityYaw, 0);
		model.renderToBuffer(matrix, vertexBuilder, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);

		matrix.popPose();

		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(BaronBombEntity entity) {
		return TEXTURE;
	}
}

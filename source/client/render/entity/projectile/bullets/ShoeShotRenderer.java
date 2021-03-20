package net.tslat.aoa3.client.render.entity.projectile.bullets;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.util.math.vector.Vector3f;
import net.tslat.aoa3.entity.projectile.gun.ShoeShotEntity;

import javax.annotation.Nullable;

public class ShoeShotRenderer extends EntityRenderer<ShoeShotEntity> {
	private final RenderType renderType;
	private final ResourceLocation texture;

	public ShoeShotRenderer(final EntityRendererManager manager, final ResourceLocation textureResource) {
		super(manager);

		texture = textureResource;
		renderType = RenderType.entityCutoutNoCull(textureResource);
	}

	@Override
	public void render(ShoeShotEntity entity, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		matrix.pushPose();
		matrix.scale(0.5f, 0.5f, 0.5f);
		matrix.mulPose(this.entityRenderDispatcher.cameraOrientation());
		matrix.mulPose(Vector3f.YP.rotationDegrees(180.0F));
		matrix.mulPose(Vector3f.XP.rotationDegrees(((entity.tickCount + partialTicks) / 0.3f) * (180f / (float)Math.PI)));

		MatrixStack.Entry matrixEntry = matrix.last();
		Matrix4f matrix4f = matrixEntry.pose();
		Matrix3f normal = matrixEntry.normal();
		IVertexBuilder vertexBuilder = buffer.getBuffer(renderType);

		pos(vertexBuilder, matrix4f, normal, packedLight, 0, 0, 0, 1);
		pos(vertexBuilder, matrix4f, normal, packedLight, 1, 0, 1, 1);
		pos(vertexBuilder, matrix4f, normal, packedLight, 1, 1, 1, 0);
		pos(vertexBuilder, matrix4f, normal, packedLight, 0, 1, 0, 0);
		matrix.popPose();

		super.render(entity, yaw, partialTicks, matrix, buffer, packedLight);
	}

	private static void pos(IVertexBuilder vertexBuilder, Matrix4f matrix4f, Matrix3f normal, int lightmapUV, float x, float y, float u, float v) {
		vertexBuilder.vertex(matrix4f, x - 0.5F, y - 0.25f, 0).color(255, 255, 255, 255).uv(u, v).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(lightmapUV).normal(normal, 0, 1, 0).endVertex();
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(ShoeShotEntity entity) {
		return texture;
	}
}

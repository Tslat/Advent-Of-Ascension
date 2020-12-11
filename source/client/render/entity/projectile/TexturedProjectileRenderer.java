package net.tslat.aoa3.client.render.entity.projectile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class TexturedProjectileRenderer<T extends Entity> extends EntityRenderer<T> {
	private final ResourceLocation texture;
	private final RenderType renderType;

	public TexturedProjectileRenderer(final EntityRendererManager renderManager, final ResourceLocation texture) {
		super(renderManager);

		this.texture = texture;
		this.renderType = RenderType.getEntityCutoutNoCull(texture);
	}

	@Override
	public void render(T entity, float yaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		matrix.push();
		matrix.scale(0.5f, 0.5f, 0.5f);
		matrix.rotate(this.renderManager.getCameraOrientation());
		matrix.rotate(Vector3f.YP.rotationDegrees(180.0F));

		MatrixStack.Entry matrixEntry = matrix.getLast();
		Matrix4f matrix4f = matrixEntry.getMatrix();
		Matrix3f normal = matrixEntry.getNormal();
		IVertexBuilder vertexBuilder = buffer.getBuffer(renderType);

		pos(vertexBuilder, matrix4f, normal, packedLight, 0, 0, 0, 1);
		pos(vertexBuilder, matrix4f, normal, packedLight, 1, 0, 1, 1);
		pos(vertexBuilder, matrix4f, normal, packedLight, 1, 1, 1, 0);
		pos(vertexBuilder, matrix4f, normal, packedLight, 0, 1, 0, 0);
		matrix.pop();
		
		super.render(entity, yaw, partialTicks, matrix, buffer, packedLight);
	}

	private static void pos(IVertexBuilder vertexBuilder, Matrix4f matrix4f, Matrix3f normal, int lightmapUV, float x, float y, float u, float v) {
		vertexBuilder.pos(matrix4f, x - 0.5F, y - 0.25f, 0).color(255, 255, 255, 255).tex(u, v).overlay(OverlayTexture.NO_OVERLAY).lightmap(lightmapUV).normal(normal, 0, 1, 0).endVertex();
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) {
		return texture;
	}
}

package net.tslat.aoa3.client.render.entity.projectile.mob;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.entity.projectile.mob.NethengeicWitherShotEntity;

import javax.annotation.Nullable;

public class NethengeicWitherShotRenderer extends EntityRenderer<NethengeicWitherShotEntity> {
	private final ResourceLocation texture;
	private final GenericHeadModel headModel = new GenericHeadModel();

	public NethengeicWitherShotRenderer(EntityRendererManager renderManager, ResourceLocation textureResource) {
		super(renderManager);

		this.texture = textureResource;
	}

	@Override
	public void render(NethengeicWitherShotEntity entity, float entityYaw, float partialTicks, MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight) {
		matrix.push();
		matrix.scale(-1.0F, -1.0F, 1.0F);

		float rotY = MathHelper.rotLerp(entity.prevRotationYaw, entity.rotationYaw, partialTicks);
		float rotX = MathHelper.lerp(partialTicks, entity.prevRotationPitch, entity.rotationPitch);
		IVertexBuilder ivertexbuilder = buffer.getBuffer(this.headModel.getRenderType(this.getEntityTexture(entity)));

		headModel.func_225603_a_(0.0F, rotY, rotX);
		headModel.render(matrix, ivertexbuilder, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrix.pop();

		super.render(entity, entityYaw, partialTicks, matrix, buffer, packedLight);
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(NethengeicWitherShotEntity entity) {
		return texture;
	}
}

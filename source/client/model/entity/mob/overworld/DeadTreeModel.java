package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class DeadTreeModel extends EntityModel<MobEntity> {
	private final ModelRenderer trunk;

	public DeadTreeModel() {
		textureWidth = 64;
		textureHeight = 32;

		trunk = new ModelRenderer(this);
		trunk.setRotationPoint(0.0F, 24.0F, 0.0F);
		trunk.setTextureOffset(0, 0).addBox(-7.0F, -16.0F, -7.0F, 14, 16, 14, 0.0F, false);
		trunk.setTextureOffset(0, 0).addBox(-7.0F, -32.0F, -7.0F, 14, 16, 14, 0.0F, false);
		trunk.setTextureOffset(0, 0).addBox(-7.0F, -48.0F, -7.0F, 14, 16, 14, 0.0F, false);
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		trunk.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

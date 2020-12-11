package net.tslat.aoa3.client.model.entity.mob.shyrelands;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class ArcflowerModel extends EntityModel<MobEntity> {
	private final ModelRenderer body5;
	private final ModelRenderer body9;

	public ArcflowerModel() {
		textureWidth = 64;
		textureHeight = 64;

		body5 = new ModelRenderer(this, 23, 35);
		body5.addBox(-7.5F, -17.0F, -7.0F, 16, 16, 0);
		body5.setRotationPoint(5.0F, 25.0F, 5.0F);
		body5.setTextureSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0F, 0.7853982F, 0.0F);
		body9 = new ModelRenderer(this, 23, 35);
		body9.addBox(-8.5F, -17.0F, -7.0F, 16, 16, 0);
		body9.setRotationPoint(-4.0F, 25.0F, 5.0F);
		body9.setTextureSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0F, -0.7853982F, 0.0F);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}

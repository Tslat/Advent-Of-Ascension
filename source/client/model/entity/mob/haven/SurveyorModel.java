package net.tslat.aoa3.client.model.entity.mob.haven;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class SurveyorModel extends EntityModel<MobEntity> {
	private final ModelRenderer shape1;
	private final ModelRenderer shape2;
	private final ModelRenderer shape3;
	private final ModelRenderer Eye;
	private final ModelRenderer shape4;
	private final ModelRenderer shape5;
	private final ModelRenderer shape6;
	private final ModelRenderer shape7;
	private final ModelRenderer shape8;
	private final ModelRenderer shape9;
	private final ModelRenderer shape10;
	private final ModelRenderer shape11;
	private final ModelRenderer shape12;

	public SurveyorModel() {
		textureWidth = 64;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 0, 17)).addBox(-6.0f, 6.0f, -3.0f, 12, 2, 6);
		shape1.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 57, 0)).addBox(-8.0f, 2.0f, -5.0f, 2, 2, 2);
		shape2.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 17)).addBox(-6.0f, -8.0f, -3.0f, 12, 2, 6);
		shape3.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(Eye = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -3.0f, 8, 8, 6);
		Eye.setRotationPoint(0.0f, 15.0f, 0.0f);
		Eye.setTextureSize(64, 32);
		Eye.mirror = true;
		setRotation(Eye, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 39, 0)).addBox(-8.0f, -4.0f, -3.0f, 2, 8, 6);
		shape4.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 39, 0)).addBox(6.0f, -4.0f, -3.0f, 2, 8, 6);
		shape5.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 57, 0)).addBox(-8.0f, -4.0f, -5.0f, 2, 2, 2);
		shape6.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape6.setTextureSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 57, 0)).addBox(6.0f, 2.0f, -5.0f, 2, 2, 2);
		shape7.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape7.setTextureSize(64, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 57, 0)).addBox(-6.0f, 6.0f, -5.0f, 2, 2, 2);
		shape8.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape8.setTextureSize(64, 32);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 57, 0)).addBox(6.0f, -4.0f, -5.0f, 2, 2, 2);
		shape9.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape9.setTextureSize(64, 32);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 57, 0)).addBox(4.0f, 6.0f, -5.0f, 2, 2, 2);
		shape10.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape10.setTextureSize(64, 32);
		shape10.mirror = true;
		setRotation(shape10, 0.0f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 57, 0)).addBox(-6.0f, -8.0f, -5.0f, 2, 2, 2);
		shape11.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape11.setTextureSize(64, 32);
		shape11.mirror = true;
		setRotation(shape11, 0.0f, 0.0f, 0.0f);
		(shape12 = new ModelRenderer(this, 57, 0)).addBox(4.0f, -8.0f, -5.0f, 2, 2, 2);
		shape12.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape12.setTextureSize(64, 32);
		shape12.mirror = true;
		setRotation(shape12, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Eye.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Eye.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		shape1.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape2.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape3.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape4.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape5.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape6.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape7.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape8.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape9.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape10.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape11.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape12.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
	}
}

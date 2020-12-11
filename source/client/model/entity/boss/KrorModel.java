package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class KrorModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightArm2;
	private final ModelRenderer leftArm2;
	private final ModelRenderer body2;
	private final ModelRenderer rightArm3;
	private final ModelRenderer leftArm3;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;
	private final ModelRenderer body7;
	private final ModelRenderer body8;

	public KrorModel() {
		textureWidth = 256;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-6.0f, -8.0f, -4.0f, 12, 8, 8);
		head.setRotationPoint(0.0f, 0.0f, -9.0f);
		head.setTextureSize(256, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 55, 7)).addBox(3.0f, -12.0f, 8.0f, 3, 12, 6);
		body.setRotationPoint(0.0f, 5.0f, 4.0f);
		body.setTextureSize(256, 32);
		body.mirror = true;
		setRotation(body, -0.7853982f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 218, 13)).addBox(-4.0f, 14.0f, -6.0f, 8, 10, 8);
		rightArm.setRotationPoint(-12.0f, 0.0f, -6.0f);
		rightArm.setTextureSize(256, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 218, 13)).addBox(-2.0f, 14.0f, -6.0f, 8, 10, 8);
		leftArm.setRotationPoint(10.0f, 0.0f, -6.0f);
		leftArm.setTextureSize(256, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 189, 13)).addBox(-2.0f, 0.0f, -2.0f, 6, 12, 6);
		rightLeg.setRotationPoint(-8.0f, 12.0f, 14.0f);
		rightLeg.setTextureSize(256, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 189, 13)).addBox(-2.0f, 0.0f, -2.0f, 6, 12, 6);
		leftLeg.setRotationPoint(6.0f, 12.0f, 14.0f);
		leftLeg.setTextureSize(256, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 162, 0)).addBox(-3.0f, -2.0f, -2.0f, 6, 22, 6);
		rightArm2.setRotationPoint(-10.0f, 4.0f, 4.0f);
		rightArm2.setTextureSize(256, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 162, 0)).addBox(-1.0f, -2.0f, -2.0f, 6, 22, 6);
		leftArm2.setRotationPoint(8.0f, 4.0f, 4.0f);
		leftArm2.setTextureSize(256, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 93, 0)).addBox(-7.0f, 0.0f, -2.0f, 14, 16, 6);
		body2.setRotationPoint(0.0f, 5.0f, 4.0f);
		body2.setTextureSize(256, 32);
		body2.mirror = true;
		setRotation(body2, 1.047198f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 135, 0)).addBox(-3.0f, -2.0f, -2.0f, 6, 26, 6);
		rightArm3.setRotationPoint(-12.0f, 0.0f, -6.0f);
		rightArm3.setTextureSize(256, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new ModelRenderer(this, 135, 0)).addBox(-1.0f, -2.0f, -2.0f, 6, 26, 6);
		leftArm3.setRotationPoint(10.0f, 0.0f, -6.0f);
		leftArm3.setTextureSize(256, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 85, 0)).addBox(-9.0f, -12.0f, -2.0f, 18, 12, 6);
		body3.setRotationPoint(0.0f, 5.0f, 4.0f);
		body3.setTextureSize(256, 32);
		body3.mirror = true;
		setRotation(body3, 1.047198f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 55, 7)).addBox(-6.0f, -12.0f, 8.0f, 3, 12, 6);
		body4.setRotationPoint(0.0f, 5.0f, 4.0f);
		body4.setTextureSize(256, 32);
		body4.mirror = true;
		setRotation(body4, -0.7853982f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 55, 7)).addBox(-8.0f, -12.0f, -11.0f, 3, 12, 6);
		body5.setRotationPoint(0.0f, 5.0f, 4.0f);
		body5.setTextureSize(256, 32);
		body5.mirror = true;
		setRotation(body5, -0.7853982f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 55, 7)).addBox(5.0f, -12.0f, -11.0f, 3, 12, 6);
		body6.setRotationPoint(0.0f, 5.0f, 4.0f);
		body6.setTextureSize(256, 32);
		body6.mirror = true;
		setRotation(body6, -0.7853982f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 55, 7)).addBox(-6.0f, -12.0f, 0.0f, 3, 12, 6);
		body7.setRotationPoint(0.0f, 5.0f, 4.0f);
		body7.setTextureSize(256, 32);
		body7.mirror = true;
		setRotation(body7, -0.7853982f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 55, 7)).addBox(3.0f, -12.0f, 0.0f, 3, 12, 6);
		body8.setRotationPoint(0.0f, 5.0f, 4.0f);
		body8.setTextureSize(256, 32);
		body8.mirror = true;
		setRotation(body8, -0.7853982f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightArm.rotateAngleY = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightArm2.rotateAngleY = 0.0f;
		rightArm3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightArm3.rotateAngleY = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftArm.rotateAngleY = 0.0f;
		leftArm2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftArm2.rotateAngleY = 0.0f;
		leftArm3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftArm3.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

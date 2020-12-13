package net.tslat.aoa3.client.model.entity.mob.mysterium;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class FungikModel extends EntityModel<MobEntity> {
	private final ModelRenderer head1;
	private final ModelRenderer body;
	private final ModelRenderer rightArm1;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg2;
	private final ModelRenderer head2;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer rightArm2;
	private final ModelRenderer rightArm3;
	private final ModelRenderer rightArm4;

	public FungikModel() {
		textureWidth = 128;
		textureHeight = 64;
		(head1 = new ModelRenderer(this, 40, 0)).addBox(-8.0f, -12.0f, -8.0f, 16, 6, 16);
		head1.setRotationPoint(0.0f, -4.0f, 0.0f);
		head1.setTextureSize(128, 64);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 71, 25)).addBox(-10.0f, -4.0f, -4.0f, 6, 3, 6);
		body.setRotationPoint(11.0f, 0.0f, 9.0f);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, -1.047198f, 0.0f, 0.0f);
		(rightArm1 = new ModelRenderer(this, 113, 16)).addBox(-3.0f, -10.0f, -11.0f, 4, 3, 4);
		rightArm1.setRotationPoint(-8.0f, -2.0f, 0.0f);
		rightArm1.setTextureSize(128, 64);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0.6108652f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 21, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 16, 4);
		leftArm.setRotationPoint(8.0f, -2.0f, 0.0f);
		leftArm.setTextureSize(128, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 48)).addBox(-3.0f, 6.0f, -3.0f, 6, 10, 6);
		rightLeg.setRotationPoint(-4.0f, 8.0f, 0.0f);
		rightLeg.setTextureSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 0, 48)).addBox(-3.0f, 6.0f, -3.0f, 6, 10, 6);
		leftLeg2.setRotationPoint(4.0f, 8.0f, 0.0f);
		leftLeg2.setTextureSize(128, 64);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 33)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leftLeg.setRotationPoint(4.0f, 8.0f, 0.0f);
		leftLeg.setTextureSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 0, 33)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		rightLeg2.setRotationPoint(-4.0f, 8.0f, 0.0f);
		rightLeg2.setTextureSize(128, 64);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -6.0f, -4.0f, 8, 6, 8);
		head2.setRotationPoint(0.0f, -4.0f, 0.0f);
		head2.setTextureSize(128, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 28, 46)).addBox(-7.0f, 0.0f, -2.0f, 14, 12, 4);
		body2.setRotationPoint(0.0f, -4.0f, 0.0f);
		body2.setTextureSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 71, 36)).addBox(-10.0f, -3.0f, -4.0f, 6, 3, 6);
		body3.setRotationPoint(4.0f, -4.0f, 9.0f);
		body3.setTextureSize(128, 64);
		body3.mirror = true;
		setRotation(body3, -0.6981317f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 59, 23)).addBox(-8.0f, -1.0f, -2.0f, 2, 11, 2);
		body4.setRotationPoint(11.0f, 0.0f, 9.0f);
		body4.setTextureSize(128, 64);
		body4.mirror = true;
		setRotation(body4, -1.047198f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 59, 23)).addBox(-8.0f, 0.0f, -2.0f, 2, 11, 2);
		body5.setRotationPoint(4.0f, -4.0f, 9.0f);
		body5.setTextureSize(128, 64);
		body5.mirror = true;
		setRotation(body5, -0.6981317f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 21, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 16, 4);
		rightArm2.setRotationPoint(-8.0f, -2.0f, 0.0f);
		rightArm2.setTextureSize(128, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 113, 24)).addBox(-2.0f, 1.0f, -10.0f, 2, 14, 2);
		rightArm3.setRotationPoint(-8.0f, -2.0f, 0.0f);
		rightArm3.setTextureSize(128, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.6108652f, 0.0f, 0.0f);
		(rightArm4 = new ModelRenderer(this, 113, 42)).addBox(-2.0f, -7.0f, -10.0f, 2, 8, 2);
		rightArm4.setRotationPoint(-8.0f, -2.0f, 0.0f);
		rightArm4.setTextureSize(128, 64);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.6108652f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head1.rotateAngleY = netHeadYaw / 57.295776f;
		head1.rotateAngleX = headPitch / 54.11268f;
		head2.rotateAngleY = netHeadYaw / 57.295776f;
		head2.rotateAngleX = headPitch / 54.11268f;
		rightArm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.61f;
		rightArm1.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		rightArm3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.61f;
		rightArm3.rotateAngleZ = 0.0f;
		rightArm4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.61f;
		rightArm4.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		rightLeg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg2.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

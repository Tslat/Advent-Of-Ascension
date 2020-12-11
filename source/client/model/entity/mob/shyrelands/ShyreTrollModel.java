package net.tslat.aoa3.client.model.entity.mob.shyrelands;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ShyreTrollModel extends EntityModel<MobEntity> {
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer head;
	private final ModelRenderer nose;
	private final ModelRenderer leftEar;
	private final ModelRenderer rightEar;
	private final ModelRenderer leftArm;
	private final ModelRenderer Staff;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftEar2;
	private final ModelRenderer rightEar2;

	public ShyreTrollModel() {
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this, 36, 15);
		body.addBox(-4.0F, 0.0F, -3.0F, 8, 11, 6);
		body.setRotationPoint(0.0F, 3.0F, 1.0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightLeg = new ModelRenderer(this, 18, 18);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4);
		rightLeg.setRotationPoint(-2.0F, 14.0F, 1.0F);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new ModelRenderer(this, 18, 18);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4);
		leftLeg.setRotationPoint(2.0F, 14.0F, 1.0F);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
		head.setRotationPoint(0.0F, 3.0F, 0.0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		nose = new ModelRenderer(this, 24, 0);
		nose.addBox(-1.0F, -7.0F, -5.0F, 2, 5, 2, 0.0F);
		nose.setRotationPoint(0.0F, 3.0F, -1.0F);
		nose.setTextureSize(64, 32);
		nose.mirror = true;
		setRotation(nose, 0.0F, 0.0F, 0.0F);
		leftEar = new ModelRenderer(this, 43, 8);
		leftEar.addBox(-9.0F, -16.0F, -3.0F, 4, 1, 5, 0.0F);
		leftEar.setRotationPoint(1.0F, 3.0F, 0.0F);
		leftEar.setTextureSize(64, 32);
		leftEar.mirror = true;
		rightEar = new ModelRenderer(this, 32, 8);
		rightEar.addBox(5.0F, -16.0F, -3.0F, 4, 1, 5, 0.0F);
		rightEar.setRotationPoint(-1.0F, 3.0F, 0.0F);
		rightEar.setTextureSize(64, 32);
		rightEar.mirror = true;
		leftArm = new ModelRenderer(this, 0, 18);
		leftArm.addBox(0.0F, -1.0F, -2.0F, 4, 10, 4);
		leftArm.setRotationPoint(4.0F, 4.0F, 1.0F);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0F, 0.0F, 0.0F);
		Staff = new ModelRenderer(this, 60, 0);
		Staff.addBox(-2.0F, -2.0F, -5.0F, 1, 12, 1);
		Staff.setRotationPoint(-4.5F, 4.0F, 1.0F);
		Staff.setTextureSize(64, 32);
		Staff.mirror = true;
		setRotation(Staff, 0.3490659F, 0.0F, 0.0F);
		rightArm = new ModelRenderer(this, 0, 18);
		rightArm.addBox(-4.0F, -1.0F, -2.0F, 4, 10, 4);
		rightArm.setRotationPoint(-4.0F, 4.0F, 1.0F);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0F, 0.0F, 0.0F);
		leftEar2 = new ModelRenderer(this, 44, 0);
		leftEar2.addBox(-8.0F, -13.0F, 2.0F, 4, 6, 1, 0.0F);
		leftEar2.setRotationPoint(1.0F, 3.0F, 0.0F);
		leftEar2.setTextureSize(64, 32);
		leftEar2.mirror = true;
		setRotation(leftEar2, 0.0F, 0.0F, 0.8922867F);
		rightEar2 = new ModelRenderer(this, 33, 0);
		rightEar2.addBox(4.0F, -13.0F, 2.0F, 4, 6, 1, 0.0F);
		rightEar2.setRotationPoint(-1.0F, 3.0F, 0.0F);
		rightEar2.setTextureSize(64, 32);
		rightEar2.mirror = true;
		setRotation(rightEar2, 0.0F, 0.0F, -0.8922821F);
		leftEar2.addChild(this.leftEar);
		head.addChild(this.nose);
		head.addChild(this.leftEar2);
		rightEar2.addChild(this.rightEar);
		head.addChild(this.rightEar2);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Staff.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);

		rightArm.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm.rotateAngleZ = 0.0F;

		Staff.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F + 0.43633F);
		Staff.rotateAngleZ = 0.0F;

		leftArm.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm.rotateAngleZ = 0.0F;

		rightLeg.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		rightLeg.rotateAngleY = 0.0F;

		leftLeg.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);
	}
}

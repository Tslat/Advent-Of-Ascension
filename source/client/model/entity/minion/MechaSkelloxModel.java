package net.tslat.aoa3.client.model.entity.minion;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class MechaSkelloxModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body1;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg1;
	private final ModelRenderer rightLeg2;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer body2;
	private final ModelRenderer leftArm2;
	private final ModelRenderer rightArm2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer leftLeg2p2;
	private final ModelRenderer leftLeg1p2;
	private final ModelRenderer rightLegp2;
	private final ModelRenderer rightLeg2p2;

	public MechaSkelloxModel() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, -6.0f, -5.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 68, 0)).addBox(-4.0f, 0.0f, 3.0f, 10, 8, 18);
		body1.setRotationPoint(-1.0f, 4.0f, -10.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 58, 20)).addBox(0.0f, -2.0f, -1.0f, 2, 2, 2);
		rightLeg.setRotationPoint(-5.0f, 14.0f, 8.0f);
		rightLeg.setTextureSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg1 = new ModelRenderer(this, 58, 20)).addBox(-2.0f, -2.0f, -1.0f, 2, 2, 2);
		leftLeg1.setRotationPoint(5.0f, 14.0f, 8.0f);
		leftLeg1.setTextureSize(128, 32);
		leftLeg1.mirror = true;
		setRotation(leftLeg1, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 58, 20)).addBox(0.0f, -2.0f, -1.0f, 2, 2, 2);
		rightLeg2.setRotationPoint(-5.0f, 14.0f, -3.0f);
		rightLeg2.setTextureSize(128, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 58, 20)).addBox(-2.0f, -2.0f, -1.0f, 2, 2, 2);
		leftLeg2.setRotationPoint(5.0f, 14.0f, -3.0f);
		leftLeg2.setTextureSize(128, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 47, 19)).addBox(5.0f, 0.0f, -1.0f, 2, 2, 2);
		body2.setRotationPoint(-6.0f, -6.0f, -5.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 52, 0)).addBox(-1.0f, -2.0f, -2.0f, 4, 14, 4);
		leftArm2.setRotationPoint(8.0f, -2.0f, -5.0f);
		leftArm2.setTextureSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 52, 0)).addBox(-3.0f, -2.0f, -2.0f, 4, 14, 4);
		rightArm2.setRotationPoint(-8.0f, -2.0f, -5.0f);
		rightArm2.setTextureSize(128, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 19, 18)).addBox(-4.0f, 0.0f, -2.0f, 8, 8, 4);
		body3.setRotationPoint(0.0f, -4.0f, -5.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 47, 19)).addBox(-7.0f, 0.0f, -1.0f, 3, 2, 2);
		body4.setRotationPoint(0.0f, -4.0f, -5.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 47, 19)).addBox(4.0f, 0.0f, -1.0f, 3, 2, 2);
		body5.setRotationPoint(0.0f, -4.0f, -5.0f);
		body5.setTextureSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(leftLeg2p2 = new ModelRenderer(this, 0, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		leftLeg2p2.setRotationPoint(5.0f, 14.0f, -3.0f);
		leftLeg2p2.setTextureSize(128, 32);
		leftLeg2p2.mirror = true;
		setRotation(leftLeg2p2, 0.0f, 0.0f, 0.0f);
		(leftLeg1p2 = new ModelRenderer(this, 0, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		leftLeg1p2.setRotationPoint(5.0f, 14.0f, 8.0f);
		leftLeg1p2.setTextureSize(128, 32);
		leftLeg1p2.mirror = true;
		setRotation(leftLeg1p2, 0.0f, 0.0f, 0.0f);
		(rightLegp2 = new ModelRenderer(this, 0, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		rightLegp2.setRotationPoint(-5.0f, 14.0f, 8.0f);
		rightLegp2.setTextureSize(128, 32);
		rightLegp2.mirror = true;
		setRotation(rightLegp2, 0.0f, 0.0f, 0.0f);
		(rightLeg2p2 = new ModelRenderer(this, 0, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		rightLeg2p2.setRotationPoint(-5.0f, 14.0f, -3.0f);
		rightLeg2p2.setTextureSize(128, 32);
		rightLeg2p2.mirror = true;
		setRotation(rightLeg2p2, 0.0f, 0.0f, 0.0f);
	}

	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg1p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLegp2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		leftLeg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftLeg1.rotateAngleY = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg1p2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftLeg1p2.rotateAngleY = 0.0f;
		rightLegp2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLegp2.rotateAngleY = 0.0f;
		leftLeg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		rightLeg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg2p2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		rightLeg2p2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

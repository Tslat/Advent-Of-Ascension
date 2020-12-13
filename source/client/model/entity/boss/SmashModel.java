package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class SmashModel extends EntityModel<MobEntity> {
	private final ModelRenderer headbrace;
	private final ModelRenderer bodylower;
	private final ModelRenderer rightArm1;
	private final ModelRenderer leftArm10;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm1;
	private final ModelRenderer leftArm2;
	private final ModelRenderer leftArm3;
	private final ModelRenderer leftArm4;
	private final ModelRenderer leftArm5;
	private final ModelRenderer leftArm6;
	private final ModelRenderer leftArm7;
	private final ModelRenderer leftArm8;
	private final ModelRenderer leftArm9;

	public SmashModel() {
		textureWidth = 128;
		textureHeight = 32;
		(headbrace = new ModelRenderer(this, 35, 1)).addBox(-5.0f, -1.0f, -5.0f, 10, 2, 10);
		headbrace.setRotationPoint(0.0f, -9.0f, 0.0f);
		headbrace.setTextureSize(128, 32);
		headbrace.mirror = true;
		setRotation(headbrace, 0.0f, 0.0f, 0.0f);
		(bodylower = new ModelRenderer(this, 100, 1)).addBox(-4.0f, 0.0f, -2.0f, 8, 8, 4);
		bodylower.setRotationPoint(0.0f, 4.0f, 0.0f);
		bodylower.setTextureSize(128, 32);
		bodylower.mirror = true;
		setRotation(bodylower, 0.0f, 0.0f, 0.0f);
		(rightArm1 = new ModelRenderer(this, 71, 21)).addBox(-4.0f, -1.0f, -3.0f, 7, 5, 6);
		rightArm1.setRotationPoint(-7.0f, -4.0f, 0.0f);
		rightArm1.setTextureSize(128, 32);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0.0f, 0.0f, 0.0f);
		(leftArm10 = new ModelRenderer(this, 71, 21)).addBox(-3.0f, -1.0f, -3.0f, 7, 5, 6);
		leftArm10.setRotationPoint(7.0f, -4.0f, 0.0f);
		leftArm10.setTextureSize(128, 32);
		leftArm10.mirror = true;
		setRotation(leftArm10, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 51, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setRotationPoint(-3.0f, 12.0f, 0.0f);
		rightLeg.setTextureSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 51, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setRotationPoint(3.0f, 12.0f, 0.0f);
		leftLeg.setTextureSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, -9.0f, 0.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 99, 14)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 6);
		body.setRotationPoint(0.0f, -8.0f, -1.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 81, 2)).addBox(-3.0f, 4.0f, -2.0f, 4, 12, 4);
		rightArm.setRotationPoint(-7.0f, -4.0f, 0.0f);
		rightArm.setTextureSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm1 = new ModelRenderer(this, 4, 21)).addBox(-3.0f, 9.0f, -16.0f, 8, 8, 2);
		leftArm1.setRotationPoint(7.0f, -4.0f, 0.0f);
		leftArm1.setTextureSize(128, 32);
		leftArm1.mirror = true;
		setRotation(leftArm1, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 81, 2)).addBox(-1.0f, 4.0f, -2.0f, 4, 12, 4);
		leftArm2.setRotationPoint(7.0f, -4.0f, 0.0f);
		leftArm2.setTextureSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new ModelRenderer(this, 39, 17)).addBox(0.0f, 12.0f, -4.0f, 2, 2, 2);
		leftArm3.setRotationPoint(7.0f, -4.0f, 0.0f);
		leftArm3.setTextureSize(128, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(leftArm4 = new ModelRenderer(this, 36, 22)).addBox(-1.0f, 11.0f, -18.0f, 4, 4, 2);
		leftArm4.setRotationPoint(7.0f, -4.0f, 0.0f);
		leftArm4.setTextureSize(128, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(leftArm5 = new ModelRenderer(this, 4, 21)).addBox(-3.0f, 9.0f, -8.0f, 8, 8, 2);
		leftArm5.setRotationPoint(7.0f, -4.0f, 0.0f);
		leftArm5.setTextureSize(128, 32);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, 0.0f);
		(leftArm6 = new ModelRenderer(this, 4, 21)).addBox(-3.0f, 9.0f, -12.0f, 8, 8, 2);
		leftArm6.setRotationPoint(7.0f, -4.0f, 0.0f);
		leftArm6.setTextureSize(128, 32);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, 0.0f);
		(leftArm7 = new ModelRenderer(this, 36, 22)).addBox(-1.0f, 11.0f, -6.0f, 4, 4, 2);
		leftArm7.setRotationPoint(7.0f, -4.0f, 0.0f);
		leftArm7.setTextureSize(128, 32);
		leftArm7.mirror = true;
		setRotation(leftArm7, 0.0f, 0.0f, 0.0f);
		(leftArm8 = new ModelRenderer(this, 36, 22)).addBox(-1.0f, 11.0f, -10.0f, 4, 4, 2);
		leftArm8.setRotationPoint(7.0f, -4.0f, 0.0f);
		leftArm8.setTextureSize(128, 32);
		leftArm8.mirror = true;
		setRotation(leftArm8, 0.0f, 0.0f, 0.0f);
		(leftArm9 = new ModelRenderer(this, 36, 22)).addBox(-1.0f, 11.0f, -14.0f, 4, 4, 2);
		leftArm9.setRotationPoint(7.0f, -4.0f, 0.0f);
		leftArm9.setTextureSize(128, 32);
		leftArm9.mirror = true;
		setRotation(leftArm9, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		headbrace.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		bodylower.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 57.295776f;
		head.rotateAngleX = headPitch / 54.11268f;
		headbrace.rotateAngleY = netHeadYaw / 57.295776f;
		headbrace.rotateAngleX = headPitch / 54.11268f;
		rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		rightArm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm1.rotateAngleZ = 0.0f;
		leftArm1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm1.rotateAngleZ = 0.0f;
		leftArm2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm2.rotateAngleZ = 0.0f;
		leftArm3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm3.rotateAngleZ = 0.0f;
		leftArm4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm4.rotateAngleZ = 0.0f;
		leftArm5.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm5.rotateAngleZ = 0.0f;
		leftArm6.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm6.rotateAngleZ = 0.0f;
		leftArm7.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm7.rotateAngleZ = 0.0f;
		leftArm8.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm8.rotateAngleZ = 0.0f;
		leftArm9.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm9.rotateAngleZ = 0.0f;
		leftArm10.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm10.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

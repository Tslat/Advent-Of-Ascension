package net.tslat.aoa3.client.model.entity.mob.lborean;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class NeptunoModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer rightArm2;
	private final ModelRenderer rightArm3;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer head6;

	public NeptunoModel() {
		textureWidth = 128;
		textureHeight = 64;
		(head = new ModelRenderer(this, 81, 7)).addBox(4.0f, -10.0f, 2.0f, 2, 3, 11);
		head.setRotationPoint(0.0f, -11.0f, -5.0f);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, 0.1745329f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 25, 0)).addBox(-4.0f, 0.0f, -2.0f, 10, 6, 7);
		body.setRotationPoint(-1.0f, 9.0f, 0.0f);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 98, 39)).addBox(-4.0f, 13.0f, -27.0f, 6, 12, 6);
		rightArm.setRotationPoint(-10.0f, -10.0f, -3.0f);
		rightArm.setTextureSize(128, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.3490659f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 34)).addBox(-2.0f, -2.0f, -3.0f, 6, 24, 6);
		leftArm.setRotationPoint(10.0f, -10.0f, -3.0f);
		leftArm.setTextureSize(128, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 0)).addBox(-3.0f, 0.0f, -3.0f, 6, 15, 6);
		rightLeg.setRotationPoint(-8.0f, 9.0f, 2.0f);
		rightLeg.setTextureSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 0)).addBox(-3.0f, 0.0f, -3.0f, 6, 15, 6);
		leftLeg.setRotationPoint(8.0f, 9.0f, 2.0f);
		leftLeg.setTextureSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0174533f);
		(body2 = new ModelRenderer(this, 61, 2)).addBox(-5.0f, 0.0f, -2.0f, 16, 12, 7);
		body2.setRotationPoint(-3.0f, -15.0f, -2.0f);
		body2.setTextureSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 0, 23)).addBox(-4.0f, 0.0f, -2.0f, 12, 12, 7);
		body3.setRotationPoint(-2.0f, -3.0f, -1.0f);
		body3.setTextureSize(128, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 34)).addBox(-4.0f, -2.0f, -3.0f, 6, 24, 6);
		rightArm2.setRotationPoint(-10.0f, -10.0f, -3.0f);
		rightArm2.setTextureSize(128, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 66, 39)).addBox(-3.0f, 16.0f, -21.0f, 4, 4, 19);
		rightArm3.setRotationPoint(-10.0f, -10.0f, -3.0f);
		rightArm3.setTextureSize(128, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.3490659f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 43)).addBox(-4.0f, -13.0f, -4.0f, 8, 13, 8);
		head2.setRotationPoint(0.0f, -11.0f, -5.0f);
		head2.setTextureSize(128, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 81, 7)).addBox(-6.0f, -10.0f, 2.0f, 2, 3, 11);
		head3.setRotationPoint(0.0f, -11.0f, -5.0f);
		head3.setTextureSize(128, 64);
		head3.mirror = true;
		setRotation(head3, 0.1745329f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 81, 7)).addBox(-1.0f, -11.0f, 7.0f, 2, 3, 11);
		head4.setRotationPoint(0.0f, -11.0f, -5.0f);
		head4.setTextureSize(128, 64);
		head4.mirror = true;
		setRotation(head4, 0.7853982f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 81, 7)).addBox(-6.0f, -12.0f, 2.0f, 2, 3, 11);
		head5.setRotationPoint(0.0f, -11.0f, -5.0f);
		head5.setTextureSize(128, 64);
		head5.mirror = true;
		setRotation(head5, 0.5235988f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 81, 7)).addBox(4.0f, -12.0f, 2.0f, 2, 3, 11);
		head6.setRotationPoint(0.0f, -11.0f, -5.0f);
		head6.setTextureSize(128, 64);
		head6.mirror = true;
		setRotation(head6, 0.5235988f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head2.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head3.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head4.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head5.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head6.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.349f;
		rightArm.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		rightArm3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.349f;
		rightArm3.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

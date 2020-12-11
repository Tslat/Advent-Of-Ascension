package net.tslat.aoa3.client.model.entity.npc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class TrollTraderModel extends EntityModel<MobEntity> {
	private ModelRenderer body;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer head;
	private ModelRenderer nose;
	private ModelRenderer leftArm;
	private ModelRenderer rightArm;
	private ModelRenderer leftEar1;
	private ModelRenderer leftEar2;

	public TrollTraderModel() {
		textureWidth = 64;
		textureHeight = 32;
		(body = new ModelRenderer(this, 36, 15)).addBox(-4.0f, 0.0f, -3.0f, 8, 11, 6);
		body.setRotationPoint(0.0f, 3.0f, 1.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 18, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		rightLeg.setRotationPoint(-2.0f, 14.0f, 1.0f);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 18, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		leftLeg.setRotationPoint(2.0f, 14.0f, 1.0f);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 3.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(nose = new ModelRenderer(this, 33, 8)).addBox(-1.0f, -4.0f, -6.0f, 2, 5, 2);
		nose.setRotationPoint(0.0f, 3.0f, 0.0f);
		nose.setTextureSize(64, 32);
		nose.mirror = true;
		setRotation(nose, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 0, 18)).addBox(0.0f, -1.0f, -2.0f, 4, 10, 4);
		leftArm.setRotationPoint(4.0f, 4.0f, 1.0f);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 0, 18)).addBox(-4.0f, -1.0f, -2.0f, 4, 10, 4);
		rightArm.setRotationPoint(-4.0f, 4.0f, 1.0f);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftEar1 = new ModelRenderer(this, 45, 2)).addBox(-1.0f, -11.0f, -3.0f, 2, 1, 6);
		leftEar1.setRotationPoint(0.0f, 3.0f, 0.0f);
		leftEar1.setTextureSize(64, 32);
		leftEar1.mirror = true;
		setRotation(leftEar1, 0.0f, 0.0f, 0.0f);
		(leftEar2 = new ModelRenderer(this, 43, 0)).addBox(-1.0f, -10.0f, -4.0f, 2, 2, 8);
		leftEar2.setRotationPoint(0.0f, 3.0f, 0.0f);
		leftEar2.setTextureSize(64, 32);
		leftEar2.mirror = true;
		setRotation(leftEar2, 0.0f, 0.0f, 0.0f);
	}

	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		nose.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftEar1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftEar2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 57.295776f;
		head.rotateAngleX = headPitch / 54.11268f;
		nose.rotateAngleY = netHeadYaw / 57.295776f;
		nose.rotateAngleX = headPitch / 54.11268f;
		leftEar1.rotateAngleY = netHeadYaw / 57.295776f;
		leftEar1.rotateAngleX = headPitch / 54.11268f;
		leftEar2.rotateAngleY = netHeadYaw / 57.295776f;
		leftEar2.rotateAngleX = headPitch / 54.11268f;
		rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

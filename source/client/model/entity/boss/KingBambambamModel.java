package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class KingBambambamModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer staff1;
	private final ModelRenderer staff2;
	private final ModelRenderer staff3;

	public KingBambambamModel() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -10.0f, -5.0f, 10, 10, 10);
		head.setRotationPoint(0.0f, 2.0f, 0.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 72, 0)).addBox(-9.0f, 0.0f, -5.0f, 18, 16, 10);
		body.setRotationPoint(0.0f, 2.0f, 0.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 13)).addBox(-5.0f, -2.0f, -3.0f, 6, 12, 6);
		rightArm.setRotationPoint(-10.0f, 4.0f, 0.0f);
		rightArm.setTextureSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 13)).addBox(1.0f, -2.0f, -3.0f, 6, 12, 6);
		leftArm.setRotationPoint(8.0f, 4.0f, 0.0f);
		leftArm.setTextureSize(128, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 47, 1)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		rightLeg.setRotationPoint(-5.0f, 18.0f, 0.0f);
		rightLeg.setTextureSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 47, 1)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		leftLeg.setRotationPoint(5.0f, 18.0f, 0.0f);
		leftLeg.setTextureSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(staff3 = new ModelRenderer(this, 98, 11)).addBox(-4.0f, 4.0f, -17.0f, 4, 4, 3);
		staff3.setRotationPoint(-10.0f, 4.0f, 0.0f);
		staff3.setTextureSize(128, 32);
		staff3.mirror = true;
		setRotation(staff3, -0.7853982f, 0.0f, 0.0f);
		(staff1 = new ModelRenderer(this, 98, 11)).addBox(-3.0f, 5.0f, -3.0f, 2, 2, 12);
		staff1.setRotationPoint(-10.0f, 4.0f, 0.0f);
		staff1.setTextureSize(128, 32);
		staff1.mirror = true;
		setRotation(staff1, -0.7853982f, 0.0f, 0.0f);
		(staff2 = new ModelRenderer(this, 98, 11)).addBox(-3.0f, 5.0f, -15.0f, 2, 2, 12);
		staff2.setRotationPoint(-10.0f, 4.0f, 0.0f);
		staff2.setTextureSize(128, 32);
		staff2.mirror = true;
		setRotation(staff2, -0.7853982f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		staff1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		staff2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		staff3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		staff1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f - 0.43633f;
		staff1.rotateAngleZ = 0.0f;
		staff2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f - 0.43633f;
		staff2.rotateAngleZ = 0.0f;
		staff3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f - 0.43633f;
		staff3.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

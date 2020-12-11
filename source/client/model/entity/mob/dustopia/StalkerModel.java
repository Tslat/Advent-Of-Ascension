package net.tslat.aoa3.client.model.entity.mob.dustopia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class StalkerModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer body2;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftArm2;
	private final ModelRenderer leftArm3;
	private final ModelRenderer leftArm4;
	private final ModelRenderer leftArm5;
	private final ModelRenderer leftArm6;
	private final ModelRenderer body3;

	public StalkerModel() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head.setRotationPoint(0.0f, -11.0f, -1.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 25, 12)).addBox(-1.0f, -2.0f, -1.0f, 2, 2, 2);
		body.setRotationPoint(0.0f, -9.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 21, 4);
		rightLeg.setRotationPoint(-3.0f, 3.0f, 0.0f);
		rightLeg.setTextureSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 21, 4);
		leftLeg.setRotationPoint(3.0f, 3.0f, 0.0f);
		leftLeg.setTextureSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 36)).addBox(-4.0f, 0.0f, -2.0f, 6, 7, 3);
		body2.setRotationPoint(1.0f, -8.0f, 4.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm.setRotationPoint(-4.0f, -1.0f, 4.0f);
		leftArm.setTextureSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 1.047198f);
		(leftArm2 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm2.setRotationPoint(-12.0f, -4.0f, 4.0f);
		leftArm2.setTextureSize(64, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, -1.570796f);
		(leftArm3 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm3.setRotationPoint(-4.0f, -7.0f, 4.0f);
		leftArm3.setTextureSize(64, 64);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 2.094395f);
		(leftArm4 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm4.setRotationPoint(4.0f, -4.0f, 4.0f);
		leftArm4.setTextureSize(64, 64);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, -1.570796f);
		(leftArm5 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm5.setRotationPoint(4.0f, -1.0f, 4.0f);
		leftArm5.setTextureSize(64, 64);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, -1.047198f);
		(leftArm6 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm6.setRotationPoint(4.0f, -7.0f, 4.0f);
		leftArm6.setTextureSize(64, 64);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, -2.094395f);
		(body3 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body3.setRotationPoint(0.0f, -9.0f, 0.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

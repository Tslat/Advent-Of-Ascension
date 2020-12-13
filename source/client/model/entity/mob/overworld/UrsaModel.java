package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class UrsaModel extends EntityModel<MobEntity> {
	private final ModelRenderer Torso;
	private final ModelRenderer body;
	private final ModelRenderer Left_Back_Quad;
	private final ModelRenderer Right_Back_Quad;
	private final ModelRenderer Left_Back_Leg;
	private final ModelRenderer Right_Back_Leg;
	private final ModelRenderer Left_Front_Quad;
	private final ModelRenderer Right_Front_Quad;
	private final ModelRenderer Left_Front_Leg;
	private final ModelRenderer Right_Front_Leg;
	private final ModelRenderer tail;
	private final ModelRenderer head;
	private final ModelRenderer Mouth;
	private final ModelRenderer leftEar;
	private final ModelRenderer rightEar;

	public UrsaModel() {
		textureWidth = 128;
		textureHeight = 64;
		(Torso = new ModelRenderer(this, 0, 0)).addBox(-6.0f, -6.0f, -13.0f, 12, 12, 12);
		Torso.setRotationPoint(0.0f, 6.0f, 1.0f);
		Torso.setTextureSize(64, 32);
		Torso.mirror = true;
		setRotation(Torso, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 49, 0)).addBox(-5.0f, -5.0f, 0.0f, 10, 10, 10);
		body.setRotationPoint(0.0f, 7.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(Left_Back_Quad = new ModelRenderer(this, 49, 20)).addBox(0.0f, -3.0f, -3.0f, 6, 6, 6);
		Left_Back_Quad.setRotationPoint(4.0f, 8.0f, 5.0f);
		Left_Back_Quad.setTextureSize(64, 32);
		Left_Back_Quad.mirror = true;
		setRotation(Left_Back_Quad, 0.0f, 0.0f, 0.0f);
		(Right_Back_Quad = new ModelRenderer(this, 49, 20)).addBox(-6.0f, -3.0f, -3.0f, 6, 6, 6);
		Right_Back_Quad.setRotationPoint(-4.0f, 8.0f, 5.0f);
		Right_Back_Quad.setTextureSize(64, 32);
		Right_Back_Quad.mirror = true;
		setRotation(Right_Back_Quad, 0.0f, 0.0f, 0.0f);
		(Left_Back_Leg = new ModelRenderer(this, 111, 17)).addBox(0.0f, 3.0f, -2.0f, 4, 14, 4);
		Left_Back_Leg.setRotationPoint(5.0f, 7.0f, 5.0f);
		Left_Back_Leg.setTextureSize(64, 32);
		Left_Back_Leg.mirror = true;
		setRotation(Left_Back_Leg, 0.0f, 0.0f, 0.0f);
		(Right_Back_Leg = new ModelRenderer(this, 111, 17)).addBox(-4.0f, 3.0f, -2.0f, 4, 14, 4);
		Right_Back_Leg.setRotationPoint(-5.0f, 7.0f, 4.066667f);
		Right_Back_Leg.setTextureSize(64, 32);
		Right_Back_Leg.mirror = true;
		setRotation(Right_Back_Leg, 0.0f, 0.0f, 0.0f);
		(Left_Front_Quad = new ModelRenderer(this, 90, 0)).addBox(0.0f, -4.0f, -4.0f, 6, 8, 8);
		Left_Front_Quad.setRotationPoint(5.0f, 7.0f, -7.0f);
		Left_Front_Quad.setTextureSize(64, 32);
		Left_Front_Quad.mirror = true;
		setRotation(Left_Front_Quad, 0.0f, 0.0f, 0.0f);
		(Right_Front_Quad = new ModelRenderer(this, 90, 0)).addBox(-6.0f, -4.0f, -4.0f, 6, 8, 8);
		Right_Front_Quad.setRotationPoint(-5.0f, 7.0f, -7.0f);
		Right_Front_Quad.setTextureSize(64, 32);
		Right_Front_Quad.mirror = true;
		setRotation(Right_Front_Quad, 0.0f, 0.0f, 0.0f);
		(Left_Front_Leg = new ModelRenderer(this, 0, 25)).addBox(0.0f, 3.0f, -2.0f, 4, 14, 4);
		Left_Front_Leg.setRotationPoint(6.0f, 8.0f, -7.0f);
		Left_Front_Leg.setTextureSize(64, 32);
		Left_Front_Leg.mirror = true;
		setRotation(Left_Front_Leg, 0.0f, 0.0f, 0.0f);
		(Right_Front_Leg = new ModelRenderer(this, 0, 25)).addBox(-4.0f, 3.0f, -2.0f, 4, 14, 4);
		Right_Front_Leg.setRotationPoint(-6.0f, 7.0f, -7.0f);
		Right_Front_Leg.setTextureSize(64, 32);
		Right_Front_Leg.mirror = true;
		setRotation(Right_Front_Leg, 0.0f, 0.0f, 0.0f);
		(tail = new ModelRenderer(this, 49, 33)).addBox(-2.0f, -2.0f, 0.0f, 4, 4, 13);
		tail.setRotationPoint(0.0f, 4.0f, 8.0f);
		tail.setTextureSize(64, 32);
		tail.mirror = true;
		setRotation(tail, -0.7435722f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 17, 25)).addBox(-4.0f, -4.0f, -6.0f, 8, 9, 7);
		head.setRotationPoint(0.0f, 5.0f, -13.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(Mouth = new ModelRenderer(this, 0, 44)).addBox(-3.0f, -2.0f, -5.0f, 6, 4, 4);
		Mouth.setRotationPoint(0.0f, 7.0f, -18.0f);
		Mouth.setTextureSize(64, 32);
		Mouth.mirror = true;
		setRotation(Mouth, 0.0f, 0.0f, 0.0f);
		(leftEar = new ModelRenderer(this, 21, 44)).addBox(-1.0f, -3.0f, -1.0f, 3, 3, 2);
		leftEar.setRotationPoint(3.0f, 2.0f, -14.0f);
		leftEar.setTextureSize(64, 32);
		leftEar.mirror = true;
		setRotation(leftEar, 0.0f, 0.0f, 0.0f);
		(rightEar = new ModelRenderer(this, 21, 44)).addBox(-2.0f, -3.0f, -1.0f, 3, 3, 2);
		rightEar.setRotationPoint(-3.0f, 2.0f, -14.0f);
		rightEar.setTextureSize(64, 32);
		rightEar.mirror = true;
		setRotation(rightEar, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Left_Front_Leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Left_Front_Leg.rotateAngleY = 0.0f;
		Left_Front_Quad.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Left_Front_Quad.rotateAngleY = 0.0f;
		Left_Back_Leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Left_Back_Leg.rotateAngleY = 0.0f;
		Left_Back_Quad.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Left_Back_Quad.rotateAngleY = 0.0f;
		Right_Front_Leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Right_Front_Leg.rotateAngleY = 0.0f;
		Right_Front_Quad.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Right_Front_Quad.rotateAngleY = 0.0f;
		Right_Back_Leg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Right_Back_Leg.rotateAngleY = 0.0f;
		Right_Back_Quad.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Right_Back_Quad.rotateAngleY = 0.0f;
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		Torso.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Left_Back_Quad.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Right_Back_Quad.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Left_Back_Leg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Right_Back_Leg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Left_Front_Quad.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Right_Front_Quad.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Left_Front_Leg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Right_Front_Leg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		tail.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Mouth.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftEar.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightEar.render(matrix, buffer, light, overlay, red, green, blue, alpha);

	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

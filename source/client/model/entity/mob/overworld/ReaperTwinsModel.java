package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class ReaperTwinsModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer body2;
	private final ModelRenderer rightArm2;
	private final ModelRenderer rightArm3;
	private final ModelRenderer rightArm4;
	private final ModelRenderer rightArm5;
	private final ModelRenderer rightArm6;
	private final ModelRenderer rightArm7;
	private final ModelRenderer rightArm8;
	private final ModelRenderer head2;
	private final ModelRenderer rightArm3x2;
	private final ModelRenderer rightArm4x2;
	private final ModelRenderer rightArm5x2;
	private final ModelRenderer rightArmx2;
	private final ModelRenderer rightArm6x2;
	private final ModelRenderer rightArm7x2;
	private final ModelRenderer rightArm8x2;

	public ReaperTwinsModel() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(5.0f, -6.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 38)).addBox(-5.0f, 0.0f, -2.0f, 10, 13, 4);
		body.setRotationPoint(0.0f, 10.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 55, 2)).addBox(1.0f, -8.0f, -16.0f, 2, 3, 1);
		rightArm.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightArm.setTextureSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.8726646f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 37, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 16, 4);
		leftArm.setRotationPoint(5.0f, -4.0f, 0.0f);
		leftArm.setTextureSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 12, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 16, 4);
		body2.setRotationPoint(0.0f, -6.0f, 0.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 37, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 16, 4);
		rightArm2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm2.setTextureSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 55, 16)).addBox(1.0f, -17.0f, -12.0f, 2, 36, 2);
		rightArm3.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightArm3.setTextureSize(64, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.8726646f, 0.0f, 0.0f);
		(rightArm4 = new ModelRenderer(this, 55, 2)).addBox(1.0f, -15.0f, -14.0f, 2, 3, 2);
		rightArm4.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightArm4.setTextureSize(64, 64);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.8726646f, 0.0f, 0.0f);
		(rightArm5 = new ModelRenderer(this, 55, 2)).addBox(1.0f, -14.0f, -16.0f, 2, 3, 2);
		rightArm5.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightArm5.setTextureSize(64, 64);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.8726646f, 0.0f, 0.0f);
		(rightArm6 = new ModelRenderer(this, 55, 2)).addBox(1.0f, -13.0f, -18.0f, 2, 3, 2);
		rightArm6.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightArm6.setTextureSize(64, 64);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.8726646f, 0.0f, 0.0f);
		(rightArm7 = new ModelRenderer(this, 55, 2)).addBox(1.0f, -11.0f, -19.0f, 2, 4, 1);
		rightArm7.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightArm7.setTextureSize(64, 64);
		rightArm7.mirror = true;
		setRotation(rightArm7, 0.8726646f, 0.0f, 0.0f);
		(rightArm8 = new ModelRenderer(this, 55, 2)).addBox(1.0f, -10.0f, -18.0f, 2, 4, 2);
		rightArm8.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightArm8.setTextureSize(64, 64);
		rightArm8.mirror = true;
		setRotation(rightArm8, 0.8726646f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setRotationPoint(-6.0f, -6.0f, 0.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(rightArm3x2 = new ModelRenderer(this, 55, 16)).addBox(-2.0f, -17.0f, -12.0f, 2, 36, 2);
		rightArm3x2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm3x2.setTextureSize(64, 64);
		rightArm3x2.mirror = true;
		setRotation(rightArm3x2, 0.8726646f, 0.0f, 0.0f);
		(rightArm4x2 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -15.0f, -14.0f, 2, 3, 2);
		rightArm4x2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm4x2.setTextureSize(64, 64);
		rightArm4x2.mirror = true;
		setRotation(rightArm4x2, 0.8726646f, 0.0f, 0.0f);
		(rightArm5x2 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -14.0f, -16.0f, 2, 3, 2);
		rightArm5x2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm5x2.setTextureSize(64, 64);
		rightArm5x2.mirror = true;
		setRotation(rightArm5x2, 0.8726646f, 0.0f, 0.0f);
		(rightArmx2 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -8.0f, -16.0f, 2, 3, 1);
		rightArmx2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArmx2.setTextureSize(64, 64);
		rightArmx2.mirror = true;
		setRotation(rightArmx2, 0.8726646f, 0.0f, 0.0f);
		(rightArm6x2 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -13.0f, -18.0f, 2, 3, 2);
		rightArm6x2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm6x2.setTextureSize(64, 64);
		rightArm6x2.mirror = true;
		setRotation(rightArm6x2, 0.8726646f, 0.0f, 0.0f);
		(rightArm7x2 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -11.0f, -19.0f, 2, 4, 1);
		rightArm7x2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm7x2.setTextureSize(64, 64);
		rightArm7x2.mirror = true;
		setRotation(rightArm7x2, 0.8726646f, 0.0f, 0.0f);
		(rightArm8x2 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -10.0f, -18.0f, 2, 4, 2);
		rightArm8x2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm8x2.setTextureSize(64, 64);
		rightArm8x2.mirror = true;
		setRotation(rightArm8x2, 0.8726646f, 0.0f, 0.0f);
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3x2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4x2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm5x2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArmx2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm6x2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm7x2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm8x2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

package net.tslat.aoa3.client.model.entity.mob.barathos;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class EmperorBeastModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer rightLeg2;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer head2;
	private final ModelRenderer head3;

	public EmperorBeastModel() {
		textureWidth = 128;
		textureHeight = 64;
		(head = new ModelRenderer(this, 41, 18)).addBox(-10.0f, -21.0f, 0.0f, 20, 3, 8);
		head.setRotationPoint(0.0f, -11.0f, 0.0f);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, -0.1745329f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 90, 46)).addBox(-3.0f, 17.0f, 15.0f, 6, 6, 12);
		body.setRotationPoint(0.0f, -11.0f, -3.0f);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 83, 2)).addBox(-5.0f, 13.0f, -11.0f, 10, 4, 12);
		rightLeg.setRotationPoint(-7.0f, 7.0f, 6.0f);
		rightLeg.setTextureSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 83, 2)).addBox(-5.0f, 13.0f, -11.0f, 10, 4, 12);
		leftLeg.setRotationPoint(7.0f, 7.0f, 6.0f);
		leftLeg.setTextureSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 100, 22)).addBox(-3.0f, 0.0f, -4.0f, 6, 15, 8);
		leftLeg2.setRotationPoint(7.0f, 7.0f, 6.0f);
		leftLeg2.setTextureSize(128, 64);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, -0.3490659f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 100, 22)).addBox(-3.0f, 0.0f, -4.0f, 6, 15, 8);
		rightLeg2.setRotationPoint(-7.0f, 7.0f, 6.0f);
		rightLeg2.setTextureSize(128, 64);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, -0.3490659f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 24, 29)).addBox(-4.0f, 0.0f, -2.0f, 8, 23, 12);
		body2.setRotationPoint(0.0f, -11.0f, -3.0f);
		body2.setTextureSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 67, 29)).addBox(-4.0f, 11.0f, 10.0f, 8, 12, 8);
		body3.setRotationPoint(0.0f, -11.0f, -3.0f);
		body3.setTextureSize(128, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 28)).addBox(-2.0f, -20.0f, -4.0f, 4, 8, 6);
		head2.setRotationPoint(0.0f, -11.0f, 0.0f);
		head2.setTextureSize(128, 64);
		head2.mirror = true;
		setRotation(head2, -0.4537856f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -13.0f, -4.0f, 8, 13, 13);
		head3.setRotationPoint(0.0f, -11.0f, 0.0f);
		head3.setTextureSize(128, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		rightLeg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount - 0.349f;
		rightLeg2.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount - 0.349f;
	}
}

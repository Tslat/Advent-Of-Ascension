package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class LivingFungiModel extends EntityModel<MobEntity> {
	private final ModelRenderer p1;
	private final ModelRenderer p2;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer body1;
	private final ModelRenderer head1;

	public LivingFungiModel() {
		textureWidth = 128;
		textureHeight = 32;
		(p1 = new ModelRenderer(this, 64, 0)).addBox(-3.0f, -7.0f, -3.0f, 16, 12, 16);
		p1.setRotationPoint(-5.0f, -8.0f, -5.0f);
		p1.setTextureSize(128, 32);
		p1.mirror = true;
		setRotation(p1, 0.0f, 0.0f, 0.0f);
		(p2 = new ModelRenderer(this, 17, 21)).addBox(-4.0f, 0.0f, -2.0f, 18, 6, 5);
		p2.setRotationPoint(-5.0f, 0.0f, -1.0f);
		p2.setTextureSize(128, 32);
		p2.mirror = true;
		setRotation(p2, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 52, 1)).addBox(-1.0f, -2.0f, -1.0f, 2, 12, 2);
		rightArm.setRotationPoint(-7.0f, 8.0f, 0.0f);
		rightArm.setTextureSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 52, 1)).addBox(-1.0f, -2.0f, -1.0f, 2, 12, 2);
		leftArm.setRotationPoint(7.0f, 8.0f, 0.0f);
		leftArm.setTextureSize(128, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		rightLeg.setRotationPoint(-3.0f, 16.0f, 0.0f);
		rightLeg.setTextureSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leftLeg.setRotationPoint(3.0f, 16.0f, 0.0f);
		leftLeg.setTextureSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 25, 0)).addBox(-4.0f, 0.0f, -2.0f, 8, 10, 4);
		body1.setRotationPoint(0.0f, 6.0f, 0.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(head1 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -7.0f, -3.0f, 6, 5, 6);
		head1.setRotationPoint(0.0f, 3.0f, 2.0f);
		head1.setTextureSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		p1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.mysterium;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFungik extends ModelBase {
	private ModelRenderer head1;
	private ModelRenderer body;
	private ModelRenderer rightArm1;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg2;
	private ModelRenderer leftLeg;
	private ModelRenderer rightLeg2;
	private ModelRenderer head2;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer body4;
	private ModelRenderer body5;
	private ModelRenderer rightArm2;
	private ModelRenderer rightArm3;
	private ModelRenderer rightArm4;

	public ModelFungik() {
		textureWidth = 128;
		textureHeight = 64;
		(head1 = new ModelRenderer(this, 40, 0)).addBox(-8.0f, -12.0f, -8.0f, 16, 6, 16);
		head1.setRotationPoint(0.0f, -4.0f, 0.0f);
		head1.setTextureSize(128, 64);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 71, 25)).addBox(-10.0f, -4.0f, -4.0f, 6, 3, 6);
		body.setRotationPoint(11.0f, 0.0f, 9.0f);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, -1.047198f, 0.0f, 0.0f);
		(rightArm1 = new ModelRenderer(this, 113, 16)).addBox(-3.0f, -10.0f, -11.0f, 4, 3, 4);
		rightArm1.setRotationPoint(-8.0f, -2.0f, 0.0f);
		rightArm1.setTextureSize(128, 64);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0.6108652f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 21, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 16, 4);
		leftArm.setRotationPoint(8.0f, -2.0f, 0.0f);
		leftArm.setTextureSize(128, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 48)).addBox(-3.0f, 6.0f, -3.0f, 6, 10, 6);
		rightLeg.setRotationPoint(-4.0f, 8.0f, 0.0f);
		rightLeg.setTextureSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 0, 48)).addBox(-3.0f, 6.0f, -3.0f, 6, 10, 6);
		leftLeg2.setRotationPoint(4.0f, 8.0f, 0.0f);
		leftLeg2.setTextureSize(128, 64);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 33)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leftLeg.setRotationPoint(4.0f, 8.0f, 0.0f);
		leftLeg.setTextureSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 0, 33)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		rightLeg2.setRotationPoint(-4.0f, 8.0f, 0.0f);
		rightLeg2.setTextureSize(128, 64);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -6.0f, -4.0f, 8, 6, 8);
		head2.setRotationPoint(0.0f, -4.0f, 0.0f);
		head2.setTextureSize(128, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 28, 46)).addBox(-7.0f, 0.0f, -2.0f, 14, 12, 4);
		body2.setRotationPoint(0.0f, -4.0f, 0.0f);
		body2.setTextureSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 71, 36)).addBox(-10.0f, -3.0f, -4.0f, 6, 3, 6);
		body3.setRotationPoint(4.0f, -4.0f, 9.0f);
		body3.setTextureSize(128, 64);
		body3.mirror = true;
		setRotation(body3, -0.6981317f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 59, 23)).addBox(-8.0f, -1.0f, -2.0f, 2, 11, 2);
		body4.setRotationPoint(11.0f, 0.0f, 9.0f);
		body4.setTextureSize(128, 64);
		body4.mirror = true;
		setRotation(body4, -1.047198f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 59, 23)).addBox(-8.0f, 0.0f, -2.0f, 2, 11, 2);
		body5.setRotationPoint(4.0f, -4.0f, 9.0f);
		body5.setTextureSize(128, 64);
		body5.mirror = true;
		setRotation(body5, -0.6981317f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 21, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 16, 4);
		rightArm2.setRotationPoint(-8.0f, -2.0f, 0.0f);
		rightArm2.setTextureSize(128, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 113, 24)).addBox(-2.0f, 1.0f, -10.0f, 2, 14, 2);
		rightArm3.setRotationPoint(-8.0f, -2.0f, 0.0f);
		rightArm3.setTextureSize(128, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.6108652f, 0.0f, 0.0f);
		(rightArm4 = new ModelRenderer(this, 113, 42)).addBox(-2.0f, -7.0f, -10.0f, 2, 8, 2);
		rightArm4.setRotationPoint(-8.0f, -2.0f, 0.0f);
		rightArm4.setTextureSize(128, 64);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.6108652f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head1.render(par7);
		body.render(par7);
		rightArm1.render(par7);
		leftArm.render(par7);
		rightLeg.render(par7);
		leftLeg2.render(par7);
		leftLeg.render(par7);
		rightLeg2.render(par7);
		head2.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		rightArm2.render(par7);
		rightArm3.render(par7);
		rightArm4.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head1.rotateAngleY = par4 / 57.295776f;
		head1.rotateAngleX = par5 / 54.11268f;
		head2.rotateAngleY = par4 / 57.295776f;
		head2.rotateAngleX = par5 / 54.11268f;
		rightArm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.61f;
		rightArm1.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		rightArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.61f;
		rightArm3.rotateAngleZ = 0.0f;
		rightArm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.61f;
		rightArm4.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		rightLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg2.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

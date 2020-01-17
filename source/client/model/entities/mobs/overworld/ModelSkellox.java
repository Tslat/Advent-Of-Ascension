package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSkellox extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body1;
	private ModelRenderer rightArm1;
	private ModelRenderer leftArm1;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg1;
	private ModelRenderer rightLeg2;
	private ModelRenderer leftLeg2;
	private ModelRenderer body2;
	private ModelRenderer leftArm2;
	private ModelRenderer rightArm2;

	public ModelSkellox() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, -4.0f, -5.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 68, 0)).addBox(-4.0f, 0.0f, 3.0f, 10, 8, 18);
		body1.setRotationPoint(-1.0f, 4.0f, -10.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(rightArm1 = new ModelRenderer(this, 49, 24)).addBox(1.0f, -3.0f, -2.0f, 2, 3, 4);
		rightArm1.setRotationPoint(-7.0f, -1.0f, -5.0f);
		rightArm1.setTextureSize(128, 32);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0.0f, 0.0f, 0.0f);
		(leftArm1 = new ModelRenderer(this, 49, 24)).addBox(-3.0f, -3.0f, -2.0f, 2, 3, 4);
		leftArm1.setRotationPoint(7.0f, -1.0f, -5.0f);
		leftArm1.setTextureSize(128, 32);
		leftArm1.mirror = true;
		setRotation(leftArm1, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setRotationPoint(-5.0f, 12.0f, 8.0f);
		rightLeg.setTextureSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg1 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg1.setRotationPoint(5.0f, 12.0f, 8.0f);
		leftLeg1.setTextureSize(128, 32);
		leftLeg1.mirror = true;
		setRotation(leftLeg1, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg2.setRotationPoint(-5.0f, 12.0f, -3.0f);
		rightLeg2.setTextureSize(128, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg2.setRotationPoint(5.0f, 12.0f, -3.0f);
		leftLeg2.setTextureSize(128, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 19, 18)).addBox(-4.0f, 0.0f, -2.0f, 8, 8, 4);
		body2.setRotationPoint(0.0f, -4.0f, -5.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 52, 0)).addBox(-1.0f, -2.0f, -2.0f, 4, 14, 4);
		leftArm2.setRotationPoint(7.0f, -1.0f, -5.0f);
		leftArm2.setTextureSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 52, 0)).addBox(-3.0f, -2.0f, -2.0f, 4, 14, 4);
		rightArm2.setRotationPoint(-7.0f, -1.0f, -5.0f);
		rightArm2.setTextureSize(128, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body1.render(par7);
		rightArm1.render(par7);
		leftArm1.render(par7);
		rightLeg.render(par7);
		leftLeg1.render(par7);
		rightLeg2.render(par7);
		leftLeg2.render(par7);
		body2.render(par7);
		leftArm2.render(par7);
		rightArm2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
		leftLeg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftLeg1.rotateAngleY = 0.0f;
		leftLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftLeg2.rotateAngleY = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		rightLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		rightArm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm1.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		leftArm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm1.rotateAngleZ = 0.0f;
		leftArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm2.rotateAngleZ = 0.0f;
	}
}

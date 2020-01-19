package net.tslat.aoa3.client.model.entities.mobs.lborean;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelMermage extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer body4;
	private ModelRenderer body5;
	private ModelRenderer rightArm2;
	private ModelRenderer leftArm2;
	private ModelRenderer rightArm3;
	private ModelRenderer rightArm4;
	private ModelRenderer rightArm5;
	private ModelRenderer rightArm6;
	private ModelRenderer rightArm7;

	public ModelMermage() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, -2.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 3, 52)).addBox(-9.0f, 6.0f, -1.0f, 16, 8, 0);
		body.setRotationPoint(1.0f, 12.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.6981317f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 4)).addBox(-4.0f, -5.0f, -3.0f, 6, 3, 6);
		rightArm.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightArm.setTextureSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 4)).addBox(-2.0f, -5.0f, -3.0f, 6, 3, 6);
		leftArm.setRotationPoint(6.0f, 2.0f, 0.0f);
		leftArm.setTextureSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 13, 17)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body2.setRotationPoint(0.0f, -2.0f, -1.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 16, 34)).addBox(-4.0f, 0.0f, -2.0f, 7, 7, 3);
		body3.setRotationPoint(0.5f, 8.0f, -1.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.2617994f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 16, 45)).addBox(-4.0f, 0.0f, -2.0f, 6, 5, 2);
		body4.setRotationPoint(1.0f, 12.0f, 0.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.6981317f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 40, 16)).addBox(-6.0f, 5.0f, -2.0f, 10, 2, 2);
		body5.setRotationPoint(1.0f, 12.0f, 0.0f);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 0.6981317f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 35)).addBox(-2.0f, -21.0f, -9.0f, 2, 8, 2);
		rightArm2.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightArm2.setTextureSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.7853982f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 38, 47)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm2.setRotationPoint(6.0f, 2.0f, 0.0f);
		leftArm2.setTextureSize(64, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 38, 47)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm3.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightArm3.setTextureSize(64, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(rightArm4 = new ModelRenderer(this, 56, 28)).addBox(-2.0f, -11.0f, -9.0f, 2, 22, 2);
		rightArm4.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightArm4.setTextureSize(64, 64);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.7853982f, 0.0f, 0.0f);
		(rightArm5 = new ModelRenderer(this, 40, 30)).addBox(-4.0f, -13.0f, -9.0f, 6, 2, 2);
		rightArm5.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightArm5.setTextureSize(64, 64);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.7853982f, 0.0f, 0.0f);
		(rightArm6 = new ModelRenderer(this, 40, 35)).addBox(-6.0f, -18.0f, -9.0f, 2, 7, 2);
		rightArm6.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightArm6.setTextureSize(64, 64);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.7853982f, 0.0f, 0.0f);
		(rightArm7 = new ModelRenderer(this, 40, 35)).addBox(2.0f, -18.0f, -9.0f, 2, 7, 2);
		rightArm7.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightArm7.setTextureSize(64, 64);
		rightArm7.mirror = true;
		setRotation(rightArm7, 0.7853982f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		rightArm2.render(par7);
		leftArm2.render(par7);
		rightArm3.render(par7);
		rightArm4.render(par7);
		rightArm5.render(par7);
		rightArm6.render(par7);
		rightArm7.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.785f;
		rightArm2.rotateAngleZ = 0.0f;
		rightArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm3.rotateAngleZ = 0.0f;
		rightArm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.785f;
		rightArm4.rotateAngleZ = 0.0f;
		rightArm5.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.785f;
		rightArm5.rotateAngleZ = 0.0f;
		rightArm6.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.785f;
		rightArm6.rotateAngleZ = 0.0f;
		rightArm7.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.785f;
		rightArm7.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		leftArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm2.rotateAngleZ = 0.0f;
	}
}

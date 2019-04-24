package net.nevermine.boss.kror;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelKror extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer rightarm2;
	ModelRenderer leftarm2;
	ModelRenderer body2;
	ModelRenderer rightarm3;
	ModelRenderer leftarm3;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer body7;
	ModelRenderer body8;

	public modelKror() {
		textureWidth = 256;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-6.0f, -8.0f, -4.0f, 12, 8, 8);
		head.setRotationPoint(0.0f, 0.0f, -9.0f);
		head.setTextureSize(256, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 55, 7)).addBox(3.0f, -12.0f, 8.0f, 3, 12, 6);
		body.setRotationPoint(0.0f, 5.0f, 4.0f);
		body.setTextureSize(256, 32);
		body.mirror = true;
		setRotation(body, -0.7853982f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 218, 13)).addBox(-4.0f, 14.0f, -6.0f, 8, 10, 8);
		rightarm.setRotationPoint(-12.0f, 0.0f, -6.0f);
		rightarm.setTextureSize(256, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 218, 13)).addBox(-2.0f, 14.0f, -6.0f, 8, 10, 8);
		leftarm.setRotationPoint(10.0f, 0.0f, -6.0f);
		leftarm.setTextureSize(256, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 189, 13)).addBox(-2.0f, 0.0f, -2.0f, 6, 12, 6);
		rightleg.setRotationPoint(-8.0f, 12.0f, 14.0f);
		rightleg.setTextureSize(256, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 189, 13)).addBox(-2.0f, 0.0f, -2.0f, 6, 12, 6);
		leftleg.setRotationPoint(6.0f, 12.0f, 14.0f);
		leftleg.setTextureSize(256, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 162, 0)).addBox(-3.0f, -2.0f, -2.0f, 6, 22, 6);
		rightarm2.setRotationPoint(-10.0f, 4.0f, 4.0f);
		rightarm2.setTextureSize(256, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 162, 0)).addBox(-1.0f, -2.0f, -2.0f, 6, 22, 6);
		leftarm2.setRotationPoint(8.0f, 4.0f, 4.0f);
		leftarm2.setTextureSize(256, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 93, 0)).addBox(-7.0f, 0.0f, -2.0f, 14, 16, 6);
		body2.setRotationPoint(0.0f, 5.0f, 4.0f);
		body2.setTextureSize(256, 32);
		body2.mirror = true;
		setRotation(body2, 1.047198f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 135, 0)).addBox(-3.0f, -2.0f, -2.0f, 6, 26, 6);
		rightarm3.setRotationPoint(-12.0f, 0.0f, -6.0f);
		rightarm3.setTextureSize(256, 32);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0f, 0.0f, 0.0f);
		(leftarm3 = new ModelRenderer(this, 135, 0)).addBox(-1.0f, -2.0f, -2.0f, 6, 26, 6);
		leftarm3.setRotationPoint(10.0f, 0.0f, -6.0f);
		leftarm3.setTextureSize(256, 32);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 85, 0)).addBox(-9.0f, -12.0f, -2.0f, 18, 12, 6);
		body3.setRotationPoint(0.0f, 5.0f, 4.0f);
		body3.setTextureSize(256, 32);
		body3.mirror = true;
		setRotation(body3, 1.047198f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 55, 7)).addBox(-6.0f, -12.0f, 8.0f, 3, 12, 6);
		body4.setRotationPoint(0.0f, 5.0f, 4.0f);
		body4.setTextureSize(256, 32);
		body4.mirror = true;
		setRotation(body4, -0.7853982f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 55, 7)).addBox(-8.0f, -12.0f, -11.0f, 3, 12, 6);
		body5.setRotationPoint(0.0f, 5.0f, 4.0f);
		body5.setTextureSize(256, 32);
		body5.mirror = true;
		setRotation(body5, -0.7853982f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 55, 7)).addBox(5.0f, -12.0f, -11.0f, 3, 12, 6);
		body6.setRotationPoint(0.0f, 5.0f, 4.0f);
		body6.setTextureSize(256, 32);
		body6.mirror = true;
		setRotation(body6, -0.7853982f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 55, 7)).addBox(-6.0f, -12.0f, 0.0f, 3, 12, 6);
		body7.setRotationPoint(0.0f, 5.0f, 4.0f);
		body7.setTextureSize(256, 32);
		body7.mirror = true;
		setRotation(body7, -0.7853982f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 55, 7)).addBox(3.0f, -12.0f, 0.0f, 3, 12, 6);
		body8.setRotationPoint(0.0f, 5.0f, 4.0f);
		body8.setTextureSize(256, 32);
		body8.mirror = true;
		setRotation(body8, -0.7853982f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		rightarm2.render(par7);
		leftarm2.render(par7);
		body2.render(par7);
		rightarm3.render(par7);
		leftarm3.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
		body7.render(par7);
		body8.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightarm.rotateAngleY = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightarm2.rotateAngleY = 0.0f;
		rightarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightarm3.rotateAngleY = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftarm.rotateAngleY = 0.0f;
		leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftarm2.rotateAngleY = 0.0f;
		leftarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftarm3.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

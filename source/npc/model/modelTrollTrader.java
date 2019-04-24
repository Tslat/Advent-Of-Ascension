package net.nevermine.npc.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelTrollTrader extends ModelBase {
	ModelRenderer Body;
	ModelRenderer Right_Leg;
	ModelRenderer Left_Leg;
	ModelRenderer Head;
	ModelRenderer Nose;
	ModelRenderer Left_Arm;
	ModelRenderer Right_Arm;
	ModelRenderer Left_Ear1;
	ModelRenderer Left_Ear2;

	public modelTrollTrader() {
		textureWidth = 64;
		textureHeight = 32;
		(Body = new ModelRenderer(this, 36, 15)).addBox(-4.0f, 0.0f, -3.0f, 8, 11, 6);
		Body.setRotationPoint(0.0f, 3.0f, 1.0f);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0.0f, 0.0f, 0.0f);
		(Right_Leg = new ModelRenderer(this, 18, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		Right_Leg.setRotationPoint(-2.0f, 14.0f, 1.0f);
		Right_Leg.setTextureSize(64, 32);
		Right_Leg.mirror = true;
		setRotation(Right_Leg, 0.0f, 0.0f, 0.0f);
		(Left_Leg = new ModelRenderer(this, 18, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		Left_Leg.setRotationPoint(2.0f, 14.0f, 1.0f);
		Left_Leg.setTextureSize(64, 32);
		Left_Leg.mirror = true;
		setRotation(Left_Leg, 0.0f, 0.0f, 0.0f);
		(Head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		Head.setRotationPoint(0.0f, 3.0f, 0.0f);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0.0f, 0.0f, 0.0f);
		(Nose = new ModelRenderer(this, 33, 8)).addBox(-1.0f, -4.0f, -6.0f, 2, 5, 2);
		Nose.setRotationPoint(0.0f, 3.0f, 0.0f);
		Nose.setTextureSize(64, 32);
		Nose.mirror = true;
		setRotation(Nose, 0.0f, 0.0f, 0.0f);
		(Left_Arm = new ModelRenderer(this, 0, 18)).addBox(0.0f, -1.0f, -2.0f, 4, 10, 4);
		Left_Arm.setRotationPoint(4.0f, 4.0f, 1.0f);
		Left_Arm.setTextureSize(64, 32);
		Left_Arm.mirror = true;
		setRotation(Left_Arm, 0.0f, 0.0f, 0.0f);
		(Right_Arm = new ModelRenderer(this, 0, 18)).addBox(-4.0f, -1.0f, -2.0f, 4, 10, 4);
		Right_Arm.setRotationPoint(-4.0f, 4.0f, 1.0f);
		Right_Arm.setTextureSize(64, 32);
		Right_Arm.mirror = true;
		setRotation(Right_Arm, 0.0f, 0.0f, 0.0f);
		(Left_Ear1 = new ModelRenderer(this, 45, 2)).addBox(-1.0f, -11.0f, -3.0f, 2, 1, 6);
		Left_Ear1.setRotationPoint(0.0f, 3.0f, 0.0f);
		Left_Ear1.setTextureSize(64, 32);
		Left_Ear1.mirror = true;
		setRotation(Left_Ear1, 0.0f, 0.0f, 0.0f);
		(Left_Ear2 = new ModelRenderer(this, 43, 0)).addBox(-1.0f, -10.0f, -4.0f, 2, 2, 8);
		Left_Ear2.setRotationPoint(0.0f, 3.0f, 0.0f);
		Left_Ear2.setTextureSize(64, 32);
		Left_Ear2.mirror = true;
		setRotation(Left_Ear2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Body.render(par7);
		Right_Leg.render(par7);
		Left_Leg.render(par7);
		Head.render(par7);
		Nose.render(par7);
		Left_Arm.render(par7);
		Right_Arm.render(par7);
		Left_Ear1.render(par7);
		Left_Ear2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		Head.rotateAngleY = par4 / 57.295776f;
		Head.rotateAngleX = par5 / 54.11268f;
		Nose.rotateAngleY = par4 / 57.295776f;
		Nose.rotateAngleX = par5 / 54.11268f;
		Left_Ear1.rotateAngleY = par4 / 57.295776f;
		Left_Ear1.rotateAngleX = par5 / 54.11268f;
		Left_Ear2.rotateAngleY = par4 / 57.295776f;
		Left_Ear2.rotateAngleX = par5 / 54.11268f;
		Right_Arm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		Right_Arm.rotateAngleZ = 0.0f;
		Left_Arm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		Left_Arm.rotateAngleZ = 0.0f;
		Right_Leg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		Right_Leg.rotateAngleY = 0.0f;
		Left_Leg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

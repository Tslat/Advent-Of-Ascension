package net.nevermine.mob.model.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelGoblin extends ModelBase {
	ModelRenderer Body;
	ModelRenderer Right_Leg;
	ModelRenderer Left_Leg;
	ModelRenderer Head;
	ModelRenderer Nose;
	ModelRenderer Left_Ear;
	ModelRenderer Right_Ear;
	ModelRenderer Left_Arm;
	ModelRenderer Staff;
	ModelRenderer Right_Arm;

	public modelGoblin() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.Left_Ear = new ModelRenderer(this, 44, 0);
		this.Left_Ear.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.Left_Ear.addBox(-8.0F, -12.0F, 2.0F, 4, 6, 1, 0.0F);
		this.setRotation(Left_Ear, 0.0F, 0.0F, 0.8922123136195012F);
		this.Head = new ModelRenderer(this, 0, 0);
		this.Head.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.Nose = new ModelRenderer(this, 33, 8);
		this.Nose.setRotationPoint(0.0F, 3.0F, -1.0F);
		this.Nose.addBox(-1.0F, -7.0F, -5.0F, 2, 5, 2, 0.0F);
		this.Right_Leg = new ModelRenderer(this, 18, 18);
		this.Right_Leg.setRotationPoint(-2.0F, 14.0F, 1.0F);
		this.Right_Leg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.Right_Arm = new ModelRenderer(this, 0, 18);
		this.Right_Arm.setRotationPoint(-4.0F, 4.0F, 1.0F);
		this.Right_Arm.addBox(-4.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.Left_Leg = new ModelRenderer(this, 18, 18);
		this.Left_Leg.setRotationPoint(2.0F, 14.0F, 1.0F);
		this.Left_Leg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.Body = new ModelRenderer(this, 36, 15);
		this.Body.setRotationPoint(0.0F, 3.0F, 1.0F);
		this.Body.addBox(-4.0F, 0.0F, -3.0F, 8, 11, 6, 0.0F);
		this.Right_Ear = new ModelRenderer(this, 33, 0);
		this.Right_Ear.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.Right_Ear.addBox(4.0F, -12.0F, 2.0F, 4, 6, 1, 0.0F);
		this.setRotation(Right_Ear, 0.0F, 0.0F, -0.8922123136195012F);
		this.Left_Arm = new ModelRenderer(this, 0, 18);
		this.Left_Arm.setRotationPoint(4.0F, 4.0F, 1.0F);
		this.Left_Arm.addBox(0.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.Staff = new ModelRenderer(this, 59, 0);
		this.Staff.setRotationPoint(-4.0F, 4.0F, 1.0F);
		this.Staff.addBox(-2.5F, -9.0F, -5.0F, 1, 20, 1, 0.0F);
		this.setRotation(Staff, 0.43632999062538147F, 0.0F, 0.0F);
		this.Head.addChild(this.Left_Ear);
		this.Head.addChild(this.Nose);
		this.Head.addChild(this.Right_Ear);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Body.render(par7);
		Right_Leg.render(par7);
		Left_Leg.render(par7);
		Head.render(par7);
		Left_Arm.render(par7);
		Staff.render(par7);
		Right_Arm.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		Head.rotateAngleY = par4 / (float)(180f / Math.PI);
		Right_Arm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		Right_Arm.rotateAngleZ = 0.0f;
		Staff.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.43633f;
		Staff.rotateAngleZ = 0.0f;
		Left_Arm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		Left_Arm.rotateAngleZ = 0.0f;
		Right_Leg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		Right_Leg.rotateAngleY = 0.0f;
		Left_Leg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

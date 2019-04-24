package net.tslat.aoa3.client.model.entities.mobs.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelShyreTroll extends ModelBase {
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
	ModelRenderer Left_Ear2;
	ModelRenderer Right_Ear2;

	public ModelShyreTroll() {
		textureWidth = 64;
		textureHeight = 32;

		Body = new ModelRenderer(this, 36, 15);
		Body.addBox(-4.0F, 0.0F, -3.0F, 8, 11, 6);
		Body.setRotationPoint(0.0F, 3.0F, 1.0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0.0F, 0.0F, 0.0F);
		Right_Leg = new ModelRenderer(this, 18, 18);
		Right_Leg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4);
		Right_Leg.setRotationPoint(-2.0F, 14.0F, 1.0F);
		Right_Leg.setTextureSize(64, 32);
		Right_Leg.mirror = true;
		setRotation(Right_Leg, 0.0F, 0.0F, 0.0F);
		Left_Leg = new ModelRenderer(this, 18, 18);
		Left_Leg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4);
		Left_Leg.setRotationPoint(2.0F, 14.0F, 1.0F);
		Left_Leg.setTextureSize(64, 32);
		Left_Leg.mirror = true;
		setRotation(Left_Leg, 0.0F, 0.0F, 0.0F);
		Head = new ModelRenderer(this, 0, 0);
		Head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
		Head.setRotationPoint(0.0F, 3.0F, 0.0F);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0.0F, 0.0F, 0.0F);
		Nose = new ModelRenderer(this, 24, 0);
		Nose.addBox(-1.0F, -7.0F, -5.0F, 2, 5, 2, 0.0F);
		Nose.setRotationPoint(0.0F, 3.0F, -1.0F);
		Nose.setTextureSize(64, 32);
		Nose.mirror = true;
		setRotation(Nose, 0.0F, 0.0F, 0.0F);
		Left_Ear = new ModelRenderer(this, 43, 8);
		Left_Ear.addBox(-9.0F, -16.0F, -3.0F, 4, 1, 5, 0.0F);
		Left_Ear.setRotationPoint(1.0F, 3.0F, 0.0F);
		Left_Ear.setTextureSize(64, 32);
		Left_Ear.mirror = true;
		Right_Ear = new ModelRenderer(this, 32, 8);
		Right_Ear.addBox(5.0F, -16.0F, -3.0F, 4, 1, 5, 0.0F);
		Right_Ear.setRotationPoint(-1.0F, 3.0F, 0.0F);
		Right_Ear.setTextureSize(64, 32);
		Right_Ear.mirror = true;
		Left_Arm = new ModelRenderer(this, 0, 18);
		Left_Arm.addBox(0.0F, -1.0F, -2.0F, 4, 10, 4);
		Left_Arm.setRotationPoint(4.0F, 4.0F, 1.0F);
		Left_Arm.setTextureSize(64, 32);
		Left_Arm.mirror = true;
		setRotation(Left_Arm, 0.0F, 0.0F, 0.0F);
		Staff = new ModelRenderer(this, 60, 0);
		Staff.addBox(-2.0F, -2.0F, -5.0F, 1, 12, 1);
		Staff.setRotationPoint(-4.5F, 4.0F, 1.0F);
		Staff.setTextureSize(64, 32);
		Staff.mirror = true;
		setRotation(Staff, 0.3490659F, 0.0F, 0.0F);
		Right_Arm = new ModelRenderer(this, 0, 18);
		Right_Arm.addBox(-4.0F, -1.0F, -2.0F, 4, 10, 4);
		Right_Arm.setRotationPoint(-4.0F, 4.0F, 1.0F);
		Right_Arm.setTextureSize(64, 32);
		Right_Arm.mirror = true;
		setRotation(Right_Arm, 0.0F, 0.0F, 0.0F);
		Left_Ear2 = new ModelRenderer(this, 44, 0);
		Left_Ear2.addBox(-8.0F, -13.0F, 2.0F, 4, 6, 1, 0.0F);
		Left_Ear2.setRotationPoint(1.0F, 3.0F, 0.0F);
		Left_Ear2.setTextureSize(64, 32);
		Left_Ear2.mirror = true;
		setRotation(Left_Ear2, 0.0F, 0.0F, 0.8922867F);
		Right_Ear2 = new ModelRenderer(this, 33, 0);
		Right_Ear2.addBox(4.0F, -13.0F, 2.0F, 4, 6, 1, 0.0F);
		Right_Ear2.setRotationPoint(-1.0F, 3.0F, 0.0F);
		Right_Ear2.setTextureSize(64, 32);
		Right_Ear2.mirror = true;
		setRotation(Right_Ear2, 0.0F, 0.0F, -0.8922821F);
		Left_Ear2.addChild(this.Left_Ear);
		Head.addChild(this.Nose);
		Head.addChild(this.Left_Ear2);
		Right_Ear2.addChild(this.Right_Ear);
		Head.addChild(this.Right_Ear2);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Body.render(par7);
		Right_Leg.render(par7);
		Left_Leg.render(par7);
		Head.render(par7);
		Left_Arm.render(par7);
		Staff.render(par7);
		Right_Arm.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		Head.rotateAngleY = par4 / (float)(180f / Math.PI);

		Right_Arm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		Right_Arm.rotateAngleZ = 0.0F;

		Staff.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F + 0.43633F);
		Staff.rotateAngleZ = 0.0F;

		Left_Arm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		Left_Arm.rotateAngleZ = 0.0F;

		Right_Leg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		Right_Leg.rotateAngleY = 0.0F;

		Left_Leg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
	}
}

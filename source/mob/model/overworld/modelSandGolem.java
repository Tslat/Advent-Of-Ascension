package net.nevermine.mob.model.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelSandGolem extends ModelBase {
	ModelRenderer Right_foot;
	ModelRenderer Left_foot;
	ModelRenderer Hip;
	ModelRenderer Hip_Torso_Joint;
	ModelRenderer Lower_Torso;
	ModelRenderer Torso;
	ModelRenderer Head;
	ModelRenderer Nose;
	ModelRenderer Eyebrow;
	ModelRenderer Left_Arm;
	ModelRenderer Right_Arm;

	public modelSandGolem() {
		textureWidth = 128;
		textureHeight = 32;
		(Right_foot = new ModelRenderer(this, 61, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		Right_foot.setRotationPoint(-3.0f, 15.0f, 0.0f);
		Right_foot.setTextureSize(64, 32);
		Right_foot.mirror = true;
		setRotation(Right_foot, 0.0f, 0.0f, 0.0f);
		(Left_foot = new ModelRenderer(this, 61, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		Left_foot.setRotationPoint(3.0f, 15.0f, 0.0f);
		Left_foot.setTextureSize(64, 32);
		Left_foot.mirror = true;
		setRotation(Left_foot, 0.0f, 0.0f, 0.0f);
		(Hip = new ModelRenderer(this, 60, 13)).addBox(-3.0f, 0.0f, -3.0f, 6, 2, 6);
		Hip.setRotationPoint(0.0f, 13.0f, 0.0f);
		Hip.setTextureSize(64, 32);
		Hip.mirror = true;
		setRotation(Hip, 0.0f, 0.0f, 0.0f);
		(Hip_Torso_Joint = new ModelRenderer(this, 25, 0)).addBox(-2.0f, -3.0f, -2.0f, 4, 3, 4);
		Hip_Torso_Joint.setRotationPoint(0.0f, 13.0f, 0.0f);
		Hip_Torso_Joint.setTextureSize(64, 32);
		Hip_Torso_Joint.mirror = true;
		setRotation(Hip_Torso_Joint, 0.0f, 0.0f, 0.0f);
		(Lower_Torso = new ModelRenderer(this, 43, 23)).addBox(-3.0f, -1.0f, -3.0f, 6, 3, 6);
		Lower_Torso.setRotationPoint(0.0f, 9.0f, 0.0f);
		Lower_Torso.setTextureSize(64, 32);
		Lower_Torso.mirror = true;
		setRotation(Lower_Torso, 0.0f, 0.0f, 0.0f);
		(Torso = new ModelRenderer(this, 0, 16)).addBox(-6.0f, -7.0f, -4.5f, 12, 7, 9);
		Torso.setRotationPoint(0.0f, 8.0f, 0.0f);
		Torso.setTextureSize(64, 32);
		Torso.mirror = true;
		setRotation(Torso, 0.0f, 0.0f, 0.0f);
		(Head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		Head.setRotationPoint(0.0f, 1.0f, 0.0f);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0.0f, 0.0f, 0.0f);
		(Nose = new ModelRenderer(this, 25, 8)).addBox(-1.0f, -4.0f, -4.0f, 2, 4, 1);
		Nose.setRotationPoint(0.0f, 1.0f, 0.0f);
		Nose.setTextureSize(64, 32);
		Nose.mirror = true;
		setRotation(Nose, 0.0f, 0.0f, 0.0f);
		(Eyebrow = new ModelRenderer(this, 27, 13)).addBox(-3.0f, -6.0f, -4.0f, 6, 2, 1);
		Eyebrow.setRotationPoint(0.0f, 1.0f, 0.0f);
		Eyebrow.setTextureSize(64, 32);
		Eyebrow.mirror = true;
		setRotation(Eyebrow, 0.0f, 0.0f, 0.0f);
		(Left_Arm = new ModelRenderer(this, 43, 0)).addBox(0.0f, -2.0f, -2.0f, 4, 17, 4);
		Left_Arm.setRotationPoint(6.0f, 1.0f, 0.0f);
		Left_Arm.setTextureSize(64, 32);
		Left_Arm.mirror = true;
		setRotation(Left_Arm, 0.0f, 0.0f, 0.0f);
		(Right_Arm = new ModelRenderer(this, 43, 0)).addBox(-4.0f, -2.0f, -2.0f, 4, 17, 4);
		Right_Arm.setRotationPoint(-6.0f, 1.0f, 0.0f);
		Right_Arm.setTextureSize(64, 32);
		Right_Arm.mirror = true;
		setRotation(Right_Arm, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Right_foot.render(par7);
		Left_foot.render(par7);
		Hip.render(par7);
		Hip_Torso_Joint.render(par7);
		Lower_Torso.render(par7);
		Torso.render(par7);
		Head.render(par7);
		Nose.render(par7);
		Eyebrow.render(par7);
		Left_Arm.render(par7);
		Right_Arm.render(par7);
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
		Eyebrow.rotateAngleY = par4 / 57.295776f;
		Eyebrow.rotateAngleX = par5 / 54.11268f;
		Right_Arm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		Right_Arm.rotateAngleZ = 0.0f;
		Left_Arm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		Left_Arm.rotateAngleZ = 0.0f;
		Right_foot.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		Right_foot.rotateAngleY = 0.0f;
		Left_foot.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

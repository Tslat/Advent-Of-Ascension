package net.nevermine.boss.fourguardians;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelGuardian extends ModelBase {
	ModelRenderer head;
	ModelRenderer rightarm1;
	ModelRenderer leftarm1;
	ModelRenderer rightarm2;
	ModelRenderer leftarm2;
	ModelRenderer rightarm3;
	ModelRenderer leftarm3;
	ModelRenderer rightarm4;
	ModelRenderer leftarm4;
	ModelRenderer rightarm5;
	ModelRenderer leftarm5;

	public modelGuardian() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-6.0f, -12.0f, -2.0f, 12, 12, 4);
		head.setRotationPoint(0.0f, 15.0f, 0.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightarm1 = new ModelRenderer(this, 89, 3)).addBox(-1.0f, 10.0f, -2.0f, 2, 4, 1);
		rightarm1.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightarm1.setTextureSize(128, 32);
		rightarm1.mirror = true;
		setRotation(rightarm1, 0.0f, 0.0f, 0.0f);
		(leftarm1 = new ModelRenderer(this, 113, 3)).addBox(-1.0f, 10.0f, -2.0f, 2, 4, 1);
		leftarm1.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftarm1.setTextureSize(128, 32);
		leftarm1.mirror = true;
		setRotation(leftarm1, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 40, 3)).addBox(-1.0f, -2.0f, -4.0f, 2, 12, 8);
		rightarm2.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightarm2.setTextureSize(128, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 64, 3)).addBox(-1.0f, -2.0f, -4.0f, 2, 12, 8);
		leftarm2.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftarm2.setTextureSize(128, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 89, 3)).addBox(-1.0f, 10.0f, -4.0f, 2, 4, 1);
		rightarm3.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightarm3.setTextureSize(128, 32);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0f, 0.0f, 0.0f);
		(leftarm3 = new ModelRenderer(this, 113, 3)).addBox(-1.0f, 10.0f, -4.0f, 2, 4, 1);
		leftarm3.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftarm3.setTextureSize(128, 32);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0f, 0.0f, 0.0f);
		(rightarm4 = new ModelRenderer(this, 89, 3)).addBox(-1.0f, 10.0f, 3.0f, 2, 4, 1);
		rightarm4.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightarm4.setTextureSize(128, 32);
		rightarm4.mirror = true;
		setRotation(rightarm4, 0.0f, 0.0f, 0.0f);
		(leftarm4 = new ModelRenderer(this, 113, 3)).addBox(-1.0f, 10.0f, 3.0f, 2, 4, 1);
		leftarm4.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftarm4.setTextureSize(128, 32);
		leftarm4.mirror = true;
		setRotation(leftarm4, 0.0f, 0.0f, 0.0f);
		(rightarm5 = new ModelRenderer(this, 89, 3)).addBox(-1.0f, 10.0f, 1.0f, 2, 4, 1);
		rightarm5.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightarm5.setTextureSize(128, 32);
		rightarm5.mirror = true;
		setRotation(rightarm5, 0.0f, 0.0f, 0.0f);
		(leftarm5 = new ModelRenderer(this, 113, 3)).addBox(-1.0f, 10.0f, 1.0f, 2, 4, 1);
		leftarm5.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftarm5.setTextureSize(128, 32);
		leftarm5.mirror = true;
		setRotation(leftarm5, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		rightarm1.render(par7);
		leftarm1.render(par7);
		rightarm2.render(par7);
		leftarm2.render(par7);
		rightarm3.render(par7);
		leftarm3.render(par7);
		rightarm4.render(par7);
		leftarm4.render(par7);
		rightarm5.render(par7);
		leftarm5.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		rightarm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm1.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		rightarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm3.rotateAngleZ = 0.0f;
		rightarm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm4.rotateAngleZ = 0.0f;
		rightarm5.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm5.rotateAngleZ = 0.0f;
		leftarm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm1.rotateAngleZ = 0.0f;
		leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm2.rotateAngleZ = 0.0f;
		leftarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm3.rotateAngleZ = 0.0f;
		leftarm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm4.rotateAngleZ = 0.0f;
		leftarm5.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm5.rotateAngleZ = 0.0f;
	}
}

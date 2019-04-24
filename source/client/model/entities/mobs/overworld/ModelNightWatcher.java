package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelNightWatcher extends ModelBase {
	ModelRenderer Head;
	ModelRenderer Body1;
	ModelRenderer RightArm1;
	ModelRenderer LeftArm1;
	ModelRenderer RightLeg1;
	ModelRenderer LeftLeg1;
	ModelRenderer Body2;
	ModelRenderer LeftLeg2;
	ModelRenderer RightLeg2;
	ModelRenderer LeftArm2;
	ModelRenderer RightArm2;
	ModelRenderer LeftArm3;
	ModelRenderer RightArm3;
	ModelRenderer LeftArm4;
	ModelRenderer RightArm4;

	public ModelNightWatcher() {
		textureWidth = 64;
		textureHeight = 32;
		(Head = new ModelRenderer(this, 0, 16)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		Head.setRotationPoint(0.0f, -13.0f, -5.0f);
		Head.setTextureSize(64, 32);
		setRotation(Head, 0.0f, 0.0f, 0.0f);
		(Body1 = new ModelRenderer(this, 26, 8)).addBox(-6.0f, 0.0f, -2.0f, 10, 2, 4);
		Body1.setRotationPoint(1.0f, -18.0f, 0.0f);
		Body1.setTextureSize(64, 32);
		setRotation(Body1, 0.0f, 0.0f, 0.0f);
		(RightArm1 = new ModelRenderer(this, 3, 7)).addBox(-1.0f, 30.0f, 1.0f, 1, 6, 1);
		RightArm1.setRotationPoint(-5.0f, -14.0f, 0.0f);
		RightArm1.setTextureSize(64, 32);
		setRotation(RightArm1, 0.0f, 0.0f, 0.0f);
		(LeftArm1 = new ModelRenderer(this, 3, 7)).addBox(0.0f, 30.0f, 1.0f, 1, 6, 1);
		LeftArm1.setRotationPoint(5.0f, -14.0f, 0.0f);
		LeftArm1.setTextureSize(64, 32);
		setRotation(LeftArm1, 0.0f, 0.0f, 0.0f);
		(RightLeg1 = new ModelRenderer(this, 46, 0)).addBox(-1.0f, 30.0f, -1.0f, 2, 4, 2);
		RightLeg1.setRotationPoint(-2.0f, -10.0f, 0.0f);
		RightLeg1.setTextureSize(64, 32);
		setRotation(RightLeg1, 0.0f, 0.0f, 0.0f);
		(LeftLeg1 = new ModelRenderer(this, 46, 0)).addBox(-1.0f, 30.0f, -1.0f, 2, 4, 2);
		LeftLeg1.setRotationPoint(2.0f, -10.0f, 0.0f);
		LeftLeg1.setTextureSize(64, 32);
		setRotation(LeftLeg1, 0.0f, 0.0f, 0.0f);
		(Body2 = new ModelRenderer(this, 32, 16)).addBox(-4.0f, 0.0f, -2.0f, 6, 6, 4);
		Body2.setRotationPoint(1.0f, -16.0f, 0.0f);
		Body2.setTextureSize(64, 32);
		setRotation(Body2, 0.0f, 0.0f, 0.0f);
		(LeftLeg2 = new ModelRenderer(this, 56, 0)).addBox(-1.0f, 0.0f, -1.0f, 2, 30, 2);
		LeftLeg2.setRotationPoint(2.0f, -10.0f, 0.0f);
		LeftLeg2.setTextureSize(64, 32);
		setRotation(LeftLeg2, 0.0f, 0.0f, 0.0f);
		(RightLeg2 = new ModelRenderer(this, 56, 0)).addBox(-1.0f, 0.0f, -1.0f, 2, 30, 2);
		RightLeg2.setRotationPoint(-2.0f, -10.0f, 0.0f);
		RightLeg2.setTextureSize(64, 32);
		setRotation(RightLeg2, 0.0f, 0.0f, 0.0f);
		(LeftArm2 = new ModelRenderer(this, 56, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 30, 2);
		LeftArm2.setRotationPoint(5.0f, -14.0f, 0.0f);
		LeftArm2.setTextureSize(64, 32);
		setRotation(LeftArm2, 0.0f, 0.0f, 0.0f);
		(RightArm2 = new ModelRenderer(this, 56, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 30, 2);
		RightArm2.setRotationPoint(-5.0f, -14.0f, 0.0f);
		RightArm2.setTextureSize(64, 32);
		setRotation(RightArm2, 0.0f, 0.0f, 0.0f);
		(LeftArm3 = new ModelRenderer(this, 3, 0)).addBox(0.0f, 28.0f, -2.0f, 1, 2, 4);
		LeftArm3.setRotationPoint(5.0f, -14.0f, 0.0f);
		LeftArm3.setTextureSize(64, 32);
		setRotation(LeftArm3, 0.0f, 0.0f, 0.0f);
		(RightArm3 = new ModelRenderer(this, 3, 0)).addBox(-1.0f, 28.0f, -2.0f, 1, 2, 4);
		RightArm3.setRotationPoint(-5.0f, -14.0f, 0.0f);
		RightArm3.setTextureSize(64, 32);
		setRotation(RightArm3, 0.0f, 0.0f, 0.0f);
		(LeftArm4 = new ModelRenderer(this, 3, 7)).addBox(0.0f, 30.0f, -2.0f, 1, 6, 1);
		LeftArm4.setRotationPoint(5.0f, -14.0f, 0.0f);
		LeftArm4.setTextureSize(64, 32);
		setRotation(LeftArm4, 0.0f, 0.0f, 0.0f);
		(RightArm4 = new ModelRenderer(this, 3, 7)).addBox(-1.0f, 30.0f, -2.0f, 1, 6, 1);
		RightArm4.setRotationPoint(-5.0f, -14.0f, 0.0f);
		RightArm4.setTextureSize(64, 32);
		setRotation(RightArm4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Head.render(par7);
		Body1.render(par7);
		RightArm1.render(par7);
		LeftArm1.render(par7);
		RightLeg1.render(par7);
		LeftLeg1.render(par7);
		Body2.render(par7);
		LeftLeg2.render(par7);
		RightLeg2.render(par7);
		LeftArm2.render(par7);
		RightArm2.render(par7);
		LeftArm3.render(par7);
		RightArm3.render(par7);
		LeftArm4.render(par7);
		RightArm4.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		Head.rotateAngleY = par4 / 57.295776f;
		Head.rotateAngleX = par5 / 54.11268f;
		RightArm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		RightArm1.rotateAngleZ = 0.0f;
		RightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		RightArm2.rotateAngleZ = 0.0f;
		RightArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		RightArm3.rotateAngleZ = 0.0f;
		RightArm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		RightArm4.rotateAngleZ = 0.0f;
		LeftArm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		LeftArm1.rotateAngleZ = 0.0f;
		LeftArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		LeftArm2.rotateAngleZ = 0.0f;
		LeftArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		LeftArm3.rotateAngleZ = 0.0f;
		LeftArm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		LeftArm4.rotateAngleZ = 0.0f;
		RightLeg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		RightLeg1.rotateAngleY = 0.0f;
		RightLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		RightLeg2.rotateAngleY = 0.0f;
		LeftLeg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		LeftLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

package net.nevermine.mob.model.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelRammerhead extends ModelBase {
	ModelRenderer eye1;
	ModelRenderer fin1;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer headbar;
	ModelRenderer neck;
	ModelRenderer eye2;
	ModelRenderer body;
	ModelRenderer fin2;

	public modelRammerhead() {
		textureWidth = 128;
		textureHeight = 32;
		(eye1 = new ModelRenderer(this, 57, 12)).addBox(-16.0f, -4.0f, -9.0f, 4, 4, 6);
		eye1.setRotationPoint(0.0f, 14.0f, -6.0f);
		eye1.setTextureSize(128, 32);
		eye1.mirror = true;
		setRotation(eye1, 0.0f, 0.0f, 0.0f);
		(fin1 = new ModelRenderer(this, 30, 10)).addBox(-4.0f, -9.0f, -7.0f, 2, 15, 7);
		fin1.setRotationPoint(6.0f, 6.0f, 2.0f);
		fin1.setTextureSize(128, 32);
		fin1.mirror = true;
		setRotation(fin1, 2.007129f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 8, 4);
		leg1.setRotationPoint(-3.0f, 16.0f, 7.0f);
		leg1.setTextureSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 8, 4);
		leg2.setRotationPoint(3.0f, 16.0f, 7.0f);
		leg2.setTextureSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 8, 4);
		leg3.setRotationPoint(-3.0f, 16.0f, -5.0f);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 8, 4);
		leg4.setRotationPoint(3.0f, 16.0f, -5.0f);
		leg4.setTextureSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(headbar = new ModelRenderer(this, 23, 0)).addBox(-12.0f, -4.0f, -9.0f, 24, 4, 4);
		headbar.setRotationPoint(0.0f, 14.0f, -6.0f);
		headbar.setTextureSize(128, 32);
		headbar.mirror = true;
		setRotation(headbar, 0.0f, 0.0f, 0.0f);
		(neck = new ModelRenderer(this, 51, 24)).addBox(-2.0f, -4.0f, -5.0f, 4, 4, 3);
		neck.setRotationPoint(0.0f, 14.0f, -6.0f);
		neck.setTextureSize(128, 32);
		neck.mirror = true;
		setRotation(neck, 0.0f, 0.0f, 0.0f);
		(eye2 = new ModelRenderer(this, 0, 0)).addBox(12.0f, -4.0f, -9.0f, 4, 4, 6);
		eye2.setRotationPoint(0.0f, 14.0f, -6.0f);
		eye2.setTextureSize(128, 32);
		eye2.mirror = true;
		setRotation(eye2, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 88, 7)).addBox(-6.0f, -10.0f, -7.0f, 12, 17, 7);
		body.setRotationPoint(0.0f, 9.0f, 2.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(fin2 = new ModelRenderer(this, 30, 10)).addBox(-4.0f, -9.0f, -7.0f, 2, 15, 7);
		fin2.setRotationPoint(0.0f, 6.0f, 2.0f);
		fin2.setTextureSize(128, 32);
		fin2.mirror = true;
		setRotation(fin2, 2.007129f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		eye1.render(par7);
		fin1.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		headbar.render(par7);
		neck.render(par7);
		eye2.render(par7);
		body.render(par7);
		fin2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg1.rotateAngleY = 0.0f;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

package net.nevermine.mob.model.runandor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelRunicornRider extends ModelBase {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer part1;
	ModelRenderer part2;
	ModelRenderer part3;
	ModelRenderer part4;
	ModelRenderer part6;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer part7;
	ModelRenderer part8;
	ModelRenderer part9;
	ModelRenderer part10;
	ModelRenderer part11;
	ModelRenderer part12;

	public modelRunicornRider() {
		textureWidth = 128;
		textureHeight = 64;
		(Shape1 = new ModelRenderer(this, 111, 2)).addBox(0.0f, 0.0f, 0.0f, 4, 12, 4);
		Shape1.setRotationPoint(4.0f, -9.0f, -2.0f);
		Shape1.setTextureSize(128, 64);
		setRotation(Shape1, -1.047198f, 0.2443461f, 0.0f);
		(Shape2 = new ModelRenderer(this, 111, 2)).addBox(0.0f, 0.0f, 0.0f, 4, 12, 4);
		Shape2.setRotationPoint(-7.0f, -9.0f, -2.0f);
		Shape2.setTextureSize(128, 64);
		setRotation(Shape2, -1.047198f, -0.2443461f, 0.0f);
		(Shape3 = new ModelRenderer(this, 89, 47)).addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
		Shape3.setRotationPoint(-4.0f, -17.0f, -4.0f);
		Shape3.setTextureSize(128, 64);
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 111, 20)).addBox(0.0f, 0.0f, 0.0f, 4, 12, 4);
		Shape4.setRotationPoint(5.0f, 0.0f, -2.0f);
		Shape4.setTextureSize(128, 64);
		setRotation(Shape4, -0.3490659f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 111, 20)).addBox(0.0f, 0.0f, 0.0f, 4, 12, 4);
		Shape5.setRotationPoint(-8.0f, 0.0f, -2.0f);
		Shape5.setTextureSize(128, 64);
		setRotation(Shape5, -0.3490659f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 55, 0)).addBox(-0.5f, 0.0f, 0.0f, 8, 12, 4);
		Shape6.setRotationPoint(-3.0f, -9.0f, -2.0f);
		Shape6.setTextureSize(128, 64);
		setRotation(Shape6, 0.0f, 0.0f, 0.0f);
		(part1 = new ModelRenderer(this, 35, 16)).addBox(0.0f, -0.5f, 0.0f, 1, 3, 1);
		part1.setRotationPoint(-2.0f, -9.0f, -17.06667f);
		part1.setTextureSize(128, 64);
		setRotation(part1, 0.3490659f, 0.0f, 0.0f);
		(part2 = new ModelRenderer(this, 42, 18)).addBox(-2.5f, -6.5f, -4.0f, 2, 9, 2);
		part2.setRotationPoint(2.0f, -9.0f, -17.0f);
		part2.setTextureSize(128, 64);
		setRotation(part2, 0.3490659f, 0.0f, 0.0f);
		(part3 = new ModelRenderer(this, 0, 0)).addBox(0.5f, 0.0f, 0.0f, 4, 13, 6);
		part3.setRotationPoint(-2.0f, -1.0f, -19.0f);
		part3.setTextureSize(128, 64);
		setRotation(part3, 0.6444293f, 0.0f, 0.0f);
		(part4 = new ModelRenderer(this, 0, 44)).addBox(0.0f, 0.0f, 0.0f, 9, 9, 11);
		part4.setRotationPoint(-4.0f, 3.0f, -12.0f);
		part4.setTextureSize(128, 64);
		setRotation(part4, 0.0f, 0.0f, 0.0f);
		(part6 = new ModelRenderer(this, 55, 17)).addBox(0.0f, 0.0f, 0.0f, 7, 5, 3);
		part6.setRotationPoint(-3.0f, 9.0f, -5.0f);
		part6.setTextureSize(128, 64);
		setRotation(part6, 0.9397927f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 58, 40)).addBox(-2.0f, 0.0f, -2.0f, 4, 20, 4);
		leg1.setRotationPoint(-3.0f, 4.0f, -9.0f);
		leg1.setTextureSize(128, 64);
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 58, 40)).addBox(-2.0f, 0.0f, -2.0f, 4, 20, 4);
		leg2.setRotationPoint(4.0f, 4.0f, -9.0f);
		leg2.setTextureSize(128, 64);
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 93, 12)).addBox(-2.0f, 0.0f, -2.0f, 4, 20, 4);
		leg3.setRotationPoint(4.0f, 4.0f, 10.0f);
		leg3.setTextureSize(128, 64);
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 93, 12)).addBox(-2.0f, 0.0f, -2.0f, 4, 20, 4);
		leg4.setRotationPoint(-3.0f, 4.0f, 10.0f);
		leg4.setTextureSize(128, 64);
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(part7 = new ModelRenderer(this, 51, 25)).addBox(2.0f, 0.0f, 0.0f, 9, 1, 7);
		part7.setRotationPoint(-6.0f, 2.0f, 5.0f);
		part7.setTextureSize(128, 64);
		setRotation(part7, 0.0f, 0.0f, 0.0f);
		(part8 = new ModelRenderer(this, 74, 43)).addBox(0.0f, 0.0f, 0.0f, 3, 19, 2);
		part8.setRotationPoint(-1.0f, 3.0f, 10.0f);
		part8.setTextureSize(128, 64);
		setRotation(part8, 0.4833219f, 0.0f, 0.0f);
		(part9 = new ModelRenderer(this, 45, 46)).addBox(1.0f, 0.0f, -6.0f, 1, 15, 3);
		part9.setRotationPoint(-1.0f, -10.0f, -12.0f);
		part9.setTextureSize(128, 64);
		setRotation(part9, 0.669215f, 0.0f, 0.0f);
		(part10 = new ModelRenderer(this, 22, 0)).addBox(-1.0f, 0.0f, 0.0f, 5, 5, 11);
		part10.setRotationPoint(-1.0f, -3.466667f, -25.0f);
		part10.setTextureSize(128, 64);
		setRotation(part10, 0.3490659f, 0.0f, 0.0f);
		(part11 = new ModelRenderer(this, 35, 16)).addBox(0.0f, -0.5f, 0.0f, 1, 3, 1);
		part11.setRotationPoint(2.0f, -9.0f, -17.0f);
		part11.setTextureSize(128, 64);
		setRotation(part11, 0.3490659f, 0.0f, 0.0f);
		(part12 = new ModelRenderer(this, 0, 25)).addBox(0.0f, 0.0f, 0.0f, 9, 7, 12);
		part12.setRotationPoint(-4.0f, 3.0f, -1.0f);
		part12.setTextureSize(128, 64);
		setRotation(part12, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Shape1.render(par7);
		Shape2.render(par7);
		Shape3.render(par7);
		Shape4.render(par7);
		Shape5.render(par7);
		Shape6.render(par7);
		part1.render(par7);
		part2.render(par7);
		part3.render(par7);
		part4.render(par7);
		part6.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		part7.render(par7);
		part8.render(par7);
		part9.render(par7);
		part10.render(par7);
		part11.render(par7);
		part12.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		part3.rotateAngleY = par4 / (float)(180f / Math.PI);
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
	}
}

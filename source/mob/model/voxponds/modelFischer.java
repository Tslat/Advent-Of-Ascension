package net.nevermine.mob.model.voxponds;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class modelFischer extends ModelBase {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer r1;
	ModelRenderer Shape3;
	ModelRenderer r2;

	public modelFischer() {
		textureWidth = 128;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
		Shape1.setRotationPoint(-4.0f, 11.0f, 9.0f);
		Shape1.setTextureSize(128, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 79, 0)).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
		Shape2.setRotationPoint(-6.0f, 9.0f, -6.0f);
		Shape2.setTextureSize(128, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 35, 0)).addBox(-5.0f, 0.0f, -5.0f, 10, 2, 10);
		r1.setRotationPoint(0.0f, 22.0f, -0.3333333f);
		r1.setTextureSize(128, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 35, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
		Shape3.setRotationPoint(-5.0f, 10.0f, 2.0f);
		Shape3.setTextureSize(128, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 35, 0)).addBox(-5.0f, 0.0f, -5.0f, 10, 2, 10);
		r2.setRotationPoint(0.0f, 6.0f, -0.3333333f);
		r2.setTextureSize(128, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Shape1.render(par7);
		Shape2.render(par7);
		r1.render(par7);
		Shape3.render(par7);
		r2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		r1.rotateAngleY = par3 * 0.067f * 2.25f;
		r2.rotateAngleY = par3 * 0.067f * 2.25f;
	}
}

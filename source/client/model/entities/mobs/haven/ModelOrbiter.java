package net.tslat.aoa3.client.model.entities.mobs.haven;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOrbiter extends ModelBase {
	ModelRenderer Shape1;
	ModelRenderer S2r;
	ModelRenderer Shape7;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer S2r6;
	ModelRenderer S2r5;
	ModelRenderer S2r4;
	ModelRenderer S2r3;
	ModelRenderer S2r2;

	public ModelOrbiter() {
		textureWidth = 64;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 33, 15)).addBox(-1.0f, 1.0f, -1.0f, 2, 3, 2);
		Shape1.setRotationPoint(0.0f, 12.0f, 0.0f);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(S2r = new ModelRenderer(this, 0, 15)).addBox(5.0f, -11.0f, -2.0f, 1, 3, 4);
		S2r.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r.setTextureSize(64, 32);
		S2r.mirror = true;
		setRotation(S2r, 0.0f, 0.0f, 0.0f);
		(Shape7 = new ModelRenderer(this, 16, 0)).addBox(-2.0f, -8.0f, -2.0f, 4, 4, 4);
		Shape7.setRotationPoint(0.0f, 12.0f, 0.0f);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 32, 0)).addBox(4.0f, -2.0f, -2.0f, 4, 4, 4);
		Shape2.setRotationPoint(0.0f, 12.0f, 0.0f);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 48, 0)).addBox(-8.0f, -2.0f, -2.0f, 4, 4, 4);
		Shape3.setRotationPoint(0.0f, 12.0f, 0.0f);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 43, 9)).addBox(-4.0f, -1.0f, -1.0f, 8, 2, 2);
		Shape4.setRotationPoint(0.0f, 12.0f, 0.0f);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 33, 9)).addBox(-1.0f, -4.0f, -1.0f, 2, 3, 2);
		Shape5.setRotationPoint(0.0f, 12.0f, 0.0f);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, 4.0f, -2.0f, 4, 4, 4);
		Shape6.setRotationPoint(0.0f, 12.0f, 0.0f);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0f, 0.0f, 0.0f);
		(S2r6 = new ModelRenderer(this, 0, 15)).addBox(5.0f, 8.0f, -2.0f, 1, 3, 4);
		S2r6.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r6.setTextureSize(64, 32);
		S2r6.mirror = true;
		setRotation(S2r6, 0.0f, 0.0f, 0.0f);
		(S2r5 = new ModelRenderer(this, 0, 9)).addBox(-5.0f, -10.0f, -2.0f, 10, 1, 4);
		S2r5.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r5.setTextureSize(64, 32);
		S2r5.mirror = true;
		setRotation(S2r5, 0.0f, 0.0f, 0.0f);
		(S2r4 = new ModelRenderer(this, 0, 9)).addBox(-5.0f, 9.0f, -2.0f, 10, 1, 4);
		S2r4.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r4.setTextureSize(64, 32);
		S2r4.mirror = true;
		setRotation(S2r4, 0.0f, 0.0f, 0.0f);
		(S2r3 = new ModelRenderer(this, 0, 15)).addBox(-6.0f, -11.0f, -2.0f, 1, 3, 4);
		S2r3.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r3.setTextureSize(64, 32);
		S2r3.mirror = true;
		setRotation(S2r3, 0.0f, 0.0f, 0.0f);
		(S2r2 = new ModelRenderer(this, 0, 15)).addBox(-6.0f, 8.0f, -2.0f, 1, 3, 4);
		S2r2.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r2.setTextureSize(64, 32);
		S2r2.mirror = true;
		setRotation(S2r2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Shape1.render(par7);
		S2r.render(par7);
		Shape7.render(par7);
		Shape2.render(par7);
		Shape3.render(par7);
		Shape4.render(par7);
		Shape5.render(par7);
		Shape6.render(par7);
		S2r6.render(par7);
		S2r5.render(par7);
		S2r4.render(par7);
		S2r3.render(par7);
		S2r2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		Shape1.rotateAngleY = par3 * 0.067f * 1.25f;
		Shape2.rotateAngleX = par3 * 0.067f * 1.25f;
		Shape3.rotateAngleX = par3 * 0.067f * 1.25f;
		Shape4.rotateAngleX = par3 * 0.067f * 1.25f;
		Shape5.rotateAngleY = par3 * 0.067f * 1.25f;
		Shape6.rotateAngleY = par3 * 0.067f * 1.25f;
		Shape7.rotateAngleY = par3 * 0.067f * 1.25f;
		S2r.rotateAngleZ = par3 * 0.067f * 3.25f;
		S2r2.rotateAngleZ = par3 * 0.067f * 3.25f;
		S2r3.rotateAngleZ = par3 * 0.067f * 3.25f;
		S2r4.rotateAngleZ = par3 * 0.067f * 3.25f;
		S2r5.rotateAngleZ = par3 * 0.067f * 3.25f;
		S2r6.rotateAngleZ = par3 * 0.067f * 3.25f;
	}
}

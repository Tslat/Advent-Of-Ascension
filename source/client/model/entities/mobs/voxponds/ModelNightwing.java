package net.tslat.aoa3.client.model.entities.mobs.voxponds;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelNightwing extends ModelBase {
	ModelRenderer wingR;
	ModelRenderer Shape2;
	ModelRenderer wingL;
	ModelRenderer wingL2;
	ModelRenderer wingR2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape8;
	ModelRenderer Shape7;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;

	public ModelNightwing() {
		textureWidth = 128;
		textureHeight = 64;
		(wingR = new ModelRenderer(this, 29, 34)).addBox(-15.0f, -4.0f, -5.0f, 14, 4, 0);
		wingR.setRotationPoint(-5.0f, 12.0f, 4.0f);
		wingR.setTextureSize(128, 64);
		wingR.mirror = true;
		setRotation(wingR, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 97, 13)).addBox(0.0f, 5.0f, 0.0f, 8, 1, 6);
		Shape2.setRotationPoint(-4.0f, 9.0f, -12.0f);
		Shape2.setTextureSize(128, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(wingL = new ModelRenderer(this, 29, 34)).addBox(0.0f, -4.0f, -5.0f, 14, 4, 0);
		wingL.setRotationPoint(5.0f, 12.0f, 4.0f);
		wingL.setTextureSize(128, 64);
		wingL.mirror = true;
		setRotation(wingL, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 35, 14)).addBox(0.0f, 0.0f, -5.0f, 14, 2, 12);
		wingL2.setRotationPoint(5.0f, 12.0f, 4.0f);
		wingL2.setTextureSize(128, 64);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 35, 14)).addBox(-15.0f, 0.0f, -5.0f, 14, 2, 12);
		wingR2.setRotationPoint(-5.0f, 12.0f, 4.0f);
		wingR2.setTextureSize(128, 64);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 90, 22)).addBox(0.0f, 0.0f, 0.0f, 8, 6, 8);
		Shape3.setRotationPoint(-4.0f, 10.0f, 8.0f);
		Shape3.setTextureSize(128, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 2, 33)).addBox(1.0f, -2.0f, 6.0f, 4, 4, 8);
		Shape4.setRotationPoint(0.0f, 11.0f, 15.0f);
		Shape4.setTextureSize(128, 64);
		Shape4.mirror = true;
		setRotation(Shape4, -0.8726646f, 0.5235988f, 0.0f);
		(Shape5 = new ModelRenderer(this, 2, 33)).addBox(1.0f, -2.0f, 6.0f, 4, 4, 8);
		Shape5.setRotationPoint(-5.0f, 11.0f, 12.0f);
		Shape5.setTextureSize(128, 64);
		Shape5.mirror = true;
		setRotation(Shape5, -0.8726646f, -0.5235988f, 0.0f);
		(Shape6 = new ModelRenderer(this, 19, 47)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 8);
		Shape6.setRotationPoint(-5.0f, 11.0f, 12.0f);
		Shape6.setTextureSize(128, 64);
		Shape6.mirror = true;
		setRotation(Shape6, -0.4363323f, -0.5235988f, 0.0f);
		(Shape8 = new ModelRenderer(this, 19, 47)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 8);
		Shape8.setRotationPoint(0.0f, 11.0f, 15.0f);
		Shape8.setTextureSize(128, 64);
		Shape8.mirror = true;
		setRotation(Shape8, -0.4363323f, 0.5235988f, 0.0f);
		(Shape7 = new ModelRenderer(this, 77, 1)).addBox(1.0f, 0.0f, 0.0f, 1, 4, 7);
		Shape7.setRotationPoint(1.0f, 10.0f, -11.0f);
		Shape7.setTextureSize(128, 64);
		Shape7.mirror = true;
		setRotation(Shape7, 0.7853982f, 0.0f, 0.0f);
		(Shape9 = new ModelRenderer(this, 97, 1)).addBox(0.0f, 0.0f, 0.0f, 8, 4, 6);
		Shape9.setRotationPoint(-4.0f, 9.0f, -12.0f);
		Shape9.setTextureSize(128, 64);
		Shape9.mirror = true;
		setRotation(Shape9, 0.0f, 0.0f, 0.0f);
		(Shape10 = new ModelRenderer(this, 77, 1)).addBox(1.0f, 0.0f, 0.0f, 1, 4, 7);
		Shape10.setRotationPoint(-4.0f, 10.0f, -11.0f);
		Shape10.setTextureSize(128, 64);
		Shape10.mirror = true;
		setRotation(Shape10, 0.7853982f, 0.0f, 0.0f);
		(Shape11 = new ModelRenderer(this, 68, 37)).addBox(0.0f, 0.0f, 0.0f, 12, 9, 18);
		Shape11.setRotationPoint(-6.0f, 9.0f, -6.0f);
		Shape11.setTextureSize(128, 64);
		Shape11.mirror = true;
		setRotation(Shape11, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		wingR.render(par7);
		Shape2.render(par7);
		wingL.render(par7);
		wingL2.render(par7);
		wingR2.render(par7);
		Shape3.render(par7);
		Shape4.render(par7);
		Shape5.render(par7);
		Shape6.render(par7);
		Shape8.render(par7);
		Shape7.render(par7);
		Shape9.render(par7);
		Shape10.render(par7);
		Shape11.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		wingR.rotateAngleZ = MathHelper.cos(par3 * 0.3f) * 3.1415927f * 0.15f;
		wingL.rotateAngleZ = -wingR.rotateAngleZ;
		wingR2.rotateAngleZ = MathHelper.cos(par3 * 0.3f) * 3.1415927f * 0.15f;
		wingL2.rotateAngleZ = -wingR2.rotateAngleZ;
	}
}

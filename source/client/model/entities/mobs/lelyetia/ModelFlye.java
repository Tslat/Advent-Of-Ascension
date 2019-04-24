package net.tslat.aoa3.client.model.entities.mobs.lelyetia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFlye extends ModelBase {
	ModelRenderer Shape2;
	ModelRenderer wingR;
	ModelRenderer wingL;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer wingR2;
	ModelRenderer wingL2;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;

	public ModelFlye() {
		textureWidth = 128;
		textureHeight = 32;
		(Shape2 = new ModelRenderer(this, 79, 0)).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
		Shape2.setRotationPoint(2.0f, -4.0f, -6.0f);
		Shape2.setTextureSize(128, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(wingR = new ModelRenderer(this, 35, 0)).addBox(-10.0f, 0.0f, -5.0f, 10, 2, 10);
		wingR.setRotationPoint(-14.0f, 1.0f, 0.0f);
		wingR.setTextureSize(128, 32);
		wingR.mirror = true;
		setRotation(wingR, 0.0f, 0.0f, 0.0f);
		(wingL = new ModelRenderer(this, 35, 0)).addBox(0.0f, 0.0f, -5.0f, 10, 2, 10);
		wingL.setRotationPoint(14.0f, 0.0f, 0.0f);
		wingL.setTextureSize(128, 32);
		wingL.mirror = true;
		setRotation(wingL, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 79, 0)).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
		Shape3.setRotationPoint(-14.0f, -4.0f, -6.0f);
		Shape3.setTextureSize(128, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 68, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
		Shape4.setRotationPoint(6.0f, 8.0f, -2.0f);
		Shape4.setTextureSize(128, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 79, 0)).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
		Shape5.setRotationPoint(-14.0f, 12.0f, -6.0f);
		Shape5.setTextureSize(128, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 35, 0)).addBox(-10.0f, 0.0f, -5.0f, 10, 2, 10);
		wingR2.setRotationPoint(-14.0f, 16.0f, 0.0f);
		wingR2.setTextureSize(128, 32);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 35, 0)).addBox(0.0f, 0.0f, -5.0f, 10, 2, 10);
		wingL2.setRotationPoint(14.0f, 15.0f, 0.0f);
		wingL2.setTextureSize(128, 32);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 79, 0)).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
		Shape6.setRotationPoint(2.0f, 12.0f, -6.0f);
		Shape6.setTextureSize(128, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0f, 0.0f, 0.0f);
		(Shape7 = new ModelRenderer(this, 68, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
		Shape7.setRotationPoint(-2.0f, 16.0f, -2.0f);
		Shape7.setTextureSize(128, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0.0f, 0.0f, 0.0f);
		(Shape8 = new ModelRenderer(this, 68, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
		Shape8.setRotationPoint(-2.0f, 0.0f, -2.0f);
		Shape8.setTextureSize(128, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0.0f, 0.0f, 0.0f);
		(Shape9 = new ModelRenderer(this, 68, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 4);
		Shape9.setRotationPoint(-10.0f, 8.0f, -2.0f);
		Shape9.setTextureSize(128, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Shape2.render(par7);
		wingR.render(par7);
		wingL.render(par7);
		Shape3.render(par7);
		Shape4.render(par7);
		Shape5.render(par7);
		wingR2.render(par7);
		wingL2.render(par7);
		Shape6.render(par7);
		Shape7.render(par7);
		Shape8.render(par7);
		Shape9.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		wingR.rotateAngleZ = MathHelper.cos(par3 * 2.1f) * 3.1415927f * 0.15f;
		wingL.rotateAngleZ = -wingR.rotateAngleZ;
		wingR2.rotateAngleZ = MathHelper.cos(par3 * 2.1f) * 3.1415927f * 0.15f;
		wingL2.rotateAngleZ = -wingR2.rotateAngleZ;
	}
}

package net.nevermine.boss.corallus;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelCorallusShot extends ModelBase {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;

	public modelCorallusShot() {
		textureWidth = 64;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 0, 24)).addBox(-8.0f, 0.0f, -3.0f, 2, 1, 1);
		Shape1.setRotationPoint(0.0f, 15.0f, 0.0f);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 0, 24)).addBox(-3.0f, 3.0f, 1.0f, 6, 1, 1);
		Shape2.setRotationPoint(0.0f, 16.0f, 0.0f);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 0, 24)).addBox(-6.0f, -3.0f, -3.0f, 1, 6, 1);
		Shape3.setRotationPoint(0.0f, 15.0f, 0.0f);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6);
		Shape4.setRotationPoint(0.0f, 15.0f, 0.0f);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 0, 24)).addBox(2.0f, 4.0f, 1.0f, 1, 2, 1);
		Shape5.setRotationPoint(0.0f, 16.0f, 0.0f);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 0, 24)).addBox(-3.0f, -6.0f, -2.0f, 6, 1, 1);
		Shape6.setRotationPoint(0.0f, 16.0f, 0.0f);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0f, 0.0f, 0.0f);
		(Shape7 = new ModelRenderer(this, 0, 24)).addBox(-2.0f, -7.0f, -2.0f, 1, 1, 1);
		Shape7.setRotationPoint(0.0f, 16.0f, 0.0f);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0.0f, 0.0f, 0.0f);
		(Shape8 = new ModelRenderer(this, 0, 24)).addBox(-1.0f, 4.0f, 1.0f, 1, 1, 1);
		Shape8.setRotationPoint(0.0f, 16.0f, 0.0f);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0.0f, 0.0f, 0.0f);
		(Shape9 = new ModelRenderer(this, 0, 24)).addBox(5.0f, -3.0f, 1.0f, 1, 6, 1);
		Shape9.setRotationPoint(0.0f, 15.0f, 0.0f);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0.0f, 0.0f, 0.0f);
		(Shape10 = new ModelRenderer(this, 0, 24)).addBox(6.0f, -2.0f, 1.0f, 2, 1, 1);
		Shape10.setRotationPoint(0.0f, 15.0f, 0.0f);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0.0f, 0.0f, 0.0f);
		(Shape11 = new ModelRenderer(this, 0, 24)).addBox(6.0f, 1.0f, 1.0f, 2, 1, 1);
		Shape11.setRotationPoint(0.0f, 15.0f, 0.0f);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Shape1.render(par7);
		Shape2.render(par7);
		Shape3.render(par7);
		Shape4.render(par7);
		Shape5.render(par7);
		Shape6.render(par7);
		Shape7.render(par7);
		Shape8.render(par7);
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
		final ModelRenderer shape1 = Shape1;
		shape1.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		final ModelRenderer shape2 = Shape2;
		shape2.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		final ModelRenderer shape3 = Shape3;
		shape3.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		final ModelRenderer shape4 = Shape4;
		shape4.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		final ModelRenderer shape5 = Shape5;
		shape5.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		final ModelRenderer shape6 = Shape6;
		shape6.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		final ModelRenderer shape7 = Shape7;
		shape7.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		final ModelRenderer shape8 = Shape8;
		shape8.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		final ModelRenderer shape9 = Shape9;
		shape9.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		final ModelRenderer shape10 = Shape10;
		shape10.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		final ModelRenderer shape11 = Shape11;
		shape11.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
	}
}

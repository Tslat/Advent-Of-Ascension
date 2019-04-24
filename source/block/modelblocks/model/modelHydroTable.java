package net.nevermine.block.modelblocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class modelHydroTable extends ModelBase implements ModelEternalBlock {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;

	public modelHydroTable() {
		textureWidth = 64;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 25, 18)).addBox(-4.0f, -3.0f, -4.0f, 8, 4, 8);
		Shape1.setRotationPoint(0.0f, 22.0f, 0.0f);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 0, 0)).addBox(-8.0f, 1.0f, -7.0f, 16, 1, 16);
		Shape2.setRotationPoint(0.0f, 22.0f, 0.0f);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 0, 7)).addBox(4.0f, -14.0f, 5.0f, 4, 2, 4);
		Shape3.setRotationPoint(0.0f, 22.0f, 0.0f);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 0, 18)).addBox(-3.0f, -11.0f, -3.0f, 6, 8, 6);
		Shape4.setRotationPoint(0.0f, 22.0f, 0.0f);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 0, 0)).addBox(-8.0f, -12.0f, -7.0f, 16, 1, 16);
		Shape5.setRotationPoint(0.0f, 22.0f, 0.0f);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 0, 7)).addBox(4.0f, -14.0f, -7.0f, 4, 2, 4);
		Shape6.setRotationPoint(0.0f, 22.0f, 0.0f);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0f, 0.0f, 0.0f);
		(Shape7 = new ModelRenderer(this, 0, 7)).addBox(-8.0f, -14.0f, 5.0f, 4, 2, 4);
		Shape7.setRotationPoint(0.0f, 22.0f, 0.0f);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0.0f, 0.0f, 0.0f);
		(Shape8 = new ModelRenderer(this, 0, 7)).addBox(-8.0f, -14.0f, -7.0f, 4, 2, 4);
		Shape8.setRotationPoint(0.0f, 22.0f, 0.0f);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0.0f, 0.0f, 0.0f);
	}

	public void render(final float f5) {
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
		Shape6.render(f5);
		Shape7.render(f5);
		Shape8.render(f5);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

package net.nevermine.block.modelblocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class ChargingTable extends ModelBase implements ModelEternalBlock {
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

	public ChargingTable() {
		textureWidth = 128;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 67, 0)).addBox(0.0f, 1.0f, 0.0f, 1, 6, 1);
		Shape1.setRotationPoint(4.0f, 15.0f, 4.0f);
		Shape1.setTextureSize(128, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 1.0f, 0.0f, 16, 2, 16);
		Shape2.setRotationPoint(-8.0f, 21.0f, -8.0f);
		Shape2.setTextureSize(128, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 75, 0)).addBox(0.0f, 1.0f, 0.0f, 1, 4, 1);
		Shape3.setRotationPoint(2.0f, 9.0f, 2.0f);
		Shape3.setTextureSize(128, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 67, 0)).addBox(0.0f, 1.0f, 0.0f, 1, 6, 1);
		Shape4.setRotationPoint(-5.0f, 15.0f, 4.0f);
		Shape4.setTextureSize(128, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 67, 0)).addBox(0.0f, 1.0f, 0.0f, 1, 6, 1);
		Shape5.setRotationPoint(4.0f, 15.0f, -5.0f);
		Shape5.setTextureSize(128, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 67, 0)).addBox(0.0f, 1.0f, 0.0f, 1, 6, 1);
		Shape6.setRotationPoint(-5.0f, 15.0f, -5.0f);
		Shape6.setTextureSize(128, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0f, 0.0f, 0.0f);
		(Shape7 = new ModelRenderer(this, 65, 9)).addBox(0.0f, 1.0f, 0.0f, 10, 2, 10);
		Shape7.setRotationPoint(-5.0f, 13.0f, -5.0f);
		Shape7.setTextureSize(128, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0.0f, 0.0f, 0.0f);
		(Shape8 = new ModelRenderer(this, 0, 21)).addBox(0.0f, 1.0f, 0.0f, 6, 2, 6);
		Shape8.setRotationPoint(-3.0f, 7.0f, -3.0f);
		Shape8.setTextureSize(128, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0.0f, 0.0f, 0.0f);
		(Shape9 = new ModelRenderer(this, 75, 0)).addBox(0.0f, 1.0f, 0.0f, 1, 4, 1);
		Shape9.setRotationPoint(-3.0f, 9.0f, 2.0f);
		Shape9.setTextureSize(128, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0.0f, 0.0f, 0.0f);
		(Shape10 = new ModelRenderer(this, 75, 0)).addBox(0.0f, 1.0f, 0.0f, 1, 4, 1);
		Shape10.setRotationPoint(2.0f, 9.0f, -3.0f);
		Shape10.setTextureSize(128, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0.0f, 0.0f, 0.0f);
		(Shape11 = new ModelRenderer(this, 75, 0)).addBox(0.0f, 1.0f, 0.0f, 1, 4, 1);
		Shape11.setRotationPoint(-3.0f, 9.0f, -3.0f);
		Shape11.setTextureSize(128, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0.0f, 0.0f, 0.0f);
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
		Shape9.render(f5);
		Shape10.render(f5);
		Shape11.render(f5);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

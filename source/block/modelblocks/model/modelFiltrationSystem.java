package net.nevermine.block.modelblocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class modelFiltrationSystem extends ModelBase implements ModelEternalBlock {
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
	ModelRenderer Shape12;
	ModelRenderer Shape13;

	public modelFiltrationSystem() {
		textureWidth = 64;
		textureHeight = 64;
		(Shape1 = new ModelRenderer(this, 0, 19)).addBox(-1.0f, -4.0f, -1.0f, 6, 4, 6);
		Shape1.setRotationPoint(-2.0f, 12.0f, -2.0f);
		Shape1.setTextureSize(64, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 32, 44)).addBox(15.0f, -2.0f, 1.0f, 1, 2, 14);
		Shape2.setRotationPoint(-8.0f, 23.0f, -8.0f);
		Shape2.setTextureSize(64, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 35, 27)).addBox(1.0f, 2.0f, -4.0f, 2, 2, 12);
		Shape3.setRotationPoint(-2.0f, 12.0f, -2.0f);
		Shape3.setTextureSize(64, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 0, 34)).addBox(0.0f, -2.0f, 0.0f, 16, 2, 1);
		Shape4.setRotationPoint(-8.0f, 23.0f, -8.0f);
		Shape4.setTextureSize(64, 64);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 0, 39)).addBox(0.0f, -2.0f, 15.0f, 16, 2, 1);
		Shape5.setRotationPoint(-8.0f, 23.0f, -8.0f);
		Shape5.setTextureSize(64, 64);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 0, 44)).addBox(0.0f, -2.0f, 1.0f, 1, 2, 14);
		Shape6.setRotationPoint(-8.0f, 23.0f, -8.0f);
		Shape6.setTextureSize(64, 64);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0f, 0.0f, 0.0f);
		(Shape7 = new ModelRenderer(this, 25, 18)).addBox(0.0f, 0.0f, 0.0f, 4, 11, 4);
		Shape7.setRotationPoint(-2.0f, 12.0f, -2.0f);
		Shape7.setTextureSize(64, 64);
		Shape7.mirror = true;
		setRotation(Shape7, 0.0f, 0.0f, 0.0f);
		(Shape8 = new ModelRenderer(this, 48, 5)).addBox(0.0f, 1.0f, -6.0f, 4, 4, 4);
		Shape8.setRotationPoint(-2.0f, 12.0f, -2.0f);
		Shape8.setTextureSize(64, 64);
		Shape8.mirror = true;
		setRotation(Shape8, 0.0f, 0.0f, 0.0f);
		(Shape9 = new ModelRenderer(this, 17, 51)).addBox(-4.0f, 2.0f, 1.0f, 12, 2, 2);
		Shape9.setRotationPoint(-2.0f, 12.0f, -2.0f);
		Shape9.setTextureSize(64, 64);
		Shape9.mirror = true;
		setRotation(Shape9, 0.0f, 0.0f, 0.0f);
		(Shape10 = new ModelRenderer(this, 48, 5)).addBox(-6.0f, 1.0f, 0.0f, 4, 4, 4);
		Shape10.setRotationPoint(-2.0f, 12.0f, -2.0f);
		Shape10.setTextureSize(64, 64);
		Shape10.mirror = true;
		setRotation(Shape10, 0.0f, 0.0f, 0.0f);
		(Shape11 = new ModelRenderer(this, 48, 5)).addBox(6.0f, 1.0f, 0.0f, 4, 4, 4);
		Shape11.setRotationPoint(-2.0f, 12.0f, -2.0f);
		Shape11.setTextureSize(64, 64);
		Shape11.mirror = true;
		setRotation(Shape11, 0.0f, 0.0f, 0.0f);
		(Shape12 = new ModelRenderer(this, 48, 5)).addBox(0.0f, 1.0f, 6.0f, 4, 4, 4);
		Shape12.setRotationPoint(-2.0f, 12.0f, -2.0f);
		Shape12.setTextureSize(64, 64);
		Shape12.mirror = true;
		setRotation(Shape12, 0.0f, 0.0f, 0.0f);
		(Shape13 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 1, 16);
		Shape13.setRotationPoint(-8.0f, 23.0f, -8.0f);
		Shape13.setTextureSize(64, 64);
		Shape13.mirror = true;
		setRotation(Shape13, 0.0f, 0.0f, 0.0f);
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
		Shape12.render(f5);
		Shape13.render(f5);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

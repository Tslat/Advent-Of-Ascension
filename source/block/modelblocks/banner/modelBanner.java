package net.nevermine.block.modelblocks.banner;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class modelBanner extends ModelBase implements ModelEternalBlock {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;

	public modelBanner() {
		textureWidth = 64;
		textureHeight = 128;
		(Shape1 = new ModelRenderer(this, 0, 11)).addBox(0.0f, 0.0f, 0.0f, 20, 57, 0);
		Shape1.setRotationPoint(-10.0f, -7.0f, 0.0f);
		Shape1.setTextureSize(64, 128);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 5, 5, 5);
		Shape2.setRotationPoint(-16.0f, -10.0f, -2.5f);
		Shape2.setTextureSize(64, 128);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 5, 5, 5);
		Shape3.setRotationPoint(11.0f, -10.0f, -2.5f);
		Shape3.setTextureSize(64, 128);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 0, 84)).addBox(0.0f, 0.0f, 0.0f, 13, 3, 3);
		Shape4.setRotationPoint(0.0f, -9.0f, -1.5f);
		Shape4.setTextureSize(64, 128);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 0, 84)).addBox(0.0f, 0.0f, 0.0f, 13, 3, 3);
		Shape5.setRotationPoint(-13.0f, -9.0f, -1.5f);
		Shape5.setTextureSize(64, 128);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
	}

	public void render(final float f5) {
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		Shape5.render(f5);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

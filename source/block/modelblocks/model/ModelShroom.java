package net.nevermine.block.modelblocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class ModelShroom extends ModelBase implements ModelEternalBlock {
	ModelRenderer Shape1;
	ModelRenderer Shape2;

	public ModelShroom() {
		textureWidth = 128;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 10, 16);
		Shape1.setRotationPoint(-8.0f, 8.0f, -8.0f);
		Shape1.setTextureSize(128, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 66, 0)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
		Shape2.setRotationPoint(-3.0f, 18.0f, -3.0f);
		Shape2.setTextureSize(128, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final float par7) {
		Shape1.render(par7);
		Shape2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

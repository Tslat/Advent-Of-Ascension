package net.nevermine.block.modelblocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class ModelShroomStem extends ModelBase implements ModelEternalBlock {
	ModelRenderer Shape2;

	public ModelShroomStem() {
		textureWidth = 128;
		textureHeight = 32;
		(Shape2 = new ModelRenderer(this, 66, 0)).addBox(0.0f, 0.0f, 0.0f, 6, 16, 6);
		Shape2.setRotationPoint(-3.0f, 8.0f, -3.0f);
		Shape2.setTextureSize(128, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final float f5) {
		Shape2.render(f5);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

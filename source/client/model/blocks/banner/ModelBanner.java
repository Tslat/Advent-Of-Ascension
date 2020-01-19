package net.tslat.aoa3.client.model.blocks.banner;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelBanner extends ModelBase {
	private ModelRenderer shape1;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer shape4;
	private ModelRenderer shape5;

	public ModelBanner() {
		textureWidth = 64;
		textureHeight = 128;
		(shape1 = new ModelRenderer(this, 0, 11)).addBox(0.0f, 0.0f, 0.0f, 20, 57, 0);
		shape1.setRotationPoint(-10.0f, -7.0f, 0.0f);
		shape1.setTextureSize(64, 128);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 5, 5, 5);
		shape2.setRotationPoint(-16.0f, -10.0f, -2.5f);
		shape2.setTextureSize(64, 128);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 5, 5, 5);
		shape3.setRotationPoint(11.0f, -10.0f, -2.5f);
		shape3.setTextureSize(64, 128);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 0, 84)).addBox(0.0f, 0.0f, 0.0f, 13, 3, 3);
		shape4.setRotationPoint(0.0f, -9.0f, -1.5f);
		shape4.setTextureSize(64, 128);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 0, 84)).addBox(0.0f, 0.0f, 0.0f, 13, 3, 3);
		shape5.setRotationPoint(-13.0f, -9.0f, -1.5f);
		shape5.setTextureSize(64, 128);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
	}

	public void render(final float f5) {
		shape1.render(f5);
		shape2.render(f5);
		shape3.render(f5);
		shape4.render(f5);
		shape5.render(f5);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

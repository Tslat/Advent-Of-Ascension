package net.nevermine.block.modelblocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class SkeletorStatue extends ModelBase implements ModelEternalBlock {
	ModelRenderer head1;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer body1;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer body7;

	public SkeletorStatue() {
		textureWidth = 128;
		textureHeight = 32;
		(head1 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head1.setRotationPoint(10.0f, 10.0f, -9.0f);
		head1.setTextureSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leg1.setRotationPoint(-7.0f, 16.0f, 7.0f);
		leg1.setTextureSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leg2.setRotationPoint(7.0f, 16.0f, 7.0f);
		leg2.setTextureSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 18.0f, -2.0f, 4, 8, 4);
		leg3.setRotationPoint(-7.0f, -2.0f, -5.0f);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leg4.setRotationPoint(7.0f, 16.0f, -5.0f);
		leg4.setTextureSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 111, 0)).addBox(-5.0f, -10.0f, -7.0f, 2, 16, 6);
		body1.setRotationPoint(4.0f, 3.0f, 3.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 1.570796f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head2.setRotationPoint(0.0f, 10.0f, -11.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head3.setRotationPoint(-10.0f, 10.0f, -9.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 71, 0)).addBox(-3.0f, -13.0f, -7.0f, 6, 11, 3);
		body2.setRotationPoint(0.0f, 9.0f, -1.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 1.134464f, -0.6632251f, 0.0f);
		(body3 = new ModelRenderer(this, 71, 0)).addBox(-3.0f, -10.0f, -7.0f, 6, 11, 3);
		body3.setRotationPoint(0.0f, 9.0f, -1.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 1.134464f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 71, 0)).addBox(-3.0f, -13.0f, -7.0f, 6, 11, 3);
		body4.setRotationPoint(0.0f, 9.0f, -1.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 1.134464f, 0.6632251f, 0.0f);
		(body5 = new ModelRenderer(this, 33, 0)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body5.setRotationPoint(0.0f, 11.0f, 3.0f);
		body5.setTextureSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 1.570796f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 96, 0)).addBox(-5.0f, -10.0f, -7.0f, 2, 16, 4);
		body6.setRotationPoint(0.0f, 3.0f, 3.0f);
		body6.setTextureSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 1.570796f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 96, 0)).addBox(-5.0f, -10.0f, -7.0f, 2, 16, 4);
		body7.setRotationPoint(8.0f, 3.0f, 3.0f);
		body7.setTextureSize(128, 32);
		body7.mirror = true;
		setRotation(body7, 1.570796f, 0.0f, 0.0f);
	}

	public void render(final float f5) {
		head1.render(f5);
		leg1.render(f5);
		leg2.render(f5);
		leg3.render(f5);
		leg4.render(f5);
		body1.render(f5);
		head2.render(f5);
		head3.render(f5);
		body2.render(f5);
		body3.render(f5);
		body4.render(f5);
		body5.render(f5);
		body6.render(f5);
		body7.render(f5);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

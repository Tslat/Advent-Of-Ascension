package net.nevermine.block.modelblocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class SkelemanStatue extends ModelBase implements ModelEternalBlock {
	ModelRenderer head;
	ModelRenderer body3;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body;
	ModelRenderer body2;
	ModelRenderer head2;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer body7;
	ModelRenderer body8;
	ModelRenderer body9;

	public SkelemanStatue() {
		textureWidth = 64;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(-5.0f, 4.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, -0.1487144f, 0.0f);
		(body3 = new ModelRenderer(this, 35, 1)).addBox(-4.0f, 0.0f, -2.0f, 5, 2, 4);
		body3.setRotationPoint(8.0f, 5.0f, 4.0f);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightarm.setRotationPoint(-10.0f, 5.0f, 0.0f);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftarm.setRotationPoint(10.0f, 5.0f, 0.0f);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, -0.4089647f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg.setRotationPoint(-6.0f, 12.0f, 0.0f);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.3346075f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg.setRotationPoint(6.0f, 12.0f, 0.0f);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, 5.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 32, 3)).addBox(-4.0f, 1.0f, 1.0f, 12, 4, 2);
		body2.setRotationPoint(5.0f, 4.0f, 5.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, -0.7853982f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setRotationPoint(5.0f, 4.0f, 0.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.4089647f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 35, 1)).addBox(-4.0f, 0.0f, -2.0f, 4, 4, 4);
		body4.setRotationPoint(2.0f, 3.0f, 4.0f);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 32, 3)).addBox(-4.0f, -3.0f, 1.0f, 12, 4, 2);
		body5.setRotationPoint(-1.0f, -5.0f, 5.0f);
		body5.setTextureSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 1.570796f);
		(body6 = new ModelRenderer(this, 35, 1)).addBox(-4.0f, 0.0f, -2.0f, 5, 4, 4);
		body6.setRotationPoint(8.0f, 4.0f, 0.0f);
		body6.setTextureSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 35, 1)).addBox(-4.0f, 0.0f, -2.0f, 5, 4, 4);
		body7.setRotationPoint(-5.0f, 4.0f, 0.0f);
		body7.setTextureSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 32, 3)).addBox(-4.0f, -3.0f, 1.0f, 12, 4, 2);
		body8.setRotationPoint(-11.0f, 4.0f, 5.0f);
		body8.setTextureSize(64, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.7853982f);
		(body9 = new ModelRenderer(this, 35, 1)).addBox(-4.0f, 0.0f, -2.0f, 5, 2, 4);
		body9.setRotationPoint(-5.0f, 5.0f, 4.0f);
		body9.setTextureSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0f, 0.0f, 0.0f);
	}

	public void render(final float f5) {
		head.render(f5);
		body3.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
		body.render(f5);
		body2.render(f5);
		head2.render(f5);
		body4.render(f5);
		body5.render(f5);
		body6.render(f5);
		body7.render(f5);
		body8.render(f5);
		body9.render(f5);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

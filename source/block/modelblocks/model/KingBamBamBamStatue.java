package net.nevermine.block.modelblocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class KingBamBamBamStatue extends ModelBase implements ModelEternalBlock {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;

	public KingBamBamBamStatue() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -10.0f, -5.0f, 10, 10, 10);
		head.setRotationPoint(0.0f, 2.0f, 0.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 72, 0)).addBox(-9.0f, 0.0f, -5.0f, 18, 16, 10);
		body.setRotationPoint(0.0f, 2.0f, 0.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 40, 13)).addBox(-5.0f, -2.0f, -3.0f, 6, 12, 6);
		rightarm.setRotationPoint(-10.0f, 4.0f, 0.0f);
		rightarm.setTextureSize(128, 32);
		rightarm.mirror = true;
		setRotation(rightarm, -0.7807508f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 40, 13)).addBox(1.0f, -2.0f, -3.0f, 6, 12, 6);
		leftarm.setRotationPoint(8.0f, 4.0f, 0.0f);
		leftarm.setTextureSize(128, 32);
		leftarm.mirror = true;
		setRotation(leftarm, -0.7807508f, -0.4089647f, 0.0f);
		(rightleg = new ModelRenderer(this, 47, 1)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		rightleg.setRotationPoint(-5.0f, 18.0f, 0.0f);
		rightleg.setTextureSize(128, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 47, 1)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		leftleg.setRotationPoint(5.0f, 18.0f, 0.0f);
		leftleg.setTextureSize(128, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.3346075f, 0.0f, 0.0f);
	}

	public void render(final float f5) {
		head.render(f5);
		body.render(f5);
		rightarm.render(f5);
		leftarm.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

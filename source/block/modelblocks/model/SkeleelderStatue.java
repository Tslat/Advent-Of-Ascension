package net.nevermine.block.modelblocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class SkeleelderStatue extends ModelBase implements ModelEternalBlock {
	ModelRenderer Head;
	ModelRenderer Body;
	ModelRenderer RightArm;
	ModelRenderer LeftArm;
	ModelRenderer RightLeg;
	ModelRenderer LeftLeg;
	ModelRenderer Body2;

	public SkeleelderStatue() {
		textureWidth = 64;
		textureHeight = 32;
		(Head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		Head.setRotationPoint(0.0f, -9.0f, -11.0f);
		Head.setTextureSize(64, 32);
		setRotation(Head, 0.0f, 0.0f, 0.0f);
		(Body = new ModelRenderer(this, 0, 16)).addBox(-4.0f, 0.5f, -2.0f, 10, 12, 4);
		Body.setRotationPoint(-1.0f, -13.0f, -11.0f);
		Body.setTextureSize(64, 32);
		setRotation(Body, 1.396263f, 0.0f, 0.0f);
		(RightArm = new ModelRenderer(this, 56, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 30, 2);
		RightArm.setRotationPoint(-5.0f, -12.0f, -8.0f);
		RightArm.setTextureSize(64, 32);
		setRotation(RightArm, 0.0f, 0.0f, 0.0f);
		(LeftArm = new ModelRenderer(this, 56, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 30, 2);
		LeftArm.setRotationPoint(5.0f, -12.0f, -8.0f);
		LeftArm.setTextureSize(64, 32);
		setRotation(LeftArm, 0.0f, 0.0f, 0.0f);
		(RightLeg = new ModelRenderer(this, 56, 0)).addBox(-1.0f, 0.0f, -1.0f, 2, 30, 2);
		RightLeg.setRotationPoint(-4.0f, -2.0f, 7.0f);
		RightLeg.setTextureSize(64, 32);
		setRotation(RightLeg, 0.1396263f, 0.0f, 0.0f);
		(LeftLeg = new ModelRenderer(this, 56, 0)).addBox(-1.0f, 0.0f, -1.0f, 2, 30, 2);
		LeftLeg.setRotationPoint(5.0f, -2.0f, 7.0f);
		LeftLeg.setTextureSize(64, 32);
		setRotation(LeftLeg, -0.4089647f, 0.0f, 0.0f);
		(Body2 = new ModelRenderer(this, 32, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		Body2.setRotationPoint(0.0f, -11.0f, 0.0f);
		Body2.setTextureSize(64, 32);
		setRotation(Body2, 0.6108652f, 0.0f, 0.0f);
	}

	public void render(final float f5) {
		Head.render(f5);
		Body.render(f5);
		RightArm.render(f5);
		LeftArm.render(f5);
		RightLeg.render(f5);
		LeftLeg.render(f5);
		Body2.render(f5);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

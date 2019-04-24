package net.nevermine.block.modelblocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;
import org.lwjgl.opengl.GL11;

public class CrystocoreStatue extends ModelBase implements ModelEternalBlock {
	ModelRenderer Shape1;
	ModelRenderer Center;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;

	public CrystocoreStatue() {
		textureWidth = 128;
		textureHeight = 128;
		(Shape1 = new ModelRenderer(this, 42, 88)).addBox(-20.0f, 14.0f, -5.0f, 10, 14, 10);
		Shape1.setRotationPoint(0.0f, -4.0f, 0.0f);
		Shape1.setTextureSize(128, 128);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Center = new ModelRenderer(this, 0, 0)).addBox(-10.0f, -10.0f, -10.0f, 20, 20, 20);
		Center.setRotationPoint(0.0f, -5.0f, 0.0f);
		Center.setTextureSize(128, 128);
		Center.mirror = true;
		setRotation(Center, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 0, 42)).addBox(-10.0f, 15.0f, -4.0f, 20, 8, 8);
		Shape2.setRotationPoint(0.0f, -4.0f, 0.0f);
		Shape2.setTextureSize(128, 128);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 0, 88)).addBox(10.0f, 14.0f, -5.0f, 10, 10, 10);
		Shape3.setRotationPoint(0.0f, -4.0f, 0.0f);
		Shape3.setTextureSize(128, 128);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 42, 61)).addBox(10.0f, -29.0f, -5.0f, 10, 14, 10);
		Shape4.setRotationPoint(0.0f, -5.0f, 0.0f);
		Shape4.setTextureSize(128, 128);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 0, 42)).addBox(-10.0f, -24.0f, -4.0f, 20, 8, 8);
		Shape5.setRotationPoint(0.0f, -5.0f, 0.0f);
		Shape5.setTextureSize(128, 128);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 0, 61)).addBox(-20.0f, -25.0f, -5.0f, 10, 10, 10);
		Shape6.setRotationPoint(0.0f, -5.0f, 0.0f);
		Shape6.setTextureSize(128, 128);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0f, 0.0f, 0.0f);
	}

	public void render(final float par7) {
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		Shape1.render(par7);
		Center.render(par7);
		Shape2.render(par7);
		Shape3.render(par7);
		Shape4.render(par7);
		Shape5.render(par7);
		Shape6.render(par7);
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

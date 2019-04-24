package net.nevermine.boss.shadowlord;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class modelShadowlord extends ModelBase {
	ModelRenderer Shape1;
	ModelRenderer Head;
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
	ModelRenderer Shape14;
	ModelRenderer Shape15;

	public modelShadowlord() {
		textureWidth = 128;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 106, 0)).addBox(-1.0f, -3.0f, -3.0f, 2, 14, 8);
		Shape1.setRotationPoint(7.0f, 9.0f, -1.0f);
		Shape1.setTextureSize(128, 32);
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Head = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -8.0f, -3.0f, 10, 8, 6);
		Head.setRotationPoint(0.0f, 17.5f, 0.0f);
		Head.setTextureSize(128, 32);
		setRotation(Head, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 64, 23)).addBox(-6.0f, -3.0f, -6.0f, 3, 3, 6);
		Shape2.setRotationPoint(8.0f, 8.0f, -1.0f);
		Shape2.setTextureSize(128, 32);
		setRotation(Shape2, -1.047198f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 33, 0)).addBox(-6.0f, 0.0f, -8.0f, 12, 2, 8);
		Shape3.setRotationPoint(0.0f, 19.0f, 4.0f);
		Shape3.setTextureSize(128, 32);
		setRotation(Shape3, 0.179193f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 87, 17)).addBox(-1.0f, 0.0f, -3.0f, 2, 8, 6);
		Shape4.setRotationPoint(7.0f, 10.0f, 0.0f);
		Shape4.setTextureSize(128, 32);
		setRotation(Shape4, 0.0f, 0.0f, -0.4363323f);
		(Shape5 = new ModelRenderer(this, 36, 12)).addBox(-1.0f, -3.0f, -2.0f, 6, 3, 3);
		Shape5.setRotationPoint(7.0f, 19.0f, 6.0f);
		Shape5.setTextureSize(128, 32);
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 90, 0)).addBox(-1.0f, 0.0f, -3.0f, 2, 8, 4);
		Shape6.setRotationPoint(7.0f, 10.0f, 1.0f);
		Shape6.setTextureSize(128, 32);
		setRotation(Shape6, 0.0f, 0.0f, -1.832596f);
		(Shape7 = new ModelRenderer(this, 87, 17)).addBox(-1.0f, 0.0f, -3.0f, 2, 8, 6);
		Shape7.setRotationPoint(-7.0f, 10.0f, 0.0f);
		Shape7.setTextureSize(128, 32);
		setRotation(Shape7, 0.0f, 0.0f, 0.4363323f);
		(Shape8 = new ModelRenderer(this, 75, 0)).addBox(-1.0f, 0.0f, -3.0f, 2, 8, 4);
		Shape8.setRotationPoint(-7.0f, 10.0f, 1.0f);
		Shape8.setTextureSize(128, 32);
		setRotation(Shape8, 0.0f, 0.0f, 1.832596f);
		(Shape9 = new ModelRenderer(this, 106, 0)).addBox(-1.0f, -3.0f, -3.0f, 2, 14, 8);
		Shape9.setRotationPoint(-7.0f, 9.0f, -1.0f);
		Shape9.setTextureSize(128, 32);
		setRotation(Shape9, 0.0f, 0.0f, 0.0f);
		(Shape10 = new ModelRenderer(this, 41, 26)).addBox(-1.0f, -3.0f, -2.0f, 7, 2, 3);
		Shape10.setRotationPoint(11.0f, 19.5f, 5.0f);
		Shape10.setTextureSize(128, 32);
		setRotation(Shape10, 0.0f, 0.5235988f, 0.0f);
		(Shape11 = new ModelRenderer(this, 36, 12)).addBox(-1.0f, -3.0f, -2.0f, 6, 3, 3);
		Shape11.setRotationPoint(-11.0f, 19.0f, 6.0f);
		Shape11.setTextureSize(128, 32);
		setRotation(Shape11, 0.0f, 0.0f, 0.0f);
		(Shape12 = new ModelRenderer(this, 41, 20)).addBox(-1.0f, -3.0f, -2.0f, 7, 2, 3);
		Shape12.setRotationPoint(-15.0f, 19.5f, 3.0f);
		Shape12.setTextureSize(128, 32);
		setRotation(Shape12, 0.0f, -0.5235988f, 0.0f);
		(Shape13 = new ModelRenderer(this, 0, 21)).addBox(-6.0f, -3.0f, -3.0f, 12, 1, 8);
		Shape13.setRotationPoint(0.0f, 21.0f, -1.0f);
		Shape13.setTextureSize(128, 32);
		setRotation(Shape13, 0.0f, 0.0f, 0.0f);
		(Shape14 = new ModelRenderer(this, 0, 21)).addBox(-6.0f, -3.0f, -3.0f, 12, 3, 8);
		Shape14.setRotationPoint(0.0f, 9.0f, -1.0f);
		Shape14.setTextureSize(128, 32);
		setRotation(Shape14, 0.0f, 0.0f, 0.0f);
		(Shape15 = new ModelRenderer(this, 63, 13)).addBox(-6.0f, -3.0f, -6.0f, 3, 3, 6);
		Shape15.setRotationPoint(1.0f, 8.0f, -1.0f);
		Shape15.setTextureSize(128, 32);
		setRotation(Shape15, -1.047198f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Shape1.render(par7);
		Head.render(par7);
		Shape2.render(par7);
		Shape3.render(par7);
		Shape4.render(par7);
		Shape5.render(par7);
		Shape6.render(par7);
		Shape7.render(par7);
		Shape8.render(par7);
		Shape9.render(par7);
		Shape10.render(par7);
		Shape11.render(par7);
		Shape12.render(par7);
		Shape13.render(par7);
		Shape14.render(par7);
		Shape15.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		Head.rotateAngleY = par4 / 57.295776f;
		Head.rotateAngleX = par5 / 54.11268f;
	}
}

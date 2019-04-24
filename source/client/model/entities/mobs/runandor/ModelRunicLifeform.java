package net.tslat.aoa3.client.model.entities.mobs.runandor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRunicLifeform extends ModelBase {
	ModelRenderer Shape1;
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

	public ModelRunicLifeform() {
		textureWidth = 128;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 51, 13)).addBox(0.0f, 0.0f, 0.0f, 3, 2, 4);
		Shape1.setRotationPoint(3.0f, 17.0f, -3.0f);
		Shape1.setTextureSize(128, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 51, 22)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 4);
		Shape2.setRotationPoint(6.0f, 6.0f, -3.0f);
		Shape2.setTextureSize(128, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 1);
		Shape3.setRotationPoint(-5.0f, 3.0f, -3.0f);
		Shape3.setTextureSize(128, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 51, 22)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 4);
		Shape4.setRotationPoint(-8.0f, 6.0f, -3.0f);
		Shape4.setTextureSize(128, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 42, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 1, 10);
		Shape5.setRotationPoint(-5.0f, 2.0f, -6.0f);
		Shape5.setTextureSize(128, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 84, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 1, 10);
		Shape6.setRotationPoint(-5.0f, 13.0f, -6.0f);
		Shape6.setTextureSize(128, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0f, 0.0f, 0.0f);
		(Shape7 = new ModelRenderer(this, 73, 23)).addBox(0.0f, 0.0f, 0.0f, 4, 5, 4);
		Shape7.setRotationPoint(-2.0f, 14.0f, -3.0f);
		Shape7.setTextureSize(128, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0.0f, 0.0f, 0.0f);
		(Shape8 = new ModelRenderer(this, 73, 14)).addBox(0.0f, 0.0f, 0.0f, 16, 3, 4);
		Shape8.setRotationPoint(-8.0f, 19.0f, -3.0f);
		Shape8.setTextureSize(128, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0.0f, 0.0f, 0.0f);
		(Shape9 = new ModelRenderer(this, 51, 13)).addBox(0.0f, 0.0f, 0.0f, 3, 2, 4);
		Shape9.setRotationPoint(-6.0f, 17.0f, -3.0f);
		Shape9.setTextureSize(128, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0.0f, 0.0f, 0.0f);
		(Shape10 = new ModelRenderer(this, 25, 10)).addBox(0.0f, 0.0f, 0.0f, 1, 12, 10);
		Shape10.setRotationPoint(5.0f, 2.0f, -6.0f);
		Shape10.setTextureSize(128, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0.0f, 0.0f, 0.0f);
		(Shape11 = new ModelRenderer(this, 2, 10)).addBox(0.0f, 0.0f, 0.0f, 1, 12, 10);
		Shape11.setRotationPoint(-6.0f, 2.0f, -6.0f);
		Shape11.setTextureSize(128, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0.0f, 0.0f, 0.0f);
		(Shape12 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 1);
		Shape12.setRotationPoint(-5.0f, 3.0f, 3.0f);
		Shape12.setTextureSize(128, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Shape1.render(par7);
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
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
	}
}

package net.tslat.aoa3.client.model.entities.mobs.dustopia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDuston extends ModelBase {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;

	public ModelDuston() {
		textureWidth = 64;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 25, 23)).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
		Shape1.setRotationPoint(-0.5f, -4.0f, -0.5f);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0174533f);
		(Shape2 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
		Shape2.setRotationPoint(-5.0f, 5.0f, -5.0f);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 25, 28)).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
		Shape3.setRotationPoint(-0.5f, 21.0f, -0.5f);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 52, 0)).addBox(2.0f, 0.0f, 1.0f, 6, 20, 0);
		Shape4.setRotationPoint(-1.0f, 0.0f, -1.0f);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 5, 23)).addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
		Shape5.setRotationPoint(-1.0f, -1.0f, -1.0f);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 15, 23)).addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
		Shape6.setRotationPoint(-1.0f, 15.0f, -1.0f);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0f, 0.0f, 0.0f);
		(Shape7 = new ModelRenderer(this, 40, 0)).addBox(-4.0f, 0.0f, 1.0f, 6, 20, 0);
		Shape7.setRotationPoint(-3.0f, 0.0f, -1.0f);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0.0f, 0.0f, 0.0f);
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
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
	}
}

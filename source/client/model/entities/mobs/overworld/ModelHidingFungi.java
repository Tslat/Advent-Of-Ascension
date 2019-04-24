package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHidingFungi extends ModelBase {
	ModelRenderer Shape1;
	ModelRenderer Shape2;

	public ModelHidingFungi() {
		textureWidth = 128;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 10, 16);
		Shape1.setRotationPoint(-8.0f, 8.0f, -8.0f);
		Shape1.setTextureSize(128, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 66, 0)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 6);
		Shape2.setRotationPoint(-3.0f, 18.0f, -3.0f);
		Shape2.setTextureSize(128, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Shape1.render(par7);
		Shape2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float f, final float f1, final float f2, final float f3, final float f4, final float f5) {
	}
}

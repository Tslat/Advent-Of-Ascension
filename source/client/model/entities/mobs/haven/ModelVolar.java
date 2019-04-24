package net.tslat.aoa3.client.model.entities.mobs.haven;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelVolar extends ModelBase {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer wingR;
	ModelRenderer Shape3;
	ModelRenderer wingL;

	public ModelVolar() {
		textureWidth = 128;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
		Shape1.setRotationPoint(-4.0f, 11.0f, 9.0f);
		Shape1.setTextureSize(128, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 79, 0)).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
		Shape2.setRotationPoint(-6.0f, 9.0f, -6.0f);
		Shape2.setTextureSize(128, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(wingR = new ModelRenderer(this, 35, 0)).addBox(-10.0f, 0.0f, -5.0f, 10, 2, 10);
		wingR.setRotationPoint(-5.0f, 12.0f, 5.0f);
		wingR.setTextureSize(128, 32);
		wingR.mirror = true;
		setRotation(wingR, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 35, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
		Shape3.setRotationPoint(-5.0f, 10.0f, 2.0f);
		Shape3.setTextureSize(128, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(wingL = new ModelRenderer(this, 35, 0)).addBox(0.0f, 0.0f, -5.0f, 10, 2, 10);
		wingL.setRotationPoint(5.0f, 12.0f, 5.0f);
		wingL.setTextureSize(128, 32);
		wingL.mirror = true;
		setRotation(wingL, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Shape1.render(par7);
		Shape2.render(par7);
		wingR.render(par7);
		Shape3.render(par7);
		wingL.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		wingR.rotateAngleZ = MathHelper.cos(par3 * 0.1f) * 3.1415927f * 0.15f;
		wingL.rotateAngleZ = -wingR.rotateAngleZ;
	}
}

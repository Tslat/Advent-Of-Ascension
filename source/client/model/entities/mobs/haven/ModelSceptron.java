package net.tslat.aoa3.client.model.entities.mobs.haven;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSceptron extends ModelBase {
	ModelRenderer r1;
	ModelRenderer Shape1;
	ModelRenderer r2;
	ModelRenderer r3;
	ModelRenderer r4;
	ModelRenderer r5;
	ModelRenderer r6;

	public ModelSceptron() {
		textureWidth = 64;
		textureHeight = 64;
		(r1 = new ModelRenderer(this, 0, 15)).addBox(-5.0f, 8.0f, -1.0f, 10, 1, 2);
		r1.setRotationPoint(0.0f, 13.0f, 0.0f);
		r1.setTextureSize(64, 64);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(Shape1 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, -2.0f, -1.0f, 4, 4, 2);
		Shape1.setRotationPoint(0.0f, 13.0f, 0.0f);
		Shape1.setTextureSize(64, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 7)).addBox(-3.0f, -5.0f, -1.0f, 6, 1, 2);
		r2.setRotationPoint(0.0f, 13.0f, 0.0f);
		r2.setTextureSize(64, 64);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 0, 7)).addBox(-3.0f, 4.0f, -1.0f, 6, 1, 2);
		r3.setRotationPoint(0.0f, 13.0f, 0.0f);
		r3.setTextureSize(64, 64);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 0, 11)).addBox(-4.0f, 6.0f, -1.0f, 8, 1, 2);
		r4.setRotationPoint(0.0f, 13.0f, 0.0f);
		r4.setTextureSize(64, 64);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 0, 11)).addBox(-4.0f, -7.0f, -1.0f, 8, 1, 2);
		r5.setRotationPoint(0.0f, 13.0f, 0.0f);
		r5.setTextureSize(64, 64);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 0, 15)).addBox(-5.0f, -9.0f, -1.0f, 10, 1, 2);
		r6.setRotationPoint(0.0f, 13.0f, 0.0f);
		r6.setTextureSize(64, 64);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		r1.render(par7);
		Shape1.render(par7);
		r2.render(par7);
		r3.render(par7);
		r4.render(par7);
		r5.render(par7);
		r6.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		r1.rotateAngleZ = par3 * 0.067f * 1.25f;
		r2.rotateAngleZ = par3 * 0.067f * 1.25f;
		r3.rotateAngleZ = par3 * 0.067f * 1.25f;
		r4.rotateAngleZ = par3 * 0.067f * 1.25f;
		r5.rotateAngleZ = par3 * 0.067f * 1.25f;
		r6.rotateAngleZ = par3 * 0.067f * 1.25f;
	}
}

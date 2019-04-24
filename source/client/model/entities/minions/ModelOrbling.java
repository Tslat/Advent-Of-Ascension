package net.tslat.aoa3.client.model.entities.minions;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOrbling extends ModelBase {
	ModelRenderer Shape1;
	ModelRenderer r1;
	ModelRenderer r3;
	ModelRenderer r2;
	ModelRenderer r4;

	public ModelOrbling() {
		textureWidth = 64;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 0, 6)).addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4);
		Shape1.setRotationPoint(0.0f, 15.0f, 0.0f);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 0, 20)).addBox(-5.0f, -5.0f, -3.0f, 10, 2, 0);
		r1.setRotationPoint(0.0f, 15.0f, 0.0f);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 0, 16)).addBox(-5.0f, 3.0f, 3.0f, 10, 2, 0);
		r3.setRotationPoint(0.0f, 15.0f, 0.0f);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -7.0f, -4.0f, 10, 2, 2);
		r2.setRotationPoint(0.0f, 15.0f, 0.0f);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 0, 0)).addBox(-5.0f, 5.0f, 2.0f, 10, 2, 2);
		r4.setRotationPoint(0.0f, 15.0f, 0.0f);
		r4.setTextureSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Shape1.render(par7);
		r1.render(par7);
		r3.render(par7);
		r2.render(par7);
		r4.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		r1.rotateAngleZ = par3 * 0.067f * 5.25f;
		r2.rotateAngleZ = par3 * 0.067f * 5.25f;
		r3.rotateAngleZ = par3 * 0.067f * -5.25f;
		r4.rotateAngleZ = par3 * 0.067f * -5.25f;
	}
}
package net.tslat.aoa3.client.model.entities.misc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBloodlust extends ModelBase {
	private ModelRenderer r1;
	private ModelRenderer r2;
	private ModelRenderer r3;
	private ModelRenderer r4;
	private ModelRenderer r5;
	private ModelRenderer r6;
	private ModelRenderer r7;

	public ModelBloodlust() {
		textureWidth = 64;
		textureHeight = 32;
		(r1 = new ModelRenderer(this, 33, 0)).addBox(-2.0f, -2.0f, 4.0f, 4, 4, 4);
		r1.setRotationPoint(0.0f, 16.0f, 0.0f);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
		r2.setRotationPoint(0.0f, 16.0f, 0.0f);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 33, 0)).addBox(-2.0f, -8.0f, -2.0f, 4, 4, 4);
		r3.setRotationPoint(0.0f, 16.0f, 0.0f);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 33, 0)).addBox(-2.0f, 4.0f, -2.0f, 4, 4, 4);
		r4.setRotationPoint(0.0f, 16.0f, 0.0f);
		r4.setTextureSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 33, 0)).addBox(-8.0f, -2.0f, -2.0f, 4, 4, 4);
		r5.setRotationPoint(0.0f, 16.0f, 0.0f);
		r5.setTextureSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 33, 0)).addBox(4.0f, -2.0f, -2.0f, 4, 4, 4);
		r6.setRotationPoint(0.0f, 16.0f, 0.0f);
		r6.setTextureSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
		(r7 = new ModelRenderer(this, 33, 0)).addBox(-2.0f, -2.0f, -8.0f, 4, 4, 4);
		r7.setRotationPoint(0.0f, 16.0f, 0.0f);
		r7.setTextureSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		r1.render(par7);
		r2.render(par7);
		r3.render(par7);
		r4.render(par7);
		r5.render(par7);
		r6.render(par7);
		r7.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		r1.rotateAngleY = par3 * 0.167f * 1.25f;
		r2.rotateAngleY = par3 * 0.167f * 1.25f;
		r3.rotateAngleY = par3 * 0.167f * 1.25f;
		r4.rotateAngleY = par3 * 0.167f * 1.25f;
		r5.rotateAngleY = par3 * 0.167f * 1.25f;
		r6.rotateAngleY = par3 * 0.167f * 1.25f;
		r7.rotateAngleY = par3 * 0.167f * 1.25f;
	}
}

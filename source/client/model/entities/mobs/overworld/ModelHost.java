package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHost extends ModelBase {
	ModelRenderer Body1;
	ModelRenderer r1;
	ModelRenderer Body2;
	ModelRenderer r2;
	ModelRenderer r3;
	ModelRenderer r4;
	ModelRenderer r5;
	ModelRenderer r6;
	ModelRenderer r7;
	ModelRenderer r8;
	ModelRenderer r9;
	ModelRenderer r10;
	ModelRenderer r11;
	ModelRenderer r12;

	public ModelHost() {
		textureWidth = 64;
		textureHeight = 64;
		(Body1 = new ModelRenderer(this, 0, 21)).addBox(-4.0f, 0.0f, -4.0f, 8, 5, 8);
		Body1.setRotationPoint(0.0f, 17.0f, 0.0f);
		Body1.setTextureSize(64, 64);
		Body1.mirror = true;
		setRotation(Body1, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 0, 49)).addBox(9.0f, 9.0f, 5.0f, 2, 3, 2);
		r1.setRotationPoint(0.0f, 11.0f, 0.0f);
		r1.setTextureSize(64, 64);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(Body2 = new ModelRenderer(this, 0, 0)).addBox(-7.0f, 0.0f, -7.0f, 14, 6, 14);
		Body2.setRotationPoint(0.0f, 11.0f, 0.0f);
		Body2.setTextureSize(64, 64);
		Body2.mirror = true;
		setRotation(Body2, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 42)).addBox(5.0f, 9.0f, -11.0f, 2, 3, 2);
		r2.setRotationPoint(0.0f, 11.0f, 0.0f);
		r2.setTextureSize(64, 64);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 0, 42)).addBox(5.0f, 9.0f, 9.0f, 2, 3, 2);
		r3.setRotationPoint(0.0f, 11.0f, 0.0f);
		r3.setTextureSize(64, 64);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 0, 49)).addBox(-11.0f, 9.0f, 5.0f, 2, 3, 2);
		r4.setRotationPoint(0.0f, 11.0f, 0.0f);
		r4.setTextureSize(64, 64);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 0, 36)).addBox(-9.0f, 7.0f, -11.0f, 18, 2, 2);
		r5.setRotationPoint(0.0f, 11.0f, 0.0f);
		r5.setTextureSize(64, 64);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 0, 36)).addBox(-9.0f, 7.0f, 9.0f, 18, 2, 2);
		r6.setRotationPoint(0.0f, 11.0f, 0.0f);
		r6.setTextureSize(64, 64);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
		(r7 = new ModelRenderer(this, 0, 42)).addBox(-7.0f, 9.0f, -11.0f, 2, 3, 2);
		r7.setRotationPoint(0.0f, 11.0f, 0.0f);
		r7.setTextureSize(64, 64);
		r7.mirror = true;
		setRotation(r7, 0.0f, 0.0f, 0.0f);
		(r8 = new ModelRenderer(this, 0, 42)).addBox(-7.0f, 9.0f, 9.0f, 2, 3, 2);
		r8.setRotationPoint(0.0f, 11.0f, 0.0f);
		r8.setTextureSize(64, 64);
		r8.mirror = true;
		setRotation(r8, 0.0f, 0.0f, 0.0f);
		(r9 = new ModelRenderer(this, 0, 40)).addBox(9.0f, 7.0f, -11.0f, 2, 2, 22);
		r9.setRotationPoint(0.0f, 11.0f, 0.0f);
		r9.setTextureSize(64, 64);
		r9.mirror = true;
		setRotation(r9, 0.0f, 0.0f, 0.0f);
		(r10 = new ModelRenderer(this, 0, 40)).addBox(-11.0f, 7.0f, -11.0f, 2, 2, 22);
		r10.setRotationPoint(0.0f, 11.0f, 0.0f);
		r10.setTextureSize(64, 64);
		r10.mirror = true;
		setRotation(r10, 0.0f, 0.0f, 0.0f);
		(r11 = new ModelRenderer(this, 0, 49)).addBox(9.0f, 9.0f, -7.0f, 2, 3, 2);
		r11.setRotationPoint(0.0f, 11.0f, 0.0f);
		r11.setTextureSize(64, 64);
		r11.mirror = true;
		setRotation(r11, 0.0f, 0.0f, 0.0f);
		(r12 = new ModelRenderer(this, 0, 49)).addBox(-11.0f, 9.0f, -7.0f, 2, 3, 2);
		r12.setRotationPoint(0.0f, 11.0f, 0.0f);
		r12.setTextureSize(64, 64);
		r12.mirror = true;
		setRotation(r12, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Body1.render(par7);
		r1.render(par7);
		Body2.render(par7);
		r2.render(par7);
		r3.render(par7);
		r4.render(par7);
		r5.render(par7);
		r6.render(par7);
		r7.render(par7);
		r8.render(par7);
		r9.render(par7);
		r10.render(par7);
		r11.render(par7);
		r12.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		r1.rotateAngleY = par3 * 0.267f * 1.25f;
		r2.rotateAngleY = par3 * 0.267f * 1.25f;
		r3.rotateAngleY = par3 * 0.267f * 1.25f;
		r4.rotateAngleY = par3 * 0.267f * 1.25f;
		r5.rotateAngleY = par3 * 0.267f * 1.25f;
		r6.rotateAngleY = par3 * 0.267f * 1.25f;
		r7.rotateAngleY = par3 * 0.267f * 1.25f;
		r8.rotateAngleY = par3 * 0.267f * 1.25f;
		r9.rotateAngleY = par3 * 0.267f * 1.25f;
		r10.rotateAngleY = par3 * 0.267f * 1.25f;
		r11.rotateAngleY = par3 * 0.267f * 1.25f;
		r12.rotateAngleY = par3 * 0.267f * 1.25f;
	}
}

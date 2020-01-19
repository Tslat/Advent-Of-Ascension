package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRoloscope extends ModelBase {
	private ModelRenderer r1;
	private ModelRenderer Eye;
	private ModelRenderer r2;
	private ModelRenderer r3;
	private ModelRenderer r4;
	private ModelRenderer r5;
	private ModelRenderer r6;
	private ModelRenderer r7;
	private ModelRenderer r8;
	private ModelRenderer r9;

	public ModelRoloscope() {
		textureWidth = 64;
		textureHeight = 64;
		(r1 = new ModelRenderer(this, 0, 46)).addBox(0.5f, 7.0f, -11.0f, 3, 4, 2);
		r1.setRotationPoint(0.0f, 7.0f, 0.0f);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(Eye = new ModelRenderer(this, 0, 0)).addBox(-5.0f, 0.0f, -4.0f, 10, 10, 10);
		Eye.setRotationPoint(0.0f, 7.0f, 0.0f);
		Eye.setTextureSize(64, 32);
		Eye.mirror = true;
		setRotation(Eye, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 21)).addBox(-7.0f, 11.0f, -5.0f, 14, 2, 13);
		r2.setRotationPoint(0.0f, 7.0f, 0.0f);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 0, 46)).addBox(-7.0f, 7.0f, -11.0f, 3, 4, 2);
		r3.setRotationPoint(0.0f, 7.0f, 0.0f);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 0, 46)).addBox(4.0f, 7.0f, -11.0f, 3, 4, 2);
		r4.setRotationPoint(0.0f, 7.0f, 0.0f);
		r4.setTextureSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 0, 46)).addBox(-3.5f, 7.0f, -11.0f, 3, 4, 2);
		r5.setRotationPoint(0.0f, 7.0f, 0.0f);
		r5.setTextureSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 0, 37)).addBox(4.0f, 11.0f, -11.0f, 3, 2, 6);
		r6.setRotationPoint(0.0f, 7.0f, 0.0f);
		r6.setTextureSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
		(r7 = new ModelRenderer(this, 0, 37)).addBox(0.5f, 11.0f, -11.0f, 3, 2, 6);
		r7.setRotationPoint(0.0f, 7.0f, 0.0f);
		r7.setTextureSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0.0f, 0.0f, 0.0f);
		(r8 = new ModelRenderer(this, 0, 37)).addBox(-3.5f, 11.0f, -11.0f, 3, 2, 6);
		r8.setRotationPoint(0.0f, 7.0f, 0.0f);
		r8.setTextureSize(64, 32);
		r8.mirror = true;
		setRotation(r8, 0.0f, 0.0f, 0.0f);
		(r9 = new ModelRenderer(this, 0, 37)).addBox(-7.0f, 11.0f, -11.0f, 3, 2, 6);
		r9.setRotationPoint(0.0f, 7.0f, 0.0f);
		r9.setTextureSize(64, 32);
		r9.mirror = true;
		setRotation(r9, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		r1.render(par7);
		Eye.render(par7);
		r2.render(par7);
		r3.render(par7);
		r4.render(par7);
		r5.render(par7);
		r6.render(par7);
		r7.render(par7);
		r8.render(par7);
		r9.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		Eye.rotateAngleY = par4 / 57.295776f;
		Eye.rotateAngleX = par5 / 54.11268f;
		r1.rotateAngleY = par3 * 0.067f * 1.25f;
		r2.rotateAngleY = par3 * 0.067f * 1.25f;
		r3.rotateAngleY = par3 * 0.067f * 1.25f;
		r4.rotateAngleY = par3 * 0.067f * 1.25f;
		r5.rotateAngleY = par3 * 0.067f * 1.25f;
		r6.rotateAngleY = par3 * 0.067f * 1.25f;
		r7.rotateAngleY = par3 * 0.067f * 1.25f;
		r8.rotateAngleY = par3 * 0.067f * 1.25f;
		r9.rotateAngleY = par3 * 0.067f * 1.25f;
	}
}

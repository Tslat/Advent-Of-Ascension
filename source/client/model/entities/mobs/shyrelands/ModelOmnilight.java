package net.tslat.aoa3.client.model.entities.mobs.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOmnilight extends ModelBase {
	ModelRenderer r1;
	ModelRenderer Eye;
	ModelRenderer r5;
	ModelRenderer r3;
	ModelRenderer r4;
	ModelRenderer r2;
	ModelRenderer r6;
	ModelRenderer r7;
	ModelRenderer r8;

	public ModelOmnilight() {
		textureWidth = 64;
		textureHeight = 64;

		r1 = new ModelRenderer(this, 7, 19);
		r1.addBox(6.0F, -7.0F, -1.0F, 1, 14, 2);
		r1.setRotationPoint(0.0F, 16.0F, 0.0F);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0F, 0.0F, 0.0F);
		Eye = new ModelRenderer(this, 0, 0);
		Eye.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4);
		Eye.setRotationPoint(0.0F, 16.0F, 0.0F);
		Eye.setTextureSize(64, 32);
		Eye.mirror = true;
		setRotation(Eye, 0.0F, 0.0F, 0.0F);
		r5 = new ModelRenderer(this, 36, 25);
		r5.addBox(-1.0F, -4.0F, 4.0F, 2, 8, 1);
		r5.setRotationPoint(0.0F, 16.0F, 0.0F);
		r5.setTextureSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0.0F, 0.0F, 0.0F);
		r3 = new ModelRenderer(this, 0, 15);
		r3.addBox(-6.0F, 6.0F, -1.0F, 12, 1, 2);
		r3.setRotationPoint(0.0F, 16.0F, 0.0F);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0F, 0.0F, 0.0F);
		r4 = new ModelRenderer(this, 0, 19);
		r4.addBox(-7.0F, -7.0F, -1.0F, 1, 14, 2);
		r4.setRotationPoint(0.0F, 16.0F, 0.0F);
		r4.setTextureSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0F, 0.0F, 0.0F);
		r2 = new ModelRenderer(this, 0, 10);
		r2.addBox(-6.0F, -7.0F, -1.0F, 12, 1, 2);
		r2.setRotationPoint(0.0F, 16.0F, 0.0F);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0F, 0.0F, 0.0F);
		r6 = new ModelRenderer(this, 29, 1);
		r6.addBox(-1.0F, -5.0F, -5.0F, 2, 1, 10);
		r6.setRotationPoint(0.0F, 16.0F, 0.0F);
		r6.setTextureSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0.0F, 0.0F, 0.0F);
		r7 = new ModelRenderer(this, 29, 13);
		r7.addBox(-1.0F, 4.0F, -5.0F, 2, 1, 10);
		r7.setRotationPoint(0.0F, 16.0F, 0.0F);
		r7.setTextureSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0.0F, 0.0F, 0.0F);
		r8 = new ModelRenderer(this, 29, 25);
		r8.addBox(-1.0F, -4.0F, -5.0F, 2, 8, 1);
		r8.setRotationPoint(0.0F, 16.0F, 0.0F);
		r8.setTextureSize(64, 32);
		r8.mirror = true;
		setRotation(r8, 0.0F, 0.0F, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		r1.render(par7);
		Eye.render(par7);
		r5.render(par7);
		r3.render(par7);
		r4.render(par7);
		r2.render(par7);
		r6.render(par7);
		r7.render(par7);
		r8.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		r1.rotateAngleY = (par3 * 0.067F * 1.25F);
		r2.rotateAngleY = (par3 * 0.067F * 1.25F);
		r3.rotateAngleY = (par3 * 0.067F * 1.25F);
		r4.rotateAngleY = (par3 * 0.067F * 1.25F);
		r5.rotateAngleY = (par3 * -0.167F * 1.25F);
		r6.rotateAngleY = (par3 * -0.167F * 1.25F);
		r7.rotateAngleY = (par3 * -0.167F * 1.25F);
		r8.rotateAngleY = (par3 * -0.167F * 1.25F);
	}
}

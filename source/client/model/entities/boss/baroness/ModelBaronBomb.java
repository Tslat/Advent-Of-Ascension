package net.tslat.aoa3.client.model.entities.boss.baroness;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBaronBomb extends ModelBase {
	ModelRenderer r5;
	ModelRenderer r4;
	ModelRenderer r3;
	ModelRenderer r2;
	ModelRenderer r1;
	ModelRenderer Shape1;
	ModelRenderer r6;
	ModelRenderer r7;
	ModelRenderer r8;

	public ModelBaronBomb() {
		textureWidth = 64;
		textureHeight = 32;

		r5 = new ModelRenderer(this, 0, 17);
		r5.addBox(6F, -6F, -2F, 4, 4, 4);
		r5.setRotationPoint(0F, 0F, 0F);
		r5.setTextureSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0F, 0F, 0F);
		r4 = new ModelRenderer(this, 34, 0);
		r4.addBox(1F, -13F, 1F, 3, 4, 3);
		r4.setRotationPoint(0F, 0F, 0F);
		r4.setTextureSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0F, 0F, 0F);
		r3 = new ModelRenderer(this, 0, 17);
		r3.addBox(-2F, -6F, -10F, 4, 4, 4);
		r3.setRotationPoint(0F, 0F, 0F);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0F, 0F, 0F);
		r2 = new ModelRenderer(this, 0, 17);
		r2.addBox(-10F, -6F, -2F, 4, 4, 4);
		r2.setRotationPoint(0F, 0F, 0F);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0F, 0F, 0F);
		r1 = new ModelRenderer(this, 0, 17);
		r1.addBox(-2F, -6F, 6F, 4, 4, 4);
		r1.setRotationPoint(0F, 0F, 0F);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0F, 0F, 0F);
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-4F, -8F, -4F, 8, 8, 8);
		Shape1.setRotationPoint(0F, 0F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		r6 = new ModelRenderer(this, 34, 0);
		r6.addBox(1F, -13F, -4F, 3, 4, 3);
		r6.setRotationPoint(0F, 0F, 0F);
		r6.setTextureSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0F, 0F, 0F);
		r7 = new ModelRenderer(this, 34, 0);
		r7.addBox(-4F, -13F, 1F, 3, 4, 3);
		r7.setRotationPoint(0F, 0F, 0F);
		r7.setTextureSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0F, 0F, 0F);
		r8 = new ModelRenderer(this, 34, 0);
		r8.addBox(-4F, -13F, -4F, 3, 4, 3);
		r8.setRotationPoint(0F, 0F, 0F);
		r8.setTextureSize(64, 32);
		r8.mirror = true;
		setRotation(r8, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		r5.render(par7);
		r4.render(par7);
		r3.render(par7);
		r2.render(par7);
		r1.render(par7);
		Shape1.render(par7);
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
		this.r1.rotateAngleY = (par3 * 0.067F) * 1.25F;
		this.r2.rotateAngleY = (par3 * 0.067F) * 1.25F;

		this.r3.rotateAngleY = (par3 * 0.067F) * 1.25F;
		this.r4.rotateAngleY = (par3 * 0.067F) * 1.25F;

		this.r5.rotateAngleY = (par3 * 0.067F) * 1.25F;
		this.r6.rotateAngleY = (par3 * 0.067F) * 1.25F;

		this.r7.rotateAngleY = (par3 * 0.067F) * 1.25F;
		this.r8.rotateAngleY = (par3 * 0.067F) * 1.25F;
	}
}

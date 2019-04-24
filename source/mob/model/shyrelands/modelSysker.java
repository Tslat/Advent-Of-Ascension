package net.nevermine.mob.model.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelSysker extends ModelBase {
	ModelRenderer r1;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer head;
	ModelRenderer r2;
	ModelRenderer r3;
	ModelRenderer r4;
	ModelRenderer r5;
	ModelRenderer r6;
	ModelRenderer r7;
	ModelRenderer r8;
	ModelRenderer r9;
	ModelRenderer r10;

	public modelSysker() {
		textureWidth = 64;
		textureHeight = 64;

		r1 = new ModelRenderer(this, 43, 0);
		r1.addBox(6.0F, -3.0F, -2.0F, 4, 4, 4);
		r1.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0F, 0.0F, 0.0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		rightleg.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0F, 0.0F, 0.0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		leftleg.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0F, 0.0F, 0.0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
		head.setRotationPoint(0.0F, 9.466666F, 0.0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		r2 = new ModelRenderer(this, 33, 11);
		r2.addBox(9.0F, -7.0F, -1.0F, 2, 1, 2);
		r2.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0F, 0.0F, 0.0F);
		r3 = new ModelRenderer(this, 43, 0);
		r3.addBox(-10.0F, -3.0F, -2.0F, 4, 4, 4);
		r3.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0F, 0.0F, 0.0F);
		r4 = new ModelRenderer(this, 33, 11);
		r4.addBox(-11.0F, -7.0F, -1.0F, 2, 1, 2);
		r4.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r4.setTextureSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0F, 0.0F, 0.0F);
		r5 = new ModelRenderer(this, 33, 0);
		r5.addBox(7.0F, -11.0F, -1.0F, 2, 8, 2);
		r5.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r5.setTextureSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0.0F, 0.0F, 0.0F);
		r6 = new ModelRenderer(this, 33, 0);
		r6.addBox(-9.0F, -11.0F, -1.0F, 2, 8, 2);
		r6.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r6.setTextureSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0.0F, 0.0F, 0.0F);
		r7 = new ModelRenderer(this, 33, 11);
		r7.addBox(-11.0F, -11.0F, -1.0F, 2, 1, 2);
		r7.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r7.setTextureSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0.0F, 0.0F, 0.0F);
		r8 = new ModelRenderer(this, 33, 11);
		r8.addBox(9.0F, -11.0F, -1.0F, 2, 1, 2);
		r8.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r8.setTextureSize(64, 32);
		r8.mirror = true;
		setRotation(r8, 0.0F, 0.0F, 0.0F);
		r9 = new ModelRenderer(this, 33, 11);
		r9.addBox(-11.0F, -9.0F, -1.0F, 2, 1, 2);
		r9.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r9.setTextureSize(64, 32);
		r9.mirror = true;
		setRotation(r9, 0.0F, 0.0F, 0.0F);
		r10 = new ModelRenderer(this, 33, 11);
		r10.addBox(9.0F, -9.0F, -1.0F, 2, 1, 2);
		r10.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r10.setTextureSize(64, 32);
		r10.mirror = true;
		setRotation(r10, 0.0F, 0.0F, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		r1.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		head.render(par7);
		r2.render(par7);
		r3.render(par7);
		r4.render(par7);
		r5.render(par7);
		r6.render(par7);
		r7.render(par7);
		r8.render(par7);
		r9.render(par7);
		r10.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		r1.rotateAngleY = (par3 * 0.067F * 4.25F);
		r2.rotateAngleY = (par3 * 0.067F * 4.25F);
		r3.rotateAngleY = (par3 * 0.067F * 4.25F);
		r4.rotateAngleY = (par3 * 0.067F * 4.25F);
		r5.rotateAngleY = (par3 * 0.067F * 4.25F);
		r6.rotateAngleY = (par3 * 0.067F * 4.25F);
		r7.rotateAngleY = (par3 * 0.067F * 4.25F);
		r8.rotateAngleY = (par3 * 0.067F * 4.25F);
		r9.rotateAngleY = (par3 * 0.067F * 4.25F);
		r10.rotateAngleY = (par3 * 0.067F * 4.25F);

		rightleg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightleg.rotateAngleY = 0.0F;

		leftleg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);

		head.rotateAngleY = (par4 / 57.295776F);
		head.rotateAngleX = (par5 / 54.11268F);
	}
}

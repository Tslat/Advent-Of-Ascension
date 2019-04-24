package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelMuckopede extends ModelBase {
	ModelRenderer head;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg4;
	ModelRenderer leg3;
	ModelRenderer body;
	ModelRenderer body2;
	ModelRenderer leg5;
	ModelRenderer leg7;
	ModelRenderer leg9;
	ModelRenderer leg6;
	ModelRenderer leg8;
	ModelRenderer leg10;
	ModelRenderer leg12;
	ModelRenderer leg11;

	public ModelMuckopede() {
		textureWidth = 64;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 14.0f, -15.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg1.setRotationPoint(-7.0f, 18.0f, 4.0f);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg2.setRotationPoint(7.0f, 18.0f, 4.0f);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leg4.setRotationPoint(7.0f, 16.0f, 16.0f);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leg3.setRotationPoint(-7.0f, 16.0f, 16.0f);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 28, 8)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body.setRotationPoint(0.0f, 15.0f, 10.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 28, 8)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body2.setRotationPoint(0.0f, 15.0f, -6.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(leg5 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg5.setRotationPoint(-7.0f, 18.0f, -14.0f);
		leg5.setTextureSize(64, 32);
		leg5.mirror = true;
		setRotation(leg5, 0.0f, 0.0f, 0.0f);
		(leg7 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg7.setRotationPoint(-7.0f, 18.0f, -8.0f);
		leg7.setTextureSize(64, 32);
		leg7.mirror = true;
		setRotation(leg7, 0.0f, 0.0f, 0.0f);
		(leg9 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg9.setRotationPoint(-7.0f, 18.0f, -2.0f);
		leg9.setTextureSize(64, 32);
		leg9.mirror = true;
		setRotation(leg9, 0.0f, 0.0f, 0.0f);
		(leg6 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg6.setRotationPoint(7.0f, 18.0f, -14.0f);
		leg6.setTextureSize(64, 32);
		leg6.mirror = true;
		setRotation(leg6, 0.0f, 0.0f, 0.0f);
		(leg8 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg8.setRotationPoint(7.0f, 18.0f, -8.0f);
		leg8.setTextureSize(64, 32);
		leg8.mirror = true;
		setRotation(leg8, 0.0f, 0.0f, 0.0f);
		(leg10 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg10.setRotationPoint(7.0f, 18.0f, -2.0f);
		leg10.setTextureSize(64, 32);
		leg10.mirror = true;
		setRotation(leg10, 0.0f, 0.0f, 0.0f);
		(leg12 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg12.setRotationPoint(7.0f, 18.0f, 10.0f);
		leg12.setTextureSize(64, 32);
		leg12.mirror = true;
		setRotation(leg12, 0.0f, 0.0f, 0.0f);
		(leg11 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg11.setRotationPoint(-7.0f, 18.0f, 10.0f);
		leg11.setTextureSize(64, 32);
		leg11.mirror = true;
		setRotation(leg11, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg4.render(par7);
		leg3.render(par7);
		body.render(par7);
		body2.render(par7);
		leg5.render(par7);
		leg7.render(par7);
		leg9.render(par7);
		leg6.render(par7);
		leg8.render(par7);
		leg10.render(par7);
		leg12.render(par7);
		leg11.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg1.rotateAngleY = 0.0f;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3.rotateAngleY = 0.0f;
		leg5.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg5.rotateAngleY = 0.0f;
		leg7.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg7.rotateAngleY = 0.0f;
		leg9.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg9.rotateAngleY = 0.0f;
		leg11.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg11.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg6.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg8.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg10.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg12.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

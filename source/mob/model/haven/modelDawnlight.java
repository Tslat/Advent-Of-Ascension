package net.nevermine.mob.model.haven;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelDawnlight extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer horn1;
	ModelRenderer horn2;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer body7;

	public modelDawnlight() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head.setRotationPoint(0.0f, 6.0f, -8.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 18, 51)).addBox(-6.0f, -7.0f, -6.0f, 6, 1, 8);
		body.setRotationPoint(1.0f, 20.0f, 14.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, -0.7853982f, -0.6981317f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
		leg1.setRotationPoint(-3.0f, 12.0f, 7.0f);
		leg1.setTextureSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
		leg2.setRotationPoint(3.0f, 12.0f, 7.0f);
		leg2.setTextureSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
		leg3.setRotationPoint(-3.0f, 12.0f, -5.0f);
		leg3.setTextureSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
		leg4.setRotationPoint(3.0f, 12.0f, -5.0f);
		leg4.setTextureSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(horn1 = new ModelRenderer(this, 17, 34)).addBox(-4.0f, -5.0f, -4.0f, 2, 2, 2);
		horn1.setRotationPoint(0.0f, 5.0f, -7.0f);
		horn1.setTextureSize(64, 64);
		horn1.mirror = true;
		setRotation(horn1, 0.0f, 0.0f, 0.0f);
		(horn2 = new ModelRenderer(this, 17, 34)).addBox(2.0f, -5.0f, -4.0f, 2, 2, 2);
		horn2.setRotationPoint(0.0f, 5.0f, -7.0f);
		horn2.setTextureSize(64, 64);
		horn2.mirror = true;
		setRotation(horn2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 18, 33)).addBox(-6.0f, -2.0f, -7.0f, 8, 3, 8);
		body2.setRotationPoint(2.0f, 12.0f, 14.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, -0.3490659f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 18, 51)).addBox(-6.0f, -7.0f, -7.0f, 6, 1, 8);
		body3.setRotationPoint(4.0f, 21.0f, 11.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, -0.7853982f, 0.6981317f, 0.0f);
		(body4 = new ModelRenderer(this, 18, 9)).addBox(-6.0f, -10.0f, -7.0f, 12, 18, 6);
		body4.setRotationPoint(0.0f, 5.0f, 2.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 1.570796f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 0, 44)).addBox(0.0f, -10.0f, -7.0f, 2, 9, 5);
		body5.setRotationPoint(-1.0f, 11.0f, 10.0f);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, -0.4363323f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 0, 44)).addBox(0.0f, -10.0f, -7.0f, 2, 9, 5);
		body6.setRotationPoint(-1.0f, 7.0f, 2.0f);
		body6.setTextureSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0.4363323f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 0, 44)).addBox(0.0f, -10.0f, -7.0f, 2, 9, 5);
		body7.setRotationPoint(-1.0f, 8.0f, 6.5f);
		body7.setTextureSize(64, 64);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		horn1.render(par7);
		horn2.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
		body7.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 79.57747f;
		head.rotateAngleX = par5 / 76.39437f;
		horn1.rotateAngleY = par4 / 79.57747f;
		horn1.rotateAngleX = par5 / 76.39437f;
		horn2.rotateAngleY = par4 / 79.57747f;
		horn2.rotateAngleX = par5 / 76.39437f;
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
	}
}

package net.nevermine.minion.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelHorntail extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer head2;

	public modelHorntail() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 32)).addBox(-7.0f, -10.0f, -4.0f, 14, 5, 8);
		head.setRotationPoint(0.0f, 4.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 40, 47)).addBox(-1.0f, 0.0f, -2.0f, 2, 7, 2);
		body.setRotationPoint(3.0f, 22.0f, 4.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 39, 0)).addBox(-1.0f, -1.0f, -2.0f, 2, 8, 4);
		rightarm.setRotationPoint(-5.0f, 5.0f, 0.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 39, 0)).addBox(-1.0f, -1.0f, -2.0f, 2, 8, 4);
		leftarm.setRotationPoint(5.0f, 5.0f, 0.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 15)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg.setRotationPoint(-6.0f, 12.0f, 0.0f);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 15)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg.setRotationPoint(6.0f, 12.0f, 0.0f);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 32, 12)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 7);
		body2.setRotationPoint(0.0f, 4.0f, -1.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 16, 46)).addBox(-4.0f, 0.0f, -2.0f, 6, 6, 5);
		body3.setRotationPoint(1.0f, 15.0f, 0.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.3839724f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 46, 37)).addBox(-4.0f, 0.0f, -2.0f, 4, 5, 3);
		body4.setRotationPoint(2.0f, 19.0f, 2.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.8726646f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 40, 47)).addBox(-7.0f, 0.0f, -2.0f, 2, 7, 2);
		body5.setRotationPoint(3.0f, 22.0f, 4.0f);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 1.570796f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -5.0f, -4.0f, 8, 5, 8);
		head2.setRotationPoint(0.0f, 4.0f, 0.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		head2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
		head2.rotateAngleY = par4 / (float)(180f / Math.PI);
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		rightarm.rotateAngleY = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftarm.rotateAngleY = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftleg.rotateAngleY = 0.0f;
	}
}

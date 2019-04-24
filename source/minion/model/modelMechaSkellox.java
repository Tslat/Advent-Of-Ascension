package net.nevermine.minion.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelMechaSkellox extends ModelBase {
	ModelRenderer head;
	ModelRenderer body1;
	ModelRenderer rightleg;
	ModelRenderer leftleg1;
	ModelRenderer rightleg2;
	ModelRenderer leftleg2;
	ModelRenderer body2;
	ModelRenderer leftarm2;
	ModelRenderer rightarm2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer leftleg2p2;
	ModelRenderer leftleg1p2;
	ModelRenderer rightlegp2;
	ModelRenderer rightleg2p2;

	public modelMechaSkellox() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, -6.0f, -5.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 68, 0)).addBox(-4.0f, 0.0f, 3.0f, 10, 8, 18);
		body1.setRotationPoint(-1.0f, 4.0f, -10.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 58, 20)).addBox(0.0f, -2.0f, -1.0f, 2, 2, 2);
		rightleg.setRotationPoint(-5.0f, 14.0f, 8.0f);
		rightleg.setTextureSize(128, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg1 = new ModelRenderer(this, 58, 20)).addBox(-2.0f, -2.0f, -1.0f, 2, 2, 2);
		leftleg1.setRotationPoint(5.0f, 14.0f, 8.0f);
		leftleg1.setTextureSize(128, 32);
		leftleg1.mirror = true;
		setRotation(leftleg1, 0.0f, 0.0f, 0.0f);
		(rightleg2 = new ModelRenderer(this, 58, 20)).addBox(0.0f, -2.0f, -1.0f, 2, 2, 2);
		rightleg2.setRotationPoint(-5.0f, 14.0f, -3.0f);
		rightleg2.setTextureSize(128, 32);
		rightleg2.mirror = true;
		setRotation(rightleg2, 0.0f, 0.0f, 0.0f);
		(leftleg2 = new ModelRenderer(this, 58, 20)).addBox(-2.0f, -2.0f, -1.0f, 2, 2, 2);
		leftleg2.setRotationPoint(5.0f, 14.0f, -3.0f);
		leftleg2.setTextureSize(128, 32);
		leftleg2.mirror = true;
		setRotation(leftleg2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 47, 19)).addBox(5.0f, 0.0f, -1.0f, 2, 2, 2);
		body2.setRotationPoint(-6.0f, -6.0f, -5.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 52, 0)).addBox(-1.0f, -2.0f, -2.0f, 4, 14, 4);
		leftarm2.setRotationPoint(8.0f, -2.0f, -5.0f);
		leftarm2.setTextureSize(128, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 52, 0)).addBox(-3.0f, -2.0f, -2.0f, 4, 14, 4);
		rightarm2.setRotationPoint(-8.0f, -2.0f, -5.0f);
		rightarm2.setTextureSize(128, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 19, 18)).addBox(-4.0f, 0.0f, -2.0f, 8, 8, 4);
		body3.setRotationPoint(0.0f, -4.0f, -5.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 47, 19)).addBox(-7.0f, 0.0f, -1.0f, 3, 2, 2);
		body4.setRotationPoint(0.0f, -4.0f, -5.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 47, 19)).addBox(4.0f, 0.0f, -1.0f, 3, 2, 2);
		body5.setRotationPoint(0.0f, -4.0f, -5.0f);
		body5.setTextureSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(leftleg2p2 = new ModelRenderer(this, 0, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		leftleg2p2.setRotationPoint(5.0f, 14.0f, -3.0f);
		leftleg2p2.setTextureSize(128, 32);
		leftleg2p2.mirror = true;
		setRotation(leftleg2p2, 0.0f, 0.0f, 0.0f);
		(leftleg1p2 = new ModelRenderer(this, 0, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		leftleg1p2.setRotationPoint(5.0f, 14.0f, 8.0f);
		leftleg1p2.setTextureSize(128, 32);
		leftleg1p2.mirror = true;
		setRotation(leftleg1p2, 0.0f, 0.0f, 0.0f);
		(rightlegp2 = new ModelRenderer(this, 0, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		rightlegp2.setRotationPoint(-5.0f, 14.0f, 8.0f);
		rightlegp2.setTextureSize(128, 32);
		rightlegp2.mirror = true;
		setRotation(rightlegp2, 0.0f, 0.0f, 0.0f);
		(rightleg2p2 = new ModelRenderer(this, 0, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		rightleg2p2.setRotationPoint(-5.0f, 14.0f, -3.0f);
		rightleg2p2.setTextureSize(128, 32);
		rightleg2p2.mirror = true;
		setRotation(rightleg2p2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body1.render(par7);
		rightleg.render(par7);
		leftleg1.render(par7);
		rightleg2.render(par7);
		leftleg2.render(par7);
		body2.render(par7);
		leftarm2.render(par7);
		rightarm2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		leftleg2p2.render(par7);
		leftleg1p2.render(par7);
		rightlegp2.render(par7);
		rightleg2p2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
		leftleg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftleg1.rotateAngleY = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg1p2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftleg1p2.rotateAngleY = 0.0f;
		rightlegp2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightlegp2.rotateAngleY = 0.0f;
		leftleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		rightleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftleg2p2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		rightleg2p2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

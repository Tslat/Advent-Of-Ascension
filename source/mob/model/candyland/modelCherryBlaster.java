package net.nevermine.mob.model.candyland;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelCherryBlaster extends ModelBase {
	ModelRenderer body;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer body7;

	public modelCherryBlaster() {
		textureWidth = 64;
		textureHeight = 64;
		(body = new ModelRenderer(this, 32, 30)).addBox(0.0f, 0.0f, -3.0f, 4, 5, 12);
		body.setRotationPoint(3.0f, 11.0f, -3.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 4, 4);
		rightleg.setRotationPoint(-3.0f, 20.0f, 0.0f);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 4, 4);
		leftleg.setRotationPoint(3.0f, 20.0f, 0.0f);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 15)).addBox(-4.0f, 0.0f, -2.0f, 10, 4, 10);
		body2.setRotationPoint(-1.0f, 16.0f, -3.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 6, 11)).addBox(0.0f, -12.0f, 2.0f, 2, 2, 2);
		body3.setRotationPoint(-1.0f, 7.0f, -3.0f);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 0, 49)).addBox(-2.0f, 0.0f, 5.0f, 6, 5, 4);
		body4.setRotationPoint(-1.0f, 11.0f, -3.0f);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 0, 30)).addBox(-6.0f, 0.0f, -3.0f, 4, 5, 12);
		body5.setRotationPoint(-1.0f, 11.0f, -3.0f);
		body5.setTextureSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 16, 0)).addBox(-4.0f, 0.0f, -2.0f, 10, 4, 10);
		body6.setRotationPoint(-1.0f, 7.0f, -3.0f);
		body6.setTextureSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 1, 11)).addBox(0.5333334f, -10.0f, 2.5f, 1, 10, 1);
		body7.setRotationPoint(-1.0f, 7.0f, -3.0f);
		body7.setTextureSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
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
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

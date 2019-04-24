package net.nevermine.mob.model.gardencia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelCarrotop extends ModelBase {
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer body7;
	ModelRenderer body8;
	ModelRenderer body9;
	ModelRenderer body10;
	ModelRenderer leftleg2;
	ModelRenderer rightleg2;
	ModelRenderer body11;
	ModelRenderer body12;
	ModelRenderer leftleg3;
	ModelRenderer rightleg3;

	public modelCarrotop() {
		textureWidth = 64;
		textureHeight = 32;
		(rightleg = new ModelRenderer(this, 19, 16)).addBox(-2.0f, -4.0f, -2.0f, 4, 4, 4);
		rightleg.setRotationPoint(-5.0f, 10.0f, 0.0f);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 19, 16)).addBox(-2.0f, -4.0f, -2.0f, 4, 4, 4);
		leftleg.setRotationPoint(5.0f, 10.0f, 0.0f);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-1.5f, -15.0f, -1.5f, 3, 3, 3);
		body.setRotationPoint(0.0f, 4.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 0, 6)).addBox(1.0f, 10.0f, -1.0f, 2, 2, 2);
		body2.setRotationPoint(0.0f, 4.0f, 0.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 10, 16)).addBox(-3.5f, -3.0f, -3.5f, 7, 3, 7);
		body3.setRotationPoint(0.0f, 4.0f, 0.0f);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 10, 16)).addBox(-3.0f, -6.0f, -3.0f, 6, 3, 6);
		body4.setRotationPoint(0.0f, 4.0f, 0.0f);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 16, 16)).addBox(-1.0f, -18.0f, -1.0f, 2, 3, 2);
		body5.setRotationPoint(0.0f, 4.0f, 0.0f);
		body5.setTextureSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 16, 16)).addBox(-2.5f, -9.0f, -2.5f, 5, 3, 5);
		body6.setRotationPoint(0.0f, 4.0f, 0.0f);
		body6.setTextureSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 16, 16)).addBox(-2.0f, -12.0f, -2.0f, 4, 3, 4);
		body7.setRotationPoint(0.0f, 4.0f, 0.0f);
		body7.setTextureSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 8, 27)).addBox(-4.0f, 4.0f, 1.0f, 8, 2, 3);
		body8.setRotationPoint(0.0f, 4.0f, 0.0f);
		body8.setTextureSize(64, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(body9 = new ModelRenderer(this, 0, 6)).addBox(-1.0f, 8.0f, -1.0f, 2, 6, 2);
		body9.setRotationPoint(0.0f, 4.0f, 0.0f);
		body9.setTextureSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0f, 0.0f, 0.0f);
		(body10 = new ModelRenderer(this, 0, 6)).addBox(-3.0f, 10.0f, -1.0f, 2, 2, 2);
		body10.setRotationPoint(0.0f, 4.0f, 0.0f);
		body10.setTextureSize(64, 32);
		body10.mirror = true;
		setRotation(body10, 0.0f, 0.0f, 0.0f);
		(leftleg2 = new ModelRenderer(this, 48, 11)).addBox(-2.0f, 10.13333f, -2.0f, 4, 4, 4);
		leftleg2.setRotationPoint(5.0f, 10.0f, 0.0f);
		leftleg2.setTextureSize(64, 32);
		leftleg2.mirror = true;
		setRotation(leftleg2, 0.0f, 0.0f, 0.0f);
		(rightleg2 = new ModelRenderer(this, 48, 11)).addBox(-2.0f, 10.13333f, -2.0f, 4, 4, 4);
		rightleg2.setRotationPoint(-5.0f, 10.0f, 0.0f);
		rightleg2.setTextureSize(64, 32);
		rightleg2.mirror = true;
		setRotation(rightleg2, 0.0f, 0.0f, 0.0f);
		(body11 = new ModelRenderer(this, 16, 0)).addBox(-4.0f, 0.0f, -4.0f, 8, 4, 8);
		body11.setRotationPoint(0.0f, 4.0f, 0.0f);
		body11.setTextureSize(64, 32);
		body11.mirror = true;
		setRotation(body11, 0.0f, 0.0f, 0.0f);
		(body12 = new ModelRenderer(this, 32, 22)).addBox(-4.0f, 6.0f, -4.0f, 8, 2, 8);
		body12.setRotationPoint(0.0f, 4.0f, 0.0f);
		body12.setTextureSize(64, 32);
		body12.mirror = true;
		setRotation(body12, 0.0f, 0.0f, 0.0f);
		(leftleg3 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -1.0f, 2, 14, 2);
		leftleg3.setRotationPoint(5.0f, 10.0f, 0.0f);
		leftleg3.setTextureSize(64, 32);
		leftleg3.mirror = true;
		setRotation(leftleg3, 0.0f, 0.0f, 0.0f);
		(rightleg3 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -1.0f, 2, 14, 2);
		rightleg3.setRotationPoint(-5.0f, 10.0f, 0.0f);
		rightleg3.setTextureSize(64, 32);
		rightleg3.mirror = true;
		setRotation(rightleg3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		rightleg.render(par7);
		leftleg.render(par7);
		body.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
		body7.render(par7);
		body8.render(par7);
		body9.render(par7);
		body10.render(par7);
		leftleg2.render(par7);
		rightleg2.render(par7);
		body11.render(par7);
		body12.render(par7);
		leftleg3.render(par7);
		rightleg3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		rightleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg2.rotateAngleY = 0.0f;
		rightleg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg3.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftleg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

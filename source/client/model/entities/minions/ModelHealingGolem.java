package net.tslat.aoa3.client.model.entities.minions;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHealingGolem extends ModelBase {
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
	ModelRenderer body6;
	ModelRenderer leftarm2;
	ModelRenderer rightarm2;

	public ModelHealingGolem() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -3.0f, -4.0f, 8, 3, 8);
		head.setRotationPoint(0.0f, -7.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 26, 16)).addBox(-6.0f, -12.0f, -3.0f, 12, 6, 6);
		body.setRotationPoint(0.0f, 5.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 34, 1)).addBox(-5.0f, -5.0f, -4.0f, 7, 3, 7);
		rightarm.setRotationPoint(-12.0f, -3.0f, 0.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 34, 1)).addBox(-2.0f, -5.0f, -4.0f, 7, 3, 7);
		leftarm.setRotationPoint(12.0f, -3.0f, 0.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 13, 6);
		rightleg.setRotationPoint(-4.0f, 11.0f, 0.0f);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 13, 6);
		leftleg.setRotationPoint(4.0f, 11.0f, 0.0f);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 22, 39)).addBox(6.0f, -12.0f, -3.0f, 4, 18, 6);
		body2.setRotationPoint(0.0f, 5.0f, 0.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 2, 54)).addBox(-4.0f, -2.0f, -2.0f, 2, 6, 2);
		body3.setRotationPoint(3.0f, 1.0f, 1.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 22, 39)).addBox(-10.0f, -12.0f, -3.0f, 4, 18, 6);
		body4.setRotationPoint(0.0f, 5.0f, 0.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 26, 16)).addBox(-6.0f, 0.0f, -3.0f, 12, 6, 6);
		body5.setRotationPoint(0.0f, 5.0f, 0.0f);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 2, 54)).addBox(-6.0f, 0.0f, -3.0f, 6, 2, 2);
		body6.setRotationPoint(3.0f, 1.0f, 1.0f);
		body6.setTextureSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 44, 30)).addBox(0.0f, -2.0f, -3.0f, 5, 28, 5);
		leftarm2.setRotationPoint(12.0f, -3.0f, 0.0f);
		leftarm2.setTextureSize(64, 64);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 44, 30)).addBox(-5.0f, -2.0f, -3.0f, 5, 28, 5);
		rightarm2.setRotationPoint(-12.0f, -3.0f, 0.0f);
		rightarm2.setTextureSize(64, 64);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
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
		body6.render(par7);
		leftarm2.render(par7);
		rightarm2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm2.rotateAngleZ = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

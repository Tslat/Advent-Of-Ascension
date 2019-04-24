package net.nevermine.mob.model.mysterium;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelFungung extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer body2;
	ModelRenderer head2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer leftarm2;
	ModelRenderer rightarm2;
	ModelRenderer rightarm3;
	ModelRenderer leftarm3;

	public modelFungung() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 16)).addBox(-6.0f, -16.0f, -6.0f, 12, 8, 12);
		head.setRotationPoint(0.0f, 5.0f, -4.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 47, 4)).addBox(0.0f, -6.0f, 8.0f, 4, 3, 4);
		body.setRotationPoint(0.0f, 17.0f, -3.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 26, 8)).addBox(-9.0f, 2.0f, -2.0f, 3, 4, 4);
		rightarm.setRotationPoint(-5.0f, 6.0f, -3.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 26, 0)).addBox(6.0f, 2.0f, -2.0f, 3, 4, 4);
		leftarm.setRotationPoint(5.0f, 6.0f, -3.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 39, 37)).addBox(-4.0f, 0.0f, -2.0f, 8, 14, 4);
		body2.setRotationPoint(0.0f, 3.0f, -3.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -8.0f, -3.0f, 6, 8, 6);
		head2.setRotationPoint(0.0f, 5.0f, -4.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 3, 42)).addBox(-5.0f, 0.0f, -2.0f, 10, 7, 14);
		body3.setRotationPoint(0.0f, 17.0f, -3.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 47, 21)).addBox(1.0f, -3.0f, 9.0f, 2, 3, 2);
		body4.setRotationPoint(0.0f, 17.0f, -3.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 47, 13)).addBox(-5.0f, -6.0f, 4.0f, 4, 3, 4);
		body5.setRotationPoint(0.0f, 17.0f, -3.0f);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 47, 21)).addBox(-4.0f, -3.0f, 5.0f, 2, 3, 2);
		body6.setRotationPoint(0.0f, 17.0f, -3.0f);
		body6.setTextureSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 0, 37)).addBox(-1.0f, -2.0f, -2.0f, 4, 13, 4);
		leftarm2.setRotationPoint(5.0f, 6.0f, -3.0f);
		leftarm2.setTextureSize(64, 64);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 0, 37)).addBox(-3.0f, -2.0f, -2.0f, 4, 13, 4);
		rightarm2.setRotationPoint(-5.0f, 6.0f, -3.0f);
		rightarm2.setTextureSize(64, 64);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 18, 37)).addBox(-6.0f, 3.0f, -1.0f, 3, 2, 2);
		rightarm3.setRotationPoint(-5.0f, 6.0f, -3.0f);
		rightarm3.setTextureSize(64, 64);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0f, 0.0f, 0.0f);
		(leftarm3 = new ModelRenderer(this, 18, 37)).addBox(3.0f, 3.0f, -1.0f, 3, 2, 2);
		leftarm3.setRotationPoint(5.0f, 6.0f, -3.0f);
		leftarm3.setTextureSize(64, 64);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		body2.render(par7);
		head2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
		leftarm2.render(par7);
		rightarm2.render(par7);
		rightarm3.render(par7);
		leftarm3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		head2.rotateAngleY = par4 / 57.295776f;
		head2.rotateAngleX = par5 / 54.11268f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		rightarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm3.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm2.rotateAngleZ = 0.0f;
		leftarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm3.rotateAngleZ = 0.0f;
	}
}

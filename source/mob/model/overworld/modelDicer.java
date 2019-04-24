package net.nevermine.mob.model.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelDicer extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leftarm;
	ModelRenderer rightarm;
	ModelRenderer body2;
	ModelRenderer leftarm2;
	ModelRenderer rightarm2;
	ModelRenderer body3;
	ModelRenderer leftarm3;
	ModelRenderer rightarm3;
	ModelRenderer leftarm4;
	ModelRenderer rightarm4;

	public modelDicer() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 10.0f, 6.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 2, 30)).addBox(-4.0f, 0.0f, -2.0f, 10, 4, 4);
		body.setRotationPoint(-1.0f, 10.0f, 12.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 40, 34)).addBox(0.5f, 9.0f, -8.0f, 1, 4, 4);
		leftarm.setRotationPoint(6.0f, 12.0f, 0.0f);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 40, 34)).addBox(-1.5f, 9.0f, -8.0f, 1, 4, 4);
		rightarm.setRotationPoint(-6.0f, 12.0f, 0.0f);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 2, 16)).addBox(-4.0f, 0.0f, -2.0f, 4, 4, 8);
		body2.setRotationPoint(2.0f, 10.0f, 4.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftarm2.setRotationPoint(6.0f, 12.0f, 12.0f);
		leftarm2.setTextureSize(64, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightarm2.setRotationPoint(-6.0f, 12.0f, 12.0f);
		rightarm2.setTextureSize(64, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 2, 30)).addBox(-4.0f, 0.0f, -2.0f, 10, 4, 4);
		body3.setRotationPoint(-1.0f, 10.0f, 0.0f);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(leftarm3 = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftarm3.setRotationPoint(6.0f, 12.0f, 0.0f);
		leftarm3.setTextureSize(64, 32);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightarm3.setRotationPoint(-6.0f, 12.0f, 0.0f);
		rightarm3.setTextureSize(64, 32);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0f, 0.0f, 0.0f);
		(leftarm4 = new ModelRenderer(this, 40, 1)).addBox(0.0f, 7.0f, -8.0f, 2, 2, 6);
		leftarm4.setRotationPoint(6.0f, 12.0f, 0.0f);
		leftarm4.setTextureSize(64, 32);
		leftarm4.mirror = true;
		setRotation(leftarm4, 0.0f, 0.0f, 0.0f);
		(rightarm4 = new ModelRenderer(this, 40, 1)).addBox(-2.0f, 7.0f, -8.0f, 2, 2, 6);
		rightarm4.setRotationPoint(-6.0f, 12.0f, 0.0f);
		rightarm4.setTextureSize(64, 32);
		rightarm4.mirror = true;
		setRotation(rightarm4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		leftarm.render(par7);
		rightarm.render(par7);
		body2.render(par7);
		leftarm2.render(par7);
		rightarm2.render(par7);
		body3.render(par7);
		leftarm3.render(par7);
		rightarm3.render(par7);
		leftarm4.render(par7);
		rightarm4.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		rightarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm3.rotateAngleZ = 0.0f;
		rightarm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm4.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm2.rotateAngleZ = 0.0f;
		leftarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm3.rotateAngleZ = 0.0f;
		leftarm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm4.rotateAngleZ = 0.0f;
	}
}

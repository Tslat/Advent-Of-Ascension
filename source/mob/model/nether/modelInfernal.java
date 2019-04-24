package net.nevermine.mob.model.nether;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelInfernal extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer leftarm;

	public modelInfernal() {
		textureWidth = 128;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -10.0f, -4.0f, 10, 10, 8);
		head.setRotationPoint(0.0f, 4.0f, -7.533333f);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 34, 19)).addBox(-7.0f, 0.0f, -5.0f, 14, 5, 10);
		body.setRotationPoint(0.0f, -2.0f, -6.0f);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.6981317f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 100, 0)).addBox(-6.0f, -2.0f, -3.0f, 6, 23, 6);
		rightarm.setRotationPoint(-9.0f, 3.0f, -4.0f);
		rightarm.setTextureSize(128, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 21)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		rightleg.setRotationPoint(-5.0f, 14.0f, 0.0f);
		rightleg.setTextureSize(128, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 21)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		leftleg.setRotationPoint(5.0f, 14.0f, 0.0f);
		leftleg.setTextureSize(128, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 34, 39)).addBox(-9.0f, 0.0f, -6.0f, 18, 12, 12);
		body2.setRotationPoint(0.0f, 0.0f, -4.0f);
		body2.setTextureSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.3490659f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 39, 0)).addBox(-8.0f, 0.0f, -5.0f, 16, 5, 10);
		body3.setRotationPoint(0.0f, 9.0f, 0.0f);
		body3.setTextureSize(128, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 100, 0)).addBox(-1.0f, -2.0f, -3.0f, 6, 23, 6);
		leftarm.setRotationPoint(10.0f, 3.0f, -4.0f);
		leftarm.setTextureSize(128, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		body2.render(par7);
		body3.render(par7);
		leftarm.render(par7);
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
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

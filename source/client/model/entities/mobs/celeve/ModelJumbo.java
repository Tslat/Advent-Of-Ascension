package net.tslat.aoa3.client.model.entities.mobs.celeve;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelJumbo extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer rightarm2;
	ModelRenderer rightarm3;
	ModelRenderer head4;

	public ModelJumbo() {
		textureWidth = 128;
		textureHeight = 64;
		(head = new ModelRenderer(this, 43, 17)).addBox(-5.0f, -14.0f, -5.0f, 10, 4, 10);
		head.setRotationPoint(0.0f, -4.0f, 0.0f);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 40, 35)).addBox(-1.0f, 4.0f, -4.0f, 3, 2, 1);
		body.setRotationPoint(0.0f, -4.0f, 0.0f);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 67, 47)).addBox(-5.0f, 8.0f, -17.0f, 8, 8, 8);
		rightarm.setRotationPoint(-8.0f, -2.0f, 0.0f);
		rightarm.setTextureSize(128, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 40, 40)).addBox(-2.0f, -2.0f, -3.0f, 6, 16, 6);
		leftarm.setRotationPoint(8.0f, -2.0f, 0.0f);
		leftarm.setTextureSize(128, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 21)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		rightleg.setRotationPoint(-3.0f, 12.0f, 0.0f);
		rightleg.setTextureSize(128, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 21)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		leftleg.setRotationPoint(3.0f, 12.0f, 0.0f);
		leftleg.setTextureSize(128, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 33, 0)).addBox(-1.5f, -6.0f, -7.0f, 3, 3, 2);
		head2.setRotationPoint(0.0f, -4.0f, 0.0f);
		head2.setTextureSize(128, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 42, 0)).addBox(-6.0f, -10.0f, -6.0f, 12, 2, 12);
		head3.setRotationPoint(0.0f, -4.0f, 0.0f);
		head3.setTextureSize(128, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 2, 41)).addBox(-6.0f, 0.0f, -3.0f, 12, 16, 6);
		body2.setRotationPoint(0.0f, -4.0f, 0.0f);
		body2.setTextureSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 26, 34)).addBox(-3.0f, 4.0f, -5.0f, 2, 2, 2);
		body3.setRotationPoint(0.0f, -4.0f, 0.0f);
		body3.setTextureSize(128, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 34, 39)).addBox(-3.0f, 1.0f, -4.0f, 2, 3, 1);
		body4.setRotationPoint(0.0f, -4.0f, 0.0f);
		body4.setTextureSize(128, 64);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 34, 39)).addBox(-3.0f, 6.0f, -4.0f, 2, 3, 1);
		body5.setRotationPoint(0.0f, -4.0f, 0.0f);
		body5.setTextureSize(128, 64);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 40, 35)).addBox(-6.0f, 4.0f, -4.0f, 3, 2, 1);
		body6.setRotationPoint(0.0f, -4.0f, 0.0f);
		body6.setTextureSize(128, 64);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 40, 40)).addBox(-4.0f, -2.0f, -3.0f, 6, 16, 6);
		rightarm2.setRotationPoint(-8.0f, -2.0f, 0.0f);
		rightarm2.setTextureSize(128, 64);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 67, 34)).addBox(-2.0f, 11.0f, -9.0f, 2, 2, 6);
		rightarm3.setRotationPoint(-8.0f, -2.0f, 0.0f);
		rightarm3.setTextureSize(128, 64);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -8.0f, -5.0f, 10, 8, 10);
		head4.setRotationPoint(0.0f, -4.0f, 0.0f);
		head4.setTextureSize(128, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		head2.render(par7);
		head3.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
		rightarm2.render(par7);
		rightarm3.render(par7);
		head4.render(par7);
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
		head3.rotateAngleY = par4 / 57.295776f;
		head3.rotateAngleX = par5 / 54.11268f;
		head4.rotateAngleY = par4 / 57.295776f;
		head4.rotateAngleX = par5 / 54.11268f;
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		rightarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm3.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

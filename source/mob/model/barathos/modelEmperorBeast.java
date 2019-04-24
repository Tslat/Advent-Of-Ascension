package net.nevermine.mob.model.barathos;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelEmperorBeast extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer leftleg2;
	ModelRenderer rightleg2;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer head2;
	ModelRenderer head3;

	public modelEmperorBeast() {
		textureWidth = 128;
		textureHeight = 64;
		(head = new ModelRenderer(this, 41, 18)).addBox(-10.0f, -21.0f, 0.0f, 20, 3, 8);
		head.setRotationPoint(0.0f, -11.0f, 0.0f);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, -0.1745329f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 90, 46)).addBox(-3.0f, 17.0f, 15.0f, 6, 6, 12);
		body.setRotationPoint(0.0f, -11.0f, -3.0f);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 83, 2)).addBox(-5.0f, 13.0f, -11.0f, 10, 4, 12);
		rightleg.setRotationPoint(-7.0f, 7.0f, 6.0f);
		rightleg.setTextureSize(128, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 83, 2)).addBox(-5.0f, 13.0f, -11.0f, 10, 4, 12);
		leftleg.setRotationPoint(7.0f, 7.0f, 6.0f);
		leftleg.setTextureSize(128, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(leftleg2 = new ModelRenderer(this, 100, 22)).addBox(-3.0f, 0.0f, -4.0f, 6, 15, 8);
		leftleg2.setRotationPoint(7.0f, 7.0f, 6.0f);
		leftleg2.setTextureSize(128, 64);
		leftleg2.mirror = true;
		setRotation(leftleg2, -0.3490659f, 0.0f, 0.0f);
		(rightleg2 = new ModelRenderer(this, 100, 22)).addBox(-3.0f, 0.0f, -4.0f, 6, 15, 8);
		rightleg2.setRotationPoint(-7.0f, 7.0f, 6.0f);
		rightleg2.setTextureSize(128, 64);
		rightleg2.mirror = true;
		setRotation(rightleg2, -0.3490659f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 24, 29)).addBox(-4.0f, 0.0f, -2.0f, 8, 23, 12);
		body2.setRotationPoint(0.0f, -11.0f, -3.0f);
		body2.setTextureSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 67, 29)).addBox(-4.0f, 11.0f, 10.0f, 8, 12, 8);
		body3.setRotationPoint(0.0f, -11.0f, -3.0f);
		body3.setTextureSize(128, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 28)).addBox(-2.0f, -20.0f, -4.0f, 4, 8, 6);
		head2.setRotationPoint(0.0f, -11.0f, 0.0f);
		head2.setTextureSize(128, 64);
		head2.mirror = true;
		setRotation(head2, -0.4537856f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -13.0f, -4.0f, 8, 13, 13);
		head3.setRotationPoint(0.0f, -11.0f, 0.0f);
		head3.setTextureSize(128, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		leftleg2.render(par7);
		rightleg2.render(par7);
		body2.render(par7);
		body3.render(par7);
		head2.render(par7);
		head3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		rightleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2 - 0.349f;
		rightleg2.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2 - 0.349f;
	}
}

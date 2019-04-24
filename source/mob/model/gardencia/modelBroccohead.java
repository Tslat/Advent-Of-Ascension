package net.nevermine.mob.model.gardencia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelBroccohead extends ModelBase {
	ModelRenderer body;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body2;
	ModelRenderer head;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer head5;
	ModelRenderer head6;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer body7;

	public modelBroccohead() {
		textureWidth = 128;
		textureHeight = 32;
		(body = new ModelRenderer(this, 112, 18)).addBox(-4.0f, 8.0f, -4.0f, 4, 5, 4);
		body.setRotationPoint(-4.0f, -8.0f, -5.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.6108652f, 1.117011f, 0.0f);
		(rightleg = new ModelRenderer(this, 112, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg.setRotationPoint(-3.0f, 12.0f, 0.0f);
		rightleg.setTextureSize(128, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 112, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg.setRotationPoint(3.0f, 12.0f, 0.0f);
		leftleg.setTextureSize(128, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 79, 12)).addBox(-4.0f, 0.0f, -4.0f, 8, 12, 8);
		body2.setRotationPoint(0.0f, 0.0f, 0.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 2, 11)).addBox(-5.0f, -5.0f, 2.0f, 12, 5, 12);
		head.setRotationPoint(-1.0f, -7.0f, -1.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 53, 14)).addBox(-2.0f, -7.0f, 5.0f, 6, 2, 6);
		head2.setRotationPoint(-1.0f, -7.0f, -1.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 2, 11)).addBox(-14.0f, -5.0f, -12.0f, 12, 5, 12);
		head3.setRotationPoint(1.0f, -6.0f, 3.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 53, 14)).addBox(-11.0f, -7.0f, -9.0f, 6, 2, 6);
		head4.setRotationPoint(1.0f, -6.0f, 3.0f);
		head4.setTextureSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 2, 11)).addBox(1.0f, -5.0f, -16.0f, 12, 5, 12);
		head5.setRotationPoint(-2.0f, -5.0f, 6.0f);
		head5.setTextureSize(128, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 53, 14)).addBox(4.0f, -7.0f, -13.0f, 6, 2, 6);
		head6.setRotationPoint(-2.0f, -5.0f, 6.0f);
		head6.setTextureSize(128, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 112, 18)).addBox(-4.0f, -2.0f, -4.0f, 4, 10, 4);
		body3.setRotationPoint(-4.0f, -8.0f, -5.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.6108652f, 1.117011f, 0.0f);
		(body4 = new ModelRenderer(this, 112, 18)).addBox(-4.0f, 0.0f, -4.0f, 4, 10, 4);
		body4.setRotationPoint(2.0f, -8.0f, 11.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, -0.5235988f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 112, 18)).addBox(-4.0f, 10.0f, -4.0f, 4, 5, 4);
		body5.setRotationPoint(2.0f, -8.0f, 11.0f);
		body5.setTextureSize(128, 32);
		body5.mirror = true;
		setRotation(body5, -0.5235988f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 112, 18)).addBox(-4.0f, 8.0f, -4.0f, 4, 5, 4);
		body6.setRotationPoint(6.0f, -8.0f, -2.0f);
		body6.setTextureSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 0.5235988f, -0.5235988f, 0.0f);
		(body7 = new ModelRenderer(this, 112, 18)).addBox(-4.0f, -2.0f, -4.0f, 4, 10, 4);
		body7.setRotationPoint(6.0f, -8.0f, -2.0f);
		body7.setTextureSize(128, 32);
		body7.mirror = true;
		setRotation(body7, 0.5235988f, -0.5235988f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		body2.render(par7);
		head.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
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

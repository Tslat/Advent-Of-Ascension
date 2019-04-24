package net.nevermine.mob.model.lunalus;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelZarg extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer body7;
	ModelRenderer body8;
	ModelRenderer body9;
	ModelRenderer body10;
	ModelRenderer body11;
	ModelRenderer body12;
	ModelRenderer body13;

	public modelZarg() {
		textureWidth = 64;
		textureHeight = 32;
		(head = new ModelRenderer(this, 34, 0)).addBox(-9.0f, -3.0f, -1.0f, 2, 9, 2);
		head.setRotationPoint(4.0f, 0.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 42, 9)).addBox(-5.0f, 7.0f, -6.0f, 10, 1, 1);
		body.setRotationPoint(0.0f, 6.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		rightleg.setRotationPoint(-4.0f, 16.0f, 0.0f);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leftleg.setRotationPoint(4.0f, 16.0f, 0.0f);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-11.0f, -9.0f, -3.0f, 6, 6, 6);
		head2.setRotationPoint(4.0f, 0.0f, 0.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 25, 0)).addBox(-1.0f, 0.0f, -1.0f, 2, 6, 2);
		head3.setRotationPoint(4.0f, 0.0f, 0.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head4.setRotationPoint(4.0f, 0.0f, 0.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 12)).addBox(-7.0f, 0.0f, -5.0f, 14, 10, 10);
		body2.setRotationPoint(0.0f, 6.0f, 0.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 49, 0)).addBox(4.0f, 3.0f, -6.0f, 1, 4, 1);
		body3.setRotationPoint(0.0f, 6.0f, 0.0f);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 54, 2)).addBox(2.5f, 6.0f, -6.0f, 1, 1, 1);
		body4.setRotationPoint(0.0f, 6.0f, 0.0f);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 44, 0)).addBox(-5.0f, 3.0f, -6.0f, 1, 4, 1);
		body5.setRotationPoint(0.0f, 6.0f, 0.0f);
		body5.setTextureSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 42, 6)).addBox(-5.0f, 2.0f, -6.0f, 10, 1, 1);
		body6.setRotationPoint(0.0f, 6.0f, 0.0f);
		body6.setTextureSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 54, 2)).addBox(0.5f, 6.0f, -6.0f, 1, 1, 1);
		body7.setRotationPoint(0.0f, 6.0f, 0.0f);
		body7.setTextureSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 54, 2)).addBox(2.5f, 3.0f, -6.0f, 1, 1, 1);
		body8.setRotationPoint(0.0f, 6.0f, 0.0f);
		body8.setTextureSize(64, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(body9 = new ModelRenderer(this, 54, 2)).addBox(0.5f, 3.0f, -6.0f, 1, 1, 1);
		body9.setRotationPoint(0.0f, 6.0f, 0.0f);
		body9.setTextureSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0f, 0.0f, 0.0f);
		(body10 = new ModelRenderer(this, 54, 2)).addBox(-1.5f, 6.0f, -6.0f, 1, 1, 1);
		body10.setRotationPoint(0.0f, 6.0f, 0.0f);
		body10.setTextureSize(64, 32);
		body10.mirror = true;
		setRotation(body10, 0.0f, 0.0f, 0.0f);
		(body11 = new ModelRenderer(this, 54, 2)).addBox(-3.5f, 6.0f, -6.0f, 1, 1, 1);
		body11.setRotationPoint(0.0f, 6.0f, 0.0f);
		body11.setTextureSize(64, 32);
		body11.mirror = true;
		setRotation(body11, 0.0f, 0.0f, 0.0f);
		(body12 = new ModelRenderer(this, 54, 2)).addBox(-1.5f, 3.0f, -6.0f, 1, 1, 1);
		body12.setRotationPoint(0.0f, 6.0f, 0.0f);
		body12.setTextureSize(64, 32);
		body12.mirror = true;
		setRotation(body12, 0.0f, 0.0f, 0.0f);
		(body13 = new ModelRenderer(this, 54, 2)).addBox(-3.5f, 3.0f, -6.0f, 1, 1, 1);
		body13.setRotationPoint(0.0f, 6.0f, 0.0f);
		body13.setTextureSize(64, 32);
		body13.mirror = true;
		setRotation(body13, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
		body7.render(par7);
		body8.render(par7);
		body9.render(par7);
		body10.render(par7);
		body11.render(par7);
		body12.render(par7);
		body13.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head2.rotateAngleY = par4 / (float)(180f / Math.PI);
		head4.rotateAngleY = par4 / (float)(180f / Math.PI);
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

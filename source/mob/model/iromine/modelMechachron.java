package net.nevermine.mob.model.iromine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelMechachron extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body8;
	ModelRenderer body9;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer body7;

	public modelMechachron() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 16, 21)).addBox(5.0f, -8.0f, 2.0f, 2, 4, 8);
		head.setRotationPoint(0.0f, 17.0f, -6.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.5235988f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 29, 17)).addBox(12.0f, -9.0f, -3.0f, 1, 5, 4);
		body.setRotationPoint(-4.0f, 12.0f, 5.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 1.745329f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 40, 11)).addBox(-4.0f, -3.0f, -3.0f, 6, 16, 6);
		rightarm.setRotationPoint(-11.0f, 11.0f, 0.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 40, 11)).addBox(-2.0f, -3.0f, -3.0f, 6, 16, 6);
		leftarm.setRotationPoint(11.0f, 10.0f, 0.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg.setRotationPoint(-5.0f, 12.0f, 14.0f);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg.setRotationPoint(5.0f, 12.0f, 14.0f);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 43, 34)).addBox(-1.0f, 8.0f, -4.0f, 2, 8, 2);
		body8.setRotationPoint(0.0f, 12.0f, 5.0f);
		body8.setTextureSize(64, 64);
		body8.mirror = true;
		setRotation(body8, 2.530727f, 0.0f, 0.0f);
		(body9 = new ModelRenderer(this, 29, 17)).addBox(-5.0f, -9.0f, -3.0f, 1, 5, 4);
		body9.setRotationPoint(-4.0f, 12.0f, 5.0f);
		body9.setTextureSize(64, 64);
		body9.mirror = true;
		setRotation(body9, 1.745329f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -8.0f, -4.0f, 10, 8, 8);
		head2.setRotationPoint(0.0f, 17.0f, -6.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 16, 21)).addBox(-7.0f, -8.0f, 2.0f, 2, 4, 8);
		head3.setRotationPoint(0.0f, 17.0f, -6.0f);
		head3.setTextureSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.5235988f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 0, 34)).addBox(-4.0f, -11.0f, -3.0f, 16, 22, 4);
		body2.setRotationPoint(-4.0f, 12.0f, 5.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 1.745329f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 43, 34)).addBox(-1.0f, 2.0f, 2.0f, 2, 8, 2);
		body3.setRotationPoint(-4.0f, 12.0f, 5.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 2.530727f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 43, 34)).addBox(-1.0f, 2.0f, 2.0f, 2, 8, 2);
		body4.setRotationPoint(4.0f, 12.0f, 5.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 2.530727f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 43, 46)).addBox(-2.0f, 2.0f, -5.0f, 4, 8, 4);
		body5.setRotationPoint(0.0f, 12.0f, 5.0f);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 2.530727f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 43, 46)).addBox(-2.0f, -4.0f, 1.0f, 4, 8, 4);
		body6.setRotationPoint(-4.0f, 12.0f, 5.0f);
		body6.setTextureSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 2.530727f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 43, 46)).addBox(-2.0f, -4.0f, 1.0f, 4, 8, 4);
		body7.setRotationPoint(4.0f, 12.0f, 5.0f);
		body7.setTextureSize(64, 64);
		body7.mirror = true;
		setRotation(body7, 2.530727f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		body8.render(par7);
		body9.render(par7);
		head2.render(par7);
		head3.render(par7);
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
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftarm.rotateAngleY = 0.0f;
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

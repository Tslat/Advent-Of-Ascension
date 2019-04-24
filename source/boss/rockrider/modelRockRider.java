package net.nevermine.boss.rockrider;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelRockRider extends ModelBase {
	ModelRenderer headrider;
	ModelRenderer bodyrider;
	ModelRenderer rightarmrider;
	ModelRenderer leftarmrider;
	ModelRenderer rightlegrider;
	ModelRenderer leftlegrider;
	ModelRenderer body1;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer head;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;

	public modelRockRider() {
		textureWidth = 128;
		textureHeight = 32;
		(headrider = new ModelRenderer(this, 96, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		headrider.setRotationPoint(0.0f, -4.0f, 6.0f);
		headrider.setTextureSize(128, 32);
		headrider.mirror = true;
		setRotation(headrider, 0.0f, 0.0f, 0.0f);
		(bodyrider = new ModelRenderer(this, 70, 20)).addBox(-4.0f, 0.0f, -2.0f, 8, 8, 4);
		bodyrider.setRotationPoint(0.0f, -4.0f, 5.0f);
		bodyrider.setTextureSize(128, 32);
		bodyrider.mirror = true;
		setRotation(bodyrider, 0.3490659f, 0.0f, 0.0f);
		(rightarmrider = new ModelRenderer(this, 112, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightarmrider.setRotationPoint(-5.0f, -2.0f, 6.0f);
		rightarmrider.setTextureSize(128, 32);
		rightarmrider.mirror = true;
		setRotation(rightarmrider, -1.396263f, 0.0f, 0.0f);
		(leftarmrider = new ModelRenderer(this, 112, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftarmrider.setRotationPoint(5.0f, -2.0f, 6.0f);
		leftarmrider.setTextureSize(128, 32);
		leftarmrider.mirror = true;
		setRotation(leftarmrider, -1.396263f, 0.0f, 0.0f);
		(rightlegrider = new ModelRenderer(this, 96, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 3);
		rightlegrider.setRotationPoint(-3.0f, 3.0f, 8.0f);
		rightlegrider.setTextureSize(128, 32);
		rightlegrider.mirror = true;
		setRotation(rightlegrider, 0.148353f, 0.0f, 0.0f);
		(leftlegrider = new ModelRenderer(this, 96, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 3);
		leftlegrider.setRotationPoint(3.0f, 3.0f, 8.0f);
		leftlegrider.setTextureSize(128, 32);
		leftlegrider.mirror = true;
		setRotation(leftlegrider, 0.148353f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 0, 16)).addBox(-7.0f, 0.0f, -5.0f, 14, 6, 10);
		body1.setRotationPoint(0.0f, 7.0f, -5.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 1.570796f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 52, 2)).addBox(-3.0f, -2.0f, -2.0f, 4, 22, 4);
		rightarm.setRotationPoint(-8.0f, 4.0f, -2.0f);
		rightarm.setTextureSize(128, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 52, 2)).addBox(-1.0f, -2.0f, -2.0f, 4, 22, 4);
		leftarm.setRotationPoint(8.0f, 4.0f, -2.0f);
		leftarm.setTextureSize(128, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 7.0f, -5.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 72, 5)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		rightleg.setRotationPoint(-6.0f, 16.0f, 6.0f);
		rightleg.setTextureSize(128, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 72, 5)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leftleg.setRotationPoint(6.0f, 16.0f, 6.0f);
		leftleg.setTextureSize(128, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 8, 22)).addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
		body2.setRotationPoint(0.0f, 13.0f, 6.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 4, 20)).addBox(-5.0f, 0.0f, -3.0f, 10, 6, 6);
		body3.setRotationPoint(0.0f, 10.0f, 3.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.5235988f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 1, 18)).addBox(-6.0f, 0.0f, -4.0f, 12, 6, 8);
		body4.setRotationPoint(0.0f, 7.0f, -1.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 1.047198f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		headrider.render(par7);
		bodyrider.render(par7);
		rightarmrider.render(par7);
		leftarmrider.render(par7);
		rightlegrider.render(par7);
		leftlegrider.render(par7);
		body1.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		head.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		headrider.rotateAngleY = par4 / 57.295776f;
		headrider.rotateAngleX = par5 / 54.11268f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftarm.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

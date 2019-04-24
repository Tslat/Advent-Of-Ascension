package net.nevermine.minion.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelBlissard extends ModelBase {
	ModelRenderer WolfHead;
	ModelRenderer Body;
	ModelRenderer Mane;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Ear1;
	ModelRenderer Ear2;
	ModelRenderer Nose;
	ModelRenderer Tail;
	ModelRenderer Tail2;
	ModelRenderer Tail3;
	ModelRenderer Tail4;
	ModelRenderer Tail5;
	ModelRenderer Tail6;
	ModelRenderer Body2;

	public modelBlissard() {
		textureWidth = 64;
		textureHeight = 64;
		(WolfHead = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -3.0f, -2.0f, 6, 6, 4);
		WolfHead.setRotationPoint(-1.0f, 13.5f, -7.0f);
		WolfHead.setTextureSize(64, 64);
		WolfHead.mirror = true;
		setRotation(WolfHead, 0.0f, 0.0f, 0.0f);
		(Body = new ModelRenderer(this, 21, 31)).addBox(-4.0f, -2.0f, -3.0f, 4, 8, 3);
		Body.setRotationPoint(1.0f, 8.0f, 2.0f);
		Body.setTextureSize(64, 64);
		Body.mirror = true;
		setRotation(Body, 1.570796f, 0.0f, 0.0f);
		(Mane = new ModelRenderer(this, 21, 0)).addBox(-4.0f, -3.0f, -3.0f, 8, 6, 7);
		Mane.setRotationPoint(-1.0f, 14.0f, -3.0f);
		Mane.setTextureSize(64, 64);
		Mane.mirror = true;
		setRotation(Mane, 1.570796f, 0.0f, 0.0f);
		(Leg1 = new ModelRenderer(this, 0, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
		Leg1.setRotationPoint(-2.5f, 16.0f, 7.0f);
		Leg1.setTextureSize(64, 64);
		Leg1.mirror = true;
		setRotation(Leg1, 0.0f, 0.0f, 0.0f);
		(Leg2 = new ModelRenderer(this, 0, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
		Leg2.setRotationPoint(0.5f, 16.0f, 7.0f);
		Leg2.setTextureSize(64, 64);
		Leg2.mirror = true;
		setRotation(Leg2, 0.0f, 0.0f, 0.0f);
		(Leg3 = new ModelRenderer(this, 0, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
		Leg3.setRotationPoint(-2.5f, 16.0f, -4.0f);
		Leg3.setTextureSize(64, 64);
		Leg3.mirror = true;
		setRotation(Leg3, 0.0f, 0.0f, 0.0f);
		(Leg4 = new ModelRenderer(this, 0, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
		Leg4.setRotationPoint(0.5f, 16.0f, -4.0f);
		Leg4.setTextureSize(64, 64);
		Leg4.mirror = true;
		setRotation(Leg4, 0.0f, 0.0f, 0.0f);
		(Ear1 = new ModelRenderer(this, 45, 14)).addBox(-5.0f, -6.0f, 0.0f, 4, 6, 1);
		Ear1.setRotationPoint(-1.0f, 13.5f, -7.0f);
		Ear1.setTextureSize(64, 64);
		Ear1.mirror = true;
		setRotation(Ear1, 0.0f, 0.0f, 0.0f);
		(Ear2 = new ModelRenderer(this, 45, 14)).addBox(1.0f, -6.0f, 0.0f, 4, 6, 1);
		Ear2.setRotationPoint(-1.0f, 13.5f, -7.0f);
		Ear2.setTextureSize(64, 64);
		Ear2.mirror = true;
		setRotation(Ear2, 0.0f, 0.0f, 0.0f);
		(Nose = new ModelRenderer(this, 0, 10)).addBox(-2.0f, 0.0f, -5.0f, 3, 3, 4);
		Nose.setRotationPoint(-0.5f, 13.5f, -7.0f);
		Nose.setTextureSize(64, 64);
		Nose.mirror = true;
		setRotation(Nose, 0.0f, 0.0f, 0.0f);
		(Tail = new ModelRenderer(this, 9, 18)).addBox(-1.0f, 7.0f, -1.0f, 2, 3, 2);
		Tail.setRotationPoint(-1.0f, 10.9f, 7.0f);
		Tail.setTextureSize(64, 64);
		Tail.mirror = true;
		setRotation(Tail, 1.919862f, 0.0f, 0.0f);
		(Tail2 = new ModelRenderer(this, 9, 31)).addBox(-1.0f, 0.0f, -1.0f, 2, 7, 3);
		Tail2.setRotationPoint(-1.0f, 10.9f, 7.0f);
		Tail2.setTextureSize(64, 64);
		Tail2.mirror = true;
		setRotation(Tail2, 1.919862f, 0.0f, 0.0f);
		(Tail3 = new ModelRenderer(this, 9, 31)).addBox(-1.0f, 0.0f, -1.0f, 2, 7, 3);
		Tail3.setRotationPoint(-1.0f, 16.0f, 7.0f);
		Tail3.setTextureSize(64, 64);
		Tail3.mirror = true;
		setRotation(Tail3, 1.130069f, 0.0f, 0.0f);
		(Tail4 = new ModelRenderer(this, 9, 18)).addBox(-1.0f, 7.0f, -1.0f, 2, 3, 2);
		Tail4.setRotationPoint(-1.0f, 16.0f, 7.0f);
		Tail4.setTextureSize(64, 64);
		Tail4.mirror = true;
		setRotation(Tail4, 1.130069f, 0.0f, 0.0f);
		(Tail5 = new ModelRenderer(this, 9, 31)).addBox(-1.0f, 0.0f, -1.0f, 2, 7, 3);
		Tail5.setRotationPoint(-1.0f, 13.3f, 7.0f);
		Tail5.setTextureSize(64, 64);
		Tail5.mirror = true;
		setRotation(Tail5, 1.48353f, 0.0f, 0.0f);
		(Tail6 = new ModelRenderer(this, 9, 18)).addBox(-1.0f, 7.0f, -1.0f, 2, 3, 2);
		Tail6.setRotationPoint(-1.0f, 13.3f, 7.0f);
		Tail6.setTextureSize(64, 64);
		Tail6.mirror = true;
		setRotation(Tail6, 1.48353f, 0.0f, 0.0f);
		(Body2 = new ModelRenderer(this, 18, 14)).addBox(-4.0f, -2.0f, -3.0f, 6, 9, 6);
		Body2.setRotationPoint(0.0f, 14.0f, 2.0f);
		Body2.setTextureSize(64, 64);
		Body2.mirror = true;
		setRotation(Body2, 1.570796f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		WolfHead.render(par7);
		Body.render(par7);
		Mane.render(par7);
		Leg1.render(par7);
		Leg2.render(par7);
		Leg3.render(par7);
		Leg4.render(par7);
		Ear1.render(par7);
		Ear2.render(par7);
		Nose.render(par7);
		Tail.render(par7);
		Tail2.render(par7);
		Tail3.render(par7);
		Tail4.render(par7);
		Tail5.render(par7);
		Tail6.render(par7);
		Body2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		Leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		Leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		Leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		Leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
	}
}

package net.nevermine.mob.model.deeplands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelRockCrawler extends ModelBase {
	ModelRenderer RearEnd;
	ModelRenderer Leg6;
	ModelRenderer Leg4;
	ModelRenderer Leg2;
	ModelRenderer Leg5;
	ModelRenderer Leg3;
	ModelRenderer Leg1;
	ModelRenderer Leg8;
	ModelRenderer Leg7;
	ModelRenderer RearEnd2;
	ModelRenderer RearEnd3;

	public modelRockCrawler() {
		textureWidth = 128;
		textureHeight = 32;
		(RearEnd = new ModelRenderer(this, 0, 10)).addBox(-9.0f, -15.0f, -7.0f, 8, 8, 8);
		RearEnd.setRotationPoint(4.0f, 8.0f, 4.0f);
		RearEnd.setTextureSize(128, 32);
		RearEnd.mirror = true;
		setRotation(RearEnd, 0.0f, 0.0f, 0.0f);
		(Leg6 = new ModelRenderer(this, 18, 0)).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg6.setRotationPoint(6.0f, 16.0f, 2.0f);
		Leg6.setTextureSize(128, 32);
		Leg6.mirror = true;
		setRotation(Leg6, 0.0f, 0.2792527f, 0.1919862f);
		(Leg4 = new ModelRenderer(this, 18, 0)).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg4.setRotationPoint(6.0f, 16.0f, 4.0f);
		Leg4.setTextureSize(128, 32);
		Leg4.mirror = true;
		setRotation(Leg4, 0.0f, -0.2792527f, 0.1919862f);
		(Leg2 = new ModelRenderer(this, 18, 0)).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg2.setRotationPoint(6.0f, 16.0f, 6.0f);
		Leg2.setTextureSize(128, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0.0f, -0.5759587f, 0.1919862f);
		(Leg5 = new ModelRenderer(this, 18, 5)).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg5.setRotationPoint(-6.0f, 16.0f, 2.0f);
		Leg5.setTextureSize(128, 32);
		Leg5.mirror = true;
		setRotation(Leg5, 0.0f, -0.2792527f, -0.1919862f);
		(Leg3 = new ModelRenderer(this, 18, 5)).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg3.setRotationPoint(-6.0f, 16.0f, 4.0f);
		Leg3.setTextureSize(128, 32);
		Leg3.mirror = true;
		setRotation(Leg3, 0.0f, 0.2792527f, -0.1919862f);
		(Leg1 = new ModelRenderer(this, 18, 5)).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg1.setRotationPoint(-6.0f, 16.0f, 6.0f);
		Leg1.setTextureSize(128, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0.0f, 0.5759587f, -0.1919862f);
		(Leg8 = new ModelRenderer(this, 18, 0)).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg8.setRotationPoint(6.0f, 16.0f, -1.0f);
		Leg8.setTextureSize(128, 32);
		Leg8.mirror = true;
		setRotation(Leg8, 0.0f, 0.5759587f, 0.1919862f);
		(Leg7 = new ModelRenderer(this, 18, 5)).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg7.setRotationPoint(-6.0f, 16.0f, -1.0f);
		Leg7.setTextureSize(128, 32);
		Leg7.mirror = true;
		setRotation(Leg7, 0.0f, -0.5759587f, -0.1919862f);
		(RearEnd2 = new ModelRenderer(this, 70, 6)).addBox(-8.0f, -6.0f, -6.0f, 14, 12, 14);
		RearEnd2.setRotationPoint(0.0f, 12.0f, 1.0f);
		RearEnd2.setTextureSize(128, 32);
		RearEnd2.mirror = true;
		setRotation(RearEnd2, 0.0f, 0.0f, 0.0f);
		(RearEnd3 = new ModelRenderer(this, 35, 10)).addBox(-7.0f, -7.0f, -5.0f, 4, 5, 4);
		RearEnd3.setRotationPoint(4.0f, 8.0f, 4.0f);
		RearEnd3.setTextureSize(128, 32);
		RearEnd3.mirror = true;
		setRotation(RearEnd3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		RearEnd.render(par7);
		Leg6.render(par7);
		Leg4.render(par7);
		Leg2.render(par7);
		Leg5.render(par7);
		Leg3.render(par7);
		Leg1.render(par7);
		Leg8.render(par7);
		Leg7.render(par7);
		RearEnd2.render(par7);
		RearEnd3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		final float var8 = 0.7853982f;
		Leg1.rotateAngleZ = -var8;
		Leg2.rotateAngleZ = var8;
		Leg3.rotateAngleZ = -var8 * 0.74f;
		Leg4.rotateAngleZ = var8 * 0.74f;
		Leg5.rotateAngleZ = -var8 * 0.74f;
		Leg6.rotateAngleZ = var8 * 0.74f;
		Leg7.rotateAngleZ = -var8;
		Leg8.rotateAngleZ = var8;
		final float var9 = -0.0f;
		final float var10 = 0.3926991f;
		Leg1.rotateAngleY = var10 * 2.0f + var9;
		Leg2.rotateAngleY = -var10 * 2.0f - var9;
		Leg3.rotateAngleY = var10 * 1.0f + var9;
		Leg4.rotateAngleY = -var10 * 1.0f - var9;
		Leg5.rotateAngleY = -var10 * 1.0f + var9;
		Leg6.rotateAngleY = var10 * 1.0f - var9;
		Leg7.rotateAngleY = -var10 * 2.0f + var9;
		Leg8.rotateAngleY = var10 * 2.0f - var9;
		final float var11 = -(MathHelper.cos(par1 * 0.6662f * 2.0f + 0.0f) * 0.4f) * par2;
		final float var12 = -(MathHelper.cos(par1 * 0.6662f * 2.0f + 3.1415927f) * 0.4f) * par2;
		final float var13 = -(MathHelper.cos(par1 * 0.6662f * 2.0f + 1.5707964f) * 0.4f) * par2;
		final float var14 = -(MathHelper.cos(par1 * 0.6662f * 2.0f + 4.712389f) * 0.4f) * par2;
		final float var15 = Math.abs(MathHelper.sin(par1 * 0.6662f + 0.0f) * 0.4f) * par2;
		final float var16 = Math.abs(MathHelper.sin(par1 * 0.6662f + 3.1415927f) * 0.4f) * par2;
		final float var17 = Math.abs(MathHelper.sin(par1 * 0.6662f + 1.5707964f) * 0.4f) * par2;
		final float var18 = Math.abs(MathHelper.sin(par1 * 0.6662f + 4.712389f) * 0.4f) * par2;
		final ModelRenderer leg1 = Leg1;
		leg1.rotateAngleY += var11;
		final ModelRenderer leg2 = Leg2;
		leg2.rotateAngleY += -var11;
		final ModelRenderer leg3 = Leg3;
		leg3.rotateAngleY += var12;
		final ModelRenderer leg4 = Leg4;
		leg4.rotateAngleY += -var12;
		final ModelRenderer leg5 = Leg5;
		leg5.rotateAngleY += var13;
		final ModelRenderer leg6 = Leg6;
		leg6.rotateAngleY += -var13;
		final ModelRenderer leg7 = Leg7;
		leg7.rotateAngleY += var14;
		final ModelRenderer leg8 = Leg8;
		leg8.rotateAngleY += -var14;
		final ModelRenderer leg9 = Leg1;
		leg9.rotateAngleZ += var15;
		final ModelRenderer leg10 = Leg2;
		leg10.rotateAngleZ += -var15;
		final ModelRenderer leg11 = Leg3;
		leg11.rotateAngleZ += var16;
		final ModelRenderer leg12 = Leg4;
		leg12.rotateAngleZ += -var16;
		final ModelRenderer leg13 = Leg5;
		leg13.rotateAngleZ += var17;
		final ModelRenderer leg14 = Leg6;
		leg14.rotateAngleZ += -var17;
		final ModelRenderer leg15 = Leg7;
		leg15.rotateAngleZ += var18;
		final ModelRenderer leg16 = Leg8;
		leg16.rotateAngleZ += -var18;
	}
}

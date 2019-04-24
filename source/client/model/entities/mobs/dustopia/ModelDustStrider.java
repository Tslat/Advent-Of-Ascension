package net.tslat.aoa3.client.model.entities.mobs.dustopia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDustStrider extends ModelBase {
	ModelRenderer Head;
	ModelRenderer RearEnd;
	ModelRenderer Leg2;
	ModelRenderer Leg1;
	ModelRenderer Leg2p2;
	ModelRenderer Leg1p2;
	ModelRenderer Leg2p3;
	ModelRenderer Leg1p3;
	ModelRenderer Leg2p4;
	ModelRenderer Leg1p4;
	ModelRenderer Leg2p5;
	ModelRenderer Leg1p5;
	ModelRenderer RearEnd2;
	ModelRenderer RearEnd3;
	ModelRenderer RearEnd4;
	ModelRenderer RearEnd5;
	ModelRenderer RearEnd6;
	ModelRenderer RearEnd7;
	ModelRenderer RearEnd8;
	ModelRenderer RearEnd9;
	ModelRenderer RearEnd10;
	ModelRenderer RearEnd11;
	ModelRenderer RearEnd12;
	ModelRenderer RearEnd13;
	ModelRenderer Head2;
	ModelRenderer Head3;
	ModelRenderer Head4;

	public ModelDustStrider() {
		textureWidth = 128;
		textureHeight = 32;
		(Head = new ModelRenderer(this, 58, 23)).addBox(2.0f, 2.0f, -11.0f, 2, 2, 5);
		Head.setRotationPoint(0.0f, 15.0f, -3.0f);
		Head.setTextureSize(128, 32);
		Head.mirror = true;
		setRotation(Head, 0.0f, 0.0f, 0.0f);
		(RearEnd = new ModelRenderer(this, 87, 8)).addBox(1.0f, -12.0f, 0.0f, 2, 2, 2);
		RearEnd.setRotationPoint(1.0f, 14.0f, 9.0f);
		RearEnd.setTextureSize(128, 32);
		RearEnd.mirror = true;
		setRotation(RearEnd, 0.0f, 0.0f, 0.0f);
		(Leg2 = new ModelRenderer(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2.setRotationPoint(4.0f, 18.0f, 10.0f);
		Leg2.setTextureSize(128, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1 = new ModelRenderer(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1.setRotationPoint(-4.0f, 18.0f, 10.0f);
		Leg1.setTextureSize(128, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0.0f, 0.5759587f, -0.1919862f);
		(Leg2p2 = new ModelRenderer(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2p2.setRotationPoint(4.0f, 18.0f, 6.0f);
		Leg2p2.setTextureSize(128, 32);
		Leg2p2.mirror = true;
		setRotation(Leg2p2, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1p2 = new ModelRenderer(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1p2.setRotationPoint(-4.0f, 18.0f, 6.0f);
		Leg1p2.setTextureSize(128, 32);
		Leg1p2.mirror = true;
		setRotation(Leg1p2, 0.0f, 0.5759587f, -0.1919862f);
		(Leg2p3 = new ModelRenderer(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2p3.setRotationPoint(4.0f, 18.0f, 2.0f);
		Leg2p3.setTextureSize(128, 32);
		Leg2p3.mirror = true;
		setRotation(Leg2p3, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1p3 = new ModelRenderer(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1p3.setRotationPoint(-4.0f, 18.0f, 2.0f);
		Leg1p3.setTextureSize(128, 32);
		Leg1p3.mirror = true;
		setRotation(Leg1p3, 0.0f, 0.5759587f, -0.1919862f);
		(Leg2p4 = new ModelRenderer(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2p4.setRotationPoint(4.0f, 18.0f, -6.0f);
		Leg2p4.setTextureSize(128, 32);
		Leg2p4.mirror = true;
		setRotation(Leg2p4, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1p4 = new ModelRenderer(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1p4.setRotationPoint(-4.0f, 18.0f, -6.0f);
		Leg1p4.setTextureSize(128, 32);
		Leg1p4.mirror = true;
		setRotation(Leg1p4, 0.0f, 0.5759587f, -0.1919862f);
		(Leg2p5 = new ModelRenderer(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2p5.setRotationPoint(4.0f, 18.0f, -2.0f);
		Leg2p5.setTextureSize(128, 32);
		Leg2p5.mirror = true;
		setRotation(Leg2p5, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1p5 = new ModelRenderer(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1p5.setRotationPoint(-4.0f, 18.0f, -2.0f);
		Leg1p5.setTextureSize(128, 32);
		Leg1p5.mirror = true;
		setRotation(Leg1p5, 0.0f, 0.5759587f, -0.1919862f);
		(RearEnd2 = new ModelRenderer(this, 0, 2)).addBox(-5.0f, -6.0f, -6.0f, 10, 9, 18);
		RearEnd2.setRotationPoint(0.0f, 14.0f, 0.0f);
		RearEnd2.setTextureSize(128, 32);
		RearEnd2.mirror = true;
		setRotation(RearEnd2, 0.0f, 0.0f, 0.0f);
		(RearEnd3 = new ModelRenderer(this, 87, 8)).addBox(-5.0f, -12.0f, 0.0f, 2, 2, 2);
		RearEnd3.setRotationPoint(1.0f, 14.0f, 9.0f);
		RearEnd3.setTextureSize(128, 32);
		RearEnd3.mirror = true;
		setRotation(RearEnd3, 0.0f, 0.0f, 0.0f);
		(RearEnd4 = new ModelRenderer(this, 72, 7)).addBox(1.0f, -10.0f, -5.0f, 2, 4, 8);
		RearEnd4.setRotationPoint(1.0f, 14.0f, 9.0f);
		RearEnd4.setTextureSize(128, 32);
		RearEnd4.mirror = true;
		setRotation(RearEnd4, 0.0f, 0.0f, 0.0f);
		(RearEnd5 = new ModelRenderer(this, 72, 7)).addBox(-5.0f, -10.0f, -5.0f, 2, 4, 8);
		RearEnd5.setRotationPoint(1.0f, 14.0f, 9.0f);
		RearEnd5.setTextureSize(128, 32);
		RearEnd5.mirror = true;
		setRotation(RearEnd5, 0.0f, 0.0f, 0.0f);
		(RearEnd6 = new ModelRenderer(this, 87, 8)).addBox(1.0f, -12.0f, -4.0f, 2, 2, 2);
		RearEnd6.setRotationPoint(1.0f, 14.0f, 9.0f);
		RearEnd6.setTextureSize(128, 32);
		RearEnd6.mirror = true;
		setRotation(RearEnd6, 0.0f, 0.0f, 0.0f);
		(RearEnd7 = new ModelRenderer(this, 87, 8)).addBox(-5.0f, -12.0f, -4.0f, 2, 2, 2);
		RearEnd7.setRotationPoint(1.0f, 14.0f, 9.0f);
		RearEnd7.setTextureSize(128, 32);
		RearEnd7.mirror = true;
		setRotation(RearEnd7, 0.0f, 0.0f, 0.0f);
		(RearEnd8 = new ModelRenderer(this, 87, 8)).addBox(1.0f, -12.0f, 0.0f, 2, 2, 2);
		RearEnd8.setRotationPoint(1.0f, 14.0f, 0.0f);
		RearEnd8.setTextureSize(128, 32);
		RearEnd8.mirror = true;
		setRotation(RearEnd8, 0.0f, 0.0f, 0.0f);
		(RearEnd9 = new ModelRenderer(this, 87, 8)).addBox(-5.0f, -12.0f, 0.0f, 2, 2, 2);
		RearEnd9.setRotationPoint(1.0f, 14.0f, 0.0f);
		RearEnd9.setTextureSize(128, 32);
		RearEnd9.mirror = true;
		setRotation(RearEnd9, 0.0f, 0.0f, 0.0f);
		(RearEnd10 = new ModelRenderer(this, 72, 7)).addBox(1.0f, -10.0f, -5.0f, 2, 4, 8);
		RearEnd10.setRotationPoint(1.0f, 14.0f, 0.0f);
		RearEnd10.setTextureSize(128, 32);
		RearEnd10.mirror = true;
		setRotation(RearEnd10, 0.0f, 0.0f, 0.0f);
		(RearEnd11 = new ModelRenderer(this, 87, 8)).addBox(1.0f, -12.0f, -4.0f, 2, 2, 2);
		RearEnd11.setRotationPoint(1.0f, 14.0f, 0.0f);
		RearEnd11.setTextureSize(128, 32);
		RearEnd11.mirror = true;
		setRotation(RearEnd11, 0.0f, 0.0f, 0.0f);
		(RearEnd12 = new ModelRenderer(this, 87, 8)).addBox(-5.0f, -12.0f, -4.0f, 2, 2, 2);
		RearEnd12.setRotationPoint(1.0f, 14.0f, 0.0f);
		RearEnd12.setTextureSize(128, 32);
		RearEnd12.mirror = true;
		setRotation(RearEnd12, 0.0f, 0.0f, 0.0f);
		(RearEnd13 = new ModelRenderer(this, 72, 7)).addBox(-5.0f, -10.0f, -5.0f, 2, 4, 8);
		RearEnd13.setRotationPoint(1.0f, 14.0f, 0.0f);
		RearEnd13.setTextureSize(128, 32);
		RearEnd13.mirror = true;
		setRotation(RearEnd13, 0.0f, 0.0f, 0.0f);
		(Head2 = new ModelRenderer(this, 96, 11)).addBox(-5.0f, -9.0f, -7.0f, 10, 10, 0);
		Head2.setRotationPoint(0.0f, 15.0f, -3.0f);
		Head2.setTextureSize(128, 32);
		Head2.mirror = true;
		setRotation(Head2, 0.0f, 0.0f, 0.0f);
		(Head3 = new ModelRenderer(this, 58, 23)).addBox(-4.0f, 2.0f, -11.0f, 2, 2, 5);
		Head3.setRotationPoint(0.0f, 15.0f, -3.0f);
		Head3.setTextureSize(128, 32);
		Head3.mirror = true;
		setRotation(Head3, 0.0f, 0.0f, 0.0f);
		(Head4 = new ModelRenderer(this, 42, 6)).addBox(-4.0f, -6.0f, -8.0f, 8, 8, 5);
		Head4.setRotationPoint(0.0f, 15.0f, -3.0f);
		Head4.setTextureSize(128, 32);
		Head4.mirror = true;
		setRotation(Head4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Head.render(par7);
		RearEnd.render(par7);
		Leg2.render(par7);
		Leg1.render(par7);
		Leg2p2.render(par7);
		Leg1p2.render(par7);
		Leg2p3.render(par7);
		Leg1p3.render(par7);
		Leg2p4.render(par7);
		Leg1p4.render(par7);
		Leg2p5.render(par7);
		Leg1p5.render(par7);
		RearEnd2.render(par7);
		RearEnd3.render(par7);
		RearEnd4.render(par7);
		RearEnd5.render(par7);
		RearEnd6.render(par7);
		RearEnd7.render(par7);
		RearEnd8.render(par7);
		RearEnd9.render(par7);
		RearEnd10.render(par7);
		RearEnd11.render(par7);
		RearEnd12.render(par7);
		RearEnd13.render(par7);
		Head2.render(par7);
		Head3.render(par7);
		Head4.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		float var8 = ((float)Math.PI / 4F);
		Leg1.rotateAngleZ = -var8;
		Leg2.rotateAngleZ = var8;

		Leg1p2.rotateAngleZ = -var8;
		Leg2p2.rotateAngleZ = var8;

		Leg1p3.rotateAngleZ = -var8;
		Leg2p3.rotateAngleZ = var8;

		Leg1p4.rotateAngleZ = -var8;
		Leg2p4.rotateAngleZ = var8;

		Leg1p5.rotateAngleZ = -var8;
		Leg2p5.rotateAngleZ = var8;

		float var9 = -0.0F;
		float var10 = 0.3926991F;
		Leg1.rotateAngleY = var10 * 2.0F + var9;
		Leg2.rotateAngleY = -var10 * 2.0F - var9;

		Leg1p2.rotateAngleY = var10 * 2.0F + var9;
		Leg2p2.rotateAngleY = -var10 * 2.0F - var9;

		Leg1p3.rotateAngleY = var10 * 2.0F + var9;
		Leg2p3.rotateAngleY = -var10 * 2.0F - var9;

		Leg1p4.rotateAngleY = var10 * 2.0F + var9;
		Leg2p4.rotateAngleY = -var10 * 2.0F - var9;

		Leg1p5.rotateAngleY = var10 * 2.0F + var9;
		Leg2p5.rotateAngleY = -var10 * 2.0F - var9;

		float var11 = -(MathHelper.cos(par1 * 0.6662F * 2.0F + 0.0F) * 0.4F) * par2;
		float var15 = Math.abs(MathHelper.sin(par1 * 0.6662F + 0.0F) * 0.4F) * par2;

		Leg1.rotateAngleY += var11;
		Leg2.rotateAngleY += -var11;

		Leg1p2.rotateAngleZ += var15;
		Leg2p2.rotateAngleZ += -var15;

		Leg1p3.rotateAngleY += var11;
		Leg2p3.rotateAngleY += -var11;

		Leg1p4.rotateAngleZ += var15;
		Leg2p4.rotateAngleZ += -var15;

		Leg1p5.rotateAngleY += var11;
		Leg2p5.rotateAngleY += -var11;

		Leg1.rotateAngleZ += var15;
		Leg2.rotateAngleZ += -var15;

		Leg1p2.rotateAngleY += var11;
		Leg2p2.rotateAngleY += -var11;

		Leg1p3.rotateAngleZ += var15;
		Leg2p3.rotateAngleZ += -var15;

		Leg1p4.rotateAngleY += var11;
		Leg2p4.rotateAngleY += -var11;

		Leg1p5.rotateAngleZ += var15;
		Leg2p5.rotateAngleZ += -var15;
	}
}

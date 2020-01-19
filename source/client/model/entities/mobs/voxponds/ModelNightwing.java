package net.tslat.aoa3.client.model.entities.mobs.voxponds;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelNightwing extends ModelBase {
	private ModelRenderer wingR;
	private ModelRenderer shape2;
	private ModelRenderer wingL;
	private ModelRenderer wingL2;
	private ModelRenderer wingR2;
	private ModelRenderer shape3;
	private ModelRenderer shape4;
	private ModelRenderer shape5;
	private ModelRenderer shape6;
	private ModelRenderer shape8;
	private ModelRenderer shape7;
	private ModelRenderer shape9;
	private ModelRenderer shape10;
	private ModelRenderer shape11;

	public ModelNightwing() {
		textureWidth = 128;
		textureHeight = 64;
		(wingR = new ModelRenderer(this, 29, 34)).addBox(-15.0f, -4.0f, -5.0f, 14, 4, 0);
		wingR.setRotationPoint(-5.0f, 12.0f, 4.0f);
		wingR.setTextureSize(128, 64);
		wingR.mirror = true;
		setRotation(wingR, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 97, 13)).addBox(0.0f, 5.0f, 0.0f, 8, 1, 6);
		shape2.setRotationPoint(-4.0f, 9.0f, -12.0f);
		shape2.setTextureSize(128, 64);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(wingL = new ModelRenderer(this, 29, 34)).addBox(0.0f, -4.0f, -5.0f, 14, 4, 0);
		wingL.setRotationPoint(5.0f, 12.0f, 4.0f);
		wingL.setTextureSize(128, 64);
		wingL.mirror = true;
		setRotation(wingL, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 35, 14)).addBox(0.0f, 0.0f, -5.0f, 14, 2, 12);
		wingL2.setRotationPoint(5.0f, 12.0f, 4.0f);
		wingL2.setTextureSize(128, 64);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 35, 14)).addBox(-15.0f, 0.0f, -5.0f, 14, 2, 12);
		wingR2.setRotationPoint(-5.0f, 12.0f, 4.0f);
		wingR2.setTextureSize(128, 64);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 90, 22)).addBox(0.0f, 0.0f, 0.0f, 8, 6, 8);
		shape3.setRotationPoint(-4.0f, 10.0f, 8.0f);
		shape3.setTextureSize(128, 64);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 2, 33)).addBox(1.0f, -2.0f, 6.0f, 4, 4, 8);
		shape4.setRotationPoint(0.0f, 11.0f, 15.0f);
		shape4.setTextureSize(128, 64);
		shape4.mirror = true;
		setRotation(shape4, -0.8726646f, 0.5235988f, 0.0f);
		(shape5 = new ModelRenderer(this, 2, 33)).addBox(1.0f, -2.0f, 6.0f, 4, 4, 8);
		shape5.setRotationPoint(-5.0f, 11.0f, 12.0f);
		shape5.setTextureSize(128, 64);
		shape5.mirror = true;
		setRotation(shape5, -0.8726646f, -0.5235988f, 0.0f);
		(shape6 = new ModelRenderer(this, 19, 47)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 8);
		shape6.setRotationPoint(-5.0f, 11.0f, 12.0f);
		shape6.setTextureSize(128, 64);
		shape6.mirror = true;
		setRotation(shape6, -0.4363323f, -0.5235988f, 0.0f);
		(shape8 = new ModelRenderer(this, 19, 47)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 8);
		shape8.setRotationPoint(0.0f, 11.0f, 15.0f);
		shape8.setTextureSize(128, 64);
		shape8.mirror = true;
		setRotation(shape8, -0.4363323f, 0.5235988f, 0.0f);
		(shape7 = new ModelRenderer(this, 77, 1)).addBox(1.0f, 0.0f, 0.0f, 1, 4, 7);
		shape7.setRotationPoint(1.0f, 10.0f, -11.0f);
		shape7.setTextureSize(128, 64);
		shape7.mirror = true;
		setRotation(shape7, 0.7853982f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 97, 1)).addBox(0.0f, 0.0f, 0.0f, 8, 4, 6);
		shape9.setRotationPoint(-4.0f, 9.0f, -12.0f);
		shape9.setTextureSize(128, 64);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 77, 1)).addBox(1.0f, 0.0f, 0.0f, 1, 4, 7);
		shape10.setRotationPoint(-4.0f, 10.0f, -11.0f);
		shape10.setTextureSize(128, 64);
		shape10.mirror = true;
		setRotation(shape10, 0.7853982f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 68, 37)).addBox(0.0f, 0.0f, 0.0f, 12, 9, 18);
		shape11.setRotationPoint(-6.0f, 9.0f, -6.0f);
		shape11.setTextureSize(128, 64);
		shape11.mirror = true;
		setRotation(shape11, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		wingR.render(par7);
		shape2.render(par7);
		wingL.render(par7);
		wingL2.render(par7);
		wingR2.render(par7);
		shape3.render(par7);
		shape4.render(par7);
		shape5.render(par7);
		shape6.render(par7);
		shape8.render(par7);
		shape7.render(par7);
		shape9.render(par7);
		shape10.render(par7);
		shape11.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		wingR.rotateAngleZ = MathHelper.cos(par3 * 0.3f) * 3.1415927f * 0.15f;
		wingL.rotateAngleZ = -wingR.rotateAngleZ;
		wingR2.rotateAngleZ = MathHelper.cos(par3 * 0.3f) * 3.1415927f * 0.15f;
		wingL2.rotateAngleZ = -wingR2.rotateAngleZ;
	}
}

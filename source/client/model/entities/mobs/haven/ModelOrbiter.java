package net.tslat.aoa3.client.model.entities.mobs.haven;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOrbiter extends ModelBase {
	private ModelRenderer shape1;
	private ModelRenderer S2r;
	private ModelRenderer shape7;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer shape4;
	private ModelRenderer shape5;
	private ModelRenderer shape6;
	private ModelRenderer S2r6;
	private ModelRenderer S2r5;
	private ModelRenderer S2r4;
	private ModelRenderer S2r3;
	private ModelRenderer S2r2;

	public ModelOrbiter() {
		textureWidth = 64;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 33, 15)).addBox(-1.0f, 1.0f, -1.0f, 2, 3, 2);
		shape1.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(S2r = new ModelRenderer(this, 0, 15)).addBox(5.0f, -11.0f, -2.0f, 1, 3, 4);
		S2r.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r.setTextureSize(64, 32);
		S2r.mirror = true;
		setRotation(S2r, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 16, 0)).addBox(-2.0f, -8.0f, -2.0f, 4, 4, 4);
		shape7.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape7.setTextureSize(64, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 32, 0)).addBox(4.0f, -2.0f, -2.0f, 4, 4, 4);
		shape2.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 48, 0)).addBox(-8.0f, -2.0f, -2.0f, 4, 4, 4);
		shape3.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 43, 9)).addBox(-4.0f, -1.0f, -1.0f, 8, 2, 2);
		shape4.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 33, 9)).addBox(-1.0f, -4.0f, -1.0f, 2, 3, 2);
		shape5.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, 4.0f, -2.0f, 4, 4, 4);
		shape6.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape6.setTextureSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(S2r6 = new ModelRenderer(this, 0, 15)).addBox(5.0f, 8.0f, -2.0f, 1, 3, 4);
		S2r6.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r6.setTextureSize(64, 32);
		S2r6.mirror = true;
		setRotation(S2r6, 0.0f, 0.0f, 0.0f);
		(S2r5 = new ModelRenderer(this, 0, 9)).addBox(-5.0f, -10.0f, -2.0f, 10, 1, 4);
		S2r5.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r5.setTextureSize(64, 32);
		S2r5.mirror = true;
		setRotation(S2r5, 0.0f, 0.0f, 0.0f);
		(S2r4 = new ModelRenderer(this, 0, 9)).addBox(-5.0f, 9.0f, -2.0f, 10, 1, 4);
		S2r4.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r4.setTextureSize(64, 32);
		S2r4.mirror = true;
		setRotation(S2r4, 0.0f, 0.0f, 0.0f);
		(S2r3 = new ModelRenderer(this, 0, 15)).addBox(-6.0f, -11.0f, -2.0f, 1, 3, 4);
		S2r3.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r3.setTextureSize(64, 32);
		S2r3.mirror = true;
		setRotation(S2r3, 0.0f, 0.0f, 0.0f);
		(S2r2 = new ModelRenderer(this, 0, 15)).addBox(-6.0f, 8.0f, -2.0f, 1, 3, 4);
		S2r2.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r2.setTextureSize(64, 32);
		S2r2.mirror = true;
		setRotation(S2r2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		shape1.render(par7);
		S2r.render(par7);
		shape7.render(par7);
		shape2.render(par7);
		shape3.render(par7);
		shape4.render(par7);
		shape5.render(par7);
		shape6.render(par7);
		S2r6.render(par7);
		S2r5.render(par7);
		S2r4.render(par7);
		S2r3.render(par7);
		S2r2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		shape1.rotateAngleY = par3 * 0.067f * 1.25f;
		shape2.rotateAngleX = par3 * 0.067f * 1.25f;
		shape3.rotateAngleX = par3 * 0.067f * 1.25f;
		shape4.rotateAngleX = par3 * 0.067f * 1.25f;
		shape5.rotateAngleY = par3 * 0.067f * 1.25f;
		shape6.rotateAngleY = par3 * 0.067f * 1.25f;
		shape7.rotateAngleY = par3 * 0.067f * 1.25f;
		S2r.rotateAngleZ = par3 * 0.067f * 3.25f;
		S2r2.rotateAngleZ = par3 * 0.067f * 3.25f;
		S2r3.rotateAngleZ = par3 * 0.067f * 3.25f;
		S2r4.rotateAngleZ = par3 * 0.067f * 3.25f;
		S2r5.rotateAngleZ = par3 * 0.067f * 3.25f;
		S2r6.rotateAngleZ = par3 * 0.067f * 3.25f;
	}
}

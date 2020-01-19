package net.tslat.aoa3.client.model.entities.mobs.voxponds;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFischer extends ModelBase {
	private ModelRenderer shape1;
	private ModelRenderer shape2;
	private ModelRenderer r1;
	private ModelRenderer shape3;
	private ModelRenderer r2;

	public ModelFischer() {
		textureWidth = 128;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
		shape1.setRotationPoint(-4.0f, 11.0f, 9.0f);
		shape1.setTextureSize(128, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 79, 0)).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
		shape2.setRotationPoint(-6.0f, 9.0f, -6.0f);
		shape2.setTextureSize(128, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 35, 0)).addBox(-5.0f, 0.0f, -5.0f, 10, 2, 10);
		r1.setRotationPoint(0.0f, 22.0f, -0.3333333f);
		r1.setTextureSize(128, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 35, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
		shape3.setRotationPoint(-5.0f, 10.0f, 2.0f);
		shape3.setTextureSize(128, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 35, 0)).addBox(-5.0f, 0.0f, -5.0f, 10, 2, 10);
		r2.setRotationPoint(0.0f, 6.0f, -0.3333333f);
		r2.setTextureSize(128, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		shape1.render(par7);
		shape2.render(par7);
		r1.render(par7);
		shape3.render(par7);
		r2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		r1.rotateAngleY = par3 * 0.067f * 2.25f;
		r2.rotateAngleY = par3 * 0.067f * 2.25f;
	}
}

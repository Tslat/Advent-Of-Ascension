package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAnemia extends ModelBase {
	private ModelRenderer shape1;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer shape4;
	private ModelRenderer shape5;
	private ModelRenderer shape6;
	private ModelRenderer shape7;
	private ModelRenderer shape8;
	private ModelRenderer shape9;
	private ModelRenderer shape10;
	private ModelRenderer shape11;
	private ModelRenderer shape12;

	public ModelAnemia() {
		textureWidth = 64;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 36, 25)).addBox(-6.0f, 0.0f, 1.0f, 6, 4, 1);
		shape1.setRotationPoint(3.0f, 11.0f, -7.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 50, 11)).addBox(0.5f, 0.0f, -1.5f, 3, 8, 3);
		shape2.setRotationPoint(3.0f, -4.0f, 4.0f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 25)).addBox(0.0f, 0.0f, 0.0f, 10, 2, 2);
		shape3.setRotationPoint(-5.0f, 15.0f, -7.0f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
		shape4.setRotationPoint(2.0f, 18.0f, -6.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
		shape5.setRotationPoint(-4.0f, 18.0f, -6.0f);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 25, 25)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 2);
		shape6.setRotationPoint(-5.0f, 11.0f, -7.0f);
		shape6.setTextureSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 0, 25)).addBox(0.0f, 0.0f, 0.0f, 10, 2, 2);
		shape7.setRotationPoint(-5.0f, 9.0f, -7.0f);
		shape7.setTextureSize(64, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 25, 25)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 2);
		shape8.setRotationPoint(3.0f, 11.0f, -7.0f);
		shape8.setTextureSize(64, 32);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
		shape9.setRotationPoint(-6.0f, 8.0f, -5.0f);
		shape9.setTextureSize(64, 32);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 50, 11)).addBox(0.5f, 0.0f, -1.5f, 3, 8, 3);
		shape10.setRotationPoint(-7.0f, -4.0f, 4.0f);
		shape10.setTextureSize(64, 32);
		shape10.mirror = true;
		setRotation(shape10, 0.0f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 38, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 6, 4);
		shape11.setRotationPoint(3.0f, 4.0f, 2.0f);
		shape11.setTextureSize(64, 32);
		shape11.mirror = true;
		setRotation(shape11, 0.0f, 0.0f, 0.0f);
		(shape12 = new ModelRenderer(this, 38, 0)).addBox(0.0f, 0.0f, 0.0f, 4, 6, 4);
		shape12.setRotationPoint(-7.0f, 4.0f, 2.0f);
		shape12.setTextureSize(64, 32);
		shape12.mirror = true;
		setRotation(shape12, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		shape1.render(par7);
		shape2.render(par7);
		shape3.render(par7);
		shape4.render(par7);
		shape5.render(par7);
		shape6.render(par7);
		shape7.render(par7);
		shape8.render(par7);
		shape9.render(par7);
		shape10.render(par7);
		shape11.render(par7);
		shape12.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
	}
}

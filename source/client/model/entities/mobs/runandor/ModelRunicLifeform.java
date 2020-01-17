package net.tslat.aoa3.client.model.entities.mobs.runandor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelRunicLifeform extends ModelBase {
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

	public ModelRunicLifeform() {
		textureWidth = 128;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 51, 13)).addBox(0.0f, 0.0f, 0.0f, 3, 2, 4);
		shape1.setRotationPoint(3.0f, 17.0f, -3.0f);
		shape1.setTextureSize(128, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 51, 22)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 4);
		shape2.setRotationPoint(6.0f, 6.0f, -3.0f);
		shape2.setTextureSize(128, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 1);
		shape3.setRotationPoint(-5.0f, 3.0f, -3.0f);
		shape3.setTextureSize(128, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 51, 22)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 4);
		shape4.setRotationPoint(-8.0f, 6.0f, -3.0f);
		shape4.setTextureSize(128, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 42, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 1, 10);
		shape5.setRotationPoint(-5.0f, 2.0f, -6.0f);
		shape5.setTextureSize(128, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 84, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 1, 10);
		shape6.setRotationPoint(-5.0f, 13.0f, -6.0f);
		shape6.setTextureSize(128, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 73, 23)).addBox(0.0f, 0.0f, 0.0f, 4, 5, 4);
		shape7.setRotationPoint(-2.0f, 14.0f, -3.0f);
		shape7.setTextureSize(128, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 73, 14)).addBox(0.0f, 0.0f, 0.0f, 16, 3, 4);
		shape8.setRotationPoint(-8.0f, 19.0f, -3.0f);
		shape8.setTextureSize(128, 32);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 51, 13)).addBox(0.0f, 0.0f, 0.0f, 3, 2, 4);
		shape9.setRotationPoint(-6.0f, 17.0f, -3.0f);
		shape9.setTextureSize(128, 32);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 25, 10)).addBox(0.0f, 0.0f, 0.0f, 1, 12, 10);
		shape10.setRotationPoint(5.0f, 2.0f, -6.0f);
		shape10.setTextureSize(128, 32);
		shape10.mirror = true;
		setRotation(shape10, 0.0f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 2, 10)).addBox(0.0f, 0.0f, 0.0f, 1, 12, 10);
		shape11.setRotationPoint(-6.0f, 2.0f, -6.0f);
		shape11.setTextureSize(128, 32);
		shape11.mirror = true;
		setRotation(shape11, 0.0f, 0.0f, 0.0f);
		(shape12 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 1);
		shape12.setRotationPoint(-5.0f, 3.0f, 3.0f);
		shape12.setTextureSize(128, 32);
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

package net.tslat.aoa3.client.model.entities.mobs.dustopia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDuston extends ModelBase {
	private ModelRenderer shape1;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer shape4;
	private ModelRenderer shape5;
	private ModelRenderer shape6;
	private ModelRenderer shape7;

	public ModelDuston() {
		textureWidth = 64;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 25, 23)).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
		shape1.setRotationPoint(-0.5f, -4.0f, -0.5f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0174533f);
		(shape2 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
		shape2.setRotationPoint(-5.0f, 5.0f, -5.0f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 25, 28)).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
		shape3.setRotationPoint(-0.5f, 21.0f, -0.5f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 52, 0)).addBox(2.0f, 0.0f, 1.0f, 6, 20, 0);
		shape4.setRotationPoint(-1.0f, 0.0f, -1.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 5, 23)).addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
		shape5.setRotationPoint(-1.0f, -1.0f, -1.0f);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 15, 23)).addBox(0.0f, 0.0f, 0.0f, 2, 6, 2);
		shape6.setRotationPoint(-1.0f, 15.0f, -1.0f);
		shape6.setTextureSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 40, 0)).addBox(-4.0f, 0.0f, 1.0f, 6, 20, 0);
		shape7.setRotationPoint(-3.0f, 0.0f, -1.0f);
		shape7.setTextureSize(64, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
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
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
	}
}

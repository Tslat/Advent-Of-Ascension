package net.tslat.aoa3.client.model.entities.animals;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGummySnail extends ModelBase {
	private ModelRenderer shape1;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer shape4;
	private ModelRenderer shape5;

	public ModelGummySnail() {
		textureWidth = 64;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 26, 18)).addBox(-3.5f, 12.0f, 0.0f, 7, 1, 12);
		shape1.setRotationPoint(0.0f, 11.0f, -3.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, 0.0f, 0.0f, 6, 12, 12);
		shape2.setRotationPoint(0.0f, 11.0f, -2.95f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 34, 0)).addBox(3.0f, 2.0f, 1.0f, 1, 2, 2);
		shape3.setRotationPoint(0.0f, 16.0f, -8.0f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 42, 0)).addBox(-3.0f, 0.0f, 0.0f, 6, 8, 5);
		shape4.setRotationPoint(0.0f, 16.0f, -8.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 34, 0)).addBox(-4.0f, 2.0f, 1.0f, 1, 2, 2);
		shape5.setRotationPoint(0.0f, 16.0f, -8.0f);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		shape1.render(par7);
		shape2.render(par7);
		shape3.render(par7);
		shape4.render(par7);
		shape5.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
	}
}

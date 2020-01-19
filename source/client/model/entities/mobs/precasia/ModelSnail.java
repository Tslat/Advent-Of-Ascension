package net.tslat.aoa3.client.model.entities.mobs.precasia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSnail extends ModelBase {
	private ModelRenderer shape1;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer shape5;
	private ModelRenderer shape6;
	private ModelRenderer shape4;

	public ModelSnail() {
		textureWidth = 64;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 21, 21)).addBox(-2.5f, 0.0f, -0.5f, 2, 2, 2);
		shape1.setRotationPoint(4.0f, 16.0f, -6.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 0)).addBox(-1.0f, 0.0f, 0.0f, 6, 10, 10);
		shape2.setRotationPoint(-2.0f, 14.0f, -2.0f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 21)).addBox(-1.0f, 0.0f, 0.0f, 4, 4, 5);
		shape3.setRotationPoint(-1.0f, 20.0f, -7.0f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 21, 21)).addBox(-2.5f, 0.0f, -0.5f, 2, 2, 2);
		shape5.setRotationPoint(-1.0f, 16.0f, -6.0f);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 19, 26)).addBox(-2.0f, 0.0f, 0.0f, 1, 4, 1);
		shape6.setRotationPoint(4.0f, 18.0f, -6.0f);
		shape6.setTextureSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 19, 26)).addBox(-2.0f, 0.0f, 0.0f, 1, 4, 1);
		shape4.setRotationPoint(-1.0f, 18.0f, -6.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		shape1.render(par7);
		shape2.render(par7);
		shape3.render(par7);
		shape5.render(par7);
		shape6.render(par7);
		shape4.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
	}
}

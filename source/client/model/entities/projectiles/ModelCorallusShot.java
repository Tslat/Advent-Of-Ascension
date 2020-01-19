package net.tslat.aoa3.client.model.entities.projectiles;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCorallusShot extends ModelBase {
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

	public ModelCorallusShot() {
		textureWidth = 64;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 0, 24)).addBox(-8.0f, 0.0f, -3.0f, 2, 1, 1);
		shape1.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 24)).addBox(-3.0f, 3.0f, 1.0f, 6, 1, 1);
		shape2.setRotationPoint(0.0f, 16.0f, 0.0f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 24)).addBox(-6.0f, -3.0f, -3.0f, 1, 6, 1);
		shape3.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -3.0f, -3.0f, 6, 6, 6);
		shape4.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 0, 24)).addBox(2.0f, 4.0f, 1.0f, 1, 2, 1);
		shape5.setRotationPoint(0.0f, 16.0f, 0.0f);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 0, 24)).addBox(-3.0f, -6.0f, -2.0f, 6, 1, 1);
		shape6.setRotationPoint(0.0f, 16.0f, 0.0f);
		shape6.setTextureSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 0, 24)).addBox(-2.0f, -7.0f, -2.0f, 1, 1, 1);
		shape7.setRotationPoint(0.0f, 16.0f, 0.0f);
		shape7.setTextureSize(64, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 0, 24)).addBox(-1.0f, 4.0f, 1.0f, 1, 1, 1);
		shape8.setRotationPoint(0.0f, 16.0f, 0.0f);
		shape8.setTextureSize(64, 32);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 0, 24)).addBox(5.0f, -3.0f, 1.0f, 1, 6, 1);
		shape9.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape9.setTextureSize(64, 32);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 0, 24)).addBox(6.0f, -2.0f, 1.0f, 2, 1, 1);
		shape10.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape10.setTextureSize(64, 32);
		shape10.mirror = true;
		setRotation(shape10, 0.0f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 0, 24)).addBox(6.0f, 1.0f, 1.0f, 2, 1, 1);
		shape11.setRotationPoint(0.0f, 15.0f, 0.0f);
		shape11.setTextureSize(64, 32);
		shape11.mirror = true;
		setRotation(shape11, 0.0f, 0.0f, 0.0f);
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
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity entity) {
		shape1.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		shape2.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		shape3.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		shape4.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		shape5.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		shape6.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		shape7.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		shape8.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		shape9.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		shape10.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
		shape11.rotateAngleZ += MathHelper.sin(par2 * 0.067f) * 0.25f;
	}
}

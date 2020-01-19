package net.tslat.aoa3.client.model.entities.mobs.haven;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelVoliant extends ModelBase {
	private ModelRenderer shape1;
	private ModelRenderer shape2;
	private ModelRenderer wingR;
	private ModelRenderer shape4;
	private ModelRenderer wingL;
	private ModelRenderer shape3;
	private ModelRenderer shape5;
	private ModelRenderer shape6;
	private ModelRenderer shape7;
	private ModelRenderer shape8;
	private ModelRenderer shape9;

	public ModelVoliant() {
		textureWidth = 128;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
		shape1.setRotationPoint(-4.0f, 7.0f, 9.0f);
		shape1.setTextureSize(128, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 8, 17)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 11);
		shape2.setRotationPoint(4.0f, 11.0f, 12.0f);
		shape2.setTextureSize(128, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(wingR = new ModelRenderer(this, 35, 0)).addBox(-10.0f, 0.0f, -5.0f, 10, 2, 10);
		wingR.setRotationPoint(-5.0f, 8.0f, 5.0f);
		wingR.setTextureSize(128, 32);
		wingR.mirror = true;
		setRotation(wingR, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 35, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
		shape4.setRotationPoint(-5.0f, 6.0f, 2.0f);
		shape4.setTextureSize(128, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(wingL = new ModelRenderer(this, 35, 0)).addBox(0.0f, 0.0f, -5.0f, 10, 2, 10);
		wingL.setRotationPoint(5.0f, 8.0f, 5.0f);
		wingL.setTextureSize(128, 32);
		wingL.mirror = true;
		setRotation(wingL, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 79, 0)).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
		shape3.setRotationPoint(-6.0f, 5.0f, -6.0f);
		shape3.setTextureSize(128, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 8, 17)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 11);
		shape5.setRotationPoint(-6.0f, 11.0f, 12.0f);
		shape5.setTextureSize(128, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 40, 3)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 8);
		shape6.setRotationPoint(4.0f, 1.0f, -4.0f);
		shape6.setTextureSize(128, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 40, 3)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 8);
		shape7.setRotationPoint(-6.0f, 1.0f, -4.0f);
		shape7.setTextureSize(128, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 40, 3)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 13);
		shape8.setRotationPoint(-6.0f, 17.0f, -4.0f);
		shape8.setTextureSize(128, 32);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 40, 3)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 13);
		shape9.setRotationPoint(4.0f, 17.0f, -4.0f);
		shape9.setTextureSize(128, 32);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		shape1.render(par7);
		shape2.render(par7);
		wingR.render(par7);
		shape4.render(par7);
		wingL.render(par7);
		shape3.render(par7);
		shape5.render(par7);
		shape6.render(par7);
		shape7.render(par7);
		shape8.render(par7);
		shape9.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		wingR.rotateAngleZ = MathHelper.cos(par3 * 0.1f) * 3.1415927f * 0.15f;
		wingL.rotateAngleZ = -wingR.rotateAngleZ;
	}
}

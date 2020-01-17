package net.tslat.aoa3.client.model.entities.mobs.greckon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelValkyrie extends ModelBase {
	private ModelRenderer shape2;
	private ModelRenderer wingR1;
	private ModelRenderer wingR2;
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
	private ModelRenderer shape13;
	private ModelRenderer shape14;
	private ModelRenderer wingL1;
	private ModelRenderer wingR3;
	private ModelRenderer wingL2;
	private ModelRenderer wingL3;

	public ModelValkyrie() {
		textureWidth = 128;
		textureHeight = 64;
		(shape2 = new ModelRenderer(this, 39, 30)).addBox(0.0f, 0.0f, 0.0f, 0, 5, 18);
		shape2.setRotationPoint(6.0f, 15.0f, -6.0f);
		shape2.setTextureSize(128, 64);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(wingR1 = new ModelRenderer(this, 35, 14)).addBox(-10.0f, 0.0f, -5.0f, 10, 2, 10);
		wingR1.setRotationPoint(-5.0f, 9.0f, 5.0f);
		wingR1.setTextureSize(128, 64);
		wingR1.mirror = true;
		setRotation(wingR1, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 35, 0)).addBox(-10.0f, 2.0f, 3.0f, 2, 3, 2);
		wingR2.setRotationPoint(-5.0f, 9.0f, 5.0f);
		wingR2.setTextureSize(128, 64);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 85, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
		shape3.setRotationPoint(-2.0f, 10.0f, -12.0f);
		shape3.setTextureSize(128, 64);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 39, 30)).addBox(0.0f, 0.0f, 0.0f, 0, 5, 18);
		shape4.setRotationPoint(-6.0f, 15.0f, -6.0f);
		shape4.setTextureSize(128, 64);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 97, 1)).addBox(0.0f, 0.0f, 0.0f, 8, 4, 6);
		shape5.setRotationPoint(-4.0f, 6.0f, -12.0f);
		shape5.setTextureSize(128, 64);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 79, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
		shape6.setRotationPoint(-4.0f, 10.0f, -12.0f);
		shape6.setTextureSize(128, 64);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 79, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
		shape7.setRotationPoint(3.0f, 10.0f, -12.0f);
		shape7.setTextureSize(128, 64);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 85, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
		shape8.setRotationPoint(1.0f, 10.0f, -12.0f);
		shape8.setTextureSize(128, 64);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 68, 37)).addBox(0.0f, 0.0f, 0.0f, 12, 9, 18);
		shape9.setRotationPoint(-6.0f, 6.0f, -6.0f);
		shape9.setTextureSize(128, 64);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 9, 53)).addBox(0.0f, 2.0f, 2.0f, 2, 2, 5);
		shape10.setRotationPoint(-1.0f, 0.0f, 17.5f);
		shape10.setTextureSize(128, 64);
		shape10.mirror = true;
		setRotation(shape10, 2.879793f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 1, 2)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 9);
		shape11.setRotationPoint(-3.0f, 9.0f, 8.0f);
		shape11.setTextureSize(128, 64);
		shape11.mirror = true;
		setRotation(shape11, 0.4363323f, 0.0f, 0.0f);
		(shape12 = new ModelRenderer(this, 5, 19)).addBox(0.0f, 0.0f, 0.0f, 5, 5, 7);
		shape12.setRotationPoint(-2.5f, 9.0f, 14.0f);
		shape12.setTextureSize(128, 64);
		shape12.mirror = true;
		setRotation(shape12, 1.22173f, 0.0f, 0.0f);
		(shape13 = new ModelRenderer(this, 10, 32)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 5);
		shape13.setRotationPoint(-2.0f, 4.0f, 16.5f);
		shape13.setTextureSize(128, 64);
		shape13.mirror = true;
		setRotation(shape13, 1.570796f, 0.0f, 0.0f);
		(shape14 = new ModelRenderer(this, 9, 43)).addBox(0.0f, 0.0f, -1.0f, 3, 3, 5);
		shape14.setRotationPoint(-1.5f, 0.0f, 17.0f);
		shape14.setTextureSize(128, 64);
		shape14.mirror = true;
		setRotation(shape14, 2.268928f, 0.0f, 0.0f);
		(wingL1 = new ModelRenderer(this, 35, 14)).addBox(0.0f, 0.0f, -5.0f, 10, 2, 10);
		wingL1.setRotationPoint(5.0f, 9.0f, 5.0f);
		wingL1.setTextureSize(128, 64);
		wingL1.mirror = true;
		setRotation(wingL1, 0.0f, 0.0f, 0.0f);
		(wingR3 = new ModelRenderer(this, 35, 0)).addBox(-10.0f, 2.0f, -5.0f, 2, 3, 2);
		wingR3.setRotationPoint(-5.0f, 9.0f, 5.0f);
		wingR3.setTextureSize(128, 64);
		wingR3.mirror = true;
		setRotation(wingR3, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 35, 0)).addBox(8.0f, 2.0f, 3.0f, 2, 3, 2);
		wingL2.setRotationPoint(5.0f, 9.0f, 5.0f);
		wingL2.setTextureSize(128, 64);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
		(wingL3 = new ModelRenderer(this, 35, 0)).addBox(8.0f, 2.0f, -5.0f, 2, 3, 2);
		wingL3.setRotationPoint(5.0f, 9.0f, 5.0f);
		wingL3.setTextureSize(128, 64);
		wingL3.mirror = true;
		setRotation(wingL3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		shape2.render(par7);
		wingR1.render(par7);
		wingR2.render(par7);
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
		shape13.render(par7);
		shape14.render(par7);
		wingL1.render(par7);
		wingR3.render(par7);
		wingL2.render(par7);
		wingL3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		wingR1.rotateAngleZ = MathHelper.cos(par3 * 0.5f) * 3.1415927f * 0.35f;
		wingL1.rotateAngleZ = -wingR1.rotateAngleZ;
		wingR2.rotateAngleZ = MathHelper.cos(par3 * 0.5f) * 3.1415927f * 0.35f;
		wingL2.rotateAngleZ = -wingR2.rotateAngleZ;
		wingR3.rotateAngleZ = MathHelper.cos(par3 * 0.5f) * 3.1415927f * 0.35f;
		wingL3.rotateAngleZ = -wingR3.rotateAngleZ;
	}
}

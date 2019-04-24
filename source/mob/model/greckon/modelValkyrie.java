package net.nevermine.mob.model.greckon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelValkyrie extends ModelBase {
	ModelRenderer Shape2;
	ModelRenderer wingR1;
	ModelRenderer wingR2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;
	ModelRenderer Shape14;
	ModelRenderer wingL1;
	ModelRenderer wingR3;
	ModelRenderer wingL2;
	ModelRenderer wingL3;

	public modelValkyrie() {
		textureWidth = 128;
		textureHeight = 64;
		(Shape2 = new ModelRenderer(this, 39, 30)).addBox(0.0f, 0.0f, 0.0f, 0, 5, 18);
		Shape2.setRotationPoint(6.0f, 15.0f, -6.0f);
		Shape2.setTextureSize(128, 64);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
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
		(Shape3 = new ModelRenderer(this, 85, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
		Shape3.setRotationPoint(-2.0f, 10.0f, -12.0f);
		Shape3.setTextureSize(128, 64);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 39, 30)).addBox(0.0f, 0.0f, 0.0f, 0, 5, 18);
		Shape4.setRotationPoint(-6.0f, 15.0f, -6.0f);
		Shape4.setTextureSize(128, 64);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 97, 1)).addBox(0.0f, 0.0f, 0.0f, 8, 4, 6);
		Shape5.setRotationPoint(-4.0f, 6.0f, -12.0f);
		Shape5.setTextureSize(128, 64);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 79, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
		Shape6.setRotationPoint(-4.0f, 10.0f, -12.0f);
		Shape6.setTextureSize(128, 64);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0f, 0.0f, 0.0f);
		(Shape7 = new ModelRenderer(this, 79, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
		Shape7.setRotationPoint(3.0f, 10.0f, -12.0f);
		Shape7.setTextureSize(128, 64);
		Shape7.mirror = true;
		setRotation(Shape7, 0.0f, 0.0f, 0.0f);
		(Shape8 = new ModelRenderer(this, 85, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
		Shape8.setRotationPoint(1.0f, 10.0f, -12.0f);
		Shape8.setTextureSize(128, 64);
		Shape8.mirror = true;
		setRotation(Shape8, 0.0f, 0.0f, 0.0f);
		(Shape9 = new ModelRenderer(this, 68, 37)).addBox(0.0f, 0.0f, 0.0f, 12, 9, 18);
		Shape9.setRotationPoint(-6.0f, 6.0f, -6.0f);
		Shape9.setTextureSize(128, 64);
		Shape9.mirror = true;
		setRotation(Shape9, 0.0f, 0.0f, 0.0f);
		(Shape10 = new ModelRenderer(this, 9, 53)).addBox(0.0f, 2.0f, 2.0f, 2, 2, 5);
		Shape10.setRotationPoint(-1.0f, 0.0f, 17.5f);
		Shape10.setTextureSize(128, 64);
		Shape10.mirror = true;
		setRotation(Shape10, 2.879793f, 0.0f, 0.0f);
		(Shape11 = new ModelRenderer(this, 1, 2)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 9);
		Shape11.setRotationPoint(-3.0f, 9.0f, 8.0f);
		Shape11.setTextureSize(128, 64);
		Shape11.mirror = true;
		setRotation(Shape11, 0.4363323f, 0.0f, 0.0f);
		(Shape12 = new ModelRenderer(this, 5, 19)).addBox(0.0f, 0.0f, 0.0f, 5, 5, 7);
		Shape12.setRotationPoint(-2.5f, 9.0f, 14.0f);
		Shape12.setTextureSize(128, 64);
		Shape12.mirror = true;
		setRotation(Shape12, 1.22173f, 0.0f, 0.0f);
		(Shape13 = new ModelRenderer(this, 10, 32)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 5);
		Shape13.setRotationPoint(-2.0f, 4.0f, 16.5f);
		Shape13.setTextureSize(128, 64);
		Shape13.mirror = true;
		setRotation(Shape13, 1.570796f, 0.0f, 0.0f);
		(Shape14 = new ModelRenderer(this, 9, 43)).addBox(0.0f, 0.0f, -1.0f, 3, 3, 5);
		Shape14.setRotationPoint(-1.5f, 0.0f, 17.0f);
		Shape14.setTextureSize(128, 64);
		Shape14.mirror = true;
		setRotation(Shape14, 2.268928f, 0.0f, 0.0f);
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
		Shape2.render(par7);
		wingR1.render(par7);
		wingR2.render(par7);
		Shape3.render(par7);
		Shape4.render(par7);
		Shape5.render(par7);
		Shape6.render(par7);
		Shape7.render(par7);
		Shape8.render(par7);
		Shape9.render(par7);
		Shape10.render(par7);
		Shape11.render(par7);
		Shape12.render(par7);
		Shape13.render(par7);
		Shape14.render(par7);
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

package net.tslat.aoa3.client.model.entities.mobs.barathos;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTharafly extends ModelBase {
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer Shape6;
	ModelRenderer Shape7;
	ModelRenderer WingR1;
	ModelRenderer WingR2;
	ModelRenderer WingL1;
	ModelRenderer WingL2;
	ModelRenderer Shape8;
	ModelRenderer Shape9;
	ModelRenderer Shape10;
	ModelRenderer Shape11;
	ModelRenderer Shape12;
	ModelRenderer Shape13;

	public ModelTharafly() {
		textureWidth = 128;
		textureHeight = 32;
		(Shape1 = new ModelRenderer(this, 26, 0)).addBox(6.0f, 0.0f, 2.0f, 4, 2, 4);
		Shape1.setRotationPoint(-3.0f, 11.0f, 3.0f);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 0, 20)).addBox(-1.0f, 0.0f, -2.0f, 4, 4, 8);
		Shape2.setRotationPoint(-1.0f, 13.0f, -2.0f);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.5235988f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 44, 2)).addBox(7.0f, 0.0f, 2.0f, 6, 2, 4);
		Shape3.setRotationPoint(-3.0f, 14.0f, -8.0f);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 69, 25)).addBox(0.0f, -2.0f, 0.0f, 2, 2, 2);
		Shape4.setRotationPoint(3.0f, 8.0f, 2.0f);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0.5235988f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 0, 0)).addBox(-1.0f, 0.0f, 0.0f, 8, 8, 7);
		Shape5.setRotationPoint(-3.0f, 12.0f, -8.0f);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
		(Shape6 = new ModelRenderer(this, 44, 2)).addBox(-7.0f, 0.0f, 2.0f, 6, 2, 4);
		Shape6.setRotationPoint(-3.0f, 14.0f, -8.0f);
		Shape6.setTextureSize(64, 32);
		Shape6.mirror = true;
		setRotation(Shape6, 0.0f, 0.0f, 0.0f);
		(Shape7 = new ModelRenderer(this, 26, 0)).addBox(-4.0f, 0.0f, 2.0f, 4, 2, 4);
		Shape7.setRotationPoint(-3.0f, 11.0f, 3.0f);
		Shape7.setTextureSize(64, 32);
		Shape7.mirror = true;
		setRotation(Shape7, 0.0f, 0.0f, 0.0f);
		(WingR1 = new ModelRenderer(this, 28, 10)).addBox(-10.0f, 0.0f, -4.0f, 10, 1, 8);
		WingR1.setRotationPoint(-10.0f, 15.0f, -4.0f);
		WingR1.setTextureSize(64, 32);
		WingR1.mirror = true;
		setRotation(WingR1, 0.0f, 0.0f, 0.0f);
		(WingR2 = new ModelRenderer(this, 28, 10)).addBox(-10.0f, 0.0f, -4.0f, 10, 1, 8);
		WingR2.setRotationPoint(-7.0f, 12.0f, 7.0f);
		WingR2.setTextureSize(64, 32);
		WingR2.mirror = true;
		setRotation(WingR2, 0.0f, 0.0f, 0.0f);
		(WingL1 = new ModelRenderer(this, 28, 21)).addBox(0.0f, 0.0f, -4.0f, 10, 1, 8);
		WingL1.setRotationPoint(10.0f, 15.0f, -4.0f);
		WingL1.setTextureSize(64, 32);
		WingL1.mirror = true;
		setRotation(WingL1, 0.0f, 0.0f, 0.0f);
		(WingL2 = new ModelRenderer(this, 28, 21)).addBox(0.0f, 0.0f, -4.0f, 10, 1, 8);
		WingL2.setRotationPoint(7.0f, 12.0f, 7.0f);
		WingL2.setTextureSize(64, 32);
		WingL2.mirror = true;
		setRotation(WingL2, 0.0f, 0.0f, 0.0f);
		(Shape8 = new ModelRenderer(this, 70, 0)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 7);
		Shape8.setRotationPoint(-3.0f, 9.0f, 3.0f);
		Shape8.setTextureSize(64, 32);
		Shape8.mirror = true;
		setRotation(Shape8, 0.0f, 0.0f, 0.0f);
		(Shape9 = new ModelRenderer(this, 69, 25)).addBox(0.0f, -2.0f, 0.0f, 2, 2, 2);
		Shape9.setRotationPoint(-5.0f, 8.0f, 2.0f);
		Shape9.setTextureSize(64, 32);
		Shape9.mirror = true;
		setRotation(Shape9, 0.5235988f, 0.0f, 0.0f);
		(Shape10 = new ModelRenderer(this, 86, 16)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 7);
		Shape10.setRotationPoint(3.0f, 8.0f, 2.0f);
		Shape10.setTextureSize(64, 32);
		Shape10.mirror = true;
		setRotation(Shape10, 0.5235988f, 0.0f, 0.0f);
		(Shape11 = new ModelRenderer(this, 86, 16)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 7);
		Shape11.setRotationPoint(-5.0f, 8.0f, 2.0f);
		Shape11.setTextureSize(64, 32);
		Shape11.mirror = true;
		setRotation(Shape11, 0.5235988f, 0.0f, 0.0f);
		(Shape12 = new ModelRenderer(this, 65, 15)).addBox(0.0f, -3.0f, 0.0f, 2, 1, 7);
		Shape12.setRotationPoint(3.0f, 8.0f, 2.0f);
		Shape12.setTextureSize(64, 32);
		Shape12.mirror = true;
		setRotation(Shape12, 0.5235988f, 0.0f, 0.0f);
		(Shape13 = new ModelRenderer(this, 65, 15)).addBox(0.0f, -3.0f, 0.0f, 2, 1, 7);
		Shape13.setRotationPoint(-5.0f, 8.0f, 2.0f);
		Shape13.setTextureSize(64, 32);
		Shape13.mirror = true;
		setRotation(Shape13, 0.5235988f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Shape1.render(par7);
		Shape2.render(par7);
		Shape3.render(par7);
		Shape4.render(par7);
		Shape5.render(par7);
		Shape6.render(par7);
		Shape7.render(par7);
		WingR1.render(par7);
		WingR2.render(par7);
		WingL1.render(par7);
		WingL2.render(par7);
		Shape8.render(par7);
		Shape9.render(par7);
		Shape10.render(par7);
		Shape11.render(par7);
		Shape12.render(par7);
		Shape13.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		WingR1.rotateAngleZ = MathHelper.cos(par3 * 2.1f) * 3.1415927f * 0.15f;
		WingL1.rotateAngleZ = -WingR1.rotateAngleZ;
		WingR2.rotateAngleZ = MathHelper.cos(par3 * 2.1f) * 3.1415927f * 0.15f;
		WingL2.rotateAngleZ = -WingR2.rotateAngleZ;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.barathos;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTharafly extends ModelBase {
	private ModelRenderer shape1;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer shape4;
	private ModelRenderer shape5;
	private ModelRenderer shape6;
	private ModelRenderer shape7;
	private ModelRenderer WingR1;
	private ModelRenderer WingR2;
	private ModelRenderer WingL1;
	private ModelRenderer WingL2;
	private ModelRenderer shape8;
	private ModelRenderer shape9;
	private ModelRenderer shape10;
	private ModelRenderer shape11;
	private ModelRenderer shape12;
	private ModelRenderer shape13;

	public ModelTharafly() {
		textureWidth = 128;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 26, 0)).addBox(6.0f, 0.0f, 2.0f, 4, 2, 4);
		shape1.setRotationPoint(-3.0f, 11.0f, 3.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 20)).addBox(-1.0f, 0.0f, -2.0f, 4, 4, 8);
		shape2.setRotationPoint(-1.0f, 13.0f, -2.0f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.5235988f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 44, 2)).addBox(7.0f, 0.0f, 2.0f, 6, 2, 4);
		shape3.setRotationPoint(-3.0f, 14.0f, -8.0f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 69, 25)).addBox(0.0f, -2.0f, 0.0f, 2, 2, 2);
		shape4.setRotationPoint(3.0f, 8.0f, 2.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.5235988f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 0, 0)).addBox(-1.0f, 0.0f, 0.0f, 8, 8, 7);
		shape5.setRotationPoint(-3.0f, 12.0f, -8.0f);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 44, 2)).addBox(-7.0f, 0.0f, 2.0f, 6, 2, 4);
		shape6.setRotationPoint(-3.0f, 14.0f, -8.0f);
		shape6.setTextureSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 26, 0)).addBox(-4.0f, 0.0f, 2.0f, 4, 2, 4);
		shape7.setRotationPoint(-3.0f, 11.0f, 3.0f);
		shape7.setTextureSize(64, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
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
		(shape8 = new ModelRenderer(this, 70, 0)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 7);
		shape8.setRotationPoint(-3.0f, 9.0f, 3.0f);
		shape8.setTextureSize(64, 32);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 69, 25)).addBox(0.0f, -2.0f, 0.0f, 2, 2, 2);
		shape9.setRotationPoint(-5.0f, 8.0f, 2.0f);
		shape9.setTextureSize(64, 32);
		shape9.mirror = true;
		setRotation(shape9, 0.5235988f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 86, 16)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 7);
		shape10.setRotationPoint(3.0f, 8.0f, 2.0f);
		shape10.setTextureSize(64, 32);
		shape10.mirror = true;
		setRotation(shape10, 0.5235988f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 86, 16)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 7);
		shape11.setRotationPoint(-5.0f, 8.0f, 2.0f);
		shape11.setTextureSize(64, 32);
		shape11.mirror = true;
		setRotation(shape11, 0.5235988f, 0.0f, 0.0f);
		(shape12 = new ModelRenderer(this, 65, 15)).addBox(0.0f, -3.0f, 0.0f, 2, 1, 7);
		shape12.setRotationPoint(3.0f, 8.0f, 2.0f);
		shape12.setTextureSize(64, 32);
		shape12.mirror = true;
		setRotation(shape12, 0.5235988f, 0.0f, 0.0f);
		(shape13 = new ModelRenderer(this, 65, 15)).addBox(0.0f, -3.0f, 0.0f, 2, 1, 7);
		shape13.setRotationPoint(-5.0f, 8.0f, 2.0f);
		shape13.setTextureSize(64, 32);
		shape13.mirror = true;
		setRotation(shape13, 0.5235988f, 0.0f, 0.0f);
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
		WingR1.render(par7);
		WingR2.render(par7);
		WingL1.render(par7);
		WingL2.render(par7);
		shape8.render(par7);
		shape9.render(par7);
		shape10.render(par7);
		shape11.render(par7);
		shape12.render(par7);
		shape13.render(par7);
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

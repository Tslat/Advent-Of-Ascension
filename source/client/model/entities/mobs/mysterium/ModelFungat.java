package net.tslat.aoa3.client.model.entities.mobs.mysterium;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFungat extends ModelBase {
	private ModelRenderer shape1;
	private ModelRenderer shape3;
	private ModelRenderer WingL;
	private ModelRenderer WingR;
	private ModelRenderer shape4;
	private ModelRenderer shape6;

	public ModelFungat() {
		textureWidth = 64;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 0, 14)).addBox(0.0f, 0.0f, 0.0f, 5, 2, 2);
		shape1.setRotationPoint(3.0f, 14.0f, -2.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 14)).addBox(0.0f, 0.0f, 0.0f, 5, 2, 2);
		shape3.setRotationPoint(-8.0f, 14.0f, -2.0f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(WingL = new ModelRenderer(this, 32, 0)).addBox(0.0f, 0.0f, -4.0f, 8, 1, 8);
		WingL.setRotationPoint(8.0f, 15.0f, -1.0f);
		WingL.setTextureSize(64, 32);
		WingL.mirror = true;
		setRotation(WingL, 0.0f, 0.0f, 0.0f);
		(WingR = new ModelRenderer(this, 32, 0)).addBox(-8.0f, 0.0f, -4.0f, 8, 1, 8);
		WingR.setRotationPoint(-8.0f, 15.0f, -1.0f);
		WingR.setTextureSize(64, 32);
		WingR.mirror = true;
		setRotation(WingR, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 16, 14)).addBox(-3.0f, -6.0f, -2.0f, 12, 6, 12);
		shape4.setRotationPoint(-3.0f, 12.0f, -4.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 0, 0)).addBox(-1.0f, 0.0f, 0.0f, 8, 6, 8);
		shape6.setRotationPoint(-3.0f, 12.0f, -4.0f);
		shape6.setTextureSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		shape1.render(par7);
		shape3.render(par7);
		WingL.render(par7);
		WingR.render(par7);
		shape4.render(par7);
		shape6.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		WingR.rotateAngleZ = MathHelper.cos(par3 * 2.1f) * 3.1415927f * 0.15f;
		WingL.rotateAngleZ = -WingR.rotateAngleZ;
	}
}

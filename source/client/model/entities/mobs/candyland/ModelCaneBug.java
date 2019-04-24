package net.tslat.aoa3.client.model.entities.mobs.candyland;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCaneBug extends ModelBase {
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer body;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer leg6;
	ModelRenderer leg5;

	public ModelCaneBug() {
		textureWidth = 128;
		textureHeight = 32;
		(leg1 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, 0.0f, -1.0f, 2, 8, 2);
		leg1.setRotationPoint(-4.0f, 16.0f, 12.0f);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, -1.0f, 2, 8, 2);
		leg2.setRotationPoint(4.0f, 16.0f, 12.0f);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, 0.0f, -2.0f, 2, 8, 2);
		leg3.setRotationPoint(-4.0f, 16.0f, -5.0f);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, -2.0f, 2, 8, 2);
		leg4.setRotationPoint(4.0f, 16.0f, -5.0f);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 5, 4)).addBox(-6.0f, -10.0f, -7.0f, 8, 21, 7);
		body.setRotationPoint(2.0f, 10.0f, 19.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 71, 12)).addBox(-6.0f, -10.0f, -7.0f, 8, 11, 8);
		body2.setRotationPoint(2.0f, 1.0f, 11.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 37, 2)).addBox(-6.0f, -10.0f, -7.0f, 8, 22, 8);
		body3.setRotationPoint(2.0f, 14.0f, 0.0f);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 1.570796f, 0.0f, 0.0f);
		(leg6 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, -1.0f, 2, 8, 2);
		leg6.setRotationPoint(4.0f, 16.0f, 3.0f);
		leg6.setTextureSize(64, 32);
		leg6.mirror = true;
		setRotation(leg6, 0.0f, 0.0f, 0.0f);
		(leg5 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, 0.0f, -1.0f, 2, 8, 2);
		leg5.setRotationPoint(-4.0f, 16.0f, 3.0f);
		leg5.setTextureSize(64, 32);
		leg5.mirror = true;
		setRotation(leg5, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		body.render(par7);
		body2.render(par7);
		body3.render(par7);
		leg6.render(par7);
		leg5.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg1.rotateAngleY = 0.0f;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3.rotateAngleY = 0.0f;
		leg5.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg5.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg6.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelNatura extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer body0;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer arm1;
	ModelRenderer arm2;
	ModelRenderer head2;
	ModelRenderer head3;

	public ModelNatura() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 43, 0)).addBox(4.0f, -5.0f, -4.0f, 1, 3, 6);
		head.setRotationPoint(0.0f, 1.0f, -4.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 51, 46)).addBox(3.0f, -12.0f, -6.0f, 6, 10, 0);
		body.setRotationPoint(0.0f, 11.0f, 2.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
		leg1.setRotationPoint(-6.0f, 12.0f, 10.0f);
		leg1.setTextureSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
		leg2.setRotationPoint(6.0f, 12.0f, 10.0f);
		leg2.setTextureSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 24, 48)).addBox(-3.0f, 0.0f, -3.0f, 4, 10, 4);
		leg3.setRotationPoint(-6.0f, 12.0f, -4.0f);
		leg3.setTextureSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, -1.570796f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 24, 48)).addBox(-1.0f, 0.0f, -3.0f, 4, 10, 4);
		leg4.setRotationPoint(6.0f, 12.0f, -4.0f);
		leg4.setTextureSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, -1.570796f, 0.0f, 0.0f);
		(body0 = new ModelRenderer(this, 1, 35)).addBox(-5.0f, -4.0f, -2.0f, 2, 18, 7);
		body0.setRotationPoint(4.0f, 11.0f, 2.0f);
		body0.setTextureSize(64, 64);
		body0.mirror = true;
		setRotation(body0, 2.617994f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 23, 35)).addBox(-3.0f, -12.0f, -7.0f, 6, 10, 2);
		body2.setRotationPoint(0.0f, 11.0f, 2.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 40, 35)).addBox(-9.0f, -12.0f, -6.0f, 6, 10, 0);
		body3.setRotationPoint(0.0f, 11.0f, 2.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 23, 9)).addBox(-6.0f, -10.0f, -5.0f, 12, 18, 7);
		body4.setRotationPoint(0.0f, 11.0f, 2.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 1.570796f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 1, 35)).addBox(-5.0f, -4.0f, -2.0f, 2, 18, 7);
		body5.setRotationPoint(0.0f, 11.0f, 2.0f);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 2.181662f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 1, 35)).addBox(-5.0f, -4.0f, -2.0f, 2, 18, 7);
		body6.setRotationPoint(8.0f, 11.0f, 2.0f);
		body6.setTextureSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 2.181662f, 0.0f, 0.0f);
		(arm1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
		arm1.setRotationPoint(-6.0f, 12.0f, 0.0f);
		arm1.setTextureSize(64, 64);
		arm1.mirror = true;
		setRotation(arm1, 0.0f, 0.0f, 0.0f);
		(arm2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
		arm2.setRotationPoint(6.0f, 12.0f, 0.0f);
		arm2.setTextureSize(64, 64);
		arm2.mirror = true;
		setRotation(arm2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head2.setRotationPoint(0.0f, 1.0f, -4.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 43, 0)).addBox(-5.0f, -5.0f, -4.0f, 1, 3, 6);
		head3.setRotationPoint(0.0f, 1.0f, -4.0f);
		head3.setTextureSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		body0.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
		arm1.render(par7);
		arm2.render(par7);
		head2.render(par7);
		head3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		arm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		arm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.barathos;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelKeeler extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer leg4p2;
	ModelRenderer leg3p2;
	ModelRenderer leg4p3;
	ModelRenderer leg3p3;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer body7;
	ModelRenderer body8;
	ModelRenderer head2;
	ModelRenderer head3;

	public ModelKeeler() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 47, 0)).addBox(-2.0f, -4.0f, -8.0f, 5, 8, 2);
		head.setRotationPoint(-0.5f, 11.0f, -2.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 51, 18)).addBox(-4.0f, 1.0f, -7.0f, 8, 8, 6);
		body.setRotationPoint(0.0f, 14.0f, 7.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 1.22173f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 26, 22)).addBox(-2.0f, 9.0f, 1.0f, 2, 2, 8);
		leg3.setRotationPoint(-8.0f, 12.0f, -1.0f);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 26, 22)).addBox(0.0f, 9.0f, 1.0f, 2, 2, 8);
		leg4.setRotationPoint(8.0f, 12.0f, -1.0f);
		leg4.setTextureSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg4p2 = new ModelRenderer(this, 23, 10)).addBox(-2.0f, -2.0f, -4.0f, 6, 4, 6);
		leg4p2.setRotationPoint(8.0f, 12.0f, -1.0f);
		leg4p2.setTextureSize(128, 32);
		leg4p2.mirror = true;
		setRotation(leg4p2, 0.0f, 0.0f, 0.0f);
		(leg3p2 = new ModelRenderer(this, 23, 10)).addBox(-4.0f, -2.0f, -4.0f, 6, 4, 6);
		leg3p2.setRotationPoint(-8.0f, 12.0f, -1.0f);
		leg3p2.setTextureSize(128, 32);
		leg3p2.mirror = true;
		setRotation(leg3p2, 0.0f, 0.0f, 0.0f);
		(leg4p3 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
		leg4p3.setRotationPoint(8.0f, 12.0f, -1.0f);
		leg4p3.setTextureSize(128, 32);
		leg4p3.mirror = true;
		setRotation(leg4p3, 0.0f, 0.0f, 0.0f);
		(leg3p3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
		leg3p3.setRotationPoint(-8.0f, 12.0f, -1.0f);
		leg3p3.setTextureSize(128, 32);
		leg3p3.mirror = true;
		setRotation(leg3p3, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 88, 0)).addBox(-1.0f, -11.0f, 4.0f, 2, 4, 6);
		body2.setRotationPoint(-7.0f, 20.0f, 5.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 82, 11)).addBox(-6.0f, -10.0f, -7.0f, 12, 11, 10);
		body3.setRotationPoint(0.0f, 14.0f, 7.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 1.22173f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 66, 0)).addBox(-2.0f, -8.0f, 3.0f, 4, 6, 6);
		body4.setRotationPoint(-7.0f, 20.0f, 5.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 0.5235988f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 66, 0)).addBox(-2.0f, -8.0f, 3.0f, 4, 6, 6);
		body5.setRotationPoint(0.0f, 14.0f, 7.0f);
		body5.setTextureSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 1.22173f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 88, 0)).addBox(-1.0f, -11.0f, 4.0f, 2, 4, 6);
		body6.setRotationPoint(0.0f, 14.0f, 7.0f);
		body6.setTextureSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 0.6981317f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 66, 0)).addBox(-2.0f, -8.0f, 3.0f, 4, 6, 6);
		body7.setRotationPoint(7.0f, 20.0f, 5.0f);
		body7.setTextureSize(128, 32);
		body7.mirror = true;
		setRotation(body7, 0.5235988f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 88, 0)).addBox(-1.0f, -11.0f, 4.0f, 2, 4, 6);
		body8.setRotationPoint(7.0f, 20.0f, 5.0f);
		body8.setTextureSize(128, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 26, 0)).addBox(-1.0f, -3.0f, -10.0f, 3, 6, 2);
		head2.setRotationPoint(-0.5f, 11.0f, -2.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 0)).addBox(-1.0f, -4.0f, -6.0f, 3, 8, 6);
		head3.setRotationPoint(-0.5f, 11.0f, -2.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		leg4p2.render(par7);
		leg3p2.render(par7);
		leg4p3.render(par7);
		leg3p3.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
		body7.render(par7);
		body8.render(par7);
		head2.render(par7);
		head3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3.rotateAngleY = 0.0f;
		leg3p2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3p2.rotateAngleY = 0.0f;
		leg3p3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3p3.rotateAngleY = 0.0f;
		leg4p2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4p3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

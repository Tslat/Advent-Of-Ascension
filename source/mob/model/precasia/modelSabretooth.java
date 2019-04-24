package net.nevermine.mob.model.precasia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelSabretooth extends ModelBase {
	ModelRenderer head1;
	ModelRenderer body1;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer head5;
	ModelRenderer body2;
	ModelRenderer head6;

	public modelSabretooth() {
		textureWidth = 64;
		textureHeight = 32;
		(head1 = new ModelRenderer(this, 30, 0)).addBox(2.0f, -7.0f, -1.0f, 2, 3, 1);
		head1.setRotationPoint(0.0f, 11.0f, -8.0f);
		head1.setTextureSize(64, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 18, 19)).addBox(-1.0f, 6.0f, 1.0f, 2, 11, 2);
		body1.setRotationPoint(0.0f, 9.0f, 2.0f);
		body1.setTextureSize(64, 32);
		body1.mirror = true;
		setRotation(body1, 0.8726646f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 8, 4);
		leg1.setRotationPoint(-3.0f, 16.0f, 7.0f);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 8, 4);
		leg2.setRotationPoint(3.0f, 16.0f, 7.0f);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 8, 4);
		leg3.setRotationPoint(-3.0f, 16.0f, -5.0f);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 8, 4);
		leg4.setRotationPoint(3.0f, 16.0f, -5.0f);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 59, 3)).addBox(2.0f, 2.0f, -7.0f, 1, 8, 1);
		head2.setRotationPoint(0.0f, 11.0f, -8.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 30, 0)).addBox(-4.0f, -7.0f, -1.0f, 2, 3, 1);
		head3.setRotationPoint(0.0f, 11.0f, -8.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 37, 2)).addBox(-2.0f, 0.0f, -7.0f, 4, 3, 1);
		head4.setRotationPoint(0.0f, 11.0f, -8.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 59, 3)).addBox(-3.0f, 2.0f, -7.0f, 1, 8, 1);
		head5.setRotationPoint(0.0f, 11.0f, -8.0f);
		head5.setTextureSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 26, 7)).addBox(-6.0f, -10.0f, -7.0f, 12, 18, 7);
		body2.setRotationPoint(0.0f, 9.0f, 2.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head6.setRotationPoint(0.0f, 11.0f, -8.0f);
		head6.setTextureSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head1.render(par7);
		body1.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		body2.render(par7);
		head6.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head1.rotateAngleY = par4 / 79.57747f;
		head1.rotateAngleX = par5 / 76.39437f;
		head2.rotateAngleY = par4 / 79.57747f;
		head2.rotateAngleX = par5 / 76.39437f;
		head3.rotateAngleY = par4 / 79.57747f;
		head3.rotateAngleX = par5 / 76.39437f;
		head4.rotateAngleY = par4 / 79.57747f;
		head4.rotateAngleX = par5 / 76.39437f;
		head5.rotateAngleY = par4 / 79.57747f;
		head5.rotateAngleX = par5 / 76.39437f;
		head6.rotateAngleY = par4 / 79.57747f;
		head6.rotateAngleX = par5 / 76.39437f;
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
	}
}

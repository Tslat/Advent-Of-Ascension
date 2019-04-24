package net.tslat.aoa3.client.model.entities.minions;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCorby extends ModelBase {
	ModelRenderer head2;
	ModelRenderer body1;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer head1;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer body2;
	ModelRenderer body3;

	public ModelCorby() {
		textureWidth = 128;
		textureHeight = 32;
		(head2 = new ModelRenderer(this, 58, 0)).addBox(4.0f, 4.0f, -6.0f, 0, 4, 6);
		head2.setRotationPoint(0.0f, 11.0f, -8.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 73, 7)).addBox(-4.0f, -8.0f, -3.0f, 3, 15, 8);
		body1.setRotationPoint(5.0f, 9.0f, 2.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 2.094395f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 8, 4);
		leg1.setRotationPoint(-3.0f, 16.0f, 7.0f);
		leg1.setTextureSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 8, 4);
		leg2.setRotationPoint(3.0f, 16.0f, 7.0f);
		leg2.setTextureSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 8, 4);
		leg3.setRotationPoint(-3.0f, 16.0f, -5.0f);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 8, 4);
		leg4.setRotationPoint(3.0f, 16.0f, -5.0f);
		leg4.setTextureSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(head1 = new ModelRenderer(this, 72, 0)).addBox(-4.0f, 4.0f, -6.0f, 8, 4, 0);
		head1.setRotationPoint(0.0f, 11.0f, -8.0f);
		head1.setTextureSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 58, 0)).addBox(-4.0f, 4.0f, -6.0f, 0, 4, 6);
		head3.setRotationPoint(0.0f, 11.0f, -8.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head4.setRotationPoint(0.0f, 11.0f, -8.0f);
		head4.setTextureSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 26, 6)).addBox(-6.0f, -10.0f, -7.0f, 12, 18, 8);
		body2.setRotationPoint(0.0f, 9.0f, 2.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 73, 7)).addBox(-4.0f, -8.0f, -3.0f, 3, 15, 8);
		body3.setRotationPoint(0.0f, 9.0f, 2.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 2.094395f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head2.render(par7);
		body1.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		head1.render(par7);
		head3.render(par7);
		head4.render(par7);
		body2.render(par7);
		body3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head1.rotateAngleY = par4 / 57.295776f;
		head1.rotateAngleX = par5 / 54.11268f;
		head2.rotateAngleY = par4 / 57.295776f;
		head2.rotateAngleX = par5 / 54.11268f;
		head3.rotateAngleY = par4 / 57.295776f;
		head3.rotateAngleX = par5 / 54.11268f;
		head4.rotateAngleY = par4 / 57.295776f;
		head4.rotateAngleX = par5 / 54.11268f;
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg1.rotateAngleY = 0.0f;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSphinx extends ModelBase {
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg4;
	ModelRenderer leg3;
	ModelRenderer body2;
	ModelRenderer head1;
	ModelRenderer head2;
	ModelRenderer head3;

	public ModelSphinx() {
		textureWidth = 64;
		textureHeight = 32;
		(body = new ModelRenderer(this, 28, 8)).addBox(-5.0f, -8.0f, -7.0f, 10, 6, 8);
		body.setRotationPoint(0.0f, 17.0f, 17.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -7.0f, 4, 4, 8);
		leg1.setRotationPoint(-7.0f, 20.0f, 13.0f);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -7.0f, 4, 4, 8);
		leg2.setRotationPoint(7.0f, 20.0f, 13.0f);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -7.0f, 4, 4, 8);
		leg4.setRotationPoint(-7.0f, 20.0f, -5.0f);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -7.0f, 4, 4, 8);
		leg3.setRotationPoint(7.0f, 20.0f, -5.0f);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 28, 8)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body2.setRotationPoint(0.0f, 17.0f, 3.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(head1 = new ModelRenderer(this, 45, 0)).addBox(-15.0f, -8.0f, 4.0f, 8, 4, 1);
		head1.setRotationPoint(0.0f, 12.0f, -5.0f);
		head1.setTextureSize(64, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 1.570796f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -5.0f, 8, 8, 5);
		head2.setRotationPoint(0.0f, 12.0f, -5.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 26, 0)).addBox(-15.0f, 4.0f, 4.0f, 8, 4, 1);
		head3.setRotationPoint(0.0f, 12.0f, -5.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 1.570796f);
		head2.addChild(head1);
		head2.addChild(head3);

	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg4.render(par7);
		leg3.render(par7);
		body2.render(par7);
		head2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head2.rotateAngleY = par4 / (float)(180f / Math.PI);
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg1.rotateAngleY = 0.0f;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

package net.nevermine.mob.model.barathos;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelNospike extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg1p2;
	ModelRenderer leg2p2;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer head5;

	public modelNospike() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 34, 22)).addBox(-0.5f, -4.0f, -14.0f, 1, 1, 2);
		head.setRotationPoint(0.0f, 8.0f, -3.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 39)).addBox(-6.0f, -10.0f, -7.0f, 12, 10, 12);
		body.setRotationPoint(0.0f, 14.0f, 6.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 30)).addBox(-1.0f, -6.0f, -6.0f, 3, 8, 8);
		leg1.setRotationPoint(-8.0f, 12.0f, 7.0f);
		leg1.setTextureSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 30)).addBox(-2.0f, -6.0f, -6.0f, 3, 8, 8);
		leg2.setRotationPoint(8.0f, 12.0f, 7.0f);
		leg2.setTextureSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg1p2 = new ModelRenderer(this, 0, 47)).addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
		leg1p2.setRotationPoint(-8.0f, 12.0f, 6.0f);
		leg1p2.setTextureSize(64, 64);
		leg1p2.mirror = true;
		setRotation(leg1p2, 0.0f, 0.0f, 0.0f);
		(leg2p2 = new ModelRenderer(this, 0, 47)).addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
		leg2p2.setRotationPoint(8.0f, 12.0f, 6.0f);
		leg2p2.setTextureSize(64, 64);
		leg2p2.mirror = true;
		setRotation(leg2p2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 8);
		head2.setRotationPoint(0.0f, 8.0f, -3.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 34, 0)).addBox(-3.0f, -4.0f, -8.0f, 6, 6, 2);
		head3.setRotationPoint(0.0f, 8.0f, -3.0f);
		head3.setTextureSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 34, 11)).addBox(-2.0f, -4.0f, -10.0f, 4, 4, 2);
		head4.setRotationPoint(0.0f, 8.0f, -3.0f);
		head4.setTextureSize(64, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 34, 22)).addBox(-1.0f, -4.0f, -12.0f, 2, 2, 2);
		head5.setRotationPoint(0.0f, 8.0f, -3.0f);
		head5.setTextureSize(64, 64);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg1p2.render(par7);
		leg2p2.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg1.rotateAngleY = 0.0f;
		leg1p2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg1p2.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg2p2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

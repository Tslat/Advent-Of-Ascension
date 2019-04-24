package net.nevermine.mob.model.creeponia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelWingedCreeper extends ModelBase {
	ModelRenderer head;
	ModelRenderer wingL1;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer body;
	ModelRenderer wingR1;
	ModelRenderer wingR2;
	ModelRenderer wingL2;

	public modelWingedCreeper() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 6.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(wingL1 = new ModelRenderer(this, 42, 28)).addBox(2.0f, 1.0f, 0.0f, 10, 8, 0);
		wingL1.setRotationPoint(2.0f, 8.0f, 2.0f);
		wingL1.setTextureSize(64, 32);
		wingL1.mirror = true;
		setRotation(wingL1, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg3.setRotationPoint(-2.0f, 18.0f, -4.0f);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg4.setRotationPoint(2.0f, 18.0f, -4.0f);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(0.0f, 0.0f, -2.0f, 4, 6, 4);
		leg1.setRotationPoint(-4.0f, 18.0f, 4.0f);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg2.setRotationPoint(2.0f, 18.0f, 4.0f);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, 6.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(wingR1 = new ModelRenderer(this, 42, 19)).addBox(-12.0f, 1.0f, 0.0f, 10, 8, 0);
		wingR1.setRotationPoint(-2.0f, 8.0f, 2.0f);
		wingR1.setTextureSize(64, 32);
		wingR1.mirror = true;
		setRotation(wingR1, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 34, 3)).addBox(-12.0f, -1.0f, -1.0f, 12, 2, 2);
		wingR2.setRotationPoint(-2.0f, 8.0f, 2.0f);
		wingR2.setTextureSize(64, 32);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 34, 8)).addBox(0.0f, -1.0f, -1.0f, 12, 2, 2);
		wingL2.setRotationPoint(2.0f, 8.0f, 2.0f);
		wingL2.setTextureSize(64, 32);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		wingL1.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		body.render(par7);
		wingR1.render(par7);
		wingR2.render(par7);
		wingL2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 57.295776f;
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		wingR1.rotateAngleZ = MathHelper.cos(par3 * 0.4f) * 3.1415927f * 0.15f;
		wingL1.rotateAngleZ = -wingR1.rotateAngleZ;
		wingR2.rotateAngleZ = MathHelper.cos(par3 * 0.4f) * 3.1415927f * 0.15f;
		wingL2.rotateAngleZ = -wingR1.rotateAngleZ;
	}
}

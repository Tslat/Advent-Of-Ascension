package net.tslat.aoa3.client.model.entities.mobs.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelArcworm extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer leg1;
	private ModelRenderer leg2;
	private ModelRenderer leg3;
	private ModelRenderer leg4;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer body4;
	private ModelRenderer body5;
	private ModelRenderer body6;
	private ModelRenderer body7;
	private ModelRenderer leg5;
	private ModelRenderer leg6;
	private ModelRenderer leg7;
	private ModelRenderer leg8;
	private ModelRenderer body8;
	private ModelRenderer body9;

	public ModelArcworm() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6);
		head.setRotationPoint(0.0F, 14.0F, -8.0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 23, 10);
		body.addBox(-6.0F, 14.0F, -7.0F, 12, 5, 8);
		body.setRotationPoint(0.0F, 11.0F, 2.0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 1.570796F, 0.0F, 0.0F);
		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-3.0F, 0.0F, -2.0F, 4, 6, 4);
		leg1.setRotationPoint(-3.0F, 18.0F, 19.0F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0F, 0.0F, 0.0F);
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-1.0F, 0.0F, -2.0F, 4, 6, 4);
		leg2.setRotationPoint(3.0F, 18.0F, 19.0F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0F, 0.0F, 0.0F);
		leg3 = new ModelRenderer(this, 0, 16);
		leg3.addBox(-3.0F, 0.0F, -3.0F, 4, 6, 4);
		leg3.setRotationPoint(-3.0F, 18.0F, 12.0F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0F, 0.0F, 0.0F);
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.addBox(-1.0F, 0.0F, -3.0F, 4, 6, 4);
		leg4.setRotationPoint(3.0F, 18.0F, 12.0F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 23, 25);
		body2.addBox(-4.0F, -5.0F, -6.0F, 8, 3, 6);
		body2.setRotationPoint(0.0F, 11.0F, 2.0F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796F, 0.0F, 0.0F);
		body3 = new ModelRenderer(this, 23, 25);
		body3.addBox(-4.0F, 3.0F, -6.0F, 8, 3, 6);
		body3.setRotationPoint(0.0F, 11.0F, 2.0F);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 1.570796F, 0.0F, 0.0F);
		body4 = new ModelRenderer(this, 23, 25);
		body4.addBox(-4.0F, 11.0F, -6.0F, 8, 3, 6);
		body4.setRotationPoint(0.0F, 11.0F, 2.0F);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 1.570796F, 0.0F, 0.0F);
		body5 = new ModelRenderer(this, 23, 35);
		body5.addBox(-7.5F, -17.0F, -7.0F, 16, 16, 0);
		body5.setRotationPoint(5.0F, 11.0F, -1.0F);
		body5.setTextureSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0F, 0.7853982F, 0.0F);
		body6 = new ModelRenderer(this, 23, 10);
		body6.addBox(-6.0F, -2.0F, -7.0F, 12, 5, 8);
		body6.setRotationPoint(0.0F, 11.0F, 2.0F);
		body6.setTextureSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 1.570796F, 0.0F, 0.0F);
		body7 = new ModelRenderer(this, 23, 10);
		body7.addBox(-6.0F, 6.0F, -7.0F, 12, 5, 8);
		body7.setRotationPoint(0.0F, 11.0F, 2.0F);
		body7.setTextureSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 1.570796F, 0.0F, 0.0F);
		leg5 = new ModelRenderer(this, 0, 16);
		leg5.addBox(-1.0F, 0.0F, -2.0F, 4, 6, 4);
		leg5.setRotationPoint(3.0F, 18.0F, 3.0F);
		leg5.setTextureSize(64, 32);
		leg5.mirror = true;
		setRotation(leg5, 0.0F, 0.0F, 0.0F);
		leg6 = new ModelRenderer(this, 0, 16);
		leg6.addBox(-3.0F, 0.0F, -2.0F, 4, 6, 4);
		leg6.setRotationPoint(-3.0F, 18.0F, 3.0F);
		leg6.setTextureSize(64, 32);
		leg6.mirror = true;
		setRotation(leg6, 0.0F, 0.0F, 0.0F);
		leg7 = new ModelRenderer(this, 0, 16);
		leg7.addBox(-1.0F, 0.0F, -3.0F, 4, 6, 4);
		leg7.setRotationPoint(3.0F, 18.0F, -4.0F);
		leg7.setTextureSize(64, 32);
		leg7.mirror = true;
		setRotation(leg7, 0.0F, 0.0F, 0.0F);
		leg8 = new ModelRenderer(this, 0, 16);
		leg8.addBox(-3.0F, 0.0F, -3.0F, 4, 6, 4);
		leg8.setRotationPoint(-3.0F, 18.0F, -4.0F);
		leg8.setTextureSize(64, 32);
		leg8.mirror = true;
		setRotation(leg8, 0.0F, 0.0F, 0.0F);
		body8 = new ModelRenderer(this, 23, 10);
		body8.addBox(-6.0F, -10.0F, -7.0F, 12, 5, 8);
		body8.setRotationPoint(0.0F, 11.0F, 2.0F);
		body8.setTextureSize(64, 32);
		body8.mirror = true;
		setRotation(body8, 1.570796F, 0.0F, 0.0F);
		body9 = new ModelRenderer(this, 23, 35);
		body9.addBox(-8.5F, -17.0F, -7.0F, 16, 16, 0);
		body9.setRotationPoint(-4.0F, 11.0F, -1.0F);
		body9.setTextureSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0F, -0.7853982F, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
		body7.render(par7);
		leg5.render(par7);
		leg6.render(par7);
		leg7.render(par7);
		leg8.render(par7);
		body8.render(par7);
		body9.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		leg1.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		leg1.rotateAngleY = 0.0F;

		leg3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		leg3.rotateAngleY = 0.0F;

		leg5.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		leg5.rotateAngleY = 0.0F;

		leg7.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		leg7.rotateAngleY = 0.0F;

		leg2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
		leg4.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
		leg6.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
		leg8.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
	}
}

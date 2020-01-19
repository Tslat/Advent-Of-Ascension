package net.tslat.aoa3.client.model.entities.animals;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTrotter extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer leg3;
	private ModelRenderer leg4;
	private ModelRenderer leg3p2;
	private ModelRenderer leg4p2;
	private ModelRenderer leg3p3;
	private ModelRenderer leg4p3;
	private ModelRenderer body2;
	private ModelRenderer body3;

	public ModelTrotter() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head.setRotationPoint(0.0f, 10.0f, -8.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 25, 34)).addBox(6.0f, -7.0f, -1.0f, 2, 20, 7);
		body.setRotationPoint(0.0f, 8.0f, 2.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 2.356194f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 53)).addBox(0.0f, 10.0f, -7.0f, 2, 2, 4);
		leg3.setRotationPoint(-8.0f, 12.0f, 4.0f);
		leg3.setTextureSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 53)).addBox(2.0f, 10.0f, -7.0f, 2, 2, 4);
		leg4.setRotationPoint(8.0f, 12.0f, 4.0f);
		leg4.setTextureSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg3p2 = new ModelRenderer(this, 0, 30)).addBox(-4.0f, 0.0f, -3.0f, 6, 12, 6);
		leg3p2.setRotationPoint(-8.0f, 12.0f, 4.0f);
		leg3p2.setTextureSize(64, 64);
		leg3p2.mirror = true;
		setRotation(leg3p2, 0.0f, 0.0f, 0.0f);
		(leg4p2 = new ModelRenderer(this, 0, 30)).addBox(-2.0f, 0.0f, -3.0f, 6, 12, 6);
		leg4p2.setRotationPoint(8.0f, 12.0f, 4.0f);
		leg4p2.setTextureSize(64, 64);
		leg4p2.mirror = true;
		setRotation(leg4p2, 0.0f, 0.0f, 0.0f);
		(leg3p3 = new ModelRenderer(this, 0, 53)).addBox(-4.0f, 10.0f, -7.0f, 2, 2, 4);
		leg3p3.setRotationPoint(-8.0f, 12.0f, 4.0f);
		leg3p3.setTextureSize(64, 64);
		leg3p3.mirror = true;
		setRotation(leg3p3, 0.0f, 0.0f, 0.0f);
		(leg4p3 = new ModelRenderer(this, 0, 53)).addBox(-2.0f, 10.0f, -7.0f, 2, 2, 4);
		leg4p3.setRotationPoint(8.0f, 12.0f, 4.0f);
		leg4p3.setTextureSize(64, 64);
		leg4p3.mirror = true;
		setRotation(leg4p3, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 18, 4)).addBox(-6.0f, -10.0f, -7.0f, 12, 18, 10);
		body2.setRotationPoint(0.0f, 8.0f, 2.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 25, 34)).addBox(-8.0f, -7.0f, -1.0f, 2, 20, 7);
		body3.setRotationPoint(0.0f, 8.0f, 2.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 2.356194f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		leg3p2.render(par7);
		leg4p2.render(par7);
		leg3p3.render(par7);
		leg4p3.render(par7);
		body2.render(par7);
		body3.render(par7);
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
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4p2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4p3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

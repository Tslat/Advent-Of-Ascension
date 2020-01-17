package net.tslat.aoa3.client.model.entities.animals;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCoratee extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer leg1;
	private ModelRenderer leg2;
	private ModelRenderer leg3;
	private ModelRenderer leg4;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer body4;

	public ModelCoratee() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -4.0f, -6.0f, 10, 10, 6);
		head.setRotationPoint(0.0f, 6.0f, -4.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 112, 18)).addBox(0.0f, 17.0f, -3.0f, 2, 8, 6);
		body.setRotationPoint(2.0f, 7.0f, 6.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 1.22173f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 8, 4);
		leg1.setRotationPoint(-5.0f, 16.0f, 12.0f);
		leg1.setTextureSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 8, 4);
		leg2.setRotationPoint(5.0f, 16.0f, 12.0f);
		leg2.setTextureSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 8, 4);
		leg3.setRotationPoint(-5.0f, 16.0f, -1.0f);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 8, 4);
		leg4.setRotationPoint(5.0f, 16.0f, -1.0f);
		leg4.setTextureSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 35, 0)).addBox(-6.0f, -10.0f, -7.0f, 14, 18, 14);
		body2.setRotationPoint(-1.0f, 9.0f, 6.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 95, 0)).addBox(-6.0f, 7.0f, -4.0f, 8, 10, 8);
		body3.setRotationPoint(2.0f, 7.0f, 6.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 1.22173f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 95, 18)).addBox(-6.0f, 17.0f, -3.0f, 2, 8, 6);
		body4.setRotationPoint(2.0f, 7.0f, 6.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 1.22173f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float scale) {
		setRotationAngles(par2, par3, par4, par5, par6, scale, par1Entity);

		if (isChild) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(0, 11 * scale, 1 * scale);
			GlStateManager.scale(0.75f, 0.75f, 0.75f);
			head.render(scale);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5f, 0.5f, 0.5f);
			GlStateManager.translate(0, 24 * scale, 0);
			head.render(scale);
			body.render(scale);
			leg1.render(scale);
			leg2.render(scale);
			leg3.render(scale);
			leg4.render(scale);
			body2.render(scale);
			body3.render(scale);
			body4.render(scale);
			GlStateManager.popMatrix();
		}
		else {
			head.render(scale);
			body.render(scale);
			leg1.render(scale);
			leg2.render(scale);
			leg3.render(scale);
			leg4.render(scale);
			body2.render(scale);
			body3.render(scale);
			body4.render(scale);
		}
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 79.57747f;
		head.rotateAngleX = par5 / 76.39437f;
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
	}
}

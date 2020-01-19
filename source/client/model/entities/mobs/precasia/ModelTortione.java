package net.tslat.aoa3.client.model.entities.mobs.precasia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTortione extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body1;
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

	public ModelTortione() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -3.0f, -8.0f, 8, 5, 8);
		head.setRotationPoint(0.0f, 12.0f, -6.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 77, 4)).addBox(-5.0f, -10.0f, -7.0f, 1, 18, 10);
		body1.setRotationPoint(11.0f, 11.0f, 3.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 1.570796f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg1.setRotationPoint(-3.0f, 18.0f, 7.0f);
		leg1.setTextureSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg2.setRotationPoint(3.0f, 18.0f, 7.0f);
		leg2.setTextureSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg3.setRotationPoint(-3.0f, 18.0f, -5.0f);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg4.setRotationPoint(3.0f, 18.0f, -5.0f);
		leg4.setTextureSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 102, 13)).addBox(-5.0f, -10.0f, -7.0f, 12, 18, 1);
		body2.setRotationPoint(-1.0f, 2.0f, 3.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 9, 21)).addBox(-5.0f, -10.0f, -7.0f, 2, 1, 10);
		body3.setRotationPoint(10.0f, 11.0f, 2.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 1.570796f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 34, 8)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body4.setRotationPoint(0.0f, 11.0f, 3.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 1.570796f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 77, 4)).addBox(-5.0f, -10.0f, -7.0f, 1, 18, 10);
		body5.setRotationPoint(-2.0f, 11.0f, 3.0f);
		body5.setTextureSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 1.570796f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 79, 1)).addBox(-5.0f, -10.0f, -7.0f, 14, 1, 10);
		body6.setRotationPoint(-2.0f, 11.0f, 21.0f);
		body6.setTextureSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 1.570796f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 9, 21)).addBox(-5.0f, -10.0f, -7.0f, 2, 1, 10);
		body7.setRotationPoint(-2.0f, 11.0f, 2.0f);
		body7.setTextureSize(128, 32);
		body7.mirror = true;
		setRotation(body7, 1.570796f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body1.render(par7);
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
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
	}
}

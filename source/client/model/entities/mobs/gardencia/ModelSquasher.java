package net.tslat.aoa3.client.model.entities.mobs.gardencia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSquasher extends ModelBase {
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer leftarm2;
	ModelRenderer rightarm2;

	public ModelSquasher() {
		textureWidth = 128;
		textureHeight = 32;
		(body = new ModelRenderer(this, 107, 1)).addBox(-5.0f, -1.0f, -4.0f, 4, 1, 4);
		body.setRotationPoint(3.0f, -9.0f, 3.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 18, 20)).addBox(-3.0f, 3.0f, 1.0f, 4, 6, 4);
		rightarm.setRotationPoint(-7.0f, 5.0f, 0.0f);
		rightarm.setTextureSize(128, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 18, 20)).addBox(-1.0f, 3.0f, 1.0f, 4, 6, 4);
		leftarm.setRotationPoint(7.0f, 5.0f, 0.0f);
		leftarm.setTextureSize(128, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg.setRotationPoint(-6.0f, 12.0f, 1.0f);
		rightleg.setTextureSize(128, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg.setRotationPoint(6.0f, 12.0f, 1.0f);
		leftleg.setTextureSize(128, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 78, 11)).addBox(-6.0f, 0.0f, -5.0f, 12, 9, 12);
		body2.setRotationPoint(0.0f, 3.0f, 0.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 0, 0)).addBox(-6.0f, 0.0f, -5.0f, 10, 2, 10);
		body3.setRotationPoint(1.0f, 12.0f, 1.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 35, 20)).addBox(-6.0f, 0.0f, -5.0f, 10, 2, 10);
		body4.setRotationPoint(1.0f, 1.0f, 1.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 41, 0)).addBox(-6.0f, 0.0f, -5.0f, 8, 9, 8);
		body5.setRotationPoint(2.0f, -8.0f, 2.0f);
		body5.setTextureSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 78, 1)).addBox(-5.0f, -1.0f, -4.0f, 6, 1, 6);
		body6.setRotationPoint(2.0f, -8.0f, 2.0f);
		body6.setTextureSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 18, 20)).addBox(-1.0f, 3.0f, -3.0f, 4, 6, 4);
		leftarm2.setRotationPoint(7.0f, 5.0f, 0.0f);
		leftarm2.setTextureSize(128, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 18, 20)).addBox(-3.0f, 3.0f, -3.0f, 4, 6, 4);
		rightarm2.setRotationPoint(-7.0f, 5.0f, 0.0f);
		rightarm2.setTextureSize(128, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
		leftarm2.render(par7);
		rightarm2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

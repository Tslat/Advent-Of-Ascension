package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSkellox extends ModelBase {
	ModelRenderer head;
	ModelRenderer body1;
	ModelRenderer rightarm1;
	ModelRenderer leftarm1;
	ModelRenderer rightleg;
	ModelRenderer leftleg1;
	ModelRenderer rightleg2;
	ModelRenderer leftleg2;
	ModelRenderer body2;
	ModelRenderer leftarm2;
	ModelRenderer rightarm2;

	public ModelSkellox() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, -4.0f, -5.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 68, 0)).addBox(-4.0f, 0.0f, 3.0f, 10, 8, 18);
		body1.setRotationPoint(-1.0f, 4.0f, -10.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(rightarm1 = new ModelRenderer(this, 49, 24)).addBox(1.0f, -3.0f, -2.0f, 2, 3, 4);
		rightarm1.setRotationPoint(-7.0f, -1.0f, -5.0f);
		rightarm1.setTextureSize(128, 32);
		rightarm1.mirror = true;
		setRotation(rightarm1, 0.0f, 0.0f, 0.0f);
		(leftarm1 = new ModelRenderer(this, 49, 24)).addBox(-3.0f, -3.0f, -2.0f, 2, 3, 4);
		leftarm1.setRotationPoint(7.0f, -1.0f, -5.0f);
		leftarm1.setTextureSize(128, 32);
		leftarm1.mirror = true;
		setRotation(leftarm1, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg.setRotationPoint(-5.0f, 12.0f, 8.0f);
		rightleg.setTextureSize(128, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg1 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg1.setRotationPoint(5.0f, 12.0f, 8.0f);
		leftleg1.setTextureSize(128, 32);
		leftleg1.mirror = true;
		setRotation(leftleg1, 0.0f, 0.0f, 0.0f);
		(rightleg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg2.setRotationPoint(-5.0f, 12.0f, -3.0f);
		rightleg2.setTextureSize(128, 32);
		rightleg2.mirror = true;
		setRotation(rightleg2, 0.0f, 0.0f, 0.0f);
		(leftleg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg2.setRotationPoint(5.0f, 12.0f, -3.0f);
		leftleg2.setTextureSize(128, 32);
		leftleg2.mirror = true;
		setRotation(leftleg2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 19, 18)).addBox(-4.0f, 0.0f, -2.0f, 8, 8, 4);
		body2.setRotationPoint(0.0f, -4.0f, -5.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 52, 0)).addBox(-1.0f, -2.0f, -2.0f, 4, 14, 4);
		leftarm2.setRotationPoint(7.0f, -1.0f, -5.0f);
		leftarm2.setTextureSize(128, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 52, 0)).addBox(-3.0f, -2.0f, -2.0f, 4, 14, 4);
		rightarm2.setRotationPoint(-7.0f, -1.0f, -5.0f);
		rightarm2.setTextureSize(128, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body1.render(par7);
		rightarm1.render(par7);
		leftarm1.render(par7);
		rightleg.render(par7);
		leftleg1.render(par7);
		rightleg2.render(par7);
		leftleg2.render(par7);
		body2.render(par7);
		leftarm2.render(par7);
		rightarm2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
		leftleg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftleg1.rotateAngleY = 0.0f;
		leftleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftleg2.rotateAngleY = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		rightleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		rightarm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm1.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		leftarm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm1.rotateAngleZ = 0.0f;
		leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm2.rotateAngleZ = 0.0f;
	}
}

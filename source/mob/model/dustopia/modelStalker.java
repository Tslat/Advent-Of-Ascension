package net.nevermine.mob.model.dustopia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelStalker extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body2;
	ModelRenderer leftarm;
	ModelRenderer leftarm2;
	ModelRenderer leftarm3;
	ModelRenderer leftarm4;
	ModelRenderer leftarm5;
	ModelRenderer leftarm6;
	ModelRenderer body3;

	public modelStalker() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head.setRotationPoint(0.0f, -11.0f, -1.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 25, 12)).addBox(-1.0f, -2.0f, -1.0f, 2, 2, 2);
		body.setRotationPoint(0.0f, -9.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 21, 4);
		rightleg.setRotationPoint(-3.0f, 3.0f, 0.0f);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 21, 4);
		leftleg.setRotationPoint(3.0f, 3.0f, 0.0f);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 36)).addBox(-4.0f, 0.0f, -2.0f, 6, 7, 3);
		body2.setRotationPoint(1.0f, -8.0f, 4.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftarm.setRotationPoint(-4.0f, -1.0f, 4.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 1.047198f);
		(leftarm2 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftarm2.setRotationPoint(-12.0f, -4.0f, 4.0f);
		leftarm2.setTextureSize(64, 64);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, -1.570796f);
		(leftarm3 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftarm3.setRotationPoint(-4.0f, -7.0f, 4.0f);
		leftarm3.setTextureSize(64, 64);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0f, 0.0f, 2.094395f);
		(leftarm4 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftarm4.setRotationPoint(4.0f, -4.0f, 4.0f);
		leftarm4.setTextureSize(64, 64);
		leftarm4.mirror = true;
		setRotation(leftarm4, 0.0f, 0.0f, -1.570796f);
		(leftarm5 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftarm5.setRotationPoint(4.0f, -1.0f, 4.0f);
		leftarm5.setTextureSize(64, 64);
		leftarm5.mirror = true;
		setRotation(leftarm5, 0.0f, 0.0f, -1.047198f);
		(leftarm6 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftarm6.setRotationPoint(4.0f, -7.0f, 4.0f);
		leftarm6.setTextureSize(64, 64);
		leftarm6.mirror = true;
		setRotation(leftarm6, 0.0f, 0.0f, -2.094395f);
		(body3 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body3.setRotationPoint(0.0f, -9.0f, 0.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		body2.render(par7);
		leftarm.render(par7);
		leftarm2.render(par7);
		leftarm3.render(par7);
		leftarm4.render(par7);
		leftarm5.render(par7);
		leftarm6.render(par7);
		body3.render(par7);
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

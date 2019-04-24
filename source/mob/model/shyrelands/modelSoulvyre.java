package net.nevermine.mob.model.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelSoulvyre extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body2;
	ModelRenderer rightarm3;
	ModelRenderer leftarm3;
	ModelRenderer leftarm2;
	ModelRenderer rightarm2;

	public modelSoulvyre() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6);
		head.setRotationPoint(0.0F, -4.0F, 0.0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 32, 16);
		body.addBox(1.0F, 0.0F, -2.0F, 3, 15, 4);
		body.setRotationPoint(0.0F, -3.0F, 0.0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightarm = new ModelRenderer(this, 38, 5);
		rightarm.addBox(-3.0F, -5.0F, -3.0F, 5, 3, 6);
		rightarm.setRotationPoint(-6.0F, 2.0F, 0.0F);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0F, 0.0F, 0.0F);
		leftarm = new ModelRenderer(this, 38, 5);
		leftarm.addBox(-2.0F, -5.0F, -3.0F, 5, 3, 6);
		leftarm.setRotationPoint(6.0F, 2.0F, 0.0F);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0F, 0.0F, 0.0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		rightleg.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0F, 0.0F, 0.0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		leftleg.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 16, 16);
		body2.addBox(-4.0F, 0.0F, -2.0F, 3, 15, 4);
		body2.setRotationPoint(0.0F, -3.0F, 0.0F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0F, 0.0F, 0.0F);
		rightarm3 = new ModelRenderer(this, 48, 32);
		rightarm3.addBox(-3.0F, 9.0F, -0.5F, 2, 7, 1);
		rightarm3.setRotationPoint(-6.0F, 2.0F, 0.0F);
		rightarm3.setTextureSize(64, 32);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0F, 0.0F, 0.0F);
		leftarm3 = new ModelRenderer(this, 48, 32);
		leftarm3.addBox(1.0F, 9.0F, -0.5F, 2, 7, 1);
		leftarm3.setRotationPoint(6.0F, 2.0F, 0.0F);
		leftarm3.setTextureSize(64, 32);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0F, 0.0F, 0.0F);
		leftarm2 = new ModelRenderer(this, 48, 16);
		leftarm2.addBox(-1.0F, -2.0F, -2.0F, 4, 11, 4);
		leftarm2.setRotationPoint(6.0F, 2.0F, 0.0F);
		leftarm2.setTextureSize(64, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0F, 0.0F, 0.0F);
		rightarm2 = new ModelRenderer(this, 48, 16);
		rightarm2.addBox(-3.0F, -2.0F, -2.0F, 4, 11, 4);
		rightarm2.setRotationPoint(-6.0F, 2.0F, 0.0F);
		rightarm2.setTextureSize(64, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0F, 0.0F, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		body2.render(par7);
		rightarm3.render(par7);
		leftarm3.render(par7);
		leftarm2.render(par7);
		rightarm2.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		rightarm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm.rotateAngleZ = 0.0F;

		rightarm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm2.rotateAngleZ = 0.0F;

		rightarm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm3.rotateAngleZ = 0.0F;

		head.rotateAngleY = (par4 / 57.295776F);
		head.rotateAngleX = (par5 / 54.11268F);

		leftarm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm.rotateAngleZ = 0.0F;

		leftarm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm2.rotateAngleZ = 0.0F;

		leftarm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm3.rotateAngleZ = 0.0F;

		rightleg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightleg.rotateAngleY = 0.0F;

		leftleg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
	}
}

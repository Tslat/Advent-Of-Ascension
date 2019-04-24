package net.tslat.aoa3.client.model.entities.mobs.runandor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelPaladin extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer leftarm2;
	ModelRenderer leftleg2;
	ModelRenderer rightleg2;
	ModelRenderer rightarm2;
	ModelRenderer rightarm3;
	ModelRenderer rightarm4;
	ModelRenderer rightarm5;
	ModelRenderer rightarm6;
	ModelRenderer body2;
	ModelRenderer rightarm7;

	public ModelPaladin() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 0.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 25, 34)).addBox(-5.0f, 10.0f, -3.0f, 10, 2, 6);
		body.setRotationPoint(0.0f, 1.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 40, 3)).addBox(-4.0f, -5.0f, -3.0f, 6, 5, 6);
		rightarm.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 40, 3)).addBox(-2.0f, -5.0f, -3.0f, 6, 5, 6);
		leftarm.setRotationPoint(6.0f, 2.0f, 0.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 34)).addBox(-3.0f, 6.0f, -3.0f, 6, 6, 6);
		rightleg.setRotationPoint(-3.0f, 12.0f, 0.0f);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 34)).addBox(-3.0f, 6.0f, -3.0f, 6, 6, 6);
		leftleg.setRotationPoint(3.0f, 12.0f, 0.0f);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftarm2.setRotationPoint(6.0f, 2.0f, 0.0f);
		leftarm2.setTextureSize(64, 64);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(leftleg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 11, 4);
		leftleg2.setRotationPoint(3.0f, 13.0f, 0.0f);
		leftleg2.setTextureSize(64, 64);
		leftleg2.mirror = true;
		setRotation(leftleg2, 0.0f, 0.0f, 0.0f);
		(rightleg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 11, 4);
		rightleg2.setRotationPoint(-3.0f, 13.0f, 0.0f);
		rightleg2.setTextureSize(64, 64);
		rightleg2.mirror = true;
		setRotation(rightleg2, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightarm2.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightarm2.setTextureSize(64, 64);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 40, 44)).addBox(-2.0f, 7.0f, -5.0f, 2, 2, 8);
		rightarm3.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightarm3.setTextureSize(64, 64);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0f, 0.0f, 0.0f);
		(rightarm4 = new ModelRenderer(this, 31, 44)).addBox(-2.0f, 4.0f, -7.0f, 2, 8, 2);
		rightarm4.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightarm4.setTextureSize(64, 64);
		rightarm4.mirror = true;
		setRotation(rightarm4, 0.0f, 0.0f, 0.0f);
		(rightarm5 = new ModelRenderer(this, 35, 55)).addBox(-2.0f, 6.5f, -24.0f, 2, 3, 5);
		rightarm5.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightarm5.setTextureSize(64, 64);
		rightarm5.mirror = true;
		setRotation(rightarm5, 0.0f, 0.0f, 0.0f);
		(rightarm6 = new ModelRenderer(this, 2, 50)).addBox(-2.0f, 5.5f, -14.0f, 2, 5, 7);
		rightarm6.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightarm6.setTextureSize(64, 64);
		rightarm6.mirror = true;
		setRotation(rightarm6, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body2.setRotationPoint(0.0f, 0.0f, 0.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(rightarm7 = new ModelRenderer(this, 49, 55)).addBox(-2.0f, 6.0f, -19.0f, 2, 4, 5);
		rightarm7.setRotationPoint(-6.0f, 2.0f, 0.0f);
		rightarm7.setTextureSize(64, 64);
		rightarm7.mirror = true;
		setRotation(rightarm7, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		leftarm2.render(par7);
		leftleg2.render(par7);
		rightleg2.render(par7);
		rightarm2.render(par7);
		rightarm3.render(par7);
		rightarm4.render(par7);
		rightarm5.render(par7);
		rightarm6.render(par7);
		body2.render(par7);
		rightarm7.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		rightarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm3.rotateAngleZ = 0.0f;
		rightarm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm4.rotateAngleZ = 0.0f;
		rightarm5.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm5.rotateAngleZ = 0.0f;
		rightarm6.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm6.rotateAngleZ = 0.0f;
		rightarm7.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm7.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm2.rotateAngleZ = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		rightleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg2.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

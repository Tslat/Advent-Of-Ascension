package net.tslat.aoa3.client.model.entities.mobs.immortallis;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGoldCreature extends ModelBase {
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer rightarm2;
	ModelRenderer leftarm2;
	ModelRenderer leftarm3;
	ModelRenderer rightarm3;
	ModelRenderer rightleg2;
	ModelRenderer leftleg2;

	public ModelGoldCreature() {
		textureWidth = 64;
		textureHeight = 64;
		(body = new ModelRenderer(this, 0, 31)).addBox(-4.0f, 0.0f, -3.0f, 10, 12, 6);
		body.setRotationPoint(-1.0f, 0.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 40, 1)).addBox(-4.0f, 11.0f, -3.0f, 6, 6, 6);
		rightarm.setRotationPoint(-7.0f, 2.0f, 0.0f);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 40, 1)).addBox(-2.0f, 11.0f, -3.0f, 6, 6, 6);
		leftarm.setRotationPoint(7.0f, 2.0f, 0.0f);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 8.0f, -3.0f, 6, 4, 6);
		rightleg.setRotationPoint(-3.0f, 12.0f, 0.0f);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 8.0f, -3.0f, 6, 4, 6);
		leftleg.setRotationPoint(3.0f, 12.0f, 0.0f);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 40, 16)).addBox(-3.0f, 1.0f, -2.0f, 4, 10, 4);
		rightarm2.setRotationPoint(-7.0f, 2.0f, 0.0f);
		rightarm2.setTextureSize(64, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 40, 16)).addBox(-1.0f, 1.0f, -2.0f, 4, 10, 4);
		leftarm2.setRotationPoint(7.0f, 2.0f, 0.0f);
		leftarm2.setTextureSize(64, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(leftarm3 = new ModelRenderer(this, 40, 1)).addBox(-2.0f, -5.0f, -3.0f, 6, 6, 6);
		leftarm3.setRotationPoint(7.0f, 2.0f, 0.0f);
		leftarm3.setTextureSize(64, 32);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 40, 1)).addBox(-4.0f, -5.0f, -3.0f, 6, 6, 6);
		rightarm3.setRotationPoint(-7.0f, 2.0f, 0.0f);
		rightarm3.setTextureSize(64, 32);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0f, 0.0f, 0.0f);
		(rightleg2 = new ModelRenderer(this, 0, 2)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		rightleg2.setRotationPoint(-3.0f, 12.0f, 0.0f);
		rightleg2.setTextureSize(64, 32);
		rightleg2.mirror = true;
		setRotation(rightleg2, 0.0f, 0.0f, 0.0f);
		(leftleg2 = new ModelRenderer(this, 0, 2)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leftleg2.setRotationPoint(3.0f, 12.0f, 0.0f);
		leftleg2.setTextureSize(64, 32);
		leftleg2.mirror = true;
		setRotation(leftleg2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		rightarm2.render(par7);
		leftarm2.render(par7);
		leftarm3.render(par7);
		rightarm3.render(par7);
		rightleg2.render(par7);
		leftleg2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
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

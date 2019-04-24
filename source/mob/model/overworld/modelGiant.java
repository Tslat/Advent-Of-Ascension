package net.nevermine.mob.model.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class modelGiant extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer leftleg2;
	ModelRenderer rightleg2;
	ModelRenderer leftleg3;
	ModelRenderer rightleg3;
	ModelRenderer rightarm2;
	ModelRenderer leftarm;
	ModelRenderer leftarm2;

	public modelGiant() {
		textureWidth = 128;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -14.0f, -4.0f, 10, 14, 8);
		head.setRotationPoint(0.0f, -20.0f, 0.0f);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 0, 35)).addBox(-6.0f, 0.0f, -2.0f, 12, 18, 10);
		body.setRotationPoint(0.0f, -20.0f, -3.0f);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 36, 2)).addBox(-18.0f, -6.0f, -6.0f, 19, 10, 10);
		rightarm.setRotationPoint(-6.0f, -17.0f, 0.0f);
		rightarm.setTextureSize(128, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 96, 0)).addBox(-4.0f, -5.0f, -3.0f, 8, 8, 8);
		rightleg.setRotationPoint(-7.0f, -2.0f, -1.0f);
		rightleg.setTextureSize(128, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 96, 0)).addBox(-4.0f, -5.0f, -3.0f, 8, 8, 8);
		leftleg.setRotationPoint(7.0f, -2.0f, -1.0f);
		leftleg.setTextureSize(128, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(leftleg2 = new ModelRenderer(this, 80, 39)).addBox(-6.0f, 14.0f, -5.0f, 12, 12, 12);
		leftleg2.setRotationPoint(7.0f, -2.0f, -1.0f);
		leftleg2.setTextureSize(128, 64);
		leftleg2.mirror = true;
		setRotation(leftleg2, 0.0f, 0.0f, 0.0f);
		(rightleg2 = new ModelRenderer(this, 80, 39)).addBox(-6.0f, 14.0f, -5.0f, 12, 12, 12);
		rightleg2.setRotationPoint(-7.0f, -2.0f, -1.0f);
		rightleg2.setTextureSize(128, 64);
		rightleg2.mirror = true;
		setRotation(rightleg2, 0.0f, 0.0f, 0.0f);
		(leftleg3 = new ModelRenderer(this, 87, 17)).addBox(-5.0f, 3.0f, -4.0f, 10, 11, 10);
		leftleg3.setRotationPoint(7.0f, -2.0f, -1.0f);
		leftleg3.setTextureSize(128, 64);
		leftleg3.mirror = true;
		setRotation(leftleg3, 0.0f, 0.0f, 0.0f);
		(rightleg3 = new ModelRenderer(this, 87, 17)).addBox(-5.0f, 3.0f, -4.0f, 10, 11, 10);
		rightleg3.setRotationPoint(-7.0f, -2.0f, -1.0f);
		rightleg3.setTextureSize(128, 64);
		rightleg3.mirror = true;
		setRotation(rightleg3, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 46, 26)).addBox(-17.0f, 4.0f, -5.0f, 8, 30, 8);
		rightarm2.setRotationPoint(-6.0f, -17.0f, 0.0f);
		rightarm2.setTextureSize(128, 64);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 36, 2)).addBox(-1.0f, -6.0f, -6.0f, 19, 10, 10);
		leftarm.setRotationPoint(6.0f, -17.0f, 0.0f);
		leftarm.setTextureSize(128, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 46, 26)).addBox(8.0f, 4.0f, -5.0f, 8, 30, 8);
		leftarm2.setRotationPoint(6.0f, -17.0f, 0.0f);
		leftarm2.setTextureSize(128, 64);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		leftleg2.render(par7);
		rightleg2.render(par7);
		leftleg3.render(par7);
		rightleg3.render(par7);
		rightarm2.render(par7);
		leftarm.render(par7);
		leftarm2.render(par7);
		GL11.glPopMatrix();
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
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm2.rotateAngleZ = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		rightleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg2.rotateAngleY = 0.0f;
		rightleg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg3.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftleg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

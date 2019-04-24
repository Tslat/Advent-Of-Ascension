package net.tslat.aoa3.client.model.entities.mobs.immortallis;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelReaver extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightleg2;
	ModelRenderer leftleg2;
	ModelRenderer leftleg3;
	ModelRenderer rightleg3;
	ModelRenderer leftleg4;
	ModelRenderer rightleg4;

	public ModelReaver() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -8.0f, -4.0f, 6, 13, 8);
		head.setRotationPoint(0.0f, -8.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 39, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, -8.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightleg2 = new ModelRenderer(this, 25, 34)).addBox(-2.0f, 0.0f, -2.0f, 4, 16, 4);
		rightleg2.setRotationPoint(-6.0f, -8.0f, 0.0f);
		rightleg2.setTextureSize(64, 32);
		rightleg2.mirror = true;
		setRotation(rightleg2, 0.0f, 0.0f, 0.0f);
		(leftleg2 = new ModelRenderer(this, 25, 34)).addBox(-2.0f, 0.0f, -2.0f, 4, 16, 4);
		leftleg2.setRotationPoint(6.0f, -8.0f, 0.0f);
		leftleg2.setTextureSize(64, 32);
		leftleg2.mirror = true;
		setRotation(leftleg2, 0.0f, 0.0f, 0.0f);
		(leftleg3 = new ModelRenderer(this, 0, 48)).addBox(-4.0f, 24.0f, -4.0f, 8, 8, 8);
		leftleg3.setRotationPoint(6.0f, -8.0f, 0.0f);
		leftleg3.setTextureSize(64, 32);
		leftleg3.mirror = true;
		setRotation(leftleg3, 0.0f, 0.0f, 0.0f);
		(rightleg3 = new ModelRenderer(this, 0, 48)).addBox(-4.0f, 24.0f, -4.0f, 8, 8, 8);
		rightleg3.setRotationPoint(-6.0f, -8.0f, 0.0f);
		rightleg3.setTextureSize(64, 32);
		rightleg3.mirror = true;
		setRotation(rightleg3, 0.0f, 0.0f, 0.0f);
		(leftleg4 = new ModelRenderer(this, 40, 50)).addBox(-3.0f, 16.0f, -3.0f, 6, 8, 6);
		leftleg4.setRotationPoint(6.0f, -8.0f, 0.0f);
		leftleg4.setTextureSize(64, 32);
		leftleg4.mirror = true;
		setRotation(leftleg4, 0.0f, 0.0f, 0.0f);
		(rightleg4 = new ModelRenderer(this, 40, 50)).addBox(-3.0f, 16.0f, -3.0f, 6, 8, 6);
		rightleg4.setRotationPoint(-6.0f, -8.0f, 0.0f);
		rightleg4.setTextureSize(64, 32);
		rightleg4.mirror = true;
		setRotation(rightleg4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		head.render(par7);
		body.render(par7);
		rightleg2.render(par7);
		leftleg2.render(par7);
		leftleg3.render(par7);
		rightleg3.render(par7);
		leftleg4.render(par7);
		rightleg4.render(par7);
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
		rightleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg2.rotateAngleY = 0.0f;
		rightleg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg3.rotateAngleY = 0.0f;
		rightleg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg4.rotateAngleY = 0.0f;
		leftleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftleg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftleg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

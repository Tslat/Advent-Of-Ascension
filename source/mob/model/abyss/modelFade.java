package net.nevermine.mob.model.abyss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class modelFade extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer leftarm2;
	ModelRenderer rightarm2;
	ModelRenderer leftarm3;
	ModelRenderer rightarm3;
	ModelRenderer body6;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;

	public modelFade() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 0.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 33)).addBox(-4.0f, 21.0f, -2.0f, 8, 2, 4);
		body.setRotationPoint(0.0f, 0.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 40, 10)).addBox(0.0f, 10.0f, -1.0f, 1, 3, 2);
		rightarm.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, -0.7853982f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 40, 10)).addBox(2.0f, 10.0f, -1.0f, 1, 3, 2);
		leftarm.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, -0.7853982f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftarm2.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm2.setTextureSize(64, 64);
		leftarm2.mirror = true;
		setRotation(leftarm2, -0.7853982f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightarm2.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm2.setTextureSize(64, 64);
		rightarm2.mirror = true;
		setRotation(rightarm2, -0.7853982f, 0.0f, 0.0f);
		(leftarm3 = new ModelRenderer(this, 40, 10)).addBox(-1.0f, 10.0f, -1.0f, 1, 3, 2);
		leftarm3.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm3.setTextureSize(64, 64);
		leftarm3.mirror = true;
		setRotation(leftarm3, -0.7853982f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 40, 10)).addBox(-3.0f, 10.0f, -1.0f, 1, 3, 2);
		rightarm3.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm3.setTextureSize(64, 64);
		rightarm3.mirror = true;
		setRotation(rightarm3, -0.7853982f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 0, 32)).addBox(-3.0f, -1.0f, 2.0f, 6, 1, 1);
		body6.setRotationPoint(0.0f, 3.0f, 0.0f);
		body6.setTextureSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0.2617994f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 33)).addBox(-4.0f, 13.0f, -2.0f, 8, 2, 4);
		body2.setRotationPoint(0.0f, 0.0f, 0.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 16, 33)).addBox(-4.0f, 17.0f, -2.0f, 8, 2, 4);
		body3.setRotationPoint(0.0f, 0.0f, 0.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body4.setRotationPoint(0.0f, 0.0f, 0.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 0, 18)).addBox(-4.0f, 0.0f, 2.5f, 8, 12, 0);
		body5.setRotationPoint(0.0f, 3.0f, 0.0f);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 0.2617994f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		leftarm2.render(par7);
		rightarm2.render(par7);
		leftarm3.render(par7);
		rightarm3.render(par7);
		body6.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
	}
}

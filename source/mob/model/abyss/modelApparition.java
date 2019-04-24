package net.nevermine.mob.model.abyss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class modelApparition extends ModelBase {
	ModelRenderer Tail;
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;

	public modelApparition() {
		textureWidth = 64;
		textureHeight = 32;
		(Tail = new ModelRenderer(this, 36, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		Tail.setRotationPoint(0.0f, 17.0f, 4.0f);
		Tail.setTextureSize(64, 32);
		Tail.mirror = true;
		setRotation(Tail, 1.07818f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 7.0f, -1.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, 7.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.4089647f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightarm.setRotationPoint(-5.0f, 9.0f, 0.0f);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, -0.8179294f, 0.0f, 0.4833219f);
		(leftarm = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftarm.setRotationPoint(5.0f, 9.0f, 0.0f);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, -0.8179294f, 0.0f, -0.4833219f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Tail.render(par7);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		//rightarm.rotateAngleX = MathHelper.cos(par1 * 1.1662f + 3.1415927f) * 2.0f * par2 * 1.0f;
		//rightarm.rotateAngleZ = 0.0f;
		//leftarm.rotateAngleX = MathHelper.cos(par1 * 1.1662f) * 2.0f * par2 * 1.0f;
		//leftarm.rotateAngleZ = 0.0f;
	}
}

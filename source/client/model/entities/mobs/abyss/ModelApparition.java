package net.tslat.aoa3.client.model.entities.mobs.abyss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelApparition extends ModelBase {
	private ModelRenderer tail;
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;

	public ModelApparition() {
		textureWidth = 64;
		textureHeight = 32;
		(tail = new ModelRenderer(this, 36, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		tail.setRotationPoint(0.0f, 17.0f, 4.0f);
		tail.setTextureSize(64, 32);
		tail.mirror = true;
		setRotation(tail, 1.07818f, 0.0f, 0.0f);
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
		(rightArm = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setRotationPoint(-5.0f, 9.0f, 0.0f);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, -0.8179294f, 0.0f, 0.4833219f);
		(leftArm = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setRotationPoint(5.0f, 9.0f, 0.0f);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, -0.8179294f, 0.0f, -0.4833219f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		tail.render(par7);
		head.render(par7);
		body.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		//rightArm.rotateAngleX = MathHelper.cos(par1 * 1.1662f + 3.1415927f) * 2.0f * par2 * 1.0f;
		//rightArm.rotateAngleZ = 0.0f;
		//leftArm.rotateAngleX = MathHelper.cos(par1 * 1.1662f) * 2.0f * par2 * 1.0f;
		//leftArm.rotateAngleZ = 0.0f;
	}
}

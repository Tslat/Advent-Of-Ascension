package net.tslat.aoa3.client.model.entities.npcs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTrollTrader extends ModelBase {
	private ModelRenderer body;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer head;
	private ModelRenderer nose;
	private ModelRenderer leftArm;
	private ModelRenderer rightArm;
	private ModelRenderer leftEar1;
	private ModelRenderer leftEar2;

	public ModelTrollTrader() {
		textureWidth = 64;
		textureHeight = 32;
		(body = new ModelRenderer(this, 36, 15)).addBox(-4.0f, 0.0f, -3.0f, 8, 11, 6);
		body.setRotationPoint(0.0f, 3.0f, 1.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 18, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		rightLeg.setRotationPoint(-2.0f, 14.0f, 1.0f);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 18, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		leftLeg.setRotationPoint(2.0f, 14.0f, 1.0f);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 3.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(nose = new ModelRenderer(this, 33, 8)).addBox(-1.0f, -4.0f, -6.0f, 2, 5, 2);
		nose.setRotationPoint(0.0f, 3.0f, 0.0f);
		nose.setTextureSize(64, 32);
		nose.mirror = true;
		setRotation(nose, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 0, 18)).addBox(0.0f, -1.0f, -2.0f, 4, 10, 4);
		leftArm.setRotationPoint(4.0f, 4.0f, 1.0f);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 0, 18)).addBox(-4.0f, -1.0f, -2.0f, 4, 10, 4);
		rightArm.setRotationPoint(-4.0f, 4.0f, 1.0f);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftEar1 = new ModelRenderer(this, 45, 2)).addBox(-1.0f, -11.0f, -3.0f, 2, 1, 6);
		leftEar1.setRotationPoint(0.0f, 3.0f, 0.0f);
		leftEar1.setTextureSize(64, 32);
		leftEar1.mirror = true;
		setRotation(leftEar1, 0.0f, 0.0f, 0.0f);
		(leftEar2 = new ModelRenderer(this, 43, 0)).addBox(-1.0f, -10.0f, -4.0f, 2, 2, 8);
		leftEar2.setRotationPoint(0.0f, 3.0f, 0.0f);
		leftEar2.setTextureSize(64, 32);
		leftEar2.mirror = true;
		setRotation(leftEar2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		head.render(par7);
		nose.render(par7);
		leftArm.render(par7);
		rightArm.render(par7);
		leftEar1.render(par7);
		leftEar2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		nose.rotateAngleY = par4 / 57.295776f;
		nose.rotateAngleX = par5 / 54.11268f;
		leftEar1.rotateAngleY = par4 / 57.295776f;
		leftEar1.rotateAngleX = par5 / 54.11268f;
		leftEar2.rotateAngleY = par4 / 57.295776f;
		leftEar2.rotateAngleX = par5 / 54.11268f;
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

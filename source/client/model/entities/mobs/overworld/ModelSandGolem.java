package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSandGolem extends ModelBase {
	private ModelRenderer rightFoot;
	private ModelRenderer leftFoot;
	private ModelRenderer Hip;
	private ModelRenderer hipTorsoJoint;
	private ModelRenderer lowerTorso;
	private ModelRenderer Torso;
	private ModelRenderer head;
	private ModelRenderer nose;
	private ModelRenderer Eyebrow;
	private ModelRenderer leftArm;
	private ModelRenderer rightArm;

	public ModelSandGolem() {
		textureWidth = 128;
		textureHeight = 32;
		(rightFoot = new ModelRenderer(this, 61, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		rightFoot.setRotationPoint(-3.0f, 15.0f, 0.0f);
		rightFoot.setTextureSize(64, 32);
		rightFoot.mirror = true;
		setRotation(rightFoot, 0.0f, 0.0f, 0.0f);
		(leftFoot = new ModelRenderer(this, 61, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		leftFoot.setRotationPoint(3.0f, 15.0f, 0.0f);
		leftFoot.setTextureSize(64, 32);
		leftFoot.mirror = true;
		setRotation(leftFoot, 0.0f, 0.0f, 0.0f);
		(Hip = new ModelRenderer(this, 60, 13)).addBox(-3.0f, 0.0f, -3.0f, 6, 2, 6);
		Hip.setRotationPoint(0.0f, 13.0f, 0.0f);
		Hip.setTextureSize(64, 32);
		Hip.mirror = true;
		setRotation(Hip, 0.0f, 0.0f, 0.0f);
		(hipTorsoJoint = new ModelRenderer(this, 25, 0)).addBox(-2.0f, -3.0f, -2.0f, 4, 3, 4);
		hipTorsoJoint.setRotationPoint(0.0f, 13.0f, 0.0f);
		hipTorsoJoint.setTextureSize(64, 32);
		hipTorsoJoint.mirror = true;
		setRotation(hipTorsoJoint, 0.0f, 0.0f, 0.0f);
		(lowerTorso = new ModelRenderer(this, 43, 23)).addBox(-3.0f, -1.0f, -3.0f, 6, 3, 6);
		lowerTorso.setRotationPoint(0.0f, 9.0f, 0.0f);
		lowerTorso.setTextureSize(64, 32);
		lowerTorso.mirror = true;
		setRotation(lowerTorso, 0.0f, 0.0f, 0.0f);
		(Torso = new ModelRenderer(this, 0, 16)).addBox(-6.0f, -7.0f, -4.5f, 12, 7, 9);
		Torso.setRotationPoint(0.0f, 8.0f, 0.0f);
		Torso.setTextureSize(64, 32);
		Torso.mirror = true;
		setRotation(Torso, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head.setRotationPoint(0.0f, 1.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(nose = new ModelRenderer(this, 25, 8)).addBox(-1.0f, -4.0f, -4.0f, 2, 4, 1);
		nose.setRotationPoint(0.0f, 1.0f, 0.0f);
		nose.setTextureSize(64, 32);
		nose.mirror = true;
		setRotation(nose, 0.0f, 0.0f, 0.0f);
		(Eyebrow = new ModelRenderer(this, 27, 13)).addBox(-3.0f, -6.0f, -4.0f, 6, 2, 1);
		Eyebrow.setRotationPoint(0.0f, 1.0f, 0.0f);
		Eyebrow.setTextureSize(64, 32);
		Eyebrow.mirror = true;
		setRotation(Eyebrow, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 43, 0)).addBox(0.0f, -2.0f, -2.0f, 4, 17, 4);
		leftArm.setRotationPoint(6.0f, 1.0f, 0.0f);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 43, 0)).addBox(-4.0f, -2.0f, -2.0f, 4, 17, 4);
		rightArm.setRotationPoint(-6.0f, 1.0f, 0.0f);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		rightFoot.render(par7);
		leftFoot.render(par7);
		Hip.render(par7);
		hipTorsoJoint.render(par7);
		lowerTorso.render(par7);
		Torso.render(par7);
		head.render(par7);
		nose.render(par7);
		Eyebrow.render(par7);
		leftArm.render(par7);
		rightArm.render(par7);
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
		Eyebrow.rotateAngleY = par4 / 57.295776f;
		Eyebrow.rotateAngleX = par5 / 54.11268f;
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightFoot.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightFoot.rotateAngleY = 0.0f;
		leftFoot.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

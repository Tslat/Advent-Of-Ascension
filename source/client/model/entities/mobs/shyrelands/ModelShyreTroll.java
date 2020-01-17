package net.tslat.aoa3.client.model.entities.mobs.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelShyreTroll extends ModelBase {
	private ModelRenderer body;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer head;
	private ModelRenderer nose;
	private ModelRenderer leftEar;
	private ModelRenderer rightEar;
	private ModelRenderer leftArm;
	private ModelRenderer Staff;
	private ModelRenderer rightArm;
	private ModelRenderer leftEar2;
	private ModelRenderer rightEar2;

	public ModelShyreTroll() {
		textureWidth = 64;
		textureHeight = 32;

		body = new ModelRenderer(this, 36, 15);
		body.addBox(-4.0F, 0.0F, -3.0F, 8, 11, 6);
		body.setRotationPoint(0.0F, 3.0F, 1.0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightLeg = new ModelRenderer(this, 18, 18);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4);
		rightLeg.setRotationPoint(-2.0F, 14.0F, 1.0F);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new ModelRenderer(this, 18, 18);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4);
		leftLeg.setRotationPoint(2.0F, 14.0F, 1.0F);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
		head.setRotationPoint(0.0F, 3.0F, 0.0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		nose = new ModelRenderer(this, 24, 0);
		nose.addBox(-1.0F, -7.0F, -5.0F, 2, 5, 2, 0.0F);
		nose.setRotationPoint(0.0F, 3.0F, -1.0F);
		nose.setTextureSize(64, 32);
		nose.mirror = true;
		setRotation(nose, 0.0F, 0.0F, 0.0F);
		leftEar = new ModelRenderer(this, 43, 8);
		leftEar.addBox(-9.0F, -16.0F, -3.0F, 4, 1, 5, 0.0F);
		leftEar.setRotationPoint(1.0F, 3.0F, 0.0F);
		leftEar.setTextureSize(64, 32);
		leftEar.mirror = true;
		rightEar = new ModelRenderer(this, 32, 8);
		rightEar.addBox(5.0F, -16.0F, -3.0F, 4, 1, 5, 0.0F);
		rightEar.setRotationPoint(-1.0F, 3.0F, 0.0F);
		rightEar.setTextureSize(64, 32);
		rightEar.mirror = true;
		leftArm = new ModelRenderer(this, 0, 18);
		leftArm.addBox(0.0F, -1.0F, -2.0F, 4, 10, 4);
		leftArm.setRotationPoint(4.0F, 4.0F, 1.0F);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0F, 0.0F, 0.0F);
		Staff = new ModelRenderer(this, 60, 0);
		Staff.addBox(-2.0F, -2.0F, -5.0F, 1, 12, 1);
		Staff.setRotationPoint(-4.5F, 4.0F, 1.0F);
		Staff.setTextureSize(64, 32);
		Staff.mirror = true;
		setRotation(Staff, 0.3490659F, 0.0F, 0.0F);
		rightArm = new ModelRenderer(this, 0, 18);
		rightArm.addBox(-4.0F, -1.0F, -2.0F, 4, 10, 4);
		rightArm.setRotationPoint(-4.0F, 4.0F, 1.0F);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0F, 0.0F, 0.0F);
		leftEar2 = new ModelRenderer(this, 44, 0);
		leftEar2.addBox(-8.0F, -13.0F, 2.0F, 4, 6, 1, 0.0F);
		leftEar2.setRotationPoint(1.0F, 3.0F, 0.0F);
		leftEar2.setTextureSize(64, 32);
		leftEar2.mirror = true;
		setRotation(leftEar2, 0.0F, 0.0F, 0.8922867F);
		rightEar2 = new ModelRenderer(this, 33, 0);
		rightEar2.addBox(4.0F, -13.0F, 2.0F, 4, 6, 1, 0.0F);
		rightEar2.setRotationPoint(-1.0F, 3.0F, 0.0F);
		rightEar2.setTextureSize(64, 32);
		rightEar2.mirror = true;
		setRotation(rightEar2, 0.0F, 0.0F, -0.8922821F);
		leftEar2.addChild(this.leftEar);
		head.addChild(this.nose);
		head.addChild(this.leftEar2);
		rightEar2.addChild(this.rightEar);
		head.addChild(this.rightEar2);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		head.render(par7);
		leftArm.render(par7);
		Staff.render(par7);
		rightArm.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);

		rightArm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm.rotateAngleZ = 0.0F;

		Staff.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F + 0.43633F);
		Staff.rotateAngleZ = 0.0F;

		leftArm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftArm.rotateAngleZ = 0.0F;

		rightLeg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightLeg.rotateAngleY = 0.0F;

		leftLeg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
	}
}

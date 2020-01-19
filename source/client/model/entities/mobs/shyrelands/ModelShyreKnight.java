package net.tslat.aoa3.client.model.entities.mobs.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelShyreKnight extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer leftArm2;
	private ModelRenderer leftLeg2;
	private ModelRenderer rightLeg2;
	private ModelRenderer body2;
	private ModelRenderer rightArm2;
	private ModelRenderer leftArm3;
	private ModelRenderer rightArm3;
	private ModelRenderer rightArm4;
	private ModelRenderer leftArm4;

	public ModelShyreKnight() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 25, 34);
		body.addBox(-5.0F, 10.0F, -3.0F, 10, 2, 6);
		body.setRotationPoint(0.0F, 1.0F, 0.0F);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightArm = new ModelRenderer(this, 40, 3);
		rightArm.addBox(-4.0F, -5.0F, -3.0F, 6, 5, 6);
		rightArm.setRotationPoint(-6.0F, 2.0F, 0.0F);
		rightArm.setTextureSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0F, 0.0F, 0.0F);
		leftArm = new ModelRenderer(this, 40, 3);
		leftArm.addBox(-2.0F, -5.0F, -3.0F, 6, 5, 6);
		leftArm.setRotationPoint(6.0F, 2.0F, 0.0F);
		leftArm.setTextureSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0F, 0.0F, 0.0F);
		rightLeg = new ModelRenderer(this, 0, 34);
		rightLeg.addBox(-3.0F, 6.0F, -3.0F, 6, 6, 6);
		rightLeg.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightLeg.setTextureSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new ModelRenderer(this, 0, 34);
		leftLeg.addBox(-3.0F, 6.0F, -3.0F, 6, 6, 6);
		leftLeg.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftLeg.setTextureSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		leftArm2 = new ModelRenderer(this, 10, 47);
		leftArm2.addBox(5.0F, 2.0F, 0.0F, 3, 12, 0);
		leftArm2.setRotationPoint(6.0F, 2.0F, 0.0F);
		leftArm2.setTextureSize(64, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0F, 0.0F, 0.0F);
		leftLeg2 = new ModelRenderer(this, 0, 16);
		leftLeg2.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4);
		leftLeg2.setRotationPoint(3.0F, 13.0F, 0.0F);
		leftLeg2.setTextureSize(64, 64);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0F, 0.0F, 0.0F);
		rightLeg2 = new ModelRenderer(this, 0, 16);
		rightLeg2.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4);
		rightLeg2.setRotationPoint(-3.0F, 13.0F, 0.0F);
		rightLeg2.setTextureSize(64, 64);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 16, 16);
		body2.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
		body2.setRotationPoint(0.0F, 0.0F, 0.0F);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0F, 0.0F, 0.0F);
		rightArm2 = new ModelRenderer(this, 40, 16);
		rightArm2.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
		rightArm2.setRotationPoint(-6.0F, 2.0F, 0.0F);
		rightArm2.setTextureSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0F, 0.0F, 0.0F);
		leftArm3 = new ModelRenderer(this, 40, 16);
		leftArm3.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
		leftArm3.setRotationPoint(6.0F, 2.0F, 0.0F);
		leftArm3.setTextureSize(64, 64);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0F, 0.0F, 0.0F);
		rightArm3 = new ModelRenderer(this, 28, 46);
		rightArm3.addBox(-5.0F, 2.0F, -2.0F, 2, 12, 4);
		rightArm3.setRotationPoint(-6.0F, 2.0F, 0.0F);
		rightArm3.setTextureSize(64, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0F, 0.0F, 0.0F);
		rightArm4 = new ModelRenderer(this, 2, 47);
		rightArm4.addBox(-8.0F, 2.0F, 0.0F, 3, 12, 0);
		rightArm4.setRotationPoint(-6.0F, 2.0F, 0.0F);
		rightArm4.setTextureSize(64, 64);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.0F, 0.0F, 0.0F);
		leftArm4 = new ModelRenderer(this, 41, 46);
		leftArm4.addBox(3.0F, 2.0F, -2.0F, 2, 12, 4);
		leftArm4.setRotationPoint(6.0F, 2.0F, 0.0F);
		leftArm4.setTextureSize(64, 64);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0F, 0.0F, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		leftArm2.render(par7);
		leftLeg2.render(par7);
		rightLeg2.render(par7);
		body2.render(par7);
		rightArm2.render(par7);
		leftArm3.render(par7);
		rightArm3.render(par7);
		rightArm4.render(par7);
		leftArm4.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		head.rotateAngleY = (par4 / 57.295776F);
		head.rotateAngleX = (par5 / 54.11268F);

		rightArm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm.rotateAngleZ = 0.0F;

		rightArm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm2.rotateAngleZ = 0.0F;

		rightArm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm3.rotateAngleZ = 0.0F;

		rightArm4.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm4.rotateAngleZ = 0.0F;

		leftArm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftArm.rotateAngleZ = 0.0F;

		leftArm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftArm2.rotateAngleZ = 0.0F;

		leftArm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftArm3.rotateAngleZ = 0.0F;

		leftArm4.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftArm4.rotateAngleZ = 0.0F;

		rightLeg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightLeg.rotateAngleY = 0.0F;

		rightLeg2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightLeg2.rotateAngleY = 0.0F;

		leftLeg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);

		leftLeg2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
	}
}

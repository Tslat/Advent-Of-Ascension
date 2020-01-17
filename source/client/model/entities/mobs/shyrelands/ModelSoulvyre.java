package net.tslat.aoa3.client.model.entities.mobs.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSoulvyre extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer body2;
	private ModelRenderer rightArm3;
	private ModelRenderer leftArm3;
	private ModelRenderer leftArm2;
	private ModelRenderer rightArm2;

	public ModelSoulvyre() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6);
		head.setRotationPoint(0.0F, -4.0F, 0.0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 32, 16);
		body.addBox(1.0F, 0.0F, -2.0F, 3, 15, 4);
		body.setRotationPoint(0.0F, -3.0F, 0.0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightArm = new ModelRenderer(this, 38, 5);
		rightArm.addBox(-3.0F, -5.0F, -3.0F, 5, 3, 6);
		rightArm.setRotationPoint(-6.0F, 2.0F, 0.0F);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0F, 0.0F, 0.0F);
		leftArm = new ModelRenderer(this, 38, 5);
		leftArm.addBox(-2.0F, -5.0F, -3.0F, 5, 3, 6);
		leftArm.setRotationPoint(6.0F, 2.0F, 0.0F);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0F, 0.0F, 0.0F);
		rightLeg = new ModelRenderer(this, 0, 16);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		rightLeg.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new ModelRenderer(this, 0, 16);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		leftLeg.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 16, 16);
		body2.addBox(-4.0F, 0.0F, -2.0F, 3, 15, 4);
		body2.setRotationPoint(0.0F, -3.0F, 0.0F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0F, 0.0F, 0.0F);
		rightArm3 = new ModelRenderer(this, 48, 32);
		rightArm3.addBox(-3.0F, 9.0F, -0.5F, 2, 7, 1);
		rightArm3.setRotationPoint(-6.0F, 2.0F, 0.0F);
		rightArm3.setTextureSize(64, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0F, 0.0F, 0.0F);
		leftArm3 = new ModelRenderer(this, 48, 32);
		leftArm3.addBox(1.0F, 9.0F, -0.5F, 2, 7, 1);
		leftArm3.setRotationPoint(6.0F, 2.0F, 0.0F);
		leftArm3.setTextureSize(64, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0F, 0.0F, 0.0F);
		leftArm2 = new ModelRenderer(this, 48, 16);
		leftArm2.addBox(-1.0F, -2.0F, -2.0F, 4, 11, 4);
		leftArm2.setRotationPoint(6.0F, 2.0F, 0.0F);
		leftArm2.setTextureSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0F, 0.0F, 0.0F);
		rightArm2 = new ModelRenderer(this, 48, 16);
		rightArm2.addBox(-3.0F, -2.0F, -2.0F, 4, 11, 4);
		rightArm2.setRotationPoint(-6.0F, 2.0F, 0.0F);
		rightArm2.setTextureSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0F, 0.0F, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		body2.render(par7);
		rightArm3.render(par7);
		leftArm3.render(par7);
		leftArm2.render(par7);
		rightArm2.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		rightArm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm.rotateAngleZ = 0.0F;

		rightArm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm2.rotateAngleZ = 0.0F;

		rightArm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm3.rotateAngleZ = 0.0F;

		head.rotateAngleY = (par4 / 57.295776F);
		head.rotateAngleX = (par5 / 54.11268F);

		leftArm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftArm.rotateAngleZ = 0.0F;

		leftArm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftArm2.rotateAngleZ = 0.0F;

		leftArm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftArm3.rotateAngleZ = 0.0F;

		rightLeg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightLeg.rotateAngleY = 0.0F;

		leftLeg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
	}
}

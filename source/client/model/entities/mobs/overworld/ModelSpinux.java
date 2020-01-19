package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSpinux extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer head2;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer body4;
	private ModelRenderer head3;
	private ModelRenderer body6;
	private ModelRenderer leftArm2;
	private ModelRenderer rightArm2;

	public ModelSpinux() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -8.0f, -4.0f, 6, 8, 8);
		head.setRotationPoint(0.0f, 0.0f, -2.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 16, 33)).addBox(-1.0f, -13.0f, -3.0f, 2, 11, 4);
		head2.setRotationPoint(0.0f, 0.0f, -2.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 11)).addBox(0.0f, -2.0f, -2.0f, 1, 2, 2);
		rightArm.setRotationPoint(-3.0f, 2.0f, 0.0f);
		rightArm.setTextureSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 11)).addBox(-1.0f, -2.0f, -2.0f, 1, 2, 2);
		leftArm.setRotationPoint(3.0f, 2.0f, 0.0f);
		leftArm.setTextureSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2);
		rightLeg.setRotationPoint(-2.0f, 12.0f, 0.0f);
		rightLeg.setTextureSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2);
		leftLeg.setRotationPoint(2.0f, 12.0f, 0.0f);
		leftLeg.setTextureSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		body2.setRotationPoint(0.0f, 0.0f, 0.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 16, 33)).addBox(-1.0f, 0.0f, -2.0f, 2, 11, 4);
		body3.setRotationPoint(0.0f, 7.0f, 11.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, -1.22173f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 16, 33)).addBox(-1.0f, 0.0f, -2.0f, 2, 11, 4);
		body4.setRotationPoint(0.0f, -7.0f, 8.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, -0.7853982f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 16, 33)).addBox(-1.0f, -14.0f, -1.0f, 2, 11, 4);
		head3.setRotationPoint(0.0f, 0.0f, -2.0f);
		head3.setTextureSize(64, 64);
		head3.mirror = true;
		setRotation(head3, -0.2617994f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 16, 33)).addBox(-1.0f, -1.0f, -2.0f, 2, 11, 4);
		body6.setRotationPoint(0.0f, 1.0f, 9.0f);
		body6.setTextureSize(64, 64);
		body6.mirror = true;
		setRotation(body6, -1.047198f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 40, 16)).addBox(0.0f, -2.0f, -2.0f, 2, 15, 2);
		leftArm2.setRotationPoint(3.0f, 2.0f, 0.0f);
		leftArm2.setTextureSize(64, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 16)).addBox(-2.0f, -2.0f, -2.0f, 2, 15, 2);
		rightArm2.setRotationPoint(-3.0f, 2.0f, 0.0f);
		rightArm2.setTextureSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		head2.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		head3.render(par7);
		body6.render(par7);
		leftArm2.render(par7);
		rightArm2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
		head2.rotateAngleY = par4 / (float)(180f / Math.PI);
		head3.rotateAngleY = par4 / (float)(180f / Math.PI);
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		leftArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm2.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.lborean;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelNeptuno extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer rightArm2;
	private ModelRenderer rightArm3;
	private ModelRenderer head2;
	private ModelRenderer head3;
	private ModelRenderer head4;
	private ModelRenderer head5;
	private ModelRenderer head6;

	public ModelNeptuno() {
		textureWidth = 128;
		textureHeight = 64;
		(head = new ModelRenderer(this, 81, 7)).addBox(4.0f, -10.0f, 2.0f, 2, 3, 11);
		head.setRotationPoint(0.0f, -11.0f, -5.0f);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, 0.1745329f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 25, 0)).addBox(-4.0f, 0.0f, -2.0f, 10, 6, 7);
		body.setRotationPoint(-1.0f, 9.0f, 0.0f);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 98, 39)).addBox(-4.0f, 13.0f, -27.0f, 6, 12, 6);
		rightArm.setRotationPoint(-10.0f, -10.0f, -3.0f);
		rightArm.setTextureSize(128, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.3490659f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 34)).addBox(-2.0f, -2.0f, -3.0f, 6, 24, 6);
		leftArm.setRotationPoint(10.0f, -10.0f, -3.0f);
		leftArm.setTextureSize(128, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 0)).addBox(-3.0f, 0.0f, -3.0f, 6, 15, 6);
		rightLeg.setRotationPoint(-8.0f, 9.0f, 2.0f);
		rightLeg.setTextureSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 0)).addBox(-3.0f, 0.0f, -3.0f, 6, 15, 6);
		leftLeg.setRotationPoint(8.0f, 9.0f, 2.0f);
		leftLeg.setTextureSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0174533f);
		(body2 = new ModelRenderer(this, 61, 2)).addBox(-5.0f, 0.0f, -2.0f, 16, 12, 7);
		body2.setRotationPoint(-3.0f, -15.0f, -2.0f);
		body2.setTextureSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 0, 23)).addBox(-4.0f, 0.0f, -2.0f, 12, 12, 7);
		body3.setRotationPoint(-2.0f, -3.0f, -1.0f);
		body3.setTextureSize(128, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 34)).addBox(-4.0f, -2.0f, -3.0f, 6, 24, 6);
		rightArm2.setRotationPoint(-10.0f, -10.0f, -3.0f);
		rightArm2.setTextureSize(128, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 66, 39)).addBox(-3.0f, 16.0f, -21.0f, 4, 4, 19);
		rightArm3.setRotationPoint(-10.0f, -10.0f, -3.0f);
		rightArm3.setTextureSize(128, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.3490659f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 43)).addBox(-4.0f, -13.0f, -4.0f, 8, 13, 8);
		head2.setRotationPoint(0.0f, -11.0f, -5.0f);
		head2.setTextureSize(128, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 81, 7)).addBox(-6.0f, -10.0f, 2.0f, 2, 3, 11);
		head3.setRotationPoint(0.0f, -11.0f, -5.0f);
		head3.setTextureSize(128, 64);
		head3.mirror = true;
		setRotation(head3, 0.1745329f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 81, 7)).addBox(-1.0f, -11.0f, 7.0f, 2, 3, 11);
		head4.setRotationPoint(0.0f, -11.0f, -5.0f);
		head4.setTextureSize(128, 64);
		head4.mirror = true;
		setRotation(head4, 0.7853982f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 81, 7)).addBox(-6.0f, -12.0f, 2.0f, 2, 3, 11);
		head5.setRotationPoint(0.0f, -11.0f, -5.0f);
		head5.setTextureSize(128, 64);
		head5.mirror = true;
		setRotation(head5, 0.5235988f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 81, 7)).addBox(4.0f, -12.0f, 2.0f, 2, 3, 11);
		head6.setRotationPoint(0.0f, -11.0f, -5.0f);
		head6.setTextureSize(128, 64);
		head6.mirror = true;
		setRotation(head6, 0.5235988f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		body2.render(par7);
		body3.render(par7);
		rightArm2.render(par7);
		rightArm3.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
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
		head4.rotateAngleY = par4 / (float)(180f / Math.PI);
		head5.rotateAngleY = par4 / (float)(180f / Math.PI);
		head6.rotateAngleY = par4 / (float)(180f / Math.PI);
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightArm.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		rightArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightArm3.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

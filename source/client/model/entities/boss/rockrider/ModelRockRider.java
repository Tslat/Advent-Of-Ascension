package net.tslat.aoa3.client.model.entities.boss.rockrider;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRockRider extends ModelBase {
	private ModelRenderer headrider;
	private ModelRenderer bodyrider;
	private ModelRenderer rightArmrider;
	private ModelRenderer leftArmrider;
	private ModelRenderer rightLegrider;
	private ModelRenderer leftLegrider;
	private ModelRenderer body1;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer head;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer body4;

	public ModelRockRider() {
		textureWidth = 128;
		textureHeight = 32;
		(headrider = new ModelRenderer(this, 96, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		headrider.setRotationPoint(0.0f, -4.0f, 6.0f);
		headrider.setTextureSize(128, 32);
		headrider.mirror = true;
		setRotation(headrider, 0.0f, 0.0f, 0.0f);
		(bodyrider = new ModelRenderer(this, 70, 20)).addBox(-4.0f, 0.0f, -2.0f, 8, 8, 4);
		bodyrider.setRotationPoint(0.0f, -4.0f, 5.0f);
		bodyrider.setTextureSize(128, 32);
		bodyrider.mirror = true;
		setRotation(bodyrider, 0.3490659f, 0.0f, 0.0f);
		(rightArmrider = new ModelRenderer(this, 112, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArmrider.setRotationPoint(-5.0f, -2.0f, 6.0f);
		rightArmrider.setTextureSize(128, 32);
		rightArmrider.mirror = true;
		setRotation(rightArmrider, -1.396263f, 0.0f, 0.0f);
		(leftArmrider = new ModelRenderer(this, 112, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArmrider.setRotationPoint(5.0f, -2.0f, 6.0f);
		leftArmrider.setTextureSize(128, 32);
		leftArmrider.mirror = true;
		setRotation(leftArmrider, -1.396263f, 0.0f, 0.0f);
		(rightLegrider = new ModelRenderer(this, 96, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 3);
		rightLegrider.setRotationPoint(-3.0f, 3.0f, 8.0f);
		rightLegrider.setTextureSize(128, 32);
		rightLegrider.mirror = true;
		setRotation(rightLegrider, 0.148353f, 0.0f, 0.0f);
		(leftLegrider = new ModelRenderer(this, 96, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 3);
		leftLegrider.setRotationPoint(3.0f, 3.0f, 8.0f);
		leftLegrider.setTextureSize(128, 32);
		leftLegrider.mirror = true;
		setRotation(leftLegrider, 0.148353f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 0, 16)).addBox(-7.0f, 0.0f, -5.0f, 14, 6, 10);
		body1.setRotationPoint(0.0f, 7.0f, -5.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 1.570796f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 52, 2)).addBox(-3.0f, -2.0f, -2.0f, 4, 22, 4);
		rightArm.setRotationPoint(-8.0f, 4.0f, -2.0f);
		rightArm.setTextureSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 52, 2)).addBox(-1.0f, -2.0f, -2.0f, 4, 22, 4);
		leftArm.setRotationPoint(8.0f, 4.0f, -2.0f);
		leftArm.setTextureSize(128, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 7.0f, -5.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 72, 5)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		rightLeg.setRotationPoint(-6.0f, 16.0f, 6.0f);
		rightLeg.setTextureSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 72, 5)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leftLeg.setRotationPoint(6.0f, 16.0f, 6.0f);
		leftLeg.setTextureSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 8, 22)).addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
		body2.setRotationPoint(0.0f, 13.0f, 6.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 4, 20)).addBox(-5.0f, 0.0f, -3.0f, 10, 6, 6);
		body3.setRotationPoint(0.0f, 10.0f, 3.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.5235988f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 1, 18)).addBox(-6.0f, 0.0f, -4.0f, 12, 6, 8);
		body4.setRotationPoint(0.0f, 7.0f, -1.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 1.047198f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		headrider.render(par7);
		bodyrider.render(par7);
		rightArmrider.render(par7);
		leftArmrider.render(par7);
		rightLegrider.render(par7);
		leftLegrider.render(par7);
		body1.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		head.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		headrider.rotateAngleY = par4 / 57.295776f;
		headrider.rotateAngleX = par5 / 54.11268f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftArm.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

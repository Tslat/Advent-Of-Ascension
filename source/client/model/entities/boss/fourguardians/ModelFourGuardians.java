package net.tslat.aoa3.client.model.entities.boss.fourguardians;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFourGuardians extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer rightArm1;
	private ModelRenderer leftArm1;
	private ModelRenderer rightArm2;
	private ModelRenderer leftArm2;
	private ModelRenderer rightArm3;
	private ModelRenderer leftArm3;
	private ModelRenderer rightArm4;
	private ModelRenderer leftArm4;
	private ModelRenderer rightArm5;
	private ModelRenderer leftArm5;

	public ModelFourGuardians() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-6.0f, -12.0f, -2.0f, 12, 12, 4);
		head.setRotationPoint(0.0f, 15.0f, 0.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightArm1 = new ModelRenderer(this, 89, 3)).addBox(-1.0f, 10.0f, -2.0f, 2, 4, 1);
		rightArm1.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightArm1.setTextureSize(128, 32);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0.0f, 0.0f, 0.0f);
		(leftArm1 = new ModelRenderer(this, 113, 3)).addBox(-1.0f, 10.0f, -2.0f, 2, 4, 1);
		leftArm1.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftArm1.setTextureSize(128, 32);
		leftArm1.mirror = true;
		setRotation(leftArm1, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 3)).addBox(-1.0f, -2.0f, -4.0f, 2, 12, 8);
		rightArm2.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightArm2.setTextureSize(128, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 64, 3)).addBox(-1.0f, -2.0f, -4.0f, 2, 12, 8);
		leftArm2.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftArm2.setTextureSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 89, 3)).addBox(-1.0f, 10.0f, -4.0f, 2, 4, 1);
		rightArm3.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightArm3.setTextureSize(128, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new ModelRenderer(this, 113, 3)).addBox(-1.0f, 10.0f, -4.0f, 2, 4, 1);
		leftArm3.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftArm3.setTextureSize(128, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(rightArm4 = new ModelRenderer(this, 89, 3)).addBox(-1.0f, 10.0f, 3.0f, 2, 4, 1);
		rightArm4.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightArm4.setTextureSize(128, 32);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.0f, 0.0f, 0.0f);
		(leftArm4 = new ModelRenderer(this, 113, 3)).addBox(-1.0f, 10.0f, 3.0f, 2, 4, 1);
		leftArm4.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftArm4.setTextureSize(128, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(rightArm5 = new ModelRenderer(this, 89, 3)).addBox(-1.0f, 10.0f, 1.0f, 2, 4, 1);
		rightArm5.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightArm5.setTextureSize(128, 32);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.0f, 0.0f, 0.0f);
		(leftArm5 = new ModelRenderer(this, 113, 3)).addBox(-1.0f, 10.0f, 1.0f, 2, 4, 1);
		leftArm5.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftArm5.setTextureSize(128, 32);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		rightArm1.render(par7);
		leftArm1.render(par7);
		rightArm2.render(par7);
		leftArm2.render(par7);
		rightArm3.render(par7);
		leftArm3.render(par7);
		rightArm4.render(par7);
		leftArm4.render(par7);
		rightArm5.render(par7);
		leftArm5.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		rightArm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm1.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		rightArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm3.rotateAngleZ = 0.0f;
		rightArm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm4.rotateAngleZ = 0.0f;
		rightArm5.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm5.rotateAngleZ = 0.0f;
		leftArm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm1.rotateAngleZ = 0.0f;
		leftArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm2.rotateAngleZ = 0.0f;
		leftArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm3.rotateAngleZ = 0.0f;
		leftArm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm4.rotateAngleZ = 0.0f;
		leftArm5.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm5.rotateAngleZ = 0.0f;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.dustopia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelStalker extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer body2;
	private ModelRenderer leftArm;
	private ModelRenderer leftArm2;
	private ModelRenderer leftArm3;
	private ModelRenderer leftArm4;
	private ModelRenderer leftArm5;
	private ModelRenderer leftArm6;
	private ModelRenderer body3;

	public ModelStalker() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head.setRotationPoint(0.0f, -11.0f, -1.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 25, 12)).addBox(-1.0f, -2.0f, -1.0f, 2, 2, 2);
		body.setRotationPoint(0.0f, -9.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 21, 4);
		rightLeg.setRotationPoint(-3.0f, 3.0f, 0.0f);
		rightLeg.setTextureSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 21, 4);
		leftLeg.setRotationPoint(3.0f, 3.0f, 0.0f);
		leftLeg.setTextureSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 36)).addBox(-4.0f, 0.0f, -2.0f, 6, 7, 3);
		body2.setRotationPoint(1.0f, -8.0f, 4.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm.setRotationPoint(-4.0f, -1.0f, 4.0f);
		leftArm.setTextureSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 1.047198f);
		(leftArm2 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm2.setRotationPoint(-12.0f, -4.0f, 4.0f);
		leftArm2.setTextureSize(64, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, -1.570796f);
		(leftArm3 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm3.setRotationPoint(-4.0f, -7.0f, 4.0f);
		leftArm3.setTextureSize(64, 64);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 2.094395f);
		(leftArm4 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm4.setRotationPoint(4.0f, -4.0f, 4.0f);
		leftArm4.setTextureSize(64, 64);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, -1.570796f);
		(leftArm5 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm5.setRotationPoint(4.0f, -1.0f, 4.0f);
		leftArm5.setTextureSize(64, 64);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, -1.047198f);
		(leftArm6 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm6.setRotationPoint(4.0f, -7.0f, 4.0f);
		leftArm6.setTextureSize(64, 64);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, -2.094395f);
		(body3 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body3.setRotationPoint(0.0f, -9.0f, 0.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		body2.render(par7);
		leftArm.render(par7);
		leftArm2.render(par7);
		leftArm3.render(par7);
		leftArm4.render(par7);
		leftArm5.render(par7);
		leftArm6.render(par7);
		body3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

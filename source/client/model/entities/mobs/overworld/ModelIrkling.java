package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelIrkling extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer body2;
	private ModelRenderer head2;
	private ModelRenderer head3;
	private ModelRenderer head4;
	private ModelRenderer head5;

	public ModelIrkling() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 43, 6)).addBox(5.0f, -9.0f, -0.5333334f, 5, 2, 3);
		head.setRotationPoint(0.0f, 6.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 27, 33)).addBox(-8.0f, 0.0f, 2.0f, 16, 14, 1);
		body.setRotationPoint(0.0f, 6.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 6, 4);
		rightArm.setRotationPoint(-3.0f, 9.0f, 0.0f);
		rightArm.setTextureSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 6, 4);
		leftArm.setRotationPoint(3.0f, 9.0f, 0.0f);
		leftArm.setTextureSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		rightLeg.setRotationPoint(-2.0f, 18.0f, 0.0f);
		rightLeg.setTextureSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leftLeg.setRotationPoint(2.0f, 18.0f, 0.0f);
		leftLeg.setTextureSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		body2.setRotationPoint(0.0f, 6.0f, 0.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -6.0f, -2.533333f, 6, 6, 6);
		head2.setRotationPoint(0.0f, 6.0f, 0.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 43, 0)).addBox(-10.0f, -9.0f, -0.5333334f, 5, 2, 3);
		head3.setRotationPoint(0.0f, 6.0f, 0.0f);
		head3.setTextureSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 30, 10)).addBox(3.0f, -9.0f, -0.5333334f, 2, 6, 3);
		head4.setRotationPoint(0.0f, 6.0f, 0.0f);
		head4.setTextureSize(64, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 25, 0)).addBox(-5.0f, -9.0f, -0.5333334f, 2, 6, 3);
		head5.setRotationPoint(0.0f, 6.0f, 0.0f);
		head5.setTextureSize(64, 64);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
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
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
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
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

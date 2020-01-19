package net.tslat.aoa3.client.model.entities.mobs.dustopia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelMerkyre extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer head2;
	private ModelRenderer head3;
	private ModelRenderer head4;
	private ModelRenderer head5;
	private ModelRenderer head6;
	private ModelRenderer leftArm2;
	private ModelRenderer rightArm2;
	private ModelRenderer leftLeg3;
	private ModelRenderer rightLeg3;

	public ModelMerkyre() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-2.0f, -8.0f, -2.0f, 4, 6, 4);
		head.setRotationPoint(0.0f, 0.0f, 0.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, 0.0f, 0.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 102, 16)).addBox(-6.0f, -2.0f, 0.0f, 3, 12, 0);
		rightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm.setTextureSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 115, 16)).addBox(3.0f, -2.0f, 0.0f, 3, 12, 0);
		leftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm.setTextureSize(128, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 62, 16)).addBox(-6.0f, 0.0f, 0.0f, 4, 12, 0);
		rightLeg.setRotationPoint(-2.0f, 12.0f, 0.0f);
		rightLeg.setTextureSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 75, 16)).addBox(2.0f, 0.0f, 0.0f, 4, 12, 0);
		leftLeg.setRotationPoint(2.0f, 12.0f, 0.0f);
		leftLeg.setTextureSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 19, 3)).addBox(-5.0f, -2.0f, -5.0f, 10, 2, 10);
		head2.setRotationPoint(0.0f, 0.0f, 0.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 18, 5)).addBox(-5.0f, -6.0f, 3.0f, 2, 4, 2);
		head3.setRotationPoint(0.0f, 0.0f, 0.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 18, 5)).addBox(3.0f, -6.0f, -5.0f, 2, 4, 2);
		head4.setRotationPoint(0.0f, 0.0f, 0.0f);
		head4.setTextureSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 18, 5)).addBox(-5.0f, -6.0f, -5.0f, 2, 4, 2);
		head5.setRotationPoint(0.0f, 0.0f, 0.0f);
		head5.setTextureSize(128, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 18, 5)).addBox(3.0f, -6.0f, 3.0f, 2, 4, 2);
		head6.setRotationPoint(0.0f, 0.0f, 0.0f);
		head6.setTextureSize(128, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm2.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm2.setTextureSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm2.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm2.setTextureSize(128, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftLeg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg3.setRotationPoint(2.0f, 12.0f, 0.0f);
		leftLeg3.setTextureSize(128, 32);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.0f, 0.0f, 0.0f);
		(rightLeg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg3.setRotationPoint(-2.0f, 12.0f, 0.0f);
		rightLeg3.setTextureSize(128, 32);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
		leftArm2.render(par7);
		rightArm2.render(par7);
		leftLeg3.render(par7);
		rightLeg3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		head2.rotateAngleY = par4 / 57.295776f;
		head2.rotateAngleX = par5 / 54.11268f;
		head3.rotateAngleY = par4 / 57.295776f;
		head3.rotateAngleX = par5 / 54.11268f;
		head4.rotateAngleY = par4 / 57.295776f;
		head4.rotateAngleX = par5 / 54.11268f;
		head5.rotateAngleY = par4 / 57.295776f;
		head5.rotateAngleX = par5 / 54.11268f;
		head6.rotateAngleY = par4 / 57.295776f;
		head6.rotateAngleX = par5 / 54.11268f;
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
		rightLeg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg3.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLeg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

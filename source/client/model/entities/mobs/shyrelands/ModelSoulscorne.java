package net.tslat.aoa3.client.model.entities.mobs.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSoulscorne extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer leftLeg2;
	private ModelRenderer rightLeg2;
	private ModelRenderer leftLeg3;
	private ModelRenderer rightLeg3;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer body4;
	private ModelRenderer body5;
	private ModelRenderer leftArm2;
	private ModelRenderer rightArm2;
	private ModelRenderer leftArm3;
	private ModelRenderer rightArm3;
	private ModelRenderer head2;
	private ModelRenderer head3;

	public ModelSoulscorne() {
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 33, 0);
		head.addBox(1.0F, -5.0F, -4.0F, 2, 1, 8);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 58, 11);
		body.addBox(2.0F, 3.0F, -1.0F, 1, 6, 1);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightArm = new ModelRenderer(this, 47, 26);
		rightArm.addBox(-3.0F, 8.0F, -2.0F, 4, 1, 4);
		rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0F, 0.0F, 0.0F);
		leftArm = new ModelRenderer(this, 47, 26);
		leftArm.addBox(-1.0F, 8.0F, -2.0F, 4, 1, 4);
		leftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0F, 0.0F, 0.0F);
		rightLeg = new ModelRenderer(this, 0, 24);
		rightLeg.addBox(-2.0F, 5.0F, -2.0F, 4, 2, 4);
		rightLeg.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new ModelRenderer(this, 0, 24);
		leftLeg.addBox(-2.0F, 5.0F, -2.0F, 4, 2, 4);
		leftLeg.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		leftLeg2 = new ModelRenderer(this, 0, 16);
		leftLeg2.addBox(-2.0F, 7.0F, -2.0F, 4, 5, 2);
		leftLeg2.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftLeg2.setTextureSize(64, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0F, 0.0F, 0.0F);
		rightLeg2 = new ModelRenderer(this, 0, 16);
		rightLeg2.addBox(-2.0F, 7.0F, -2.0F, 4, 5, 2);
		rightLeg2.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightLeg2.setTextureSize(64, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0F, 0.0F, 0.0F);
		leftLeg3 = new ModelRenderer(this, 0, 16);
		leftLeg3.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 2);
		leftLeg3.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftLeg3.setTextureSize(64, 32);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.0F, 0.0F, 0.0F);
		rightLeg3 = new ModelRenderer(this, 0, 16);
		rightLeg3.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 2);
		rightLeg3.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightLeg3.setTextureSize(64, 32);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.0F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 17, 16);
		body2.addBox(-4.0F, 0.0F, 0.0F, 8, 12, 2);
		body2.setRotationPoint(0.0F, 0.0F, 0.0F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0F, 0.0F, 0.0F);
		body3 = new ModelRenderer(this, 36, 13);
		body3.addBox(-3.0F, 2.0F, -1.0F, 6, 1, 1);
		body3.setRotationPoint(0.0F, 0.0F, 0.0F);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0F, 0.0F, 0.0F);
		body4 = new ModelRenderer(this, 36, 13);
		body4.addBox(-3.0F, 9.0F, -1.0F, 6, 1, 1);
		body4.setRotationPoint(0.0F, 0.0F, 0.0F);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0F, 0.0F, 0.0F);
		body5 = new ModelRenderer(this, 58, 11);
		body5.addBox(-3.0F, 3.0F, -1.0F, 1, 6, 1);
		body5.setRotationPoint(0.0F, 0.0F, 0.0F);
		body5.setTextureSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0F, 0.0F, 0.0F);
		leftArm2 = new ModelRenderer(this, 43, 16);
		leftArm2.addBox(-1.0F, -2.0F, -2.0F, 4, 5, 4);
		leftArm2.setRotationPoint(5.0F, 2.0F, 0.0F);
		leftArm2.setTextureSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0F, 0.0F, 0.0F);
		rightArm2 = new ModelRenderer(this, 43, 16);
		rightArm2.addBox(-3.0F, -2.0F, -2.0F, 4, 5, 4);
		rightArm2.setRotationPoint(-5.0F, 2.0F, 0.0F);
		rightArm2.setTextureSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0F, 0.0F, 0.0F);
		leftArm3 = new ModelRenderer(this, 38, 25);
		leftArm3.addBox(0.0F, 3.0F, -1.0F, 2, 5, 2);
		leftArm3.setRotationPoint(5.0F, 2.0F, 0.0F);
		leftArm3.setTextureSize(64, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0F, 0.0F, 0.0F);
		rightArm3 = new ModelRenderer(this, 38, 25);
		rightArm3.addBox(-2.0F, 3.0F, -1.0F, 2, 5, 2);
		rightArm3.setRotationPoint(-5.0F, 2.0F, 0.0F);
		rightArm3.setTextureSize(64, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0F, 0.0F, 0.0F);
		head2 = new ModelRenderer(this, 0, 0);
		head2.addBox(-4.0F, -4.0F, -4.0F, 8, 4, 8);
		head2.setRotationPoint(0.0F, 0.0F, 0.0F);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0F, 0.0F, 0.0F);
		head3 = new ModelRenderer(this, 33, 0);
		head3.addBox(-3.0F, -5.0F, -4.0F, 2, 1, 8);
		head3.setRotationPoint(0.0F, 0.0F, 0.0F);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0F, 0.0F, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		leftLeg2.render(par7);
		rightLeg2.render(par7);
		leftLeg3.render(par7);
		rightLeg3.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		leftArm2.render(par7);
		rightArm2.render(par7);
		leftArm3.render(par7);
		rightArm3.render(par7);
		head2.render(par7);
		head3.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		rightLeg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightLeg.rotateAngleY = 0.0F;

		rightLeg2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightLeg2.rotateAngleY = 0.0F;

		rightLeg3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightLeg3.rotateAngleY = 0.0F;

		leftLeg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);

		leftLeg2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);

		leftLeg3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);

		rightArm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm.rotateAngleZ = 0.0F;

		rightArm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm2.rotateAngleZ = 0.0F;

		rightArm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm3.rotateAngleZ = 0.0F;

		leftArm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftArm.rotateAngleZ = 0.0F;

		leftArm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftArm2.rotateAngleZ = 0.0F;

		leftArm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftArm3.rotateAngleZ = 0.0F;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelStimulosus extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightLeg2;
	private ModelRenderer leftLeg2;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer body4;
	private ModelRenderer body5;
	private ModelRenderer head2;
	private ModelRenderer body6;
	private ModelRenderer body7;
	private ModelRenderer body8;
	private ModelRenderer body9;
	private ModelRenderer body10;
	private ModelRenderer body11;
	private ModelRenderer body12;
	private ModelRenderer body13;

	public ModelStimulosus() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(1.0F, -2.0F, -3.0F, 3, 3, 6);
		head.setRotationPoint(0.0F, -4.0F, 0.0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 21, 33);
		body.addBox(-3.0F, 0.0F, -2.0F, 6, 2, 4);
		body.setRotationPoint(0.0F, 14.0F, 0.0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightLeg2 = new ModelRenderer(this, 0, 27);
		rightLeg2.addBox(-2.0F, 6.0F, -4.0F, 4, 2, 6);
		rightLeg2.setRotationPoint(-3.0F, 16.0F, 0.0F);
		rightLeg2.setTextureSize(64, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0F, 0.0F, 0.0F);
		leftLeg2 = new ModelRenderer(this, 0, 27);
		leftLeg2.addBox(-2.0F, 6.0F, -4.0F, 4, 2, 6);
		leftLeg2.setRotationPoint(3.0F, 16.0F, 0.0F);
		leftLeg2.setTextureSize(64, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0F, 0.0F, 0.0F);
		rightLeg = new ModelRenderer(this, 0, 16);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		rightLeg.setRotationPoint(-3.0F, 16.0F, 0.0F);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new ModelRenderer(this, 0, 16);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 6, 4);
		leftLeg.setRotationPoint(3.0F, 16.0F, 0.0F);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 38, 16);
		body2.addBox(-2.0F, 0.0F, 0.0F, 4, 9, 1);
		body2.setRotationPoint(6.0F, 0.0F, 2.0F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0F, 0.0F, -0.1745329F);
		body3 = new ModelRenderer(this, 26, 6);
		body3.addBox(-6.0F, 4.0F, -2.5F, 12, 4, 5);
		body3.setRotationPoint(9.0F, 2.0F, 0.0F);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0F, 0.0F, 0.0F);
		body4 = new ModelRenderer(this, 38, 16);
		body4.addBox(-2.0F, 0.0F, -3.0F, 4, 9, 1);
		body4.setRotationPoint(6.0F, 0.0F, 0.0F);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0F, 0.0F, 0.1745329F);
		body5 = new ModelRenderer(this, 21, 16);
		body5.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		body5.setRotationPoint(9.0F, 2.0F, 0.0F);
		body5.setTextureSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0F, 0.0F, 0.0F);
		head2 = new ModelRenderer(this, 0, 0);
		head2.addBox(-4.0F, -2.0F, -3.0F, 3, 3, 6);
		head2.setRotationPoint(0.0F, -4.0F, 0.0F);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0F, 0.0F, 0.0F);
		body6 = new ModelRenderer(this, 38, 16);
		body6.addBox(-2.0F, 0.0F, -3.0F, 4, 9, 1);
		body6.setRotationPoint(0.0F, 7.0F, 0.0F);
		body6.setTextureSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 0.0F, 0.0F, -0.1745329F);
		body7 = new ModelRenderer(this, 38, 16);
		body7.addBox(-2.0F, 0.0F, 0.0F, 4, 9, 1);
		body7.setRotationPoint(0.0F, 7.0F, 2.0F);
		body7.setTextureSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 0.0F, 0.0F, 0.1745329F);
		body8 = new ModelRenderer(this, 38, 16);
		body8.addBox(-2.0F, 0.0F, -3.0F, 4, 9, 1);
		body8.setRotationPoint(-6.0F, 0.0F, 0.0F);
		body8.setTextureSize(64, 32);
		body8.mirror = true;
		setRotation(body8, 0.0F, 0.0F, -0.1745329F);
		body9 = new ModelRenderer(this, 38, 16);
		body9.addBox(-2.0F, 0.0F, 0.0F, 4, 9, 1);
		body9.setRotationPoint(-6.0F, 0.0F, 2.0F);
		body9.setTextureSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0F, 0.0F, 0.1745329F);
		body10 = new ModelRenderer(this, 26, 6);
		body10.addBox(-6.0F, 4.0F, -2.5F, 12, 4, 5);
		body10.setRotationPoint(0.0F, -4.0F, 0.0F);
		body10.setTextureSize(64, 32);
		body10.mirror = true;
		setRotation(body10, 0.0F, 0.0F, 0.0F);
		body11 = new ModelRenderer(this, 21, 16);
		body11.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		body11.setRotationPoint(0.0F, -4.0F, 0.0F);
		body11.setTextureSize(64, 32);
		body11.mirror = true;
		setRotation(body11, 0.0F, 0.0F, 0.0F);
		body12 = new ModelRenderer(this, 26, 6);
		body12.addBox(-6.0F, 4.0F, -2.5F, 12, 4, 5);
		body12.setRotationPoint(-9.0F, 2.0F, 0.0F);
		body12.setTextureSize(64, 32);
		body12.mirror = true;
		setRotation(body12, 0.0F, 0.0F, 0.0F);
		body13 = new ModelRenderer(this, 21, 16);
		body13.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		body13.setRotationPoint(-9.0F, 2.0F, 0.0F);
		body13.setTextureSize(64, 32);
		body13.mirror = true;
		setRotation(body13, 0.0F, 0.0F, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightLeg2.render(par7);
		leftLeg2.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		head2.render(par7);
		body6.render(par7);
		body7.render(par7);
		body8.render(par7);
		body9.render(par7);
		body10.render(par7);
		body11.render(par7);
		body12.render(par7);
		body13.render(par7);
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

		leftLeg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
		leftLeg2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
	}
}

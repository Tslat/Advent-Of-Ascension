package net.tslat.aoa3.client.model.entities.boss.craexxeus;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelXxeus extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer rightArm2;
	private ModelRenderer leftArm2;
	private ModelRenderer leftArm3;
	private ModelRenderer rightArm3;
	private ModelRenderer leftArm4;
	private ModelRenderer rightArm4;
	private ModelRenderer leftArm5;
	private ModelRenderer rightArm5;
	private ModelRenderer rightArm6;
	private ModelRenderer leftArm6;
	private ModelRenderer rightArm7;
	private ModelRenderer leftArm7;
	private ModelRenderer rightArm8;
	private ModelRenderer leftArm8;
	private ModelRenderer rightArm9;
	private ModelRenderer leftArm9;
	private ModelRenderer rightArm10;
	private ModelRenderer leftArm10;
	private ModelRenderer rightArm11;
	private ModelRenderer leftArm11;
	private ModelRenderer head2;
	private ModelRenderer head3;
	private ModelRenderer head4;
	private ModelRenderer head5;
	private ModelRenderer head6;
	private ModelRenderer head7;
	private ModelRenderer body;
	private ModelRenderer leftArm12;
	private ModelRenderer rightArm12;
	private ModelRenderer leftArm13;
	private ModelRenderer rightArm13;
	private ModelRenderer leftArm14;
	private ModelRenderer rightArm14;
	private ModelRenderer leftArm15;
	private ModelRenderer rightArm15;
	private ModelRenderer leftArm16;
	private ModelRenderer rightArm16;

	public ModelXxeus() {
		textureWidth = 256;
		textureHeight = 128;

		head = new ModelRenderer(this, 66, 7);
		head.addBox(-2.0F, -14.0F, -5.0F, 4, 2, 1);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureSize(256, 128);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		rightArm = new ModelRenderer(this, 57, 27);
		rightArm.addBox(-4.0F, -7.0F, 1.0F, 1, 3, 2);
		rightArm.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm.setTextureSize(256, 128);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0F, 0.0F, 0.2617994F);
		leftArm = new ModelRenderer(this, 57, 27);
		leftArm.addBox(3.0F, -7.0F, 1.0F, 1, 3, 2);
		leftArm.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm.setTextureSize(256, 128);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0F, 0.0F, -0.2617994F);
		rightLeg = new ModelRenderer(this, 0, 16);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		rightLeg.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightLeg.setTextureSize(256, 128);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new ModelRenderer(this, 0, 16);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		leftLeg.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftLeg.setTextureSize(256, 128);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		rightArm2 = new ModelRenderer(this, 90, 7);
		rightArm2.addBox(-1.5F, 14.0F, -19.0F, 1, 1, 5);
		rightArm2.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm2.setTextureSize(256, 128);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0F, 0.0F, 0.2617994F);
		leftArm2 = new ModelRenderer(this, 90, 7);
		leftArm2.addBox(0.5F, 14.0F, -19.0F, 1, 1, 5);
		leftArm2.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm2.setTextureSize(256, 128);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0F, 0.0F, -0.2617994F);
		leftArm3 = new ModelRenderer(this, 57, 17);
		leftArm3.addBox(-2.0F, -4.0F, -3.0F, 6, 2, 6);
		leftArm3.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm3.setTextureSize(256, 128);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0F, 0.0F, -0.2617994F);
		rightArm3 = new ModelRenderer(this, 57, 17);
		rightArm3.addBox(-4.0F, -4.0F, -3.0F, 6, 2, 6);
		rightArm3.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm3.setTextureSize(256, 128);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0F, 0.0F, 0.2617994F);
		leftArm4 = new ModelRenderer(this, 57, 27);
		leftArm4.addBox(3.0F, -7.0F, -3.0F, 1, 3, 2);
		leftArm4.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm4.setTextureSize(256, 128);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0F, 0.0F, -0.2617994F);
		rightArm4 = new ModelRenderer(this, 57, 27);
		rightArm4.addBox(-4.0F, -7.0F, -3.0F, 1, 3, 2);
		rightArm4.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm4.setTextureSize(256, 128);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.0F, 0.0F, 0.2617994F);
		leftArm5 = new ModelRenderer(this, 40, 16);
		leftArm5.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
		leftArm5.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm5.setTextureSize(256, 128);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0F, 0.0F, -0.2617994F);
		rightArm5 = new ModelRenderer(this, 40, 16);
		rightArm5.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
		rightArm5.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm5.setTextureSize(256, 128);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.0F, 0.0F, 0.2617994F);
		rightArm6 = new ModelRenderer(this, 73, 29);
		rightArm6.addBox(-2.0F, 7.0F, -7.0F, 2, 2, 10);
		rightArm6.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm6.setTextureSize(256, 128);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.0F, 0.0F, 0.2617994F);
		leftArm6 = new ModelRenderer(this, 73, 29);
		leftArm6.addBox(0.0F, 7.0F, -7.0F, 2, 2, 10);
		leftArm6.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm6.setTextureSize(256, 128);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0F, 0.0F, -0.2617994F);
		rightArm7 = new ModelRenderer(this, 72, 92);
		rightArm7.addBox(-1.5F, 4.0F, -27.0F, 1, 2, 2);
		rightArm7.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm7.setTextureSize(256, 128);
		rightArm7.mirror = true;
		setRotation(rightArm7, 0.0F, 0.0F, 0.2617994F);
		leftArm7 = new ModelRenderer(this, 72, 92);
		leftArm7.addBox(0.5F, 4.0F, -27.0F, 1, 2, 2);
		leftArm7.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm7.setTextureSize(256, 128);
		leftArm7.mirror = true;
		setRotation(leftArm7, 0.0F, 0.0F, -0.2617994F);
		rightArm8 = new ModelRenderer(this, 111, 1);
		rightArm8.addBox(-1.5F, 5.0F, -24.0F, 1, 6, 2);
		rightArm8.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm8.setTextureSize(256, 128);
		rightArm8.mirror = true;
		setRotation(rightArm8, 0.0F, 0.0F, 0.2617994F);
		leftArm8 = new ModelRenderer(this, 111, 1);
		leftArm8.addBox(0.5F, 5.0F, -24.0F, 1, 6, 2);
		leftArm8.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm8.setTextureSize(256, 128);
		leftArm8.mirror = true;
		setRotation(leftArm8, 0.0F, 0.0F, -0.2617994F);
		rightArm9 = new ModelRenderer(this, 99, 14);
		rightArm9.addBox(-1.5F, 12.0F, -19.0F, 1, 2, 2);
		rightArm9.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm9.setTextureSize(256, 128);
		rightArm9.mirror = true;
		setRotation(rightArm9, 0.0F, 0.0F, 0.2617994F);
		leftArm9 = new ModelRenderer(this, 99, 14);
		leftArm9.addBox(0.5F, 12.0F, -19.0F, 1, 2, 2);
		leftArm9.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm9.setTextureSize(256, 128);
		leftArm9.mirror = true;
		setRotation(leftArm9, 0.0F, 0.0F, -0.2617994F);
		rightArm10 = new ModelRenderer(this, 90, 7);
		rightArm10.addBox(-1.5F, 1.0F, -19.0F, 1, 1, 5);
		rightArm10.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm10.setTextureSize(256, 128);
		rightArm10.mirror = true;
		setRotation(rightArm10, 0.0F, 0.0F, 0.2617994F);
		leftArm10 = new ModelRenderer(this, 90, 7);
		leftArm10.addBox(0.5F, 1.0F, -19.0F, 1, 1, 5);
		leftArm10.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm10.setTextureSize(256, 128);
		leftArm10.mirror = true;
		setRotation(leftArm10, 0.0F, 0.0F, -0.2617994F);
		rightArm11 = new ModelRenderer(this, 90, 1);
		rightArm11.addBox(-1.5F, 2.0F, -19.0F, 1, 2, 2);
		rightArm11.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm11.setTextureSize(256, 128);
		rightArm11.mirror = true;
		setRotation(rightArm11, 0.0F, 0.0F, 0.2617994F);
		leftArm11 = new ModelRenderer(this, 90, 1);
		leftArm11.addBox(0.5F, 2.0F, -19.0F, 1, 2, 2);
		leftArm11.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm11.setTextureSize(256, 128);
		leftArm11.mirror = true;
		setRotation(leftArm11, 0.0F, 0.0F, -0.2617994F);
		head2 = new ModelRenderer(this, 0, 0);
		head2.addBox(-4.0F, -5.0F, -4.0F, 8, 5, 8);
		head2.setRotationPoint(0.0F, 0.0F, 0.0F);
		head2.setTextureSize(256, 128);
		head2.mirror = true;
		setRotation(head2, 0.0F, 0.0F, 0.0F);
		head3 = new ModelRenderer(this, 103, 28);
		head3.addBox(5.0F, -9.0F, 2.0F, 1, 4, 11);
		head3.setRotationPoint(0.0F, 0.0F, 0.0F);
		head3.setTextureSize(256, 128);
		head3.mirror = true;
		setRotation(head3, 0.5235988F, 0.0F, 0.0F);
		head4 = new ModelRenderer(this, 66, 0);
		head4.addBox(-4.0F, -10.0F, -5.0F, 8, 1, 1);
		head4.setRotationPoint(0.0F, 0.0F, 0.0F);
		head4.setTextureSize(256, 128);
		head4.mirror = true;
		setRotation(head4, 0.0F, 0.0F, 0.0F);
		head5 = new ModelRenderer(this, 66, 3);
		head5.addBox(-3.0F, -12.0F, -5.0F, 6, 2, 1);
		head5.setRotationPoint(0.0F, 0.0F, 0.0F);
		head5.setTextureSize(256, 128);
		head5.mirror = true;
		setRotation(head5, 0.0F, 0.0F, 0.0F);
		head6 = new ModelRenderer(this, 33, 0);
		head6.addBox(-5.0F, -9.0F, -5.0F, 10, 4, 11);
		head6.setRotationPoint(0.0F, 0.0F, 0.0F);
		head6.setTextureSize(256, 128);
		head6.mirror = true;
		setRotation(head6, 0.0F, 0.0F, 0.0F);
		head7 = new ModelRenderer(this, 103, 28);
		head7.addBox(-6.0F, -9.0F, 2.0F, 1, 4, 11);
		head7.setRotationPoint(0.0F, 0.0F, 0.0F);
		head7.setTextureSize(256, 128);
		head7.mirror = true;
		setRotation(head7, 0.5235988F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 16, 16);
		body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.setTextureSize(256, 128);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		leftArm12 = new ModelRenderer(this, 89, 1);
		leftArm12.addBox(0.5F, 4.0F, -22.0F, 1, 8, 18);
		leftArm12.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm12.setTextureSize(256, 128);
		leftArm12.mirror = true;
		setRotation(leftArm12, 0.0F, 0.0F, -0.2617994F);
		rightArm12 = new ModelRenderer(this, 89, 1);
		rightArm12.addBox(-1.5F, 4.0F, -22.0F, 1, 8, 18);
		rightArm12.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm12.setTextureSize(256, 128);
		rightArm12.mirror = true;
		setRotation(rightArm12, 0.0F, 0.0F, 0.2617994F);
		leftArm13 = new ModelRenderer(this, 72, 70);
		leftArm13.addBox(0.5F, 6.0F, -39.0F, 1, 2, 15);
		leftArm13.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm13.setTextureSize(256, 128);
		leftArm13.mirror = true;
		setRotation(leftArm13, 0.0F, 0.0F, -0.2617994F);
		rightArm13 = new ModelRenderer(this, 72, 70);
		rightArm13.addBox(-1.5F, 6.0F, -39.0F, 1, 2, 15);
		rightArm13.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm13.setTextureSize(256, 128);
		rightArm13.mirror = true;
		setRotation(rightArm13, 0.0F, 0.0F, 0.2617994F);
		leftArm14 = new ModelRenderer(this, 72, 92);
		leftArm14.addBox(0.5F, 4.0F, -39.0F, 1, 2, 2);
		leftArm14.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm14.setTextureSize(256, 128);
		leftArm14.mirror = true;
		setRotation(leftArm14, 0.0F, 0.0F, -0.2617994F);
		rightArm14 = new ModelRenderer(this, 72, 92);
		rightArm14.addBox(-1.5F, 4.0F, -39.0F, 1, 2, 2);
		rightArm14.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm14.setTextureSize(256, 128);
		rightArm14.mirror = true;
		setRotation(rightArm14, 0.0F, 0.0F, 0.2617994F);
		leftArm15 = new ModelRenderer(this, 72, 92);
		leftArm15.addBox(0.5F, 4.0F, -35.0F, 1, 2, 2);
		leftArm15.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm15.setTextureSize(256, 128);
		leftArm15.mirror = true;
		setRotation(leftArm15, 0.0F, 0.0F, -0.2617994F);
		rightArm15 = new ModelRenderer(this, 72, 92);
		rightArm15.addBox(-1.5F, 4.0F, -35.0F, 1, 2, 2);
		rightArm15.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm15.setTextureSize(256, 128);
		rightArm15.mirror = true;
		setRotation(rightArm15, 0.0F, 0.0F, 0.2617994F);
		leftArm16 = new ModelRenderer(this, 72, 92);
		leftArm16.addBox(0.5F, 4.0F, -31.0F, 1, 2, 2);
		leftArm16.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftArm16.setTextureSize(256, 128);
		leftArm16.mirror = true;
		setRotation(leftArm16, 0.0F, 0.0F, -0.2617994F);
		rightArm16 = new ModelRenderer(this, 72, 92);
		rightArm16.addBox(-1.5F, 4.0F, -31.0F, 1, 2, 2);
		rightArm16.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightArm16.setTextureSize(256, 128);
		rightArm16.mirror = true;
		setRotation(rightArm16, 0.0F, 0.0F, 0.2617994F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		rightArm2.render(par7);
		leftArm2.render(par7);
		leftArm3.render(par7);
		rightArm3.render(par7);
		leftArm4.render(par7);
		rightArm4.render(par7);
		leftArm5.render(par7);
		rightArm5.render(par7);
		rightArm6.render(par7);
		leftArm6.render(par7);
		rightArm7.render(par7);
		leftArm7.render(par7);
		rightArm8.render(par7);
		leftArm8.render(par7);
		rightArm9.render(par7);
		leftArm9.render(par7);
		rightArm10.render(par7);
		leftArm10.render(par7);
		rightArm11.render(par7);
		leftArm11.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
		head7.render(par7);
		body.render(par7);
		leftArm12.render(par7);
		rightArm12.render(par7);
		leftArm13.render(par7);
		rightArm13.render(par7);
		leftArm14.render(par7);
		rightArm14.render(par7);
		leftArm15.render(par7);
		rightArm15.render(par7);
		leftArm16.render(par7);
		rightArm16.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		rightArm.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm.rotateAngleZ = 0.0F;

		rightArm2.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm2.rotateAngleZ = 0.0F;

		rightArm3.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm3.rotateAngleZ = 0.0F;

		rightArm4.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm4.rotateAngleZ = 0.0F;

		rightArm5.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm5.rotateAngleZ = 0.0F;

		rightArm6.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm6.rotateAngleZ = 0.0F;

		rightArm7.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm7.rotateAngleZ = 0.0F;

		rightArm8.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm8.rotateAngleZ = 0.0F;

		rightArm9.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm9.rotateAngleZ = 0.0F;

		rightArm10.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm10.rotateAngleZ = 0.0F;

		rightArm11.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm11.rotateAngleZ = 0.0F;

		rightArm12.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm12.rotateAngleZ = 0.0F;

		rightArm13.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm13.rotateAngleZ = 0.0F;

		rightArm14.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm14.rotateAngleZ = 0.0F;

		rightArm15.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm15.rotateAngleZ = 0.0F;

		rightArm16.rotateAngleX = (MathHelper.cos(par1 * 0.3331F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightArm16.rotateAngleZ = 0.0F;

		leftArm.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm.rotateAngleZ = 0.0F;

		leftArm2.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm2.rotateAngleZ = 0.0F;

		leftArm3.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm3.rotateAngleZ = 0.0F;

		leftArm4.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm4.rotateAngleZ = 0.0F;

		leftArm5.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm5.rotateAngleZ = 0.0F;

		leftArm6.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm6.rotateAngleZ = 0.0F;

		leftArm7.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm7.rotateAngleZ = 0.0F;

		leftArm8.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm8.rotateAngleZ = 0.0F;

		leftArm9.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm9.rotateAngleZ = 0.0F;

		leftArm10.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm10.rotateAngleZ = 0.0F;

		leftArm11.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm11.rotateAngleZ = 0.0F;

		leftArm12.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm12.rotateAngleZ = 0.0F;

		leftArm13.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm13.rotateAngleZ = 0.0F;

		leftArm14.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm14.rotateAngleZ = 0.0F;

		leftArm15.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm15.rotateAngleZ = 0.0F;

		leftArm16.rotateAngleX = (MathHelper.cos(par1 * 0.3331F) * 2.0F * par2 * 0.5F);
		leftArm16.rotateAngleZ = 0.0F;

		rightLeg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightLeg.rotateAngleY = 0.0F;

		leftLeg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
	}
}

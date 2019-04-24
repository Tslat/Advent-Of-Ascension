package net.tslat.aoa3.client.model.entities.boss.craexxeus;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelXxeus extends ModelBase {
	ModelRenderer head;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer rightarm2;
	ModelRenderer leftarm2;
	ModelRenderer leftarm3;
	ModelRenderer rightarm3;
	ModelRenderer leftarm4;
	ModelRenderer rightarm4;
	ModelRenderer leftarm5;
	ModelRenderer rightarm5;
	ModelRenderer rightarm6;
	ModelRenderer leftarm6;
	ModelRenderer rightarm7;
	ModelRenderer leftarm7;
	ModelRenderer rightarm8;
	ModelRenderer leftarm8;
	ModelRenderer rightarm9;
	ModelRenderer leftarm9;
	ModelRenderer rightarm10;
	ModelRenderer leftarm10;
	ModelRenderer rightarm11;
	ModelRenderer leftarm11;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer head5;
	ModelRenderer head6;
	ModelRenderer head7;
	ModelRenderer body;
	ModelRenderer leftarm12;
	ModelRenderer rightarm12;
	ModelRenderer leftarm13;
	ModelRenderer rightarm13;
	ModelRenderer leftarm14;
	ModelRenderer rightarm14;
	ModelRenderer leftarm15;
	ModelRenderer rightarm15;
	ModelRenderer leftarm16;
	ModelRenderer rightarm16;

	public ModelXxeus() {
		textureWidth = 256;
		textureHeight = 128;

		head = new ModelRenderer(this, 66, 7);
		head.addBox(-2.0F, -14.0F, -5.0F, 4, 2, 1);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureSize(256, 128);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		rightarm = new ModelRenderer(this, 57, 27);
		rightarm.addBox(-4.0F, -7.0F, 1.0F, 1, 3, 2);
		rightarm.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm.setTextureSize(256, 128);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0F, 0.0F, 0.2617994F);
		leftarm = new ModelRenderer(this, 57, 27);
		leftarm.addBox(3.0F, -7.0F, 1.0F, 1, 3, 2);
		leftarm.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm.setTextureSize(256, 128);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0F, 0.0F, -0.2617994F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		rightleg.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightleg.setTextureSize(256, 128);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0F, 0.0F, 0.0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		leftleg.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftleg.setTextureSize(256, 128);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0F, 0.0F, 0.0F);
		rightarm2 = new ModelRenderer(this, 90, 7);
		rightarm2.addBox(-1.5F, 14.0F, -19.0F, 1, 1, 5);
		rightarm2.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm2.setTextureSize(256, 128);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0F, 0.0F, 0.2617994F);
		leftarm2 = new ModelRenderer(this, 90, 7);
		leftarm2.addBox(0.5F, 14.0F, -19.0F, 1, 1, 5);
		leftarm2.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm2.setTextureSize(256, 128);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0F, 0.0F, -0.2617994F);
		leftarm3 = new ModelRenderer(this, 57, 17);
		leftarm3.addBox(-2.0F, -4.0F, -3.0F, 6, 2, 6);
		leftarm3.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm3.setTextureSize(256, 128);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0F, 0.0F, -0.2617994F);
		rightarm3 = new ModelRenderer(this, 57, 17);
		rightarm3.addBox(-4.0F, -4.0F, -3.0F, 6, 2, 6);
		rightarm3.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm3.setTextureSize(256, 128);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0F, 0.0F, 0.2617994F);
		leftarm4 = new ModelRenderer(this, 57, 27);
		leftarm4.addBox(3.0F, -7.0F, -3.0F, 1, 3, 2);
		leftarm4.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm4.setTextureSize(256, 128);
		leftarm4.mirror = true;
		setRotation(leftarm4, 0.0F, 0.0F, -0.2617994F);
		rightarm4 = new ModelRenderer(this, 57, 27);
		rightarm4.addBox(-4.0F, -7.0F, -3.0F, 1, 3, 2);
		rightarm4.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm4.setTextureSize(256, 128);
		rightarm4.mirror = true;
		setRotation(rightarm4, 0.0F, 0.0F, 0.2617994F);
		leftarm5 = new ModelRenderer(this, 40, 16);
		leftarm5.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
		leftarm5.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm5.setTextureSize(256, 128);
		leftarm5.mirror = true;
		setRotation(leftarm5, 0.0F, 0.0F, -0.2617994F);
		rightarm5 = new ModelRenderer(this, 40, 16);
		rightarm5.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
		rightarm5.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm5.setTextureSize(256, 128);
		rightarm5.mirror = true;
		setRotation(rightarm5, 0.0F, 0.0F, 0.2617994F);
		rightarm6 = new ModelRenderer(this, 73, 29);
		rightarm6.addBox(-2.0F, 7.0F, -7.0F, 2, 2, 10);
		rightarm6.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm6.setTextureSize(256, 128);
		rightarm6.mirror = true;
		setRotation(rightarm6, 0.0F, 0.0F, 0.2617994F);
		leftarm6 = new ModelRenderer(this, 73, 29);
		leftarm6.addBox(0.0F, 7.0F, -7.0F, 2, 2, 10);
		leftarm6.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm6.setTextureSize(256, 128);
		leftarm6.mirror = true;
		setRotation(leftarm6, 0.0F, 0.0F, -0.2617994F);
		rightarm7 = new ModelRenderer(this, 72, 92);
		rightarm7.addBox(-1.5F, 4.0F, -27.0F, 1, 2, 2);
		rightarm7.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm7.setTextureSize(256, 128);
		rightarm7.mirror = true;
		setRotation(rightarm7, 0.0F, 0.0F, 0.2617994F);
		leftarm7 = new ModelRenderer(this, 72, 92);
		leftarm7.addBox(0.5F, 4.0F, -27.0F, 1, 2, 2);
		leftarm7.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm7.setTextureSize(256, 128);
		leftarm7.mirror = true;
		setRotation(leftarm7, 0.0F, 0.0F, -0.2617994F);
		rightarm8 = new ModelRenderer(this, 111, 1);
		rightarm8.addBox(-1.5F, 5.0F, -24.0F, 1, 6, 2);
		rightarm8.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm8.setTextureSize(256, 128);
		rightarm8.mirror = true;
		setRotation(rightarm8, 0.0F, 0.0F, 0.2617994F);
		leftarm8 = new ModelRenderer(this, 111, 1);
		leftarm8.addBox(0.5F, 5.0F, -24.0F, 1, 6, 2);
		leftarm8.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm8.setTextureSize(256, 128);
		leftarm8.mirror = true;
		setRotation(leftarm8, 0.0F, 0.0F, -0.2617994F);
		rightarm9 = new ModelRenderer(this, 99, 14);
		rightarm9.addBox(-1.5F, 12.0F, -19.0F, 1, 2, 2);
		rightarm9.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm9.setTextureSize(256, 128);
		rightarm9.mirror = true;
		setRotation(rightarm9, 0.0F, 0.0F, 0.2617994F);
		leftarm9 = new ModelRenderer(this, 99, 14);
		leftarm9.addBox(0.5F, 12.0F, -19.0F, 1, 2, 2);
		leftarm9.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm9.setTextureSize(256, 128);
		leftarm9.mirror = true;
		setRotation(leftarm9, 0.0F, 0.0F, -0.2617994F);
		rightarm10 = new ModelRenderer(this, 90, 7);
		rightarm10.addBox(-1.5F, 1.0F, -19.0F, 1, 1, 5);
		rightarm10.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm10.setTextureSize(256, 128);
		rightarm10.mirror = true;
		setRotation(rightarm10, 0.0F, 0.0F, 0.2617994F);
		leftarm10 = new ModelRenderer(this, 90, 7);
		leftarm10.addBox(0.5F, 1.0F, -19.0F, 1, 1, 5);
		leftarm10.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm10.setTextureSize(256, 128);
		leftarm10.mirror = true;
		setRotation(leftarm10, 0.0F, 0.0F, -0.2617994F);
		rightarm11 = new ModelRenderer(this, 90, 1);
		rightarm11.addBox(-1.5F, 2.0F, -19.0F, 1, 2, 2);
		rightarm11.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm11.setTextureSize(256, 128);
		rightarm11.mirror = true;
		setRotation(rightarm11, 0.0F, 0.0F, 0.2617994F);
		leftarm11 = new ModelRenderer(this, 90, 1);
		leftarm11.addBox(0.5F, 2.0F, -19.0F, 1, 2, 2);
		leftarm11.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm11.setTextureSize(256, 128);
		leftarm11.mirror = true;
		setRotation(leftarm11, 0.0F, 0.0F, -0.2617994F);
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
		leftarm12 = new ModelRenderer(this, 89, 1);
		leftarm12.addBox(0.5F, 4.0F, -22.0F, 1, 8, 18);
		leftarm12.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm12.setTextureSize(256, 128);
		leftarm12.mirror = true;
		setRotation(leftarm12, 0.0F, 0.0F, -0.2617994F);
		rightarm12 = new ModelRenderer(this, 89, 1);
		rightarm12.addBox(-1.5F, 4.0F, -22.0F, 1, 8, 18);
		rightarm12.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm12.setTextureSize(256, 128);
		rightarm12.mirror = true;
		setRotation(rightarm12, 0.0F, 0.0F, 0.2617994F);
		leftarm13 = new ModelRenderer(this, 72, 70);
		leftarm13.addBox(0.5F, 6.0F, -39.0F, 1, 2, 15);
		leftarm13.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm13.setTextureSize(256, 128);
		leftarm13.mirror = true;
		setRotation(leftarm13, 0.0F, 0.0F, -0.2617994F);
		rightarm13 = new ModelRenderer(this, 72, 70);
		rightarm13.addBox(-1.5F, 6.0F, -39.0F, 1, 2, 15);
		rightarm13.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm13.setTextureSize(256, 128);
		rightarm13.mirror = true;
		setRotation(rightarm13, 0.0F, 0.0F, 0.2617994F);
		leftarm14 = new ModelRenderer(this, 72, 92);
		leftarm14.addBox(0.5F, 4.0F, -39.0F, 1, 2, 2);
		leftarm14.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm14.setTextureSize(256, 128);
		leftarm14.mirror = true;
		setRotation(leftarm14, 0.0F, 0.0F, -0.2617994F);
		rightarm14 = new ModelRenderer(this, 72, 92);
		rightarm14.addBox(-1.5F, 4.0F, -39.0F, 1, 2, 2);
		rightarm14.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm14.setTextureSize(256, 128);
		rightarm14.mirror = true;
		setRotation(rightarm14, 0.0F, 0.0F, 0.2617994F);
		leftarm15 = new ModelRenderer(this, 72, 92);
		leftarm15.addBox(0.5F, 4.0F, -35.0F, 1, 2, 2);
		leftarm15.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm15.setTextureSize(256, 128);
		leftarm15.mirror = true;
		setRotation(leftarm15, 0.0F, 0.0F, -0.2617994F);
		rightarm15 = new ModelRenderer(this, 72, 92);
		rightarm15.addBox(-1.5F, 4.0F, -35.0F, 1, 2, 2);
		rightarm15.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm15.setTextureSize(256, 128);
		rightarm15.mirror = true;
		setRotation(rightarm15, 0.0F, 0.0F, 0.2617994F);
		leftarm16 = new ModelRenderer(this, 72, 92);
		leftarm16.addBox(0.5F, 4.0F, -31.0F, 1, 2, 2);
		leftarm16.setRotationPoint(6.0F, 3.0F, 0.0F);
		leftarm16.setTextureSize(256, 128);
		leftarm16.mirror = true;
		setRotation(leftarm16, 0.0F, 0.0F, -0.2617994F);
		rightarm16 = new ModelRenderer(this, 72, 92);
		rightarm16.addBox(-1.5F, 4.0F, -31.0F, 1, 2, 2);
		rightarm16.setRotationPoint(-6.0F, 3.0F, 0.0F);
		rightarm16.setTextureSize(256, 128);
		rightarm16.mirror = true;
		setRotation(rightarm16, 0.0F, 0.0F, 0.2617994F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		rightarm2.render(par7);
		leftarm2.render(par7);
		leftarm3.render(par7);
		rightarm3.render(par7);
		leftarm4.render(par7);
		rightarm4.render(par7);
		leftarm5.render(par7);
		rightarm5.render(par7);
		rightarm6.render(par7);
		leftarm6.render(par7);
		rightarm7.render(par7);
		leftarm7.render(par7);
		rightarm8.render(par7);
		leftarm8.render(par7);
		rightarm9.render(par7);
		leftarm9.render(par7);
		rightarm10.render(par7);
		leftarm10.render(par7);
		rightarm11.render(par7);
		leftarm11.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
		head7.render(par7);
		body.render(par7);
		leftarm12.render(par7);
		rightarm12.render(par7);
		leftarm13.render(par7);
		rightarm13.render(par7);
		leftarm14.render(par7);
		rightarm14.render(par7);
		leftarm15.render(par7);
		rightarm15.render(par7);
		leftarm16.render(par7);
		rightarm16.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		rightarm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm.rotateAngleZ = 0.0F;

		rightarm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm2.rotateAngleZ = 0.0F;

		rightarm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm3.rotateAngleZ = 0.0F;

		rightarm4.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm4.rotateAngleZ = 0.0F;

		rightarm5.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm5.rotateAngleZ = 0.0F;

		rightarm6.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm6.rotateAngleZ = 0.0F;

		rightarm7.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm7.rotateAngleZ = 0.0F;

		rightarm8.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm8.rotateAngleZ = 0.0F;

		rightarm9.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm9.rotateAngleZ = 0.0F;

		rightarm10.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm10.rotateAngleZ = 0.0F;

		rightarm11.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm11.rotateAngleZ = 0.0F;

		rightarm12.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm12.rotateAngleZ = 0.0F;

		rightarm13.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm13.rotateAngleZ = 0.0F;

		rightarm14.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm14.rotateAngleZ = 0.0F;

		rightarm15.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm15.rotateAngleZ = 0.0F;

		rightarm16.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm16.rotateAngleZ = 0.0F;

		leftarm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm.rotateAngleZ = 0.0F;

		leftarm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm2.rotateAngleZ = 0.0F;

		leftarm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm3.rotateAngleZ = 0.0F;

		leftarm4.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm4.rotateAngleZ = 0.0F;

		leftarm5.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm5.rotateAngleZ = 0.0F;

		leftarm6.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm6.rotateAngleZ = 0.0F;

		leftarm7.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm7.rotateAngleZ = 0.0F;

		leftarm8.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm8.rotateAngleZ = 0.0F;

		leftarm9.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm9.rotateAngleZ = 0.0F;

		leftarm10.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm10.rotateAngleZ = 0.0F;

		leftarm11.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm11.rotateAngleZ = 0.0F;

		leftarm12.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm12.rotateAngleZ = 0.0F;

		leftarm13.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm13.rotateAngleZ = 0.0F;

		leftarm14.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm14.rotateAngleZ = 0.0F;

		leftarm15.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm15.rotateAngleZ = 0.0F;

		leftarm16.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm16.rotateAngleZ = 0.0F;

		rightleg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightleg.rotateAngleY = 0.0F;

		leftleg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
	}
}

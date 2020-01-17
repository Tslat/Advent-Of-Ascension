package net.tslat.aoa3.client.model.entities.animals;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHalycon extends ModelBase {
	private ModelRenderer rightEar;
	private ModelRenderer leftEar;
	private ModelRenderer Middle_Back_Spike;
	private ModelRenderer Front_Back_Spike;
	private ModelRenderer Last_Back_Spike;
	private ModelRenderer tail;
	private ModelRenderer Jaw_spike;
	private ModelRenderer Left_body_Plate;
	private ModelRenderer Right_body_Plate;
	private ModelRenderer Left_Top_body_Plate;
	private ModelRenderer Right_Top_body_Plate;
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer leg1;
	private ModelRenderer leg2;
	private ModelRenderer leg3;
	private ModelRenderer leg4;
	private ModelRenderer udders;

	public ModelHalycon() {
		textureWidth = 128;
		textureHeight = 64;
		(rightEar = new ModelRenderer(this, 0, 0)).addBox(-1.0f, -1.0f, -1.0f, 2, 2, 2);
		rightEar.setRotationPoint(-5.0f, 1.0f, -11.0f);
		rightEar.setTextureSize(128, 64);
		rightEar.mirror = true;
		setRotation(rightEar, 0.0f, 0.0f, 0.0f);
		(leftEar = new ModelRenderer(this, 0, 0)).addBox(-1.0f, -1.0f, -1.0f, 2, 2, 2);
		leftEar.setRotationPoint(5.0f, 1.0f, -11.0f);
		leftEar.setTextureSize(128, 64);
		leftEar.mirror = true;
		setRotation(leftEar, 0.0f, 0.0f, 0.0f);
		(Middle_Back_Spike = new ModelRenderer(this, 70, 12)).addBox(-1.0f, -6.0f, -1.0f, 2, 6, 2);
		Middle_Back_Spike.setRotationPoint(0.0f, 3.0f, 0.0f);
		Middle_Back_Spike.setTextureSize(128, 64);
		Middle_Back_Spike.mirror = true;
		setRotation(Middle_Back_Spike, -0.5205006f, 0.0f, 0.0f);
		(Front_Back_Spike = new ModelRenderer(this, 70, 0)).addBox(-1.0f, -8.0f, -1.0f, 2, 8, 2);
		Front_Back_Spike.setRotationPoint(0.0f, 3.0f, -5.0f);
		Front_Back_Spike.setTextureSize(128, 64);
		Front_Back_Spike.mirror = true;
		setRotation(Front_Back_Spike, -0.5205006f, 0.0f, 0.0f);
		(Last_Back_Spike = new ModelRenderer(this, 70, 22)).addBox(-1.0f, -4.0f, -1.0f, 2, 4, 2);
		Last_Back_Spike.setRotationPoint(0.0f, 3.0f, 5.0f);
		Last_Back_Spike.setTextureSize(128, 64);
		Last_Back_Spike.mirror = true;
		setRotation(Last_Back_Spike, -0.5205006f, 0.0f, 0.0f);
		(tail = new ModelRenderer(this, 13, 0)).addBox(-2.0f, -2.0f, 0.0f, 4, 4, 8);
		tail.setRotationPoint(0.0f, 4.0f, 8.0f);
		tail.setTextureSize(128, 64);
		tail.mirror = true;
		setRotation(tail, -0.7435722f, 0.0f, 0.0f);
		(Jaw_spike = new ModelRenderer(this, 6, 34)).addBox(-2.0f, -2.0f, -2.0f, 4, 3, 2);
		Jaw_spike.setRotationPoint(0.0f, 10.0f, -8.0f);
		Jaw_spike.setTextureSize(128, 64);
		Jaw_spike.mirror = true;
		setRotation(Jaw_spike, 0.0f, 0.0f, 0.0f);
		(Left_body_Plate = new ModelRenderer(this, 0, 40)).addBox(0.0f, -3.0f, -8.0f, 1, 7, 16);
		Left_body_Plate.setRotationPoint(6.0f, 6.0f, 1.0f);
		Left_body_Plate.setTextureSize(128, 64);
		Left_body_Plate.mirror = true;
		setRotation(Left_body_Plate, 0.0f, 0.0f, 0.0f);
		(Right_body_Plate = new ModelRenderer(this, 0, 40)).addBox(-1.0f, -3.0f, -8.0f, 1, 7, 16);
		Right_body_Plate.setRotationPoint(-6.0f, 6.0f, 1.0f);
		Right_body_Plate.setTextureSize(128, 64);
		Right_body_Plate.mirror = true;
		setRotation(Right_body_Plate, 0.0f, 0.0f, 0.0f);
		(Left_Top_body_Plate = new ModelRenderer(this, 36, 40)).addBox(-2.0f, -1.0f, 0.0f, 4, 1, 15);
		Left_Top_body_Plate.setRotationPoint(3.0f, 2.0f, -6.0f);
		Left_Top_body_Plate.setTextureSize(128, 64);
		Left_Top_body_Plate.mirror = true;
		setRotation(Left_Top_body_Plate, 0.0f, 0.0f, 0.0f);
		(Right_Top_body_Plate = new ModelRenderer(this, 36, 40)).addBox(-2.0f, -1.0f, 0.0f, 4, 1, 15);
		Right_Top_body_Plate.setRotationPoint(-3.0f, 2.0f, -6.0f);
		Right_Top_body_Plate.setTextureSize(128, 64);
		Right_Top_body_Plate.mirror = true;
		setRotation(Right_Top_body_Plate, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 25, 25)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head.setRotationPoint(0.0f, 4.0f, -8.0f);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 80, 35)).addBox(-6.0f, -10.0f, -7.0f, 12, 18, 10);
		body.setRotationPoint(0.0f, 5.0f, 2.0f);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
		leg1.setRotationPoint(-3.0f, 12.0f, 7.0f);
		leg1.setTextureSize(128, 64);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
		leg2.setRotationPoint(3.0f, 12.0f, 7.0f);
		leg2.setTextureSize(128, 64);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
		leg3.setRotationPoint(-3.0f, 12.0f, -5.0f);
		leg3.setTextureSize(128, 64);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
		leg4.setRotationPoint(3.0f, 12.0f, -5.0f);
		leg4.setTextureSize(128, 64);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(udders = new ModelRenderer(this, 52, 0)).addBox(-2.0f, -3.0f, 0.0f, 4, 5, 4);
		udders.setRotationPoint(0.0f, 16.0f, 4.0f);
		udders.setTextureSize(128, 64);
		udders.mirror = true;
		setRotation(udders, 1.570796f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float scale) {
		setRotationAngles(par2, par3, par4, par5, par6, scale, par1Entity);

		if (isChild) {
			GlStateManager.pushMatrix();
			GlStateManager.translate(0, 8 * scale, 4 * scale);
			head.render(scale);
			Jaw_spike.render(scale);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5f, 0.5f, 0.5f);
			GlStateManager.translate(0, 24 * scale, 0);
			Middle_Back_Spike.render(scale);
			Front_Back_Spike.render(scale);
			Last_Back_Spike.render(scale);
			tail.render(scale);
			Left_body_Plate.render(scale);
			Right_body_Plate.render(scale);
			Left_Top_body_Plate.render(scale);
			Right_Top_body_Plate.render(scale);
			body.render(scale);
			leg1.render(scale);
			leg2.render(scale);
			leg3.render(scale);
			leg4.render(scale);
			udders.render(scale);
			GlStateManager.popMatrix();
		}
		else {
			Middle_Back_Spike.render(scale);
			Front_Back_Spike.render(scale);
			Last_Back_Spike.render(scale);
			tail.render(scale);
			Jaw_spike.render(scale);
			Left_body_Plate.render(scale);
			Right_body_Plate.render(scale);
			Left_Top_body_Plate.render(scale);
			Right_Top_body_Plate.render(scale);
			head.render(scale);
			body.render(scale);
			leg1.render(scale);
			leg2.render(scale);
			leg3.render(scale);
			leg4.render(scale);
			udders.render(scale);
		}
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 79.57747f;
		head.rotateAngleX = par5 / 76.39437f;
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg1.rotateAngleY = 0.0f;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

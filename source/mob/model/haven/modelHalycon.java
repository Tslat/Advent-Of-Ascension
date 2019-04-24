package net.nevermine.mob.model.haven;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelHalycon extends ModelBase {
	ModelRenderer Right_Ear;
	ModelRenderer Left_Ear;
	ModelRenderer Middle_Back_Spike;
	ModelRenderer Front_Back_Spike;
	ModelRenderer Last_Back_Spike;
	ModelRenderer Tail;
	ModelRenderer Jaw_spike;
	ModelRenderer Left_Body_Plate;
	ModelRenderer Right_Body_Plate;
	ModelRenderer Left_Top_Body_Plate;
	ModelRenderer Right_Top_Body_Plate;
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer udders;

	public modelHalycon() {
		textureWidth = 128;
		textureHeight = 64;
		(Right_Ear = new ModelRenderer(this, 0, 0)).addBox(-1.0f, -1.0f, -1.0f, 2, 2, 2);
		Right_Ear.setRotationPoint(-5.0f, 1.0f, -11.0f);
		Right_Ear.setTextureSize(128, 64);
		Right_Ear.mirror = true;
		setRotation(Right_Ear, 0.0f, 0.0f, 0.0f);
		(Left_Ear = new ModelRenderer(this, 0, 0)).addBox(-1.0f, -1.0f, -1.0f, 2, 2, 2);
		Left_Ear.setRotationPoint(5.0f, 1.0f, -11.0f);
		Left_Ear.setTextureSize(128, 64);
		Left_Ear.mirror = true;
		setRotation(Left_Ear, 0.0f, 0.0f, 0.0f);
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
		(Tail = new ModelRenderer(this, 13, 0)).addBox(-2.0f, -2.0f, 0.0f, 4, 4, 8);
		Tail.setRotationPoint(0.0f, 4.0f, 8.0f);
		Tail.setTextureSize(128, 64);
		Tail.mirror = true;
		setRotation(Tail, -0.7435722f, 0.0f, 0.0f);
		(Jaw_spike = new ModelRenderer(this, 6, 34)).addBox(-2.0f, -2.0f, -2.0f, 4, 3, 2);
		Jaw_spike.setRotationPoint(0.0f, 10.0f, -8.0f);
		Jaw_spike.setTextureSize(128, 64);
		Jaw_spike.mirror = true;
		setRotation(Jaw_spike, 0.0f, 0.0f, 0.0f);
		(Left_Body_Plate = new ModelRenderer(this, 0, 40)).addBox(0.0f, -3.0f, -8.0f, 1, 7, 16);
		Left_Body_Plate.setRotationPoint(6.0f, 6.0f, 1.0f);
		Left_Body_Plate.setTextureSize(128, 64);
		Left_Body_Plate.mirror = true;
		setRotation(Left_Body_Plate, 0.0f, 0.0f, 0.0f);
		(Right_Body_Plate = new ModelRenderer(this, 0, 40)).addBox(-1.0f, -3.0f, -8.0f, 1, 7, 16);
		Right_Body_Plate.setRotationPoint(-6.0f, 6.0f, 1.0f);
		Right_Body_Plate.setTextureSize(128, 64);
		Right_Body_Plate.mirror = true;
		setRotation(Right_Body_Plate, 0.0f, 0.0f, 0.0f);
		(Left_Top_Body_Plate = new ModelRenderer(this, 36, 40)).addBox(-2.0f, -1.0f, 0.0f, 4, 1, 15);
		Left_Top_Body_Plate.setRotationPoint(3.0f, 2.0f, -6.0f);
		Left_Top_Body_Plate.setTextureSize(128, 64);
		Left_Top_Body_Plate.mirror = true;
		setRotation(Left_Top_Body_Plate, 0.0f, 0.0f, 0.0f);
		(Right_Top_Body_Plate = new ModelRenderer(this, 36, 40)).addBox(-2.0f, -1.0f, 0.0f, 4, 1, 15);
		Right_Top_Body_Plate.setRotationPoint(-3.0f, 2.0f, -6.0f);
		Right_Top_Body_Plate.setTextureSize(128, 64);
		Right_Top_Body_Plate.mirror = true;
		setRotation(Right_Top_Body_Plate, 0.0f, 0.0f, 0.0f);
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

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Middle_Back_Spike.render(par7);
		Front_Back_Spike.render(par7);
		Last_Back_Spike.render(par7);
		Tail.render(par7);
		Jaw_spike.render(par7);
		Left_Body_Plate.render(par7);
		Right_Body_Plate.render(par7);
		Left_Top_Body_Plate.render(par7);
		Right_Top_Body_Plate.render(par7);
		head.render(par7);
		body.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		udders.render(par7);
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

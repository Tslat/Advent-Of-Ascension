package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelUrsa extends ModelBase {
	private ModelRenderer Torso;
	private ModelRenderer body;
	private ModelRenderer Left_Back_Quad;
	private ModelRenderer Right_Back_Quad;
	private ModelRenderer Left_Back_Leg;
	private ModelRenderer Right_Back_Leg;
	private ModelRenderer Left_Front_Quad;
	private ModelRenderer Right_Front_Quad;
	private ModelRenderer Left_Front_Leg;
	private ModelRenderer Right_Front_Leg;
	private ModelRenderer tail;
	private ModelRenderer head;
	private ModelRenderer Mouth;
	private ModelRenderer leftEar;
	private ModelRenderer rightEar;

	public ModelUrsa() {
		textureWidth = 128;
		textureHeight = 64;
		(Torso = new ModelRenderer(this, 0, 0)).addBox(-6.0f, -6.0f, -13.0f, 12, 12, 12);
		Torso.setRotationPoint(0.0f, 6.0f, 1.0f);
		Torso.setTextureSize(64, 32);
		Torso.mirror = true;
		setRotation(Torso, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 49, 0)).addBox(-5.0f, -5.0f, 0.0f, 10, 10, 10);
		body.setRotationPoint(0.0f, 7.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(Left_Back_Quad = new ModelRenderer(this, 49, 20)).addBox(0.0f, -3.0f, -3.0f, 6, 6, 6);
		Left_Back_Quad.setRotationPoint(4.0f, 8.0f, 5.0f);
		Left_Back_Quad.setTextureSize(64, 32);
		Left_Back_Quad.mirror = true;
		setRotation(Left_Back_Quad, 0.0f, 0.0f, 0.0f);
		(Right_Back_Quad = new ModelRenderer(this, 49, 20)).addBox(-6.0f, -3.0f, -3.0f, 6, 6, 6);
		Right_Back_Quad.setRotationPoint(-4.0f, 8.0f, 5.0f);
		Right_Back_Quad.setTextureSize(64, 32);
		Right_Back_Quad.mirror = true;
		setRotation(Right_Back_Quad, 0.0f, 0.0f, 0.0f);
		(Left_Back_Leg = new ModelRenderer(this, 111, 17)).addBox(0.0f, 3.0f, -2.0f, 4, 14, 4);
		Left_Back_Leg.setRotationPoint(5.0f, 7.0f, 5.0f);
		Left_Back_Leg.setTextureSize(64, 32);
		Left_Back_Leg.mirror = true;
		setRotation(Left_Back_Leg, 0.0f, 0.0f, 0.0f);
		(Right_Back_Leg = new ModelRenderer(this, 111, 17)).addBox(-4.0f, 3.0f, -2.0f, 4, 14, 4);
		Right_Back_Leg.setRotationPoint(-5.0f, 7.0f, 4.066667f);
		Right_Back_Leg.setTextureSize(64, 32);
		Right_Back_Leg.mirror = true;
		setRotation(Right_Back_Leg, 0.0f, 0.0f, 0.0f);
		(Left_Front_Quad = new ModelRenderer(this, 90, 0)).addBox(0.0f, -4.0f, -4.0f, 6, 8, 8);
		Left_Front_Quad.setRotationPoint(5.0f, 7.0f, -7.0f);
		Left_Front_Quad.setTextureSize(64, 32);
		Left_Front_Quad.mirror = true;
		setRotation(Left_Front_Quad, 0.0f, 0.0f, 0.0f);
		(Right_Front_Quad = new ModelRenderer(this, 90, 0)).addBox(-6.0f, -4.0f, -4.0f, 6, 8, 8);
		Right_Front_Quad.setRotationPoint(-5.0f, 7.0f, -7.0f);
		Right_Front_Quad.setTextureSize(64, 32);
		Right_Front_Quad.mirror = true;
		setRotation(Right_Front_Quad, 0.0f, 0.0f, 0.0f);
		(Left_Front_Leg = new ModelRenderer(this, 0, 25)).addBox(0.0f, 3.0f, -2.0f, 4, 14, 4);
		Left_Front_Leg.setRotationPoint(6.0f, 8.0f, -7.0f);
		Left_Front_Leg.setTextureSize(64, 32);
		Left_Front_Leg.mirror = true;
		setRotation(Left_Front_Leg, 0.0f, 0.0f, 0.0f);
		(Right_Front_Leg = new ModelRenderer(this, 0, 25)).addBox(-4.0f, 3.0f, -2.0f, 4, 14, 4);
		Right_Front_Leg.setRotationPoint(-6.0f, 7.0f, -7.0f);
		Right_Front_Leg.setTextureSize(64, 32);
		Right_Front_Leg.mirror = true;
		setRotation(Right_Front_Leg, 0.0f, 0.0f, 0.0f);
		(tail = new ModelRenderer(this, 49, 33)).addBox(-2.0f, -2.0f, 0.0f, 4, 4, 13);
		tail.setRotationPoint(0.0f, 4.0f, 8.0f);
		tail.setTextureSize(64, 32);
		tail.mirror = true;
		setRotation(tail, -0.7435722f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 17, 25)).addBox(-4.0f, -4.0f, -6.0f, 8, 9, 7);
		head.setRotationPoint(0.0f, 5.0f, -13.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(Mouth = new ModelRenderer(this, 0, 44)).addBox(-3.0f, -2.0f, -5.0f, 6, 4, 4);
		Mouth.setRotationPoint(0.0f, 7.0f, -18.0f);
		Mouth.setTextureSize(64, 32);
		Mouth.mirror = true;
		setRotation(Mouth, 0.0f, 0.0f, 0.0f);
		(leftEar = new ModelRenderer(this, 21, 44)).addBox(-1.0f, -3.0f, -1.0f, 3, 3, 2);
		leftEar.setRotationPoint(3.0f, 2.0f, -14.0f);
		leftEar.setTextureSize(64, 32);
		leftEar.mirror = true;
		setRotation(leftEar, 0.0f, 0.0f, 0.0f);
		(rightEar = new ModelRenderer(this, 21, 44)).addBox(-2.0f, -3.0f, -1.0f, 3, 3, 2);
		rightEar.setRotationPoint(-3.0f, 2.0f, -14.0f);
		rightEar.setTextureSize(64, 32);
		rightEar.mirror = true;
		setRotation(rightEar, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Torso.render(par7);
		body.render(par7);
		Left_Back_Quad.render(par7);
		Right_Back_Quad.render(par7);
		Left_Back_Leg.render(par7);
		Right_Back_Leg.render(par7);
		Left_Front_Quad.render(par7);
		Right_Front_Quad.render(par7);
		Left_Front_Leg.render(par7);
		Right_Front_Leg.render(par7);
		tail.render(par7);
		head.render(par7);
		Mouth.render(par7);
		leftEar.render(par7);
		rightEar.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		Left_Front_Leg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		Left_Front_Leg.rotateAngleY = 0.0f;
		Left_Front_Quad.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		Left_Front_Quad.rotateAngleY = 0.0f;
		Left_Back_Leg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		Left_Back_Leg.rotateAngleY = 0.0f;
		Left_Back_Quad.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		Left_Back_Quad.rotateAngleY = 0.0f;
		Right_Front_Leg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		Right_Front_Leg.rotateAngleY = 0.0f;
		Right_Front_Quad.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		Right_Front_Quad.rotateAngleY = 0.0f;
		Right_Back_Leg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		Right_Back_Leg.rotateAngleY = 0.0f;
		Right_Back_Quad.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		Right_Back_Quad.rotateAngleY = 0.0f;
	}
}

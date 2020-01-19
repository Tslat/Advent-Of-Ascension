package net.tslat.aoa3.client.model.entities.mobs.haven;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelAngelica extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer wingL;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer body;
	private ModelRenderer wingL2;
	private ModelRenderer wingR;
	private ModelRenderer wingR2;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer body4;
	private ModelRenderer body5;
	private ModelRenderer body6;

	public ModelAngelica() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 0.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(wingL = new ModelRenderer(this, 23, 41)).addBox(1.0f, 2.0f, -1.0f, 10, 17, 0);
		wingL.setRotationPoint(1.0f, 1.0f, 5.0f);
		wingL.setTextureSize(64, 64);
		wingL.mirror = true;
		setRotation(wingL, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm.setTextureSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm.setTextureSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 8, 17)).addBox(2.0f, 12.0f, 1.0f, 2, 9, 1);
		body.setRotationPoint(0.0f, 0.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 1, 33)).addBox(1.0f, 0.0f, -2.0f, 10, 2, 2);
		wingL2.setRotationPoint(1.0f, 1.0f, 5.0f);
		wingL2.setTextureSize(64, 64);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
		(wingR = new ModelRenderer(this, 1, 41)).addBox(-11.0f, 2.0f, -1.0f, 10, 17, 0);
		wingR.setRotationPoint(-1.0f, 1.0f, 5.0f);
		wingR.setTextureSize(64, 64);
		wingR.mirror = true;
		setRotation(wingR, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 1, 33)).addBox(-11.0f, 0.0f, -2.0f, 10, 2, 2);
		wingR2.setRotationPoint(-1.0f, 1.0f, 5.0f);
		wingR2.setTextureSize(64, 64);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 35, 3)).addBox(-4.0f, 0.0f, 2.0f, 8, 4, 4);
		body2.setRotationPoint(0.0f, 0.0f, 0.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 8, 17)).addBox(-4.0f, 12.0f, 1.0f, 2, 9, 1);
		body3.setRotationPoint(0.0f, 0.0f, 0.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 1, 17)).addBox(2.0f, 12.0f, -2.0f, 2, 5, 1);
		body4.setRotationPoint(0.0f, 0.0f, 0.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 1, 17)).addBox(-4.0f, 12.0f, -2.0f, 2, 5, 1);
		body5.setRotationPoint(0.0f, 0.0f, 0.0f);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body6.setRotationPoint(0.0f, 0.0f, 0.0f);
		body6.setTextureSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		wingL.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		body.render(par7);
		wingL2.render(par7);
		wingR.render(par7);
		wingR2.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		wingR.rotateAngleY = MathHelper.cos(par3 * 0.3f) * 3.1415927f * 0.15f;
		wingL.rotateAngleY = -wingR.rotateAngleY;
		wingR2.rotateAngleY = MathHelper.cos(par3 * 0.3f) * 3.1415927f * 0.15f;
		wingL2.rotateAngleY = -wingR.rotateAngleY;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.mysterium;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFungung extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer body2;
	private ModelRenderer head2;
	private ModelRenderer body3;
	private ModelRenderer body4;
	private ModelRenderer body5;
	private ModelRenderer body6;
	private ModelRenderer leftArm2;
	private ModelRenderer rightArm2;
	private ModelRenderer rightArm3;
	private ModelRenderer leftArm3;

	public ModelFungung() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 16)).addBox(-6.0f, -16.0f, -6.0f, 12, 8, 12);
		head.setRotationPoint(0.0f, 5.0f, -4.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 47, 4)).addBox(0.0f, -6.0f, 8.0f, 4, 3, 4);
		body.setRotationPoint(0.0f, 17.0f, -3.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 26, 8)).addBox(-9.0f, 2.0f, -2.0f, 3, 4, 4);
		rightArm.setRotationPoint(-5.0f, 6.0f, -3.0f);
		rightArm.setTextureSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 26, 0)).addBox(6.0f, 2.0f, -2.0f, 3, 4, 4);
		leftArm.setRotationPoint(5.0f, 6.0f, -3.0f);
		leftArm.setTextureSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 39, 37)).addBox(-4.0f, 0.0f, -2.0f, 8, 14, 4);
		body2.setRotationPoint(0.0f, 3.0f, -3.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -8.0f, -3.0f, 6, 8, 6);
		head2.setRotationPoint(0.0f, 5.0f, -4.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 3, 42)).addBox(-5.0f, 0.0f, -2.0f, 10, 7, 14);
		body3.setRotationPoint(0.0f, 17.0f, -3.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 47, 21)).addBox(1.0f, -3.0f, 9.0f, 2, 3, 2);
		body4.setRotationPoint(0.0f, 17.0f, -3.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 47, 13)).addBox(-5.0f, -6.0f, 4.0f, 4, 3, 4);
		body5.setRotationPoint(0.0f, 17.0f, -3.0f);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 47, 21)).addBox(-4.0f, -3.0f, 5.0f, 2, 3, 2);
		body6.setRotationPoint(0.0f, 17.0f, -3.0f);
		body6.setTextureSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 0, 37)).addBox(-1.0f, -2.0f, -2.0f, 4, 13, 4);
		leftArm2.setRotationPoint(5.0f, 6.0f, -3.0f);
		leftArm2.setTextureSize(64, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 0, 37)).addBox(-3.0f, -2.0f, -2.0f, 4, 13, 4);
		rightArm2.setRotationPoint(-5.0f, 6.0f, -3.0f);
		rightArm2.setTextureSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 18, 37)).addBox(-6.0f, 3.0f, -1.0f, 3, 2, 2);
		rightArm3.setRotationPoint(-5.0f, 6.0f, -3.0f);
		rightArm3.setTextureSize(64, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new ModelRenderer(this, 18, 37)).addBox(3.0f, 3.0f, -1.0f, 3, 2, 2);
		leftArm3.setRotationPoint(5.0f, 6.0f, -3.0f);
		leftArm3.setTextureSize(64, 64);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		body2.render(par7);
		head2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
		leftArm2.render(par7);
		rightArm2.render(par7);
		rightArm3.render(par7);
		leftArm3.render(par7);
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
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		rightArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm3.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		leftArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm2.rotateAngleZ = 0.0f;
		leftArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm3.rotateAngleZ = 0.0f;
	}
}

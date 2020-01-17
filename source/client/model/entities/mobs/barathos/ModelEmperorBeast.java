package net.tslat.aoa3.client.model.entities.mobs.barathos;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEmperorBeast extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer leftLeg2;
	private ModelRenderer rightLeg2;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer head2;
	private ModelRenderer head3;

	public ModelEmperorBeast() {
		textureWidth = 128;
		textureHeight = 64;
		(head = new ModelRenderer(this, 41, 18)).addBox(-10.0f, -21.0f, 0.0f, 20, 3, 8);
		head.setRotationPoint(0.0f, -11.0f, 0.0f);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, -0.1745329f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 90, 46)).addBox(-3.0f, 17.0f, 15.0f, 6, 6, 12);
		body.setRotationPoint(0.0f, -11.0f, -3.0f);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 83, 2)).addBox(-5.0f, 13.0f, -11.0f, 10, 4, 12);
		rightLeg.setRotationPoint(-7.0f, 7.0f, 6.0f);
		rightLeg.setTextureSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 83, 2)).addBox(-5.0f, 13.0f, -11.0f, 10, 4, 12);
		leftLeg.setRotationPoint(7.0f, 7.0f, 6.0f);
		leftLeg.setTextureSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 100, 22)).addBox(-3.0f, 0.0f, -4.0f, 6, 15, 8);
		leftLeg2.setRotationPoint(7.0f, 7.0f, 6.0f);
		leftLeg2.setTextureSize(128, 64);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, -0.3490659f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 100, 22)).addBox(-3.0f, 0.0f, -4.0f, 6, 15, 8);
		rightLeg2.setRotationPoint(-7.0f, 7.0f, 6.0f);
		rightLeg2.setTextureSize(128, 64);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, -0.3490659f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 24, 29)).addBox(-4.0f, 0.0f, -2.0f, 8, 23, 12);
		body2.setRotationPoint(0.0f, -11.0f, -3.0f);
		body2.setTextureSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 67, 29)).addBox(-4.0f, 11.0f, 10.0f, 8, 12, 8);
		body3.setRotationPoint(0.0f, -11.0f, -3.0f);
		body3.setTextureSize(128, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 28)).addBox(-2.0f, -20.0f, -4.0f, 4, 8, 6);
		head2.setRotationPoint(0.0f, -11.0f, 0.0f);
		head2.setTextureSize(128, 64);
		head2.mirror = true;
		setRotation(head2, -0.4537856f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -13.0f, -4.0f, 8, 13, 13);
		head3.setRotationPoint(0.0f, -11.0f, 0.0f);
		head3.setTextureSize(128, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		leftLeg2.render(par7);
		rightLeg2.render(par7);
		body2.render(par7);
		body3.render(par7);
		head2.render(par7);
		head3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		rightLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2 - 0.349f;
		rightLeg2.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2 - 0.349f;
	}
}

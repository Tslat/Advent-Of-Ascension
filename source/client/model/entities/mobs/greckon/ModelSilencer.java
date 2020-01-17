package net.tslat.aoa3.client.model.entities.mobs.greckon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSilencer extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
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
	private ModelRenderer leftArm6;
	private ModelRenderer rightArm6;
	private ModelRenderer leftArm7;
	private ModelRenderer rightArm7;

	public ModelSilencer() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 0.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, 0.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 100, 22)).addBox(-8.0f, -12.0f, -1.0f, 2, 3, 2);
		rightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 100, 22)).addBox(6.0f, -12.0f, -1.0f, 2, 3, 2);
		leftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setRotationPoint(-2.0f, 12.0f, 0.0f);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setRotationPoint(2.0f, 12.0f, 0.0f);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 43, 11)).addBox(-5.0f, -2.0f, -2.0f, 6, 4, 4);
		rightArm2.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm2.setTextureSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 43, 11)).addBox(-1.0f, -2.0f, -2.0f, 6, 4, 4);
		leftArm2.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm2.setTextureSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new ModelRenderer(this, 63, 22)).addBox(5.0f, -4.0f, -2.0f, 4, 6, 4);
		leftArm3.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm3.setTextureSize(64, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 63, 22)).addBox(-9.0f, -4.0f, -2.0f, 4, 6, 4);
		rightArm3.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm3.setTextureSize(64, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm4 = new ModelRenderer(this, 36, 0)).addBox(5.5f, -8.0f, -2.0f, 3, 3, 1);
		leftArm4.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm4.setTextureSize(64, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(rightArm4 = new ModelRenderer(this, 36, 0)).addBox(-8.5f, -8.0f, -2.0f, 3, 3, 1);
		rightArm4.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm4.setTextureSize(64, 32);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.0f, 0.0f, 0.0f);
		(leftArm5 = new ModelRenderer(this, 115, 22)).addBox(4.0f, -12.0f, -1.0f, 1, 3, 2);
		leftArm5.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm5.setTextureSize(64, 32);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, 0.0f);
		(rightArm5 = new ModelRenderer(this, 115, 22)).addBox(-10.0f, -12.0f, -1.0f, 1, 3, 2);
		rightArm5.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm5.setTextureSize(64, 32);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.0f, 0.0f, 0.0f);
		(leftArm6 = new ModelRenderer(this, 115, 22)).addBox(9.0f, -12.0f, -1.0f, 1, 3, 2);
		leftArm6.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm6.setTextureSize(64, 32);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, 0.0f);
		(rightArm6 = new ModelRenderer(this, 115, 22)).addBox(-5.0f, -12.0f, -1.0f, 1, 3, 2);
		rightArm6.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm6.setTextureSize(64, 32);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.0f, 0.0f, 0.0f);
		(leftArm7 = new ModelRenderer(this, 81, 22)).addBox(4.0f, -9.0f, -1.0f, 6, 5, 2);
		leftArm7.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm7.setTextureSize(64, 32);
		leftArm7.mirror = true;
		setRotation(leftArm7, 0.0f, 0.0f, 0.0f);
		(rightArm7 = new ModelRenderer(this, 81, 22)).addBox(-10.0f, -9.0f, -1.0f, 6, 5, 2);
		rightArm7.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm7.setTextureSize(64, 32);
		rightArm7.mirror = true;
		setRotation(rightArm7, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
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
		leftArm6.render(par7);
		rightArm6.render(par7);
		leftArm7.render(par7);
		rightArm7.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
	}
}

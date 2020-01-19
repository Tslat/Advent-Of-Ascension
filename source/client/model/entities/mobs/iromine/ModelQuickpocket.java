package net.tslat.aoa3.client.model.entities.mobs.iromine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelQuickpocket extends ModelBase {
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer rightArm2;
	private ModelRenderer leftArm2;
	private ModelRenderer head2;
	private ModelRenderer head3;
	private ModelRenderer head4;
	private ModelRenderer head5;
	private ModelRenderer head6;
	private ModelRenderer head7;
	private ModelRenderer head8;
	private ModelRenderer head9;
	private ModelRenderer head10;

	public ModelQuickpocket() {
		textureWidth = 64;
		textureHeight = 32;
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 6, 8, 2);
		body.setRotationPoint(1.0f, 8.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 16)).addBox(0.0f, -2.0f, -2.0f, 2, 2, 2);
		rightArm.setRotationPoint(-5.0f, 10.0f, 0.0f);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 16)).addBox(-2.0f, -2.0f, -2.0f, 2, 2, 2);
		leftArm.setRotationPoint(5.0f, 10.0f, 0.0f);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 2, 8, 2);
		rightLeg.setRotationPoint(-2.0f, 16.0f, 0.0f);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 2, 8, 2);
		leftLeg.setRotationPoint(2.0f, 16.0f, 0.0f);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 16)).addBox(-2.0f, -1.0f, -2.0f, 2, 12, 2);
		rightArm2.setRotationPoint(-4.0f, 11.0f, 0.0f);
		rightArm2.setTextureSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 40, 16)).addBox(0.0f, -1.0f, -2.0f, 2, 12, 2);
		leftArm2.setRotationPoint(4.0f, 11.0f, 0.0f);
		leftArm2.setTextureSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 9)).addBox(1.0f, 0.0f, -3.0f, 1, 2, 1);
		head2.setRotationPoint(0.0f, 8.0f, -1.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 17, 0)).addBox(-3.0f, -4.0f, 5.0f, 1, 1, 1);
		head3.setRotationPoint(0.0f, 8.0f, -1.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.7853982f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 17, 0)).addBox(2.0f, -4.0f, 5.0f, 1, 1, 1);
		head4.setRotationPoint(0.0f, 8.0f, -1.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.7853982f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 17, 0)).addBox(-3.0f, -3.0f, 2.0f, 1, 2, 4);
		head5.setRotationPoint(0.0f, 8.0f, -1.0f);
		head5.setTextureSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.7853982f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 17, 0)).addBox(2.0f, -3.0f, 2.0f, 1, 2, 4);
		head6.setRotationPoint(0.0f, 8.0f, -1.0f);
		head6.setTextureSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.7853982f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 17, 0)).addBox(-3.0f, -4.0f, 3.0f, 1, 1, 1);
		head7.setRotationPoint(0.0f, 8.0f, -1.0f);
		head7.setTextureSize(64, 32);
		head7.mirror = true;
		setRotation(head7, 0.7853982f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 17, 0)).addBox(2.0f, -4.0f, 3.0f, 1, 1, 1);
		head8.setRotationPoint(0.0f, 8.0f, -1.0f);
		head8.setTextureSize(64, 32);
		head8.mirror = true;
		setRotation(head8, 0.7853982f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, -4.0f, -3.0f, 4, 4, 4);
		head9.setRotationPoint(0.0f, 8.0f, -1.0f);
		head9.setTextureSize(64, 32);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 0, 9)).addBox(-2.0f, 0.0f, -3.0f, 1, 2, 1);
		head10.setRotationPoint(0.0f, 8.0f, -1.0f);
		head10.setTextureSize(64, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		rightArm2.render(par7);
		leftArm2.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
		head7.render(par7);
		head8.render(par7);
		head9.render(par7);
		head10.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head2.rotateAngleY = par4 / (float)(180f / Math.PI);
		head3.rotateAngleY = par4 / (float)(180f / Math.PI);
		head4.rotateAngleY = par4 / (float)(180f / Math.PI);
		head5.rotateAngleY = par4 / (float)(180f / Math.PI);
		head6.rotateAngleY = par4 / (float)(180f / Math.PI);
		head7.rotateAngleY = par4 / (float)(180f / Math.PI);
		head8.rotateAngleY = par4 / (float)(180f / Math.PI);
		head9.rotateAngleY = par4 / (float)(180f / Math.PI);
		head10.rotateAngleY = par4 / (float)(180f / Math.PI);
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		leftArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm2.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

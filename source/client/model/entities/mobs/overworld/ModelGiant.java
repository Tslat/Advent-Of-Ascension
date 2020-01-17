package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelGiant extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer leftLeg2;
	private ModelRenderer rightLeg2;
	private ModelRenderer leftLeg3;
	private ModelRenderer rightLeg3;
	private ModelRenderer rightArm2;
	private ModelRenderer leftArm;
	private ModelRenderer leftArm2;

	public ModelGiant() {
		textureWidth = 128;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -14.0f, -4.0f, 10, 14, 8);
		head.setRotationPoint(0.0f, -20.0f, 0.0f);
		head.setTextureSize(128, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 0, 35)).addBox(-6.0f, 0.0f, -2.0f, 12, 18, 10);
		body.setRotationPoint(0.0f, -20.0f, -3.0f);
		body.setTextureSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 36, 2)).addBox(-18.0f, -6.0f, -6.0f, 19, 10, 10);
		rightArm.setRotationPoint(-6.0f, -17.0f, 0.0f);
		rightArm.setTextureSize(128, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 96, 0)).addBox(-4.0f, -5.0f, -3.0f, 8, 8, 8);
		rightLeg.setRotationPoint(-7.0f, -2.0f, -1.0f);
		rightLeg.setTextureSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 96, 0)).addBox(-4.0f, -5.0f, -3.0f, 8, 8, 8);
		leftLeg.setRotationPoint(7.0f, -2.0f, -1.0f);
		leftLeg.setTextureSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 80, 39)).addBox(-6.0f, 14.0f, -5.0f, 12, 12, 12);
		leftLeg2.setRotationPoint(7.0f, -2.0f, -1.0f);
		leftLeg2.setTextureSize(128, 64);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 80, 39)).addBox(-6.0f, 14.0f, -5.0f, 12, 12, 12);
		rightLeg2.setRotationPoint(-7.0f, -2.0f, -1.0f);
		rightLeg2.setTextureSize(128, 64);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg3 = new ModelRenderer(this, 87, 17)).addBox(-5.0f, 3.0f, -4.0f, 10, 11, 10);
		leftLeg3.setRotationPoint(7.0f, -2.0f, -1.0f);
		leftLeg3.setTextureSize(128, 64);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.0f, 0.0f, 0.0f);
		(rightLeg3 = new ModelRenderer(this, 87, 17)).addBox(-5.0f, 3.0f, -4.0f, 10, 11, 10);
		rightLeg3.setRotationPoint(-7.0f, -2.0f, -1.0f);
		rightLeg3.setTextureSize(128, 64);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 46, 26)).addBox(-17.0f, 4.0f, -5.0f, 8, 30, 8);
		rightArm2.setRotationPoint(-6.0f, -17.0f, 0.0f);
		rightArm2.setTextureSize(128, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 36, 2)).addBox(-1.0f, -6.0f, -6.0f, 19, 10, 10);
		leftArm.setRotationPoint(6.0f, -17.0f, 0.0f);
		leftArm.setTextureSize(128, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 46, 26)).addBox(8.0f, 4.0f, -5.0f, 8, 30, 8);
		leftArm2.setRotationPoint(6.0f, -17.0f, 0.0f);
		leftArm2.setTextureSize(128, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, 771);
		head.render(par7);
		body.render(par7);
		rightArm.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		leftLeg2.render(par7);
		rightLeg2.render(par7);
		leftLeg3.render(par7);
		rightLeg3.render(par7);
		rightArm2.render(par7);
		leftArm.render(par7);
		leftArm2.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
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
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		leftArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm2.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		rightLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg2.rotateAngleY = 0.0f;
		rightLeg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg3.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLeg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

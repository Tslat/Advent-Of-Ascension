package net.tslat.aoa3.client.model.entities.mobs.immortallis;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelFenix extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer rightLeg2;
	private ModelRenderer leftLeg2;
	private ModelRenderer leftLeg3;
	private ModelRenderer rightLeg3;
	private ModelRenderer leftLeg4;
	private ModelRenderer rightLeg4;
	private ModelRenderer rightArm2;
	private ModelRenderer leftArm2;
	private ModelRenderer rightArm3;
	private ModelRenderer leftArm3;

	public ModelFenix() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -5.0f, -4.0f, 8, 5, 7);
		head.setRotationPoint(0.0f, 0.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, 0.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 57, 16)).addBox(-0.5f, 6.0f, -7.0f, 1, 12, 1);
		rightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 57, 16)).addBox(1.5f, 6.0f, -7.0f, 1, 12, 1);
		leftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 13, 33)).addBox(0.5f, 12.0f, -4.5f, 1, 1, 3);
		rightLeg.setRotationPoint(-2.0f, 11.0f, 0.0f);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 13, 33)).addBox(0.5f, 12.0f, -4.5f, 1, 1, 3);
		leftLeg.setRotationPoint(2.0f, 11.0f, 0.0f);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 7, 4);
		rightLeg2.setRotationPoint(-2.0f, 11.0f, 0.0f);
		rightLeg2.setTextureSize(64, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, -0.4363323f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 7, 4);
		leftLeg2.setRotationPoint(2.0f, 11.0f, 0.0f);
		leftLeg2.setTextureSize(64, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, -0.4363323f, 0.0f, 0.0f);
		(leftLeg3 = new ModelRenderer(this, 0, 28)).addBox(-1.5f, 3.0f, -6.5f, 3, 8, 3);
		leftLeg3.setRotationPoint(2.0f, 11.0f, 0.0f);
		leftLeg3.setTextureSize(64, 32);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.4363323f, 0.0f, 0.0f);
		(rightLeg3 = new ModelRenderer(this, 0, 28)).addBox(-1.5f, 3.0f, -6.5f, 3, 8, 3);
		rightLeg3.setRotationPoint(-2.0f, 11.0f, 0.0f);
		rightLeg3.setTextureSize(64, 32);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.4363323f, 0.0f, 0.0f);
		(leftLeg4 = new ModelRenderer(this, 13, 33)).addBox(-1.5f, 12.0f, -4.5f, 1, 1, 3);
		leftLeg4.setRotationPoint(2.0f, 11.0f, 0.0f);
		leftLeg4.setTextureSize(64, 32);
		leftLeg4.mirror = true;
		setRotation(leftLeg4, 0.0f, 0.0f, 0.0f);
		(rightLeg4 = new ModelRenderer(this, 13, 33)).addBox(-1.5f, 12.0f, -4.5f, 1, 1, 3);
		rightLeg4.setRotationPoint(-2.0f, 11.0f, 0.0f);
		rightLeg4.setTextureSize(64, 32);
		rightLeg4.mirror = true;
		setRotation(rightLeg4, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm2.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm2.setTextureSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, -0.6981317f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm2.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm2.setTextureSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, -0.6981317f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 57, 16)).addBox(-2.5f, 6.0f, -7.0f, 1, 12, 1);
		rightArm3.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm3.setTextureSize(64, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new ModelRenderer(this, 57, 16)).addBox(-0.5f, 6.0f, -7.0f, 1, 12, 1);
		leftArm3.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm3.setTextureSize(64, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, 771);
		head.render(par7);
		body.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		rightLeg2.render(par7);
		leftLeg2.render(par7);
		leftLeg3.render(par7);
		rightLeg3.render(par7);
		leftLeg4.render(par7);
		rightLeg4.render(par7);
		rightArm2.render(par7);
		leftArm2.render(par7);
		rightArm3.render(par7);
		leftArm3.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		rightLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2 - 0.436f;
		rightLeg2.rotateAngleY = 0.0f;
		rightLeg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2 + 0.436f;
		rightLeg3.rotateAngleY = 0.0f;
		rightLeg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg4.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2 - 0.436f;
		leftLeg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2 + 0.436f;
		leftLeg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

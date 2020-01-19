package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelGhostlyNightReaper extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer body2;
	private ModelRenderer rightArm2;
	private ModelRenderer rightArm3;
	private ModelRenderer rightArm4;
	private ModelRenderer rightArm5;
	private ModelRenderer rightArm6;
	private ModelRenderer rightArm7;
	private ModelRenderer rightArm8;

	public ModelGhostlyNightReaper() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, -6.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 38)).addBox(-5.0f, 0.0f, -2.0f, 10, 13, 4);
		body.setRotationPoint(0.0f, 10.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -1.0f, -11.0f, 2, 3, 1);
		rightArm.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm.setTextureSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.3490659f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 37, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 16, 4);
		leftArm.setRotationPoint(5.0f, -4.0f, 0.0f);
		leftArm.setTextureSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 12, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 16, 4);
		body2.setRotationPoint(0.0f, -6.0f, 0.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 37, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 16, 4);
		rightArm2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm2.setTextureSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 55, 16)).addBox(-2.0f, -10.0f, -7.0f, 2, 36, 2);
		rightArm3.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm3.setTextureSize(64, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.3490659f, 0.0f, 0.0f);
		(rightArm4 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -8.0f, -9.0f, 2, 3, 2);
		rightArm4.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm4.setTextureSize(64, 64);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.3490659f, 0.0f, 0.0f);
		(rightArm5 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -7.0f, -11.0f, 2, 3, 2);
		rightArm5.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm5.setTextureSize(64, 64);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.3490659f, 0.0f, 0.0f);
		(rightArm6 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -6.0f, -13.0f, 2, 3, 2);
		rightArm6.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm6.setTextureSize(64, 64);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.3490659f, 0.0f, 0.0f);
		(rightArm7 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -4.0f, -14.0f, 2, 4, 1);
		rightArm7.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm7.setTextureSize(64, 64);
		rightArm7.mirror = true;
		setRotation(rightArm7, 0.3490659f, 0.0f, 0.0f);
		(rightArm8 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -3.0f, -13.0f, 2, 4, 2);
		rightArm8.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightArm8.setTextureSize(64, 64);
		rightArm8.mirror = true;
		setRotation(rightArm8, 0.3490659f, 0.0f, 0.0f);
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
		body2.render(par7);
		rightArm2.render(par7);
		rightArm3.render(par7);
		rightArm4.render(par7);
		rightArm5.render(par7);
		rightArm6.render(par7);
		rightArm7.render(par7);
		rightArm8.render(par7);
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
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightArm.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		rightArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightArm3.rotateAngleZ = 0.0f;
		rightArm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightArm4.rotateAngleZ = 0.0f;
		rightArm5.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightArm5.rotateAngleZ = 0.0f;
		rightArm6.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightArm6.rotateAngleZ = 0.0f;
		rightArm7.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightArm7.rotateAngleZ = 0.0f;
		rightArm8.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightArm8.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
	}
}

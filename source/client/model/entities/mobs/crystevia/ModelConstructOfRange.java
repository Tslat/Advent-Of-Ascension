package net.tslat.aoa3.client.model.entities.mobs.crystevia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelConstructOfRange extends ModelBase {
	private ModelRenderer r4;
	private ModelRenderer leftArm;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer rightArm;
	private ModelRenderer rightArm2;
	private ModelRenderer rightArm3;
	private ModelRenderer body;
	private ModelRenderer r1;
	private ModelRenderer r2;
	private ModelRenderer r3;

	public ModelConstructOfRange() {
		textureWidth = 64;
		textureHeight = 64;
		(r4 = new ModelRenderer(this, 33, 38)).addBox(6.0f, -6.0f, 4.0f, 3, 12, 3);
		r4.setRotationPoint(0.0f, 0.0f, 0.0f);
		r4.setTextureSize(64, 64);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 1)).addBox(-2.0f, -2.0f, -3.0f, 6, 14, 6);
		leftArm.setRotationPoint(9.0f, 2.0f, 0.0f);
		leftArm.setTextureSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 20)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		rightLeg.setRotationPoint(-3.0f, 14.0f, 0.0f);
		rightLeg.setTextureSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 20)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		leftLeg.setRotationPoint(3.0f, 14.0f, 0.0f);
		leftLeg.setTextureSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 28, 22)).addBox(-4.0f, -9.0f, -11.0f, 6, 4, 6);
		rightArm.setRotationPoint(-9.0f, 2.0f, 0.0f);
		rightArm.setTextureSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.5235988f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 1)).addBox(-4.0f, -2.0f, -3.0f, 6, 14, 6);
		rightArm2.setRotationPoint(-9.0f, 2.0f, 0.0f);
		rightArm2.setTextureSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 55, 22)).addBox(-2.0f, -5.0f, -9.0f, 2, 20, 2);
		rightArm3.setRotationPoint(-9.0f, 2.0f, 0.0f);
		rightArm3.setTextureSize(64, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.5235988f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 0, 1)).addBox(-6.0f, 0.0f, -3.0f, 12, 12, 6);
		body.setRotationPoint(0.0f, 0.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 0, 38)).addBox(-6.0f, -9.0f, 4.0f, 12, 3, 3);
		r1.setRotationPoint(0.0f, 0.0f, 0.0f);
		r1.setTextureSize(64, 64);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 38)).addBox(-6.0f, 6.0f, 4.0f, 12, 3, 3);
		r2.setRotationPoint(0.0f, 0.0f, 0.0f);
		r2.setTextureSize(64, 64);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 33, 38)).addBox(-9.0f, -6.0f, 4.0f, 3, 12, 3);
		r3.setRotationPoint(0.0f, 0.0f, 0.0f);
		r3.setTextureSize(64, 64);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		r4.render(par7);
		leftArm.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		rightArm.render(par7);
		rightArm2.render(par7);
		rightArm3.render(par7);
		body.render(par7);
		r1.render(par7);
		r2.render(par7);
		r3.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.523f;
		rightArm.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		rightArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.523f;
		rightArm2.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		r1.rotateAngleZ = par3 * 0.067f * 1.25f;
		r2.rotateAngleZ = par3 * 0.067f * 1.25f;
		r3.rotateAngleZ = par3 * 0.067f * 1.25f;
		r4.rotateAngleZ = par3 * 0.067f * 1.25f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

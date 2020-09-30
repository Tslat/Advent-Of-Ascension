package net.tslat.aoa3.client.model.entities.mobs.crystevia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelConstructOfResistance extends ModelBase {
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer vr1;
	private ModelRenderer hr1;
	private ModelRenderer head;
	private ModelRenderer leftArm;
	private ModelRenderer vr2;
	private ModelRenderer leftLeg;
	private ModelRenderer rightLeg;
	private ModelRenderer vr3;
	private ModelRenderer vr4;
	private ModelRenderer hr2;
	private ModelRenderer hr3;
	private ModelRenderer hr4;

	public ModelConstructOfResistance() {
		textureWidth = 64;
		textureHeight = 64;
		(body = new ModelRenderer(this, 1, 1)).addBox(-4.0f, 0.0f, -3.0f, 8, 14, 6);
		body.setRotationPoint(0.0f, -2.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 47, 1)).addBox(-3.0f, -4.0f, -2.0f, 4, 23, 4);
		rightArm.setRotationPoint(-7.0f, 2.0f, 0.0f);
		rightArm.setTextureSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(vr1 = new ModelRenderer(this, 30, 1)).addBox(-1.0f, -3.0f, -3.0f, 2, 6, 6);
		vr1.setRotationPoint(-5.0f, 1.0f, 0.0f);
		vr1.setTextureSize(64, 64);
		vr1.mirror = true;
		setRotation(vr1, 0.0f, 0.0f, 0.0f);
		(hr1 = new ModelRenderer(this, 24, 22)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 4);
		hr1.setRotationPoint(-2.0f, 12.0f, 0.0f);
		hr1.setTextureSize(64, 64);
		hr1.mirror = true;
		setRotation(hr1, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 5, 45)).addBox(-2.0f, 4.0f, -2.0f, 4, 4, 4);
		head.setRotationPoint(0.0f, -14.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 47, 1)).addBox(-1.0f, -4.0f, -2.0f, 4, 23, 4);
		leftArm.setRotationPoint(7.0f, 2.0f, 0.0f);
		leftArm.setTextureSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(vr2 = new ModelRenderer(this, 30, 32)).addBox(-1.0f, -3.0f, -3.0f, 2, 6, 6);
		vr2.setRotationPoint(4.0f, -8.0f, 0.0f);
		vr2.setTextureSize(64, 64);
		vr2.mirror = true;
		setRotation(vr2, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 24)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		leftLeg.setRotationPoint(2.0f, 14.0f, 0.0f);
		leftLeg.setTextureSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 24)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		rightLeg.setRotationPoint(-2.0f, 14.0f, 0.0f);
		rightLeg.setTextureSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(vr3 = new ModelRenderer(this, 30, 1)).addBox(-1.0f, -3.0f, -3.0f, 2, 6, 6);
		vr3.setRotationPoint(5.0f, 1.0f, 0.0f);
		vr3.setTextureSize(64, 64);
		vr3.mirror = true;
		setRotation(vr3, 0.0f, 0.0f, 0.0f);
		(vr4 = new ModelRenderer(this, 30, 32)).addBox(-1.0f, -3.0f, -3.0f, 2, 6, 6);
		vr4.setRotationPoint(-4.0f, -8.0f, 0.0f);
		vr4.setTextureSize(64, 64);
		vr4.mirror = true;
		setRotation(vr4, 0.0f, 0.0f, 0.0f);
		(hr2 = new ModelRenderer(this, 24, 22)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 4);
		hr2.setRotationPoint(2.0f, 12.0f, 0.0f);
		hr2.setTextureSize(64, 64);
		hr2.mirror = true;
		setRotation(hr2, 0.0f, 0.0f, 0.0f);
		(hr3 = new ModelRenderer(this, 24, 45)).addBox(-3.0f, 0.0f, -3.0f, 6, 2, 6);
		hr3.setRotationPoint(0.0f, -4.0f, 0.0f);
		hr3.setTextureSize(64, 64);
		hr3.mirror = true;
		setRotation(hr3, 0.0f, 0.0f, 0.0f);
		(hr4 = new ModelRenderer(this, 24, 45)).addBox(-3.0f, 0.0f, -3.0f, 6, 2, 6);
		hr4.setRotationPoint(0.0f, -14.0f, 0.0f);
		hr4.setTextureSize(64, 64);
		hr4.mirror = true;
		setRotation(hr4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		body.render(par7);
		rightArm.render(par7);
		vr1.render(par7);
		hr1.render(par7);
		head.render(par7);
		leftArm.render(par7);
		vr2.render(par7);
		leftLeg.render(par7);
		rightLeg.render(par7);
		vr3.render(par7);
		vr4.render(par7);
		hr2.render(par7);
		hr3.render(par7);
		hr4.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		hr1.rotateAngleY = par3 * 0.067f * 1.25f;
		hr2.rotateAngleY = par3 * 0.067f * 1.25f;
		hr3.rotateAngleY = par3 * 0.067f * 1.25f;
		hr4.rotateAngleY = par3 * 0.067f * 1.25f;
		vr1.rotateAngleX = par3 * 0.067f * 1.25f;
		vr2.rotateAngleX = par3 * 0.067f * 1.25f;
		vr3.rotateAngleX = par3 * 0.067f * 1.25f;
		vr4.rotateAngleX = par3 * 0.067f * 1.25f;
	}
}

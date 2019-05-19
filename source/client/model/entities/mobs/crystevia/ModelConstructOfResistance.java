package net.tslat.aoa3.client.model.entities.mobs.crystevia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelConstructOfResistance extends ModelBase {
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer vr1;
	ModelRenderer hr1;
	ModelRenderer head;
	ModelRenderer leftarm;
	ModelRenderer vr2;
	ModelRenderer leftleg;
	ModelRenderer rightleg;
	ModelRenderer vr3;
	ModelRenderer vr4;
	ModelRenderer hr2;
	ModelRenderer hr3;
	ModelRenderer hr4;

	public ModelConstructOfResistance() {
		textureWidth = 64;
		textureHeight = 64;
		(body = new ModelRenderer(this, 1, 1)).addBox(-4.0f, 0.0f, -3.0f, 8, 14, 6);
		body.setRotationPoint(0.0f, -2.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 47, 1)).addBox(-3.0f, -4.0f, -2.0f, 4, 23, 4);
		rightarm.setRotationPoint(-7.0f, 2.0f, 0.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
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
		(leftarm = new ModelRenderer(this, 47, 1)).addBox(-1.0f, -4.0f, -2.0f, 4, 23, 4);
		leftarm.setRotationPoint(7.0f, 2.0f, 0.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(vr2 = new ModelRenderer(this, 30, 32)).addBox(-1.0f, -3.0f, -3.0f, 2, 6, 6);
		vr2.setRotationPoint(4.0f, -8.0f, 0.0f);
		vr2.setTextureSize(64, 64);
		vr2.mirror = true;
		setRotation(vr2, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 24)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		leftleg.setRotationPoint(2.0f, 14.0f, 0.0f);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 24)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		rightleg.setRotationPoint(-2.0f, 14.0f, 0.0f);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
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
		GlStateManager.blendFunc(770, 771);
		body.render(par7);
		rightarm.render(par7);
		vr1.render(par7);
		hr1.render(par7);
		head.render(par7);
		leftarm.render(par7);
		vr2.render(par7);
		leftleg.render(par7);
		rightleg.render(par7);
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
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
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

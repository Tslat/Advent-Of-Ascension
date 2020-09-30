package net.tslat.aoa3.client.model.entities.boss.crystocore;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelCrystocore extends ModelBase {
	private ModelRenderer shape1;
	private ModelRenderer Center;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer shape4;
	private ModelRenderer shape5;
	private ModelRenderer shape6;

	public ModelCrystocore() {
		textureWidth = 128;
		textureHeight = 128;
		(shape1 = new ModelRenderer(this, 42, 88)).addBox(-20.0f, 14.0f, -5.0f, 10, 14, 10);
		shape1.setRotationPoint(0.0f, -4.0f, 0.0f);
		shape1.setTextureSize(128, 128);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(Center = new ModelRenderer(this, 0, 0)).addBox(-10.0f, -10.0f, -10.0f, 20, 20, 20);
		Center.setRotationPoint(0.0f, -5.0f, 0.0f);
		Center.setTextureSize(128, 128);
		Center.mirror = true;
		setRotation(Center, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 42)).addBox(-10.0f, 15.0f, -4.0f, 20, 8, 8);
		shape2.setRotationPoint(0.0f, -4.0f, 0.0f);
		shape2.setTextureSize(128, 128);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 88)).addBox(10.0f, 14.0f, -5.0f, 10, 10, 10);
		shape3.setRotationPoint(0.0f, -4.0f, 0.0f);
		shape3.setTextureSize(128, 128);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 42, 61)).addBox(10.0f, -29.0f, -5.0f, 10, 14, 10);
		shape4.setRotationPoint(0.0f, -5.0f, 0.0f);
		shape4.setTextureSize(128, 128);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 0, 42)).addBox(-10.0f, -24.0f, -4.0f, 20, 8, 8);
		shape5.setRotationPoint(0.0f, -5.0f, 0.0f);
		shape5.setTextureSize(128, 128);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 0, 61)).addBox(-20.0f, -25.0f, -5.0f, 10, 10, 10);
		shape6.setRotationPoint(0.0f, -5.0f, 0.0f);
		shape6.setTextureSize(128, 128);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		shape1.render(par7);
		Center.render(par7);
		shape2.render(par7);
		shape3.render(par7);
		shape4.render(par7);
		shape5.render(par7);
		shape6.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		Center.rotateAngleY = par4 / (float)(180f / Math.PI);
		shape1.rotateAngleZ = par3 * 0.067f * 1.25f;
		shape2.rotateAngleZ = par3 * 0.067f * 1.25f;
		shape3.rotateAngleZ = par3 * 0.067f * 1.25f;
		shape4.rotateAngleZ = par3 * 0.067f * 1.25f;
		shape5.rotateAngleZ = par3 * 0.067f * 1.25f;
		shape6.rotateAngleZ = par3 * 0.067f * 1.25f;
		shape1.rotateAngleY = par3 * 0.067f * 1.25f;
		shape2.rotateAngleY = par3 * 0.067f * 1.25f;
		shape3.rotateAngleY = par3 * 0.067f * 1.25f;
		shape4.rotateAngleY = par3 * 0.067f * 1.25f;
		shape5.rotateAngleY = par3 * 0.067f * 1.25f;
		shape6.rotateAngleY = par3 * 0.067f * 1.25f;
	}
}

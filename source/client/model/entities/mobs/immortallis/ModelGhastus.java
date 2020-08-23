package net.tslat.aoa3.client.model.entities.mobs.immortallis;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelGhastus extends ModelBase {
	private ModelRenderer r1;
	private ModelRenderer head;
	private ModelRenderer r2;
	private ModelRenderer r3;
	private ModelRenderer r4;
	private ModelRenderer r5;
	private ModelRenderer r6;
	private ModelRenderer r7;
	private ModelRenderer r8;

	public ModelGhastus() {
		textureWidth = 64;
		textureHeight = 32;
		(r1 = new ModelRenderer(this, 0, 0)).addBox(-10.0f, -2.0f, 2.0f, 1, 1, 1);
		r1.setRotationPoint(0.0f, 15.0f, 0.0f);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 15.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 0)).addBox(-7.0f, -3.0f, -4.0f, 1, 1, 1);
		r2.setRotationPoint(0.0f, 15.0f, 0.0f);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 0, 0)).addBox(-6.0f, 0.0f, 2.0f, 1, 1, 1);
		r3.setRotationPoint(0.0f, 15.0f, 0.0f);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 0, 0)).addBox(-9.0f, 0.0f, -1.0f, 1, 1, 1);
		r4.setRotationPoint(0.0f, 15.0f, 0.0f);
		r4.setTextureSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 0, 0)).addBox(-7.0f, 2.0f, 2.0f, 1, 1, 1);
		r5.setRotationPoint(0.0f, 15.0f, 0.0f);
		r5.setTextureSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 0, 0)).addBox(-7.0f, 2.0f, -3.0f, 1, 1, 1);
		r6.setRotationPoint(0.0f, 15.0f, 0.0f);
		r6.setTextureSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
		(r7 = new ModelRenderer(this, 0, 0)).addBox(-10.0f, 0.0f, -3.0f, 1, 1, 1);
		r7.setRotationPoint(0.0f, 15.0f, 0.0f);
		r7.setTextureSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0.0f, 0.0f, 0.0f);
		(r8 = new ModelRenderer(this, 0, 0)).addBox(-6.0f, -2.0f, -1.0f, 1, 1, 1);
		r8.setRotationPoint(0.0f, 15.0f, 0.0f);
		r8.setTextureSize(64, 32);
		r8.mirror = true;
		setRotation(r8, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		r1.render(par7);
		head.render(par7);
		r2.render(par7);
		r3.render(par7);
		r4.render(par7);
		r5.render(par7);
		r6.render(par7);
		r7.render(par7);
		r8.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		r1.rotateAngleZ = par3 * 0.067f * 1.25f;
		r2.rotateAngleZ = par3 * 0.067f * 1.25f;
		r3.rotateAngleZ = par3 * 0.067f * 1.25f;
		r4.rotateAngleZ = par3 * 0.067f * 1.25f;
		r5.rotateAngleZ = par3 * 0.067f * 1.25f;
		r6.rotateAngleZ = par3 * 0.067f * 1.25f;
		r7.rotateAngleZ = par3 * 0.067f * 1.25f;
		r8.rotateAngleZ = par3 * 0.067f * 1.25f;
	}
}

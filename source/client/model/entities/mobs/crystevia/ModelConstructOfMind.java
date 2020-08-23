package net.tslat.aoa3.client.model.entities.mobs.crystevia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelConstructOfMind extends ModelBase {
	private ModelRenderer or1;
	private ModelRenderer ir1;
	private ModelRenderer or2;
	private ModelRenderer ir2;
	private ModelRenderer ir3;
	private ModelRenderer or3;
	private ModelRenderer ir4;
	private ModelRenderer or4;
	private ModelRenderer or5;
	private ModelRenderer or6;

	public ModelConstructOfMind() {
		textureWidth = 64;
		textureHeight = 64;
		(or1 = new ModelRenderer(this, 28, 0)).addBox(-15.0f, -3.0f, -3.0f, 2, 6, 6);
		or1.setRotationPoint(0.0f, 4.0f, 0.0f);
		or1.setTextureSize(64, 64);
		or1.mirror = true;
		setRotation(or1, 0.0f, 0.0f, 0.0f);
		(ir1 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, 4.0f, -3.0f, 6, 6, 6);
		ir1.setRotationPoint(0.0f, 4.0f, 0.0f);
		ir1.setTextureSize(64, 64);
		ir1.mirror = true;
		setRotation(ir1, 0.0f, 0.0f, 0.0f);
		(or2 = new ModelRenderer(this, 0, 15)).addBox(-4.0f, 12.0f, -3.0f, 8, 8, 6);
		or2.setRotationPoint(0.0f, 4.0f, 0.0f);
		or2.setTextureSize(64, 64);
		or2.mirror = true;
		setRotation(or2, 0.0f, 0.0f, 0.0f);
		(ir2 = new ModelRenderer(this, 0, 0)).addBox(-11.0f, -3.0f, -3.0f, 6, 6, 6);
		ir2.setRotationPoint(0.0f, 4.0f, 0.0f);
		ir2.setTextureSize(64, 64);
		ir2.mirror = true;
		setRotation(ir2, 0.0f, 0.0f, 0.0f);
		(ir3 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -11.0f, -3.0f, 6, 6, 6);
		ir3.setRotationPoint(0.0f, 4.0f, 0.0f);
		ir3.setTextureSize(64, 64);
		ir3.mirror = true;
		setRotation(ir3, 0.0f, 0.0f, 0.0f);
		(or3 = new ModelRenderer(this, 0, 15)).addBox(-4.0f, -21.0f, -3.0f, 8, 8, 6);
		or3.setRotationPoint(0.0f, 4.0f, 0.0f);
		or3.setTextureSize(64, 64);
		or3.mirror = true;
		setRotation(or3, 0.0f, 0.0f, 0.0f);
		(ir4 = new ModelRenderer(this, 0, 0)).addBox(5.0f, -3.0f, -3.0f, 6, 6, 6);
		ir4.setRotationPoint(0.0f, 4.0f, 0.0f);
		ir4.setTextureSize(64, 64);
		ir4.mirror = true;
		setRotation(ir4, 0.0f, 0.0f, 0.0f);
		(or4 = new ModelRenderer(this, 28, 0)).addBox(-19.0f, -3.0f, -3.0f, 2, 6, 6);
		or4.setRotationPoint(0.0f, 4.0f, 0.0f);
		or4.setTextureSize(64, 64);
		or4.mirror = true;
		setRotation(or4, 0.0f, 0.0f, 0.0f);
		(or5 = new ModelRenderer(this, 28, 0)).addBox(17.0f, -3.0f, -3.0f, 2, 6, 6);
		or5.setRotationPoint(0.0f, 4.0f, 0.0f);
		or5.setTextureSize(64, 64);
		or5.mirror = true;
		setRotation(or5, 0.0f, 0.0f, 0.0f);
		(or6 = new ModelRenderer(this, 28, 0)).addBox(13.0f, -3.0f, -3.0f, 2, 6, 6);
		or6.setRotationPoint(0.0f, 4.0f, 0.0f);
		or6.setTextureSize(64, 64);
		or6.mirror = true;
		setRotation(or6, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		or1.render(par7);
		ir1.render(par7);
		or2.render(par7);
		ir2.render(par7);
		ir3.render(par7);
		or3.render(par7);
		ir4.render(par7);
		or4.render(par7);
		or5.render(par7);
		or6.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		ir1.rotateAngleZ = par3 * 0.067f * 1.25f;
		ir2.rotateAngleZ = par3 * 0.067f * 1.25f;
		ir3.rotateAngleZ = par3 * 0.067f * 1.25f;
		ir4.rotateAngleZ = par3 * 0.067f * 1.25f;
		or1.rotateAngleX = par3 * 0.067f * 2.25f;
		or2.rotateAngleX = par3 * 0.067f * 2.25f;
		or3.rotateAngleX = par3 * 0.067f * 2.25f;
		or4.rotateAngleX = par3 * 0.067f * 2.25f;
		or5.rotateAngleX = par3 * 0.067f * 2.25f;
		or6.rotateAngleX = par3 * 0.067f * 2.25f;
	}
}

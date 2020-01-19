package net.tslat.aoa3.client.model.entities.mobs.crystevia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelConstructOfTerror extends ModelBase {
	private ModelRenderer r1;
	private ModelRenderer shape1;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer shape4;
	private ModelRenderer shape5;
	private ModelRenderer r3;
	private ModelRenderer r4;
	private ModelRenderer r2;

	public ModelConstructOfTerror() {
		textureWidth = 64;
		textureHeight = 64;
		(r1 = new ModelRenderer(this, 29, 12)).addBox(-8.0f, -15.0f, -1.0f, 2, 3, 2);
		r1.setRotationPoint(0.0f, 16.0f, 0.0f);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 6, 14, 6);
		shape1.setRotationPoint(-10.0f, 10.0f, -3.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 0)).addBox(14.0f, 0.0f, 0.0f, 6, 14, 6);
		shape2.setRotationPoint(-10.0f, 10.0f, -3.0f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 25, 0)).addBox(6.0f, 5.0f, 1.0f, 2, 4, 4);
		shape3.setRotationPoint(-10.0f, 10.0f, -3.0f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 25, 0)).addBox(12.0f, 5.0f, 1.0f, 2, 4, 4);
		shape4.setRotationPoint(-10.0f, 10.0f, -3.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 39, 0)).addBox(8.0f, 4.0f, 1.0f, 4, 6, 4);
		shape5.setRotationPoint(-10.0f, 10.0f, -3.0f);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 29, 12)).addBox(6.0f, -15.0f, -1.0f, 2, 3, 2);
		r3.setRotationPoint(0.0f, 16.0f, 0.0f);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 39, 12)).addBox(-9.0f, -12.0f, -2.0f, 4, 5, 4);
		r4.setRotationPoint(0.0f, 16.0f, 0.0f);
		r4.setTextureSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 39, 12)).addBox(5.0f, -12.0f, -2.0f, 4, 5, 4);
		r2.setRotationPoint(0.0f, 16.0f, 0.0f);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, 771);
		r1.render(par7);
		shape1.render(par7);
		shape2.render(par7);
		shape3.render(par7);
		shape4.render(par7);
		shape5.render(par7);
		r3.render(par7);
		r4.render(par7);
		r2.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		r1.rotateAngleY = par3 * 0.067f * 1.25f;
		r2.rotateAngleY = par3 * 0.067f * 1.25f;
		r3.rotateAngleY = par3 * 0.067f * 1.25f;
		r4.rotateAngleY = par3 * 0.067f * 1.25f;
	}
}

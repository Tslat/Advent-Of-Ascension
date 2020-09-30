package net.tslat.aoa3.client.model.entities.mobs.candyland;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelAirhead extends ModelBase {
	private ModelRenderer R2;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer R1;
	private ModelRenderer R3;
	private ModelRenderer R4;

	public ModelAirhead() {
		textureWidth = 64;
		textureHeight = 64;
		(R2 = new ModelRenderer(this, 26, 47)).addBox(7.5f, 4.0f, -4.0f, 1, 1, 10);
		R2.setRotationPoint(0.0f, 17.0f, -1.0f);
		R2.setTextureSize(64, 64);
		R2.mirror = true;
		setRotation(R2, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
		shape2.setRotationPoint(-8.0f, -3.0f, -8.0f);
		shape2.setTextureSize(64, 64);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 34)).addBox(0.0f, 0.0f, 0.0f, 6, 7, 6);
		shape3.setRotationPoint(-3.0f, 13.0f, -3.0f);
		shape3.setTextureSize(64, 64);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(R1 = new ModelRenderer(this, 26, 47)).addBox(-8.5f, 4.0f, -4.0f, 1, 1, 10);
		R1.setRotationPoint(0.0f, 17.0f, -1.0f);
		R1.setTextureSize(64, 64);
		R1.mirror = true;
		setRotation(R1, 0.0f, 0.0f, 0.0f);
		(R3 = new ModelRenderer(this, 26, 34)).addBox(6.0f, 0.0f, -2.0f, 4, 4, 6);
		R3.setRotationPoint(0.0f, 17.0f, -1.0f);
		R3.setTextureSize(64, 64);
		R3.mirror = true;
		setRotation(R3, 0.0f, 0.0f, 0.0f);
		(R4 = new ModelRenderer(this, 26, 34)).addBox(-10.0f, 0.0f, -2.0f, 4, 4, 6);
		R4.setRotationPoint(0.0f, 17.0f, -1.0f);
		R4.setTextureSize(64, 64);
		R4.mirror = true;
		setRotation(R4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		R2.render(par7);
		shape2.render(par7);
		shape3.render(par7);
		R1.render(par7);
		R3.render(par7);
		R4.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		R1.rotateAngleY = par3 * 0.117f * 1.25f;
		R2.rotateAngleY = par3 * 0.117f * 1.25f;
		R3.rotateAngleY = par3 * 0.117f * 1.25f;
		R4.rotateAngleY = par3 * 0.117f * 1.25f;
	}
}

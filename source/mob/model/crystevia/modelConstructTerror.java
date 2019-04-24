package net.nevermine.mob.model.crystevia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class modelConstructTerror extends ModelBase {
	ModelRenderer r1;
	ModelRenderer Shape1;
	ModelRenderer Shape2;
	ModelRenderer Shape3;
	ModelRenderer Shape4;
	ModelRenderer Shape5;
	ModelRenderer r3;
	ModelRenderer r4;
	ModelRenderer r2;

	public modelConstructTerror() {
		textureWidth = 64;
		textureHeight = 64;
		(r1 = new ModelRenderer(this, 29, 12)).addBox(-8.0f, -15.0f, -1.0f, 2, 3, 2);
		r1.setRotationPoint(0.0f, 16.0f, 0.0f);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(Shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 6, 14, 6);
		Shape1.setRotationPoint(-10.0f, 10.0f, -3.0f);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 0, 0)).addBox(14.0f, 0.0f, 0.0f, 6, 14, 6);
		Shape2.setRotationPoint(-10.0f, 10.0f, -3.0f);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
		(Shape3 = new ModelRenderer(this, 25, 0)).addBox(6.0f, 5.0f, 1.0f, 2, 4, 4);
		Shape3.setRotationPoint(-10.0f, 10.0f, -3.0f);
		Shape3.setTextureSize(64, 32);
		Shape3.mirror = true;
		setRotation(Shape3, 0.0f, 0.0f, 0.0f);
		(Shape4 = new ModelRenderer(this, 25, 0)).addBox(12.0f, 5.0f, 1.0f, 2, 4, 4);
		Shape4.setRotationPoint(-10.0f, 10.0f, -3.0f);
		Shape4.setTextureSize(64, 32);
		Shape4.mirror = true;
		setRotation(Shape4, 0.0f, 0.0f, 0.0f);
		(Shape5 = new ModelRenderer(this, 39, 0)).addBox(8.0f, 4.0f, 1.0f, 4, 6, 4);
		Shape5.setRotationPoint(-10.0f, 10.0f, -3.0f);
		Shape5.setTextureSize(64, 32);
		Shape5.mirror = true;
		setRotation(Shape5, 0.0f, 0.0f, 0.0f);
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
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		r1.render(par7);
		Shape1.render(par7);
		Shape2.render(par7);
		Shape3.render(par7);
		Shape4.render(par7);
		Shape5.render(par7);
		r3.render(par7);
		r4.render(par7);
		r2.render(par7);
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

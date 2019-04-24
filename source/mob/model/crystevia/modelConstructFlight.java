package net.nevermine.mob.model.crystevia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class modelConstructFlight extends ModelBase {
	ModelRenderer WingL1;
	ModelRenderer r1;
	ModelRenderer WingL2;
	ModelRenderer WingL3;
	ModelRenderer WingR1;
	ModelRenderer WingR2;
	ModelRenderer WingR3;
	ModelRenderer Shape1;
	ModelRenderer r2;
	ModelRenderer r3;
	ModelRenderer r4;
	ModelRenderer r5;
	ModelRenderer r6;

	public modelConstructFlight() {
		textureWidth = 64;
		textureHeight = 64;
		(WingL1 = new ModelRenderer(this, 1, 41)).addBox(9.0f, 0.0f, 3.0f, 4, 2, 2);
		WingL1.setRotationPoint(6.0f, 14.0f, -1.0f);
		WingL1.setTextureSize(64, 64);
		WingL1.mirror = true;
		setRotation(WingL1, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 43, 6)).addBox(2.0f, -6.0f, -8.0f, 3, 2, 2);
		r1.setRotationPoint(0.0f, 11.0f, -1.0f);
		r1.setTextureSize(64, 64);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(WingL2 = new ModelRenderer(this, 0, 22)).addBox(-1.0f, 0.0f, -5.0f, 10, 2, 10);
		WingL2.setRotationPoint(6.0f, 14.0f, -1.0f);
		WingL2.setTextureSize(64, 64);
		WingL2.mirror = true;
		setRotation(WingL2, 0.0f, 0.0f, 0.0f);
		(WingL3 = new ModelRenderer(this, 1, 41)).addBox(9.0f, 0.0f, -5.0f, 4, 2, 2);
		WingL3.setRotationPoint(6.0f, 14.0f, -1.0f);
		WingL3.setTextureSize(64, 64);
		WingL3.mirror = true;
		setRotation(WingL3, 0.0f, 0.0f, 0.0f);
		(WingR1 = new ModelRenderer(this, 0, 22)).addBox(-9.0f, 0.0f, -5.0f, 10, 2, 10);
		WingR1.setRotationPoint(-6.0f, 14.0f, -1.0f);
		WingR1.setTextureSize(64, 64);
		WingR1.mirror = true;
		setRotation(WingR1, 0.0f, 0.0f, 0.0f);
		(WingR2 = new ModelRenderer(this, 1, 35)).addBox(-13.0f, 0.0f, -5.0f, 4, 2, 2);
		WingR2.setRotationPoint(-6.0f, 14.0f, -1.0f);
		WingR2.setTextureSize(64, 64);
		WingR2.mirror = true;
		setRotation(WingR2, 0.0f, 0.0f, 0.0f);
		(WingR3 = new ModelRenderer(this, 1, 35)).addBox(-13.0f, 0.0f, 3.0f, 4, 2, 2);
		WingR3.setRotationPoint(-6.0f, 14.0f, -1.0f);
		WingR3.setTextureSize(64, 64);
		WingR3.mirror = true;
		setRotation(WingR3, 0.0f, 0.0f, 0.0f);
		(Shape1 = new ModelRenderer(this, 0, 0)).addBox(-5.0f, 0.0f, 0.0f, 10, 10, 10);
		Shape1.setRotationPoint(0.0f, 11.0f, -6.0f);
		Shape1.setTextureSize(64, 64);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 43, 6)).addBox(2.0f, -6.0f, 6.0f, 3, 2, 2);
		r2.setRotationPoint(0.0f, 11.0f, -1.0f);
		r2.setTextureSize(64, 64);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 34, 0)).addBox(-5.0f, -4.0f, 6.0f, 10, 2, 2);
		r3.setRotationPoint(0.0f, 11.0f, -1.0f);
		r3.setTextureSize(64, 64);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 34, 0)).addBox(-5.0f, -4.0f, -8.0f, 10, 2, 2);
		r4.setRotationPoint(0.0f, 11.0f, -1.0f);
		r4.setTextureSize(64, 64);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 43, 6)).addBox(-5.0f, -6.0f, 6.0f, 3, 2, 2);
		r5.setRotationPoint(0.0f, 11.0f, -1.0f);
		r5.setTextureSize(64, 64);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 43, 6)).addBox(-5.0f, -6.0f, -8.0f, 3, 2, 2);
		r6.setRotationPoint(0.0f, 11.0f, -1.0f);
		r6.setTextureSize(64, 64);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		WingL1.render(par7);
		r1.render(par7);
		WingL2.render(par7);
		WingL3.render(par7);
		WingR1.render(par7);
		WingR2.render(par7);
		WingR3.render(par7);
		Shape1.render(par7);
		r2.render(par7);
		r3.render(par7);
		r4.render(par7);
		r5.render(par7);
		r6.render(par7);
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
		r5.rotateAngleY = par3 * 0.067f * 1.25f;
		r6.rotateAngleY = par3 * 0.067f * 1.25f;
		WingR1.rotateAngleZ = MathHelper.cos(par3 * 0.1f) * 3.1415927f * 0.15f;
		WingL1.rotateAngleZ = -WingR1.rotateAngleZ;
		WingR2.rotateAngleZ = MathHelper.cos(par3 * 0.1f) * 3.1415927f * 0.15f;
		WingL2.rotateAngleZ = -WingR2.rotateAngleZ;
		WingR3.rotateAngleZ = MathHelper.cos(par3 * 0.1f) * 3.1415927f * 0.15f;
		WingL3.rotateAngleZ = -WingR2.rotateAngleZ;
	}
}

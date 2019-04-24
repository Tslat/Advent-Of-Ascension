package net.tslat.aoa3.client.model.entities.mobs.crystevia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelConstructOfRange extends ModelBase {
	ModelRenderer r4;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer rightarm;
	ModelRenderer rightarm2;
	ModelRenderer rightarm3;
	ModelRenderer body;
	ModelRenderer r1;
	ModelRenderer r2;
	ModelRenderer r3;

	public ModelConstructOfRange() {
		textureWidth = 64;
		textureHeight = 64;
		(r4 = new ModelRenderer(this, 33, 38)).addBox(6.0f, -6.0f, 4.0f, 3, 12, 3);
		r4.setRotationPoint(0.0f, 0.0f, 0.0f);
		r4.setTextureSize(64, 64);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 40, 1)).addBox(-2.0f, -2.0f, -3.0f, 6, 14, 6);
		leftarm.setRotationPoint(9.0f, 2.0f, 0.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 20)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		rightleg.setRotationPoint(-3.0f, 14.0f, 0.0f);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 20)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		leftleg.setRotationPoint(3.0f, 14.0f, 0.0f);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 28, 22)).addBox(-4.0f, -9.0f, -11.0f, 6, 4, 6);
		rightarm.setRotationPoint(-9.0f, 2.0f, 0.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.5235988f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 40, 1)).addBox(-4.0f, -2.0f, -3.0f, 6, 14, 6);
		rightarm2.setRotationPoint(-9.0f, 2.0f, 0.0f);
		rightarm2.setTextureSize(64, 64);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 55, 22)).addBox(-2.0f, -5.0f, -9.0f, 2, 20, 2);
		rightarm3.setRotationPoint(-9.0f, 2.0f, 0.0f);
		rightarm3.setTextureSize(64, 64);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.5235988f, 0.0f, 0.0f);
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
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		r4.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		rightarm.render(par7);
		rightarm2.render(par7);
		rightarm3.render(par7);
		body.render(par7);
		r1.render(par7);
		r2.render(par7);
		r3.render(par7);
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.523f;
		rightarm.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		rightarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.523f;
		rightarm2.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		r1.rotateAngleZ = par3 * 0.067f * 1.25f;
		r2.rotateAngleZ = par3 * 0.067f * 1.25f;
		r3.rotateAngleZ = par3 * 0.067f * 1.25f;
		r4.rotateAngleZ = par3 * 0.067f * 1.25f;
	}
}

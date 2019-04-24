package net.tslat.aoa3.client.model.entities.mobs.crystevia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelConstructOfStrength extends ModelBase {
	ModelRenderer r1;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer rightarm2;
	ModelRenderer leftarm2;
	ModelRenderer head;
	ModelRenderer r2;
	ModelRenderer r3;
	ModelRenderer r4;

	public ModelConstructOfStrength() {
		textureWidth = 64;
		textureHeight = 64;
		(r1 = new ModelRenderer(this, 0, 36)).addBox(6.0f, -7.0f, -4.0f, 2, 2, 8);
		r1.setRotationPoint(0.0f, -6.0f, 0.0f);
		r1.setTextureSize(64, 64);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 38)).addBox(-4.0f, 0.0f, -2.0f, 16, 18, 8);
		body.setRotationPoint(-4.0f, -6.0f, -2.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 25, 24)).addBox(2.0f, -1.0f, -3.0f, 1, 5, 6);
		rightarm.setRotationPoint(-11.0f, -3.0f, 0.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 25, 24)).addBox(-3.0f, -1.0f, -3.0f, 1, 5, 6);
		leftarm.setRotationPoint(11.0f, -3.0f, 0.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 18)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		rightleg.setRotationPoint(-4.0f, 12.0f, 0.0f);
		rightleg.setTextureSize(64, 64);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 18)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		leftleg.setRotationPoint(4.0f, 12.0f, 0.0f);
		leftleg.setTextureSize(64, 64);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 40, 2)).addBox(-4.0f, -2.0f, -3.0f, 6, 23, 6);
		rightarm2.setRotationPoint(-11.0f, -3.0f, 0.0f);
		rightarm2.setTextureSize(64, 64);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 40, 2)).addBox(-2.0f, -2.0f, -3.0f, 6, 23, 6);
		leftarm2.setRotationPoint(11.0f, -3.0f, 0.0f);
		leftarm2.setTextureSize(64, 64);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, -6.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 42, 33)).addBox(-4.0f, -7.0f, -8.0f, 8, 2, 2);
		r2.setRotationPoint(0.0f, -6.0f, 0.0f);
		r2.setTextureSize(64, 64);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 42, 33)).addBox(-4.0f, -7.0f, 6.0f, 8, 2, 2);
		r3.setRotationPoint(0.0f, -6.0f, 0.0f);
		r3.setTextureSize(64, 64);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 0, 36)).addBox(-8.0f, -7.0f, -4.0f, 2, 2, 8);
		r4.setRotationPoint(0.0f, -6.0f, 0.0f);
		r4.setTextureSize(64, 64);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		r1.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		rightarm2.render(par7);
		leftarm2.render(par7);
		head.render(par7);
		r2.render(par7);
		r3.render(par7);
		r4.render(par7);
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
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm2.rotateAngleZ = 0.0f;
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		r1.rotateAngleY = par3 * 0.067f * 1.25f;
		r2.rotateAngleY = par3 * 0.067f * 1.25f;
		r3.rotateAngleY = par3 * 0.067f * 1.25f;
		r4.rotateAngleY = par3 * 0.067f * 1.25f;
	}
}

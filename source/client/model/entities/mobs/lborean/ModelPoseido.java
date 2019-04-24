package net.tslat.aoa3.client.model.entities.mobs.lborean;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPoseido extends ModelBase {
	ModelRenderer head;
	ModelRenderer r4;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer leftarm2;
	ModelRenderer rightarm2;
	ModelRenderer head2;
	ModelRenderer body;
	ModelRenderer r1;
	ModelRenderer r2;
	ModelRenderer r3;

	public ModelPoseido() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 18)).addBox(-4.0f, 0.0f, -4.0f, 8, 5, 0);
		head.setRotationPoint(0.0f, 6.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 1, 27)).addBox(3.0f, 14.0f, -1.0f, 2, 2, 2);
		r4.setRotationPoint(0.0f, 6.0f, 0.0f);
		r4.setTextureSize(64, 64);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 40, 6)).addBox(-3.0f, -4.0f, -3.0f, 5, 2, 6);
		rightarm.setRotationPoint(-6.0f, 9.0f, 0.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 40, 6)).addBox(-2.0f, -4.0f, -3.0f, 5, 2, 6);
		leftarm.setRotationPoint(6.0f, 9.0f, 0.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftarm2.setRotationPoint(6.0f, 9.0f, 0.0f);
		leftarm2.setTextureSize(64, 64);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightarm2.setRotationPoint(-6.0f, 9.0f, 0.0f);
		rightarm2.setTextureSize(64, 64);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setRotationPoint(0.0f, 6.0f, 0.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, 6.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 1, 33)).addBox(-4.0f, 14.0f, -6.0f, 8, 2, 2);
		r1.setRotationPoint(0.0f, 6.0f, 0.0f);
		r1.setTextureSize(64, 64);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 1, 33)).addBox(-4.0f, 14.0f, 4.0f, 8, 2, 2);
		r2.setRotationPoint(0.0f, 6.0f, 0.0f);
		r2.setTextureSize(64, 64);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 1, 27)).addBox(-5.0f, 14.0f, -1.0f, 2, 2, 2);
		r3.setRotationPoint(0.0f, 6.0f, 0.0f);
		r3.setTextureSize(64, 64);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		r4.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		leftarm2.render(par7);
		rightarm2.render(par7);
		head2.render(par7);
		body.render(par7);
		r1.render(par7);
		r2.render(par7);
		r3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
		head2.rotateAngleY = par4 / (float)(180f / Math.PI);
		r1.rotateAngleY = par3 * 0.767f * 1.25f;
		r2.rotateAngleY = par3 * 0.767f * 1.25f;
		r3.rotateAngleY = par3 * 0.767f * 1.25f;
		r4.rotateAngleY = par3 * 0.767f * 1.25f;
	}
}

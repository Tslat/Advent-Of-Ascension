package net.tslat.aoa3.client.model.entities.boss.skeletalarmy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSkeleElder extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer RightArm;
	private ModelRenderer LeftArm;
	private ModelRenderer RightLeg;
	private ModelRenderer LeftLeg;
	private ModelRenderer body2;

	public ModelSkeleElder() {
		textureWidth = 64;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, -9.0f, -11.0f);
		head.setTextureSize(64, 32);
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 0, 16)).addBox(-4.0f, 0.5f, -2.0f, 10, 12, 4);
		body.setRotationPoint(-1.0f, -13.0f, -11.0f);
		body.setTextureSize(64, 32);
		setRotation(body, 1.396263f, 0.0f, 0.0f);
		(RightArm = new ModelRenderer(this, 56, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 30, 2);
		RightArm.setRotationPoint(-5.0f, -12.0f, -8.0f);
		RightArm.setTextureSize(64, 32);
		setRotation(RightArm, 0.0f, 0.0f, 0.0f);
		(LeftArm = new ModelRenderer(this, 56, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 30, 2);
		LeftArm.setRotationPoint(5.0f, -12.0f, -8.0f);
		LeftArm.setTextureSize(64, 32);
		LeftArm.mirror = true;
		setRotation(LeftArm, 0.0f, 0.0f, 0.0f);
		(RightLeg = new ModelRenderer(this, 56, 0)).addBox(-1.0f, 0.0f, -1.0f, 2, 30, 2);
		RightLeg.setRotationPoint(-4.0f, -2.0f, 7.0f);
		RightLeg.setTextureSize(64, 32);
		setRotation(RightLeg, 0.0f, 0.0f, 0.0f);
		(LeftLeg = new ModelRenderer(this, 56, 0)).addBox(-1.0f, 0.0f, -1.0f, 2, 30, 2);
		LeftLeg.setRotationPoint(5.0f, -2.0f, 7.0f);
		LeftLeg.setTextureSize(64, 32);
		setRotation(LeftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 32, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body2.setRotationPoint(0.0f, -11.0f, 0.0f);
		body2.setTextureSize(64, 32);
		setRotation(body2, 0.6108652f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		RightArm.render(par7);
		LeftArm.render(par7);
		RightLeg.render(par7);
		LeftLeg.render(par7);
		body2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		RightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		RightArm.rotateAngleZ = 0.0f;
		LeftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		LeftArm.rotateAngleZ = 0.0f;
		RightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		RightLeg.rotateAngleY = 0.0f;
		LeftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

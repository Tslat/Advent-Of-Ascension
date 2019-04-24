package net.nevermine.mob.model.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelLinger extends ModelBase {
	ModelRenderer head;
	ModelRenderer rightarm;
	ModelRenderer rightarm2;
	ModelRenderer rightarm3;
	ModelRenderer head2;
	ModelRenderer head3;

	public modelLinger() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 27)).addBox(-9.0f, -5.0f, 4.0f, 18, 2, 2);
		head.setRotationPoint(0.0f, 15.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 34, 33)).addBox(-4.0f, -14.0f, -8.0f, 6, 6, 6);
		rightarm.setRotationPoint(-7.0f, 11.0f, -1.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.7853982f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 44, 1)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightarm2.setRotationPoint(-7.0f, 11.0f, -1.0f);
		rightarm2.setTextureSize(64, 64);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 34, 1)).addBox(-2.0f, -8.0f, -6.0f, 2, 21, 2);
		rightarm3.setRotationPoint(-7.0f, 11.0f, -1.0f);
		rightarm3.setTextureSize(64, 64);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.7853982f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setRotationPoint(0.0f, 15.0f, 0.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 33)).addBox(-1.0f, -13.0f, 4.0f, 2, 8, 2);
		head3.setRotationPoint(0.0f, 15.0f, 0.0f);
		head3.setTextureSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		rightarm.render(par7);
		rightarm2.render(par7);
		rightarm3.render(par7);
		head2.render(par7);
		head3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.785f;
		rightarm.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		rightarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.785f;
		rightarm3.rotateAngleZ = 0.0f;
	}
}

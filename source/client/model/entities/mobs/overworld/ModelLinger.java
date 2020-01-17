package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLinger extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer rightArm;
	private ModelRenderer rightArm2;
	private ModelRenderer rightArm3;
	private ModelRenderer head2;
	private ModelRenderer head3;

	public ModelLinger() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 27)).addBox(-9.0f, -5.0f, 4.0f, 18, 2, 2);
		head.setRotationPoint(0.0f, 15.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 34, 33)).addBox(-4.0f, -14.0f, -8.0f, 6, 6, 6);
		rightArm.setRotationPoint(-7.0f, 11.0f, -1.0f);
		rightArm.setTextureSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.7853982f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 44, 1)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm2.setRotationPoint(-7.0f, 11.0f, -1.0f);
		rightArm2.setTextureSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 34, 1)).addBox(-2.0f, -8.0f, -6.0f, 2, 21, 2);
		rightArm3.setRotationPoint(-7.0f, 11.0f, -1.0f);
		rightArm3.setTextureSize(64, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.7853982f, 0.0f, 0.0f);
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
		rightArm.render(par7);
		rightArm2.render(par7);
		rightArm3.render(par7);
		head2.render(par7);
		head3.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.785f;
		rightArm.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		rightArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.785f;
		rightArm3.rotateAngleZ = 0.0f;
	}
}

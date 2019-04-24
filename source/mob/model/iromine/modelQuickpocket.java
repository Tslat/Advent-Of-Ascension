package net.nevermine.mob.model.iromine;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelQuickpocket extends ModelBase {
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer rightarm2;
	ModelRenderer leftarm2;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer head5;
	ModelRenderer head6;
	ModelRenderer head7;
	ModelRenderer head8;
	ModelRenderer head9;
	ModelRenderer head10;

	public modelQuickpocket() {
		textureWidth = 64;
		textureHeight = 32;
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 6, 8, 2);
		body.setRotationPoint(1.0f, 8.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 40, 16)).addBox(0.0f, -2.0f, -2.0f, 2, 2, 2);
		rightarm.setRotationPoint(-5.0f, 10.0f, 0.0f);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 40, 16)).addBox(-2.0f, -2.0f, -2.0f, 2, 2, 2);
		leftarm.setRotationPoint(5.0f, 10.0f, 0.0f);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 2, 8, 2);
		rightleg.setRotationPoint(-2.0f, 16.0f, 0.0f);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 2, 8, 2);
		leftleg.setRotationPoint(2.0f, 16.0f, 0.0f);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 40, 16)).addBox(-2.0f, -1.0f, -2.0f, 2, 12, 2);
		rightarm2.setRotationPoint(-4.0f, 11.0f, 0.0f);
		rightarm2.setTextureSize(64, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 40, 16)).addBox(0.0f, -1.0f, -2.0f, 2, 12, 2);
		leftarm2.setRotationPoint(4.0f, 11.0f, 0.0f);
		leftarm2.setTextureSize(64, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 9)).addBox(1.0f, 0.0f, -3.0f, 1, 2, 1);
		head2.setRotationPoint(0.0f, 8.0f, -1.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 17, 0)).addBox(-3.0f, -4.0f, 5.0f, 1, 1, 1);
		head3.setRotationPoint(0.0f, 8.0f, -1.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.7853982f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 17, 0)).addBox(2.0f, -4.0f, 5.0f, 1, 1, 1);
		head4.setRotationPoint(0.0f, 8.0f, -1.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.7853982f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 17, 0)).addBox(-3.0f, -3.0f, 2.0f, 1, 2, 4);
		head5.setRotationPoint(0.0f, 8.0f, -1.0f);
		head5.setTextureSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.7853982f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 17, 0)).addBox(2.0f, -3.0f, 2.0f, 1, 2, 4);
		head6.setRotationPoint(0.0f, 8.0f, -1.0f);
		head6.setTextureSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.7853982f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 17, 0)).addBox(-3.0f, -4.0f, 3.0f, 1, 1, 1);
		head7.setRotationPoint(0.0f, 8.0f, -1.0f);
		head7.setTextureSize(64, 32);
		head7.mirror = true;
		setRotation(head7, 0.7853982f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 17, 0)).addBox(2.0f, -4.0f, 3.0f, 1, 1, 1);
		head8.setRotationPoint(0.0f, 8.0f, -1.0f);
		head8.setTextureSize(64, 32);
		head8.mirror = true;
		setRotation(head8, 0.7853982f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, -4.0f, -3.0f, 4, 4, 4);
		head9.setRotationPoint(0.0f, 8.0f, -1.0f);
		head9.setTextureSize(64, 32);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 0, 9)).addBox(-2.0f, 0.0f, -3.0f, 1, 2, 1);
		head10.setRotationPoint(0.0f, 8.0f, -1.0f);
		head10.setTextureSize(64, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		rightarm2.render(par7);
		leftarm2.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
		head7.render(par7);
		head8.render(par7);
		head9.render(par7);
		head10.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head2.rotateAngleY = par4 / (float)(180f / Math.PI);
		head3.rotateAngleY = par4 / (float)(180f / Math.PI);
		head4.rotateAngleY = par4 / (float)(180f / Math.PI);
		head5.rotateAngleY = par4 / (float)(180f / Math.PI);
		head6.rotateAngleY = par4 / (float)(180f / Math.PI);
		head7.rotateAngleY = par4 / (float)(180f / Math.PI);
		head8.rotateAngleY = par4 / (float)(180f / Math.PI);
		head9.rotateAngleY = par4 / (float)(180f / Math.PI);
		head10.rotateAngleY = par4 / (float)(180f / Math.PI);
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm2.rotateAngleZ = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

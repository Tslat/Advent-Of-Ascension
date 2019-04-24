package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLivingFungi extends ModelBase {
	ModelRenderer p1;
	ModelRenderer p2;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body1;
	ModelRenderer head1;

	public ModelLivingFungi() {
		textureWidth = 128;
		textureHeight = 32;
		(p1 = new ModelRenderer(this, 64, 0)).addBox(-3.0f, -7.0f, -3.0f, 16, 12, 16);
		p1.setRotationPoint(-5.0f, -8.0f, -5.0f);
		p1.setTextureSize(128, 32);
		p1.mirror = true;
		setRotation(p1, 0.0f, 0.0f, 0.0f);
		(p2 = new ModelRenderer(this, 17, 21)).addBox(-4.0f, 0.0f, -2.0f, 18, 6, 5);
		p2.setRotationPoint(-5.0f, 0.0f, -1.0f);
		p2.setTextureSize(128, 32);
		p2.mirror = true;
		setRotation(p2, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 52, 1)).addBox(-1.0f, -2.0f, -1.0f, 2, 12, 2);
		rightarm.setRotationPoint(-7.0f, 8.0f, 0.0f);
		rightarm.setTextureSize(128, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 52, 1)).addBox(-1.0f, -2.0f, -1.0f, 2, 12, 2);
		leftarm.setRotationPoint(7.0f, 8.0f, 0.0f);
		leftarm.setTextureSize(128, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		rightleg.setRotationPoint(-3.0f, 16.0f, 0.0f);
		rightleg.setTextureSize(128, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leftleg.setRotationPoint(3.0f, 16.0f, 0.0f);
		leftleg.setTextureSize(128, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 25, 0)).addBox(-4.0f, 0.0f, -2.0f, 8, 10, 4);
		body1.setRotationPoint(0.0f, 6.0f, 0.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(head1 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -7.0f, -3.0f, 6, 5, 6);
		head1.setRotationPoint(0.0f, 3.0f, 2.0f);
		head1.setTextureSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		p1.render(par7);
		p2.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		body1.render(par7);
		head1.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

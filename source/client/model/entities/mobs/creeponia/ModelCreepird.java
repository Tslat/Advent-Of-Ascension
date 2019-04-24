package net.tslat.aoa3.client.model.entities.mobs.creeponia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCreepird extends ModelBase {
	ModelRenderer wingR1;
	ModelRenderer Head;
	ModelRenderer wingR2;
	ModelRenderer Shape1;
	ModelRenderer wingL1;
	ModelRenderer wingL2;
	ModelRenderer Shape2;

	public ModelCreepird() {
		textureWidth = 64;
		textureHeight = 32;
		(wingR1 = new ModelRenderer(this, 30, 20)).addBox(-10.0f, 0.0f, -4.0f, 8, 0, 8);
		wingR1.setRotationPoint(-6.0f, 17.0f, 0.0f);
		wingR1.setTextureSize(64, 32);
		wingR1.mirror = true;
		setRotation(wingR1, 0.0f, 0.0f, 0.0f);
		(Head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
		Head.setRotationPoint(0.0f, 17.0f, 0.0f);
		Head.setTextureSize(64, 32);
		Head.mirror = true;
		setRotation(Head, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 33, 0)).addBox(-2.0f, -1.0f, -4.0f, 2, 2, 8);
		wingR2.setRotationPoint(-6.0f, 17.0f, 0.0f);
		wingR2.setTextureSize(64, 32);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(Shape1 = new ModelRenderer(this, 30, 2)).addBox(-10.0f, -0.5f, -1.0f, 2, 1, 2);
		Shape1.setRotationPoint(4.0f, 17.0f, 0.0f);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0.0f, 0.0f, 0.0f);
		(wingL1 = new ModelRenderer(this, 30, 11)).addBox(2.0f, 0.0f, -4.0f, 8, 0, 8);
		wingL1.setRotationPoint(6.0f, 17.0f, 0.0f);
		wingL1.setTextureSize(64, 32);
		wingL1.mirror = true;
		setRotation(wingL1, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 33, 0)).addBox(0.0f, -1.0f, -4.0f, 2, 2, 8);
		wingL2.setRotationPoint(6.0f, 17.0f, 0.0f);
		wingL2.setTextureSize(64, 32);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
		(Shape2 = new ModelRenderer(this, 30, 2)).addBox(0.0f, -0.5f, -1.0f, 2, 1, 2);
		Shape2.setRotationPoint(4.0f, 17.0f, 0.0f);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		wingR1.render(par7);
		Head.render(par7);
		wingR2.render(par7);
		Shape1.render(par7);
		wingL1.render(par7);
		wingL2.render(par7);
		Shape2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		wingR1.rotateAngleZ = MathHelper.cos(par3 * 0.1f) * 3.1415927f * 0.15f;
		wingL1.rotateAngleZ = -wingR1.rotateAngleZ;
		wingR2.rotateAngleZ = MathHelper.cos(par3 * 0.1f) * 3.1415927f * 0.15f;
		wingL2.rotateAngleZ = -wingR1.rotateAngleZ;
	}
}

package net.nevermine.mob.model.greckon;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelSilencer extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer rightarm2;
	ModelRenderer leftarm2;
	ModelRenderer leftarm3;
	ModelRenderer rightarm3;
	ModelRenderer leftarm4;
	ModelRenderer rightarm4;
	ModelRenderer leftarm5;
	ModelRenderer rightarm5;
	ModelRenderer leftarm6;
	ModelRenderer rightarm6;
	ModelRenderer leftarm7;
	ModelRenderer rightarm7;

	public modelSilencer() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 0.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, 0.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 100, 22)).addBox(-8.0f, -12.0f, -1.0f, 2, 3, 2);
		rightarm.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 100, 22)).addBox(6.0f, -12.0f, -1.0f, 2, 3, 2);
		leftarm.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg.setRotationPoint(-2.0f, 12.0f, 0.0f);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg.setRotationPoint(2.0f, 12.0f, 0.0f);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 43, 11)).addBox(-5.0f, -2.0f, -2.0f, 6, 4, 4);
		rightarm2.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm2.setTextureSize(64, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 43, 11)).addBox(-1.0f, -2.0f, -2.0f, 6, 4, 4);
		leftarm2.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm2.setTextureSize(64, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(leftarm3 = new ModelRenderer(this, 63, 22)).addBox(5.0f, -4.0f, -2.0f, 4, 6, 4);
		leftarm3.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm3.setTextureSize(64, 32);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 63, 22)).addBox(-9.0f, -4.0f, -2.0f, 4, 6, 4);
		rightarm3.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm3.setTextureSize(64, 32);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0f, 0.0f, 0.0f);
		(leftarm4 = new ModelRenderer(this, 36, 0)).addBox(5.5f, -8.0f, -2.0f, 3, 3, 1);
		leftarm4.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm4.setTextureSize(64, 32);
		leftarm4.mirror = true;
		setRotation(leftarm4, 0.0f, 0.0f, 0.0f);
		(rightarm4 = new ModelRenderer(this, 36, 0)).addBox(-8.5f, -8.0f, -2.0f, 3, 3, 1);
		rightarm4.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm4.setTextureSize(64, 32);
		rightarm4.mirror = true;
		setRotation(rightarm4, 0.0f, 0.0f, 0.0f);
		(leftarm5 = new ModelRenderer(this, 115, 22)).addBox(4.0f, -12.0f, -1.0f, 1, 3, 2);
		leftarm5.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm5.setTextureSize(64, 32);
		leftarm5.mirror = true;
		setRotation(leftarm5, 0.0f, 0.0f, 0.0f);
		(rightarm5 = new ModelRenderer(this, 115, 22)).addBox(-10.0f, -12.0f, -1.0f, 1, 3, 2);
		rightarm5.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm5.setTextureSize(64, 32);
		rightarm5.mirror = true;
		setRotation(rightarm5, 0.0f, 0.0f, 0.0f);
		(leftarm6 = new ModelRenderer(this, 115, 22)).addBox(9.0f, -12.0f, -1.0f, 1, 3, 2);
		leftarm6.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm6.setTextureSize(64, 32);
		leftarm6.mirror = true;
		setRotation(leftarm6, 0.0f, 0.0f, 0.0f);
		(rightarm6 = new ModelRenderer(this, 115, 22)).addBox(-5.0f, -12.0f, -1.0f, 1, 3, 2);
		rightarm6.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm6.setTextureSize(64, 32);
		rightarm6.mirror = true;
		setRotation(rightarm6, 0.0f, 0.0f, 0.0f);
		(leftarm7 = new ModelRenderer(this, 81, 22)).addBox(4.0f, -9.0f, -1.0f, 6, 5, 2);
		leftarm7.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm7.setTextureSize(64, 32);
		leftarm7.mirror = true;
		setRotation(leftarm7, 0.0f, 0.0f, 0.0f);
		(rightarm7 = new ModelRenderer(this, 81, 22)).addBox(-10.0f, -9.0f, -1.0f, 6, 5, 2);
		rightarm7.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm7.setTextureSize(64, 32);
		rightarm7.mirror = true;
		setRotation(rightarm7, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		rightarm2.render(par7);
		leftarm2.render(par7);
		leftarm3.render(par7);
		rightarm3.render(par7);
		leftarm4.render(par7);
		rightarm4.render(par7);
		leftarm5.render(par7);
		rightarm5.render(par7);
		leftarm6.render(par7);
		rightarm6.render(par7);
		leftarm7.render(par7);
		rightarm7.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
	}
}

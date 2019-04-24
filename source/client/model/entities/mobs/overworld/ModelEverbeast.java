package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEverbeast extends ModelBase {
	ModelRenderer head;
	ModelRenderer body1;
	ModelRenderer rightarm1;
	ModelRenderer leftarm1;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body2;
	ModelRenderer rightarm2;
	ModelRenderer leftarm2;
	ModelRenderer leftarm3;
	ModelRenderer leftarm4;
	ModelRenderer leftarm5;
	ModelRenderer leftarm6;
	ModelRenderer head2;

	public ModelEverbeast() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 23, 0)).addBox(0.0f, -6.5f, -3.5f, 3, 7, 7);
		head.setRotationPoint(0.0f, 2.0f, -2.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 43, 0)).addBox(-4.0f, 0.0f, -2.0f, 12, 6, 4);
		body1.setRotationPoint(-2.0f, 0.0f, 0.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(rightarm1 = new ModelRenderer(this, 58, 16)).addBox(-2.0f, 10.0f, -1.0f, 2, 8, 2);
		rightarm1.setRotationPoint(-7.0f, 3.0f, 0.0f);
		rightarm1.setTextureSize(128, 32);
		rightarm1.mirror = true;
		setRotation(rightarm1, 0.0f, 0.0f, 0.0f);
		(leftarm1 = new ModelRenderer(this, 75, 0)).addBox(3.0f, 12.0f, -1.0f, 2, 8, 2);
		leftarm1.setRotationPoint(7.0f, 2.0f, 0.0f);
		leftarm1.setTextureSize(128, 32);
		leftarm1.mirror = true;
		setRotation(leftarm1, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg.setRotationPoint(-3.0f, 12.0f, 0.0f);
		rightleg.setTextureSize(128, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg.setRotationPoint(3.0f, 12.0f, 0.0f);
		leftleg.setTextureSize(128, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 22)).addBox(-4.0f, 0.0f, -2.0f, 8, 6, 4);
		body2.setRotationPoint(0.0f, 6.0f, 0.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 72, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightarm2.setRotationPoint(-7.0f, 3.0f, 0.0f);
		rightarm2.setTextureSize(128, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 93, 0)).addBox(-1.0f, -5.0f, -4.0f, 6, 17, 8);
		leftarm2.setRotationPoint(7.0f, 2.0f, 0.0f);
		leftarm2.setTextureSize(128, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(leftarm3 = new ModelRenderer(this, 84, 0)).addBox(-1.0f, 12.0f, 1.0f, 2, 4, 2);
		leftarm3.setRotationPoint(7.0f, 2.0f, 0.0f);
		leftarm3.setTextureSize(128, 32);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0f, 0.0f, 0.0f);
		(leftarm4 = new ModelRenderer(this, 75, 0)).addBox(3.0f, 12.0f, 2.0f, 2, 8, 2);
		leftarm4.setRotationPoint(7.0f, 2.0f, 0.0f);
		leftarm4.setTextureSize(128, 32);
		leftarm4.mirror = true;
		setRotation(leftarm4, 0.0f, 0.0f, 0.0f);
		(leftarm5 = new ModelRenderer(this, 75, 0)).addBox(3.0f, 12.0f, -4.0f, 2, 8, 2);
		leftarm5.setRotationPoint(7.0f, 2.0f, 0.0f);
		leftarm5.setTextureSize(128, 32);
		leftarm5.mirror = true;
		setRotation(leftarm5, 0.0f, 0.0f, 0.0f);
		(leftarm6 = new ModelRenderer(this, 84, 0)).addBox(-1.0f, 12.0f, -3.0f, 2, 4, 2);
		leftarm6.setRotationPoint(7.0f, 2.0f, 0.0f);
		leftarm6.setTextureSize(128, 32);
		leftarm6.mirror = true;
		setRotation(leftarm6, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 5, 6, 6);
		head2.setRotationPoint(0.0f, 2.0f, -2.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body1.render(par7);
		rightarm1.render(par7);
		leftarm1.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		body2.render(par7);
		rightarm2.render(par7);
		leftarm2.render(par7);
		leftarm3.render(par7);
		leftarm4.render(par7);
		leftarm5.render(par7);
		leftarm6.render(par7);
		head2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		head2.rotateAngleY = par4 / 57.295776f;
		head2.rotateAngleX = par5 / 54.11268f;
		rightarm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm1.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		leftarm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm1.rotateAngleZ = 0.0f;
		leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm2.rotateAngleZ = 0.0f;
		leftarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm3.rotateAngleZ = 0.0f;
		leftarm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm4.rotateAngleZ = 0.0f;
		leftarm5.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm5.rotateAngleZ = 0.0f;
		leftarm6.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm6.rotateAngleZ = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

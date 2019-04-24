package net.nevermine.mob.model.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelSoulscorne extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer leftleg2;
	ModelRenderer rightleg2;
	ModelRenderer leftleg3;
	ModelRenderer rightleg3;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer leftarm2;
	ModelRenderer rightarm2;
	ModelRenderer leftarm3;
	ModelRenderer rightarm3;
	ModelRenderer head2;
	ModelRenderer head3;

	public modelSoulscorne() {
		textureWidth = 64;
		textureHeight = 32;

		head = new ModelRenderer(this, 33, 0);
		head.addBox(1.0F, -5.0F, -4.0F, 2, 1, 8);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 58, 11);
		body.addBox(2.0F, 3.0F, -1.0F, 1, 6, 1);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightarm = new ModelRenderer(this, 47, 26);
		rightarm.addBox(-3.0F, 8.0F, -2.0F, 4, 1, 4);
		rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0F, 0.0F, 0.0F);
		leftarm = new ModelRenderer(this, 47, 26);
		leftarm.addBox(-1.0F, 8.0F, -2.0F, 4, 1, 4);
		leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0F, 0.0F, 0.0F);
		rightleg = new ModelRenderer(this, 0, 24);
		rightleg.addBox(-2.0F, 5.0F, -2.0F, 4, 2, 4);
		rightleg.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0F, 0.0F, 0.0F);
		leftleg = new ModelRenderer(this, 0, 24);
		leftleg.addBox(-2.0F, 5.0F, -2.0F, 4, 2, 4);
		leftleg.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0F, 0.0F, 0.0F);
		leftleg2 = new ModelRenderer(this, 0, 16);
		leftleg2.addBox(-2.0F, 7.0F, -2.0F, 4, 5, 2);
		leftleg2.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftleg2.setTextureSize(64, 32);
		leftleg2.mirror = true;
		setRotation(leftleg2, 0.0F, 0.0F, 0.0F);
		rightleg2 = new ModelRenderer(this, 0, 16);
		rightleg2.addBox(-2.0F, 7.0F, -2.0F, 4, 5, 2);
		rightleg2.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightleg2.setTextureSize(64, 32);
		rightleg2.mirror = true;
		setRotation(rightleg2, 0.0F, 0.0F, 0.0F);
		leftleg3 = new ModelRenderer(this, 0, 16);
		leftleg3.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 2);
		leftleg3.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftleg3.setTextureSize(64, 32);
		leftleg3.mirror = true;
		setRotation(leftleg3, 0.0F, 0.0F, 0.0F);
		rightleg3 = new ModelRenderer(this, 0, 16);
		rightleg3.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 2);
		rightleg3.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightleg3.setTextureSize(64, 32);
		rightleg3.mirror = true;
		setRotation(rightleg3, 0.0F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 17, 16);
		body2.addBox(-4.0F, 0.0F, 0.0F, 8, 12, 2);
		body2.setRotationPoint(0.0F, 0.0F, 0.0F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0F, 0.0F, 0.0F);
		body3 = new ModelRenderer(this, 36, 13);
		body3.addBox(-3.0F, 2.0F, -1.0F, 6, 1, 1);
		body3.setRotationPoint(0.0F, 0.0F, 0.0F);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0F, 0.0F, 0.0F);
		body4 = new ModelRenderer(this, 36, 13);
		body4.addBox(-3.0F, 9.0F, -1.0F, 6, 1, 1);
		body4.setRotationPoint(0.0F, 0.0F, 0.0F);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0F, 0.0F, 0.0F);
		body5 = new ModelRenderer(this, 58, 11);
		body5.addBox(-3.0F, 3.0F, -1.0F, 1, 6, 1);
		body5.setRotationPoint(0.0F, 0.0F, 0.0F);
		body5.setTextureSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0F, 0.0F, 0.0F);
		leftarm2 = new ModelRenderer(this, 43, 16);
		leftarm2.addBox(-1.0F, -2.0F, -2.0F, 4, 5, 4);
		leftarm2.setRotationPoint(5.0F, 2.0F, 0.0F);
		leftarm2.setTextureSize(64, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0F, 0.0F, 0.0F);
		rightarm2 = new ModelRenderer(this, 43, 16);
		rightarm2.addBox(-3.0F, -2.0F, -2.0F, 4, 5, 4);
		rightarm2.setRotationPoint(-5.0F, 2.0F, 0.0F);
		rightarm2.setTextureSize(64, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0F, 0.0F, 0.0F);
		leftarm3 = new ModelRenderer(this, 38, 25);
		leftarm3.addBox(0.0F, 3.0F, -1.0F, 2, 5, 2);
		leftarm3.setRotationPoint(5.0F, 2.0F, 0.0F);
		leftarm3.setTextureSize(64, 32);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0F, 0.0F, 0.0F);
		rightarm3 = new ModelRenderer(this, 38, 25);
		rightarm3.addBox(-2.0F, 3.0F, -1.0F, 2, 5, 2);
		rightarm3.setRotationPoint(-5.0F, 2.0F, 0.0F);
		rightarm3.setTextureSize(64, 32);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0F, 0.0F, 0.0F);
		head2 = new ModelRenderer(this, 0, 0);
		head2.addBox(-4.0F, -4.0F, -4.0F, 8, 4, 8);
		head2.setRotationPoint(0.0F, 0.0F, 0.0F);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0F, 0.0F, 0.0F);
		head3 = new ModelRenderer(this, 33, 0);
		head3.addBox(-3.0F, -5.0F, -4.0F, 2, 1, 8);
		head3.setRotationPoint(0.0F, 0.0F, 0.0F);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0F, 0.0F, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		leftleg2.render(par7);
		rightleg2.render(par7);
		leftleg3.render(par7);
		rightleg3.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		leftarm2.render(par7);
		rightarm2.render(par7);
		leftarm3.render(par7);
		rightarm3.render(par7);
		head2.render(par7);
		head3.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		rightleg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightleg.rotateAngleY = 0.0F;

		rightleg2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightleg2.rotateAngleY = 0.0F;

		rightleg3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightleg3.rotateAngleY = 0.0F;

		leftleg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);

		leftleg2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);

		leftleg3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);

		rightarm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm.rotateAngleZ = 0.0F;

		rightarm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm2.rotateAngleZ = 0.0F;

		rightarm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm3.rotateAngleZ = 0.0F;

		leftarm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm.rotateAngleZ = 0.0F;

		leftarm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm2.rotateAngleZ = 0.0F;

		leftarm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm3.rotateAngleZ = 0.0F;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelReaperTwins extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer body2;
	ModelRenderer rightarm2;
	ModelRenderer rightarm3;
	ModelRenderer rightarm4;
	ModelRenderer rightarm5;
	ModelRenderer rightarm6;
	ModelRenderer rightarm7;
	ModelRenderer rightarm8;
	ModelRenderer head2;
	ModelRenderer rightarm3x2;
	ModelRenderer rightarm4x2;
	ModelRenderer rightarm5x2;
	ModelRenderer rightarmx2;
	ModelRenderer rightarm6x2;
	ModelRenderer rightarm7x2;
	ModelRenderer rightarm8x2;

	public ModelReaperTwins() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(5.0f, -6.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 38)).addBox(-5.0f, 0.0f, -2.0f, 10, 13, 4);
		body.setRotationPoint(0.0f, 10.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 55, 2)).addBox(1.0f, -8.0f, -16.0f, 2, 3, 1);
		rightarm.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.8726646f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 37, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 16, 4);
		leftarm.setRotationPoint(5.0f, -4.0f, 0.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 12, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 16, 4);
		body2.setRotationPoint(0.0f, -6.0f, 0.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 37, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 16, 4);
		rightarm2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm2.setTextureSize(64, 64);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 55, 16)).addBox(1.0f, -17.0f, -12.0f, 2, 36, 2);
		rightarm3.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightarm3.setTextureSize(64, 64);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.8726646f, 0.0f, 0.0f);
		(rightarm4 = new ModelRenderer(this, 55, 2)).addBox(1.0f, -15.0f, -14.0f, 2, 3, 2);
		rightarm4.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightarm4.setTextureSize(64, 64);
		rightarm4.mirror = true;
		setRotation(rightarm4, 0.8726646f, 0.0f, 0.0f);
		(rightarm5 = new ModelRenderer(this, 55, 2)).addBox(1.0f, -14.0f, -16.0f, 2, 3, 2);
		rightarm5.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightarm5.setTextureSize(64, 64);
		rightarm5.mirror = true;
		setRotation(rightarm5, 0.8726646f, 0.0f, 0.0f);
		(rightarm6 = new ModelRenderer(this, 55, 2)).addBox(1.0f, -13.0f, -18.0f, 2, 3, 2);
		rightarm6.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightarm6.setTextureSize(64, 64);
		rightarm6.mirror = true;
		setRotation(rightarm6, 0.8726646f, 0.0f, 0.0f);
		(rightarm7 = new ModelRenderer(this, 55, 2)).addBox(1.0f, -11.0f, -19.0f, 2, 4, 1);
		rightarm7.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightarm7.setTextureSize(64, 64);
		rightarm7.mirror = true;
		setRotation(rightarm7, 0.8726646f, 0.0f, 0.0f);
		(rightarm8 = new ModelRenderer(this, 55, 2)).addBox(1.0f, -10.0f, -18.0f, 2, 4, 2);
		rightarm8.setRotationPoint(4.0f, -4.0f, 0.0f);
		rightarm8.setTextureSize(64, 64);
		rightarm8.mirror = true;
		setRotation(rightarm8, 0.8726646f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setRotationPoint(-6.0f, -6.0f, 0.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(rightarm3x2 = new ModelRenderer(this, 55, 16)).addBox(-2.0f, -17.0f, -12.0f, 2, 36, 2);
		rightarm3x2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm3x2.setTextureSize(64, 64);
		rightarm3x2.mirror = true;
		setRotation(rightarm3x2, 0.8726646f, 0.0f, 0.0f);
		(rightarm4x2 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -15.0f, -14.0f, 2, 3, 2);
		rightarm4x2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm4x2.setTextureSize(64, 64);
		rightarm4x2.mirror = true;
		setRotation(rightarm4x2, 0.8726646f, 0.0f, 0.0f);
		(rightarm5x2 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -14.0f, -16.0f, 2, 3, 2);
		rightarm5x2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm5x2.setTextureSize(64, 64);
		rightarm5x2.mirror = true;
		setRotation(rightarm5x2, 0.8726646f, 0.0f, 0.0f);
		(rightarmx2 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -8.0f, -16.0f, 2, 3, 1);
		rightarmx2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarmx2.setTextureSize(64, 64);
		rightarmx2.mirror = true;
		setRotation(rightarmx2, 0.8726646f, 0.0f, 0.0f);
		(rightarm6x2 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -13.0f, -18.0f, 2, 3, 2);
		rightarm6x2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm6x2.setTextureSize(64, 64);
		rightarm6x2.mirror = true;
		setRotation(rightarm6x2, 0.8726646f, 0.0f, 0.0f);
		(rightarm7x2 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -11.0f, -19.0f, 2, 4, 1);
		rightarm7x2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm7x2.setTextureSize(64, 64);
		rightarm7x2.mirror = true;
		setRotation(rightarm7x2, 0.8726646f, 0.0f, 0.0f);
		(rightarm8x2 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -10.0f, -18.0f, 2, 4, 2);
		rightarm8x2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm8x2.setTextureSize(64, 64);
		rightarm8x2.mirror = true;
		setRotation(rightarm8x2, 0.8726646f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		body2.render(par7);
		rightarm2.render(par7);
		rightarm3.render(par7);
		rightarm4.render(par7);
		rightarm5.render(par7);
		rightarm6.render(par7);
		rightarm7.render(par7);
		rightarm8.render(par7);
		head2.render(par7);
		rightarm3x2.render(par7);
		rightarm4x2.render(par7);
		rightarm5x2.render(par7);
		rightarmx2.render(par7);
		rightarm6x2.render(par7);
		rightarm7x2.render(par7);
		rightarm8x2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
	}
}

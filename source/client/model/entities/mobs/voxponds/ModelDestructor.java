package net.tslat.aoa3.client.model.entities.mobs.voxponds;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDestructor extends ModelBase {
	ModelRenderer head;
	ModelRenderer plate1;
	ModelRenderer rightarm1;
	ModelRenderer leftarm1;
	ModelRenderer rightleg1;
	ModelRenderer leftleg1;
	ModelRenderer body;
	ModelRenderer plate2;
	ModelRenderer leftarm2;
	ModelRenderer rightarm2;
	ModelRenderer rightarm3;
	ModelRenderer leftarm3;
	ModelRenderer rightarm4;
	ModelRenderer leftarm4;
	ModelRenderer rightleg5;
	ModelRenderer leftleg5;

	public ModelDestructor() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -10.0f, -4.0f, 8, 10, 8);
		head.setRotationPoint(0.0f, -5.0f, 0.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(plate1 = new ModelRenderer(this, 87, 0)).addBox(4.0f, 0.0f, -2.0f, 1, 3, 4);
		plate1.setRotationPoint(0.0f, -6.0f, 0.0f);
		plate1.setTextureSize(128, 32);
		plate1.mirror = true;
		setRotation(plate1, 0.0f, 0.0f, 0.0f);
		(rightarm1 = new ModelRenderer(this, 40, 23)).addBox(-5.0f, 8.0f, -2.0f, 2, 3, 4);
		rightarm1.setRotationPoint(-6.0f, -3.0f, 0.0f);
		rightarm1.setTextureSize(128, 32);
		rightarm1.mirror = true;
		setRotation(rightarm1, 0.0f, 0.0f, 0.0f);
		(leftarm1 = new ModelRenderer(this, 40, 23)).addBox(3.0f, 8.0f, -2.0f, 2, 3, 4);
		leftarm1.setRotationPoint(6.0f, -3.0f, 0.0f);
		leftarm1.setTextureSize(128, 32);
		leftarm1.mirror = true;
		setRotation(leftarm1, 0.0f, 0.0f, 0.0f);
		(rightleg1 = new ModelRenderer(this, 20, 20)).addBox(-1.0f, 3.0f, -3.0f, 2, 4, 2);
		rightleg1.setRotationPoint(-3.0f, 12.0f, 0.0f);
		rightleg1.setTextureSize(128, 32);
		rightleg1.mirror = true;
		setRotation(rightleg1, 0.0f, 0.0f, 0.0f);
		(leftleg1 = new ModelRenderer(this, 20, 20)).addBox(-1.0f, 3.0f, -3.0f, 2, 4, 2);
		leftleg1.setRotationPoint(3.0f, 12.0f, 0.0f);
		leftleg1.setTextureSize(128, 32);
		leftleg1.mirror = true;
		setRotation(leftleg1, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 61, 0)).addBox(-4.0f, 0.0f, -2.0f, 8, 18, 4);
		body.setRotationPoint(0.0f, -6.0f, 0.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(plate2 = new ModelRenderer(this, 87, 0)).addBox(-5.0f, 0.0f, -2.0f, 1, 3, 4);
		plate2.setRotationPoint(0.0f, -6.0f, 0.0f);
		plate2.setTextureSize(128, 32);
		plate2.mirror = true;
		setRotation(plate2, 0.0f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 40, 2)).addBox(-1.0f, -2.0f, -2.0f, 4, 16, 4);
		leftarm2.setRotationPoint(6.0f, -3.0f, 0.0f);
		leftarm2.setTextureSize(128, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 40, 2)).addBox(-3.0f, -2.0f, -2.0f, 4, 16, 4);
		rightarm2.setRotationPoint(-6.0f, -3.0f, 0.0f);
		rightarm2.setTextureSize(128, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 40, 23)).addBox(-5.0f, -2.0f, -2.0f, 2, 3, 4);
		rightarm3.setRotationPoint(-6.0f, -3.0f, 0.0f);
		rightarm3.setTextureSize(128, 32);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0f, 0.0f, 0.0f);
		(leftarm3 = new ModelRenderer(this, 40, 23)).addBox(3.0f, -2.0f, -2.0f, 2, 3, 4);
		leftarm3.setRotationPoint(6.0f, -3.0f, 0.0f);
		leftarm3.setTextureSize(128, 32);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0f, 0.0f, 0.0f);
		(rightarm4 = new ModelRenderer(this, 40, 23)).addBox(-5.0f, 3.0f, -2.0f, 2, 3, 4);
		rightarm4.setRotationPoint(-6.0f, -3.0f, 0.0f);
		rightarm4.setTextureSize(128, 32);
		rightarm4.mirror = true;
		setRotation(rightarm4, 0.0f, 0.0f, 0.0f);
		(leftarm4 = new ModelRenderer(this, 40, 23)).addBox(3.0f, 3.0f, -2.0f, 2, 3, 4);
		leftarm4.setRotationPoint(6.0f, -3.0f, 0.0f);
		leftarm4.setTextureSize(128, 32);
		leftarm4.mirror = true;
		setRotation(leftarm4, 0.0f, 0.0f, 0.0f);
		(rightleg5 = new ModelRenderer(this, 90, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg5.setRotationPoint(-3.0f, 12.0f, 0.0f);
		rightleg5.setTextureSize(128, 32);
		rightleg5.mirror = true;
		setRotation(rightleg5, 0.0f, 0.0f, 0.0f);
		(leftleg5 = new ModelRenderer(this, 90, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg5.setRotationPoint(3.0f, 12.0f, 0.0f);
		leftleg5.setTextureSize(128, 32);
		leftleg5.mirror = true;
		setRotation(leftleg5, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		plate1.render(par7);
		rightarm1.render(par7);
		leftarm1.render(par7);
		rightleg1.render(par7);
		leftleg1.render(par7);
		body.render(par7);
		plate2.render(par7);
		leftarm2.render(par7);
		rightarm2.render(par7);
		rightarm3.render(par7);
		leftarm3.render(par7);
		rightarm4.render(par7);
		leftarm4.render(par7);
		rightleg5.render(par7);
		leftleg5.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
	}
}

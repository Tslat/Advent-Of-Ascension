package net.nevermine.block.modelblocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class GuardianStatueBlue extends ModelBase implements ModelEternalBlock {
	ModelRenderer head;
	ModelRenderer rightarm1;
	ModelRenderer leftarm1;
	ModelRenderer rightarm2;
	ModelRenderer leftarm2;
	ModelRenderer rightarm3;
	ModelRenderer leftarm3;
	ModelRenderer rightarm4;
	ModelRenderer leftarm4;
	ModelRenderer rightarm5;
	ModelRenderer leftarm5;

	public GuardianStatueBlue() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-6.0f, -12.0f, -2.0f, 12, 12, 4);
		head.setRotationPoint(0.0f, 15.0f, 0.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightarm1 = new ModelRenderer(this, 89, 3)).addBox(-1.0f, 10.0f, -2.0f, 2, 4, 1);
		rightarm1.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightarm1.setTextureSize(128, 32);
		rightarm1.mirror = true;
		setRotation(rightarm1, -0.5205006f, -0.8179294f, 0.240525f);
		(leftarm1 = new ModelRenderer(this, 113, 3)).addBox(-1.0f, 10.0f, -2.0f, 2, 4, 1);
		leftarm1.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftarm1.setTextureSize(128, 32);
		leftarm1.mirror = true;
		setRotation(leftarm1, 0.0f, 0.7238469f, -0.3346075f);
		(rightarm2 = new ModelRenderer(this, 40, 3)).addBox(-1.0f, -2.0f, -4.0f, 2, 12, 8);
		rightarm2.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightarm2.setTextureSize(128, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, -0.5205006f, -0.8179294f, 0.240525f);
		(leftarm2 = new ModelRenderer(this, 64, 3)).addBox(-1.0f, -2.0f, -4.0f, 2, 12, 8);
		leftarm2.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftarm2.setTextureSize(128, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0.0f, 0.7238469f, -0.3346075f);
		(rightarm3 = new ModelRenderer(this, 89, 3)).addBox(-1.0f, 10.0f, -4.0f, 2, 4, 1);
		rightarm3.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightarm3.setTextureSize(128, 32);
		rightarm3.mirror = true;
		setRotation(rightarm3, -0.5205006f, -0.8179294f, 0.240525f);
		(leftarm3 = new ModelRenderer(this, 113, 3)).addBox(-1.0f, 10.0f, -4.0f, 2, 4, 1);
		leftarm3.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftarm3.setTextureSize(128, 32);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0f, 0.7238469f, -0.3346075f);
		(rightarm4 = new ModelRenderer(this, 89, 3)).addBox(-1.0f, 10.0f, 3.0f, 2, 4, 1);
		rightarm4.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightarm4.setTextureSize(128, 32);
		rightarm4.mirror = true;
		setRotation(rightarm4, -0.5205006f, -0.8179294f, 0.240525f);
		(leftarm4 = new ModelRenderer(this, 113, 3)).addBox(-1.0f, 10.0f, 3.0f, 2, 4, 1);
		leftarm4.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftarm4.setTextureSize(128, 32);
		leftarm4.mirror = true;
		setRotation(leftarm4, 0.0f, 0.7238469f, -0.3346075f);
		(rightarm5 = new ModelRenderer(this, 89, 3)).addBox(-1.0f, 10.0f, 1.0f, 2, 4, 1);
		rightarm5.setRotationPoint(-10.0f, 7.0f, 0.0f);
		rightarm5.setTextureSize(128, 32);
		rightarm5.mirror = true;
		setRotation(rightarm5, -0.5205006f, -0.8179294f, 0.240525f);
		(leftarm5 = new ModelRenderer(this, 113, 3)).addBox(-1.0f, 10.0f, 1.0f, 2, 4, 1);
		leftarm5.setRotationPoint(10.0f, 7.0f, 0.0f);
		leftarm5.setTextureSize(128, 32);
		leftarm5.mirror = true;
		setRotation(leftarm5, 0.0f, 0.7238469f, -0.3346075f);
	}

	public void render(final float f5) {
		head.render(f5);
		rightarm1.render(f5);
		leftarm1.render(f5);
		rightarm2.render(f5);
		leftarm2.render(f5);
		rightarm3.render(f5);
		leftarm3.render(f5);
		rightarm4.render(f5);
		leftarm4.render(f5);
		rightarm5.render(f5);
		leftarm5.render(f5);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

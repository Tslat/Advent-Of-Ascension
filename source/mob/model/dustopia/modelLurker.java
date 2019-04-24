package net.nevermine.mob.model.dustopia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class modelLurker extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer body6;
	ModelRenderer body7;
	ModelRenderer body8;
	ModelRenderer body9;
	ModelRenderer body10;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer head5;

	public modelLurker() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 44, 0)).addBox(5.0f, -15.0f, -5.0f, 2, 4, 2);
		head.setRotationPoint(0.0f, -1.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 35, 37)).addBox(12.0f, 9.0f, -11.0f, 1, 8, 2);
		body.setRotationPoint(0.0f, -2.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.6981317f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 0, 33)).addBox(-5.0f, 12.0f, -2.0f, 10, 10, 4);
		body2.setRotationPoint(0.0f, -2.0f, 0.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 26, 16)).addBox(-7.0f, 0.0f, -2.0f, 14, 12, 4);
		body3.setRotationPoint(0.0f, -2.0f, 0.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 35, 37)).addBox(-8.0f, 9.0f, -11.0f, 1, 8, 2);
		body4.setRotationPoint(0.0f, -2.0f, 0.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.6981317f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 0, 16)).addBox(-12.0f, -2.0f, -3.0f, 6, 6, 6);
		body5.setRotationPoint(0.0f, -2.0f, 0.0f);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 0, 16)).addBox(6.0f, -2.0f, -3.0f, 6, 6, 6);
		body6.setRotationPoint(0.0f, -2.0f, 0.0f);
		body6.setTextureSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 43, 34)).addBox(8.0f, 4.0f, -2.0f, 4, 12, 4);
		body7.setRotationPoint(0.0f, -2.0f, 0.0f);
		body7.setTextureSize(64, 64);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 43, 34)).addBox(-12.0f, 4.0f, -2.0f, 4, 12, 4);
		body8.setRotationPoint(0.0f, -2.0f, 0.0f);
		body8.setTextureSize(64, 64);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(body9 = new ModelRenderer(this, 35, 37)).addBox(7.0f, 9.0f, -11.0f, 1, 8, 2);
		body9.setRotationPoint(0.0f, -2.0f, 0.0f);
		body9.setTextureSize(64, 64);
		body9.mirror = true;
		setRotation(body9, 0.6981317f, 0.0f, 0.0f);
		(body10 = new ModelRenderer(this, 35, 37)).addBox(-13.0f, 9.0f, -11.0f, 1, 8, 2);
		body10.setRotationPoint(0.0f, -2.0f, 0.0f);
		body10.setTextureSize(64, 64);
		body10.mirror = true;
		setRotation(body10, 0.6981317f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -6.0f, 8, 10, 5);
		head2.setRotationPoint(0.0f, -1.0f, 0.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 44, 0)).addBox(-7.0f, -15.0f, -5.0f, 2, 4, 2);
		head3.setRotationPoint(0.0f, -1.0f, 0.0f);
		head3.setTextureSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 27, 0)).addBox(4.0f, -11.0f, -6.0f, 4, 6, 4);
		head4.setRotationPoint(0.0f, -1.0f, 0.0f);
		head4.setTextureSize(64, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 27, 0)).addBox(-8.0f, -11.0f, -6.0f, 4, 6, 4);
		head5.setRotationPoint(0.0f, -1.0f, 0.0f);
		head5.setTextureSize(64, 64);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		body6.render(par7);
		body7.render(par7);
		body8.render(par7);
		body9.render(par7);
		body10.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
	}
}

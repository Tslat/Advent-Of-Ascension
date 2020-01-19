package net.tslat.aoa3.client.model.entities.minions;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBlissard extends ModelBase {
	private ModelRenderer Wolfhead;
	private ModelRenderer body;
	private ModelRenderer Mane;
	private ModelRenderer Leg1;
	private ModelRenderer Leg2;
	private ModelRenderer Leg3;
	private ModelRenderer Leg4;
	private ModelRenderer Ear1;
	private ModelRenderer Ear2;
	private ModelRenderer nose;
	private ModelRenderer tail;
	private ModelRenderer tail2;
	private ModelRenderer tail3;
	private ModelRenderer tail4;
	private ModelRenderer tail5;
	private ModelRenderer tail6;
	private ModelRenderer body2;

	public ModelBlissard() {
		textureWidth = 64;
		textureHeight = 64;
		(Wolfhead = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -3.0f, -2.0f, 6, 6, 4);
		Wolfhead.setRotationPoint(-1.0f, 13.5f, -7.0f);
		Wolfhead.setTextureSize(64, 64);
		Wolfhead.mirror = true;
		setRotation(Wolfhead, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 21, 31)).addBox(-4.0f, -2.0f, -3.0f, 4, 8, 3);
		body.setRotationPoint(1.0f, 8.0f, 2.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(Mane = new ModelRenderer(this, 21, 0)).addBox(-4.0f, -3.0f, -3.0f, 8, 6, 7);
		Mane.setRotationPoint(-1.0f, 14.0f, -3.0f);
		Mane.setTextureSize(64, 64);
		Mane.mirror = true;
		setRotation(Mane, 1.570796f, 0.0f, 0.0f);
		(Leg1 = new ModelRenderer(this, 0, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
		Leg1.setRotationPoint(-2.5f, 16.0f, 7.0f);
		Leg1.setTextureSize(64, 64);
		Leg1.mirror = true;
		setRotation(Leg1, 0.0f, 0.0f, 0.0f);
		(Leg2 = new ModelRenderer(this, 0, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
		Leg2.setRotationPoint(0.5f, 16.0f, 7.0f);
		Leg2.setTextureSize(64, 64);
		Leg2.mirror = true;
		setRotation(Leg2, 0.0f, 0.0f, 0.0f);
		(Leg3 = new ModelRenderer(this, 0, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
		Leg3.setRotationPoint(-2.5f, 16.0f, -4.0f);
		Leg3.setTextureSize(64, 64);
		Leg3.mirror = true;
		setRotation(Leg3, 0.0f, 0.0f, 0.0f);
		(Leg4 = new ModelRenderer(this, 0, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 8, 2);
		Leg4.setRotationPoint(0.5f, 16.0f, -4.0f);
		Leg4.setTextureSize(64, 64);
		Leg4.mirror = true;
		setRotation(Leg4, 0.0f, 0.0f, 0.0f);
		(Ear1 = new ModelRenderer(this, 45, 14)).addBox(-5.0f, -6.0f, 0.0f, 4, 6, 1);
		Ear1.setRotationPoint(-1.0f, 13.5f, -7.0f);
		Ear1.setTextureSize(64, 64);
		Ear1.mirror = true;
		setRotation(Ear1, 0.0f, 0.0f, 0.0f);
		(Ear2 = new ModelRenderer(this, 45, 14)).addBox(1.0f, -6.0f, 0.0f, 4, 6, 1);
		Ear2.setRotationPoint(-1.0f, 13.5f, -7.0f);
		Ear2.setTextureSize(64, 64);
		Ear2.mirror = true;
		setRotation(Ear2, 0.0f, 0.0f, 0.0f);
		(nose = new ModelRenderer(this, 0, 10)).addBox(-2.0f, 0.0f, -5.0f, 3, 3, 4);
		nose.setRotationPoint(-0.5f, 13.5f, -7.0f);
		nose.setTextureSize(64, 64);
		nose.mirror = true;
		setRotation(nose, 0.0f, 0.0f, 0.0f);
		(tail = new ModelRenderer(this, 9, 18)).addBox(-1.0f, 7.0f, -1.0f, 2, 3, 2);
		tail.setRotationPoint(-1.0f, 10.9f, 7.0f);
		tail.setTextureSize(64, 64);
		tail.mirror = true;
		setRotation(tail, 1.919862f, 0.0f, 0.0f);
		(tail2 = new ModelRenderer(this, 9, 31)).addBox(-1.0f, 0.0f, -1.0f, 2, 7, 3);
		tail2.setRotationPoint(-1.0f, 10.9f, 7.0f);
		tail2.setTextureSize(64, 64);
		tail2.mirror = true;
		setRotation(tail2, 1.919862f, 0.0f, 0.0f);
		(tail3 = new ModelRenderer(this, 9, 31)).addBox(-1.0f, 0.0f, -1.0f, 2, 7, 3);
		tail3.setRotationPoint(-1.0f, 16.0f, 7.0f);
		tail3.setTextureSize(64, 64);
		tail3.mirror = true;
		setRotation(tail3, 1.130069f, 0.0f, 0.0f);
		(tail4 = new ModelRenderer(this, 9, 18)).addBox(-1.0f, 7.0f, -1.0f, 2, 3, 2);
		tail4.setRotationPoint(-1.0f, 16.0f, 7.0f);
		tail4.setTextureSize(64, 64);
		tail4.mirror = true;
		setRotation(tail4, 1.130069f, 0.0f, 0.0f);
		(tail5 = new ModelRenderer(this, 9, 31)).addBox(-1.0f, 0.0f, -1.0f, 2, 7, 3);
		tail5.setRotationPoint(-1.0f, 13.3f, 7.0f);
		tail5.setTextureSize(64, 64);
		tail5.mirror = true;
		setRotation(tail5, 1.48353f, 0.0f, 0.0f);
		(tail6 = new ModelRenderer(this, 9, 18)).addBox(-1.0f, 7.0f, -1.0f, 2, 3, 2);
		tail6.setRotationPoint(-1.0f, 13.3f, 7.0f);
		tail6.setTextureSize(64, 64);
		tail6.mirror = true;
		setRotation(tail6, 1.48353f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 18, 14)).addBox(-4.0f, -2.0f, -3.0f, 6, 9, 6);
		body2.setRotationPoint(0.0f, 14.0f, 2.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Wolfhead.render(par7);
		body.render(par7);
		Mane.render(par7);
		Leg1.render(par7);
		Leg2.render(par7);
		Leg3.render(par7);
		Leg4.render(par7);
		Ear1.render(par7);
		Ear2.render(par7);
		nose.render(par7);
		tail.render(par7);
		tail2.render(par7);
		tail3.render(par7);
		tail4.render(par7);
		tail5.render(par7);
		tail6.render(par7);
		body2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		Leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		Leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		Leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		Leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
	}
}

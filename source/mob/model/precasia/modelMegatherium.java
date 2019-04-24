package net.nevermine.mob.model.precasia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelMegatherium extends ModelBase {
	ModelRenderer body1;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer head1;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer head5;
	ModelRenderer head6;
	ModelRenderer head7;
	ModelRenderer head8;
	ModelRenderer head9;
	ModelRenderer head10;
	ModelRenderer head11;
	ModelRenderer body2;

	public modelMegatherium() {
		textureWidth = 128;
		textureHeight = 32;
		(body1 = new ModelRenderer(this, 28, 20)).addBox(-1.0f, 2.0f, 4.0f, 2, 10, 2);
		body1.setRotationPoint(0.0f, 7.0f, 9.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.8726646f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 18)).addBox(-3.0f, 0.0f, -2.0f, 4, 10, 4);
		leg1.setRotationPoint(-3.0f, 14.0f, 13.0f);
		leg1.setTextureSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 18)).addBox(-1.0f, 0.0f, -2.0f, 4, 10, 4);
		leg2.setRotationPoint(3.0f, 14.0f, 13.0f);
		leg2.setTextureSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 18)).addBox(-3.0f, 0.0f, -3.0f, 4, 10, 4);
		leg3.setRotationPoint(-3.0f, 14.0f, 1.0f);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 18)).addBox(-1.0f, 0.0f, -3.0f, 4, 10, 4);
		leg4.setRotationPoint(3.0f, 14.0f, 1.0f);
		leg4.setTextureSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(head1 = new ModelRenderer(this, 109, 14)).addBox(-1.0f, -13.0f, -8.0f, 2, 1, 3);
		head1.setRotationPoint(0.0f, 4.0f, -2.0f);
		head1.setTextureSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 1.396263f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 36, 0)).addBox(-3.0f, -9.0f, -9.0f, 6, 3, 5);
		head2.setRotationPoint(0.0f, 4.0f, -2.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 1.570796f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 93, 0)).addBox(1.0f, -13.0f, -5.0f, 1, 1, 3);
		head3.setRotationPoint(0.0f, 4.0f, -2.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 2.443461f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 93, 0)).addBox(-2.0f, -13.0f, -5.0f, 1, 1, 3);
		head4.setRotationPoint(0.0f, 4.0f, -2.0f);
		head4.setTextureSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 2.443461f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 92, 8)).addBox(0.5f, -11.0f, -8.0f, 2, 2, 3);
		head5.setRotationPoint(0.0f, 4.0f, -2.0f);
		head5.setTextureSize(128, 32);
		head5.mirror = true;
		setRotation(head5, 1.919862f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 92, 8)).addBox(-2.5f, -11.0f, -8.0f, 2, 2, 3);
		head6.setRotationPoint(0.0f, 4.0f, -2.0f);
		head6.setTextureSize(128, 32);
		head6.mirror = true;
		setRotation(head6, 1.919862f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 101, 19)).addBox(-7.0f, -7.0f, -2.0f, 5, 10, 1);
		head7.setRotationPoint(0.0f, 4.0f, -2.0f);
		head7.setTextureSize(128, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 109, 0)).addBox(-3.0f, -6.0f, -11.0f, 6, 4, 3);
		head8.setRotationPoint(0.0f, 4.0f, -2.0f);
		head8.setTextureSize(128, 32);
		head8.mirror = true;
		setRotation(head8, 0.4363323f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 109, 8)).addBox(-2.0f, -10.0f, -10.0f, 4, 2, 3);
		head9.setRotationPoint(0.0f, 4.0f, -2.0f);
		head9.setTextureSize(128, 32);
		head9.mirror = true;
		setRotation(head9, 0.9599311f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -10.0f, 8, 8, 10);
		head10.setRotationPoint(0.0f, 4.0f, -2.0f);
		head10.setTextureSize(128, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
		(head11 = new ModelRenderer(this, 115, 19)).addBox(2.0f, -7.0f, -2.0f, 5, 10, 1);
		head11.setRotationPoint(0.0f, 4.0f, -2.0f);
		head11.setTextureSize(128, 32);
		head11.mirror = true;
		setRotation(head11, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 50, 2)).addBox(-6.0f, -10.0f, -7.0f, 12, 18, 12);
		body2.setRotationPoint(0.0f, 7.0f, 8.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body1.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		head1.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
		head7.render(par7);
		head8.render(par7);
		head9.render(par7);
		head10.render(par7);
		head11.render(par7);
		body2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg1.rotateAngleY = 0.0f;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

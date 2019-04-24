package net.tslat.aoa3.client.model.entities.mobs.lelyetia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGrobbler extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer head2;
	ModelRenderer body2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer head5;
	ModelRenderer head6;
	ModelRenderer head7;
	ModelRenderer head8;
	ModelRenderer head9;
	ModelRenderer head10;
	ModelRenderer body3;
	ModelRenderer body4;

	public ModelGrobbler() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 35)).addBox(5.0f, 0.0f, 1.0f, 1, 2, 1);
		head.setRotationPoint(0.0f, 11.0f, -8.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 0, 2)).addBox(-6.0f, -10.0f, -7.0f, 12, 9, 2);
		body.setRotationPoint(0.0f, 11.0f, 15.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 24)).addBox(-3.0f, 0.0f, -2.0f, 4, 6, 4);
		leg1.setRotationPoint(-3.0f, 18.0f, 12.0f);
		leg1.setTextureSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 24)).addBox(-1.0f, 0.0f, -2.0f, 4, 6, 4);
		leg2.setRotationPoint(3.0f, 18.0f, 12.0f);
		leg2.setTextureSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 2, 51)).addBox(-3.0f, 0.0f, -3.0f, 4, 8, 4);
		leg3.setRotationPoint(-3.0f, 16.0f, 0.0f);
		leg3.setTextureSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 2, 51)).addBox(-1.0f, 0.0f, -3.0f, 4, 8, 4);
		leg4.setRotationPoint(3.0f, 16.0f, 0.0f);
		leg4.setTextureSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 19, 51)).addBox(-6.0f, -4.0f, -6.0f, 12, 3, 10);
		head2.setRotationPoint(0.0f, 12.0f, -8.0f);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 45, 32)).addBox(-3.0f, -9.0f, -3.0f, 6, 10, 3);
		body2.setRotationPoint(0.0f, 5.0f, 12.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 35)).addBox(-6.0f, 2.0f, -6.0f, 12, 3, 10);
		head3.setRotationPoint(0.0f, 11.0f, -8.0f);
		head3.setTextureSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 0, 35)).addBox(5.0f, 0.0f, -5.0f, 1, 2, 1);
		head4.setRotationPoint(0.0f, 11.0f, -8.0f);
		head4.setTextureSize(64, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 0, 35)).addBox(5.0f, 0.0f, -3.0f, 1, 2, 1);
		head5.setRotationPoint(0.0f, 11.0f, -8.0f);
		head5.setTextureSize(64, 64);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 0, 35)).addBox(5.0f, 0.0f, -1.0f, 1, 2, 1);
		head6.setRotationPoint(0.0f, 11.0f, -8.0f);
		head6.setTextureSize(64, 64);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 0, 35)).addBox(-6.0f, 0.0f, 1.0f, 1, 2, 1);
		head7.setRotationPoint(0.0f, 11.0f, -8.0f);
		head7.setTextureSize(64, 64);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 0, 35)).addBox(-6.0f, 0.0f, -1.0f, 1, 2, 1);
		head8.setRotationPoint(0.0f, 11.0f, -8.0f);
		head8.setTextureSize(64, 64);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 0, 35)).addBox(-6.0f, 0.0f, -3.0f, 1, 2, 1);
		head9.setRotationPoint(0.0f, 11.0f, -8.0f);
		head9.setTextureSize(64, 64);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 0, 35)).addBox(-6.0f, 0.0f, -5.0f, 1, 2, 1);
		head10.setRotationPoint(0.0f, 11.0f, -8.0f);
		head10.setTextureSize(64, 64);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 21, 6)).addBox(-6.0f, -10.0f, -7.0f, 12, 18, 8);
		body3.setRotationPoint(0.0f, 9.0f, 6.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 1.570796f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 45, 32)).addBox(-3.0f, -9.0f, -3.0f, 6, 16, 3);
		body4.setRotationPoint(0.0f, 6.0f, -4.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 1.570796f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		head2.render(par7);
		body2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
		head7.render(par7);
		head8.render(par7);
		head9.render(par7);
		head10.render(par7);
		body3.render(par7);
		body4.render(par7);
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

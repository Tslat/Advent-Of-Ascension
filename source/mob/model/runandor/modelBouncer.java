package net.nevermine.mob.model.runandor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelBouncer extends ModelBase {
	ModelRenderer body;
	ModelRenderer legp1;
	ModelRenderer legp2;
	ModelRenderer legp3;
	ModelRenderer legp4;
	ModelRenderer head;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;

	public modelBouncer() {
		textureWidth = 64;
		textureHeight = 32;
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, 0.0f, 8, 9, 4);
		body.setRotationPoint(0.0f, 4.0f, -1.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(legp1 = new ModelRenderer(this, 47, 24)).addBox(1.0f, 12.0f, -6.0f, 2, 2, 6);
		legp1.setRotationPoint(0.0f, 10.0f, 0.0f);
		legp1.setTextureSize(64, 32);
		legp1.mirror = true;
		setRotation(legp1, 0.0f, 0.0f, 0.0f);
		(legp2 = new ModelRenderer(this, 33, 1)).addBox(-1.0f, 1.0f, -1.0f, 2, 9, 4);
		legp2.setRotationPoint(0.0f, 10.0f, 0.0f);
		legp2.setTextureSize(64, 32);
		legp2.mirror = true;
		setRotation(legp2, -0.3490659f, 0.0f, 0.0f);
		(legp3 = new ModelRenderer(this, 47, 13)).addBox(-2.0f, 7.0f, -6.0f, 4, 6, 4);
		legp3.setRotationPoint(0.0f, 10.0f, 0.0f);
		legp3.setTextureSize(64, 32);
		legp3.mirror = true;
		setRotation(legp3, 0.1745329f, 0.0f, 0.0f);
		(legp4 = new ModelRenderer(this, 47, 24)).addBox(-3.0f, 12.0f, -6.0f, 2, 2, 6);
		legp4.setRotationPoint(0.0f, 10.0f, 0.0f);
		legp4.setTextureSize(64, 32);
		legp4.mirror = true;
		setRotation(legp4, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 9.0f, -5.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 16)).addBox(-4.0f, -8.0f, 4.0f, 0, 8, 8);
		head2.setRotationPoint(8.0f, 17.0f, -5.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, -1.570796f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 16)).addBox(-4.0f, -8.0f, -4.0f, 0, 8, 8);
		head3.setRotationPoint(0.0f, 17.0f, -5.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 0, 16)).addBox(-4.0f, -8.0f, -4.0f, 0, 8, 8);
		head4.setRotationPoint(8.0f, 17.0f, -5.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body.render(par7);
		legp1.render(par7);
		legp2.render(par7);
		legp3.render(par7);
		legp4.render(par7);
		head.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		legp1.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		legp2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2 - 0.349f;
		legp3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2 + 0.174f;
		legp4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

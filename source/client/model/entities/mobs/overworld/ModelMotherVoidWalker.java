package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelMotherVoidWalker extends ModelBase {
	ModelRenderer body;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer body2;
	ModelRenderer head3;
	ModelRenderer head2;
	ModelRenderer headfin2;
	ModelRenderer head1;
	ModelRenderer headfin1;
	ModelRenderer head4;

	public ModelMotherVoidWalker() {
		textureWidth = 64;
		textureHeight = 32;
		(body = new ModelRenderer(this, 16, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		body.setRotationPoint(7.0f, 6.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg.setRotationPoint(-6.0f, 12.0f, 0.0f);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg.setRotationPoint(6.0f, 12.0f, 0.0f);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		body2.setRotationPoint(-7.0f, 6.0f, 0.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head3.setRotationPoint(9.0f, 6.0f, 0.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setRotationPoint(-9.0f, 6.0f, 0.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(headfin2 = new ModelRenderer(this, 0, 0)).addBox(-1.0f, -10.0f, -4.0f, 2, 2, 8);
		headfin2.setRotationPoint(-9.0f, 6.0f, 0.0f);
		headfin2.setTextureSize(64, 32);
		headfin2.mirror = true;
		setRotation(headfin2, 0.0f, 0.0f, 0.0f);
		(head1 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head1.setRotationPoint(0.0f, 10.0f, 0.0f);
		head1.setTextureSize(64, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(headfin1 = new ModelRenderer(this, 0, 0)).addBox(-1.0f, -10.0f, -4.0f, 2, 2, 8);
		headfin1.setRotationPoint(0.0f, 10.0f, 0.0f);
		headfin1.setTextureSize(64, 32);
		headfin1.mirror = true;
		setRotation(headfin1, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 0, 0)).addBox(-1.0f, -10.0f, -4.0f, 2, 2, 8);
		head4.setRotationPoint(9.0f, 6.0f, 0.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		body.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		body2.render(par7);
		head3.render(par7);
		head2.render(par7);
		headfin2.render(par7);
		head1.render(par7);
		headfin1.render(par7);
		head4.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

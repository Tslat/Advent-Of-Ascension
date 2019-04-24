package net.tslat.aoa3.client.model.entities.boss.elusive;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelElusive extends ModelBase {
	ModelRenderer head4;
	ModelRenderer body3;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head1;
	ModelRenderer head11;
	ModelRenderer head6;
	ModelRenderer head7;
	ModelRenderer head8;
	ModelRenderer body;
	ModelRenderer body2;
	ModelRenderer head5;
	ModelRenderer head9;
	ModelRenderer head10;

	public ModelElusive() {
		textureWidth = 64;
		textureHeight = 32;
		(head4 = new ModelRenderer(this, 0, 12)).addBox(-4.0f, -5.0f, 3.0f, 8, 2, 1);
		head4.setRotationPoint(0.0f, 6.0f, -3.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 56, 4)).addBox(2.0f, -6.0f, 2.0f, 2, 12, 2);
		body3.setRotationPoint(0.0f, 0.0f, 3.0f);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 40, 0)).addBox(-3.0f, -2.0f, -2.0f, 4, 20, 4);
		rightarm.setRotationPoint(-5.0f, 4.0f, 2.0f);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 40, 0)).addBox(-1.0f, -2.0f, -2.0f, 4, 20, 4);
		leftarm.setRotationPoint(5.0f, 4.0f, 2.0f);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg.setRotationPoint(-2.0f, 12.0f, 0.0f);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg.setRotationPoint(2.0f, 12.0f, 0.0f);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 50, 25)).addBox(4.0f, -8.0f, -6.0f, 2, 2, 5);
		head2.setRotationPoint(0.0f, 6.0f, -3.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 26, 0)).addBox(2.0f, 0.0f, -3.0f, 2, 3, 2);
		head3.setRotationPoint(0.0f, 6.0f, -3.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head1 = new ModelRenderer(this, 0, 12)).addBox(-4.0f, -5.0f, 2.0f, 8, 2, 1);
		head1.setRotationPoint(0.0f, 6.0f, -3.0f);
		head1.setTextureSize(64, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(head11 = new ModelRenderer(this, 22, 14)).addBox(-4.0f, -4.0f, -4.0f, 8, 1, 0);
		head11.setRotationPoint(0.0f, 6.0f, -3.0f);
		head11.setTextureSize(64, 32);
		head11.mirror = true;
		setRotation(head11, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 26, 0)).addBox(-4.0f, 0.0f, -3.0f, 2, 3, 2);
		head6.setRotationPoint(0.0f, 6.0f, -3.0f);
		head6.setTextureSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 3, 8);
		head7.setRotationPoint(0.0f, 6.0f, -3.0f);
		head7.setTextureSize(64, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 50, 25)).addBox(-6.0f, -8.0f, -6.0f, 2, 2, 5);
		head8.setRotationPoint(0.0f, 6.0f, -3.0f);
		head8.setTextureSize(64, 32);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, 0.0f, 3.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 56, 4)).addBox(-4.0f, -6.0f, 2.0f, 2, 12, 2);
		body2.setRotationPoint(0.0f, 0.0f, 3.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -3.0f, -4.0f, 8, 3, 8);
		head5.setRotationPoint(0.0f, 6.0f, -3.0f);
		head5.setTextureSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 22, 6)).addBox(-4.0f, -4.0f, -4.0f, 0, 1, 6);
		head9.setRotationPoint(0.0f, 6.0f, -3.0f);
		head9.setTextureSize(64, 32);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 22, 6)).addBox(4.0f, -4.0f, -4.0f, 0, 1, 6);
		head10.setRotationPoint(0.0f, 6.0f, -3.0f);
		head10.setTextureSize(64, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head4.render(par7);
		body3.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		head2.render(par7);
		head3.render(par7);
		head1.render(par7);
		head11.render(par7);
		head6.render(par7);
		head7.render(par7);
		head8.render(par7);
		body.render(par7);
		body2.render(par7);
		head5.render(par7);
		head9.render(par7);
		head10.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

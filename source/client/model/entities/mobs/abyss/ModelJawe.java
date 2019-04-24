package net.tslat.aoa3.client.model.entities.mobs.abyss;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelJawe extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer body2;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer head5;
	ModelRenderer head6;
	ModelRenderer head8;
	ModelRenderer head9;
	ModelRenderer head10;
	ModelRenderer head11;
	ModelRenderer head12;
	ModelRenderer head13;
	ModelRenderer leg4p2;
	ModelRenderer leg3p2;

	public ModelJawe() {
		textureWidth = 64;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 11)).addBox(-3.0f, -7.0f, -4.0f, 6, 4, 6);
		head.setRotationPoint(0.0f, 16.0f, -8.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 22, 13)).addBox(-6.0f, -5.0f, -7.0f, 12, 10, 9);
		body.setRotationPoint(0.0f, 16.0f, 7.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 18, 8)).addBox(-0.5f, 6.0f, 2.0f, 0, 2, 6);
		leg3.setRotationPoint(-5.0f, 16.0f, -4.0f);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 18, 8)).addBox(1.5f, 6.0f, 2.0f, 0, 2, 6);
		leg4.setRotationPoint(4.0f, 16.0f, -4.0f);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 28, 1)).addBox(-6.0f, -5.0f, -7.0f, 12, 5, 6);
		body2.setRotationPoint(0.0f, 16.0f, 1.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(3.0f, -3.0f, 0.0f, 1, 2, 1);
		head2.setRotationPoint(0.0f, 16.0f, -8.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -2.0f, -6.0f, 6, 2, 8);
		head3.setRotationPoint(0.0f, 16.0f, -8.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 3, 5)).addBox(-2.0f, -3.0f, -6.0f, 1, 1, 1);
		head4.setRotationPoint(0.0f, 16.0f, -8.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 0, 0)).addBox(3.0f, -3.0f, -4.0f, 1, 2, 1);
		head5.setRotationPoint(0.0f, 16.0f, -8.0f);
		head5.setTextureSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 0, 0)).addBox(3.0f, -3.0f, -2.0f, 1, 2, 1);
		head6.setRotationPoint(0.0f, 16.0f, -8.0f);
		head6.setTextureSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -3.0f, 0.0f, 1, 2, 1);
		head8.setRotationPoint(0.0f, 16.0f, -8.0f);
		head8.setTextureSize(64, 32);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -3.0f, -2.0f, 1, 2, 1);
		head9.setRotationPoint(0.0f, 16.0f, -8.0f);
		head9.setTextureSize(64, 32);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -3.0f, -4.0f, 1, 2, 1);
		head10.setRotationPoint(0.0f, 16.0f, -8.0f);
		head10.setTextureSize(64, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
		(head11 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -3.0f, -6.0f, 1, 2, 1);
		head11.setRotationPoint(0.0f, 16.0f, -8.0f);
		head11.setTextureSize(64, 32);
		head11.mirror = true;
		setRotation(head11, 0.0f, 0.0f, 0.0f);
		(head12 = new ModelRenderer(this, 0, 0)).addBox(3.0f, -3.0f, -6.0f, 1, 2, 1);
		head12.setRotationPoint(0.0f, 16.0f, -8.0f);
		head12.setTextureSize(64, 32);
		head12.mirror = true;
		setRotation(head12, 0.0f, 0.0f, 0.0f);
		(head13 = new ModelRenderer(this, 3, 5)).addBox(1.0f, -3.0f, -6.0f, 1, 1, 1);
		head13.setRotationPoint(0.0f, 16.0f, -8.0f);
		head13.setTextureSize(64, 32);
		head13.mirror = true;
		setRotation(head13, 0.0f, 0.0f, 0.0f);
		(leg4p2 = new ModelRenderer(this, 0, 21)).addBox(0.0f, 0.0f, -1.0f, 3, 8, 3);
		leg4p2.setRotationPoint(4.0f, 16.0f, -4.0f);
		leg4p2.setTextureSize(64, 32);
		leg4p2.mirror = true;
		setRotation(leg4p2, 0.0f, 0.0f, 0.0f);
		(leg3p2 = new ModelRenderer(this, 0, 21)).addBox(-2.0f, 0.0f, -1.0f, 3, 8, 3);
		leg3p2.setRotationPoint(-5.0f, 16.0f, -4.0f);
		leg3p2.setTextureSize(64, 32);
		leg3p2.mirror = true;
		setRotation(leg3p2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		body2.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
		head8.render(par7);
		head9.render(par7);
		head10.render(par7);
		head11.render(par7);
		head12.render(par7);
		head13.render(par7);
		leg4p2.render(par7);
		leg3p2.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3.rotateAngleY = 0.0f;
		leg3p2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3p2.rotateAngleY = 0.0f;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4p2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

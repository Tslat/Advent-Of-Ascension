package net.nevermine.mob.model.voxponds;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelGadgetoid extends ModelBase {
	ModelRenderer head;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer rightleg2;
	ModelRenderer leftleg2;
	ModelRenderer TurningHead;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer head5;
	ModelRenderer head6;
	ModelRenderer head7;
	ModelRenderer head8;
	ModelRenderer head9;
	ModelRenderer head10;
	ModelRenderer head11;
	ModelRenderer head12;

	public modelGadgetoid() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, 0.0f, -4.0f, 6, 2, 6);
		head.setRotationPoint(1.0f, 5.0f, 1.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg.setRotationPoint(-6.0f, 12.0f, 6.0f);
		rightleg.setTextureSize(128, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg.setRotationPoint(6.0f, 12.0f, 6.0f);
		leftleg.setTextureSize(128, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(rightleg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightleg2.setRotationPoint(-6.0f, 12.0f, -6.0f);
		rightleg2.setTextureSize(128, 32);
		rightleg2.mirror = true;
		setRotation(rightleg2, 0.0f, 0.0f, 0.0f);
		(leftleg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftleg2.setRotationPoint(6.0f, 12.0f, -6.0f);
		leftleg2.setTextureSize(128, 32);
		leftleg2.mirror = true;
		setRotation(leftleg2, 0.0f, 0.0f, 0.0f);
		(TurningHead = new ModelRenderer(this, 36, 16)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		TurningHead.setRotationPoint(0.0f, 3.0f, 0.0f);
		TurningHead.setTextureSize(128, 32);
		TurningHead.mirror = true;
		setRotation(TurningHead, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 36, 1)).addBox(6.0f, 0.5f, 12.0f, 4, 2, 1);
		head3.setRotationPoint(-4.0f, 9.0f, -4.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 18, 15)).addBox(-4.0f, 0.0f, -4.0f, 4, 6, 4);
		head4.setRotationPoint(2.0f, 3.0f, 2.0f);
		head4.setTextureSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 61, 0)).addBox(-4.0f, 0.0f, -4.0f, 16, 3, 16);
		head5.setRotationPoint(-4.0f, 9.0f, -4.0f);
		head5.setTextureSize(128, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 36, 1)).addBox(-2.0f, 0.5f, 12.0f, 4, 2, 1);
		head6.setRotationPoint(-4.0f, 9.0f, -4.0f);
		head6.setTextureSize(128, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 36, 1)).addBox(6.0f, 0.5f, -5.0f, 4, 2, 1);
		head7.setRotationPoint(-4.0f, 9.0f, -4.0f);
		head7.setTextureSize(128, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 36, 6)).addBox(12.0f, 0.5f, 6.0f, 1, 2, 4);
		head8.setRotationPoint(-4.0f, 9.0f, -4.0f);
		head8.setTextureSize(128, 32);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 36, 1)).addBox(-2.0f, 0.5f, -5.0f, 4, 2, 1);
		head9.setRotationPoint(-4.0f, 9.0f, -4.0f);
		head9.setTextureSize(128, 32);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 36, 6)).addBox(12.0f, 0.5f, -2.0f, 1, 2, 4);
		head10.setRotationPoint(-4.0f, 9.0f, -4.0f);
		head10.setTextureSize(128, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
		(head11 = new ModelRenderer(this, 36, 6)).addBox(-5.0f, 0.5f, 6.0f, 1, 2, 4);
		head11.setRotationPoint(-4.0f, 9.0f, -4.0f);
		head11.setTextureSize(128, 32);
		head11.mirror = true;
		setRotation(head11, 0.0f, 0.0f, 0.0f);
		(head12 = new ModelRenderer(this, 36, 6)).addBox(-5.0f, 0.5f, -2.0f, 1, 2, 4);
		head12.setRotationPoint(-4.0f, 9.0f, -4.0f);
		head12.setTextureSize(128, 32);
		head12.mirror = true;
		setRotation(head12, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		rightleg2.render(par7);
		leftleg2.render(par7);
		TurningHead.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
		head7.render(par7);
		head8.render(par7);
		head9.render(par7);
		head10.render(par7);
		head11.render(par7);
		head12.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		TurningHead.rotateAngleY = par4 / 57.295776f;
		TurningHead.rotateAngleX = par5 / 54.11268f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftleg.rotateAngleY = 0.0f;
		leftleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		rightleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

package net.tslat.aoa3.client.model.entities.boss.clunkhead;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelClunkhead extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer head2;
	private ModelRenderer head3;
	private ModelRenderer head4;
	private ModelRenderer head5;
	private ModelRenderer head6;
	private ModelRenderer head7;
	private ModelRenderer head8;
	private ModelRenderer head9;
	private ModelRenderer head10;
	private ModelRenderer head11;

	public ModelClunkhead() {
		textureWidth = 256;
		textureHeight = 32;
		(head = new ModelRenderer(this, 196, 0)).addBox(6.0f, -12.0f, -12.0f, 2, 10, 10);
		head.setRotationPoint(0.0f, 12.0f, 4.0f);
		head.setTextureSize(256, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 14)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		rightLeg.setRotationPoint(-4.0f, 12.0f, 0.0f);
		rightLeg.setTextureSize(256, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 14)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		leftLeg.setRotationPoint(4.0f, 12.0f, 0.0f);
		leftLeg.setTextureSize(256, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 68, 0)).addBox(-8.0f, -18.0f, -2.0f, 16, 18, 6);
		head2.setRotationPoint(0.0f, 12.0f, 4.0f);
		head2.setTextureSize(256, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 25, 0)).addBox(-11.0f, -4.0f, -12.0f, 3, 3, 16);
		head3.setRotationPoint(0.0f, 12.0f, 4.0f);
		head3.setTextureSize(256, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 114, 18)).addBox(-8.0f, -2.0f, -12.0f, 16, 2, 10);
		head4.setRotationPoint(0.0f, 12.0f, 4.0f);
		head4.setTextureSize(256, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 169, 0)).addBox(-8.0f, -12.0f, -12.0f, 2, 10, 10);
		head5.setRotationPoint(0.0f, 12.0f, 4.0f);
		head5.setTextureSize(256, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 114, 0)).addBox(-8.0f, -18.0f, -12.0f, 16, 6, 10);
		head6.setRotationPoint(0.0f, 12.0f, 4.0f);
		head6.setTextureSize(256, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 25, 0)).addBox(-7.0f, -21.0f, -12.0f, 3, 3, 16);
		head7.setRotationPoint(0.0f, 12.0f, 4.0f);
		head7.setTextureSize(256, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 25, 0)).addBox(4.0f, -21.0f, -12.0f, 3, 3, 16);
		head8.setRotationPoint(0.0f, 12.0f, 4.0f);
		head8.setTextureSize(256, 32);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 25, 0)).addBox(-11.0f, -17.0f, -12.0f, 3, 3, 16);
		head9.setRotationPoint(0.0f, 12.0f, 4.0f);
		head9.setTextureSize(256, 32);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 25, 0)).addBox(8.0f, -4.0f, -12.0f, 3, 3, 16);
		head10.setRotationPoint(0.0f, 12.0f, 4.0f);
		head10.setTextureSize(256, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
		(head11 = new ModelRenderer(this, 25, 0)).addBox(8.0f, -17.0f, -12.0f, 3, 3, 16);
		head11.setRotationPoint(0.0f, 12.0f, 4.0f);
		head11.setTextureSize(256, 32);
		head11.mirror = true;
		setRotation(head11, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
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
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

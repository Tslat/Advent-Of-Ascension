package net.nevermine.block.modelblocks.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.nevermine.block.modelblocks.ModelEternalBlock;

public class ClunkheadStatue extends ModelBase implements ModelEternalBlock {
	ModelRenderer head;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
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

	public ClunkheadStatue() {
		textureWidth = 256;
		textureHeight = 32;
		(head = new ModelRenderer(this, 196, 0)).addBox(6.0f, -12.0f, -12.0f, 2, 10, 10);
		head.setRotationPoint(0.0f, 12.0f, 4.0f);
		head.setTextureSize(256, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 0, 14)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		rightleg.setRotationPoint(-4.0f, 12.0f, 0.0f);
		rightleg.setTextureSize(256, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 0, 14)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		leftleg.setRotationPoint(4.0f, 12.0f, 0.0f);
		leftleg.setTextureSize(256, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
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

	public void render(final float par7) {
		head.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
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
}

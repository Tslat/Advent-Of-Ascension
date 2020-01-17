package net.tslat.aoa3.client.model.entities.mobs.voxponds;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGadgetoid extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer rightLeg2;
	private ModelRenderer leftLeg2;
	private ModelRenderer Turninghead;
	private ModelRenderer head3;
	private ModelRenderer head4;
	private ModelRenderer head5;
	private ModelRenderer head6;
	private ModelRenderer head7;
	private ModelRenderer head8;
	private ModelRenderer head9;
	private ModelRenderer head10;
	private ModelRenderer head11;
	private ModelRenderer head12;

	public ModelGadgetoid() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, 0.0f, -4.0f, 6, 2, 6);
		head.setRotationPoint(1.0f, 5.0f, 1.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setRotationPoint(-6.0f, 12.0f, 6.0f);
		rightLeg.setTextureSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setRotationPoint(6.0f, 12.0f, 6.0f);
		leftLeg.setTextureSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg2.setRotationPoint(-6.0f, 12.0f, -6.0f);
		rightLeg2.setTextureSize(128, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg2.setRotationPoint(6.0f, 12.0f, -6.0f);
		leftLeg2.setTextureSize(128, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(Turninghead = new ModelRenderer(this, 36, 16)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		Turninghead.setRotationPoint(0.0f, 3.0f, 0.0f);
		Turninghead.setTextureSize(128, 32);
		Turninghead.mirror = true;
		setRotation(Turninghead, 0.0f, 0.0f, 0.0f);
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
		rightLeg.render(par7);
		leftLeg.render(par7);
		rightLeg2.render(par7);
		leftLeg2.render(par7);
		Turninghead.render(par7);
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
		Turninghead.rotateAngleY = par4 / 57.295776f;
		Turninghead.rotateAngleX = par5 / 54.11268f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftLeg.rotateAngleY = 0.0f;
		leftLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		rightLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

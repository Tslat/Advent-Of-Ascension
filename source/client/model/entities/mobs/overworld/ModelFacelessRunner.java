package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFacelessRunner extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightLegfoot;
	private ModelRenderer leftLegfoot;
	private ModelRenderer leftLegtop;
	private ModelRenderer rightLegtop;
	private ModelRenderer head2;
	private ModelRenderer head3;
	private ModelRenderer rightLegmid;
	private ModelRenderer leftLegmid;
	private ModelRenderer rightLegfoot2;
	private ModelRenderer leftLegfoot2;
	private ModelRenderer head4;

	public ModelFacelessRunner() {
		textureWidth = 64;
		textureHeight = 32;
		(head = new ModelRenderer(this, 29, 8)).addBox(-4.0f, 0.0f, 0.0f, 8, 1, 1);
		head.setRotationPoint(0.0f, 7.0f, -4.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 9, 18)).addBox(-4.0f, 0.0f, -2.0f, 8, 3, 4);
		body.setRotationPoint(0.0f, 7.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLegfoot = new ModelRenderer(this, 52, 26)).addBox(1.0f, 15.0f, -3.0f, 2, 2, 4);
		rightLegfoot.setRotationPoint(-6.0f, 7.0f, 0.0f);
		rightLegfoot.setTextureSize(64, 32);
		rightLegfoot.mirror = true;
		setRotation(rightLegfoot, 0.0f, 0.0f, 0.0f);
		(leftLegfoot = new ModelRenderer(this, 52, 26)).addBox(1.0f, 15.0f, -3.0f, 2, 2, 4);
		leftLegfoot.setRotationPoint(6.0f, 7.0f, 0.0f);
		leftLegfoot.setTextureSize(64, 32);
		leftLegfoot.mirror = true;
		setRotation(leftLegfoot, 0.0f, 0.0f, 0.0f);
		(leftLegtop = new ModelRenderer(this, 48, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		leftLegtop.setRotationPoint(6.0f, 7.0f, 0.0f);
		leftLegtop.setTextureSize(64, 32);
		leftLegtop.mirror = true;
		setRotation(leftLegtop, 0.0f, 0.0f, 0.0f);
		(rightLegtop = new ModelRenderer(this, 48, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		rightLegtop.setRotationPoint(-6.0f, 7.0f, 0.0f);
		rightLegtop.setTextureSize(64, 32);
		rightLegtop.mirror = true;
		setRotation(rightLegtop, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 13, 5)).addBox(-3.0f, -4.0f, 9.0f, 6, 3, 2);
		head2.setRotationPoint(0.0f, 7.0f, -4.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 9, 26)).addBox(-4.0f, 1.0f, -4.0f, 8, 1, 5);
		head3.setRotationPoint(0.0f, 7.0f, -4.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(rightLegmid = new ModelRenderer(this, 39, 18)).addBox(-1.0f, 7.0f, 0.0f, 2, 10, 4);
		rightLegmid.setRotationPoint(-6.0f, 7.0f, 0.0f);
		rightLegmid.setTextureSize(64, 32);
		rightLegmid.mirror = true;
		setRotation(rightLegmid, 0.0f, 0.0f, 0.0f);
		(leftLegmid = new ModelRenderer(this, 39, 18)).addBox(-1.0f, 7.0f, 0.0f, 2, 10, 4);
		leftLegmid.setRotationPoint(6.0f, 7.0f, 0.0f);
		leftLegmid.setTextureSize(64, 32);
		leftLegmid.mirror = true;
		setRotation(leftLegmid, 0.0f, 0.0f, 0.0f);
		(rightLegfoot2 = new ModelRenderer(this, 52, 26)).addBox(-3.0f, 15.0f, -3.0f, 2, 2, 4);
		rightLegfoot2.setRotationPoint(-6.0f, 7.0f, 0.0f);
		rightLegfoot2.setTextureSize(64, 32);
		rightLegfoot2.mirror = true;
		setRotation(rightLegfoot2, 0.0f, 0.0f, 0.0f);
		(leftLegfoot2 = new ModelRenderer(this, 52, 26)).addBox(-3.0f, 15.0f, -3.0f, 2, 2, 4);
		leftLegfoot2.setRotationPoint(6.0f, 7.0f, 0.0f);
		leftLegfoot2.setTextureSize(64, 32);
		leftLegfoot2.mirror = true;
		setRotation(leftLegfoot2, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -5.0f, -4.0f, 8, 5, 13);
		head4.setRotationPoint(0.0f, 7.0f, -4.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightLegfoot.render(par7);
		leftLegfoot.render(par7);
		leftLegtop.render(par7);
		rightLegtop.render(par7);
		head2.render(par7);
		head3.render(par7);
		rightLegmid.render(par7);
		leftLegmid.render(par7);
		rightLegfoot2.render(par7);
		leftLegfoot2.render(par7);
		head4.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 89.12676f;
		head.rotateAngleX = par5 / 85.943665f;
		head2.rotateAngleY = par4 / 89.12676f;
		head2.rotateAngleX = par5 / 85.943665f;
		head3.rotateAngleY = par4 / 89.12676f;
		head3.rotateAngleX = par5 / 85.943665f;
		head4.rotateAngleY = par4 / 89.12676f;
		head4.rotateAngleX = par5 / 85.943665f;
		rightLegtop.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLegtop.rotateAngleY = 0.0f;
		rightLegmid.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLegmid.rotateAngleY = 0.0f;
		rightLegfoot.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLegfoot.rotateAngleY = 0.0f;
		rightLegfoot2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLegfoot2.rotateAngleY = 0.0f;
		leftLegtop.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLegmid.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLegfoot.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLegfoot2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

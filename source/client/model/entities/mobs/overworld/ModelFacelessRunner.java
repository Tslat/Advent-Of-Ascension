package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFacelessRunner extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightlegfoot;
	ModelRenderer leftlegfoot;
	ModelRenderer leftlegtop;
	ModelRenderer rightlegtop;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer rightlegmid;
	ModelRenderer leftlegmid;
	ModelRenderer rightlegfoot2;
	ModelRenderer leftlegfoot2;
	ModelRenderer head4;

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
		(rightlegfoot = new ModelRenderer(this, 52, 26)).addBox(1.0f, 15.0f, -3.0f, 2, 2, 4);
		rightlegfoot.setRotationPoint(-6.0f, 7.0f, 0.0f);
		rightlegfoot.setTextureSize(64, 32);
		rightlegfoot.mirror = true;
		setRotation(rightlegfoot, 0.0f, 0.0f, 0.0f);
		(leftlegfoot = new ModelRenderer(this, 52, 26)).addBox(1.0f, 15.0f, -3.0f, 2, 2, 4);
		leftlegfoot.setRotationPoint(6.0f, 7.0f, 0.0f);
		leftlegfoot.setTextureSize(64, 32);
		leftlegfoot.mirror = true;
		setRotation(leftlegfoot, 0.0f, 0.0f, 0.0f);
		(leftlegtop = new ModelRenderer(this, 48, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		leftlegtop.setRotationPoint(6.0f, 7.0f, 0.0f);
		leftlegtop.setTextureSize(64, 32);
		leftlegtop.mirror = true;
		setRotation(leftlegtop, 0.0f, 0.0f, 0.0f);
		(rightlegtop = new ModelRenderer(this, 48, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		rightlegtop.setRotationPoint(-6.0f, 7.0f, 0.0f);
		rightlegtop.setTextureSize(64, 32);
		rightlegtop.mirror = true;
		setRotation(rightlegtop, 0.0f, 0.0f, 0.0f);
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
		(rightlegmid = new ModelRenderer(this, 39, 18)).addBox(-1.0f, 7.0f, 0.0f, 2, 10, 4);
		rightlegmid.setRotationPoint(-6.0f, 7.0f, 0.0f);
		rightlegmid.setTextureSize(64, 32);
		rightlegmid.mirror = true;
		setRotation(rightlegmid, 0.0f, 0.0f, 0.0f);
		(leftlegmid = new ModelRenderer(this, 39, 18)).addBox(-1.0f, 7.0f, 0.0f, 2, 10, 4);
		leftlegmid.setRotationPoint(6.0f, 7.0f, 0.0f);
		leftlegmid.setTextureSize(64, 32);
		leftlegmid.mirror = true;
		setRotation(leftlegmid, 0.0f, 0.0f, 0.0f);
		(rightlegfoot2 = new ModelRenderer(this, 52, 26)).addBox(-3.0f, 15.0f, -3.0f, 2, 2, 4);
		rightlegfoot2.setRotationPoint(-6.0f, 7.0f, 0.0f);
		rightlegfoot2.setTextureSize(64, 32);
		rightlegfoot2.mirror = true;
		setRotation(rightlegfoot2, 0.0f, 0.0f, 0.0f);
		(leftlegfoot2 = new ModelRenderer(this, 52, 26)).addBox(-3.0f, 15.0f, -3.0f, 2, 2, 4);
		leftlegfoot2.setRotationPoint(6.0f, 7.0f, 0.0f);
		leftlegfoot2.setTextureSize(64, 32);
		leftlegfoot2.mirror = true;
		setRotation(leftlegfoot2, 0.0f, 0.0f, 0.0f);
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
		rightlegfoot.render(par7);
		leftlegfoot.render(par7);
		leftlegtop.render(par7);
		rightlegtop.render(par7);
		head2.render(par7);
		head3.render(par7);
		rightlegmid.render(par7);
		leftlegmid.render(par7);
		rightlegfoot2.render(par7);
		leftlegfoot2.render(par7);
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
		rightlegtop.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightlegtop.rotateAngleY = 0.0f;
		rightlegmid.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightlegmid.rotateAngleY = 0.0f;
		rightlegfoot.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightlegfoot.rotateAngleY = 0.0f;
		rightlegfoot2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightlegfoot2.rotateAngleY = 0.0f;
		leftlegtop.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftlegmid.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftlegfoot.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftlegfoot2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

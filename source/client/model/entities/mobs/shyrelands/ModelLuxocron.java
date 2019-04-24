package net.tslat.aoa3.client.model.entities.mobs.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLuxocron extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer leg1;
	ModelRenderer leg2;
	ModelRenderer leg3;
	ModelRenderer leg4;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer head5;
	ModelRenderer head6;

	public ModelLuxocron() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 35, 4);
		head.addBox(-6.0F, -7.0F, -4.0F, 12, 1, 2);
		head.setRotationPoint(0.0F, 12.0F, -8.0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 22, 34);
		body.addBox(-1.0F, -7.0F, -7.0F, 2, 14, 4);
		body.setRotationPoint(0.0F, 4.0F, 8.0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 2.007129F, 0.0F, 0.0F);
		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-3.0F, 0.0F, -2.0F, 4, 8, 4);
		leg1.setRotationPoint(-3.0F, 16.0F, 7.0F);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0F, 0.0F, 0.0F);
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-1.0F, 0.0F, -2.0F, 4, 8, 4);
		leg2.setRotationPoint(3.0F, 16.0F, 7.0F);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0F, 0.0F, 0.0F);
		leg3 = new ModelRenderer(this, 0, 16);
		leg3.addBox(-3.0F, 0.0F, -3.0F, 4, 8, 4);
		leg3.setRotationPoint(-3.0F, 16.0F, -5.0F);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0F, 0.0F, 0.0F);
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.addBox(-1.0F, 0.0F, -3.0F, 4, 8, 4);
		leg4.setRotationPoint(3.0F, 16.0F, -5.0F);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 22, 8);
		body2.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 7);
		body2.setRotationPoint(0.0F, 9.0F, 2.0F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796F, 0.0F, 0.0F);
		body3 = new ModelRenderer(this, 22, 34);
		body3.addBox(-1.0F, -7.0F, -7.0F, 2, 14, 4);
		body3.setRotationPoint(0.0F, 2.0F, -9.0F);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 2.879793F, 0.0F, 0.0F);
		body4 = new ModelRenderer(this, 22, 34);
		body4.addBox(-1.0F, -7.0F, -7.0F, 2, 14, 4);
		body4.setRotationPoint(0.0F, 2.0F, 0.0F);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 2.356194F, 0.0F, 0.0F);
		head2 = new ModelRenderer(this, 0, 0);
		head2.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6);
		head2.setRotationPoint(0.0F, 12.0F, -8.0F);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0F, 0.0F, 0.0F);
		head3 = new ModelRenderer(this, 58, 9);
		head3.addBox(-5.5F, -6.0F, -3.5F, 1, 3, 1);
		head3.setRotationPoint(0.0F, 12.0F, -8.0F);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0F, 0.0F, 0.0F);
		head4 = new ModelRenderer(this, 58, 9);
		head4.addBox(4.5F, -6.0F, -3.5F, 1, 3, 1);
		head4.setRotationPoint(0.0F, 12.0F, -8.0F);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0F, 0.0F, 0.0F);
		head5 = new ModelRenderer(this, 25, 0);
		head5.addBox(-6.0F, -3.0F, -4.0F, 2, 2, 2);
		head5.setRotationPoint(0.0F, 12.0F, -8.0F);
		head5.setTextureSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0F, 0.0F, 0.0F);
		head6 = new ModelRenderer(this, 25, 0);
		head6.addBox(4.0F, -3.0F, -4.0F, 2, 2, 2);
		head6.setRotationPoint(0.0F, 12.0F, -8.0F);
		head6.setTextureSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.0F, 0.0F, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		leg1.render(par7);
		leg2.render(par7);
		leg3.render(par7);
		leg4.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		leg1.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		leg1.rotateAngleY = 0.0F;

		leg3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		leg3.rotateAngleY = 0.0F;

		leg2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
		leg4.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);
	}
}

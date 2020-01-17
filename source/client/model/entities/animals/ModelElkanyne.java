package net.tslat.aoa3.client.model.entities.animals;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelElkanyne extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer leg1;
	private ModelRenderer leg2;
	private ModelRenderer leg3;
	private ModelRenderer leg4;
	private ModelRenderer head2;
	private ModelRenderer head3;
	private ModelRenderer head4;
	private ModelRenderer head5;
	private ModelRenderer head6;
	private ModelRenderer head7;
	private ModelRenderer body2;

	public ModelElkanyne() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 33)).addBox(1.0f, -12.0f, -3.0f, 2, 6, 2);
		head.setRotationPoint(1.0f, 9.0f, -7.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 10, 15)).addBox(-6.0f, -4.0f, -7.0f, 2, 2, 2);
		body.setRotationPoint(5.0f, 12.0f, 15.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -1.0f, 2, 12, 2);
		leg1.setRotationPoint(-3.0f, 12.0f, 7.0f);
		leg1.setTextureSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(0.0f, 0.0f, -1.0f, 2, 12, 2);
		leg2.setRotationPoint(3.0f, 12.0f, 7.0f);
		leg2.setTextureSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 2, 12, 2);
		leg3.setRotationPoint(-3.0f, 12.0f, -5.0f);
		leg3.setTextureSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(0.0f, 0.0f, -2.0f, 2, 12, 2);
		leg4.setRotationPoint(3.0f, 12.0f, -5.0f);
		leg4.setTextureSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 6, 6, 8);
		head2.setRotationPoint(1.0f, 9.0f, -7.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 33)).addBox(-5.0f, -12.0f, -3.0f, 2, 6, 2);
		head3.setRotationPoint(1.0f, 9.0f, -7.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 0, 33)).addBox(1.0f, -8.0f, -1.0f, 2, 6, 2);
		head4.setRotationPoint(1.0f, 9.0f, -7.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 0, 33)).addBox(-5.0f, -8.0f, -1.0f, 2, 6, 2);
		head5.setRotationPoint(1.0f, 9.0f, -7.0f);
		head5.setTextureSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 0, 33)).addBox(1.0f, -12.0f, 1.0f, 2, 6, 2);
		head6.setRotationPoint(1.0f, 9.0f, -7.0f);
		head6.setTextureSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 0, 33)).addBox(-5.0f, -12.0f, 1.0f, 2, 6, 2);
		head7.setRotationPoint(1.0f, 9.0f, -7.0f);
		head7.setTextureSize(64, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 10, 15)).addBox(-6.0f, -4.0f, -7.0f, 12, 4, 15);
		body2.setRotationPoint(0.0f, 12.0f, 0.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float scale) {
		setRotationAngles(par2, par3, par4, par5, par6, scale, par1Entity);

		if (isChild) {
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.75f, 0.75f, 0.75f);
			GlStateManager.translate(0, 12f * scale, 2 * scale);
			head.render(scale);
			head2.render(scale);
			head3.render(scale);
			head4.render(scale);
			head5.render(scale);
			head6.render(scale);
			head7.render(scale);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.5f, 0.5f, 0.5f);
			GlStateManager.translate(0, 24f * scale, 0);
			body.render(scale);
			leg1.render(scale);
			leg2.render(scale);
			leg3.render(scale);
			leg4.render(scale);
			body2.render(scale);
			GlStateManager.popMatrix();
		}
		else {
			head.render(scale);
			body.render(scale);
			leg1.render(scale);
			leg2.render(scale);
			leg3.render(scale);
			leg4.render(scale);
			head2.render(scale);
			head3.render(scale);
			head4.render(scale);
			head5.render(scale);
			head6.render(scale);
			head7.render(scale);
			body2.render(scale);
		}
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg1.rotateAngleY = 0.0f;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

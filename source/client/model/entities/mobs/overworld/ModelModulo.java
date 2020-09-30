package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelModulo extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer shape1;
	private ModelRenderer body;
	private ModelRenderer shape2;
	private ModelRenderer shape3;
	private ModelRenderer shape4;
	private ModelRenderer shape5;
	private ModelRenderer shape6;
	private ModelRenderer shape7;
	private ModelRenderer shape8;
	private ModelRenderer shape9;
	private ModelRenderer shape10;
	private ModelRenderer shape11;
	private ModelRenderer shape12;
	private ModelRenderer shape13;
	private ModelRenderer shape14;

	public ModelModulo() {
		textureWidth = 128;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 13.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(shape1 = new ModelRenderer(this, 1, 48)).addBox(-7.5f, -9.0f, 7.5f, 15, 10, 0);
		shape1.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 6, 5, 4);
		body.setRotationPoint(0.0f, 13.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 61, 23)).addBox(-8.0f, -11.0f, -8.0f, 16, 2, 16);
		shape2.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 34, 37)).addBox(-7.5f, -9.0f, -7.5f, 0, 10, 15);
		shape3.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 34, 37)).addBox(7.5f, -9.0f, -7.5f, 0, 10, 15);
		shape4.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 1, 48)).addBox(-7.5f, -9.0f, -7.5f, 15, 10, 0);
		shape5.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 21, 22)).addBox(-7.0f, 1.0f, 7.0f, 14, 4, 1);
		shape6.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape6.setTextureSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 21, 22)).addBox(-7.0f, 1.0f, -8.0f, 14, 4, 1);
		shape7.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape7.setTextureSize(64, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 21, 1)).addBox(7.0f, 1.0f, -8.0f, 1, 4, 16);
		shape8.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape8.setTextureSize(64, 32);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 21, 1)).addBox(-8.0f, 1.0f, -8.0f, 1, 4, 16);
		shape9.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape9.setTextureSize(64, 32);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 69, 43)).addBox(-2.0f, 6.0f, -10.0f, 4, 4, 1);
		shape10.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape10.setTextureSize(64, 32);
		shape10.mirror = true;
		setRotation(shape10, 0.0f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 0, 28)).addBox(-8.0f, 5.0f, -8.0f, 16, 2, 16);
		shape11.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape11.setTextureSize(64, 32);
		shape11.mirror = true;
		setRotation(shape11, 0.0f, 0.0f, 0.0f);
		(shape12 = new ModelRenderer(this, 69, 42)).addBox(-1.0f, 7.0f, -15.0f, 2, 2, 16);
		shape12.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape12.setTextureSize(64, 32);
		shape12.mirror = true;
		setRotation(shape12, 0.0f, 0.0f, 0.0f);
		(shape13 = new ModelRenderer(this, 69, 43)).addBox(-2.0f, 6.0f, -14.0f, 4, 4, 1);
		shape13.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape13.setTextureSize(64, 32);
		shape13.mirror = true;
		setRotation(shape13, 0.0f, 0.0f, 0.0f);
		(shape14 = new ModelRenderer(this, 69, 43)).addBox(-2.0f, 6.0f, -12.0f, 4, 4, 1);
		shape14.setRotationPoint(0.0f, 13.0f, 0.0f);
		shape14.setTextureSize(64, 32);
		shape14.mirror = true;
		setRotation(shape14, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		head.render(par7);
		shape1.render(par7);
		body.render(par7);
		shape2.render(par7);
		shape3.render(par7);
		shape4.render(par7);
		shape5.render(par7);
		shape6.render(par7);
		shape7.render(par7);
		shape8.render(par7);
		shape9.render(par7);
		shape10.render(par7);
		shape11.render(par7);
		shape12.render(par7);
		shape13.render(par7);
		shape14.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
	}
}

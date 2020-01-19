package net.tslat.aoa3.client.model.entities.mobs.immortallis;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelReaver extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer rightLeg2;
	private ModelRenderer leftLeg2;
	private ModelRenderer leftLeg3;
	private ModelRenderer rightLeg3;
	private ModelRenderer leftLeg4;
	private ModelRenderer rightLeg4;

	public ModelReaver() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -8.0f, -4.0f, 6, 13, 8);
		head.setRotationPoint(0.0f, -8.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 39, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, -8.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 25, 34)).addBox(-2.0f, 0.0f, -2.0f, 4, 16, 4);
		rightLeg2.setRotationPoint(-6.0f, -8.0f, 0.0f);
		rightLeg2.setTextureSize(64, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 25, 34)).addBox(-2.0f, 0.0f, -2.0f, 4, 16, 4);
		leftLeg2.setRotationPoint(6.0f, -8.0f, 0.0f);
		leftLeg2.setTextureSize(64, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg3 = new ModelRenderer(this, 0, 48)).addBox(-4.0f, 24.0f, -4.0f, 8, 8, 8);
		leftLeg3.setRotationPoint(6.0f, -8.0f, 0.0f);
		leftLeg3.setTextureSize(64, 32);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.0f, 0.0f, 0.0f);
		(rightLeg3 = new ModelRenderer(this, 0, 48)).addBox(-4.0f, 24.0f, -4.0f, 8, 8, 8);
		rightLeg3.setRotationPoint(-6.0f, -8.0f, 0.0f);
		rightLeg3.setTextureSize(64, 32);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.0f, 0.0f, 0.0f);
		(leftLeg4 = new ModelRenderer(this, 40, 50)).addBox(-3.0f, 16.0f, -3.0f, 6, 8, 6);
		leftLeg4.setRotationPoint(6.0f, -8.0f, 0.0f);
		leftLeg4.setTextureSize(64, 32);
		leftLeg4.mirror = true;
		setRotation(leftLeg4, 0.0f, 0.0f, 0.0f);
		(rightLeg4 = new ModelRenderer(this, 40, 50)).addBox(-3.0f, 16.0f, -3.0f, 6, 8, 6);
		rightLeg4.setRotationPoint(-6.0f, -8.0f, 0.0f);
		rightLeg4.setTextureSize(64, 32);
		rightLeg4.mirror = true;
		setRotation(rightLeg4, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, 771);
		head.render(par7);
		body.render(par7);
		rightLeg2.render(par7);
		leftLeg2.render(par7);
		leftLeg3.render(par7);
		rightLeg3.render(par7);
		leftLeg4.render(par7);
		rightLeg4.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
		rightLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg2.rotateAngleY = 0.0f;
		rightLeg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg3.rotateAngleY = 0.0f;
		rightLeg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg4.rotateAngleY = 0.0f;
		leftLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLeg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLeg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

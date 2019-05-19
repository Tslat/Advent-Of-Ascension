package net.tslat.aoa3.client.model.entities.mobs.immortallis;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelFenix extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer rightleg2;
	ModelRenderer leftleg2;
	ModelRenderer leftleg3;
	ModelRenderer rightleg3;
	ModelRenderer leftleg4;
	ModelRenderer rightleg4;
	ModelRenderer rightarm2;
	ModelRenderer leftarm2;
	ModelRenderer rightarm3;
	ModelRenderer leftarm3;

	public ModelFenix() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -5.0f, -4.0f, 8, 5, 7);
		head.setRotationPoint(0.0f, 0.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, 0.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 57, 16)).addBox(-0.5f, 6.0f, -7.0f, 1, 12, 1);
		rightarm.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0.0f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 57, 16)).addBox(1.5f, 6.0f, -7.0f, 1, 12, 1);
		leftarm.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(rightleg = new ModelRenderer(this, 13, 33)).addBox(0.5f, 12.0f, -4.5f, 1, 1, 3);
		rightleg.setRotationPoint(-2.0f, 11.0f, 0.0f);
		rightleg.setTextureSize(64, 32);
		rightleg.mirror = true;
		setRotation(rightleg, 0.0f, 0.0f, 0.0f);
		(leftleg = new ModelRenderer(this, 13, 33)).addBox(0.5f, 12.0f, -4.5f, 1, 1, 3);
		leftleg.setRotationPoint(2.0f, 11.0f, 0.0f);
		leftleg.setTextureSize(64, 32);
		leftleg.mirror = true;
		setRotation(leftleg, 0.0f, 0.0f, 0.0f);
		(rightleg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 7, 4);
		rightleg2.setRotationPoint(-2.0f, 11.0f, 0.0f);
		rightleg2.setTextureSize(64, 32);
		rightleg2.mirror = true;
		setRotation(rightleg2, -0.4363323f, 0.0f, 0.0f);
		(leftleg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 7, 4);
		leftleg2.setRotationPoint(2.0f, 11.0f, 0.0f);
		leftleg2.setTextureSize(64, 32);
		leftleg2.mirror = true;
		setRotation(leftleg2, -0.4363323f, 0.0f, 0.0f);
		(leftleg3 = new ModelRenderer(this, 0, 28)).addBox(-1.5f, 3.0f, -6.5f, 3, 8, 3);
		leftleg3.setRotationPoint(2.0f, 11.0f, 0.0f);
		leftleg3.setTextureSize(64, 32);
		leftleg3.mirror = true;
		setRotation(leftleg3, 0.4363323f, 0.0f, 0.0f);
		(rightleg3 = new ModelRenderer(this, 0, 28)).addBox(-1.5f, 3.0f, -6.5f, 3, 8, 3);
		rightleg3.setRotationPoint(-2.0f, 11.0f, 0.0f);
		rightleg3.setTextureSize(64, 32);
		rightleg3.mirror = true;
		setRotation(rightleg3, 0.4363323f, 0.0f, 0.0f);
		(leftleg4 = new ModelRenderer(this, 13, 33)).addBox(-1.5f, 12.0f, -4.5f, 1, 1, 3);
		leftleg4.setRotationPoint(2.0f, 11.0f, 0.0f);
		leftleg4.setTextureSize(64, 32);
		leftleg4.mirror = true;
		setRotation(leftleg4, 0.0f, 0.0f, 0.0f);
		(rightleg4 = new ModelRenderer(this, 13, 33)).addBox(-1.5f, 12.0f, -4.5f, 1, 1, 3);
		rightleg4.setRotationPoint(-2.0f, 11.0f, 0.0f);
		rightleg4.setTextureSize(64, 32);
		rightleg4.mirror = true;
		setRotation(rightleg4, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightarm2.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm2.setTextureSize(64, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, -0.6981317f, 0.0f, 0.0f);
		(leftarm2 = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftarm2.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm2.setTextureSize(64, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, -0.6981317f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 57, 16)).addBox(-2.5f, 6.0f, -7.0f, 1, 12, 1);
		rightarm3.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm3.setTextureSize(64, 32);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.0f, 0.0f, 0.0f);
		(leftarm3 = new ModelRenderer(this, 57, 16)).addBox(-0.5f, 6.0f, -7.0f, 1, 12, 1);
		leftarm3.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm3.setTextureSize(64, 32);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, 771);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		rightleg2.render(par7);
		leftleg2.render(par7);
		leftleg3.render(par7);
		rightleg3.render(par7);
		leftleg4.render(par7);
		rightleg4.render(par7);
		rightarm2.render(par7);
		leftarm2.render(par7);
		rightarm3.render(par7);
		leftarm3.render(par7);
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
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		rightleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2 - 0.436f;
		rightleg2.rotateAngleY = 0.0f;
		rightleg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2 + 0.436f;
		rightleg3.rotateAngleY = 0.0f;
		rightleg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg4.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftleg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2 - 0.436f;
		leftleg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2 + 0.436f;
		leftleg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

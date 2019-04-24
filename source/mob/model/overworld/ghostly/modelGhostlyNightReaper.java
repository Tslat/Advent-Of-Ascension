package net.nevermine.mob.model.overworld.ghostly;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public class modelGhostlyNightReaper extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer body2;
	ModelRenderer rightarm2;
	ModelRenderer rightarm3;
	ModelRenderer rightarm4;
	ModelRenderer rightarm5;
	ModelRenderer rightarm6;
	ModelRenderer rightarm7;
	ModelRenderer rightarm8;

	public modelGhostlyNightReaper() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, -6.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 38)).addBox(-5.0f, 0.0f, -2.0f, 10, 13, 4);
		body.setRotationPoint(0.0f, 10.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -1.0f, -11.0f, 2, 3, 1);
		rightarm.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm.setTextureSize(64, 64);
		rightarm.mirror = true;
		setRotation(rightarm, 0.3490659f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 37, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 16, 4);
		leftarm.setRotationPoint(5.0f, -4.0f, 0.0f);
		leftarm.setTextureSize(64, 64);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 12, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 16, 4);
		body2.setRotationPoint(0.0f, -6.0f, 0.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 37, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 16, 4);
		rightarm2.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm2.setTextureSize(64, 64);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 55, 16)).addBox(-2.0f, -10.0f, -7.0f, 2, 36, 2);
		rightarm3.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm3.setTextureSize(64, 64);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.3490659f, 0.0f, 0.0f);
		(rightarm4 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -8.0f, -9.0f, 2, 3, 2);
		rightarm4.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm4.setTextureSize(64, 64);
		rightarm4.mirror = true;
		setRotation(rightarm4, 0.3490659f, 0.0f, 0.0f);
		(rightarm5 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -7.0f, -11.0f, 2, 3, 2);
		rightarm5.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm5.setTextureSize(64, 64);
		rightarm5.mirror = true;
		setRotation(rightarm5, 0.3490659f, 0.0f, 0.0f);
		(rightarm6 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -6.0f, -13.0f, 2, 3, 2);
		rightarm6.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm6.setTextureSize(64, 64);
		rightarm6.mirror = true;
		setRotation(rightarm6, 0.3490659f, 0.0f, 0.0f);
		(rightarm7 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -4.0f, -14.0f, 2, 4, 1);
		rightarm7.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm7.setTextureSize(64, 64);
		rightarm7.mirror = true;
		setRotation(rightarm7, 0.3490659f, 0.0f, 0.0f);
		(rightarm8 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -3.0f, -13.0f, 2, 4, 2);
		rightarm8.setRotationPoint(-5.0f, -4.0f, 0.0f);
		rightarm8.setTextureSize(64, 64);
		rightarm8.mirror = true;
		setRotation(rightarm8, 0.3490659f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GL11.glPushMatrix();
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		body2.render(par7);
		rightarm2.render(par7);
		rightarm3.render(par7);
		rightarm4.render(par7);
		rightarm5.render(par7);
		rightarm6.render(par7);
		rightarm7.render(par7);
		rightarm8.render(par7);
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
		rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightarm.rotateAngleZ = 0.0f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		rightarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightarm3.rotateAngleZ = 0.0f;
		rightarm4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightarm4.rotateAngleZ = 0.0f;
		rightarm5.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightarm5.rotateAngleZ = 0.0f;
		rightarm6.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightarm6.rotateAngleZ = 0.0f;
		rightarm7.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightarm7.rotateAngleZ = 0.0f;
		rightarm8.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.349f;
		rightarm8.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
	}
}

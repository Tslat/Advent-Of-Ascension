package net.tslat.aoa3.client.model.entities.mobs.crystevia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelConstructOfSpeed extends ModelBase {
	private ModelRenderer r1;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer hindleg;
	private ModelRenderer leftLeg;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer rightLeg;
	private ModelRenderer head;
	private ModelRenderer r2;
	private ModelRenderer r3;

	public ModelConstructOfSpeed() {
		textureWidth = 64;
		textureHeight = 32;
		(r1 = new ModelRenderer(this, 37, 0)).addBox(2.0f, -9.0f, -1.0f, 2, 2, 2);
		r1.setRotationPoint(0.0f, -1.0f, -2.0f);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 25, 1)).addBox(2.0f, 9.0f, 2.0f, 2, 3, 6);
		body.setRotationPoint(0.0f, 1.0f, -1.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(hindleg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		hindleg.setRotationPoint(0.0f, 12.0f, 6.0f);
		hindleg.setTextureSize(64, 32);
		hindleg.mirror = true;
		setRotation(hindleg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setRotationPoint(4.0f, 12.0f, -2.0f);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body2.setRotationPoint(0.0f, 0.0f, -1.0f);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 25, 1)).addBox(-4.0f, 9.0f, 2.0f, 2, 3, 6);
		body3.setRotationPoint(0.0f, 1.0f, -1.0f);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setRotationPoint(-4.0f, 12.0f, -2.0f);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -6.0f, -4.0f, 6, 8, 6);
		head.setRotationPoint(0.0f, 2.0f, -1.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 46, 0)).addBox(-2.0f, -10.0f, -2.0f, 4, 6, 4);
		r2.setRotationPoint(0.0f, -1.0f, -2.0f);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 37, 0)).addBox(-4.0f, -9.0f, -1.0f, 2, 2, 2);
		r3.setRotationPoint(0.0f, -1.0f, -2.0f);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		r1.render(par7);
		body.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		hindleg.render(par7);
		leftLeg.render(par7);
		body2.render(par7);
		body3.render(par7);
		rightLeg.render(par7);
		head.render(par7);
		r2.render(par7);
		r3.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		hindleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		r1.rotateAngleY = par3 * 0.067f * 1.25f;
		r2.rotateAngleY = par3 * 0.067f * 1.25f;
		r3.rotateAngleY = par3 * 0.067f * 1.25f;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelGhostlyGoblin extends ModelBase {
	private ModelRenderer body;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer head;
	private ModelRenderer nose;
	private ModelRenderer leftEar;
	private ModelRenderer rightEar;
	private ModelRenderer leftArm;
	private ModelRenderer Staff;
	private ModelRenderer rightArm;

	public ModelGhostlyGoblin() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.leftEar = new ModelRenderer(this, 44, 0);
		this.leftEar.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.leftEar.addBox(-8.0F, -12.0F, 2.0F, 4, 6, 1, 0.0F);
		this.setRotation(leftEar, 0.0F, 0.0F, 0.8922123136195012F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.nose = new ModelRenderer(this, 33, 8);
		this.nose.setRotationPoint(0.0F, 3.0F, -1.0F);
		this.nose.addBox(-1.0F, -7.0F, -5.0F, 2, 5, 2, 0.0F);
		this.rightLeg = new ModelRenderer(this, 18, 18);
		this.rightLeg.setRotationPoint(-2.0F, 14.0F, 1.0F);
		this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.rightArm = new ModelRenderer(this, 0, 18);
		this.rightArm.setRotationPoint(-4.0F, 4.0F, 1.0F);
		this.rightArm.addBox(-4.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.leftLeg = new ModelRenderer(this, 18, 18);
		this.leftLeg.setRotationPoint(2.0F, 14.0F, 1.0F);
		this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.body = new ModelRenderer(this, 36, 15);
		this.body.setRotationPoint(0.0F, 3.0F, 1.0F);
		this.body.addBox(-4.0F, 0.0F, -3.0F, 8, 11, 6, 0.0F);
		this.rightEar = new ModelRenderer(this, 33, 0);
		this.rightEar.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.rightEar.addBox(4.0F, -12.0F, 2.0F, 4, 6, 1, 0.0F);
		this.setRotation(rightEar, 0.0F, 0.0F, -0.8922123136195012F);
		this.leftArm = new ModelRenderer(this, 0, 18);
		this.leftArm.setRotationPoint(4.0F, 4.0F, 1.0F);
		this.leftArm.addBox(0.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.Staff = new ModelRenderer(this, 59, 0);
		this.Staff.setRotationPoint(-4.0F, 4.0F, 1.0F);
		this.Staff.addBox(-2.5F, -9.0F, -5.0F, 1, 20, 1, 0.0F);
		this.setRotation(Staff, 0.43632999062538147F, 0.0F, 0.0F);
		this.head.addChild(this.leftEar);
		this.head.addChild(this.nose);
		this.head.addChild(this.rightEar);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, 771);
		body.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		head.render(par7);
		leftArm.render(par7);
		Staff.render(par7);
		rightArm.render(par7);
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
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		Staff.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f + 0.43633f;
		Staff.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

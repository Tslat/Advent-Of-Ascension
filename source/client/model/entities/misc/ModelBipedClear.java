package net.tslat.aoa3.client.model.entities.misc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelBipedClear extends ModelBase {
	public ModelRenderer hat;
	public ModelRenderer head;
	public ModelRenderer body;
	public ModelRenderer leftArm;
	public ModelRenderer rightArm;
	public ModelRenderer leftLeg;
	public ModelRenderer rightLeg;

	public ModelBipedClear() {
		textureWidth = 64;
		textureHeight = 32;
		head = new ModelRenderer(this, 0, 0);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		head.mirror = true;
		body = new ModelRenderer(this, 16, 16);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.mirror = true;
		body.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		rightArm = new ModelRenderer(this, 40, 16);
		rightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		rightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		setRotateAngle(rightArm, 0.04276056667386108F, 0.0F, 0.06387905062299247F);
		rightArm.mirror = true;
		leftArm = new ModelRenderer(this, 40, 16);
		leftArm.mirror = true;
		leftArm.setRotationPoint(5.0F, 2.0F, -0.0F);
		leftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		setRotateAngle(leftArm, -0.04276056667386108F, 0.0F, -0.06387905062299247F);
		leftArm.mirror = true;
		leftLeg = new ModelRenderer(this, 0, 16);
		leftLeg.mirror = true;
		leftLeg.setRotationPoint(1.9F, 12.0F, 0.1F);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		leftLeg.mirror = true;
		hat = new ModelRenderer(this, 32, 0);
		hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		hat.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
		hat.mirror = true;
		rightLeg = new ModelRenderer(this, 0, 16);
		rightLeg.setRotationPoint(-1.9F, 12.0F, 0.1F);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		rightLeg.mirror = true;
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, 771);
		head.render(f5);
		body.render(f5);
		rightArm.render(f5);
		leftArm.render(f5);
		leftLeg.render(f5);
		hat.render(f5);
		rightLeg.render(f5);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / (float)(180f / Math.PI);
		hat.rotateAngleY = par4 / (float)(180f / Math.PI);
		rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		rightArm.rotateAngleZ = 0.0F;
		leftArm.rotateAngleZ = 0.0F;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 1.4F * par2;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 1.4F * par2;
		rightLeg.rotateAngleY = 0.0F;
		leftLeg.rotateAngleY = 0.0F;
	}
}

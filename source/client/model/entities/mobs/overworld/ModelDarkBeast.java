package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelDarkBeast extends ModelBase {
	private ModelRenderer head1;
	private ModelRenderer body1;
	private ModelRenderer rightArm1;
	private ModelRenderer leftArm1;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer body2;
	private ModelRenderer body4;
	private ModelRenderer head2;
	private ModelRenderer head3;
	private ModelRenderer head4;
	private ModelRenderer head5;
	private ModelRenderer leftArm2;
	private ModelRenderer rightArm2;
	private ModelRenderer body3;

	public ModelDarkBeast() {
		textureWidth = 128;
		textureHeight = 32;
		(head1 = new ModelRenderer(this, 53, 0)).addBox(4.0f, -14.0f, -1.0f, 1, 2, 2);
		head1.setRotationPoint(0.0f, 5.0f, -11.0f);
		head1.setTextureSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 63, 6)).addBox(-4.0f, 0.0f, -2.0f, 2, 2, 10);
		body1.setRotationPoint(3.0f, 15.0f, 3.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, -0.5235988f, 0.0f, 0.0f);
		(rightArm1 = new ModelRenderer(this, 89, 0)).addBox(-3.0f, 14.0f, -2.0f, 6, 3, 4);
		rightArm1.setRotationPoint(-6.0f, 7.0f, -4.0f);
		rightArm1.setTextureSize(128, 32);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0.0f, 0.0f, 0.0f);
		(leftArm1 = new ModelRenderer(this, 89, 0)).addBox(-3.0f, 14.0f, -2.0f, 6, 3, 4);
		leftArm1.setRotationPoint(6.0f, 7.0f, -4.0f);
		leftArm1.setTextureSize(128, 32);
		leftArm1.mirror = true;
		setRotation(leftArm1, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		rightLeg.setRotationPoint(-4.0f, 16.0f, 2.0f);
		rightLeg.setTextureSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leftLeg.setRotationPoint(4.0f, 16.0f, 2.0f);
		leftLeg.setTextureSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 82, 20)).addBox(-4.0f, -7.0f, -2.0f, 10, 8, 4);
		body2.setRotationPoint(-1.0f, 7.0f, -3.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.8726646f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 56, 20)).addBox(-4.0f, 4.0f, -2.0f, 8, 8, 4);
		body4.setRotationPoint(0.0f, 3.0f, -4.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 0.2617994f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setRotationPoint(0.0f, 5.0f, -11.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 53, 0)).addBox(-5.0f, -14.0f, -1.0f, 1, 2, 2);
		head3.setRotationPoint(0.0f, 5.0f, -11.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 38, 0)).addBox(4.0f, -12.0f, -2.0f, 1, 8, 4);
		head4.setRotationPoint(0.0f, 5.0f, -11.0f);
		head4.setTextureSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 38, 0)).addBox(-5.0f, -12.0f, -2.0f, 1, 8, 4);
		head5.setRotationPoint(0.0f, 5.0f, -11.0f);
		head5.setTextureSize(128, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 111, 0)).addBox(-1.0f, -2.0f, -2.0f, 4, 16, 4);
		leftArm2.setRotationPoint(6.0f, 7.0f, -4.0f);
		leftArm2.setTextureSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 111, 0)).addBox(-3.0f, -2.0f, -2.0f, 4, 16, 4);
		rightArm2.setRotationPoint(-6.0f, 7.0f, -4.0f);
		rightArm2.setTextureSize(128, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 39, 24)).addBox(-4.0f, 0.0f, -2.0f, 4, 4, 4);
		body3.setRotationPoint(2.0f, 14.0f, 0.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, 771);
		head1.render(par7);
		body1.render(par7);
		rightArm1.render(par7);
		leftArm1.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		body2.render(par7);
		body4.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		leftArm2.render(par7);
		rightArm2.render(par7);
		body3.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head1.rotateAngleY = par4 / 57.295776f;
		head1.rotateAngleX = par5 / 54.11268f;
		head2.rotateAngleY = par4 / 57.295776f;
		head2.rotateAngleX = par5 / 54.11268f;
		head3.rotateAngleY = par4 / 57.295776f;
		head3.rotateAngleX = par5 / 54.11268f;
		head4.rotateAngleY = par4 / 57.295776f;
		head4.rotateAngleX = par5 / 54.11268f;
		head5.rotateAngleY = par4 / 57.295776f;
		head5.rotateAngleX = par5 / 54.11268f;
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightArm2.rotateAngleY = 0.0f;
		rightArm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightArm1.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftArm1.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

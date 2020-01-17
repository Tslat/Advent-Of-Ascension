package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class ModelGhostlyCharger extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;
	private ModelRenderer rightLeg2;
	private ModelRenderer leftLeg2;
	private ModelRenderer rightLeg3;
	private ModelRenderer leftLeg3;
	private ModelRenderer rightLeg4;
	private ModelRenderer leftLeg4;
	private ModelRenderer head2;
	private ModelRenderer head3;
	private ModelRenderer head4;
	private ModelRenderer head5;
	private ModelRenderer head6;
	private ModelRenderer head7;
	private ModelRenderer head8;
	private ModelRenderer head9;

	public ModelGhostlyCharger() {
		textureWidth = 64;
		textureHeight = 32;
		(head = new ModelRenderer(this, 37, 0)).addBox(-2.0f, -7.0f, -6.0f, 4, 1, 2);
		head.setRotationPoint(0.0f, 10.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 3, 4);
		rightLeg.setRotationPoint(-3.0f, 9.0f, 0.0f);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 3, 4);
		leftLeg.setRotationPoint(3.0f, 9.0f, 0.0f);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 18, 24)).addBox(1.0f, 13.0f, -5.0f, 1, 2, 6);
		rightLeg2.setRotationPoint(-3.0f, 9.0f, 0.0f);
		rightLeg2.setTextureSize(64, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 18, 24)).addBox(1.0f, 13.0f, -5.0f, 1, 2, 6);
		leftLeg2.setRotationPoint(3.0f, 9.0f, 0.0f);
		leftLeg2.setTextureSize(64, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(rightLeg3 = new ModelRenderer(this, 33, 16)).addBox(-1.0f, 3.0f, -1.0f, 2, 12, 2);
		rightLeg3.setRotationPoint(-3.0f, 9.0f, 0.0f);
		rightLeg3.setTextureSize(64, 32);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.0f, 0.0f, 0.0f);
		(leftLeg3 = new ModelRenderer(this, 33, 16)).addBox(-1.0f, 3.0f, -1.0f, 2, 12, 2);
		leftLeg3.setRotationPoint(3.0f, 9.0f, 0.0f);
		leftLeg3.setTextureSize(64, 32);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.0f, 0.0f, 0.0f);
		(rightLeg4 = new ModelRenderer(this, 18, 24)).addBox(-2.0f, 13.0f, -5.0f, 1, 2, 6);
		rightLeg4.setRotationPoint(-3.0f, 9.0f, 0.0f);
		rightLeg4.setTextureSize(64, 32);
		rightLeg4.mirror = true;
		setRotation(rightLeg4, 0.0f, 0.0f, 0.0f);
		(leftLeg4 = new ModelRenderer(this, 18, 24)).addBox(-2.0f, 13.0f, -5.0f, 1, 2, 6);
		leftLeg4.setRotationPoint(3.0f, 9.0f, 0.0f);
		leftLeg4.setTextureSize(64, 32);
		leftLeg4.mirror = true;
		setRotation(leftLeg4, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 16, 4)).addBox(4.0f, -7.0f, -3.0f, 1, 6, 6);
		head2.setRotationPoint(0.0f, 9.0f, 0.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 37, 0)).addBox(-3.0f, -5.0f, -6.0f, 6, 2, 2);
		head3.setRotationPoint(0.0f, 9.0f, 0.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 37, 0)).addBox(-2.0f, -5.0f, -7.0f, 4, 2, 1);
		head4.setRotationPoint(0.0f, 9.0f, 0.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 37, 0)).addBox(-2.0f, -3.0f, -6.0f, 4, 1, 2);
		head5.setRotationPoint(0.0f, 9.0f, 0.0f);
		head5.setTextureSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 38, 7)).addBox(-3.0f, -9.0f, -3.0f, 6, 1, 6);
		head6.setRotationPoint(0.0f, 9.0f, 0.0f);
		head6.setTextureSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 16, 4)).addBox(-5.0f, -7.0f, -3.0f, 1, 6, 6);
		head7.setRotationPoint(0.0f, 9.0f, 0.0f);
		head7.setTextureSize(64, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 45, 16)).addBox(-3.0f, -7.0f, 4.0f, 6, 6, 1);
		head8.setRotationPoint(0.0f, 9.0f, 0.0f);
		head8.setTextureSize(64, 32);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head9.setRotationPoint(0.0f, 9.0f, 0.0f);
		head9.setTextureSize(64, 32);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(770, 771);
		head.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
		rightLeg2.render(par7);
		leftLeg2.render(par7);
		rightLeg3.render(par7);
		leftLeg3.render(par7);
		rightLeg4.render(par7);
		leftLeg4.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
		head7.render(par7);
		head8.render(par7);
		head9.render(par7);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		rightLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg2.rotateAngleY = 0.0f;
		rightLeg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg3.rotateAngleY = 0.0f;
		rightLeg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg4.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLeg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLeg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leftLeg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

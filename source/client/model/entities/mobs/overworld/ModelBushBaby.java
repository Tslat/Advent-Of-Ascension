package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBushBaby extends ModelBase {
	private ModelRenderer head;
	private ModelRenderer rightLeg;
	private ModelRenderer leftLeg;

	public ModelBushBaby() {
		textureWidth = 64;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-6.0f, -12.0f, -6.0f, 12, 12, 12);
		head.setRotationPoint(0.0f, 22.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 24)).addBox(-3.0f, 0.0f, -3.0f, 6, 2, 6);
		rightLeg.setRotationPoint(-4.0f, 22.0f, 0.0f);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 24)).addBox(-3.0f, 0.0f, -3.0f, 6, 2, 6);
		leftLeg.setRotationPoint(4.0f, 22.0f, 0.0f);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		rightLeg.render(par7);
		leftLeg.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

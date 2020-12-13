package net.tslat.aoa3.client.model.entity.mob.deeplands;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class RockCrawlerModel extends EntityModel<MobEntity> {
	private final ModelRenderer RearEnd;
	private final ModelRenderer Leg6;
	private final ModelRenderer Leg4;
	private final ModelRenderer Leg2;
	private final ModelRenderer Leg5;
	private final ModelRenderer Leg3;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg8;
	private final ModelRenderer Leg7;
	private final ModelRenderer RearEnd2;
	private final ModelRenderer RearEnd3;

	public RockCrawlerModel() {
		textureWidth = 128;
		textureHeight = 32;
		(RearEnd = new ModelRenderer(this, 0, 10)).addBox(-9.0f, -15.0f, -7.0f, 8, 8, 8);
		RearEnd.setRotationPoint(4.0f, 8.0f, 4.0f);
		RearEnd.setTextureSize(128, 32);
		RearEnd.mirror = true;
		setRotation(RearEnd, 0.0f, 0.0f, 0.0f);
		(Leg6 = new ModelRenderer(this, 18, 0)).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg6.setRotationPoint(6.0f, 16.0f, 2.0f);
		Leg6.setTextureSize(128, 32);
		Leg6.mirror = true;
		setRotation(Leg6, 0.0f, 0.2792527f, 0.1919862f);
		(Leg4 = new ModelRenderer(this, 18, 0)).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg4.setRotationPoint(6.0f, 16.0f, 4.0f);
		Leg4.setTextureSize(128, 32);
		Leg4.mirror = true;
		setRotation(Leg4, 0.0f, -0.2792527f, 0.1919862f);
		(Leg2 = new ModelRenderer(this, 18, 0)).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg2.setRotationPoint(6.0f, 16.0f, 6.0f);
		Leg2.setTextureSize(128, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0.0f, -0.5759587f, 0.1919862f);
		(Leg5 = new ModelRenderer(this, 18, 5)).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg5.setRotationPoint(-6.0f, 16.0f, 2.0f);
		Leg5.setTextureSize(128, 32);
		Leg5.mirror = true;
		setRotation(Leg5, 0.0f, -0.2792527f, -0.1919862f);
		(Leg3 = new ModelRenderer(this, 18, 5)).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg3.setRotationPoint(-6.0f, 16.0f, 4.0f);
		Leg3.setTextureSize(128, 32);
		Leg3.mirror = true;
		setRotation(Leg3, 0.0f, 0.2792527f, -0.1919862f);
		(Leg1 = new ModelRenderer(this, 18, 5)).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg1.setRotationPoint(-6.0f, 16.0f, 6.0f);
		Leg1.setTextureSize(128, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0.0f, 0.5759587f, -0.1919862f);
		(Leg8 = new ModelRenderer(this, 18, 0)).addBox(-1.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg8.setRotationPoint(6.0f, 16.0f, -1.0f);
		Leg8.setTextureSize(128, 32);
		Leg8.mirror = true;
		setRotation(Leg8, 0.0f, 0.5759587f, 0.1919862f);
		(Leg7 = new ModelRenderer(this, 18, 5)).addBox(-15.0f, -1.0f, -1.0f, 16, 2, 2);
		Leg7.setRotationPoint(-6.0f, 16.0f, -1.0f);
		Leg7.setTextureSize(128, 32);
		Leg7.mirror = true;
		setRotation(Leg7, 0.0f, -0.5759587f, -0.1919862f);
		(RearEnd2 = new ModelRenderer(this, 70, 6)).addBox(-8.0f, -6.0f, -6.0f, 14, 12, 14);
		RearEnd2.setRotationPoint(0.0f, 12.0f, 1.0f);
		RearEnd2.setTextureSize(128, 32);
		RearEnd2.mirror = true;
		setRotation(RearEnd2, 0.0f, 0.0f, 0.0f);
		(RearEnd3 = new ModelRenderer(this, 35, 10)).addBox(-7.0f, -7.0f, -5.0f, 4, 5, 4);
		RearEnd3.setRotationPoint(4.0f, 8.0f, 4.0f);
		RearEnd3.setTextureSize(128, 32);
		RearEnd3.mirror = true;
		setRotation(RearEnd3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		RearEnd.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		final float var8 = 0.7853982f;
		Leg1.rotateAngleZ = -var8;
		Leg2.rotateAngleZ = var8;
		Leg3.rotateAngleZ = -var8 * 0.74f;
		Leg4.rotateAngleZ = var8 * 0.74f;
		Leg5.rotateAngleZ = -var8 * 0.74f;
		Leg6.rotateAngleZ = var8 * 0.74f;
		Leg7.rotateAngleZ = -var8;
		Leg8.rotateAngleZ = var8;
		final float var9 = -0.0f;
		final float var10 = 0.3926991f;
		Leg1.rotateAngleY = var10 * 2.0f + var9;
		Leg2.rotateAngleY = -var10 * 2.0f - var9;
		Leg3.rotateAngleY = var10 * 1.0f + var9;
		Leg4.rotateAngleY = -var10 * 1.0f - var9;
		Leg5.rotateAngleY = -var10 * 1.0f + var9;
		Leg6.rotateAngleY = var10 * 1.0f - var9;
		Leg7.rotateAngleY = -var10 * 2.0f + var9;
		Leg8.rotateAngleY = var10 * 2.0f - var9;
		final float var11 = -(MathHelper.cos(limbSwing * 0.6662f * 2.0f + 0.0f) * 0.4f) * limbSwingAmount;
		final float var12 = -(MathHelper.cos(limbSwing * 0.6662f * 2.0f + 3.1415927f) * 0.4f) * limbSwingAmount;
		final float var13 = -(MathHelper.cos(limbSwing * 0.6662f * 2.0f + 1.5707964f) * 0.4f) * limbSwingAmount;
		final float var14 = -(MathHelper.cos(limbSwing * 0.6662f * 2.0f + 4.712389f) * 0.4f) * limbSwingAmount;
		final float var15 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + 0.0f) * 0.4f) * limbSwingAmount;
		final float var16 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + 3.1415927f) * 0.4f) * limbSwingAmount;
		final float var17 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + 1.5707964f) * 0.4f) * limbSwingAmount;
		final float var18 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + 4.712389f) * 0.4f) * limbSwingAmount;
		Leg1.rotateAngleY += var11;
		Leg2.rotateAngleY += -var11;
		Leg3.rotateAngleY += var12;
		Leg4.rotateAngleY += -var12;
		Leg5.rotateAngleY += var13;
		Leg6.rotateAngleY += -var13;
		Leg7.rotateAngleY += var14;
		Leg8.rotateAngleY += -var14;
		Leg1.rotateAngleZ += var15;
		Leg2.rotateAngleZ += -var15;
		Leg3.rotateAngleZ += var16;
		Leg4.rotateAngleZ += -var16;
		Leg5.rotateAngleZ += var17;
		Leg6.rotateAngleZ += -var17;
		Leg7.rotateAngleZ += var18;
		Leg8.rotateAngleZ += -var18;
	}
}

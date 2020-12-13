package net.tslat.aoa3.client.model.entity.mob.barathos;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class SquigglerModel extends EntityModel<MobEntity> {
	private final ModelRenderer body1;
	private final ModelRenderer Leg8;
	private final ModelRenderer Leg2;
	private final ModelRenderer Leg7;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg2p2;
	private final ModelRenderer Leg8p2;
	private final ModelRenderer Leg1p2;
	private final ModelRenderer Leg7p2;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer head;
	private final ModelRenderer body4;

	public SquigglerModel() {
		textureWidth = 64;
		textureHeight = 64;
		(body1 = new ModelRenderer(this, 0, 32)).addBox(-4.0f, -2.0f, -3.0f, 4, 8, 4);
		body1.setRotationPoint(5.0f, 7.0f, 6.0f);
		body1.setTextureSize(64, 64);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, -1.22173f);
		(Leg8 = new ModelRenderer(this, 0, 51)).addBox(11.0f, -9.0f, -2.0f, 4, 8, 4);
		Leg8.setRotationPoint(4.0f, 21.0f, 1.0f);
		Leg8.setTextureSize(64, 64);
		Leg8.mirror = true;
		setRotation(Leg8, 0.0f, 0.5759587f, 0.0f);
		(Leg2 = new ModelRenderer(this, 0, 51)).addBox(11.0f, -9.0f, -2.0f, 4, 8, 4);
		Leg2.setRotationPoint(4.0f, 21.0f, 8.0f);
		Leg2.setTextureSize(64, 64);
		Leg2.mirror = true;
		setRotation(Leg2, 0.0f, -0.5759587f, 0.0f);
		(Leg7 = new ModelRenderer(this, 0, 51)).addBox(-15.0f, -9.0f, -2.0f, 4, 8, 4);
		Leg7.setRotationPoint(-4.0f, 21.0f, 1.0f);
		Leg7.setTextureSize(64, 64);
		Leg7.mirror = true;
		setRotation(Leg7, 0.0f, -0.5759587f, 0.0f);
		(Leg1 = new ModelRenderer(this, 0, 51)).addBox(-15.0f, -9.0f, -2.0f, 4, 8, 4);
		Leg1.setRotationPoint(-4.0f, 21.0f, 8.0f);
		Leg1.setTextureSize(64, 64);
		Leg1.mirror = true;
		setRotation(Leg1, 0.0f, 0.5759587f, 0.0f);
		(Leg2p2 = new ModelRenderer(this, 18, 55)).addBox(-1.0f, -1.0f, -2.0f, 16, 4, 4);
		Leg2p2.setRotationPoint(4.0f, 21.0f, 8.0f);
		Leg2p2.setTextureSize(64, 64);
		Leg2p2.mirror = true;
		setRotation(Leg2p2, 0.0f, -0.5759587f, 0.0f);
		(Leg8p2 = new ModelRenderer(this, 18, 55)).addBox(-1.0f, -1.0f, -2.0f, 16, 4, 4);
		Leg8p2.setRotationPoint(4.0f, 21.0f, 1.0f);
		Leg8p2.setTextureSize(64, 64);
		Leg8p2.mirror = true;
		setRotation(Leg8p2, 0.0f, 0.5759587f, 0.0f);
		(Leg1p2 = new ModelRenderer(this, 18, 55)).addBox(-15.0f, -1.0f, -2.0f, 16, 4, 4);
		Leg1p2.setRotationPoint(-4.0f, 21.0f, 8.0f);
		Leg1p2.setTextureSize(64, 64);
		Leg1p2.mirror = true;
		setRotation(Leg1p2, 0.0f, 0.5759587f, 0.0f);
		(Leg7p2 = new ModelRenderer(this, 18, 55)).addBox(-15.0f, -1.0f, -2.0f, 16, 4, 4);
		Leg7p2.setRotationPoint(-4.0f, 21.0f, 1.0f);
		Leg7p2.setTextureSize(64, 64);
		Leg7p2.mirror = true;
		setRotation(Leg7p2, 0.0f, -0.5759587f, 0.0f);
		(body2 = new ModelRenderer(this, 21, 35)).addBox(-5.0f, -4.0f, -6.0f, 10, 7, 11);
		body2.setRotationPoint(0.0f, 20.0f, 5.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 34, 16)).addBox(-5.0f, -4.0f, -6.0f, 8, 11, 6);
		body3.setRotationPoint(1.0f, 9.0f, 8.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 14)).addBox(-5.0f, -4.0f, -6.0f, 8, 8, 8);
		head.setRotationPoint(1.0f, 1.0f, 7.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 0, 32)).addBox(-4.0f, -2.0f, -3.0f, 4, 8, 4);
		body4.setRotationPoint(-4.0f, 11.0f, 6.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 1.22173f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg8p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg7p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 57.295776f;
		head.rotateAngleX = headPitch / 54.11268f;
		final float var8 = 0.7853982f;
		Leg1.rotateAngleZ = -var8;
		Leg2.rotateAngleZ = var8;
		Leg7.rotateAngleZ = -var8;
		Leg8.rotateAngleZ = var8;
		Leg1p2.rotateAngleZ = -var8;
		Leg2p2.rotateAngleZ = var8;
		Leg7p2.rotateAngleZ = -var8;
		Leg8p2.rotateAngleZ = var8;
		final float var9 = -0.0f;
		final float var10 = 0.3926991f;
		Leg1.rotateAngleY = var10 * 2.0f + var9;
		Leg2.rotateAngleY = -var10 * 2.0f - var9;
		Leg7.rotateAngleY = -var10 * 2.0f + var9;
		Leg8.rotateAngleY = var10 * 2.0f - var9;
		Leg1p2.rotateAngleY = var10 * 2.0f + var9;
		Leg2p2.rotateAngleY = -var10 * 2.0f - var9;
		Leg7p2.rotateAngleY = -var10 * 2.0f + var9;
		Leg8p2.rotateAngleY = var10 * 2.0f - var9;
		final float var11 = -(MathHelper.cos(limbSwing * 0.6662f * 2.0f + 0.0f) * 0.4f) * limbSwingAmount;
		final float var12 = -(MathHelper.cos(limbSwing * 0.6662f * 2.0f + 3.1415927f) * 0.4f) * limbSwingAmount;
		final float var13 = -(MathHelper.cos(limbSwing * 0.6662f * 2.0f + 1.5707964f) * 0.4f) * limbSwingAmount;
		final float var14 = -(MathHelper.cos(limbSwing * 0.6662f * 2.0f + 4.712389f) * 0.4f) * limbSwingAmount;
		final float var15 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + 0.0f) * 0.4f) * limbSwingAmount;
		final float var16 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + 3.1415927f) * 0.4f) * limbSwingAmount;
		final float var17 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + 1.5707964f) * 0.4f) * limbSwingAmount;
		final float var18 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + 4.712389f) * 0.4f) * limbSwingAmount;
		final ModelRenderer leg1 = Leg1;
		leg1.rotateAngleY += var11;
		final ModelRenderer leg2 = Leg2;
		leg2.rotateAngleY += -var11;
		final ModelRenderer leg3 = Leg7;
		leg3.rotateAngleY += var14;
		final ModelRenderer leg4 = Leg8;
		leg4.rotateAngleY += -var14;
		final ModelRenderer leg5 = Leg1;
		leg5.rotateAngleZ += var15;
		final ModelRenderer leg6 = Leg2;
		leg6.rotateAngleZ += -var15;
		final ModelRenderer leg7 = Leg7;
		leg7.rotateAngleZ += var18;
		final ModelRenderer leg8 = Leg8;
		leg8.rotateAngleZ += -var18;
		final ModelRenderer leg1p2 = Leg1p2;
		leg1p2.rotateAngleY += var11;
		final ModelRenderer leg2p2 = Leg2p2;
		leg2p2.rotateAngleY += -var11;
		final ModelRenderer leg7p2 = Leg7p2;
		leg7p2.rotateAngleY += var14;
		final ModelRenderer leg8p2 = Leg8p2;
		leg8p2.rotateAngleY += -var14;
		final ModelRenderer leg1p3 = Leg1p2;
		leg1p3.rotateAngleZ += var15;
		final ModelRenderer leg2p3 = Leg2p2;
		leg2p3.rotateAngleZ += -var15;
		final ModelRenderer leg7p3 = Leg7p2;
		leg7p3.rotateAngleZ += var18;
		final ModelRenderer leg8p3 = Leg8p2;
		leg8p3.rotateAngleZ += -var18;
	}
}

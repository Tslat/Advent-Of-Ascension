package net.tslat.aoa3.client.model.entity.mob.haven;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class DawnlightModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer horn1;
	private final ModelRenderer horn2;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;
	private final ModelRenderer body7;

	public DawnlightModel() {
		textureWidth = 64;
		textureHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head.setRotationPoint(0.0f, 6.0f, -8.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 18, 51)).addBox(-6.0f, -7.0f, -6.0f, 6, 1, 8);
		body.setRotationPoint(1.0f, 20.0f, 14.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, -0.7853982f, -0.6981317f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
		leg1.setRotationPoint(-3.0f, 12.0f, 7.0f);
		leg1.setTextureSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
		leg2.setRotationPoint(3.0f, 12.0f, 7.0f);
		leg2.setTextureSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
		leg3.setRotationPoint(-3.0f, 12.0f, -5.0f);
		leg3.setTextureSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
		leg4.setRotationPoint(3.0f, 12.0f, -5.0f);
		leg4.setTextureSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(horn1 = new ModelRenderer(this, 17, 34)).addBox(-4.0f, -5.0f, -4.0f, 2, 2, 2);
		horn1.setRotationPoint(0.0f, 5.0f, -7.0f);
		horn1.setTextureSize(64, 64);
		horn1.mirror = true;
		setRotation(horn1, 0.0f, 0.0f, 0.0f);
		(horn2 = new ModelRenderer(this, 17, 34)).addBox(2.0f, -5.0f, -4.0f, 2, 2, 2);
		horn2.setRotationPoint(0.0f, 5.0f, -7.0f);
		horn2.setTextureSize(64, 64);
		horn2.mirror = true;
		setRotation(horn2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 18, 33)).addBox(-6.0f, -2.0f, -7.0f, 8, 3, 8);
		body2.setRotationPoint(2.0f, 12.0f, 14.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, -0.3490659f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 18, 51)).addBox(-6.0f, -7.0f, -7.0f, 6, 1, 8);
		body3.setRotationPoint(4.0f, 21.0f, 11.0f);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, -0.7853982f, 0.6981317f, 0.0f);
		(body4 = new ModelRenderer(this, 18, 9)).addBox(-6.0f, -10.0f, -7.0f, 12, 18, 6);
		body4.setRotationPoint(0.0f, 5.0f, 2.0f);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 1.570796f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 0, 44)).addBox(0.0f, -10.0f, -7.0f, 2, 9, 5);
		body5.setRotationPoint(-1.0f, 11.0f, 10.0f);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, -0.4363323f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 0, 44)).addBox(0.0f, -10.0f, -7.0f, 2, 9, 5);
		body6.setRotationPoint(-1.0f, 7.0f, 2.0f);
		body6.setTextureSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0.4363323f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 0, 44)).addBox(0.0f, -10.0f, -7.0f, 2, 9, 5);
		body7.setRotationPoint(-1.0f, 8.0f, 6.5f);
		body7.setTextureSize(64, 64);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		horn1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		horn2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 79.57747f;
		head.rotateAngleX = headPitch / 76.39437f;
		horn1.rotateAngleY = netHeadYaw / 79.57747f;
		horn1.rotateAngleX = headPitch / 76.39437f;
		horn2.rotateAngleY = netHeadYaw / 79.57747f;
		horn2.rotateAngleX = headPitch / 76.39437f;
		leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}

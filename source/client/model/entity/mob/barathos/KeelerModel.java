package net.tslat.aoa3.client.model.entity.mob.barathos;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class KeelerModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg4p2;
	private final ModelRenderer leg3p2;
	private final ModelRenderer leg4p3;
	private final ModelRenderer leg3p3;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;
	private final ModelRenderer body7;
	private final ModelRenderer body8;
	private final ModelRenderer head2;
	private final ModelRenderer head3;

	public KeelerModel() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 47, 0)).addBox(-2.0f, -4.0f, -8.0f, 5, 8, 2);
		head.setRotationPoint(-0.5f, 11.0f, -2.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 51, 18)).addBox(-4.0f, 1.0f, -7.0f, 8, 8, 6);
		body.setRotationPoint(0.0f, 14.0f, 7.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 1.22173f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 26, 22)).addBox(-2.0f, 9.0f, 1.0f, 2, 2, 8);
		leg3.setRotationPoint(-8.0f, 12.0f, -1.0f);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 26, 22)).addBox(0.0f, 9.0f, 1.0f, 2, 2, 8);
		leg4.setRotationPoint(8.0f, 12.0f, -1.0f);
		leg4.setTextureSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg4p2 = new ModelRenderer(this, 23, 10)).addBox(-2.0f, -2.0f, -4.0f, 6, 4, 6);
		leg4p2.setRotationPoint(8.0f, 12.0f, -1.0f);
		leg4p2.setTextureSize(128, 32);
		leg4p2.mirror = true;
		setRotation(leg4p2, 0.0f, 0.0f, 0.0f);
		(leg3p2 = new ModelRenderer(this, 23, 10)).addBox(-4.0f, -2.0f, -4.0f, 6, 4, 6);
		leg3p2.setRotationPoint(-8.0f, 12.0f, -1.0f);
		leg3p2.setTextureSize(128, 32);
		leg3p2.mirror = true;
		setRotation(leg3p2, 0.0f, 0.0f, 0.0f);
		(leg4p3 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
		leg4p3.setRotationPoint(8.0f, 12.0f, -1.0f);
		leg4p3.setTextureSize(128, 32);
		leg4p3.mirror = true;
		setRotation(leg4p3, 0.0f, 0.0f, 0.0f);
		(leg3p3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
		leg3p3.setRotationPoint(-8.0f, 12.0f, -1.0f);
		leg3p3.setTextureSize(128, 32);
		leg3p3.mirror = true;
		setRotation(leg3p3, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 88, 0)).addBox(-1.0f, -11.0f, 4.0f, 2, 4, 6);
		body2.setRotationPoint(-7.0f, 20.0f, 5.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 82, 11)).addBox(-6.0f, -10.0f, -7.0f, 12, 11, 10);
		body3.setRotationPoint(0.0f, 14.0f, 7.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 1.22173f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 66, 0)).addBox(-2.0f, -8.0f, 3.0f, 4, 6, 6);
		body4.setRotationPoint(-7.0f, 20.0f, 5.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 0.5235988f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 66, 0)).addBox(-2.0f, -8.0f, 3.0f, 4, 6, 6);
		body5.setRotationPoint(0.0f, 14.0f, 7.0f);
		body5.setTextureSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 1.22173f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 88, 0)).addBox(-1.0f, -11.0f, 4.0f, 2, 4, 6);
		body6.setRotationPoint(0.0f, 14.0f, 7.0f);
		body6.setTextureSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 0.6981317f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 66, 0)).addBox(-2.0f, -8.0f, 3.0f, 4, 6, 6);
		body7.setRotationPoint(7.0f, 20.0f, 5.0f);
		body7.setTextureSize(128, 32);
		body7.mirror = true;
		setRotation(body7, 0.5235988f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 88, 0)).addBox(-1.0f, -11.0f, 4.0f, 2, 4, 6);
		body8.setRotationPoint(7.0f, 20.0f, 5.0f);
		body8.setTextureSize(128, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 26, 0)).addBox(-1.0f, -3.0f, -10.0f, 3, 6, 2);
		head2.setRotationPoint(-0.5f, 11.0f, -2.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 0)).addBox(-1.0f, -4.0f, -6.0f, 3, 8, 6);
		head3.setRotationPoint(-0.5f, 11.0f, -2.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4p3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3p3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.rotateAngleY = 0.0f;
		leg3p2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3p2.rotateAngleY = 0.0f;
		leg3p3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3p3.rotateAngleY = 0.0f;
		leg4p2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4p3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

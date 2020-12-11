package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class UrkaModel extends EntityModel<MobEntity> {
	private final ModelRenderer head1;
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer body5;

	public UrkaModel() {
		textureWidth = 128;
		textureHeight = 32;
		(head1 = new ModelRenderer(this, 100, 23)).addBox(2.5f, -5.5f, -17.0f, 1, 1, 5);
		head1.setRotationPoint(0.0f, 2.0f, -7.0f);
		head1.setTextureSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.8726646f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 46, 0)).addBox(-6.0f, 0.0f, -7.0f, 12, 7, 7);
		body.setRotationPoint(0.0f, 5.0f, 2.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 90, 18)).addBox(-1.0f, 0.0f, 0.0f, 2, 12, 2);
		leg1.setRotationPoint(-5.0f, 12.0f, 7.0f);
		leg1.setTextureSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 90, 18)).addBox(-1.0f, 0.0f, 0.0f, 2, 12, 2);
		leg2.setRotationPoint(5.0f, 12.0f, 7.0f);
		leg2.setTextureSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 90, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2);
		leg3.setRotationPoint(-5.0f, 12.0f, -5.0f);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 90, 18)).addBox(-1.0f, 0.0f, -1.0f, 2, 12, 2);
		leg4.setRotationPoint(5.0f, 12.0f, -5.0f);
		leg4.setTextureSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 89, 7)).addBox(-6.0f, -4.0f, -5.0f, 12, 4, 7);
		body2.setRotationPoint(0.0f, 5.0f, 2.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 0, 11)).addBox(0.0f, -9.0f, -1.0f, 0, 15, 6);
		body3.setRotationPoint(0.0f, 4.0f, 2.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 1.570796f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 15, 18)).addBox(-6.0f, -8.0f, -7.0f, 12, 4, 10);
		body4.setRotationPoint(0.0f, 5.0f, 2.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 1.570796f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -5.0f, -9.0f, 6, 6, 9);
		head2.setRotationPoint(0.0f, 2.0f, -7.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.8726646f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 100, 23)).addBox(-3.5f, -5.5f, -17.0f, 1, 1, 5);
		head3.setRotationPoint(0.0f, 2.0f, -7.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.8726646f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 106, 21)).addBox(2.0f, -6.0f, -12.0f, 2, 2, 9);
		head4.setRotationPoint(0.0f, 2.0f, -7.0f);
		head4.setTextureSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.8726646f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 106, 21)).addBox(-4.0f, -6.0f, -12.0f, 2, 2, 9);
		head5.setRotationPoint(0.0f, 2.0f, -7.0f);
		head5.setTextureSize(128, 32);
		head5.mirror = true;
		setRotation(head5, 0.8726646f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 62, 17)).addBox(-4.0f, -12.0f, -4.0f, 8, 10, 4);
		body5.setRotationPoint(0.0f, 4.0f, 2.0f);
		body5.setTextureSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 1.047198f, 0.0f, 0.0f);
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head1.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head2.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head3.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head4.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head5.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1.rotateAngleY = 0.0f;
		leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

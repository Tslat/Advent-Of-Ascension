package net.tslat.aoa3.client.model.entity.animal;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class CorateeModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;

	public CorateeModel() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -4.0f, -6.0f, 10, 10, 6);
		head.setRotationPoint(0.0f, 6.0f, -4.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 112, 18)).addBox(0.0f, 17.0f, -3.0f, 2, 8, 6);
		body.setRotationPoint(2.0f, 7.0f, 6.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 1.22173f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 8, 4);
		leg1.setRotationPoint(-5.0f, 16.0f, 12.0f);
		leg1.setTextureSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 8, 4);
		leg2.setRotationPoint(5.0f, 16.0f, 12.0f);
		leg2.setTextureSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 8, 4);
		leg3.setRotationPoint(-5.0f, 16.0f, -1.0f);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 8, 4);
		leg4.setRotationPoint(5.0f, 16.0f, -1.0f);
		leg4.setTextureSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 35, 0)).addBox(-6.0f, -10.0f, -7.0f, 14, 18, 14);
		body2.setRotationPoint(-1.0f, 9.0f, 6.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 95, 0)).addBox(-6.0f, 7.0f, -4.0f, 8, 10, 8);
		body3.setRotationPoint(2.0f, 7.0f, 6.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 1.22173f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 95, 18)).addBox(-6.0f, 17.0f, -3.0f, 2, 8, 6);
		body4.setRotationPoint(2.0f, 7.0f, 6.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 1.22173f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		if (isChild) {
			matrix.push();
			matrix.translate(0, 0.6875f, 0.0625f);
			matrix.scale(0.75f, 0.75f, 0.75f);
			head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			matrix.pop();
			matrix.push();
			matrix.scale(0.5f, 0.5f, 0.5f);
			matrix.translate(0, 1.5f, 0);
			head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			matrix.pop();
		}
		else {
			head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
			body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		}
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 79.57747f;
		head.rotateAngleX = headPitch / 76.39437f;
		leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}

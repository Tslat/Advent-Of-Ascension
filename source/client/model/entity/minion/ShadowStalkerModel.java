package net.tslat.aoa3.client.model.entity.minion;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class ShadowStalkerModel extends EntityModel<MobEntity> {
	private final ModelRenderer shape1;
	private final ModelRenderer Eye;
	private final ModelRenderer shape2;
	private final ModelRenderer shape3;
	private final ModelRenderer shape4;
	private final ModelRenderer shape5;

	public ShadowStalkerModel() {
		textureWidth = 64;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 3, 20)).addBox(-8.0f, 0.0f, -1.0f, 12, 1, 10);
		shape1.setRotationPoint(2.0f, 21.0f, -4.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.1745329f, 0.0f, 0.0f);
		(Eye = new ModelRenderer(this, 0, 0)).addBox(-6.0f, -10.0f, -4.0f, 12, 10, 8);
		Eye.setRotationPoint(0.0f, 19.0f, 0.0f);
		Eye.setTextureSize(64, 32);
		Eye.mirror = true;
		setRotation(Eye, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 54, 20)).addBox(2.0f, -14.0f, 6.0f, 2, 5, 2);
		shape2.setRotationPoint(0.0f, 19.0f, -4.0f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.5235988f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 20)).addBox(-8.0f, -12.0f, -1.0f, 16, 2, 10);
		shape3.setRotationPoint(0.0f, 19.0f, -4.0f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 54, 20)).addBox(-4.0f, -14.0f, 6.0f, 2, 5, 2);
		shape4.setRotationPoint(0.0f, 19.0f, -4.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.5235988f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 0, 20)).addBox(-8.0f, 0.0f, -1.0f, 16, 1, 10);
		shape5.setRotationPoint(0.0f, 19.0f, -4.0f);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
	}

	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		Eye.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Eye.rotateAngleY = netHeadYaw / 57.295776f;
	}
}
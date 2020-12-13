package net.tslat.aoa3.client.model.entity.mob.candyland;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class AirheadModel extends EntityModel<MobEntity> {
	private final ModelRenderer R2;
	private final ModelRenderer shape2;
	private final ModelRenderer shape3;
	private final ModelRenderer R1;
	private final ModelRenderer R3;
	private final ModelRenderer R4;

	public AirheadModel() {
		super(RenderType::getEntityTranslucent);
		textureWidth = 64;
		textureHeight = 64;
		(R2 = new ModelRenderer(this, 26, 47)).addBox(7.5f, 4.0f, -4.0f, 1, 1, 10);
		R2.setRotationPoint(0.0f, 17.0f, -1.0f);
		R2.setTextureSize(64, 64);
		R2.mirror = true;
		setRotation(R2, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 16, 16, 16);
		shape2.setRotationPoint(-8.0f, -3.0f, -8.0f);
		shape2.setTextureSize(64, 64);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 34)).addBox(0.0f, 0.0f, 0.0f, 6, 7, 6);
		shape3.setRotationPoint(-3.0f, 13.0f, -3.0f);
		shape3.setTextureSize(64, 64);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(R1 = new ModelRenderer(this, 26, 47)).addBox(-8.5f, 4.0f, -4.0f, 1, 1, 10);
		R1.setRotationPoint(0.0f, 17.0f, -1.0f);
		R1.setTextureSize(64, 64);
		R1.mirror = true;
		setRotation(R1, 0.0f, 0.0f, 0.0f);
		(R3 = new ModelRenderer(this, 26, 34)).addBox(6.0f, 0.0f, -2.0f, 4, 4, 6);
		R3.setRotationPoint(0.0f, 17.0f, -1.0f);
		R3.setTextureSize(64, 64);
		R3.mirror = true;
		setRotation(R3, 0.0f, 0.0f, 0.0f);
		(R4 = new ModelRenderer(this, 26, 34)).addBox(-10.0f, 0.0f, -2.0f, 4, 4, 6);
		R4.setRotationPoint(0.0f, 17.0f, -1.0f);
		R4.setTextureSize(64, 64);
		R4.mirror = true;
		setRotation(R4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		R2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		R1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		R3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		R4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		R1.rotateAngleY = ageInTicks * 0.117f * 1.25f;
		R2.rotateAngleY = ageInTicks * 0.117f * 1.25f;
		R3.rotateAngleY = ageInTicks * 0.117f * 1.25f;
		R4.rotateAngleY = ageInTicks * 0.117f * 1.25f;
	}
}

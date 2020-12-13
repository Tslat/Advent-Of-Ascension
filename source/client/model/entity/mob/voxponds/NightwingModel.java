package net.tslat.aoa3.client.model.entity.mob.voxponds;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class NightwingModel extends EntityModel<MobEntity> {
	private final ModelRenderer wingR;
	private final ModelRenderer shape2;
	private final ModelRenderer wingL;
	private final ModelRenderer wingL2;
	private final ModelRenderer wingR2;
	private final ModelRenderer shape3;
	private final ModelRenderer shape4;
	private final ModelRenderer shape5;
	private final ModelRenderer shape6;
	private final ModelRenderer shape8;
	private final ModelRenderer shape7;
	private final ModelRenderer shape9;
	private final ModelRenderer shape10;
	private final ModelRenderer shape11;

	public NightwingModel() {
		textureWidth = 128;
		textureHeight = 64;
		(wingR = new ModelRenderer(this, 29, 34)).addBox(-15.0f, -4.0f, -5.0f, 14, 4, 0);
		wingR.setRotationPoint(-5.0f, 12.0f, 4.0f);
		wingR.setTextureSize(128, 64);
		wingR.mirror = true;
		setRotation(wingR, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 97, 13)).addBox(0.0f, 5.0f, 0.0f, 8, 1, 6);
		shape2.setRotationPoint(-4.0f, 9.0f, -12.0f);
		shape2.setTextureSize(128, 64);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(wingL = new ModelRenderer(this, 29, 34)).addBox(0.0f, -4.0f, -5.0f, 14, 4, 0);
		wingL.setRotationPoint(5.0f, 12.0f, 4.0f);
		wingL.setTextureSize(128, 64);
		wingL.mirror = true;
		setRotation(wingL, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 35, 14)).addBox(0.0f, 0.0f, -5.0f, 14, 2, 12);
		wingL2.setRotationPoint(5.0f, 12.0f, 4.0f);
		wingL2.setTextureSize(128, 64);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 35, 14)).addBox(-15.0f, 0.0f, -5.0f, 14, 2, 12);
		wingR2.setRotationPoint(-5.0f, 12.0f, 4.0f);
		wingR2.setTextureSize(128, 64);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 90, 22)).addBox(0.0f, 0.0f, 0.0f, 8, 6, 8);
		shape3.setRotationPoint(-4.0f, 10.0f, 8.0f);
		shape3.setTextureSize(128, 64);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 2, 33)).addBox(1.0f, -2.0f, 6.0f, 4, 4, 8);
		shape4.setRotationPoint(0.0f, 11.0f, 15.0f);
		shape4.setTextureSize(128, 64);
		shape4.mirror = true;
		setRotation(shape4, -0.8726646f, 0.5235988f, 0.0f);
		(shape5 = new ModelRenderer(this, 2, 33)).addBox(1.0f, -2.0f, 6.0f, 4, 4, 8);
		shape5.setRotationPoint(-5.0f, 11.0f, 12.0f);
		shape5.setTextureSize(128, 64);
		shape5.mirror = true;
		setRotation(shape5, -0.8726646f, -0.5235988f, 0.0f);
		(shape6 = new ModelRenderer(this, 19, 47)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 8);
		shape6.setRotationPoint(-5.0f, 11.0f, 12.0f);
		shape6.setTextureSize(128, 64);
		shape6.mirror = true;
		setRotation(shape6, -0.4363323f, -0.5235988f, 0.0f);
		(shape8 = new ModelRenderer(this, 19, 47)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 8);
		shape8.setRotationPoint(0.0f, 11.0f, 15.0f);
		shape8.setTextureSize(128, 64);
		shape8.mirror = true;
		setRotation(shape8, -0.4363323f, 0.5235988f, 0.0f);
		(shape7 = new ModelRenderer(this, 77, 1)).addBox(1.0f, 0.0f, 0.0f, 1, 4, 7);
		shape7.setRotationPoint(1.0f, 10.0f, -11.0f);
		shape7.setTextureSize(128, 64);
		shape7.mirror = true;
		setRotation(shape7, 0.7853982f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 97, 1)).addBox(0.0f, 0.0f, 0.0f, 8, 4, 6);
		shape9.setRotationPoint(-4.0f, 9.0f, -12.0f);
		shape9.setTextureSize(128, 64);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 77, 1)).addBox(1.0f, 0.0f, 0.0f, 1, 4, 7);
		shape10.setRotationPoint(-4.0f, 10.0f, -11.0f);
		shape10.setTextureSize(128, 64);
		shape10.mirror = true;
		setRotation(shape10, 0.7853982f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 68, 37)).addBox(0.0f, 0.0f, 0.0f, 12, 9, 18);
		shape11.setRotationPoint(-6.0f, 9.0f, -6.0f);
		shape11.setTextureSize(128, 64);
		shape11.mirror = true;
		setRotation(shape11, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		wingR.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		wingR.rotateAngleZ = MathHelper.cos(ageInTicks * 0.3f) * 3.1415927f * 0.15f;
		wingL.rotateAngleZ = -wingR.rotateAngleZ;
		wingR2.rotateAngleZ = MathHelper.cos(ageInTicks * 0.3f) * 3.1415927f * 0.15f;
		wingL2.rotateAngleZ = -wingR2.rotateAngleZ;
	}
}

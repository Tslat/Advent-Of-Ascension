package net.tslat.aoa3.client.model.entity.mob.haven;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class OrbiterModel extends EntityModel<MobEntity> {
	private final ModelRenderer shape1;
	private final ModelRenderer S2r;
	private final ModelRenderer shape7;
	private final ModelRenderer shape2;
	private final ModelRenderer shape3;
	private final ModelRenderer shape4;
	private final ModelRenderer shape5;
	private final ModelRenderer shape6;
	private final ModelRenderer S2r6;
	private final ModelRenderer S2r5;
	private final ModelRenderer S2r4;
	private final ModelRenderer S2r3;
	private final ModelRenderer S2r2;

	public OrbiterModel() {
		textureWidth = 64;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 33, 15)).addBox(-1.0f, 1.0f, -1.0f, 2, 3, 2);
		shape1.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(S2r = new ModelRenderer(this, 0, 15)).addBox(5.0f, -11.0f, -2.0f, 1, 3, 4);
		S2r.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r.setTextureSize(64, 32);
		S2r.mirror = true;
		setRotation(S2r, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 16, 0)).addBox(-2.0f, -8.0f, -2.0f, 4, 4, 4);
		shape7.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape7.setTextureSize(64, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 32, 0)).addBox(4.0f, -2.0f, -2.0f, 4, 4, 4);
		shape2.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 48, 0)).addBox(-8.0f, -2.0f, -2.0f, 4, 4, 4);
		shape3.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape3.setTextureSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 43, 9)).addBox(-4.0f, -1.0f, -1.0f, 8, 2, 2);
		shape4.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape4.setTextureSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 33, 9)).addBox(-1.0f, -4.0f, -1.0f, 2, 3, 2);
		shape5.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape5.setTextureSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, 4.0f, -2.0f, 4, 4, 4);
		shape6.setRotationPoint(0.0f, 12.0f, 0.0f);
		shape6.setTextureSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(S2r6 = new ModelRenderer(this, 0, 15)).addBox(5.0f, 8.0f, -2.0f, 1, 3, 4);
		S2r6.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r6.setTextureSize(64, 32);
		S2r6.mirror = true;
		setRotation(S2r6, 0.0f, 0.0f, 0.0f);
		(S2r5 = new ModelRenderer(this, 0, 9)).addBox(-5.0f, -10.0f, -2.0f, 10, 1, 4);
		S2r5.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r5.setTextureSize(64, 32);
		S2r5.mirror = true;
		setRotation(S2r5, 0.0f, 0.0f, 0.0f);
		(S2r4 = new ModelRenderer(this, 0, 9)).addBox(-5.0f, 9.0f, -2.0f, 10, 1, 4);
		S2r4.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r4.setTextureSize(64, 32);
		S2r4.mirror = true;
		setRotation(S2r4, 0.0f, 0.0f, 0.0f);
		(S2r3 = new ModelRenderer(this, 0, 15)).addBox(-6.0f, -11.0f, -2.0f, 1, 3, 4);
		S2r3.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r3.setTextureSize(64, 32);
		S2r3.mirror = true;
		setRotation(S2r3, 0.0f, 0.0f, 0.0f);
		(S2r2 = new ModelRenderer(this, 0, 15)).addBox(-6.0f, 8.0f, -2.0f, 1, 3, 4);
		S2r2.setRotationPoint(0.0f, 12.0f, 0.0f);
		S2r2.setTextureSize(64, 32);
		S2r2.mirror = true;
		setRotation(S2r2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		S2r.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		S2r6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		S2r5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		S2r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		S2r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		S2r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		shape1.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		shape2.rotateAngleX = ageInTicks * 0.067f * 1.25f;
		shape3.rotateAngleX = ageInTicks * 0.067f * 1.25f;
		shape4.rotateAngleX = ageInTicks * 0.067f * 1.25f;
		shape5.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		shape6.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		shape7.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		S2r.rotateAngleZ = ageInTicks * 0.067f * 3.25f;
		S2r2.rotateAngleZ = ageInTicks * 0.067f * 3.25f;
		S2r3.rotateAngleZ = ageInTicks * 0.067f * 3.25f;
		S2r4.rotateAngleZ = ageInTicks * 0.067f * 3.25f;
		S2r5.rotateAngleZ = ageInTicks * 0.067f * 3.25f;
		S2r6.rotateAngleZ = ageInTicks * 0.067f * 3.25f;
	}
}

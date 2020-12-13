package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class HostModel extends EntityModel<MobEntity> {
	private final ModelRenderer body1;
	private final ModelRenderer r1;
	private final ModelRenderer body2;
	private final ModelRenderer r2;
	private final ModelRenderer r3;
	private final ModelRenderer r4;
	private final ModelRenderer r5;
	private final ModelRenderer r6;
	private final ModelRenderer r7;
	private final ModelRenderer r8;
	private final ModelRenderer r9;
	private final ModelRenderer r10;
	private final ModelRenderer r11;
	private final ModelRenderer r12;

	public HostModel() {
		textureWidth = 64;
		textureHeight = 64;
		(body1 = new ModelRenderer(this, 0, 21)).addBox(-4.0f, 0.0f, -4.0f, 8, 5, 8);
		body1.setRotationPoint(0.0f, 17.0f, 0.0f);
		body1.setTextureSize(64, 64);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 0, 49)).addBox(9.0f, 9.0f, 5.0f, 2, 3, 2);
		r1.setRotationPoint(0.0f, 11.0f, 0.0f);
		r1.setTextureSize(64, 64);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 0, 0)).addBox(-7.0f, 0.0f, -7.0f, 14, 6, 14);
		body2.setRotationPoint(0.0f, 11.0f, 0.0f);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 42)).addBox(5.0f, 9.0f, -11.0f, 2, 3, 2);
		r2.setRotationPoint(0.0f, 11.0f, 0.0f);
		r2.setTextureSize(64, 64);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 0, 42)).addBox(5.0f, 9.0f, 9.0f, 2, 3, 2);
		r3.setRotationPoint(0.0f, 11.0f, 0.0f);
		r3.setTextureSize(64, 64);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 0, 49)).addBox(-11.0f, 9.0f, 5.0f, 2, 3, 2);
		r4.setRotationPoint(0.0f, 11.0f, 0.0f);
		r4.setTextureSize(64, 64);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 0, 36)).addBox(-9.0f, 7.0f, -11.0f, 18, 2, 2);
		r5.setRotationPoint(0.0f, 11.0f, 0.0f);
		r5.setTextureSize(64, 64);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 0, 36)).addBox(-9.0f, 7.0f, 9.0f, 18, 2, 2);
		r6.setRotationPoint(0.0f, 11.0f, 0.0f);
		r6.setTextureSize(64, 64);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
		(r7 = new ModelRenderer(this, 0, 42)).addBox(-7.0f, 9.0f, -11.0f, 2, 3, 2);
		r7.setRotationPoint(0.0f, 11.0f, 0.0f);
		r7.setTextureSize(64, 64);
		r7.mirror = true;
		setRotation(r7, 0.0f, 0.0f, 0.0f);
		(r8 = new ModelRenderer(this, 0, 42)).addBox(-7.0f, 9.0f, 9.0f, 2, 3, 2);
		r8.setRotationPoint(0.0f, 11.0f, 0.0f);
		r8.setTextureSize(64, 64);
		r8.mirror = true;
		setRotation(r8, 0.0f, 0.0f, 0.0f);
		(r9 = new ModelRenderer(this, 0, 40)).addBox(9.0f, 7.0f, -11.0f, 2, 2, 22);
		r9.setRotationPoint(0.0f, 11.0f, 0.0f);
		r9.setTextureSize(64, 64);
		r9.mirror = true;
		setRotation(r9, 0.0f, 0.0f, 0.0f);
		(r10 = new ModelRenderer(this, 0, 40)).addBox(-11.0f, 7.0f, -11.0f, 2, 2, 22);
		r10.setRotationPoint(0.0f, 11.0f, 0.0f);
		r10.setTextureSize(64, 64);
		r10.mirror = true;
		setRotation(r10, 0.0f, 0.0f, 0.0f);
		(r11 = new ModelRenderer(this, 0, 49)).addBox(9.0f, 9.0f, -7.0f, 2, 3, 2);
		r11.setRotationPoint(0.0f, 11.0f, 0.0f);
		r11.setTextureSize(64, 64);
		r11.mirror = true;
		setRotation(r11, 0.0f, 0.0f, 0.0f);
		(r12 = new ModelRenderer(this, 0, 49)).addBox(-11.0f, 9.0f, -7.0f, 2, 3, 2);
		r12.setRotationPoint(0.0f, 11.0f, 0.0f);
		r12.setTextureSize(64, 64);
		r12.mirror = true;
		setRotation(r12, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.rotateAngleY = ageInTicks * 0.267f * 1.25f;
		r2.rotateAngleY = ageInTicks * 0.267f * 1.25f;
		r3.rotateAngleY = ageInTicks * 0.267f * 1.25f;
		r4.rotateAngleY = ageInTicks * 0.267f * 1.25f;
		r5.rotateAngleY = ageInTicks * 0.267f * 1.25f;
		r6.rotateAngleY = ageInTicks * 0.267f * 1.25f;
		r7.rotateAngleY = ageInTicks * 0.267f * 1.25f;
		r8.rotateAngleY = ageInTicks * 0.267f * 1.25f;
		r9.rotateAngleY = ageInTicks * 0.267f * 1.25f;
		r10.rotateAngleY = ageInTicks * 0.267f * 1.25f;
		r11.rotateAngleY = ageInTicks * 0.267f * 1.25f;
		r12.rotateAngleY = ageInTicks * 0.267f * 1.25f;
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

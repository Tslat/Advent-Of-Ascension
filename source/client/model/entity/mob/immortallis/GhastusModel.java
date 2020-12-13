package net.tslat.aoa3.client.model.entity.mob.immortallis;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class GhastusModel extends EntityModel<MobEntity> {
	private final ModelRenderer r1;
	private final ModelRenderer head;
	private final ModelRenderer r2;
	private final ModelRenderer r3;
	private final ModelRenderer r4;
	private final ModelRenderer r5;
	private final ModelRenderer r6;
	private final ModelRenderer r7;
	private final ModelRenderer r8;

	public GhastusModel() {
		super(RenderType::getEntityTranslucent);
		textureWidth = 64;
		textureHeight = 32;
		(r1 = new ModelRenderer(this, 0, 0)).addBox(-10.0f, -2.0f, 2.0f, 1, 1, 1);
		r1.setRotationPoint(0.0f, 15.0f, 0.0f);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 15.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 0)).addBox(-7.0f, -3.0f, -4.0f, 1, 1, 1);
		r2.setRotationPoint(0.0f, 15.0f, 0.0f);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 0, 0)).addBox(-6.0f, 0.0f, 2.0f, 1, 1, 1);
		r3.setRotationPoint(0.0f, 15.0f, 0.0f);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 0, 0)).addBox(-9.0f, 0.0f, -1.0f, 1, 1, 1);
		r4.setRotationPoint(0.0f, 15.0f, 0.0f);
		r4.setTextureSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 0, 0)).addBox(-7.0f, 2.0f, 2.0f, 1, 1, 1);
		r5.setRotationPoint(0.0f, 15.0f, 0.0f);
		r5.setTextureSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 0, 0)).addBox(-7.0f, 2.0f, -3.0f, 1, 1, 1);
		r6.setRotationPoint(0.0f, 15.0f, 0.0f);
		r6.setTextureSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
		(r7 = new ModelRenderer(this, 0, 0)).addBox(-10.0f, 0.0f, -3.0f, 1, 1, 1);
		r7.setRotationPoint(0.0f, 15.0f, 0.0f);
		r7.setTextureSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0.0f, 0.0f, 0.0f);
		(r8 = new ModelRenderer(this, 0, 0)).addBox(-6.0f, -2.0f, -1.0f, 1, 1, 1);
		r8.setRotationPoint(0.0f, 15.0f, 0.0f);
		r8.setTextureSize(64, 32);
		r8.mirror = true;
		setRotation(r8, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		r2.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		r3.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		r4.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		r5.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		r6.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		r7.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		r8.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
	}
}

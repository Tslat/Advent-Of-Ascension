package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class RoloscopeModel extends EntityModel<MobEntity> {
	private final ModelRenderer r1;
	private final ModelRenderer Eye;
	private final ModelRenderer r2;
	private final ModelRenderer r3;
	private final ModelRenderer r4;
	private final ModelRenderer r5;
	private final ModelRenderer r6;
	private final ModelRenderer r7;
	private final ModelRenderer r8;
	private final ModelRenderer r9;

	public RoloscopeModel() {
		textureWidth = 64;
		textureHeight = 64;
		(r1 = new ModelRenderer(this, 0, 46)).addBox(0.5f, 7.0f, -11.0f, 3, 4, 2);
		r1.setRotationPoint(0.0f, 7.0f, 0.0f);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(Eye = new ModelRenderer(this, 0, 0)).addBox(-5.0f, 0.0f, -4.0f, 10, 10, 10);
		Eye.setRotationPoint(0.0f, 7.0f, 0.0f);
		Eye.setTextureSize(64, 32);
		Eye.mirror = true;
		setRotation(Eye, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 21)).addBox(-7.0f, 11.0f, -5.0f, 14, 2, 13);
		r2.setRotationPoint(0.0f, 7.0f, 0.0f);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 0, 46)).addBox(-7.0f, 7.0f, -11.0f, 3, 4, 2);
		r3.setRotationPoint(0.0f, 7.0f, 0.0f);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 0, 46)).addBox(4.0f, 7.0f, -11.0f, 3, 4, 2);
		r4.setRotationPoint(0.0f, 7.0f, 0.0f);
		r4.setTextureSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 0, 46)).addBox(-3.5f, 7.0f, -11.0f, 3, 4, 2);
		r5.setRotationPoint(0.0f, 7.0f, 0.0f);
		r5.setTextureSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 0, 37)).addBox(4.0f, 11.0f, -11.0f, 3, 2, 6);
		r6.setRotationPoint(0.0f, 7.0f, 0.0f);
		r6.setTextureSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
		(r7 = new ModelRenderer(this, 0, 37)).addBox(0.5f, 11.0f, -11.0f, 3, 2, 6);
		r7.setRotationPoint(0.0f, 7.0f, 0.0f);
		r7.setTextureSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0.0f, 0.0f, 0.0f);
		(r8 = new ModelRenderer(this, 0, 37)).addBox(-3.5f, 11.0f, -11.0f, 3, 2, 6);
		r8.setRotationPoint(0.0f, 7.0f, 0.0f);
		r8.setTextureSize(64, 32);
		r8.mirror = true;
		setRotation(r8, 0.0f, 0.0f, 0.0f);
		(r9 = new ModelRenderer(this, 0, 37)).addBox(-7.0f, 11.0f, -11.0f, 3, 2, 6);
		r9.setRotationPoint(0.0f, 7.0f, 0.0f);
		r9.setTextureSize(64, 32);
		r9.mirror = true;
		setRotation(r9, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Eye.rotateAngleY = netHeadYaw / 57.295776f;
		Eye.rotateAngleX = headPitch / 54.11268f;
		r1.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r2.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r3.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r4.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r5.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r6.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r7.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r8.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r9.rotateAngleY = ageInTicks * 0.067f * 1.25f;
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Eye.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

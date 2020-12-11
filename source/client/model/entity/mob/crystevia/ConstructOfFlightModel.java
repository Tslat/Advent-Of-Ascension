package net.tslat.aoa3.client.model.entity.mob.crystevia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ConstructOfFlightModel extends EntityModel<MobEntity> {
	private final ModelRenderer WingL1;
	private final ModelRenderer r1;
	private final ModelRenderer WingL2;
	private final ModelRenderer WingL3;
	private final ModelRenderer WingR1;
	private final ModelRenderer WingR2;
	private final ModelRenderer WingR3;
	private final ModelRenderer shape1;
	private final ModelRenderer r2;
	private final ModelRenderer r3;
	private final ModelRenderer r4;
	private final ModelRenderer r5;
	private final ModelRenderer r6;

	public ConstructOfFlightModel() {
		super(RenderType::getEntityTranslucent);
		textureWidth = 64;
		textureHeight = 64;
		(WingL1 = new ModelRenderer(this, 1, 41)).addBox(9.0f, 0.0f, 3.0f, 4, 2, 2);
		WingL1.setRotationPoint(6.0f, 14.0f, -1.0f);
		WingL1.setTextureSize(64, 64);
		WingL1.mirror = true;
		setRotation(WingL1, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 43, 6)).addBox(2.0f, -6.0f, -8.0f, 3, 2, 2);
		r1.setRotationPoint(0.0f, 11.0f, -1.0f);
		r1.setTextureSize(64, 64);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(WingL2 = new ModelRenderer(this, 0, 22)).addBox(-1.0f, 0.0f, -5.0f, 10, 2, 10);
		WingL2.setRotationPoint(6.0f, 14.0f, -1.0f);
		WingL2.setTextureSize(64, 64);
		WingL2.mirror = true;
		setRotation(WingL2, 0.0f, 0.0f, 0.0f);
		(WingL3 = new ModelRenderer(this, 1, 41)).addBox(9.0f, 0.0f, -5.0f, 4, 2, 2);
		WingL3.setRotationPoint(6.0f, 14.0f, -1.0f);
		WingL3.setTextureSize(64, 64);
		WingL3.mirror = true;
		setRotation(WingL3, 0.0f, 0.0f, 0.0f);
		(WingR1 = new ModelRenderer(this, 0, 22)).addBox(-9.0f, 0.0f, -5.0f, 10, 2, 10);
		WingR1.setRotationPoint(-6.0f, 14.0f, -1.0f);
		WingR1.setTextureSize(64, 64);
		WingR1.mirror = true;
		setRotation(WingR1, 0.0f, 0.0f, 0.0f);
		(WingR2 = new ModelRenderer(this, 1, 35)).addBox(-13.0f, 0.0f, -5.0f, 4, 2, 2);
		WingR2.setRotationPoint(-6.0f, 14.0f, -1.0f);
		WingR2.setTextureSize(64, 64);
		WingR2.mirror = true;
		setRotation(WingR2, 0.0f, 0.0f, 0.0f);
		(WingR3 = new ModelRenderer(this, 1, 35)).addBox(-13.0f, 0.0f, 3.0f, 4, 2, 2);
		WingR3.setRotationPoint(-6.0f, 14.0f, -1.0f);
		WingR3.setTextureSize(64, 64);
		WingR3.mirror = true;
		setRotation(WingR3, 0.0f, 0.0f, 0.0f);
		(shape1 = new ModelRenderer(this, 0, 0)).addBox(-5.0f, 0.0f, 0.0f, 10, 10, 10);
		shape1.setRotationPoint(0.0f, 11.0f, -6.0f);
		shape1.setTextureSize(64, 64);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 43, 6)).addBox(2.0f, -6.0f, 6.0f, 3, 2, 2);
		r2.setRotationPoint(0.0f, 11.0f, -1.0f);
		r2.setTextureSize(64, 64);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 34, 0)).addBox(-5.0f, -4.0f, 6.0f, 10, 2, 2);
		r3.setRotationPoint(0.0f, 11.0f, -1.0f);
		r3.setTextureSize(64, 64);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 34, 0)).addBox(-5.0f, -4.0f, -8.0f, 10, 2, 2);
		r4.setRotationPoint(0.0f, 11.0f, -1.0f);
		r4.setTextureSize(64, 64);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 43, 6)).addBox(-5.0f, -6.0f, 6.0f, 3, 2, 2);
		r5.setRotationPoint(0.0f, 11.0f, -1.0f);
		r5.setTextureSize(64, 64);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 43, 6)).addBox(-5.0f, -6.0f, -8.0f, 3, 2, 2);
		r6.setRotationPoint(0.0f, 11.0f, -1.0f);
		r6.setTextureSize(64, 64);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		WingL1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		WingL2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		WingL3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		WingR1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		WingR2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		WingR3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r2.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r3.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r4.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r5.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		r6.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		WingR1.rotateAngleZ = MathHelper.cos(ageInTicks * 0.1f) * 3.1415927f * 0.15f;
		WingL1.rotateAngleZ = -WingR1.rotateAngleZ;
		WingR2.rotateAngleZ = MathHelper.cos(ageInTicks * 0.1f) * 3.1415927f * 0.15f;
		WingL2.rotateAngleZ = -WingR2.rotateAngleZ;
		WingR3.rotateAngleZ = MathHelper.cos(ageInTicks * 0.1f) * 3.1415927f * 0.15f;
		WingL3.rotateAngleZ = -WingR2.rotateAngleZ;
	}
}

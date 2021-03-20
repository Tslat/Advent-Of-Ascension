package net.tslat.aoa3.client.model.entity.mob.crystevia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ConstructOfRangeModel extends EntityModel<MobEntity> {
	private final ModelRenderer r4;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightArm2;
	private final ModelRenderer rightArm3;
	private final ModelRenderer body;
	private final ModelRenderer r1;
	private final ModelRenderer r2;
	private final ModelRenderer r3;

	public ConstructOfRangeModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 64;
		(r4 = new ModelRenderer(this, 33, 38)).addBox(6.0f, -6.0f, 4.0f, 3, 12, 3);
		r4.setPos(0.0f, 0.0f, 0.0f);
		r4.setTexSize(64, 64);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 1)).addBox(-2.0f, -2.0f, -3.0f, 6, 14, 6);
		leftArm.setPos(9.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 20)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		rightLeg.setPos(-3.0f, 14.0f, 0.0f);
		rightLeg.setTexSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 20)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		leftLeg.setPos(3.0f, 14.0f, 0.0f);
		leftLeg.setTexSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 28, 22)).addBox(-4.0f, -9.0f, -11.0f, 6, 4, 6);
		rightArm.setPos(-9.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.5235988f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 1)).addBox(-4.0f, -2.0f, -3.0f, 6, 14, 6);
		rightArm2.setPos(-9.0f, 2.0f, 0.0f);
		rightArm2.setTexSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 55, 22)).addBox(-2.0f, -5.0f, -9.0f, 2, 20, 2);
		rightArm3.setPos(-9.0f, 2.0f, 0.0f);
		rightArm3.setTexSize(64, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.5235988f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 0, 1)).addBox(-6.0f, 0.0f, -3.0f, 12, 12, 6);
		body.setPos(0.0f, 0.0f, 0.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 0, 38)).addBox(-6.0f, -9.0f, 4.0f, 12, 3, 3);
		r1.setPos(0.0f, 0.0f, 0.0f);
		r1.setTexSize(64, 64);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 38)).addBox(-6.0f, 6.0f, 4.0f, 12, 3, 3);
		r2.setPos(0.0f, 0.0f, 0.0f);
		r2.setTexSize(64, 64);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 33, 38)).addBox(-9.0f, -6.0f, 4.0f, 3, 12, 3);
		r3.setPos(0.0f, 0.0f, 0.0f);
		r3.setTexSize(64, 64);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightArm.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.523f;
		rightArm.zRot = 0.0f;
		rightArm2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.zRot = 0.0f;
		rightArm3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.523f;
		rightArm2.zRot = 0.0f;
		leftArm.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		r1.zRot = ageInTicks * 0.067f * 1.25f;
		r2.zRot = ageInTicks * 0.067f * 1.25f;
		r3.zRot = ageInTicks * 0.067f * 1.25f;
		r4.zRot = ageInTicks * 0.067f * 1.25f;
		rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

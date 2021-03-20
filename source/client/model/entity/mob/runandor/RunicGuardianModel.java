package net.tslat.aoa3.client.model.entity.mob.runandor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class RunicGuardianModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;
	private final ModelRenderer body7;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer rightLeg3;
	private final ModelRenderer rightLeg2;
	private final ModelRenderer leftLeg3;
	private final ModelRenderer leftArm2;
	private final ModelRenderer rightArm2;
	private final ModelRenderer body8;
	private final ModelRenderer body9;

	public RunicGuardianModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -1.0f, 8, 8, 1);
		head.setPos(0.0f, 0.0f, -2.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 10)).addBox(-3.0f, 0.0f, -2.0f, 8, 1, 1);
		body.setPos(4.0f, 12.0f, 1.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 1.22173f);
		(rightArm = new ModelRenderer(this, 40, 16)).addBox(-4.0f, -2.0f, -2.0f, 6, 8, 1);
		rightArm.setPos(-7.0f, -3.0f, 0.0f);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.7853982f);
		(leftArm = new ModelRenderer(this, 40, 16)).addBox(-2.0f, -2.0f, -2.0f, 6, 8, 1);
		leftArm.setPos(7.0f, -3.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, -0.7853982f);
		(rightLeg = new ModelRenderer(this, 0, 25)).addBox(-3.0f, 7.0f, -2.0f, 6, 4, 1);
		rightLeg.setPos(-6.0f, 13.0f, 0.0f);
		rightLeg.setTexSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 25)).addBox(-3.0f, 7.0f, -2.0f, 6, 4, 1);
		leftLeg.setPos(6.0f, 13.0f, 0.0f);
		leftLeg.setTexSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 10, 1);
		body2.setPos(0.0f, 2.0f, 0.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 16, 10)).addBox(-3.0f, 0.0f, -2.0f, 8, 1, 1);
		body3.setPos(-3.0f, 12.0f, 1.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 1.919862f);
		(body4 = new ModelRenderer(this, 16, 13)).addBox(-4.0f, 0.0f, -2.0f, 1, 1, 1);
		body4.setPos(0.5f, 2.0f, 0.0f);
		body4.setTexSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 1.570796f);
		(body5 = new ModelRenderer(this, 16, 10)).addBox(-5.0f, 0.0f, -2.0f, 8, 1, 1);
		body5.setPos(6.0f, 0.0f, 1.0f);
		body5.setTexSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, -0.6108652f);
		(body6 = new ModelRenderer(this, 16, 10)).addBox(-3.0f, 0.0f, -2.0f, 8, 1, 1);
		body6.setPos(-6.0f, 0.0f, 1.0f);
		body6.setTexSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.6108652f);
		(body7 = new ModelRenderer(this, 16, 10)).addBox(-4.0f, 0.0f, -2.0f, 8, 1, 1);
		body7.setPos(0.5f, 2.0f, 1.0f);
		body7.setTexSize(64, 64);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 1.570796f);
		(leftLeg2 = new ModelRenderer(this, 0, 32)).addBox(-0.5f, 5.0f, -1.0f, 1, 4, 1);
		leftLeg2.setPos(6.0f, 13.0f, 0.0f);
		leftLeg2.setTexSize(64, 64);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(rightLeg3 = new ModelRenderer(this, 0, 16)).addBox(-0.5f, 5.0f, -1.0f, 1, 4, 1);
		rightLeg3.setPos(-6.0f, 13.0f, 0.0f);
		rightLeg3.setTexSize(64, 64);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 6, 6, 1);
		rightLeg2.setPos(-6.0f, 13.0f, 0.0f);
		rightLeg2.setTexSize(64, 64);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 6, 6, 1);
		leftLeg3.setPos(6.0f, 13.0f, 0.0f);
		leftLeg3.setTexSize(64, 64);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 40, 16)).addBox(-2.0f, -2.0f, -2.0f, 6, 8, 1);
		leftArm2.setPos(9.0f, 5.0f, 0.0f);
		leftArm2.setTexSize(64, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 16)).addBox(-4.0f, -2.0f, -2.0f, 6, 8, 1);
		rightArm2.setPos(-9.0f, 5.0f, 0.0f);
		rightArm2.setTexSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 16, 10)).addBox(-4.0f, 0.0f, -2.0f, 8, 1, 1);
		body8.setPos(-6.0f, 6.0f, 1.0f);
		body8.setTexSize(64, 64);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.3490659f);
		(body9 = new ModelRenderer(this, 16, 10)).addBox(-4.0f, 0.0f, -2.0f, 8, 1, 1);
		body9.setPos(6.0f, 6.0f, 1.0f);
		body9.setTexSize(64, 64);
		body9.mirror = true;
		setRotation(body9, 0.0f, 0.0f, -0.3490659f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		rightLeg2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg2.yRot = 0.0f;
		rightLeg3.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg3.yRot = 0.0f;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

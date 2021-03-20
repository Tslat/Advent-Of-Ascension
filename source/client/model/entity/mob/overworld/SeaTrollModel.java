package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class SeaTrollModel extends EntityModel<MobEntity> {
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer head;
	private final ModelRenderer nose;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftEar1;
	private final ModelRenderer leftEar2;
	private final ModelRenderer Staff;

	public SeaTrollModel() {
		texWidth = 64;
		texHeight = 32;
		(body = new ModelRenderer(this, 36, 15)).addBox(-4.0f, 0.0f, -3.0f, 8, 11, 6);
		body.setPos(0.0f, 3.0f, 1.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 18, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		rightLeg.setPos(-2.0f, 14.0f, 1.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 18, 18)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		leftLeg.setPos(2.0f, 14.0f, 1.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, 3.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(nose = new ModelRenderer(this, 33, 8)).addBox(-1.0f, -4.0f, -6.0f, 2, 5, 2);
		nose.setPos(0.0f, 3.0f, 0.0f);
		nose.setTexSize(64, 32);
		nose.mirror = true;
		setRotation(nose, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 0, 18)).addBox(0.0f, -1.0f, -2.0f, 4, 10, 4);
		leftArm.setPos(4.0f, 4.0f, 1.0f);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 0, 18)).addBox(-4.0f, -1.0f, -2.0f, 4, 10, 4);
		rightArm.setPos(-4.0f, 4.0f, 1.0f);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftEar1 = new ModelRenderer(this, 45, 2)).addBox(-1.0f, -11.0f, -3.0f, 2, 1, 6);
		leftEar1.setPos(0.0f, 3.0f, 0.0f);
		leftEar1.setTexSize(64, 32);
		leftEar1.mirror = true;
		setRotation(leftEar1, 0.0f, 0.0f, 0.0f);
		(leftEar2 = new ModelRenderer(this, 43, 0)).addBox(-1.0f, -10.0f, -4.0f, 2, 2, 8);
		leftEar2.setPos(0.0f, 3.0f, 0.0f);
		leftEar2.setTexSize(64, 32);
		leftEar2.mirror = true;
		setRotation(leftEar2, 0.0f, 0.0f, 0.0f);
		(Staff = new ModelRenderer(this, 59, 0)).addBox(-2.5f, -9.0f, -5.0f, 1, 20, 1);
		Staff.setPos(-4.0f, 4.0f, 1.0f);
		Staff.setTexSize(64, 32);
		Staff.mirror = true;
		setRotation(Staff, 0.4363323f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		nose.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftEar1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftEar2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Staff.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		nose.yRot = netHeadYaw / 57.295776f;
		nose.xRot = headPitch / 54.11268f;
		leftEar1.yRot = netHeadYaw / 57.295776f;
		leftEar1.xRot = headPitch / 54.11268f;
		leftEar2.yRot = netHeadYaw / 57.295776f;
		leftEar2.xRot = headPitch / 54.11268f;
		rightArm.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		Staff.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.43633f;
		Staff.zRot = 0.0f;
		leftArm.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
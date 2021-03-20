package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class GoblinModel extends EntityModel<MobEntity> {
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer head;
	private final ModelRenderer leftArm;
	private final ModelRenderer Staff;
	private final ModelRenderer rightArm;

	public GoblinModel() {
		this.texWidth = 64;
		this.texHeight = 32;
		ModelRenderer leftEar = new ModelRenderer(this, 44, 0);
		leftEar.setPos(0.0F, 3.0F, 0.0F);
		leftEar.addBox(-8.0F, -12.0F, 2.0F, 4, 6, 1, 0.0F);
		this.setRotation(leftEar, 0.0F, 0.0F, 0.8922123136195012F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setPos(0.0F, 3.0F, 0.0F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		ModelRenderer nose = new ModelRenderer(this, 33, 8);
		nose.setPos(0.0F, 3.0F, -1.0F);
		nose.addBox(-1.0F, -7.0F, -5.0F, 2, 5, 2, 0.0F);
		this.rightLeg = new ModelRenderer(this, 18, 18);
		this.rightLeg.setPos(-2.0F, 14.0F, 1.0F);
		this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.rightArm = new ModelRenderer(this, 0, 18);
		this.rightArm.setPos(-4.0F, 4.0F, 1.0F);
		this.rightArm.addBox(-4.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.leftLeg = new ModelRenderer(this, 18, 18);
		this.leftLeg.setPos(2.0F, 14.0F, 1.0F);
		this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.body = new ModelRenderer(this, 36, 15);
		this.body.setPos(0.0F, 3.0F, 1.0F);
		this.body.addBox(-4.0F, 0.0F, -3.0F, 8, 11, 6, 0.0F);
		ModelRenderer rightEar = new ModelRenderer(this, 33, 0);
		rightEar.setPos(0.0F, 3.0F, 0.0F);
		rightEar.addBox(4.0F, -12.0F, 2.0F, 4, 6, 1, 0.0F);
		this.setRotation(rightEar, 0.0F, 0.0F, -0.8922123136195012F);
		this.leftArm = new ModelRenderer(this, 0, 18);
		this.leftArm.setPos(4.0F, 4.0F, 1.0F);
		this.leftArm.addBox(0.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.Staff = new ModelRenderer(this, 59, 0);
		this.Staff.setPos(-4.0F, 4.0F, 1.0F);
		this.Staff.addBox(-2.5F, -9.0F, -5.0F, 1, 20, 1, 0.0F);
		this.setRotation(Staff, 0.43632999062538147F, 0.0F, 0.0F);
		this.head.addChild(leftEar);
		this.head.addChild(nose);
		this.head.addChild(rightEar);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Staff.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / (float)(180f / Math.PI);
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
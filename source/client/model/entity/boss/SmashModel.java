package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class SmashModel extends EntityModel<MobEntity> {
	private final ModelRenderer headbrace;
	private final ModelRenderer bodylower;
	private final ModelRenderer rightArm1;
	private final ModelRenderer leftArm10;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm1;
	private final ModelRenderer leftArm2;
	private final ModelRenderer leftArm3;
	private final ModelRenderer leftArm4;
	private final ModelRenderer leftArm5;
	private final ModelRenderer leftArm6;
	private final ModelRenderer leftArm7;
	private final ModelRenderer leftArm8;
	private final ModelRenderer leftArm9;

	public SmashModel() {
		texWidth = 128;
		texHeight = 32;
		(headbrace = new ModelRenderer(this, 35, 1)).addBox(-5.0f, -1.0f, -5.0f, 10, 2, 10);
		headbrace.setPos(0.0f, -9.0f, 0.0f);
		headbrace.setTexSize(128, 32);
		headbrace.mirror = true;
		setRotation(headbrace, 0.0f, 0.0f, 0.0f);
		(bodylower = new ModelRenderer(this, 100, 1)).addBox(-4.0f, 0.0f, -2.0f, 8, 8, 4);
		bodylower.setPos(0.0f, 4.0f, 0.0f);
		bodylower.setTexSize(128, 32);
		bodylower.mirror = true;
		setRotation(bodylower, 0.0f, 0.0f, 0.0f);
		(rightArm1 = new ModelRenderer(this, 71, 21)).addBox(-4.0f, -1.0f, -3.0f, 7, 5, 6);
		rightArm1.setPos(-7.0f, -4.0f, 0.0f);
		rightArm1.setTexSize(128, 32);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0.0f, 0.0f, 0.0f);
		(leftArm10 = new ModelRenderer(this, 71, 21)).addBox(-3.0f, -1.0f, -3.0f, 7, 5, 6);
		leftArm10.setPos(7.0f, -4.0f, 0.0f);
		leftArm10.setTexSize(128, 32);
		leftArm10.mirror = true;
		setRotation(leftArm10, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 51, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-3.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 51, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(3.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, -9.0f, 0.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 99, 14)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 6);
		body.setPos(0.0f, -8.0f, -1.0f);
		body.setTexSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 81, 2)).addBox(-3.0f, 4.0f, -2.0f, 4, 12, 4);
		rightArm.setPos(-7.0f, -4.0f, 0.0f);
		rightArm.setTexSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm1 = new ModelRenderer(this, 4, 21)).addBox(-3.0f, 9.0f, -16.0f, 8, 8, 2);
		leftArm1.setPos(7.0f, -4.0f, 0.0f);
		leftArm1.setTexSize(128, 32);
		leftArm1.mirror = true;
		setRotation(leftArm1, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 81, 2)).addBox(-1.0f, 4.0f, -2.0f, 4, 12, 4);
		leftArm2.setPos(7.0f, -4.0f, 0.0f);
		leftArm2.setTexSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new ModelRenderer(this, 39, 17)).addBox(0.0f, 12.0f, -4.0f, 2, 2, 2);
		leftArm3.setPos(7.0f, -4.0f, 0.0f);
		leftArm3.setTexSize(128, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(leftArm4 = new ModelRenderer(this, 36, 22)).addBox(-1.0f, 11.0f, -18.0f, 4, 4, 2);
		leftArm4.setPos(7.0f, -4.0f, 0.0f);
		leftArm4.setTexSize(128, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(leftArm5 = new ModelRenderer(this, 4, 21)).addBox(-3.0f, 9.0f, -8.0f, 8, 8, 2);
		leftArm5.setPos(7.0f, -4.0f, 0.0f);
		leftArm5.setTexSize(128, 32);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, 0.0f);
		(leftArm6 = new ModelRenderer(this, 4, 21)).addBox(-3.0f, 9.0f, -12.0f, 8, 8, 2);
		leftArm6.setPos(7.0f, -4.0f, 0.0f);
		leftArm6.setTexSize(128, 32);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, 0.0f);
		(leftArm7 = new ModelRenderer(this, 36, 22)).addBox(-1.0f, 11.0f, -6.0f, 4, 4, 2);
		leftArm7.setPos(7.0f, -4.0f, 0.0f);
		leftArm7.setTexSize(128, 32);
		leftArm7.mirror = true;
		setRotation(leftArm7, 0.0f, 0.0f, 0.0f);
		(leftArm8 = new ModelRenderer(this, 36, 22)).addBox(-1.0f, 11.0f, -10.0f, 4, 4, 2);
		leftArm8.setPos(7.0f, -4.0f, 0.0f);
		leftArm8.setTexSize(128, 32);
		leftArm8.mirror = true;
		setRotation(leftArm8, 0.0f, 0.0f, 0.0f);
		(leftArm9 = new ModelRenderer(this, 36, 22)).addBox(-1.0f, 11.0f, -14.0f, 4, 4, 2);
		leftArm9.setPos(7.0f, -4.0f, 0.0f);
		leftArm9.setTexSize(128, 32);
		leftArm9.mirror = true;
		setRotation(leftArm9, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		headbrace.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		bodylower.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		headbrace.yRot = netHeadYaw / 57.295776f;
		headbrace.xRot = headPitch / 54.11268f;
		rightArm.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		rightArm1.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm1.zRot = 0.0f;
		leftArm1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm1.zRot = 0.0f;
		leftArm2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm2.zRot = 0.0f;
		leftArm3.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm3.zRot = 0.0f;
		leftArm4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm4.zRot = 0.0f;
		leftArm5.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm5.zRot = 0.0f;
		leftArm6.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm6.zRot = 0.0f;
		leftArm7.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm7.zRot = 0.0f;
		leftArm8.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm8.zRot = 0.0f;
		leftArm9.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm9.zRot = 0.0f;
		leftArm10.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm10.zRot = 0.0f;
		rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

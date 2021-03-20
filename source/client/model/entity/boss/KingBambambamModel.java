package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class KingBambambamModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer staff1;
	private final ModelRenderer staff2;
	private final ModelRenderer staff3;

	public KingBambambamModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -10.0f, -5.0f, 10, 10, 10);
		head.setPos(0.0f, 2.0f, 0.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 72, 0)).addBox(-9.0f, 0.0f, -5.0f, 18, 16, 10);
		body.setPos(0.0f, 2.0f, 0.0f);
		body.setTexSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 13)).addBox(-5.0f, -2.0f, -3.0f, 6, 12, 6);
		rightArm.setPos(-10.0f, 4.0f, 0.0f);
		rightArm.setTexSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 13)).addBox(1.0f, -2.0f, -3.0f, 6, 12, 6);
		leftArm.setPos(8.0f, 4.0f, 0.0f);
		leftArm.setTexSize(128, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 47, 1)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		rightLeg.setPos(-5.0f, 18.0f, 0.0f);
		rightLeg.setTexSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 47, 1)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		leftLeg.setPos(5.0f, 18.0f, 0.0f);
		leftLeg.setTexSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(staff3 = new ModelRenderer(this, 98, 11)).addBox(-4.0f, 4.0f, -17.0f, 4, 4, 3);
		staff3.setPos(-10.0f, 4.0f, 0.0f);
		staff3.setTexSize(128, 32);
		staff3.mirror = true;
		setRotation(staff3, -0.7853982f, 0.0f, 0.0f);
		(staff1 = new ModelRenderer(this, 98, 11)).addBox(-3.0f, 5.0f, -3.0f, 2, 2, 12);
		staff1.setPos(-10.0f, 4.0f, 0.0f);
		staff1.setTexSize(128, 32);
		staff1.mirror = true;
		setRotation(staff1, -0.7853982f, 0.0f, 0.0f);
		(staff2 = new ModelRenderer(this, 98, 11)).addBox(-3.0f, 5.0f, -15.0f, 2, 2, 12);
		staff2.setPos(-10.0f, 4.0f, 0.0f);
		staff2.setTexSize(128, 32);
		staff2.mirror = true;
		setRotation(staff2, -0.7853982f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		staff1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		staff2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		staff3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		rightArm.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		staff1.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f - 0.43633f;
		staff1.zRot = 0.0f;
		staff2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f - 0.43633f;
		staff2.zRot = 0.0f;
		staff3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f - 0.43633f;
		staff3.zRot = 0.0f;
		leftArm.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

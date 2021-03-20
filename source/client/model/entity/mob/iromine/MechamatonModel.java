package net.tslat.aoa3.client.model.entity.mob.iromine;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class MechamatonModel extends EntityModel<MobEntity> {
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftArm2;
	private final ModelRenderer rightArm2;
	private final ModelRenderer head;
	private final ModelRenderer leftArm3;
	private final ModelRenderer rightArm3;
	private final ModelRenderer leftArm4;
	private final ModelRenderer leftArm5;
	private final ModelRenderer rightArm4;
	private final ModelRenderer rightArm5;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer leftArm6;
	private final ModelRenderer rightArm6;

	public MechamatonModel() {
		texWidth = 128;
		texHeight = 32;
		(body = new ModelRenderer(this, 60, 6)).addBox(0.0f, 9.0f, -2.0f, 5, 3, 6);
		body.setPos(-2.0f, -7.0f, -1.0f);
		body.setTexSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 32, 0)).addBox(-11.0f, -5.0f, 3.0f, 3, 24, 3);
		rightArm.setPos(-7.0f, -3.0f, 0.0f);
		rightArm.setTexSize(128, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 32, 0)).addBox(8.0f, -5.0f, 3.0f, 3, 24, 3);
		leftArm.setPos(7.0f, -3.0f, 0.0f);
		leftArm.setTexSize(128, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 99, 0)).addBox(-4.0f, 0.0f, -3.0f, 8, 13, 6);
		rightLeg.setPos(-5.0f, 11.0f, 0.0f);
		rightLeg.setTexSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 99, 0)).addBox(-4.0f, 0.0f, -3.0f, 8, 13, 6);
		leftLeg.setPos(5.0f, 11.0f, 0.0f);
		leftLeg.setTexSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 0, 17)).addBox(2.0f, 12.0f, -2.0f, 10, 4, 4);
		leftArm2.setPos(7.0f, -3.0f, 0.0f);
		leftArm2.setTexSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 0, 17)).addBox(-12.0f, 12.0f, -2.0f, 10, 4, 4);
		rightArm2.setPos(-7.0f, -3.0f, 0.0f);
		rightArm2.setTexSize(128, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, -2.0f, -3.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new ModelRenderer(this, 32, 0)).addBox(8.0f, -5.0f, -6.0f, 3, 24, 3);
		leftArm3.setPos(7.0f, -3.0f, 0.0f);
		leftArm3.setTexSize(128, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 32, 0)).addBox(-11.0f, -5.0f, -6.0f, 3, 24, 3);
		rightArm3.setPos(-7.0f, -3.0f, 0.0f);
		rightArm3.setTexSize(128, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm4 = new ModelRenderer(this, 32, 1)).addBox(3.0f, -5.0f, -6.0f, 3, 24, 3);
		leftArm4.setPos(7.0f, -3.0f, 0.0f);
		leftArm4.setTexSize(128, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(leftArm5 = new ModelRenderer(this, 32, 0)).addBox(3.0f, -5.0f, 3.0f, 3, 24, 3);
		leftArm5.setPos(7.0f, -3.0f, 0.0f);
		leftArm5.setTexSize(128, 32);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, 0.0f);
		(rightArm4 = new ModelRenderer(this, 32, 0)).addBox(-6.0f, -5.0f, -6.0f, 3, 24, 3);
		rightArm4.setPos(-7.0f, -3.0f, 0.0f);
		rightArm4.setTexSize(128, 32);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.0f, 0.0f, 0.0f);
		(rightArm5 = new ModelRenderer(this, 32, 0)).addBox(-6.0f, -5.0f, 3.0f, 3, 24, 3);
		rightArm5.setPos(-7.0f, -3.0f, 0.0f);
		rightArm5.setTexSize(128, 32);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 60, 0)).addBox(-4.0f, 0.0f, -2.0f, 12, 9, 6);
		body2.setPos(-2.0f, -7.0f, -1.0f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 60, 6)).addBox(-4.0f, 12.0f, -2.0f, 12, 6, 6);
		body3.setPos(-2.0f, -7.0f, -1.0f);
		body3.setTexSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(leftArm6 = new ModelRenderer(this, 0, 17)).addBox(-1.0f, -2.0f, -2.0f, 10, 4, 4);
		leftArm6.setPos(7.0f, -3.0f, 0.0f);
		leftArm6.setTexSize(128, 32);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, 0.0f);
		(rightArm6 = new ModelRenderer(this, 0, 17)).addBox(-9.0f, -2.0f, -2.0f, 10, 4, 4);
		rightArm6.setPos(-7.0f, -3.0f, 0.0f);
		rightArm6.setTexSize(128, 32);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		rightArm2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.zRot = 0.0f;
		rightArm3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm3.zRot = 0.0f;
		rightArm4.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm4.zRot = 0.0f;
		rightArm5.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm5.zRot = 0.0f;
		rightArm6.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm6.zRot = 0.0f;
		leftArm.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
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
		rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

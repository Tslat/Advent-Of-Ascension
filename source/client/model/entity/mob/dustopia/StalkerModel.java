package net.tslat.aoa3.client.model.entity.mob.dustopia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class StalkerModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer body2;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftArm2;
	private final ModelRenderer leftArm3;
	private final ModelRenderer leftArm4;
	private final ModelRenderer leftArm5;
	private final ModelRenderer leftArm6;
	private final ModelRenderer body3;

	public StalkerModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head.setPos(0.0f, -11.0f, -1.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 25, 12)).addBox(-1.0f, -2.0f, -1.0f, 2, 2, 2);
		body.setPos(0.0f, -9.0f, 0.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 21, 4);
		rightLeg.setPos(-3.0f, 3.0f, 0.0f);
		rightLeg.setTexSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 21, 4);
		leftLeg.setPos(3.0f, 3.0f, 0.0f);
		leftLeg.setTexSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 36)).addBox(-4.0f, 0.0f, -2.0f, 6, 7, 3);
		body2.setPos(1.0f, -8.0f, 4.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm.setPos(-4.0f, -1.0f, 4.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 1.047198f);
		(leftArm2 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm2.setPos(-12.0f, -4.0f, 4.0f);
		leftArm2.setTexSize(64, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, -1.570796f);
		(leftArm3 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm3.setPos(-4.0f, -7.0f, 4.0f);
		leftArm3.setTexSize(64, 64);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 2.094395f);
		(leftArm4 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm4.setPos(4.0f, -4.0f, 4.0f);
		leftArm4.setTexSize(64, 64);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, -1.570796f);
		(leftArm5 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm5.setPos(4.0f, -1.0f, 4.0f);
		leftArm5.setTexSize(64, 64);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, -1.047198f);
		(leftArm6 = new ModelRenderer(this, 42, 19)).addBox(-1.0f, -2.0f, -2.0f, 2, 12, 2);
		leftArm6.setPos(4.0f, -7.0f, 4.0f);
		leftArm6.setTexSize(64, 64);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, -2.094395f);
		(body3 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body3.setPos(0.0f, -9.0f, 0.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

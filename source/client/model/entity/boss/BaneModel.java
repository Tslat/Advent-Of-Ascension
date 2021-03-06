package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class BaneModel extends EntityModel<MobEntity> {
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
	private final ModelRenderer body8;
	private final ModelRenderer body9;
	private final ModelRenderer body10;
	private final ModelRenderer head;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer head6;
	private final ModelRenderer head7;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer rightLeg2;

	public BaneModel() {
		texWidth = 64;
		texHeight = 64;
		(body = new ModelRenderer(this, 51, 47)).addBox(-4.0f, -3.0f, -2.0f, 3, 12, 3);
		body.setPos(5.5f, -1.0f, 6.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 0, 35)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setPos(-6.0f, 7.0f, 0.0f);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 0, 35)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setPos(6.0f, 7.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-0.5f, 5.0f, -6.5f, 1, 2, 5);
		rightLeg.setPos(-2.0f, 17.0f, 0.0f);
		rightLeg.setTexSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-0.5f, 5.0f, -6.5f, 1, 2, 5);
		leftLeg.setPos(2.0f, 17.0f, 0.0f);
		leftLeg.setTexSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 47, 0)).addBox(-4.0f, 0.0f, -2.0f, 2, 2, 3);
		body2.setPos(4.0f, 13.0f, 14.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 51, 47)).addBox(-4.0f, -3.0f, -2.0f, 3, 12, 3);
		body3.setPos(-0.5f, -1.0f, 6.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 47, 27)).addBox(-4.0f, -3.0f, -2.0f, 4, 12, 4);
		body4.setPos(5.0f, -5.0f, -2.0f);
		body4.setTexSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 1.396263f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 47, 27)).addBox(-4.0f, -3.0f, -2.0f, 4, 12, 4);
		body5.setPos(-1.0f, -5.0f, -2.0f);
		body5.setTexSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 1.396263f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, -3.0f, -2.0f, 4, 9, 4);
		body6.setPos(5.0f, 9.0f, 4.0f);
		body6.setTexSize(64, 64);
		body6.mirror = true;
		setRotation(body6, -0.7853982f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, -3.0f, -2.0f, 4, 9, 4);
		body7.setPos(-1.0f, 9.0f, 4.0f);
		body7.setTexSize(64, 64);
		body7.mirror = true;
		setRotation(body7, -0.7853982f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 19, 35)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body8.setPos(0.0f, 5.0f, 0.0f);
		body8.setTexSize(64, 64);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(body9 = new ModelRenderer(this, 36, 13)).addBox(-4.0f, 0.0f, -2.0f, 6, 6, 8);
		body9.setPos(2.0f, 11.0f, 3.0f);
		body9.setTexSize(64, 64);
		body9.mirror = true;
		setRotation(body9, 0.0f, 0.0f, 0.0f);
		(body10 = new ModelRenderer(this, 49, 5)).addBox(-4.0f, 0.0f, -2.0f, 4, 4, 3);
		body10.setPos(3.0f, 12.0f, 11.0f);
		body10.setTexSize(64, 64);
		body10.mirror = true;
		setRotation(body10, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 11)).addBox(-2.0f, -1.0f, 2.0f, 4, 2, 2);
		head.setPos(0.0f, 9.0f, -6.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 25, 0)).addBox(2.0f, -2.5f, 4.0f, 2, 4, 8);
		head2.setPos(0.0f, 9.0f, -6.0f);
		head2.setTexSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 1.047198f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 14, 9)).addBox(-1.0f, 7.0f, -4.0f, 2, 2, 3);
		head3.setPos(0.0f, 9.0f, -6.0f);
		head3.setTexSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 26, 52)).addBox(-2.0f, 1.0f, -4.0f, 4, 4, 8);
		head4.setPos(0.0f, 9.0f, -6.0f);
		head4.setTexSize(64, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 0, 0)).addBox(-1.5f, 5.0f, -4.0f, 3, 2, 5);
		head5.setPos(0.0f, 9.0f, -6.0f);
		head5.setTexSize(64, 64);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 0, 52)).addBox(-2.0f, -5.0f, -4.0f, 4, 4, 8);
		head6.setPos(0.0f, 9.0f, -6.0f);
		head6.setTexSize(64, 64);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 25, 0)).addBox(-4.0f, -2.5f, 4.0f, 2, 4, 8);
		head7.setPos(0.0f, 9.0f, -6.0f);
		head7.setTexSize(64, 64);
		head7.mirror = true;
		setRotation(head7, 1.047198f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 0, 24)).addBox(-1.5f, 0.0f, -1.5f, 3, 7, 3);
		leftLeg2.setPos(2.0f, 17.0f, 0.0f);
		leftLeg2.setTexSize(64, 64);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 0, 24)).addBox(-1.5f, 0.0f, -1.5f, 3, 7, 3);
		rightLeg2.setPos(-2.0f, 17.0f, 0.0f);
		rightLeg2.setTexSize(64, 64);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
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
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightArm.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		leftArm.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		rightLeg2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg2.yRot = 0.0f;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

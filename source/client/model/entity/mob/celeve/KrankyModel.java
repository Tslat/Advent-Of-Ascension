package net.tslat.aoa3.client.model.entity.mob.celeve;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class KrankyModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer head6;
	private final ModelRenderer head7;
	private final ModelRenderer head8;
	private final ModelRenderer head9;
	private final ModelRenderer head10;
	private final ModelRenderer head11;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;
	private final ModelRenderer body7;
	private final ModelRenderer body8;
	private final ModelRenderer body9;
	private final ModelRenderer body10;
	private final ModelRenderer body11;
	private final ModelRenderer body12;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer rightLeg2;

	public KrankyModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new ModelRenderer(this, 0, 52)).addBox(-4.0f, -13.0f, -4.0f, 8, 2, 8);
		head.setPos(0.0f, 0.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 28, 16)).addBox(2.0f, 0.0f, -2.0f, 2, 12, 4);
		body.setPos(0.0f, 0.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setPos(-5.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setPos(5.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 34, 4)).addBox(-2.0f, 9.0f, -5.0f, 4, 3, 3);
		rightLeg.setPos(-3.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 34, 4)).addBox(-2.0f, 9.0f, -5.0f, 4, 3, 3);
		leftLeg.setPos(3.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setPos(0.0f, 0.0f, 0.0f);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 40)).addBox(4.5f, -5.0f, 4.5f, 2, 2, 2);
		head3.setPos(0.0f, 0.0f, 0.0f);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 0, 34)).addBox(-6.0f, -11.0f, -6.0f, 12, 3, 12);
		head4.setPos(0.0f, 0.0f, 0.0f);
		head4.setTexSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 0, 34)).addBox(5.0f, -8.0f, 5.0f, 1, 3, 1);
		head5.setPos(0.0f, 0.0f, 0.0f);
		head5.setTexSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 0, 40)).addBox(-6.5f, -5.0f, 4.5f, 2, 2, 2);
		head6.setPos(0.0f, 0.0f, 0.0f);
		head6.setTexSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 0, 34)).addBox(-6.0f, -8.0f, 5.0f, 1, 3, 1);
		head7.setPos(0.0f, 0.0f, 0.0f);
		head7.setTexSize(64, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 0, 40)).addBox(4.5f, -5.0f, -6.5f, 2, 2, 2);
		head8.setPos(0.0f, 0.0f, 0.0f);
		head8.setTexSize(64, 32);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 0, 34)).addBox(5.0f, -8.0f, -6.0f, 1, 3, 1);
		head9.setPos(0.0f, 0.0f, 0.0f);
		head9.setTexSize(64, 32);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 0, 40)).addBox(-6.5f, -5.0f, -6.5f, 2, 2, 2);
		head10.setPos(0.0f, 0.0f, 0.0f);
		head10.setTexSize(64, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
		(head11 = new ModelRenderer(this, 0, 34)).addBox(-6.0f, -8.0f, -6.0f, 1, 3, 1);
		head11.setPos(0.0f, 0.0f, 0.0f);
		head11.setTexSize(64, 32);
		head11.mirror = true;
		setRotation(head11, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 32, 13)).addBox(1.0f, 10.0f, -3.0f, 3, 1, 1);
		body2.setPos(0.0f, 0.0f, 0.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 2, 12, 4);
		body3.setPos(0.0f, 0.0f, 0.0f);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 32, 13)).addBox(-4.0f, 10.0f, -3.0f, 3, 1, 1);
		body4.setPos(0.0f, 0.0f, 0.0f);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 32, 13)).addBox(1.0f, 2.0f, -3.0f, 3, 1, 1);
		body5.setPos(0.0f, 0.0f, 0.0f);
		body5.setTexSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 32, 13)).addBox(-4.0f, 2.0f, -3.0f, 3, 1, 1);
		body6.setPos(0.0f, 0.0f, 0.0f);
		body6.setTexSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 32, 13)).addBox(1.0f, 4.0f, -3.0f, 3, 1, 1);
		body7.setPos(0.0f, 0.0f, 0.0f);
		body7.setTexSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 32, 13)).addBox(-4.0f, 4.0f, -3.0f, 3, 1, 1);
		body8.setPos(0.0f, 0.0f, 0.0f);
		body8.setTexSize(64, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(body9 = new ModelRenderer(this, 32, 13)).addBox(1.0f, 6.0f, -3.0f, 3, 1, 1);
		body9.setPos(0.0f, 0.0f, 0.0f);
		body9.setTexSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0f, 0.0f, 0.0f);
		(body10 = new ModelRenderer(this, 32, 13)).addBox(-4.0f, 6.0f, -3.0f, 3, 1, 1);
		body10.setPos(0.0f, 0.0f, 0.0f);
		body10.setTexSize(64, 32);
		body10.mirror = true;
		setRotation(body10, 0.0f, 0.0f, 0.0f);
		(body11 = new ModelRenderer(this, 32, 13)).addBox(1.0f, 8.0f, -3.0f, 3, 1, 1);
		body11.setPos(0.0f, 0.0f, 0.0f);
		body11.setTexSize(64, 32);
		body11.mirror = true;
		setRotation(body11, 0.0f, 0.0f, 0.0f);
		(body12 = new ModelRenderer(this, 32, 13)).addBox(-4.0f, 8.0f, -3.0f, 3, 1, 1);
		body12.setPos(0.0f, 0.0f, 0.0f);
		body12.setTexSize(64, 32);
		body12.mirror = true;
		setRotation(body12, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg2.setPos(3.0f, 12.0f, 0.0f);
		leftLeg2.setTexSize(64, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg2.setPos(-3.0f, 12.0f, 0.0f);
		rightLeg2.setTexSize(64, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		head2.yRot = netHeadYaw / 57.295776f;
		head2.xRot = headPitch / 54.11268f;
		head3.yRot = netHeadYaw / 57.295776f;
		head3.xRot = headPitch / 54.11268f;
		head4.yRot = netHeadYaw / 57.295776f;
		head4.xRot = headPitch / 54.11268f;
		head5.yRot = netHeadYaw / 57.295776f;
		head5.xRot = headPitch / 54.11268f;
		head6.yRot = netHeadYaw / 57.295776f;
		head6.xRot = headPitch / 54.11268f;
		head7.yRot = netHeadYaw / 57.295776f;
		head7.xRot = headPitch / 54.11268f;
		head8.yRot = netHeadYaw / 57.295776f;
		head8.xRot = headPitch / 54.11268f;
		head9.yRot = netHeadYaw / 57.295776f;
		head9.xRot = headPitch / 54.11268f;
		head10.yRot = netHeadYaw / 57.295776f;
		head10.xRot = headPitch / 54.11268f;
		head11.yRot = netHeadYaw / 57.295776f;
		head11.xRot = headPitch / 54.11268f;
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

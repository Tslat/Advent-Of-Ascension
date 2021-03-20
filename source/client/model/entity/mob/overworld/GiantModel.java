package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class GiantModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer rightLeg2;
	private final ModelRenderer leftLeg3;
	private final ModelRenderer rightLeg3;
	private final ModelRenderer rightArm2;
	private final ModelRenderer leftArm;
	private final ModelRenderer leftArm2;

	public GiantModel() {
		super(RenderType::entityTranslucent);
		texWidth = 128;
		texHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -14.0f, -4.0f, 10, 14, 8);
		head.setPos(0.0f, -20.0f, 0.0f);
		head.setTexSize(128, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 0, 35)).addBox(-6.0f, 0.0f, -2.0f, 12, 18, 10);
		body.setPos(0.0f, -20.0f, -3.0f);
		body.setTexSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 36, 2)).addBox(-18.0f, -6.0f, -6.0f, 19, 10, 10);
		rightArm.setPos(-6.0f, -17.0f, 0.0f);
		rightArm.setTexSize(128, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 96, 0)).addBox(-4.0f, -5.0f, -3.0f, 8, 8, 8);
		rightLeg.setPos(-7.0f, -2.0f, -1.0f);
		rightLeg.setTexSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 96, 0)).addBox(-4.0f, -5.0f, -3.0f, 8, 8, 8);
		leftLeg.setPos(7.0f, -2.0f, -1.0f);
		leftLeg.setTexSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 80, 39)).addBox(-6.0f, 14.0f, -5.0f, 12, 12, 12);
		leftLeg2.setPos(7.0f, -2.0f, -1.0f);
		leftLeg2.setTexSize(128, 64);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 80, 39)).addBox(-6.0f, 14.0f, -5.0f, 12, 12, 12);
		rightLeg2.setPos(-7.0f, -2.0f, -1.0f);
		rightLeg2.setTexSize(128, 64);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg3 = new ModelRenderer(this, 87, 17)).addBox(-5.0f, 3.0f, -4.0f, 10, 11, 10);
		leftLeg3.setPos(7.0f, -2.0f, -1.0f);
		leftLeg3.setTexSize(128, 64);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.0f, 0.0f, 0.0f);
		(rightLeg3 = new ModelRenderer(this, 87, 17)).addBox(-5.0f, 3.0f, -4.0f, 10, 11, 10);
		rightLeg3.setPos(-7.0f, -2.0f, -1.0f);
		rightLeg3.setTexSize(128, 64);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 46, 26)).addBox(-17.0f, 4.0f, -5.0f, 8, 30, 8);
		rightArm2.setPos(-6.0f, -17.0f, 0.0f);
		rightArm2.setTexSize(128, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 36, 2)).addBox(-1.0f, -6.0f, -6.0f, 19, 10, 10);
		leftArm.setPos(6.0f, -17.0f, 0.0f);
		leftArm.setTexSize(128, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 46, 26)).addBox(8.0f, 4.0f, -5.0f, 8, 30, 8);
		leftArm2.setPos(6.0f, -17.0f, 0.0f);
		leftArm2.setTexSize(128, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		rightArm.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		rightArm2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.zRot = 0.0f;
		leftArm.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		leftArm2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm2.zRot = 0.0f;
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

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}

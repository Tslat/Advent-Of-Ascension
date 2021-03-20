package net.tslat.aoa3.client.model.entity.mob.shyrelands;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ShyreKnightModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftArm2;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer rightLeg2;
	private final ModelRenderer body2;
	private final ModelRenderer rightArm2;
	private final ModelRenderer leftArm3;
	private final ModelRenderer rightArm3;
	private final ModelRenderer rightArm4;
	private final ModelRenderer leftArm4;

	public ShyreKnightModel() {
		texWidth = 64;
		texHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
		head.setPos(0.0F, 0.0F, 0.0F);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 25, 34);
		body.addBox(-5.0F, 10.0F, -3.0F, 10, 2, 6);
		body.setPos(0.0F, 1.0F, 0.0F);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightArm = new ModelRenderer(this, 40, 3);
		rightArm.addBox(-4.0F, -5.0F, -3.0F, 6, 5, 6);
		rightArm.setPos(-6.0F, 2.0F, 0.0F);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0F, 0.0F, 0.0F);
		leftArm = new ModelRenderer(this, 40, 3);
		leftArm.addBox(-2.0F, -5.0F, -3.0F, 6, 5, 6);
		leftArm.setPos(6.0F, 2.0F, 0.0F);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0F, 0.0F, 0.0F);
		rightLeg = new ModelRenderer(this, 0, 34);
		rightLeg.addBox(-3.0F, 6.0F, -3.0F, 6, 6, 6);
		rightLeg.setPos(-3.0F, 12.0F, 0.0F);
		rightLeg.setTexSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new ModelRenderer(this, 0, 34);
		leftLeg.addBox(-3.0F, 6.0F, -3.0F, 6, 6, 6);
		leftLeg.setPos(3.0F, 12.0F, 0.0F);
		leftLeg.setTexSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		leftArm2 = new ModelRenderer(this, 10, 47);
		leftArm2.addBox(5.0F, 2.0F, 0.0F, 3, 12, 0);
		leftArm2.setPos(6.0F, 2.0F, 0.0F);
		leftArm2.setTexSize(64, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0F, 0.0F, 0.0F);
		leftLeg2 = new ModelRenderer(this, 0, 16);
		leftLeg2.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4);
		leftLeg2.setPos(3.0F, 13.0F, 0.0F);
		leftLeg2.setTexSize(64, 64);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0F, 0.0F, 0.0F);
		rightLeg2 = new ModelRenderer(this, 0, 16);
		rightLeg2.addBox(-2.0F, 0.0F, -2.0F, 4, 11, 4);
		rightLeg2.setPos(-3.0F, 13.0F, 0.0F);
		rightLeg2.setTexSize(64, 64);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 16, 16);
		body2.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4);
		body2.setPos(0.0F, 0.0F, 0.0F);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0F, 0.0F, 0.0F);
		rightArm2 = new ModelRenderer(this, 40, 16);
		rightArm2.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4);
		rightArm2.setPos(-6.0F, 2.0F, 0.0F);
		rightArm2.setTexSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0F, 0.0F, 0.0F);
		leftArm3 = new ModelRenderer(this, 40, 16);
		leftArm3.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4);
		leftArm3.setPos(6.0F, 2.0F, 0.0F);
		leftArm3.setTexSize(64, 64);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0F, 0.0F, 0.0F);
		rightArm3 = new ModelRenderer(this, 28, 46);
		rightArm3.addBox(-5.0F, 2.0F, -2.0F, 2, 12, 4);
		rightArm3.setPos(-6.0F, 2.0F, 0.0F);
		rightArm3.setTexSize(64, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0F, 0.0F, 0.0F);
		rightArm4 = new ModelRenderer(this, 2, 47);
		rightArm4.addBox(-8.0F, 2.0F, 0.0F, 3, 12, 0);
		rightArm4.setPos(-6.0F, 2.0F, 0.0F);
		rightArm4.setTexSize(64, 64);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.0F, 0.0F, 0.0F);
		leftArm4 = new ModelRenderer(this, 41, 46);
		leftArm4.addBox(3.0F, 2.0F, -2.0F, 2, 12, 4);
		leftArm4.setPos(6.0F, 2.0F, 0.0F);
		leftArm4.setTexSize(64, 64);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = (netHeadYaw / 57.295776F);
		head.xRot = (headPitch / 54.11268F);

		rightArm.xRot = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm.zRot = 0.0F;

		rightArm2.xRot = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm2.zRot = 0.0F;

		rightArm3.xRot = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm3.zRot = 0.0F;

		rightArm4.xRot = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 2.0F * limbSwingAmount * 0.5F);
		rightArm4.zRot = 0.0F;

		leftArm.xRot = (MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm.zRot = 0.0F;

		leftArm2.xRot = (MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm2.zRot = 0.0F;

		leftArm3.xRot = (MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm3.zRot = 0.0F;

		leftArm4.xRot = (MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F);
		leftArm4.zRot = 0.0F;

		rightLeg.xRot = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		rightLeg.yRot = 0.0F;

		rightLeg2.xRot = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		rightLeg2.yRot = 0.0F;

		leftLeg.xRot = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);

		leftLeg2.xRot = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);
	}
}

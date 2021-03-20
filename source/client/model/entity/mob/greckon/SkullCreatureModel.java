package net.tslat.aoa3.client.model.entity.mob.greckon;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class SkullCreatureModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer rightLeg2;
	private final ModelRenderer leftLeg3;
	private final ModelRenderer rightLeg3;
	private final ModelRenderer rightLeg4;
	private final ModelRenderer rightLeg5;
	private final ModelRenderer leftLeg4;
	private final ModelRenderer leftLeg5;

	public SkullCreatureModel() {
		texWidth = 64;
		texHeight = 32;
		(head = new ModelRenderer(this, 37, 0)).addBox(-3.0f, -6.0f, -4.0f, 6, 6, 6);
		head.setPos(8.0f, 10.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 43, 28)).addBox(-4.0f, 15.0f, -1.0f, 8, 2, 2);
		body.setPos(0.0f, -5.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 11, 27)).addBox(-4.0f, 6.0f, -2.0f, 2, 2, 2);
		rightLeg.setPos(-2.0f, 12.0f, 1.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 22, 26)).addBox(-1.0f, 6.0f, -2.0f, 1, 2, 2);
		leftLeg.setPos(2.0f, 12.0f, 1.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 34, 13)).addBox(-1.0f, 0.0f, -1.0f, 2, 15, 2);
		body2.setPos(0.0f, -5.0f, 0.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 50, 23)).addBox(-1.0f, 0.0f, -1.0f, 4, 2, 2);
		body3.setPos(-4.0f, -2.0f, 0.0f);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 50, 23)).addBox(-1.0f, 0.0f, -1.0f, 4, 2, 2);
		body4.setPos(2.0f, -2.0f, 0.0f);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 50, 23)).addBox(-1.0f, 0.0f, -1.0f, 4, 2, 2);
		body5.setPos(-4.0f, 5.0f, 0.0f);
		body5.setTexSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 50, 23)).addBox(-1.0f, 0.0f, -1.0f, 4, 2, 2);
		body6.setPos(2.0f, 5.0f, 0.0f);
		body6.setTexSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setPos(0.0f, -5.0f, 0.0f);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 37, 0)).addBox(-3.0f, -6.0f, -4.0f, 6, 6, 6);
		head3.setPos(-8.0f, 10.0f, 0.0f);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 37, 0)).addBox(-3.0f, -6.0f, -4.0f, 6, 6, 6);
		head4.setPos(8.0f, 2.0f, 0.0f);
		head4.setTexSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 37, 0)).addBox(-3.0f, -6.0f, -4.0f, 6, 6, 6);
		head5.setPos(-8.0f, 2.0f, 0.0f);
		head5.setTexSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 0, 18)).addBox(0.0f, 0.0f, -2.0f, 2, 12, 2);
		leftLeg2.setPos(2.0f, 12.0f, 1.0f);
		leftLeg2.setTexSize(64, 32);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, 0.0f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 0, 18)).addBox(-2.0f, 0.0f, -2.0f, 2, 12, 2);
		rightLeg2.setPos(-2.0f, 12.0f, 1.0f);
		rightLeg2.setTexSize(64, 32);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, 0.0f, 0.0f, 0.0f);
		(leftLeg3 = new ModelRenderer(this, 11, 27)).addBox(2.0f, 6.0f, -2.0f, 2, 2, 2);
		leftLeg3.setPos(2.0f, 12.0f, 1.0f);
		leftLeg3.setTexSize(64, 32);
		leftLeg3.mirror = true;
		setRotation(leftLeg3, 0.0f, 0.0f, 0.0f);
		(rightLeg3 = new ModelRenderer(this, 22, 26)).addBox(0.0f, 6.0f, -2.0f, 1, 2, 2);
		rightLeg3.setPos(-2.0f, 12.0f, 1.0f);
		rightLeg3.setTexSize(64, 32);
		rightLeg3.mirror = true;
		setRotation(rightLeg3, 0.0f, 0.0f, 0.0f);
		(rightLeg4 = new ModelRenderer(this, 11, 27)).addBox(-4.0f, 2.0f, -2.0f, 2, 2, 2);
		rightLeg4.setPos(-2.0f, 12.0f, 1.0f);
		rightLeg4.setTexSize(64, 32);
		rightLeg4.mirror = true;
		setRotation(rightLeg4, 0.0f, 0.0f, 0.0f);
		(rightLeg5 = new ModelRenderer(this, 22, 26)).addBox(0.0f, 2.0f, -2.0f, 1, 2, 2);
		rightLeg5.setPos(-2.0f, 12.0f, 1.0f);
		rightLeg5.setTexSize(64, 32);
		rightLeg5.mirror = true;
		setRotation(rightLeg5, 0.0f, 0.0f, 0.0f);
		(leftLeg4 = new ModelRenderer(this, 22, 26)).addBox(-1.0f, 2.0f, -2.0f, 1, 2, 2);
		leftLeg4.setPos(2.0f, 12.0f, 1.0f);
		leftLeg4.setTexSize(64, 32);
		leftLeg4.mirror = true;
		setRotation(leftLeg4, 0.0f, 0.0f, 0.0f);
		(leftLeg5 = new ModelRenderer(this, 11, 27)).addBox(2.0f, 2.0f, -2.0f, 2, 2, 2);
		leftLeg5.setPos(2.0f, 12.0f, 1.0f);
		leftLeg5.setTexSize(64, 32);
		leftLeg5.mirror = true;
		setRotation(leftLeg5, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head2.yRot = netHeadYaw / (float)(180f / Math.PI);
		rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg3.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg5.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg4.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg5.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

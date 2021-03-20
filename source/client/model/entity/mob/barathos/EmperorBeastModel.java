package net.tslat.aoa3.client.model.entity.mob.barathos;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class EmperorBeastModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftLeg2;
	private final ModelRenderer rightLeg2;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer head2;
	private final ModelRenderer head3;

	public EmperorBeastModel() {
		texWidth = 128;
		texHeight = 64;
		(head = new ModelRenderer(this, 41, 18)).addBox(-10.0f, -21.0f, 0.0f, 20, 3, 8);
		head.setPos(0.0f, -11.0f, 0.0f);
		head.setTexSize(128, 64);
		head.mirror = true;
		setRotation(head, -0.1745329f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 90, 46)).addBox(-3.0f, 17.0f, 15.0f, 6, 6, 12);
		body.setPos(0.0f, -11.0f, -3.0f);
		body.setTexSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 83, 2)).addBox(-5.0f, 13.0f, -11.0f, 10, 4, 12);
		rightLeg.setPos(-7.0f, 7.0f, 6.0f);
		rightLeg.setTexSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 83, 2)).addBox(-5.0f, 13.0f, -11.0f, 10, 4, 12);
		leftLeg.setPos(7.0f, 7.0f, 6.0f);
		leftLeg.setTexSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg2 = new ModelRenderer(this, 100, 22)).addBox(-3.0f, 0.0f, -4.0f, 6, 15, 8);
		leftLeg2.setPos(7.0f, 7.0f, 6.0f);
		leftLeg2.setTexSize(128, 64);
		leftLeg2.mirror = true;
		setRotation(leftLeg2, -0.3490659f, 0.0f, 0.0f);
		(rightLeg2 = new ModelRenderer(this, 100, 22)).addBox(-3.0f, 0.0f, -4.0f, 6, 15, 8);
		rightLeg2.setPos(-7.0f, 7.0f, 6.0f);
		rightLeg2.setTexSize(128, 64);
		rightLeg2.mirror = true;
		setRotation(rightLeg2, -0.3490659f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 24, 29)).addBox(-4.0f, 0.0f, -2.0f, 8, 23, 12);
		body2.setPos(0.0f, -11.0f, -3.0f);
		body2.setTexSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 67, 29)).addBox(-4.0f, 11.0f, 10.0f, 8, 12, 8);
		body3.setPos(0.0f, -11.0f, -3.0f);
		body3.setTexSize(128, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 28)).addBox(-2.0f, -20.0f, -4.0f, 4, 8, 6);
		head2.setPos(0.0f, -11.0f, 0.0f);
		head2.setTexSize(128, 64);
		head2.mirror = true;
		setRotation(head2, -0.4537856f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -13.0f, -4.0f, 8, 13, 13);
		head3.setPos(0.0f, -11.0f, 0.0f);
		head3.setTexSize(128, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		rightLeg2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount - 0.349f;
		rightLeg2.yRot = 0.0f;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLeg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount - 0.349f;
	}
}

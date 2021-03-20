package net.tslat.aoa3.client.model.entity.mob.greckon;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class BansheeModel extends EntityModel<MobEntity> {
	private final ModelRenderer head1;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;
	private final ModelRenderer body7;

	public BansheeModel() {
		texWidth = 128;
		texHeight = 64;
		(head1 = new ModelRenderer(this, 85, 25)).addBox(-5.0f, -11.0f, 4.0f, 10, 13, 6);
		head1.setPos(0.0f, -4.0f, 1.0f);
		head1.setTexSize(128, 64);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 2, 47)).addBox(-7.0f, 20.0f, -5.0f, 14, 6, 10);
		body.setPos(0.0f, -3.0f, 0.0f);
		body.setTexSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.1745329f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setPos(-5.0f, -1.0f, 0.0f);
		rightArm.setTexSize(128, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.4363323f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setPos(5.0f, -1.0f, 0.0f);
		leftArm.setTexSize(128, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.4363323f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-2.0f, 9.0f, 2.0f);
		rightLeg.setTexSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.1745329f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(2.0f, 9.0f, 2.0f);
		leftLeg.setTexSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.1745329f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setPos(0.0f, -3.0f, 0.0f);
		head2.setTexSize(128, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 62, 0)).addBox(-5.0f, -8.0f, -4.0f, 10, 9, 4);
		head3.setPos(0.0f, -4.0f, 1.0f);
		head3.setTexSize(128, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 96, 0)).addBox(-5.0f, -10.0f, 0.0f, 10, 12, 4);
		head4.setPos(0.0f, -4.0f, 1.0f);
		head4.setTexSize(128, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 52, 35)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body2.setPos(0.0f, -3.0f, 0.0f);
		body2.setTexSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.1745329f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 2, 33)).addBox(-6.0f, 16.0f, -4.0f, 12, 4, 8);
		body3.setPos(0.0f, -3.0f, 0.0f);
		body3.setTexSize(128, 64);
		body3.mirror = true;
		setRotation(body3, 0.1745329f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 53, 53)).addBox(-5.0f, 12.0f, -3.0f, 10, 4, 6);
		body4.setPos(0.0f, -3.0f, 0.0f);
		body4.setTexSize(128, 64);
		body4.mirror = true;
		setRotation(body4, 0.1745329f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 61, 15)).addBox(3.5f, -1.0f, -3.5f, 5, 12, 5);
		body5.setPos(0.0f, -3.0f, 0.0f);
		body5.setTexSize(128, 64);
		body5.mirror = true;
		setRotation(body5, 0.4363323f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 88, 45)).addBox(-5.0f, 0.0f, -3.0f, 10, 12, 6);
		body6.setPos(0.0f, -3.0f, 0.0f);
		body6.setTexSize(128, 64);
		body6.mirror = true;
		setRotation(body6, 0.1745329f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 61, 15)).addBox(-8.5f, -1.0f, -3.5f, 5, 12, 5);
		body7.setPos(0.0f, -3.0f, 0.0f);
		body7.setTexSize(128, 64);
		body7.mirror = true;
		setRotation(body7, 0.4363323f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head1.yRot = netHeadYaw / 57.295776f;
		head1.xRot = headPitch / 54.11268f;
		head2.yRot = netHeadYaw / 57.295776f;
		head2.xRot = headPitch / 54.11268f;
		head3.yRot = netHeadYaw / 57.295776f;
		head3.xRot = headPitch / 54.11268f;
		head4.yRot = netHeadYaw / 57.295776f;
		head4.xRot = headPitch / 54.11268f;
	}
}

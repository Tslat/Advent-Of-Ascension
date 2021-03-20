package net.tslat.aoa3.client.model.entity.animal;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class AngelicaModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer wingL;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer body;
	private final ModelRenderer wingL2;
	private final ModelRenderer wingR;
	private final ModelRenderer wingR2;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;

	public AngelicaModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, 0.0f, 0.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(wingL = new ModelRenderer(this, 23, 41)).addBox(1.0f, 2.0f, -1.0f, 10, 17, 0);
		wingL.setPos(1.0f, 1.0f, 5.0f);
		wingL.setTexSize(64, 64);
		wingL.mirror = true;
		setRotation(wingL, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setPos(-5.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setPos(5.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 8, 17)).addBox(2.0f, 12.0f, 1.0f, 2, 9, 1);
		body.setPos(0.0f, 0.0f, 0.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 1, 33)).addBox(1.0f, 0.0f, -2.0f, 10, 2, 2);
		wingL2.setPos(1.0f, 1.0f, 5.0f);
		wingL2.setTexSize(64, 64);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
		(wingR = new ModelRenderer(this, 1, 41)).addBox(-11.0f, 2.0f, -1.0f, 10, 17, 0);
		wingR.setPos(-1.0f, 1.0f, 5.0f);
		wingR.setTexSize(64, 64);
		wingR.mirror = true;
		setRotation(wingR, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 1, 33)).addBox(-11.0f, 0.0f, -2.0f, 10, 2, 2);
		wingR2.setPos(-1.0f, 1.0f, 5.0f);
		wingR2.setTexSize(64, 64);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 35, 3)).addBox(-4.0f, 0.0f, 2.0f, 8, 4, 4);
		body2.setPos(0.0f, 0.0f, 0.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 8, 17)).addBox(-4.0f, 12.0f, 1.0f, 2, 9, 1);
		body3.setPos(0.0f, 0.0f, 0.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 1, 17)).addBox(2.0f, 12.0f, -2.0f, 2, 5, 1);
		body4.setPos(0.0f, 0.0f, 0.0f);
		body4.setTexSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 1, 17)).addBox(-4.0f, 12.0f, -2.0f, 2, 5, 1);
		body5.setPos(0.0f, 0.0f, 0.0f);
		body5.setTexSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body6.setPos(0.0f, 0.0f, 0.0f);
		body6.setTexSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		leftArm.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		wingR.yRot = MathHelper.cos(ageInTicks * 0.3f) * 3.1415927f * 0.15f;
		wingL.yRot = -wingR.yRot;
		wingR2.yRot = MathHelper.cos(ageInTicks * 0.3f) * 3.1415927f * 0.15f;
		wingL2.yRot = -wingR.yRot;
	}
}

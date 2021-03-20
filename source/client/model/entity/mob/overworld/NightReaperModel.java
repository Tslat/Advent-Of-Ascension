package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class NightReaperModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer body2;
	private final ModelRenderer rightArm2;
	private final ModelRenderer rightArm3;
	private final ModelRenderer rightArm4;
	private final ModelRenderer rightArm5;
	private final ModelRenderer rightArm6;
	private final ModelRenderer rightArm7;
	private final ModelRenderer rightArm8;

	public NightReaperModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, -6.0f, 0.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 38)).addBox(-5.0f, 0.0f, -2.0f, 10, 13, 4);
		body.setPos(0.0f, 10.0f, 0.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -1.0f, -11.0f, 2, 3, 1);
		rightArm.setPos(-5.0f, -4.0f, 0.0f);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.3490659f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 37, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 16, 4);
		leftArm.setPos(5.0f, -4.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 12, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 16, 4);
		body2.setPos(0.0f, -6.0f, 0.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 37, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 16, 4);
		rightArm2.setPos(-5.0f, -4.0f, 0.0f);
		rightArm2.setTexSize(64, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 55, 16)).addBox(-2.0f, -10.0f, -7.0f, 2, 36, 2);
		rightArm3.setPos(-5.0f, -4.0f, 0.0f);
		rightArm3.setTexSize(64, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.3490659f, 0.0f, 0.0f);
		(rightArm4 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -8.0f, -9.0f, 2, 3, 2);
		rightArm4.setPos(-5.0f, -4.0f, 0.0f);
		rightArm4.setTexSize(64, 64);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.3490659f, 0.0f, 0.0f);
		(rightArm5 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -7.0f, -11.0f, 2, 3, 2);
		rightArm5.setPos(-5.0f, -4.0f, 0.0f);
		rightArm5.setTexSize(64, 64);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.3490659f, 0.0f, 0.0f);
		(rightArm6 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -6.0f, -13.0f, 2, 3, 2);
		rightArm6.setPos(-5.0f, -4.0f, 0.0f);
		rightArm6.setTexSize(64, 64);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.3490659f, 0.0f, 0.0f);
		(rightArm7 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -4.0f, -14.0f, 2, 4, 1);
		rightArm7.setPos(-5.0f, -4.0f, 0.0f);
		rightArm7.setTexSize(64, 64);
		rightArm7.mirror = true;
		setRotation(rightArm7, 0.3490659f, 0.0f, 0.0f);
		(rightArm8 = new ModelRenderer(this, 55, 2)).addBox(-2.0f, -3.0f, -13.0f, 2, 4, 2);
		rightArm8.setPos(-5.0f, -4.0f, 0.0f);
		rightArm8.setTexSize(64, 64);
		rightArm8.mirror = true;
		setRotation(rightArm8, 0.3490659f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / (float)(180f / Math.PI);
		rightArm.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.349f;
		rightArm.zRot = 0.0f;
		rightArm2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.zRot = 0.0f;
		rightArm3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.349f;
		rightArm3.zRot = 0.0f;
		rightArm4.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.349f;
		rightArm4.zRot = 0.0f;
		rightArm5.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.349f;
		rightArm5.zRot = 0.0f;
		rightArm6.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.349f;
		rightArm6.zRot = 0.0f;
		rightArm7.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.349f;
		rightArm7.zRot = 0.0f;
		rightArm8.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.349f;
		rightArm8.zRot = 0.0f;
		leftArm.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}

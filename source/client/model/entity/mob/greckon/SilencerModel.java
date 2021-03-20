package net.tslat.aoa3.client.model.entity.mob.greckon;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class SilencerModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightArm2;
	private final ModelRenderer leftArm2;
	private final ModelRenderer leftArm3;
	private final ModelRenderer rightArm3;
	private final ModelRenderer leftArm4;
	private final ModelRenderer rightArm4;
	private final ModelRenderer leftArm5;
	private final ModelRenderer rightArm5;
	private final ModelRenderer leftArm6;
	private final ModelRenderer rightArm6;
	private final ModelRenderer leftArm7;
	private final ModelRenderer rightArm7;

	public SilencerModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, 0.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setPos(0.0f, 0.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 100, 22)).addBox(-8.0f, -12.0f, -1.0f, 2, 3, 2);
		rightArm.setPos(-5.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 100, 22)).addBox(6.0f, -12.0f, -1.0f, 2, 3, 2);
		leftArm.setPos(5.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setPos(-2.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setPos(2.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 43, 11)).addBox(-5.0f, -2.0f, -2.0f, 6, 4, 4);
		rightArm2.setPos(-5.0f, 2.0f, 0.0f);
		rightArm2.setTexSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 43, 11)).addBox(-1.0f, -2.0f, -2.0f, 6, 4, 4);
		leftArm2.setPos(5.0f, 2.0f, 0.0f);
		leftArm2.setTexSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new ModelRenderer(this, 63, 22)).addBox(5.0f, -4.0f, -2.0f, 4, 6, 4);
		leftArm3.setPos(5.0f, 2.0f, 0.0f);
		leftArm3.setTexSize(64, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 63, 22)).addBox(-9.0f, -4.0f, -2.0f, 4, 6, 4);
		rightArm3.setPos(-5.0f, 2.0f, 0.0f);
		rightArm3.setTexSize(64, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm4 = new ModelRenderer(this, 36, 0)).addBox(5.5f, -8.0f, -2.0f, 3, 3, 1);
		leftArm4.setPos(5.0f, 2.0f, 0.0f);
		leftArm4.setTexSize(64, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(rightArm4 = new ModelRenderer(this, 36, 0)).addBox(-8.5f, -8.0f, -2.0f, 3, 3, 1);
		rightArm4.setPos(-5.0f, 2.0f, 0.0f);
		rightArm4.setTexSize(64, 32);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.0f, 0.0f, 0.0f);
		(leftArm5 = new ModelRenderer(this, 115, 22)).addBox(4.0f, -12.0f, -1.0f, 1, 3, 2);
		leftArm5.setPos(5.0f, 2.0f, 0.0f);
		leftArm5.setTexSize(64, 32);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0.0f, 0.0f, 0.0f);
		(rightArm5 = new ModelRenderer(this, 115, 22)).addBox(-10.0f, -12.0f, -1.0f, 1, 3, 2);
		rightArm5.setPos(-5.0f, 2.0f, 0.0f);
		rightArm5.setTexSize(64, 32);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.0f, 0.0f, 0.0f);
		(leftArm6 = new ModelRenderer(this, 115, 22)).addBox(9.0f, -12.0f, -1.0f, 1, 3, 2);
		leftArm6.setPos(5.0f, 2.0f, 0.0f);
		leftArm6.setTexSize(64, 32);
		leftArm6.mirror = true;
		setRotation(leftArm6, 0.0f, 0.0f, 0.0f);
		(rightArm6 = new ModelRenderer(this, 115, 22)).addBox(-5.0f, -12.0f, -1.0f, 1, 3, 2);
		rightArm6.setPos(-5.0f, 2.0f, 0.0f);
		rightArm6.setTexSize(64, 32);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.0f, 0.0f, 0.0f);
		(leftArm7 = new ModelRenderer(this, 81, 22)).addBox(4.0f, -9.0f, -1.0f, 6, 5, 2);
		leftArm7.setPos(5.0f, 2.0f, 0.0f);
		leftArm7.setTexSize(64, 32);
		leftArm7.mirror = true;
		setRotation(leftArm7, 0.0f, 0.0f, 0.0f);
		(rightArm7 = new ModelRenderer(this, 81, 22)).addBox(-10.0f, -9.0f, -1.0f, 6, 5, 2);
		rightArm7.setPos(-5.0f, 2.0f, 0.0f);
		rightArm7.setTexSize(64, 32);
		rightArm7.mirror = true;
		setRotation(rightArm7, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
	}
}

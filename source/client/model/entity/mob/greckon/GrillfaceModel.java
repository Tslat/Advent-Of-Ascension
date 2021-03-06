package net.tslat.aoa3.client.model.entity.mob.greckon;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class GrillfaceModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer leftArm;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer leftArm2;
	private final ModelRenderer rightArm2;
	private final ModelRenderer rightArm3;
	private final ModelRenderer leftArm3;
	private final ModelRenderer head6;
	private final ModelRenderer head7;
	private final ModelRenderer head8;
	private final ModelRenderer head9;
	private final ModelRenderer head10;
	private final ModelRenderer head11;
	private final ModelRenderer head12;
	private final ModelRenderer head13;
	private final ModelRenderer head14;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;

	public GrillfaceModel() {
		texWidth = 128;
		texHeight = 64;
		(head = new ModelRenderer(this, 0, 13)).addBox(-2.0f, -6.0f, -12.0f, 4, 4, 8);
		head.setPos(0.0f, -4.0f, 6.0f);
		head.setTexSize(128, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 48, 38)).addBox(-0.5f, 14.0f, 8.5f, 1, 6, 1);
		body.setPos(0.0f, 4.0f, 6.0f);
		body.setTexSize(128, 64);
		body.mirror = true;
		setRotation(body, 0.0872665f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 16)).addBox(1.0f, -2.0f, -2.0f, 2, 3, 4);
		rightArm.setPos(-7.0f, -2.0f, 6.0f);
		rightArm.setTexSize(128, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 38)).addBox(-2.0f, 0.0f, -2.0f, 4, 16, 4);
		rightLeg.setPos(-4.0f, 8.0f, 6.0f);
		rightLeg.setTexSize(128, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 38)).addBox(-2.0f, 0.0f, -2.0f, 4, 16, 4);
		leftLeg.setPos(4.0f, 8.0f, 6.0f);
		leftLeg.setTexSize(128, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 16)).addBox(-3.0f, -2.0f, -2.0f, 2, 3, 4);
		leftArm.setPos(7.0f, -2.0f, 6.0f);
		leftArm.setTexSize(128, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -6.0f, -14.0f, 8, 3, 2);
		head2.setPos(0.0f, -4.0f, 6.0f);
		head2.setTexSize(128, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 66, 54)).addBox(5.0f, -15.5f, -8.0f, 2, 2, 6);
		head3.setPos(0.0f, -4.0f, 6.0f);
		head3.setTexSize(128, 64);
		head3.mirror = true;
		setRotation(head3, 0.5205006f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 56, 28)).addBox(-4.0f, -8.0f, -20.0f, 8, 2, 8);
		head4.setPos(0.0f, -4.0f, 6.0f);
		head4.setTexSize(128, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 98, 36)).addBox(-4.0f, -6.0f, -20.0f, 8, 3, 0);
		head5.setPos(0.0f, -4.0f, 6.0f);
		head5.setTexSize(128, 64);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 100, 12)).addBox(0.0f, 8.0f, -1.0f, 2, 16, 2);
		leftArm2.setPos(7.0f, -2.0f, 6.0f);
		leftArm2.setTexSize(128, 64);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 100, 12)).addBox(-2.0f, 8.0f, -1.0f, 2, 16, 2);
		rightArm2.setPos(-7.0f, -2.0f, 6.0f);
		rightArm2.setTexSize(128, 64);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 109, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm3.setPos(-7.0f, -2.0f, 6.0f);
		rightArm3.setTexSize(128, 64);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new ModelRenderer(this, 109, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm3.setPos(7.0f, -2.0f, 6.0f);
		leftArm3.setTexSize(128, 64);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 56, 41)).addBox(-4.0f, -3.0f, -20.0f, 8, 2, 8);
		head6.setPos(0.0f, -4.0f, 6.0f);
		head6.setTexSize(128, 64);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 98, 46)).addBox(-4.0f, -6.0f, -20.0f, 0, 3, 6);
		head7.setPos(0.0f, -4.0f, 6.0f);
		head7.setTexSize(128, 64);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 98, 46)).addBox(4.0f, -6.0f, -20.0f, 0, 3, 6);
		head8.setPos(0.0f, -4.0f, 6.0f);
		head8.setTexSize(128, 64);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 53, 1)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head9.setPos(0.0f, -4.0f, 6.0f);
		head9.setTexSize(128, 64);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 17, 51)).addBox(4.0f, -8.0f, -11.0f, 4, 4, 8);
		head10.setPos(0.0f, -4.0f, 6.0f);
		head10.setTexSize(128, 64);
		head10.mirror = true;
		setRotation(head10, -0.6108652f, 0.0f, 0.0f);
		(head11 = new ModelRenderer(this, 45, 54)).addBox(4.5f, -12.5f, -10.0f, 3, 3, 6);
		head11.setPos(0.0f, -4.0f, 6.0f);
		head11.setTexSize(128, 64);
		head11.mirror = true;
		setRotation(head11, 0.0f, 0.0f, 0.0f);
		(head12 = new ModelRenderer(this, 66, 54)).addBox(-7.0f, -15.5f, -8.0f, 2, 2, 6);
		head12.setPos(0.0f, -4.0f, 6.0f);
		head12.setTexSize(128, 64);
		head12.mirror = true;
		setRotation(head12, 0.5205006f, 0.0f, 0.0f);
		(head13 = new ModelRenderer(this, 45, 54)).addBox(-7.5f, -12.5f, -10.0f, 3, 3, 6);
		head13.setPos(0.0f, -4.0f, 6.0f);
		head13.setTexSize(128, 64);
		head13.mirror = true;
		setRotation(head13, 0.0f, 0.0f, 0.0f);
		(head14 = new ModelRenderer(this, 17, 51)).addBox(-8.0f, -8.0f, -11.0f, 4, 4, 8);
		head14.setPos(0.0f, -4.0f, 6.0f);
		head14.setTexSize(128, 64);
		head14.mirror = true;
		setRotation(head14, -0.6108652f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 19, 29)).addBox(-3.0f, 1.0f, -4.0f, 6, 10, 8);
		body2.setPos(0.0f, -4.0f, 6.0f);
		body2.setTexSize(128, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 25, 0)).addBox(-3.5f, 0.0f, -3.0f, 7, 8, 6);
		body3.setPos(0.0f, 4.0f, 6.0f);
		body3.setTexSize(128, 64);
		body3.mirror = true;
		setRotation(body3, 1.308997f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 24, 14)).addBox(-2.0f, 3.0f, 4.0f, 4, 8, 4);
		body4.setPos(0.0f, 4.0f, 6.0f);
		body4.setTexSize(128, 64);
		body4.mirror = true;
		setRotation(body4, 0.4363323f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 46, 25)).addBox(-1.0f, 7.0f, 8.0f, 2, 8, 2);
		body5.setPos(0.0f, 4.0f, 6.0f);
		body5.setTexSize(128, 64);
		body5.mirror = true;
		setRotation(body5, 0.0872665f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 22, 32)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body6.setPos(0.0f, -4.0f, 6.0f);
		body6.setTexSize(128, 64);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head14.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		rightArm.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.zRot = 0.0f;
		rightArm2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.zRot = 0.0f;
		rightArm3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm3.zRot = 0.0f;
		leftArm.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		leftArm2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm2.zRot = 0.0f;
		leftArm3.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm3.zRot = 0.0f;
		rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

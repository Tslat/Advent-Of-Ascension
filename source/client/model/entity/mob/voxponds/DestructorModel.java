package net.tslat.aoa3.client.model.entity.mob.voxponds;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class DestructorModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer plate1;
	private final ModelRenderer rightArm1;
	private final ModelRenderer leftArm1;
	private final ModelRenderer rightLeg1;
	private final ModelRenderer leftLeg1;
	private final ModelRenderer body;
	private final ModelRenderer plate2;
	private final ModelRenderer leftArm2;
	private final ModelRenderer rightArm2;
	private final ModelRenderer rightArm3;
	private final ModelRenderer leftArm3;
	private final ModelRenderer rightArm4;
	private final ModelRenderer leftArm4;
	private final ModelRenderer rightLeg5;
	private final ModelRenderer leftLeg5;

	public DestructorModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -10.0f, -4.0f, 8, 10, 8);
		head.setPos(0.0f, -5.0f, 0.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(plate1 = new ModelRenderer(this, 87, 0)).addBox(4.0f, 0.0f, -2.0f, 1, 3, 4);
		plate1.setPos(0.0f, -6.0f, 0.0f);
		plate1.setTexSize(128, 32);
		plate1.mirror = true;
		setRotation(plate1, 0.0f, 0.0f, 0.0f);
		(rightArm1 = new ModelRenderer(this, 40, 23)).addBox(-5.0f, 8.0f, -2.0f, 2, 3, 4);
		rightArm1.setPos(-6.0f, -3.0f, 0.0f);
		rightArm1.setTexSize(128, 32);
		rightArm1.mirror = true;
		setRotation(rightArm1, 0.0f, 0.0f, 0.0f);
		(leftArm1 = new ModelRenderer(this, 40, 23)).addBox(3.0f, 8.0f, -2.0f, 2, 3, 4);
		leftArm1.setPos(6.0f, -3.0f, 0.0f);
		leftArm1.setTexSize(128, 32);
		leftArm1.mirror = true;
		setRotation(leftArm1, 0.0f, 0.0f, 0.0f);
		(rightLeg1 = new ModelRenderer(this, 20, 20)).addBox(-1.0f, 3.0f, -3.0f, 2, 4, 2);
		rightLeg1.setPos(-3.0f, 12.0f, 0.0f);
		rightLeg1.setTexSize(128, 32);
		rightLeg1.mirror = true;
		setRotation(rightLeg1, 0.0f, 0.0f, 0.0f);
		(leftLeg1 = new ModelRenderer(this, 20, 20)).addBox(-1.0f, 3.0f, -3.0f, 2, 4, 2);
		leftLeg1.setPos(3.0f, 12.0f, 0.0f);
		leftLeg1.setTexSize(128, 32);
		leftLeg1.mirror = true;
		setRotation(leftLeg1, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 61, 0)).addBox(-4.0f, 0.0f, -2.0f, 8, 18, 4);
		body.setPos(0.0f, -6.0f, 0.0f);
		body.setTexSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(plate2 = new ModelRenderer(this, 87, 0)).addBox(-5.0f, 0.0f, -2.0f, 1, 3, 4);
		plate2.setPos(0.0f, -6.0f, 0.0f);
		plate2.setTexSize(128, 32);
		plate2.mirror = true;
		setRotation(plate2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 40, 2)).addBox(-1.0f, -2.0f, -2.0f, 4, 16, 4);
		leftArm2.setPos(6.0f, -3.0f, 0.0f);
		leftArm2.setTexSize(128, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 2)).addBox(-3.0f, -2.0f, -2.0f, 4, 16, 4);
		rightArm2.setPos(-6.0f, -3.0f, 0.0f);
		rightArm2.setTexSize(128, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(rightArm3 = new ModelRenderer(this, 40, 23)).addBox(-5.0f, -2.0f, -2.0f, 2, 3, 4);
		rightArm3.setPos(-6.0f, -3.0f, 0.0f);
		rightArm3.setTexSize(128, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0.0f, 0.0f, 0.0f);
		(leftArm3 = new ModelRenderer(this, 40, 23)).addBox(3.0f, -2.0f, -2.0f, 2, 3, 4);
		leftArm3.setPos(6.0f, -3.0f, 0.0f);
		leftArm3.setTexSize(128, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0.0f, 0.0f, 0.0f);
		(rightArm4 = new ModelRenderer(this, 40, 23)).addBox(-5.0f, 3.0f, -2.0f, 2, 3, 4);
		rightArm4.setPos(-6.0f, -3.0f, 0.0f);
		rightArm4.setTexSize(128, 32);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.0f, 0.0f, 0.0f);
		(leftArm4 = new ModelRenderer(this, 40, 23)).addBox(3.0f, 3.0f, -2.0f, 2, 3, 4);
		leftArm4.setPos(6.0f, -3.0f, 0.0f);
		leftArm4.setTexSize(128, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0.0f, 0.0f, 0.0f);
		(rightLeg5 = new ModelRenderer(this, 90, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg5.setPos(-3.0f, 12.0f, 0.0f);
		rightLeg5.setTexSize(128, 32);
		rightLeg5.mirror = true;
		setRotation(rightLeg5, 0.0f, 0.0f, 0.0f);
		(leftLeg5 = new ModelRenderer(this, 90, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg5.setPos(3.0f, 12.0f, 0.0f);
		leftLeg5.setTexSize(128, 32);
		leftLeg5.mirror = true;
		setRotation(leftLeg5, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		plate1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		plate2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
	}
}

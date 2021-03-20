package net.tslat.aoa3.client.model.entity.mob.crystevia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ConstructOfResistanceModel extends EntityModel<MobEntity> {
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer vr1;
	private final ModelRenderer hr1;
	private final ModelRenderer head;
	private final ModelRenderer leftArm;
	private final ModelRenderer vr2;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightLeg;
	private final ModelRenderer vr3;
	private final ModelRenderer vr4;
	private final ModelRenderer hr2;
	private final ModelRenderer hr3;
	private final ModelRenderer hr4;

	public ConstructOfResistanceModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 64;
		(body = new ModelRenderer(this, 1, 1)).addBox(-4.0f, 0.0f, -3.0f, 8, 14, 6);
		body.setPos(0.0f, -2.0f, 0.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 47, 1)).addBox(-3.0f, -4.0f, -2.0f, 4, 23, 4);
		rightArm.setPos(-7.0f, 2.0f, 0.0f);
		rightArm.setTexSize(64, 64);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(vr1 = new ModelRenderer(this, 30, 1)).addBox(-1.0f, -3.0f, -3.0f, 2, 6, 6);
		vr1.setPos(-5.0f, 1.0f, 0.0f);
		vr1.setTexSize(64, 64);
		vr1.mirror = true;
		setRotation(vr1, 0.0f, 0.0f, 0.0f);
		(hr1 = new ModelRenderer(this, 24, 22)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 4);
		hr1.setPos(-2.0f, 12.0f, 0.0f);
		hr1.setTexSize(64, 64);
		hr1.mirror = true;
		setRotation(hr1, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 5, 45)).addBox(-2.0f, 4.0f, -2.0f, 4, 4, 4);
		head.setPos(0.0f, -14.0f, 0.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 47, 1)).addBox(-1.0f, -4.0f, -2.0f, 4, 23, 4);
		leftArm.setPos(7.0f, 2.0f, 0.0f);
		leftArm.setTexSize(64, 64);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(vr2 = new ModelRenderer(this, 30, 32)).addBox(-1.0f, -3.0f, -3.0f, 2, 6, 6);
		vr2.setPos(4.0f, -8.0f, 0.0f);
		vr2.setTexSize(64, 64);
		vr2.mirror = true;
		setRotation(vr2, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 24)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		leftLeg.setPos(2.0f, 14.0f, 0.0f);
		leftLeg.setTexSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 24)).addBox(-2.0f, 0.0f, -2.0f, 4, 10, 4);
		rightLeg.setPos(-2.0f, 14.0f, 0.0f);
		rightLeg.setTexSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(vr3 = new ModelRenderer(this, 30, 1)).addBox(-1.0f, -3.0f, -3.0f, 2, 6, 6);
		vr3.setPos(5.0f, 1.0f, 0.0f);
		vr3.setTexSize(64, 64);
		vr3.mirror = true;
		setRotation(vr3, 0.0f, 0.0f, 0.0f);
		(vr4 = new ModelRenderer(this, 30, 32)).addBox(-1.0f, -3.0f, -3.0f, 2, 6, 6);
		vr4.setPos(-4.0f, -8.0f, 0.0f);
		vr4.setTexSize(64, 64);
		vr4.mirror = true;
		setRotation(vr4, 0.0f, 0.0f, 0.0f);
		(hr2 = new ModelRenderer(this, 24, 22)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 4);
		hr2.setPos(2.0f, 12.0f, 0.0f);
		hr2.setTexSize(64, 64);
		hr2.mirror = true;
		setRotation(hr2, 0.0f, 0.0f, 0.0f);
		(hr3 = new ModelRenderer(this, 24, 45)).addBox(-3.0f, 0.0f, -3.0f, 6, 2, 6);
		hr3.setPos(0.0f, -4.0f, 0.0f);
		hr3.setTexSize(64, 64);
		hr3.mirror = true;
		setRotation(hr3, 0.0f, 0.0f, 0.0f);
		(hr4 = new ModelRenderer(this, 24, 45)).addBox(-3.0f, 0.0f, -3.0f, 6, 2, 6);
		hr4.setPos(0.0f, -14.0f, 0.0f);
		hr4.setTexSize(64, 64);
		hr4.mirror = true;
		setRotation(hr4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		vr1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		hr1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		vr2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		vr3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		vr4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		hr2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		hr3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		hr4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		leftArm.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.zRot = 0.0f;
		rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		hr1.yRot = ageInTicks * 0.067f * 1.25f;
		hr2.yRot = ageInTicks * 0.067f * 1.25f;
		hr3.yRot = ageInTicks * 0.067f * 1.25f;
		hr4.yRot = ageInTicks * 0.067f * 1.25f;
		vr1.xRot = ageInTicks * 0.067f * 1.25f;
		vr2.xRot = ageInTicks * 0.067f * 1.25f;
		vr3.xRot = ageInTicks * 0.067f * 1.25f;
		vr4.xRot = ageInTicks * 0.067f * 1.25f;
	}
}

package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class GhostlyGoblinModel extends EntityModel<MobEntity> {
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer head;
	private final ModelRenderer nose;
	private final ModelRenderer leftEar;
	private final ModelRenderer rightEar;
	private final ModelRenderer leftArm;
	private final ModelRenderer Staff;
	private final ModelRenderer rightArm;

	public GhostlyGoblinModel() {
		super(RenderType::getEntityTranslucent);
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.leftEar = new ModelRenderer(this, 44, 0);
		this.leftEar.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.leftEar.addBox(-8.0F, -12.0F, 2.0F, 4, 6, 1, 0.0F);
		this.setRotation(leftEar, 0.0F, 0.0F, 0.8922123136195012F);
		this.head = new ModelRenderer(this, 0, 0);
		this.head.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.nose = new ModelRenderer(this, 33, 8);
		this.nose.setRotationPoint(0.0F, 3.0F, -1.0F);
		this.nose.addBox(-1.0F, -7.0F, -5.0F, 2, 5, 2, 0.0F);
		this.rightLeg = new ModelRenderer(this, 18, 18);
		this.rightLeg.setRotationPoint(-2.0F, 14.0F, 1.0F);
		this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.rightArm = new ModelRenderer(this, 0, 18);
		this.rightArm.setRotationPoint(-4.0F, 4.0F, 1.0F);
		this.rightArm.addBox(-4.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.leftLeg = new ModelRenderer(this, 18, 18);
		this.leftLeg.setRotationPoint(2.0F, 14.0F, 1.0F);
		this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F);
		this.body = new ModelRenderer(this, 36, 15);
		this.body.setRotationPoint(0.0F, 3.0F, 1.0F);
		this.body.addBox(-4.0F, 0.0F, -3.0F, 8, 11, 6, 0.0F);
		this.rightEar = new ModelRenderer(this, 33, 0);
		this.rightEar.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.rightEar.addBox(4.0F, -12.0F, 2.0F, 4, 6, 1, 0.0F);
		this.setRotation(rightEar, 0.0F, 0.0F, -0.8922123136195012F);
		this.leftArm = new ModelRenderer(this, 0, 18);
		this.leftArm.setRotationPoint(4.0F, 4.0F, 1.0F);
		this.leftArm.addBox(0.0F, -1.0F, -2.0F, 4, 10, 4, 0.0F);
		this.Staff = new ModelRenderer(this, 59, 0);
		this.Staff.setRotationPoint(-4.0F, 4.0F, 1.0F);
		this.Staff.addBox(-2.5F, -9.0F, -5.0F, 1, 20, 1, 0.0F);
		this.setRotation(Staff, 0.43632999062538147F, 0.0F, 0.0F);
		this.head.addChild(this.leftEar);
		this.head.addChild(this.nose);
		this.head.addChild(this.rightEar);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Staff.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		Staff.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f + 0.43633f;
		Staff.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}
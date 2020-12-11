package net.tslat.aoa3.client.model.entity.mob.iromine;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class QuickpocketModel extends EntityModel<MobEntity> {
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer rightArm2;
	private final ModelRenderer leftArm2;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer head6;
	private final ModelRenderer head7;
	private final ModelRenderer head8;
	private final ModelRenderer head9;
	private final ModelRenderer head10;

	public QuickpocketModel() {
		textureWidth = 64;
		textureHeight = 32;
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 6, 8, 2);
		body.setRotationPoint(1.0f, 8.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 40, 16)).addBox(0.0f, -2.0f, -2.0f, 2, 2, 2);
		rightArm.setRotationPoint(-5.0f, 10.0f, 0.0f);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 40, 16)).addBox(-2.0f, -2.0f, -2.0f, 2, 2, 2);
		leftArm.setRotationPoint(5.0f, 10.0f, 0.0f);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 2, 8, 2);
		rightLeg.setRotationPoint(-2.0f, 16.0f, 0.0f);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 2, 8, 2);
		leftLeg.setRotationPoint(2.0f, 16.0f, 0.0f);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(rightArm2 = new ModelRenderer(this, 40, 16)).addBox(-2.0f, -1.0f, -2.0f, 2, 12, 2);
		rightArm2.setRotationPoint(-4.0f, 11.0f, 0.0f);
		rightArm2.setTextureSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.0f, 0.0f, 0.0f);
		(leftArm2 = new ModelRenderer(this, 40, 16)).addBox(0.0f, -1.0f, -2.0f, 2, 12, 2);
		leftArm2.setRotationPoint(4.0f, 11.0f, 0.0f);
		leftArm2.setTextureSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 9)).addBox(1.0f, 0.0f, -3.0f, 1, 2, 1);
		head2.setRotationPoint(0.0f, 8.0f, -1.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 17, 0)).addBox(-3.0f, -4.0f, 5.0f, 1, 1, 1);
		head3.setRotationPoint(0.0f, 8.0f, -1.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.7853982f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 17, 0)).addBox(2.0f, -4.0f, 5.0f, 1, 1, 1);
		head4.setRotationPoint(0.0f, 8.0f, -1.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.7853982f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 17, 0)).addBox(-3.0f, -3.0f, 2.0f, 1, 2, 4);
		head5.setRotationPoint(0.0f, 8.0f, -1.0f);
		head5.setTextureSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.7853982f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 17, 0)).addBox(2.0f, -3.0f, 2.0f, 1, 2, 4);
		head6.setRotationPoint(0.0f, 8.0f, -1.0f);
		head6.setTextureSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.7853982f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 17, 0)).addBox(-3.0f, -4.0f, 3.0f, 1, 1, 1);
		head7.setRotationPoint(0.0f, 8.0f, -1.0f);
		head7.setTextureSize(64, 32);
		head7.mirror = true;
		setRotation(head7, 0.7853982f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 17, 0)).addBox(2.0f, -4.0f, 3.0f, 1, 1, 1);
		head8.setRotationPoint(0.0f, 8.0f, -1.0f);
		head8.setTextureSize(64, 32);
		head8.mirror = true;
		setRotation(head8, 0.7853982f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 0, 0)).addBox(-2.0f, -4.0f, -3.0f, 4, 4, 4);
		head9.setRotationPoint(0.0f, 8.0f, -1.0f);
		head9.setTextureSize(64, 32);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 0, 9)).addBox(-2.0f, 0.0f, -3.0f, 1, 2, 1);
		head10.setRotationPoint(0.0f, 8.0f, -1.0f);
		head10.setTextureSize(64, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head2.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head3.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head4.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head5.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head6.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head7.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head8.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head9.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		head10.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		rightArm2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm2.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		leftArm2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm2.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

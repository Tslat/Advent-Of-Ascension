package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class SandGolemModel extends EntityModel<MobEntity> {
	private final ModelRenderer rightFoot;
	private final ModelRenderer leftFoot;
	private final ModelRenderer Hip;
	private final ModelRenderer hipTorsoJoint;
	private final ModelRenderer lowerTorso;
	private final ModelRenderer Torso;
	private final ModelRenderer head;
	private final ModelRenderer nose;
	private final ModelRenderer Eyebrow;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;

	public SandGolemModel() {
		textureWidth = 128;
		textureHeight = 32;
		(rightFoot = new ModelRenderer(this, 61, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		rightFoot.setRotationPoint(-3.0f, 15.0f, 0.0f);
		rightFoot.setTextureSize(64, 32);
		rightFoot.mirror = true;
		setRotation(rightFoot, 0.0f, 0.0f, 0.0f);
		(leftFoot = new ModelRenderer(this, 61, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		leftFoot.setRotationPoint(3.0f, 15.0f, 0.0f);
		leftFoot.setTextureSize(64, 32);
		leftFoot.mirror = true;
		setRotation(leftFoot, 0.0f, 0.0f, 0.0f);
		(Hip = new ModelRenderer(this, 60, 13)).addBox(-3.0f, 0.0f, -3.0f, 6, 2, 6);
		Hip.setRotationPoint(0.0f, 13.0f, 0.0f);
		Hip.setTextureSize(64, 32);
		Hip.mirror = true;
		setRotation(Hip, 0.0f, 0.0f, 0.0f);
		(hipTorsoJoint = new ModelRenderer(this, 25, 0)).addBox(-2.0f, -3.0f, -2.0f, 4, 3, 4);
		hipTorsoJoint.setRotationPoint(0.0f, 13.0f, 0.0f);
		hipTorsoJoint.setTextureSize(64, 32);
		hipTorsoJoint.mirror = true;
		setRotation(hipTorsoJoint, 0.0f, 0.0f, 0.0f);
		(lowerTorso = new ModelRenderer(this, 43, 23)).addBox(-3.0f, -1.0f, -3.0f, 6, 3, 6);
		lowerTorso.setRotationPoint(0.0f, 9.0f, 0.0f);
		lowerTorso.setTextureSize(64, 32);
		lowerTorso.mirror = true;
		setRotation(lowerTorso, 0.0f, 0.0f, 0.0f);
		(Torso = new ModelRenderer(this, 0, 16)).addBox(-6.0f, -7.0f, -4.5f, 12, 7, 9);
		Torso.setRotationPoint(0.0f, 8.0f, 0.0f);
		Torso.setTextureSize(64, 32);
		Torso.mirror = true;
		setRotation(Torso, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head.setRotationPoint(0.0f, 1.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(nose = new ModelRenderer(this, 25, 8)).addBox(-1.0f, -4.0f, -4.0f, 2, 4, 1);
		nose.setRotationPoint(0.0f, 1.0f, 0.0f);
		nose.setTextureSize(64, 32);
		nose.mirror = true;
		setRotation(nose, 0.0f, 0.0f, 0.0f);
		(Eyebrow = new ModelRenderer(this, 27, 13)).addBox(-3.0f, -6.0f, -4.0f, 6, 2, 1);
		Eyebrow.setRotationPoint(0.0f, 1.0f, 0.0f);
		Eyebrow.setTextureSize(64, 32);
		Eyebrow.mirror = true;
		setRotation(Eyebrow, 0.0f, 0.0f, 0.0f);
		(leftArm = new ModelRenderer(this, 43, 0)).addBox(0.0f, -2.0f, -2.0f, 4, 17, 4);
		leftArm.setRotationPoint(6.0f, 1.0f, 0.0f);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0.0f, 0.0f, 0.0f);
		(rightArm = new ModelRenderer(this, 43, 0)).addBox(-4.0f, -2.0f, -2.0f, 4, 17, 4);
		rightArm.setRotationPoint(-6.0f, 1.0f, 0.0f);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 57.295776f;
		head.rotateAngleX = headPitch / 54.11268f;
		nose.rotateAngleY = netHeadYaw / 57.295776f;
		nose.rotateAngleX = headPitch / 54.11268f;
		Eyebrow.rotateAngleY = netHeadYaw / 57.295776f;
		Eyebrow.rotateAngleX = headPitch / 54.11268f;
		rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightFoot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightFoot.rotateAngleY = 0.0f;
		leftFoot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		rightFoot.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftFoot.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Hip.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		hipTorsoJoint.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		lowerTorso.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Torso.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		nose.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Eyebrow.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

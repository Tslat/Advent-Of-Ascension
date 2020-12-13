package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class FacelessRunnerModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightLegfoot;
	private final ModelRenderer leftLegfoot;
	private final ModelRenderer leftLegtop;
	private final ModelRenderer rightLegtop;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer rightLegmid;
	private final ModelRenderer leftLegmid;
	private final ModelRenderer rightLegfoot2;
	private final ModelRenderer leftLegfoot2;
	private final ModelRenderer head4;

	public FacelessRunnerModel() {
		textureWidth = 64;
		textureHeight = 32;
		(head = new ModelRenderer(this, 29, 8)).addBox(-4.0f, 0.0f, 0.0f, 8, 1, 1);
		head.setRotationPoint(0.0f, 7.0f, -4.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 9, 18)).addBox(-4.0f, 0.0f, -2.0f, 8, 3, 4);
		body.setRotationPoint(0.0f, 7.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightLegfoot = new ModelRenderer(this, 52, 26)).addBox(1.0f, 15.0f, -3.0f, 2, 2, 4);
		rightLegfoot.setRotationPoint(-6.0f, 7.0f, 0.0f);
		rightLegfoot.setTextureSize(64, 32);
		rightLegfoot.mirror = true;
		setRotation(rightLegfoot, 0.0f, 0.0f, 0.0f);
		(leftLegfoot = new ModelRenderer(this, 52, 26)).addBox(1.0f, 15.0f, -3.0f, 2, 2, 4);
		leftLegfoot.setRotationPoint(6.0f, 7.0f, 0.0f);
		leftLegfoot.setTextureSize(64, 32);
		leftLegfoot.mirror = true;
		setRotation(leftLegfoot, 0.0f, 0.0f, 0.0f);
		(leftLegtop = new ModelRenderer(this, 48, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		leftLegtop.setRotationPoint(6.0f, 7.0f, 0.0f);
		leftLegtop.setTextureSize(64, 32);
		leftLegtop.mirror = true;
		setRotation(leftLegtop, 0.0f, 0.0f, 0.0f);
		(rightLegtop = new ModelRenderer(this, 48, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		rightLegtop.setRotationPoint(-6.0f, 7.0f, 0.0f);
		rightLegtop.setTextureSize(64, 32);
		rightLegtop.mirror = true;
		setRotation(rightLegtop, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 13, 5)).addBox(-3.0f, -4.0f, 9.0f, 6, 3, 2);
		head2.setRotationPoint(0.0f, 7.0f, -4.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 9, 26)).addBox(-4.0f, 1.0f, -4.0f, 8, 1, 5);
		head3.setRotationPoint(0.0f, 7.0f, -4.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(rightLegmid = new ModelRenderer(this, 39, 18)).addBox(-1.0f, 7.0f, 0.0f, 2, 10, 4);
		rightLegmid.setRotationPoint(-6.0f, 7.0f, 0.0f);
		rightLegmid.setTextureSize(64, 32);
		rightLegmid.mirror = true;
		setRotation(rightLegmid, 0.0f, 0.0f, 0.0f);
		(leftLegmid = new ModelRenderer(this, 39, 18)).addBox(-1.0f, 7.0f, 0.0f, 2, 10, 4);
		leftLegmid.setRotationPoint(6.0f, 7.0f, 0.0f);
		leftLegmid.setTextureSize(64, 32);
		leftLegmid.mirror = true;
		setRotation(leftLegmid, 0.0f, 0.0f, 0.0f);
		(rightLegfoot2 = new ModelRenderer(this, 52, 26)).addBox(-3.0f, 15.0f, -3.0f, 2, 2, 4);
		rightLegfoot2.setRotationPoint(-6.0f, 7.0f, 0.0f);
		rightLegfoot2.setTextureSize(64, 32);
		rightLegfoot2.mirror = true;
		setRotation(rightLegfoot2, 0.0f, 0.0f, 0.0f);
		(leftLegfoot2 = new ModelRenderer(this, 52, 26)).addBox(-3.0f, 15.0f, -3.0f, 2, 2, 4);
		leftLegfoot2.setRotationPoint(6.0f, 7.0f, 0.0f);
		leftLegfoot2.setTextureSize(64, 32);
		leftLegfoot2.mirror = true;
		setRotation(leftLegfoot2, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -5.0f, -4.0f, 8, 5, 13);
		head4.setRotationPoint(0.0f, 7.0f, -4.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 89.12676f;
		head.rotateAngleX = headPitch / 85.943665f;
		head2.rotateAngleY = netHeadYaw / 89.12676f;
		head2.rotateAngleX = headPitch / 85.943665f;
		head3.rotateAngleY = netHeadYaw / 89.12676f;
		head3.rotateAngleX = headPitch / 85.943665f;
		head4.rotateAngleY = netHeadYaw / 89.12676f;
		head4.rotateAngleX = headPitch / 85.943665f;
		rightLegtop.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLegtop.rotateAngleY = 0.0f;
		rightLegmid.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLegmid.rotateAngleY = 0.0f;
		rightLegfoot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLegfoot.rotateAngleY = 0.0f;
		rightLegfoot2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLegfoot2.rotateAngleY = 0.0f;
		leftLegtop.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLegmid.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLegfoot.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leftLegfoot2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLegfoot.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLegfoot.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLegtop.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLegtop.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLegmid.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLegmid.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLegfoot2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLegfoot2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

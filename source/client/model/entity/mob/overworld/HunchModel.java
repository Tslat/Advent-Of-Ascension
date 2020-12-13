package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class HunchModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;

	public HunchModel() {
		textureWidth = 64;
		textureHeight = 32;

		(head = new ModelRenderer(this, 0, 0))
				.addBox(-4.0f, -5.0f, -4.0f, 8, 5, 8);
		head.setRotationPoint(0.0f, 6.0f, 0.0f);
		head.setTextureSize(64, 32);

		(body = new ModelRenderer(this, 16, 16))
				.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, 6.0f, 0.0f);
		body.setTextureSize(64, 32);

		(rightArm = new ModelRenderer(this, 40, 16))
				.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setRotationPoint(-5.0f, 6.0f, 0.0f);
		rightArm.setTextureSize(64, 32);

		(leftArm = new ModelRenderer(this, 40, 16))
				.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setRotationPoint(5.0f, 6.0f, 0.0f);
		leftArm.setTextureSize(64, 32);

		(rightLeg = new ModelRenderer(this, 0, 16))
				.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		rightLeg.setRotationPoint(-2.0f, 18.0f, 0.0f);
		rightLeg.setTextureSize(64, 32);

		(leftLeg = new ModelRenderer(this, 0, 16))
				.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leftLeg.setRotationPoint(2.0f, 18.0f, 0.0f);
		leftLeg.setTextureSize(64, 32);
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 57.295776f;
		head.rotateAngleX = headPitch / 54.11268f;
		rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightArm.rotateAngleZ = 0.0f;
		leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftArm.rotateAngleZ = 0.0f;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}
}

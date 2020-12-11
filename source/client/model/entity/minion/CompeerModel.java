package net.tslat.aoa3.client.model.entity.minion;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class CompeerModel extends EntityModel<MobEntity> {
	private final ModelRenderer body;
	private final ModelRenderer Leg_Front_1;
	private final ModelRenderer Leg_back;
	private final ModelRenderer Leg_back_2;
	private final ModelRenderer Leg_Front_2;
	private final ModelRenderer head;
	private final ModelRenderer Back_Bone;

	public CompeerModel() {
		textureWidth = 128;
		textureHeight = 64;
		(body = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -5.0f, -8.0f, 10, 10, 16);
		body.setRotationPoint(0.0f, 10.0f, -2.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(Leg_Front_1 = new ModelRenderer(this, 73, 14)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		Leg_Front_1.setRotationPoint(3.0f, 15.0f, -6.0f);
		Leg_Front_1.setTextureSize(64, 32);
		Leg_Front_1.mirror = true;
		setRotation(Leg_Front_1, 0.0f, 0.0f, 0.0f);
		(Leg_back = new ModelRenderer(this, 73, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		Leg_back.setRotationPoint(3.0f, 15.0f, 3.0f);
		Leg_back.setTextureSize(64, 32);
		Leg_back.mirror = true;
		setRotation(Leg_back, 0.0f, 0.0f, 0.0f);
		(Leg_back_2 = new ModelRenderer(this, 73, 0)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		Leg_back_2.setRotationPoint(-3.0f, 15.0f, 3.0f);
		Leg_back_2.setTextureSize(64, 32);
		Leg_back_2.mirror = true;
		setRotation(Leg_back_2, 0.0f, 0.0f, 0.0f);
		(Leg_Front_2 = new ModelRenderer(this, 73, 14)).addBox(-2.0f, 0.0f, -2.0f, 4, 9, 4);
		Leg_Front_2.setRotationPoint(-3.0f, 15.0f, -6.0f);
		Leg_Front_2.setTextureSize(64, 32);
		Leg_Front_2.mirror = true;
		setRotation(Leg_Front_2, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 28, 28)).addBox(-4.0f, -4.0f, -9.0f, 8, 8, 9);
		head.setRotationPoint(0.0f, 7.0f, -8.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(Back_Bone = new ModelRenderer(this, 0, 27)).addBox(-1.5f, -3.0f, -1.5f, 3, 3, 10);
		Back_Bone.setRotationPoint(0.0f, 5.0f, -4.0f);
		Back_Bone.setTextureSize(64, 32);
		Back_Bone.mirror = true;
		setRotation(Back_Bone, 0.0f, 0.0f, 0.0f);
	}

	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {

		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg_Front_1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg_back.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg_back_2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg_Front_2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Back_Bone.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 57.295776f;
		head.rotateAngleX = headPitch / 54.11268f;
		Leg_Front_1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Leg_Front_1.rotateAngleY = 0.0f;
		Leg_back.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Leg_back.rotateAngleY = 0.0f;
		Leg_back_2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Leg_Front_2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

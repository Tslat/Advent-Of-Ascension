package net.tslat.aoa3.client.model.entity.minion;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class PenguinModel extends EntityModel<MobEntity> {
	private final ModelRenderer Foot1;
	private final ModelRenderer body;
	private final ModelRenderer Foot2;
	private final ModelRenderer head;
	private final ModelRenderer Beak_mouth;
	private final ModelRenderer Beak_top;
	private final ModelRenderer R_arm;
	private final ModelRenderer L_arm;
	private final ModelRenderer tail;

	public PenguinModel() {
		textureWidth = 64;
		textureHeight = 32;
		(Foot1 = new ModelRenderer(this, 0, 17)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		Foot1.setRotationPoint(-2.0f, 18.0f, 0.0f);
		Foot1.setTextureSize(64, 32);
		Foot1.mirror = true;
		setRotation(Foot1, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 18, 17)).addBox(-4.0f, 0.0f, -4.0f, 8, 6, 7);
		body.setRotationPoint(0.0f, 12.0f, 0.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(Foot2 = new ModelRenderer(this, 0, 17)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		Foot2.setRotationPoint(2.0f, 18.0f, 0.0f);
		Foot2.setTextureSize(64, 32);
		Foot2.mirror = true;
		setRotation(Foot2, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 12.0f, -2.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(Beak_mouth = new ModelRenderer(this, 34, 13)).addBox(-2.0f, -3.0f, -5.0f, 4, 1, 1);
		Beak_mouth.setRotationPoint(0.0f, 12.0f, -2.0f);
		Beak_mouth.setTextureSize(64, 32);
		Beak_mouth.mirror = true;
		setRotation(Beak_mouth, 0.0f, 0.0f, 0.0f);
		(Beak_top = new ModelRenderer(this, 57, 13)).addBox(-1.0f, -4.0f, -5.0f, 2, 1, 1);
		Beak_top.setRotationPoint(0.0f, 12.0f, -2.0f);
		Beak_top.setTextureSize(64, 32);
		Beak_top.mirror = true;
		setRotation(Beak_top, 0.0f, 0.0f, 0.0f);
		(R_arm = new ModelRenderer(this, 46, 0)).addBox(-1.0f, 0.0f, -2.0f, 1, 7, 4);
		R_arm.setRotationPoint(-4.0f, 12.0f, 0.0f);
		R_arm.setTextureSize(64, 32);
		R_arm.mirror = true;
		setRotation(R_arm, 0.0f, 0.0f, 15.0f);
		(L_arm = new ModelRenderer(this, 34, 0)).addBox(0.0f, 0.0f, -1.955556f, 1, 7, 4);
		L_arm.setRotationPoint(4.0f, 12.0f, 0.0f);
		L_arm.setTextureSize(64, 32);
		L_arm.mirror = true;
		setRotation(L_arm, 0.0f, 0.0f, -15.0f);
		(tail = new ModelRenderer(this, 0, 28)).addBox(-1.0f, -1.0f, -1.0f, 2, 2, 2);
		tail.setRotationPoint(0.0f, 18.0f, 3.0f);
		tail.setTextureSize(64, 32);
		tail.mirror = true;
		setRotation(tail, 0.7853982f, 0.0f, 0.0f);
	}

	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		Foot1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Foot2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Beak_mouth.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Beak_top.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		R_arm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		L_arm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		tail.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 57.295776f;
		head.rotateAngleX = headPitch / 54.11268f;
		Beak_mouth.rotateAngleY = netHeadYaw / 57.295776f;
		Beak_mouth.rotateAngleX = headPitch / 54.11268f;
		Beak_top.rotateAngleY = netHeadYaw / 57.295776f;
		Beak_top.rotateAngleX = headPitch / 54.11268f;
		Foot1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Foot1.rotateAngleY = 0.0f;
		Foot2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		R_arm.rotateAngleZ = MathHelper.cos(ageInTicks * 0.1f) * 3.1415927f * 0.03f;
		L_arm.rotateAngleZ = -R_arm.rotateAngleZ;
	}
}

package net.tslat.aoa3.client.model.entity.animal;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class NightWatcherModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body1;
	private final ModelRenderer RightArm1;
	private final ModelRenderer LeftArm1;
	private final ModelRenderer RightLeg1;
	private final ModelRenderer LeftLeg1;
	private final ModelRenderer body2;
	private final ModelRenderer LeftLeg2;
	private final ModelRenderer RightLeg2;
	private final ModelRenderer LeftArm2;
	private final ModelRenderer RightArm2;
	private final ModelRenderer LeftArm3;
	private final ModelRenderer RightArm3;
	private final ModelRenderer LeftArm4;
	private final ModelRenderer RightArm4;

	public NightWatcherModel() {
		texWidth = 64;
		texHeight = 32;
		(head = new ModelRenderer(this, 0, 16)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head.setPos(0.0f, -13.0f, -5.0f);
		head.setTexSize(64, 32);
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 26, 8)).addBox(-6.0f, 0.0f, -2.0f, 10, 2, 4);
		body1.setPos(1.0f, -18.0f, 0.0f);
		body1.setTexSize(64, 32);
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(RightArm1 = new ModelRenderer(this, 3, 7)).addBox(-1.0f, 30.0f, 1.0f, 1, 6, 1);
		RightArm1.setPos(-5.0f, -14.0f, 0.0f);
		RightArm1.setTexSize(64, 32);
		setRotation(RightArm1, 0.0f, 0.0f, 0.0f);
		(LeftArm1 = new ModelRenderer(this, 3, 7)).addBox(0.0f, 30.0f, 1.0f, 1, 6, 1);
		LeftArm1.setPos(5.0f, -14.0f, 0.0f);
		LeftArm1.setTexSize(64, 32);
		setRotation(LeftArm1, 0.0f, 0.0f, 0.0f);
		(RightLeg1 = new ModelRenderer(this, 46, 0)).addBox(-1.0f, 30.0f, -1.0f, 2, 4, 2);
		RightLeg1.setPos(-2.0f, -10.0f, 0.0f);
		RightLeg1.setTexSize(64, 32);
		setRotation(RightLeg1, 0.0f, 0.0f, 0.0f);
		(LeftLeg1 = new ModelRenderer(this, 46, 0)).addBox(-1.0f, 30.0f, -1.0f, 2, 4, 2);
		LeftLeg1.setPos(2.0f, -10.0f, 0.0f);
		LeftLeg1.setTexSize(64, 32);
		setRotation(LeftLeg1, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 32, 16)).addBox(-4.0f, 0.0f, -2.0f, 6, 6, 4);
		body2.setPos(1.0f, -16.0f, 0.0f);
		body2.setTexSize(64, 32);
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(LeftLeg2 = new ModelRenderer(this, 56, 0)).addBox(-1.0f, 0.0f, -1.0f, 2, 30, 2);
		LeftLeg2.setPos(2.0f, -10.0f, 0.0f);
		LeftLeg2.setTexSize(64, 32);
		setRotation(LeftLeg2, 0.0f, 0.0f, 0.0f);
		(RightLeg2 = new ModelRenderer(this, 56, 0)).addBox(-1.0f, 0.0f, -1.0f, 2, 30, 2);
		RightLeg2.setPos(-2.0f, -10.0f, 0.0f);
		RightLeg2.setTexSize(64, 32);
		setRotation(RightLeg2, 0.0f, 0.0f, 0.0f);
		(LeftArm2 = new ModelRenderer(this, 56, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 30, 2);
		LeftArm2.setPos(5.0f, -14.0f, 0.0f);
		LeftArm2.setTexSize(64, 32);
		setRotation(LeftArm2, 0.0f, 0.0f, 0.0f);
		(RightArm2 = new ModelRenderer(this, 56, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 30, 2);
		RightArm2.setPos(-5.0f, -14.0f, 0.0f);
		RightArm2.setTexSize(64, 32);
		setRotation(RightArm2, 0.0f, 0.0f, 0.0f);
		(LeftArm3 = new ModelRenderer(this, 3, 0)).addBox(0.0f, 28.0f, -2.0f, 1, 2, 4);
		LeftArm3.setPos(5.0f, -14.0f, 0.0f);
		LeftArm3.setTexSize(64, 32);
		setRotation(LeftArm3, 0.0f, 0.0f, 0.0f);
		(RightArm3 = new ModelRenderer(this, 3, 0)).addBox(-1.0f, 28.0f, -2.0f, 1, 2, 4);
		RightArm3.setPos(-5.0f, -14.0f, 0.0f);
		RightArm3.setTexSize(64, 32);
		setRotation(RightArm3, 0.0f, 0.0f, 0.0f);
		(LeftArm4 = new ModelRenderer(this, 3, 7)).addBox(0.0f, 30.0f, -2.0f, 1, 6, 1);
		LeftArm4.setPos(5.0f, -14.0f, 0.0f);
		LeftArm4.setTexSize(64, 32);
		setRotation(LeftArm4, 0.0f, 0.0f, 0.0f);
		(RightArm4 = new ModelRenderer(this, 3, 7)).addBox(-1.0f, 30.0f, -2.0f, 1, 6, 1);
		RightArm4.setPos(-5.0f, -14.0f, 0.0f);
		RightArm4.setTexSize(64, 32);
		setRotation(RightArm4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		RightArm1.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		RightArm1.zRot = 0.0f;
		RightArm2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		RightArm2.zRot = 0.0f;
		RightArm3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		RightArm3.zRot = 0.0f;
		RightArm4.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		RightArm4.zRot = 0.0f;
		LeftArm1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		LeftArm1.zRot = 0.0f;
		LeftArm2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		LeftArm2.zRot = 0.0f;
		LeftArm3.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		LeftArm3.zRot = 0.0f;
		LeftArm4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		LeftArm4.zRot = 0.0f;
		RightLeg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		RightLeg1.yRot = 0.0f;
		RightLeg2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		RightLeg2.yRot = 0.0f;
		LeftLeg1.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		LeftLeg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RightArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		LeftArm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RightLeg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		LeftLeg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		LeftLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RightLeg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		LeftArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RightArm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		LeftArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RightArm3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		LeftArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RightArm4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}

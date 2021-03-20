package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class MuckopedeModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg4;
	private final ModelRenderer leg3;
	private final ModelRenderer body;
	private final ModelRenderer body2;
	private final ModelRenderer leg5;
	private final ModelRenderer leg7;
	private final ModelRenderer leg9;
	private final ModelRenderer leg6;
	private final ModelRenderer leg8;
	private final ModelRenderer leg10;
	private final ModelRenderer leg12;
	private final ModelRenderer leg11;

	public MuckopedeModel() {
		texWidth = 64;
		texHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head.setPos(0.0f, 14.0f, -15.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg1.setPos(-7.0f, 18.0f, 4.0f);
		leg1.setTexSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg2.setPos(7.0f, 18.0f, 4.0f);
		leg2.setTexSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leg4.setPos(7.0f, 16.0f, 16.0f);
		leg4.setTexSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 8, 4);
		leg3.setPos(-7.0f, 16.0f, 16.0f);
		leg3.setTexSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 28, 8)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body.setPos(0.0f, 15.0f, 10.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 28, 8)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body2.setPos(0.0f, 15.0f, -6.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(leg5 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg5.setPos(-7.0f, 18.0f, -14.0f);
		leg5.setTexSize(64, 32);
		leg5.mirror = true;
		setRotation(leg5, 0.0f, 0.0f, 0.0f);
		(leg7 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg7.setPos(-7.0f, 18.0f, -8.0f);
		leg7.setTexSize(64, 32);
		leg7.mirror = true;
		setRotation(leg7, 0.0f, 0.0f, 0.0f);
		(leg9 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg9.setPos(-7.0f, 18.0f, -2.0f);
		leg9.setTexSize(64, 32);
		leg9.mirror = true;
		setRotation(leg9, 0.0f, 0.0f, 0.0f);
		(leg6 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg6.setPos(7.0f, 18.0f, -14.0f);
		leg6.setTexSize(64, 32);
		leg6.mirror = true;
		setRotation(leg6, 0.0f, 0.0f, 0.0f);
		(leg8 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg8.setPos(7.0f, 18.0f, -8.0f);
		leg8.setTexSize(64, 32);
		leg8.mirror = true;
		setRotation(leg8, 0.0f, 0.0f, 0.0f);
		(leg10 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg10.setPos(7.0f, 18.0f, -2.0f);
		leg10.setTexSize(64, 32);
		leg10.mirror = true;
		setRotation(leg10, 0.0f, 0.0f, 0.0f);
		(leg12 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg12.setPos(7.0f, 18.0f, 10.0f);
		leg12.setTexSize(64, 32);
		leg12.mirror = true;
		setRotation(leg12, 0.0f, 0.0f, 0.0f);
		(leg11 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg11.setPos(-7.0f, 18.0f, 10.0f);
		leg11.setTexSize(64, 32);
		leg11.mirror = true;
		setRotation(leg11, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = limbSwingAmount / 54.11268f;
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1.yRot = 0.0f;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.yRot = 0.0f;
		leg5.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg5.yRot = 0.0f;
		leg7.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg7.yRot = 0.0f;
		leg9.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg9.yRot = 0.0f;
		leg11.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg11.yRot = 0.0f;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg6.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg8.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg10.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg12.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}

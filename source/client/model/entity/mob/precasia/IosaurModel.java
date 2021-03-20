package net.tslat.aoa3.client.model.entity.mob.precasia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class IosaurModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body1;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;

	public IosaurModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -2.0f, -5.0f, 6, 5, 8);
		head.setPos(0.0f, -2.0f, -11.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 70, 21)).addBox(-4.0f, -13.0f, -11.0f, 4, 7, 4);
		body1.setPos(2.0f, 5.0f, 3.0f);
		body1.setTexSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.6108652f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 14)).addBox(-2.0f, 0.0f, -2.0f, 4, 14, 4);
		leg1.setPos(-7.0f, 10.0f, 7.0f);
		leg1.setTexSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 14)).addBox(-2.0f, 0.0f, -2.0f, 4, 14, 4);
		leg2.setPos(7.0f, 10.0f, 7.0f);
		leg2.setTexSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 14)).addBox(-2.0f, 0.0f, -2.0f, 4, 14, 4);
		leg3.setPos(-7.0f, 10.0f, -5.0f);
		leg3.setTexSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 14)).addBox(-2.0f, 0.0f, -2.0f, 4, 14, 4);
		leg4.setPos(7.0f, 10.0f, -5.0f);
		leg4.setTexSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 117, 8)).addBox(-2.0f, 15.0f, 3.0f, 2, 6, 2);
		body2.setPos(1.0f, 3.0f, 3.0f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.8726646f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 100, 19)).addBox(-5.0f, -11.0f, -9.0f, 6, 7, 6);
		body3.setPos(2.0f, 5.0f, 3.0f);
		body3.setTexSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.9599311f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 28, 8)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body4.setPos(0.0f, 6.0f, 3.0f);
		body4.setTexSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 1.570796f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 69, 8)).addBox(-3.0f, 5.0f, -6.0f, 6, 6, 6);
		body5.setPos(0.0f, 6.0f, 3.0f);
		body5.setTexSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 1.570796f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 98, 8)).addBox(-2.0f, 10.0f, -5.0f, 4, 6, 4);
		body6.setPos(0.0f, 3.0f, 3.0f);
		body6.setTexSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 1.308997f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / (float)(180f / Math.PI);
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}

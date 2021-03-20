package net.tslat.aoa3.client.model.entity.mob.deeplands;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class RockbiterModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg2p2;
	private final ModelRenderer leg4p2;
	private final ModelRenderer leg3p2;
	private final ModelRenderer leg1p2;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;

	public RockbiterModel() {
		texWidth = 128;
		texHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head.setPos(0.0f, 10.0f, -8.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 97, 10)).addBox(-2.0f, -8.0f, -4.0f, 1, 8, 14);
		body.setPos(0.0f, 11.0f, 2.0f);
		body.setTexSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 67, 2)).addBox(-4.0f, -2.0f, -2.0f, 6, 6, 6);
		leg1.setPos(-8.0f, 12.0f, 11.0f);
		leg1.setTexSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, -0.8726646f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 67, 2)).addBox(-2.0f, -2.0f, -2.0f, 6, 6, 6);
		leg2.setPos(8.0f, 12.0f, 11.0f);
		leg2.setTexSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, -0.8726646f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 67, 2)).addBox(-4.0f, -2.0f, -3.0f, 6, 6, 6);
		leg3.setPos(-8.0f, 12.0f, -1.0f);
		leg3.setTexSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, -0.8726646f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 67, 2)).addBox(-2.0f, -2.0f, -3.0f, 6, 6, 6);
		leg4.setPos(8.0f, 12.0f, -1.0f);
		leg4.setTexSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, -0.8726646f, 0.0f, 0.0f);
		(leg2p2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
		leg2p2.setPos(8.0f, 12.0f, 11.0f);
		leg2p2.setTexSize(128, 32);
		leg2p2.mirror = true;
		setRotation(leg2p2, 0.0f, 0.0f, 0.0f);
		(leg4p2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
		leg4p2.setPos(8.0f, 12.0f, -1.0f);
		leg4p2.setTexSize(128, 32);
		leg4p2.mirror = true;
		setRotation(leg4p2, 0.0f, 0.0f, 0.0f);
		(leg3p2 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
		leg3p2.setPos(-8.0f, 12.0f, -1.0f);
		leg3p2.setTexSize(128, 32);
		leg3p2.mirror = true;
		setRotation(leg3p2, 0.0f, 0.0f, 0.0f);
		(leg1p2 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
		leg1p2.setPos(-8.0f, 12.0f, 11.0f);
		leg1p2.setTexSize(128, 32);
		leg1p2.mirror = true;
		setRotation(leg1p2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 18, 4)).addBox(-6.0f, -10.0f, -7.0f, 12, 18, 10);
		body2.setPos(0.0f, 11.0f, 2.0f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 70, 15)).addBox(-5.0f, -10.0f, -7.0f, 2, 7, 10);
		body3.setPos(0.0f, 11.0f, 2.0f);
		body3.setTexSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 70, 15)).addBox(3.0f, -10.0f, -7.0f, 2, 7, 10);
		body4.setPos(0.0f, 11.0f, 2.0f);
		body4.setTexSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 97, 10)).addBox(1.0f, -8.0f, -4.0f, 1, 8, 14);
		body5.setPos(0.0f, 11.0f, 2.0f);
		body5.setTexSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount - 0.872f;
		leg1.yRot = 0.0f;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount - 0.872f;
		leg3.yRot = 0.0f;
		leg1p2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1p2.yRot = 0.0f;
		leg3p2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3p2.yRot = 0.0f;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount - 0.872f;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount - 0.872f;
		leg2p2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4p2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

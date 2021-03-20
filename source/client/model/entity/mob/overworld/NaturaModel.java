package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class NaturaModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer body0;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;
	private final ModelRenderer arm1;
	private final ModelRenderer arm2;
	private final ModelRenderer head2;
	private final ModelRenderer head3;

	public NaturaModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new ModelRenderer(this, 43, 0)).addBox(4.0f, -5.0f, -4.0f, 1, 3, 6);
		head.setPos(0.0f, 1.0f, -4.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 51, 46)).addBox(3.0f, -12.0f, -6.0f, 6, 10, 0);
		body.setPos(0.0f, 11.0f, 2.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 12, 4);
		leg1.setPos(-6.0f, 12.0f, 10.0f);
		leg1.setTexSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 12, 4);
		leg2.setPos(6.0f, 12.0f, 10.0f);
		leg2.setTexSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 24, 48)).addBox(-3.0f, 0.0f, -3.0f, 4, 10, 4);
		leg3.setPos(-6.0f, 12.0f, -4.0f);
		leg3.setTexSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, -1.570796f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 24, 48)).addBox(-1.0f, 0.0f, -3.0f, 4, 10, 4);
		leg4.setPos(6.0f, 12.0f, -4.0f);
		leg4.setTexSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, -1.570796f, 0.0f, 0.0f);
		(body0 = new ModelRenderer(this, 1, 35)).addBox(-5.0f, -4.0f, -2.0f, 2, 18, 7);
		body0.setPos(4.0f, 11.0f, 2.0f);
		body0.setTexSize(64, 64);
		body0.mirror = true;
		setRotation(body0, 2.617994f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 23, 35)).addBox(-3.0f, -12.0f, -7.0f, 6, 10, 2);
		body2.setPos(0.0f, 11.0f, 2.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 40, 35)).addBox(-9.0f, -12.0f, -6.0f, 6, 10, 0);
		body3.setPos(0.0f, 11.0f, 2.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 23, 9)).addBox(-6.0f, -10.0f, -5.0f, 12, 18, 7);
		body4.setPos(0.0f, 11.0f, 2.0f);
		body4.setTexSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 1.570796f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 1, 35)).addBox(-5.0f, -4.0f, -2.0f, 2, 18, 7);
		body5.setPos(0.0f, 11.0f, 2.0f);
		body5.setTexSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 2.181662f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 1, 35)).addBox(-5.0f, -4.0f, -2.0f, 2, 18, 7);
		body6.setPos(8.0f, 11.0f, 2.0f);
		body6.setTexSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 2.181662f, 0.0f, 0.0f);
		(arm1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 12, 4);
		arm1.setPos(-6.0f, 12.0f, 0.0f);
		arm1.setTexSize(64, 64);
		arm1.mirror = true;
		setRotation(arm1, 0.0f, 0.0f, 0.0f);
		(arm2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 12, 4);
		arm2.setPos(6.0f, 12.0f, 0.0f);
		arm2.setTexSize(64, 64);
		arm2.mirror = true;
		setRotation(arm2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head2.setPos(0.0f, 1.0f, -4.0f);
		head2.setTexSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 43, 0)).addBox(-5.0f, -5.0f, -4.0f, 1, 3, 6);
		head3.setPos(0.0f, 1.0f, -4.0f);
		head3.setTexSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		arm1.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		arm2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body0.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		arm1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		arm2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}

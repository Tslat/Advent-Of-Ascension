package net.tslat.aoa3.client.model.entity.mob.lelyetia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ZhinxModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;
	private final ModelRenderer body7;
	private final ModelRenderer body8;
	private final ModelRenderer body9;
	private final ModelRenderer body10;
	private final ModelRenderer body11;
	private final ModelRenderer body12;
	private final ModelRenderer body13;
	private final ModelRenderer body14;
	private final ModelRenderer body15;
	private final ModelRenderer body16;
	private final ModelRenderer body17;
	private final ModelRenderer body18;

	public ZhinxModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-2.0f, -4.0f, -3.0f, 4, 6, 4);
		head.setPos(0.0f, 16.0f, -7.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 6, 34)).addBox(-3.0f, -3.0f, -5.0f, 6, 4, 1);
		body.setPos(0.0f, 16.0f, -1.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 2, 7, 2);
		leg1.setPos(-3.0f, 17.0f, 7.0f);
		leg1.setTexSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(0.0f, 0.0f, -2.0f, 2, 7, 2);
		leg2.setPos(3.0f, 17.0f, 7.0f);
		leg2.setTexSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -3.0f, 2, 7, 2);
		leg3.setPos(-3.0f, 17.0f, -2.0f);
		leg3.setTexSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(0.0f, 0.0f, -3.0f, 2, 7, 2);
		leg4.setPos(3.0f, 17.0f, -2.0f);
		leg4.setTexSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 6, 26)).addBox(1.0f, -5.0f, 9.0f, 1, 3, 3);
		body2.setPos(0.0f, 17.0f, -1.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.1745329f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 6, 43)).addBox(-5.0f, -3.0f, -4.0f, 10, 4, 12);
		body3.setPos(0.0f, 16.0f, -1.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 27, 30)).addBox(1.0f, -4.0f, -1.0f, 1, 1, 10);
		body4.setPos(0.0f, 17.0f, -1.0f);
		body4.setTexSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0.1745329f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 31, 34)).addBox(-4.0f, -4.0f, -1.0f, 1, 1, 6);
		body5.setPos(1.0f, 14.0f, -3.0f);
		body5.setTexSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 1.570796f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 6, 26)).addBox(-4.0f, -5.0f, 7.0f, 1, 3, 3);
		body6.setPos(1.0f, 16.0f, -3.0f);
		body6.setTexSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 1.570796f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 31, 34)).addBox(3.0f, -4.0f, -1.0f, 1, 1, 6);
		body7.setPos(-1.0f, 14.0f, -3.0f);
		body7.setTexSize(64, 64);
		body7.mirror = true;
		setRotation(body7, 1.570796f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 6, 26)).addBox(3.0f, -5.0f, 7.0f, 1, 3, 3);
		body8.setPos(-1.0f, 16.0f, -3.0f);
		body8.setTexSize(64, 64);
		body8.mirror = true;
		setRotation(body8, 1.570796f, 0.0f, 0.0f);
		(body9 = new ModelRenderer(this, 27, 30)).addBox(2.0f, -4.0f, -1.0f, 1, 1, 10);
		body9.setPos(0.0f, 17.0f, -1.0f);
		body9.setTexSize(64, 64);
		body9.mirror = true;
		setRotation(body9, 0.4363323f, 0.0f, 0.0f);
		(body10 = new ModelRenderer(this, 6, 26)).addBox(2.0f, -5.0f, 9.0f, 1, 3, 3);
		body10.setPos(0.0f, 17.0f, -1.0f);
		body10.setTexSize(64, 64);
		body10.mirror = true;
		setRotation(body10, 0.4363323f, 0.0f, 0.0f);
		(body11 = new ModelRenderer(this, 27, 30)).addBox(-3.0f, -4.0f, -1.0f, 1, 1, 10);
		body11.setPos(0.0f, 17.0f, -1.0f);
		body11.setTexSize(64, 64);
		body11.mirror = true;
		setRotation(body11, 0.4363323f, 0.0f, 0.0f);
		(body12 = new ModelRenderer(this, 6, 26)).addBox(-3.0f, -5.0f, 9.0f, 1, 3, 3);
		body12.setPos(0.0f, 17.0f, -1.0f);
		body12.setTexSize(64, 64);
		body12.mirror = true;
		setRotation(body12, 0.4363323f, 0.0f, 0.0f);
		(body13 = new ModelRenderer(this, 27, 30)).addBox(-2.0f, -4.0f, -1.0f, 1, 1, 10);
		body13.setPos(0.0f, 17.0f, -1.0f);
		body13.setTexSize(64, 64);
		body13.mirror = true;
		setRotation(body13, 0.1745329f, 0.0f, 0.0f);
		(body14 = new ModelRenderer(this, 6, 26)).addBox(-2.0f, -5.0f, 9.0f, 1, 3, 3);
		body14.setPos(0.0f, 17.0f, -1.0f);
		body14.setTexSize(64, 64);
		body14.mirror = true;
		setRotation(body14, 0.1745329f, 0.0f, 0.0f);
		(body15 = new ModelRenderer(this, 27, 30)).addBox(3.0f, -4.0f, -1.0f, 1, 1, 10);
		body15.setPos(0.0f, 16.0f, -1.0f);
		body15.setTexSize(64, 64);
		body15.mirror = true;
		setRotation(body15, 0.7853982f, 0.0f, 0.0f);
		(body16 = new ModelRenderer(this, 27, 30)).addBox(-4.0f, -4.0f, -1.0f, 1, 1, 10);
		body16.setPos(0.0f, 16.0f, -1.0f);
		body16.setTexSize(64, 64);
		body16.mirror = true;
		setRotation(body16, 0.7853982f, 0.0f, 0.0f);
		(body17 = new ModelRenderer(this, 6, 26)).addBox(-4.0f, -5.0f, 9.0f, 1, 3, 3);
		body17.setPos(0.0f, 16.0f, -1.0f);
		body17.setTexSize(64, 64);
		body17.mirror = true;
		setRotation(body17, 0.7853982f, 0.0f, 0.0f);
		(body18 = new ModelRenderer(this, 6, 26)).addBox(3.0f, -5.0f, 9.0f, 1, 3, 3);
		body18.setPos(0.0f, 16.0f, -1.0f);
		body18.setTexSize(64, 64);
		body18.mirror = true;
		setRotation(body18, 0.7853982f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body14.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body15.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body16.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body17.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body18.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1.yRot = 0.0f;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.yRot = 0.0f;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

package net.tslat.aoa3.client.model.entity.mob.creeponia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.entity.mob.creeponia.AoACreeponiaCreeper;

public class BoneCreeperModel extends EntityModel<AoACreeponiaCreeper> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
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

	public BoneCreeperModel() {
		this(0f);
	}

	public BoneCreeperModel(float delta) {
		texWidth = 64;
		texHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, delta);
		head.setPos(0.0f, 6.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 29, 20)).addBox(3.0f, 7.0f, -4.0f, 1, 1, 5, delta);
		body.setPos(0.0f, 6.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, delta);
		leg3.setPos(-2.0f, 18.0f, -4.0f);
		leg3.setTexSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, delta);
		leg4.setPos(2.0f, 18.0f, -4.0f);
		leg4.setTexSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(0.0f, 0.0f, -2.0f, 4, 6, 4, delta);
		leg1.setPos(-4.0f, 18.0f, 4.0f);
		leg1.setTexSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, delta);
		leg2.setPos(2.0f, 18.0f, 4.0f);
		leg2.setTexSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 16)).addBox(-1.0f, 0.0f, -2.0f, 2, 12, 4, delta);
		body2.setPos(0.0f, 6.0f, 0.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 29, 17)).addBox(-3.0f, 7.0f, 0.0f, 6, 1, 1, delta);
		body3.setPos(0.0f, 6.0f, 0.0f);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 29, 20)).addBox(-4.0f, 7.0f, -4.0f, 1, 1, 5, delta);
		body4.setPos(0.0f, 6.0f, 0.0f);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 29, 20)).addBox(3.0f, 1.0f, -4.0f, 1, 1, 5, delta);
		body5.setPos(0.0f, 6.0f, 0.0f);
		body5.setTexSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 29, 17)).addBox(-3.0f, 1.0f, 0.0f, 6, 1, 1, delta);
		body6.setPos(0.0f, 6.0f, 0.0f);
		body6.setTexSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 29, 20)).addBox(-4.0f, 1.0f, -4.0f, 1, 1, 5, delta);
		body7.setPos(0.0f, 6.0f, 0.0f);
		body7.setTexSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 29, 20)).addBox(3.0f, 3.0f, -4.0f, 1, 1, 5, delta);
		body8.setPos(0.0f, 6.0f, 0.0f);
		body8.setTexSize(64, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(body9 = new ModelRenderer(this, 29, 17)).addBox(-3.0f, 3.0f, 0.0f, 6, 1, 1, delta);
		body9.setPos(0.0f, 6.0f, 0.0f);
		body9.setTexSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0f, 0.0f, 0.0f);
		(body10 = new ModelRenderer(this, 29, 20)).addBox(-4.0f, 3.0f, -4.0f, 1, 1, 5, delta);
		body10.setPos(0.0f, 6.0f, 0.0f);
		body10.setTexSize(64, 32);
		body10.mirror = true;
		setRotation(body10, 0.0f, 0.0f, 0.0f);
		(body11 = new ModelRenderer(this, 29, 20)).addBox(3.0f, 5.0f, -4.0f, 1, 1, 5, delta);
		body11.setPos(0.0f, 6.0f, 0.0f);
		body11.setTexSize(64, 32);
		body11.mirror = true;
		setRotation(body11, 0.0f, 0.0f, 0.0f);
		(body12 = new ModelRenderer(this, 29, 17)).addBox(-3.0f, 5.0f, 0.0f, 6, 1, 1, delta);
		body12.setPos(0.0f, 6.0f, 0.0f);
		body12.setTexSize(64, 32);
		body12.mirror = true;
		setRotation(body12, 0.0f, 0.0f, 0.0f);
		(body13 = new ModelRenderer(this, 29, 20)).addBox(-4.0f, 5.0f, -4.0f, 1, 1, 5, delta);
		body13.setPos(0.0f, 6.0f, 0.0f);
		body13.setTexSize(64, 32);
		body13.mirror = true;
		setRotation(body13, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(AoACreeponiaCreeper entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 57.295776f;
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}

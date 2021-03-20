package net.tslat.aoa3.client.model.entity.mob.creeponia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.entity.mob.creeponia.AoACreeponiaCreeper;

public class WingedCreeperModel extends EntityModel<AoACreeponiaCreeper> {
	private final ModelRenderer head;
	private final ModelRenderer wingL1;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer body;
	private final ModelRenderer wingR1;
	private final ModelRenderer wingR2;
	private final ModelRenderer wingL2;

	public WingedCreeperModel(float delta) {
		texWidth = 64;
		texHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, delta);
		head.setPos(0.0f, 6.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(wingL1 = new ModelRenderer(this, 42, 28)).addBox(2.0f, 1.0f, 0.0f, 10, 8, 0, delta);
		wingL1.setPos(2.0f, 8.0f, 2.0f);
		wingL1.setTexSize(64, 32);
		wingL1.mirror = true;
		setRotation(wingL1, 0.0f, 0.0f, 0.0f);
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
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4, delta);
		body.setPos(0.0f, 6.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(wingR1 = new ModelRenderer(this, 42, 19)).addBox(-12.0f, 1.0f, 0.0f, 10, 8, 0, delta);
		wingR1.setPos(-2.0f, 8.0f, 2.0f);
		wingR1.setTexSize(64, 32);
		wingR1.mirror = true;
		setRotation(wingR1, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 34, 3)).addBox(-12.0f, -1.0f, -1.0f, 12, 2, 2, delta);
		wingR2.setPos(-2.0f, 8.0f, 2.0f);
		wingR2.setTexSize(64, 32);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 34, 8)).addBox(0.0f, -1.0f, -1.0f, 12, 2, 2, delta);
		wingL2.setPos(2.0f, 8.0f, 2.0f);
		wingL2.setTexSize(64, 32);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(AoACreeponiaCreeper entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 57.295776f;
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		wingR1.zRot = MathHelper.cos(ageInTicks * 0.4f) * 3.1415927f * 0.15f;
		wingL1.zRot = -wingR1.zRot;
		wingR2.zRot = MathHelper.cos(ageInTicks * 0.4f) * 3.1415927f * 0.15f;
		wingL2.zRot = -wingR1.zRot;
	}
}

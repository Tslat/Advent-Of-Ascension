package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class HorndronModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer body2;
	private final ModelRenderer leg4p2;
	private final ModelRenderer leg3p2;
	private final ModelRenderer leg2p2;
	private final ModelRenderer leg1p2;
	private final ModelRenderer body3;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer head6;

	public HorndronModel() {
		texWidth = 64;
		texHeight = 64;
		(head = new ModelRenderer(this, 0, 30)).addBox(-3.0f, -2.0f, -8.0f, 6, 5, 2);
		head.setPos(0.0f, 11.0f, -4.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 18, 26)).addBox(-8.0f, -10.0f, -14.0f, 16, 9, 7);
		body.setPos(0.0f, 18.0f, 10.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 26)).addBox(-4.0f, 6.0f, -3.0f, 6, 1, 1);
		leg1.setPos(-3.0f, 17.0f, 12.0f);
		leg1.setTexSize(64, 64);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 26)).addBox(-2.0f, 6.0f, -3.0f, 6, 1, 1);
		leg2.setPos(3.0f, 17.0f, 12.0f);
		leg2.setTexSize(64, 64);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 26)).addBox(-4.0f, 6.0f, -4.0f, 6, 1, 1);
		leg3.setPos(-3.0f, 17.0f, 0.0f);
		leg3.setTexSize(64, 64);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 26)).addBox(-2.0f, 6.0f, -4.0f, 6, 1, 1);
		leg4.setPos(3.0f, 17.0f, 0.0f);
		leg4.setTexSize(64, 64);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 24, 11)).addBox(-4.0f, -12.0f, -12.0f, 8, 2, 12);
		body2.setPos(0.0f, 18.0f, 10.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(leg4p2 = new ModelRenderer(this, 0, 14)).addBox(-2.0f, 0.0f, -3.0f, 6, 7, 4);
		leg4p2.setPos(3.0f, 17.0f, 0.0f);
		leg4p2.setTexSize(64, 64);
		leg4p2.mirror = true;
		setRotation(leg4p2, 0.0f, 0.0f, 0.0f);
		(leg3p2 = new ModelRenderer(this, 0, 14)).addBox(-4.0f, 0.0f, -3.0f, 6, 7, 4);
		leg3p2.setPos(-3.0f, 17.0f, 0.0f);
		leg3p2.setTexSize(64, 64);
		leg3p2.mirror = true;
		setRotation(leg3p2, 0.0f, 0.0f, 0.0f);
		(leg2p2 = new ModelRenderer(this, 0, 14)).addBox(-2.0f, 0.0f, -2.0f, 6, 7, 4);
		leg2p2.setPos(3.0f, 17.0f, 12.0f);
		leg2p2.setTexSize(64, 64);
		leg2p2.mirror = true;
		setRotation(leg2p2, 0.0f, 0.0f, 0.0f);
		(leg1p2 = new ModelRenderer(this, 0, 14)).addBox(-4.0f, 0.0f, -2.0f, 6, 7, 4);
		leg1p2.setPos(-3.0f, 17.0f, 12.0f);
		leg1p2.setTexSize(64, 64);
		leg1p2.mirror = true;
		setRotation(leg1p2, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 18, 44)).addBox(-6.0f, -10.0f, -7.0f, 12, 9, 11);
		body3.setPos(0.0f, 18.0f, 10.0f);
		body3.setTexSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 50, 0)).addBox(4.5f, -3.5f, -10.0f, 2, 2, 4);
		head2.setPos(0.0f, 11.0f, -4.0f);
		head2.setTexSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -6.0f, 8, 8, 6);
		head3.setPos(0.0f, 11.0f, -4.0f);
		head3.setTexSize(64, 64);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 50, 0)).addBox(-6.5f, -3.5f, -10.0f, 2, 2, 4);
		head4.setPos(0.0f, 11.0f, -4.0f);
		head4.setTexSize(64, 64);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 31, 0)).addBox(4.0f, -4.0f, -6.0f, 3, 3, 5);
		head5.setPos(0.0f, 11.0f, -4.0f);
		head5.setTexSize(64, 64);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 31, 0)).addBox(-7.0f, -4.0f, -6.0f, 3, 3, 5);
		head6.setPos(0.0f, 11.0f, -4.0f);
		head6.setTexSize(64, 64);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1p2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2p2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3p2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4p2.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
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
		leg4p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}

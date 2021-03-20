package net.tslat.aoa3.client.model.entity.mob.barathos;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class RamradonModel extends EntityModel<MobEntity> {
	private final ModelRenderer body;
	private final ModelRenderer head2;
	private final ModelRenderer head7;
	private final ModelRenderer head5;
	private final ModelRenderer head3;
	private final ModelRenderer head6;
	private final ModelRenderer head4;
	private final ModelRenderer head;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;

	public RamradonModel() {
		texWidth = 128;
		texHeight = 32;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 16.0F, 1.0F);
		body.texOffs(32, 19).addBox(-5.0F, -7.0F, 3.0F, 10, 5, 8, 0.0F, true);

		head2 = new ModelRenderer(this);
		head2.setPos(0.0F, -4.0F, -8.0F);
		body.addChild(head2);
		head2.texOffs(0, 0).addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F, true);

		head7 = new ModelRenderer(this);
		head7.setPos(0.0F, 0.0F, 0.0F);
		head2.addChild(head7);
		setRotation(head7, 0.5236F, 0.0F, 0.0F);
		head7.texOffs(68, 21).addBox(4.0F, -6.0F, -4.0F, 5, 5, 6, 0.0F, true);
		head7.texOffs(84, 16).addBox(4.5F, -5.5F, 1.0F, 4, 4, 6, 0.0F, true);

		head5 = new ModelRenderer(this);
		head5.setPos(0.0F, 0.0F, 0.0F);
		head7.addChild(head5);
		setRotation(head5, -0.4363F, 0.0F, 0.0F);
		head5.texOffs(85, 17).addBox(5.0F, -7.5F, 4.0F, 3, 3, 6, 0.0F, true);

		head3 = new ModelRenderer(this);
		head3.setPos(0.0F, 0.0F, 0.0F);
		head5.addChild(head3);
		setRotation(head3, -0.0873F, 0.0F, 0.0F);
		head3.texOffs(86, 16).addBox(5.5F, -8.0F, 8.0F, 2, 2, 6, 0.0F, true);

		head6 = new ModelRenderer(this);
		head6.setPos(0.0F, 0.0F, 0.0F);
		head2.addChild(head6);
		setRotation(head6, 0.5236F, 0.0F, 0.0F);
		head6.texOffs(68, 21).addBox(-9.0F, -6.0F, -4.0F, 5, 5, 6, 0.0F, false);
		head6.texOffs(84, 16).addBox(-8.5F, -5.5F, 1.0F, 4, 4, 6, 0.0F, false);

		head4 = new ModelRenderer(this);
		head4.setPos(0.0F, 0.0F, 0.0F);
		head6.addChild(head4);
		setRotation(head4, -0.4363F, 0.0F, 0.0F);
		head4.texOffs(85, 17).addBox(-8.0F, -7.5F, 4.0F, 3, 3, 6, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 0.0F, 0.0F);
		head4.addChild(head);
		setRotation(head, -0.0873F, 0.0F, 0.0F);
		head.texOffs(86, 16).addBox(-7.5F, -8.0F, 8.0F, 2, 2, 6, 0.0F, false);

		leg1 = new ModelRenderer(this);
		leg1.setPos(6.0F, -2.0F, 14.0F);
		body.addChild(leg1);
		leg1.texOffs(16, 18).addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F, true);

		leg2 = new ModelRenderer(this);
		leg2.setPos(-6.0F, -2.0F, 14.0F);
		body.addChild(leg2);
		leg2.texOffs(16, 18).addBox(-2.0F, 0.0F, -2.0F, 4, 10, 4, 0.0F, false);

		leg3 = new ModelRenderer(this);
		leg3.setPos(4.0F, 0.0F, -3.0F);
		body.addChild(leg3);
		leg3.texOffs(0, 16).addBox(-2.0F, 0.0F, -3.0F, 4, 8, 4, 0.0F, true);

		leg4 = new ModelRenderer(this);
		leg4.setPos(-4.0F, 0.0F, -3.0F);
		body.addChild(leg4);
		leg4.texOffs(0, 16).addBox(-2.0F, 0.0F, -3.0F, 4, 8, 4, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(body2);
		setRotation(body2, 0.3718F, 0.0F, 0.0F);
		body2.texOffs(68, 0).addBox(-7.0F, -8.0F, -7.0F, 14, 8, 8, 0.0F, true);

		body3 = new ModelRenderer(this);
		body3.setPos(0.0F, 0.0F, 12.0F);
		body.addChild(body3);
		setRotation(body3, -0.11F, 0.0F, 0.0F);
		body3.texOffs(28, 0).addBox(-6.0F, -7.0F, -3.0F, 12, 6, 8, 0.0F, true);

		body4 = new ModelRenderer(this);
		body4.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(body4);
		setRotation(body4, 0.1115F, 0.0F, 0.0F);
		body4.texOffs(70, 1).addBox(-6.0F, -7.0F, -3.0F, 12, 6, 8, 0.0F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}

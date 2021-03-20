package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class BugeyeModel extends EntityModel<MobEntity> {
	private final ModelRenderer root;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg5;
	private final ModelRenderer leg6;
	private final ModelRenderer body;
	private final ModelRenderer head;

	public BugeyeModel() {
		texWidth = 64;
		texHeight = 64;

		root = new ModelRenderer(this);
		root.setPos(0.0F, 24.0F, 0.0F);

		leg1 = new ModelRenderer(this);
		leg1.setPos(8.0F, -12.0F, 7.5F);
		root.addChild(leg1);
		leg1.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setPos(-8.0F, -12.0F, 7.5F);
		root.addChild(leg2);
		leg2.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true);

		leg3 = new ModelRenderer(this);
		leg3.setPos(8.0F, -12.0F, 1.0F);
		root.addChild(leg3);
		leg3.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

		leg4 = new ModelRenderer(this);
		leg4.setPos(-8.0F, -12.0F, 1.0F);
		root.addChild(leg4);
		leg4.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true);

		leg5 = new ModelRenderer(this);
		leg5.setPos(8.0F, -12.0F, -5.5F);
		root.addChild(leg5);
		leg5.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

		leg6 = new ModelRenderer(this);
		leg6.setPos(-8.0F, -12.0F, -5.5F);
		root.addChild(leg6);
		leg6.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -13.0F, 2.0F);
		setRotation(body, 1.5708F, 0.0F, 0.0F);
		root.addChild(body);
		body.texOffs(1, 35).addBox(-8.0F, -10.0F, -7.0F, 16, 18, 9, 0.0F, true);
		body.texOffs(35, 11).addBox(-5.0F, -10.0F, 2.0F, 10, 18, 3, 0.0F, true);

		head = new ModelRenderer(this);
		head.setPos(1.0F, 2.0F, -10.0F);
		setRotation(head, -1.5708F, 0.0F, 0.0F);
		body.addChild(head);
		head.texOffs(0, 0).addBox(-6.0F, -13.0F, -15.0F, 10, 10, 3, 0.0F, true);
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1.yRot = 0.0f;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.yRot = 0.0f;
		leg5.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg5.yRot = 0.0f;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg6.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}

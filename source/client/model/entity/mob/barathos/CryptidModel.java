package net.tslat.aoa3.client.model.entity.mob.barathos;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class CryptidModel extends EntityModel<MobEntity> {
	private final ModelRenderer root;
	private final ModelRenderer head;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer body2;
	private final ModelRenderer body6;
	private final ModelRenderer body5;
	private final ModelRenderer tail;
	private final ModelRenderer body3;

	public CryptidModel() {
		texWidth = 128;
		texHeight = 64;

		root = new ModelRenderer(this);
		root.setPos(0.0F, 24.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setPos(0.0F, -11.0F, -10.0F);
		root.addChild(head);
		head.texOffs(0, 0).addBox(-4.0F, 4.0F, -11.0F, 8, 3, 11, 0.0F, true);
		head.texOffs(40, 12).addBox(-5.0F, -6.0F, -12.0F, 10, 2, 12, 0.0F, false);
		head.texOffs(0, 14).addBox(-4.0F, -4.0F, -12.0F, 8, 8, 12, 0.0F, true);

		leg1 = new ModelRenderer(this);
		leg1.setPos(7.0F, -12.0F, 7.0F);
		root.addChild(leg1);
		leg1.texOffs(28, 21).addBox(-3.0F, 10.0F, -5.0F, 2, 2, 3, 0.0F, true);
		leg1.texOffs(0, 44).addBox(-3.0F, 0.0F, -2.0F, 6, 12, 6, 0.0F, false);
		leg1.texOffs(28, 21).addBox(1.0F, 10.0F, -5.0F, 2, 2, 3, 0.0F, false);

		leg2 = new ModelRenderer(this);
		leg2.setPos(-7.0F, -12.0F, 7.0F);
		root.addChild(leg2);
		leg2.texOffs(28, 21).addBox(-3.0F, 10.0F, -5.0F, 2, 2, 3, 0.0F, true);
		leg2.texOffs(0, 44).addBox(-3.0F, 0.0F, -2.0F, 6, 12, 6, 0.0F, true);
		leg2.texOffs(28, 21).addBox(1.0F, 10.0F, -5.0F, 2, 2, 3, 0.0F, false);

		leg3 = new ModelRenderer(this);
		leg3.setPos(7.0F, -12.0F, -5.0F);
		root.addChild(leg3);
		leg3.texOffs(28, 21).addBox(-3.0F, 10.0F, -6.0F, 2, 2, 3, 0.0F, true);
		leg3.texOffs(0, 44).addBox(-3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F, false);
		leg3.texOffs(28, 21).addBox(1.0F, 10.0F, -6.0F, 2, 2, 3, 0.0F, false);

		leg4 = new ModelRenderer(this);
		leg4.setPos(-7.0F, -12.0F, -5.0F);
		root.addChild(leg4);
		leg4.texOffs(28, 21).addBox(-3.0F, 10.0F, -6.0F, 2, 2, 3, 0.0F, true);
		leg4.texOffs(0, 44).addBox(-3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F, true);
		leg4.texOffs(28, 21).addBox(1.0F, 10.0F, -6.0F, 2, 2, 3, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setPos(0.0F, 0.0F, 0.0F);
		root.addChild(body2);
		body2.texOffs(34, 36).addBox(-6.0F, -13.0F, -10.0F, 12, 6, 22, 0.0F, true);

		body6 = new ModelRenderer(this);
		body6.setPos(-1.0F, -20.0F, -1.0F);
		body2.addChild(body6);
		setRotation(body6, 1.9199F, 0.0F, 0.0F);
		body6.texOffs(52, 12).addBox(-4.0F, -10.0F, -4.0F, 10, 18, 2, 0.0F, true);

		body5 = new ModelRenderer(this);
		body5.setPos(0.0F, -17.0F, 0.0F);
		body2.addChild(body5);
		setRotation(body5, 1.5708F, 0.0F, 0.0F);
		body5.texOffs(52, 12).addBox(-7.0F, -10.0F, -4.0F, 14, 22, 2, 0.0F, true);

		tail = new ModelRenderer(this);
		tail.setPos(0.0F, -11.0F, 10.0F);
		body2.addChild(tail);
		setRotation(tail, 1.1345F, 0.0F, 0.0F);
		tail.texOffs(32, 44).addBox(-4.0F, 0.0F, -2.0F, 8, 10, 4, 0.0F, true);
		tail.texOffs(52, 24).addBox(-5.0F, 0.0F, 2.0F, 10, 10, 2, 0.0F, true);

		body3 = new ModelRenderer(this);
		body3.setPos(-1.0F, -19.0F, 8.0F);
		body2.addChild(body3);
		setRotation(body3, 1.7977F, 0.0F, 0.0F);
		body3.texOffs(52, 19).addBox(-4.0F, -10.0F, -4.0F, 10, 15, 2, 0.0F, false);
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

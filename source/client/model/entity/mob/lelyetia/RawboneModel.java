package net.tslat.aoa3.client.model.entity.mob.lelyetia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class RawboneModel extends EntityModel<MobEntity> {
	private final ModelRenderer root;
	private final ModelRenderer Shape2;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer neck;
	private final ModelRenderer head2;
	private final ModelRenderer Shape12;
	private final ModelRenderer Shape1;

	public RawboneModel() {
		texWidth = 64;
		texHeight = 64;

		root = new ModelRenderer(this);
		root.setPos(0.0F, 24.0F, 0.0F);


		Shape2 = new ModelRenderer(this);
		Shape2.setPos(-2.0F, -10.0F, -6.0F);
		root.addChild(Shape2);
		Shape2.texOffs(0, 58).addBox(0.0F, -7.0F, 8.0F, 4, 2, 2, 0.0F, true);
		Shape2.texOffs(0, 33).addBox(4.0F, -7.0F, 6.0F, 2, 2, 6, 0.0F, true);
		Shape2.texOffs(0, 33).addBox(-2.0F, -7.0F, 6.0F, 2, 2, 6, 0.0F, true);

		leg1 = new ModelRenderer(this);
		leg1.setPos(6.0F, -2.0F, 18.0F);
		Shape2.addChild(leg1);
		leg1.texOffs(10, 16).addBox(-1.0F, 9.0F, -2.0F, 4, 3, 4, 0.0F, true);
		leg1.texOffs(0, 16).addBox(0.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F, true);

		leg2 = new ModelRenderer(this);
		leg2.setPos(-3.0F, -2.0F, 18.0F);
		Shape2.addChild(leg2);
		leg2.texOffs(10, 16).addBox(-2.0F, 9.0F, -2.0F, 4, 3, 4, 0.0F, true);
		leg2.texOffs(0, 16).addBox(-1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F, true);

		leg3 = new ModelRenderer(this);
		leg3.setPos(6.0F, -2.0F, 1.0F);
		Shape2.addChild(leg3);
		leg3.texOffs(10, 16).addBox(-1.0F, 9.0F, -3.0F, 4, 3, 4, 0.0F, true);
		leg3.texOffs(0, 16).addBox(0.0F, 0.0F, -2.0F, 2, 9, 2, 0.0F, true);

		leg4 = new ModelRenderer(this);
		leg4.setPos(-3.0F, -2.0F, 1.0F);
		Shape2.addChild(leg4);
		leg4.texOffs(10, 16).addBox(-2.0F, 9.0F, -3.0F, 4, 3, 4, 0.0F, true);
		leg4.texOffs(0, 16).addBox(-1.0F, 0.0F, -2.0F, 2, 9, 2, 0.0F, true);

		neck = new ModelRenderer(this);
		neck.setPos(2.0F, -3.0F, 3.0F);
		Shape2.addChild(neck);
		setRotation(neck, -0.5236F, 0.0F, 0.0F);
		neck.texOffs(19, 33).addBox(-1.0F, 0.0F, -5.0F, 2, 2, 5, 0.0F, true);
		neck.texOffs(0, 42).addBox(-2.0F, -1.0F, -9.0F, 4, 4, 4, 0.0F, true);

		head2 = new ModelRenderer(this);
		head2.setPos(0.0F, 0.2321F, -8.5981F);
		neck.addChild(head2);
		setRotation(head2, 0.5236F, 0.0F, 0.0F);
		head2.texOffs(0, 0).addBox(-4.0F, -4.0F, -3.0F, 8, 8, 3, 0.0F, true);
		head2.texOffs(24, 0).addBox(2.0F, -6.0F, -1.0F, 5, 7, 2, 0.0F, true);
		head2.texOffs(40, 0).addBox(-7.0F, -6.0F, -1.0F, 5, 7, 2, 0.0F, true);

		Shape12 = new ModelRenderer(this);
		Shape12.setPos(2.0F, -7.0F, 12.0F);
		Shape2.addChild(Shape12);
		setRotation(Shape12, -0.5236F, 0.0F, 0.0F);
		Shape12.texOffs(0, 42).addBox(-5.0F, -0.1699F, -0.4378F, 4, 4, 4, 0.0F, true);
		Shape12.texOffs(0, 42).addBox(1.0F, -0.1699F, -0.4378F, 4, 4, 4, 0.0F, true);
		Shape12.texOffs(0, 58).addBox(-2.0F, 0.8301F, 0.5622F, 4, 2, 2, 0.0F, true);
		Shape12.texOffs(0, 33).addBox(2.0F, 0.8301F, 3.5622F, 2, 2, 6, 0.0F, true);
		Shape12.texOffs(0, 58).addBox(-2.0F, 0.8301F, 5.5622F, 4, 2, 2, 0.0F, true);
		Shape12.texOffs(0, 33).addBox(-4.0F, 0.8301F, 3.5622F, 2, 2, 6, 0.0F, true);

		Shape1 = new ModelRenderer(this);
		Shape1.setPos(2.0F, -7.0F, 6.0F);
		Shape2.addChild(Shape1);
		setRotation(Shape1, -2.618F, 0.0F, 0.0F);
		Shape1.texOffs(0, 42).addBox(1.0F, -4.0622F, -0.3038F, 4, 4, 4, 0.0F, true);
		Shape1.texOffs(0, 42).addBox(-5.0F, -4.0622F, -0.3038F, 4, 4, 4, 0.0F, false);
		Shape1.texOffs(0, 33).addBox(2.0F, -3.0622F, 3.6962F, 2, 2, 6, 0.0F, true);
		Shape1.texOffs(0, 33).addBox(-4.0F, -3.0622F, 3.6962F, 2, 2, 6, 0.0F, false);
		Shape1.texOffs(0, 58).addBox(-2.0F, -3.0622F, 0.6962F, 4, 2, 2, 0.0F, true);
		Shape1.texOffs(0, 52).addBox(-2.0F, -3.0622F, 4.6962F, 4, 2, 3, 0.0F, true);
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

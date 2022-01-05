package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.object.entity.boss.HiveKingEntity;

public class HiveKingModel extends EntityModel<HiveKingEntity> {
	private final ModelRenderer root;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;

	public HiveKingModel() {
		texWidth = 256;
		texHeight = 32;

		root = new ModelRenderer(this);
		root.setPos(0.0F, 24.0F, 0.0F);


		leg1 = new ModelRenderer(this);
		leg1.setPos(10.0F, -12.0F, 18.0F);
		root.addChild(leg1);
		leg1.texOffs(0, 14).addBox(-2.0F, 0.0F, -2.0F, 6, 12, 6, 0.0F, true);

		leg2 = new ModelRenderer(this);
		leg2.setPos(-10.0F, -12.0F, 19.0F);
		root.addChild(leg2);
		leg2.texOffs(0, 14).addBox(-3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F, false);

		leg3 = new ModelRenderer(this);
		leg3.setPos(12.0F, -17.0F, 1.0F);
		root.addChild(leg3);
		leg3.texOffs(31, 8).addBox(-3.0F, 0.0F, -3.0F, 6, 17, 6, 0.0F, true);

		leg4 = new ModelRenderer(this);
		leg4.setPos(-12.0F, -17.0F, 1.0F);
		root.addChild(leg4);
		leg4.texOffs(31, 8).addBox(-3.0F, 0.0F, -3.0F, 6, 17, 6, 0.0F, false);

		ModelRenderer head13 = new ModelRenderer(this);
		head13.setPos(0.0F, -18.0F, 0.0F);
		root.addChild(head13);
		head13.texOffs(89, 16).addBox(-8.0F, -4.0F, -3.0F, 16, 12, 4, 0.0F, true);
		head13.texOffs(67, 12).addBox(2.0F, -6.0F, -3.0F, 5, 2, 4, 0.0F, false);
		head13.texOffs(67, 12).addBox(-7.0F, -6.0F, -3.0F, 5, 2, 4, 0.0F, true);
		head13.texOffs(58, 18).addBox(8.0F, -2.0F, -3.0F, 2, 2, 4, 0.0F, false);
		head13.texOffs(58, 18).addBox(-10.0F, -2.0F, -3.0F, 2, 2, 4, 0.0F, true);
		head13.texOffs(35, 1).addBox(-3.0F, -4.0F, -4.0F, 6, 5, 1, 0.0F, true);
		head13.texOffs(35, 1).addBox(2.0F, 2.0F, -4.0F, 6, 5, 1, 0.0F, true);
		head13.texOffs(35, 1).addBox(-8.0F, 2.0F, -4.0F, 6, 5, 1, 0.0F, false);

		ModelRenderer head2 = new ModelRenderer(this);
		head2.setPos(-4.0F, 4.0F, 0.0F);
		head13.addChild(head2);
		setRotation(head2, -0.4363F, 0.0F, -2.2689F);
		head2.texOffs(0, 0).addBox(-2.0F, -2.0F, -9.0F, 4, 4, 9, 0.0F, true);

		ModelRenderer head3 = new ModelRenderer(this);
		head3.setPos(0.0F, 0.0F, 0.0F);
		head2.addChild(head3);
		setRotation(head3, 0.8727F, 0.0F, 0.0F);
		head3.texOffs(60, 0).addBox(-1.0F, -7.0F, -14.0F, 2, 2, 9, 0.0F, true);

		ModelRenderer head6 = new ModelRenderer(this);
		head6.setPos(4.0F, 4.0F, 0.0F);
		head13.addChild(head6);
		setRotation(head6, -0.4363F, 0.0F, 2.2689F);
		head6.texOffs(0, 0).addBox(-2.0F, -2.0F, -9.0F, 4, 4, 9, 0.0F, true);

		ModelRenderer head5 = new ModelRenderer(this);
		head5.setPos(0.0F, 0.0F, 0.0F);
		head6.addChild(head5);
		setRotation(head5, 0.8727F, 0.0F, 0.0F);
		head5.texOffs(60, 0).addBox(-1.0F, -7.0F, -14.0F, 2, 2, 9, 0.0F, true);

		ModelRenderer head8 = new ModelRenderer(this);
		head8.setPos(0.0F, 0.0F, 0.0F);
		head13.addChild(head8);
		setRotation(head8, -0.4363F, 0.0F, 0.0F);
		head8.texOffs(0, 0).addBox(-2.0F, -2.0F, -9.0F, 4, 4, 9, 0.0F, true);

		ModelRenderer head4 = new ModelRenderer(this);
		head4.setPos(0.0F, 0.0F, 0.0F);
		head8.addChild(head4);
		setRotation(head4, 0.8727F, 0.0F, 0.0F);
		head4.texOffs(60, 0).addBox(-1.0F, -7.0F, -14.0F, 2, 2, 9, 0.0F, true);

		ModelRenderer body17 = new ModelRenderer(this);
		body17.setPos(0.0F, -10.0F, 8.0F);
		root.addChild(body17);
		setRotation(body17, -0.1745F, 0.0F, 0.0F);
		body17.texOffs(92, 0).addBox(-9.0F, -6.0F, -9.0F, 18, 6, 4, 0.0F, true);
		body17.texOffs(136, 9).addBox(-8.0F, -4.0F, -7.0F, 16, 4, 18, 0.0F, true);
		body17.texOffs(136, 9).addBox(-7.0F, -8.0F, -9.0F, 14, 2, 20, 0.0F, true);
		body17.texOffs(131, 15).addBox(-7.0F, -6.0F, 6.0F, 5, 2, 5, 0.0F, true);
		body17.texOffs(131, 15).addBox(2.0F, -6.0F, 6.0F, 5, 2, 5, 0.0F, false);

		ModelRenderer body21 = new ModelRenderer(this);
		body21.setPos(0.0F, 0.0F, 0.0F);
		body17.addChild(body21);
		setRotation(body21, 0.3491F, 0.0F, 0.0F);
		body21.texOffs(207, 7).addBox(-3.0F, -8.0F, 0.0F, 2, 5, 20, 0.0F, false);
		body21.texOffs(207, 7).addBox(1.0F, -8.0F, 0.0F, 2, 5, 20, 0.0F, true);

		ModelRenderer body18 = new ModelRenderer(this);
		body18.setPos(0.0F, 0.0F, 0.0F);
		body17.addChild(body18);
		setRotation(body18, 0.5236F, 0.0F, 0.0F);
		body18.texOffs(207, 7).addBox(-6.0F, -13.0F, -4.0F, 2, 5, 20, 0.0F, false);
		body18.texOffs(207, 7).addBox(4.0F, -13.0F, -4.0F, 2, 5, 20, 0.0F, true);
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
	public void setupAnim(HiveKingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}

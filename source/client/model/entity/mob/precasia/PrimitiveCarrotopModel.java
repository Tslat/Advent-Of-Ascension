package net.tslat.aoa3.client.model.entity.mob.precasia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class PrimitiveCarrotopModel extends EntityModel<MobEntity> {
	private final ModelRenderer root;
	private final ModelRenderer rightleg;
	private final ModelRenderer leftleg;
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone7;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone6;
	private final ModelRenderer bone8;
	private final ModelRenderer bone3;

	public PrimitiveCarrotopModel() {
		texWidth = 64;
		texHeight = 32;

		root = new ModelRenderer(this);
		root.setPos(0.0F, 24.0F, 0.0F);

		rightleg = new ModelRenderer(this);
		rightleg.setPos(4.0F, -16.0F, 0.0F);
		root.addChild(rightleg);
		rightleg.texOffs(19, 16).addBox(-1.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, true);
		rightleg.texOffs(0, 16).addBox(0.0F, 2.0F, -1.0F, 2, 14, 2, 0.0F, true);
		rightleg.texOffs(0, 14).addBox(-1.0F, 12.1333F, -2.0F, 4, 4, 4, 0.0F, true);

		leftleg = new ModelRenderer(this);
		leftleg.setPos(-4.0F, -16.0F, 0.0F);
		root.addChild(leftleg);
		leftleg.texOffs(19, 16).addBox(-3.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, true);
		leftleg.texOffs(0, 16).addBox(-2.0F, 2.0F, -1.0F, 2, 14, 2, 0.0F, true);
		leftleg.texOffs(0, 14).addBox(-3.0F, 12.1333F, -2.0F, 4, 4, 4, 0.0F, true);

		body = new ModelRenderer(this);
		body.setPos(0.0F, -20.0F, 0.0F);
		root.addChild(body);
		body.texOffs(16, 16).addBox(-1.5F, -13.0F, -1.5F, 3, 3, 3, 0.0F, true);
		body.texOffs(0, 6).addBox(-3.0F, 10.0F, -1.0F, 2, 2, 2, 0.0F, true);
		body.texOffs(10, 16).addBox(-3.5F, -3.0F, -3.5F, 7, 3, 7, 0.0F, true);
		body.texOffs(16, 16).addBox(-1.0F, -0.15F, -4.499F, 2, 2, 1, 0.0F, true);
		body.texOffs(10, 16).addBox(-3.0F, -5.5F, -3.0F, 6, 3, 6, 0.0F, true);
		body.texOffs(16, 16).addBox(-1.0F, -16.0F, -1.0F, 2, 3, 2, 0.0F, true);
		body.texOffs(16, 16).addBox(-2.5F, -8.0F, -2.5F, 5, 3, 5, 0.0F, true);
		body.texOffs(16, 16).addBox(-2.0F, -10.5F, -2.0F, 4, 3, 4, 0.0F, true);
		body.texOffs(8, 27).addBox(-4.0F, 4.0F, 1.0F, 8, 2, 3, 0.0F, true);
		body.texOffs(0, 6).addBox(-1.0F, 8.0F, -1.0F, 2, 6, 2, 0.0F, true);
		body.texOffs(0, 6).addBox(1.0F, 10.0F, -1.0F, 2, 2, 2, 0.0F, true);
		body.texOffs(16, 0).addBox(-4.0F, 0.0F, -4.0F, 8, 4, 8, 0.0F, true);
		body.texOffs(34, 24).addBox(-4.0F, 6.0F, -2.0F, 8, 2, 6, 0.0F, true);

		bone = new ModelRenderer(this);
		bone.setPos(0.0F, 20.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, -0.3491F);
		body.addChild(bone);
		bone.texOffs(16, 16).addBox(7.1474F, -18.7134F, -4.5F, 3, 2, 3, 0.0F, true);

		bone2 = new ModelRenderer(this);
		bone2.setPos(0.0F, 20.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.3491F);
		body.addChild(bone2);
		bone2.texOffs(16, 16).addBox(-10.1474F, -18.7134F, -4.5F, 3, 2, 3, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setPos(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone4, 0.4363F, -0.0873F, -0.5236F);
		body.addChild(bone4);
		bone4.texOffs(16, 16).addBox(-1.0F, -5.25F, -1.0F, 1, 3, 1, 0.0F, true);

		bone5 = new ModelRenderer(this);
		bone5.setPos(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone5, -0.5236F, -0.0873F, 0.4363F);
		body.addChild(bone5);
		bone5.texOffs(16, 16).addBox(0.5F, -5.75F, -0.25F, 1, 4, 1, 0.0F, true);

		bone7 = new ModelRenderer(this);
		bone7.setPos(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone7, 0.6109F, -0.0873F, 0.0F);
		body.addChild(bone7);
		bone7.texOffs(16, 16).addBox(0.5F, -3.25F, -2.75F, 1, 3, 1, 0.0F, true);

		bone9 = new ModelRenderer(this);
		bone9.setPos(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone9, 0.1745F, -0.8727F, 0.0F);
		body.addChild(bone9);
		bone9.texOffs(16, 16).addBox(0.5F, -8.25F, -0.5F, 1, 4, 1, 0.0F, true);

		bone10 = new ModelRenderer(this);
		bone10.setPos(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone10, -0.2618F, 0.0F, -0.3491F);
		body.addChild(bone10);
		bone10.texOffs(16, 16).addBox(0.5F, -8.25F, -0.5F, 1, 4, 1, 0.0F, true);

		bone6 = new ModelRenderer(this);
		bone6.setPos(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone6, -0.0873F, -0.0873F, 0.3491F);
		body.addChild(bone6);
		bone6.texOffs(16, 16).addBox(2.5F, -2.0F, -1.45F, 1, 4, 1, 0.0F, true);

		bone8 = new ModelRenderer(this);
		bone8.setPos(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone8, -0.0873F, 0.0873F, -0.3491F);
		body.addChild(bone8);
		bone8.texOffs(16, 16).addBox(-3.5F, -2.25F, 0.8F, 1, 4, 1, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setPos(0.0F, 3.7274F, 1.4989F);
		setRotationAngle(bone3, 0.0873F, 0.0F, 0.0F);
		body.addChild(bone3);
		bone3.texOffs(36, 12).addBox(-3.999F, -0.0823F, -6.3399F, 8, 4, 6, 0.0F, true);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		root.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	private void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightleg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftleg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

package net.tslat.aoa3.client.model.entity.mob.runandor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ArielModel extends EntityModel<MobEntity> {
	private final ModelRenderer root;
	private final ModelRenderer body;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightleg;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightarm2;
	private final ModelRenderer head;
	private final ModelRenderer wing;
	private final ModelRenderer body4;
	private final ModelRenderer body7;
	private final ModelRenderer wing2;
	private final ModelRenderer body2;
	private final ModelRenderer body3;

	public ArielModel() {
		texWidth = 64;
		texHeight = 64;

		root = new ModelRenderer(this);
		root.setPos(0.0F, 29.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setPos(-7.0F, -28.0F, 5.0F);
		root.addChild(body);
		body.texOffs(16, 16).addBox(3.0F, -2.0F, -7.0F, 8, 13, 4, 0.0F, true);
		body.texOffs(42, 5).addBox(11.0F, -3.0F, -8.0F, 4, 3, 6, 0.0F, true);
		body.texOffs(42, 5).addBox(-1.0F, -3.0F, -8.0F, 4, 3, 6, 0.0F, false);

		leftarm = new ModelRenderer(this);
		leftarm.setPos(3.0F, -1.0F, -5.0F);
		body.addChild(leftarm);
		leftarm.texOffs(41, 16).addBox(-3.0F, 0.0F, -2.0F, 3, 12, 4, 0.0F, false);

		rightleg = new ModelRenderer(this);
		rightleg.setPos(9.0F, 11.0F, -5.0F);
		body.addChild(rightleg);
		rightleg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false);

		leftleg = new ModelRenderer(this);
		leftleg.setPos(5.0F, 11.0F, -5.0F);
		body.addChild(leftleg);
		leftleg.texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true);

		rightarm2 = new ModelRenderer(this);
		rightarm2.setPos(11.0F, -1.0F, -5.0F);
		body.addChild(rightarm2);
		rightarm2.texOffs(41, 16).addBox(0.0F, 0.0F, -2.0F, 3, 12, 4, 0.0F, true);

		head = new ModelRenderer(this);
		head.setPos(7.0F, -2.0F, -5.0F);
		body.addChild(head);
		head.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, true);

		wing = new ModelRenderer(this);
		wing.setPos(-2.0F, -28.0F, 0.0F);
		setRotation(wing, 0.0F, 0.5236F, 0.0F);
		root.addChild(wing);
		wing.texOffs(0, 34).addBox(2.0F, 0.0F, 3.0F, 2, 3, 3, 0.0F, true);

		body4 = new ModelRenderer(this);
		body4.setPos(3.0F, 3.0F, 6.0F);
		setRotation(body4, 0.0F, -0.5236F, -1.5708F);
		wing.addChild(body4);
		body4.texOffs(8, 41).addBox(0.0F, -1.0F, -2.0F, 9, 2, 2, -0.1F, true);

		body7 = new ModelRenderer(this);
		body7.setPos(8.5F, 0.4F, -1.65F);
		setRotation(body7, 1.5708F, -1.0472F, 0.0F);
		body4.addChild(body7);
		body7.texOffs(23, 35).addBox(0.0835F, -0.3714F, -0.7F, 10, 2, 2, 0.1F, true);
		body7.texOffs(0, 45).addBox(0.0835F, 1.6286F, 0.3F, 10, 18, 0, 0.0F, true);
		body7.texOffs(47, 37).addBox(0.0835F, 19.6286F, 0.3F, 6, 10, 0, 0.0F, true);

		wing2 = new ModelRenderer(this);
		wing2.setPos(2.0F, -28.0F, 0.0F);
		setRotation(wing2, 0.0F, -0.5236F, 0.0F);
		root.addChild(wing2);
		wing2.texOffs(0, 34).addBox(-4.0F, 0.0F, 3.0F, 2, 3, 3, 0.0F, false);

		body2 = new ModelRenderer(this);
		body2.setPos(-3.0F, 3.0F, 6.0F);
		setRotation(body2, 0.0F, 0.5236F, 1.5708F);
		wing2.addChild(body2);
		body2.texOffs(8, 41).addBox(-9.0F, -1.0F, -2.0F, 9, 2, 2, -0.1F, false);

		body3 = new ModelRenderer(this);
		body3.setPos(-8.5F, 0.4F, -1.65F);
		setRotation(body3, 1.5708F, 1.0472F, 0.0F);
		body2.addChild(body3);
		body3.texOffs(23, 35).addBox(-10.0835F, -0.3714F, -0.7F, 10, 2, 2, 0.1F, false);
		body3.texOffs(0, 45).addBox(-10.0835F, 1.6286F, 0.3F, 10, 18, 0, 0.0F, false);
		body3.texOffs(47, 37).addBox(-6.0835F, 19.6286F, 0.3F, 6, 10, 0, 0.0F, false);
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
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		rightarm2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		rightarm2.zRot = 0.0f;
		leftarm.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		leftarm.zRot = 0.0f;
		rightleg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightleg.yRot = 0.0f;
		leftleg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

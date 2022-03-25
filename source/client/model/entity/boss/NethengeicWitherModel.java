package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.tslat.aoa3.content.entity.boss.NethengeicWitherEntity;

public class NethengeicWitherModel extends EntityModel<NethengeicWitherEntity> {
	private final ModelRenderer body1;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer head1;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;
	private final ModelRenderer body7;
	private final ModelRenderer body8;
	private final ModelRenderer body9;
	private final ModelRenderer head10;
	private final ModelRenderer head;
	private final ModelRenderer head2;
	private final ModelRenderer body11;
	private final ModelRenderer body12;
	private final ModelRenderer body13;
	private final ModelRenderer body14;
	private final ModelRenderer head7;
	private final ModelRenderer body15;

	public NethengeicWitherModel() {
		texWidth = 128;
		texHeight = 32;
		(body1 = new ModelRenderer(this, 3, 21)).addBox(-1.5f, 0.0f, -2.0f, 3, 9, 2);
		body1.setPos(3.0f, 14.0f, 0.0f);
		body1.setTexSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.2617994f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 34, 25)).addBox(-2.0f, 0.0f, -2.0f, 8, 3, 2);
		body2.setPos(-13.0f, -2.0f, 2.0f);
		body2.setTexSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, -1.570796f);
		(body3 = new ModelRenderer(this, 1, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 2);
		body3.setPos(-7.0f, 12.0f, 0.0f);
		body3.setTexSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(head1 = new ModelRenderer(this, 34, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head1.setPos(11.0f, 4.0f, -3.0f);
		head1.setTexSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 59, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 2);
		body4.setPos(3.0f, 4.0f, 0.0f);
		body4.setTexSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 16, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 2);
		body5.setPos(7.0f, 12.0f, 0.0f);
		body5.setTexSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 1, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 2);
		body6.setPos(-7.0f, 6.0f, 0.0f);
		body6.setTexSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 16, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 2);
		body7.setPos(7.0f, 6.0f, 0.0f);
		body7.setTexSize(128, 32);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 1, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 2);
		body8.setPos(-7.0f, 9.0f, 0.0f);
		body8.setTexSize(128, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(body9 = new ModelRenderer(this, 16, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 2);
		body9.setPos(7.0f, 9.0f, 0.0f);
		body9.setTexSize(128, 32);
		body9.mirror = true;
		setRotation(body9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 34, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head10.setPos(-11.0f, 4.0f, -3.0f);
		head10.setTexSize(128, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -5.0f, 8, 8, 8);
		head.setPos(0.0f, 1.0f, -1.0f);
		head.setTexSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 75, 0)).addBox(-4.0f, -8.0f, -5.0f, 8, 8, 8);
		head2.setPos(-9.0f, -3.0f, 3.0f);
		head2.setTexSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(body11 = new ModelRenderer(this, 34, 25)).addBox(-2.0f, 0.0f, -2.0f, 8, 3, 2);
		body11.setPos(2.0f, 1.0f, 0.0f);
		body11.setTexSize(128, 32);
		body11.mirror = true;
		setRotation(body11, 0.0f, 0.0f, 0.0f);
		(body12 = new ModelRenderer(this, 34, 25)).addBox(-2.0f, 0.0f, -2.0f, 8, 3, 2);
		body12.setPos(9.0f, -2.0f, 2.0f);
		body12.setTexSize(128, 32);
		body12.mirror = true;
		setRotation(body12, 0.0f, 0.0f, -1.570796f);
		(body13 = new ModelRenderer(this, 3, 21)).addBox(-1.5f, 0.0f, -2.0f, 3, 9, 2);
		body13.setPos(-3.0f, 14.0f, 0.0f);
		body13.setTexSize(128, 32);
		body13.mirror = true;
		setRotation(body13, 0.2617994f, 0.0f, 0.0f);
		(body14 = new ModelRenderer(this, 59, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 2);
		body14.setPos(-3.0f, 4.0f, 0.0f);
		body14.setTexSize(128, 32);
		body14.mirror = true;
		setRotation(body14, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 75, 0)).addBox(-4.0f, -8.0f, -5.0f, 8, 8, 8);
		head7.setPos(9.0f, -3.0f, 3.0f);
		head7.setTexSize(128, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(body15 = new ModelRenderer(this, 37, 17)).addBox(0.0f, 0.0f, -2.0f, 8, 3, 2);
		body15.setPos(-8.0f, 1.0f, 0.0f);
		body15.setTexSize(128, 32);
		body15.mirror = true;
		setRotation(body15, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body14.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body15.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(NethengeicWitherEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		head2.yRot = netHeadYaw / 57.295776f;
		head2.xRot = headPitch / 54.11268f;
		head7.yRot = netHeadYaw / 57.295776f;
		head7.xRot = headPitch / 54.11268f;
	}
}

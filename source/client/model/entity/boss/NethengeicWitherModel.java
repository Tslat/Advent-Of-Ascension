package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.tslat.aoa3.entity.boss.NethengeicWitherEntity;

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
		textureWidth = 128;
		textureHeight = 32;
		(body1 = new ModelRenderer(this, 3, 21)).addBox(-1.5f, 0.0f, -2.0f, 3, 9, 2);
		body1.setRotationPoint(3.0f, 14.0f, 0.0f);
		body1.setTextureSize(128, 32);
		body1.mirror = true;
		setRotation(body1, 0.2617994f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 34, 25)).addBox(-2.0f, 0.0f, -2.0f, 8, 3, 2);
		body2.setRotationPoint(-13.0f, -2.0f, 2.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, -1.570796f);
		(body3 = new ModelRenderer(this, 1, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 2);
		body3.setRotationPoint(-7.0f, 12.0f, 0.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(head1 = new ModelRenderer(this, 34, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head1.setRotationPoint(11.0f, 4.0f, -3.0f);
		head1.setTextureSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(body4 = new ModelRenderer(this, 59, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 2);
		body4.setRotationPoint(3.0f, 4.0f, 0.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, 0.0f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 16, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 2);
		body5.setRotationPoint(7.0f, 12.0f, 0.0f);
		body5.setTextureSize(128, 32);
		body5.mirror = true;
		setRotation(body5, 0.0f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 1, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 2);
		body6.setRotationPoint(-7.0f, 6.0f, 0.0f);
		body6.setTextureSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 0.0f, 0.0f, 0.0f);
		(body7 = new ModelRenderer(this, 16, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 2);
		body7.setRotationPoint(7.0f, 6.0f, 0.0f);
		body7.setTextureSize(128, 32);
		body7.mirror = true;
		setRotation(body7, 0.0f, 0.0f, 0.0f);
		(body8 = new ModelRenderer(this, 1, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 2);
		body8.setRotationPoint(-7.0f, 9.0f, 0.0f);
		body8.setTextureSize(128, 32);
		body8.mirror = true;
		setRotation(body8, 0.0f, 0.0f, 0.0f);
		(body9 = new ModelRenderer(this, 16, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 2, 2);
		body9.setRotationPoint(7.0f, 9.0f, 0.0f);
		body9.setTextureSize(128, 32);
		body9.mirror = true;
		setRotation(body9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 34, 0)).addBox(-3.0f, -6.0f, -3.0f, 6, 6, 6);
		head10.setRotationPoint(-11.0f, 4.0f, -3.0f);
		head10.setTextureSize(128, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -5.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 1.0f, -1.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 75, 0)).addBox(-4.0f, -8.0f, -5.0f, 8, 8, 8);
		head2.setRotationPoint(-9.0f, -3.0f, 3.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(body11 = new ModelRenderer(this, 34, 25)).addBox(-2.0f, 0.0f, -2.0f, 8, 3, 2);
		body11.setRotationPoint(2.0f, 1.0f, 0.0f);
		body11.setTextureSize(128, 32);
		body11.mirror = true;
		setRotation(body11, 0.0f, 0.0f, 0.0f);
		(body12 = new ModelRenderer(this, 34, 25)).addBox(-2.0f, 0.0f, -2.0f, 8, 3, 2);
		body12.setRotationPoint(9.0f, -2.0f, 2.0f);
		body12.setTextureSize(128, 32);
		body12.mirror = true;
		setRotation(body12, 0.0f, 0.0f, -1.570796f);
		(body13 = new ModelRenderer(this, 3, 21)).addBox(-1.5f, 0.0f, -2.0f, 3, 9, 2);
		body13.setRotationPoint(-3.0f, 14.0f, 0.0f);
		body13.setTextureSize(128, 32);
		body13.mirror = true;
		setRotation(body13, 0.2617994f, 0.0f, 0.0f);
		(body14 = new ModelRenderer(this, 59, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 2);
		body14.setRotationPoint(-3.0f, 4.0f, 0.0f);
		body14.setTextureSize(128, 32);
		body14.mirror = true;
		setRotation(body14, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 75, 0)).addBox(-4.0f, -8.0f, -5.0f, 8, 8, 8);
		head7.setRotationPoint(9.0f, -3.0f, 3.0f);
		head7.setTextureSize(128, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(body15 = new ModelRenderer(this, 37, 17)).addBox(0.0f, 0.0f, -2.0f, 8, 3, 2);
		body15.setRotationPoint(-8.0f, 1.0f, 0.0f);
		body15.setTextureSize(128, 32);
		body15.mirror = true;
		setRotation(body15, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
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
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(NethengeicWitherEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 57.295776f;
		head.rotateAngleX = headPitch / 54.11268f;
		head2.rotateAngleY = netHeadYaw / 57.295776f;
		head2.rotateAngleX = headPitch / 54.11268f;
		head7.rotateAngleY = netHeadYaw / 57.295776f;
		head7.rotateAngleX = headPitch / 54.11268f;
	}
}

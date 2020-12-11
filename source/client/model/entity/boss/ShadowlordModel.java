package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class ShadowlordModel extends EntityModel<MobEntity> {
	private final ModelRenderer shape1;
	private final ModelRenderer head;
	private final ModelRenderer shape2;
	private final ModelRenderer shape3;
	private final ModelRenderer shape4;
	private final ModelRenderer shape5;
	private final ModelRenderer shape6;
	private final ModelRenderer shape7;
	private final ModelRenderer shape8;
	private final ModelRenderer shape9;
	private final ModelRenderer shape10;
	private final ModelRenderer shape11;
	private final ModelRenderer shape12;
	private final ModelRenderer shape13;
	private final ModelRenderer shape14;
	private final ModelRenderer shape15;

	public ShadowlordModel() {
		textureWidth = 128;
		textureHeight = 32;
		(shape1 = new ModelRenderer(this, 106, 0)).addBox(-1.0f, -3.0f, -3.0f, 2, 14, 8);
		shape1.setRotationPoint(7.0f, 9.0f, -1.0f);
		shape1.setTextureSize(128, 32);
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -8.0f, -3.0f, 10, 8, 6);
		head.setRotationPoint(0.0f, 17.5f, 0.0f);
		head.setTextureSize(128, 32);
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 64, 23)).addBox(-6.0f, -3.0f, -6.0f, 3, 3, 6);
		shape2.setRotationPoint(8.0f, 8.0f, -1.0f);
		shape2.setTextureSize(128, 32);
		setRotation(shape2, -1.047198f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 33, 0)).addBox(-6.0f, 0.0f, -8.0f, 12, 2, 8);
		shape3.setRotationPoint(0.0f, 19.0f, 4.0f);
		shape3.setTextureSize(128, 32);
		setRotation(shape3, 0.179193f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 87, 17)).addBox(-1.0f, 0.0f, -3.0f, 2, 8, 6);
		shape4.setRotationPoint(7.0f, 10.0f, 0.0f);
		shape4.setTextureSize(128, 32);
		setRotation(shape4, 0.0f, 0.0f, -0.4363323f);
		(shape5 = new ModelRenderer(this, 36, 12)).addBox(-1.0f, -3.0f, -2.0f, 6, 3, 3);
		shape5.setRotationPoint(7.0f, 19.0f, 6.0f);
		shape5.setTextureSize(128, 32);
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 90, 0)).addBox(-1.0f, 0.0f, -3.0f, 2, 8, 4);
		shape6.setRotationPoint(7.0f, 10.0f, 1.0f);
		shape6.setTextureSize(128, 32);
		setRotation(shape6, 0.0f, 0.0f, -1.832596f);
		(shape7 = new ModelRenderer(this, 87, 17)).addBox(-1.0f, 0.0f, -3.0f, 2, 8, 6);
		shape7.setRotationPoint(-7.0f, 10.0f, 0.0f);
		shape7.setTextureSize(128, 32);
		setRotation(shape7, 0.0f, 0.0f, 0.4363323f);
		(shape8 = new ModelRenderer(this, 75, 0)).addBox(-1.0f, 0.0f, -3.0f, 2, 8, 4);
		shape8.setRotationPoint(-7.0f, 10.0f, 1.0f);
		shape8.setTextureSize(128, 32);
		setRotation(shape8, 0.0f, 0.0f, 1.832596f);
		(shape9 = new ModelRenderer(this, 106, 0)).addBox(-1.0f, -3.0f, -3.0f, 2, 14, 8);
		shape9.setRotationPoint(-7.0f, 9.0f, -1.0f);
		shape9.setTextureSize(128, 32);
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 41, 26)).addBox(-1.0f, -3.0f, -2.0f, 7, 2, 3);
		shape10.setRotationPoint(11.0f, 19.5f, 5.0f);
		shape10.setTextureSize(128, 32);
		setRotation(shape10, 0.0f, 0.5235988f, 0.0f);
		(shape11 = new ModelRenderer(this, 36, 12)).addBox(-1.0f, -3.0f, -2.0f, 6, 3, 3);
		shape11.setRotationPoint(-11.0f, 19.0f, 6.0f);
		shape11.setTextureSize(128, 32);
		setRotation(shape11, 0.0f, 0.0f, 0.0f);
		(shape12 = new ModelRenderer(this, 41, 20)).addBox(-1.0f, -3.0f, -2.0f, 7, 2, 3);
		shape12.setRotationPoint(-15.0f, 19.5f, 3.0f);
		shape12.setTextureSize(128, 32);
		setRotation(shape12, 0.0f, -0.5235988f, 0.0f);
		(shape13 = new ModelRenderer(this, 0, 21)).addBox(-6.0f, -3.0f, -3.0f, 12, 1, 8);
		shape13.setRotationPoint(0.0f, 21.0f, -1.0f);
		shape13.setTextureSize(128, 32);
		setRotation(shape13, 0.0f, 0.0f, 0.0f);
		(shape14 = new ModelRenderer(this, 0, 21)).addBox(-6.0f, -3.0f, -3.0f, 12, 3, 8);
		shape14.setRotationPoint(0.0f, 9.0f, -1.0f);
		shape14.setTextureSize(128, 32);
		setRotation(shape14, 0.0f, 0.0f, 0.0f);
		(shape15 = new ModelRenderer(this, 63, 13)).addBox(-6.0f, -3.0f, -6.0f, 3, 3, 6);
		shape15.setRotationPoint(1.0f, 8.0f, -1.0f);
		shape15.setTextureSize(128, 32);
		setRotation(shape15, -1.047198f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape14.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape15.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 57.295776f;
		head.rotateAngleX = headPitch / 54.11268f;
	}
}

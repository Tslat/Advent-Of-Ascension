package net.tslat.aoa3.client.model.entity.minion;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class BlissardModel extends EntityModel<MobEntity> {
	private final ModelRenderer root;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg2;
	private final ModelRenderer Leg3;
	private final ModelRenderer Leg4;

	public BlissardModel() {
		textureWidth = 64;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);


		ModelRenderer wolfHead = new ModelRenderer(this);
		wolfHead.setRotationPoint(0.0F, -10.5F, -8.5F);
		root.addChild(wolfHead);
		wolfHead.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F, true);
		wolfHead.setTextureOffset(45, 14).addBox(1.0F, -6.0F, 0.0F, 4, 6, 1, 0.0F, true);
		wolfHead.setTextureOffset(45, 14).addBox(-5.0F, -6.0F, 0.0F, 4, 6, 1, 0.0F, true);
		wolfHead.setTextureOffset(0, 10).addBox(-1.5F, -0.001F, -5.0F, 3, 3, 4, 0.0F, true);

		ModelRenderer body = new ModelRenderer(this);
		body.setRotationPoint(-2.0F, -16.0F, 0.5F);
		root.addChild(body);
		setRotation(body, 1.5708F, 0.0F, 0.0F);
		body.setTextureOffset(21, 31).addBox(0.0F, -2.0F, -3.0F, 4, 8, 3, 0.0F, true);
		body.setTextureOffset(21, 0).addBox(-2.0F, -8.0F, -9.0F, 8, 6, 7, 0.0F, true);
		body.setTextureOffset(18, 14).addBox(-1.0F, -2.0F, -9.0F, 6, 9, 6, 0.0F, true);

		Leg1 = new ModelRenderer(this);
		Leg1.setRotationPoint(1.5F, -8.0F, 5.5F);
		root.addChild(Leg1);
		Leg1.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, true);

		Leg2 = new ModelRenderer(this);
		Leg2.setRotationPoint(-1.5F, -8.0F, 5.5F);
		root.addChild(Leg2);
		Leg2.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, true);

		Leg3 = new ModelRenderer(this);
		Leg3.setRotationPoint(1.5F, -8.0F, -5.5F);
		root.addChild(Leg3);
		Leg3.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, true);

		Leg4 = new ModelRenderer(this);
		Leg4.setRotationPoint(-1.5F, -8.0F, -5.5F);
		root.addChild(Leg4);
		Leg4.setTextureOffset(0, 18).addBox(-1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, true);

		ModelRenderer tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -13.1F, 5.5F);
		root.addChild(tail);
		setRotation(tail, 1.9199F, 0.0F, 0.0F);
		tail.setTextureOffset(9, 18).addBox(-1.0F, 7.0F, -1.0F, 2, 3, 2, 0.0F, true);
		tail.setTextureOffset(9, 31).addBox(-1.0F, 0.0F, -1.0F, 2, 7, 3, 0.0F, true);

		ModelRenderer tail3 = new ModelRenderer(this);
		tail3.setRotationPoint(0.0F, -8.0F, 5.5F);
		root.addChild(tail3);
		setRotation(tail3, 1.1301F, 0.0F, 0.0F);
		tail3.setTextureOffset(9, 31).addBox(-1.0F, 0.0F, -1.0F, 2, 7, 3, 0.0F, true);
		tail3.setTextureOffset(9, 18).addBox(-1.0F, 7.0F, -1.0F, 2, 3, 2, 0.0F, true);

		ModelRenderer tail5 = new ModelRenderer(this);
		tail5.setRotationPoint(0.0F, -10.7F, 5.5F);
		root.addChild(tail5);
		setRotation(tail5, 1.4835F, 0.0F, 0.0F);
		tail5.setTextureOffset(9, 31).addBox(-1.0F, 0.0F, -1.0F, 2, 7, 3, 0.0F, true);
		tail5.setTextureOffset(9, 18).addBox(-1.0F, 7.0F, -1.0F, 2, 3, 2, 0.0F, true);
	}

	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		Leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		Leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}

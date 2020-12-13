package net.tslat.aoa3.client.model.entity.mob.gardencia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class BroccoheadModel extends EntityModel<MobEntity> {
	private final ModelRenderer body;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer body2;
	private final ModelRenderer head;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer head6;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;
	private final ModelRenderer body7;

	public BroccoheadModel() {
		textureWidth = 128;
		textureHeight = 32;
		(body = new ModelRenderer(this, 112, 18)).addBox(-4.0f, 8.0f, -4.0f, 4, 5, 4);
		body.setRotationPoint(-4.0f, -8.0f, -5.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.6108652f, 1.117011f, 0.0f);
		(rightLeg = new ModelRenderer(this, 112, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		rightLeg.setRotationPoint(-3.0f, 12.0f, 0.0f);
		rightLeg.setTextureSize(128, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 112, 1)).addBox(-2.0f, 0.0f, -2.0f, 4, 12, 4);
		leftLeg.setRotationPoint(3.0f, 12.0f, 0.0f);
		leftLeg.setTextureSize(128, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 79, 12)).addBox(-4.0f, 0.0f, -4.0f, 8, 12, 8);
		body2.setRotationPoint(0.0f, 0.0f, 0.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 2, 11)).addBox(-5.0f, -5.0f, 2.0f, 12, 5, 12);
		head.setRotationPoint(-1.0f, -7.0f, -1.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 53, 14)).addBox(-2.0f, -7.0f, 5.0f, 6, 2, 6);
		head2.setRotationPoint(-1.0f, -7.0f, -1.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 2, 11)).addBox(-14.0f, -5.0f, -12.0f, 12, 5, 12);
		head3.setRotationPoint(1.0f, -6.0f, 3.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 53, 14)).addBox(-11.0f, -7.0f, -9.0f, 6, 2, 6);
		head4.setRotationPoint(1.0f, -6.0f, 3.0f);
		head4.setTextureSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 2, 11)).addBox(1.0f, -5.0f, -16.0f, 12, 5, 12);
		head5.setRotationPoint(-2.0f, -5.0f, 6.0f);
		head5.setTextureSize(128, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 53, 14)).addBox(4.0f, -7.0f, -13.0f, 6, 2, 6);
		head6.setRotationPoint(-2.0f, -5.0f, 6.0f);
		head6.setTextureSize(128, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 112, 18)).addBox(-4.0f, -2.0f, -4.0f, 4, 10, 4);
		body3.setRotationPoint(-4.0f, -8.0f, -5.0f);
		body3.setTextureSize(128, 32);
		body3.mirror = true;
		setRotation(body3, 0.6108652f, 1.117011f, 0.0f);
		(body4 = new ModelRenderer(this, 112, 18)).addBox(-4.0f, 0.0f, -4.0f, 4, 10, 4);
		body4.setRotationPoint(2.0f, -8.0f, 11.0f);
		body4.setTextureSize(128, 32);
		body4.mirror = true;
		setRotation(body4, -0.5235988f, 0.0f, 0.0f);
		(body5 = new ModelRenderer(this, 112, 18)).addBox(-4.0f, 10.0f, -4.0f, 4, 5, 4);
		body5.setRotationPoint(2.0f, -8.0f, 11.0f);
		body5.setTextureSize(128, 32);
		body5.mirror = true;
		setRotation(body5, -0.5235988f, 0.0f, 0.0f);
		(body6 = new ModelRenderer(this, 112, 18)).addBox(-4.0f, 8.0f, -4.0f, 4, 5, 4);
		body6.setRotationPoint(6.0f, -8.0f, -2.0f);
		body6.setTextureSize(128, 32);
		body6.mirror = true;
		setRotation(body6, 0.5235988f, -0.5235988f, 0.0f);
		(body7 = new ModelRenderer(this, 112, 18)).addBox(-4.0f, -2.0f, -4.0f, 4, 10, 4);
		body7.setRotationPoint(6.0f, -8.0f, -2.0f);
		body7.setTextureSize(128, 32);
		body7.mirror = true;
		setRotation(body7, 0.5235988f, -0.5235988f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

package net.tslat.aoa3.client.model.entity.mob.deeplands;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class CaseConstructModel extends EntityModel<MobEntity> {
	private final ModelRenderer Case2;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer R1;
	private final ModelRenderer Case;

	public CaseConstructModel() {
		textureWidth = 64;
		textureHeight = 64;
		(Case2 = new ModelRenderer(this, 24, 13)).addBox(-6.0f, -1.0f, -4.0f, 12, 1, 8);
		Case2.setRotationPoint(0.0f, -5.0f, 0.0f);
		Case2.setTextureSize(64, 64);
		Case2.mirror = true;
		setRotation(Case2, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		rightLeg.setRotationPoint(-4.0f, 14.0f, 0.0f);
		rightLeg.setTextureSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		leftLeg.setRotationPoint(4.0f, 14.0f, 0.0f);
		leftLeg.setTextureSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -4.0f, 8, 4, 8);
		head.setRotationPoint(0.0f, -6.0f, 0.0f);
		head.setTextureSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 24, 22)).addBox(-6.0f, -4.0f, -4.0f, 12, 2, 8);
		body.setRotationPoint(0.0f, 16.0f, 0.0f);
		body.setTextureSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(R1 = new ModelRenderer(this, 24, 13)).addBox(-6.0f, -1.0f, -4.0f, 12, 1, 8);
		R1.setRotationPoint(0.0f, -4.0f, 0.0f);
		R1.setTextureSize(64, 64);
		R1.mirror = true;
		setRotation(R1, 0.0f, 0.0f, 0.0f);
		(Case = new ModelRenderer(this, 0, 32)).addBox(-8.0f, -8.0f, -8.0f, 16, 16, 16);
		Case.setRotationPoint(0.0f, 4.0f, 0.0f);
		Case.setTextureSize(64, 64);
		Case.mirror = true;
		setRotation(Case, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		Case2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		R1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Case.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		R1.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		Case.rotateAngleY = ageInTicks * -0.067f * 1.25f;
		Case2.rotateAngleY = ageInTicks * -0.067f * 1.25f;
		rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.rotateAngleY = 0.0f;
		leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

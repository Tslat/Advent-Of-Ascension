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
		texWidth = 64;
		texHeight = 64;
		(Case2 = new ModelRenderer(this, 24, 13)).addBox(-6.0f, -1.0f, -4.0f, 12, 1, 8);
		Case2.setPos(0.0f, -5.0f, 0.0f);
		Case2.setTexSize(64, 64);
		Case2.mirror = true;
		setRotation(Case2, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		rightLeg.setPos(-4.0f, 14.0f, 0.0f);
		rightLeg.setTexSize(64, 64);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 10, 6);
		leftLeg.setPos(4.0f, 14.0f, 0.0f);
		leftLeg.setTexSize(64, 64);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -4.0f, 8, 4, 8);
		head.setPos(0.0f, -6.0f, 0.0f);
		head.setTexSize(64, 64);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 24, 22)).addBox(-6.0f, -4.0f, -4.0f, 12, 2, 8);
		body.setPos(0.0f, 16.0f, 0.0f);
		body.setTexSize(64, 64);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(R1 = new ModelRenderer(this, 24, 13)).addBox(-6.0f, -1.0f, -4.0f, 12, 1, 8);
		R1.setPos(0.0f, -4.0f, 0.0f);
		R1.setTexSize(64, 64);
		R1.mirror = true;
		setRotation(R1, 0.0f, 0.0f, 0.0f);
		(Case = new ModelRenderer(this, 0, 32)).addBox(-8.0f, -8.0f, -8.0f, 16, 16, 16);
		Case.setPos(0.0f, 4.0f, 0.0f);
		Case.setTexSize(64, 64);
		Case.mirror = true;
		setRotation(Case, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		Case2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		R1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Case.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		R1.yRot = ageInTicks * 0.067f * 1.25f;
		Case.yRot = ageInTicks * -0.067f * 1.25f;
		Case2.yRot = ageInTicks * -0.067f * 1.25f;
		rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

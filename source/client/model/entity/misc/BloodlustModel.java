package net.tslat.aoa3.client.model.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.tslat.aoa3.entity.misc.BloodlustEntity;

public class BloodlustModel extends EntityModel<BloodlustEntity> {
	private final ModelRenderer r1;
	private final ModelRenderer r2;
	private final ModelRenderer r3;
	private final ModelRenderer r4;
	private final ModelRenderer r5;
	private final ModelRenderer r6;
	private final ModelRenderer r7;

	public BloodlustModel() {
		texWidth = 64;
		texHeight = 32;
		(r1 = new ModelRenderer(this, 33, 0)).addBox(-2.0f, -2.0f, 4.0f, 4, 4, 4);
		r1.setPos(0.0f, 16.0f, 0.0f);
		r1.setTexSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
		r2.setPos(0.0f, 16.0f, 0.0f);
		r2.setTexSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 33, 0)).addBox(-2.0f, -8.0f, -2.0f, 4, 4, 4);
		r3.setPos(0.0f, 16.0f, 0.0f);
		r3.setTexSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 33, 0)).addBox(-2.0f, 4.0f, -2.0f, 4, 4, 4);
		r4.setPos(0.0f, 16.0f, 0.0f);
		r4.setTexSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 33, 0)).addBox(-8.0f, -2.0f, -2.0f, 4, 4, 4);
		r5.setPos(0.0f, 16.0f, 0.0f);
		r5.setTexSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 33, 0)).addBox(4.0f, -2.0f, -2.0f, 4, 4, 4);
		r6.setPos(0.0f, 16.0f, 0.0f);
		r6.setTexSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
		(r7 = new ModelRenderer(this, 33, 0)).addBox(-2.0f, -2.0f, -8.0f, 4, 4, 4);
		r7.setPos(0.0f, 16.0f, 0.0f);
		r7.setTexSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(BloodlustEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.yRot = ageInTicks * 0.167f * 1.25f;
		r2.yRot = ageInTicks * 0.167f * 1.25f;
		r3.yRot = ageInTicks * 0.167f * 1.25f;
		r4.yRot = ageInTicks * 0.167f * 1.25f;
		r5.yRot = ageInTicks * 0.167f * 1.25f;
		r6.yRot = ageInTicks * 0.167f * 1.25f;
		r7.yRot = ageInTicks * 0.167f * 1.25f;
	}
}

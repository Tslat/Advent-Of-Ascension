package net.tslat.aoa3.client.model.entity.mob.creeponia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class HostModel extends EntityModel<MobEntity> {
	private final ModelRenderer body1;
	private final ModelRenderer r1;
	private final ModelRenderer body2;
	private final ModelRenderer r2;
	private final ModelRenderer r3;
	private final ModelRenderer r4;
	private final ModelRenderer r5;
	private final ModelRenderer r6;
	private final ModelRenderer r7;
	private final ModelRenderer r8;
	private final ModelRenderer r9;
	private final ModelRenderer r10;
	private final ModelRenderer r11;
	private final ModelRenderer r12;

	public HostModel() {
		texWidth = 64;
		texHeight = 64;
		(body1 = new ModelRenderer(this, 0, 21)).addBox(-4.0f, 0.0f, -4.0f, 8, 5, 8);
		body1.setPos(0.0f, 17.0f, 0.0f);
		body1.setTexSize(64, 64);
		body1.mirror = true;
		setRotation(body1, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 0, 49)).addBox(9.0f, 9.0f, 5.0f, 2, 3, 2);
		r1.setPos(0.0f, 11.0f, 0.0f);
		r1.setTexSize(64, 64);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 0, 0)).addBox(-7.0f, 0.0f, -7.0f, 14, 6, 14);
		body2.setPos(0.0f, 11.0f, 0.0f);
		body2.setTexSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 42)).addBox(5.0f, 9.0f, -11.0f, 2, 3, 2);
		r2.setPos(0.0f, 11.0f, 0.0f);
		r2.setTexSize(64, 64);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 0, 42)).addBox(5.0f, 9.0f, 9.0f, 2, 3, 2);
		r3.setPos(0.0f, 11.0f, 0.0f);
		r3.setTexSize(64, 64);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 0, 49)).addBox(-11.0f, 9.0f, 5.0f, 2, 3, 2);
		r4.setPos(0.0f, 11.0f, 0.0f);
		r4.setTexSize(64, 64);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 0, 36)).addBox(-9.0f, 7.0f, -11.0f, 18, 2, 2);
		r5.setPos(0.0f, 11.0f, 0.0f);
		r5.setTexSize(64, 64);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 0, 36)).addBox(-9.0f, 7.0f, 9.0f, 18, 2, 2);
		r6.setPos(0.0f, 11.0f, 0.0f);
		r6.setTexSize(64, 64);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
		(r7 = new ModelRenderer(this, 0, 42)).addBox(-7.0f, 9.0f, -11.0f, 2, 3, 2);
		r7.setPos(0.0f, 11.0f, 0.0f);
		r7.setTexSize(64, 64);
		r7.mirror = true;
		setRotation(r7, 0.0f, 0.0f, 0.0f);
		(r8 = new ModelRenderer(this, 0, 42)).addBox(-7.0f, 9.0f, 9.0f, 2, 3, 2);
		r8.setPos(0.0f, 11.0f, 0.0f);
		r8.setTexSize(64, 64);
		r8.mirror = true;
		setRotation(r8, 0.0f, 0.0f, 0.0f);
		(r9 = new ModelRenderer(this, 0, 40)).addBox(9.0f, 7.0f, -11.0f, 2, 2, 22);
		r9.setPos(0.0f, 11.0f, 0.0f);
		r9.setTexSize(64, 64);
		r9.mirror = true;
		setRotation(r9, 0.0f, 0.0f, 0.0f);
		(r10 = new ModelRenderer(this, 0, 40)).addBox(-11.0f, 7.0f, -11.0f, 2, 2, 22);
		r10.setPos(0.0f, 11.0f, 0.0f);
		r10.setTexSize(64, 64);
		r10.mirror = true;
		setRotation(r10, 0.0f, 0.0f, 0.0f);
		(r11 = new ModelRenderer(this, 0, 49)).addBox(9.0f, 9.0f, -7.0f, 2, 3, 2);
		r11.setPos(0.0f, 11.0f, 0.0f);
		r11.setTexSize(64, 64);
		r11.mirror = true;
		setRotation(r11, 0.0f, 0.0f, 0.0f);
		(r12 = new ModelRenderer(this, 0, 49)).addBox(-11.0f, 9.0f, -7.0f, 2, 3, 2);
		r12.setPos(0.0f, 11.0f, 0.0f);
		r12.setTexSize(64, 64);
		r12.mirror = true;
		setRotation(r12, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.yRot = ageInTicks * 0.267f * 1.25f;
		r2.yRot = ageInTicks * 0.267f * 1.25f;
		r3.yRot = ageInTicks * 0.267f * 1.25f;
		r4.yRot = ageInTicks * 0.267f * 1.25f;
		r5.yRot = ageInTicks * 0.267f * 1.25f;
		r6.yRot = ageInTicks * 0.267f * 1.25f;
		r7.yRot = ageInTicks * 0.267f * 1.25f;
		r8.yRot = ageInTicks * 0.267f * 1.25f;
		r9.yRot = ageInTicks * 0.267f * 1.25f;
		r10.yRot = ageInTicks * 0.267f * 1.25f;
		r11.yRot = ageInTicks * 0.267f * 1.25f;
		r12.yRot = ageInTicks * 0.267f * 1.25f;
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}
}

package net.tslat.aoa3.client.model.entity.mob.nowhere;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class UriohModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer head2;
	private final ModelRenderer r1;
	private final ModelRenderer head3;
	private final ModelRenderer r2;
	private final ModelRenderer r3;
	private final ModelRenderer r4;
	private final ModelRenderer r5;
	private final ModelRenderer r6;
	private final ModelRenderer r7;
	private final ModelRenderer r8;

	public UriohModel() {
		super(RenderType::entityTranslucent);
		texWidth = 64;
		texHeight = 32;
		(head = new ModelRenderer(this, 26, 0)).addBox(4.0f, -5.0f, -1.0f, 8, 2, 2);
		head.setPos(0.0f, 17.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setPos(0.0f, 17.0f, 0.0f);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 35, 16)).addBox(10.0f, 4.0f, -4.0f, 2, 2, 8);
		r1.setPos(0.0f, 13.0f, 0.0f);
		r1.setTexSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 26, 0)).addBox(-12.0f, -5.0f, -1.0f, 8, 2, 2);
		head3.setPos(0.0f, 17.0f, 0.0f);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 35, 16)).addBox(-12.0f, 4.0f, -4.0f, 2, 2, 8);
		r2.setPos(0.0f, 13.0f, 0.0f);
		r2.setTexSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 35, 5)).addBox(10.0f, -4.0f, -6.0f, 2, 8, 2);
		r3.setPos(0.0f, 13.0f, 0.0f);
		r3.setTexSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 35, 5)).addBox(-12.0f, -4.0f, -6.0f, 2, 8, 2);
		r4.setPos(0.0f, 13.0f, 0.0f);
		r4.setTexSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
		(r5 = new ModelRenderer(this, 35, 16)).addBox(10.0f, -6.0f, -4.0f, 2, 2, 8);
		r5.setPos(0.0f, 13.0f, 0.0f);
		r5.setTexSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0.0f, 0.0f, 0.0f);
		(r6 = new ModelRenderer(this, 35, 16)).addBox(-12.0f, -6.0f, -4.0f, 2, 2, 8);
		r6.setPos(0.0f, 13.0f, 0.0f);
		r6.setTexSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0.0f, 0.0f, 0.0f);
		(r7 = new ModelRenderer(this, 35, 5)).addBox(10.0f, -4.0f, 4.0f, 2, 8, 2);
		r7.setPos(0.0f, 13.0f, 0.0f);
		r7.setTexSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0.0f, 0.0f, 0.0f);
		(r8 = new ModelRenderer(this, 35, 5)).addBox(-12.0f, -4.0f, 4.0f, 2, 8, 2);
		r8.setPos(0.0f, 13.0f, 0.0f);
		r8.setTexSize(64, 32);
		r8.mirror = true;
		setRotation(r8, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.xRot = ageInTicks * 0.067f * 2.25f;
		r2.xRot = ageInTicks * 0.067f * 2.25f;
		r3.xRot = ageInTicks * 0.067f * 2.25f;
		r4.xRot = ageInTicks * 0.067f * 2.25f;
		r5.xRot = ageInTicks * 0.067f * 2.25f;
		r6.xRot = ageInTicks * 0.067f * 2.25f;
		r7.xRot = ageInTicks * 0.067f * 2.25f;
		r8.xRot = ageInTicks * 0.067f * 2.25f;
	}
}

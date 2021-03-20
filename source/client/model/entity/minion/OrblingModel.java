package net.tslat.aoa3.client.model.entity.minion;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class OrblingModel extends EntityModel<MobEntity> {
	private final ModelRenderer shape1;
	private final ModelRenderer r1;
	private final ModelRenderer r3;
	private final ModelRenderer r2;
	private final ModelRenderer r4;

	public OrblingModel() {
		texWidth = 64;
		texHeight = 32;
		(shape1 = new ModelRenderer(this, 0, 6)).addBox(-2.0f, -2.0f, -2.0f, 4, 4, 4);
		shape1.setPos(0.0f, 15.0f, 0.0f);
		shape1.setTexSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(r1 = new ModelRenderer(this, 0, 20)).addBox(-5.0f, -5.0f, -3.0f, 10, 2, 0);
		r1.setPos(0.0f, 15.0f, 0.0f);
		r1.setTexSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0f, 0.0f, 0.0f);
		(r3 = new ModelRenderer(this, 0, 16)).addBox(-5.0f, 3.0f, 3.0f, 10, 2, 0);
		r3.setPos(0.0f, 15.0f, 0.0f);
		r3.setTexSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0f, 0.0f, 0.0f);
		(r2 = new ModelRenderer(this, 0, 0)).addBox(-5.0f, -7.0f, -4.0f, 10, 2, 2);
		r2.setPos(0.0f, 15.0f, 0.0f);
		r2.setTexSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0f, 0.0f, 0.0f);
		(r4 = new ModelRenderer(this, 0, 0)).addBox(-5.0f, 5.0f, 2.0f, 10, 2, 2);
		r4.setPos(0.0f, 15.0f, 0.0f);
		r4.setTexSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0f, 0.0f, 0.0f);
	}

	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.zRot = ageInTicks * 0.067f * 5.25f;
		r2.zRot = ageInTicks * 0.067f * 5.25f;
		r3.zRot = ageInTicks * 0.067f * -5.25f;
		r4.zRot = ageInTicks * 0.067f * -5.25f;
	}
}
package net.tslat.aoa3.client.model.entity.animal;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class VoliantModel extends EntityModel<MobEntity> {
	private final ModelRenderer shape1;
	private final ModelRenderer shape2;
	private final ModelRenderer wingR;
	private final ModelRenderer shape4;
	private final ModelRenderer wingL;
	private final ModelRenderer shape3;
	private final ModelRenderer shape5;
	private final ModelRenderer shape6;
	private final ModelRenderer shape7;
	private final ModelRenderer shape8;
	private final ModelRenderer shape9;

	public VoliantModel() {
		texWidth = 128;
		texHeight = 32;
		(shape1 = new ModelRenderer(this, 0, 0)).addBox(0.0f, 0.0f, 0.0f, 8, 8, 8);
		shape1.setPos(-4.0f, 7.0f, 9.0f);
		shape1.setTexSize(128, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 8, 17)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 11);
		shape2.setPos(4.0f, 11.0f, 12.0f);
		shape2.setTexSize(128, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(wingR = new ModelRenderer(this, 35, 0)).addBox(-10.0f, 0.0f, -5.0f, 10, 2, 10);
		wingR.setPos(-5.0f, 8.0f, 5.0f);
		wingR.setTexSize(128, 32);
		wingR.mirror = true;
		setRotation(wingR, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 35, 0)).addBox(0.0f, 0.0f, 0.0f, 10, 10, 10);
		shape4.setPos(-5.0f, 6.0f, 2.0f);
		shape4.setTexSize(128, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(wingL = new ModelRenderer(this, 35, 0)).addBox(0.0f, 0.0f, -5.0f, 10, 2, 10);
		wingL.setPos(5.0f, 8.0f, 5.0f);
		wingL.setTexSize(128, 32);
		wingL.mirror = true;
		setRotation(wingL, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 79, 0)).addBox(0.0f, 0.0f, 0.0f, 12, 12, 12);
		shape3.setPos(-6.0f, 5.0f, -6.0f);
		shape3.setTexSize(128, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 8, 17)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 11);
		shape5.setPos(-6.0f, 11.0f, 12.0f);
		shape5.setTexSize(128, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 40, 3)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 8);
		shape6.setPos(4.0f, 1.0f, -4.0f);
		shape6.setTexSize(128, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 40, 3)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 8);
		shape7.setPos(-6.0f, 1.0f, -4.0f);
		shape7.setTexSize(128, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 40, 3)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 13);
		shape8.setPos(-6.0f, 17.0f, -4.0f);
		shape8.setTexSize(128, 32);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 40, 3)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 13);
		shape9.setPos(4.0f, 17.0f, -4.0f);
		shape9.setTexSize(128, 32);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		wingR.zRot = MathHelper.cos(ageInTicks * 0.1f) * 3.1415927f * 0.15f;
		wingL.zRot = -wingR.zRot;
	}
}

package net.tslat.aoa3.client.model.entity.mob.candyland;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class GingerbirdModel extends EntityModel<MobEntity> {
	private final ModelRenderer wingR1;
	private final ModelRenderer shape1;
	private final ModelRenderer wingL1;
	private final ModelRenderer wingL2;
	private final ModelRenderer wingR2;
	private final ModelRenderer wingL3;
	private final ModelRenderer wingR3;
	private final ModelRenderer wingL4;
	private final ModelRenderer wingR4;
	private final ModelRenderer wingL5;
	private final ModelRenderer wingR5;
	private final ModelRenderer wingL6;
	private final ModelRenderer wingR6;
	private final ModelRenderer shape2;
	private final ModelRenderer shape3;
	private final ModelRenderer shape4;
	private final ModelRenderer shape5;
	private final ModelRenderer shape6;
	private final ModelRenderer shape7;
	private final ModelRenderer shape8;
	private final ModelRenderer shape9;

	public GingerbirdModel() {
		texWidth = 64;
		texHeight = 64;
		(wingR1 = new ModelRenderer(this, 18, 44)).addBox(-9.5f, -0.5f, -0.5f, 4, 1, 1);
		wingR1.setPos(-4.0f, 18.0f, 3.0f);
		wingR1.setTexSize(64, 64);
		wingR1.mirror = true;
		setRotation(wingR1, 0.0f, 0.0f, 0.0f);
		(shape1 = new ModelRenderer(this, 52, 0)).addBox(2.5f, 1.0f, 1.0f, 1, 2, 2);
		shape1.setPos(0.0f, 15.0f, -9.0f);
		shape1.setTexSize(64, 64);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(wingL1 = new ModelRenderer(this, 18, 44)).addBox(5.5f, -0.5f, -0.5f, 4, 1, 1);
		wingL1.setPos(4.0f, 18.0f, 3.0f);
		wingL1.setTexSize(64, 64);
		wingL1.mirror = true;
		setRotation(wingL1, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 0, 24)).addBox(0.0f, 0.0f, -5.0f, 11, 1, 10);
		wingL2.setPos(4.0f, 18.0f, 3.0f);
		wingL2.setTexSize(64, 64);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 0, 24)).addBox(-11.0f, 0.0f, -5.0f, 11, 1, 10);
		wingR2.setPos(-4.0f, 18.0f, 3.0f);
		wingR2.setTexSize(64, 64);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(wingL3 = new ModelRenderer(this, 0, 40)).addBox(0.5f, -0.5f, -4.5f, 10, 1, 1);
		wingL3.setPos(4.0f, 18.0f, 3.0f);
		wingL3.setTexSize(64, 64);
		wingL3.mirror = true;
		setRotation(wingL3, 0.0f, 0.0f, 0.0f);
		(wingR3 = new ModelRenderer(this, 0, 40)).addBox(-10.5f, -0.5f, -4.5f, 10, 1, 1);
		wingR3.setPos(-4.0f, 18.0f, 3.0f);
		wingR3.setTexSize(64, 64);
		wingR3.mirror = true;
		setRotation(wingR3, 0.0f, 0.0f, 0.0f);
		(wingL4 = new ModelRenderer(this, 19, 40)).addBox(9.5f, -0.5f, -3.5f, 1, 1, 8);
		wingL4.setPos(4.0f, 18.0f, 3.0f);
		wingL4.setTexSize(64, 64);
		wingL4.mirror = true;
		setRotation(wingL4, 0.0f, 0.0f, 0.0f);
		(wingR4 = new ModelRenderer(this, 19, 40)).addBox(-10.5f, -0.5f, -3.5f, 1, 1, 8);
		wingR4.setPos(-4.0f, 18.0f, 3.0f);
		wingR4.setTexSize(64, 64);
		wingR4.mirror = true;
		setRotation(wingR4, 0.0f, 0.0f, 0.0f);
		(wingL5 = new ModelRenderer(this, 18, 44)).addBox(6.5f, -0.5f, 1.5f, 3, 1, 1);
		wingL5.setPos(4.0f, 18.0f, 3.0f);
		wingL5.setTexSize(64, 64);
		wingL5.mirror = true;
		setRotation(wingL5, 0.0f, 0.0f, 0.0f);
		(wingR5 = new ModelRenderer(this, 18, 44)).addBox(-9.5f, -0.5f, 1.5f, 3, 1, 1);
		wingR5.setPos(-4.0f, 18.0f, 3.0f);
		wingR5.setTexSize(64, 64);
		wingR5.mirror = true;
		setRotation(wingR5, 0.0f, 0.0f, 0.0f);
		(wingL6 = new ModelRenderer(this, 18, 44)).addBox(6.5f, -0.5f, -2.5f, 3, 1, 1);
		wingL6.setPos(4.0f, 18.0f, 3.0f);
		wingL6.setTexSize(64, 64);
		wingL6.mirror = true;
		setRotation(wingL6, 0.0f, 0.0f, 0.0f);
		(wingR6 = new ModelRenderer(this, 18, 44)).addBox(-9.5f, -0.5f, -2.5f, 3, 1, 1);
		wingR6.setPos(-4.0f, 18.0f, 3.0f);
		wingR6.setTexSize(64, 64);
		wingR6.mirror = true;
		setRotation(wingR6, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 47)).addBox(-3.0f, -0.5f, 8.0f, 2, 1, 1);
		shape2.setPos(2.0f, 16.0f, -2.0f);
		shape2.setTexSize(64, 64);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 32, 0)).addBox(-3.0f, 0.0f, 0.0f, 6, 4, 6);
		shape3.setPos(0.0f, 15.0f, -9.0f);
		shape3.setTexSize(64, 64);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 52, 0)).addBox(-3.5f, 1.0f, 1.0f, 1, 2, 2);
		shape4.setPos(0.0f, 15.0f, -9.0f);
		shape4.setTexSize(64, 64);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, 0.0f, 0.0f, 8, 6, 14);
		shape5.setPos(0.0f, 16.0f, -4.0f);
		shape5.setTexSize(64, 64);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 0, 47)).addBox(-3.0f, -0.5f, 1.0f, 1, 1, 9);
		shape6.setPos(0.0f, 16.0f, -2.0f);
		shape6.setTexSize(64, 64);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 0, 47)).addBox(-3.0f, -0.5f, 1.0f, 1, 1, 9);
		shape7.setPos(5.0f, 16.0f, -2.0f);
		shape7.setTexSize(64, 64);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 0, 47)).addBox(-3.0f, -0.5f, 2.0f, 2, 1, 1);
		shape8.setPos(2.0f, 16.0f, -2.0f);
		shape8.setTexSize(64, 64);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 0, 47)).addBox(-3.0f, -0.5f, 5.0f, 2, 1, 1);
		shape9.setPos(2.0f, 16.0f, -2.0f);
		shape9.setTexSize(64, 64);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		wingR1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		wingR1.zRot = MathHelper.cos(ageInTicks * 0.5f) * 3.1415927f * 0.35f;
		wingL1.zRot = -wingR1.zRot;
		wingR2.zRot = MathHelper.cos(ageInTicks * 0.5f) * 3.1415927f * 0.35f;
		wingL2.zRot = -wingR2.zRot;
		wingR3.zRot = MathHelper.cos(ageInTicks * 0.5f) * 3.1415927f * 0.35f;
		wingL3.zRot = -wingR3.zRot;
		wingR4.zRot = MathHelper.cos(ageInTicks * 0.5f) * 3.1415927f * 0.35f;
		wingL4.zRot = -wingR4.zRot;
		wingR5.zRot = MathHelper.cos(ageInTicks * 0.5f) * 3.1415927f * 0.35f;
		wingL5.zRot = -wingR5.zRot;
		wingR6.zRot = MathHelper.cos(ageInTicks * 0.5f) * 3.1415927f * 0.35f;
		wingL6.zRot = -wingR6.zRot;
	}
}

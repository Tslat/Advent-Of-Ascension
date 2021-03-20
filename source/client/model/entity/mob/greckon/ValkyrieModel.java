package net.tslat.aoa3.client.model.entity.mob.greckon;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ValkyrieModel extends EntityModel<MobEntity> {
	private final ModelRenderer shape2;
	private final ModelRenderer wingR1;
	private final ModelRenderer wingR2;
	private final ModelRenderer shape3;
	private final ModelRenderer shape4;
	private final ModelRenderer shape5;
	private final ModelRenderer shape6;
	private final ModelRenderer shape7;
	private final ModelRenderer shape8;
	private final ModelRenderer shape9;
	private final ModelRenderer shape10;
	private final ModelRenderer shape11;
	private final ModelRenderer shape12;
	private final ModelRenderer shape13;
	private final ModelRenderer shape14;
	private final ModelRenderer wingL1;
	private final ModelRenderer wingR3;
	private final ModelRenderer wingL2;
	private final ModelRenderer wingL3;

	public ValkyrieModel() {
		texWidth = 128;
		texHeight = 64;
		(shape2 = new ModelRenderer(this, 39, 30)).addBox(0.0f, 0.0f, 0.0f, 0, 5, 18);
		shape2.setPos(6.0f, 15.0f, -6.0f);
		shape2.setTexSize(128, 64);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(wingR1 = new ModelRenderer(this, 35, 14)).addBox(-10.0f, 0.0f, -5.0f, 10, 2, 10);
		wingR1.setPos(-5.0f, 9.0f, 5.0f);
		wingR1.setTexSize(128, 64);
		wingR1.mirror = true;
		setRotation(wingR1, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 35, 0)).addBox(-10.0f, 2.0f, 3.0f, 2, 3, 2);
		wingR2.setPos(-5.0f, 9.0f, 5.0f);
		wingR2.setTexSize(128, 64);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 85, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
		shape3.setPos(-2.0f, 10.0f, -12.0f);
		shape3.setTexSize(128, 64);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 39, 30)).addBox(0.0f, 0.0f, 0.0f, 0, 5, 18);
		shape4.setPos(-6.0f, 15.0f, -6.0f);
		shape4.setTexSize(128, 64);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 97, 1)).addBox(0.0f, 0.0f, 0.0f, 8, 4, 6);
		shape5.setPos(-4.0f, 6.0f, -12.0f);
		shape5.setTexSize(128, 64);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 79, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
		shape6.setPos(-4.0f, 10.0f, -12.0f);
		shape6.setTexSize(128, 64);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 79, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 4, 1);
		shape7.setPos(3.0f, 10.0f, -12.0f);
		shape7.setTexSize(128, 64);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 85, 1)).addBox(0.0f, 0.0f, 0.0f, 1, 3, 1);
		shape8.setPos(1.0f, 10.0f, -12.0f);
		shape8.setTexSize(128, 64);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 68, 37)).addBox(0.0f, 0.0f, 0.0f, 12, 9, 18);
		shape9.setPos(-6.0f, 6.0f, -6.0f);
		shape9.setTexSize(128, 64);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 9, 53)).addBox(0.0f, 2.0f, 2.0f, 2, 2, 5);
		shape10.setPos(-1.0f, 0.0f, 17.5f);
		shape10.setTexSize(128, 64);
		shape10.mirror = true;
		setRotation(shape10, 2.879793f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 1, 2)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 9);
		shape11.setPos(-3.0f, 9.0f, 8.0f);
		shape11.setTexSize(128, 64);
		shape11.mirror = true;
		setRotation(shape11, 0.4363323f, 0.0f, 0.0f);
		(shape12 = new ModelRenderer(this, 5, 19)).addBox(0.0f, 0.0f, 0.0f, 5, 5, 7);
		shape12.setPos(-2.5f, 9.0f, 14.0f);
		shape12.setTexSize(128, 64);
		shape12.mirror = true;
		setRotation(shape12, 1.22173f, 0.0f, 0.0f);
		(shape13 = new ModelRenderer(this, 10, 32)).addBox(0.0f, 0.0f, 0.0f, 4, 4, 5);
		shape13.setPos(-2.0f, 4.0f, 16.5f);
		shape13.setTexSize(128, 64);
		shape13.mirror = true;
		setRotation(shape13, 1.570796f, 0.0f, 0.0f);
		(shape14 = new ModelRenderer(this, 9, 43)).addBox(0.0f, 0.0f, -1.0f, 3, 3, 5);
		shape14.setPos(-1.5f, 0.0f, 17.0f);
		shape14.setTexSize(128, 64);
		shape14.mirror = true;
		setRotation(shape14, 2.268928f, 0.0f, 0.0f);
		(wingL1 = new ModelRenderer(this, 35, 14)).addBox(0.0f, 0.0f, -5.0f, 10, 2, 10);
		wingL1.setPos(5.0f, 9.0f, 5.0f);
		wingL1.setTexSize(128, 64);
		wingL1.mirror = true;
		setRotation(wingL1, 0.0f, 0.0f, 0.0f);
		(wingR3 = new ModelRenderer(this, 35, 0)).addBox(-10.0f, 2.0f, -5.0f, 2, 3, 2);
		wingR3.setPos(-5.0f, 9.0f, 5.0f);
		wingR3.setTexSize(128, 64);
		wingR3.mirror = true;
		setRotation(wingR3, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 35, 0)).addBox(8.0f, 2.0f, 3.0f, 2, 3, 2);
		wingL2.setPos(5.0f, 9.0f, 5.0f);
		wingL2.setTexSize(128, 64);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
		(wingL3 = new ModelRenderer(this, 35, 0)).addBox(8.0f, 2.0f, -5.0f, 2, 3, 2);
		wingL3.setPos(5.0f, 9.0f, 5.0f);
		wingL3.setTexSize(128, 64);
		wingL3.mirror = true;
		setRotation(wingL3, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape14.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
	}
}

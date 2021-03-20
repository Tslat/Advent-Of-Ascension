package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class NightflyModel extends EntityModel<MobEntity> {
	private final ModelRenderer shape1;
	private final ModelRenderer wingR;
	private final ModelRenderer wingL;
	private final ModelRenderer shape2;
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
	private final ModelRenderer shape15;
	private final ModelRenderer shape16;
	private final ModelRenderer shape17;
	private final ModelRenderer shape18;
	private final ModelRenderer shape19;

	public NightflyModel() {
		texWidth = 128;
		texHeight = 32;
		(shape1 = new ModelRenderer(this, 11, 2)).addBox(9.0f, 3.0f, 0.0f, 3, 2, 2);
		shape1.setPos(-6.0f, 16.0f, 9.0f);
		shape1.setTexSize(128, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(wingR = new ModelRenderer(this, 35, 0)).addBox(-10.0f, 0.0f, -5.0f, 11, 1, 14);
		wingR.setPos(-5.0f, 12.0f, 0.0f);
		wingR.setTexSize(128, 32);
		wingR.mirror = true;
		setRotation(wingR, 0.0f, 0.0f, 0.0f);
		(wingL = new ModelRenderer(this, 35, 0)).addBox(0.0f, 0.0f, -5.0f, 11, 1, 14);
		wingL.setPos(4.0f, 12.0f, 0.0f);
		wingL.setTexSize(128, 32);
		wingL.mirror = true;
		setRotation(wingL, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 9, 5)).addBox(9.0f, 0.0f, 0.0f, 3, 3, 17);
		shape2.setPos(-6.0f, 6.0f, -6.0f);
		shape2.setTexSize(128, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 11, 2)).addBox(0.0f, 3.0f, 0.0f, 3, 2, 2);
		shape3.setPos(-6.0f, 16.0f, 9.0f);
		shape3.setTexSize(128, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 11, 2)).addBox(10.0f, 0.0f, 0.0f, 2, 3, 2);
		shape4.setPos(-6.0f, 16.0f, 9.0f);
		shape4.setTexSize(128, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 11, 2)).addBox(0.0f, 0.0f, 0.0f, 2, 3, 2);
		shape5.setPos(-6.0f, 16.0f, 9.0f);
		shape5.setTexSize(128, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 11, 2)).addBox(9.0f, 3.0f, 0.0f, 3, 2, 2);
		shape6.setPos(-6.0f, 16.0f, -6.0f);
		shape6.setTexSize(128, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 11, 2)).addBox(10.0f, 0.0f, 0.0f, 2, 3, 2);
		shape7.setPos(-6.0f, 16.0f, -6.0f);
		shape7.setTexSize(128, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 11, 2)).addBox(0.0f, 3.0f, 0.0f, 3, 2, 2);
		shape8.setPos(-6.0f, 16.0f, -6.0f);
		shape8.setTexSize(128, 32);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 11, 2)).addBox(0.0f, 0.0f, 0.0f, 2, 3, 2);
		shape9.setPos(-6.0f, 16.0f, -6.0f);
		shape9.setTexSize(128, 32);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 11, 2)).addBox(9.0f, 3.0f, 0.0f, 3, 2, 2);
		shape10.setPos(-6.0f, 16.0f, -1.0f);
		shape10.setTexSize(128, 32);
		shape10.mirror = true;
		setRotation(shape10, 0.0f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 11, 2)).addBox(10.0f, 0.0f, 0.0f, 2, 3, 2);
		shape11.setPos(-6.0f, 16.0f, -1.0f);
		shape11.setTexSize(128, 32);
		shape11.mirror = true;
		setRotation(shape11, 0.0f, 0.0f, 0.0f);
		(shape12 = new ModelRenderer(this, 11, 2)).addBox(0.0f, 3.0f, 0.0f, 3, 2, 2);
		shape12.setPos(-6.0f, 16.0f, -1.0f);
		shape12.setTexSize(128, 32);
		shape12.mirror = true;
		setRotation(shape12, 0.0f, 0.0f, 0.0f);
		(shape13 = new ModelRenderer(this, 11, 2)).addBox(0.0f, 0.0f, 0.0f, 2, 3, 2);
		shape13.setPos(-6.0f, 16.0f, -1.0f);
		shape13.setTexSize(128, 32);
		shape13.mirror = true;
		setRotation(shape13, 0.0f, 0.0f, 0.0f);
		(shape14 = new ModelRenderer(this, 11, 2)).addBox(9.0f, 3.0f, 0.0f, 3, 2, 2);
		shape14.setPos(-6.0f, 16.0f, 4.0f);
		shape14.setTexSize(128, 32);
		shape14.mirror = true;
		setRotation(shape14, 0.0f, 0.0f, 0.0f);
		(shape15 = new ModelRenderer(this, 11, 2)).addBox(10.0f, 0.0f, 0.0f, 2, 3, 2);
		shape15.setPos(-6.0f, 16.0f, 4.0f);
		shape15.setTexSize(128, 32);
		shape15.mirror = true;
		setRotation(shape15, 0.0f, 0.0f, 0.0f);
		(shape16 = new ModelRenderer(this, 11, 2)).addBox(0.0f, 3.0f, 0.0f, 3, 2, 2);
		shape16.setPos(-6.0f, 16.0f, 4.0f);
		shape16.setTexSize(128, 32);
		shape16.mirror = true;
		setRotation(shape16, 0.0f, 0.0f, 0.0f);
		(shape17 = new ModelRenderer(this, 11, 2)).addBox(0.0f, 0.0f, 0.0f, 2, 3, 2);
		shape17.setPos(-6.0f, 16.0f, 4.0f);
		shape17.setTexSize(128, 32);
		shape17.mirror = true;
		setRotation(shape17, 0.0f, 0.0f, 0.0f);
		(shape18 = new ModelRenderer(this, 70, 3)).addBox(0.0f, 0.0f, 0.0f, 12, 7, 17);
		shape18.setPos(-6.0f, 9.0f, -6.0f);
		shape18.setTexSize(128, 32);
		shape18.mirror = true;
		setRotation(shape18, 0.0f, 0.0f, 0.0f);
		(shape19 = new ModelRenderer(this, 9, 5)).addBox(0.0f, 0.0f, 0.0f, 3, 3, 17);
		shape19.setPos(-6.0f, 6.0f, -6.0f);
		shape19.setTexSize(128, 32);
		shape19.mirror = true;
		setRotation(shape19, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
		shape15.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape16.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape17.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape18.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape19.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		wingR.zRot = MathHelper.cos(ageInTicks * 2.9f) * 3.1415927f * 0.15f;
		wingL.zRot = -wingR.zRot;
	}
}
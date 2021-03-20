package net.tslat.aoa3.client.model.entity.mob.barathos;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class TharaflyModel extends EntityModel<MobEntity> {
	private final ModelRenderer shape1;
	private final ModelRenderer shape2;
	private final ModelRenderer shape3;
	private final ModelRenderer shape4;
	private final ModelRenderer shape5;
	private final ModelRenderer shape6;
	private final ModelRenderer shape7;
	private final ModelRenderer WingR1;
	private final ModelRenderer WingR2;
	private final ModelRenderer WingL1;
	private final ModelRenderer WingL2;
	private final ModelRenderer shape8;
	private final ModelRenderer shape9;
	private final ModelRenderer shape10;
	private final ModelRenderer shape11;
	private final ModelRenderer shape12;
	private final ModelRenderer shape13;

	public TharaflyModel() {
		texWidth = 128;
		texHeight = 32;
		(shape1 = new ModelRenderer(this, 26, 0)).addBox(6.0f, 0.0f, 2.0f, 4, 2, 4);
		shape1.setPos(-3.0f, 11.0f, 3.0f);
		shape1.setTexSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 20)).addBox(-1.0f, 0.0f, -2.0f, 4, 4, 8);
		shape2.setPos(-1.0f, 13.0f, -2.0f);
		shape2.setTexSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.5235988f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 44, 2)).addBox(7.0f, 0.0f, 2.0f, 6, 2, 4);
		shape3.setPos(-3.0f, 14.0f, -8.0f);
		shape3.setTexSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 69, 25)).addBox(0.0f, -2.0f, 0.0f, 2, 2, 2);
		shape4.setPos(3.0f, 8.0f, 2.0f);
		shape4.setTexSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.5235988f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 0, 0)).addBox(-1.0f, 0.0f, 0.0f, 8, 8, 7);
		shape5.setPos(-3.0f, 12.0f, -8.0f);
		shape5.setTexSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 44, 2)).addBox(-7.0f, 0.0f, 2.0f, 6, 2, 4);
		shape6.setPos(-3.0f, 14.0f, -8.0f);
		shape6.setTexSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 26, 0)).addBox(-4.0f, 0.0f, 2.0f, 4, 2, 4);
		shape7.setPos(-3.0f, 11.0f, 3.0f);
		shape7.setTexSize(64, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(WingR1 = new ModelRenderer(this, 28, 10)).addBox(-10.0f, 0.0f, -4.0f, 10, 1, 8);
		WingR1.setPos(-10.0f, 15.0f, -4.0f);
		WingR1.setTexSize(64, 32);
		WingR1.mirror = true;
		setRotation(WingR1, 0.0f, 0.0f, 0.0f);
		(WingR2 = new ModelRenderer(this, 28, 10)).addBox(-10.0f, 0.0f, -4.0f, 10, 1, 8);
		WingR2.setPos(-7.0f, 12.0f, 7.0f);
		WingR2.setTexSize(64, 32);
		WingR2.mirror = true;
		setRotation(WingR2, 0.0f, 0.0f, 0.0f);
		(WingL1 = new ModelRenderer(this, 28, 21)).addBox(0.0f, 0.0f, -4.0f, 10, 1, 8);
		WingL1.setPos(10.0f, 15.0f, -4.0f);
		WingL1.setTexSize(64, 32);
		WingL1.mirror = true;
		setRotation(WingL1, 0.0f, 0.0f, 0.0f);
		(WingL2 = new ModelRenderer(this, 28, 21)).addBox(0.0f, 0.0f, -4.0f, 10, 1, 8);
		WingL2.setPos(7.0f, 12.0f, 7.0f);
		WingL2.setTexSize(64, 32);
		WingL2.mirror = true;
		setRotation(WingL2, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 70, 0)).addBox(0.0f, 0.0f, 0.0f, 6, 6, 7);
		shape8.setPos(-3.0f, 9.0f, 3.0f);
		shape8.setTexSize(64, 32);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 69, 25)).addBox(0.0f, -2.0f, 0.0f, 2, 2, 2);
		shape9.setPos(-5.0f, 8.0f, 2.0f);
		shape9.setTexSize(64, 32);
		shape9.mirror = true;
		setRotation(shape9, 0.5235988f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 86, 16)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 7);
		shape10.setPos(3.0f, 8.0f, 2.0f);
		shape10.setTexSize(64, 32);
		shape10.mirror = true;
		setRotation(shape10, 0.5235988f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 86, 16)).addBox(0.0f, 0.0f, 0.0f, 2, 4, 7);
		shape11.setPos(-5.0f, 8.0f, 2.0f);
		shape11.setTexSize(64, 32);
		shape11.mirror = true;
		setRotation(shape11, 0.5235988f, 0.0f, 0.0f);
		(shape12 = new ModelRenderer(this, 65, 15)).addBox(0.0f, -3.0f, 0.0f, 2, 1, 7);
		shape12.setPos(3.0f, 8.0f, 2.0f);
		shape12.setTexSize(64, 32);
		shape12.mirror = true;
		setRotation(shape12, 0.5235988f, 0.0f, 0.0f);
		(shape13 = new ModelRenderer(this, 65, 15)).addBox(0.0f, -3.0f, 0.0f, 2, 1, 7);
		shape13.setPos(-5.0f, 8.0f, 2.0f);
		shape13.setTexSize(64, 32);
		shape13.mirror = true;
		setRotation(shape13, 0.5235988f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		WingR1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		WingR2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		WingL1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		WingL2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		WingR1.zRot = MathHelper.cos(ageInTicks * 2.1f) * 3.1415927f * 0.15f;
		WingL1.zRot = -WingR1.zRot;
		WingR2.zRot = MathHelper.cos(ageInTicks * 2.1f) * 3.1415927f * 0.15f;
		WingL2.zRot = -WingR2.zRot;
	}
}

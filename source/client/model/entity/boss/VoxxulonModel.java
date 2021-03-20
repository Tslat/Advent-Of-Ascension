package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class VoxxulonModel extends EntityModel<MobEntity> {
	private final ModelRenderer shape1;
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
	private final ModelRenderer shape20;

	public VoxxulonModel() {
		texWidth = 64;
		texHeight = 128;
		(shape1 = new ModelRenderer(this, 0, 19)).addBox(-6.0f, -6.0f, -5.0f, 5, 11, 10);
		shape1.setPos(0.0f, 11.0f, 0.0f);
		shape1.setTexSize(64, 128);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 109)).addBox(-2.0f, 2.0f, -9.0f, 1, 8, 1);
		shape2.setPos(0.0f, 14.0f, 0.0f);
		shape2.setTexSize(64, 128);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 0)).addBox(-8.0f, -1.0f, 3.0f, 16, 6, 6);
		shape3.setPos(0.0f, 11.0f, 0.0f);
		shape3.setTexSize(64, 128);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 32, 22)).addBox(-1.0f, 1.0f, -6.0f, 2, 4, 9);
		shape4.setPos(0.0f, 11.0f, 0.0f);
		shape4.setTexSize(64, 128);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 33, 39)).addBox(-8.0f, -4.0f, -4.0f, 3, 5, 5);
		shape5.setPos(0.0f, 11.0f, 0.0f);
		shape5.setTexSize(64, 128);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 0, 44)).addBox(1.0f, -6.0f, -5.0f, 5, 11, 10);
		shape6.setPos(0.0f, 11.0f, 0.0f);
		shape6.setTexSize(64, 128);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 48, 8)).addBox(-8.5f, -7.5f, 2.0f, 3, 3, 4);
		shape7.setPos(0.0f, 11.0f, 0.0f);
		shape7.setTexSize(64, 128);
		shape7.mirror = true;
		setRotation(shape7, 0.4363323f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 0, 70)).addBox(-8.0f, 2.0f, -7.0f, 16, 8, 16);
		shape8.setPos(0.0f, 14.0f, 0.0f);
		shape8.setTexSize(64, 128);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 0, 109)).addBox(-8.0f, 2.0f, -9.0f, 1, 8, 1);
		shape9.setPos(0.0f, 14.0f, 0.0f);
		shape9.setTexSize(64, 128);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 0, 99)).addBox(-8.0f, 2.0f, -8.0f, 16, 1, 1);
		shape10.setPos(0.0f, 14.0f, 0.0f);
		shape10.setTexSize(64, 128);
		shape10.mirror = true;
		setRotation(shape10, 0.0f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 0, 109)).addBox(5.0f, 2.0f, -9.0f, 1, 8, 1);
		shape11.setPos(0.0f, 14.0f, 0.0f);
		shape11.setTexSize(64, 128);
		shape11.mirror = true;
		setRotation(shape11, 0.0f, 0.0f, 0.0f);
		(shape12 = new ModelRenderer(this, 0, 109)).addBox(3.0f, 2.0f, -9.0f, 1, 8, 1);
		shape12.setPos(0.0f, 14.0f, 0.0f);
		shape12.setTexSize(64, 128);
		shape12.mirror = true;
		setRotation(shape12, 0.0f, 0.0f, 0.0f);
		(shape13 = new ModelRenderer(this, 0, 109)).addBox(-6.0f, 2.0f, -9.0f, 1, 8, 1);
		shape13.setPos(0.0f, 14.0f, 0.0f);
		shape13.setTexSize(64, 128);
		shape13.mirror = true;
		setRotation(shape13, 0.0f, 0.0f, 0.0f);
		(shape14 = new ModelRenderer(this, 0, 109)).addBox(-4.0f, 2.0f, -9.0f, 1, 8, 1);
		shape14.setPos(0.0f, 14.0f, 0.0f);
		shape14.setTexSize(64, 128);
		shape14.mirror = true;
		setRotation(shape14, 0.0f, 0.0f, 0.0f);
		(shape15 = new ModelRenderer(this, 0, 109)).addBox(1.0f, 2.0f, -9.0f, 1, 8, 1);
		shape15.setPos(0.0f, 14.0f, 0.0f);
		shape15.setTexSize(64, 128);
		shape15.mirror = true;
		setRotation(shape15, 0.0f, 0.0f, 0.0f);
		(shape16 = new ModelRenderer(this, 0, 109)).addBox(7.0f, 2.0f, -9.0f, 1, 8, 1);
		shape16.setPos(0.0f, 14.0f, 0.0f);
		shape16.setTexSize(64, 128);
		shape16.mirror = true;
		setRotation(shape16, 0.0f, 0.0f, 0.0f);
		(shape17 = new ModelRenderer(this, 33, 39)).addBox(5.0f, -4.0f, -4.0f, 3, 5, 5);
		shape17.setPos(0.0f, 11.0f, 0.0f);
		shape17.setTexSize(64, 128);
		shape17.mirror = true;
		setRotation(shape17, 0.0f, 0.0f, 0.0f);
		(shape18 = new ModelRenderer(this, 48, 8)).addBox(5.5f, -7.5f, 2.0f, 3, 3, 4);
		shape18.setPos(0.0f, 11.0f, 0.0f);
		shape18.setTexSize(64, 128);
		shape18.mirror = true;
		setRotation(shape18, 0.4363323f, 0.0f, 0.0f);
		(shape19 = new ModelRenderer(this, 48, 8)).addBox(6.0f, -7.0f, -4.0f, 2, 2, 6);
		shape19.setPos(0.0f, 11.0f, 0.0f);
		shape19.setTexSize(64, 128);
		shape19.mirror = true;
		setRotation(shape19, 0.4363323f, 0.0f, 0.0f);
		(shape20 = new ModelRenderer(this, 48, 8)).addBox(-8.0f, -7.0f, -4.0f, 2, 2, 6);
		shape20.setPos(0.0f, 11.0f, 0.0f);
		shape20.setTexSize(64, 128);
		shape20.mirror = true;
		setRotation(shape20, 0.4363323f, 0.0f, 0.0f);
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
		shape20.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
}

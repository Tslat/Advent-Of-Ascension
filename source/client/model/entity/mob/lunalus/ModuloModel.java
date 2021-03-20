package net.tslat.aoa3.client.model.entity.mob.lunalus;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class ModuloModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer shape1;
	private final ModelRenderer body;
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

	public ModuloModel() {
		super(RenderType::entityTranslucent);
		texWidth = 128;
		texHeight = 64;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, 13.0f, 0.0f);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(shape1 = new ModelRenderer(this, 1, 48)).addBox(-7.5f, -9.0f, 7.5f, 15, 10, 0);
		shape1.setPos(0.0f, 13.0f, 0.0f);
		shape1.setTexSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 6, 5, 4);
		body.setPos(0.0f, 13.0f, 0.0f);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 61, 23)).addBox(-8.0f, -11.0f, -8.0f, 16, 2, 16);
		shape2.setPos(0.0f, 13.0f, 0.0f);
		shape2.setTexSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 34, 37)).addBox(-7.5f, -9.0f, -7.5f, 0, 10, 15);
		shape3.setPos(0.0f, 13.0f, 0.0f);
		shape3.setTexSize(64, 32);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 34, 37)).addBox(7.5f, -9.0f, -7.5f, 0, 10, 15);
		shape4.setPos(0.0f, 13.0f, 0.0f);
		shape4.setTexSize(64, 32);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 1, 48)).addBox(-7.5f, -9.0f, -7.5f, 15, 10, 0);
		shape5.setPos(0.0f, 13.0f, 0.0f);
		shape5.setTexSize(64, 32);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 21, 22)).addBox(-7.0f, 1.0f, 7.0f, 14, 4, 1);
		shape6.setPos(0.0f, 13.0f, 0.0f);
		shape6.setTexSize(64, 32);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
		(shape7 = new ModelRenderer(this, 21, 22)).addBox(-7.0f, 1.0f, -8.0f, 14, 4, 1);
		shape7.setPos(0.0f, 13.0f, 0.0f);
		shape7.setTexSize(64, 32);
		shape7.mirror = true;
		setRotation(shape7, 0.0f, 0.0f, 0.0f);
		(shape8 = new ModelRenderer(this, 21, 1)).addBox(7.0f, 1.0f, -8.0f, 1, 4, 16);
		shape8.setPos(0.0f, 13.0f, 0.0f);
		shape8.setTexSize(64, 32);
		shape8.mirror = true;
		setRotation(shape8, 0.0f, 0.0f, 0.0f);
		(shape9 = new ModelRenderer(this, 21, 1)).addBox(-8.0f, 1.0f, -8.0f, 1, 4, 16);
		shape9.setPos(0.0f, 13.0f, 0.0f);
		shape9.setTexSize(64, 32);
		shape9.mirror = true;
		setRotation(shape9, 0.0f, 0.0f, 0.0f);
		(shape10 = new ModelRenderer(this, 69, 43)).addBox(-2.0f, 6.0f, -10.0f, 4, 4, 1);
		shape10.setPos(0.0f, 13.0f, 0.0f);
		shape10.setTexSize(64, 32);
		shape10.mirror = true;
		setRotation(shape10, 0.0f, 0.0f, 0.0f);
		(shape11 = new ModelRenderer(this, 0, 28)).addBox(-8.0f, 5.0f, -8.0f, 16, 2, 16);
		shape11.setPos(0.0f, 13.0f, 0.0f);
		shape11.setTexSize(64, 32);
		shape11.mirror = true;
		setRotation(shape11, 0.0f, 0.0f, 0.0f);
		(shape12 = new ModelRenderer(this, 69, 42)).addBox(-1.0f, 7.0f, -15.0f, 2, 2, 16);
		shape12.setPos(0.0f, 13.0f, 0.0f);
		shape12.setTexSize(64, 32);
		shape12.mirror = true;
		setRotation(shape12, 0.0f, 0.0f, 0.0f);
		(shape13 = new ModelRenderer(this, 69, 43)).addBox(-2.0f, 6.0f, -14.0f, 4, 4, 1);
		shape13.setPos(0.0f, 13.0f, 0.0f);
		shape13.setTexSize(64, 32);
		shape13.mirror = true;
		setRotation(shape13, 0.0f, 0.0f, 0.0f);
		(shape14 = new ModelRenderer(this, 69, 43)).addBox(-2.0f, 6.0f, -12.0f, 4, 4, 1);
		shape14.setPos(0.0f, 13.0f, 0.0f);
		shape14.setTexSize(64, 32);
		shape14.mirror = true;
		setRotation(shape14, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
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
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
	}
}
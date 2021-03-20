package net.tslat.aoa3.client.model.entity.mob.precasia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class DyrehornModel extends EntityModel<MobEntity> {
	private final ModelRenderer part1;
	private final ModelRenderer part3;
	private final ModelRenderer part4;
	private final ModelRenderer part6;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer part7;
	private final ModelRenderer part8;
	private final ModelRenderer part9;
	private final ModelRenderer part10;
	private final ModelRenderer part11;
	private final ModelRenderer part12;
	private final ModelRenderer part20;
	private final ModelRenderer part13;
	private final ModelRenderer part14;
	private final ModelRenderer part15;
	private final ModelRenderer part16;
	private final ModelRenderer part17;
	private final ModelRenderer part18;
	private final ModelRenderer part19;

	public DyrehornModel() {
		texWidth = 128;
		texHeight = 64;
		(part1 = new ModelRenderer(this, 109, 21)).addBox(0.0f, -5.5f, 2.0f, 2, 2, 2);
		part1.setPos(-4.0f, -9.0f, -18.06667f);
		part1.setTexSize(128, 64);
		setRotation(part1, 0.3490659f, 0.0f, 0.0f);
		(part3 = new ModelRenderer(this, 0, 0)).addBox(0.5f, 0.0f, 0.0f, 4, 13, 6);
		part3.setPos(-2.0f, -1.0f, -19.0f);
		part3.setTexSize(128, 64);
		setRotation(part3, 0.6444293f, 0.0f, 0.0f);
		(part4 = new ModelRenderer(this, 0, 44)).addBox(0.0f, 0.0f, 0.0f, 9, 9, 11);
		part4.setPos(-4.0f, 3.0f, -12.0f);
		part4.setTexSize(128, 64);
		setRotation(part4, 0.0f, 0.0f, 0.0f);
		(part6 = new ModelRenderer(this, 55, 17)).addBox(0.0f, 0.0f, 0.0f, 7, 5, 3);
		part6.setPos(-3.0f, 9.0f, -5.0f);
		part6.setTexSize(128, 64);
		setRotation(part6, 0.9397927f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 58, 40)).addBox(-2.0f, 0.0f, -2.0f, 4, 20, 4);
		leg1.setPos(-3.0f, 4.0f, -9.0f);
		leg1.setTexSize(128, 64);
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 58, 40)).addBox(-2.0f, 0.0f, -2.0f, 4, 20, 4);
		leg2.setPos(4.0f, 4.0f, -9.0f);
		leg2.setTexSize(128, 64);
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 58, 40)).addBox(-2.0f, 0.0f, -2.0f, 4, 20, 4);
		leg3.setPos(4.0f, 4.0f, 10.0f);
		leg3.setTexSize(128, 64);
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 58, 40)).addBox(-2.0f, 0.0f, -2.0f, 4, 20, 4);
		leg4.setPos(-3.0f, 4.0f, 10.0f);
		leg4.setTexSize(128, 64);
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(part7 = new ModelRenderer(this, 51, 25)).addBox(2.0f, 0.0f, 0.0f, 9, 1, 7);
		part7.setPos(-6.0f, 2.0f, 5.0f);
		part7.setTexSize(128, 64);
		setRotation(part7, 0.0f, 0.0f, 0.0f);
		(part8 = new ModelRenderer(this, 74, 43)).addBox(0.0f, 0.0f, 0.0f, 3, 10, 2);
		part8.setPos(-1.0f, 3.0f, 10.0f);
		part8.setTexSize(128, 64);
		setRotation(part8, 0.4833219f, 0.0f, 0.0f);
		(part9 = new ModelRenderer(this, 45, 46)).addBox(1.0f, 0.0f, -6.0f, 1, 15, 3);
		part9.setPos(-1.0f, -10.0f, -12.0f);
		part9.setTexSize(128, 64);
		setRotation(part9, 0.669215f, 0.0f, 0.0f);
		(part10 = new ModelRenderer(this, 56, 0)).addBox(4.0f, 4.0f, -5.0f, 2, 2, 11);
		part10.setPos(-1.0f, -3.466667f, -25.0f);
		part10.setTexSize(128, 64);
		setRotation(part10, 0.3490659f, 0.0f, 0.0f);
		(part11 = new ModelRenderer(this, 109, 21)).addBox(0.0f, -5.5f, 2.0f, 2, 2, 2);
		part11.setPos(3.0f, -9.0f, -18.0f);
		part11.setTexSize(128, 64);
		setRotation(part11, 0.3490659f, 0.0f, 0.0f);
		(part12 = new ModelRenderer(this, 0, 25)).addBox(0.0f, 0.0f, 0.0f, 9, 7, 12);
		part12.setPos(-4.0f, 3.0f, -1.0f);
		part12.setTexSize(128, 64);
		setRotation(part12, 0.0f, 0.0f, 0.0f);
		(part20 = new ModelRenderer(this, 89, 7)).addBox(0.0f, -1.5f, 0.0f, 2, 8, 2);
		part20.setPos(3.0f, -9.0f, -18.0f);
		part20.setTexSize(128, 64);
		setRotation(part20, 0.3490659f, 0.0f, 0.0f);
		(part13 = new ModelRenderer(this, 89, 7)).addBox(0.0f, -1.5f, 0.0f, 2, 8, 2);
		part13.setPos(-4.0f, -9.0f, -18.06667f);
		part13.setTexSize(128, 64);
		setRotation(part13, 0.3490659f, 0.0f, 0.0f);
		(part14 = new ModelRenderer(this, 89, 21)).addBox(0.0f, -3.5f, -2.0f, 2, 2, 6);
		part14.setPos(3.0f, -9.0f, -18.0f);
		part14.setTexSize(128, 64);
		setRotation(part14, 0.3490659f, 0.0f, 0.0f);
		(part15 = new ModelRenderer(this, 89, 21)).addBox(0.0f, -3.5f, -2.0f, 2, 2, 6);
		part15.setPos(-4.0f, -9.0f, -18.06667f);
		part15.setTexSize(128, 64);
		setRotation(part15, 0.3490659f, 0.0f, 0.0f);
		(part16 = new ModelRenderer(this, 109, 21)).addBox(0.0f, -5.5f, -2.0f, 2, 2, 2);
		part16.setPos(3.0f, -9.0f, -18.0f);
		part16.setTexSize(128, 64);
		setRotation(part16, 0.3490659f, 0.0f, 0.0f);
		(part17 = new ModelRenderer(this, 109, 21)).addBox(0.0f, -5.5f, -2.0f, 2, 2, 2);
		part17.setPos(-4.0f, -9.0f, -18.06667f);
		part17.setTexSize(128, 64);
		setRotation(part17, 0.3490659f, 0.0f, 0.0f);
		(part18 = new ModelRenderer(this, 22, 0)).addBox(-1.0f, 0.0f, 0.0f, 5, 5, 11);
		part18.setPos(-1.0f, -3.466667f, -25.0f);
		part18.setTexSize(128, 64);
		setRotation(part18, 0.3490659f, 0.0f, 0.0f);
		(part19 = new ModelRenderer(this, 56, 0)).addBox(-3.0f, 4.0f, -5.0f, 2, 2, 11);
		part19.setPos(-1.0f, -3.466667f, -25.0f);
		part19.setTexSize(128, 64);
		setRotation(part19, 0.3490659f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		part1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part20.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part14.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part15.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part16.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part17.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part18.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		part19.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}

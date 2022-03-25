package net.tslat.aoa3.client.model.entity.projectile;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.tslat.aoa3.content.entity.projectile.blaster.ArcwormShotEntity;

public class ArcwormShotModel extends EntityModel<ArcwormShotEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer body5;
	private final ModelRenderer body6;
	private final ModelRenderer body7;
	private final ModelRenderer leg5;
	private final ModelRenderer leg6;
	private final ModelRenderer leg7;
	private final ModelRenderer leg8;
	private final ModelRenderer body8;
	private final ModelRenderer body9;

	public ArcwormShotModel() {
		texWidth = 64;
		texHeight = 64;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6);
		head.setPos(0.0F, 14.0F, -8.0F);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 23, 10);
		body.addBox(-6.0F, 14.0F, -7.0F, 12, 5, 8);
		body.setPos(0.0F, 11.0F, 2.0F);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 1.570796F, 0.0F, 0.0F);
		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-3.0F, 0.0F, -2.0F, 4, 6, 4);
		leg1.setPos(-3.0F, 18.0F, 19.0F);
		leg1.setTexSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0F, 0.0F, 0.0F);
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-1.0F, 0.0F, -2.0F, 4, 6, 4);
		leg2.setPos(3.0F, 18.0F, 19.0F);
		leg2.setTexSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0F, 0.0F, 0.0F);
		leg3 = new ModelRenderer(this, 0, 16);
		leg3.addBox(-3.0F, 0.0F, -3.0F, 4, 6, 4);
		leg3.setPos(-3.0F, 18.0F, 12.0F);
		leg3.setTexSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0F, 0.0F, 0.0F);
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.addBox(-1.0F, 0.0F, -3.0F, 4, 6, 4);
		leg4.setPos(3.0F, 18.0F, 12.0F);
		leg4.setTexSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 23, 25);
		body2.addBox(-4.0F, -5.0F, -6.0F, 8, 3, 6);
		body2.setPos(0.0F, 11.0F, 2.0F);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796F, 0.0F, 0.0F);
		body3 = new ModelRenderer(this, 23, 25);
		body3.addBox(-4.0F, 3.0F, -6.0F, 8, 3, 6);
		body3.setPos(0.0F, 11.0F, 2.0F);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 1.570796F, 0.0F, 0.0F);
		body4 = new ModelRenderer(this, 23, 25);
		body4.addBox(-4.0F, 11.0F, -6.0F, 8, 3, 6);
		body4.setPos(0.0F, 11.0F, 2.0F);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 1.570796F, 0.0F, 0.0F);
		body5 = new ModelRenderer(this, 23, 35);
		body5.addBox(-7.5F, -17.0F, -7.0F, 16, 16, 0);
		body5.setPos(5.0F, 11.0F, -1.0F);
		body5.setTexSize(64, 32);
		body5.mirror = true;
		setRotation(body5, 0.0F, 0.7853982F, 0.0F);
		body6 = new ModelRenderer(this, 23, 10);
		body6.addBox(-6.0F, -2.0F, -7.0F, 12, 5, 8);
		body6.setPos(0.0F, 11.0F, 2.0F);
		body6.setTexSize(64, 32);
		body6.mirror = true;
		setRotation(body6, 1.570796F, 0.0F, 0.0F);
		body7 = new ModelRenderer(this, 23, 10);
		body7.addBox(-6.0F, 6.0F, -7.0F, 12, 5, 8);
		body7.setPos(0.0F, 11.0F, 2.0F);
		body7.setTexSize(64, 32);
		body7.mirror = true;
		setRotation(body7, 1.570796F, 0.0F, 0.0F);
		leg5 = new ModelRenderer(this, 0, 16);
		leg5.addBox(-1.0F, 0.0F, -2.0F, 4, 6, 4);
		leg5.setPos(3.0F, 18.0F, 3.0F);
		leg5.setTexSize(64, 32);
		leg5.mirror = true;
		setRotation(leg5, 0.0F, 0.0F, 0.0F);
		leg6 = new ModelRenderer(this, 0, 16);
		leg6.addBox(-3.0F, 0.0F, -2.0F, 4, 6, 4);
		leg6.setPos(-3.0F, 18.0F, 3.0F);
		leg6.setTexSize(64, 32);
		leg6.mirror = true;
		setRotation(leg6, 0.0F, 0.0F, 0.0F);
		leg7 = new ModelRenderer(this, 0, 16);
		leg7.addBox(-1.0F, 0.0F, -3.0F, 4, 6, 4);
		leg7.setPos(3.0F, 18.0F, -4.0F);
		leg7.setTexSize(64, 32);
		leg7.mirror = true;
		setRotation(leg7, 0.0F, 0.0F, 0.0F);
		leg8 = new ModelRenderer(this, 0, 16);
		leg8.addBox(-3.0F, 0.0F, -3.0F, 4, 6, 4);
		leg8.setPos(-3.0F, 18.0F, -4.0F);
		leg8.setTexSize(64, 32);
		leg8.mirror = true;
		setRotation(leg8, 0.0F, 0.0F, 0.0F);
		body8 = new ModelRenderer(this, 23, 10);
		body8.addBox(-6.0F, -10.0F, -7.0F, 12, 5, 8);
		body8.setPos(0.0F, 11.0F, 2.0F);
		body8.setTexSize(64, 32);
		body8.mirror = true;
		setRotation(body8, 1.570796F, 0.0F, 0.0F);
		body9 = new ModelRenderer(this, 23, 35);
		body9.addBox(-8.5F, -17.0F, -7.0F, 16, 16, 0);
		body9.setPos(-4.0F, 11.0F, -1.0F);
		body9.setTexSize(64, 32);
		body9.mirror = true;
		setRotation(body9, 0.0F, -0.7853982F, 0.0F);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(ArcwormShotEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}
}

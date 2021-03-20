package net.tslat.aoa3.client.model.entity.mob.shyrelands;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class LuxocronModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer body2;
	private final ModelRenderer body3;
	private final ModelRenderer body4;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer head6;

	public LuxocronModel() {
		texWidth = 64;
		texHeight = 64;

		head = new ModelRenderer(this, 35, 4);
		head.addBox(-6.0F, -7.0F, -4.0F, 12, 1, 2);
		head.setPos(0.0F, 12.0F, -8.0F);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 22, 34);
		body.addBox(-1.0F, -7.0F, -7.0F, 2, 14, 4);
		body.setPos(0.0F, 4.0F, 8.0F);
		body.setTexSize(64, 32);
		body.mirror = true;
		setRotation(body, 2.007129F, 0.0F, 0.0F);
		leg1 = new ModelRenderer(this, 0, 16);
		leg1.addBox(-3.0F, 0.0F, -2.0F, 4, 8, 4);
		leg1.setPos(-3.0F, 16.0F, 7.0F);
		leg1.setTexSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0F, 0.0F, 0.0F);
		leg2 = new ModelRenderer(this, 0, 16);
		leg2.addBox(-1.0F, 0.0F, -2.0F, 4, 8, 4);
		leg2.setPos(3.0F, 16.0F, 7.0F);
		leg2.setTexSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0F, 0.0F, 0.0F);
		leg3 = new ModelRenderer(this, 0, 16);
		leg3.addBox(-3.0F, 0.0F, -3.0F, 4, 8, 4);
		leg3.setPos(-3.0F, 16.0F, -5.0F);
		leg3.setTexSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0F, 0.0F, 0.0F);
		leg4 = new ModelRenderer(this, 0, 16);
		leg4.addBox(-1.0F, 0.0F, -3.0F, 4, 8, 4);
		leg4.setPos(3.0F, 16.0F, -5.0F);
		leg4.setTexSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 22, 8);
		body2.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 7);
		body2.setPos(0.0F, 9.0F, 2.0F);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 1.570796F, 0.0F, 0.0F);
		body3 = new ModelRenderer(this, 22, 34);
		body3.addBox(-1.0F, -7.0F, -7.0F, 2, 14, 4);
		body3.setPos(0.0F, 2.0F, -9.0F);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 2.879793F, 0.0F, 0.0F);
		body4 = new ModelRenderer(this, 22, 34);
		body4.addBox(-1.0F, -7.0F, -7.0F, 2, 14, 4);
		body4.setPos(0.0F, 2.0F, 0.0F);
		body4.setTexSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 2.356194F, 0.0F, 0.0F);
		head2 = new ModelRenderer(this, 0, 0);
		head2.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6);
		head2.setPos(0.0F, 12.0F, -8.0F);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0F, 0.0F, 0.0F);
		head3 = new ModelRenderer(this, 58, 9);
		head3.addBox(-5.5F, -6.0F, -3.5F, 1, 3, 1);
		head3.setPos(0.0F, 12.0F, -8.0F);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0F, 0.0F, 0.0F);
		head4 = new ModelRenderer(this, 58, 9);
		head4.addBox(4.5F, -6.0F, -3.5F, 1, 3, 1);
		head4.setPos(0.0F, 12.0F, -8.0F);
		head4.setTexSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0F, 0.0F, 0.0F);
		head5 = new ModelRenderer(this, 25, 0);
		head5.addBox(-6.0F, -3.0F, -4.0F, 2, 2, 2);
		head5.setPos(0.0F, 12.0F, -8.0F);
		head5.setTexSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0.0F, 0.0F, 0.0F);
		head6 = new ModelRenderer(this, 25, 0);
		head6.addBox(4.0F, -3.0F, -4.0F, 2, 2, 2);
		head6.setPos(0.0F, 12.0F, -8.0F);
		head6.setTexSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0.0F, 0.0F, 0.0F);
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
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		leg1.yRot = 0.0F;

		leg3.xRot = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		leg3.yRot = 0.0F;

		leg2.xRot = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);
		leg4.xRot = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);
	}
}

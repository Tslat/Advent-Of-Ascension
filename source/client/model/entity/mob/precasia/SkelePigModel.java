package net.tslat.aoa3.client.model.entity.mob.precasia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class SkelePigModel extends EntityModel<MobEntity> {
	private ModelRenderer head1;
	private ModelRenderer body3;
	private ModelRenderer leg1;
	private ModelRenderer leg2;
	private ModelRenderer leg4;
	private ModelRenderer head2;
	private ModelRenderer head3;
	private ModelRenderer body1;
	private ModelRenderer body2;
	private ModelRenderer leg3;

	public SkelePigModel() {
		texWidth = 64;
		texHeight = 32;
		(head1 = new ModelRenderer(this, 36, 0)).addBox(3.0f, 4.0f, -12.0f, 1, 1, 6);
		head1.setPos(0.0f, 12.0f, -6.0f);
		head1.setTexSize(64, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(body3 = new ModelRenderer(this, 16, 24)).addBox(0.0f, -5.0f, -7.0f, 2, 4, 4);
		body3.setPos(-1.0f, 11.0f, 10.0f);
		body3.setTexSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0.0f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg1.setPos(-3.0f, 18.0f, 7.0f);
		leg1.setTexSize(64, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg2.setPos(3.0f, 18.0f, 7.0f);
		leg2.setTexSize(64, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg4.setPos(-3.0f, 18.0f, -5.0f);
		leg4.setTexSize(64, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -8.0f, 8, 8, 8);
		head2.setPos(0.0f, 12.0f, -6.0f);
		head2.setTexSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 36, 0)).addBox(-4.0f, 4.0f, -12.0f, 1, 1, 6);
		head3.setPos(0.0f, 12.0f, -6.0f);
		head3.setTexSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(body1 = new ModelRenderer(this, 28, 8)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body1.setPos(0.0f, 11.0f, 3.0f);
		body1.setTexSize(64, 32);
		body1.mirror = true;
		setRotation(body1, 1.570796f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 16, 24)).addBox(0.0f, -5.0f, -7.0f, 2, 4, 4);
		body2.setPos(-1.0f, 11.0f, 3.0f);
		body2.setTexSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4);
		leg3.setPos(3.0f, 18.0f, -5.0f);
		leg3.setTexSize(64, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
	}

	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head1.yRot = netHeadYaw / 57.295776f;
		head1.xRot = headPitch / 54.11268f;
		head2.yRot = netHeadYaw / 57.295776f;
		head2.xRot = headPitch / 54.11268f;
		head3.yRot = netHeadYaw / 57.295776f;
		head3.xRot = headPitch / 54.11268f;
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1.yRot = 0.0f;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.yRot = 0.0f;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

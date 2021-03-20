package net.tslat.aoa3.client.model.entity.mob.precasia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class TerradonModel extends EntityModel<MobEntity> {
	private final ModelRenderer head1;
	private final ModelRenderer body;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer head6;
	private final ModelRenderer head7;

	public TerradonModel() {
		texWidth = 128;
		texHeight = 32;
		(head1 = new ModelRenderer(this, 105, 5)).addBox(3.0f, -6.0f, -10.0f, 2, 2, 6);
		head1.setPos(0.0f, 12.0f, -6.0f);
		head1.setTexSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 28, 8)).addBox(-5.0f, -10.0f, -7.0f, 10, 16, 8);
		body.setPos(0.0f, 11.0f, 3.0f);
		body.setTexSize(128, 32);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		leg1.setPos(-4.0f, 18.0f, 7.0f);
		leg1.setTexSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		leg2.setPos(4.0f, 18.0f, 7.0f);
		leg2.setTexSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		leg3.setPos(-4.0f, 18.0f, -5.0f);
		leg3.setTexSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 6, 6, 6);
		leg4.setPos(4.0f, 18.0f, -5.0f);
		leg4.setTexSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 97, 19)).addBox(-5.0f, -3.0f, -9.0f, 10, 8, 5);
		head2.setPos(0.0f, 12.0f, -6.0f);
		head2.setTexSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 106, 0)).addBox(-1.0f, -1.0f, -15.0f, 2, 2, 2);
		head3.setPos(0.0f, 12.0f, -6.0f);
		head3.setTexSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 105, 5)).addBox(-5.0f, -6.0f, -10.0f, 2, 2, 6);
		head4.setPos(0.0f, 12.0f, -6.0f);
		head4.setTexSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 66, 26)).addBox(-3.0f, 1.0f, -15.0f, 6, 3, 3);
		head5.setPos(0.0f, 12.0f, -6.0f);
		head5.setTexSize(128, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 66, 19)).addBox(-4.0f, 0.0f, -12.0f, 8, 4, 3);
		head6.setPos(0.0f, 12.0f, -6.0f);
		head6.setTexSize(128, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 67, 1)).addBox(-7.0f, -8.0f, -4.0f, 14, 14, 3);
		head7.setPos(0.0f, 12.0f, -6.0f);
		head7.setTexSize(128, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head1.yRot = netHeadYaw / 79.57747f;
		head1.xRot = headPitch / 76.39437f;
		head2.yRot = netHeadYaw / 79.57747f;
		head2.xRot = headPitch / 76.39437f;
		head3.yRot = netHeadYaw / 79.57747f;
		head3.xRot = headPitch / 76.39437f;
		head4.yRot = netHeadYaw / 79.57747f;
		head4.xRot = headPitch / 76.39437f;
		head5.yRot = netHeadYaw / 79.57747f;
		head5.xRot = headPitch / 76.39437f;
		head6.yRot = netHeadYaw / 79.57747f;
		head6.xRot = headPitch / 76.39437f;
		head7.yRot = netHeadYaw / 79.57747f;
		head7.xRot = headPitch / 76.39437f;
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}

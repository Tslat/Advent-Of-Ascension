package net.tslat.aoa3.client.model.entity.mob.runandor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class BouncerModel extends EntityModel<MobEntity> {
	private final ModelRenderer body;
	private final ModelRenderer legp1;
	private final ModelRenderer legp2;
	private final ModelRenderer legp3;
	private final ModelRenderer legp4;
	private final ModelRenderer head;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;

	public BouncerModel() {
		textureWidth = 64;
		textureHeight = 32;
		(body = new ModelRenderer(this, 16, 16)).addBox(-4.0f, 0.0f, 0.0f, 8, 9, 4);
		body.setRotationPoint(0.0f, 4.0f, -1.0f);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(legp1 = new ModelRenderer(this, 47, 24)).addBox(1.0f, 12.0f, -6.0f, 2, 2, 6);
		legp1.setRotationPoint(0.0f, 10.0f, 0.0f);
		legp1.setTextureSize(64, 32);
		legp1.mirror = true;
		setRotation(legp1, 0.0f, 0.0f, 0.0f);
		(legp2 = new ModelRenderer(this, 33, 1)).addBox(-1.0f, 1.0f, -1.0f, 2, 9, 4);
		legp2.setRotationPoint(0.0f, 10.0f, 0.0f);
		legp2.setTextureSize(64, 32);
		legp2.mirror = true;
		setRotation(legp2, -0.3490659f, 0.0f, 0.0f);
		(legp3 = new ModelRenderer(this, 47, 13)).addBox(-2.0f, 7.0f, -6.0f, 4, 6, 4);
		legp3.setRotationPoint(0.0f, 10.0f, 0.0f);
		legp3.setTextureSize(64, 32);
		legp3.mirror = true;
		setRotation(legp3, 0.1745329f, 0.0f, 0.0f);
		(legp4 = new ModelRenderer(this, 47, 24)).addBox(-3.0f, 12.0f, -6.0f, 2, 2, 6);
		legp4.setRotationPoint(0.0f, 10.0f, 0.0f);
		legp4.setTextureSize(64, 32);
		legp4.mirror = true;
		setRotation(legp4, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 9.0f, -5.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 16)).addBox(-4.0f, -8.0f, 4.0f, 0, 8, 8);
		head2.setRotationPoint(8.0f, 17.0f, -5.0f);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, -1.570796f, 0.0f);
		(head3 = new ModelRenderer(this, 0, 16)).addBox(-4.0f, -8.0f, -4.0f, 0, 8, 8);
		head3.setRotationPoint(0.0f, 17.0f, -5.0f);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 0, 16)).addBox(-4.0f, -8.0f, -4.0f, 0, 8, 8);
		head4.setRotationPoint(8.0f, 17.0f, -5.0f);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		legp1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		legp2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		legp3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		legp4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		legp1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		legp2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount - 0.349f;
		legp3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount + 0.174f;
		legp4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

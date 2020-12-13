package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class RammerheadModel extends EntityModel<MobEntity> {
	private final ModelRenderer eye1;
	private final ModelRenderer fin1;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer headbar;
	private final ModelRenderer neck;
	private final ModelRenderer eye2;
	private final ModelRenderer body;
	private final ModelRenderer fin2;

	public RammerheadModel() {
		textureWidth = 128;
		textureHeight = 32;
		(eye1 = new ModelRenderer(this, 57, 12)).addBox(-16.0f, -4.0f, -9.0f, 4, 4, 6);
		eye1.setRotationPoint(0.0f, 14.0f, -6.0f);
		eye1.setTextureSize(128, 32);
		eye1.mirror = true;
		setRotation(eye1, 0.0f, 0.0f, 0.0f);
		(fin1 = new ModelRenderer(this, 30, 10)).addBox(-4.0f, -9.0f, -7.0f, 2, 15, 7);
		fin1.setRotationPoint(6.0f, 6.0f, 2.0f);
		fin1.setTextureSize(128, 32);
		fin1.mirror = true;
		setRotation(fin1, 2.007129f, 0.0f, 0.0f);
		(leg1 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -2.0f, 4, 8, 4);
		leg1.setRotationPoint(-3.0f, 16.0f, 7.0f);
		leg1.setTextureSize(128, 32);
		leg1.mirror = true;
		setRotation(leg1, 0.0f, 0.0f, 0.0f);
		(leg2 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -2.0f, 4, 8, 4);
		leg2.setRotationPoint(3.0f, 16.0f, 7.0f);
		leg2.setTextureSize(128, 32);
		leg2.mirror = true;
		setRotation(leg2, 0.0f, 0.0f, 0.0f);
		(leg3 = new ModelRenderer(this, 0, 16)).addBox(-3.0f, 0.0f, -3.0f, 4, 8, 4);
		leg3.setRotationPoint(-3.0f, 16.0f, -5.0f);
		leg3.setTextureSize(128, 32);
		leg3.mirror = true;
		setRotation(leg3, 0.0f, 0.0f, 0.0f);
		(leg4 = new ModelRenderer(this, 0, 16)).addBox(-1.0f, 0.0f, -3.0f, 4, 8, 4);
		leg4.setRotationPoint(3.0f, 16.0f, -5.0f);
		leg4.setTextureSize(128, 32);
		leg4.mirror = true;
		setRotation(leg4, 0.0f, 0.0f, 0.0f);
		(headbar = new ModelRenderer(this, 23, 0)).addBox(-12.0f, -4.0f, -9.0f, 24, 4, 4);
		headbar.setRotationPoint(0.0f, 14.0f, -6.0f);
		headbar.setTextureSize(128, 32);
		headbar.mirror = true;
		setRotation(headbar, 0.0f, 0.0f, 0.0f);
		(neck = new ModelRenderer(this, 51, 24)).addBox(-2.0f, -4.0f, -5.0f, 4, 4, 3);
		neck.setRotationPoint(0.0f, 14.0f, -6.0f);
		neck.setTextureSize(128, 32);
		neck.mirror = true;
		setRotation(neck, 0.0f, 0.0f, 0.0f);
		(eye2 = new ModelRenderer(this, 0, 0)).addBox(12.0f, -4.0f, -9.0f, 4, 4, 6);
		eye2.setRotationPoint(0.0f, 14.0f, -6.0f);
		eye2.setTextureSize(128, 32);
		eye2.mirror = true;
		setRotation(eye2, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 88, 7)).addBox(-6.0f, -10.0f, -7.0f, 12, 17, 7);
		body.setRotationPoint(0.0f, 9.0f, 2.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 1.570796f, 0.0f, 0.0f);
		(fin2 = new ModelRenderer(this, 30, 10)).addBox(-4.0f, -9.0f, -7.0f, 2, 15, 7);
		fin2.setRotationPoint(0.0f, 6.0f, 2.0f);
		fin2.setTextureSize(128, 32);
		fin2.mirror = true;
		setRotation(fin2, 2.007129f, 0.0f, 0.0f);
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg1.rotateAngleY = 0.0f;
		leg3.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg3.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.rotateAngleX = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		eye1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		fin1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leg4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		headbar.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		neck.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		eye2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		fin2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

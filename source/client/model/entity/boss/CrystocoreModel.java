package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.tslat.aoa3.entity.boss.CrystocoreEntity;
import org.lwjgl.opengl.GL11;

public class CrystocoreModel extends EntityModel<CrystocoreEntity> {
	private final ModelRenderer shape1;
	private final ModelRenderer Center;
	private final ModelRenderer shape2;
	private final ModelRenderer shape3;
	private final ModelRenderer shape4;
	private final ModelRenderer shape5;
	private final ModelRenderer shape6;

	public CrystocoreModel() {
		textureWidth = 128;
		textureHeight = 128;
		(shape1 = new ModelRenderer(this, 42, 88)).addBox(-20.0f, 14.0f, -5.0f, 10, 14, 10);
		shape1.setRotationPoint(0.0f, -4.0f, 0.0f);
		shape1.setTextureSize(128, 128);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(Center = new ModelRenderer(this, 0, 0)).addBox(-10.0f, -10.0f, -10.0f, 20, 20, 20);
		Center.setRotationPoint(0.0f, -5.0f, 0.0f);
		Center.setTextureSize(128, 128);
		Center.mirror = true;
		setRotation(Center, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 42)).addBox(-10.0f, 15.0f, -4.0f, 20, 8, 8);
		shape2.setRotationPoint(0.0f, -4.0f, 0.0f);
		shape2.setTextureSize(128, 128);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 88)).addBox(10.0f, 14.0f, -5.0f, 10, 10, 10);
		shape3.setRotationPoint(0.0f, -4.0f, 0.0f);
		shape3.setTextureSize(128, 128);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 42, 61)).addBox(10.0f, -29.0f, -5.0f, 10, 14, 10);
		shape4.setRotationPoint(0.0f, -5.0f, 0.0f);
		shape4.setTextureSize(128, 128);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 0, 42)).addBox(-10.0f, -24.0f, -4.0f, 20, 8, 8);
		shape5.setRotationPoint(0.0f, -5.0f, 0.0f);
		shape5.setTextureSize(128, 128);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 0, 61)).addBox(-20.0f, -25.0f, -5.0f, 10, 10, 10);
		shape6.setRotationPoint(0.0f, -5.0f, 0.0f);
		shape6.setTextureSize(128, 128);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Center.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		GlStateManager.disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(CrystocoreEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Center.rotateAngleY = netHeadYaw / (float)(180f / Math.PI);
		shape1.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape2.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape3.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape4.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape5.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape6.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		shape1.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		shape2.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		shape3.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		shape4.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		shape5.rotateAngleY = ageInTicks * 0.067f * 1.25f;
		shape6.rotateAngleY = ageInTicks * 0.067f * 1.25f;
	}
}

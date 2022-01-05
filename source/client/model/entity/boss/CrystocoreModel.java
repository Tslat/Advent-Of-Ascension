package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.tslat.aoa3.object.entity.boss.CrystocoreEntity;
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
		texWidth = 128;
		texHeight = 128;
		(shape1 = new ModelRenderer(this, 42, 88)).addBox(-20.0f, 14.0f, -5.0f, 10, 14, 10);
		shape1.setPos(0.0f, -4.0f, 0.0f);
		shape1.setTexSize(128, 128);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(Center = new ModelRenderer(this, 0, 0)).addBox(-10.0f, -10.0f, -10.0f, 20, 20, 20);
		Center.setPos(0.0f, -5.0f, 0.0f);
		Center.setTexSize(128, 128);
		Center.mirror = true;
		setRotation(Center, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 0, 42)).addBox(-10.0f, 15.0f, -4.0f, 20, 8, 8);
		shape2.setPos(0.0f, -4.0f, 0.0f);
		shape2.setTexSize(128, 128);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
		(shape3 = new ModelRenderer(this, 0, 88)).addBox(10.0f, 14.0f, -5.0f, 10, 10, 10);
		shape3.setPos(0.0f, -4.0f, 0.0f);
		shape3.setTexSize(128, 128);
		shape3.mirror = true;
		setRotation(shape3, 0.0f, 0.0f, 0.0f);
		(shape4 = new ModelRenderer(this, 42, 61)).addBox(10.0f, -29.0f, -5.0f, 10, 14, 10);
		shape4.setPos(0.0f, -5.0f, 0.0f);
		shape4.setTexSize(128, 128);
		shape4.mirror = true;
		setRotation(shape4, 0.0f, 0.0f, 0.0f);
		(shape5 = new ModelRenderer(this, 0, 42)).addBox(-10.0f, -24.0f, -4.0f, 20, 8, 8);
		shape5.setPos(0.0f, -5.0f, 0.0f);
		shape5.setTexSize(128, 128);
		shape5.mirror = true;
		setRotation(shape5, 0.0f, 0.0f, 0.0f);
		(shape6 = new ModelRenderer(this, 0, 61)).addBox(-20.0f, -25.0f, -5.0f, 10, 10, 10);
		shape6.setPos(0.0f, -5.0f, 0.0f);
		shape6.setTexSize(128, 128);
		shape6.mirror = true;
		setRotation(shape6, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		GlStateManager._pushMatrix();
		GlStateManager._enableBlend();
		GlStateManager._blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Center.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		GlStateManager._disableBlend();
		GL11.glPopMatrix();
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(CrystocoreEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		Center.yRot = netHeadYaw / (float)(180f / Math.PI);
		shape1.zRot = ageInTicks * 0.067f * 1.25f;
		shape2.zRot = ageInTicks * 0.067f * 1.25f;
		shape3.zRot = ageInTicks * 0.067f * 1.25f;
		shape4.zRot = ageInTicks * 0.067f * 1.25f;
		shape5.zRot = ageInTicks * 0.067f * 1.25f;
		shape6.zRot = ageInTicks * 0.067f * 1.25f;
		shape1.yRot = ageInTicks * 0.067f * 1.25f;
		shape2.yRot = ageInTicks * 0.067f * 1.25f;
		shape3.yRot = ageInTicks * 0.067f * 1.25f;
		shape4.yRot = ageInTicks * 0.067f * 1.25f;
		shape5.yRot = ageInTicks * 0.067f * 1.25f;
		shape6.yRot = ageInTicks * 0.067f * 1.25f;
	}
}

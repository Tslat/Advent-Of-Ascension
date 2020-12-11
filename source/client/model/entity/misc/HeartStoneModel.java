package net.tslat.aoa3.client.model.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HeartStoneModel extends EntityModel<Entity> {
	private final ModelRenderer box1;

	public HeartStoneModel() {
		textureWidth = 16;
		textureHeight = 16;

		box1 = new ModelRenderer(this);
		box1.setRotationPoint(0.0F, 24.0F, 0.0F);
		box1.setTextureOffset(0, 10).addBox(-0.75F, -5.75F, 0F, 2, 1, 0, 0.0F, false);
		box1.setTextureOffset(0, 8).addBox(0.5F, -5.0F, 0F, 1, 2, 0, 0.0F, false);
		box1.setTextureOffset(0, 9).addBox(-1.25F, -3.75F, 0F, 2, 1, 0, 0.0F, false);
		box1.setTextureOffset(0, 8).addBox(-1.5F, -5.5F, 0F, 1, 2, 0, 0.0F, false);

		ModelRenderer box2 = new ModelRenderer(this);
		box2.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotationAngle(box2, 0.0F, 0.0F, -0.7854F);
		box1.addChild(box2);
		box2.setTextureOffset(4, 6).addBox(1.0F, -2.0F, 0.0F, 3, 1, 1, 0.0F, false);
		box2.setTextureOffset(4, 0).addBox(4.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false);
		box2.setTextureOffset(0, 0).addBox(1.0F, -5.0F, 0.0F, 1, 3, 1, 0.0F, false);
		box2.setTextureOffset(0, 6).addBox(2.0F, -5.0F, 0.0F, 3, 1, 1, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		box1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		box1.rotateAngleY = ageInTicks * 0.167f * 1.25f;
	}
}
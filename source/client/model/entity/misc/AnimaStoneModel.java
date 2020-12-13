package net.tslat.aoa3.client.model.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AnimaStoneModel extends EntityModel<Entity> {
	private final ModelRenderer box1;

	public AnimaStoneModel() {
		textureWidth = 16;
		textureHeight = 16;

		box1 = new ModelRenderer(this);
		box1.setRotationPoint(0.0F, 24.0F, 0.0F);
		box1.setTextureOffset(0, 6).addBox(-0.5F, -6.0F, -0.5F, 1, 1, 1, 0.0F, false);
		box1.setTextureOffset(0, 6).addBox(-1.5F, -5.0F, -0.5F, 1, 1, 1, 0.0F, false);
		box1.setTextureOffset(0, 6).addBox(0.5F, -5.0F, -0.5F, 1, 1, 1, 0.0F, false);
		box1.setTextureOffset(0, 6).addBox(-0.5F, -4.0F, -0.5F, 1, 1, 1, 0.0F, false);

		ModelRenderer box2 = new ModelRenderer(this);
		box2.setRotationPoint(0.0F, -3.0F, -0.5F);
		setRotationAngle(box2, 0.0F, 0.0F, -0.7854F);
		box1.addChild(box2);
		box2.setTextureOffset(0, 5).addBox(0.0707F, -2.0707F, 0.0F, 2, 2, 1, 0.0F, false);
		box2.setTextureOffset(12, 2).addBox(0.9572F, 0.5783F, 0.0F, 1, 1, 1, 0.0F, false);
		box2.setTextureOffset(12, 0).addBox(0.1641F, -3.6996F, 0.0F, 1, 1, 1, 0.0F, false);
		box2.setTextureOffset(0, 4).addBox(-1.5783F, -1.9572F, 0.0F, 1, 1, 1, 0.0F, false);
		box2.setTextureOffset(6, 0).addBox(2.6996F, -1.9572F, 0.0F, 1, 1, 1, 0.0F, false);
		box2.setTextureOffset(0, 4).addBox(-1.5783F, -1.1641F, 0.0F, 1, 1, 1, 0.0F, false);
		box2.setTextureOffset(12, 0).addBox(0.9572F, -3.6996F, 0.0F, 1, 1, 1, 0.0F, false);
		box2.setTextureOffset(7, 0).addBox(2.6996F, -1.1641F, 0.0F, 1, 1, 1, 0.0F, false);
		box2.setTextureOffset(12, 2).addBox(0.1641F, 0.5783F, 0.0F, 1, 1, 1, 0.0F, false);

		ModelRenderer box3 = new ModelRenderer(this);
		box3.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotationAngle(box3, 0.0F, 0.0F, -0.5236F);
		box1.addChild(box3);
		box3.setTextureOffset(0, 0).addBox(0.701F, -6.2141F, 0.0F, 1, 2, 1, 0.0F, true);
		box3.setTextureOffset(0, 3).addBox(2.799F, -3.5801F, 0.0F, 1, 2, 1, 0.0F, false);
		box3.setTextureOffset(10, 5).addBox(-0.067F, -3.3481F, 0.0F, 2, 1, 1, 0.0F, false);
		box3.setTextureOffset(5, 0).addBox(2.567F, -5.4462F, 0.0F, 2, 1, 1, 0.0F, true);

		ModelRenderer box4 = new ModelRenderer(this);
		box4.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotationAngle(box4, 0.0F, 0.0F, -1.0472F);
		box1.addChild(box4);
		box4.setTextureOffset(0, 0).addBox(4.4462F, -4.567F, 0.0F, 1, 2, 1, 0.0F, false);
		box4.setTextureOffset(12, 2).addBox(2.3481F, -1.933F, 0.0F, 1, 2, 1, 0.0F, false);
		box4.setTextureOffset(0, 4).addBox(1.5801F, -3.799F, 0.0F, 2, 1, 1, 0.0F, false);
		box4.setTextureOffset(5, 0).addBox(4.2141F, -1.701F, 0.0F, 2, 1, 1, 0.0F, true);
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
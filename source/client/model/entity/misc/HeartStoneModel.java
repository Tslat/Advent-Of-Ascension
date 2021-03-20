package net.tslat.aoa3.client.model.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HeartStoneModel extends EntityModel<Entity> {
	private final ModelRenderer box1;

	public HeartStoneModel() {
		texWidth = 16;
		texHeight = 16;

		box1 = new ModelRenderer(this);
		box1.setPos(0.0F, 24.0F, 0.0F);
		box1.texOffs(0, 10).addBox(-0.75F, -5.75F, 0F, 2, 1, 0, 0.0F, false);
		box1.texOffs(0, 8).addBox(0.5F, -5.0F, 0F, 1, 2, 0, 0.0F, false);
		box1.texOffs(0, 9).addBox(-1.25F, -3.75F, 0F, 2, 1, 0, 0.0F, false);
		box1.texOffs(0, 8).addBox(-1.5F, -5.5F, 0F, 1, 2, 0, 0.0F, false);

		ModelRenderer box2 = new ModelRenderer(this);
		box2.setPos(0.0F, 0.0F, -0.5F);
		setRotationAngle(box2, 0.0F, 0.0F, -0.7854F);
		box1.addChild(box2);
		box2.texOffs(4, 6).addBox(1.0F, -2.0F, 0.0F, 3, 1, 1, 0.0F, false);
		box2.texOffs(4, 0).addBox(4.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false);
		box2.texOffs(0, 0).addBox(1.0F, -5.0F, 0.0F, 1, 3, 1, 0.0F, false);
		box2.texOffs(0, 6).addBox(2.0F, -5.0F, 0.0F, 3, 1, 1, 0.0F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		box1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void setupAnim(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		box1.yRot = ageInTicks * 0.167f * 1.25f;
	}
}
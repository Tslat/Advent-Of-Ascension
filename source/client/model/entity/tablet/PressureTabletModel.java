package net.tslat.aoa3.client.model.entity.tablet;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class PressureTabletModel extends EntityModel<Entity> {
	private final ModelRenderer base;
	private final ModelRenderer icon;

	public PressureTabletModel() {
		texWidth = 32;
		texHeight = 32;

		base = new ModelRenderer(this);
		base.setPos(0.0F, 24.0F, 0.0F);
		base.texOffs(0, 0).addBox(-4.0F, -1.05F, -4.0F, 8, 1, 5, 0.0F, false);
		base.texOffs(0, 26).addBox(-4.0F, -1.04F, -4.0F, 8, 1, 5, 0.0F, false);
		base.texOffs(9, 0).addBox(-3.5F, -1.05F, 1.0F, 7, 1, 4, 0.0F, false);
		base.texOffs(1, 27).addBox(-3.5F, -1.04F, 1.0F, 7, 1, 4, 0.0F, false);
		base.texOffs(0, 8).addBox(-4.5F, -1.0F, -4.5F, 9, 1, 1, 0.0F, false);
		base.texOffs(0, 6).addBox(-0.2844F, -1.0F, 4.4658F, 4, 1, 1, 0.0F, false);
		base.texOffs(0, 6).addBox(-3.7156F, -1.0F, 4.4658F, 4, 1, 1, 0.0F, false);
		base.texOffs(0, 6).addBox(-3.7156F, -1.25F, 4.4658F, 4, 1, 1, 0.0F, true);
		base.texOffs(10, 6).addBox(-0.2844F, -1.25F, 4.4658F, 4, 1, 1, 0.0F, true);
		base.texOffs(0, 8).addBox(-4.5F, -1.25F, -4.5F, 9, 1, 1, 0.0F, false);

		ModelRenderer cube2 = new ModelRenderer(this);
		cube2.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube2, 0.0F, 1.4835F, 0.0F);
		base.addChild(cube2);
		cube2.texOffs(0, 8).addBox(-5.1211F, -1.0F, 3.1778F, 9, 1, 1, 0.0F, false);
		cube2.texOffs(0, 8).addBox(-5.1211F, -1.25F, 3.1778F, 9, 1, 1, 0.0F, false);

		ModelRenderer cube3 = new ModelRenderer(this);
		cube3.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube3, 0.0F, -1.4835F, 0.0F);
		base.addChild(cube3);
		cube3.texOffs(0, 6).addBox(-3.8789F, -1.0F, 3.1778F, 9, 1, 1, 0.0F, false);
		cube3.texOffs(0, 6).addBox(-3.8789F, -1.25F, 3.1778F, 9, 1, 1, 0.0F, false);

		icon = new ModelRenderer(this);
		icon.setPos(0.0F, 24.0F, 1.25F);
		icon.texOffs(7, 10).addBox(0.0F, -1.25F, -0.75F, 2, 1, 2, 0.0F, false);
		icon.texOffs(0, 10).addBox(0.6464F, -1.251F, 0.6036F, 1, 1, 1, 0.0F, false);
		icon.texOffs(15, 11).addBox(0.6464F, -1.249F, -1.1036F, 1, 1, 1, 0.0F, false);
		icon.texOffs(0, 10).addBox(-0.3536F, -1.251F, -0.3964F, 1, 1, 1, 0.0F, false);
		icon.texOffs(15, 11).addBox(1.3536F, -1.249F, -0.3964F, 1, 1, 1, 0.0F, false);
		icon.texOffs(0, 10).addBox(-0.3536F, -1.251F, -0.1036F, 1, 1, 1, 0.0F, false);
		icon.texOffs(15, 11).addBox(1.3536F, -1.249F, -0.1036F, 1, 1, 1, 0.0F, false);
		icon.texOffs(0, 10).addBox(0.3536F, -1.251F, 0.6036F, 1, 1, 1, 0.0F, false);
		icon.texOffs(15, 11).addBox(0.3536F, -1.249F, -1.1036F, 1, 1, 1, 0.0F, false);
		icon.texOffs(0, 12).addBox(-0.2464F, -1.25F, -0.9536F, 1, 1, 1, 0.0F, false);
		icon.texOffs(15, 11).addBox(-1.0F, -1.249F, -1.7036F, 1, 1, 1, 0.0F, false);
		icon.texOffs(0, 10).addBox(-1.1393F, -1.251F, -2.9571F, 2, 1, 1, 0.0F, false);
		icon.texOffs(15, 11).addBox(-2.5964F, -1.249F, -2.3536F, 1, 1, 1, 0.0F, false);

		ModelRenderer cube = new ModelRenderer(this);
		cube.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube, 0.0F, -0.7854F, 0.0F);
		icon.addChild(cube);
		cube.texOffs(4, 14).addBox(0.3839F, -1.2511F, -0.1161F, 1, 1, 1, 0.0F, false);
		cube.texOffs(1, 12).addBox(-2.3521F, -1.25F, -0.2929F, 1, 1, 1, 0.0F, false);
		cube.texOffs(1, 12).addBox(-3.5431F, -1.25F, -1.2854F, 1, 1, 1, 0.0F, false);
		cube.texOffs(15, 11).addBox(-2.7931F, -1.25F, -2.2854F, 1, 1, 1, 0.0F, false);
		cube.texOffs(1, 12).addBox(1.2981F, -1.25F, -1.0303F, 1, 1, 1, 0.0F, false);
		cube.texOffs(15, 11).addBox(0.3839F, -1.249F, -1.9445F, 1, 1, 1, 0.0F, false);
	}

	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		base.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		icon.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}

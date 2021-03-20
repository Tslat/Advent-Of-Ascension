package net.tslat.aoa3.client.model.entity.tablet;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SightTabletModel extends EntityModel<Entity> {
	private final ModelRenderer base;
	private final ModelRenderer icon;

	public SightTabletModel() {
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
		icon.setPos(0.3F, 24.0F, 0.1F);
		icon.texOffs(6, 10).addBox(-0.5928F, -1.2495F, -1.5703F, 2, 1, 1, 0.0F, false);
		icon.texOffs(7, 10).addBox(1.1466F, -1.251F, -1.467F, 1, 1, 1, 0.0F, false);
		icon.texOffs(7, 10).addBox(1.1466F, -1.2495F, -1.5703F, 1, 1, 1, 0.0F, false);

		ModelRenderer cube = new ModelRenderer(this);
		cube.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube, 0.0F, 0.4363F, 0.0F);
		icon.addChild(cube);
		cube.texOffs(8, 13).addBox(-2.5F, -1.25F, 0.25F, 4, 1, 1, 0.0F, false);

		ModelRenderer cube6 = new ModelRenderer(this);
		cube6.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube6, 0.0F, 0.3491F, 0.0F);
		icon.addChild(cube6);
		cube6.texOffs(10, 13).addBox(-2.02F, -1.25F, -1.6784F, 2, 1, 1, 0.0F, false);
		cube6.texOffs(0, 15).addBox(-2.37F, -1.248F, -0.9284F, 4, 1, 2, 0.0F, false);
		cube6.texOffs(12, 10).addBox(-1.37F, -1.251F, -0.9284F, 2, 1, 2, 0.0F, false);

		ModelRenderer cube4 = new ModelRenderer(this);
		cube4.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube4, 0.0F, -0.2618F, 0.0F);
		icon.addChild(cube4);
		cube4.texOffs(11, 13).addBox(0.9526F, -1.25F, -1.0066F, 1, 1, 1, 0.0F, false);

		ModelRenderer cube5 = new ModelRenderer(this);
		cube5.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube5, 0.0F, -0.0873F, 0.0F);
		icon.addChild(cube5);
		cube5.texOffs(0, 10).addBox(-2.5401F, -1.25F, -0.6675F, 1, 1, 3, 0.0F, false);
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

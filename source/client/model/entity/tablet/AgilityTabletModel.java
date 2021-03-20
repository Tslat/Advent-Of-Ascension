package net.tslat.aoa3.client.model.entity.tablet;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AgilityTabletModel extends EntityModel<Entity> {
	private final ModelRenderer base;
	private final ModelRenderer icon;

	public AgilityTabletModel() {
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
		icon.setPos(0.0F, 24.0F, 0.0F);
		icon.texOffs(18, 12).addBox(-1.5F, -1.245F, 1.75F, 1, 1, 1, 0.0F, false);
		icon.texOffs(18, 12).addBox(-1.5F, -1.245F, 1.25F, 1, 1, 1, 0.0F, false);
		icon.texOffs(10, 11).addBox(-1.25F, -1.245F, -0.25F, 1, 1, 2, 0.0F, false);
		icon.texOffs(0, 14).addBox(-1.0F, -1.25F, -0.25F, 2, 1, 3, 0.0F, false);
		icon.texOffs(0, 11).addBox(-2.0F, -1.25F, -2.25F, 3, 1, 2, 0.0F, false);
		icon.texOffs(18, 12).addBox(-2.7071F, -1.249F, -2.25F, 1, 1, 1, 0.0F, false);
		icon.texOffs(18, 12).addBox(-2.7071F, -1.249F, -1.9571F, 1, 1, 1, 0.0F, false);

		ModelRenderer cube4 = new ModelRenderer(this);
		cube4.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube4, 0.0F, -0.7854F, 0.0F);
		icon.addChild(cube4);
		cube4.texOffs(18, 12).addBox(-2.591F, -1.249F, 0.2374F, 1, 1, 1, 0.0F, false);

		ModelRenderer cube5 = new ModelRenderer(this);
		cube5.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube5, 0.0F, 1.1345F, 0.0F);
		icon.addChild(cube5);
		cube5.texOffs(23, 11).addBox(0.0F, -1.251F, 0.0F, 1, 1, 2, 0.0F, false);

		ModelRenderer cube6 = new ModelRenderer(this);
		cube6.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube6, 0.0F, 0.7854F, 0.0F);
		icon.addChild(cube6);
		cube6.texOffs(23, 11).addBox(0.0F, -1.252F, 0.75F, 1, 1, 2, 0.0F, false);

		ModelRenderer cube7 = new ModelRenderer(this);
		cube7.setPos(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube7, 0.0F, 0.6109F, 0.0F);
		icon.addChild(cube7);
		cube7.texOffs(10, 11).addBox(-0.5F, -1.253F, 1.25F, 1, 1, 2, 0.0F, false);
	}

	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		base.yRot = (float)Math.PI / 180f * netHeadYaw;
		icon.yRot = (float)Math.PI / 180f * netHeadYaw;
	}

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

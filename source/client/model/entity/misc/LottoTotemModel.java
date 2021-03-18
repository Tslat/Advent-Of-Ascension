package net.tslat.aoa3.client.model.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LottoTotemModel extends EntityModel<Entity> {
	private final ModelRenderer base;
	private final ModelRenderer coin;
	private final ModelRenderer cube2;
	private final ModelRenderer cube3;
	private final ModelRenderer cube4;
	private final ModelRenderer cube5;
	private final ModelRenderer cube6;
	private final ModelRenderer cube7;
	private final ModelRenderer cube8;
	private final ModelRenderer cube9;

	public LottoTotemModel() {
		textureWidth = 32;
		textureHeight = 32;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.setTextureOffset(0, 0).addBox(-2.0F, -8.001F, -6.0F, 4, 8, 12, 0.0F, false);
		base.setTextureOffset(0, 20).addBox(-3.0F, -9.0F, -3.0F, 6, 1, 6, 0.0F, false);

		cube3 = new ModelRenderer(this);
		cube3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(cube3, 0.0F, -0.7854F, 0.0F);
		base.addChild(cube3);
		cube3.setTextureOffset(0, 0).addBox(2.6569F, -8.0F, -2.8284F, 3, 8, 3, 0.0F, false);
		cube3.setTextureOffset(0, 20).addBox(5.1569F, -2.0F, -2.8284F, 2, 2, 1, 0.0F, false);
		cube3.setTextureOffset(0, 20).addBox(-7.1569F, -2.0F, -2.8284F, 2, 2, 1, 0.0F, false);
		cube3.setTextureOffset(0, 20).addBox(5.1569F, -2.0F, 1.8284F, 2, 2, 1, 0.0F, false);
		cube3.setTextureOffset(0, 20).addBox(-7.1569F, -2.0F, 1.8284F, 2, 2, 1, 0.0F, false);
		cube3.setTextureOffset(22, 25).addBox(1.8284F, -2.0F, 5.1569F, 1, 2, 2, 0.0F, false);
		cube3.setTextureOffset(22, 25).addBox(1.8284F, -2.0F, -7.1569F, 1, 2, 2, 0.0F, false);
		cube3.setTextureOffset(22, 25).addBox(-2.8284F, -2.0F, 5.1569F, 1, 2, 2, 0.0F, false);
		cube3.setTextureOffset(22, 25).addBox(-2.8284F, -2.0F, -7.1569F, 1, 2, 2, 0.0F, false);
		cube3.setTextureOffset(20, 0).addBox(2.6569F, -8.0F, -0.1716F, 3, 8, 3, 0.0F, false);
		cube3.setTextureOffset(20, 0).addBox(-2.8284F, -8.0F, 2.6569F, 3, 8, 3, 0.0F, false);
		cube3.setTextureOffset(0, 0).addBox(-2.8284F, -8.0F, -5.6569F, 3, 8, 3, 0.0F, false);
		cube3.setTextureOffset(0, 0).addBox(-0.1716F, -8.0F, 2.6569F, 3, 8, 3, 0.0F, false);
		cube3.setTextureOffset(20, 0).addBox(-0.1716F, -8.0F, -5.6569F, 3, 8, 3, 0.0F, false);
		cube3.setTextureOffset(20, 0).addBox(-5.6569F, -8.0F, -2.8284F, 3, 8, 3, 0.0F, false);
		cube3.setTextureOffset(0, 0).addBox(-5.6569F, -8.0F, -0.1716F, 3, 8, 3, 0.0F, false);

		cube4 = new ModelRenderer(this);
		cube4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(cube4, 0.0F, 0.0F, -0.4363F);
		cube3.addChild(cube4);
		cube4.setTextureOffset(26, 20).addBox(5.3315F, -2.788F, -2.8284F, 2, 4, 1, 0.0F, false);
		cube4.setTextureOffset(26, 20).addBox(5.3315F, -2.788F, 1.8284F, 2, 4, 1, 0.0F, false);

		cube5 = new ModelRenderer(this);
		cube5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(cube5, 0.0F, 0.0F, 0.4363F);
		cube3.addChild(cube5);
		cube5.setTextureOffset(26, 20).addBox(-7.3315F, -2.788F, -2.8284F, 2, 4, 1, 0.0F, false);
		cube5.setTextureOffset(26, 20).addBox(-7.3315F, -2.788F, 1.8284F, 2, 4, 1, 0.0F, false);

		cube6 = new ModelRenderer(this);
		cube6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(cube6, -0.4363F, 0.0F, 0.0F);
		cube3.addChild(cube6);
		cube6.setTextureOffset(18, 20).addBox(-2.8284F, -2.788F, -7.3315F, 1, 4, 2, 0.0F, false);
		cube6.setTextureOffset(18, 20).addBox(1.8284F, -2.788F, -7.3315F, 1, 4, 2, 0.0F, false);

		cube7 = new ModelRenderer(this);
		cube7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(cube7, 0.4363F, 0.0F, 0.0F);
		cube3.addChild(cube7);
		cube7.setTextureOffset(18, 20).addBox(-2.8284F, -2.788F, 5.3315F, 1, 4, 2, 0.0F, false);
		cube7.setTextureOffset(18, 20).addBox(1.8284F, -2.788F, 5.3315F, 1, 4, 2, 0.0F, false);

		cube2 = new ModelRenderer(this);
		cube2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(cube2, 0.0F, -1.5708F, 0.0F);
		base.addChild(cube2);
		cube2.setTextureOffset(0, 0).addBox(-2.0F, -8.001F, -6.0F, 4, 8, 12, 0.0F, false);

		coin = new ModelRenderer(this);
		coin.setRotationPoint(0.0F, 11.5F, 0.0F);
		setRotation(coin, -1.5708F, 0.0F, 0.0F);
		coin.setTextureOffset(0, 27).addBox(-1.5F, -0.499F, 0.6F, 3, 1, 2, 0.0F, false);
		coin.setTextureOffset(10, 27).addBox(-1.5F, -0.501F, -2.5962F, 3, 1, 2, 0.0F, false);

		cube8 = new ModelRenderer(this);
		cube8.setRotationPoint(-0.5F, 11.5F, 1.6F);
		setRotation(cube8, 0.0F, -1.0472F, 0.0F);
		coin.addChild(cube8);
		cube8.setTextureOffset(10, 27).addBox(-2.634F, -11.998F, -3.8301F, 3, 1, 2, 0.0F, false);
		cube8.setTextureOffset(0, 27).addBox(-2.634F, -12.002F, -0.634F, 3, 1, 2, 0.0F, false);

		cube9 = new ModelRenderer(this);
		cube9.setRotationPoint(-0.5F, 11.5F, 1.6F);
		setRotation(cube9, 0.0F, 1.0472F, 0.0F);
		coin.addChild(cube9);
		cube9.setTextureOffset(0, 27).addBox(0.134F, -12.0F, 0.2321F, 3, 1, 2, 0.0F, false);
		cube9.setTextureOffset(10, 27).addBox(0.134F, -12.0F, -2.9641F, 3, 1, 2, 0.0F, false);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		base.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		coin.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		coin.rotateAngleY = (ageInTicks * 0.045f) % 360;
	}
}

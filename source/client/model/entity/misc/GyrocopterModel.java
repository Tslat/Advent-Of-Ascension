package net.tslat.aoa3.client.model.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class GyrocopterModel extends EntityModel<Entity> {
	private final ModelRenderer root;
	private final ModelRenderer pTop7;
	private final ModelRenderer pTop8;
	private final ModelRenderer pFront1;

	public GyrocopterModel() {
		textureWidth = 128;
		textureHeight = 128;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);

		ModelRenderer shape1 = new ModelRenderer(this);
		shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		root.addChild(shape1);
		shape1.setTextureOffset(72, 69).addBox(11.0F, -5.0F, -7.0F, 3, 3, 0, 0.0F, true);
		shape1.setTextureOffset(3, 91).addBox(-11.0F, -30.0F, 16.0F, 2, 18, 2, 0.0F, true);
		shape1.setTextureOffset(35, 94).addBox(-8.5F, -18.0F, 13.0F, 17, 16, 2, 0.0F, true);
		shape1.setTextureOffset(54, 2).addBox(-7.5F, -14.0F, -6.0F, 1, 2, 2, 0.0F, true);
		shape1.setTextureOffset(0, 91).addBox(9.0F, -10.0F, -7.0F, 2, 10, 26, 0.0F, true);
		shape1.setTextureOffset(3, 91).addBox(9.0F, -30.0F, 16.0F, 2, 18, 2, 0.0F, true);
		shape1.setTextureOffset(14, 84).addBox(-1.5F, -10.0F, -9.0F, 5, 2, 2, 0.0F, true);
		shape1.setTextureOffset(93, 91).addBox(-12.0F, -13.0F, -9.0F, 4, 4, 4, 0.0F, true);
		shape1.setTextureOffset(84, 69).addBox(11.0F, -5.0F, -5.0F, 3, 3, 16, 0.0F, true);
		shape1.setTextureOffset(81, 69).addBox(11.5F, -4.5F, -10.0F, 2, 2, 5, 0.0F, true);
		shape1.setTextureOffset(72, 69).addBox(11.0F, -5.0F, -9.0F, 3, 3, 0, 0.0F, true);
		shape1.setTextureOffset(72, 69).addBox(11.0F, -5.0F, -8.0F, 3, 3, 0, 0.0F, true);
		shape1.setTextureOffset(84, 69).addBox(-14.0F, -5.0F, -5.0F, 3, 3, 16, 0.0F, true);
		shape1.setTextureOffset(72, 69).addBox(-14.0F, -5.0F, -9.0F, 3, 3, 0, 0.0F, true);
		shape1.setTextureOffset(72, 69).addBox(-14.0F, -5.0F, -7.0F, 3, 3, 0, 0.0F, true);
		shape1.setTextureOffset(72, 69).addBox(-14.0F, -5.0F, -8.0F, 3, 3, 0, 0.0F, true);
		shape1.setTextureOffset(81, 69).addBox(-13.5F, -4.5F, -10.0F, 2, 2, 5, 0.0F, true);
		shape1.setTextureOffset(94, 104).addBox(13.0F, -13.0F, 4.0F, 4, 4, 4, 0.0F, true);
		shape1.setTextureOffset(63, 18).addBox(-8.5F, -12.0F, 19.0F, 17, 2, 3, 0.0F, true);
		shape1.setTextureOffset(93, 91).addBox(8.0F, -13.0F, -9.0F, 4, 4, 4, 0.0F, true);
		shape1.setTextureOffset(34, 78).addBox(-11.0F, -10.0F, 19.0F, 22, 10, 2, 0.0F, true);
		shape1.setTextureOffset(66, 26).addBox(1.0F, -23.0F, 18.5F, 7, 2, 5, 0.0F, true);
		shape1.setTextureOffset(66, 37).addBox(-8.0F, -23.0F, 18.5F, 7, 2, 5, 0.0F, true);
		shape1.setTextureOffset(30, 63).addBox(-9.0F, -10.0F, -7.0F, 18, 2, 2, 0.0F, true);
		shape1.setTextureOffset(33, 8).addBox(-8.5F, -12.0F, -7.0F, 17, 2, 4, 0.0F, true);
		shape1.setTextureOffset(54, 2).addBox(1.5F, -14.0F, -6.0F, 1, 2, 2, 0.0F, true);
		shape1.setTextureOffset(33, 4).addBox(-6.5F, -14.0F, -5.0F, 4, 2, 1, 0.0F, true);
		shape1.setTextureOffset(33, 4).addBox(2.5F, -14.0F, -5.0F, 4, 2, 1, 0.0F, true);
		shape1.setTextureOffset(64, 2).addBox(-7.5F, -15.0F, -6.0F, 6, 1, 2, 0.0F, true);
		shape1.setTextureOffset(64, 2).addBox(1.5F, -15.0F, -6.0F, 6, 1, 2, 0.0F, true);
		shape1.setTextureOffset(45, 2).addBox(-2.5F, -14.0F, -6.0F, 1, 2, 2, 0.0F, true);
		shape1.setTextureOffset(45, 2).addBox(6.5F, -14.0F, -6.0F, 1, 2, 2, 0.0F, true);
		shape1.setTextureOffset(30, 70).addBox(-9.0F, -8.0F, -9.0F, 18, 6, 2, 0.0F, true);
		shape1.setTextureOffset(58, 91).addBox(-11.0F, -10.0F, -7.0F, 2, 10, 26, 0.0F, true);
		shape1.setTextureOffset(94, 104).addBox(-17.0F, -13.0F, 4.0F, 4, 4, 4, 0.0F, true);
		shape1.setTextureOffset(94, 90).addBox(13.0F, -14.0F, 12.0F, 6, 6, 6, 0.0F, true);
		shape1.setTextureOffset(94, 90).addBox(-19.0F, -14.0F, 12.0F, 6, 6, 6, 0.0F, true);
		shape1.setTextureOffset(0, 32).addBox(-9.0F, -2.0F, -7.0F, 18, 2, 26, 0.0F, true);
		shape1.setTextureOffset(0, 33).addBox(-9.0F, 0.0F, -7.0F, 18, 1, 26, 0.0F, true);
		shape1.setTextureOffset(63, 25).addBox(-11.5F, -12.0F, -7.0F, 3, 2, 29, 0.0F, true);
		shape1.setTextureOffset(63, 25).addBox(8.5F, -12.0F, -7.0F, 3, 2, 29, 0.0F, true);

		pTop7 = new ModelRenderer(this);
		pTop7.setRotationPoint(-10.0F, -33.0F, 17.0F);
		root.addChild(pTop7);
		pTop7.setTextureOffset(0, 51).addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, 0.0F, true);
		pTop7.setTextureOffset(0, 71).addBox(-1.5F, 1.0F, 1.5F, 3, 1, 6, 0.0F, true);
		pTop7.setTextureOffset(0, 71).addBox(1.5F, 1.0F, -1.5F, 6, 1, 3, 0.0F, true);
		pTop7.setTextureOffset(0, 71).addBox(-7.5F, 1.0F, -1.5F, 6, 1, 3, 0.0F, true);
		pTop7.setTextureOffset(0, 71).addBox(-1.5F, 1.0F, -7.5F, 3, 1, 6, 0.0F, true);

		pTop8 = new ModelRenderer(this);
		pTop8.setRotationPoint(10.0F, -33.0F, 17.0F);
		root.addChild(pTop8);
		pTop8.setTextureOffset(0, 71).addBox(1.5F, 1.0F, -1.5F, 6, 1, 3, 0.0F, true);
		pTop8.setTextureOffset(0, 51).addBox(-1.5F, 0.0F, -1.5F, 3, 3, 3, 0.0F, true);
		pTop8.setTextureOffset(0, 71).addBox(-1.5F, 1.0F, 1.5F, 3, 1, 6, 0.0F, true);
		pTop8.setTextureOffset(0, 71).addBox(-7.5F, 1.0F, -1.5F, 6, 1, 3, 0.0F, true);
		pTop8.setTextureOffset(0, 71).addBox(-1.5F, 1.0F, -7.5F, 3, 1, 6, 0.0F, true);

		pFront1 = new ModelRenderer(this);
		pFront1.setRotationPoint(0.0F, -8.0F, -9.0F);
		root.addChild(pFront1);
		pFront1.setTextureOffset(16, 34).addBox(-1.5F, 1.5F, -2.0F, 3, 7, 1, 0.0F, true);
		pFront1.setTextureOffset(0, 37).addBox(-1.5F, -1.5F, -3.0F, 3, 3, 3, 0.0F, true);
		pFront1.setTextureOffset(0, 45).addBox(1.5F, -1.5F, -2.0F, 7, 3, 1, 0.0F, true);
		pFront1.setTextureOffset(0, 45).addBox(-8.5F, -1.5F, -2.0F, 7, 3, 1, 0.0F, true);
		pFront1.setTextureOffset(16, 34).addBox(-1.5F, -8.5F, -2.0F, 3, 7, 1, 0.0F, true);

		ModelRenderer shape8 = new ModelRenderer(this);
		shape8.setRotationPoint(4.0F, -6.0F, -4.0F);
		setRotationAngle(shape8, -0.3491F, 0.0F, 0.0F);
		root.addChild(shape8);
		shape8.setTextureOffset(19, 68).addBox(-9.0F, -8.0F, -1.0F, 2, 12, 2, 0.0F, true);
		shape8.setTextureOffset(13, 92).addBox(-9.5F, -11.0F, -1.5F, 3, 3, 3, 0.0F, true);

		ModelRenderer shape24 = new ModelRenderer(this);
		shape24.setRotationPoint(8.0F, -6.0F, -9.0F);
		setRotationAngle(shape24, -0.1396F, 0.0F, 0.0F);
		root.addChild(shape24);
		shape24.setTextureOffset(109, 20).addBox(-9.0F, -24.0F, 25.0F, 2, 25, 5, 0.0F, true);

		ModelRenderer shape36 = new ModelRenderer(this);
		shape36.setRotationPoint(4.0F, -6.0F, -4.0F);
		setRotationAngle(shape36, -0.4363F, 0.0F, 0.0F);
		root.addChild(shape36);
		shape36.setTextureOffset(15, 61).addBox(-1.5F, -11.0F, -1.5F, 3, 3, 3, 0.0F, true);
		shape36.setTextureOffset(19, 68).addBox(-1.0F, -8.0F, -1.0F, 2, 12, 2, 0.0F, true);

		ModelRenderer shape37 = new ModelRenderer(this);
		shape37.setRotationPoint(4.0F, -6.0F, -4.0F);
		setRotationAngle(shape37, -0.7854F, 0.0F, 0.0F);
		root.addChild(shape37);
		shape37.setTextureOffset(19, 68).addBox(-5.0F, -8.0F, -1.0F, 2, 12, 2, 0.0F, true);
		shape37.setTextureOffset(13, 101).addBox(-5.5F, -11.0F, -1.5F, 3, 3, 3, 0.0F, true);

		ModelRenderer shape42 = new ModelRenderer(this);
		shape42.setRotationPoint(8.0F, -13.0F, -6.0F);
		setRotationAngle(shape42, 0.0F, 0.0F, 0.5236F);
		root.addChild(shape42);
		shape42.setTextureOffset(3, 97).addBox(5.0F, -2.0F, 11.0F, 2, 8, 2, 0.0F, true);
		shape42.setTextureOffset(3, 97).addBox(5.0F, -2.0F, 20.0F, 2, 8, 2, 0.0F, true);

		ModelRenderer shape47 = new ModelRenderer(this);
		shape47.setRotationPoint(8.0F, -13.0F, -6.0F);
		setRotationAngle(shape47, 0.0F, 0.0F, -0.5236F);
		root.addChild(shape47);
		shape47.setTextureOffset(3, 97).addBox(-21.0F, -10.0F, 20.0F, 2, 8, 2, 0.0F, true);
		shape47.setTextureOffset(3, 97).addBox(-21.0F, -10.0F, 11.0F, 2, 8, 2, 0.0F, true);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		pFront1.rotateAngleZ = ageInTicks * 0.067f * 11.25f;
		pTop7.rotateAngleY = ageInTicks * 0.067f * 11.25f;
		pTop8.rotateAngleY = ageInTicks * 0.067f * 11.25f;
	}
}

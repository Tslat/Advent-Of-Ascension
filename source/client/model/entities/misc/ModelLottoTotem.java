package net.tslat.aoa3.client.model.entities.misc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLottoTotem extends ModelBase {
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

	public ModelLottoTotem() {
		textureWidth = 32;
		textureHeight = 32;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -2.0F, -8.001F, -6.0F, 4, 8, 12, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 20, -3.0F, -9.0F, -3.0F, 6, 1, 6, 0.0F, false));

		cube3 = new ModelRenderer(this);
		cube3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(cube3, 0.0F, -0.7854F, 0.0F);
		base.addChild(cube3);
		cube3.cubeList.add(new ModelBox(cube3, 0, 0, 2.6569F, -8.0F, -2.8284F, 3, 8, 3, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 20, 5.1569F, -2.0F, -2.8284F, 2, 2, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 20, -7.1569F, -2.0F, -2.8284F, 2, 2, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 20, 5.1569F, -2.0F, 1.8284F, 2, 2, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 20, -7.1569F, -2.0F, 1.8284F, 2, 2, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 22, 25, 1.8284F, -2.0F, 5.1569F, 1, 2, 2, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 22, 25, 1.8284F, -2.0F, -7.1569F, 1, 2, 2, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 22, 25, -2.8284F, -2.0F, 5.1569F, 1, 2, 2, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 22, 25, -2.8284F, -2.0F, -7.1569F, 1, 2, 2, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 20, 0, 2.6569F, -8.0F, -0.1716F, 3, 8, 3, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 20, 0, -2.8284F, -8.0F, 2.6569F, 3, 8, 3, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 0, -2.8284F, -8.0F, -5.6569F, 3, 8, 3, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 0, -0.1716F, -8.0F, 2.6569F, 3, 8, 3, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 20, 0, -0.1716F, -8.0F, -5.6569F, 3, 8, 3, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 20, 0, -5.6569F, -8.0F, -2.8284F, 3, 8, 3, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 0, -5.6569F, -8.0F, -0.1716F, 3, 8, 3, 0.0F, false));

		cube4 = new ModelRenderer(this);
		cube4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(cube4, 0.0F, 0.0F, -0.4363F);
		cube3.addChild(cube4);
		cube4.cubeList.add(new ModelBox(cube4, 26, 20, 5.3315F, -2.788F, -2.8284F, 2, 4, 1, 0.0F, false));
		cube4.cubeList.add(new ModelBox(cube4, 26, 20, 5.3315F, -2.788F, 1.8284F, 2, 4, 1, 0.0F, false));

		cube5 = new ModelRenderer(this);
		cube5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(cube5, 0.0F, 0.0F, 0.4363F);
		cube3.addChild(cube5);
		cube5.cubeList.add(new ModelBox(cube5, 26, 20, -7.3315F, -2.788F, -2.8284F, 2, 4, 1, 0.0F, false));
		cube5.cubeList.add(new ModelBox(cube5, 26, 20, -7.3315F, -2.788F, 1.8284F, 2, 4, 1, 0.0F, false));

		cube6 = new ModelRenderer(this);
		cube6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(cube6, -0.4363F, 0.0F, 0.0F);
		cube3.addChild(cube6);
		cube6.cubeList.add(new ModelBox(cube6, 18, 20, -2.8284F, -2.788F, -7.3315F, 1, 4, 2, 0.0F, false));
		cube6.cubeList.add(new ModelBox(cube6, 18, 20, 1.8284F, -2.788F, -7.3315F, 1, 4, 2, 0.0F, false));

		cube7 = new ModelRenderer(this);
		cube7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(cube7, 0.4363F, 0.0F, 0.0F);
		cube3.addChild(cube7);
		cube7.cubeList.add(new ModelBox(cube7, 18, 20, -2.8284F, -2.788F, 5.3315F, 1, 4, 2, 0.0F, false));
		cube7.cubeList.add(new ModelBox(cube7, 18, 20, 1.8284F, -2.788F, 5.3315F, 1, 4, 2, 0.0F, false));

		cube2 = new ModelRenderer(this);
		cube2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(cube2, 0.0F, -1.5708F, 0.0F);
		base.addChild(cube2);
		cube2.cubeList.add(new ModelBox(cube2, 0, 0, -2.0F, -8.001F, -6.0F, 4, 8, 12, 0.0F, false));

		coin = new ModelRenderer(this);
		coin.setRotationPoint(0.0F, 11.5F, 0.0F);
		setRotation(coin, -1.5708F, 0.0F, 0.0F);
		coin.cubeList.add(new ModelBox(coin, 0, 27, -1.5F, -0.499F, 0.6F, 3, 1, 2, 0.0F, false));
		coin.cubeList.add(new ModelBox(coin, 10, 27, -1.5F, -0.501F, -2.5962F, 3, 1, 2, 0.0F, false));

		cube8 = new ModelRenderer(this);
		cube8.setRotationPoint(-0.5F, 11.5F, 1.6F);
		setRotation(cube8, 0.0F, -1.0472F, 0.0F);
		coin.addChild(cube8);
		cube8.cubeList.add(new ModelBox(cube8, 10, 27, -2.634F, -11.998F, -3.8301F, 3, 1, 2, 0.0F, false));
		cube8.cubeList.add(new ModelBox(cube8, 0, 27, -2.634F, -12.002F, -0.634F, 3, 1, 2, 0.0F, false));

		cube9 = new ModelRenderer(this);
		cube9.setRotationPoint(-0.5F, 11.5F, 1.6F);
		setRotation(cube9, 0.0F, 1.0472F, 0.0F);
		coin.addChild(cube9);
		cube9.cubeList.add(new ModelBox(cube9, 0, 27, 0.134F, -12.0F, 0.2321F, 3, 1, 2, 0.0F, false));
		cube9.cubeList.add(new ModelBox(cube9, 10, 27, 0.134F, -12.0F, -2.9641F, 3, 1, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		base.render(f5);
		coin.render(f5);
	}

	public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		coin.rotateAngleY = limbSwingAmount * 0.045f;
	}
}

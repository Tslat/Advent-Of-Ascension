package net.tslat.aoa3.client.model.entities.tablets;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCleansingTablet extends ModelBase {
	private final ModelRenderer base;
	private final ModelRenderer icon;

	public ModelCleansingTablet() {
		textureWidth = 32;
		textureHeight = 32;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 24.0F, 0.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -4.0F, -1.05F, -4.0F, 8, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 26, -4.0F, -1.04F, -4.0F, 8, 1, 5, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 9, 0, -3.5F, -1.05F, 1.0F, 7, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 1, 27, -3.5F, -1.04F, 1.0F, 7, 1, 4, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 8, -4.5F, -1.0F, -4.5F, 9, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 6, -0.2844F, -1.0F, 4.4658F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 6, -3.7156F, -1.0F, 4.4658F, 4, 1, 1, 0.0F, false));
		base.cubeList.add(new ModelBox(base, 0, 6, -3.7156F, -1.25F, 4.4658F, 4, 1, 1, 0.0F, true));
		base.cubeList.add(new ModelBox(base, 10, 6, -0.2844F, -1.25F, 4.4658F, 4, 1, 1, 0.0F, true));
		base.cubeList.add(new ModelBox(base, 0, 8, -4.5F, -1.25F, -4.5F, 9, 1, 1, 0.0F, false));

		ModelRenderer cube2 = new ModelRenderer(this);
		cube2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube2, 0.0F, 1.4835F, 0.0F);
		base.addChild(cube2);
		cube2.cubeList.add(new ModelBox(cube2, 0, 8, -5.1211F, -1.0F, 3.1778F, 9, 1, 1, 0.0F, false));
		cube2.cubeList.add(new ModelBox(cube2, 0, 8, -5.1211F, -1.25F, 3.1778F, 9, 1, 1, 0.0F, false));

		ModelRenderer cube3 = new ModelRenderer(this);
		cube3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube3, 0.0F, -1.4835F, 0.0F);
		base.addChild(cube3);
		cube3.cubeList.add(new ModelBox(cube3, 0, 6, -3.8789F, -1.0F, 3.1778F, 9, 1, 1, 0.0F, false));
		cube3.cubeList.add(new ModelBox(cube3, 0, 6, -3.8789F, -1.25F, 3.1778F, 9, 1, 1, 0.0F, false));

		icon = new ModelRenderer(this);
		icon.setRotationPoint(0.0F, 24.0F, 0.25F);
		icon.cubeList.add(new ModelBox(icon, 14, 11, 0.5648F, -1.249F, -1.6142F, 1, 1, 1, 0.0F, false));
		icon.cubeList.add(new ModelBox(icon, 0, 10, -0.8577F, -1.25F, -2.3213F, 1, 1, 4, 0.0F, false));
		icon.cubeList.add(new ModelBox(icon, 6, 13, -0.1423F, -1.2489F, -2.3213F, 1, 1, 4, 0.0F, false));
		icon.cubeList.add(new ModelBox(icon, 6, 10, -1.5648F, -1.251F, -1.6142F, 1, 1, 1, 0.0F, false));

		ModelRenderer cube = new ModelRenderer(this);
		cube.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube, 0.0F, -0.7854F, 0.0F);
		icon.addChild(cube);
		cube.cubeList.add(new ModelBox(cube, 6, 10, 1.091F, -1.251F, 1.091F, 1, 1, 1, 0.0F, false));
		cube.cubeList.add(new ModelBox(cube, 6, 10, -2.2479F, -1.251F, -1.0349F, 1, 1, 1, 0.0F, false));
		cube.cubeList.add(new ModelBox(cube, 14, 11, -1.0349F, -1.249F, -2.2479F, 1, 1, 1, 0.0F, false));

		ModelRenderer cube4 = new ModelRenderer(this);
		cube4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube4, 0.0F, -1.2217F, 0.0F);
		icon.addChild(cube4);
		cube4.cubeList.add(new ModelBox(cube4, 6, 10, -0.1275F, -1.251F, 0.434F, 2, 1, 1, 0.0F, false));

		ModelRenderer cube6 = new ModelRenderer(this);
		cube6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube6, 0.0F, -1.3963F, 0.0F);
		icon.addChild(cube6);
		cube6.cubeList.add(new ModelBox(cube6, 6, 10, -0.8766F, -1.251F, 0.4344F, 1, 1, 1, 0.0F, false));

		ModelRenderer cube5 = new ModelRenderer(this);
		cube5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube5, 0.0F, 1.2217F, 0.0F);
		icon.addChild(cube5);
		cube5.cubeList.add(new ModelBox(cube5, 2, 15, -1.8725F, -1.25F, 0.434F, 2, 1, 1, 0.0F, false));

		ModelRenderer cube7 = new ModelRenderer(this);
		cube7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(cube7, 0.0F, 1.3963F, 0.0F);
		icon.addChild(cube7);
		cube7.cubeList.add(new ModelBox(cube7, 14, 11, -0.1234F, -1.249F, 0.4344F, 1, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		base.render(f5);
		icon.render(f5);
	}

	private void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

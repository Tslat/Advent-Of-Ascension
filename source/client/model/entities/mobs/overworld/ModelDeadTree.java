package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDeadTree extends ModelBase {
	private final ModelRenderer trunk;

	public ModelDeadTree() {
		textureWidth = 64;
		textureHeight = 32;

		trunk = new ModelRenderer(this);
		trunk.setRotationPoint(0.0F, 24.0F, 0.0F);
		trunk.cubeList.add(new ModelBox(trunk, 0, 0, -7.0F, -16.0F, -7.0F, 14, 16, 14, 0.0F, false));
		trunk.cubeList.add(new ModelBox(trunk, 0, 0, -7.0F, -32.0F, -7.0F, 14, 16, 14, 0.0F, false));
		trunk.cubeList.add(new ModelBox(trunk, 0, 0, -7.0F, -48.0F, -7.0F, 14, 16, 14, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float scaleFactor) {
		trunk.render(scaleFactor);
	}

	public void setRotation(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}

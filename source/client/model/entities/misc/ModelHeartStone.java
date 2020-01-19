package net.tslat.aoa3.client.model.entities.misc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHeartStone extends ModelBase {
	private final ModelRenderer box1;

	public ModelHeartStone() {
		textureWidth = 16;
		textureHeight = 16;

		box1 = new ModelRenderer(this);
		box1.setRotationPoint(0.0F, 24.0F, 0.0F);
		box1.cubeList.add(new ModelBox(box1, 0, 10, -0.75F, -5.75F, 0F, 2, 1, 0, 0.0F, false));
		box1.cubeList.add(new ModelBox(box1, 0, 8, 0.5F, -5.0F, 0F, 1, 2, 0, 0.0F, false));
		box1.cubeList.add(new ModelBox(box1, 0, 9, -1.25F, -3.75F, 0F, 2, 1, 0, 0.0F, false));
		box1.cubeList.add(new ModelBox(box1, 0, 8, -1.5F, -5.5F, 0F, 1, 2, 0, 0.0F, false));

		ModelRenderer box2 = new ModelRenderer(this);
		box2.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotationAngle(box2, 0.0F, 0.0F, -0.7854F);
		box1.addChild(box2);
		box2.cubeList.add(new ModelBox(box2, 4, 6, 1.0F, -2.0F, 0.0F, 3, 1, 1, 0.0F, false));
		box2.cubeList.add(new ModelBox(box2, 4, 0, 4.0F, -4.0F, 0.0F, 1, 3, 1, 0.0F, false));
		box2.cubeList.add(new ModelBox(box2, 0, 0, 1.0F, -5.0F, 0.0F, 1, 3, 1, 0.0F, false));
		box2.cubeList.add(new ModelBox(box2, 0, 6, 2.0F, -5.0F, 0.0F, 3, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float scale) {
		setRotationAngles(f, f1, f2, f3, f4, scale, entity);
		box1.render(scale);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		box1.rotateAngleY = par3 * 0.167f * 1.25f;
	}
}
package net.tslat.aoa3.client.model.entities.misc;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelAnimaStone extends ModelBase {
	private final ModelRenderer box1;

	public ModelAnimaStone() {
		textureWidth = 16;
		textureHeight = 16;

		box1 = new ModelRenderer(this);
		box1.setRotationPoint(0.0F, 24.0F, 0.0F);
		box1.cubeList.add(new ModelBox(box1, 0, 6, -0.5F, -6.0F, -0.5F, 1, 1, 1, 0.0F, false));
		box1.cubeList.add(new ModelBox(box1, 0, 6, -1.5F, -5.0F, -0.5F, 1, 1, 1, 0.0F, false));
		box1.cubeList.add(new ModelBox(box1, 0, 6, 0.5F, -5.0F, -0.5F, 1, 1, 1, 0.0F, false));
		box1.cubeList.add(new ModelBox(box1, 0, 6, -0.5F, -4.0F, -0.5F, 1, 1, 1, 0.0F, false));

		ModelRenderer box2 = new ModelRenderer(this);
		box2.setRotationPoint(0.0F, -3.0F, -0.5F);
		setRotationAngle(box2, 0.0F, 0.0F, -0.7854F);
		box1.addChild(box2);
		box2.cubeList.add(new ModelBox(box2, 0, 5, 0.0707F, -2.0707F, 0.0F, 2, 2, 1, 0.0F, false));
		box2.cubeList.add(new ModelBox(box2, 12, 2, 0.9572F, 0.5783F, 0.0F, 1, 1, 1, 0.0F, false));
		box2.cubeList.add(new ModelBox(box2, 12, 0, 0.1641F, -3.6996F, 0.0F, 1, 1, 1, 0.0F, false));
		box2.cubeList.add(new ModelBox(box2, 0, 4, -1.5783F, -1.9572F, 0.0F, 1, 1, 1, 0.0F, false));
		box2.cubeList.add(new ModelBox(box2, 6, 0, 2.6996F, -1.9572F, 0.0F, 1, 1, 1, 0.0F, false));
		box2.cubeList.add(new ModelBox(box2, 0, 4, -1.5783F, -1.1641F, 0.0F, 1, 1, 1, 0.0F, false));
		box2.cubeList.add(new ModelBox(box2, 12, 0, 0.9572F, -3.6996F, 0.0F, 1, 1, 1, 0.0F, false));
		box2.cubeList.add(new ModelBox(box2, 7, 0, 2.6996F, -1.1641F, 0.0F, 1, 1, 1, 0.0F, false));
		box2.cubeList.add(new ModelBox(box2, 12, 2, 0.1641F, 0.5783F, 0.0F, 1, 1, 1, 0.0F, false));

		ModelRenderer box3 = new ModelRenderer(this);
		box3.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotationAngle(box3, 0.0F, 0.0F, -0.5236F);
		box1.addChild(box3);
		box3.cubeList.add(new ModelBox(box3, 0, 0, 0.701F, -6.2141F, 0.0F, 1, 2, 1, 0.0F, true));
		box3.cubeList.add(new ModelBox(box3, 0, 3, 2.799F, -3.5801F, 0.0F, 1, 2, 1, 0.0F, false));
		box3.cubeList.add(new ModelBox(box3, 10, 5, -0.067F, -3.3481F, 0.0F, 2, 1, 1, 0.0F, false));
		box3.cubeList.add(new ModelBox(box3, 5, 0, 2.567F, -5.4462F, 0.0F, 2, 1, 1, 0.0F, true));

		ModelRenderer box4 = new ModelRenderer(this);
		box4.setRotationPoint(0.0F, 0.0F, -0.5F);
		setRotationAngle(box4, 0.0F, 0.0F, -1.0472F);
		box1.addChild(box4);
		box4.cubeList.add(new ModelBox(box4, 0, 0, 4.4462F, -4.567F, 0.0F, 1, 2, 1, 0.0F, false));
		box4.cubeList.add(new ModelBox(box4, 12, 2, 2.3481F, -1.933F, 0.0F, 1, 2, 1, 0.0F, false));
		box4.cubeList.add(new ModelBox(box4, 0, 4, 1.5801F, -3.799F, 0.0F, 2, 1, 1, 0.0F, false));
		box4.cubeList.add(new ModelBox(box4, 5, 0, 4.2141F, -1.701F, 0.0F, 2, 1, 1, 0.0F, true));
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
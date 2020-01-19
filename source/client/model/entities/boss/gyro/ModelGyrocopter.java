package net.tslat.aoa3.client.model.entities.boss.gyro;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGyrocopter extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer Shape1;
	private final ModelRenderer pTop7;
	private final ModelRenderer pTop8;
	private final ModelRenderer pFront1;
	private final ModelRenderer Shape8;
	private final ModelRenderer Shape24;
	private final ModelRenderer Shape36;
	private final ModelRenderer Shape37;
	private final ModelRenderer Shape42;
	private final ModelRenderer Shape47;

	public ModelGyrocopter() {
		textureWidth = 128;
		textureHeight = 128;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);

		Shape1 = new ModelRenderer(this);
		Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		root.addChild(Shape1);
		Shape1.cubeList.add(new ModelBox(Shape1, 72, 69, 11.0F, -5.0F, -7.0F, 3, 3, 0, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 3, 91, -11.0F, -30.0F, 16.0F, 2, 18, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 35, 94, -8.5F, -18.0F, 13.0F, 17, 16, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 54, 2, -7.5F, -14.0F, -6.0F, 1, 2, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 0, 91, 9.0F, -10.0F, -7.0F, 2, 10, 26, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 3, 91, 9.0F, -30.0F, 16.0F, 2, 18, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 14, 84, -1.5F, -10.0F, -9.0F, 5, 2, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 93, 91, -12.0F, -13.0F, -9.0F, 4, 4, 4, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 84, 69, 11.0F, -5.0F, -5.0F, 3, 3, 16, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 81, 69, 11.5F, -4.5F, -10.0F, 2, 2, 5, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 72, 69, 11.0F, -5.0F, -9.0F, 3, 3, 0, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 72, 69, 11.0F, -5.0F, -8.0F, 3, 3, 0, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 84, 69, -14.0F, -5.0F, -5.0F, 3, 3, 16, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 72, 69, -14.0F, -5.0F, -9.0F, 3, 3, 0, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 72, 69, -14.0F, -5.0F, -7.0F, 3, 3, 0, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 72, 69, -14.0F, -5.0F, -8.0F, 3, 3, 0, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 81, 69, -13.5F, -4.5F, -10.0F, 2, 2, 5, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 94, 104, 13.0F, -13.0F, 4.0F, 4, 4, 4, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 63, 18, -8.5F, -12.0F, 19.0F, 17, 2, 3, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 93, 91, 8.0F, -13.0F, -9.0F, 4, 4, 4, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 34, 78, -11.0F, -10.0F, 19.0F, 22, 10, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 66, 26, 1.0F, -23.0F, 18.5F, 7, 2, 5, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 66, 37, -8.0F, -23.0F, 18.5F, 7, 2, 5, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 30, 63, -9.0F, -10.0F, -7.0F, 18, 2, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 33, 8, -8.5F, -12.0F, -7.0F, 17, 2, 4, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 54, 2, 1.5F, -14.0F, -6.0F, 1, 2, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 33, 4, -6.5F, -14.0F, -5.0F, 4, 2, 1, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 33, 4, 2.5F, -14.0F, -5.0F, 4, 2, 1, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 64, 2, -7.5F, -15.0F, -6.0F, 6, 1, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 64, 2, 1.5F, -15.0F, -6.0F, 6, 1, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 45, 2, -2.5F, -14.0F, -6.0F, 1, 2, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 45, 2, 6.5F, -14.0F, -6.0F, 1, 2, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 30, 70, -9.0F, -8.0F, -9.0F, 18, 6, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 58, 91, -11.0F, -10.0F, -7.0F, 2, 10, 26, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 94, 104, -17.0F, -13.0F, 4.0F, 4, 4, 4, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 94, 90, 13.0F, -14.0F, 12.0F, 6, 6, 6, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 94, 90, -19.0F, -14.0F, 12.0F, 6, 6, 6, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 0, 32, -9.0F, -2.0F, -7.0F, 18, 2, 26, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 0, 33, -9.0F, 0.0F, -7.0F, 18, 1, 26, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 63, 25, -11.5F, -12.0F, -7.0F, 3, 2, 29, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 63, 25, 8.5F, -12.0F, -7.0F, 3, 2, 29, 0.0F, true));

		pTop7 = new ModelRenderer(this);
		pTop7.setRotationPoint(-10.0F, -33.0F, 17.0F);
		root.addChild(pTop7);
		pTop7.cubeList.add(new ModelBox(pTop7, 0, 51, -1.5F, 0.0F, -1.5F, 3, 3, 3, 0.0F, true));
		pTop7.cubeList.add(new ModelBox(pTop7, 0, 71, -1.5F, 1.0F, 1.5F, 3, 1, 6, 0.0F, true));
		pTop7.cubeList.add(new ModelBox(pTop7, 0, 71, 1.5F, 1.0F, -1.5F, 6, 1, 3, 0.0F, true));
		pTop7.cubeList.add(new ModelBox(pTop7, 0, 71, -7.5F, 1.0F, -1.5F, 6, 1, 3, 0.0F, true));
		pTop7.cubeList.add(new ModelBox(pTop7, 0, 71, -1.5F, 1.0F, -7.5F, 3, 1, 6, 0.0F, true));

		pTop8 = new ModelRenderer(this);
		pTop8.setRotationPoint(10.0F, -33.0F, 17.0F);
		root.addChild(pTop8);
		pTop8.cubeList.add(new ModelBox(pTop8, 0, 71, 1.5F, 1.0F, -1.5F, 6, 1, 3, 0.0F, true));
		pTop8.cubeList.add(new ModelBox(pTop8, 0, 51, -1.5F, 0.0F, -1.5F, 3, 3, 3, 0.0F, true));
		pTop8.cubeList.add(new ModelBox(pTop8, 0, 71, -1.5F, 1.0F, 1.5F, 3, 1, 6, 0.0F, true));
		pTop8.cubeList.add(new ModelBox(pTop8, 0, 71, -7.5F, 1.0F, -1.5F, 6, 1, 3, 0.0F, true));
		pTop8.cubeList.add(new ModelBox(pTop8, 0, 71, -1.5F, 1.0F, -7.5F, 3, 1, 6, 0.0F, true));

		pFront1 = new ModelRenderer(this);
		pFront1.setRotationPoint(0.0F, -8.0F, -9.0F);
		root.addChild(pFront1);
		pFront1.cubeList.add(new ModelBox(pFront1, 16, 34, -1.5F, 1.5F, -2.0F, 3, 7, 1, 0.0F, true));
		pFront1.cubeList.add(new ModelBox(pFront1, 0, 37, -1.5F, -1.5F, -3.0F, 3, 3, 3, 0.0F, true));
		pFront1.cubeList.add(new ModelBox(pFront1, 0, 45, 1.5F, -1.5F, -2.0F, 7, 3, 1, 0.0F, true));
		pFront1.cubeList.add(new ModelBox(pFront1, 0, 45, -8.5F, -1.5F, -2.0F, 7, 3, 1, 0.0F, true));
		pFront1.cubeList.add(new ModelBox(pFront1, 16, 34, -1.5F, -8.5F, -2.0F, 3, 7, 1, 0.0F, true));

		Shape8 = new ModelRenderer(this);
		Shape8.setRotationPoint(4.0F, -6.0F, -4.0F);
		setRotationAngle(Shape8, -0.3491F, 0.0F, 0.0F);
		root.addChild(Shape8);
		Shape8.cubeList.add(new ModelBox(Shape8, 19, 68, -9.0F, -8.0F, -1.0F, 2, 12, 2, 0.0F, true));
		Shape8.cubeList.add(new ModelBox(Shape8, 13, 92, -9.5F, -11.0F, -1.5F, 3, 3, 3, 0.0F, true));

		Shape24 = new ModelRenderer(this);
		Shape24.setRotationPoint(8.0F, -6.0F, -9.0F);
		setRotationAngle(Shape24, -0.1396F, 0.0F, 0.0F);
		root.addChild(Shape24);
		Shape24.cubeList.add(new ModelBox(Shape24, 109, 20, -9.0F, -24.0F, 25.0F, 2, 25, 5, 0.0F, true));

		Shape36 = new ModelRenderer(this);
		Shape36.setRotationPoint(4.0F, -6.0F, -4.0F);
		setRotationAngle(Shape36, -0.4363F, 0.0F, 0.0F);
		root.addChild(Shape36);
		Shape36.cubeList.add(new ModelBox(Shape36, 15, 61, -1.5F, -11.0F, -1.5F, 3, 3, 3, 0.0F, true));
		Shape36.cubeList.add(new ModelBox(Shape36, 19, 68, -1.0F, -8.0F, -1.0F, 2, 12, 2, 0.0F, true));

		Shape37 = new ModelRenderer(this);
		Shape37.setRotationPoint(4.0F, -6.0F, -4.0F);
		setRotationAngle(Shape37, -0.7854F, 0.0F, 0.0F);
		root.addChild(Shape37);
		Shape37.cubeList.add(new ModelBox(Shape37, 19, 68, -5.0F, -8.0F, -1.0F, 2, 12, 2, 0.0F, true));
		Shape37.cubeList.add(new ModelBox(Shape37, 13, 101, -5.5F, -11.0F, -1.5F, 3, 3, 3, 0.0F, true));

		Shape42 = new ModelRenderer(this);
		Shape42.setRotationPoint(8.0F, -13.0F, -6.0F);
		setRotationAngle(Shape42, 0.0F, 0.0F, 0.5236F);
		root.addChild(Shape42);
		Shape42.cubeList.add(new ModelBox(Shape42, 3, 97, 5.0F, -2.0F, 11.0F, 2, 8, 2, 0.0F, true));
		Shape42.cubeList.add(new ModelBox(Shape42, 3, 97, 5.0F, -2.0F, 20.0F, 2, 8, 2, 0.0F, true));

		Shape47 = new ModelRenderer(this);
		Shape47.setRotationPoint(8.0F, -13.0F, -6.0F);
		setRotationAngle(Shape47, 0.0F, 0.0F, -0.5236F);
		root.addChild(Shape47);
		Shape47.cubeList.add(new ModelBox(Shape47, 3, 97, -21.0F, -10.0F, 20.0F, 2, 8, 2, 0.0F, true));
		Shape47.cubeList.add(new ModelBox(Shape47, 3, 97, -21.0F, -10.0F, 11.0F, 2, 8, 2, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		root.render(f5);
	}

	private void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		pFront1.rotateAngleZ = par3 * 0.067f * 11.25f;
		pTop7.rotateAngleY = par3 * 0.067f * 11.25f;
		pTop8.rotateAngleY = par3 * 0.067f * 11.25f;
	}
}

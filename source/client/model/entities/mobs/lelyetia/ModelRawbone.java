package net.tslat.aoa3.client.model.entities.mobs.lelyetia;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRawbone extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer Shape2;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer neck;
	private final ModelRenderer head2;
	private final ModelRenderer Shape12;
	private final ModelRenderer Shape1;

	public ModelRawbone() {
		textureWidth = 64;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);


		Shape2 = new ModelRenderer(this);
		Shape2.setRotationPoint(-2.0F, -10.0F, -6.0F);
		root.addChild(Shape2);
		Shape2.cubeList.add(new ModelBox(Shape2, 0, 58, 0.0F, -7.0F, 8.0F, 4, 2, 2, 0.0F, true));
		Shape2.cubeList.add(new ModelBox(Shape2, 0, 33, 4.0F, -7.0F, 6.0F, 2, 2, 6, 0.0F, true));
		Shape2.cubeList.add(new ModelBox(Shape2, 0, 33, -2.0F, -7.0F, 6.0F, 2, 2, 6, 0.0F, true));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(6.0F, -2.0F, 18.0F);
		Shape2.addChild(leg1);
		leg1.cubeList.add(new ModelBox(leg1, 10, 16, -1.0F, 9.0F, -2.0F, 4, 3, 4, 0.0F, true));
		leg1.cubeList.add(new ModelBox(leg1, 0, 16, 0.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F, true));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(-3.0F, -2.0F, 18.0F);
		Shape2.addChild(leg2);
		leg2.cubeList.add(new ModelBox(leg2, 10, 16, -2.0F, 9.0F, -2.0F, 4, 3, 4, 0.0F, true));
		leg2.cubeList.add(new ModelBox(leg2, 0, 16, -1.0F, 0.0F, -1.0F, 2, 9, 2, 0.0F, true));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(6.0F, -2.0F, 1.0F);
		Shape2.addChild(leg3);
		leg3.cubeList.add(new ModelBox(leg3, 10, 16, -1.0F, 9.0F, -3.0F, 4, 3, 4, 0.0F, true));
		leg3.cubeList.add(new ModelBox(leg3, 0, 16, 0.0F, 0.0F, -2.0F, 2, 9, 2, 0.0F, true));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(-3.0F, -2.0F, 1.0F);
		Shape2.addChild(leg4);
		leg4.cubeList.add(new ModelBox(leg4, 10, 16, -2.0F, 9.0F, -3.0F, 4, 3, 4, 0.0F, true));
		leg4.cubeList.add(new ModelBox(leg4, 0, 16, -1.0F, 0.0F, -2.0F, 2, 9, 2, 0.0F, true));

		neck = new ModelRenderer(this);
		neck.setRotationPoint(2.0F, -3.0F, 3.0F);
		Shape2.addChild(neck);
		setRotation(neck, -0.5236F, 0.0F, 0.0F);
		neck.cubeList.add(new ModelBox(neck, 19, 33, -1.0F, 0.0F, -5.0F, 2, 2, 5, 0.0F, true));
		neck.cubeList.add(new ModelBox(neck, 0, 42, -2.0F, -1.0F, -9.0F, 4, 4, 4, 0.0F, true));

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(0.0F, 0.2321F, -8.5981F);
		neck.addChild(head2);
		setRotation(head2, 0.5236F, 0.0F, 0.0F);
		head2.cubeList.add(new ModelBox(head2, 0, 0, -4.0F, -4.0F, -3.0F, 8, 8, 3, 0.0F, true));
		head2.cubeList.add(new ModelBox(head2, 24, 0, 2.0F, -6.0F, -1.0F, 5, 7, 2, 0.0F, true));
		head2.cubeList.add(new ModelBox(head2, 40, 0, -7.0F, -6.0F, -1.0F, 5, 7, 2, 0.0F, true));

		Shape12 = new ModelRenderer(this);
		Shape12.setRotationPoint(2.0F, -7.0F, 12.0F);
		Shape2.addChild(Shape12);
		setRotation(Shape12, -0.5236F, 0.0F, 0.0F);
		Shape12.cubeList.add(new ModelBox(Shape12, 0, 42, -5.0F, -0.1699F, -0.4378F, 4, 4, 4, 0.0F, true));
		Shape12.cubeList.add(new ModelBox(Shape12, 0, 42, 1.0F, -0.1699F, -0.4378F, 4, 4, 4, 0.0F, true));
		Shape12.cubeList.add(new ModelBox(Shape12, 0, 58, -2.0F, 0.8301F, 0.5622F, 4, 2, 2, 0.0F, true));
		Shape12.cubeList.add(new ModelBox(Shape12, 0, 33, 2.0F, 0.8301F, 3.5622F, 2, 2, 6, 0.0F, true));
		Shape12.cubeList.add(new ModelBox(Shape12, 0, 58, -2.0F, 0.8301F, 5.5622F, 4, 2, 2, 0.0F, true));
		Shape12.cubeList.add(new ModelBox(Shape12, 0, 33, -4.0F, 0.8301F, 3.5622F, 2, 2, 6, 0.0F, true));

		Shape1 = new ModelRenderer(this);
		Shape1.setRotationPoint(2.0F, -7.0F, 6.0F);
		Shape2.addChild(Shape1);
		setRotation(Shape1, -2.618F, 0.0F, 0.0F);
		Shape1.cubeList.add(new ModelBox(Shape1, 0, 42, 1.0F, -4.0622F, -0.3038F, 4, 4, 4, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 0, 42, -5.0F, -4.0622F, -0.3038F, 4, 4, 4, 0.0F, false));
		Shape1.cubeList.add(new ModelBox(Shape1, 0, 33, 2.0F, -3.0622F, 3.6962F, 2, 2, 6, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 0, 33, -4.0F, -3.0622F, 3.6962F, 2, 2, 6, 0.0F, false));
		Shape1.cubeList.add(new ModelBox(Shape1, 0, 58, -2.0F, -3.0622F, 0.6962F, 4, 2, 2, 0.0F, true));
		Shape1.cubeList.add(new ModelBox(Shape1, 0, 52, -2.0F, -3.0622F, 4.6962F, 4, 2, 3, 0.0F, true));
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		root.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg1.rotateAngleY = 0.0f;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg3.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelChimera extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg2;
	private final ModelRenderer Leg3;
	private final ModelRenderer Leg4;
	private final ModelRenderer Tail3;
	private final ModelRenderer Tail4;
	private final ModelRenderer Tail5;
	private final ModelRenderer Tail6;
	private final ModelRenderer Tail7;
	private final ModelRenderer Tail1;
	private final ModelRenderer Tail2;
	private final ModelRenderer Head1;
	private final ModelRenderer Head2;
	private final ModelRenderer Body2;

	public ModelChimera() {
		textureWidth = 64;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);


		Leg1 = new ModelRenderer(this);
		Leg1.setRotationPoint(3.5F, -8.0F, 7.0F);
		root.addChild(Leg1);
		Leg1.cubeList.add(new ModelBox(Leg1, 39, 14, -1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, true));

		Leg2 = new ModelRenderer(this);
		Leg2.setRotationPoint(-1.5F, -8.0F, 7.0F);
		root.addChild(Leg2);
		Leg2.cubeList.add(new ModelBox(Leg2, 39, 14, -1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, false));

		Leg3 = new ModelRenderer(this);
		Leg3.setRotationPoint(4.5F, -8.0F, -4.0F);
		root.addChild(Leg3);
		Leg3.cubeList.add(new ModelBox(Leg3, 39, 14, -1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, true));

		Leg4 = new ModelRenderer(this);
		Leg4.setRotationPoint(-2.5F, -8.0F, -4.0F);
		root.addChild(Leg4);
		Leg4.cubeList.add(new ModelBox(Leg4, 39, 14, -1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, false));

		Tail3 = new ModelRenderer(this);
		Tail3.setRotationPoint(1.0F, -11.0F, 8.0F);
		root.addChild(Tail3);
		setRotation(Tail3, 2.0944F, 0.0F, 0.0F);
		Tail3.cubeList.add(new ModelBox(Tail3, 33, 24, -2.0F, 1.0F, -2.0F, 4, 4, 4, 0.0F, true));

		Tail4 = new ModelRenderer(this);
		Tail4.setRotationPoint(0.0F, 0.0F, 0.0F);
		Tail3.addChild(Tail4);
		setRotation(Tail4, 0.6109F, 0.0F, 0.0F);
		Tail4.cubeList.add(new ModelBox(Tail4, 26, 14, -1.0F, 3.5F, -3.5F, 2, 6, 2, 0.0F, true));

		Tail5 = new ModelRenderer(this);
		Tail5.setRotationPoint(0.0F, 0.0F, 0.0F);
		Tail4.addChild(Tail5);
		setRotation(Tail5, 0.4363F, 0.0F, 0.0F);
		Tail5.cubeList.add(new ModelBox(Tail5, 33, 24, -2.0F, 7.0F, -8.0F, 4, 4, 4, 0.0F, true));

		Tail6 = new ModelRenderer(this);
		Tail6.setRotationPoint(0.0F, 0.0F, 0.0F);
		Tail5.addChild(Tail6);
		setRotation(Tail6, 0.6109F, 0.0F, 0.0F);
		Tail6.cubeList.add(new ModelBox(Tail6, 26, 14, -1.0F, 5.0F, -12.0F, 2, 6, 2, 0.0F, true));

		Tail7 = new ModelRenderer(this);
		Tail7.setRotationPoint(0.0F, 0.0F, 0.0F);
		Tail6.addChild(Tail7);
		setRotation(Tail7, 0.6109F, 0.0F, 0.0F);
		Tail7.cubeList.add(new ModelBox(Tail7, 33, 24, -2.0F, 1.0F, -17.0F, 4, 4, 4, 0.0F, true));

		Tail1 = new ModelRenderer(this);
		Tail1.setRotationPoint(0.0F, 0.0F, 0.0F);
		Tail7.addChild(Tail1);
		setRotation(Tail1, 0.4363F, 0.0F, 0.0F);
		Tail1.cubeList.add(new ModelBox(Tail1, 26, 14, -1.0F, -3.0F, -17.0F, 2, 6, 2, 0.0F, true));

		Tail2 = new ModelRenderer(this);
		Tail2.setRotationPoint(0.0F, 0.0F, 0.0F);
		Tail1.addChild(Tail2);
		setRotation(Tail2, 0.3491F, 0.0F, 0.0F);
		Tail2.cubeList.add(new ModelBox(Tail2, 33, 24, -2.0F, -4.0F, -18.0F, 4, 4, 4, 0.0F, true));

		Head1 = new ModelRenderer(this);
		Head1.setRotationPoint(0.0F, -2.0F, -16.0F);
		Tail2.addChild(Head1);
		setRotation(Head1, 1.1345F, 0.0F, 0.0F);
		Head1.cubeList.add(new ModelBox(Head1, 0, 0, -3.0F, -2.659F, -4.4716F, 6, 6, 4, 0.0F, true));
		Head1.cubeList.add(new ModelBox(Head1, 55, 1, 3.0F, -3.659F, -2.4716F, 3, 8, 1, 0.0F, false));
		Head1.cubeList.add(new ModelBox(Head1, 55, 1, -6.0F, -3.659F, -2.4716F, 3, 8, 1, 0.0F, true));
		Head1.cubeList.add(new ModelBox(Head1, 0, 10, -1.5F, 0.341F, -5.4716F, 3, 3, 1, 0.0F, true));

		Head2 = new ModelRenderer(this);
		Head2.setRotationPoint(1.0F, -10.5F, -7.0F);
		root.addChild(Head2);
		Head2.cubeList.add(new ModelBox(Head2, 0, 0, -3.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F, true));
		Head2.cubeList.add(new ModelBox(Head2, 0, 10, -1.5F, 0.0F, -3.0F, 3, 3, 1, 0.0F, true));
		Head2.cubeList.add(new ModelBox(Head2, 55, 1, 3.0F, -4.0F, 0.0F, 3, 8, 1, 0.0F, false));
		Head2.cubeList.add(new ModelBox(Head2, 55, 1, -6.0F, -4.0F, 0.0F, 3, 8, 1, 0.0F, true));

		Body2 = new ModelRenderer(this);
		Body2.setRotationPoint(0.0F, -10.0F, 2.0F);
		root.addChild(Body2);
		setRotation(Body2, 1.5708F, 0.0F, 0.0F);
		Body2.cubeList.add(new ModelBox(Body2, 0, 16, -2.0F, -2.0F, -3.0F, 6, 9, 6, 0.0F, true));
		Body2.cubeList.add(new ModelBox(Body2, 50, 17, 4.0F, 0.0F, -1.0F, 1, 9, 6, 0.0F, true));
		Body2.cubeList.add(new ModelBox(Body2, 50, 17, -3.0F, 0.0F, -1.0F, 1, 9, 6, 0.0F, false));
		Body2.cubeList.add(new ModelBox(Body2, 21, 0, -3.0F, -8.0F, -3.0F, 8, 6, 7, 0.0F, true));
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
		Head1.rotateAngleX = 1.13446401f + (par5 * (float)Math.PI / 180f * 0.5f);
		Head2.rotateAngleX = par5 * (float)Math.PI / 180f * 0.5f;
		Leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		Leg1.rotateAngleY = 0.0f;
		Leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		Leg3.rotateAngleY = 0.0f;
		Leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		Leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

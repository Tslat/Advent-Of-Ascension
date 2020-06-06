package net.tslat.aoa3.client.model.entities.minions;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBlissard extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer WolfHead;
	private final ModelRenderer Body;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg2;
	private final ModelRenderer Leg3;
	private final ModelRenderer Leg4;
	private final ModelRenderer Tail;
	private final ModelRenderer Tail3;
	private final ModelRenderer Tail5;

	public ModelBlissard() {
		textureWidth = 64;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);


		WolfHead = new ModelRenderer(this);
		WolfHead.setRotationPoint(0.0F, -10.5F, -8.5F);
		root.addChild(WolfHead);
		WolfHead.cubeList.add(new ModelBox(WolfHead, 0, 0, -3.0F, -3.0F, -2.0F, 6, 6, 4, 0.0F, true));
		WolfHead.cubeList.add(new ModelBox(WolfHead, 45, 14, 1.0F, -6.0F, 0.0F, 4, 6, 1, 0.0F, true));
		WolfHead.cubeList.add(new ModelBox(WolfHead, 45, 14, -5.0F, -6.0F, 0.0F, 4, 6, 1, 0.0F, true));
		WolfHead.cubeList.add(new ModelBox(WolfHead, 0, 10, -1.5F, -0.001F, -5.0F, 3, 3, 4, 0.0F, true));

		Body = new ModelRenderer(this);
		Body.setRotationPoint(-2.0F, -16.0F, 0.5F);
		root.addChild(Body);
		setRotation(Body, 1.5708F, 0.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 21, 31, 0.0F, -2.0F, -3.0F, 4, 8, 3, 0.0F, true));
		Body.cubeList.add(new ModelBox(Body, 21, 0, -2.0F, -8.0F, -9.0F, 8, 6, 7, 0.0F, true));
		Body.cubeList.add(new ModelBox(Body, 18, 14, -1.0F, -2.0F, -9.0F, 6, 9, 6, 0.0F, true));

		Leg1 = new ModelRenderer(this);
		Leg1.setRotationPoint(1.5F, -8.0F, 5.5F);
		root.addChild(Leg1);
		Leg1.cubeList.add(new ModelBox(Leg1, 0, 18, -1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, true));

		Leg2 = new ModelRenderer(this);
		Leg2.setRotationPoint(-1.5F, -8.0F, 5.5F);
		root.addChild(Leg2);
		Leg2.cubeList.add(new ModelBox(Leg2, 0, 18, -1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, true));

		Leg3 = new ModelRenderer(this);
		Leg3.setRotationPoint(1.5F, -8.0F, -5.5F);
		root.addChild(Leg3);
		Leg3.cubeList.add(new ModelBox(Leg3, 0, 18, -1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, true));

		Leg4 = new ModelRenderer(this);
		Leg4.setRotationPoint(-1.5F, -8.0F, -5.5F);
		root.addChild(Leg4);
		Leg4.cubeList.add(new ModelBox(Leg4, 0, 18, -1.0F, 0.0F, -1.0F, 2, 8, 2, 0.0F, true));

		Tail = new ModelRenderer(this);
		Tail.setRotationPoint(0.0F, -13.1F, 5.5F);
		root.addChild(Tail);
		setRotation(Tail, 1.9199F, 0.0F, 0.0F);
		Tail.cubeList.add(new ModelBox(Tail, 9, 18, -1.0F, 7.0F, -1.0F, 2, 3, 2, 0.0F, true));
		Tail.cubeList.add(new ModelBox(Tail, 9, 31, -1.0F, 0.0F, -1.0F, 2, 7, 3, 0.0F, true));

		Tail3 = new ModelRenderer(this);
		Tail3.setRotationPoint(0.0F, -8.0F, 5.5F);
		root.addChild(Tail3);
		setRotation(Tail3, 1.1301F, 0.0F, 0.0F);
		Tail3.cubeList.add(new ModelBox(Tail3, 9, 31, -1.0F, 0.0F, -1.0F, 2, 7, 3, 0.0F, true));
		Tail3.cubeList.add(new ModelBox(Tail3, 9, 18, -1.0F, 7.0F, -1.0F, 2, 3, 2, 0.0F, true));

		Tail5 = new ModelRenderer(this);
		Tail5.setRotationPoint(0.0F, -10.7F, 5.5F);
		root.addChild(Tail5);
		setRotation(Tail5, 1.4835F, 0.0F, 0.0F);
		Tail5.cubeList.add(new ModelBox(Tail5, 9, 31, -1.0F, 0.0F, -1.0F, 2, 7, 3, 0.0F, true));
		Tail5.cubeList.add(new ModelBox(Tail5, 9, 18, -1.0F, 7.0F, -1.0F, 2, 3, 2, 0.0F, true));
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
		Leg1.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		Leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		Leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		Leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
	}
}

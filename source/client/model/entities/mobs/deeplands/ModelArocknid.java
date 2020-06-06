package net.tslat.aoa3.client.model.entities.mobs.deeplands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelArocknid extends ModelBase {
	private final ModelRenderer Body;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg2;
	private final ModelRenderer Leg3;
	private final ModelRenderer Leg4;
	private final ModelRenderer Leg5;
	private final ModelRenderer Leg6;
	private final ModelRenderer Leg7;
	private final ModelRenderer Leg8;
	private final ModelRenderer Head;
	private final ModelRenderer Head3;
	private final ModelRenderer Body3;
	private final ModelRenderer RearEnd;

	public ModelArocknid() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 20.0F, 0.0F);
		Body.cubeList.add(new ModelBox(Body, 0, 0, -3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F, true));

		Leg1 = new ModelRenderer(this);
		Leg1.setRotationPoint(2.0F, 0.0F, 0.5F);
		Body.addChild(Leg1);
		setRotation(Leg1, 0.0F, -0.576F, -0.2443F);
		Leg1.cubeList.add(new ModelBox(Leg1, 18, 0, 0.4293F, -1.5413F, -0.7891F, 16, 2, 2, 0.0F, true));

		Leg2 = new ModelRenderer(this);
		Leg2.setRotationPoint(-2.0F, 0.0F, 0.5F);
		Body.addChild(Leg2);
		setRotation(Leg2, 0.0F, 0.576F, 0.2443F);
		Leg2.cubeList.add(new ModelBox(Leg2, 18, 0, -16.4293F, -1.5413F, -0.7891F, 16, 2, 2, 0.0F, false));

		Leg3 = new ModelRenderer(this);
		Leg3.setRotationPoint(2.0F, 0.0F, 0.5F);
		Body.addChild(Leg3);
		setRotation(Leg3, 0.0F, -0.2793F, -0.2443F);
		Leg3.cubeList.add(new ModelBox(Leg3, 18, 0, -0.0052F, -1.5196F, -1.0519F, 16, 2, 2, 0.0F, true));

		Leg4 = new ModelRenderer(this);
		Leg4.setRotationPoint(-2.0F, 0.0F, 0.5F);
		Body.addChild(Leg4);
		setRotation(Leg4, 0.0F, 0.2793F, 0.2443F);
		Leg4.cubeList.add(new ModelBox(Leg4, 18, 0, -15.9948F, -1.5196F, -1.0519F, 16, 2, 2, 0.0F, false));

		Leg5 = new ModelRenderer(this);
		Leg5.setRotationPoint(2.0F, 0.0F, 0.5F);
		Body.addChild(Leg5);
		setRotation(Leg5, 0.0F, 0.2793F, -0.2443F);
		Leg5.cubeList.add(new ModelBox(Leg5, 18, 0, -0.0052F, -1.5276F, -0.9481F, 16, 2, 2, 0.0F, true));

		Leg6 = new ModelRenderer(this);
		Leg6.setRotationPoint(-2.0F, 0.0F, 0.5F);
		Body.addChild(Leg6);
		setRotation(Leg6, 0.0F, -0.2793F, 0.2443F);
		Leg6.cubeList.add(new ModelBox(Leg6, 18, 0, -15.9948F, -1.5276F, -0.9481F, 16, 2, 2, 0.0F, false));

		Leg7 = new ModelRenderer(this);
		Leg7.setRotationPoint(2.0F, 0.0F, 0.5F);
		Body.addChild(Leg7);
		setRotation(Leg7, 0.0F, 0.576F, -0.2443F);
		Leg7.cubeList.add(new ModelBox(Leg7, 18, 0, 0.4293F, -1.5513F, -1.2109F, 16, 2, 2, 0.0F, true));

		Leg8 = new ModelRenderer(this);
		Leg8.setRotationPoint(-2.0F, 0.0F, 0.5F);
		Body.addChild(Leg8);
		setRotation(Leg8, 0.0F, -0.576F, 0.2443F);
		Leg8.cubeList.add(new ModelBox(Leg8, 18, 0, -16.4293F, -1.5513F, -1.2109F, 16, 2, 2, 0.0F, false));

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, -3.0F);
		Body.addChild(Head);
		Head.cubeList.add(new ModelBox(Head, 32, 4, -4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F, true));

		Head3 = new ModelRenderer(this);
		Head3.setRotationPoint(0.0F, 0.0F, 6.0F);
		Head.addChild(Head3);
		setRotation(Head3, 0.6109F, 0.0F, 0.0F);
		Head3.cubeList.add(new ModelBox(Head3, 4, 33, 2.0F, -11.0F, -9.0F, 1, 4, 9, 0.0F, true));
		Head3.cubeList.add(new ModelBox(Head3, 4, 33, -3.0F, -11.0F, -9.0F, 1, 4, 9, 0.0F, true));

		Body3 = new ModelRenderer(this);
		Body3.setRotationPoint(-1.0F, -1.0F, 2.0F);
		Body.addChild(Body3);
		setRotation(Body3, 0.912F, 0.0F, 0.0F);
		Body3.cubeList.add(new ModelBox(Body3, 31, 23, 2.0F, -4.0F, -2.0F, 1, 6, 13, 0.0F, true));
		Body3.cubeList.add(new ModelBox(Body3, 31, 23, -1.0F, -4.0F, -2.0F, 1, 6, 13, 0.0F, true));

		RearEnd = new ModelRenderer(this);
		RearEnd.setRotationPoint(0.0F, 3.0F, 3.0F);
		Body.addChild(RearEnd);
		RearEnd.cubeList.add(new ModelBox(RearEnd, 0, 12, -5.0F, -4.0F, 0.0F, 3, 4, 12, 0.0F, true));
		RearEnd.cubeList.add(new ModelBox(RearEnd, 0, 12, 2.0F, -4.0F, 0.0F, 3, 4, 12, 0.0F, true));
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		Body.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		float rot1 = -(MathHelper.cos(par1 * 1.324f) * 0.4f) * par2;
		float rot2 = -(MathHelper.cos(par1 * 1.324f + (float)Math.PI) * 0.4f) * par2;
		float rot3 = -(MathHelper.cos(par1 * 1.324f + (float)Math.PI / 2f) * 0.4f) * par2;
		float rot4 = -(MathHelper.cos(par1 * 1.324f + (float)Math.PI * 1.5f) * 0.4f) * par2;
		float lift1 = Math.abs(MathHelper.sin(par1 * 0.6662f) * 0.4f) * par2;
		float lift2 = Math.abs(MathHelper.sin(par1 * 0.6662f + (float)Math.PI) * 0.4f) * par2;
		float lift3 = Math.abs(MathHelper.sin(par1 * 0.6662f + (float)Math.PI / 2f) * 0.4f) * par2;
		float lift4 = Math.abs(MathHelper.sin(par1 * 0.6662f + (float)Math.PI * 1.5f) * 0.4f) * par2;
		float quarterPi = -(float)Math.PI / 4f;
		float eighthPi = -(float)Math.PI / 8f;

		Leg1.rotateAngleY = quarterPi + rot1;
		Leg2.rotateAngleY = -quarterPi + rot1;
		Leg3.rotateAngleY = eighthPi + rot2;
		Leg4.rotateAngleY = -eighthPi + rot2;
		Leg5.rotateAngleY = -eighthPi + rot3;
		Leg6.rotateAngleY = eighthPi + rot3;
		Leg7.rotateAngleY = -quarterPi + rot4;
		Leg8.rotateAngleY = quarterPi + rot4;

		Leg1.rotateAngleZ = -eighthPi + lift1;
		Leg2.rotateAngleZ = eighthPi - lift1;
		Leg3.rotateAngleZ = -eighthPi * 0.74f + lift2;
		Leg4.rotateAngleZ = eighthPi * 0.74f - lift2;
		Leg5.rotateAngleZ = -eighthPi * 0.74f + lift3;
		Leg6.rotateAngleZ = eighthPi * 0.74f - lift3;
		Leg7.rotateAngleZ = -eighthPi + lift4;
		Leg8.rotateAngleZ = eighthPi - lift4;
	}
}

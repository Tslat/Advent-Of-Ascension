package net.tslat.aoa3.client.model.entities.mobs.runandor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelAriel extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer body;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightleg;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightarm2;
	private final ModelRenderer head;
	private final ModelRenderer wing;
	private final ModelRenderer body4;
	private final ModelRenderer body7;
	private final ModelRenderer wing2;
	private final ModelRenderer body2;
	private final ModelRenderer body3;

	public ModelAriel() {
		textureWidth = 64;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 29.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(-7.0F, -28.0F, 5.0F);
		root.addChild(body);
		body.cubeList.add(new ModelBox(body, 16, 16, 3.0F, -2.0F, -7.0F, 8, 13, 4, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 42, 5, 11.0F, -3.0F, -8.0F, 4, 3, 6, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 42, 5, -1.0F, -3.0F, -8.0F, 4, 3, 6, 0.0F, false));

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(3.0F, -1.0F, -5.0F);
		body.addChild(leftarm);
		leftarm.cubeList.add(new ModelBox(leftarm, 41, 16, -3.0F, 0.0F, -2.0F, 3, 12, 4, 0.0F, false));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(9.0F, 11.0F, -5.0F);
		body.addChild(rightleg);
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(5.0F, 11.0F, -5.0F);
		body.addChild(leftleg);
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true));

		rightarm2 = new ModelRenderer(this);
		rightarm2.setRotationPoint(11.0F, -1.0F, -5.0F);
		body.addChild(rightarm2);
		rightarm2.cubeList.add(new ModelBox(rightarm2, 41, 16, 0.0F, 0.0F, -2.0F, 3, 12, 4, 0.0F, true));

		head = new ModelRenderer(this);
		head.setRotationPoint(7.0F, -2.0F, -5.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, true));

		wing = new ModelRenderer(this);
		wing.setRotationPoint(-2.0F, -28.0F, 0.0F);
		setRotation(wing, 0.0F, 0.5236F, 0.0F);
		root.addChild(wing);
		wing.cubeList.add(new ModelBox(wing, 0, 34, 2.0F, 0.0F, 3.0F, 2, 3, 3, 0.0F, true));

		body4 = new ModelRenderer(this);
		body4.setRotationPoint(3.0F, 3.0F, 6.0F);
		setRotation(body4, 0.0F, -0.5236F, -1.5708F);
		wing.addChild(body4);
		body4.cubeList.add(new ModelBox(body4, 8, 41, 0.0F, -1.0F, -2.0F, 9, 2, 2, -0.1F, true));

		body7 = new ModelRenderer(this);
		body7.setRotationPoint(8.5F, 0.4F, -1.65F);
		setRotation(body7, 1.5708F, -1.0472F, 0.0F);
		body4.addChild(body7);
		body7.cubeList.add(new ModelBox(body7, 23, 35, 0.0835F, -0.3714F, -0.7F, 10, 2, 2, 0.1F, true));
		body7.cubeList.add(new ModelBox(body7, 0, 45, 0.0835F, 1.6286F, 0.3F, 10, 18, 0, 0.0F, true));
		body7.cubeList.add(new ModelBox(body7, 47, 37, 0.0835F, 19.6286F, 0.3F, 6, 10, 0, 0.0F, true));

		wing2 = new ModelRenderer(this);
		wing2.setRotationPoint(2.0F, -28.0F, 0.0F);
		setRotation(wing2, 0.0F, -0.5236F, 0.0F);
		root.addChild(wing2);
		wing2.cubeList.add(new ModelBox(wing2, 0, 34, -4.0F, 0.0F, 3.0F, 2, 3, 3, 0.0F, false));

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(-3.0F, 3.0F, 6.0F);
		setRotation(body2, 0.0F, 0.5236F, 1.5708F);
		wing2.addChild(body2);
		body2.cubeList.add(new ModelBox(body2, 8, 41, -9.0F, -1.0F, -2.0F, 9, 2, 2, -0.1F, false));

		body3 = new ModelRenderer(this);
		body3.setRotationPoint(-8.5F, 0.4F, -1.65F);
		setRotation(body3, 1.5708F, 1.0472F, 0.0F);
		body2.addChild(body3);
		body3.cubeList.add(new ModelBox(body3, 23, 35, -10.0835F, -0.3714F, -0.7F, 10, 2, 2, 0.1F, false));
		body3.cubeList.add(new ModelBox(body3, 0, 45, -10.0835F, 1.6286F, 0.3F, 10, 18, 0, 0.0F, false));
		body3.cubeList.add(new ModelBox(body3, 47, 37, -6.0835F, 19.6286F, 0.3F, 6, 10, 0, 0.0F, false));
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotations(par2, par3, par4, par5, par6, par7, par1Entity);
		root.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotations(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = par5 / 54.11268f;
		rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0.0f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		rightleg.rotateAngleY = 0.0f;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

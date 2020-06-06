package net.tslat.aoa3.client.model.entities.mobs.barathos;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCryptid extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer head;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer body2;
	private final ModelRenderer body6;
	private final ModelRenderer body5;
	private final ModelRenderer tail;
	private final ModelRenderer body3;

	public ModelCryptid() {
		textureWidth = 128;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -11.0F, -10.0F);
		root.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, 4.0F, -11.0F, 8, 3, 11, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 40, 12, -5.0F, -6.0F, -12.0F, 10, 2, 12, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 14, -4.0F, -4.0F, -12.0F, 8, 8, 12, 0.0F, true));

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(7.0F, -12.0F, 7.0F);
		root.addChild(leg1);
		leg1.cubeList.add(new ModelBox(leg1, 28, 21, -3.0F, 10.0F, -5.0F, 2, 2, 3, 0.0F, true));
		leg1.cubeList.add(new ModelBox(leg1, 0, 44, -3.0F, 0.0F, -2.0F, 6, 12, 6, 0.0F, false));
		leg1.cubeList.add(new ModelBox(leg1, 28, 21, 1.0F, 10.0F, -5.0F, 2, 2, 3, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(-7.0F, -12.0F, 7.0F);
		root.addChild(leg2);
		leg2.cubeList.add(new ModelBox(leg2, 28, 21, -3.0F, 10.0F, -5.0F, 2, 2, 3, 0.0F, true));
		leg2.cubeList.add(new ModelBox(leg2, 0, 44, -3.0F, 0.0F, -2.0F, 6, 12, 6, 0.0F, true));
		leg2.cubeList.add(new ModelBox(leg2, 28, 21, 1.0F, 10.0F, -5.0F, 2, 2, 3, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(7.0F, -12.0F, -5.0F);
		root.addChild(leg3);
		leg3.cubeList.add(new ModelBox(leg3, 28, 21, -3.0F, 10.0F, -6.0F, 2, 2, 3, 0.0F, true));
		leg3.cubeList.add(new ModelBox(leg3, 0, 44, -3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F, false));
		leg3.cubeList.add(new ModelBox(leg3, 28, 21, 1.0F, 10.0F, -6.0F, 2, 2, 3, 0.0F, false));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(-7.0F, -12.0F, -5.0F);
		root.addChild(leg4);
		leg4.cubeList.add(new ModelBox(leg4, 28, 21, -3.0F, 10.0F, -6.0F, 2, 2, 3, 0.0F, true));
		leg4.cubeList.add(new ModelBox(leg4, 0, 44, -3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F, true));
		leg4.cubeList.add(new ModelBox(leg4, 28, 21, 1.0F, 10.0F, -6.0F, 2, 2, 3, 0.0F, false));

		body2 = new ModelRenderer(this);
		body2.setRotationPoint(0.0F, 0.0F, 0.0F);
		root.addChild(body2);
		body2.cubeList.add(new ModelBox(body2, 34, 36, -6.0F, -13.0F, -10.0F, 12, 6, 22, 0.0F, true));

		body6 = new ModelRenderer(this);
		body6.setRotationPoint(-1.0F, -20.0F, -1.0F);
		body2.addChild(body6);
		setRotation(body6, 1.9199F, 0.0F, 0.0F);
		body6.cubeList.add(new ModelBox(body6, 52, 12, -4.0F, -10.0F, -4.0F, 10, 18, 2, 0.0F, true));

		body5 = new ModelRenderer(this);
		body5.setRotationPoint(0.0F, -17.0F, 0.0F);
		body2.addChild(body5);
		setRotation(body5, 1.5708F, 0.0F, 0.0F);
		body5.cubeList.add(new ModelBox(body5, 52, 12, -7.0F, -10.0F, -4.0F, 14, 22, 2, 0.0F, true));

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -11.0F, 10.0F);
		body2.addChild(tail);
		setRotation(tail, 1.1345F, 0.0F, 0.0F);
		tail.cubeList.add(new ModelBox(tail, 32, 44, -4.0F, 0.0F, -2.0F, 8, 10, 4, 0.0F, true));
		tail.cubeList.add(new ModelBox(tail, 52, 24, -5.0F, 0.0F, 2.0F, 10, 10, 2, 0.0F, true));

		body3 = new ModelRenderer(this);
		body3.setRotationPoint(-1.0F, -19.0F, 8.0F);
		body2.addChild(body3);
		setRotation(body3, 1.7977F, 0.0F, 0.0F);
		body3.cubeList.add(new ModelBox(body3, 52, 19, -4.0F, -10.0F, -4.0F, 10, 15, 2, 0.0F, false));
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

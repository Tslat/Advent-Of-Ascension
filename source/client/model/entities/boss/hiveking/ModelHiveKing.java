package net.tslat.aoa3.client.model.entities.boss.hiveking;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHiveKing extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer head13;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head6;
	private final ModelRenderer head5;
	private final ModelRenderer head8;
	private final ModelRenderer head4;
	private final ModelRenderer body17;
	private final ModelRenderer body21;
	private final ModelRenderer body18;

	public ModelHiveKing() {
		textureWidth = 256;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);


		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(10.0F, -12.0F, 18.0F);
		root.addChild(leg1);
		leg1.cubeList.add(new ModelBox(leg1, 0, 14, -2.0F, 0.0F, -2.0F, 6, 12, 6, 0.0F, true));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(-10.0F, -12.0F, 19.0F);
		root.addChild(leg2);
		leg2.cubeList.add(new ModelBox(leg2, 0, 14, -3.0F, 0.0F, -3.0F, 6, 12, 6, 0.0F, false));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(12.0F, -17.0F, 1.0F);
		root.addChild(leg3);
		leg3.cubeList.add(new ModelBox(leg3, 31, 8, -3.0F, 0.0F, -3.0F, 6, 17, 6, 0.0F, true));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(-12.0F, -17.0F, 1.0F);
		root.addChild(leg4);
		leg4.cubeList.add(new ModelBox(leg4, 31, 8, -3.0F, 0.0F, -3.0F, 6, 17, 6, 0.0F, false));

		head13 = new ModelRenderer(this);
		head13.setRotationPoint(0.0F, -18.0F, 0.0F);
		root.addChild(head13);
		head13.cubeList.add(new ModelBox(head13, 89, 16, -8.0F, -4.0F, -3.0F, 16, 12, 4, 0.0F, true));
		head13.cubeList.add(new ModelBox(head13, 67, 12, 2.0F, -6.0F, -3.0F, 5, 2, 4, 0.0F, false));
		head13.cubeList.add(new ModelBox(head13, 67, 12, -7.0F, -6.0F, -3.0F, 5, 2, 4, 0.0F, true));
		head13.cubeList.add(new ModelBox(head13, 58, 18, 8.0F, -2.0F, -3.0F, 2, 2, 4, 0.0F, false));
		head13.cubeList.add(new ModelBox(head13, 58, 18, -10.0F, -2.0F, -3.0F, 2, 2, 4, 0.0F, true));
		head13.cubeList.add(new ModelBox(head13, 35, 1, -3.0F, -4.0F, -4.0F, 6, 5, 1, 0.0F, true));
		head13.cubeList.add(new ModelBox(head13, 35, 1, 2.0F, 2.0F, -4.0F, 6, 5, 1, 0.0F, true));
		head13.cubeList.add(new ModelBox(head13, 35, 1, -8.0F, 2.0F, -4.0F, 6, 5, 1, 0.0F, false));

		head2 = new ModelRenderer(this);
		head2.setRotationPoint(-4.0F, 4.0F, 0.0F);
		head13.addChild(head2);
		setRotation(head2, -0.4363F, 0.0F, -2.2689F);
		head2.cubeList.add(new ModelBox(head2, 0, 0, -2.0F, -2.0F, -9.0F, 4, 4, 9, 0.0F, true));

		head3 = new ModelRenderer(this);
		head3.setRotationPoint(0.0F, 0.0F, 0.0F);
		head2.addChild(head3);
		setRotation(head3, 0.8727F, 0.0F, 0.0F);
		head3.cubeList.add(new ModelBox(head3, 60, 0, -1.0F, -7.0F, -14.0F, 2, 2, 9, 0.0F, true));

		head6 = new ModelRenderer(this);
		head6.setRotationPoint(4.0F, 4.0F, 0.0F);
		head13.addChild(head6);
		setRotation(head6, -0.4363F, 0.0F, 2.2689F);
		head6.cubeList.add(new ModelBox(head6, 0, 0, -2.0F, -2.0F, -9.0F, 4, 4, 9, 0.0F, true));

		head5 = new ModelRenderer(this);
		head5.setRotationPoint(0.0F, 0.0F, 0.0F);
		head6.addChild(head5);
		setRotation(head5, 0.8727F, 0.0F, 0.0F);
		head5.cubeList.add(new ModelBox(head5, 60, 0, -1.0F, -7.0F, -14.0F, 2, 2, 9, 0.0F, true));

		head8 = new ModelRenderer(this);
		head8.setRotationPoint(0.0F, 0.0F, 0.0F);
		head13.addChild(head8);
		setRotation(head8, -0.4363F, 0.0F, 0.0F);
		head8.cubeList.add(new ModelBox(head8, 0, 0, -2.0F, -2.0F, -9.0F, 4, 4, 9, 0.0F, true));

		head4 = new ModelRenderer(this);
		head4.setRotationPoint(0.0F, 0.0F, 0.0F);
		head8.addChild(head4);
		setRotation(head4, 0.8727F, 0.0F, 0.0F);
		head4.cubeList.add(new ModelBox(head4, 60, 0, -1.0F, -7.0F, -14.0F, 2, 2, 9, 0.0F, true));

		body17 = new ModelRenderer(this);
		body17.setRotationPoint(0.0F, -10.0F, 8.0F);
		root.addChild(body17);
		setRotation(body17, -0.1745F, 0.0F, 0.0F);
		body17.cubeList.add(new ModelBox(body17, 92, 0, -9.0F, -6.0F, -9.0F, 18, 6, 4, 0.0F, true));
		body17.cubeList.add(new ModelBox(body17, 136, 9, -8.0F, -4.0F, -7.0F, 16, 4, 18, 0.0F, true));
		body17.cubeList.add(new ModelBox(body17, 136, 9, -7.0F, -8.0F, -9.0F, 14, 2, 20, 0.0F, true));
		body17.cubeList.add(new ModelBox(body17, 131, 15, -7.0F, -6.0F, 6.0F, 5, 2, 5, 0.0F, true));
		body17.cubeList.add(new ModelBox(body17, 131, 15, 2.0F, -6.0F, 6.0F, 5, 2, 5, 0.0F, false));

		body21 = new ModelRenderer(this);
		body21.setRotationPoint(0.0F, 0.0F, 0.0F);
		body17.addChild(body21);
		setRotation(body21, 0.3491F, 0.0F, 0.0F);
		body21.cubeList.add(new ModelBox(body21, 207, 7, -3.0F, -8.0F, 0.0F, 2, 5, 20, 0.0F, false));
		body21.cubeList.add(new ModelBox(body21, 207, 7, 1.0F, -8.0F, 0.0F, 2, 5, 20, 0.0F, true));

		body18 = new ModelRenderer(this);
		body18.setRotationPoint(0.0F, 0.0F, 0.0F);
		body17.addChild(body18);
		setRotation(body18, 0.5236F, 0.0F, 0.0F);
		body18.cubeList.add(new ModelBox(body18, 207, 7, -6.0F, -13.0F, -4.0F, 2, 5, 20, 0.0F, false));
		body18.cubeList.add(new ModelBox(body18, 207, 7, 4.0F, -13.0F, -4.0F, 2, 5, 20, 0.0F, true));
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
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg3.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
	}
}

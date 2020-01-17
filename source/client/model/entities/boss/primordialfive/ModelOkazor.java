package net.tslat.aoa3.client.model.entities.boss.primordialfive;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelOkazor extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer body4;
	private final ModelRenderer rightarm2;
	private final ModelRenderer leftarm2;
	private final ModelRenderer mace;
	private final ModelRenderer head;
	private final ModelRenderer head3;
	private final ModelRenderer head7;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightleg;

	public ModelOkazor() {
		textureWidth = 128;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);

		body4 = new ModelRenderer(this);
		body4.setRotationPoint(0.0F, -29.0F, 0.0F);
		root.addChild(body4);
		body4.cubeList.add(new ModelBox(body4, 57, 16, -4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F, true));
		body4.cubeList.add(new ModelBox(body4, 50, 50, -3.0F, 9.0F, -3.5F, 6, 3, 2, 0.0F, true));
		body4.cubeList.add(new ModelBox(body4, 50, 39, -5.5F, 12.0F, -3.5F, 11, 2, 6, 0.0F, true));
		body4.cubeList.add(new ModelBox(body4, 27, 47, -5.0F, -1.0F, -3.0F, 3, 4, 6, 0.0F, true));
		body4.cubeList.add(new ModelBox(body4, 16, 16, -5.0F, 14.0F, -3.0F, 10, 12, 5, 0.0F, true));
		body4.cubeList.add(new ModelBox(body4, 2, 47, 2.0F, -1.0F, -3.0F, 3, 4, 6, 0.0F, true));
		body4.cubeList.add(new ModelBox(body4, 23, 36, -9.0F, -1.0F, -3.0F, 4, 2, 6, 0.0F, true));
		body4.cubeList.add(new ModelBox(body4, 1, 36, 5.0F, -1.0F, -3.0F, 4, 2, 6, 0.0F, true));

		rightarm2 = new ModelRenderer(this);
		rightarm2.setRotationPoint(7.5F, 1.25F, -0.5F);
		body4.addChild(rightarm2);
		rightarm2.cubeList.add(new ModelBox(rightarm2, 83, 16, -1.5F, -0.25F, -1.5F, 3, 16, 3, 0.0F, true));
		rightarm2.cubeList.add(new ModelBox(rightarm2, 104, 16, 1.5F, 8.75F, -2.5F, 1, 6, 5, 0.0F, true));

		leftarm2 = new ModelRenderer(this);
		leftarm2.setRotationPoint(-7.5F, 1.0F, -0.5F);
		body4.addChild(leftarm2);
		leftarm2.cubeList.add(new ModelBox(leftarm2, 83, 16, -1.5F, 0.0F, -1.5F, 3, 16, 3, 0.0F, true));
		leftarm2.cubeList.add(new ModelBox(leftarm2, 117, 16, -2.5F, 9.0F, -2.5F, 1, 6, 5, 0.0F, true));

		mace = new ModelRenderer(this);
		mace.setRotationPoint(15.0F, 14.25F, 0.0F);
		leftarm2.addChild(mace);
		mace.cubeList.add(new ModelBox(mace, 88, 39, -16.0F, -1.25F, -7.5F, 2, 2, 6, 0.0F, true));
		mace.cubeList.add(new ModelBox(mace, 83, 49, -18.0F, -3.25F, -10.5F, 6, 6, 3, 0.0F, true));
		mace.cubeList.add(new ModelBox(mace, 83, 59, -18.0F, 2.75F, -10.5F, 2, 2, 2, 0.0F, true));
		mace.cubeList.add(new ModelBox(mace, 83, 59, -12.0F, 0.75F, -10.5F, 2, 2, 2, 0.0F, true));
		mace.cubeList.add(new ModelBox(mace, 83, 59, -14.0F, 2.75F, -10.5F, 2, 2, 2, 0.0F, true));
		mace.cubeList.add(new ModelBox(mace, 83, 59, -14.0F, -5.25F, -10.5F, 2, 2, 2, 0.0F, true));
		mace.cubeList.add(new ModelBox(mace, 83, 59, -18.0F, -5.25F, -10.5F, 2, 2, 2, 0.0F, true));
		mace.cubeList.add(new ModelBox(mace, 83, 59, -12.0F, -3.25F, -10.5F, 2, 2, 2, 0.0F, true));
		mace.cubeList.add(new ModelBox(mace, 83, 59, -20.0F, 0.75F, -10.5F, 2, 2, 2, 0.0F, true));
		mace.cubeList.add(new ModelBox(mace, 83, 59, -20.0F, -3.25F, -10.5F, 2, 2, 2, 0.0F, true));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, -1.0F);
		body4.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -3.0F, -8.0F, -4.0F, 6, 8, 6, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 65, 0, -4.0F, -9.0F, -3.0F, 1, 7, 6, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 25, 0, -3.0F, -9.0F, -3.0F, 6, 1, 5, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 80, 0, -3.0F, -9.0F, 2.0F, 6, 7, 1, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 49, 0, 3.0F, -9.0F, -3.0F, 1, 7, 6, 0.0F, true));

		head3 = new ModelRenderer(this);
		head3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(head3, 0.0F, 0.0F, 0.7854F);
		head.addChild(head3);
		head3.cubeList.add(new ModelBox(head3, 111, 3, -2.0F, -11.0F, -1.0F, 2, 7, 3, 0.0F, false));

		head7 = new ModelRenderer(this);
		head7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(head7, 0.0F, 0.0F, -0.7854F);
		head.addChild(head7);
		head7.cubeList.add(new ModelBox(head7, 97, 3, 0.0F, -11.0F, -1.0F, 2, 7, 3, 0.0F, true));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(-2.0F, 23.0F, 0.0F);
		body4.addChild(leftleg);
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 16, -2.0F, 0.0F, -2.0F, 3, 6, 3, 0.0F, true));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(2.0F, 23.0F, 0.0F);
		body4.addChild(rightleg);
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 16, -1.0F, 0.0F, -2.0F, 3, 6, 3, 0.0F, true));
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		root.render(par7);
	}

	private void setRotationAngle(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head.rotateAngleY = (float)(par4 / 180f / Math.PI);
		rightarm2.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + Math.PI)) * 2.0f * par2 * 0.5f;
		rightarm2.rotateAngleZ = 0;
		leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm2.rotateAngleZ = 0;
		leftleg.rotateAngleX = MathHelper.cos((float)(par1 * 0.6662f + Math.PI)) * 1.4f * par2;
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
	}
}

package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBugeye extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer leg5;
	private final ModelRenderer leg6;
	private final ModelRenderer body;
	private final ModelRenderer head;

	public ModelBugeye() {
		textureWidth = 64;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(8.0F, -12.0F, 7.5F);
		root.addChild(leg1);
		leg1.cubeList.add(new ModelBox(leg1, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(-8.0F, -12.0F, 7.5F);
		root.addChild(leg2);
		leg2.cubeList.add(new ModelBox(leg2, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true));

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(8.0F, -12.0F, 1.0F);
		root.addChild(leg3);
		leg3.cubeList.add(new ModelBox(leg3, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(-8.0F, -12.0F, 1.0F);
		root.addChild(leg4);
		leg4.cubeList.add(new ModelBox(leg4, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true));

		leg5 = new ModelRenderer(this);
		leg5.setRotationPoint(8.0F, -12.0F, -5.5F);
		root.addChild(leg5);
		leg5.cubeList.add(new ModelBox(leg5, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

		leg6 = new ModelRenderer(this);
		leg6.setRotationPoint(-8.0F, -12.0F, -5.5F);
		root.addChild(leg6);
		leg6.cubeList.add(new ModelBox(leg6, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -13.0F, 2.0F);
		setRotation(body, 1.5708F, 0.0F, 0.0F);
		root.addChild(body);
		body.cubeList.add(new ModelBox(body, 1, 35, -8.0F, -10.0F, -7.0F, 16, 18, 9, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 35, 11, -5.0F, -10.0F, 2.0F, 10, 18, 3, 0.0F, true));

		head = new ModelRenderer(this);
		head.setRotationPoint(1.0F, 2.0F, -10.0F);
		setRotation(head, -1.5708F, 0.0F, 0.0F);
		body.addChild(head);
		head.cubeList.add(new ModelBox(head, 0, 0, -6.0F, -13.0F, -15.0F, 10, 10, 3, 0.0F, true));
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
		leg5.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leg5.rotateAngleY = 0.0f;
		leg2.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg4.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
		leg6.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;
	}
}

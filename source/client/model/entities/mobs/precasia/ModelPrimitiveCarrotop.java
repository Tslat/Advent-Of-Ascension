package net.tslat.aoa3.client.model.entities.mobs.precasia;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.client.model.entities.animations.ModelAnimatable;

public class ModelPrimitiveCarrotop extends ModelAnimatable {
	private final ModelRenderer root;
	private final ModelRenderer rightleg;
	private final ModelRenderer leftleg;
	private final ModelRenderer body;
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone7;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer bone6;
	private final ModelRenderer bone8;
	private final ModelRenderer bone3;

	public ModelPrimitiveCarrotop() {
		textureWidth = 64;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(4.0F, -16.0F, 0.0F);
		root.addChild(rightleg);
		rightleg.cubeList.add(new ModelBox(rightleg, 19, 16, -1.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, true));
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 16, 0.0F, 2.0F, -1.0F, 2, 14, 2, 0.0F, true));
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 14, -1.0F, 12.1333F, -2.0F, 4, 4, 4, 0.0F, true));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(-4.0F, -16.0F, 0.0F);
		root.addChild(leftleg);
		leftleg.cubeList.add(new ModelBox(leftleg, 19, 16, -3.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, true));
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 16, -2.0F, 2.0F, -1.0F, 2, 14, 2, 0.0F, true));
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 14, -3.0F, 12.1333F, -2.0F, 4, 4, 4, 0.0F, true));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -20.0F, 0.0F);
		root.addChild(body);
		body.cubeList.add(new ModelBox(body, 16, 16, -1.5F, -13.0F, -1.5F, 3, 3, 3, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 0, 6, -3.0F, 10.0F, -1.0F, 2, 2, 2, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 10, 16, -3.5F, -3.0F, -3.5F, 7, 3, 7, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 16, 16, -1.0F, -0.15F, -4.499F, 2, 2, 1, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 10, 16, -3.0F, -5.5F, -3.0F, 6, 3, 6, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 16, 16, -1.0F, -16.0F, -1.0F, 2, 3, 2, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 16, 16, -2.5F, -8.0F, -2.5F, 5, 3, 5, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 16, 16, -2.0F, -10.5F, -2.0F, 4, 3, 4, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 8, 27, -4.0F, 4.0F, 1.0F, 8, 2, 3, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 0, 6, -1.0F, 8.0F, -1.0F, 2, 6, 2, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 0, 6, 1.0F, 10.0F, -1.0F, 2, 2, 2, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 16, 0, -4.0F, 0.0F, -4.0F, 8, 4, 8, 0.0F, true));
		body.cubeList.add(new ModelBox(body, 34, 24, -4.0F, 6.0F, -2.0F, 8, 2, 6, 0.0F, true));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 20.0F, 0.0F);
		setRotationAngle(bone, 0.0F, 0.0F, -0.3491F);
		body.addChild(bone);
		bone.cubeList.add(new ModelBox(bone, 16, 16, 7.1474F, -18.7134F, -4.5F, 3, 2, 3, 0.0F, true));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 20.0F, 0.0F);
		setRotationAngle(bone2, 0.0F, 0.0F, 0.3491F);
		body.addChild(bone2);
		bone2.cubeList.add(new ModelBox(bone2, 16, 16, -10.1474F, -18.7134F, -4.5F, 3, 2, 3, 0.0F, false));

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone4, 0.4363F, -0.0873F, -0.5236F);
		body.addChild(bone4);
		bone4.cubeList.add(new ModelBox(bone4, 16, 16, -1.0F, -5.25F, -1.0F, 1, 3, 1, 0.0F, true));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone5, -0.5236F, -0.0873F, 0.4363F);
		body.addChild(bone5);
		bone5.cubeList.add(new ModelBox(bone5, 16, 16, 0.5F, -5.75F, -0.25F, 1, 4, 1, 0.0F, true));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone7, 0.6109F, -0.0873F, 0.0F);
		body.addChild(bone7);
		bone7.cubeList.add(new ModelBox(bone7, 16, 16, 0.5F, -3.25F, -2.75F, 1, 3, 1, 0.0F, true));

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone9, 0.1745F, -0.8727F, 0.0F);
		body.addChild(bone9);
		bone9.cubeList.add(new ModelBox(bone9, 16, 16, 0.5F, -8.25F, -0.5F, 1, 4, 1, 0.0F, true));

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone10, -0.2618F, 0.0F, -0.3491F);
		body.addChild(bone10);
		bone10.cubeList.add(new ModelBox(bone10, 16, 16, 0.5F, -8.25F, -0.5F, 1, 4, 1, 0.0F, true));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone6, -0.0873F, -0.0873F, 0.3491F);
		body.addChild(bone6);
		bone6.cubeList.add(new ModelBox(bone6, 16, 16, 2.5F, -2.0F, -1.45F, 1, 4, 1, 0.0F, true));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(0.0F, -6.0F, 0.0F);
		setRotationAngle(bone8, -0.0873F, 0.0873F, -0.3491F);
		body.addChild(bone8);
		bone8.cubeList.add(new ModelBox(bone8, 16, 16, -3.5F, -2.25F, 0.8F, 1, 4, 1, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 3.7274F, 1.4989F);
		setRotationAngle(bone3, 0.0873F, 0.0F, 0.0F);
		body.addChild(bone3);
		bone3.cubeList.add(new ModelBox(bone3, 36, 12, -3.999F, -0.0823F, -6.3399F, 8, 4, 6, 0.0F, true));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		root.render(f5);
	}

	private void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		rightleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftleg.rotateAngleX = MathHelper.cos(par1 * 0.6662f + 3.1415927f) * 1.4f * par2;

		super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
	}
}

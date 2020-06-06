package net.tslat.aoa3.client.model.entities.mobs.overworld;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelScrubby extends ModelBase {
	private final ModelRenderer root;
	private final ModelRenderer body;
	private final ModelRenderer rightleg;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightarm;
	private final ModelRenderer leftarm;
	private final ModelRenderer head;

	public ModelScrubby() {
		textureWidth = 64;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -12.0F, -1.0F);
		root.addChild(body);
		setRotation(body, 0.4363F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 16, 16, -4.0F, -0.4226F, -2.9063F, 8, 7, 4, 0.0F, true));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(3.0F, 6.0F, -1.0F);
		body.addChild(rightleg);
		setRotation(rightleg, -0.4363F, 0.0F, 0.0F);
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 16, -2.0F, 0.1395F, -1.6294F, 4, 6, 4, 0.0F, true));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(-3.0F, 6.0F, -1.0F);
		body.addChild(leftleg);
		setRotation(leftleg, -0.4363F, 0.0F, 0.0F);
		leftleg.cubeList.add(new ModelBox(leftleg, 0, 16, -2.0F, 0.1395F, -1.6294F, 4, 6, 4, 0.0F, false));

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(5.0F, 0.5774F, -1.9063F);
		body.addChild(rightarm);
		setRotation(rightarm, -0.4363F, 0.0F, 0.0F);
		rightarm.cubeList.add(new ModelBox(rightarm, 43, 16, -1.0F, -1.5774F, -2.0937F, 4, 8, 4, 0.0F, true));

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(-5.0F, 0.5774F, -1.9063F);
		body.addChild(leftarm);
		setRotation(leftarm, -0.4363F, 0.0F, 0.0F);
		leftarm.cubeList.add(new ModelBox(leftarm, 43, 16, -3.0F, -1.5774F, -2.0937F, 4, 8, 4, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.6711F, -4.4837F);
		body.addChild(head);
		setRotation(head, -0.4363F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.6711F, -3.5163F, 8, 8, 5, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 32, 0, -6.0F, -11.6711F, -2.5163F, 2, 6, 2, 0.0F, true));
		head.cubeList.add(new ModelBox(head, 32, 0, 4.0F, -11.6711F, -2.5163F, 2, 6, 2, 0.0F, true));
	}

	public void render(final Entity entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, entity);
		root.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity entity) {
		head.rotateAngleY = par4 / 57.295776f;
		head.rotateAngleX = -0.43633f + par5 / 54.11268f;
		rightleg.rotateAngleX = -0.43633f + MathHelper.cos(par1 * 0.6662f) * 1.4f * par2;
		leftleg.rotateAngleX = -0.43633f + MathHelper.cos(par1 * 0.6662f + (float)Math.PI) * 1.4f * par2;
	}
}

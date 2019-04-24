package net.tslat.aoa3.client.model.entities.mobs.shyrelands;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelArcWizard extends ModelBase {
	ModelRenderer head;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightleg;
	ModelRenderer leftleg;
	ModelRenderer r1;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer head5;
	ModelRenderer head6;
	ModelRenderer head7;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer body5;
	ModelRenderer rightarm2;
	ModelRenderer rightarm3;
	ModelRenderer rightarm4;

	public ModelArcWizard() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this, 46, 3);
		head.addBox(4.0F, -13.0F, 1.0F, 1, 4, 2);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.setTextureSize(64, 32);
		setRotation(head, 0.0F, 0.0F, 0.0F);
		body = new ModelRenderer(this, 16, 37);
		body.addBox(-0.5F, 6.0F, -2.0F, 1, 4, 4);
		body.setRotationPoint(0.0F, -4.0F, 0.0F);
		body.setTextureSize(64, 32);
		setRotation(body, 0.0F, 0.0F, 0.0F);
		rightarm = new ModelRenderer(this, 38, 42);
		rightarm.addBox(-3.0F, -14.0F, -7.0F, 4, 5, 2);
		rightarm.setRotationPoint(-5.0F, -3.0F, 0.0F);
		rightarm.setTextureSize(64, 32);
		setRotation(rightarm, 0.3490659F, 0.0F, 0.0F);
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(-1.0F, -2.0F, -2.0F, 4, 16, 4);
		leftarm.setRotationPoint(5.0F, -3.0F, 0.0F);
		leftarm.setTextureSize(64, 32);
		setRotation(leftarm, 0.0F, 0.0F, 0.0F);
		rightleg = new ModelRenderer(this, 0, 16);
		rightleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		rightleg.setTextureSize(64, 32);
		setRotation(rightleg, 0.0F, 0.0F, 0.0F);
		leftleg = new ModelRenderer(this, 0, 16);
		leftleg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		leftleg.setRotationPoint(2.0F, 12.0F, 0.0F);
		leftleg.setTextureSize(64, 32);
		setRotation(leftleg, 0.0F, 0.0F, 0.0F);
		r1 = new ModelRenderer(this, 52, 8);
		r1.addBox(-2.0F, -14.0F, -1.0F, 4, 6, 2);
		r1.setRotationPoint(0.0F, 0.0F, 0.0F);
		r1.setTextureSize(64, 32);
		setRotation(r1, 0.0F, 0.0F, 0.0F);
		head2 = new ModelRenderer(this, 46, 3);
		head2.addBox(-5.0F, -13.0F, 1.0F, 1, 4, 2);
		head2.setRotationPoint(0.0F, 0.0F, 0.0F);
		head2.setTextureSize(64, 32);
		setRotation(head2, 0.0F, 0.0F, 0.0F);
		head3 = new ModelRenderer(this, 26, 3);
		head3.addBox(4.0F, -9.0F, -4.0F, 1, 4, 8);
		head3.setRotationPoint(0.0F, 0.0F, 0.0F);
		head3.setTextureSize(64, 32);
		setRotation(head3, 0.0F, 0.0F, 0.0F);
		head4 = new ModelRenderer(this, 26, 3);
		head4.addBox(-5.0F, -9.0F, -4.0F, 1, 4, 8);
		head4.setRotationPoint(0.0F, 0.0F, 0.0F);
		head4.setTextureSize(64, 32);
		setRotation(head4, 0.0F, 0.0F, 0.0F);
		head5 = new ModelRenderer(this, 39, 3);
		head5.addBox(4.0F, -11.0F, -3.0F, 1, 2, 2);
		head5.setRotationPoint(0.0F, 0.0F, 0.0F);
		head5.setTextureSize(64, 32);
		setRotation(head5, 0.0F, 0.0F, 0.0F);
		head6 = new ModelRenderer(this, 39, 3);
		head6.addBox(-5.0F, -11.0F, -3.0F, 1, 2, 2);
		head6.setRotationPoint(0.0F, 0.0F, 0.0F);
		head6.setTextureSize(64, 32);
		setRotation(head6, 0.0F, 0.0F, 0.0F);
		head7 = new ModelRenderer(this, 0, 0);
		head7.addBox(-4.0F, -7.0F, -4.0F, 8, 2, 8);
		head7.setRotationPoint(0.0F, 0.0F, 0.0F);
		head7.setTextureSize(64, 32);
		setRotation(head7, 0.0F, 0.0F, 0.0F);
		body2 = new ModelRenderer(this, 16, 16);
		body2.addBox(-4.0F, 0.0F, -2.0F, 8, 6, 4);
		body2.setRotationPoint(0.0F, -4.0F, 0.0F);
		body2.setTextureSize(64, 32);
		setRotation(body2, 0.0F, 0.0F, 0.0F);
		body3 = new ModelRenderer(this, 16, 26);
		body3.addBox(-4.0F, 10.0F, -2.0F, 8, 6, 4);
		body3.setRotationPoint(0.0F, -4.0F, 0.0F);
		body3.setTextureSize(64, 32);
		setRotation(body3, 0.0F, 0.0F, 0.0F);
		body4 = new ModelRenderer(this, 16, 37);
		body4.addBox(-3.0F, 6.0F, -2.0F, 1, 4, 4);
		body4.setRotationPoint(0.0F, -4.0F, 0.0F);
		body4.setTextureSize(64, 32);
		setRotation(body4, 0.0F, 0.0F, 0.0F);
		body5 = new ModelRenderer(this, 16, 37);
		body5.addBox(2.0F, 6.0F, -2.0F, 1, 4, 4);
		body5.setRotationPoint(0.0F, -4.0F, 0.0F);
		body5.setTextureSize(64, 32);
		setRotation(body5, 0.0F, 0.0F, 0.0F);
		rightarm2 = new ModelRenderer(this, 40, 16);
		rightarm2.addBox(-3.0F, -2.0F, -2.0F, 4, 16, 4);
		rightarm2.setRotationPoint(-5.0F, -3.0F, 0.0F);
		rightarm2.setTextureSize(64, 32);
		setRotation(rightarm2, 0.0F, 0.0F, 0.0F);
		rightarm3 = new ModelRenderer(this, 56, 20);
		rightarm3.addBox(-2.0F, -5.0F, -7.0F, 2, 28, 2);
		rightarm3.setRotationPoint(-5.0F, -3.0F, 0.0F);
		rightarm3.setTextureSize(64, 32);
		setRotation(rightarm3, 0.3490659F, 0.0F, 0.0F);
		rightarm4 = new ModelRenderer(this, 38, 37);
		rightarm4.addBox(-4.0F, -8.0F, -7.0F, 6, 2, 2);
		rightarm4.setRotationPoint(-5.0F, -3.0F, 0.0F);
		rightarm4.setTextureSize(64, 32);
		setRotation(rightarm4, 0.3490659F, 0.0F, 0.0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightleg.render(par7);
		leftleg.render(par7);
		r1.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		head5.render(par7);
		head6.render(par7);
		head7.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		body5.render(par7);
		rightarm2.render(par7);
		rightarm3.render(par7);
		rightarm4.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
		rightleg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 1.4F * par2);
		rightleg.rotateAngleY = 0.0F;

		leftleg.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 1.4F * par2);

		r1.rotateAngleY = (par3 * 0.067F * 1.25F);

		rightarm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F + 0.349F);
		rightarm.rotateAngleZ = 0.0F;

		rightarm2.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F);
		rightarm2.rotateAngleZ = 0.0F;

		rightarm3.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F + 0.349F);
		rightarm3.rotateAngleZ = 0.0F;

		rightarm4.rotateAngleX = (MathHelper.cos(par1 * 0.6662F + 3.1415927F) * 2.0F * par2 * 0.5F + 0.349F);
		rightarm4.rotateAngleZ = 0.0F;

		leftarm.rotateAngleX = (MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F);
		leftarm.rotateAngleZ = 0.0F;
	}
}

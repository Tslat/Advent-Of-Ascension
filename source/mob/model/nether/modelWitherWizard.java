package net.nevermine.mob.model.nether;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class modelWitherWizard extends ModelBase {
	ModelRenderer head1;
	ModelRenderer body;
	ModelRenderer rightarm1;
	ModelRenderer leftarm;
	ModelRenderer body2;
	ModelRenderer head2;
	ModelRenderer head3;
	ModelRenderer head4;
	ModelRenderer rightarm2;
	ModelRenderer rightarm3;
	ModelRenderer rightarm4;
	ModelRenderer rightarm5;
	ModelRenderer rightarm6;

	public modelWitherWizard() {
		textureWidth = 128;
		textureHeight = 32;
		(head1 = new ModelRenderer(this, 102, 21)).addBox(-3.0f, -16.0f, -3.0f, 6, 5, 6);
		head1.setRotationPoint(0.0f, 0.0f, 0.0f);
		head1.setTextureSize(128, 32);
		head1.mirror = true;
		setRotation(head1, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 40, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body.setRotationPoint(0.0f, 12.0f, 0.0f);
		body.setTextureSize(128, 32);
		body.mirror = true;
		setRotation(body, 0.0f, 0.0f, 0.0f);
		(rightarm1 = new ModelRenderer(this, 97, 7)).addBox(0.0f, -15.0f, -8.0f, 2, 2, 2);
		rightarm1.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm1.setTextureSize(128, 32);
		rightarm1.mirror = true;
		setRotation(rightarm1, 0.6108652f, 0.0f, 0.0f);
		(leftarm = new ModelRenderer(this, 24, 16)).addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftarm.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftarm.setTextureSize(128, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 0, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body2.setRotationPoint(0.0f, 0.0f, 0.0f);
		body2.setTextureSize(128, 32);
		body2.mirror = true;
		setRotation(body2, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head2.setRotationPoint(0.0f, 0.0f, 0.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 32, 3)).addBox(-6.0f, -8.0f, -5.0f, 12, 3, 10);
		head3.setRotationPoint(0.0f, 0.0f, 0.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 64, 21)).addBox(-5.0f, -11.0f, -4.0f, 10, 3, 8);
		head4.setRotationPoint(0.0f, 0.0f, 0.0f);
		head4.setTextureSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(rightarm2 = new ModelRenderer(this, 24, 16)).addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightarm2.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm2.setTextureSize(128, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.0f, 0.0f, 0.0f);
		(rightarm3 = new ModelRenderer(this, 82, 0)).addBox(-2.0f, 1.0f, -8.0f, 2, 18, 2);
		rightarm3.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm3.setTextureSize(128, 32);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0.6108652f, 0.0f, 0.0f);
		(rightarm4 = new ModelRenderer(this, 82, 0)).addBox(-2.0f, -11.0f, -8.0f, 2, 12, 2);
		rightarm4.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm4.setTextureSize(128, 32);
		rightarm4.mirror = true;
		setRotation(rightarm4, 0.6108652f, 0.0f, 0.0f);
		(rightarm5 = new ModelRenderer(this, 97, 0)).addBox(-4.0f, -13.0f, -8.0f, 6, 2, 2);
		rightarm5.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm5.setTextureSize(128, 32);
		rightarm5.mirror = true;
		setRotation(rightarm5, 0.6108652f, 0.0f, 0.0f);
		(rightarm6 = new ModelRenderer(this, 97, 7)).addBox(-4.0f, -15.0f, -8.0f, 2, 2, 2);
		rightarm6.setRotationPoint(-5.0f, 2.0f, 0.0f);
		rightarm6.setTextureSize(128, 32);
		rightarm6.mirror = true;
		setRotation(rightarm6, 0.6108652f, 0.0f, 0.0f);
	}

	public void render(final Entity par1Entity, final float par2, final float par3, final float par4, final float par5, final float par6, final float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head1.render(par7);
		body.render(par7);
		rightarm1.render(par7);
		leftarm.render(par7);
		body2.render(par7);
		head2.render(par7);
		head3.render(par7);
		head4.render(par7);
		rightarm2.render(par7);
		rightarm3.render(par7);
		rightarm4.render(par7);
		rightarm5.render(par7);
		rightarm6.render(par7);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(final float par1, final float par2, final float par3, final float par4, final float par5, final float par6, final Entity par7Entity) {
		head1.rotateAngleY = par4 / 57.295776f;
		head1.rotateAngleX = par5 / 54.11268f;
		head2.rotateAngleY = par4 / 57.295776f;
		head2.rotateAngleX = par5 / 54.11268f;
		head3.rotateAngleY = par4 / 57.295776f;
		head3.rotateAngleX = par5 / 54.11268f;
		head4.rotateAngleY = par4 / 57.295776f;
		head4.rotateAngleX = par5 / 54.11268f;
		leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662f) * 2.0f * par2 * 0.5f;
		leftarm.rotateAngleZ = 0.0f;
	}
}

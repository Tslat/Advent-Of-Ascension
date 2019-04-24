package net.tslat.aoa3.client.model.entities.boss.baroness;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBaroness extends ModelBase {
	ModelRenderer head4;
	ModelRenderer body;
	ModelRenderer rightarm;
	ModelRenderer leftarm;
	ModelRenderer rightarm2;
	ModelRenderer leftarm2;
	ModelRenderer body2;
	ModelRenderer body3;
	ModelRenderer body4;
	ModelRenderer head6;
	ModelRenderer head5;
	ModelRenderer rightarm3;
	ModelRenderer rightarm4;
	ModelRenderer rightarm5;
	ModelRenderer rightarm6;
	ModelRenderer head2;
	ModelRenderer r2;
	ModelRenderer head3;
	ModelRenderer r1;
	ModelRenderer leftarm3;
	ModelRenderer rightarm7;
	ModelRenderer leftarm4;
	ModelRenderer rightarm8;
	ModelRenderer leftarm5;
	ModelRenderer rightarm9;

	public ModelBaroness() {
		textureWidth = 128;
		textureHeight = 64;

		head4 = new ModelRenderer(this, 104, 3);
		head4.addBox(-3F, -18F, -3F, 6, 5, 6);
		head4.setRotationPoint(0F, -2F, 0F);
		head4.setTextureSize(64, 32);
		head4.mirror = true;
		setRotation(head4, 0F, 0F, 0F);
		body = new ModelRenderer(this, 7, 41);
		body.addBox(-5F, 15F, -3F, 10, 5, 6);
		body.setRotationPoint(0F, -2F, 0F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		setRotation(body, 0F, 0F, 0F);
		rightarm = new ModelRenderer(this, 40, 49);
		rightarm.addBox(-7F, -8F, 1F, 3, 2, 1);
		rightarm.setRotationPoint(-6F, 0F, 0F);
		rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		setRotation(rightarm, 0F, 0F, 0F);
		leftarm = new ModelRenderer(this, 40, 49);
		leftarm.addBox(4F, -8F, 1F, 3, 2, 1);
		leftarm.setRotationPoint(6F, 0F, 0F);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
		setRotation(leftarm, 0F, 0F, 0F);
		rightarm2 = new ModelRenderer(this, 79, 25);
		rightarm2.addBox(-2F, -9F, -5F, 2, 3, 2);
		rightarm2.setRotationPoint(-6F, 0F, 0F);
		rightarm2.setTextureSize(64, 32);
		rightarm2.mirror = true;
		setRotation(rightarm2, 0.4363323F, 0F, 0F);
		leftarm2 = new ModelRenderer(this, 40, 16);
		leftarm2.addBox(-1F, 0F, -2F, 4, 10, 4);
		leftarm2.setRotationPoint(6F, 0F, 0F);
		leftarm2.setTextureSize(64, 32);
		leftarm2.mirror = true;
		setRotation(leftarm2, 0F, 0F, 0F);
		body2 = new ModelRenderer(this, 16, 16);
		body2.addBox(-4F, 0F, -2F, 8, 10, 4);
		body2.setRotationPoint(0F, -2F, 0F);
		body2.setTextureSize(64, 32);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		body3 = new ModelRenderer(this, 3, 52);
		body3.addBox(-5.5F, 20F, -3.5F, 11, 5, 7);
		body3.setRotationPoint(0F, -2F, 0F);
		body3.setTextureSize(64, 32);
		body3.mirror = true;
		setRotation(body3, 0F, 0F, 0F);
		body4 = new ModelRenderer(this, 11, 31);
		body4.addBox(-4.5F, 10F, -2.5F, 9, 5, 5);
		body4.setRotationPoint(0F, -2F, 0F);
		body4.setTextureSize(64, 32);
		body4.mirror = true;
		setRotation(body4, 0F, 0F, 0F);
		head6 = new ModelRenderer(this, 32, 2);
		head6.addBox(-5F, -8F, -5F, 10, 2, 10);
		head6.setRotationPoint(0F, -2F, 0F);
		head6.setTextureSize(64, 32);
		head6.mirror = true;
		setRotation(head6, 0F, 0F, 0F);
		head5 = new ModelRenderer(this, 72, 1);
		head5.addBox(-4F, -13F, -4F, 8, 5, 8);
		head5.setRotationPoint(0F, -2F, 0F);
		head5.setTextureSize(64, 32);
		head5.mirror = true;
		setRotation(head5, 0F, 0F, 0F);
		rightarm3 = new ModelRenderer(this, 40, 16);
		rightarm3.addBox(-3F, 0F, -2F, 4, 10, 4);
		rightarm3.setRotationPoint(-6F, 0F, 0F);
		rightarm3.setTextureSize(64, 32);
		rightarm3.mirror = true;
		setRotation(rightarm3, 0F, 0F, 0F);
		rightarm4 = new ModelRenderer(this, 69, 16);
		rightarm4.addBox(-2F, -4F, -7F, 2, 26, 2);
		rightarm4.setRotationPoint(-6F, 0F, 0F);
		rightarm4.setTextureSize(64, 32);
		rightarm4.mirror = true;
		setRotation(rightarm4, 0.4363323F, 0F, 0F);
		rightarm5 = new ModelRenderer(this, 79, 16);
		rightarm5.addBox(-2F, -6F, -9F, 2, 2, 6);
		rightarm5.setRotationPoint(-6F, 0F, 0F);
		rightarm5.setTextureSize(64, 32);
		rightarm5.mirror = true;
		setRotation(rightarm5, 0.4363323F, 0F, 0F);
		rightarm6 = new ModelRenderer(this, 79, 16);
		rightarm6.addBox(-2F, -11F, -9F, 2, 2, 6);
		rightarm6.setRotationPoint(-6F, 0F, 0F);
		rightarm6.setTextureSize(64, 32);
		rightarm6.mirror = true;
		setRotation(rightarm6, 0.4363323F, 0F, 0F);
		head2 = new ModelRenderer(this, 40, 40);
		head2.addBox(-4F, 0F, -4F, 8, 7, 0);
		head2.setRotationPoint(0F, -2F, 0F);
		head2.setTextureSize(64, 32);
		head2.mirror = true;
		setRotation(head2, 0F, 0F, 0F);
		r2 = new ModelRenderer(this, 97, 16);
		r2.addBox(-3F, -3F, 10F, 6, 6, 6);
		r2.setRotationPoint(0F, -21F, 0F);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0F, 0F, 0F);
		head3 = new ModelRenderer(this, 0, 0);
		head3.addBox(-4F, -6F, -4F, 8, 6, 8);
		head3.setRotationPoint(0F, -2F, 0F);
		head3.setTextureSize(64, 32);
		head3.mirror = true;
		setRotation(head3, 0F, 0F, 0F);
		r1 = new ModelRenderer(this, 97, 16);
		r1.addBox(-3F, -3F, -16F, 6, 6, 6);
		r1.setRotationPoint(0F, -21F, 0F);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0F, 0F, 0F);
		leftarm3 = new ModelRenderer(this, 40, 30);
		leftarm3.addBox(-2F, -3F, -4F, 6, 3, 6);
		leftarm3.setRotationPoint(6F, 0F, 0F);
		leftarm3.setTextureSize(64, 32);
		leftarm3.mirror = true;
		setRotation(leftarm3, 0F, 0F, 0F);
		rightarm7 = new ModelRenderer(this, 40, 30);
		rightarm7.addBox(-4F, -3F, -4F, 6, 3, 6);
		rightarm7.setRotationPoint(-6F, 0F, 0F);
		rightarm7.setTextureSize(64, 32);
		rightarm7.mirror = true;
		setRotation(rightarm7, 0F, 0F, 0F);
		leftarm4 = new ModelRenderer(this, 47, 54);
		leftarm4.addBox(-1F, -6F, 1F, 2, 3, 1);
		leftarm4.setRotationPoint(6F, 0F, 0F);
		leftarm4.setTextureSize(64, 32);
		leftarm4.mirror = true;
		setRotation(leftarm4, 0F, 0F, 0F);
		rightarm8 = new ModelRenderer(this, 47, 54);
		rightarm8.addBox(-1F, -6F, 1F, 2, 3, 1);
		rightarm8.setRotationPoint(-6F, 0F, 0F);
		rightarm8.setTextureSize(64, 32);
		rightarm8.mirror = true;
		setRotation(rightarm8, 0F, 0F, 0F);
		leftarm5 = new ModelRenderer(this, 40, 54);
		leftarm5.addBox(2F, -8F, 1F, 2, 5, 1);
		leftarm5.setRotationPoint(6F, 0F, 0F);
		leftarm5.setTextureSize(64, 32);
		leftarm5.mirror = true;
		setRotation(leftarm5, 0F, 0F, 0F);
		rightarm9 = new ModelRenderer(this, 40, 54);
		rightarm9.addBox(-4F, -8F, 1F, 2, 5, 1);
		rightarm9.setRotationPoint(-6F, 0F, 0F);
		rightarm9.setTextureSize(64, 32);
		rightarm9.mirror = true;
		setRotation(rightarm9, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head4.render(par7);
		body.render(par7);
		rightarm.render(par7);
		leftarm.render(par7);
		rightarm2.render(par7);
		leftarm2.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		head6.render(par7);
		head5.render(par7);
		rightarm3.render(par7);
		rightarm4.render(par7);
		rightarm5.render(par7);
		rightarm6.render(par7);
		head2.render(par7);
		r2.render(par7);
		head3.render(par7);
		r1.render(par7);
		leftarm3.render(par7);
		rightarm7.render(par7);
		leftarm4.render(par7);
		rightarm8.render(par7);
		leftarm5.render(par7);
		rightarm9.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {

		this.r1.rotateAngleY = (par3 * 0.067F) * 1.25F;
		this.r2.rotateAngleY = (par3 * 0.067F) * 1.25F;

		//Head Movement//
		this.head2.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head2.rotateAngleX = par5 / (170F / (float)Math.PI);

		this.head3.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head3.rotateAngleX = par5 / (170F / (float)Math.PI);

		this.head4.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head4.rotateAngleX = par5 / (170F / (float)Math.PI);

		this.head5.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head5.rotateAngleX = par5 / (170F / (float)Math.PI);

		this.head6.rotateAngleY = par4 / (180F / (float)Math.PI);
		this.head6.rotateAngleX = par5 / (170F / (float)Math.PI);

		//Right Arm Movement//
		this.rightarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.rightarm.rotateAngleZ = 0.0F;

		this.rightarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F + 0.436F;
		this.rightarm2.rotateAngleZ = 0.0F;

		this.rightarm3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.rightarm3.rotateAngleZ = 0.0F;

		this.rightarm4.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F + 0.436F;
		this.rightarm4.rotateAngleZ = 0.0F;

		this.rightarm5.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F + 0.436F;
		this.rightarm5.rotateAngleZ = 0.0F;

		this.rightarm6.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F + 0.436F;
		this.rightarm6.rotateAngleZ = 0.0F;

		this.rightarm7.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.rightarm7.rotateAngleZ = 0.0F;

		this.rightarm8.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.rightarm8.rotateAngleZ = 0.0F;

		this.rightarm9.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.rightarm9.rotateAngleZ = 0.0F;

		//Left Arm Movement//
		this.leftarm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.leftarm.rotateAngleZ = 0.0F;

		this.leftarm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.leftarm2.rotateAngleZ = 0.0F;

	}

}

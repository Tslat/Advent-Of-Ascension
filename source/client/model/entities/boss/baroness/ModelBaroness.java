package net.tslat.aoa3.client.model.entities.boss.baroness;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBaroness extends ModelBase {
	private ModelRenderer head4;
	private ModelRenderer body;
	private ModelRenderer rightArm;
	private ModelRenderer leftArm;
	private ModelRenderer rightArm2;
	private ModelRenderer leftArm2;
	private ModelRenderer body2;
	private ModelRenderer body3;
	private ModelRenderer body4;
	private ModelRenderer head6;
	private ModelRenderer head5;
	private ModelRenderer rightArm3;
	private ModelRenderer rightArm4;
	private ModelRenderer rightArm5;
	private ModelRenderer rightArm6;
	private ModelRenderer head2;
	private ModelRenderer r2;
	private ModelRenderer head3;
	private ModelRenderer r1;
	private ModelRenderer leftArm3;
	private ModelRenderer rightArm7;
	private ModelRenderer leftArm4;
	private ModelRenderer rightArm8;
	private ModelRenderer leftArm5;
	private ModelRenderer rightArm9;

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
		rightArm = new ModelRenderer(this, 40, 49);
		rightArm.addBox(-7F, -8F, 1F, 3, 2, 1);
		rightArm.setRotationPoint(-6F, 0F, 0F);
		rightArm.setTextureSize(64, 32);
		rightArm.mirror = true;
		setRotation(rightArm, 0F, 0F, 0F);
		leftArm = new ModelRenderer(this, 40, 49);
		leftArm.addBox(4F, -8F, 1F, 3, 2, 1);
		leftArm.setRotationPoint(6F, 0F, 0F);
		leftArm.setTextureSize(64, 32);
		leftArm.mirror = true;
		setRotation(leftArm, 0F, 0F, 0F);
		rightArm2 = new ModelRenderer(this, 79, 25);
		rightArm2.addBox(-2F, -9F, -5F, 2, 3, 2);
		rightArm2.setRotationPoint(-6F, 0F, 0F);
		rightArm2.setTextureSize(64, 32);
		rightArm2.mirror = true;
		setRotation(rightArm2, 0.4363323F, 0F, 0F);
		leftArm2 = new ModelRenderer(this, 40, 16);
		leftArm2.addBox(-1F, 0F, -2F, 4, 10, 4);
		leftArm2.setRotationPoint(6F, 0F, 0F);
		leftArm2.setTextureSize(64, 32);
		leftArm2.mirror = true;
		setRotation(leftArm2, 0F, 0F, 0F);
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
		rightArm3 = new ModelRenderer(this, 40, 16);
		rightArm3.addBox(-3F, 0F, -2F, 4, 10, 4);
		rightArm3.setRotationPoint(-6F, 0F, 0F);
		rightArm3.setTextureSize(64, 32);
		rightArm3.mirror = true;
		setRotation(rightArm3, 0F, 0F, 0F);
		rightArm4 = new ModelRenderer(this, 69, 16);
		rightArm4.addBox(-2F, -4F, -7F, 2, 26, 2);
		rightArm4.setRotationPoint(-6F, 0F, 0F);
		rightArm4.setTextureSize(64, 32);
		rightArm4.mirror = true;
		setRotation(rightArm4, 0.4363323F, 0F, 0F);
		rightArm5 = new ModelRenderer(this, 79, 16);
		rightArm5.addBox(-2F, -6F, -9F, 2, 2, 6);
		rightArm5.setRotationPoint(-6F, 0F, 0F);
		rightArm5.setTextureSize(64, 32);
		rightArm5.mirror = true;
		setRotation(rightArm5, 0.4363323F, 0F, 0F);
		rightArm6 = new ModelRenderer(this, 79, 16);
		rightArm6.addBox(-2F, -11F, -9F, 2, 2, 6);
		rightArm6.setRotationPoint(-6F, 0F, 0F);
		rightArm6.setTextureSize(64, 32);
		rightArm6.mirror = true;
		setRotation(rightArm6, 0.4363323F, 0F, 0F);
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
		leftArm3 = new ModelRenderer(this, 40, 30);
		leftArm3.addBox(-2F, -3F, -4F, 6, 3, 6);
		leftArm3.setRotationPoint(6F, 0F, 0F);
		leftArm3.setTextureSize(64, 32);
		leftArm3.mirror = true;
		setRotation(leftArm3, 0F, 0F, 0F);
		rightArm7 = new ModelRenderer(this, 40, 30);
		rightArm7.addBox(-4F, -3F, -4F, 6, 3, 6);
		rightArm7.setRotationPoint(-6F, 0F, 0F);
		rightArm7.setTextureSize(64, 32);
		rightArm7.mirror = true;
		setRotation(rightArm7, 0F, 0F, 0F);
		leftArm4 = new ModelRenderer(this, 47, 54);
		leftArm4.addBox(-1F, -6F, 1F, 2, 3, 1);
		leftArm4.setRotationPoint(6F, 0F, 0F);
		leftArm4.setTextureSize(64, 32);
		leftArm4.mirror = true;
		setRotation(leftArm4, 0F, 0F, 0F);
		rightArm8 = new ModelRenderer(this, 47, 54);
		rightArm8.addBox(-1F, -6F, 1F, 2, 3, 1);
		rightArm8.setRotationPoint(-6F, 0F, 0F);
		rightArm8.setTextureSize(64, 32);
		rightArm8.mirror = true;
		setRotation(rightArm8, 0F, 0F, 0F);
		leftArm5 = new ModelRenderer(this, 40, 54);
		leftArm5.addBox(2F, -8F, 1F, 2, 5, 1);
		leftArm5.setRotationPoint(6F, 0F, 0F);
		leftArm5.setTextureSize(64, 32);
		leftArm5.mirror = true;
		setRotation(leftArm5, 0F, 0F, 0F);
		rightArm9 = new ModelRenderer(this, 40, 54);
		rightArm9.addBox(-4F, -8F, 1F, 2, 5, 1);
		rightArm9.setRotationPoint(-6F, 0F, 0F);
		rightArm9.setTextureSize(64, 32);
		rightArm9.mirror = true;
		setRotation(rightArm9, 0F, 0F, 0F);
	}

	public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
		setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);
		head4.render(par7);
		body.render(par7);
		rightArm.render(par7);
		leftArm.render(par7);
		rightArm2.render(par7);
		leftArm2.render(par7);
		body2.render(par7);
		body3.render(par7);
		body4.render(par7);
		head6.render(par7);
		head5.render(par7);
		rightArm3.render(par7);
		rightArm4.render(par7);
		rightArm5.render(par7);
		rightArm6.render(par7);
		head2.render(par7);
		r2.render(par7);
		head3.render(par7);
		r1.render(par7);
		leftArm3.render(par7);
		rightArm7.render(par7);
		leftArm4.render(par7);
		rightArm8.render(par7);
		leftArm5.render(par7);
		rightArm9.render(par7);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {

		this.r1.rotateAngleY = (par3 * 0.067F) * 1.25F;
		this.r2.rotateAngleY = (par3 * 0.067F) * 1.25F;

		//head Movement//
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
		this.rightArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.rightArm.rotateAngleZ = 0.0F;

		this.rightArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F + 0.436F;
		this.rightArm2.rotateAngleZ = 0.0F;

		this.rightArm3.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.rightArm3.rotateAngleZ = 0.0F;

		this.rightArm4.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F + 0.436F;
		this.rightArm4.rotateAngleZ = 0.0F;

		this.rightArm5.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F + 0.436F;
		this.rightArm5.rotateAngleZ = 0.0F;

		this.rightArm6.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F + 0.436F;
		this.rightArm6.rotateAngleZ = 0.0F;

		this.rightArm7.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.rightArm7.rotateAngleZ = 0.0F;

		this.rightArm8.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.rightArm8.rotateAngleZ = 0.0F;

		this.rightArm9.rotateAngleX = MathHelper.cos(par1 * 0.6662F + (float)Math.PI) * 2.0F * par2 * 0.5F;
		this.rightArm9.rotateAngleZ = 0.0F;

		//Left Arm Movement//
		this.leftArm.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.leftArm.rotateAngleZ = 0.0F;

		this.leftArm2.rotateAngleX = MathHelper.cos(par1 * 0.6662F) * 2.0F * par2 * 0.5F;
		this.leftArm2.rotateAngleZ = 0.0F;

	}

}

package net.tslat.aoa3.client.model.entity.mob.shyrelands;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class SyskerModel extends EntityModel<MobEntity> {
	private final ModelRenderer r1;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer head;
	private final ModelRenderer r2;
	private final ModelRenderer r3;
	private final ModelRenderer r4;
	private final ModelRenderer r5;
	private final ModelRenderer r6;
	private final ModelRenderer r7;
	private final ModelRenderer r8;
	private final ModelRenderer r9;
	private final ModelRenderer r10;

	public SyskerModel() {
		textureWidth = 64;
		textureHeight = 64;

		r1 = new ModelRenderer(this, 43, 0);
		r1.addBox(6.0F, -3.0F, -2.0F, 4, 4, 4);
		r1.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r1.setTextureSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0F, 0.0F, 0.0F);
		rightLeg = new ModelRenderer(this, 0, 16);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		rightLeg.setRotationPoint(-3.0F, 12.0F, 0.0F);
		rightLeg.setTextureSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new ModelRenderer(this, 0, 16);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		leftLeg.setRotationPoint(3.0F, 12.0F, 0.0F);
		leftLeg.setTextureSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
		head.setRotationPoint(0.0F, 9.466666F, 0.0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		r2 = new ModelRenderer(this, 33, 11);
		r2.addBox(9.0F, -7.0F, -1.0F, 2, 1, 2);
		r2.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r2.setTextureSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0F, 0.0F, 0.0F);
		r3 = new ModelRenderer(this, 43, 0);
		r3.addBox(-10.0F, -3.0F, -2.0F, 4, 4, 4);
		r3.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r3.setTextureSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0F, 0.0F, 0.0F);
		r4 = new ModelRenderer(this, 33, 11);
		r4.addBox(-11.0F, -7.0F, -1.0F, 2, 1, 2);
		r4.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r4.setTextureSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0F, 0.0F, 0.0F);
		r5 = new ModelRenderer(this, 33, 0);
		r5.addBox(7.0F, -11.0F, -1.0F, 2, 8, 2);
		r5.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r5.setTextureSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0.0F, 0.0F, 0.0F);
		r6 = new ModelRenderer(this, 33, 0);
		r6.addBox(-9.0F, -11.0F, -1.0F, 2, 8, 2);
		r6.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r6.setTextureSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0.0F, 0.0F, 0.0F);
		r7 = new ModelRenderer(this, 33, 11);
		r7.addBox(-11.0F, -11.0F, -1.0F, 2, 1, 2);
		r7.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r7.setTextureSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0.0F, 0.0F, 0.0F);
		r8 = new ModelRenderer(this, 33, 11);
		r8.addBox(9.0F, -11.0F, -1.0F, 2, 1, 2);
		r8.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r8.setTextureSize(64, 32);
		r8.mirror = true;
		setRotation(r8, 0.0F, 0.0F, 0.0F);
		r9 = new ModelRenderer(this, 33, 11);
		r9.addBox(-11.0F, -9.0F, -1.0F, 2, 1, 2);
		r9.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r9.setTextureSize(64, 32);
		r9.mirror = true;
		setRotation(r9, 0.0F, 0.0F, 0.0F);
		r10 = new ModelRenderer(this, 33, 11);
		r10.addBox(9.0F, -9.0F, -1.0F, 2, 1, 2);
		r10.setRotationPoint(0.0F, 9.466666F, 0.0F);
		r10.setTextureSize(64, 32);
		r10.mirror = true;
		setRotation(r10, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		r1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		r10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.rotateAngleY = (ageInTicks * 0.067F * 4.25F);
		r2.rotateAngleY = (ageInTicks * 0.067F * 4.25F);
		r3.rotateAngleY = (ageInTicks * 0.067F * 4.25F);
		r4.rotateAngleY = (ageInTicks * 0.067F * 4.25F);
		r5.rotateAngleY = (ageInTicks * 0.067F * 4.25F);
		r6.rotateAngleY = (ageInTicks * 0.067F * 4.25F);
		r7.rotateAngleY = (ageInTicks * 0.067F * 4.25F);
		r8.rotateAngleY = (ageInTicks * 0.067F * 4.25F);
		r9.rotateAngleY = (ageInTicks * 0.067F * 4.25F);
		r10.rotateAngleY = (ageInTicks * 0.067F * 4.25F);

		rightLeg.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		rightLeg.rotateAngleY = 0.0F;

		leftLeg.rotateAngleX = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);

		head.rotateAngleY = (netHeadYaw / 57.295776F);
		head.rotateAngleX = (headPitch / 54.11268F);
	}
}

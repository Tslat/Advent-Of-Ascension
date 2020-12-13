package net.tslat.aoa3.client.model.entity.mob.creeponia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class CreepirdModel extends EntityModel<MobEntity> {
	private final ModelRenderer wingR1;
	private final ModelRenderer head;
	private final ModelRenderer wingR2;
	private final ModelRenderer shape1;
	private final ModelRenderer wingL1;
	private final ModelRenderer wingL2;
	private final ModelRenderer shape2;

	public CreepirdModel() {
		textureWidth = 64;
		textureHeight = 32;
		(wingR1 = new ModelRenderer(this, 30, 20)).addBox(-10.0f, 0.0f, -4.0f, 8, 0, 8);
		wingR1.setRotationPoint(-6.0f, 17.0f, 0.0f);
		wingR1.setTextureSize(64, 32);
		wingR1.mirror = true;
		setRotation(wingR1, 0.0f, 0.0f, 0.0f);
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -4.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 17.0f, 0.0f);
		head.setTextureSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(wingR2 = new ModelRenderer(this, 33, 0)).addBox(-2.0f, -1.0f, -4.0f, 2, 2, 8);
		wingR2.setRotationPoint(-6.0f, 17.0f, 0.0f);
		wingR2.setTextureSize(64, 32);
		wingR2.mirror = true;
		setRotation(wingR2, 0.0f, 0.0f, 0.0f);
		(shape1 = new ModelRenderer(this, 30, 2)).addBox(-10.0f, -0.5f, -1.0f, 2, 1, 2);
		shape1.setRotationPoint(4.0f, 17.0f, 0.0f);
		shape1.setTextureSize(64, 32);
		shape1.mirror = true;
		setRotation(shape1, 0.0f, 0.0f, 0.0f);
		(wingL1 = new ModelRenderer(this, 30, 11)).addBox(2.0f, 0.0f, -4.0f, 8, 0, 8);
		wingL1.setRotationPoint(6.0f, 17.0f, 0.0f);
		wingL1.setTextureSize(64, 32);
		wingL1.mirror = true;
		setRotation(wingL1, 0.0f, 0.0f, 0.0f);
		(wingL2 = new ModelRenderer(this, 33, 0)).addBox(0.0f, -1.0f, -4.0f, 2, 2, 8);
		wingL2.setRotationPoint(6.0f, 17.0f, 0.0f);
		wingL2.setTextureSize(64, 32);
		wingL2.mirror = true;
		setRotation(wingL2, 0.0f, 0.0f, 0.0f);
		(shape2 = new ModelRenderer(this, 30, 2)).addBox(0.0f, -0.5f, -1.0f, 2, 1, 2);
		shape2.setRotationPoint(4.0f, 17.0f, 0.0f);
		shape2.setTextureSize(64, 32);
		shape2.mirror = true;
		setRotation(shape2, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		wingR1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingR2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		wingL2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		shape2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		wingR1.rotateAngleZ = MathHelper.cos(ageInTicks * 0.1f) * 3.1415927f * 0.15f;
		wingL1.rotateAngleZ = -wingR1.rotateAngleZ;
		wingR2.rotateAngleZ = MathHelper.cos(ageInTicks * 0.1f) * 3.1415927f * 0.15f;
		wingL2.rotateAngleZ = -wingR1.rotateAngleZ;
	}
}

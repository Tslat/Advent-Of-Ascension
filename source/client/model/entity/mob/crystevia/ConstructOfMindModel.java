package net.tslat.aoa3.client.model.entity.mob.crystevia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class ConstructOfMindModel extends EntityModel<MobEntity> {
	private final ModelRenderer or1;
	private final ModelRenderer ir1;
	private final ModelRenderer or2;
	private final ModelRenderer ir2;
	private final ModelRenderer ir3;
	private final ModelRenderer or3;
	private final ModelRenderer ir4;
	private final ModelRenderer or4;
	private final ModelRenderer or5;
	private final ModelRenderer or6;

	public ConstructOfMindModel() {
		super(RenderType::getEntityTranslucent);
		textureWidth = 64;
		textureHeight = 64;
		(or1 = new ModelRenderer(this, 28, 0)).addBox(-15.0f, -3.0f, -3.0f, 2, 6, 6);
		or1.setRotationPoint(0.0f, 4.0f, 0.0f);
		or1.setTextureSize(64, 64);
		or1.mirror = true;
		setRotation(or1, 0.0f, 0.0f, 0.0f);
		(ir1 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, 4.0f, -3.0f, 6, 6, 6);
		ir1.setRotationPoint(0.0f, 4.0f, 0.0f);
		ir1.setTextureSize(64, 64);
		ir1.mirror = true;
		setRotation(ir1, 0.0f, 0.0f, 0.0f);
		(or2 = new ModelRenderer(this, 0, 15)).addBox(-4.0f, 12.0f, -3.0f, 8, 8, 6);
		or2.setRotationPoint(0.0f, 4.0f, 0.0f);
		or2.setTextureSize(64, 64);
		or2.mirror = true;
		setRotation(or2, 0.0f, 0.0f, 0.0f);
		(ir2 = new ModelRenderer(this, 0, 0)).addBox(-11.0f, -3.0f, -3.0f, 6, 6, 6);
		ir2.setRotationPoint(0.0f, 4.0f, 0.0f);
		ir2.setTextureSize(64, 64);
		ir2.mirror = true;
		setRotation(ir2, 0.0f, 0.0f, 0.0f);
		(ir3 = new ModelRenderer(this, 0, 0)).addBox(-3.0f, -11.0f, -3.0f, 6, 6, 6);
		ir3.setRotationPoint(0.0f, 4.0f, 0.0f);
		ir3.setTextureSize(64, 64);
		ir3.mirror = true;
		setRotation(ir3, 0.0f, 0.0f, 0.0f);
		(or3 = new ModelRenderer(this, 0, 15)).addBox(-4.0f, -21.0f, -3.0f, 8, 8, 6);
		or3.setRotationPoint(0.0f, 4.0f, 0.0f);
		or3.setTextureSize(64, 64);
		or3.mirror = true;
		setRotation(or3, 0.0f, 0.0f, 0.0f);
		(ir4 = new ModelRenderer(this, 0, 0)).addBox(5.0f, -3.0f, -3.0f, 6, 6, 6);
		ir4.setRotationPoint(0.0f, 4.0f, 0.0f);
		ir4.setTextureSize(64, 64);
		ir4.mirror = true;
		setRotation(ir4, 0.0f, 0.0f, 0.0f);
		(or4 = new ModelRenderer(this, 28, 0)).addBox(-19.0f, -3.0f, -3.0f, 2, 6, 6);
		or4.setRotationPoint(0.0f, 4.0f, 0.0f);
		or4.setTextureSize(64, 64);
		or4.mirror = true;
		setRotation(or4, 0.0f, 0.0f, 0.0f);
		(or5 = new ModelRenderer(this, 28, 0)).addBox(17.0f, -3.0f, -3.0f, 2, 6, 6);
		or5.setRotationPoint(0.0f, 4.0f, 0.0f);
		or5.setTextureSize(64, 64);
		or5.mirror = true;
		setRotation(or5, 0.0f, 0.0f, 0.0f);
		(or6 = new ModelRenderer(this, 28, 0)).addBox(13.0f, -3.0f, -3.0f, 2, 6, 6);
		or6.setRotationPoint(0.0f, 4.0f, 0.0f);
		or6.setTextureSize(64, 64);
		or6.mirror = true;
		setRotation(or6, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		or1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		ir1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		or2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		ir2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		ir3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		or3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		ir4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		or4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		or5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		or6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		ir1.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		ir2.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		ir3.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		ir4.rotateAngleZ = ageInTicks * 0.067f * 1.25f;
		or1.rotateAngleX = ageInTicks * 0.067f * 2.25f;
		or2.rotateAngleX = ageInTicks * 0.067f * 2.25f;
		or3.rotateAngleX = ageInTicks * 0.067f * 2.25f;
		or4.rotateAngleX = ageInTicks * 0.067f * 2.25f;
		or5.rotateAngleX = ageInTicks * 0.067f * 2.25f;
		or6.rotateAngleX = ageInTicks * 0.067f * 2.25f;
	}
}

package net.tslat.aoa3.client.model.entity.mob.mysterium;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class MushroomSpiderModel extends EntityModel<MobEntity> {
	private final ModelRenderer Body;
	private final ModelRenderer Head;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg2;
	private final ModelRenderer Leg3;
	private final ModelRenderer Leg4;
	private final ModelRenderer Leg5;
	private final ModelRenderer Leg6;
	private final ModelRenderer Leg7;
	private final ModelRenderer Leg8;
	private final ModelRenderer RearEnd;

	public MushroomSpiderModel() {
		textureWidth = 64;
		textureHeight = 64;

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 20.0F, 0.0F);
		Body.setTextureOffset(42, 20).addBox(-2.0F, -2.0F, 3.0F, 4, 4, 7, 0.0F, true);
		Body.setTextureOffset(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F, true);

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, -3.0F);
		Body.addChild(Head);
		Head.setTextureOffset(32, 4).addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F, true);
		Head.setTextureOffset(18, 14).addBox(1.0F, -8.0F, -8.0F, 3, 2, 3, 0.0F, true);
		Head.setTextureOffset(26, 6).addBox(2.0F, -7.0F, -7.0F, 1, 4, 1, 0.0F, true);
		Head.setTextureOffset(26, 6).addBox(-3.0F, -7.0F, -7.0F, 1, 4, 1, 0.0F, true);
		Head.setTextureOffset(18, 14).addBox(-4.0F, -8.0F, -8.0F, 3, 2, 3, 0.0F, true);

		Leg1 = new ModelRenderer(this);
		Leg1.setRotationPoint(2.0F, 0.0F, 0.25F);
		Body.addChild(Leg1);
		setRotation(Leg1, 0.0F, -0.576F, -0.2443F);
		Leg1.setTextureOffset(18, 0).addBox(0.5655F, -1.5413F, -0.5794F, 16, 2, 2, 0.0F, true);

		Leg2 = new ModelRenderer(this);
		Leg2.setRotationPoint(-2.0F, 0.0F, 0.5F);
		Body.addChild(Leg2);
		setRotation(Leg2, 0.0F, 0.576F, 0.2443F);
		Leg2.setTextureOffset(18, 0).addBox(-16.4293F, -1.5413F, -0.7891F, 16, 2, 2, 0.0F, false);

		Leg3 = new ModelRenderer(this);
		Leg3.setRotationPoint(2.0F, 0.0F, 0.0F);
		Body.addChild(Leg3);
		setRotation(Leg3, 0.0F, -0.2793F, -0.2443F);
		Leg3.setTextureOffset(18, 0).addBox(0.1327F, -1.5226F, -0.5712F, 16, 2, 2, 0.0F, true);

		Leg4 = new ModelRenderer(this);
		Leg4.setRotationPoint(-2.0F, 0.0F, 0.0F);
		Body.addChild(Leg4);
		setRotation(Leg4, 0.0F, 0.2793F, 0.2443F);
		Leg4.setTextureOffset(18, 0).addBox(-16.1327F, -1.5226F, -0.5712F, 16, 2, 2, 0.0F, false);

		Leg5 = new ModelRenderer(this);
		Leg5.setRotationPoint(2.0F, 0.0F, 0.0F);
		Body.addChild(Leg5);
		setRotation(Leg5, 0.0F, 0.2793F, -0.2443F);
		Leg5.setTextureOffset(18, 0).addBox(-0.143F, -1.5276F, -0.4675F, 16, 2, 2, 0.0F, true);

		Leg6 = new ModelRenderer(this);
		Leg6.setRotationPoint(-2.0F, 0.0F, 0.0F);
		Body.addChild(Leg6);
		setRotation(Leg6, 0.0F, -0.2793F, 0.2443F);
		Leg6.setTextureOffset(18, 0).addBox(-15.857F, -1.5276F, -0.4675F, 16, 2, 2, 0.0F, false);

		Leg7 = new ModelRenderer(this);
		Leg7.setRotationPoint(2.0F, 0.0F, 0.0F);
		Body.addChild(Leg7);
		setRotation(Leg7, 0.0F, 0.576F, -0.2443F);
		Leg7.setTextureOffset(18, 0).addBox(0.157F, -1.5513F, -0.7916F, 16, 2, 2, 0.0F, true);

		Leg8 = new ModelRenderer(this);
		Leg8.setRotationPoint(-2.0F, 0.0F, 0.0F);
		Body.addChild(Leg8);
		setRotation(Leg8, 0.0F, -0.576F, 0.2443F);
		Leg8.setTextureOffset(18, 0).addBox(-16.157F, -1.5513F, -0.7916F, 16, 2, 2, 0.0F, false);

		RearEnd = new ModelRenderer(this);
		RearEnd.setRotationPoint(0.0F, 0.0F, 9.0F);
		Body.addChild(RearEnd);
		RearEnd.setTextureOffset(0, 23).addBox(-6.0F, -6.0F, 1.0F, 12, 10, 10, 0.0F, true);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		Body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float rot1 = -(MathHelper.cos(limbSwing * 1.324f) * 0.4f) * limbSwingAmount;
		float rot2 = -(MathHelper.cos(limbSwing * 1.324f + (float)Math.PI) * 0.4f) * limbSwingAmount;
		float rot3 = -(MathHelper.cos(limbSwing * 1.324f + (float)Math.PI / 2f) * 0.4f) * limbSwingAmount;
		float rot4 = -(MathHelper.cos(limbSwing * 1.324f + (float)Math.PI * 1.5f) * 0.4f) * limbSwingAmount;
		float lift1 = Math.abs(MathHelper.sin(limbSwing * 0.6662f) * 0.4f) * limbSwingAmount;
		float lift2 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + (float)Math.PI) * 0.4f) * limbSwingAmount;
		float lift3 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + (float)Math.PI / 2f) * 0.4f) * limbSwingAmount;
		float lift4 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + (float)Math.PI * 1.5f) * 0.4f) * limbSwingAmount;
		float quarterPi = -(float)Math.PI / 4f;
		float eighthPi = -(float)Math.PI / 8f;

		Leg1.rotateAngleY = quarterPi + rot1;
		Leg2.rotateAngleY = -quarterPi + rot1;
		Leg3.rotateAngleY = eighthPi + rot2;
		Leg4.rotateAngleY = -eighthPi + rot2;
		Leg5.rotateAngleY = -eighthPi + rot3;
		Leg6.rotateAngleY = eighthPi + rot3;
		Leg7.rotateAngleY = -quarterPi + rot4;
		Leg8.rotateAngleY = quarterPi + rot4;

		Leg1.rotateAngleZ = -eighthPi + lift1;
		Leg2.rotateAngleZ = eighthPi - lift1;
		Leg3.rotateAngleZ = -eighthPi * 0.74f + lift2;
		Leg4.rotateAngleZ = eighthPi * 0.74f - lift2;
		Leg5.rotateAngleZ = -eighthPi * 0.74f + lift3;
		Leg6.rotateAngleZ = eighthPi * 0.74f - lift3;
		Leg7.rotateAngleZ = -eighthPi + lift4;
		Leg8.rotateAngleZ = eighthPi - lift4;
	}
}

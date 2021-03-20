package net.tslat.aoa3.client.model.entity.mob.mysterium;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class NightmareSpiderModel extends EntityModel<MobEntity> {
	private final ModelRenderer Body;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg2;
	private final ModelRenderer Leg3;
	private final ModelRenderer Leg4;
	private final ModelRenderer Leg5;
	private final ModelRenderer Leg6;
	private final ModelRenderer Leg7;
	private final ModelRenderer Leg8;
	private final ModelRenderer RearEnd;
	private final ModelRenderer RearEnd2;
	private final ModelRenderer Head;

	public NightmareSpiderModel() {
		texWidth = 128;
		texHeight = 32;

		Body = new ModelRenderer(this);
		Body.setPos(0.0F, 20.0F, 0.0F);
		Body.texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6, 6, 6, 0.0F, true);

		Leg1 = new ModelRenderer(this);
		Leg1.setPos(2.0F, 0.0F, 0.5F);
		Body.addChild(Leg1);
		setRotation(Leg1, 0.0F, -0.576F, -0.2443F);
		Leg1.texOffs(18, 0).addBox(0.4293F, -1.5413F, -0.7891F, 16, 2, 2, 0.0F, true);

		Leg2 = new ModelRenderer(this);
		Leg2.setPos(-2.0F, 0.0F, 0.5F);
		Body.addChild(Leg2);
		setRotation(Leg2, 0.0F, 0.576F, 0.2443F);
		Leg2.texOffs(18, 0).addBox(-16.4293F, -1.5413F, -0.7891F, 16, 2, 2, 0.0F, false);

		Leg3 = new ModelRenderer(this);
		Leg3.setPos(2.0F, 0.0F, 0.5F);
		Body.addChild(Leg3);
		setRotation(Leg3, 0.0F, -0.2793F, -0.2443F);
		Leg3.texOffs(18, 0).addBox(-0.0052F, -1.5226F, -1.0519F, 16, 2, 2, 0.0F, true);

		Leg4 = new ModelRenderer(this);
		Leg4.setPos(-2.0F, 0.0F, 0.5F);
		Body.addChild(Leg4);
		setRotation(Leg4, 0.0F, 0.2793F, 0.2443F);
		Leg4.texOffs(18, 0).addBox(-15.9948F, -1.5226F, -1.0519F, 16, 2, 2, 0.0F, false);

		Leg5 = new ModelRenderer(this);
		Leg5.setPos(2.0F, 0.0F, 0.5F);
		Body.addChild(Leg5);
		setRotation(Leg5, 0.0F, 0.2793F, -0.2443F);
		Leg5.texOffs(18, 0).addBox(-0.0052F, -1.5276F, -0.9481F, 16, 2, 2, 0.0F, true);

		Leg6 = new ModelRenderer(this);
		Leg6.setPos(-2.0F, 0.0F, 0.5F);
		Body.addChild(Leg6);
		setRotation(Leg6, 0.0F, -0.2793F, 0.2443F);
		Leg6.texOffs(18, 0).addBox(-15.9948F, -1.5276F, -0.9481F, 16, 2, 2, 0.0F, false);

		Leg7 = new ModelRenderer(this);
		Leg7.setPos(2.0F, 0.0F, 0.5F);
		Body.addChild(Leg7);
		setRotation(Leg7, 0.0F, 0.576F, -0.2443F);
		Leg7.texOffs(18, 0).addBox(0.4293F, -1.5513F, -1.2109F, 16, 2, 2, 0.0F, true);

		Leg8 = new ModelRenderer(this);
		Leg8.setPos(-2.0F, 0.0F, 0.5F);
		Body.addChild(Leg8);
		setRotation(Leg8, 0.0F, -0.576F, 0.2443F);
		Leg8.texOffs(18, 0).addBox(-16.4293F, -1.5513F, -1.2109F, 16, 2, 2, 0.0F, false);

		RearEnd = new ModelRenderer(this);
		RearEnd.setPos(0.0F, 0.0F, 9.0F);
		Body.addChild(RearEnd);
		RearEnd.texOffs(0, 12).addBox(-5.0F, -4.0F, -6.0F, 10, 8, 12, 0.0F, true);

		RearEnd2 = new ModelRenderer(this);
		RearEnd2.setPos(-2.0F, -4.0F, 2.0F);
		RearEnd.addChild(RearEnd2);
		setRotation(RearEnd2, 0.6109F, 0.0F, 0.0F);
		RearEnd2.texOffs(68, 1).addBox(-1.0F, -4.0F, -6.0F, 2, 4, 9, 0.0F, true);
		RearEnd2.texOffs(68, 1).addBox(3.0F, -4.0F, -6.0F, 2, 4, 9, 0.0F, true);

		Head = new ModelRenderer(this);
		Head.setPos(0.0F, 0.0F, -3.0F);
		Body.addChild(Head);
		Head.texOffs(32, 4).addBox(-4.0F, -4.0F, -8.0F, 8, 8, 8, 0.0F, true);
		Head.texOffs(92, 4).addBox(2.0F, -8.0F, -7.0F, 2, 4, 2, 0.0F, true);
		Head.texOffs(92, 4).addBox(-4.0F, -8.0F, -7.0F, 2, 4, 2, 0.0F, true);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		Body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
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

		Leg1.yRot = quarterPi + rot1;
		Leg2.yRot = -quarterPi + rot1;
		Leg3.yRot = eighthPi + rot2;
		Leg4.yRot = -eighthPi + rot2;
		Leg5.yRot = -eighthPi + rot3;
		Leg6.yRot = eighthPi + rot3;
		Leg7.yRot = -quarterPi + rot4;
		Leg8.yRot = quarterPi + rot4;

		Leg1.zRot = -eighthPi + lift1;
		Leg2.zRot = eighthPi - lift1;
		Leg3.zRot = -eighthPi * 0.74f + lift2;
		Leg4.zRot = eighthPi * 0.74f - lift2;
		Leg5.zRot = -eighthPi * 0.74f + lift3;
		Leg6.zRot = eighthPi * 0.74f - lift3;
		Leg7.zRot = -eighthPi + lift4;
		Leg8.zRot = eighthPi - lift4;
	}
}

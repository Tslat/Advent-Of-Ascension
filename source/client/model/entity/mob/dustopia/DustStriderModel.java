package net.tslat.aoa3.client.model.entity.mob.dustopia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class DustStriderModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer RearEnd;
	private final ModelRenderer Leg2;
	private final ModelRenderer Leg1;
	private final ModelRenderer Leg2p2;
	private final ModelRenderer Leg1p2;
	private final ModelRenderer Leg2p3;
	private final ModelRenderer Leg1p3;
	private final ModelRenderer Leg2p4;
	private final ModelRenderer Leg1p4;
	private final ModelRenderer Leg2p5;
	private final ModelRenderer Leg1p5;
	private final ModelRenderer RearEnd2;
	private final ModelRenderer RearEnd3;
	private final ModelRenderer RearEnd4;
	private final ModelRenderer RearEnd5;
	private final ModelRenderer RearEnd6;
	private final ModelRenderer RearEnd7;
	private final ModelRenderer RearEnd8;
	private final ModelRenderer RearEnd9;
	private final ModelRenderer RearEnd10;
	private final ModelRenderer RearEnd11;
	private final ModelRenderer RearEnd12;
	private final ModelRenderer RearEnd13;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;

	public DustStriderModel() {
		textureWidth = 128;
		textureHeight = 32;
		(head = new ModelRenderer(this, 58, 23)).addBox(2.0f, 2.0f, -11.0f, 2, 2, 5);
		head.setRotationPoint(0.0f, 15.0f, -3.0f);
		head.setTextureSize(128, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(RearEnd = new ModelRenderer(this, 87, 8)).addBox(1.0f, -12.0f, 0.0f, 2, 2, 2);
		RearEnd.setRotationPoint(1.0f, 14.0f, 9.0f);
		RearEnd.setTextureSize(128, 32);
		RearEnd.mirror = true;
		setRotation(RearEnd, 0.0f, 0.0f, 0.0f);
		(Leg2 = new ModelRenderer(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2.setRotationPoint(4.0f, 18.0f, 10.0f);
		Leg2.setTextureSize(128, 32);
		Leg2.mirror = true;
		setRotation(Leg2, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1 = new ModelRenderer(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1.setRotationPoint(-4.0f, 18.0f, 10.0f);
		Leg1.setTextureSize(128, 32);
		Leg1.mirror = true;
		setRotation(Leg1, 0.0f, 0.5759587f, -0.1919862f);
		(Leg2p2 = new ModelRenderer(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2p2.setRotationPoint(4.0f, 18.0f, 6.0f);
		Leg2p2.setTextureSize(128, 32);
		Leg2p2.mirror = true;
		setRotation(Leg2p2, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1p2 = new ModelRenderer(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1p2.setRotationPoint(-4.0f, 18.0f, 6.0f);
		Leg1p2.setTextureSize(128, 32);
		Leg1p2.mirror = true;
		setRotation(Leg1p2, 0.0f, 0.5759587f, -0.1919862f);
		(Leg2p3 = new ModelRenderer(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2p3.setRotationPoint(4.0f, 18.0f, 2.0f);
		Leg2p3.setTextureSize(128, 32);
		Leg2p3.mirror = true;
		setRotation(Leg2p3, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1p3 = new ModelRenderer(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1p3.setRotationPoint(-4.0f, 18.0f, 2.0f);
		Leg1p3.setTextureSize(128, 32);
		Leg1p3.mirror = true;
		setRotation(Leg1p3, 0.0f, 0.5759587f, -0.1919862f);
		(Leg2p4 = new ModelRenderer(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2p4.setRotationPoint(4.0f, 18.0f, -6.0f);
		Leg2p4.setTextureSize(128, 32);
		Leg2p4.mirror = true;
		setRotation(Leg2p4, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1p4 = new ModelRenderer(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1p4.setRotationPoint(-4.0f, 18.0f, -6.0f);
		Leg1p4.setTextureSize(128, 32);
		Leg1p4.mirror = true;
		setRotation(Leg1p4, 0.0f, 0.5759587f, -0.1919862f);
		(Leg2p5 = new ModelRenderer(this, 48, 0)).addBox(-1.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg2p5.setRotationPoint(4.0f, 18.0f, -2.0f);
		Leg2p5.setTextureSize(128, 32);
		Leg2p5.mirror = true;
		setRotation(Leg2p5, 0.0f, -0.5759587f, 0.1919862f);
		(Leg1p5 = new ModelRenderer(this, 48, 0)).addBox(-15.0f, -3.0f, -1.0f, 16, 2, 2);
		Leg1p5.setRotationPoint(-4.0f, 18.0f, -2.0f);
		Leg1p5.setTextureSize(128, 32);
		Leg1p5.mirror = true;
		setRotation(Leg1p5, 0.0f, 0.5759587f, -0.1919862f);
		(RearEnd2 = new ModelRenderer(this, 0, 2)).addBox(-5.0f, -6.0f, -6.0f, 10, 9, 18);
		RearEnd2.setRotationPoint(0.0f, 14.0f, 0.0f);
		RearEnd2.setTextureSize(128, 32);
		RearEnd2.mirror = true;
		setRotation(RearEnd2, 0.0f, 0.0f, 0.0f);
		(RearEnd3 = new ModelRenderer(this, 87, 8)).addBox(-5.0f, -12.0f, 0.0f, 2, 2, 2);
		RearEnd3.setRotationPoint(1.0f, 14.0f, 9.0f);
		RearEnd3.setTextureSize(128, 32);
		RearEnd3.mirror = true;
		setRotation(RearEnd3, 0.0f, 0.0f, 0.0f);
		(RearEnd4 = new ModelRenderer(this, 72, 7)).addBox(1.0f, -10.0f, -5.0f, 2, 4, 8);
		RearEnd4.setRotationPoint(1.0f, 14.0f, 9.0f);
		RearEnd4.setTextureSize(128, 32);
		RearEnd4.mirror = true;
		setRotation(RearEnd4, 0.0f, 0.0f, 0.0f);
		(RearEnd5 = new ModelRenderer(this, 72, 7)).addBox(-5.0f, -10.0f, -5.0f, 2, 4, 8);
		RearEnd5.setRotationPoint(1.0f, 14.0f, 9.0f);
		RearEnd5.setTextureSize(128, 32);
		RearEnd5.mirror = true;
		setRotation(RearEnd5, 0.0f, 0.0f, 0.0f);
		(RearEnd6 = new ModelRenderer(this, 87, 8)).addBox(1.0f, -12.0f, -4.0f, 2, 2, 2);
		RearEnd6.setRotationPoint(1.0f, 14.0f, 9.0f);
		RearEnd6.setTextureSize(128, 32);
		RearEnd6.mirror = true;
		setRotation(RearEnd6, 0.0f, 0.0f, 0.0f);
		(RearEnd7 = new ModelRenderer(this, 87, 8)).addBox(-5.0f, -12.0f, -4.0f, 2, 2, 2);
		RearEnd7.setRotationPoint(1.0f, 14.0f, 9.0f);
		RearEnd7.setTextureSize(128, 32);
		RearEnd7.mirror = true;
		setRotation(RearEnd7, 0.0f, 0.0f, 0.0f);
		(RearEnd8 = new ModelRenderer(this, 87, 8)).addBox(1.0f, -12.0f, 0.0f, 2, 2, 2);
		RearEnd8.setRotationPoint(1.0f, 14.0f, 0.0f);
		RearEnd8.setTextureSize(128, 32);
		RearEnd8.mirror = true;
		setRotation(RearEnd8, 0.0f, 0.0f, 0.0f);
		(RearEnd9 = new ModelRenderer(this, 87, 8)).addBox(-5.0f, -12.0f, 0.0f, 2, 2, 2);
		RearEnd9.setRotationPoint(1.0f, 14.0f, 0.0f);
		RearEnd9.setTextureSize(128, 32);
		RearEnd9.mirror = true;
		setRotation(RearEnd9, 0.0f, 0.0f, 0.0f);
		(RearEnd10 = new ModelRenderer(this, 72, 7)).addBox(1.0f, -10.0f, -5.0f, 2, 4, 8);
		RearEnd10.setRotationPoint(1.0f, 14.0f, 0.0f);
		RearEnd10.setTextureSize(128, 32);
		RearEnd10.mirror = true;
		setRotation(RearEnd10, 0.0f, 0.0f, 0.0f);
		(RearEnd11 = new ModelRenderer(this, 87, 8)).addBox(1.0f, -12.0f, -4.0f, 2, 2, 2);
		RearEnd11.setRotationPoint(1.0f, 14.0f, 0.0f);
		RearEnd11.setTextureSize(128, 32);
		RearEnd11.mirror = true;
		setRotation(RearEnd11, 0.0f, 0.0f, 0.0f);
		(RearEnd12 = new ModelRenderer(this, 87, 8)).addBox(-5.0f, -12.0f, -4.0f, 2, 2, 2);
		RearEnd12.setRotationPoint(1.0f, 14.0f, 0.0f);
		RearEnd12.setTextureSize(128, 32);
		RearEnd12.mirror = true;
		setRotation(RearEnd12, 0.0f, 0.0f, 0.0f);
		(RearEnd13 = new ModelRenderer(this, 72, 7)).addBox(-5.0f, -10.0f, -5.0f, 2, 4, 8);
		RearEnd13.setRotationPoint(1.0f, 14.0f, 0.0f);
		RearEnd13.setTextureSize(128, 32);
		RearEnd13.mirror = true;
		setRotation(RearEnd13, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 96, 11)).addBox(-5.0f, -9.0f, -7.0f, 10, 10, 0);
		head2.setRotationPoint(0.0f, 15.0f, -3.0f);
		head2.setTextureSize(128, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 58, 23)).addBox(-4.0f, 2.0f, -11.0f, 2, 2, 5);
		head3.setRotationPoint(0.0f, 15.0f, -3.0f);
		head3.setTextureSize(128, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 42, 6)).addBox(-4.0f, -6.0f, -8.0f, 8, 8, 5);
		head4.setRotationPoint(0.0f, 15.0f, -3.0f);
		head4.setTextureSize(128, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1p2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2p3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1p3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2p4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1p4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg2p5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		Leg1p5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd12.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RearEnd13.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float var8 = ((float)Math.PI / 4F);
		Leg1.rotateAngleZ = -var8;
		Leg2.rotateAngleZ = var8;

		Leg1p2.rotateAngleZ = -var8;
		Leg2p2.rotateAngleZ = var8;

		Leg1p3.rotateAngleZ = -var8;
		Leg2p3.rotateAngleZ = var8;

		Leg1p4.rotateAngleZ = -var8;
		Leg2p4.rotateAngleZ = var8;

		Leg1p5.rotateAngleZ = -var8;
		Leg2p5.rotateAngleZ = var8;

		float var9 = -0.0F;
		float var10 = 0.3926991F;
		Leg1.rotateAngleY = var10 * 2.0F + var9;
		Leg2.rotateAngleY = -var10 * 2.0F - var9;

		Leg1p2.rotateAngleY = var10 * 2.0F + var9;
		Leg2p2.rotateAngleY = -var10 * 2.0F - var9;

		Leg1p3.rotateAngleY = var10 * 2.0F + var9;
		Leg2p3.rotateAngleY = -var10 * 2.0F - var9;

		Leg1p4.rotateAngleY = var10 * 2.0F + var9;
		Leg2p4.rotateAngleY = -var10 * 2.0F - var9;

		Leg1p5.rotateAngleY = var10 * 2.0F + var9;
		Leg2p5.rotateAngleY = -var10 * 2.0F - var9;

		float var11 = -(MathHelper.cos(limbSwing * 0.6662F * 2.0F + 0.0F) * 0.4F) * limbSwingAmount;
		float var15 = Math.abs(MathHelper.sin(limbSwing * 0.6662F + 0.0F) * 0.4F) * limbSwingAmount;

		Leg1.rotateAngleY += var11;
		Leg2.rotateAngleY += -var11;

		Leg1p2.rotateAngleZ += var15;
		Leg2p2.rotateAngleZ += -var15;

		Leg1p3.rotateAngleY += var11;
		Leg2p3.rotateAngleY += -var11;

		Leg1p4.rotateAngleZ += var15;
		Leg2p4.rotateAngleZ += -var15;

		Leg1p5.rotateAngleY += var11;
		Leg2p5.rotateAngleY += -var11;

		Leg1.rotateAngleZ += var15;
		Leg2.rotateAngleZ += -var15;

		Leg1p2.rotateAngleY += var11;
		Leg2p2.rotateAngleY += -var11;

		Leg1p3.rotateAngleZ += var15;
		Leg2p3.rotateAngleZ += -var15;

		Leg1p4.rotateAngleY += var11;
		Leg2p4.rotateAngleY += -var11;

		Leg1p5.rotateAngleZ += var15;
		Leg2p5.rotateAngleZ += -var15;
	}
}

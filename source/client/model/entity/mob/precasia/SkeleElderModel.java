package net.tslat.aoa3.client.model.entity.mob.precasia;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class SkeleElderModel extends EntityModel<MobEntity> {
	private ModelRenderer head;
	private ModelRenderer body;
	private ModelRenderer RightArm;
	private ModelRenderer LeftArm;
	private ModelRenderer RightLeg;
	private ModelRenderer LeftLeg;
	private ModelRenderer body2;

	public SkeleElderModel() {
		texWidth = 64;
		texHeight = 32;
		(head = new ModelRenderer(this, 0, 0)).addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setPos(0.0f, -9.0f, -11.0f);
		head.setTexSize(64, 32);
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(body = new ModelRenderer(this, 0, 16)).addBox(-4.0f, 0.5f, -2.0f, 10, 12, 4);
		body.setPos(-1.0f, -13.0f, -11.0f);
		body.setTexSize(64, 32);
		setRotation(body, 1.396263f, 0.0f, 0.0f);
		(RightArm = new ModelRenderer(this, 56, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 30, 2);
		RightArm.setPos(-5.0f, -12.0f, -8.0f);
		RightArm.setTexSize(64, 32);
		setRotation(RightArm, 0.0f, 0.0f, 0.0f);
		(LeftArm = new ModelRenderer(this, 56, 0)).addBox(-1.0f, -2.0f, -1.0f, 2, 30, 2);
		LeftArm.setPos(5.0f, -12.0f, -8.0f);
		LeftArm.setTexSize(64, 32);
		LeftArm.mirror = true;
		setRotation(LeftArm, 0.0f, 0.0f, 0.0f);
		(RightLeg = new ModelRenderer(this, 56, 0)).addBox(-1.0f, 0.0f, -1.0f, 2, 30, 2);
		RightLeg.setPos(-4.0f, -2.0f, 7.0f);
		RightLeg.setTexSize(64, 32);
		setRotation(RightLeg, 0.0f, 0.0f, 0.0f);
		(LeftLeg = new ModelRenderer(this, 56, 0)).addBox(-1.0f, 0.0f, -1.0f, 2, 30, 2);
		LeftLeg.setPos(5.0f, -2.0f, 7.0f);
		LeftLeg.setTexSize(64, 32);
		setRotation(LeftLeg, 0.0f, 0.0f, 0.0f);
		(body2 = new ModelRenderer(this, 32, 16)).addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		body2.setPos(0.0f, -11.0f, 0.0f);
		body2.setTexSize(64, 32);
		setRotation(body2, 0.6108652f, 0.0f, 0.0f);
	}

	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RightArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		LeftArm.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		RightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		LeftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		body2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.yRot = netHeadYaw / 57.295776f;
		head.xRot = headPitch / 54.11268f;
		RightArm.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 2.0f * limbSwingAmount * 0.5f;
		RightArm.zRot = 0.0f;
		LeftArm.xRot = MathHelper.cos(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
		LeftArm.zRot = 0.0f;
		RightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		RightLeg.yRot = 0.0f;
		LeftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

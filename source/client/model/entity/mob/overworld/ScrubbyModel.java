package net.tslat.aoa3.client.model.entity.mob.overworld;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ScrubbyModel extends EntityModel<MobEntity> {
	private final ModelRenderer root;
	private final ModelRenderer body;
	private final ModelRenderer rightleg;
	private final ModelRenderer leftleg;
	private final ModelRenderer rightarm;
	private final ModelRenderer leftarm;
	private final ModelRenderer head;

	public ScrubbyModel() {
		textureWidth = 64;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -12.0F, -1.0F);
		root.addChild(body);
		setRotation(body, 0.4363F, 0.0F, 0.0F);
		body.setTextureOffset(16, 16).addBox(-4.0F, -0.4226F, -2.9063F, 8, 7, 4, 0.0F, true);

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(3.0F, 6.0F, -1.0F);
		body.addChild(rightleg);
		setRotation(rightleg, -0.4363F, 0.0F, 0.0F);
		rightleg.setTextureOffset(0, 16).addBox(-2.0F, 0.1395F, -1.6294F, 4, 6, 4, 0.0F, true);

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(-3.0F, 6.0F, -1.0F);
		body.addChild(leftleg);
		setRotation(leftleg, -0.4363F, 0.0F, 0.0F);
		leftleg.setTextureOffset(0, 16).addBox(-2.0F, 0.1395F, -1.6294F, 4, 6, 4, 0.0F, false);

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(5.0F, 0.5774F, -1.9063F);
		body.addChild(rightarm);
		setRotation(rightarm, -0.4363F, 0.0F, 0.0F);
		rightarm.setTextureOffset(43, 16).addBox(-1.0F, -1.5774F, -2.0937F, 4, 8, 4, 0.0F, true);

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(-5.0F, 0.5774F, -1.9063F);
		body.addChild(leftarm);
		setRotation(leftarm, -0.4363F, 0.0F, 0.0F);
		leftarm.setTextureOffset(43, 16).addBox(-3.0F, -1.5774F, -2.0937F, 4, 8, 4, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.6711F, -4.4837F);
		body.addChild(head);
		setRotation(head, -0.4363F, 0.0F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.0F, -8.6711F, -3.5163F, 8, 8, 5, 0.0F, true);
		head.setTextureOffset(32, 0).addBox(-6.0F, -11.6711F, -2.5163F, 2, 6, 2, 0.0F, true);
		head.setTextureOffset(32, 0).addBox(4.0F, -11.6711F, -2.5163F, 2, 6, 2, 0.0F, true);
	}

	@Override
	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		head.rotateAngleY = netHeadYaw / 57.295776f;
		head.rotateAngleX = -0.43633f + headPitch / 54.11268f;
		rightleg.rotateAngleX = -0.43633f + MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leftleg.rotateAngleX = -0.43633f + MathHelper.cos(limbSwing * 0.6662f + (float)Math.PI) * 1.4f * limbSwingAmount;
	}

	@Override
	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}

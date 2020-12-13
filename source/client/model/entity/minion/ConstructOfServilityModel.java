package net.tslat.aoa3.client.model.entity.minion;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;

public class ConstructOfServilityModel extends EntityModel<MobEntity> {
	private final ModelRenderer root;
	private final ModelRenderer r2;

	public ConstructOfServilityModel() {
		super(RenderType::getEntityTranslucent);
		textureWidth = 64;
		textureHeight = 64;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 24.0F, 0.0F);
		setRotation(root, 0.1745F, 0.0F, 0.0F);

		ModelRenderer head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -24.0F, 2.0F);
		setRotation(head, -0.1745F, 0.0F, 0.0F);
		root.addChild(head);
		head.setTextureOffset(0, 0).addBox(-3.0F, -4.6527F, -5.9696F, 6, 6, 6, 0.0F, true);

		ModelRenderer bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, -2.6527F, -1.9696F);
		setRotation(bone, -1.1345F, 0.0F, 0.0F);
		head.addChild(bone);
		bone.setTextureOffset(24, 2).addBox(-2.0F, -5.22F, -3.5031F, 4, 7, 3, 0.0F, true);

		ModelRenderer body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		root.addChild(body);
		body.setTextureOffset(16, 14).addBox(-4.0F, -22.0F, -4.0F, 8, 10, 8, 0.0F, true);
		body.setTextureOffset(6, 32).addBox(-5.0F, -12.0F, -5.0F, 10, 4, 10, 0.0F, true);

		r2 = new ModelRenderer(this);
		r2.setRotationPoint(0.0F, -5.0F, 0.0F);
		body.addChild(r2);
		r2.setTextureOffset(0, 31).addBox(4.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, false);
		r2.setTextureOffset(0, 31).addBox(-8.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F, true);
		r2.setTextureOffset(0, 31).addBox(-2.0F, -2.0F, 4.0F, 4, 4, 4, 0.0F, true);
		r2.setTextureOffset(0, 31).addBox(-2.0F, -2.0F, -8.0F, 4, 4, 4, 0.0F, true);

		ModelRenderer leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(-5.0F, -22.0F, 0.0F);
		setRotation(leftarm, 0.0873F, 0.0F, 0.3491F);
		body.addChild(leftarm);
		leftarm.setTextureOffset(0, 16).addBox(-3.0F, 1.0F, -2.0F, 4, 11, 4, 0.0F, false);

		ModelRenderer rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(5.0F, -22.0F, 0.0F);
		setRotation(rightarm, 0.0873F, 0.0F, -0.3491F);
		body.addChild(rightarm);
		rightarm.setTextureOffset(0, 16).addBox(-1.0F, 1.0F, -2.0F, 4, 11, 4, 0.0F, true);
	}

	public void render(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r2.rotateAngleY = ageInTicks * 0.335f;
	}
}

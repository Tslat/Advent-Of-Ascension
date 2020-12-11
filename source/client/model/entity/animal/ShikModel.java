package net.tslat.aoa3.client.model.entity.animal;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.entity.animal.ShikEntity;

public class ShikModel extends EntityModel<ShikEntity> {
	private final ModelRenderer root;
	private final ModelRenderer eye;
	private final ModelRenderer leg1;
	private final ModelRenderer leg2;
	private final ModelRenderer leg3;
	private final ModelRenderer leg4;
	private final ModelRenderer claw;
	private final ModelRenderer bone7;
	private final ModelRenderer claw2;
	private final ModelRenderer bone12;

	public ShikModel() {
		textureWidth = 32;
		textureHeight = 32;

		root = new ModelRenderer(this);
		root.setRotationPoint(0.0F, 23.0F, 0.0F);
		root.setTextureOffset(0, 6).addBox(-1.5F, -3.0F, -1.0F, 3, 2, 3, 0.0F, false);

		ModelRenderer eyestalk = new ModelRenderer(this);
		eyestalk.setRotationPoint(0.0F, -2.65F, -1.25F);
		setRotation(eyestalk, 0.3491F, 0.0F, 0.0F);
		root.addChild(eyestalk);
		eyestalk.setTextureOffset(4, 11).addBox(-0.5F, -0.9663F, -0.0647F, 1, 2, 1, 0.0F, false);

		eye = new ModelRenderer(this);
		eye.setRotationPoint(0.0F, -0.75F, 0.5F);
		setRotation(eye, -0.3491F, 0.0F, 0.0F);
		eyestalk.addChild(eye);
		eye.setTextureOffset(14, 0).addBox(-1.5F, -2.5F, -1.5F, 3, 3, 3, -0.2F, false);

		leg1 = new ModelRenderer(this);
		leg1.setRotationPoint(-0.95F, -2.25F, 0.15F);
		setRotation(leg1, 0.0F, -0.4363F, 0.0F);
		root.addChild(leg1);
		leg1.setTextureOffset(4, 14).addBox(-0.5272F, 0.1036F, -0.5F, 1, 1, 1, 0.0F, false);

		ModelRenderer bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-0.65F, 0.65F, 0.0F);
		setRotation(bone2, 0.0F, -1.5708F, 0.9599F);
		leg1.addChild(bone2);
		bone2.setTextureOffset(0, 11).addBox(-0.5F, -0.414F, -0.6229F, 1, 2, 1, 0.0F, false);

		ModelRenderer bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(bone3, -0.8727F, 0.0F, 0.0F);
		bone2.addChild(bone3);
		bone3.setTextureOffset(8, 11).addBox(-0.501F, 0.7306F, 0.4574F, 1, 2, 1, -0.1F, false);

		leg2 = new ModelRenderer(this);
		leg2.setRotationPoint(-0.95F, -2.25F, 0.9F);
		setRotation(leg2, 0.0F, 0.0873F, 0.0F);
		root.addChild(leg2);
		leg2.setTextureOffset(4, 14).addBox(-0.5272F, 0.1036F, -0.5F, 1, 1, 1, 0.0F, false);

		ModelRenderer bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(-0.65F, 0.65F, 0.0F);
		setRotation(bone13, 0.0F, -1.5708F, 0.9599F);
		leg2.addChild(bone13);
		bone13.setTextureOffset(0, 11).addBox(-0.5F, -0.414F, -0.6229F, 1, 2, 1, 0.0F, false);

		ModelRenderer bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(bone14, -0.8727F, 0.0F, 0.0F);
		bone13.addChild(bone14);
		bone14.setTextureOffset(8, 11).addBox(-0.501F, 0.7306F, 0.4574F, 1, 2, 1, -0.1F, false);

		leg3 = new ModelRenderer(this);
		leg3.setRotationPoint(0.95F, -2.25F, 0.15F);
		setRotation(leg3, 0.0F, 0.4363F, 0.0F);
		root.addChild(leg3);
		leg3.setTextureOffset(4, 14).addBox(-0.4728F, 0.1036F, -0.5F, 1, 1, 1, 0.0F, false);

		ModelRenderer bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(0.65F, 0.65F, 0.0F);
		setRotation(bone4, 0.0F, 1.5708F, -0.9599F);
		leg3.addChild(bone4);
		bone4.setTextureOffset(0, 11).addBox(-0.5F, -0.414F, -0.6229F, 1, 2, 1, 0.0F, false);

		ModelRenderer bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(bone5, -0.8727F, 0.0F, 0.0F);
		bone4.addChild(bone5);
		bone5.setTextureOffset(8, 11).addBox(-0.499F, 0.7306F, 0.4574F, 1, 2, 1, -0.1F, false);

		leg4 = new ModelRenderer(this);
		leg4.setRotationPoint(0.95F, -2.25F, 0.9F);
		setRotation(leg4, 0.0F, -0.0873F, 0.0F);
		root.addChild(leg4);
		leg4.setTextureOffset(4, 14).addBox(-0.4728F, 0.1036F, -0.5F, 1, 1, 1, 0.0F, false);

		ModelRenderer bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.65F, 0.65F, 0.0F);
		setRotation(bone6, 0.0F, 1.5708F, -0.9599F);
		leg4.addChild(bone6);
		bone6.setTextureOffset(0, 11).addBox(-0.5F, -0.414F, -0.6229F, 1, 2, 1, 0.0F, false);

		ModelRenderer bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotation(bone11, -0.8727F, 0.0F, 0.0F);
		bone6.addChild(bone11);
		bone11.setTextureOffset(8, 11).addBox(-0.499F, 0.7306F, 0.4574F, 1, 2, 1, -0.1F, false);

		claw = new ModelRenderer(this);
		claw.setRotationPoint(-0.6F, -2.5F, -0.15F);
		setRotation(claw, -1.3963F, 0.6109F, 0.0F);
		root.addChild(claw);
		claw.setTextureOffset(8, 14).addBox(-0.5F, 0.5F, -0.5F, 1, 1, 1, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(0.0F, 1.5F, -0.5F);
		setRotation(bone7, 0.6981F, 0.0F, 0.0F);
		claw.addChild(bone7);
		bone7.setTextureOffset(8, 14).addBox(-0.499F, 0.75F, 0.0F, 1, 1, 1, -0.05F, false);
		bone7.setTextureOffset(4, 14).addBox(-0.501F, 0.0F, -0.001F, 1, 1, 1, -0.05F, false);

		ModelRenderer bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(-0.05F, 1.5188F, -0.0295F);
		setRotation(bone8, 0.8727F, 0.0F, 0.0F);
		bone7.addChild(bone8);
		bone8.setTextureOffset(12, 11).addBox(-0.451F, 0.1712F, -0.1581F, 1, 2, 1, 0.0F, false);

		ModelRenderer bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(0.0257F, 0.5119F, 1.0149F);
		setRotation(bone10, 1.3963F, 0.0F, 0.0F);
		bone7.addChild(bone10);
		bone10.setTextureOffset(0, 14).addBox(-0.548F, -0.1592F, -1.0233F, 1, 1, 1, -0.1F, false);

		ModelRenderer bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-1.047F, 1.4839F, 2.8264F);
		setRotation(bone9, -1.0472F, 0.0F, 0.0F);
		bone10.addChild(bone9);
		bone9.setTextureOffset(16, 11).addBox(0.4999F, 2.083F, -3.0185F, 1, 2, 1, -0.1F, false);

		claw2 = new ModelRenderer(this);
		claw2.setRotationPoint(0.6F, -2.5F, -0.15F);
		setRotation(claw2, -1.3963F, -0.6109F, 0.0F);
		root.addChild(claw2);
		claw2.setTextureOffset(8, 14).addBox(-0.5F, 0.5F, -0.5F, 1, 1, 1, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(0.0F, 1.5F, -0.5F);
		setRotation(bone12, 0.6981F, 0.0F, 0.0F);
		claw2.addChild(bone12);
		bone12.setTextureOffset(8, 14).addBox(-0.501F, 0.75F, 0.0F, 1, 1, 1, -0.05F, false);
		bone12.setTextureOffset(4, 14).addBox(-0.499F, 0.0F, -0.001F, 1, 1, 1, -0.05F, false);

		ModelRenderer bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(0.05F, 1.5188F, -0.0295F);
		setRotation(bone15, 0.8727F, 0.0F, 0.0F);
		bone12.addChild(bone15);
		bone15.setTextureOffset(12, 11).addBox(-0.549F, 0.1712F, -0.1581F, 1, 2, 1, 0.0F, false);

		ModelRenderer bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(-0.0257F, 0.4353F, 0.9506F);
		setRotation(bone16, 1.3963F, 0.0F, 0.0F);
		bone12.addChild(bone16);
		bone16.setTextureOffset(0, 14).addBox(-0.452F, -0.0826F, -1.0876F, 1, 1, 1, -0.1F, false);

		ModelRenderer bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(1.047F, 1.4839F, 2.8264F);
		setRotation(bone17, -1.0472F, 0.0F, 0.0F);
		bone16.addChild(bone17);
		bone17.setTextureOffset(16, 11).addBox(-1.4999F, 2.177F, -2.9843F, 1, 2, 1, -0.1F, false);

		ModelRenderer back = new ModelRenderer(this);
		back.setRotationPoint(0.0F, -3.2349F, 1.9145F);
		setRotation(back, -1.2217F, 0.0F, 0.0F);
		root.addChild(back);
		back.setTextureOffset(0, 0).addBox(-2.0F, -2.25F, 0.0F, 4, 3, 3, 0.0F, false);
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

	@Override
	public void setRotationAngles(ShikEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		eye.rotateAngleX = headPitch * (float)Math.PI / 180f * 0.5f;
		eye.rotateAngleY = netHeadYaw * (float)Math.PI / 180f * 0.5f;

		float rot1 = -(MathHelper.cos(limbSwing * 4f) * 0.4f) * limbSwingAmount;
		float rot2 = -(MathHelper.cos(limbSwing * 4f + (float)Math.PI) * 0.4f) * limbSwingAmount;
		float upRot1 = Math.abs(MathHelper.sin(limbSwing * 0.6662f) * 0.4f) * limbSwingAmount;
		float upRot2 = Math.abs(MathHelper.sin(limbSwing * 0.6662f + (float)Math.PI) * 0.4f) * limbSwingAmount;

		leg1.rotateAngleY = degToRad(-25) + rot1;
		leg2.rotateAngleY = degToRad(5) + rot2;
		leg3.rotateAngleY = degToRad(25) + -rot1;
		leg4.rotateAngleY = degToRad(-5) + -rot2;
		leg1.rotateAngleZ = upRot1;
		leg2.rotateAngleZ = -upRot2;
		leg3.rotateAngleZ = upRot1;
		leg4.rotateAngleZ = -upRot2;

		bone7.rotateAngleX = degToRad(40);
		claw.rotateAngleY = degToRad(35);
		claw.rotateAngleZ = 0;
		claw2.rotateAngleY = degToRad(-35);
		claw2.rotateAngleZ = 0;
		bone12.rotateAngleX = degToRad(40);
		claw.rotateAngleX = degToRad(-80);
		claw2.rotateAngleX = degToRad(-80);

		if (entity.isScared()) {
			claw.rotateAngleY += degToRad(-100);
			claw.rotateAngleZ += degToRad(180);
			bone7.rotateAngleX += degToRad(-20);
			claw2.rotateAngleY += degToRad(100);
			claw2.rotateAngleZ += degToRad(180);
			bone12.rotateAngleX += degToRad(-20);

			// claw.rotateAngleX += degToRad(-35);
			// claw2.rotateAngleX += degToRad(-35);
		}
		else if (entity.isDancing()) {
			claw.rotateAngleX += degToRad(-60);
			claw2.rotateAngleX += degToRad(-60);
		}
	}

	private float degToRad(float deg) {
		return (float)Math.PI / 180f * deg;
	}
}

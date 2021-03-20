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
		texWidth = 64;
		texHeight = 64;

		r1 = new ModelRenderer(this, 43, 0);
		r1.addBox(6.0F, -3.0F, -2.0F, 4, 4, 4);
		r1.setPos(0.0F, 9.466666F, 0.0F);
		r1.setTexSize(64, 32);
		r1.mirror = true;
		setRotation(r1, 0.0F, 0.0F, 0.0F);
		rightLeg = new ModelRenderer(this, 0, 16);
		rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		rightLeg.setPos(-3.0F, 12.0F, 0.0F);
		rightLeg.setTexSize(64, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0F, 0.0F, 0.0F);
		leftLeg = new ModelRenderer(this, 0, 16);
		leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4);
		leftLeg.setPos(3.0F, 12.0F, 0.0F);
		leftLeg.setTexSize(64, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0F, 0.0F, 0.0F);
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8);
		head.setPos(0.0F, 9.466666F, 0.0F);
		head.setTexSize(64, 32);
		head.mirror = true;
		setRotation(head, 0.0F, 0.0F, 0.0F);
		r2 = new ModelRenderer(this, 33, 11);
		r2.addBox(9.0F, -7.0F, -1.0F, 2, 1, 2);
		r2.setPos(0.0F, 9.466666F, 0.0F);
		r2.setTexSize(64, 32);
		r2.mirror = true;
		setRotation(r2, 0.0F, 0.0F, 0.0F);
		r3 = new ModelRenderer(this, 43, 0);
		r3.addBox(-10.0F, -3.0F, -2.0F, 4, 4, 4);
		r3.setPos(0.0F, 9.466666F, 0.0F);
		r3.setTexSize(64, 32);
		r3.mirror = true;
		setRotation(r3, 0.0F, 0.0F, 0.0F);
		r4 = new ModelRenderer(this, 33, 11);
		r4.addBox(-11.0F, -7.0F, -1.0F, 2, 1, 2);
		r4.setPos(0.0F, 9.466666F, 0.0F);
		r4.setTexSize(64, 32);
		r4.mirror = true;
		setRotation(r4, 0.0F, 0.0F, 0.0F);
		r5 = new ModelRenderer(this, 33, 0);
		r5.addBox(7.0F, -11.0F, -1.0F, 2, 8, 2);
		r5.setPos(0.0F, 9.466666F, 0.0F);
		r5.setTexSize(64, 32);
		r5.mirror = true;
		setRotation(r5, 0.0F, 0.0F, 0.0F);
		r6 = new ModelRenderer(this, 33, 0);
		r6.addBox(-9.0F, -11.0F, -1.0F, 2, 8, 2);
		r6.setPos(0.0F, 9.466666F, 0.0F);
		r6.setTexSize(64, 32);
		r6.mirror = true;
		setRotation(r6, 0.0F, 0.0F, 0.0F);
		r7 = new ModelRenderer(this, 33, 11);
		r7.addBox(-11.0F, -11.0F, -1.0F, 2, 1, 2);
		r7.setPos(0.0F, 9.466666F, 0.0F);
		r7.setTexSize(64, 32);
		r7.mirror = true;
		setRotation(r7, 0.0F, 0.0F, 0.0F);
		r8 = new ModelRenderer(this, 33, 11);
		r8.addBox(9.0F, -11.0F, -1.0F, 2, 1, 2);
		r8.setPos(0.0F, 9.466666F, 0.0F);
		r8.setTexSize(64, 32);
		r8.mirror = true;
		setRotation(r8, 0.0F, 0.0F, 0.0F);
		r9 = new ModelRenderer(this, 33, 11);
		r9.addBox(-11.0F, -9.0F, -1.0F, 2, 1, 2);
		r9.setPos(0.0F, 9.466666F, 0.0F);
		r9.setTexSize(64, 32);
		r9.mirror = true;
		setRotation(r9, 0.0F, 0.0F, 0.0F);
		r10 = new ModelRenderer(this, 33, 11);
		r10.addBox(9.0F, -9.0F, -1.0F, 2, 1, 2);
		r10.setPos(0.0F, 9.466666F, 0.0F);
		r10.setTexSize(64, 32);
		r10.mirror = true;
		setRotation(r10, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
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
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		r1.yRot = (ageInTicks * 0.067F * 4.25F);
		r2.yRot = (ageInTicks * 0.067F * 4.25F);
		r3.yRot = (ageInTicks * 0.067F * 4.25F);
		r4.yRot = (ageInTicks * 0.067F * 4.25F);
		r5.yRot = (ageInTicks * 0.067F * 4.25F);
		r6.yRot = (ageInTicks * 0.067F * 4.25F);
		r7.yRot = (ageInTicks * 0.067F * 4.25F);
		r8.yRot = (ageInTicks * 0.067F * 4.25F);
		r9.yRot = (ageInTicks * 0.067F * 4.25F);
		r10.yRot = (ageInTicks * 0.067F * 4.25F);

		rightLeg.xRot = (MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount);
		rightLeg.yRot = 0.0F;

		leftLeg.xRot = (MathHelper.cos(limbSwing * 0.6662F + 3.1415927F) * 1.4F * limbSwingAmount);

		head.yRot = (netHeadYaw / 57.295776F);
		head.xRot = (headPitch / 54.11268F);
	}
}

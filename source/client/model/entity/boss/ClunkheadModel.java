package net.tslat.aoa3.client.model.entity.boss;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class ClunkheadModel extends EntityModel<MobEntity> {
	private final ModelRenderer head;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer head2;
	private final ModelRenderer head3;
	private final ModelRenderer head4;
	private final ModelRenderer head5;
	private final ModelRenderer head6;
	private final ModelRenderer head7;
	private final ModelRenderer head8;
	private final ModelRenderer head9;
	private final ModelRenderer head10;
	private final ModelRenderer head11;

	public ClunkheadModel() {
		texWidth = 256;
		texHeight = 32;
		(head = new ModelRenderer(this, 196, 0)).addBox(6.0f, -12.0f, -12.0f, 2, 10, 10);
		head.setPos(0.0f, 12.0f, 4.0f);
		head.setTexSize(256, 32);
		head.mirror = true;
		setRotation(head, 0.0f, 0.0f, 0.0f);
		(rightLeg = new ModelRenderer(this, 0, 14)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		rightLeg.setPos(-4.0f, 12.0f, 0.0f);
		rightLeg.setTexSize(256, 32);
		rightLeg.mirror = true;
		setRotation(rightLeg, 0.0f, 0.0f, 0.0f);
		(leftLeg = new ModelRenderer(this, 0, 14)).addBox(-3.0f, 0.0f, -3.0f, 6, 12, 6);
		leftLeg.setPos(4.0f, 12.0f, 0.0f);
		leftLeg.setTexSize(256, 32);
		leftLeg.mirror = true;
		setRotation(leftLeg, 0.0f, 0.0f, 0.0f);
		(head2 = new ModelRenderer(this, 68, 0)).addBox(-8.0f, -18.0f, -2.0f, 16, 18, 6);
		head2.setPos(0.0f, 12.0f, 4.0f);
		head2.setTexSize(256, 32);
		head2.mirror = true;
		setRotation(head2, 0.0f, 0.0f, 0.0f);
		(head3 = new ModelRenderer(this, 25, 0)).addBox(-11.0f, -4.0f, -12.0f, 3, 3, 16);
		head3.setPos(0.0f, 12.0f, 4.0f);
		head3.setTexSize(256, 32);
		head3.mirror = true;
		setRotation(head3, 0.0f, 0.0f, 0.0f);
		(head4 = new ModelRenderer(this, 114, 18)).addBox(-8.0f, -2.0f, -12.0f, 16, 2, 10);
		head4.setPos(0.0f, 12.0f, 4.0f);
		head4.setTexSize(256, 32);
		head4.mirror = true;
		setRotation(head4, 0.0f, 0.0f, 0.0f);
		(head5 = new ModelRenderer(this, 169, 0)).addBox(-8.0f, -12.0f, -12.0f, 2, 10, 10);
		head5.setPos(0.0f, 12.0f, 4.0f);
		head5.setTexSize(256, 32);
		head5.mirror = true;
		setRotation(head5, 0.0f, 0.0f, 0.0f);
		(head6 = new ModelRenderer(this, 114, 0)).addBox(-8.0f, -18.0f, -12.0f, 16, 6, 10);
		head6.setPos(0.0f, 12.0f, 4.0f);
		head6.setTexSize(256, 32);
		head6.mirror = true;
		setRotation(head6, 0.0f, 0.0f, 0.0f);
		(head7 = new ModelRenderer(this, 25, 0)).addBox(-7.0f, -21.0f, -12.0f, 3, 3, 16);
		head7.setPos(0.0f, 12.0f, 4.0f);
		head7.setTexSize(256, 32);
		head7.mirror = true;
		setRotation(head7, 0.0f, 0.0f, 0.0f);
		(head8 = new ModelRenderer(this, 25, 0)).addBox(4.0f, -21.0f, -12.0f, 3, 3, 16);
		head8.setPos(0.0f, 12.0f, 4.0f);
		head8.setTexSize(256, 32);
		head8.mirror = true;
		setRotation(head8, 0.0f, 0.0f, 0.0f);
		(head9 = new ModelRenderer(this, 25, 0)).addBox(-11.0f, -17.0f, -12.0f, 3, 3, 16);
		head9.setPos(0.0f, 12.0f, 4.0f);
		head9.setTexSize(256, 32);
		head9.mirror = true;
		setRotation(head9, 0.0f, 0.0f, 0.0f);
		(head10 = new ModelRenderer(this, 25, 0)).addBox(8.0f, -4.0f, -12.0f, 3, 3, 16);
		head10.setPos(0.0f, 12.0f, 4.0f);
		head10.setTexSize(256, 32);
		head10.mirror = true;
		setRotation(head10, 0.0f, 0.0f, 0.0f);
		(head11 = new ModelRenderer(this, 25, 0)).addBox(8.0f, -17.0f, -12.0f, 3, 3, 16);
		head11.setPos(0.0f, 12.0f, 4.0f);
		head11.setTexSize(256, 32);
		head11.mirror = true;
		setRotation(head11, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		head.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		rightLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		leftLeg.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head2.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head3.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head4.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head5.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head6.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head7.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head8.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head9.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head10.render(matrix, buffer, light, overlay, red, green, blue, alpha);
		head11.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		rightLeg.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		rightLeg.yRot = 0.0f;
		leftLeg.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
	}
}

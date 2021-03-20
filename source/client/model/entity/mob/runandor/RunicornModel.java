package net.tslat.aoa3.client.model.entity.mob.runandor;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.math.MathHelper;

public class RunicornModel extends EntityModel<MobEntity> {
	private final ModelRenderer root;
	private final ModelRenderer horse;
	private final ModelRenderer part6;
	private final ModelRenderer part8;
	private final ModelRenderer leg4;
	private final ModelRenderer leg3;
	private final ModelRenderer leg2;
	private final ModelRenderer leg1;
	private final ModelRenderer part3;
	private final ModelRenderer part10;
	private final ModelRenderer part2;

	public RunicornModel() {
		texWidth = 128;
		texHeight = 64;

		root = new ModelRenderer(this);
		root.setPos(0.0F, 24.0F, 0.0F);

		horse = new ModelRenderer(this);
		horse.setPos(4.0F, -21.0F, -12.0F);
		root.addChild(horse);
		horse.texOffs(51, 25).addBox(-9.0F, -1.0F, 17.0F, 9, 2, 7, 0.0F, true);
		horse.texOffs(0, 25).addBox(-9.0F, 0.0F, 11.0F, 9, 7, 12, 0.0F, true);
		horse.texOffs(0, 44).addBox(-9.0F, 0.0F, 0.0F, 9, 9, 11, 0.0F, true);

		part6 = new ModelRenderer(this);
		part6.setPos(-1.0F, 6.0F, 7.0F);
		setRotation(part6, 0.9398F, 0.0F, 0.0F);
		horse.addChild(part6);
		part6.texOffs(55, 17).addBox(-7.0F, 0.0F, 0.0F, 7, 5, 3, 0.0F, true);

		part8 = new ModelRenderer(this);
		part8.setPos(-4.5F, 0.0F, 24.0F);
		setRotation(part8, 0.4833F, 0.0F, 0.0F);
		horse.addChild(part8);
		part8.texOffs(74, 43).addBox(-1.5F, -0.9294F, -1.7709F, 3, 19, 2, 0.0F, true);

		leg4 = new ModelRenderer(this);
		leg4.setPos(-1.0F, 1.0F, 22.0F);
		horse.addChild(leg4);
		leg4.texOffs(93, 12).addBox(-2.0F, 0.0F, -2.0F, 4, 20, 4, 0.0F, true);

		leg3 = new ModelRenderer(this);
		leg3.setPos(-8.0F, 1.0F, 22.0F);
		horse.addChild(leg3);
		leg3.texOffs(93, 12).addBox(-2.0F, 0.0F, -2.0F, 4, 20, 4, 0.0F, true);

		leg2 = new ModelRenderer(this);
		leg2.setPos(-8.0F, 1.0F, 3.0F);
		horse.addChild(leg2);
		leg2.texOffs(58, 40).addBox(-2.0F, 0.0F, -2.0F, 4, 20, 4, 0.0F, true);

		leg1 = new ModelRenderer(this);
		leg1.setPos(-1.0F, 1.0F, 3.0F);
		horse.addChild(leg1);
		leg1.texOffs(58, 40).addBox(-2.0F, 0.0F, -2.0F, 4, 20, 4, 0.0F, true);

		part3 = new ModelRenderer(this);
		part3.setPos(-2.0F, 4.0F, 3.0F);
		setRotation(part3, 0.6109F, 0.0F, 0.0F);
		horse.addChild(part3);
		part3.texOffs(0, 0).addBox(-4.5F, -12.403F, -3.1885F, 4, 13, 6, 0.0F, true);
		part3.texOffs(45, 46).addBox(-3.0F, -17.403F, 2.3115F, 1, 15, 3, 0.0F, true);

		part10 = new ModelRenderer(this);
		part10.setPos(-1.0F, -11.8696F, -0.1885F);
		part3.addChild(part10);
		part10.texOffs(22, 0).addBox(-4.0F, -5.0F, -8.0F, 5, 5, 11, 0.0F, true);
		part10.texOffs(35, 16).addBox(0.0F, -8.0333F, 0.9333F, 1, 3, 1, 0.0F, true);
		part10.texOffs(35, 16).addBox(-4.0F, -8.0333F, 1.0F, 1, 3, 1, 0.0F, true);

		part2 = new ModelRenderer(this);
		part2.setPos(-3.0F, -3.5333F, 0.0F);
		setRotation(part2, 0.3491F, 0.0F, 0.0F);
		part10.addChild(part2);
		part2.texOffs(42, 18).addBox(0.5F, -7.0778F, -1.6059F, 2, 9, 2, 0.0F, true);
		part2.texOffs(35, 16).addBox(1.0F, -10.0778F, -1.1725F, 1, 3, 1, 0.0F, true);
	}

	@Override
	public void renderToBuffer(MatrixStack matrix, IVertexBuilder buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrix, buffer, light, overlay, red, green, blue, alpha);
	}

	private void setRotation(final ModelRenderer model, final float x, final float y, final float z) {
		model.xRot = x;
		model.yRot = y;
		model.zRot = z;
	}

	@Override
	public void setupAnim(MobEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		leg1.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
		leg2.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg3.xRot = MathHelper.cos(limbSwing * 0.6662f + 3.1415927f) * 1.4f * limbSwingAmount;
		leg4.xRot = MathHelper.cos(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
	}
}

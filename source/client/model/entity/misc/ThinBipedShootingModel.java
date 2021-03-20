package net.tslat.aoa3.client.model.entity.misc;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.MobEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.entity.base.AoARangedMob;

import net.minecraft.client.renderer.entity.model.BipedModel.ArmPose;

public class ThinBipedShootingModel extends BipedModel<MobEntity> {
	public ThinBipedShootingModel() {
		super(0f, 0f, 64, 32);

		this.rightArm = new ModelRenderer(this, 40, 16);
		this.rightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0);
		this.rightArm.setPos(-5.0F, 2.0F, 0.0F);
		this.leftArm = new ModelRenderer(this, 40, 16);
		this.leftArm.mirror = true;
		this.leftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, 0);
		this.leftArm.setPos(5.0F, 2.0F, 0.0F);
		this.rightLeg = new ModelRenderer(this, 0, 16);
		this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0);
		this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
		this.leftLeg = new ModelRenderer(this, 0, 16);
		this.leftLeg.mirror = true;
		this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, 0);
		this.leftLeg.setPos(2.0F, 12.0F, 0.0F);
	}

	@Override
	public void prepareMobModel(MobEntity entity, float limbSwing, float limbSwingAmount, float partialTickTime) {
		this.rightArmPose = ArmPose.EMPTY;
		this.leftArmPose = ArmPose.EMPTY;
		ItemStack itemstack = entity.getItemInHand(Hand.MAIN_HAND);

		if (itemstack.getItem() instanceof BowItem && entity.isAggressive()) {
			if (entity.getMainArm() == HandSide.RIGHT) {
				this.rightArmPose = ArmPose.BOW_AND_ARROW;
			}
			else {
				this.leftArmPose = ArmPose.BOW_AND_ARROW;
			}
		}

		super.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTickTime);
	}

	@Override
	public void setupAnim(MobEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

		ItemStack itemstack = entity.getMainHandItem();
		AoARangedMob shooter = (AoARangedMob)entity;

		if (shooter.isAggressive() && (itemstack.isEmpty() || !(itemstack.getItem() instanceof BowItem))) {
			float f = MathHelper.sin(this.attackTime * (float)Math.PI);
			float f1 = MathHelper.sin((1.0F - (1.0F - this.attackTime) * (1.0F - this.attackTime)) * (float)Math.PI);
			this.rightArm.zRot = 0.0F;
			this.leftArm.zRot = 0.0F;
			this.rightArm.yRot = -(0.1F - f * 0.6F);
			this.leftArm.yRot = 0.1F - f * 0.6F;
			this.rightArm.xRot = -((float)Math.PI / 2F);
			this.leftArm.xRot = -((float)Math.PI / 2F);
			this.rightArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.leftArm.xRot -= f * 1.2F - f1 * 0.4F;
			this.rightArm.zRot += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.leftArm.zRot -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.rightArm.xRot += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
			this.leftArm.xRot -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		}
	}

	@Override
	public void translateToHand(HandSide sideIn, MatrixStack matrixStackIn) {
		float rot = sideIn == HandSide.RIGHT ? 1.0F : -1.0F;
		ModelRenderer modelrenderer = this.getArm(sideIn);
		modelrenderer.x += rot;
		modelrenderer.translateAndRotate(matrixStackIn);
		modelrenderer.x -= rot;
	}
}

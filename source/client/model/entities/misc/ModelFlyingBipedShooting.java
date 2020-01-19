package net.tslat.aoa3.client.model.entities.misc;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.tslat.aoa3.entity.base.AoAFlyingRangedMob;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

public class ModelFlyingBipedShooting extends ModelBiped {
	public ModelFlyingBipedShooting() {
		super(0f, 0f, 64, 32);
	}

	public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTickTime) {
		this.rightArmPose = ArmPose.EMPTY;
		this.leftArmPose = ArmPose.EMPTY;
		ItemStack itemstack = entity.getHeldItem(EnumHand.MAIN_HAND);

		if (itemstack.getItem() instanceof BaseBow && ((AoAFlyingRangedMob)entity).isShooting()) {
			if (entity.getPrimaryHand() == EnumHandSide.RIGHT) {
				this.rightArmPose = ArmPose.BOW_AND_ARROW;
			}
			else {
				this.leftArmPose = ArmPose.BOW_AND_ARROW;
			}
		}

		super.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTickTime);
	}

	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netheadYaw, headPitch, scaleFactor, entityIn);

		ItemStack itemstack = ((EntityLivingBase)entityIn).getHeldItemMainhand();
		AoAFlyingRangedMob entity = (AoAFlyingRangedMob)entityIn;

		if (entity.isShooting() && (itemstack.isEmpty() || !(itemstack.getItem() instanceof BaseBow))) {
			float f = MathHelper.sin(this.swingProgress * (float)Math.PI);
			float f1 = MathHelper.sin((1.0F - (1.0F - this.swingProgress) * (1.0F - this.swingProgress)) * (float)Math.PI);
			this.bipedRightArm.rotateAngleZ = 0.0F;
			this.bipedLeftArm.rotateAngleZ = 0.0F;
			this.bipedRightArm.rotateAngleY = -(0.1F - f * 0.6F);
			this.bipedLeftArm.rotateAngleY = 0.1F - f * 0.6F;
			this.bipedRightArm.rotateAngleX = -((float)Math.PI / 2F);
			this.bipedLeftArm.rotateAngleX = -((float)Math.PI / 2F);
			this.bipedRightArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
			this.bipedLeftArm.rotateAngleX -= f * 1.2F - f1 * 0.4F;
			this.bipedRightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
			this.bipedRightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
			this.bipedLeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
		}
	}

	public void postRenderArm(float scale, EnumHandSide side) {
		float f = side == EnumHandSide.RIGHT ? 1.0F : -1.0F;
		ModelRenderer modelrenderer = this.getArmForSide(side);

		modelrenderer.rotationPointX += f;
		modelrenderer.postRender(scale);
		modelrenderer.rotationPointX -= f;
	}
}

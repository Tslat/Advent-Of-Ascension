/*
package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.ArrowLayer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.HumanoidModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.Entity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.item.UseAnim;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.HumanoidArm;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraft.util.math.vector.Vector3f;

import net.tslat.aoa3.content.entity.mob.misc.doppelganger.DoppelgangerEntity;

public class DoppelgangerRenderer extends LivingRenderer<DoppelgangerEntity, PlayerModel<DoppelgangerEntity>> {
	public DoppelgangerRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new PlayerModel<DoppelgangerEntity>(0, false), 0.5f);

		this.addLayer(new BipedArmorLayer<>(this, new HumanoidModel<>(0.5F), new HumanoidModel<>(1.0F)));
		this.addLayer(new HeldItemLayer<>(this));
		this.addLayer(new ArrowLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(DoppelgangerEntity entity) {
		return Minecraft.getInstance().player.getSkinTextureLocation();
	}

	@Override
	public Vec3 getRenderOffset(DoppelgangerEntity doppelganger, float partialTicks) {
		return doppelganger.isCrouching() ? new Vec3(0, -0.125d, 0) : super.getRenderOffset(doppelganger, partialTicks);
	}

	@Override
	public void render(DoppelgangerEntity doppelganger, float rotYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
		setModelStates(doppelganger);
		super.render(doppelganger, rotYaw, partialTicks, matrixStack, buffer, packedLight);
	}

	@Override
	protected void scale(DoppelgangerEntity doppelganger, PoseStack matrixStack, float partialTicks) {
		matrixStack.scale(0.9375f, 0.9375f, 0.9375f);
	}

	@Override
	protected boolean shouldShowName(DoppelgangerEntity pEntity) {
		return false;
	}

	@Override
	protected void renderNameTag(DoppelgangerEntity entity, TextComponent displayName, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
		matrixStack.pushPose();
		matrixStack.translate(0, 0.25875f, 0);
		super.renderNameTag(entity, Minecraft.getInstance().player.getDisplayName(), matrixStack, buffer, packedLight);
		matrixStack.popPose();
	}

	private void setModelStates(DoppelgangerEntity doppelganger) {
		PlayerModel<DoppelgangerEntity> model = this.getModel();

		model.setAllVisible(true);

		model.hat.visible = false;
		model.jacket.visible = false;
		model.leftPants.visible = false;
		model.rightPants.visible = false;
		model.leftSleeve.visible = false;
		model.rightSleeve.visible = false;
		model.crouching = doppelganger.isCrouching();
		HumanoidModel.ArmPose mainHandArmPose = getArmPose(doppelganger, InteractionHand.MAIN_HAND);
		HumanoidModel.ArmPose offhandArmPose = getArmPose(doppelganger, InteractionHand.OFF_HAND);

		if (mainHandArmPose.isTwoHanded())
			offhandArmPose = doppelganger.getOffhandItem().isEmpty() ? HumanoidModel.ArmPose.EMPTY : HumanoidModel.ArmPose.ITEM;

		if (doppelganger.getMainArm() == HumanoidArm.RIGHT) {
			model.rightArmPose = mainHandArmPose;
			model.leftArmPose = offhandArmPose;
		}
		else {
			model.rightArmPose = offhandArmPose;
			model.leftArmPose = mainHandArmPose;
		}
	}

	private static HumanoidModel.ArmPose getArmPose(DoppelgangerEntity doppelganger, InteractionHand hand) {
		ItemStack stack = doppelganger.getItemInHand(hand);

		if (stack.isEmpty())
			return HumanoidModel.ArmPose.EMPTY;

		if (doppelganger.getUsedItemHand() == hand && doppelganger.getUseItemRemainingTicks() > 0) {
			UseAnim useaction = stack.getUseAnimation();

			if (useaction == UseAnim.BLOCK)
				return HumanoidModel.ArmPose.BLOCK;

			if (useaction == UseAnim.BOW)
				return HumanoidModel.ArmPose.BOW_AND_ARROW;

			if (useaction == UseAnim.SPEAR)
				return HumanoidModel.ArmPose.THROW_SPEAR;

			if (useaction == UseAnim.CROSSBOW && hand == doppelganger.getUsedItemHand())
				return HumanoidModel.ArmPose.CROSSBOW_CHARGE;

		}
		else if (!doppelganger.swinging && stack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(stack)) {
			return HumanoidModel.ArmPose.CROSSBOW_HOLD;
		}

		return HumanoidModel.ArmPose.ITEM;
	}

	public void renderRightHand(PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, DoppelgangerEntity doppelganger) {
		this.renderHand(matrixStack, buffer, combinedLight, doppelganger, (this.model).rightArm, (this.model).rightSleeve);
	}

	public void renderLeftHand(PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, DoppelgangerEntity doppelganger) {
		this.renderHand(matrixStack, buffer, combinedLight, doppelganger, (this.model).leftArm, (this.model).leftSleeve);
	}

	private void renderHand(PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, DoppelgangerEntity doppelganger, ModelRenderer armPiece, ModelRenderer armCovering) {
		PlayerModel<DoppelgangerEntity> model = this.getModel();

		this.setModelStates(doppelganger);

		model.attackTime = 0;
		model.crouching = false;
		model.swimAmount = 0;

		model.setupAnim(doppelganger, 0, 0, 0, 0, 0);

		armPiece.getXRot() = 0;

		armPiece.render(matrixStack, buffer.getBuffer(RenderType.entitySolid(getTextureLocation(doppelganger))), combinedLight, OverlayTexture.NO_OVERLAY);

		armCovering.getXRot() = 0;

		armCovering.render(matrixStack, buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(doppelganger))), combinedLight, OverlayTexture.NO_OVERLAY);
	}

	@Override
	protected void setupRotations(DoppelgangerEntity doppelganger, PoseStack matrixStack, float age, float yawRot, float partialTicks) {
		if (doppelganger.isFallFlying()) {
			super.setupRotations(doppelganger, matrixStack, age, yawRot, partialTicks);

			float lerpFallTicks = (float)doppelganger.getFallFlyingTicks() + partialTicks;

			if (!doppelganger.isAutoSpinAttack())
				matrixStack.mulPose(Vector3f.XP.rotationDegrees(Mth.clamp(lerpFallTicks * lerpFallTicks / 100.0F, 0.0F, 1.0F) * (-90.0F - doppelganger.getXRot())));

			Vec3 viewVec = doppelganger.getViewVector(partialTicks);
			Vec3 velocity = doppelganger.getDeltaMovement();
			double velocityScaleOffset = Entity.getHorizontalDistanceSqr(velocity);
			double viewScaleOffset = Entity.getHorizontalDistanceSqr(viewVec);

			if (velocityScaleOffset > 0 && viewScaleOffset > 0)
				matrixStack.mulPose(Vector3f.YP.rotation((float)(Math.signum(velocity.x * viewVec.z - velocity.z * viewVec.x) * Math.acos((velocity.x * viewVec.x + velocity.z * viewVec.z) / Math.sqrt(velocityScaleOffset * viewScaleOffset)))));
		}
		else if (doppelganger.getSwimAmount(partialTicks) > 0) {
			super.setupRotations(doppelganger, matrixStack, age, yawRot, partialTicks);

			matrixStack.mulPose(Vector3f.XP.rotationDegrees(Mth.lerp(doppelganger.getSwimAmount(partialTicks), 0, doppelganger.isInWater() ? -90 - doppelganger.getXRot() : -90)));

			if (doppelganger.isVisuallySwimming())
				matrixStack.translate(9, -1, 0.3f);
		}
		else {
			super.setupRotations(doppelganger, matrixStack, age, yawRot, partialTicks);
		}
	}
}
*/

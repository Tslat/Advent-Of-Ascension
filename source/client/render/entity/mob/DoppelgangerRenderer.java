package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.ArrowLayer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.tslat.aoa3.content.entity.mob.misc.doppelganger.DoppelgangerEntity;

public class DoppelgangerRenderer extends LivingRenderer<DoppelgangerEntity, PlayerModel<DoppelgangerEntity>> {
	public DoppelgangerRenderer(EntityRendererManager renderManager) {
		super(renderManager, new PlayerModel<DoppelgangerEntity>(0, false), 0.5f);

		this.addLayer(new BipedArmorLayer<>(this, new BipedModel<>(0.5F), new BipedModel<>(1.0F)));
		this.addLayer(new HeldItemLayer<>(this));
		this.addLayer(new ArrowLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(DoppelgangerEntity entity) {
		return Minecraft.getInstance().player.getSkinTextureLocation();
	}

	@Override
	public Vector3d getRenderOffset(DoppelgangerEntity doppelganger, float partialTicks) {
		return doppelganger.isCrouching() ? new Vector3d(0, -0.125d, 0) : super.getRenderOffset(doppelganger, partialTicks);
	}

	@Override
	public void render(DoppelgangerEntity doppelganger, float rotYaw, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
		setModelStates(doppelganger);
		super.render(doppelganger, rotYaw, partialTicks, matrixStack, buffer, packedLight);
	}

	@Override
	protected void scale(DoppelgangerEntity doppelganger, MatrixStack matrixStack, float partialTicks) {
		matrixStack.scale(0.9375f, 0.9375f, 0.9375f);
	}

	@Override
	protected boolean shouldShowName(DoppelgangerEntity pEntity) {
		return false;
	}

	@Override
	protected void renderNameTag(DoppelgangerEntity entity, ITextComponent displayName, MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight) {
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
		BipedModel.ArmPose mainHandArmPose = getArmPose(doppelganger, Hand.MAIN_HAND);
		BipedModel.ArmPose offhandArmPose = getArmPose(doppelganger, Hand.OFF_HAND);

		if (mainHandArmPose.isTwoHanded())
			offhandArmPose = doppelganger.getOffhandItem().isEmpty() ? BipedModel.ArmPose.EMPTY : BipedModel.ArmPose.ITEM;

		if (doppelganger.getMainArm() == HandSide.RIGHT) {
			model.rightArmPose = mainHandArmPose;
			model.leftArmPose = offhandArmPose;
		}
		else {
			model.rightArmPose = offhandArmPose;
			model.leftArmPose = mainHandArmPose;
		}
	}

	private static BipedModel.ArmPose getArmPose(DoppelgangerEntity doppelganger, Hand hand) {
		ItemStack stack = doppelganger.getItemInHand(hand);

		if (stack.isEmpty())
			return BipedModel.ArmPose.EMPTY;

		if (doppelganger.getUsedItemHand() == hand && doppelganger.getUseItemRemainingTicks() > 0) {
			UseAction useaction = stack.getUseAnimation();

			if (useaction == UseAction.BLOCK)
				return BipedModel.ArmPose.BLOCK;

			if (useaction == UseAction.BOW)
				return BipedModel.ArmPose.BOW_AND_ARROW;

			if (useaction == UseAction.SPEAR)
				return BipedModel.ArmPose.THROW_SPEAR;

			if (useaction == UseAction.CROSSBOW && hand == doppelganger.getUsedItemHand())
				return BipedModel.ArmPose.CROSSBOW_CHARGE;

		}
		else if (!doppelganger.swinging && stack.getItem() instanceof CrossbowItem && CrossbowItem.isCharged(stack)) {
			return BipedModel.ArmPose.CROSSBOW_HOLD;
		}

		return BipedModel.ArmPose.ITEM;
	}

	public void renderRightHand(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, DoppelgangerEntity doppelganger) {
		this.renderHand(matrixStack, buffer, combinedLight, doppelganger, (this.model).rightArm, (this.model).rightSleeve);
	}

	public void renderLeftHand(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, DoppelgangerEntity doppelganger) {
		this.renderHand(matrixStack, buffer, combinedLight, doppelganger, (this.model).leftArm, (this.model).leftSleeve);
	}

	private void renderHand(MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, DoppelgangerEntity doppelganger, ModelRenderer armPiece, ModelRenderer armCovering) {
		PlayerModel<DoppelgangerEntity> model = this.getModel();

		this.setModelStates(doppelganger);

		model.attackTime = 0;
		model.crouching = false;
		model.swimAmount = 0;

		model.setupAnim(doppelganger, 0, 0, 0, 0, 0);

		armPiece.xRot = 0;

		armPiece.render(matrixStack, buffer.getBuffer(RenderType.entitySolid(getTextureLocation(doppelganger))), combinedLight, OverlayTexture.NO_OVERLAY);

		armCovering.xRot = 0;

		armCovering.render(matrixStack, buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(doppelganger))), combinedLight, OverlayTexture.NO_OVERLAY);
	}

	@Override
	protected void setupRotations(DoppelgangerEntity doppelganger, MatrixStack matrixStack, float age, float yawRot, float partialTicks) {
		if (doppelganger.isFallFlying()) {
			super.setupRotations(doppelganger, matrixStack, age, yawRot, partialTicks);

			float lerpFallTicks = (float)doppelganger.getFallFlyingTicks() + partialTicks;

			if (!doppelganger.isAutoSpinAttack())
				matrixStack.mulPose(Vector3f.XP.rotationDegrees(MathHelper.clamp(lerpFallTicks * lerpFallTicks / 100.0F, 0.0F, 1.0F) * (-90.0F - doppelganger.xRot)));

			Vector3d viewVec = doppelganger.getViewVector(partialTicks);
			Vector3d velocity = doppelganger.getDeltaMovement();
			double velocityScaleOffset = Entity.getHorizontalDistanceSqr(velocity);
			double viewScaleOffset = Entity.getHorizontalDistanceSqr(viewVec);

			if (velocityScaleOffset > 0 && viewScaleOffset > 0)
				matrixStack.mulPose(Vector3f.YP.rotation((float)(Math.signum(velocity.x * viewVec.z - velocity.z * viewVec.x) * Math.acos((velocity.x * viewVec.x + velocity.z * viewVec.z) / Math.sqrt(velocityScaleOffset * viewScaleOffset)))));
		}
		else if (doppelganger.getSwimAmount(partialTicks) > 0) {
			super.setupRotations(doppelganger, matrixStack, age, yawRot, partialTicks);

			matrixStack.mulPose(Vector3f.XP.rotationDegrees(MathHelper.lerp(doppelganger.getSwimAmount(partialTicks), 0, doppelganger.isInWater() ? -90 - doppelganger.xRot : -90)));

			if (doppelganger.isVisuallySwimming())
				matrixStack.translate(9, -1, 0.3f);
		}
		else {
			super.setupRotations(doppelganger, matrixStack, age, yawRot, partialTicks);
		}
	}
}

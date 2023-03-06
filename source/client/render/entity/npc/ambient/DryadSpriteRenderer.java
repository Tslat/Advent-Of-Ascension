package net.tslat.aoa3.client.render.entity.npc.ambient;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.util.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.event.RenderNameplateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.fml.ModList;
import net.tslat.aoa3.client.model.entity.npc.DryadSpriteModel;
import net.tslat.aoa3.content.entity.npc.ambient.DryadSpriteEntity;
import software.bernie.geckolib3.compat.PatchouliCompat;
import software.bernie.geckolib3.core.IAnimatableModel;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.util.Color;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.provider.GeoModelProvider;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;

import java.util.Collections;

public class DryadSpriteRenderer extends GeoEntityRenderer<DryadSpriteEntity> {
	public DryadSpriteRenderer(EntityRendererManager renderManager, float shadowSize) {
		super(renderManager, new DryadSpriteModel());

		shadowRadius = shadowSize;
	}

	@Override
	public void render(DryadSpriteEntity entity, float entityYaw, float partialTicks, MatrixStack stack, IRenderTypeBuffer bufferIn, int packedLightIn) {
		stack.pushPose();
		boolean shouldSit = entity.isPassenger() && (entity.getVehicle() != null && entity.getVehicle().shouldRiderSit());
		EntityModelData entityModelData = new EntityModelData();
		entityModelData.isSitting = shouldSit;
		entityModelData.isChild = entity.isBaby();

		float lerpBodyRot = MathHelper.rotLerp(partialTicks, entity.yBodyRotO, entity.yBodyRot);
		float lerpHeadRot = MathHelper.rotLerp(partialTicks, entity.yHeadRotO, entity.yHeadRot);
		float netHeadYaw = lerpHeadRot - lerpBodyRot;

		if (shouldSit && entity.getVehicle() instanceof LivingEntity) {
			LivingEntity vehicle = (LivingEntity) entity.getVehicle();
			lerpBodyRot = MathHelper.rotLerp(partialTicks, vehicle.yBodyRotO, vehicle.yBodyRot);
			netHeadYaw = lerpHeadRot - lerpBodyRot;
			float finalHeadYaw = MathHelper.clamp(MathHelper.wrapDegrees(netHeadYaw), -85, 85);

			lerpBodyRot = lerpHeadRot - finalHeadYaw;

			if (finalHeadYaw * finalHeadYaw > 2500)
				lerpBodyRot += finalHeadYaw * 0.2F;

			netHeadYaw = lerpHeadRot - lerpBodyRot;
		}

		float headPitch = MathHelper.lerp(partialTicks, entity.xRotO, entity.xRot);

		if (entity.getPose() == Pose.SLEEPING) {
			Direction direction = entity.getBedOrientation();

			if (direction != null) {
				float eyeHeight = entity.getEyeHeight(Pose.STANDING) - 0.1F;

				stack.translate((float) (-direction.getStepX()) * eyeHeight, 0.0D, (float) (-direction.getStepZ()) * eyeHeight);
			}
		}

		float lerpAge = this.handleRotationFloat(entity, partialTicks);
		this.applyRotations(entity, stack, lerpAge, lerpBodyRot, partialTicks);

		float limbSwingAmount = 0;
		float limbSwing = 0;

		if (!shouldSit && entity.isAlive()) {
			limbSwingAmount = MathHelper.lerp(partialTicks, entity.animationSpeedOld, entity.animationSpeed);
			limbSwing = entity.animationPosition - entity.animationSpeed * (1.0F - partialTicks);

			if (entity.isBaby())
				limbSwing *= 3;

			if (limbSwingAmount > 1)
				limbSwingAmount = 1;
		}

		entityModelData.headPitch = -headPitch;
		entityModelData.netHeadYaw = -netHeadYaw;

		AnimationEvent<DryadSpriteEntity> predicate = new AnimationEvent<DryadSpriteEntity>(entity, limbSwing, limbSwingAmount, partialTicks, !(limbSwingAmount > -0.15F && limbSwingAmount < 0.15F), Collections.singletonList(entityModelData));
		GeoModelProvider<DryadSpriteEntity> modelProvider = getGeoModelProvider();
		GeoModel model = modelProvider.getModel(modelProvider.getModelLocation(entity));

		if (modelProvider instanceof IAnimatableModel)
			((IAnimatableModel<DryadSpriteEntity>) modelProvider).setLivingAnimations(entity, this.getUniqueID(entity), predicate);

		stack.translate(0, 0.01f, 0);

		Minecraft.getInstance().textureManager.bind(getTextureLocation(entity));

		Color renderColor = getRenderColor(entity, partialTicks, stack, bufferIn, null, packedLightIn);
		RenderType renderType = getRenderType(entity, partialTicks, stack, bufferIn, null, packedLightIn, getTextureLocation(entity));
		float red = renderColor.getRed() / 255f;
		float green = renderColor.getGreen() / 255f;
		float blue = renderColor.getBlue() / 255f;
		float alpha = entity.isInvisibleTo(Minecraft.getInstance().player) ? 0 : renderColor.getAlpha() / 255f;

		render(model, entity, partialTicks, renderType, stack, bufferIn, null, packedLightIn, getPackedOverlay(entity, 0), red, green, blue, alpha);

		if (!entity.isSpectator()) {
			for (GeoLayerRenderer<DryadSpriteEntity> layerRenderer : this.layerRenderers) {
				layerRenderer.render(stack, bufferIn, packedLightIn, entity, limbSwing, limbSwingAmount, partialTicks, lerpAge, netHeadYaw, headPitch);
			}
		}

		if (ModList.get().isLoaded("patchouli"))
			PatchouliCompat.patchouliLoaded(stack);

		stack.popPose();

		RenderNameplateEvent renderNameplateEvent = new RenderNameplateEvent(entity, entity.getDisplayName(), this, stack, bufferIn, packedLightIn, partialTicks);
		MinecraftForge.EVENT_BUS.post(renderNameplateEvent);

		if (renderNameplateEvent.getResult() != Event.Result.DENY && (renderNameplateEvent.getResult() == Event.Result.ALLOW || this.shouldShowName(entity)))
			renderNameTag(entity, renderNameplateEvent.getContent(), stack, bufferIn, packedLightIn);
	}

	@Override
	protected float getDeathMaxRotation(DryadSpriteEntity entityLivingBaseIn) {
		return 0;
	}
}

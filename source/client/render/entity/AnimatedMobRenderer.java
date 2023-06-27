package net.tslat.aoa3.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.LivingEntity;
import net.tslat.aoa3.client.render.entity.layer.GeoEntityChargeLayer;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class AnimatedMobRenderer<T extends LivingEntity & GeoEntity> extends GeoEntityRenderer<T> {
	public AnimatedMobRenderer(EntityRendererProvider.Context renderManager, GeoModel<T> model, float shadowSize) {
		super(renderManager, model);

		this.shadowRadius = shadowSize;

		addRenderLayer(new GeoEntityChargeLayer<>(this));
	}

	@Override
	public float getMotionAnimThreshold(T animatable) {
		return 0.012f;
	}

	@Override
	public void actuallyRender(PoseStack poseStack, T animatable, BakedGeoModel model, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		if (animatable.getStringUUID().startsWith("00000")) { // Shiny :)
			super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, LightTexture.FULL_BRIGHT, packedOverlay, 0, 0, 0, 0.5f);
		}
		else {
			super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
		}
	}
}

package net.tslat.aoa3.client.render.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.tslat.aoa3.advent.AdventOfAscension;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;

import javax.annotation.Nullable;

public class GeoEntityChargeLayer<T extends Entity & GeoEntity> extends GeoRenderLayer<T> {
	protected static final ResourceLocation INVULNERABILITY_TEXTURE = AdventOfAscension.id("textures/entity/misc/invulnerability_aura.png");

	public GeoEntityChargeLayer(GeoRenderer<T> baseRenderer) {
		super(baseRenderer);
	}

	@Override
	public void render(PoseStack poseStack, T animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
		ResourceLocation texture = getLayerTexture(animatable, partialTick);

		if (texture == null)
			return;

		float lerpedTicks = (float)animatable.tickCount + partialTick;
		RenderType layerRenderType = RenderType.energySwirl(texture, adjustU(lerpedTicks) % 1, lerpedTicks * 0.005F % 1);

		this.renderer.reRender(bakedModel, poseStack, bufferSource, animatable, layerRenderType, buffer, partialTick, packedLight, packedOverlay, 0.5f, 0.5f, 0.5f, 0.5f);
	}

	@Nullable
	protected ResourceLocation getLayerTexture(T entity, float partialTicks) {
		return entity.isInvulnerable() ? INVULNERABILITY_TEXTURE : null;
	}

	protected float adjustU(float tick) {
		return tick * 0.005f;
	}
}

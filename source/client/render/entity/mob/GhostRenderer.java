package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.render.entity.AnimatedMobRenderer;
import net.tslat.aoa3.content.entity.mob.overworld.GhostEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class GhostRenderer extends AnimatedMobRenderer<GhostEntity> {
	public GhostRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel model, float shadowSize) {
		super(renderManager, model, shadowSize);
	}

	@Override
	public void render(GeoModel model, GhostEntity ghost, float partialTicks, RenderType type, PoseStack matrixStackIn, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		int light = ghost.level.getMaxLocalRawBrightness(ghost.blockPosition(), 15 - (int)(((ClientLevel)ghost.level).getSkyDarken(partialTicks) * 15));
		super.render(model, ghost, partialTicks, type, matrixStackIn, renderTypeBuffer, vertexBuilder, packedLightIn, packedOverlayIn, red, green, blue, Math.max(0.05f, 0.75f - (light / 15f)));
	}

	@Override
	public RenderType getRenderType(GhostEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		return RenderType.entityTranslucent(textureLocation);
	}
}

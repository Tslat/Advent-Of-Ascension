package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.model.entity.AoAEntityGeoModel;
import net.tslat.aoa3.client.render.entity.AnimatedMobRenderer;
import net.tslat.aoa3.content.entity.mob.overworld.GhostEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.object.Color;
import software.bernie.geckolib.model.GeoModel;

public class GhostRenderer extends AnimatedMobRenderer<GhostEntity> {
	public GhostRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new AoAEntityGeoModel<>("mob/overworld/ghost", true), -1);
	}

	@Override
	public Color getRenderColor(GhostEntity ghost, float partialTick, int packedLight) {
		int light = 0;

		if (ghost.level instanceof ClientLevel clientLevel)
			light = ghost.level.getMaxLocalRawBrightness(ghost.blockPosition(), 15 - (int)(clientLevel.getSkyDarken(partialTick) * 15));

		return Color.ofRGBA(1, 1, 1, Math.max(0.05f, 0.75f - (light / 15f)));
	}

	@Override
	public RenderType getRenderType(GhostEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(texture);
	}
}

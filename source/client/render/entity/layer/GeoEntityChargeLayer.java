package net.tslat.aoa3.client.render.entity.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.tslat.aoa3.advent.AdventOfAscension;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.geo.render.built.GeoModel;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;

import javax.annotation.Nullable;

public class GeoEntityChargeLayer<T extends Entity & IAnimatable> extends GeoLayerRenderer<T> {
	protected static final ResourceLocation INVULNERABILITY_TEXTURE = AdventOfAscension.id("textures/entity/misc/invulnerability_aura.png");

	public GeoEntityChargeLayer(IGeoRenderer<T> baseRenderer) {
		super(baseRenderer);
	}

	@Override
	public void render(PoseStack matrixStack, MultiBufferSource buffer, int light, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float netHeadYaw, float headPitch) {
		ResourceLocation texture = getLayerTexture(entity, partialTicks);

		if (texture == null)
			return;

		float lerpedTicks = (float)entity.tickCount + partialTicks;
		RenderType renderType = RenderType.energySwirl(texture, adjustU(lerpedTicks) % 1, lerpedTicks * 0.005F % 1);
		GeoModel model = getEntityModel().getModel(getEntityModel().getModelResource(entity));

		getRenderer().render(model, entity, partialTicks, renderType, matrixStack, buffer, buffer.getBuffer(renderType), light, OverlayTexture.NO_OVERLAY, 0.5f, 0.5f, 0.5f, 0.5f);
	}

	@Nullable
	protected ResourceLocation getLayerTexture(T entity, float partialTicks) {
		return entity.isInvulnerable() ? AdventOfAscension.id("textures/entity/misc/invulnerability_aura.png") : null;
	}

	protected float adjustU(float tick) {
		return tick * 0.005f;
	}
}

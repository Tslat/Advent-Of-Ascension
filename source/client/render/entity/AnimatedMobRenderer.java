package net.tslat.aoa3.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.LivingEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AnimatedMobRenderer<T extends LivingEntity & IAnimatable> extends GeoEntityRenderer<T> {
	public AnimatedMobRenderer(EntityRendererManager renderManager, AnimatedGeoModel<T> model, float shadowSize) {
		super(renderManager, model);

		this.shadowRadius = shadowSize;
	}
}

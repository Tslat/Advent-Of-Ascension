package net.tslat.aoa3.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.Entity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class AnimatedProjectileRenderer<T extends Entity & IAnimatable> extends GeoProjectilesRenderer<T> {
	public AnimatedProjectileRenderer(EntityRendererManager renderManager, AnimatedGeoModel<T> model, float shadowSize) {
		super(renderManager, model);

		this.shadowRadius = shadowSize;
	}
}

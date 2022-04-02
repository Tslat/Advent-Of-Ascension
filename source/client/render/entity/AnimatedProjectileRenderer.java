package net.tslat.aoa3.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class AnimatedProjectileRenderer<T extends Entity & IAnimatable> extends GeoProjectilesRenderer<T> {
	public AnimatedProjectileRenderer(EntityRendererProvider.Context renderManager, AnimatedGeoModel<T> model, float shadowSize) {
		super(renderManager, model);

		this.shadowRadius = shadowSize;
	}
}

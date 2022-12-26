package net.tslat.aoa3.client.render.entity;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.LivingEntity;
import net.tslat.aoa3.client.render.entity.layer.GeoEntityChargeLayer;
import software.bernie.geckolib.animatable.GeoEntity;
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
		return 0.013f;//super.getMotionAnimThreshold(animatable);
	}
}

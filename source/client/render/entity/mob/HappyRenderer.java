/*
package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.model.AbstractZombieModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Monster;


public class HappyRenderer extends HumanoidMobRenderer<Monster, HumanoidModel<Monster>> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entity/mob/celeve/happy.png");

	public HappyRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new AbstractZombieModel<Monster>(0, 0, 64, 64) {
			@Override
			public boolean isAggressive(Monster entityIn) {
				return false;
			}
		}, AoAMobs.HAPPY.get().getWidth() / 3f);

		addLayer(new HeldItemLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(Monster entity) {
		return texture;
	}
}*/

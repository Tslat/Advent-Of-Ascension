package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.AbstractZombieModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.common.registration.AoAEntities;

public class HappyRenderer extends BipedRenderer<MonsterEntity, BipedModel<MonsterEntity>> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entities/mobs/celeve/happy.png");

	public HappyRenderer(EntityRendererManager renderManager) {
		super(renderManager, new AbstractZombieModel<MonsterEntity>(0, 0, 64, 64) {
			@Override
			public boolean isAggressive(MonsterEntity entityIn) {
				return false;
			}
		}, AoAEntities.Mobs.HAPPY.get().getWidth() / 3f);

		addLayer(new HeldItemLayer<>(this));
	}

	@Override
	public ResourceLocation getTextureLocation(MonsterEntity entity) {
		return texture;
	}
}
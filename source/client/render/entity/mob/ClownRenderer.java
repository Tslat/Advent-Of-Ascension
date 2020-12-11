package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.AbstractZombieModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.AoAEntities;

@OnlyIn(Dist.CLIENT)
public class ClownRenderer extends BipedRenderer<MonsterEntity, BipedModel<MonsterEntity>> {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entities/mobs/overworld/clown.png");

	public ClownRenderer(EntityRendererManager renderManager) {
		super(renderManager, new AbstractZombieModel<MonsterEntity>(0, 0, 64, 64) {
			@Override
			public boolean isAggressive(MonsterEntity entityIn) {
				return false;
			}
		}, AoAEntities.Mobs.CLOWN.get().getWidth() / 3f);

		addLayer(new HeldItemLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(MonsterEntity entity) {
		return texture;
	}
}
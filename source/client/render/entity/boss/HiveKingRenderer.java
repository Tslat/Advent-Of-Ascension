package net.tslat.aoa3.client.render.entity.boss;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.boss.HiveKingModel;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.content.entity.boss.HiveKingEntity;

public class HiveKingRenderer extends MobRenderer<HiveKingEntity, EntityModel<HiveKingEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/hiveking/hive_king.png");

	public HiveKingRenderer(EntityRendererManager renderManager) {
		super(renderManager, new HiveKingModel(), AoAEntities.Mobs.HIVE_KING.get().getWidth() / 3f);
	}

	@Override
	public ResourceLocation getTextureLocation(HiveKingEntity entity) {
		return TEXTURE;
	}
}
/*
package net.tslat.aoa3.client.render.entity.boss;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.model.entity.boss.HiveKingModel;

import net.tslat.aoa3.content.entity.boss.HiveKingEntity;

public class HiveKingRenderer extends MobRenderer<HiveKingEntity, EntityModel<HiveKingEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/hiveking/hive_king.png");

	public HiveKingRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new HiveKingModel(), AoAMobs.HIVE_KING.get().getWidth() / 3f);
	}

	@Override
	protected void scale(HiveKingEntity hiveKing, PoseStack matrix, float partialTicks) {
		if (hiveKing.tickCount < 1000) {
			if (hiveKing.tickCount == 0 && hiveKing.getY() != -1 && hiveKing.growthPercent == 100)
				hiveKing.growthPercent = hiveKing.getEntityData().get(HiveKingEntity.GROWTH_PERCENT);

			float scaleFactor = hiveKing.getGrowthPercent() / 100f;

			matrix.scale(scaleFactor, scaleFactor, scaleFactor);
		}
	}

	@Override
	public ResourceLocation getTextureLocation(HiveKingEntity entity) {
		return TEXTURE;
	}
}*/

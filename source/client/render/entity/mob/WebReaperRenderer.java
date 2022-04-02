/*
package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.entity.Mob;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.abyss.WebReaperModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;

import net.tslat.aoa3.content.entity.mob.abyss.WebReaperEntity;

public class WebReaperRenderer extends AoAMobRenderer {
	public WebReaperRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new WebReaperModel(), AoAMobs.WEB_REAPER.get().getWidth() / 3f, 1.0f, new ResourceLocation("aoa3", "textures/entity/mob/abyss/web_reaper.png"));
	}

	@Override
	protected void scale(Mob entity, PoseStack matrix, float partialTicks) {
		if (entity instanceof WebReaperEntity) {
			float scale = ((WebReaperEntity)entity).getStageMod();

			matrix.scale(scale, scale, scale);
		}
	}
}*/

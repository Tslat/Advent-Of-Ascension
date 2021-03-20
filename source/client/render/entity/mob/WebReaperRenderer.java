package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.abyss.WebReaperModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.mob.abyss.WebReaperEntity;

public class WebReaperRenderer extends AoAMobRenderer {
	public WebReaperRenderer(EntityRendererManager renderManager) {
		super(renderManager, new WebReaperModel(), AoAEntities.Mobs.WEB_REAPER.get().getWidth() / 3f, 1.0f, new ResourceLocation("aoa3", "textures/entities/mobs/abyss/web_reaper.png"));
	}

	@Override
	protected void scale(MobEntity entity, MatrixStack matrix, float partialTicks) {
		if (entity instanceof WebReaperEntity) {
			float scale = ((WebReaperEntity)entity).getStageMod();

			matrix.scale(scale, scale, scale);
		}
	}
}
/*
package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.entity.Mob;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.nowhere.UriohModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;


public class UriohRenderer extends AoAMobRenderer {
	private static final float shadowScale = AoAMobs.URIOH.get().getWidth() / 3f;
	public UriohRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new UriohModel(), shadowScale, 1.0f, new ResourceLocation("aoa3", "textures/entity/mob/nowhere/urioh.png"));
	}

	@Override
	protected void scale(Mob entity, PoseStack matrix, float partialTicks) {
		float scale = Math.max(0.1f, entity.getHealth() / entity.getMaxHealth());

		matrix.scale(scale, scale, scale);
		shadowRadius = shadowScale * scale;
	}
}*/

/*
package net.tslat.aoa3.client.render.entity.mob;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.entity.Mob;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.precasia.TerradonModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;


public class TerradonRenderer extends AoAMobRenderer {
	private final ResourceLocation invulnTexture = new ResourceLocation("aoa3", "textures/entity/mob/precasia/terradon_invincible.png");

	public TerradonRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new TerradonModel(), AoAMobs.TERRADON.get().getWidth() / 3f, 1.0f, new ResourceLocation("aoa3", "textures/entity/mob/precasia/terradon.png"));
	}

	@Override
	protected void scale(Mob entity, PoseStack matrix, float partialTicks) {
		matrix.scale(2, 2, 2);
	}

	@Override
	public ResourceLocation getTextureLocation(Mob entity) {
		return entity.isInvulnerable() ? invulnTexture : super.getTextureLocation(entity);
	}
}*/

/*
package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.entity.Mob;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.mysterium.RunicGolemModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.client.render.entity.layer.InvulnerabilityAuraRenderLayer;



public class RunicGolemRenderer extends AoAMobRenderer {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entity/mob/mysterium/runic_golem.png");
	private final ResourceLocation invulnTexture = new ResourceLocation("aoa3", "textures/entity/misc/invulnerability_aura.png");

	public RunicGolemRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new RunicGolemModel(0), AoAMobs.RUNIC_GOLEM.get().getWidth() / 3, 1f, new ResourceLocation("aoa3", "textures/entity/mob/mysterium/runic_golem.png"));

		this.addLayer(new InvulnerabilityAuraRenderLayer(this, new RunicGolemModel(0.5f), invulnTexture));
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(Mob runicGolem) {
		return texture;
	}
}*/

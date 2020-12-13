package net.tslat.aoa3.client.render.entity.mob;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.mob.mysterium.RunicGolemModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.client.render.entity.layer.InvulnerabilityAuraRenderLayer;
import net.tslat.aoa3.common.registration.AoAEntities;

import javax.annotation.Nullable;

public class RunicGolemRenderer extends AoAMobRenderer {
	private final ResourceLocation texture = new ResourceLocation("aoa3", "textures/entities/mobs/mysterium/runic_golem.png");
	private final ResourceLocation invulnTexture = new ResourceLocation("aoa3", "textures/entities/misc/invulnerability_aura.png");

	public RunicGolemRenderer(EntityRendererManager renderManager) {
		super(renderManager, new RunicGolemModel(0), AoAEntities.Mobs.RUNIC_GOLEM.get().getWidth() / 3, 1f, new ResourceLocation("aoa3", "textures/entities/mobs/mysterium/runic_golem.png"));

		this.addLayer(new InvulnerabilityAuraRenderLayer(this, new RunicGolemModel(0.5f), invulnTexture));
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(MobEntity runicGolem) {
		return texture;
	}
}
package net.tslat.aoa3.client.render.entity.boss;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.tslat.aoa3.client.model.entity.boss.BaronessModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.client.render.entity.layer.InvulnerabilityAuraRenderLayer;
import net.tslat.aoa3.common.registration.AoAEntities;

import javax.annotation.Nullable;

public class BaronessRenderer extends AoAMobRenderer {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entities/boss/baroness/baroness.png");
	private static final ResourceLocation INVULN_TEXTURE = new ResourceLocation("aoa3", "textures/entities/boss/baroness/invulnerable_baroness.png");
	private static final ResourceLocation AURA_TEXTURE = new ResourceLocation("aoa3", "textures/entities/misc/invulnerability_aura.png");

	public BaronessRenderer(EntityRendererManager renderManager) {
		super(renderManager, new BaronessModel(0), AoAEntities.Mobs.BARONESS.get().getWidth() / 3, 1f, TEXTURE);

		this.addLayer(new InvulnerabilityAuraRenderLayer(this, new BaronessModel(0.5f), AURA_TEXTURE));
	}

	@Nullable
	@Override
	public ResourceLocation getEntityTexture(MobEntity baroness) {
		return baroness.isInvulnerable() ? INVULN_TEXTURE : TEXTURE;
	}
}
/*
package net.tslat.aoa3.client.render.entity.boss;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.entity.Mob;
import net.minecraft.resources.ResourceLocation;
import net.tslat.aoa3.client.model.entity.boss.BaronessModel;
import net.tslat.aoa3.client.render.entity.AoAMobRenderer;
import net.tslat.aoa3.client.render.entity.layer.InvulnerabilityAuraRenderLayer;



public class BaronessRenderer extends AoAMobRenderer {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/baroness/baroness.png");
	private static final ResourceLocation INVULN_TEXTURE = new ResourceLocation("aoa3", "textures/entity/boss/baroness/invulnerable_baroness.png");
	private static final ResourceLocation AURA_TEXTURE = new ResourceLocation("aoa3", "textures/entity/misc/invulnerability_aura.png");

	public BaronessRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new BaronessModel(0), AoAMobs.BARONESS.get().getWidth() / 3, 1f, TEXTURE);

		this.addLayer(new InvulnerabilityAuraRenderLayer(this, new BaronessModel(0.5f), AURA_TEXTURE));
	}

	@Nullable
	@Override
	public ResourceLocation getTextureLocation(Mob baroness) {
		return baroness.isInvulnerable() ? INVULN_TEXTURE : TEXTURE;
	}
}*/

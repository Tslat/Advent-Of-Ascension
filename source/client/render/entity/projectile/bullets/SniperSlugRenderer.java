package net.tslat.aoa3.client.render.entity.projectile.bullets;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.entity.projectile.gun.SniperSlugEntity;

public class SniperSlugRenderer extends ParticleProjectileRenderer<SniperSlugEntity> {
	public SniperSlugRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(SniperSlugEntity entity, float partialTicks) {}
}

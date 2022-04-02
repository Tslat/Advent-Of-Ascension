package net.tslat.aoa3.client.render.entity.projectile.bullets;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.content.entity.projectile.gun.SniperSlugEntity;

public class SniperSlugRenderer extends ParticleProjectileRenderer<SniperSlugEntity> {
	public SniperSlugRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(SniperSlugEntity entity, float partialTicks) {}
}
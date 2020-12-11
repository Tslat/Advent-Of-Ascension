package net.tslat.aoa3.client.render.entity.projectile.bullets;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.entity.projectile.gun.DischargeSlugEntity;

public class DischargeSlugRenderer extends ParticleProjectileRenderer<DischargeSlugEntity> {
	public DischargeSlugRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(DischargeSlugEntity entity, float partialTicks) {}
}

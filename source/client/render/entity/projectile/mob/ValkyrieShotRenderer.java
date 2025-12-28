package net.tslat.aoa3.client.render.entity.projectile.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.mob.ValkyrieShotEntity;
import net.tslat.tme.api.particle.ParticleBuilder;

public class ValkyrieShotRenderer extends ParticleProjectileRenderer<ValkyrieShotEntity> {
	public ValkyrieShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(ValkyrieShotEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.colourTint(0, 0, 0, 1f)
				.spawnClientParticles(entity.level());
	}
}
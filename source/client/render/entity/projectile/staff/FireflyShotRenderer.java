package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.staff.FireflyShotEntity;
import net.tslat.tme.api.particle.ParticleBuilder;

public class FireflyShotRenderer extends ParticleProjectileRenderer<FireflyShotEntity> {
	public FireflyShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(FireflyShotEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.spawnNTimes(8)
				.colourTint(0xDF9900)
				.spawnClientParticles(entity.level());
	}
}
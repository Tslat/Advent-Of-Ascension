package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.blaster.MoonDestroyerShotEntity;
import net.tslat.tme.api.particle.ParticleBuilder;

public class MoonDestroyerShotRenderer extends ParticleProjectileRenderer<MoonDestroyerShotEntity> {
	public MoonDestroyerShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(MoonDestroyerShotEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.spawnNTimes(3)
				.colourTint(0xFF69B4)
				.spawnClientParticles(entity.level());
	}
}
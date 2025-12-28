package net.tslat.aoa3.client.render.entity.projectile.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.mob.BloodballEntity;
import net.tslat.tme.api.particle.ParticleBuilder;

public class BloodballRenderer extends ParticleProjectileRenderer<BloodballEntity> {
	public BloodballRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(BloodballEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.colourTint(entity.level().random.nextFloat() * 0.7f + 0.3f, 0, 0, 1f)
				.spawnClientParticles(entity.level());
	}
}
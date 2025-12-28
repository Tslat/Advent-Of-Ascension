package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.staff.ShyreShotEntity;
import net.tslat.tme.api.particle.ParticleBuilder;

public class ShyreShotRenderer extends ParticleProjectileRenderer<ShyreShotEntity> {
	public ShyreShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(ShyreShotEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			float colourMod = entity.level().random.nextFloat() * 0.7f + 0.3f;

			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, 0.25f, 0))
				.colourTint(0, colourMod, colourMod, 1f)
				.spawnClientParticles(entity.level());
		}
	}
}
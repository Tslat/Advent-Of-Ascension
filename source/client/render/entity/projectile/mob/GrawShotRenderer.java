package net.tslat.aoa3.client.render.entity.projectile.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.mob.GrawShotEntity;
import net.tslat.tme.api.particle.ParticleBuilder;

public class GrawShotRenderer extends ParticleProjectileRenderer<GrawShotEntity> {
	public GrawShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(GrawShotEntity entity, float partialTicks) {
		float colourMod = entity.level().random.nextFloat() * 0.7f + 0.3f;

		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.colourTint(colourMod * 223 / 255f, colourMod * 153 / 255f, 0, 1f)
				.spawnClientParticles(entity.level());
	}
}
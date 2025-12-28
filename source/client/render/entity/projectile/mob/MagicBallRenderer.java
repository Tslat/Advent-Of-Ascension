package net.tslat.aoa3.client.render.entity.projectile.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.mob.MagicBallEntity;
import net.tslat.tme.api.particle.ParticleBuilder;

public class MagicBallRenderer extends ParticleProjectileRenderer<MagicBallEntity> {
	public MagicBallRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(MagicBallEntity entity, float partialTicks) {
		float colourMod = entity.level().random.nextFloat() * 0.7f + 0.3f;

		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.colourTint(colourMod * 193 / 255f, colourMod * 64 / 255f, colourMod * 215 / 255f, 1f)
				.spawnClientParticles(entity.level());
	}
}
package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.blaster.EradicatorShotEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

public class EradicatorShotRenderer extends ParticleProjectileRenderer<EradicatorShotEntity> {
	public EradicatorShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(EradicatorShotEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.spawnNTimes(3)
				.scaleMod(0.5f)
				.colourTint(ColourUtil.BLACK)
				.spawnClientParticles(entity.level());

		for (int i = 0; i < 3; i++) {
			float colourMod = entity.level().random.nextFloat() * 0.7f + 0.3f;

			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
					.scaleMod(0.5f)
					.colourTint(0, colourMod * 153 / 255f, 0, 1f)
					.spawnClientParticles(entity.level());
		}

		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.spawnNTimes(3)
				.scaleMod(0.5f)
				.colourTint(0x1E1D16)
				.spawnClientParticles(entity.level());
	}
}
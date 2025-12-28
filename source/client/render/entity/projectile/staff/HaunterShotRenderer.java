package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.staff.HaunterShotEntity;
import net.tslat.tme.api.particle.ParticleBuilder;

public class HaunterShotRenderer extends ParticleProjectileRenderer<HaunterShotEntity> {
	public HaunterShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(HaunterShotEntity entity, float partialTicks) {
		for (int i = 0; i < 8; i++) {
			float colourMod = entity.level().random.nextFloat() * 0.7f + 0.3f;

			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
					.colourTint(colourMod * 193 / 255f, colourMod * 64 / 255f, colourMod * 215 / 255f, 1f)
					.spawnClientParticles(entity.level());
		}
	}
}
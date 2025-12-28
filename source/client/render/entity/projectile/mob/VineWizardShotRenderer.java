package net.tslat.aoa3.client.render.entity.projectile.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.mob.VineWizardShotEntity;
import net.tslat.tme.api.particle.ParticleBuilder;

public class VineWizardShotRenderer extends ParticleProjectileRenderer<VineWizardShotEntity> {
	public VineWizardShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(VineWizardShotEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.colourTint(0, entity.level().random.nextFloat() * 0.7f + 0.3f, 0, 1f)
				.spawnClientParticles(entity.level());
	}
}
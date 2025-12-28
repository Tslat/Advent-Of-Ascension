package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.staff.RosidianShotEntity;
import net.tslat.tme.api.particle.ParticleBuilder;

public class RosidianShotRenderer extends ParticleProjectileRenderer<RosidianShotEntity> {
	public RosidianShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(RosidianShotEntity entity, float partialTicks) {
		if (entity.getDeltaMovement().y() > 0.98) {
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_SWIRL.get(), entity.position().add(0, 0.25f, 0))
					.spawnNTimes(8)
					.colourTint(0x926239)
					.spawnClientParticles(entity.level());
		}
		else {
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, 0.25f, 0))
					.colourTint(0, entity.level().random.nextFloat() * 0.7f + 0.3f, 0, 1f)
					.spawnClientParticles(entity.level());
		}
	}
}
package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.tme.api.particle.ParticleBuilder;

public class TangleFallRenderer extends ParticleProjectileRenderer<NonPhysicalWeaponProjectile> {
	public TangleFallRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(NonPhysicalWeaponProjectile entity, float partialTicks) {
		for (int i = 0; i < 8; i++) {
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
					.colourTint(0, entity.level().random.nextFloat() * 0.7f + 0.3f, 0, 1f)
					.spawnClientParticles(entity.level());
		}
	}
}
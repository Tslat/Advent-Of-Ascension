package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.base.PhysicalWeaponProjectile;
import net.tslat.tme.api.particle.ParticleBuilder;

public class EnergyShotRenderer extends ParticleProjectileRenderer<PhysicalWeaponProjectile> {
	public EnergyShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(PhysicalWeaponProjectile entity, float partialTicks) {
		for (int i = 0; i < 9; i++) {
			float colourMod = entity.level().random.nextFloat() * 0.7f + 0.3f;

			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, (((entity.tickCount + i / 3f) * 0.12f) % 6) - 3, 0))
					.colourTint(0, colourMod, colourMod, 1f)
					.spawnClientParticles(entity.level());
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, (((entity.tickCount + i / 3f) * 0.12f + 3f) % 6) - 3, 0))
					.colourTint(0, 0, 0, 1f)
					.spawnClientParticles(entity.level());
		}
	}
}
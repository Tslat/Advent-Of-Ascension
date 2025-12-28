package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.tme.api.particle.ParticleBuilder;

public class SoulDrainerRenderer extends ParticleProjectileRenderer<NonPhysicalWeaponProjectile> {
	public SoulDrainerRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(NonPhysicalWeaponProjectile entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
					.colourTint(entity.level().random.nextFloat() * 0.7f + 0.3f, 0, 0, 1f)
					.spawnClientParticles(entity.level());
		}
	}
}
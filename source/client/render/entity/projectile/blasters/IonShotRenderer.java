package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

public class IonShotRenderer extends ParticleProjectileRenderer<NonPhysicalWeaponProjectile> {
	public IonShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(NonPhysicalWeaponProjectile entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().add(0, 2, 0))
				.spawnNTimes(3)
				.colourTint(ColourUtil.CYAN)
				.spawnClientParticles(entity.level());
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.spawnNTimes(3)
				.colourTint(ColourUtil.WHITE)
				.spawnClientParticles(entity.level());
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().subtract(0, 2, 0))
				.spawnNTimes(3)
				.colourTint(ColourUtil.CYAN)
				.spawnClientParticles(entity.level());
	}
}
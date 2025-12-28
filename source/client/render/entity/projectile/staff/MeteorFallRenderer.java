package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

public class MeteorFallRenderer extends ParticleProjectileRenderer<NonPhysicalWeaponProjectile> {
	public MeteorFallRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(NonPhysicalWeaponProjectile entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.ignoreDistanceAndLimits()
				.spawnNTimes(3)
				.colourTint(ColourUtil.RED)
				.spawnClientParticles(entity.level());
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().subtract(0, 0.3d, 0))
				.ignoreDistanceAndLimits()
				.spawnNTimes(3)
				.colourTint(0xDF9900)
				.spawnClientParticles(entity.level());
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position().subtract(0, 0.6d, 0))
				.ignoreDistanceAndLimits()
				.spawnNTimes(3)
				.colourTint(ColourUtil.YELLOW)
				.spawnClientParticles(entity.level());
	}
}
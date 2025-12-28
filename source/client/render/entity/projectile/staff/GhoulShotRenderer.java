package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;

public class GhoulShotRenderer extends ParticleProjectileRenderer<NonPhysicalWeaponProjectile> {
	public GhoulShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(NonPhysicalWeaponProjectile entity, float partialTicks) {
		for (int i = 0; i < 8; i++) {
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
					.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
					.colourTint(0xC140D7)
					.spawnClientParticles(entity.level());
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
					.scaleMod(0.5f)
					.lifespan(Mth.ceil(20 / RandomUtil.valueBetween(0.2f, 1)))
					.colourTint(0xF7EF00)
					.spawnClientParticles(entity.level());
		}
	}
}
package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.base.NonPhysicalWeaponProjectile;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;

public class LightBlasterShotRenderer extends ParticleProjectileRenderer<NonPhysicalWeaponProjectile> {
	public LightBlasterShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(NonPhysicalWeaponProjectile entity, float partialTicks) {
		for (int i = 0; i < 9; i++) {
			float colourMod = (float)RandomUtil.valueBetween(0.3f, 1f);

			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(),  entity.position().add(0, (((entity.tickCount + i / 3f) * 0.12f) % 6) - 3, 0))
					.colourTint(0, (float)RandomUtil.valueBetween(0.3f, 1f), 0, 1f)
					.spawnClientParticles(entity.level());
			ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(),  entity.position().add(0, (((entity.tickCount + i / 3f) * 0.12f + 3f) % 6) - 3, 0))
					.colourTint(colourMod, colourMod, colourMod, 1f)
					.spawnClientParticles(entity.level());
		}
	}
}
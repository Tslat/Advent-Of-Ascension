package net.tslat.aoa3.client.render.entity.projectile.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.mob.RedGuardianShotEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

public class RedGuardianShotRenderer extends ParticleProjectileRenderer<RedGuardianShotEntity> {
	public RedGuardianShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(RedGuardianShotEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.colourTint(ColourUtil.RED)
				.spawnClientParticles(entity.level());
	}
}
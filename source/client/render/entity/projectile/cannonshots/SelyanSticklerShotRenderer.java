package net.tslat.aoa3.client.render.entity.projectile.cannonshots;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.cannon.SelyanSticklerShotEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

public class SelyanSticklerShotRenderer extends ParticleProjectileRenderer<SelyanSticklerShotEntity> {
	public SelyanSticklerShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(SelyanSticklerShotEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.spawnNTimes(14)
				.colourTint(ColourUtil.GREEN)
				.spawnClientParticles(entity.level());
	}
}
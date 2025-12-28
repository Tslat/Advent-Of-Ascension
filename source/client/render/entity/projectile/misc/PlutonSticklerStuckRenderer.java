package net.tslat.aoa3.client.render.entity.projectile.misc;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.misc.PlutonSticklerStuckEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

public class PlutonSticklerStuckRenderer extends ParticleProjectileRenderer<PlutonSticklerStuckEntity> {

	public PlutonSticklerStuckRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(PlutonSticklerStuckEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_SWIRL.get(), entity.position())
				.spawnNTimes(7)
				.colourTint(ColourUtil.YELLOW)
				.spawnClientParticles(entity.level());
	}
}
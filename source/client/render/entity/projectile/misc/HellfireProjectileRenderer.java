package net.tslat.aoa3.client.render.entity.projectile.misc;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.misc.HellfireProjectileEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

public class HellfireProjectileRenderer extends ParticleProjectileRenderer<HellfireProjectileEntity> {
	public HellfireProjectileRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(HellfireProjectileEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.spawnNTimes(5)
				.colourTint(ColourUtil.RED)
				.spawnClientParticles(entity.level());
	}
}
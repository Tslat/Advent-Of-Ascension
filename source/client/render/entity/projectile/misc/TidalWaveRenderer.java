package net.tslat.aoa3.client.render.entity.projectile.misc;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.particles.ParticleTypes;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.object.entity.projectile.misc.TidalWaveEntity;

public class TidalWaveRenderer extends ParticleProjectileRenderer<TidalWaveEntity> {
	public TidalWaveRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(TidalWaveEntity entity, float partialTicks) {
		entity.level.addAlwaysVisibleParticle(ParticleTypes.BUBBLE, entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
	}
}

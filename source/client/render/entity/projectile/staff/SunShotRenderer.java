package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.particles.ParticleTypes;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.staff.SunShotEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.tme.api.particle.ParticleBuilder;

public class SunShotRenderer extends ParticleProjectileRenderer<SunShotEntity> {
	public SunShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(SunShotEntity entity, float partialTicks) {
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.scaleMod(2)
				.colourTint(ColourUtil.YELLOW)
				.spawnClientParticles(entity.level());
		ParticleBuilder.forPositions(AoAParticleTypes.GENERIC_DUST.get(), entity.position())
				.scaleMod(2)
				.colourTint(ColourUtil.RED)
				.spawnClientParticles(entity.level());

		if (!Minecraft.getInstance().isPaused()) {
			ParticleBuilder.forRandomPosInSphere(ParticleTypes.FLAME, entity.position(), 0.5f)
					.spawnClientParticles(entity.level());
		}
	}
}
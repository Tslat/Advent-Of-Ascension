package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.blaster.ConfettiShotEntity;
import net.tslat.aoa3.library.misc.CustomisableParticleType;

public class ConfettiShotRenderer extends ParticleProjectileRenderer<ConfettiShotEntity> {
	public ConfettiShotRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(ConfettiShotEntity entity, float partialTicks) {
		for (int i = 0; i < 8; i++) {
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.RAINBOW_SPARKLER.get(), 0.15f, 10, 0), entity.getPosX(), entity.getPosY(), entity.getPosZ(), 0, -0.05, 0);
		}
	}
}

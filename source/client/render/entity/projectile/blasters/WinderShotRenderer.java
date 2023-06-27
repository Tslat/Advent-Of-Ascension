package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.blaster.WinderShotEntity;
import net.tslat.aoa3.util.ColourUtil;
import net.tslat.smartbrainlib.util.RandomUtil;

public class WinderShotRenderer extends ParticleProjectileRenderer<WinderShotEntity> {
	public WinderShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(WinderShotEntity entity, float partialTicks) {
		if (RandomUtil.fiftyFifty()) {
			for (int i = 0; i < 3; i++) {
				entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.RED), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			}
		}
		else {
			for (int i = 0; i < 3; i++) {
				entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.YELLOW), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			}
		}
	}
}
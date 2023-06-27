/*
package net.tslat.aoa3.client.render.entity.projectile.mob;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.content.entity.projectile.mob.ShadowlordShotEntity;
import net.tslat.aoa3.util.ColourUtil;

public class ShadowlordShotRenderer extends ParticleProjectileRenderer<ShadowlordShotEntity> {
	public ShadowlordShotRenderer(final EntityRendererProvider.Context manager) {
		super(manager);
	}

	@Override
	protected void addParticles(ShadowlordShotEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			if (entity.toggle) {
				entity.counter++;

				if (entity.counter == 3)
					entity.toggle = false;
			}

			if (!entity.toggle) {
				entity.counter--;

				if (entity.counter == 0)
					entity.toggle = true;
			}
		}

		if (entity.toggle) {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, ColourUtil.RGB(193, 64, 215)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
		else {
			entity.level().addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, ColourUtil.BLACK), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}*/

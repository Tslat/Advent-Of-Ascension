package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.staff.RosidianShotEntity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class RosidianShotRenderer extends ParticleProjectileRenderer<RosidianShotEntity> {
	public RosidianShotRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(RosidianShotEntity entity, float partialTicks) {
		if (entity.getDeltaMovement().y() > 0.98) {
			for (int i = 0; i < 8; i++) {
				entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, NumberUtil.RGB(146, 98, 57)), entity.getX(), entity.getY() + 0.25d, entity.getZ(), 0, 0, 0);
			}
		}
		else {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, NumberUtil.RGB(0, 255, 0)), entity.getX(), entity.getY() + 0.25d, entity.getZ(), 0, 0, 0);
		}
	}
}

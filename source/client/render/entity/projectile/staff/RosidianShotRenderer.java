package net.tslat.aoa3.client.render.entity.projectile.staff;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.staff.RosidianShotEntity;
import net.tslat.aoa3.library.misc.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class RosidianShotRenderer extends ParticleProjectileRenderer<RosidianShotEntity> {
	public RosidianShotRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(RosidianShotEntity entity, float partialTicks) {
		if (entity.getMotion().getY() > 0.98) {
			for (int i = 0; i < 8; i++) {
				entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, NumberUtil.RGB(146, 98, 57)), entity.getPosX(), entity.getPosY() + 0.25d, entity.getPosZ(), 0, 0, 0);
			}
		}
		else {
			entity.world.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 1, 3, NumberUtil.RGB(0, 255, 0)), entity.getPosX(), entity.getPosY() + 0.25d, entity.getPosZ(), 0, 0, 0);
		}
	}
}

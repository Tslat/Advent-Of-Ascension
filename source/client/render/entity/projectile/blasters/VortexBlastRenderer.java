package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.blaster.VortexBlastEntity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.RandomUtil;

public class VortexBlastRenderer extends ParticleProjectileRenderer<VortexBlastEntity> {
	public VortexBlastRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(VortexBlastEntity entity, float partialTicks) {
		if (RandomUtil.fiftyFifty()) {
			for (int i = 0; i < 3; i++) {
				entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.25f, 3, NumberUtil.RGB(255, 255, 0)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			}
		}
		else {
			for (int i = 0; i < 3; i++) {
				entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, NumberUtil.RGB(255, 255, 255)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			}
		}
	}
}

package net.tslat.aoa3.client.render.entity.projectile.mob;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.mob.LightRunicGuardianShotEntity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class RunicGuardianShotLightRenderer extends ParticleProjectileRenderer<LightRunicGuardianShotEntity> {
	public RunicGuardianShotLightRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(LightRunicGuardianShotEntity entity, float partialTicks) {
		entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 3, NumberUtil.RGB(0, 255, 255)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
	}
}

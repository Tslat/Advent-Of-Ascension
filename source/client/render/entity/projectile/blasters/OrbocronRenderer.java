package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.blaster.OrbocronEntity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class OrbocronRenderer extends ParticleProjectileRenderer<OrbocronEntity> {
	public OrbocronRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(OrbocronEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, NumberUtil.RGB(130, 0, 178)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}

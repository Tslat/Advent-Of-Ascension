package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.blaster.SeaocronEntity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class SeaocronRenderer extends ParticleProjectileRenderer<SeaocronEntity> {
	public SeaocronRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(SeaocronEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, NumberUtil.RGB(0, 255, 255)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SWIRLY.get(), 1, 3, NumberUtil.RGB(0, 255, 0)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}

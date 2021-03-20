package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.entity.projectile.blaster.SoulStormEntity;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.util.NumberUtil;

public class SoulStormRenderer extends ParticleProjectileRenderer<SoulStormEntity> {
	public SoulStormRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(SoulStormEntity entity, float partialTicks) {
		for (int i = 0; i < 6; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.25f, 3, NumberUtil.RGB(0, 255, 255)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.25f, 3, NumberUtil.RGB(0, 0, 255)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}

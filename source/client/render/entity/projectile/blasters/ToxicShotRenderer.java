package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.object.entity.projectile.blaster.ToxicShotEntity;
import net.tslat.aoa3.util.ColourUtil;

public class ToxicShotRenderer extends ParticleProjectileRenderer<ToxicShotEntity> {
	public ToxicShotRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(ToxicShotEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 20, ColourUtil.RGB(51, 102, 0)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 0.5f, 20, ColourUtil.RGB(30, 29, 22)), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
		}
	}
}
package net.tslat.aoa3.client.render.entity.projectile.blasters;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.tslat.aoa3.client.render.entity.projectile.ParticleProjectileRenderer;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.object.entity.projectile.blaster.RainbowShotEntity;
import net.tslat.aoa3.util.ColourUtil;

public class RainbowShotRenderer extends ParticleProjectileRenderer<RainbowShotEntity> {
	public RainbowShotRenderer(final EntityRendererManager manager) {
		super(manager);
	}

	@Override
	protected void addParticles(RainbowShotEntity entity, float partialTicks) {
		for (int i = 0; i < 3; i++) {
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 20, ColourUtil.RED), entity.getX(), entity.getY() + 1.5, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 20, ColourUtil.RGB(223, 153, 0)), entity.getX(), entity.getY() + 1.0, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 20, ColourUtil.YELLOW), entity.getX(), entity.getY() + 0.5, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 20, ColourUtil.GREEN), entity.getX(), entity.getY(), entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 20, ColourUtil.CYAN), entity.getX(), entity.getY() - 0.5, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 20, ColourUtil.BLUE), entity.getX(), entity.getY() - 1.0, entity.getZ(), 0, 0, 0);
			entity.level.addParticle(new CustomisableParticleType.Data(AoAParticleTypes.SPARKLER.get(), 1, 20, ColourUtil.RGB(193, 64, 215)), entity.getX(), entity.getY() - 1.5, entity.getZ(), 0, 0, 0);
		}
	}
}